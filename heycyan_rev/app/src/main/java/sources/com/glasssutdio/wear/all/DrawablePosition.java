package com.glasssutdio.wear.all;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: TextViewExt.kt */
@Metadata(m606d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m607d2 = {"Lcom/glasssutdio/wear/all/DrawablePosition;", "", "(Ljava/lang/String;I)V", "START", "TOP", "END", "BOTTOM", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class DrawablePosition {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ DrawablePosition[] $VALUES;
    public static final DrawablePosition START = new DrawablePosition("START", 0);
    public static final DrawablePosition TOP = new DrawablePosition("TOP", 1);
    public static final DrawablePosition END = new DrawablePosition("END", 2);
    public static final DrawablePosition BOTTOM = new DrawablePosition("BOTTOM", 3);

    private static final /* synthetic */ DrawablePosition[] $values() {
        return new DrawablePosition[]{START, TOP, END, BOTTOM};
    }

    public static EnumEntries<DrawablePosition> getEntries() {
        return $ENTRIES;
    }

    public static DrawablePosition valueOf(String str) {
        return (DrawablePosition) Enum.valueOf(DrawablePosition.class, str);
    }

    public static DrawablePosition[] values() {
        return (DrawablePosition[]) $VALUES.clone();
    }

    private DrawablePosition(String str, int i) {
    }

    static {
        DrawablePosition[] drawablePositionArr$values = $values();
        $VALUES = drawablePositionArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(drawablePositionArr$values);
    }
}
