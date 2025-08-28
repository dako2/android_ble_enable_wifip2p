package androidx.core.animation;

/* loaded from: classes.dex */
public final class FloatArrayEvaluator implements TypeEvaluator<float[]> {
    private float[] mArray;

    public FloatArrayEvaluator() {
    }

    public FloatArrayEvaluator(float[] fArr) {
        this.mArray = fArr;
    }

    @Override // androidx.core.animation.TypeEvaluator
    public float[] evaluate(float f, float[] fArr, float[] fArr2) {
        float[] fArr3 = this.mArray;
        if (fArr3 == null) {
            fArr3 = new float[fArr.length];
        }
        for (int i = 0; i < fArr3.length; i++) {
            float f2 = fArr[i];
            fArr3[i] = f2 + ((fArr2[i] - f2) * f);
        }
        return fArr3;
    }
}
