package com.androidnetworking.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import com.androidnetworking.error.ANError;
import com.androidnetworking.internal.ANImageLoader;

/* loaded from: classes.dex */
public class ANImageView extends AppCompatImageView {
    private int mDefaultImageId;
    private int mErrorImageId;
    private ANImageLoader.ImageContainer mImageContainer;
    private String mUrl;

    public ANImageView(Context context) {
        this(context, null);
    }

    public ANImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ANImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setImageUrl(String str) {
        this.mUrl = str;
        loadImageIfNecessary(false);
    }

    public void setDefaultImageResId(int i) {
        this.mDefaultImageId = i;
    }

    public void setErrorImageResId(int i) {
        this.mErrorImageId = i;
    }

    void loadImageIfNecessary(boolean z) {
        boolean z2;
        boolean z3;
        int width = getWidth();
        int height = getHeight();
        ImageView.ScaleType scaleType = getScaleType();
        if (getLayoutParams() != null) {
            z2 = getLayoutParams().width == -2;
            z3 = getLayoutParams().height == -2;
        } else {
            z2 = false;
            z3 = false;
        }
        boolean z4 = z2 && z3;
        if (width == 0 && height == 0 && !z4) {
            return;
        }
        if (TextUtils.isEmpty(this.mUrl)) {
            ANImageLoader.ImageContainer imageContainer = this.mImageContainer;
            if (imageContainer != null) {
                imageContainer.cancelRequest();
                this.mImageContainer = null;
            }
            setDefaultImageOrNull();
            return;
        }
        ANImageLoader.ImageContainer imageContainer2 = this.mImageContainer;
        if (imageContainer2 != null && imageContainer2.getRequestUrl() != null) {
            if (this.mImageContainer.getRequestUrl().equals(this.mUrl)) {
                return;
            }
            this.mImageContainer.cancelRequest();
            setDefaultImageOrNull();
        }
        if (z2) {
            width = 0;
        }
        this.mImageContainer = ANImageLoader.getInstance().get(this.mUrl, new C06751(z), width, z3 ? 0 : height, scaleType);
    }

    /* renamed from: com.androidnetworking.widget.ANImageView$1 */
    class C06751 implements ANImageLoader.ImageListener {
        final /* synthetic */ boolean val$isInLayoutPass;

        C06751(boolean z) {
            this.val$isInLayoutPass = z;
        }

        @Override // com.androidnetworking.internal.ANImageLoader.ImageListener
        public void onResponse(final ANImageLoader.ImageContainer imageContainer, boolean z) {
            if (z && this.val$isInLayoutPass) {
                ANImageView.this.post(new Runnable() { // from class: com.androidnetworking.widget.ANImageView.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        C06751.this.onResponse(imageContainer, false);
                    }
                });
                return;
            }
            if (imageContainer.getBitmap() == null) {
                if (ANImageView.this.mDefaultImageId != 0) {
                    ANImageView aNImageView = ANImageView.this;
                    aNImageView.setImageResource(aNImageView.mDefaultImageId);
                    return;
                }
                return;
            }
            ANImageView.this.setImageBitmap(imageContainer.getBitmap());
        }

        @Override // com.androidnetworking.internal.ANImageLoader.ImageListener
        public void onError(ANError aNError) {
            if (ANImageView.this.mErrorImageId != 0) {
                ANImageView aNImageView = ANImageView.this;
                aNImageView.setImageResource(aNImageView.mErrorImageId);
            }
        }
    }

    private void setDefaultImageOrNull() {
        int i = this.mDefaultImageId;
        if (i != 0) {
            setImageResource(i);
        } else {
            setImageBitmap(null);
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        loadImageIfNecessary(true);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        ANImageLoader.ImageContainer imageContainer = this.mImageContainer;
        if (imageContainer != null) {
            imageContainer.cancelRequest();
            setImageBitmap(null);
            this.mImageContainer = null;
        }
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }
}
