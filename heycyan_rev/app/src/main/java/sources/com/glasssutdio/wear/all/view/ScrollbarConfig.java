package com.glasssutdio.wear.all.view;

import android.graphics.Color;
import com.glasssutdio.wear.all.GlobalKt;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.config.CookieSpecs;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: ScrollbarConfig.kt */
@Metadata(m606d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b4\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 E2\u00020\u0001:\u0001EB}\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\r¢\u0006\u0002\u0010\u0012J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u000fHÆ\u0003J\t\u00105\u001a\u00020\u000fHÆ\u0003J\t\u00106\u001a\u00020\rHÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0003HÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\t\u0010<\u001a\u00020\nHÆ\u0003J\t\u0010=\u001a\u00020\u0003HÆ\u0003J\t\u0010>\u001a\u00020\rHÆ\u0003J\u0081\u0001\u0010?\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\rHÆ\u0001J\u0013\u0010@\u001a\u00020\r2\b\u0010A\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010B\u001a\u00020\u0003HÖ\u0001J\t\u0010C\u001a\u00020DHÖ\u0001R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0010\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0018\"\u0004\b\u001c\u0010\u001aR\u001a\u0010\u0011\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0014\"\u0004\b\u001e\u0010\u0016R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010 \"\u0004\b$\u0010\"R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010 \"\u0004\b&\u0010\"R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010 \"\u0004\b,\u0010\"R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010 \"\u0004\b.\u0010\"R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010 \"\u0004\b0\u0010\"R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010 \"\u0004\b2\u0010\"¨\u0006F"}, m607d2 = {"Lcom/glasssutdio/wear/all/view/ScrollbarConfig;", "", "trackWidth", "", "trackColor", "trackMarginEnd", "thumbWidth", "thumbHeight", "thumbColor", "thumbRadius", "", "thumbMarginEnd", "autoHide", "", "fadeDelay", "", "fadeDuration", "initiallyHidden", "(IIIIIIFIZJJZ)V", "getAutoHide", "()Z", "setAutoHide", "(Z)V", "getFadeDelay", "()J", "setFadeDelay", "(J)V", "getFadeDuration", "setFadeDuration", "getInitiallyHidden", "setInitiallyHidden", "getThumbColor", "()I", "setThumbColor", "(I)V", "getThumbHeight", "setThumbHeight", "getThumbMarginEnd", "setThumbMarginEnd", "getThumbRadius", "()F", "setThumbRadius", "(F)V", "getThumbWidth", "setThumbWidth", "getTrackColor", "setTrackColor", "getTrackMarginEnd", "setTrackMarginEnd", "getTrackWidth", "setTrackWidth", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class ScrollbarConfig {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private boolean autoHide;
    private long fadeDelay;
    private long fadeDuration;
    private boolean initiallyHidden;
    private int thumbColor;
    private int thumbHeight;
    private int thumbMarginEnd;
    private float thumbRadius;
    private int thumbWidth;
    private int trackColor;
    private int trackMarginEnd;
    private int trackWidth;

    public ScrollbarConfig() {
        this(0, 0, 0, 0, 0, 0, 0.0f, 0, false, 0L, 0L, false, 4095, null);
    }

    /* renamed from: component1, reason: from getter */
    public final int getTrackWidth() {
        return this.trackWidth;
    }

    /* renamed from: component10, reason: from getter */
    public final long getFadeDelay() {
        return this.fadeDelay;
    }

    /* renamed from: component11, reason: from getter */
    public final long getFadeDuration() {
        return this.fadeDuration;
    }

    /* renamed from: component12, reason: from getter */
    public final boolean getInitiallyHidden() {
        return this.initiallyHidden;
    }

    /* renamed from: component2, reason: from getter */
    public final int getTrackColor() {
        return this.trackColor;
    }

    /* renamed from: component3, reason: from getter */
    public final int getTrackMarginEnd() {
        return this.trackMarginEnd;
    }

    /* renamed from: component4, reason: from getter */
    public final int getThumbWidth() {
        return this.thumbWidth;
    }

    /* renamed from: component5, reason: from getter */
    public final int getThumbHeight() {
        return this.thumbHeight;
    }

    /* renamed from: component6, reason: from getter */
    public final int getThumbColor() {
        return this.thumbColor;
    }

    /* renamed from: component7, reason: from getter */
    public final float getThumbRadius() {
        return this.thumbRadius;
    }

    /* renamed from: component8, reason: from getter */
    public final int getThumbMarginEnd() {
        return this.thumbMarginEnd;
    }

    /* renamed from: component9, reason: from getter */
    public final boolean getAutoHide() {
        return this.autoHide;
    }

    public final ScrollbarConfig copy(int trackWidth, int trackColor, int trackMarginEnd, int thumbWidth, int thumbHeight, int thumbColor, float thumbRadius, int thumbMarginEnd, boolean autoHide, long fadeDelay, long fadeDuration, boolean initiallyHidden) {
        return new ScrollbarConfig(trackWidth, trackColor, trackMarginEnd, thumbWidth, thumbHeight, thumbColor, thumbRadius, thumbMarginEnd, autoHide, fadeDelay, fadeDuration, initiallyHidden);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ScrollbarConfig)) {
            return false;
        }
        ScrollbarConfig scrollbarConfig = (ScrollbarConfig) other;
        return this.trackWidth == scrollbarConfig.trackWidth && this.trackColor == scrollbarConfig.trackColor && this.trackMarginEnd == scrollbarConfig.trackMarginEnd && this.thumbWidth == scrollbarConfig.thumbWidth && this.thumbHeight == scrollbarConfig.thumbHeight && this.thumbColor == scrollbarConfig.thumbColor && Float.compare(this.thumbRadius, scrollbarConfig.thumbRadius) == 0 && this.thumbMarginEnd == scrollbarConfig.thumbMarginEnd && this.autoHide == scrollbarConfig.autoHide && this.fadeDelay == scrollbarConfig.fadeDelay && this.fadeDuration == scrollbarConfig.fadeDuration && this.initiallyHidden == scrollbarConfig.initiallyHidden;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((((((((((((Integer.hashCode(this.trackWidth) * 31) + Integer.hashCode(this.trackColor)) * 31) + Integer.hashCode(this.trackMarginEnd)) * 31) + Integer.hashCode(this.thumbWidth)) * 31) + Integer.hashCode(this.thumbHeight)) * 31) + Integer.hashCode(this.thumbColor)) * 31) + Float.hashCode(this.thumbRadius)) * 31) + Integer.hashCode(this.thumbMarginEnd)) * 31;
        boolean z = this.autoHide;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int iHashCode2 = (((((iHashCode + i) * 31) + Long.hashCode(this.fadeDelay)) * 31) + Long.hashCode(this.fadeDuration)) * 31;
        boolean z2 = this.initiallyHidden;
        return iHashCode2 + (z2 ? 1 : z2 ? 1 : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ScrollbarConfig(trackWidth=");
        sb.append(this.trackWidth).append(", trackColor=").append(this.trackColor).append(", trackMarginEnd=").append(this.trackMarginEnd).append(", thumbWidth=").append(this.thumbWidth).append(", thumbHeight=").append(this.thumbHeight).append(", thumbColor=").append(this.thumbColor).append(", thumbRadius=").append(this.thumbRadius).append(", thumbMarginEnd=").append(this.thumbMarginEnd).append(", autoHide=").append(this.autoHide).append(", fadeDelay=").append(this.fadeDelay).append(", fadeDuration=").append(this.fadeDuration).append(", initiallyHidden=");
        sb.append(this.initiallyHidden).append(')');
        return sb.toString();
    }

    public ScrollbarConfig(int i, int i2, int i3, int i4, int i5, int i6, float f, int i7, boolean z, long j, long j2, boolean z2) {
        this.trackWidth = i;
        this.trackColor = i2;
        this.trackMarginEnd = i3;
        this.thumbWidth = i4;
        this.thumbHeight = i5;
        this.thumbColor = i6;
        this.thumbRadius = f;
        this.thumbMarginEnd = i7;
        this.autoHide = z;
        this.fadeDelay = j;
        this.fadeDuration = j2;
        this.initiallyHidden = z2;
    }

    public final int getTrackWidth() {
        return this.trackWidth;
    }

    public final void setTrackWidth(int i) {
        this.trackWidth = i;
    }

    public /* synthetic */ ScrollbarConfig(int i, int i2, int i3, int i4, int i5, int i6, float f, int i7, boolean z, long j, long j2, boolean z2, int i8, DefaultConstructorMarker defaultConstructorMarker) {
        this((i8 & 1) != 0 ? 4 : i, (i8 & 2) != 0 ? Color.parseColor("#40000000") : i2, (i8 & 4) != 0 ? 8 : i3, (i8 & 8) != 0 ? 20 : i4, (i8 & 16) != 0 ? 40 : i5, (i8 & 32) != 0 ? Color.parseColor("#88000000") : i6, (i8 & 64) != 0 ? 10.0f : f, (i8 & 128) != 0 ? 5 : i7, (i8 & 256) != 0 ? true : z, (i8 & 512) != 0 ? 150L : j, (i8 & 1024) != 0 ? 1000L : j2, (i8 & 2048) == 0 ? z2 : true);
    }

    public final int getTrackColor() {
        return this.trackColor;
    }

    public final void setTrackColor(int i) {
        this.trackColor = i;
    }

    public final int getTrackMarginEnd() {
        return this.trackMarginEnd;
    }

    public final void setTrackMarginEnd(int i) {
        this.trackMarginEnd = i;
    }

    public final int getThumbWidth() {
        return this.thumbWidth;
    }

    public final void setThumbWidth(int i) {
        this.thumbWidth = i;
    }

    public final int getThumbHeight() {
        return this.thumbHeight;
    }

    public final void setThumbHeight(int i) {
        this.thumbHeight = i;
    }

    public final int getThumbColor() {
        return this.thumbColor;
    }

    public final void setThumbColor(int i) {
        this.thumbColor = i;
    }

    public final float getThumbRadius() {
        return this.thumbRadius;
    }

    public final void setThumbRadius(float f) {
        this.thumbRadius = f;
    }

    public final int getThumbMarginEnd() {
        return this.thumbMarginEnd;
    }

    public final void setThumbMarginEnd(int i) {
        this.thumbMarginEnd = i;
    }

    public final boolean getAutoHide() {
        return this.autoHide;
    }

    public final void setAutoHide(boolean z) {
        this.autoHide = z;
    }

    public final long getFadeDelay() {
        return this.fadeDelay;
    }

    public final void setFadeDelay(long j) {
        this.fadeDelay = j;
    }

    public final long getFadeDuration() {
        return this.fadeDuration;
    }

    public final void setFadeDuration(long j) {
        this.fadeDuration = j;
    }

    public final boolean getInitiallyHidden() {
        return this.initiallyHidden;
    }

    public final void setInitiallyHidden(boolean z) {
        this.initiallyHidden = z;
    }

    /* compiled from: ScrollbarConfig.kt */
    @Metadata(m606d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0004¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/all/view/ScrollbarConfig$Companion;", "", "()V", "alwaysVisible", "Lcom/glasssutdio/wear/all/view/ScrollbarConfig;", CookieSpecs.DEFAULT, "minimal", "prominent", "quickResponse", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* renamed from: default, reason: not valid java name */
        public final ScrollbarConfig m779default() {
            return new ScrollbarConfig(0, 0, 0, 0, 0, 0, 0.0f, 0, false, 0L, 0L, false, 4095, null);
        }

        public final ScrollbarConfig alwaysVisible() {
            return new ScrollbarConfig(0, 0, 0, 0, 0, 0, 0.0f, 0, false, 0L, 0L, false, 1791, null);
        }

        public final ScrollbarConfig minimal() {
            return new ScrollbarConfig(2, Color.parseColor("#20000000"), 0, 12, 30, Color.parseColor("#45474c"), 6.0f, 0, false, 0L, 0L, true, 1924, null);
        }

        public final ScrollbarConfig prominent() {
            return new ScrollbarConfig(6, Color.parseColor("#00000000"), GlobalKt.getDp((Number) 8), GlobalKt.getDp((Number) 18), GlobalKt.getDp((Number) 60), Color.parseColor("#45474c"), GlobalKt.getDp((Number) 12), GlobalKt.getDp((Number) 8), true, 800L, 300L, false);
        }

        public final ScrollbarConfig quickResponse() {
            return new ScrollbarConfig(0, 0, 0, 0, 0, 0, 0.0f, 0, true, 800L, 300L, true, 255, null);
        }
    }
}
