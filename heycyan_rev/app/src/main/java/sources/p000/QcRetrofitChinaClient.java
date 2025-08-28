package p000;

import androidx.core.app.NotificationCompat;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.api.BaseRetrofitClient;
import com.glasssutdio.wear.api.QcService;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/* compiled from: QcRetrofitChinaClient.kt */
@Metadata(m606d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, m607d2 = {"LQcRetrofitChinaClient;", "Lcom/glasssutdio/wear/api/BaseRetrofitClient;", "()V", NotificationCompat.CATEGORY_SERVICE, "Lcom/glasssutdio/wear/api/QcService;", "getService", "()Lcom/glasssutdio/wear/api/QcService;", "service$delegate", "Lkotlin/Lazy;", "handleBuilder", "", "builder", "Lokhttp3/OkHttpClient$Builder;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class QcRetrofitChinaClient extends BaseRetrofitClient {
    public static final QcRetrofitChinaClient INSTANCE = new QcRetrofitChinaClient();

    /* renamed from: service$delegate, reason: from kotlin metadata */
    private static final Lazy service = LazyKt.lazy(new Function0<QcService>() { // from class: QcRetrofitChinaClient$service$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final QcService invoke() {
            return (QcService) QcRetrofitChinaClient.INSTANCE.getService(QcService.class, "https://www.qlifesnap.com/glasses/");
        }
    });

    private QcRetrofitChinaClient() {
    }

    public final QcService getService() {
        return (QcService) service.getValue();
    }

    @Override // com.glasssutdio.wear.api.BaseRetrofitClient
    protected void handleBuilder(OkHttpClient.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        try {
            builder.addInterceptor(new Interceptor() { // from class: QcRetrofitChinaClient$handleBuilder$$inlined$-addInterceptor$1
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
