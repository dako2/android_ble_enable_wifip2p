package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ClientProtocolException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.config.RequestConfig;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.CloseableHttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.Configurable;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpExecutionAware;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpRequestWrapper;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol.HttpClientContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.HttpClientConnectionManager;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoute;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.scheme.SchemeRegistry;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.DefaultConnectionReuseStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.execchain.MinimalClientExec;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.BasicHttpParams;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.BasicHttpContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpRequestExecutor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
class MinimalHttpClient extends CloseableHttpClient {
    private final HttpClientConnectionManager connManager;
    private final HttpParams params = new BasicHttpParams();
    private final MinimalClientExec requestExecutor;

    public MinimalHttpClient(HttpClientConnectionManager httpClientConnectionManager) {
        this.connManager = (HttpClientConnectionManager) Args.notNull(httpClientConnectionManager, "HTTP connection manager");
        this.requestExecutor = new MinimalClientExec(new HttpRequestExecutor(), httpClientConnectionManager, DefaultConnectionReuseStrategy.INSTANCE, DefaultConnectionKeepAliveStrategy.INSTANCE);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.CloseableHttpClient
    protected CloseableHttpResponse doExecute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException {
        Args.notNull(httpHost, "Target host");
        Args.notNull(httpRequest, "HTTP request");
        HttpExecutionAware httpExecutionAware = httpRequest instanceof HttpExecutionAware ? (HttpExecutionAware) httpRequest : null;
        try {
            HttpRequestWrapper httpRequestWrapperWrap = HttpRequestWrapper.wrap(httpRequest);
            if (httpContext == null) {
                httpContext = new BasicHttpContext();
            }
            HttpClientContext httpClientContextAdapt = HttpClientContext.adapt(httpContext);
            HttpRoute httpRoute = new HttpRoute(httpHost);
            RequestConfig config = httpRequest instanceof Configurable ? ((Configurable) httpRequest).getConfig() : null;
            if (config != null) {
                httpClientContextAdapt.setRequestConfig(config);
            }
            return this.requestExecutor.execute(httpRoute, httpRequestWrapperWrap, httpClientContextAdapt, httpExecutionAware);
        } catch (HttpException e) {
            throw new ClientProtocolException(e);
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public HttpParams getParams() {
        return this.params;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.connManager.shutdown();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public ClientConnectionManager getConnectionManager() {
        return new ClientConnectionManager() { // from class: com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.MinimalHttpClient.1
            @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager
            public void shutdown() {
                MinimalHttpClient.this.connManager.shutdown();
            }

            @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager
            public ClientConnectionRequest requestConnection(HttpRoute httpRoute, Object obj) {
                throw new UnsupportedOperationException();
            }

            @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager
            public void releaseConnection(ManagedClientConnection managedClientConnection, long j, TimeUnit timeUnit) {
                throw new UnsupportedOperationException();
            }

            @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager
            public SchemeRegistry getSchemeRegistry() {
                throw new UnsupportedOperationException();
            }

            @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager
            public void closeIdleConnections(long j, TimeUnit timeUnit) {
                MinimalHttpClient.this.connManager.closeIdleConnections(j, timeUnit);
            }

            @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager
            public void closeExpiredConnections() {
                MinimalHttpClient.this.connManager.closeExpiredConnections();
            }
        };
    }
}
