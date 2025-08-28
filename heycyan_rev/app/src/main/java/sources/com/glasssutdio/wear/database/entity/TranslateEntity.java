package com.glasssutdio.wear.database.entity;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TranslateEntity.kt */
@Metadata(m606d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006¢\u0006\u0002\u0010\u000bJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\tHÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003JE\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\tHÖ\u0001J\t\u0010\"\u001a\u00020\u0006HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u001e\u0010\n\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\r¨\u0006#"}, m607d2 = {"Lcom/glasssutdio/wear/database/entity/TranslateEntity;", "", "uid", "", "createTime", "srcContent", "", "dstContent", "originType", "", "translateTitle", "(JJLjava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getCreateTime", "()J", "getDstContent", "()Ljava/lang/String;", "getOriginType", "()I", "getSrcContent", "getTranslateTitle", "setTranslateTitle", "(Ljava/lang/String;)V", "getUid", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class TranslateEntity {
    private final long createTime;
    private final String dstContent;
    private final int originType;
    private final String srcContent;
    private String translateTitle;
    private final long uid;

    /* renamed from: component1, reason: from getter */
    public final long getUid() {
        return this.uid;
    }

    /* renamed from: component2, reason: from getter */
    public final long getCreateTime() {
        return this.createTime;
    }

    /* renamed from: component3, reason: from getter */
    public final String getSrcContent() {
        return this.srcContent;
    }

    /* renamed from: component4, reason: from getter */
    public final String getDstContent() {
        return this.dstContent;
    }

    /* renamed from: component5, reason: from getter */
    public final int getOriginType() {
        return this.originType;
    }

    /* renamed from: component6, reason: from getter */
    public final String getTranslateTitle() {
        return this.translateTitle;
    }

    public final TranslateEntity copy(long uid, long createTime, String srcContent, String dstContent, int originType, String translateTitle) {
        Intrinsics.checkNotNullParameter(srcContent, "srcContent");
        Intrinsics.checkNotNullParameter(dstContent, "dstContent");
        Intrinsics.checkNotNullParameter(translateTitle, "translateTitle");
        return new TranslateEntity(uid, createTime, srcContent, dstContent, originType, translateTitle);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TranslateEntity)) {
            return false;
        }
        TranslateEntity translateEntity = (TranslateEntity) other;
        return this.uid == translateEntity.uid && this.createTime == translateEntity.createTime && Intrinsics.areEqual(this.srcContent, translateEntity.srcContent) && Intrinsics.areEqual(this.dstContent, translateEntity.dstContent) && this.originType == translateEntity.originType && Intrinsics.areEqual(this.translateTitle, translateEntity.translateTitle);
    }

    public int hashCode() {
        return (((((((((Long.hashCode(this.uid) * 31) + Long.hashCode(this.createTime)) * 31) + this.srcContent.hashCode()) * 31) + this.dstContent.hashCode()) * 31) + Integer.hashCode(this.originType)) * 31) + this.translateTitle.hashCode();
    }

    public String toString() {
        return "TranslateEntity(uid=" + this.uid + ", createTime=" + this.createTime + ", srcContent=" + this.srcContent + ", dstContent=" + this.dstContent + ", originType=" + this.originType + ", translateTitle=" + this.translateTitle + ')';
    }

    public TranslateEntity(long j, long j2, String srcContent, String dstContent, int i, String translateTitle) {
        Intrinsics.checkNotNullParameter(srcContent, "srcContent");
        Intrinsics.checkNotNullParameter(dstContent, "dstContent");
        Intrinsics.checkNotNullParameter(translateTitle, "translateTitle");
        this.uid = j;
        this.createTime = j2;
        this.srcContent = srcContent;
        this.dstContent = dstContent;
        this.originType = i;
        this.translateTitle = translateTitle;
    }

    public final long getUid() {
        return this.uid;
    }

    public final long getCreateTime() {
        return this.createTime;
    }

    public final String getSrcContent() {
        return this.srcContent;
    }

    public final String getDstContent() {
        return this.dstContent;
    }

    public final int getOriginType() {
        return this.originType;
    }

    public /* synthetic */ TranslateEntity(long j, long j2, String str, String str2, int i, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, str, str2, (i2 & 16) != 0 ? 1 : i, (i2 & 32) != 0 ? "" : str3);
    }

    public final String getTranslateTitle() {
        return this.translateTitle;
    }

    public final void setTranslateTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.translateTitle = str;
    }
}
