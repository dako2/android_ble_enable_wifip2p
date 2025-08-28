package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.entity;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/* loaded from: classes2.dex */
public class GZIPInputStreamFactory implements InputStreamFactory {
    private static final GZIPInputStreamFactory INSTANCE = new GZIPInputStreamFactory();

    public static GZIPInputStreamFactory getInstance() {
        return INSTANCE;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.entity.InputStreamFactory
    public InputStream create(InputStream inputStream) throws IOException {
        return new GZIPInputStream(inputStream);
    }
}
