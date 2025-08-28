package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnectionMetrics;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntityEnclosingRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponseFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.entity.EntityDeserializer;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.entity.EntitySerializer;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.entity.LaxContentLengthStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.entity.StrictContentLengthStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.DefaultHttpResponseParser;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.HttpRequestWriter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.LineParser;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.EofSensor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.HttpMessageParser;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.HttpMessageWriter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.HttpTransportMetrics;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionInputBuffer;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionOutputBuffer;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.io.IOException;
import java.net.SocketTimeoutException;

@Deprecated
/* loaded from: classes2.dex */
public abstract class AbstractHttpClientConnection implements HttpClientConnection {
    private SessionInputBuffer inbuffer = null;
    private SessionOutputBuffer outbuffer = null;
    private EofSensor eofSensor = null;
    private HttpMessageParser<HttpResponse> responseParser = null;
    private HttpMessageWriter<HttpRequest> requestWriter = null;
    private HttpConnectionMetricsImpl metrics = null;
    private final EntitySerializer entityserializer = createEntitySerializer();
    private final EntityDeserializer entitydeserializer = createEntityDeserializer();

    protected abstract void assertOpen() throws IllegalStateException;

    protected EntityDeserializer createEntityDeserializer() {
        return new EntityDeserializer(new LaxContentLengthStrategy());
    }

    protected EntitySerializer createEntitySerializer() {
        return new EntitySerializer(new StrictContentLengthStrategy());
    }

    protected HttpResponseFactory createHttpResponseFactory() {
        return DefaultHttpResponseFactory.INSTANCE;
    }

    protected HttpMessageParser<HttpResponse> createResponseParser(SessionInputBuffer sessionInputBuffer, HttpResponseFactory httpResponseFactory, HttpParams httpParams) {
        return new DefaultHttpResponseParser(sessionInputBuffer, (LineParser) null, httpResponseFactory, httpParams);
    }

    protected HttpMessageWriter<HttpRequest> createRequestWriter(SessionOutputBuffer sessionOutputBuffer, HttpParams httpParams) {
        return new HttpRequestWriter(sessionOutputBuffer, null, httpParams);
    }

    protected HttpConnectionMetricsImpl createConnectionMetrics(HttpTransportMetrics httpTransportMetrics, HttpTransportMetrics httpTransportMetrics2) {
        return new HttpConnectionMetricsImpl(httpTransportMetrics, httpTransportMetrics2);
    }

    protected void init(SessionInputBuffer sessionInputBuffer, SessionOutputBuffer sessionOutputBuffer, HttpParams httpParams) {
        this.inbuffer = (SessionInputBuffer) Args.notNull(sessionInputBuffer, "Input session buffer");
        this.outbuffer = (SessionOutputBuffer) Args.notNull(sessionOutputBuffer, "Output session buffer");
        if (sessionInputBuffer instanceof EofSensor) {
            this.eofSensor = (EofSensor) sessionInputBuffer;
        }
        this.responseParser = createResponseParser(sessionInputBuffer, createHttpResponseFactory(), httpParams);
        this.requestWriter = createRequestWriter(sessionOutputBuffer, httpParams);
        this.metrics = createConnectionMetrics(sessionInputBuffer.getMetrics(), sessionOutputBuffer.getMetrics());
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection
    public boolean isResponseAvailable(int i) throws IllegalStateException, IOException {
        assertOpen();
        try {
            return this.inbuffer.isDataAvailable(i);
        } catch (SocketTimeoutException unused) {
            return false;
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection
    public void sendRequestHeader(HttpRequest httpRequest) throws IllegalStateException, HttpException, IOException {
        Args.notNull(httpRequest, "HTTP request");
        assertOpen();
        this.requestWriter.write(httpRequest);
        this.metrics.incrementRequestCount();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection
    public void sendRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) throws IllegalStateException, HttpException, IOException {
        Args.notNull(httpEntityEnclosingRequest, "HTTP request");
        assertOpen();
        if (httpEntityEnclosingRequest.getEntity() == null) {
            return;
        }
        this.entityserializer.serialize(this.outbuffer, httpEntityEnclosingRequest, httpEntityEnclosingRequest.getEntity());
    }

    protected void doFlush() throws IOException {
        this.outbuffer.flush();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection
    public void flush() throws IllegalStateException, IOException {
        assertOpen();
        doFlush();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection
    public HttpResponse receiveResponseHeader() throws IllegalStateException, HttpException, IOException {
        assertOpen();
        HttpResponse httpResponse = (HttpResponse) this.responseParser.parse();
        if (httpResponse.getStatusLine().getStatusCode() >= 200) {
            this.metrics.incrementResponseCount();
        }
        return httpResponse;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection
    public void receiveResponseEntity(HttpResponse httpResponse) throws IllegalStateException, HttpException, IOException {
        Args.notNull(httpResponse, "HTTP response");
        assertOpen();
        httpResponse.setEntity(this.entitydeserializer.deserialize(this.inbuffer, httpResponse));
    }

    protected boolean isEof() {
        EofSensor eofSensor = this.eofSensor;
        return eofSensor != null && eofSensor.isEof();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public boolean isStale() {
        if (!isOpen() || isEof()) {
            return true;
        }
        try {
            this.inbuffer.isDataAvailable(1);
            return isEof();
        } catch (SocketTimeoutException unused) {
            return false;
        } catch (IOException unused2) {
            return true;
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public HttpConnectionMetrics getMetrics() {
        return this.metrics;
    }
}
