package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ProtocolVersion;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.RequestLine;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.config.RequestConfig;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicRequestLine;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpProtocolParams;
import java.net.URI;

/* loaded from: classes2.dex */
public abstract class HttpRequestBase extends AbstractExecutionAwareRequest implements HttpUriRequest, Configurable {
    private RequestConfig config;
    private URI uri;
    private ProtocolVersion version;

    public abstract String getMethod();

    public void started() {
    }

    public void setProtocolVersion(ProtocolVersion protocolVersion) {
        this.version = protocolVersion;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpMessage
    public ProtocolVersion getProtocolVersion() {
        ProtocolVersion protocolVersion = this.version;
        return protocolVersion != null ? protocolVersion : HttpProtocolParams.getVersion(getParams());
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpUriRequest
    public URI getURI() {
        return this.uri;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest
    public RequestLine getRequestLine() {
        String method = getMethod();
        ProtocolVersion protocolVersion = getProtocolVersion();
        URI uri = getURI();
        String aSCIIString = uri != null ? uri.toASCIIString() : null;
        if (aSCIIString == null || aSCIIString.isEmpty()) {
            aSCIIString = "/";
        }
        return new BasicRequestLine(method, aSCIIString, protocolVersion);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.Configurable
    public RequestConfig getConfig() {
        return this.config;
    }

    public void setConfig(RequestConfig requestConfig) {
        this.config = requestConfig;
    }

    public void setURI(URI uri) {
        this.uri = uri;
    }

    public void releaseConnection() {
        reset();
    }

    public String toString() {
        return getMethod() + HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR + getURI() + HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR + getProtocolVersion();
    }
}
