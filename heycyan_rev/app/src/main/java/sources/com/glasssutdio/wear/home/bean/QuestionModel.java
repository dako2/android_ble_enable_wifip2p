package com.glasssutdio.wear.home.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QuestionModel.kt */
@Metadata(m606d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, m607d2 = {"Lcom/glasssutdio/wear/home/bean/QuestionModel;", "", "title", "", "type", "", "(Ljava/lang/String;I)V", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "getType", "()I", "setType", "(I)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class QuestionModel {
    private String title;
    private int type;

    public static /* synthetic */ QuestionModel copy$default(QuestionModel questionModel, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = questionModel.title;
        }
        if ((i2 & 2) != 0) {
            i = questionModel.type;
        }
        return questionModel.copy(str, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component2, reason: from getter */
    public final int getType() {
        return this.type;
    }

    public final QuestionModel copy(String title, int type) {
        Intrinsics.checkNotNullParameter(title, "title");
        return new QuestionModel(title, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QuestionModel)) {
            return false;
        }
        QuestionModel questionModel = (QuestionModel) other;
        return Intrinsics.areEqual(this.title, questionModel.title) && this.type == questionModel.type;
    }

    public int hashCode() {
        return (this.title.hashCode() * 31) + Integer.hashCode(this.type);
    }

    public String toString() {
        return "QuestionModel(title=" + this.title + ", type=" + this.type + ')';
    }

    public QuestionModel(String title, int i) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.title = title;
        this.type = i;
    }

    public /* synthetic */ QuestionModel(String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? 0 : i);
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getType() {
        return this.type;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setType(int i) {
        this.type = i;
    }
}
