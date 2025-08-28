package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.p006io;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Preconditions;
import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/* loaded from: classes2.dex */
final class MultiInputStream extends InputStream {

    /* renamed from: in */
    @NullableDecl
    private InputStream f345in;

    /* renamed from: it */
    private Iterator<? extends ByteSource> f346it;

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    public MultiInputStream(Iterator<? extends ByteSource> it) throws IOException {
        this.f346it = (Iterator) Preconditions.checkNotNull(it);
        advance();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.f345in;
        if (inputStream != null) {
            try {
                inputStream.close();
            } finally {
                this.f345in = null;
            }
        }
    }

    private void advance() throws IOException {
        close();
        if (this.f346it.hasNext()) {
            this.f345in = this.f346it.next().openStream();
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        InputStream inputStream = this.f345in;
        if (inputStream == null) {
            return 0;
        }
        return inputStream.available();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        while (true) {
            InputStream inputStream = this.f345in;
            if (inputStream == null) {
                return -1;
            }
            int i = inputStream.read();
            if (i != -1) {
                return i;
            }
            advance();
        }
    }

    @Override // java.io.InputStream
    public int read(@NullableDecl byte[] bArr, int i, int i2) throws IOException {
        while (true) {
            InputStream inputStream = this.f345in;
            if (inputStream == null) {
                return -1;
            }
            int i3 = inputStream.read(bArr, i, i2);
            if (i3 != -1) {
                return i3;
            }
            advance();
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        InputStream inputStream = this.f345in;
        if (inputStream == null || j <= 0) {
            return 0L;
        }
        long jSkip = inputStream.skip(j);
        if (jSkip != 0) {
            return jSkip;
        }
        if (read() == -1) {
            return 0L;
        }
        return this.f345in.skip(j - 1) + 1;
    }
}
