package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.execchain;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntityEnclosingRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ProtocolException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthState;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.RedirectException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.RedirectStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.config.RequestConfig;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.CloseableHttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpExecutionAware;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpRequestWrapper;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpUriRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol.HttpClientContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.utils.URIUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoute;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoutePlanner;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URI;
import java.util.List;

/* loaded from: classes2.dex */
public class RedirectExec implements ClientExecChain {
    private final Log log = LogFactory.getLog(getClass());
    private final RedirectStrategy redirectStrategy;
    private final ClientExecChain requestExecutor;
    private final HttpRoutePlanner routePlanner;

    public RedirectExec(ClientExecChain clientExecChain, HttpRoutePlanner httpRoutePlanner, RedirectStrategy redirectStrategy) {
        Args.notNull(clientExecChain, "HTTP client request executor");
        Args.notNull(httpRoutePlanner, "HTTP route planner");
        Args.notNull(redirectStrategy, "HTTP redirect strategy");
        this.requestExecutor = clientExecChain;
        this.routePlanner = httpRoutePlanner;
        this.redirectStrategy = redirectStrategy;
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0122, code lost:
    
        return r4;
     */
    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.execchain.ClientExecChain
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public CloseableHttpResponse execute(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware) throws HttpException, IOException {
        Args.notNull(httpRoute, "HTTP route");
        Args.notNull(httpRequestWrapper, "HTTP request");
        Args.notNull(httpClientContext, "HTTP context");
        List<URI> redirectLocations = httpClientContext.getRedirectLocations();
        if (redirectLocations != null) {
            redirectLocations.clear();
        }
        RequestConfig requestConfig = httpClientContext.getRequestConfig();
        int maxRedirects = requestConfig.getMaxRedirects() > 0 ? requestConfig.getMaxRedirects() : 50;
        int i = 0;
        HttpRequestWrapper httpRequestWrapper2 = httpRequestWrapper;
        while (true) {
            CloseableHttpResponse closeableHttpResponseExecute = this.requestExecutor.execute(httpRoute, httpRequestWrapper2, httpClientContext, httpExecutionAware);
            try {
                if (!requestConfig.isRedirectsEnabled() || !this.redirectStrategy.isRedirected(httpRequestWrapper2.getOriginal(), closeableHttpResponseExecute, httpClientContext)) {
                    break;
                }
                if (i >= maxRedirects) {
                    throw new RedirectException("Maximum redirects (" + maxRedirects + ") exceeded");
                }
                i++;
                HttpUriRequest redirect = this.redirectStrategy.getRedirect(httpRequestWrapper2.getOriginal(), closeableHttpResponseExecute, httpClientContext);
                if (!redirect.headerIterator().hasNext()) {
                    redirect.setHeaders(httpRequestWrapper.getOriginal().getAllHeaders());
                }
                HttpRequestWrapper httpRequestWrapperWrap = HttpRequestWrapper.wrap(redirect);
                if (httpRequestWrapperWrap instanceof HttpEntityEnclosingRequest) {
                    RequestEntityProxy.enhance((HttpEntityEnclosingRequest) httpRequestWrapperWrap);
                }
                URI uri = httpRequestWrapperWrap.getURI();
                HttpHost httpHostExtractHost = URIUtils.extractHost(uri);
                if (httpHostExtractHost == null) {
                    throw new ProtocolException("Redirect URI does not specify a valid host name: " + uri);
                }
                if (!httpRoute.getTargetHost().equals(httpHostExtractHost)) {
                    AuthState targetAuthState = httpClientContext.getTargetAuthState();
                    if (targetAuthState != null) {
                        this.log.debug("Resetting target auth state");
                        targetAuthState.reset();
                    }
                    AuthState proxyAuthState = httpClientContext.getProxyAuthState();
                    if (proxyAuthState != null && proxyAuthState.isConnectionBased()) {
                        this.log.debug("Resetting proxy auth state");
                        proxyAuthState.reset();
                    }
                }
                httpRoute = this.routePlanner.determineRoute(httpHostExtractHost, httpRequestWrapperWrap, httpClientContext);
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Redirecting to '" + uri + "' via " + httpRoute);
                }
                EntityUtils.consume(closeableHttpResponseExecute.getEntity());
                closeableHttpResponseExecute.close();
                httpRequestWrapper2 = httpRequestWrapperWrap;
            } catch (HttpException e) {
                try {
                    try {
                        EntityUtils.consume(closeableHttpResponseExecute.getEntity());
                    } catch (IOException e2) {
                        this.log.debug("I/O error while releasing connection", e2);
                    }
                    closeableHttpResponseExecute.close();
                    throw e;
                } catch (Throwable th) {
                    closeableHttpResponseExecute.close();
                    throw th;
                }
            } catch (IOException e3) {
                closeableHttpResponseExecute.close();
                throw e3;
            } catch (RuntimeException e4) {
                closeableHttpResponseExecute.close();
                throw e4;
            }
        }
    }
}
