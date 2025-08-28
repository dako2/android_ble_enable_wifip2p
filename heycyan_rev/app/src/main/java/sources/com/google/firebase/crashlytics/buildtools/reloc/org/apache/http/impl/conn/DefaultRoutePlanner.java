package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ProtocolException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.config.RequestConfig;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol.HttpClientContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.SchemePortResolver;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.UnsupportedSchemeException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoute;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoutePlanner;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.net.InetAddress;

/* loaded from: classes2.dex */
public class DefaultRoutePlanner implements HttpRoutePlanner {
    private final SchemePortResolver schemePortResolver;

    protected HttpHost determineProxy(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws HttpException {
        return null;
    }

    public DefaultRoutePlanner(SchemePortResolver schemePortResolver) {
        this.schemePortResolver = schemePortResolver == null ? DefaultSchemePortResolver.INSTANCE : schemePortResolver;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoutePlanner
    public HttpRoute determineRoute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws HttpException {
        Args.notNull(httpRequest, "Request");
        if (httpHost == null) {
            throw new ProtocolException("Target host is not specified");
        }
        RequestConfig requestConfig = HttpClientContext.adapt(httpContext).getRequestConfig();
        InetAddress localAddress = requestConfig.getLocalAddress();
        HttpHost proxy = requestConfig.getProxy();
        if (proxy == null) {
            proxy = determineProxy(httpHost, httpRequest, httpContext);
        }
        if (httpHost.getPort() <= 0) {
            try {
                httpHost = new HttpHost(httpHost.getHostName(), this.schemePortResolver.resolve(httpHost), httpHost.getSchemeName());
            } catch (UnsupportedSchemeException e) {
                throw new HttpException(e.getMessage());
            }
        }
        boolean zEqualsIgnoreCase = httpHost.getSchemeName().equalsIgnoreCase("https");
        if (proxy == null) {
            return new HttpRoute(httpHost, localAddress, zEqualsIgnoreCase);
        }
        return new HttpRoute(httpHost, localAddress, proxy, zEqualsIgnoreCase);
    }
}
