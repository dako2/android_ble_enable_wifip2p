package com.glasssutdio.wear.depository.bean;

import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: DeviceSetting.kt */
@JsonClass(generateAdapter = true)
@Metadata(m606d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0010"}, m607d2 = {"Lcom/glasssutdio/wear/depository/bean/DeviceSetting;", "", "test", "", "(Z)V", "getTest", "()Z", "setTest", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class DeviceSetting {
    private boolean test;

    public DeviceSetting() {
        this(false, 1, null);
    }

    public static /* synthetic */ DeviceSetting copy$default(DeviceSetting deviceSetting, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = deviceSetting.test;
        }
        return deviceSetting.copy(z);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getTest() {
        return this.test;
    }

    public final DeviceSetting copy(boolean test) {
        return new DeviceSetting(test);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof DeviceSetting) && this.test == ((DeviceSetting) other).test;
    }

    public int hashCode() {
        boolean z = this.test;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public String toString() {
        return "DeviceSetting(test=" + this.test + ')';
    }

    public DeviceSetting(boolean z) {
        this.test = z;
    }

    public /* synthetic */ DeviceSetting(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public final boolean getTest() {
        return this.test;
    }

    public final void setTest(boolean z) {
        this.test = z;
    }
}
