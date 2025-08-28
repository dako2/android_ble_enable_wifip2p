package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.params.ConnRouteParams;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoute;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoutePlanner;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.scheme.SchemeRegistry;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Asserts;
import java.net.InetAddress;

@Deprecated
/* loaded from: classes2.dex */
public class DefaultHttpRoutePlanner implements HttpRoutePlanner {
    protected final SchemeRegistry schemeRegistry;

    public DefaultHttpRoutePlanner(SchemeRegistry schemeRegistry) {
        Args.notNull(schemeRegistry, "Scheme registry");
        this.schemeRegistry = schemeRegistry;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoutePlanner
    public HttpRoute determineRoute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws HttpException {
        Args.notNull(httpRequest, "HTTP request");
        HttpRoute forcedRoute = ConnRouteParams.getForcedRoute(httpRequest.getParams());
        if (forcedRoute != null) {
            return forcedRoute;
        }
        Asserts.notNull(httpHost, "Target host");
        InetAddress localAddress = ConnRouteParams.getLocalAddress(httpRequest.getParams());
        HttpHost defaultProxy = ConnRouteParams.getDefaultProxy(httpRequest.getParams());
        try {
            boolean zIsLayered = this.schemeRegistry.getScheme(httpHost.getSchemeName()).isLayered();
            if (defaultProxy == null) {
                return new HttpRoute(httpHost, localAddress, zIsLayered);
            }
            return new HttpRoute(httpHost, localAddress, defaultProxy, zIsLayered);
        } catch (IllegalStateException e) {
            throw new HttpException(e.getMessage());
        }
    }
}
