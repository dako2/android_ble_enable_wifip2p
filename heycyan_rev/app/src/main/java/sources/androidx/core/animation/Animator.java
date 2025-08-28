package androidx.core.animation;

import androidx.core.animation.AnimationHandler;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class Animator implements Cloneable {
    public static final long DURATION_INFINITE = -1;
    ArrayList<AnimatorListener> mListeners = null;
    ArrayList<AnimatorPauseListener> mPauseListeners = null;
    ArrayList<AnimatorUpdateListener> mUpdateListeners = null;
    boolean mPaused = false;

    public interface AnimatorPauseListener {
        void onAnimationPause(Animator animator);

        void onAnimationResume(Animator animator);
    }

    public interface AnimatorUpdateListener {
        void onAnimationUpdate(Animator animator);
    }

    void animateBasedOnPlayTime(long j, long j2, boolean z) {
    }

    boolean canReverse() {
        return false;
    }

    public void cancel() {
    }

    public void end() {
    }

    public abstract long getDuration();

    public Interpolator getInterpolator() {
        return null;
    }

    public abstract long getStartDelay();

    boolean isInitialized() {
        return true;
    }

    public abstract boolean isRunning();

    boolean pulseAnimationFrame(long j) {
        return false;
    }

    public abstract Animator setDuration(long j);

    public abstract void setInterpolator(Interpolator interpolator);

    public abstract void setStartDelay(long j);

    public void setTarget(Object obj) {
    }

    public void setupEndValues() {
    }

    public void setupStartValues() {
    }

    void skipToEndValue(boolean z) {
    }

    public void start() {
    }

    public void pause() {
        if (!isStarted() || this.mPaused) {
            return;
        }
        this.mPaused = true;
        ArrayList<AnimatorPauseListener> arrayList = this.mPauseListeners;
        if (arrayList != null) {
            Object objClone = arrayList.clone();
            if (objClone instanceof ArrayList) {
                ArrayList arrayList2 = (ArrayList) objClone;
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    ((AnimatorPauseListener) arrayList2.get(i)).onAnimationPause(this);
                }
            }
        }
    }

    public void resume() {
        if (this.mPaused) {
            this.mPaused = false;
            ArrayList<AnimatorPauseListener> arrayList = this.mPauseListeners;
            if (arrayList != null) {
                Object objClone = arrayList.clone();
                if (objClone instanceof ArrayList) {
                    ArrayList arrayList2 = (ArrayList) objClone;
                    int size = arrayList2.size();
                    for (int i = 0; i < size; i++) {
                        ((AnimatorPauseListener) arrayList2.get(i)).onAnimationResume(this);
                    }
                }
            }
        }
    }

    public boolean isPaused() {
        return this.mPaused;
    }

    public long getTotalDuration() {
        long duration = getDuration();
        if (duration == -1) {
            return -1L;
        }
        return getStartDelay() + duration;
    }

    public boolean isStarted() {
        return isRunning();
    }

    public void addListener(AnimatorListener animatorListener) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
        }
        this.mListeners.add(animatorListener);
    }

    public void removeListener(AnimatorListener animatorListener) {
        ArrayList<AnimatorListener> arrayList = this.mListeners;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(animatorListener);
        if (this.mListeners.size() == 0) {
            this.mListeners = null;
        }
    }

    ArrayList<AnimatorListener> getListeners() {
        return this.mListeners;
    }

    public void addUpdateListener(AnimatorUpdateListener animatorUpdateListener) {
        if (this.mUpdateListeners == null) {
            this.mUpdateListeners = new ArrayList<>();
        }
        this.mUpdateListeners.add(animatorUpdateListener);
    }

    public void removeAllUpdateListeners() {
        ArrayList<AnimatorUpdateListener> arrayList = this.mUpdateListeners;
        if (arrayList == null) {
            return;
        }
        arrayList.clear();
        this.mUpdateListeners = null;
    }

    public void removeUpdateListener(AnimatorUpdateListener animatorUpdateListener) {
        ArrayList<AnimatorUpdateListener> arrayList = this.mUpdateListeners;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(animatorUpdateListener);
        if (this.mUpdateListeners.size() == 0) {
            this.mUpdateListeners = null;
        }
    }

    public void addPauseListener(AnimatorPauseListener animatorPauseListener) {
        if (this.mPauseListeners == null) {
            this.mPauseListeners = new ArrayList<>();
        }
        this.mPauseListeners.add(animatorPauseListener);
    }

    public void removePauseListener(AnimatorPauseListener animatorPauseListener) {
        ArrayList<AnimatorPauseListener> arrayList = this.mPauseListeners;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(animatorPauseListener);
        if (this.mPauseListeners.size() == 0) {
            this.mPauseListeners = null;
        }
    }

    public void removeAllListeners() {
        ArrayList<AnimatorListener> arrayList = this.mListeners;
        if (arrayList != null) {
            arrayList.clear();
            this.mListeners = null;
        }
        ArrayList<AnimatorPauseListener> arrayList2 = this.mPauseListeners;
        if (arrayList2 != null) {
            arrayList2.clear();
            this.mPauseListeners = null;
        }
    }

    @Override // 
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Animator mo672clone() {
        try {
            Animator animator = (Animator) super.clone();
            if (this.mListeners != null) {
                animator.mListeners = new ArrayList<>(this.mListeners);
            }
            if (this.mPauseListeners != null) {
                animator.mPauseListeners = new ArrayList<>(this.mPauseListeners);
            }
            return animator;
        } catch (CloneNotSupportedException unused) {
            throw new AssertionError();
        }
    }

    static void addAnimationCallback(AnimationHandler.AnimationFrameCallback animationFrameCallback) {
        AnimationHandler.getInstance().addAnimationFrameCallback(animationFrameCallback);
    }

    static void removeAnimationCallback(AnimationHandler.AnimationFrameCallback animationFrameCallback) {
        AnimationHandler.getInstance().removeCallback(animationFrameCallback);
    }

    void reverse() {
        throw new IllegalStateException("Reverse is not supported");
    }

    void startWithoutPulsing(boolean z) {
        if (z) {
            reverse();
        } else {
            start();
        }
    }

    public interface AnimatorListener {
        void onAnimationCancel(Animator animator);

        void onAnimationEnd(Animator animator);

        void onAnimationRepeat(Animator animator);

        void onAnimationStart(Animator animator);

        default void onAnimationStart(Animator animator, boolean z) {
            onAnimationStart(animator);
        }

        default void onAnimationEnd(Animator animator, boolean z) {
            onAnimationEnd(animator);
        }
    }
}
