package com.glasssutdio.wear.api;

import androidx.exifinterface.media.ExifInterface;
import com.glasssutdio.wear.all.utils.GFileUtilKt;
import com.glasssutdio.wear.api.interceptor.NetworkInterceptor;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/* compiled from: BaseRetrofitClient.kt */
@Metadata(m606d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J'\u0010\u0007\u001a\u0002H\b\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\n2\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H$R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, m607d2 = {"Lcom/glasssutdio/wear/api/BaseRetrofitClient;", "", "()V", "client", "Lokhttp3/OkHttpClient;", "getClient", "()Lokhttp3/OkHttpClient;", "getService", ExifInterface.LATITUDE_SOUTH, "serviceClass", "Ljava/lang/Class;", "baseUrl", "", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;", "handleBuilder", "", "builder", "Lokhttp3/OkHttpClient$Builder;", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public abstract class BaseRetrofitClient {
    private static final int TIME_OUT = 120;

    protected abstract void handleBuilder(OkHttpClient.Builder builder);

    /* JADX WARN: Multi-variable type inference failed */
    private final OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(null, 1, 0 == true ? 1 : 0);
        Cache cache = new Cache(GFileUtilKt.getCacheFolder(), 104857600L);
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        builder.cache(cache).addInterceptor(httpLoggingInterceptor).addInterceptor(new SignatureInterceptor()).addInterceptor(new NetworkInterceptor()).connectTimeout(120L, TimeUnit.SECONDS).writeTimeout(120L, TimeUnit.SECONDS).readTimeout(120L, TimeUnit.SECONDS).dns(new XDns(120L));
        handleBuilder(builder);
        return builder.build();
    }

    public final <S> S getService(Class<S> serviceClass, String baseUrl) {
        Intrinsics.checkNotNullParameter(serviceClass, "serviceClass");
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        return (S) new Retrofit.Builder().client(getClient()).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create())).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl(baseUrl).build().create(serviceClass);
    }
}
