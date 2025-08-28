package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.p006io;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface ByteProcessor<T> {
    T getResult();

    boolean processBytes(byte[] bArr, int i, int i2) throws IOException;
}
