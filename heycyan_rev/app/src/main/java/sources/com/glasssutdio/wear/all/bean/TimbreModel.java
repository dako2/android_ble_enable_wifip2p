package com.glasssutdio.wear.all.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TimbreModel.kt */
@Metadata(m606d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0014\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\bHÆ\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001c"}, m607d2 = {"Lcom/glasssutdio/wear/all/bean/TimbreModel;", "", "title", "", "icon", "", "itemBgRes", "checked", "", "(Ljava/lang/String;IIZ)V", "getChecked", "()Z", "setChecked", "(Z)V", "getIcon", "()I", "getItemBgRes", "getTitle", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class TimbreModel {
    private boolean checked;
    private final int icon;
    private final int itemBgRes;
    private final String title;

    public static /* synthetic */ TimbreModel copy$default(TimbreModel timbreModel, String str, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = timbreModel.title;
        }
        if ((i3 & 2) != 0) {
            i = timbreModel.icon;
        }
        if ((i3 & 4) != 0) {
            i2 = timbreModel.itemBgRes;
        }
        if ((i3 & 8) != 0) {
            z = timbreModel.checked;
        }
        return timbreModel.copy(str, i, i2, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component2, reason: from getter */
    public final int getIcon() {
        return this.icon;
    }

    /* renamed from: component3, reason: from getter */
    public final int getItemBgRes() {
        return this.itemBgRes;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getChecked() {
        return this.checked;
    }

    public final TimbreModel copy(String title, int icon, int itemBgRes, boolean checked) {
        Intrinsics.checkNotNullParameter(title, "title");
        return new TimbreModel(title, icon, itemBgRes, checked);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TimbreModel)) {
            return false;
        }
        TimbreModel timbreModel = (TimbreModel) other;
        return Intrinsics.areEqual(this.title, timbreModel.title) && this.icon == timbreModel.icon && this.itemBgRes == timbreModel.itemBgRes && this.checked == timbreModel.checked;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((this.title.hashCode() * 31) + Integer.hashCode(this.icon)) * 31) + Integer.hashCode(this.itemBgRes)) * 31;
        boolean z = this.checked;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return iHashCode + i;
    }

    public String toString() {
        return "TimbreModel(title=" + this.title + ", icon=" + this.icon + ", itemBgRes=" + this.itemBgRes + ", checked=" + this.checked + ')';
    }

    public TimbreModel(String title, int i, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.title = title;
        this.icon = i;
        this.itemBgRes = i2;
        this.checked = z;
    }

    public /* synthetic */ TimbreModel(String str, int i, int i2, boolean z, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, i2, (i3 & 8) != 0 ? false : z);
    }

    public final boolean getChecked() {
        return this.checked;
    }

    public final int getIcon() {
        return this.icon;
    }

    public final int getItemBgRes() {
        return this.itemBgRes;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setChecked(boolean z) {
        this.checked = z;
    }
}
