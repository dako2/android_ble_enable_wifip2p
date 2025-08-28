package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.p006io;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Preconditions;
import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

/* loaded from: classes2.dex */
class MultiReader extends Reader {

    @NullableDecl
    private Reader current;

    /* renamed from: it */
    private final Iterator<? extends CharSource> f347it;

    MultiReader(Iterator<? extends CharSource> it) throws IOException {
        this.f347it = it;
        advance();
    }

    private void advance() throws IOException {
        close();
        if (this.f347it.hasNext()) {
            this.current = this.f347it.next().openStream();
        }
    }

    @Override // java.io.Reader
    public int read(@NullableDecl char[] cArr, int i, int i2) throws IOException {
        Reader reader = this.current;
        if (reader == null) {
            return -1;
        }
        int i3 = reader.read(cArr, i, i2);
        if (i3 != -1) {
            return i3;
        }
        advance();
        return read(cArr, i, i2);
    }

    @Override // java.io.Reader
    public long skip(long j) throws IOException {
        Preconditions.checkArgument(j >= 0, "n is negative");
        if (j > 0) {
            while (true) {
                Reader reader = this.current;
                if (reader == null) {
                    break;
                }
                long jSkip = reader.skip(j);
                if (jSkip > 0) {
                    return jSkip;
                }
                advance();
            }
        }
        return 0L;
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        Reader reader = this.current;
        return reader != null && reader.ready();
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Reader reader = this.current;
        if (reader != null) {
            try {
                reader.close();
            } finally {
                this.current = null;
            }
        }
    }
}
