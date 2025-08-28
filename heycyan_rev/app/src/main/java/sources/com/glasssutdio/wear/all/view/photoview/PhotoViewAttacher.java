package com.glasssutdio.wear.all.view.photoview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.LruCache;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.OverScroller;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class PhotoViewAttacher implements View.OnTouchListener, View.OnLayoutChangeListener {
    private static float DEFAULT_MAX_SCALE = 3.0f;
    private static float DEFAULT_MID_SCALE = 1.75f;
    private static float DEFAULT_MIN_SCALE = 1.0f;
    private static int DEFAULT_ZOOM_DURATION = 200;
    private static final int HORIZONTAL_EDGE_BOTH = 2;
    private static final int HORIZONTAL_EDGE_LEFT = 0;
    private static final int HORIZONTAL_EDGE_NONE = -1;
    private static final int HORIZONTAL_EDGE_RIGHT = 1;
    private static int SINGLE_TOUCH = 1;
    private static final float UPDATE_THRESHOLD = 0.01f;
    private static final int VERTICAL_EDGE_BOTH = 2;
    private static final int VERTICAL_EDGE_BOTTOM = 1;
    private static final int VERTICAL_EDGE_NONE = -1;
    private static final int VERTICAL_EDGE_TOP = 0;
    private ExecutorService executorService;
    private float mBaseRotation;
    private FlingRunnable mCurrentFlingRunnable;
    private GestureDetector mGestureDetector;
    private ImageView mImageView;
    private View.OnLongClickListener mLongClickListener;
    private OnMatrixChangedListener mMatrixChangeListener;
    private View.OnClickListener mOnClickListener;
    private OnViewDragListener mOnViewDragListener;
    private OnOutsidePhotoTapListener mOutsidePhotoTapListener;
    private OnPhotoTapListener mPhotoTapListener;
    private OnScaleChangedListener mScaleChangeListener;
    private CustomGestureDetector mScaleDragDetector;
    private OnSingleFlingListener mSingleFlingListener;
    private OnViewTapListener mViewTapListener;
    private Handler mainHandler;
    private LruCache<Float, Bitmap> scaleBitmapCache;
    private Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
    private int mZoomDuration = DEFAULT_ZOOM_DURATION;
    private float mMinScale = DEFAULT_MIN_SCALE;
    private float mMidScale = DEFAULT_MID_SCALE;
    private float mMaxScale = DEFAULT_MAX_SCALE;
    private boolean mAllowParentInterceptOnEdge = true;
    private boolean mBlockParentIntercept = false;
    private final Matrix mBaseMatrix = new Matrix();
    private final Matrix mDrawMatrix = new Matrix();
    private final Matrix mSuppMatrix = new Matrix();
    private final RectF mDisplayRect = new RectF();
    private final float[] mMatrixValues = new float[9];
    private int mHorizontalScrollEdge = 2;
    private int mVerticalScrollEdge = 2;
    private boolean mZoomEnabled = true;
    private ImageView.ScaleType mScaleType = ImageView.ScaleType.FIT_CENTER;
    private OnGestureListener onGestureListener = new OnGestureListener() { // from class: com.glasssutdio.wear.all.view.photoview.PhotoViewAttacher.1
        @Override // com.glasssutdio.wear.all.view.photoview.OnGestureListener
        public void onDrag(float dx, float dy) {
            if (PhotoViewAttacher.this.mScaleDragDetector.isScaling()) {
                return;
            }
            if (PhotoViewAttacher.this.mOnViewDragListener != null) {
                PhotoViewAttacher.this.mOnViewDragListener.onDrag(dx, dy);
            }
            PhotoViewAttacher.this.mSuppMatrix.postTranslate(dx, dy);
            PhotoViewAttacher.this.checkAndDisplayMatrix();
            ViewParent parent = PhotoViewAttacher.this.mImageView.getParent();
            if (!PhotoViewAttacher.this.mAllowParentInterceptOnEdge || PhotoViewAttacher.this.mScaleDragDetector.isScaling() || PhotoViewAttacher.this.mBlockParentIntercept) {
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
            } else if ((PhotoViewAttacher.this.mHorizontalScrollEdge == 2 || ((PhotoViewAttacher.this.mHorizontalScrollEdge == 0 && dx >= 1.0f) || ((PhotoViewAttacher.this.mHorizontalScrollEdge == 1 && dx <= -1.0f) || ((PhotoViewAttacher.this.mVerticalScrollEdge == 0 && dy >= 1.0f) || (PhotoViewAttacher.this.mVerticalScrollEdge == 1 && dy <= -1.0f))))) && parent != null) {
                parent.requestDisallowInterceptTouchEvent(false);
            }
        }

        @Override // com.glasssutdio.wear.all.view.photoview.OnGestureListener
        public void onFling(float startX, float startY, float velocityX, float velocityY) {
            PhotoViewAttacher photoViewAttacher = PhotoViewAttacher.this;
            PhotoViewAttacher photoViewAttacher2 = PhotoViewAttacher.this;
            photoViewAttacher.mCurrentFlingRunnable = photoViewAttacher2.new FlingRunnable(photoViewAttacher2.mImageView.getContext());
            FlingRunnable flingRunnable = PhotoViewAttacher.this.mCurrentFlingRunnable;
            PhotoViewAttacher photoViewAttacher3 = PhotoViewAttacher.this;
            int imageViewWidth = photoViewAttacher3.getImageViewWidth(photoViewAttacher3.mImageView);
            PhotoViewAttacher photoViewAttacher4 = PhotoViewAttacher.this;
            flingRunnable.fling(imageViewWidth, photoViewAttacher4.getImageViewHeight(photoViewAttacher4.mImageView), (int) velocityX, (int) velocityY);
            PhotoViewAttacher.this.mImageView.post(PhotoViewAttacher.this.mCurrentFlingRunnable);
        }

        @Override // com.glasssutdio.wear.all.view.photoview.OnGestureListener
        public void onScale(float scaleFactor, float focusX, float focusY) {
            onScale(scaleFactor, focusX, focusY, 0.0f, 0.0f);
        }

        @Override // com.glasssutdio.wear.all.view.photoview.OnGestureListener
        public void onScale(float scaleFactor, float focusX, float focusY, float dx, float dy) {
            if (PhotoViewAttacher.this.getScale() < PhotoViewAttacher.this.mMaxScale || scaleFactor < 1.0f) {
                if (PhotoViewAttacher.this.mScaleChangeListener != null) {
                    PhotoViewAttacher.this.mScaleChangeListener.onScaleChange(scaleFactor, focusX, focusY);
                }
                PhotoViewAttacher.this.mSuppMatrix.postScale(scaleFactor, scaleFactor, focusX, focusY);
                PhotoViewAttacher.this.mSuppMatrix.postTranslate(dx, dy);
                PhotoViewAttacher.this.checkAndDisplayMatrix();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public boolean needUpdateDisplayMatrix() {
        return true;
    }

    public PhotoViewAttacher(ImageView imageView) {
        this.mImageView = imageView;
        imageView.setOnTouchListener(this);
        imageView.addOnLayoutChangeListener(this);
        if (imageView.isInEditMode()) {
            return;
        }
        this.scaleBitmapCache = new LruCache<Float, Bitmap>(((int) (Runtime.getRuntime().maxMemory() / 1024)) / 8) { // from class: com.glasssutdio.wear.all.view.photoview.PhotoViewAttacher.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.util.LruCache
            public int sizeOf(Float key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
        this.executorService = Executors.newFixedThreadPool(2);
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.mBaseRotation = 0.0f;
        this.mScaleDragDetector = new CustomGestureDetector(imageView.getContext(), this.onGestureListener);
        GestureDetector gestureDetector = new GestureDetector(imageView.getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.glasssutdio.wear.all.view.photoview.PhotoViewAttacher.3
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent e) {
                if (PhotoViewAttacher.this.mLongClickListener != null) {
                    PhotoViewAttacher.this.mLongClickListener.onLongClick(PhotoViewAttacher.this.mImageView);
                }
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (PhotoViewAttacher.this.mSingleFlingListener == null || PhotoViewAttacher.this.getScale() > PhotoViewAttacher.DEFAULT_MIN_SCALE || e1.getPointerCount() > PhotoViewAttacher.SINGLE_TOUCH || e2.getPointerCount() > PhotoViewAttacher.SINGLE_TOUCH) {
                    return false;
                }
                return PhotoViewAttacher.this.mSingleFlingListener.onFling(e1, e2, velocityX, velocityY);
            }
        });
        this.mGestureDetector = gestureDetector;
        gestureDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() { // from class: com.glasssutdio.wear.all.view.photoview.PhotoViewAttacher.4
            @Override // android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }

            @Override // android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (PhotoViewAttacher.this.mOnClickListener != null) {
                    PhotoViewAttacher.this.mOnClickListener.onClick(PhotoViewAttacher.this.mImageView);
                }
                RectF displayRect = PhotoViewAttacher.this.getDisplayRect();
                float x = e.getX();
                float y = e.getY();
                if (PhotoViewAttacher.this.mViewTapListener != null) {
                    PhotoViewAttacher.this.mViewTapListener.onViewTap(PhotoViewAttacher.this.mImageView, x, y);
                }
                if (displayRect == null) {
                    return false;
                }
                if (!displayRect.contains(x, y)) {
                    if (PhotoViewAttacher.this.mOutsidePhotoTapListener == null) {
                        return false;
                    }
                    PhotoViewAttacher.this.mOutsidePhotoTapListener.onOutsidePhotoTap(PhotoViewAttacher.this.mImageView);
                    return false;
                }
                float fWidth = (x - displayRect.left) / displayRect.width();
                float fHeight = (y - displayRect.top) / displayRect.height();
                if (PhotoViewAttacher.this.mPhotoTapListener == null) {
                    return true;
                }
                PhotoViewAttacher.this.mPhotoTapListener.onPhotoTap(PhotoViewAttacher.this.mImageView, fWidth, fHeight);
                return true;
            }

            @Override // android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent ev) {
                try {
                    float scale = PhotoViewAttacher.this.getScale();
                    float x = ev.getX();
                    float y = ev.getY();
                    if (scale < PhotoViewAttacher.this.getMediumScale()) {
                        PhotoViewAttacher photoViewAttacher = PhotoViewAttacher.this;
                        photoViewAttacher.setScale(photoViewAttacher.getMediumScale(), x, y, true);
                    } else if (scale >= PhotoViewAttacher.this.getMediumScale() && scale < PhotoViewAttacher.this.getMaximumScale()) {
                        PhotoViewAttacher photoViewAttacher2 = PhotoViewAttacher.this;
                        photoViewAttacher2.setScale(photoViewAttacher2.getMaximumScale(), x, y, true);
                    } else {
                        PhotoViewAttacher photoViewAttacher3 = PhotoViewAttacher.this;
                        photoViewAttacher3.setScale(photoViewAttacher3.getMinimumScale(), x, y, true);
                    }
                } catch (ArrayIndexOutOfBoundsException unused) {
                }
                return true;
            }
        });
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener newOnDoubleTapListener) {
        this.mGestureDetector.setOnDoubleTapListener(newOnDoubleTapListener);
    }

    public void setOnScaleChangeListener(OnScaleChangedListener onScaleChangeListener) {
        this.mScaleChangeListener = onScaleChangeListener;
    }

    public void setOnSingleFlingListener(OnSingleFlingListener onSingleFlingListener) {
        this.mSingleFlingListener = onSingleFlingListener;
    }

    @Deprecated
    public boolean isZoomEnabled() {
        return this.mZoomEnabled;
    }

    public RectF getDisplayRect() {
        checkMatrixBounds();
        return getDisplayRect(getDrawMatrix());
    }

    public boolean setDisplayMatrix(Matrix finalMatrix) {
        if (finalMatrix == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        if (this.mImageView.getDrawable() == null) {
            return false;
        }
        this.mSuppMatrix.set(finalMatrix);
        checkAndDisplayMatrix();
        return true;
    }

    public void setBaseRotation(final float degrees) {
        this.mBaseRotation = degrees % 360.0f;
        update();
        setRotationBy(this.mBaseRotation);
        checkAndDisplayMatrix();
    }

    public void setRotationTo(float degrees) {
        this.mSuppMatrix.setRotate(degrees % 360.0f);
        checkAndDisplayMatrix();
    }

    public void setRotationBy(float degrees) {
        this.mSuppMatrix.postRotate(degrees % 360.0f);
        checkAndDisplayMatrix();
    }

    public float getMinimumScale() {
        return this.mMinScale;
    }

    public float getMediumScale() {
        return this.mMidScale;
    }

    public float getMaximumScale() {
        return this.mMaxScale;
    }

    public float getScale() {
        return (float) Math.sqrt(((float) Math.pow(getValue(this.mSuppMatrix, 0), 2.0d)) + ((float) Math.pow(getValue(this.mSuppMatrix, 3), 2.0d)));
    }

    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (left == oldLeft && top == oldTop && right == oldRight && bottom == oldBottom) {
            return;
        }
        updateBaseMatrix(this.mImageView.getDrawable());
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b2  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouch(View v, MotionEvent ev) {
        boolean z;
        CustomGestureDetector customGestureDetector;
        boolean z2;
        GestureDetector gestureDetector;
        RectF displayRect;
        boolean z3 = false;
        if (!this.mZoomEnabled || !Util.hasDrawable((ImageView) v)) {
            return false;
        }
        int action = ev.getAction();
        if (action == 0) {
            ViewParent parent = v.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            cancelFling();
        } else {
            if (action == 1 || action == 3) {
                if (getScale() < this.mMinScale) {
                    RectF displayRect2 = getDisplayRect();
                    if (displayRect2 != null) {
                        v.post(new AnimatedZoomRunnable(getScale(), this.mMinScale, displayRect2.centerX(), displayRect2.centerY()));
                        z = true;
                    }
                } else if (getScale() > this.mMaxScale && (displayRect = getDisplayRect()) != null) {
                    v.post(new AnimatedZoomRunnable(getScale(), this.mMaxScale, displayRect.centerX(), displayRect.centerY()));
                    z = true;
                }
            }
            customGestureDetector = this.mScaleDragDetector;
            if (customGestureDetector == null) {
                boolean zIsScaling = customGestureDetector.isScaling();
                boolean zIsDragging = this.mScaleDragDetector.isDragging();
                boolean zOnTouchEvent = this.mScaleDragDetector.onTouchEvent(ev);
                boolean z4 = (zIsScaling || this.mScaleDragDetector.isScaling()) ? false : true;
                boolean z5 = (zIsDragging || this.mScaleDragDetector.isDragging()) ? false : true;
                if (z4 && z5) {
                    z3 = true;
                }
                this.mBlockParentIntercept = z3;
                z2 = zOnTouchEvent;
            } else {
                z2 = z;
            }
            gestureDetector = this.mGestureDetector;
            if (gestureDetector == null && gestureDetector.onTouchEvent(ev)) {
                return true;
            }
        }
        z = false;
        customGestureDetector = this.mScaleDragDetector;
        if (customGestureDetector == null) {
        }
        gestureDetector = this.mGestureDetector;
        return gestureDetector == null ? z2 : z2;
    }

    public void setAllowParentInterceptOnEdge(boolean allow) {
        this.mAllowParentInterceptOnEdge = allow;
    }

    public void setMinimumScale(float minimumScale) {
        Util.checkZoomLevels(minimumScale, this.mMidScale, this.mMaxScale);
        this.mMinScale = minimumScale;
    }

    public void setMediumScale(float mediumScale) {
        Util.checkZoomLevels(this.mMinScale, mediumScale, this.mMaxScale);
        this.mMidScale = mediumScale;
    }

    public void setMaximumScale(float maximumScale) {
        Util.checkZoomLevels(this.mMinScale, this.mMidScale, maximumScale);
        this.mMaxScale = maximumScale;
    }

    public void setScaleLevels(float minimumScale, float mediumScale, float maximumScale) {
        Util.checkZoomLevels(minimumScale, mediumScale, maximumScale);
        this.mMinScale = minimumScale;
        this.mMidScale = mediumScale;
        this.mMaxScale = maximumScale;
    }

    public void setOnLongClickListener(View.OnLongClickListener listener) {
        this.mLongClickListener = listener;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.mOnClickListener = listener;
    }

    public void setOnMatrixChangeListener(OnMatrixChangedListener listener) {
        this.mMatrixChangeListener = listener;
    }

    public void setOnPhotoTapListener(OnPhotoTapListener listener) {
        this.mPhotoTapListener = listener;
    }

    public void setOnOutsidePhotoTapListener(OnOutsidePhotoTapListener mOutsidePhotoTapListener) {
        this.mOutsidePhotoTapListener = mOutsidePhotoTapListener;
    }

    public void setOnViewTapListener(OnViewTapListener listener) {
        this.mViewTapListener = listener;
    }

    public void setOnViewDragListener(OnViewDragListener listener) {
        this.mOnViewDragListener = listener;
    }

    public void setScale(float scale) {
        setScale(scale, false);
    }

    public void setScale(float scale, boolean animate) {
        setScale(scale, this.mImageView.getRight() / 2, this.mImageView.getBottom() / 2, animate);
    }

    public void setScale(float scale, float focalX, float focalY, boolean animate) {
        if (scale < this.mMinScale || scale > this.mMaxScale) {
            throw new IllegalArgumentException("Scale must be within the range of minScale and maxScale");
        }
        if (animate) {
            this.mImageView.post(new AnimatedZoomRunnable(getScale(), scale, focalX, focalY));
        } else {
            this.mSuppMatrix.setScale(scale, scale, focalX, focalY);
            checkAndDisplayMatrix();
        }
    }

    public void setZoomInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        if (!Util.isSupportedScaleType(scaleType) || scaleType == this.mScaleType) {
            return;
        }
        this.mScaleType = scaleType;
        update();
    }

    public boolean isZoomable() {
        return this.mZoomEnabled;
    }

    public void setZoomable(boolean zoomable) {
        this.mZoomEnabled = zoomable;
        update();
    }

    public void update() {
        if (this.mZoomEnabled) {
            updateBaseMatrix(this.mImageView.getDrawable());
        } else {
            resetMatrix();
        }
    }

    public void getDisplayMatrix(Matrix matrix) {
        matrix.set(getDrawMatrix());
    }

    public void getSuppMatrix(Matrix matrix) {
        matrix.set(this.mSuppMatrix);
    }

    private Matrix getDrawMatrix() {
        this.mDrawMatrix.set(this.mBaseMatrix);
        this.mDrawMatrix.postConcat(this.mSuppMatrix);
        return this.mDrawMatrix;
    }

    public Matrix getImageMatrix() {
        return this.mDrawMatrix;
    }

    public void setZoomTransitionDuration(int milliseconds) {
        this.mZoomDuration = milliseconds;
    }

    private float getValue(Matrix matrix, int whichValue) {
        matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[whichValue];
    }

    private void resetMatrix() {
        this.mSuppMatrix.reset();
        setRotationBy(this.mBaseRotation);
        setImageViewMatrix(getDrawMatrix());
        checkMatrixBounds();
    }

    private void setImageViewMatrix(Matrix matrix) {
        RectF displayRect;
        this.mImageView.setImageMatrix(matrix);
        if (this.mMatrixChangeListener == null || (displayRect = getDisplayRect(matrix)) == null) {
            return;
        }
        this.mMatrixChangeListener.onMatrixChanged(displayRect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAndDisplayMatrix() {
        if (checkMatrixBounds()) {
            setImageViewMatrix(getDrawMatrix());
        }
    }

    private RectF getDisplayRect(Matrix matrix) {
        if (this.mImageView.getDrawable() == null) {
            return null;
        }
        this.mDisplayRect.set(0.0f, 0.0f, r0.getIntrinsicWidth(), r0.getIntrinsicHeight());
        matrix.mapRect(this.mDisplayRect);
        return this.mDisplayRect;
    }

    private void updateBaseMatrix(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        float imageViewWidth = getImageViewWidth(this.mImageView);
        float imageViewHeight = getImageViewHeight(this.mImageView);
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        this.mBaseMatrix.reset();
        float f = intrinsicWidth;
        float f2 = imageViewWidth / f;
        float f3 = intrinsicHeight;
        float f4 = imageViewHeight / f3;
        if (this.mScaleType == ImageView.ScaleType.CENTER) {
            this.mBaseMatrix.postTranslate((imageViewWidth - f) / 2.0f, (imageViewHeight - f3) / 2.0f);
        } else if (this.mScaleType == ImageView.ScaleType.CENTER_CROP) {
            float fMax = Math.max(f2, f4);
            this.mBaseMatrix.postScale(fMax, fMax);
            this.mBaseMatrix.postTranslate((imageViewWidth - (f * fMax)) / 2.0f, (imageViewHeight - (f3 * fMax)) / 2.0f);
        } else if (this.mScaleType == ImageView.ScaleType.CENTER_INSIDE) {
            float fMin = Math.min(1.0f, Math.min(f2, f4));
            this.mBaseMatrix.postScale(fMin, fMin);
            this.mBaseMatrix.postTranslate((imageViewWidth - (f * fMin)) / 2.0f, (imageViewHeight - (f3 * fMin)) / 2.0f);
        } else {
            RectF rectF = new RectF(0.0f, 0.0f, f, f3);
            RectF rectF2 = new RectF(0.0f, 0.0f, imageViewWidth, imageViewHeight);
            if (((int) this.mBaseRotation) % EMachine.EM_L10M != 0) {
                rectF = new RectF(0.0f, 0.0f, f3, f);
            }
            int i = C08405.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()];
            if (i == 1) {
                this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
            } else if (i == 2) {
                this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.START);
            } else if (i == 3) {
                this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.END);
            } else if (i == 4) {
                this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
            }
        }
        resetMatrix();
    }

    /* renamed from: com.glasssutdio.wear.all.view.photoview.PhotoViewAttacher$5 */
    static /* synthetic */ class C08405 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            $SwitchMap$android$widget$ImageView$ScaleType = iArr;
            try {
                iArr[ImageView.ScaleType.FIT_CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private boolean checkMatrixBounds() {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        RectF displayRect = getDisplayRect(getDrawMatrix());
        if (displayRect == null) {
            return false;
        }
        float fHeight = displayRect.height();
        float fWidth = displayRect.width();
        float imageViewHeight = getImageViewHeight(this.mImageView);
        float f6 = 0.0f;
        if (fHeight <= imageViewHeight) {
            int i = C08405.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()];
            if (i != 2) {
                if (i == 3) {
                    f4 = imageViewHeight - fHeight;
                    f5 = displayRect.top;
                } else {
                    f4 = (imageViewHeight - fHeight) / 2.0f;
                    f5 = displayRect.top;
                }
                f = f4 - f5;
            } else {
                f = -displayRect.top;
            }
            this.mVerticalScrollEdge = 2;
        } else if (displayRect.top > 0.0f) {
            this.mVerticalScrollEdge = 0;
            f = -displayRect.top;
        } else if (displayRect.bottom < imageViewHeight) {
            this.mVerticalScrollEdge = 1;
            f = imageViewHeight - displayRect.bottom;
        } else {
            this.mVerticalScrollEdge = -1;
            f = 0.0f;
        }
        float imageViewWidth = getImageViewWidth(this.mImageView);
        if (fWidth <= imageViewWidth) {
            int i2 = C08405.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()];
            if (i2 != 2) {
                if (i2 == 3) {
                    f2 = imageViewWidth - fWidth;
                    f3 = displayRect.left;
                } else {
                    f2 = (imageViewWidth - fWidth) / 2.0f;
                    f3 = displayRect.left;
                }
                f6 = f2 - f3;
            } else {
                f6 = -displayRect.left;
            }
            this.mHorizontalScrollEdge = 2;
        } else if (displayRect.left > 0.0f) {
            this.mHorizontalScrollEdge = 0;
            f6 = -displayRect.left;
        } else if (displayRect.right < imageViewWidth) {
            f6 = imageViewWidth - displayRect.right;
            this.mHorizontalScrollEdge = 1;
        } else {
            this.mHorizontalScrollEdge = -1;
        }
        this.mSuppMatrix.postTranslate(f6, f);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getImageViewWidth(ImageView imageView) {
        return (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getImageViewHeight(ImageView imageView) {
        return (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
    }

    private void cancelFling() {
        FlingRunnable flingRunnable = this.mCurrentFlingRunnable;
        if (flingRunnable != null) {
            flingRunnable.cancelFling();
            this.mCurrentFlingRunnable = null;
        }
    }

    private class AnimatedZoomRunnable implements Runnable {
        private final float mFocalX;
        private final float mFocalY;
        private final long mStartTime = System.currentTimeMillis();
        private final float mZoomEnd;
        private final float mZoomStart;

        public AnimatedZoomRunnable(final float currentZoom, final float targetZoom, final float focalX, final float focalY) {
            this.mFocalX = focalX;
            this.mFocalY = focalY;
            this.mZoomStart = currentZoom;
            this.mZoomEnd = targetZoom;
        }

        @Override // java.lang.Runnable
        public void run() {
            float fInterpolate = interpolate();
            float f = this.mZoomStart;
            PhotoViewAttacher.this.onGestureListener.onScale((f + ((this.mZoomEnd - f) * fInterpolate)) / PhotoViewAttacher.this.getScale(), this.mFocalX, this.mFocalY);
            if (fInterpolate < 1.0f) {
                Compat.postOnAnimation(PhotoViewAttacher.this.mImageView, this);
            }
        }

        private float interpolate() {
            return PhotoViewAttacher.this.mInterpolator.getInterpolation(Math.min(1.0f, ((System.currentTimeMillis() - this.mStartTime) * 1.0f) / PhotoViewAttacher.this.mZoomDuration));
        }
    }

    private class FlingRunnable implements Runnable {
        private int mCurrentX;
        private int mCurrentY;
        private final OverScroller mScroller;

        public FlingRunnable(Context context) {
            this.mScroller = new OverScroller(context);
        }

        public void cancelFling() {
            this.mScroller.forceFinished(true);
        }

        public void fling(int viewWidth, int viewHeight, int velocityX, int velocityY) {
            int i;
            int iRound;
            int i2;
            int iRound2;
            RectF displayRect = PhotoViewAttacher.this.getDisplayRect();
            if (displayRect == null) {
                return;
            }
            int iRound3 = Math.round(-displayRect.left);
            float f = viewWidth;
            if (f < displayRect.width()) {
                iRound = Math.round(displayRect.width() - f);
                i = 0;
            } else {
                i = iRound3;
                iRound = i;
            }
            int iRound4 = Math.round(-displayRect.top);
            float f2 = viewHeight;
            if (f2 < displayRect.height()) {
                iRound2 = Math.round(displayRect.height() - f2);
                i2 = 0;
            } else {
                i2 = iRound4;
                iRound2 = i2;
            }
            this.mCurrentX = iRound3;
            this.mCurrentY = iRound4;
            if (iRound3 == iRound && iRound4 == iRound2) {
                return;
            }
            this.mScroller.fling(iRound3, iRound4, velocityX, velocityY, i, iRound, i2, iRound2, 0, 0);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.mScroller.isFinished() && this.mScroller.computeScrollOffset()) {
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                PhotoViewAttacher.this.mSuppMatrix.postTranslate(this.mCurrentX - currX, this.mCurrentY - currY);
                PhotoViewAttacher.this.checkAndDisplayMatrix();
                this.mCurrentX = currX;
                this.mCurrentY = currY;
                Compat.postOnAnimation(PhotoViewAttacher.this.mImageView, this);
            }
        }
    }

    private class ScaleBitmapRunnable implements Runnable {

        /* renamed from: dx */
        private float f170dx;

        /* renamed from: dy */
        private float f171dy;
        private float focusX;
        private float focusY;
        private float scaleFactor;

        public ScaleBitmapRunnable(float scaleFactor, float focusX, float focusY, float dx, float dy) {
            this.scaleFactor = scaleFactor;
            this.focusX = focusX;
            this.focusY = focusY;
            this.f170dx = dx;
            this.f171dy = dy;
        }

        @Override // java.lang.Runnable
        public void run() {
            Drawable drawable = PhotoViewAttacher.this.mImageView.getDrawable();
            if (drawable instanceof BitmapDrawable) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                Bitmap bitmap2 = (Bitmap) PhotoViewAttacher.this.scaleBitmapCache.get(Float.valueOf(this.scaleFactor));
                if (bitmap2 != null) {
                    updateUI(bitmap2, this.scaleFactor, this.focusX, this.focusY, this.f170dx, this.f171dy);
                    return;
                }
                int width = (int) (bitmap.getWidth() * this.scaleFactor);
                int height = (int) (bitmap.getHeight() * this.scaleFactor);
                if (width > 2048) {
                    width = 2048;
                }
                if (height > 2048) {
                    height = 2048;
                }
                Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
                PhotoViewAttacher.this.scaleBitmapCache.put(Float.valueOf(this.scaleFactor), bitmapCreateScaledBitmap);
                updateUI(bitmapCreateScaledBitmap, this.scaleFactor, this.focusX, this.focusY, this.f170dx, this.f171dy);
            }
        }

        private void updateUI(final Bitmap scaledBitmap, final float scaleFactor, final float focusX, final float focusY, final float dx, final float dy) {
            PhotoViewAttacher.this.mainHandler.post(new Runnable() { // from class: com.glasssutdio.wear.all.view.photoview.PhotoViewAttacher.ScaleBitmapRunnable.1
                @Override // java.lang.Runnable
                public void run() {
                    Bitmap bitmap = scaledBitmap;
                    if (bitmap == null || bitmap.isRecycled()) {
                        return;
                    }
                    PhotoViewAttacher.this.mImageView.setImageBitmap(scaledBitmap);
                    if (Math.abs(scaleFactor - 1.0f) > 0.01f || Math.abs(dx) > 0.01f || Math.abs(dy) > 0.01f) {
                        Matrix matrix = PhotoViewAttacher.this.mSuppMatrix;
                        float f = scaleFactor;
                        matrix.postScale(f, f, focusX, focusY);
                        PhotoViewAttacher.this.mSuppMatrix.postTranslate(dx, dy);
                        if (PhotoViewAttacher.this.needUpdateDisplayMatrix()) {
                            PhotoViewAttacher.this.checkAndDisplayMatrix();
                        }
                    }
                }
            });
        }
    }
}
