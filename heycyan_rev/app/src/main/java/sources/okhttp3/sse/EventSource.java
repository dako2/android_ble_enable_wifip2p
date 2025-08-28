package okhttp3.sse;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import okhttp3.Request;

/* compiled from: EventSource.kt */
@Metadata(m605bv = {1, 0, 3}, m606d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0006J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, m607d2 = {"Lokhttp3/sse/EventSource;", "", "cancel", "", "request", "Lokhttp3/Request;", "Factory", "okhttp-sse"}, m608k = 1, m609mv = {1, 4, 0})
/* loaded from: classes3.dex */
public interface EventSource {

    /* compiled from: EventSource.kt */
    @Metadata(m605bv = {1, 0, 3}, m606d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, m607d2 = {"Lokhttp3/sse/EventSource$Factory;", "", "newEventSource", "Lokhttp3/sse/EventSource;", "request", "Lokhttp3/Request;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lokhttp3/sse/EventSourceListener;", "okhttp-sse"}, m608k = 1, m609mv = {1, 4, 0})
    public interface Factory {
        EventSource newEventSource(Request request, EventSourceListener listener);
    }

    void cancel();

    Request request();
}
