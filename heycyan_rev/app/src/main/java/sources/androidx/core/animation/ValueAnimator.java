package androidx.core.animation;

import android.os.Looper;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.animation.AnimationUtils;
import androidx.core.animation.AnimationHandler;
import androidx.core.animation.Animator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ValueAnimator extends Animator implements AnimationHandler.AnimationFrameCallback {
    public static final int INFINITE = -1;
    public static final int RESTART = 1;
    public static final int REVERSE = 2;
    private static final String TAG = "ValueAnimator";
    private static final Interpolator sDefaultInterpolator = new AccelerateDecelerateInterpolator();
    private static float sDurationScale = 1.0f;
    private long mPauseTime;
    private boolean mReversing;
    PropertyValuesHolder[] mValues;
    HashMap<String, PropertyValuesHolder> mValuesMap;
    long mStartTime = -1;
    float mSeekFraction = -1.0f;
    private boolean mResumed = false;
    private float mOverallFraction = 0.0f;
    private float mCurrentFraction = 0.0f;
    private long mLastFrameTime = -1;
    private boolean mRunning = false;
    private boolean mStarted = false;
    private boolean mStartListenersCalled = false;
    boolean mInitialized = false;
    private boolean mAnimationEndRequested = false;
    private long mDuration = 300;
    private long mStartDelay = 0;
    private int mRepeatCount = 0;
    private int mRepeatMode = 1;
    private boolean mSelfPulse = true;
    private boolean mSuppressSelfPulseRequested = false;
    private Interpolator mInterpolator = sDefaultInterpolator;
    private float mDurationScale = -1.0f;
    String mAnimTraceName = null;

    @Override // androidx.core.animation.Animator
    boolean canReverse() {
        return true;
    }

    static void setDurationScale(float f) {
        sDurationScale = f;
    }

    static float getDurationScale() {
        return sDurationScale;
    }

    public static boolean areAnimatorsEnabled() {
        return sDurationScale != 0.0f;
    }

    public static ValueAnimator ofInt(int... iArr) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(iArr);
        return valueAnimator;
    }

    public static ValueAnimator ofArgb(int... iArr) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(iArr);
        valueAnimator.setEvaluator(ArgbEvaluator.getInstance());
        return valueAnimator;
    }

    public static ValueAnimator ofFloat(float... fArr) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(fArr);
        return valueAnimator;
    }

    public static ValueAnimator ofPropertyValuesHolder(PropertyValuesHolder... propertyValuesHolderArr) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setValues(propertyValuesHolderArr);
        return valueAnimator;
    }

    public static ValueAnimator ofObject(TypeEvaluator typeEvaluator, Object... objArr) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setObjectValues(objArr);
        valueAnimator.setEvaluator(typeEvaluator);
        return valueAnimator;
    }

    public void setIntValues(int... iArr) {
        if (iArr == null || iArr.length == 0) {
            return;
        }
        PropertyValuesHolder[] propertyValuesHolderArr = this.mValues;
        if (propertyValuesHolderArr == null || propertyValuesHolderArr.length == 0) {
            setValues(PropertyValuesHolder.ofInt("", iArr));
        } else {
            propertyValuesHolderArr[0].setIntValues(iArr);
        }
        this.mInitialized = false;
    }

    public void setFloatValues(float... fArr) {
        if (fArr == null || fArr.length == 0) {
            return;
        }
        PropertyValuesHolder[] propertyValuesHolderArr = this.mValues;
        if (propertyValuesHolderArr == null || propertyValuesHolderArr.length == 0) {
            setValues(PropertyValuesHolder.ofFloat("", fArr));
        } else {
            propertyValuesHolderArr[0].setFloatValues(fArr);
        }
        this.mInitialized = false;
    }

    public void setObjectValues(Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return;
        }
        PropertyValuesHolder[] propertyValuesHolderArr = this.mValues;
        if (propertyValuesHolderArr == null || propertyValuesHolderArr.length == 0) {
            setValues(PropertyValuesHolder.ofObject("", (TypeEvaluator) null, objArr));
        } else {
            propertyValuesHolderArr[0].setObjectValues(objArr);
        }
        this.mInitialized = false;
    }

    public void setValues(PropertyValuesHolder... propertyValuesHolderArr) {
        int length = propertyValuesHolderArr.length;
        this.mValues = propertyValuesHolderArr;
        this.mValuesMap = new HashMap<>(length);
        for (PropertyValuesHolder propertyValuesHolder : propertyValuesHolderArr) {
            this.mValuesMap.put(propertyValuesHolder.getPropertyName(), propertyValuesHolder);
        }
        this.mInitialized = false;
    }

    public PropertyValuesHolder[] getValues() {
        return this.mValues;
    }

    void initAnimation() {
        if (this.mInitialized) {
            return;
        }
        int length = this.mValues.length;
        for (int i = 0; i < length; i++) {
            this.mValues[i].init();
        }
        this.mInitialized = true;
    }

    @Override // androidx.core.animation.Animator
    public ValueAnimator setDuration(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " + j);
        }
        this.mDuration = j;
        return this;
    }

    void overrideDurationScale(float f) {
        this.mDurationScale = f;
    }

    private float resolveDurationScale() {
        float f = this.mDurationScale;
        return f >= 0.0f ? f : sDurationScale;
    }

    private long getScaledDuration() {
        return (long) (this.mDuration * resolveDurationScale());
    }

    @Override // androidx.core.animation.Animator
    public long getDuration() {
        return this.mDuration;
    }

    @Override // androidx.core.animation.Animator
    public long getTotalDuration() {
        if (this.mRepeatCount == -1) {
            return -1L;
        }
        return this.mStartDelay + (this.mDuration * (r0 + 1));
    }

    public void setCurrentPlayTime(long j) {
        long j2 = this.mDuration;
        setCurrentFraction(j2 > 0 ? j / j2 : 1.0f);
    }

    public void setCurrentFraction(float f) {
        initAnimation();
        float fClampFraction = clampFraction(f);
        if (isPulsingInternal()) {
            this.mStartTime = AnimationUtils.currentAnimationTimeMillis() - ((long) (getScaledDuration() * fClampFraction));
        } else {
            this.mSeekFraction = fClampFraction;
        }
        this.mOverallFraction = fClampFraction;
        animateValue(getCurrentIterationFraction(fClampFraction, this.mReversing));
    }

    private int getCurrentIteration(float f) {
        float fClampFraction = clampFraction(f);
        double d = fClampFraction;
        double dFloor = Math.floor(d);
        if (d == dFloor && fClampFraction > 0.0f) {
            dFloor -= 1.0d;
        }
        return (int) dFloor;
    }

    private float getCurrentIterationFraction(float f, boolean z) {
        float fClampFraction = clampFraction(f);
        int currentIteration = getCurrentIteration(fClampFraction);
        float f2 = fClampFraction - currentIteration;
        return shouldPlayBackward(currentIteration, z) ? 1.0f - f2 : f2;
    }

    private float clampFraction(float f) {
        if (f < 0.0f) {
            return 0.0f;
        }
        return this.mRepeatCount != -1 ? Math.min(f, r0 + 1) : f;
    }

    private boolean shouldPlayBackward(int i, boolean z) {
        if (i > 0 && this.mRepeatMode == 2) {
            int i2 = this.mRepeatCount;
            if (i < i2 + 1 || i2 == -1) {
                return z ? i % 2 == 0 : i % 2 != 0;
            }
        }
        return z;
    }

    public long getCurrentPlayTime() {
        float fCurrentAnimationTimeMillis;
        if (!this.mInitialized) {
            return 0L;
        }
        if (!this.mStarted && this.mSeekFraction < 0.0f) {
            return 0L;
        }
        float f = this.mSeekFraction;
        if (f >= 0.0f) {
            fCurrentAnimationTimeMillis = this.mDuration * f;
        } else {
            float fResolveDurationScale = resolveDurationScale();
            if (fResolveDurationScale == 0.0f) {
                fResolveDurationScale = 1.0f;
            }
            fCurrentAnimationTimeMillis = (AnimationUtils.currentAnimationTimeMillis() - this.mStartTime) / fResolveDurationScale;
        }
        return (long) fCurrentAnimationTimeMillis;
    }

    @Override // androidx.core.animation.Animator
    public long getStartDelay() {
        return this.mStartDelay;
    }

    @Override // androidx.core.animation.Animator
    public void setStartDelay(long j) {
        if (j < 0) {
            Log.w(TAG, "Start delay should always be non-negative");
            j = 0;
        }
        this.mStartDelay = j;
    }

    public static long getFrameDelay() {
        return AnimationHandler.getInstance().getFrameDelay();
    }

    public static void setFrameDelay(long j) {
        AnimationHandler.getInstance().setFrameDelay(j);
    }

    public Object getAnimatedValue() {
        PropertyValuesHolder[] propertyValuesHolderArr = this.mValues;
        if (propertyValuesHolderArr == null || propertyValuesHolderArr.length <= 0) {
            return null;
        }
        return propertyValuesHolderArr[0].getAnimatedValue();
    }

    public Object getAnimatedValue(String str) {
        PropertyValuesHolder propertyValuesHolder = this.mValuesMap.get(str);
        if (propertyValuesHolder != null) {
            return propertyValuesHolder.getAnimatedValue();
        }
        return null;
    }

    public void setRepeatCount(int i) {
        this.mRepeatCount = i;
    }

    public int getRepeatCount() {
        return this.mRepeatCount;
    }

    public void setRepeatMode(int i) {
        this.mRepeatMode = i;
    }

    public int getRepeatMode() {
        return this.mRepeatMode;
    }

    @Override // androidx.core.animation.Animator
    public void setInterpolator(Interpolator interpolator) {
        if (interpolator != null) {
            this.mInterpolator = interpolator;
        } else {
            this.mInterpolator = new LinearInterpolator();
        }
    }

    @Override // androidx.core.animation.Animator
    public Interpolator getInterpolator() {
        return this.mInterpolator;
    }

    public void setEvaluator(TypeEvaluator typeEvaluator) {
        PropertyValuesHolder[] propertyValuesHolderArr;
        if (typeEvaluator == null || (propertyValuesHolderArr = this.mValues) == null || propertyValuesHolderArr.length <= 0) {
            return;
        }
        propertyValuesHolderArr[0].setEvaluator(typeEvaluator);
    }

    private void notifyStartListeners() {
        if (this.mListeners != null && !this.mStartListenersCalled) {
            ArrayList arrayList = (ArrayList) this.mListeners.clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((Animator.AnimatorListener) arrayList.get(i)).onAnimationStart(this, this.mReversing);
            }
        }
        this.mStartListenersCalled = true;
    }

    private void start(boolean z) {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        this.mReversing = z;
        this.mSelfPulse = !this.mSuppressSelfPulseRequested;
        if (z) {
            float f = this.mSeekFraction;
            if (f != -1.0f && f != 0.0f) {
                if (this.mRepeatCount == -1) {
                    this.mSeekFraction = 1.0f - ((float) (f - Math.floor(f)));
                } else {
                    this.mSeekFraction = (r3 + 1) - f;
                }
            }
        }
        this.mStarted = true;
        this.mPaused = false;
        this.mRunning = false;
        this.mAnimationEndRequested = false;
        this.mLastFrameTime = -1L;
        this.mStartTime = -1L;
        if (this.mStartDelay == 0 || this.mSeekFraction >= 0.0f || this.mReversing) {
            startAnimation();
            float f2 = this.mSeekFraction;
            if (f2 == -1.0f) {
                setCurrentPlayTime(0L);
            } else {
                setCurrentFraction(f2);
            }
        }
        addAnimationCallback();
    }

    @Override // androidx.core.animation.Animator
    void startWithoutPulsing(boolean z) {
        this.mSuppressSelfPulseRequested = true;
        if (z) {
            reverse();
        } else {
            start();
        }
        this.mSuppressSelfPulseRequested = false;
    }

    @Override // androidx.core.animation.Animator
    public void start() {
        start(false);
    }

    @Override // androidx.core.animation.Animator
    public void cancel() {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        if (this.mAnimationEndRequested) {
            return;
        }
        if ((this.mStarted || this.mRunning) && this.mListeners != null) {
            if (!this.mRunning) {
                notifyStartListeners();
            }
            Iterator it = ((ArrayList) this.mListeners.clone()).iterator();
            while (it.hasNext()) {
                ((Animator.AnimatorListener) it.next()).onAnimationCancel(this);
            }
        }
        endAnimation();
    }

    @Override // androidx.core.animation.Animator
    public void end() {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        if (!this.mRunning) {
            startAnimation();
            this.mStarted = true;
        } else if (!this.mInitialized) {
            initAnimation();
        }
        animateValue(shouldPlayBackward(this.mRepeatCount, this.mReversing) ? 0.0f : 1.0f);
        endAnimation();
    }

    @Override // androidx.core.animation.Animator
    public void resume() {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be resumed from the same thread that the animator was started on");
        }
        if (this.mPaused && !this.mResumed) {
            this.mResumed = true;
            if (this.mPauseTime > 0) {
                addAnimationCallback();
            }
        }
        super.resume();
    }

    @Override // androidx.core.animation.Animator
    public void pause() {
        boolean z = this.mPaused;
        super.pause();
        if (z || !this.mPaused) {
            return;
        }
        this.mPauseTime = -1L;
        this.mResumed = false;
    }

    @Override // androidx.core.animation.Animator
    public boolean isRunning() {
        return this.mRunning;
    }

    @Override // androidx.core.animation.Animator
    public boolean isStarted() {
        return this.mStarted;
    }

    @Override // androidx.core.animation.Animator
    public void reverse() {
        if (isPulsingInternal()) {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.mStartTime = jCurrentAnimationTimeMillis - (getScaledDuration() - (jCurrentAnimationTimeMillis - this.mStartTime));
            this.mReversing = !this.mReversing;
            return;
        }
        if (this.mStarted) {
            this.mReversing = !this.mReversing;
            end();
        } else {
            start(true);
        }
    }

    private void endAnimation() {
        if (this.mAnimationEndRequested) {
            return;
        }
        removeAnimationCallback();
        this.mAnimationEndRequested = true;
        this.mPaused = false;
        boolean z = (this.mStarted || this.mRunning) && this.mListeners != null;
        if (z && !this.mRunning) {
            notifyStartListeners();
        }
        this.mRunning = false;
        this.mStarted = false;
        this.mStartListenersCalled = false;
        this.mLastFrameTime = -1L;
        this.mStartTime = -1L;
        if (z && this.mListeners != null) {
            ArrayList arrayList = (ArrayList) this.mListeners.clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((Animator.AnimatorListener) arrayList.get(i)).onAnimationEnd(this, this.mReversing);
            }
        }
        this.mReversing = false;
    }

    private void startAnimation() {
        this.mAnimationEndRequested = false;
        initAnimation();
        this.mRunning = true;
        float f = this.mSeekFraction;
        if (f >= 0.0f) {
            this.mOverallFraction = f;
        } else {
            this.mOverallFraction = 0.0f;
        }
        if (this.mListeners != null) {
            notifyStartListeners();
        }
    }

    private boolean isPulsingInternal() {
        return this.mLastFrameTime >= 0;
    }

    public String getNameForTrace() {
        String str = this.mAnimTraceName;
        return str == null ? "animator" : str;
    }

    public void setNameForTrace(String str) {
        this.mAnimTraceName = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    boolean animateBasedOnTime(long j) {
        boolean z = false;
        if (this.mRunning) {
            long scaledDuration = getScaledDuration();
            float f = scaledDuration > 0 ? (j - this.mStartTime) / scaledDuration : 1.0f;
            boolean z2 = ((int) f) > ((int) this.mOverallFraction);
            int i = this.mRepeatCount;
            boolean z3 = f >= ((float) (i + 1)) && i != -1;
            if (scaledDuration != 0) {
                if (!z2 || z3) {
                    if (z3) {
                        z = true;
                    }
                } else if (this.mListeners != null) {
                    int size = this.mListeners.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        this.mListeners.get(i2).onAnimationRepeat(this);
                    }
                }
                float fClampFraction = clampFraction(f);
                this.mOverallFraction = fClampFraction;
                animateValue(getCurrentIterationFraction(fClampFraction, this.mReversing));
            }
        }
        return z;
    }

    @Override // androidx.core.animation.Animator
    void animateBasedOnPlayTime(long j, long j2, boolean z) {
        if (j < 0 || j2 < 0) {
            throw new UnsupportedOperationException("Error: Play time should never be negative.");
        }
        initAnimation();
        int i = this.mRepeatCount;
        if (i > 0) {
            long j3 = this.mDuration;
            if (Math.min((int) (j / j3), i) != Math.min((int) (j2 / j3), this.mRepeatCount) && this.mListeners != null) {
                int size = this.mListeners.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.mListeners.get(i2).onAnimationRepeat(this);
                }
            }
        }
        if (this.mRepeatCount != -1 && j >= (r8 + 1) * this.mDuration) {
            skipToEndValue(z);
        } else {
            animateValue(getCurrentIterationFraction(j / this.mDuration, z));
        }
    }

    @Override // androidx.core.animation.Animator
    void skipToEndValue(boolean z) {
        initAnimation();
        animateValue((this.mRepeatCount % 2 == 1 && this.mRepeatMode == 2) ? 0.0f : z ? 0.0f : 1.0f);
    }

    @Override // androidx.core.animation.Animator
    boolean isInitialized() {
        return this.mInitialized;
    }

    @Override // androidx.core.animation.AnimationHandler.AnimationFrameCallback
    public final boolean doAnimationFrame(long j) {
        if (this.mStartTime < 0) {
            this.mStartTime = this.mReversing ? j : ((long) (this.mStartDelay * resolveDurationScale())) + j;
        }
        if (this.mPaused) {
            this.mPauseTime = j;
            removeAnimationCallback();
            return false;
        }
        if (this.mResumed) {
            this.mResumed = false;
            long j2 = this.mPauseTime;
            if (j2 > 0) {
                this.mStartTime += j - j2;
            }
        }
        if (!this.mRunning) {
            if (this.mStartTime > j && this.mSeekFraction == -1.0f) {
                return false;
            }
            this.mRunning = true;
            startAnimation();
        }
        if (this.mLastFrameTime < 0 && this.mSeekFraction >= 0.0f) {
            this.mStartTime = j - ((long) (getScaledDuration() * this.mSeekFraction));
            this.mSeekFraction = -1.0f;
        }
        this.mLastFrameTime = j;
        boolean zAnimateBasedOnTime = animateBasedOnTime(Math.max(j, this.mStartTime));
        if (zAnimateBasedOnTime) {
            endAnimation();
        }
        return zAnimateBasedOnTime;
    }

    @Override // androidx.core.animation.Animator
    boolean pulseAnimationFrame(long j) {
        if (this.mSelfPulse) {
            return false;
        }
        return doAnimationFrame(j);
    }

    private void removeAnimationCallback() {
        if (this.mSelfPulse) {
            removeAnimationCallback(this);
        }
    }

    private void addAnimationCallback() {
        if (this.mSelfPulse) {
            addAnimationCallback(this);
        }
    }

    public float getAnimatedFraction() {
        return this.mCurrentFraction;
    }

    void animateValue(float f) {
        float interpolation = this.mInterpolator.getInterpolation(f);
        this.mCurrentFraction = interpolation;
        int length = this.mValues.length;
        for (int i = 0; i < length; i++) {
            this.mValues[i].calculateValue(interpolation);
        }
        if (this.mUpdateListeners != null) {
            int size = this.mUpdateListeners.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.mUpdateListeners.get(i2).onAnimationUpdate(this);
            }
        }
    }

    @Override // androidx.core.animation.Animator
    /* renamed from: clone */
    public ValueAnimator mo672clone() {
        ValueAnimator valueAnimator = (ValueAnimator) super.mo672clone();
        if (this.mUpdateListeners != null) {
            valueAnimator.mUpdateListeners = new ArrayList<>(this.mUpdateListeners);
        }
        valueAnimator.mSeekFraction = -1.0f;
        valueAnimator.mReversing = false;
        valueAnimator.mInitialized = false;
        valueAnimator.mStarted = false;
        valueAnimator.mRunning = false;
        valueAnimator.mPaused = false;
        valueAnimator.mResumed = false;
        valueAnimator.mStartListenersCalled = false;
        valueAnimator.mStartTime = -1L;
        valueAnimator.mAnimationEndRequested = false;
        valueAnimator.mPauseTime = -1L;
        valueAnimator.mLastFrameTime = -1L;
        valueAnimator.mOverallFraction = 0.0f;
        valueAnimator.mCurrentFraction = 0.0f;
        valueAnimator.mSelfPulse = true;
        valueAnimator.mSuppressSelfPulseRequested = false;
        PropertyValuesHolder[] propertyValuesHolderArr = this.mValues;
        if (propertyValuesHolderArr != null) {
            int length = propertyValuesHolderArr.length;
            valueAnimator.mValues = new PropertyValuesHolder[length];
            valueAnimator.mValuesMap = new HashMap<>(length);
            for (int i = 0; i < length; i++) {
                PropertyValuesHolder propertyValuesHolderMo678clone = propertyValuesHolderArr[i].mo678clone();
                valueAnimator.mValues[i] = propertyValuesHolderMo678clone;
                valueAnimator.mValuesMap.put(propertyValuesHolderMo678clone.getPropertyName(), propertyValuesHolderMo678clone);
            }
        }
        return valueAnimator;
    }

    static int getCurrentAnimationsCount() {
        return AnimationHandler.getAnimationCount();
    }

    public String toString() {
        String str = "ValueAnimator@" + Integer.toHexString(hashCode());
        if (this.mValues != null) {
            for (int i = 0; i < this.mValues.length; i++) {
                str = str + "\n    " + this.mValues[i].toString();
            }
        }
        return str;
    }
}
