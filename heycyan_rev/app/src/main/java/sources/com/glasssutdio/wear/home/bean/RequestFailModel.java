package com.glasssutdio.wear.home.bean;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RequestFailModel.kt */
@Metadata(m606d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0016"}, m607d2 = {"Lcom/glasssutdio/wear/home/bean/RequestFailModel;", "", "code", "", NotificationCompat.CATEGORY_MESSAGE, "", "reqTYpe", "(ILjava/lang/String;I)V", "getCode", "()I", "getMsg", "()Ljava/lang/String;", "getReqTYpe", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final /* data */ class RequestFailModel {
    private final int code;
    private final String msg;
    private final int reqTYpe;

    public static /* synthetic */ RequestFailModel copy$default(RequestFailModel requestFailModel, int i, String str, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = requestFailModel.code;
        }
        if ((i3 & 2) != 0) {
            str = requestFailModel.msg;
        }
        if ((i3 & 4) != 0) {
            i2 = requestFailModel.reqTYpe;
        }
        return requestFailModel.copy(i, str, i2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getCode() {
        return this.code;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMsg() {
        return this.msg;
    }

    /* renamed from: component3, reason: from getter */
    public final int getReqTYpe() {
        return this.reqTYpe;
    }

    public final RequestFailModel copy(int code, String msg, int reqTYpe) {
        return new RequestFailModel(code, msg, reqTYpe);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RequestFailModel)) {
            return false;
        }
        RequestFailModel requestFailModel = (RequestFailModel) other;
        return this.code == requestFailModel.code && Intrinsics.areEqual(this.msg, requestFailModel.msg) && this.reqTYpe == requestFailModel.reqTYpe;
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(this.code) * 31;
        String str = this.msg;
        return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.reqTYpe);
    }

    public String toString() {
        return "RequestFailModel(code=" + this.code + ", msg=" + this.msg + ", reqTYpe=" + this.reqTYpe + ')';
    }

    public RequestFailModel(int i, String str, int i2) {
        this.code = i;
        this.msg = str;
        this.reqTYpe = i2;
    }

    public /* synthetic */ RequestFailModel(int i, String str, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i3 & 2) != 0 ? null : str, (i3 & 4) != 0 ? 0 : i2);
    }

    public final int getCode() {
        return this.code;
    }

    public final String getMsg() {
        return this.msg;
    }

    public final int getReqTYpe() {
        return this.reqTYpe;
    }
}
