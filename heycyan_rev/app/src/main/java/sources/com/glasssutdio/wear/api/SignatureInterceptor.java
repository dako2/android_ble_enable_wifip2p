package com.glasssutdio.wear.api;

import com.elvishew.xlog.XLog;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.digest.DigestUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.SignatureException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Invocation;

/* compiled from: SignatureInterceptor.kt */
@Metadata(m606d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J \u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\b0\u00102\u0006\u0010\u0011\u001a\u00020\rH\u0002J\u000e\u0010\u0012\u001a\u00020\b*\u0004\u0018\u00010\u0013H\u0002¨\u0006\u0014"}, m607d2 = {"Lcom/glasssutdio/wear/api/SignatureInterceptor;", "Lokhttp3/Interceptor;", "()V", "checkSignatureError", "", "response", "Lokhttp3/Response;", "signature", "", "executeSignedRequest", "chain", "Lokhttp3/Interceptor$Chain;", "request", "Lokhttp3/Request;", "intercept", "processSignedRequest", "Lkotlin/Pair;", "original", "hash", "Lokhttp3/RequestBody;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class SignatureInterceptor implements Interceptor {
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Method method;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        Invocation invocation = (Invocation) request.tag(Invocation.class);
        if (invocation != null && (method = invocation.method()) != null && method.isAnnotationPresent(RequiresSignature.class)) {
            Pair<Request, String> pairProcessSignedRequest = processSignedRequest(request);
            return executeSignedRequest(chain, pairProcessSignedRequest.component1(), pairProcessSignedRequest.component2());
        }
        return chain.proceed(request);
    }

    private final Response executeSignedRequest(Interceptor.Chain chain, Request request, String signature) throws SignatureException {
        try {
            Response responseProceed = chain.proceed(request);
            if (responseProceed.isSuccessful()) {
                return responseProceed;
            }
            checkSignatureError(responseProceed, signature);
            return responseProceed;
        } catch (IOException unused) {
            return new Response.Builder().request(request).protocol(Protocol.HTTP_1_1).code(503).message("Network Error").body(ResponseBody.INSTANCE.create("{\"error\":\"Signature request failed\"}", MediaType.INSTANCE.get("application/json"))).build();
        }
    }

    private final Pair<Request, String> processSignedRequest(final Request original) {
        String strMd5Hex;
        if (Intrinsics.areEqual(original.method(), HttpGet.METHOD_NAME)) {
            String strJoinToString$default = CollectionsKt.joinToString$default(CollectionsKt.sorted(original.url().queryParameterNames()), "&", null, null, 0, null, new Function1<String, CharSequence>() { // from class: com.glasssutdio.wear.api.SignatureInterceptor$processSignedRequest$sortedParams$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final CharSequence invoke(String key) {
                    Intrinsics.checkNotNullParameter(key, "key");
                    String strQueryParameter = original.url().queryParameter(key);
                    if (strQueryParameter == null) {
                        strQueryParameter = "";
                    }
                    return key + '=' + strQueryParameter;
                }
            }, 30, null);
            XLog.m137i(strJoinToString$default);
            strMd5Hex = DigestUtils.md5Hex(strJoinToString$default);
            Intrinsics.checkNotNull(strMd5Hex);
        } else {
            RequestBody requestBodyBody = original.body();
            if (requestBodyBody == null || (strMd5Hex = hash(requestBodyBody)) == null) {
                strMd5Hex = DigestUtils.md5Hex("");
            }
            Intrinsics.checkNotNull(strMd5Hex);
        }
        Pair<Long, String> pairGenerateSign = SimpleSigner.INSTANCE.generateSign("Glasses_51888", strMd5Hex);
        long jLongValue = pairGenerateSign.component1().longValue();
        String strComponent2 = pairGenerateSign.component2();
        XLog.m137i(strComponent2);
        return new Pair<>(original.newBuilder().addHeader("X-Timestamp", String.valueOf(jLongValue)).addHeader("X-Signature", strComponent2).build(), strComponent2);
    }

    private final void checkSignatureError(Response response, String signature) throws SignatureException {
        int iCode = response.code();
        if (iCode == 401) {
            throw new SignatureException("签名无效: " + signature);
        }
        if (iCode == 403) {
            throw new SignatureException("签名过期: " + signature);
        }
        if (iCode == 408) {
            throw new SignatureException("请求超时: " + signature);
        }
    }

    private final String hash(RequestBody requestBody) throws IOException {
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            String strMd5Hex = DigestUtils.md5Hex(buffer.readByteArray());
            Intrinsics.checkNotNullExpressionValue(strMd5Hex, "md5Hex(...)");
            return strMd5Hex;
        }
        return "";
    }
}
