package com.glasssutdio.wear.home.bean;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: GlassesType.kt */
@Metadata(m606d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0013"}, m607d2 = {"Lcom/glasssutdio/wear/home/bean/GlassesType;", "", "type", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getType", "()Ljava/lang/String;", "A02", "AO3", "AM01", "KEY10", "KEY21", "KEY22", "KEY23", "KEY31", "KEY40", "KEY41", "KEY42", "KEY43", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class GlassesType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ GlassesType[] $VALUES;
    private final String type;
    public static final GlassesType A02 = new GlassesType("A02", 0, "A02");
    public static final GlassesType AO3 = new GlassesType("AO3", 1, "A03");
    public static final GlassesType AM01 = new GlassesType("AM01", 2, "AM01");
    public static final GlassesType KEY10 = new GlassesType("KEY10", 3, "Key1.0");
    public static final GlassesType KEY21 = new GlassesType("KEY21", 4, "Key2.1");
    public static final GlassesType KEY22 = new GlassesType("KEY22", 5, "Key2.2");
    public static final GlassesType KEY23 = new GlassesType("KEY23", 6, "Key2.3");
    public static final GlassesType KEY31 = new GlassesType("KEY31", 7, "key3.1");
    public static final GlassesType KEY40 = new GlassesType("KEY40", 8, "key4.0");
    public static final GlassesType KEY41 = new GlassesType("KEY41", 9, "key4.1");
    public static final GlassesType KEY42 = new GlassesType("KEY42", 10, "key4.2");
    public static final GlassesType KEY43 = new GlassesType("KEY43", 11, "key4.3");

    private static final /* synthetic */ GlassesType[] $values() {
        return new GlassesType[]{A02, AO3, AM01, KEY10, KEY21, KEY22, KEY23, KEY31, KEY40, KEY41, KEY42, KEY43};
    }

    public static EnumEntries<GlassesType> getEntries() {
        return $ENTRIES;
    }

    public static GlassesType valueOf(String str) {
        return (GlassesType) Enum.valueOf(GlassesType.class, str);
    }

    public static GlassesType[] values() {
        return (GlassesType[]) $VALUES.clone();
    }

    private GlassesType(String str, int i, String str2) {
        this.type = str2;
    }

    public final String getType() {
        return this.type;
    }

    static {
        GlassesType[] glassesTypeArr$values = $values();
        $VALUES = glassesTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(glassesTypeArr$values);
    }
}
