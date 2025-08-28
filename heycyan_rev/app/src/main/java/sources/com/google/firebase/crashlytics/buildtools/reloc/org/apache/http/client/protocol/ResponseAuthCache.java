package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponseInterceptor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthProtocolState;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthScheme;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthState;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.AuthCache;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.scheme.SchemeRegistry;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.BasicAuthCache;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.ExecutionContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.io.IOException;

@Deprecated
/* loaded from: classes2.dex */
public class ResponseAuthCache implements HttpResponseInterceptor {
    private final Log log = LogFactory.getLog(getClass());

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponseInterceptor
    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        Args.notNull(httpResponse, "HTTP request");
        Args.notNull(httpContext, "HTTP context");
        AuthCache basicAuthCache = (AuthCache) httpContext.getAttribute("http.auth.auth-cache");
        HttpHost httpHost = (HttpHost) httpContext.getAttribute("http.target_host");
        AuthState authState = (AuthState) httpContext.getAttribute("http.auth.target-scope");
        if (httpHost != null && authState != null) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("Target auth state: " + authState.getState());
            }
            if (isCachable(authState)) {
                SchemeRegistry schemeRegistry = (SchemeRegistry) httpContext.getAttribute(ClientContext.SCHEME_REGISTRY);
                if (httpHost.getPort() < 0) {
                    httpHost = new HttpHost(httpHost.getHostName(), schemeRegistry.getScheme(httpHost).resolvePort(httpHost.getPort()), httpHost.getSchemeName());
                }
                if (basicAuthCache == null) {
                    basicAuthCache = new BasicAuthCache();
                    httpContext.setAttribute("http.auth.auth-cache", basicAuthCache);
                }
                int i = C20301.$SwitchMap$org$apache$http$auth$AuthProtocolState[authState.getState().ordinal()];
                if (i == 1) {
                    cache(basicAuthCache, httpHost, authState.getAuthScheme());
                } else if (i == 2) {
                    uncache(basicAuthCache, httpHost, authState.getAuthScheme());
                }
            }
        }
        HttpHost httpHost2 = (HttpHost) httpContext.getAttribute(ExecutionContext.HTTP_PROXY_HOST);
        AuthState authState2 = (AuthState) httpContext.getAttribute("http.auth.proxy-scope");
        if (httpHost2 == null || authState2 == null) {
            return;
        }
        if (this.log.isDebugEnabled()) {
            this.log.debug("Proxy auth state: " + authState2.getState());
        }
        if (isCachable(authState2)) {
            if (basicAuthCache == null) {
                basicAuthCache = new BasicAuthCache();
                httpContext.setAttribute("http.auth.auth-cache", basicAuthCache);
            }
            int i2 = C20301.$SwitchMap$org$apache$http$auth$AuthProtocolState[authState2.getState().ordinal()];
            if (i2 == 1) {
                cache(basicAuthCache, httpHost2, authState2.getAuthScheme());
            } else {
                if (i2 != 2) {
                    return;
                }
                uncache(basicAuthCache, httpHost2, authState2.getAuthScheme());
            }
        }
    }

    /* renamed from: com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol.ResponseAuthCache$1 */
    static /* synthetic */ class C20301 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$http$auth$AuthProtocolState;

        static {
            int[] iArr = new int[AuthProtocolState.values().length];
            $SwitchMap$org$apache$http$auth$AuthProtocolState = iArr;
            try {
                iArr[AuthProtocolState.CHALLENGED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$http$auth$AuthProtocolState[AuthProtocolState.FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private boolean isCachable(AuthState authState) {
        AuthScheme authScheme = authState.getAuthScheme();
        if (authScheme == null || !authScheme.isComplete()) {
            return false;
        }
        String schemeName = authScheme.getSchemeName();
        return schemeName.equalsIgnoreCase("Basic") || schemeName.equalsIgnoreCase("Digest");
    }

    private void cache(AuthCache authCache, HttpHost httpHost, AuthScheme authScheme) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Caching '" + authScheme.getSchemeName() + "' auth scheme for " + httpHost);
        }
        authCache.put(httpHost, authScheme);
    }

    private void uncache(AuthCache authCache, HttpHost httpHost, AuthScheme authScheme) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Removing from cache '" + authScheme.getSchemeName() + "' auth scheme for " + httpHost);
        }
        authCache.remove(httpHost);
    }
}
