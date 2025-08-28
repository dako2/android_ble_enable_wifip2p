package com.glasssutdio.wear.all.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectLanguageModel.kt */
@Metadata(m606d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, m607d2 = {"Lcom/glasssutdio/wear/all/bean/SelectLanguageModel;", "", "language", "Lcom/glasssutdio/wear/all/bean/QLanguageType;", "checked", "", "(Lcom/glasssutdio/wear/all/bean/QLanguageType;Z)V", "getChecked", "()Z", "setChecked", "(Z)V", "getLanguage", "()Lcom/glasssutdio/wear/all/bean/QLanguageType;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class SelectLanguageModel {
    private boolean checked;
    private final QLanguageType language;

    public static /* synthetic */ SelectLanguageModel copy$default(SelectLanguageModel selectLanguageModel, QLanguageType qLanguageType, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            qLanguageType = selectLanguageModel.language;
        }
        if ((i & 2) != 0) {
            z = selectLanguageModel.checked;
        }
        return selectLanguageModel.copy(qLanguageType, z);
    }

    /* renamed from: component1, reason: from getter */
    public final QLanguageType getLanguage() {
        return this.language;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getChecked() {
        return this.checked;
    }

    public final SelectLanguageModel copy(QLanguageType language, boolean checked) {
        Intrinsics.checkNotNullParameter(language, "language");
        return new SelectLanguageModel(language, checked);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SelectLanguageModel)) {
            return false;
        }
        SelectLanguageModel selectLanguageModel = (SelectLanguageModel) other;
        return this.language == selectLanguageModel.language && this.checked == selectLanguageModel.checked;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = this.language.hashCode() * 31;
        boolean z = this.checked;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return iHashCode + i;
    }

    public String toString() {
        return "SelectLanguageModel(language=" + this.language + ", checked=" + this.checked + ')';
    }

    public SelectLanguageModel(QLanguageType language, boolean z) {
        Intrinsics.checkNotNullParameter(language, "language");
        this.language = language;
        this.checked = z;
    }

    public /* synthetic */ SelectLanguageModel(QLanguageType qLanguageType, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(qLanguageType, (i & 2) != 0 ? false : z);
    }

    public final boolean getChecked() {
        return this.checked;
    }

    public final QLanguageType getLanguage() {
        return this.language;
    }

    public final void setChecked(boolean z) {
        this.checked = z;
    }
}
