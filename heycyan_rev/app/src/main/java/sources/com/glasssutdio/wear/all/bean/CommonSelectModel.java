package com.glasssutdio.wear.all.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CommonSelectModel.kt */
@Metadata(m606d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J3\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001c"}, m607d2 = {"Lcom/glasssutdio/wear/all/bean/CommonSelectModel;", "", "name", "", "checked", "", "value", "", "desc", "(Ljava/lang/String;ZILjava/lang/String;)V", "getChecked", "()Z", "setChecked", "(Z)V", "getDesc", "()Ljava/lang/String;", "getName", "getValue", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class CommonSelectModel {
    private boolean checked;
    private final String desc;
    private final String name;
    private final int value;

    public static /* synthetic */ CommonSelectModel copy$default(CommonSelectModel commonSelectModel, String str, boolean z, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = commonSelectModel.name;
        }
        if ((i2 & 2) != 0) {
            z = commonSelectModel.checked;
        }
        if ((i2 & 4) != 0) {
            i = commonSelectModel.value;
        }
        if ((i2 & 8) != 0) {
            str2 = commonSelectModel.desc;
        }
        return commonSelectModel.copy(str, z, i, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getChecked() {
        return this.checked;
    }

    /* renamed from: component3, reason: from getter */
    public final int getValue() {
        return this.value;
    }

    /* renamed from: component4, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    public final CommonSelectModel copy(String name, boolean checked, int value, String desc) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new CommonSelectModel(name, checked, value, desc);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CommonSelectModel)) {
            return false;
        }
        CommonSelectModel commonSelectModel = (CommonSelectModel) other;
        return Intrinsics.areEqual(this.name, commonSelectModel.name) && this.checked == commonSelectModel.checked && this.value == commonSelectModel.value && Intrinsics.areEqual(this.desc, commonSelectModel.desc);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = this.name.hashCode() * 31;
        boolean z = this.checked;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int iHashCode2 = (((iHashCode + i) * 31) + Integer.hashCode(this.value)) * 31;
        String str = this.desc;
        return iHashCode2 + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "CommonSelectModel(name=" + this.name + ", checked=" + this.checked + ", value=" + this.value + ", desc=" + this.desc + ')';
    }

    public CommonSelectModel(String name, boolean z, int i, String str) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.checked = z;
        this.value = i;
        this.desc = str;
    }

    public /* synthetic */ CommonSelectModel(String str, boolean z, int i, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? null : str2);
    }

    public final String getName() {
        return this.name;
    }

    public final boolean getChecked() {
        return this.checked;
    }

    public final void setChecked(boolean z) {
        this.checked = z;
    }

    public final int getValue() {
        return this.value;
    }

    public final String getDesc() {
        return this.desc;
    }
}
