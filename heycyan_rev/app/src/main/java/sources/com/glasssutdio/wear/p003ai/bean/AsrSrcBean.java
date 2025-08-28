package com.glasssutdio.wear.p003ai.bean;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AsrSrcBean.kt */
@Metadata(m606d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, m607d2 = {"Lcom/glasssutdio/wear/ai/bean/AsrSrcBean;", "", "content", "", "sid", NotificationCompat.CATEGORY_STATUS, "", "isNotRealTime", "", "(Ljava/lang/String;Ljava/lang/String;IZ)V", "getContent", "()Ljava/lang/String;", "()Z", "setNotRealTime", "(Z)V", "getSid", "getStatus", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class AsrSrcBean {
    private final String content;
    private boolean isNotRealTime;
    private final String sid;
    private final int status;

    public static /* synthetic */ AsrSrcBean copy$default(AsrSrcBean asrSrcBean, String str, String str2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = asrSrcBean.content;
        }
        if ((i2 & 2) != 0) {
            str2 = asrSrcBean.sid;
        }
        if ((i2 & 4) != 0) {
            i = asrSrcBean.status;
        }
        if ((i2 & 8) != 0) {
            z = asrSrcBean.isNotRealTime;
        }
        return asrSrcBean.copy(str, str2, i, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSid() {
        return this.sid;
    }

    /* renamed from: component3, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getIsNotRealTime() {
        return this.isNotRealTime;
    }

    public final AsrSrcBean copy(String content, String sid, int status, boolean isNotRealTime) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(sid, "sid");
        return new AsrSrcBean(content, sid, status, isNotRealTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AsrSrcBean)) {
            return false;
        }
        AsrSrcBean asrSrcBean = (AsrSrcBean) other;
        return Intrinsics.areEqual(this.content, asrSrcBean.content) && Intrinsics.areEqual(this.sid, asrSrcBean.sid) && this.status == asrSrcBean.status && this.isNotRealTime == asrSrcBean.isNotRealTime;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((this.content.hashCode() * 31) + this.sid.hashCode()) * 31) + Integer.hashCode(this.status)) * 31;
        boolean z = this.isNotRealTime;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return iHashCode + i;
    }

    public String toString() {
        return "AsrSrcBean(content=" + this.content + ", sid=" + this.sid + ", status=" + this.status + ", isNotRealTime=" + this.isNotRealTime + ')';
    }

    public AsrSrcBean(String content, String sid, int i, boolean z) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(sid, "sid");
        this.content = content;
        this.sid = sid;
        this.status = i;
        this.isNotRealTime = z;
    }

    public /* synthetic */ AsrSrcBean(String str, String str2, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, (i2 & 8) != 0 ? false : z);
    }

    public final String getContent() {
        return this.content;
    }

    public final String getSid() {
        return this.sid;
    }

    public final int getStatus() {
        return this.status;
    }

    public final boolean isNotRealTime() {
        return this.isNotRealTime;
    }

    public final void setNotRealTime(boolean z) {
        this.isNotRealTime = z;
    }
}
