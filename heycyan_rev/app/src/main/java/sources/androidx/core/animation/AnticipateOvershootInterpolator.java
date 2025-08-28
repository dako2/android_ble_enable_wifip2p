package androidx.core.animation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/* loaded from: classes.dex */
public class AnticipateOvershootInterpolator implements Interpolator {
    private final float mTension;

    /* renamed from: a */
    private static float m9a(float f, float f2) {
        return f * f * (((1.0f + f2) * f) - f2);
    }

    /* renamed from: o */
    private static float m10o(float f, float f2) {
        return f * f * (((1.0f + f2) * f) + f2);
    }

    public AnticipateOvershootInterpolator() {
        this.mTension = 3.0f;
    }

    public AnticipateOvershootInterpolator(float f) {
        this.mTension = f * 1.5f;
    }

    public AnticipateOvershootInterpolator(float f, float f2) {
        this.mTension = f * f2;
    }

    public AnticipateOvershootInterpolator(Context context, AttributeSet attributeSet) {
        this(context.getResources(), context.getTheme(), attributeSet);
    }

    AnticipateOvershootInterpolator(Resources resources, Resources.Theme theme, AttributeSet attributeSet) {
        TypedArray typedArrayObtainAttributes;
        if (theme != null) {
            typedArrayObtainAttributes = theme.obtainStyledAttributes(attributeSet, AndroidResources.STYLEABLE_ANTICIPATEOVERSHOOT_INTERPOLATOR, 0, 0);
        } else {
            typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, AndroidResources.STYLEABLE_ANTICIPATEOVERSHOOT_INTERPOLATOR);
        }
        this.mTension = typedArrayObtainAttributes.getFloat(0, 2.0f) * typedArrayObtainAttributes.getFloat(1, 1.5f);
        typedArrayObtainAttributes.recycle();
    }

    @Override // androidx.core.animation.Interpolator
    public float getInterpolation(float f) {
        float fM10o;
        if (f < 0.5f) {
            fM10o = m9a(f * 2.0f, this.mTension);
        } else {
            fM10o = m10o((f * 2.0f) - 2.0f, this.mTension) + 2.0f;
        }
        return fM10o * 0.5f;
    }
}
