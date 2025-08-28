package okhttp3.sse;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.sse.RealEventSource;
import okhttp3.sse.EventSource;

/* compiled from: EventSources.kt */
@Metadata(m605bv = {1, 0, 3}, m606d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007¨\u0006\r"}, m607d2 = {"Lokhttp3/sse/EventSources;", "", "()V", "createFactory", "Lokhttp3/sse/EventSource$Factory;", "client", "Lokhttp3/OkHttpClient;", "processResponse", "", "response", "Lokhttp3/Response;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lokhttp3/sse/EventSourceListener;", "okhttp-sse"}, m608k = 1, m609mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class EventSources {
    public static final EventSources INSTANCE = new EventSources();

    private EventSources() {
    }

    @JvmStatic
    public static final EventSource.Factory createFactory(final OkHttpClient client) {
        Intrinsics.checkNotNullParameter(client, "client");
        return new EventSource.Factory() { // from class: okhttp3.sse.EventSources.createFactory.1
            @Override // okhttp3.sse.EventSource.Factory
            public final EventSource newEventSource(Request request, EventSourceListener listener) {
                Intrinsics.checkNotNullParameter(request, "request");
                Intrinsics.checkNotNullParameter(listener, "listener");
                if (request.header("Accept") == null) {
                    request = request.newBuilder().addHeader("Accept", "text/event-stream").build();
                }
                RealEventSource realEventSource = new RealEventSource(request, listener);
                realEventSource.connect(client);
                return realEventSource;
            }
        };
    }

    @JvmStatic
    public static final void processResponse(Response response, EventSourceListener listener) {
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(listener, "listener");
        new RealEventSource(response.request(), listener).processResponse(response);
    }
}
