package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.output;

import java.io.OutputStream;

/* loaded from: classes2.dex */
public class CloseShieldOutputStream extends ProxyOutputStream {
    public CloseShieldOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.output.ProxyOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.out = new ClosedOutputStream();
    }
}
