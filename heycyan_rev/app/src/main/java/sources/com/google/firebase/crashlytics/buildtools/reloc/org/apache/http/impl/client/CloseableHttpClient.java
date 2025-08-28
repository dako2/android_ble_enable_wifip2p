package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ClientProtocolException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ResponseHandler;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.CloseableHttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpUriRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.utils.URIUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;
import java.io.Closeable;
import java.io.IOException;
import java.net.URI;

/* loaded from: classes2.dex */
public abstract class CloseableHttpClient implements HttpClient, Closeable {
    private final Log log = LogFactory.getLog(getClass());

    protected abstract CloseableHttpResponse doExecute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException;

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public CloseableHttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException {
        return doExecute(httpHost, httpRequest, httpContext);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public CloseableHttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException {
        Args.notNull(httpUriRequest, "HTTP request");
        return doExecute(determineTarget(httpUriRequest), httpUriRequest, httpContext);
    }

    private static HttpHost determineTarget(HttpUriRequest httpUriRequest) throws ClientProtocolException {
        URI uri = httpUriRequest.getURI();
        if (!uri.isAbsolute()) {
            return null;
        }
        HttpHost httpHostExtractHost = URIUtils.extractHost(uri);
        if (httpHostExtractHost != null) {
            return httpHostExtractHost;
        }
        throw new ClientProtocolException("URI does not specify a valid host name: " + uri);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public CloseableHttpResponse execute(HttpUriRequest httpUriRequest) throws IOException {
        return execute(httpUriRequest, (HttpContext) null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public CloseableHttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        return doExecute(httpHost, httpRequest, null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) throws IOException {
        return (T) execute(httpUriRequest, responseHandler, (HttpContext) null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException {
        return (T) execute(determineTarget(httpUriRequest), httpUriRequest, responseHandler, httpContext);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) throws IOException {
        return (T) execute(httpHost, httpRequest, responseHandler, null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException {
        Args.notNull(responseHandler, "Response handler");
        CloseableHttpResponse closeableHttpResponseExecute = execute(httpHost, httpRequest, httpContext);
        try {
            try {
                T tHandleResponse = responseHandler.handleResponse(closeableHttpResponseExecute);
                EntityUtils.consume(closeableHttpResponseExecute.getEntity());
                return tHandleResponse;
            } catch (ClientProtocolException e) {
                try {
                    EntityUtils.consume(closeableHttpResponseExecute.getEntity());
                } catch (Exception e2) {
                    this.log.warn("Error consuming content after an exception.", e2);
                }
                throw e;
            }
        } finally {
            closeableHttpResponseExecute.close();
        }
    }
}
