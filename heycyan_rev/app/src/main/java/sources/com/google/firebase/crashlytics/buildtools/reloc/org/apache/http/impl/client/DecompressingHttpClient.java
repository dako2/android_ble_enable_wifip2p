package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntityEnclosingRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequestInterceptor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponseInterceptor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ClientProtocolException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ResponseHandler;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpUriRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol.RequestAcceptEncoding;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol.ResponseContentEncoding;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.utils.URIUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.BasicHttpContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;
import java.io.IOException;

@Deprecated
/* loaded from: classes2.dex */
public class DecompressingHttpClient implements HttpClient {
    private final HttpRequestInterceptor acceptEncodingInterceptor;
    private final HttpClient backend;
    private final HttpResponseInterceptor contentEncodingInterceptor;

    public DecompressingHttpClient() {
        this(new DefaultHttpClient());
    }

    public DecompressingHttpClient(HttpClient httpClient) {
        this(httpClient, new RequestAcceptEncoding(), new ResponseContentEncoding());
    }

    DecompressingHttpClient(HttpClient httpClient, HttpRequestInterceptor httpRequestInterceptor, HttpResponseInterceptor httpResponseInterceptor) {
        this.backend = httpClient;
        this.acceptEncodingInterceptor = httpRequestInterceptor;
        this.contentEncodingInterceptor = httpResponseInterceptor;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public HttpParams getParams() {
        return this.backend.getParams();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public ClientConnectionManager getConnectionManager() {
        return this.backend.getConnectionManager();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public HttpResponse execute(HttpUriRequest httpUriRequest) throws IOException {
        return execute(getHttpHost(httpUriRequest), httpUriRequest, (HttpContext) null);
    }

    public HttpClient getHttpClient() {
        return this.backend;
    }

    HttpHost getHttpHost(HttpUriRequest httpUriRequest) {
        return URIUtils.extractHost(httpUriRequest.getURI());
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException {
        return execute(getHttpHost(httpUriRequest), httpUriRequest, httpContext);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        return execute(httpHost, httpRequest, (HttpContext) null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        HttpRequest requestWrapper;
        if (httpContext == null) {
            try {
                httpContext = new BasicHttpContext();
            } catch (HttpException e) {
                throw new ClientProtocolException(e);
            }
        }
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            requestWrapper = new EntityEnclosingRequestWrapper((HttpEntityEnclosingRequest) httpRequest);
        } else {
            requestWrapper = new RequestWrapper(httpRequest);
        }
        this.acceptEncodingInterceptor.process(requestWrapper, httpContext);
        HttpResponse httpResponseExecute = this.backend.execute(httpHost, requestWrapper, httpContext);
        try {
            try {
                this.contentEncodingInterceptor.process(httpResponseExecute, httpContext);
                if (Boolean.TRUE.equals(httpContext.getAttribute(ResponseContentEncoding.UNCOMPRESSED))) {
                    httpResponseExecute.removeHeaders("Content-Length");
                    httpResponseExecute.removeHeaders("Content-Encoding");
                    httpResponseExecute.removeHeaders("Content-MD5");
                }
                return httpResponseExecute;
            } catch (HttpException e2) {
                EntityUtils.consume(httpResponseExecute.getEntity());
                throw e2;
            }
        } catch (IOException e3) {
            EntityUtils.consume(httpResponseExecute.getEntity());
            throw e3;
        } catch (RuntimeException e4) {
            EntityUtils.consume(httpResponseExecute.getEntity());
            throw e4;
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) throws IOException {
        return (T) execute(getHttpHost(httpUriRequest), httpUriRequest, responseHandler);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException {
        return (T) execute(getHttpHost(httpUriRequest), httpUriRequest, responseHandler, httpContext);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) throws IOException {
        return (T) execute(httpHost, httpRequest, responseHandler, null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws HttpException, IOException {
        HttpResponse httpResponseExecute = execute(httpHost, httpRequest, httpContext);
        try {
            return responseHandler.handleResponse(httpResponseExecute);
        } finally {
            HttpEntity entity = httpResponseExecute.getEntity();
            if (entity != null) {
                EntityUtils.consume(entity);
            }
        }
    }
}
