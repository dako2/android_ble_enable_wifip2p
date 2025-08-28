package com.glasssutdio.wear.api;

import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.GsonInstance;
import com.glasssutdio.wear.api.request.AiChatBean;
import com.glasssutdio.wear.api.request.VisionChatRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.digest.DigestUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import io.reactivex.annotations.SchedulerSupport;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.EventSources;
import okio.Buffer;
import retrofit2.http.Body;

/* compiled from: SSEHandler.kt */
@Metadata(m606d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0007\u001a\u00020\u00002\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bJ\u001a\u0010\u000b\u001a\u00020\u00002\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bJ\u0014\u0010\f\u001a\u00020\u00002\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\rJ\u001a\u0010\u000e\u001a\u00020\u00002\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\n0\bJ,\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\t2\b\u0010\u0015\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0016\u001a\u00020\tH\u0016J$\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u001a\u0010\u0010\u001a\u00020\u00002\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bJ\u001a\u0010\u0011\u001a\u00020\u00002\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bJ\u001c\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\t0\u001c2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002J\u0018\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\t2\b\b\u0001\u0010!\u001a\u00020\"J,\u0010#\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\t2\u0006\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020\t2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'J\u0006\u0010)\u001a\u00020\nJ\u000e\u0010*\u001a\u00020\t*\u0004\u0018\u00010+H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\n\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, m607d2 = {"Lcom/glasssutdio/wear/api/SSEHandler;", "Lokhttp3/sse/EventSourceListener;", "()V", "eventSource", "Lokhttp3/sse/EventSource;", "okHttpClient", "Lokhttp3/OkHttpClient;", "onContent", "Lkotlin/Function1;", "", "", "onCustom", "onEnd", "Lkotlin/Function0;", "onError", "", "onJsonContent", "onThinking", "callback", "onEvent", BreakpointSQLiteKey.f521ID, "type", "data", "onFailure", "t", "response", "Lokhttp3/Response;", "processSignedRequest", "Lkotlin/Pair;", "Lokhttp3/Request;", "original", "startImageStream", "uid", "request", "Lcom/glasssutdio/wear/api/request/VisionChatRequest;", "startStream", "country", "appName", "messages", "", "Lcom/glasssutdio/wear/api/request/AiChatBean;", "stop", "hash", "Lokhttp3/RequestBody;", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class SSEHandler extends EventSourceListener {
    private static final String AI_CHAT_URL = "https://www.qlifesnap.com/glasses/ai/chat/stream";
    private static final String AI_IMAGE_URL = "https://www.qlifesnap.com/glasses/ai/visual/sse";
    public static final String BASE_URL = "https://www.qlifesnap.com/glasses/";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static volatile SSEHandler instance;
    private EventSource eventSource;
    private final OkHttpClient okHttpClient;
    private Function1<? super String, Unit> onContent;
    private Function1<? super String, Unit> onCustom;
    private Function0<Unit> onEnd;
    private Function1<? super Throwable, Unit> onError;
    private Function1<? super String, Unit> onJsonContent;
    private Function1<? super String, Unit> onThinking;

    public /* synthetic */ SSEHandler(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private SSEHandler() {
        this.okHttpClient = new OkHttpClient.Builder().readTimeout(30L, TimeUnit.SECONDS).retryOnConnectionFailure(true).build();
    }

    /* compiled from: SSEHandler.kt */
    @Metadata(m606d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, m607d2 = {"Lcom/glasssutdio/wear/api/SSEHandler$Companion;", "", "()V", "AI_CHAT_URL", "", "AI_IMAGE_URL", "BASE_URL", "instance", "Lcom/glasssutdio/wear/api/SSEHandler;", "getInstance", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SSEHandler getInstance() {
            SSEHandler sSEHandler = SSEHandler.instance;
            if (sSEHandler == null) {
                synchronized (this) {
                    sSEHandler = SSEHandler.instance;
                    if (sSEHandler == null) {
                        sSEHandler = new SSEHandler(null);
                        Companion companion = SSEHandler.INSTANCE;
                        SSEHandler.instance = sSEHandler;
                    }
                }
            }
            return sSEHandler;
        }
    }

    public final SSEHandler startImageStream(String uid, @Body VisionChatRequest request) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(request, "request");
        String aiLanguageCode = UserConfig.INSTANCE.getInstance().getAiLanguageCode();
        XLog.m137i("ai auto:" + aiLanguageCode);
        String str = "https://www.qlifesnap.com/glasses/ai/visual/sse/" + uid + IOUtils.DIR_SEPARATOR_UNIX + aiLanguageCode;
        RequestBody.Companion companion = RequestBody.INSTANCE;
        String json = GsonInstance.INSTANCE.getGson().toJson(request);
        Intrinsics.checkNotNullExpressionValue(json, "toJson(...)");
        this.eventSource = EventSources.createFactory(this.okHttpClient).newEventSource(processSignedRequest(new Request.Builder().url(str).post(companion.create(json, MediaType.INSTANCE.get("application/json"))).addHeader("token", UserConfig.INSTANCE.getInstance().getUserToken()).addHeader("Accept", "text/event-stream").build()).component1(), this);
        return this;
    }

    public final SSEHandler startStream(String uid, String country, String appName, List<AiChatBean> messages) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(country, "country");
        Intrinsics.checkNotNullParameter(appName, "appName");
        Intrinsics.checkNotNullParameter(messages, "messages");
        String aiLanguageCode = UserConfig.INSTANCE.getInstance().getAiLanguageCode();
        XLog.m137i("ai auto:" + aiLanguageCode);
        String str = "https://www.qlifesnap.com/glasses/ai/chat/stream/" + uid + IOUtils.DIR_SEPARATOR_UNIX + country + IOUtils.DIR_SEPARATOR_UNIX + appName + IOUtils.DIR_SEPARATOR_UNIX + aiLanguageCode;
        RequestBody.Companion companion = RequestBody.INSTANCE;
        String json = GsonInstance.INSTANCE.getGson().toJson(messages);
        Intrinsics.checkNotNullExpressionValue(json, "toJson(...)");
        this.eventSource = EventSources.createFactory(this.okHttpClient).newEventSource(processSignedRequest(new Request.Builder().url(str).post(companion.create(json, MediaType.INSTANCE.get("application/json"))).addHeader("token", UserConfig.INSTANCE.getInstance().getUserToken()).addHeader("Accept", "text/event-stream").build()).component1(), this);
        return this;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // okhttp3.sse.EventSourceListener
    public void onEvent(EventSource eventSource, String id, String type, String data) {
        Function1<? super String, Unit> function1;
        Function0<Unit> function0;
        Function1<? super String, Unit> function12;
        Function1<? super String, Unit> function13;
        Function1<? super String, Unit> function14;
        Intrinsics.checkNotNullParameter(eventSource, "eventSource");
        Intrinsics.checkNotNullParameter(data, "data");
        if (type != null) {
            switch (type.hashCode()) {
                case -1349088399:
                    if (type.equals(SchedulerSupport.CUSTOM) && (function1 = this.onCustom) != null) {
                        function1.invoke(data);
                        break;
                    }
                    break;
                case 100571:
                    if (type.equals("end") && (function0 = this.onEnd) != null) {
                        function0.invoke();
                        break;
                    }
                    break;
                case 951530617:
                    if (type.equals("content") && (function12 = this.onContent) != null) {
                        function12.invoke(data);
                        break;
                    }
                    break;
                case 1224578480:
                    if (type.equals("thinking") && (function13 = this.onThinking) != null) {
                        function13.invoke(data);
                        break;
                    }
                    break;
                case 2026987554:
                    if (type.equals("json_content") && (function14 = this.onJsonContent) != null) {
                        function14.invoke(data);
                        break;
                    }
                    break;
            }
        }
    }

    @Override // okhttp3.sse.EventSourceListener
    public void onFailure(EventSource eventSource, Throwable t, Response response) {
        Intrinsics.checkNotNullParameter(eventSource, "eventSource");
        Function1<? super Throwable, Unit> function1 = this.onError;
        if (function1 != null) {
            if (t == null) {
                t = new IOException("Unknown error");
            }
            function1.invoke(t);
        }
    }

    public final void stop() {
        EventSource eventSource = this.eventSource;
        if (eventSource != null) {
            eventSource.cancel();
        }
        this.eventSource = null;
    }

    public final SSEHandler onThinking(Function1<? super String, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.onThinking = callback;
        return this;
    }

    public final SSEHandler onContent(Function1<? super String, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.onContent = callback;
        return this;
    }

    public final SSEHandler onJsonContent(Function1<? super String, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.onJsonContent = callback;
        return this;
    }

    public final SSEHandler onEnd(Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.onEnd = callback;
        return this;
    }

    public final SSEHandler onCustom(Function1<? super String, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.onCustom = callback;
        return this;
    }

    public final SSEHandler onError(Function1<? super Throwable, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.onError = callback;
        return this;
    }

    private final Pair<Request, String> processSignedRequest(Request original) {
        String strMd5Hex;
        RequestBody requestBodyBody = original.body();
        if (requestBodyBody == null || (strMd5Hex = hash(requestBodyBody)) == null) {
            strMd5Hex = DigestUtils.md5Hex("");
        }
        SimpleSigner simpleSigner = SimpleSigner.INSTANCE;
        Intrinsics.checkNotNull(strMd5Hex);
        Pair<Long, String> pairGenerateSign = simpleSigner.generateSign("Glasses_51888", strMd5Hex);
        long jLongValue = pairGenerateSign.component1().longValue();
        String strComponent2 = pairGenerateSign.component2();
        return new Pair<>(original.newBuilder().addHeader("X-Timestamp", String.valueOf(jLongValue)).addHeader("X-Signature", strComponent2).build(), strComponent2);
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
