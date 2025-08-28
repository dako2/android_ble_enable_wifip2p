package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.Consts;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.HttpTransportMetrics;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionOutputBuffer;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.CharArrayBuffer;
import java.io.IOException;

@Deprecated
/* loaded from: classes2.dex */
public class LoggingSessionOutputBuffer implements SessionOutputBuffer {
    private final String charset;
    private final SessionOutputBuffer out;
    private final Wire wire;

    public LoggingSessionOutputBuffer(SessionOutputBuffer sessionOutputBuffer, Wire wire, String str) {
        this.out = sessionOutputBuffer;
        this.wire = wire;
        this.charset = str == null ? Consts.ASCII.name() : str;
    }

    public LoggingSessionOutputBuffer(SessionOutputBuffer sessionOutputBuffer, Wire wire) {
        this(sessionOutputBuffer, wire, null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionOutputBuffer
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        if (this.wire.enabled()) {
            this.wire.output(bArr, i, i2);
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionOutputBuffer
    public void write(int i) throws IOException {
        this.out.write(i);
        if (this.wire.enabled()) {
            this.wire.output(i);
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionOutputBuffer
    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
        if (this.wire.enabled()) {
            this.wire.output(bArr);
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionOutputBuffer
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionOutputBuffer
    public void writeLine(CharArrayBuffer charArrayBuffer) throws IOException {
        this.out.writeLine(charArrayBuffer);
        if (this.wire.enabled()) {
            this.wire.output(new String(charArrayBuffer.buffer(), 0, charArrayBuffer.length()).concat(IOUtils.LINE_SEPARATOR_WINDOWS).getBytes(this.charset));
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionOutputBuffer
    public void writeLine(String str) throws IOException {
        this.out.writeLine(str);
        if (this.wire.enabled()) {
            this.wire.output((str + IOUtils.LINE_SEPARATOR_WINDOWS).getBytes(this.charset));
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionOutputBuffer
    public HttpTransportMetrics getMetrics() {
        return this.out.getMetrics();
    }
}
