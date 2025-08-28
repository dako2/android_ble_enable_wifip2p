package com.google.firebase.crashlytics.buildtools.utils.p011io;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public abstract class SeekableInputStream extends InputStream {
    public abstract long getCurrentOffset() throws IOException;

    public abstract void readFully(byte[] bArr, int i, int i2) throws IOException;

    public abstract void seek(long j) throws IOException;
}
