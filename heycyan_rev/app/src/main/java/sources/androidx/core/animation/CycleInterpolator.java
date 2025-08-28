package androidx.core.animation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/* loaded from: classes.dex */
public class CycleInterpolator implements Interpolator {
    private float mCycles;

    public CycleInterpolator(float f) {
        this.mCycles = f;
    }

    public CycleInterpolator(Context context, AttributeSet attributeSet) {
        this(context.getResources(), context.getTheme(), attributeSet);
    }

    CycleInterpolator(Resources resources, Resources.Theme theme, AttributeSet attributeSet) {
        TypedArray typedArrayObtainAttributes;
        if (theme != null) {
            typedArrayObtainAttributes = theme.obtainStyledAttributes(attributeSet, AndroidResources.STYLEABLE_CYCLE_INTERPOLATOR, 0, 0);
        } else {
            typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, AndroidResources.STYLEABLE_CYCLE_INTERPOLATOR);
        }
        this.mCycles = typedArrayObtainAttributes.getFloat(0, 1.0f);
        typedArrayObtainAttributes.recycle();
    }

    @Override // androidx.core.animation.Interpolator
    public float getInterpolation(float f) {
        return (float) Math.sin(this.mCycles * 2.0f * 3.141592653589793d * f);
    }
}
