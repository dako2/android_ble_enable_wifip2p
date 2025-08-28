package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.SchemePortResolver;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;

/* loaded from: classes2.dex */
public class DefaultProxyRoutePlanner extends DefaultRoutePlanner {
    private final HttpHost proxy;

    public DefaultProxyRoutePlanner(HttpHost httpHost, SchemePortResolver schemePortResolver) {
        super(schemePortResolver);
        this.proxy = (HttpHost) Args.notNull(httpHost, "Proxy host");
    }

    public DefaultProxyRoutePlanner(HttpHost httpHost) {
        this(httpHost, null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.DefaultRoutePlanner
    protected HttpHost determineProxy(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws HttpException {
        return this.proxy;
    }
}
