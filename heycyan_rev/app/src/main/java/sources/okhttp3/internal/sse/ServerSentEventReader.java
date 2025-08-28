package okhttp3.internal.sse;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Options;

/* compiled from: ServerSentEventReader.kt */
@Metadata(m605bv = {1, 0, 3}, m606d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0002\u0011\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J$\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m607d2 = {"Lokhttp3/internal/sse/ServerSentEventReader;", "", "source", "Lokio/BufferedSource;", "callback", "Lokhttp3/internal/sse/ServerSentEventReader$Callback;", "(Lokio/BufferedSource;Lokhttp3/internal/sse/ServerSentEventReader$Callback;)V", "lastId", "", "completeEvent", "", BreakpointSQLiteKey.f521ID, "type", "data", "Lokio/Buffer;", "processNextEvent", "", "Callback", "Companion", "okhttp-sse"}, m608k = 1, m609mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class ServerSentEventReader {
    private final Callback callback;
    private String lastId;
    private final BufferedSource source;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Options options = Options.INSTANCE.m646of(ByteString.INSTANCE.encodeUtf8(IOUtils.LINE_SEPARATOR_WINDOWS), ByteString.INSTANCE.encodeUtf8("\r"), ByteString.INSTANCE.encodeUtf8(IOUtils.LINE_SEPARATOR_UNIX), ByteString.INSTANCE.encodeUtf8("data: "), ByteString.INSTANCE.encodeUtf8("data:"), ByteString.INSTANCE.encodeUtf8("data\r\n"), ByteString.INSTANCE.encodeUtf8("data\r"), ByteString.INSTANCE.encodeUtf8("data\n"), ByteString.INSTANCE.encodeUtf8("id: "), ByteString.INSTANCE.encodeUtf8("id:"), ByteString.INSTANCE.encodeUtf8("id\r\n"), ByteString.INSTANCE.encodeUtf8("id\r"), ByteString.INSTANCE.encodeUtf8("id\n"), ByteString.INSTANCE.encodeUtf8("event: "), ByteString.INSTANCE.encodeUtf8("event:"), ByteString.INSTANCE.encodeUtf8("event\r\n"), ByteString.INSTANCE.encodeUtf8("event\r"), ByteString.INSTANCE.encodeUtf8("event\n"), ByteString.INSTANCE.encodeUtf8("retry: "), ByteString.INSTANCE.encodeUtf8("retry:"));
    private static final ByteString CRLF = ByteString.INSTANCE.encodeUtf8(IOUtils.LINE_SEPARATOR_WINDOWS);

    /* compiled from: ServerSentEventReader.kt */
    @Metadata(m605bv = {1, 0, 3}, m606d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, m607d2 = {"Lokhttp3/internal/sse/ServerSentEventReader$Callback;", "", "onEvent", "", BreakpointSQLiteKey.f521ID, "", "type", "data", "onRetryChange", "timeMs", "", "okhttp-sse"}, m608k = 1, m609mv = {1, 4, 0})
    public interface Callback {
        void onEvent(String id, String type, String data);

        void onRetryChange(long timeMs);
    }

    public ServerSentEventReader(BufferedSource source, Callback callback) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.source = source;
        this.callback = callback;
    }

    public final boolean processNextEvent() throws IOException {
        String utf8LineStrict = this.lastId;
        Buffer buffer = new Buffer();
        while (true) {
            String utf8LineStrict2 = null;
            while (true) {
                BufferedSource bufferedSource = this.source;
                Options options2 = options;
                int iSelect = bufferedSource.select(options2);
                if (iSelect >= 0 && 2 >= iSelect) {
                    completeEvent(utf8LineStrict, utf8LineStrict2, buffer);
                    return true;
                }
                if (3 <= iSelect && 4 >= iSelect) {
                    INSTANCE.readData(this.source, buffer);
                } else if (5 <= iSelect && 7 >= iSelect) {
                    buffer.writeByte(10);
                } else if (8 <= iSelect && 9 >= iSelect) {
                    utf8LineStrict = this.source.readUtf8LineStrict();
                    if (utf8LineStrict.length() <= 0) {
                        utf8LineStrict = null;
                    }
                } else if (10 <= iSelect && 12 >= iSelect) {
                    utf8LineStrict = null;
                } else if (13 <= iSelect && 14 >= iSelect) {
                    utf8LineStrict2 = this.source.readUtf8LineStrict();
                    if (utf8LineStrict2.length() > 0) {
                    }
                } else {
                    if (15 <= iSelect && 17 >= iSelect) {
                        break;
                    }
                    if (18 <= iSelect && 19 >= iSelect) {
                        long retryMs = INSTANCE.readRetryMs(this.source);
                        if (retryMs != -1) {
                            this.callback.onRetryChange(retryMs);
                        }
                    } else if (iSelect == -1) {
                        long jIndexOfElement = this.source.indexOfElement(CRLF);
                        if (jIndexOfElement == -1) {
                            return false;
                        }
                        this.source.skip(jIndexOfElement);
                        this.source.select(options2);
                    } else {
                        throw new AssertionError();
                    }
                }
            }
        }
    }

    private final void completeEvent(String id, String type, Buffer data) throws IOException {
        if (data.size() != 0) {
            this.lastId = id;
            data.skip(1L);
            this.callback.onEvent(id, type, data.readUtf8());
        }
    }

    /* compiled from: ServerSentEventReader.kt */
    @Metadata(m605bv = {1, 0, 3}, m606d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\f\u0010\u000e\u001a\u00020\u000f*\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"}, m607d2 = {"Lokhttp3/internal/sse/ServerSentEventReader$Companion;", "", "()V", "CRLF", "Lokio/ByteString;", "options", "Lokio/Options;", "getOptions", "()Lokio/Options;", "readData", "", "Lokio/BufferedSource;", "data", "Lokio/Buffer;", "readRetryMs", "", "okhttp-sse"}, m608k = 1, m609mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Options getOptions() {
            return ServerSentEventReader.options;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void readData(BufferedSource bufferedSource, Buffer buffer) throws IOException {
            buffer.writeByte(10);
            bufferedSource.readFully(buffer, bufferedSource.indexOfElement(ServerSentEventReader.CRLF));
            bufferedSource.select(getOptions());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final long readRetryMs(BufferedSource bufferedSource) throws IOException {
            return Util.toLongOrDefault(bufferedSource.readUtf8LineStrict(), -1L);
        }
    }
}
