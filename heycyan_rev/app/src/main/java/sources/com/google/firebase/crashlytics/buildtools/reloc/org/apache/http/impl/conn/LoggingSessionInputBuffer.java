package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.Consts;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.EofSensor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.HttpTransportMetrics;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionInputBuffer;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.CharArrayBuffer;
import java.io.IOException;

@Deprecated
/* loaded from: classes2.dex */
public class LoggingSessionInputBuffer implements SessionInputBuffer, EofSensor {
    private final String charset;
    private final EofSensor eofSensor;

    /* renamed from: in */
    private final SessionInputBuffer f381in;
    private final Wire wire;

    public LoggingSessionInputBuffer(SessionInputBuffer sessionInputBuffer, Wire wire, String str) {
        this.f381in = sessionInputBuffer;
        this.eofSensor = sessionInputBuffer instanceof EofSensor ? (EofSensor) sessionInputBuffer : null;
        this.wire = wire;
        this.charset = str == null ? Consts.ASCII.name() : str;
    }

    public LoggingSessionInputBuffer(SessionInputBuffer sessionInputBuffer, Wire wire) {
        this(sessionInputBuffer, wire, null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionInputBuffer
    public boolean isDataAvailable(int i) throws IOException {
        return this.f381in.isDataAvailable(i);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionInputBuffer
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.f381in.read(bArr, i, i2);
        if (this.wire.enabled() && i3 > 0) {
            this.wire.input(bArr, i, i3);
        }
        return i3;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionInputBuffer
    public int read() throws IOException {
        int i = this.f381in.read();
        if (this.wire.enabled() && i != -1) {
            this.wire.input(i);
        }
        return i;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionInputBuffer
    public int read(byte[] bArr) throws IOException {
        int i = this.f381in.read(bArr);
        if (this.wire.enabled() && i > 0) {
            this.wire.input(bArr, 0, i);
        }
        return i;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionInputBuffer
    public String readLine() throws IOException {
        String line = this.f381in.readLine();
        if (this.wire.enabled() && line != null) {
            this.wire.input((line + IOUtils.LINE_SEPARATOR_WINDOWS).getBytes(this.charset));
        }
        return line;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionInputBuffer
    public int readLine(CharArrayBuffer charArrayBuffer) throws IOException {
        int line = this.f381in.readLine(charArrayBuffer);
        if (this.wire.enabled() && line >= 0) {
            this.wire.input(new String(charArrayBuffer.buffer(), charArrayBuffer.length() - line, line).concat(IOUtils.LINE_SEPARATOR_WINDOWS).getBytes(this.charset));
        }
        return line;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionInputBuffer
    public HttpTransportMetrics getMetrics() {
        return this.f381in.getMetrics();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.EofSensor
    public boolean isEof() {
        EofSensor eofSensor = this.eofSensor;
        if (eofSensor != null) {
            return eofSensor.isEof();
        }
        return false;
    }
}
