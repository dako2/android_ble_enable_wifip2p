package androidx.core.animation;

/* loaded from: classes.dex */
public abstract class Keyframe<T> implements Cloneable {
    float mFraction;
    boolean mHasValue;
    private Interpolator mInterpolator = null;
    Class<?> mValueType;
    boolean mValueWasSetOnStart;

    @Override // 
    /* renamed from: clone */
    public abstract Keyframe<T> mo675clone();

    public abstract T getValue();

    public abstract void setValue(T t);

    public static Keyframe<Integer> ofInt(float f, int i) {
        return new IntKeyframe(f, i);
    }

    public static Keyframe<Integer> ofInt(float f) {
        return new IntKeyframe(f);
    }

    public static Keyframe<Float> ofFloat(float f, float f2) {
        return new FloatKeyframe(f, f2);
    }

    public static Keyframe<Float> ofFloat(float f) {
        return new FloatKeyframe(f);
    }

    public static <T> Keyframe<T> ofObject(float f, T t) {
        return new ObjectKeyframe(f, t);
    }

    public static <T> Keyframe<T> ofObject(float f) {
        return new ObjectKeyframe(f, null);
    }

    public boolean hasValue() {
        return this.mHasValue;
    }

    boolean valueWasSetOnStart() {
        return this.mValueWasSetOnStart;
    }

    void setValueWasSetOnStart(boolean z) {
        this.mValueWasSetOnStart = z;
    }

    public float getFraction() {
        return this.mFraction;
    }

    public void setFraction(float f) {
        this.mFraction = f;
    }

    public Interpolator getInterpolator() {
        return this.mInterpolator;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    public Class<?> getType() {
        return this.mValueType;
    }

    static class ObjectKeyframe<T> extends Keyframe<T> {
        T mValue;

        ObjectKeyframe(float f, T t) {
            this.mFraction = f;
            this.mValue = t;
            this.mHasValue = t != null;
            this.mValueType = this.mHasValue ? t.getClass() : Object.class;
        }

        @Override // androidx.core.animation.Keyframe
        public T getValue() {
            return this.mValue;
        }

        @Override // androidx.core.animation.Keyframe
        public void setValue(T t) {
            this.mValue = t;
            this.mHasValue = t != null;
        }

        @Override // androidx.core.animation.Keyframe
        /* renamed from: clone */
        public ObjectKeyframe<T> mo675clone() {
            ObjectKeyframe<T> objectKeyframe = new ObjectKeyframe<>(getFraction(), hasValue() ? this.mValue : null);
            objectKeyframe.mValueWasSetOnStart = this.mValueWasSetOnStart;
            objectKeyframe.setInterpolator(getInterpolator());
            return objectKeyframe;
        }
    }

    static class IntKeyframe extends Keyframe<Integer> {
        int mValue;

        IntKeyframe(float f, int i) {
            this.mFraction = f;
            this.mValue = i;
            this.mValueType = Integer.TYPE;
            this.mHasValue = true;
        }

        IntKeyframe(float f) {
            this.mFraction = f;
            this.mValueType = Integer.TYPE;
        }

        public int getIntValue() {
            return this.mValue;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.core.animation.Keyframe
        public Integer getValue() {
            return Integer.valueOf(this.mValue);
        }

        @Override // androidx.core.animation.Keyframe
        public void setValue(Integer num) {
            if (num == null || num.getClass() != Integer.class) {
                return;
            }
            this.mValue = num.intValue();
            this.mHasValue = true;
        }

        @Override // androidx.core.animation.Keyframe
        /* renamed from: clone */
        public Keyframe<Integer> mo675clone() {
            IntKeyframe intKeyframe = this.mHasValue ? new IntKeyframe(getFraction(), this.mValue) : new IntKeyframe(getFraction());
            intKeyframe.setInterpolator(getInterpolator());
            intKeyframe.mValueWasSetOnStart = this.mValueWasSetOnStart;
            return intKeyframe;
        }
    }

    static class FloatKeyframe extends Keyframe<Float> {
        float mValue;

        FloatKeyframe(float f, float f2) {
            this.mFraction = f;
            this.mValue = f2;
            this.mValueType = Float.TYPE;
            this.mHasValue = true;
        }

        FloatKeyframe(float f) {
            this.mFraction = f;
            this.mValueType = Float.TYPE;
        }

        public float getFloatValue() {
            return this.mValue;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.core.animation.Keyframe
        public Float getValue() {
            return Float.valueOf(this.mValue);
        }

        @Override // androidx.core.animation.Keyframe
        public void setValue(Float f) {
            if (f == null || f.getClass() != Float.class) {
                return;
            }
            this.mValue = f.floatValue();
            this.mHasValue = true;
        }

        @Override // androidx.core.animation.Keyframe
        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Keyframe<Float> mo675clone() {
            FloatKeyframe floatKeyframe = this.mHasValue ? new FloatKeyframe(getFraction(), this.mValue) : new FloatKeyframe(getFraction());
            floatKeyframe.setInterpolator(getInterpolator());
            floatKeyframe.mValueWasSetOnStart = this.mValueWasSetOnStart;
            return floatKeyframe;
        }
    }
}
