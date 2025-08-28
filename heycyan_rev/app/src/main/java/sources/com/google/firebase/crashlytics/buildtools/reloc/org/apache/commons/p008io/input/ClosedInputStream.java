package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.input;

import java.io.InputStream;

/* loaded from: classes2.dex */
public class ClosedInputStream extends InputStream {
    public static final ClosedInputStream CLOSED_INPUT_STREAM = new ClosedInputStream();

    @Override // java.io.InputStream
    public int read() {
        return -1;
    }
}
