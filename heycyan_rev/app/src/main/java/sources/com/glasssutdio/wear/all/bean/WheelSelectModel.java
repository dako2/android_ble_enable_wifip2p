package com.glasssutdio.wear.all.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: WheelSelectModel.kt */
@Metadata(m606d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, m607d2 = {"Lcom/glasssutdio/wear/all/bean/WheelSelectModel;", "", "name", "", "isChecked", "", "isEmptyView", "(Ljava/lang/String;ZZ)V", "()Z", "setChecked", "(Z)V", "getName", "()Ljava/lang/String;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class WheelSelectModel {
    private boolean isChecked;
    private final boolean isEmptyView;
    private final String name;

    public WheelSelectModel() {
        this(null, false, false, 7, null);
    }

    public WheelSelectModel(String str, boolean z, boolean z2) {
        this.name = str;
        this.isChecked = z;
        this.isEmptyView = z2;
    }

    public /* synthetic */ WheelSelectModel(String str, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? false : z, (i & 4) != 0 ? false : z2);
    }

    public final String getName() {
        return this.name;
    }

    /* renamed from: isChecked, reason: from getter */
    public final boolean getIsChecked() {
        return this.isChecked;
    }

    /* renamed from: isEmptyView, reason: from getter */
    public final boolean getIsEmptyView() {
        return this.isEmptyView;
    }

    public final void setChecked(boolean z) {
        this.isChecked = z;
    }
}
