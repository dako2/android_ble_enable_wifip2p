package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.entity;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class DeflateInputStreamFactory implements InputStreamFactory {
    private static final DeflateInputStreamFactory INSTANCE = new DeflateInputStreamFactory();

    public static DeflateInputStreamFactory getInstance() {
        return INSTANCE;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.entity.InputStreamFactory
    public InputStream create(InputStream inputStream) throws IOException {
        return new DeflateInputStream(inputStream);
    }
}
