package com.glasssutdio.wear.api;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: NetState.kt */
@Metadata(m606d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B1\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00018\u0000\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\n¨\u0006\u000b"}, m607d2 = {"Lcom/glasssutdio/wear/api/NetState;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/glasssutdio/wear/api/BaseNetState;", "isLoading", "", "isSuccess", "retCode", "", NotificationCompat.CATEGORY_MESSAGE, "", "(ZLjava/lang/Object;ILjava/lang/String;)V", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class NetState<T> extends BaseNetState<T> {
    public NetState() {
        this(false, null, 0, null, 15, null);
    }

    public /* synthetic */ NetState(boolean z, Object obj, int i, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? null : obj, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? null : str);
    }

    public NetState(boolean z, T t, int i, String str) {
        super(z, t, i, str);
    }
}
