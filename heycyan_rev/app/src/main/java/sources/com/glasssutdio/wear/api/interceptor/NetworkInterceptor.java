package com.glasssutdio.wear.api.interceptor;

import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.GlassApplication;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.all.utils.NetWorkUtils;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Response;

/* compiled from: NetworkInterceptor.kt */
@Metadata(m606d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, m607d2 = {"Lcom/glasssutdio/wear/api/interceptor/NetworkInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "NoNetworkException", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class NetworkInterceptor implements Interceptor {
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws NoNetworkException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        if (!NetWorkUtils.INSTANCE.isNetworkAvailable(GlassApplication.INSTANCE.getInstance())) {
            ThreadExtKt.ktxRunOnUi(this, new Function1<NetworkInterceptor, Unit>() { // from class: com.glasssutdio.wear.api.interceptor.NetworkInterceptor.intercept.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NetworkInterceptor networkInterceptor) {
                    invoke2(networkInterceptor);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NetworkInterceptor ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.request_glass_1), 0, 1, null);
                }
            });
            throw new NoNetworkException("无网络连接");
        }
        return chain.proceed(chain.request());
    }

    /* compiled from: NetworkInterceptor.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, m607d2 = {"Lcom/glasssutdio/wear/api/interceptor/NetworkInterceptor$NoNetworkException;", "Ljava/io/IOException;", "message", "", "(Ljava/lang/String;)V", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class NoNetworkException extends IOException {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NoNetworkException(String message) {
            super(message);
            Intrinsics.checkNotNullParameter(message, "message");
        }
    }
}
