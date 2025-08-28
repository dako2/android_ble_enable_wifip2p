package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.execchain;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.Header;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntityEnclosingRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes2.dex */
class RequestEntityProxy implements HttpEntity {
    private boolean consumed = false;
    private final HttpEntity original;

    static void enhance(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        HttpEntity entity = httpEntityEnclosingRequest.getEntity();
        if (entity == null || entity.isRepeatable() || isEnhanced(entity)) {
            return;
        }
        httpEntityEnclosingRequest.setEntity(new RequestEntityProxy(entity));
    }

    static boolean isEnhanced(HttpEntity httpEntity) {
        return httpEntity instanceof RequestEntityProxy;
    }

    static boolean isRepeatable(HttpRequest httpRequest) {
        HttpEntity entity;
        if (!(httpRequest instanceof HttpEntityEnclosingRequest) || (entity = ((HttpEntityEnclosingRequest) httpRequest).getEntity()) == null) {
            return true;
        }
        if (!isEnhanced(entity) || ((RequestEntityProxy) entity).isConsumed()) {
            return entity.isRepeatable();
        }
        return true;
    }

    RequestEntityProxy(HttpEntity httpEntity) {
        this.original = httpEntity;
    }

    public HttpEntity getOriginal() {
        return this.original;
    }

    public boolean isConsumed() {
        return this.consumed;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return this.original.isRepeatable();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public boolean isChunked() {
        return this.original.isChunked();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public long getContentLength() {
        return this.original.getContentLength();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public Header getContentType() {
        return this.original.getContentType();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public Header getContentEncoding() {
        return this.original.getContentEncoding();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public InputStream getContent() throws IllegalStateException, IOException {
        return this.original.getContent();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        this.consumed = true;
        this.original.writeTo(outputStream);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public boolean isStreaming() {
        return this.original.isStreaming();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    @Deprecated
    public void consumeContent() throws IOException {
        this.consumed = true;
        this.original.consumeContent();
    }

    public String toString() {
        return "RequestEntityProxy{" + this.original + '}';
    }
}
