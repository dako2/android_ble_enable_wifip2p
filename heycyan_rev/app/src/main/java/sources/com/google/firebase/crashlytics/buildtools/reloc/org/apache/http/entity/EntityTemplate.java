package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public class EntityTemplate extends AbstractHttpEntity {
    private final ContentProducer contentproducer;

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public long getContentLength() {
        return -1L;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return true;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public boolean isStreaming() {
        return false;
    }

    public EntityTemplate(ContentProducer contentProducer) {
        this.contentproducer = (ContentProducer) Args.notNull(contentProducer, "Content producer");
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public InputStream getContent() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        writeTo(byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        Args.notNull(outputStream, "Output stream");
        this.contentproducer.writeTo(outputStream);
    }
}
