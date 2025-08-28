package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public class BufferedHttpEntity extends HttpEntityWrapper {
    private final byte[] buffer;

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.HttpEntityWrapper, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return true;
    }

    public BufferedHttpEntity(HttpEntity httpEntity) throws IOException {
        super(httpEntity);
        if (!httpEntity.isRepeatable() || httpEntity.getContentLength() < 0) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            httpEntity.writeTo(byteArrayOutputStream);
            byteArrayOutputStream.flush();
            this.buffer = byteArrayOutputStream.toByteArray();
            return;
        }
        this.buffer = null;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.HttpEntityWrapper, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public long getContentLength() {
        if (this.buffer != null) {
            return r0.length;
        }
        return super.getContentLength();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.HttpEntityWrapper, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public InputStream getContent() throws IOException {
        if (this.buffer != null) {
            return new ByteArrayInputStream(this.buffer);
        }
        return super.getContent();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.HttpEntityWrapper, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public boolean isChunked() {
        return this.buffer == null && super.isChunked();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.HttpEntityWrapper, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        Args.notNull(outputStream, "Output stream");
        byte[] bArr = this.buffer;
        if (bArr != null) {
            outputStream.write(bArr);
        } else {
            super.writeTo(outputStream);
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.HttpEntityWrapper, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public boolean isStreaming() {
        return this.buffer == null && super.isStreaming();
    }
}
