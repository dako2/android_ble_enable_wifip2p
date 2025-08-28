package androidx.core.animation;

/* loaded from: classes.dex */
public final class FloatEvaluator implements TypeEvaluator<Float> {
    private static final FloatEvaluator sInstance = new FloatEvaluator();

    public static FloatEvaluator getInstance() {
        return sInstance;
    }

    private FloatEvaluator() {
    }

    @Override // androidx.core.animation.TypeEvaluator
    public Float evaluate(float f, Float f2, Float f3) {
        float fFloatValue = f2.floatValue();
        return Float.valueOf(fFloatValue + (f * (f3.floatValue() - fFloatValue)));
    }
}
