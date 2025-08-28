package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.input;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class AutoCloseInputStream extends ProxyInputStream {
    public AutoCloseInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
        this.in = new ClosedInputStream();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.input.ProxyInputStream
    protected void afterRead(int i) throws IOException {
        if (i == -1) {
            close();
        }
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
}
