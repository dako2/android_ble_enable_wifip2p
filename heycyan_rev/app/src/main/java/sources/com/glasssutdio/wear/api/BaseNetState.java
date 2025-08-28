package com.glasssutdio.wear.api;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BaseNetState.kt */
@Metadata(m606d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B/\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00018\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u000bR\u0015\u0010\u0005\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0005\u0010\fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, m607d2 = {"Lcom/glasssutdio/wear/api/BaseNetState;", ExifInterface.GPS_DIRECTION_TRUE, "", "isLoading", "", "isSuccess", "retCode", "", NotificationCompat.CATEGORY_MESSAGE, "", "(ZLjava/lang/Object;ILjava/lang/String;)V", "()Z", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getMsg", "()Ljava/lang/String;", "getRetCode", "()I", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public class BaseNetState<T> {
    private final boolean isLoading;
    private final T isSuccess;
    private final String msg;
    private final int retCode;

    public BaseNetState(boolean z, T t, int i, String str) {
        this.isLoading = z;
        this.isSuccess = t;
        this.retCode = i;
        this.msg = str;
    }

    public /* synthetic */ BaseNetState(boolean z, Object obj, int i, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? null : obj, i, (i2 & 8) != 0 ? null : str);
    }

    /* renamed from: isLoading, reason: from getter */
    public final boolean getIsLoading() {
        return this.isLoading;
    }

    public final T isSuccess() {
        return this.isSuccess;
    }

    public final int getRetCode() {
        return this.retCode;
    }

    public final String getMsg() {
        return this.msg;
    }
}
