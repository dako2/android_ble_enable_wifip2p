package com.androidnetworking.internal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.cache.LruBitmapCache;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.BitmapRequestListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes.dex */
public class ANImageLoader {
    private static final int cacheSize;
    private static final int maxMemory;
    private static ANImageLoader sInstance;
    private final ImageCache mCache;
    private Runnable mRunnable;
    private int mBatchResponseDelayMs = 100;
    private final HashMap<String, BatchedImageRequest> mInFlightRequests = new HashMap<>();
    private final HashMap<String, BatchedImageRequest> mBatchedResponses = new HashMap<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private BitmapFactory.Options mBitmapOptions = new BitmapFactory.Options();

    public interface ImageCache {
        void evictAllBitmap();

        void evictBitmap(String str);

        Bitmap getBitmap(String str);

        void putBitmap(String str, Bitmap bitmap);
    }

    public interface ImageListener {
        void onError(ANError aNError);

        void onResponse(ImageContainer imageContainer, boolean z);
    }

    static {
        int iMaxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        maxMemory = iMaxMemory;
        cacheSize = iMaxMemory / 8;
    }

    public static void initialize() {
        getInstance();
    }

    public static ANImageLoader getInstance() {
        if (sInstance == null) {
            synchronized (ANImageLoader.class) {
                if (sInstance == null) {
                    sInstance = new ANImageLoader(new LruBitmapCache(cacheSize));
                }
            }
        }
        return sInstance;
    }

    public ANImageLoader(ImageCache imageCache) {
        this.mCache = imageCache;
    }

    public ImageCache getImageCache() {
        return this.mCache;
    }

    public static ImageListener getImageListener(final ImageView imageView, final int i, final int i2) {
        return new ImageListener() { // from class: com.androidnetworking.internal.ANImageLoader.1
            @Override // com.androidnetworking.internal.ANImageLoader.ImageListener
            public void onResponse(ImageContainer imageContainer, boolean z) {
                if (imageContainer.getBitmap() != null) {
                    imageView.setImageBitmap(imageContainer.getBitmap());
                    return;
                }
                int i3 = i;
                if (i3 != 0) {
                    imageView.setImageResource(i3);
                }
            }

            @Override // com.androidnetworking.internal.ANImageLoader.ImageListener
            public void onError(ANError aNError) {
                int i3 = i2;
                if (i3 != 0) {
                    imageView.setImageResource(i3);
                }
            }
        };
    }

    public boolean isCached(String str, int i, int i2) {
        return isCached(str, i, i2, ImageView.ScaleType.CENTER_INSIDE);
    }

    public boolean isCached(String str, int i, int i2, ImageView.ScaleType scaleType) {
        throwIfNotOnMainThread();
        return this.mCache.getBitmap(getCacheKey(str, i, i2, scaleType)) != null;
    }

    public ImageContainer get(String str, ImageListener imageListener) {
        return get(str, imageListener, 0, 0);
    }

    public ImageContainer get(String str, ImageListener imageListener, int i, int i2) {
        return get(str, imageListener, i, i2, ImageView.ScaleType.CENTER_INSIDE);
    }

    public ImageContainer get(String str, ImageListener imageListener, int i, int i2, ImageView.ScaleType scaleType) {
        throwIfNotOnMainThread();
        String cacheKey = getCacheKey(str, i, i2, scaleType);
        Bitmap bitmap = this.mCache.getBitmap(cacheKey);
        if (bitmap != null) {
            ImageContainer imageContainer = new ImageContainer(bitmap, str, null, null);
            imageListener.onResponse(imageContainer, true);
            return imageContainer;
        }
        ImageContainer imageContainer2 = new ImageContainer(null, str, cacheKey, imageListener);
        imageListener.onResponse(imageContainer2, true);
        BatchedImageRequest batchedImageRequest = this.mInFlightRequests.get(cacheKey);
        if (batchedImageRequest != null) {
            batchedImageRequest.addContainer(imageContainer2);
            return imageContainer2;
        }
        this.mInFlightRequests.put(cacheKey, new BatchedImageRequest(makeImageRequest(str, i, i2, scaleType, cacheKey), imageContainer2));
        return imageContainer2;
    }

    protected ANRequest makeImageRequest(String str, int i, int i2, ImageView.ScaleType scaleType, final String str2) {
        ANRequest aNRequestBuild = AndroidNetworking.get(str).setTag((Object) "ImageRequestTag").setBitmapMaxHeight(i2).setBitmapMaxWidth(i).setImageScaleType(scaleType).setBitmapConfig(Bitmap.Config.RGB_565).setBitmapOptions(this.mBitmapOptions).build();
        aNRequestBuild.getAsBitmap(new BitmapRequestListener() { // from class: com.androidnetworking.internal.ANImageLoader.2
            @Override // com.androidnetworking.interfaces.BitmapRequestListener
            public void onResponse(Bitmap bitmap) {
                ANImageLoader.this.onGetImageSuccess(str2, bitmap);
            }

            @Override // com.androidnetworking.interfaces.BitmapRequestListener
            public void onError(ANError aNError) {
                ANImageLoader.this.onGetImageError(str2, aNError);
            }
        });
        return aNRequestBuild;
    }

    public void setBitmapDecodeOptions(BitmapFactory.Options options) {
        this.mBitmapOptions = options;
    }

    public void setBatchedResponseDelay(int i) {
        this.mBatchResponseDelayMs = i;
    }

    protected void onGetImageSuccess(String str, Bitmap bitmap) {
        this.mCache.putBitmap(str, bitmap);
        BatchedImageRequest batchedImageRequestRemove = this.mInFlightRequests.remove(str);
        if (batchedImageRequestRemove != null) {
            batchedImageRequestRemove.mResponseBitmap = bitmap;
            batchResponse(str, batchedImageRequestRemove);
        }
    }

    protected void onGetImageError(String str, ANError aNError) {
        BatchedImageRequest batchedImageRequestRemove = this.mInFlightRequests.remove(str);
        if (batchedImageRequestRemove != null) {
            batchedImageRequestRemove.setError(aNError);
            batchResponse(str, batchedImageRequestRemove);
        }
    }

    public class ImageContainer {
        private Bitmap mBitmap;
        private final String mCacheKey;
        private final ImageListener mListener;
        private final String mRequestUrl;

        public ImageContainer(Bitmap bitmap, String str, String str2, ImageListener imageListener) {
            this.mBitmap = bitmap;
            this.mRequestUrl = str;
            this.mCacheKey = str2;
            this.mListener = imageListener;
        }

        public void cancelRequest() {
            if (this.mListener == null) {
                return;
            }
            BatchedImageRequest batchedImageRequest = (BatchedImageRequest) ANImageLoader.this.mInFlightRequests.get(this.mCacheKey);
            if (batchedImageRequest == null) {
                BatchedImageRequest batchedImageRequest2 = (BatchedImageRequest) ANImageLoader.this.mBatchedResponses.get(this.mCacheKey);
                if (batchedImageRequest2 != null) {
                    batchedImageRequest2.removeContainerAndCancelIfNecessary(this);
                    if (batchedImageRequest2.mContainers.size() == 0) {
                        ANImageLoader.this.mBatchedResponses.remove(this.mCacheKey);
                        return;
                    }
                    return;
                }
                return;
            }
            if (batchedImageRequest.removeContainerAndCancelIfNecessary(this)) {
                ANImageLoader.this.mInFlightRequests.remove(this.mCacheKey);
            }
        }

        public Bitmap getBitmap() {
            return this.mBitmap;
        }

        public String getRequestUrl() {
            return this.mRequestUrl;
        }
    }

    private class BatchedImageRequest {
        private ANError mANError;
        private final LinkedList<ImageContainer> mContainers;
        private final ANRequest mRequest;
        private Bitmap mResponseBitmap;

        public BatchedImageRequest(ANRequest aNRequest, ImageContainer imageContainer) {
            LinkedList<ImageContainer> linkedList = new LinkedList<>();
            this.mContainers = linkedList;
            this.mRequest = aNRequest;
            linkedList.add(imageContainer);
        }

        public void setError(ANError aNError) {
            this.mANError = aNError;
        }

        public ANError getError() {
            return this.mANError;
        }

        public void addContainer(ImageContainer imageContainer) {
            this.mContainers.add(imageContainer);
        }

        public boolean removeContainerAndCancelIfNecessary(ImageContainer imageContainer) {
            this.mContainers.remove(imageContainer);
            if (this.mContainers.size() != 0) {
                return false;
            }
            this.mRequest.cancel(true);
            if (this.mRequest.isCanceled()) {
                this.mRequest.destroy();
                ANRequestQueue.getInstance().finish(this.mRequest);
            }
            return true;
        }
    }

    private void batchResponse(String str, BatchedImageRequest batchedImageRequest) {
        this.mBatchedResponses.put(str, batchedImageRequest);
        if (this.mRunnable == null) {
            Runnable runnable = new Runnable() { // from class: com.androidnetworking.internal.ANImageLoader.3
                @Override // java.lang.Runnable
                public void run() {
                    for (BatchedImageRequest batchedImageRequest2 : ANImageLoader.this.mBatchedResponses.values()) {
                        Iterator it = batchedImageRequest2.mContainers.iterator();
                        while (it.hasNext()) {
                            ImageContainer imageContainer = (ImageContainer) it.next();
                            if (imageContainer.mListener != null) {
                                if (batchedImageRequest2.getError() == null) {
                                    imageContainer.mBitmap = batchedImageRequest2.mResponseBitmap;
                                    imageContainer.mListener.onResponse(imageContainer, false);
                                } else {
                                    imageContainer.mListener.onError(batchedImageRequest2.getError());
                                }
                            }
                        }
                    }
                    ANImageLoader.this.mBatchedResponses.clear();
                    ANImageLoader.this.mRunnable = null;
                }
            };
            this.mRunnable = runnable;
            this.mHandler.postDelayed(runnable, this.mBatchResponseDelayMs);
        }
    }

    private void throwIfNotOnMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ImageLoader must be invoked from the main thread.");
        }
    }

    private static String getCacheKey(String str, int i, int i2, ImageView.ScaleType scaleType) {
        return new StringBuilder(str.length() + 12).append("#W").append(i).append("#H").append(i2).append("#S").append(scaleType.ordinal()).append(str).toString();
    }
}
