package com.glasssutdio.wear.all.view.rippleview;

import kotlin.Metadata;

/* compiled from: RippleCircle.kt */
@Metadata(m606d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, m607d2 = {"Lcom/glasssutdio/wear/all/view/rippleview/RippleCircle;", "", "radius", "", "alpha", "", "(FI)V", "getAlpha", "()I", "setAlpha", "(I)V", "getRadius", "()F", "setRadius", "(F)V", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class RippleCircle {
    private int alpha;
    private float radius;

    public RippleCircle(float f, int i) {
        this.radius = f;
        this.alpha = i;
    }

    public final int getAlpha() {
        return this.alpha;
    }

    public final float getRadius() {
        return this.radius;
    }

    public final void setAlpha(int i) {
        this.alpha = i;
    }

    public final void setRadius(float f) {
        this.radius = f;
    }
}
