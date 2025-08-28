package com.glasssutdio.wear.api;

import androidx.core.app.NotificationCompat;
import com.glasssutdio.wear.all.pref.UserConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/* compiled from: QcRetrofitClient.kt */
@Metadata(m606d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0006\u0010\u000b\u001a\u00020\b¨\u0006\f"}, m607d2 = {"Lcom/glasssutdio/wear/api/QcRetrofitClient;", "Lcom/glasssutdio/wear/api/BaseRetrofitClient;", "()V", "handleBuilder", "", "builder", "Lokhttp3/OkHttpClient$Builder;", "serverSwitching", "Lcom/glasssutdio/wear/api/QcService;", "hw", "", NotificationCompat.CATEGORY_SERVICE, "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class QcRetrofitClient extends BaseRetrofitClient {
    public static final QcRetrofitClient INSTANCE = new QcRetrofitClient();

    private QcRetrofitClient() {
    }

    public final QcService service() {
        return serverSwitching(true);
    }

    private final QcService serverSwitching(boolean hw) {
        return hw ? (QcService) getService(QcService.class, "https://www.qlifesnap.com/glasses/") : (QcService) getService(QcService.class, "https://www.qlifesnap.com/glasses/");
    }

    @Override // com.glasssutdio.wear.api.BaseRetrofitClient
    protected void handleBuilder(OkHttpClient.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        try {
            builder.addInterceptor(new Interceptor() { // from class: com.glasssutdio.wear.api.QcRetrofitClient$handleBuilder$$inlined$-addInterceptor$1
                @Override // okhttp3.Interceptor
                public final Response intercept(Interceptor.Chain chain) {
                    Intrinsics.checkNotNullParameter(chain, "chain");
                    return chain.proceed(chain.request().newBuilder().addHeader("token", UserConfig.INSTANCE.getInstance().getUserToken()).build());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
