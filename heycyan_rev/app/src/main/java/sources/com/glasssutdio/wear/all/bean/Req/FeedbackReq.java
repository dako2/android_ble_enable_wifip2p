package com.glasssutdio.wear.all.bean.Req;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FeedbackReq.kt */
@Metadata(m606d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u001c"}, m607d2 = {"Lcom/glasssutdio/wear/all/bean/Req/FeedbackReq;", "", "feedbackId", "", "typeId", "email", "", "content", "fVersion", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "getEmail", "getFVersion", "getFeedbackId", "()I", "getTypeId", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class FeedbackReq {
    private final String content;
    private final String email;
    private final String fVersion;
    private final int feedbackId;
    private final int typeId;

    public static /* synthetic */ FeedbackReq copy$default(FeedbackReq feedbackReq, int i, int i2, String str, String str2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = feedbackReq.feedbackId;
        }
        if ((i3 & 2) != 0) {
            i2 = feedbackReq.typeId;
        }
        int i4 = i2;
        if ((i3 & 4) != 0) {
            str = feedbackReq.email;
        }
        String str4 = str;
        if ((i3 & 8) != 0) {
            str2 = feedbackReq.content;
        }
        String str5 = str2;
        if ((i3 & 16) != 0) {
            str3 = feedbackReq.fVersion;
        }
        return feedbackReq.copy(i, i4, str4, str5, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final int getFeedbackId() {
        return this.feedbackId;
    }

    /* renamed from: component2, reason: from getter */
    public final int getTypeId() {
        return this.typeId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    /* renamed from: component4, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component5, reason: from getter */
    public final String getFVersion() {
        return this.fVersion;
    }

    public final FeedbackReq copy(int feedbackId, int typeId, String email, String content, String fVersion) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(fVersion, "fVersion");
        return new FeedbackReq(feedbackId, typeId, email, content, fVersion);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FeedbackReq)) {
            return false;
        }
        FeedbackReq feedbackReq = (FeedbackReq) other;
        return this.feedbackId == feedbackReq.feedbackId && this.typeId == feedbackReq.typeId && Intrinsics.areEqual(this.email, feedbackReq.email) && Intrinsics.areEqual(this.content, feedbackReq.content) && Intrinsics.areEqual(this.fVersion, feedbackReq.fVersion);
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.feedbackId) * 31) + Integer.hashCode(this.typeId)) * 31) + this.email.hashCode()) * 31) + this.content.hashCode()) * 31) + this.fVersion.hashCode();
    }

    public String toString() {
        return "FeedbackReq(feedbackId=" + this.feedbackId + ", typeId=" + this.typeId + ", email=" + this.email + ", content=" + this.content + ", fVersion=" + this.fVersion + ')';
    }

    public FeedbackReq(int i, int i2, String email, String content, String fVersion) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(fVersion, "fVersion");
        this.feedbackId = i;
        this.typeId = i2;
        this.email = email;
        this.content = content;
        this.fVersion = fVersion;
    }

    public /* synthetic */ FeedbackReq(int i, int i2, String str, String str2, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i3 & 4) != 0 ? "" : str, str2, (i3 & 16) != 0 ? "" : str3);
    }

    public final int getFeedbackId() {
        return this.feedbackId;
    }

    public final int getTypeId() {
        return this.typeId;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getContent() {
        return this.content;
    }

    public final String getFVersion() {
        return this.fVersion;
    }
}
