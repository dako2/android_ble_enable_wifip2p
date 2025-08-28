package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.Header;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntityEnclosingRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ProtocolVersion;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.RequestLine;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.AbstractHttpMessage;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicRequestLine;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HTTP;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.net.URI;

/* loaded from: classes2.dex */
public class HttpRequestWrapper extends AbstractHttpMessage implements HttpUriRequest {
    private final String method;
    private final HttpRequest original;
    private RequestLine requestLine;
    private final HttpHost target;
    private URI uri;
    private ProtocolVersion version;

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpUriRequest
    public boolean isAborted() {
        return false;
    }

    private HttpRequestWrapper(HttpRequest httpRequest, HttpHost httpHost) {
        HttpRequest httpRequest2 = (HttpRequest) Args.notNull(httpRequest, "HTTP request");
        this.original = httpRequest2;
        this.target = httpHost;
        this.version = httpRequest2.getRequestLine().getProtocolVersion();
        this.method = httpRequest2.getRequestLine().getMethod();
        if (httpRequest instanceof HttpUriRequest) {
            this.uri = ((HttpUriRequest) httpRequest).getURI();
        } else {
            this.uri = null;
        }
        setHeaders(httpRequest.getAllHeaders());
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpMessage
    public ProtocolVersion getProtocolVersion() {
        ProtocolVersion protocolVersion = this.version;
        return protocolVersion != null ? protocolVersion : this.original.getProtocolVersion();
    }

    public void setProtocolVersion(ProtocolVersion protocolVersion) {
        this.version = protocolVersion;
        this.requestLine = null;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpUriRequest
    public URI getURI() {
        return this.uri;
    }

    public void setURI(URI uri) {
        this.uri = uri;
        this.requestLine = null;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpUriRequest
    public String getMethod() {
        return this.method;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpUriRequest
    public void abort() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest
    public RequestLine getRequestLine() {
        String uri;
        if (this.requestLine == null) {
            URI uri2 = this.uri;
            if (uri2 != null) {
                uri = uri2.toASCIIString();
            } else {
                uri = this.original.getRequestLine().getUri();
            }
            if (uri == null || uri.isEmpty()) {
                uri = "/";
            }
            this.requestLine = new BasicRequestLine(this.method, uri, getProtocolVersion());
        }
        return this.requestLine;
    }

    public HttpRequest getOriginal() {
        return this.original;
    }

    public HttpHost getTarget() {
        return this.target;
    }

    public String toString() {
        return getRequestLine() + HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR + this.headergroup;
    }

    static class HttpEntityEnclosingRequestWrapper extends HttpRequestWrapper implements HttpEntityEnclosingRequest {
        private HttpEntity entity;

        HttpEntityEnclosingRequestWrapper(HttpEntityEnclosingRequest httpEntityEnclosingRequest, HttpHost httpHost) {
            super(httpEntityEnclosingRequest, httpHost);
            this.entity = httpEntityEnclosingRequest.getEntity();
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntityEnclosingRequest
        public HttpEntity getEntity() {
            return this.entity;
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntityEnclosingRequest
        public void setEntity(HttpEntity httpEntity) {
            this.entity = httpEntity;
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntityEnclosingRequest
        public boolean expectContinue() {
            Header firstHeader = getFirstHeader("Expect");
            return firstHeader != null && HTTP.EXPECT_CONTINUE.equalsIgnoreCase(firstHeader.getValue());
        }
    }

    public static HttpRequestWrapper wrap(HttpRequest httpRequest) {
        return wrap(httpRequest, null);
    }

    public static HttpRequestWrapper wrap(HttpRequest httpRequest, HttpHost httpHost) {
        Args.notNull(httpRequest, "HTTP request");
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            return new HttpEntityEnclosingRequestWrapper((HttpEntityEnclosingRequest) httpRequest, httpHost);
        }
        return new HttpRequestWrapper(httpRequest, httpHost);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.AbstractHttpMessage, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpMessage
    @Deprecated
    public HttpParams getParams() {
        if (this.params == null) {
            this.params = this.original.getParams().copy();
        }
        return this.params;
    }
}
