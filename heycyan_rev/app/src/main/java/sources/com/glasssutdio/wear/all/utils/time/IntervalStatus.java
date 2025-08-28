package com.glasssutdio.wear.all.utils.time;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: IntervalStatus.kt */
@Metadata(m606d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, m607d2 = {"Lcom/glasssutdio/wear/all/utils/time/IntervalStatus;", "", "(Ljava/lang/String;I)V", "STATE_ACTIVE", "STATE_IDLE", "STATE_PAUSE", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class IntervalStatus {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ IntervalStatus[] $VALUES;
    public static final IntervalStatus STATE_ACTIVE = new IntervalStatus("STATE_ACTIVE", 0);
    public static final IntervalStatus STATE_IDLE = new IntervalStatus("STATE_IDLE", 1);
    public static final IntervalStatus STATE_PAUSE = new IntervalStatus("STATE_PAUSE", 2);

    private static final /* synthetic */ IntervalStatus[] $values() {
        return new IntervalStatus[]{STATE_ACTIVE, STATE_IDLE, STATE_PAUSE};
    }

    public static EnumEntries<IntervalStatus> getEntries() {
        return $ENTRIES;
    }

    public static IntervalStatus valueOf(String str) {
        return (IntervalStatus) Enum.valueOf(IntervalStatus.class, str);
    }

    public static IntervalStatus[] values() {
        return (IntervalStatus[]) $VALUES.clone();
    }

    private IntervalStatus(String str, int i) {
    }

    static {
        IntervalStatus[] intervalStatusArr$values = $values();
        $VALUES = intervalStatusArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(intervalStatusArr$values);
    }
}
