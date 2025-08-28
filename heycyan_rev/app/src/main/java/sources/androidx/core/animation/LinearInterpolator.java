package androidx.core.animation;

import android.content.Context;
import android.util.AttributeSet;

/* loaded from: classes.dex */
public class LinearInterpolator implements Interpolator {
    @Override // androidx.core.animation.Interpolator
    public float getInterpolation(float f) {
        return f;
    }

    public LinearInterpolator() {
    }

    public LinearInterpolator(Context context, AttributeSet attributeSet) {
    }
}
