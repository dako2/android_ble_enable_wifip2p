package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicLineFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.LineFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.HttpMessageWriter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.HttpMessageWriterFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionOutputBuffer;

/* loaded from: classes2.dex */
public class DefaultHttpRequestWriterFactory implements HttpMessageWriterFactory<HttpRequest> {
    public static final DefaultHttpRequestWriterFactory INSTANCE = new DefaultHttpRequestWriterFactory();
    private final LineFormatter lineFormatter;

    public DefaultHttpRequestWriterFactory(LineFormatter lineFormatter) {
        this.lineFormatter = lineFormatter == null ? BasicLineFormatter.INSTANCE : lineFormatter;
    }

    public DefaultHttpRequestWriterFactory() {
        this(null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.HttpMessageWriterFactory
    public HttpMessageWriter<HttpRequest> create(SessionOutputBuffer sessionOutputBuffer) {
        return new DefaultHttpRequestWriter(sessionOutputBuffer, this.lineFormatter);
    }
}
