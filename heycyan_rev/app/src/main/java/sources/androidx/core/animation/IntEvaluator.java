package androidx.core.animation;

/* loaded from: classes.dex */
public class IntEvaluator implements TypeEvaluator<Integer> {
    private static final IntEvaluator sInstance = new IntEvaluator();

    public static IntEvaluator getInstance() {
        return sInstance;
    }

    private IntEvaluator() {
    }

    @Override // androidx.core.animation.TypeEvaluator
    public Integer evaluate(float f, Integer num, Integer num2) {
        return Integer.valueOf((int) (num.intValue() + (f * (num2.intValue() - r3))));
    }
}
