package com.glasssutdio.wear.api.interceptor;

import com.elvishew.xlog.XLog;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Response;

/* compiled from: CacheHeaderInterceptor.kt */
@Metadata(m606d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, m607d2 = {"Lcom/glasssutdio/wear/api/interceptor/CacheHeaderInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class CacheHeaderInterceptor implements Interceptor {
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Response responseProceed = chain.proceed(chain.request());
        String strHeader$default = Response.header$default(responseProceed, "Cache-Control", null, 2, null);
        String strHeader$default2 = Response.header$default(responseProceed, "Expires", null, 2, null);
        String strHeader$default3 = Response.header$default(responseProceed, "ETag", null, 2, null);
        String strHeader$default4 = Response.header$default(responseProceed, "Last-Modified", null, 2, null);
        if (strHeader$default != null || strHeader$default2 != null || strHeader$default3 != null || strHeader$default4 != null) {
            XLog.m137i("服务器支持缓存");
            XLog.m137i("Cache-Control: " + strHeader$default);
            XLog.m137i("Expires: " + strHeader$default2);
            XLog.m137i("ETag: " + strHeader$default3);
            XLog.m137i("Last-Modified: " + strHeader$default4);
        } else {
            XLog.m137i("服务器不支持缓存");
        }
        return responseProceed;
    }
}
