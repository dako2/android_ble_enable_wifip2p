package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
class LoggingInputStream extends InputStream {

    /* renamed from: in */
    private final InputStream f380in;
    private final Wire wire;

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    public LoggingInputStream(InputStream inputStream, Wire wire) {
        this.f380in = inputStream;
        this.wire = wire;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        try {
            int i = this.f380in.read();
            if (i == -1) {
                this.wire.input("end of stream");
            } else {
                this.wire.input(i);
            }
            return i;
        } catch (IOException e) {
            this.wire.input("[read] I/O error: " + e.getMessage());
            throw e;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        try {
            int i = this.f380in.read(bArr);
            if (i == -1) {
                this.wire.input("end of stream");
            } else if (i > 0) {
                this.wire.input(bArr, 0, i);
            }
            return i;
        } catch (IOException e) {
            this.wire.input("[read] I/O error: " + e.getMessage());
            throw e;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        try {
            int i3 = this.f380in.read(bArr, i, i2);
            if (i3 == -1) {
                this.wire.input("end of stream");
            } else if (i3 > 0) {
                this.wire.input(bArr, i, i3);
            }
            return i3;
        } catch (IOException e) {
            this.wire.input("[read] I/O error: " + e.getMessage());
            throw e;
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        try {
            return super.skip(j);
        } catch (IOException e) {
            this.wire.input("[skip] I/O error: " + e.getMessage());
            throw e;
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        try {
            return this.f380in.available();
        } catch (IOException e) {
            this.wire.input("[available] I/O error : " + e.getMessage());
            throw e;
        }
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        super.mark(i);
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        super.reset();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            this.f380in.close();
        } catch (IOException e) {
            this.wire.input("[close] I/O error: " + e.getMessage());
            throw e;
        }
    }
}
