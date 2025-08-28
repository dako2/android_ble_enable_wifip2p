package com.glasssutdio.wear.all.bean.Req;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CheckVersionReq.kt */
@Metadata(m606d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u0019"}, m607d2 = {"Lcom/glasssutdio/wear/all/bean/Req/CheckVersionReq;", "", "appName", "", "appVersionCode", "", "appVersionName", "os", "(Ljava/lang/String;ILjava/lang/String;I)V", "getAppName", "()Ljava/lang/String;", "getAppVersionCode", "()I", "getAppVersionName", "getOs", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class CheckVersionReq {
    private final String appName;
    private final int appVersionCode;
    private final String appVersionName;
    private final int os;

    public static /* synthetic */ CheckVersionReq copy$default(CheckVersionReq checkVersionReq, String str, int i, String str2, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = checkVersionReq.appName;
        }
        if ((i3 & 2) != 0) {
            i = checkVersionReq.appVersionCode;
        }
        if ((i3 & 4) != 0) {
            str2 = checkVersionReq.appVersionName;
        }
        if ((i3 & 8) != 0) {
            i2 = checkVersionReq.os;
        }
        return checkVersionReq.copy(str, i, str2, i2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAppName() {
        return this.appName;
    }

    /* renamed from: component2, reason: from getter */
    public final int getAppVersionCode() {
        return this.appVersionCode;
    }

    /* renamed from: component3, reason: from getter */
    public final String getAppVersionName() {
        return this.appVersionName;
    }

    /* renamed from: component4, reason: from getter */
    public final int getOs() {
        return this.os;
    }

    public final CheckVersionReq copy(String appName, int appVersionCode, String appVersionName, int os) {
        Intrinsics.checkNotNullParameter(appName, "appName");
        Intrinsics.checkNotNullParameter(appVersionName, "appVersionName");
        return new CheckVersionReq(appName, appVersionCode, appVersionName, os);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CheckVersionReq)) {
            return false;
        }
        CheckVersionReq checkVersionReq = (CheckVersionReq) other;
        return Intrinsics.areEqual(this.appName, checkVersionReq.appName) && this.appVersionCode == checkVersionReq.appVersionCode && Intrinsics.areEqual(this.appVersionName, checkVersionReq.appVersionName) && this.os == checkVersionReq.os;
    }

    public int hashCode() {
        return (((((this.appName.hashCode() * 31) + Integer.hashCode(this.appVersionCode)) * 31) + this.appVersionName.hashCode()) * 31) + Integer.hashCode(this.os);
    }

    public String toString() {
        return "CheckVersionReq(appName=" + this.appName + ", appVersionCode=" + this.appVersionCode + ", appVersionName=" + this.appVersionName + ", os=" + this.os + ')';
    }

    public CheckVersionReq(String appName, int i, String appVersionName, int i2) {
        Intrinsics.checkNotNullParameter(appName, "appName");
        Intrinsics.checkNotNullParameter(appVersionName, "appVersionName");
        this.appName = appName;
        this.appVersionCode = i;
        this.appVersionName = appVersionName;
        this.os = i2;
    }

    public /* synthetic */ CheckVersionReq(String str, int i, String str2, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, str2, (i3 & 8) != 0 ? 2 : i2);
    }

    public final String getAppName() {
        return this.appName;
    }

    public final int getAppVersionCode() {
        return this.appVersionCode;
    }

    public final String getAppVersionName() {
        return this.appVersionName;
    }

    public final int getOs() {
        return this.os;
    }
}
