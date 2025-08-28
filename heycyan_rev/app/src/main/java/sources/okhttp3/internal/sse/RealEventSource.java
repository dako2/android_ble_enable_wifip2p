package okhttp3.internal.sse;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p014io.CloseableKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.sse.ServerSentEventReader;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

/* compiled from: RealEventSource.kt */
@Metadata(m605bv = {1, 0, 3}, m606d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0016J\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fJ$\u0010\u0010\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0016J\u0018\u0010\u0015\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u000e\u0010\u001f\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010\u0004\u001a\u00020\u0005H\u0016J\f\u0010 \u001a\u00020!*\u00020\"H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, m607d2 = {"Lokhttp3/internal/sse/RealEventSource;", "Lokhttp3/sse/EventSource;", "Lokhttp3/internal/sse/ServerSentEventReader$Callback;", "Lokhttp3/Callback;", "request", "Lokhttp3/Request;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lokhttp3/sse/EventSourceListener;", "(Lokhttp3/Request;Lokhttp3/sse/EventSourceListener;)V", NotificationCompat.CATEGORY_CALL, "Lokhttp3/internal/connection/RealCall;", "cancel", "", "connect", "client", "Lokhttp3/OkHttpClient;", "onEvent", BreakpointSQLiteKey.f521ID, "", "type", "data", "onFailure", "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "onRetryChange", "timeMs", "", "processResponse", "isEventStream", "", "Lokhttp3/ResponseBody;", "okhttp-sse"}, m608k = 1, m609mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class RealEventSource implements EventSource, ServerSentEventReader.Callback, Callback {
    private RealCall call;
    private final EventSourceListener listener;
    private final Request request;

    @Override // okhttp3.internal.sse.ServerSentEventReader.Callback
    public void onRetryChange(long timeMs) {
    }

    public RealEventSource(Request request, EventSourceListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.request = request;
        this.listener = listener;
    }

    public final void connect(OkHttpClient client) {
        Intrinsics.checkNotNullParameter(client, "client");
        Call callNewCall = client.newBuilder().eventListener(EventListener.NONE).build().newCall(this.request);
        if (callNewCall == null) {
            throw new NullPointerException("null cannot be cast to non-null type okhttp3.internal.connection.RealCall");
        }
        RealCall realCall = (RealCall) callNewCall;
        this.call = realCall;
        if (realCall == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_CALL);
        }
        realCall.enqueue(this);
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        processResponse(response);
    }

    public final void processResponse(Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        Response response2 = response;
        try {
            if (!response.isSuccessful()) {
                this.listener.onFailure(this, null, response);
                CloseableKt.closeFinally(response2, null);
                return;
            }
            ResponseBody responseBodyBody = response.body();
            Intrinsics.checkNotNull(responseBodyBody);
            if (!isEventStream(responseBodyBody)) {
                this.listener.onFailure(this, new IllegalStateException("Invalid content-type: " + responseBodyBody.get$contentType()), response);
                CloseableKt.closeFinally(response2, null);
                return;
            }
            RealCall realCall = this.call;
            if (realCall == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_CALL);
            }
            realCall.timeoutEarlyExit();
            Response responseBuild = response.newBuilder().body(Util.EMPTY_RESPONSE).build();
            ServerSentEventReader serverSentEventReader = new ServerSentEventReader(responseBodyBody.getSource(), this);
            try {
                this.listener.onOpen(this, responseBuild);
                do {
                } while (serverSentEventReader.processNextEvent());
                this.listener.onClosed(this);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(response2, null);
            } catch (Exception e) {
                this.listener.onFailure(this, e, responseBuild);
                CloseableKt.closeFinally(response2, null);
            }
        } finally {
        }
    }

    private final boolean isEventStream(ResponseBody responseBody) {
        MediaType mediaType = responseBody.get$contentType();
        return mediaType != null && Intrinsics.areEqual(mediaType.type(), "text") && Intrinsics.areEqual(mediaType.subtype(), "event-stream");
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException e) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(e, "e");
        this.listener.onFailure(this, e, null);
    }

    @Override // okhttp3.sse.EventSource
    /* renamed from: request, reason: from getter */
    public Request getRequest() {
        return this.request;
    }

    @Override // okhttp3.sse.EventSource
    public void cancel() {
        RealCall realCall = this.call;
        if (realCall == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_CALL);
        }
        realCall.cancel();
    }

    @Override // okhttp3.internal.sse.ServerSentEventReader.Callback
    public void onEvent(String id, String type, String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.listener.onEvent(this, id, type, data);
    }
}
