package androidx.core.animation;

import android.view.Choreographer;
import java.util.ArrayList;

/* loaded from: classes.dex */
class AnimationHandler {
    public static final ThreadLocal<AnimationHandler> sAnimationHandler = new ThreadLocal<>();
    private static AnimationHandler sTestHandler = null;
    private final ArrayList<AnimationFrameCallback> mAnimationCallbacks = new ArrayList<>();
    boolean mListDirty = false;
    private final AnimationFrameCallbackProvider mProvider;

    interface AnimationFrameCallback {
        boolean doAnimationFrame(long j);
    }

    interface AnimationFrameCallbackProvider {
        long getFrameDelay();

        void onNewCallbackAdded(AnimationFrameCallback animationFrameCallback);

        void postFrameCallback();

        void setFrameDelay(long j);
    }

    void onAnimationFrame(long j) {
        doAnimationFrame(j);
        if (this.mAnimationCallbacks.size() > 0) {
            this.mProvider.postFrameCallback();
        }
    }

    AnimationHandler(AnimationFrameCallbackProvider animationFrameCallbackProvider) {
        if (animationFrameCallbackProvider == null) {
            this.mProvider = new FrameCallbackProvider16();
        } else {
            this.mProvider = animationFrameCallbackProvider;
        }
    }

    public static AnimationHandler getInstance() {
        AnimationHandler animationHandler = sTestHandler;
        if (animationHandler != null) {
            return animationHandler;
        }
        ThreadLocal<AnimationHandler> threadLocal = sAnimationHandler;
        if (threadLocal.get() == null) {
            threadLocal.set(new AnimationHandler(null));
        }
        return threadLocal.get();
    }

    static void setTestHandler(AnimationHandler animationHandler) {
        sTestHandler = animationHandler;
    }

    void setFrameDelay(long j) {
        this.mProvider.setFrameDelay(j);
    }

    long getFrameDelay() {
        return this.mProvider.getFrameDelay();
    }

    void addAnimationFrameCallback(AnimationFrameCallback animationFrameCallback) {
        if (this.mAnimationCallbacks.size() == 0) {
            this.mProvider.postFrameCallback();
        }
        if (!this.mAnimationCallbacks.contains(animationFrameCallback)) {
            this.mAnimationCallbacks.add(animationFrameCallback);
        }
        this.mProvider.onNewCallbackAdded(animationFrameCallback);
    }

    public void removeCallback(AnimationFrameCallback animationFrameCallback) {
        int iIndexOf = this.mAnimationCallbacks.indexOf(animationFrameCallback);
        if (iIndexOf >= 0) {
            this.mAnimationCallbacks.set(iIndexOf, null);
            this.mListDirty = true;
        }
    }

    void autoCancelBasedOn(ObjectAnimator objectAnimator) {
        for (int size = this.mAnimationCallbacks.size() - 1; size >= 0; size--) {
            AnimationFrameCallback animationFrameCallback = this.mAnimationCallbacks.get(size);
            if (animationFrameCallback != null && objectAnimator.shouldAutoCancel(animationFrameCallback)) {
                ((Animator) this.mAnimationCallbacks.get(size)).cancel();
            }
        }
    }

    private void doAnimationFrame(long j) {
        for (int i = 0; i < this.mAnimationCallbacks.size(); i++) {
            AnimationFrameCallback animationFrameCallback = this.mAnimationCallbacks.get(i);
            if (animationFrameCallback != null) {
                animationFrameCallback.doAnimationFrame(j);
            }
        }
        cleanUpList();
    }

    private void cleanUpList() {
        if (this.mListDirty) {
            for (int size = this.mAnimationCallbacks.size() - 1; size >= 0; size--) {
                if (this.mAnimationCallbacks.get(size) == null) {
                    this.mAnimationCallbacks.remove(size);
                }
            }
            this.mListDirty = false;
        }
    }

    private int getCallbackSize() {
        int i = 0;
        for (int size = this.mAnimationCallbacks.size() - 1; size >= 0; size--) {
            if (this.mAnimationCallbacks.get(size) != null) {
                i++;
            }
        }
        return i;
    }

    public static int getAnimationCount() {
        AnimationHandler animationHandler = getInstance();
        if (animationHandler == null) {
            return 0;
        }
        return animationHandler.getCallbackSize();
    }

    private class FrameCallbackProvider16 implements AnimationFrameCallbackProvider, Choreographer.FrameCallback {
        @Override // androidx.core.animation.AnimationHandler.AnimationFrameCallbackProvider
        public void onNewCallbackAdded(AnimationFrameCallback animationFrameCallback) {
        }

        FrameCallbackProvider16() {
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            AnimationHandler.this.onAnimationFrame(j / 1000000);
        }

        @Override // androidx.core.animation.AnimationHandler.AnimationFrameCallbackProvider
        public void postFrameCallback() {
            Choreographer.getInstance().postFrameCallback(this);
        }

        @Override // androidx.core.animation.AnimationHandler.AnimationFrameCallbackProvider
        public void setFrameDelay(long j) {
            android.animation.ValueAnimator.setFrameDelay(j);
        }

        @Override // androidx.core.animation.AnimationHandler.AnimationFrameCallbackProvider
        public long getFrameDelay() {
            return android.animation.ValueAnimator.getFrameDelay();
        }
    }
}
