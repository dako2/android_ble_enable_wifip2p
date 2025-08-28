package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicLineFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.LineFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.HttpMessageWriter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.HttpMessageWriterFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionOutputBuffer;

/* loaded from: classes2.dex */
public class DefaultHttpResponseWriterFactory implements HttpMessageWriterFactory<HttpResponse> {
    public static final DefaultHttpResponseWriterFactory INSTANCE = new DefaultHttpResponseWriterFactory();
    private final LineFormatter lineFormatter;

    public DefaultHttpResponseWriterFactory(LineFormatter lineFormatter) {
        this.lineFormatter = lineFormatter == null ? BasicLineFormatter.INSTANCE : lineFormatter;
    }

    public DefaultHttpResponseWriterFactory() {
        this(null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.HttpMessageWriterFactory
    public HttpMessageWriter<HttpResponse> create(SessionOutputBuffer sessionOutputBuffer) {
        return new DefaultHttpResponseWriter(sessionOutputBuffer, this.lineFormatter);
    }
}
