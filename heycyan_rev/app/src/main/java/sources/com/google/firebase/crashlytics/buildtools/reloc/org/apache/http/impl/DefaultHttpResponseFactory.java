package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponseFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ProtocolVersion;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ReasonPhraseCatalog;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.StatusLine;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicHttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicStatusLine;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.util.Locale;

/* loaded from: classes2.dex */
public class DefaultHttpResponseFactory implements HttpResponseFactory {
    public static final DefaultHttpResponseFactory INSTANCE = new DefaultHttpResponseFactory();
    protected final ReasonPhraseCatalog reasonCatalog;

    public DefaultHttpResponseFactory(ReasonPhraseCatalog reasonPhraseCatalog) {
        this.reasonCatalog = (ReasonPhraseCatalog) Args.notNull(reasonPhraseCatalog, "Reason phrase catalog");
    }

    public DefaultHttpResponseFactory() {
        this(EnglishReasonPhraseCatalog.INSTANCE);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponseFactory
    public HttpResponse newHttpResponse(ProtocolVersion protocolVersion, int i, HttpContext httpContext) {
        Args.notNull(protocolVersion, "HTTP version");
        Locale localeDetermineLocale = determineLocale(httpContext);
        return new BasicHttpResponse(new BasicStatusLine(protocolVersion, i, this.reasonCatalog.getReason(i, localeDetermineLocale)), this.reasonCatalog, localeDetermineLocale);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponseFactory
    public HttpResponse newHttpResponse(StatusLine statusLine, HttpContext httpContext) {
        Args.notNull(statusLine, "Status line");
        return new BasicHttpResponse(statusLine, this.reasonCatalog, determineLocale(httpContext));
    }

    protected Locale determineLocale(HttpContext httpContext) {
        return Locale.getDefault();
    }
}
