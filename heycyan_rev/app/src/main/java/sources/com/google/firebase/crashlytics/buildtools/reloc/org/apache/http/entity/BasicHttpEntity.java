package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.EmptyInputStream;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Asserts;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public class BasicHttpEntity extends AbstractHttpEntity {
    private InputStream content;
    private long length = -1;

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return false;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public long getContentLength() {
        return this.length;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public InputStream getContent() throws IllegalStateException {
        Asserts.check(this.content != null, "Content has not been provided");
        return this.content;
    }

    public void setContentLength(long j) {
        this.length = j;
    }

    public void setContent(InputStream inputStream) {
        this.content = inputStream;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IllegalStateException, IOException {
        Args.notNull(outputStream, "Output stream");
        InputStream content = getContent();
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int i = content.read(bArr);
                if (i == -1) {
                    return;
                } else {
                    outputStream.write(bArr, 0, i);
                }
            }
        } finally {
            content.close();
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity
    public boolean isStreaming() {
        InputStream inputStream = this.content;
        return (inputStream == null || inputStream == EmptyInputStream.INSTANCE) ? false : true;
    }
}
