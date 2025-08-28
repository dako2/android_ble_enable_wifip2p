package com.glasssutdio.wear.p003ai.bean;

import androidx.core.app.NotificationCompat;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TranslateOneModel.kt */
@JsonClass(generateAdapter = true)
@Metadata(m606d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001d\b\u0087\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003JO\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010 \u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J\t\u0010#\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u000f\"\u0004\b\u0012\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015¨\u0006$"}, m607d2 = {"Lcom/glasssutdio/wear/ai/bean/TranslateOneModel;", "", "mItemType", "", "content", "", "isPlaying", "", "isBottom", "languageCode", "sid", NotificationCompat.CATEGORY_STATUS, "(ILjava/lang/String;ZZLjava/lang/String;Ljava/lang/String;I)V", "getContent", "()Ljava/lang/String;", "()Z", "setBottom", "(Z)V", "setPlaying", "getLanguageCode", "getMItemType", "()I", "getSid", "getStatus", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class TranslateOneModel {
    private final String content;
    private boolean isBottom;
    private boolean isPlaying;
    private final String languageCode;
    private final int mItemType;
    private final String sid;
    private final int status;

    public static /* synthetic */ TranslateOneModel copy$default(TranslateOneModel translateOneModel, int i, String str, boolean z, boolean z2, String str2, String str3, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = translateOneModel.mItemType;
        }
        if ((i3 & 2) != 0) {
            str = translateOneModel.content;
        }
        String str4 = str;
        if ((i3 & 4) != 0) {
            z = translateOneModel.isPlaying;
        }
        boolean z3 = z;
        if ((i3 & 8) != 0) {
            z2 = translateOneModel.isBottom;
        }
        boolean z4 = z2;
        if ((i3 & 16) != 0) {
            str2 = translateOneModel.languageCode;
        }
        String str5 = str2;
        if ((i3 & 32) != 0) {
            str3 = translateOneModel.sid;
        }
        String str6 = str3;
        if ((i3 & 64) != 0) {
            i2 = translateOneModel.status;
        }
        return translateOneModel.copy(i, str4, z3, z4, str5, str6, i2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getMItemType() {
        return this.mItemType;
    }

    /* renamed from: component2, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsPlaying() {
        return this.isPlaying;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getIsBottom() {
        return this.isBottom;
    }

    /* renamed from: component5, reason: from getter */
    public final String getLanguageCode() {
        return this.languageCode;
    }

    /* renamed from: component6, reason: from getter */
    public final String getSid() {
        return this.sid;
    }

    /* renamed from: component7, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    public final TranslateOneModel copy(int mItemType, String content, boolean isPlaying, boolean isBottom, String languageCode, String sid, int status) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(languageCode, "languageCode");
        Intrinsics.checkNotNullParameter(sid, "sid");
        return new TranslateOneModel(mItemType, content, isPlaying, isBottom, languageCode, sid, status);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TranslateOneModel)) {
            return false;
        }
        TranslateOneModel translateOneModel = (TranslateOneModel) other;
        return this.mItemType == translateOneModel.mItemType && Intrinsics.areEqual(this.content, translateOneModel.content) && this.isPlaying == translateOneModel.isPlaying && this.isBottom == translateOneModel.isBottom && Intrinsics.areEqual(this.languageCode, translateOneModel.languageCode) && Intrinsics.areEqual(this.sid, translateOneModel.sid) && this.status == translateOneModel.status;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((Integer.hashCode(this.mItemType) * 31) + this.content.hashCode()) * 31;
        boolean z = this.isPlaying;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (iHashCode + i) * 31;
        boolean z2 = this.isBottom;
        return ((((((i2 + (z2 ? 1 : z2 ? 1 : 0)) * 31) + this.languageCode.hashCode()) * 31) + this.sid.hashCode()) * 31) + Integer.hashCode(this.status);
    }

    public String toString() {
        return "TranslateOneModel(mItemType=" + this.mItemType + ", content=" + this.content + ", isPlaying=" + this.isPlaying + ", isBottom=" + this.isBottom + ", languageCode=" + this.languageCode + ", sid=" + this.sid + ", status=" + this.status + ')';
    }

    public TranslateOneModel(int i, String content, boolean z, boolean z2, String languageCode, String sid, int i2) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(languageCode, "languageCode");
        Intrinsics.checkNotNullParameter(sid, "sid");
        this.mItemType = i;
        this.content = content;
        this.isPlaying = z;
        this.isBottom = z2;
        this.languageCode = languageCode;
        this.sid = sid;
        this.status = i2;
    }

    public final int getMItemType() {
        return this.mItemType;
    }

    public final String getContent() {
        return this.content;
    }

    public final boolean isPlaying() {
        return this.isPlaying;
    }

    public final void setPlaying(boolean z) {
        this.isPlaying = z;
    }

    public final boolean isBottom() {
        return this.isBottom;
    }

    public final void setBottom(boolean z) {
        this.isBottom = z;
    }

    public /* synthetic */ TranslateOneModel(int i, String str, boolean z, boolean z2, String str2, String str3, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, (i3 & 4) != 0 ? false : z, (i3 & 8) != 0 ? false : z2, (i3 & 16) != 0 ? "en" : str2, str3, (i3 & 64) != 0 ? 0 : i2);
    }

    public final String getLanguageCode() {
        return this.languageCode;
    }

    public final String getSid() {
        return this.sid;
    }

    public final int getStatus() {
        return this.status;
    }
}
