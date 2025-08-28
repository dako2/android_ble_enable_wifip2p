package com.glasssutdio.wear.home.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppUpgradeModel.kt */
@Metadata(m606d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001a"}, m607d2 = {"Lcom/glasssutdio/wear/home/bean/AppUpgradeModel;", "", "appName", "", "appVersionCode", "", "force", "", "upgrade", "(Ljava/lang/String;IZI)V", "getAppName", "()Ljava/lang/String;", "getAppVersionCode", "()I", "getForce", "()Z", "getUpgrade", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class AppUpgradeModel {
    private final String appName;
    private final int appVersionCode;
    private final boolean force;
    private final int upgrade;

    public static /* synthetic */ AppUpgradeModel copy$default(AppUpgradeModel appUpgradeModel, String str, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = appUpgradeModel.appName;
        }
        if ((i3 & 2) != 0) {
            i = appUpgradeModel.appVersionCode;
        }
        if ((i3 & 4) != 0) {
            z = appUpgradeModel.force;
        }
        if ((i3 & 8) != 0) {
            i2 = appUpgradeModel.upgrade;
        }
        return appUpgradeModel.copy(str, i, z, i2);
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
    public final boolean getForce() {
        return this.force;
    }

    /* renamed from: component4, reason: from getter */
    public final int getUpgrade() {
        return this.upgrade;
    }

    public final AppUpgradeModel copy(String appName, int appVersionCode, boolean force, int upgrade) {
        Intrinsics.checkNotNullParameter(appName, "appName");
        return new AppUpgradeModel(appName, appVersionCode, force, upgrade);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppUpgradeModel)) {
            return false;
        }
        AppUpgradeModel appUpgradeModel = (AppUpgradeModel) other;
        return Intrinsics.areEqual(this.appName, appUpgradeModel.appName) && this.appVersionCode == appUpgradeModel.appVersionCode && this.force == appUpgradeModel.force && this.upgrade == appUpgradeModel.upgrade;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((this.appName.hashCode() * 31) + Integer.hashCode(this.appVersionCode)) * 31;
        boolean z = this.force;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return ((iHashCode + i) * 31) + Integer.hashCode(this.upgrade);
    }

    public String toString() {
        return "AppUpgradeModel(appName=" + this.appName + ", appVersionCode=" + this.appVersionCode + ", force=" + this.force + ", upgrade=" + this.upgrade + ')';
    }

    public AppUpgradeModel(String appName, int i, boolean z, int i2) {
        Intrinsics.checkNotNullParameter(appName, "appName");
        this.appName = appName;
        this.appVersionCode = i;
        this.force = z;
        this.upgrade = i2;
    }

    public final String getAppName() {
        return this.appName;
    }

    public final int getAppVersionCode() {
        return this.appVersionCode;
    }

    public final boolean getForce() {
        return this.force;
    }

    public final int getUpgrade() {
        return this.upgrade;
    }
}
