package com.glasssutdio.wear.home.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AiHelperModel.kt */
@Metadata(m606d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0016"}, m607d2 = {"Lcom/glasssutdio/wear/home/bean/AiHelperModel;", "", "content", "", "subContent", "img", "", "(Ljava/lang/String;Ljava/lang/String;I)V", "getContent", "()Ljava/lang/String;", "getImg", "()I", "getSubContent", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class AiHelperModel {
    private final String content;
    private final int img;
    private final String subContent;

    public static /* synthetic */ AiHelperModel copy$default(AiHelperModel aiHelperModel, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = aiHelperModel.content;
        }
        if ((i2 & 2) != 0) {
            str2 = aiHelperModel.subContent;
        }
        if ((i2 & 4) != 0) {
            i = aiHelperModel.img;
        }
        return aiHelperModel.copy(str, str2, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSubContent() {
        return this.subContent;
    }

    /* renamed from: component3, reason: from getter */
    public final int getImg() {
        return this.img;
    }

    public final AiHelperModel copy(String content, String subContent, int img) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(subContent, "subContent");
        return new AiHelperModel(content, subContent, img);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AiHelperModel)) {
            return false;
        }
        AiHelperModel aiHelperModel = (AiHelperModel) other;
        return Intrinsics.areEqual(this.content, aiHelperModel.content) && Intrinsics.areEqual(this.subContent, aiHelperModel.subContent) && this.img == aiHelperModel.img;
    }

    public int hashCode() {
        return (((this.content.hashCode() * 31) + this.subContent.hashCode()) * 31) + Integer.hashCode(this.img);
    }

    public String toString() {
        return "AiHelperModel(content=" + this.content + ", subContent=" + this.subContent + ", img=" + this.img + ')';
    }

    public AiHelperModel(String content, String subContent, int i) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(subContent, "subContent");
        this.content = content;
        this.subContent = subContent;
        this.img = i;
    }

    public /* synthetic */ AiHelperModel(String str, String str2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i2 & 4) != 0 ? 0 : i);
    }

    public final String getContent() {
        return this.content;
    }

    public final String getSubContent() {
        return this.subContent;
    }

    public final int getImg() {
        return this.img;
    }
}
