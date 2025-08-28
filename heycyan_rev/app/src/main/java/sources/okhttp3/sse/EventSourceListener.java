package okhttp3.sse;

import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Response;

/* compiled from: EventSourceListener.kt */
@Metadata(m605bv = {1, 0, 3}, m606d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J,\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J$\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006\u0012"}, m607d2 = {"Lokhttp3/sse/EventSourceListener;", "", "()V", "onClosed", "", "eventSource", "Lokhttp3/sse/EventSource;", "onEvent", BreakpointSQLiteKey.f521ID, "", "type", "data", "onFailure", "t", "", "response", "Lokhttp3/Response;", "onOpen", "okhttp-sse"}, m608k = 1, m609mv = {1, 4, 0})
/* loaded from: classes3.dex */
public abstract class EventSourceListener {
    public void onClosed(EventSource eventSource) {
        Intrinsics.checkNotNullParameter(eventSource, "eventSource");
    }

    public void onEvent(EventSource eventSource, String id, String type, String data) {
        Intrinsics.checkNotNullParameter(eventSource, "eventSource");
        Intrinsics.checkNotNullParameter(data, "data");
    }

    public void onFailure(EventSource eventSource, Throwable t, Response response) {
        Intrinsics.checkNotNullParameter(eventSource, "eventSource");
    }

    public void onOpen(EventSource eventSource, Response response) {
        Intrinsics.checkNotNullParameter(eventSource, "eventSource");
        Intrinsics.checkNotNullParameter(response, "response");
    }
}
