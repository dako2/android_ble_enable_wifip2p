package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.Header;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.config.MessageConstraints;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.ContentLengthStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.HttpMessageParserFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.HttpMessageWriterFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/* loaded from: classes2.dex */
class LoggingManagedHttpClientConnection extends DefaultManagedHttpClientConnection {
    private final Log headerlog;
    private final Log log;
    private final Wire wire;

    public LoggingManagedHttpClientConnection(String str, Log log, Log log2, Log log3, int i, int i2, CharsetDecoder charsetDecoder, CharsetEncoder charsetEncoder, MessageConstraints messageConstraints, ContentLengthStrategy contentLengthStrategy, ContentLengthStrategy contentLengthStrategy2, HttpMessageWriterFactory<HttpRequest> httpMessageWriterFactory, HttpMessageParserFactory<HttpResponse> httpMessageParserFactory) {
        super(str, i, i2, charsetDecoder, charsetEncoder, messageConstraints, contentLengthStrategy, contentLengthStrategy2, httpMessageWriterFactory, httpMessageParserFactory);
        this.log = log;
        this.headerlog = log2;
        this.wire = new Wire(log3, str);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.BHttpConnectionBase, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (super.isOpen()) {
            if (this.log.isDebugEnabled()) {
                this.log.debug(getId() + ": Close connection");
            }
            super.close();
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.BHttpConnectionBase, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public void setSocketTimeout(int i) {
        if (this.log.isDebugEnabled()) {
            this.log.debug(getId() + ": set socket timeout to " + i);
        }
        super.setSocketTimeout(i);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.DefaultManagedHttpClientConnection, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.BHttpConnectionBase, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public void shutdown() throws IOException {
        if (this.log.isDebugEnabled()) {
            this.log.debug(getId() + ": Shutdown connection");
        }
        super.shutdown();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.BHttpConnectionBase
    protected InputStream getSocketInputStream(Socket socket) throws IOException {
        InputStream socketInputStream = super.getSocketInputStream(socket);
        return this.wire.enabled() ? new LoggingInputStream(socketInputStream, this.wire) : socketInputStream;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.BHttpConnectionBase
    protected OutputStream getSocketOutputStream(Socket socket) throws IOException {
        OutputStream socketOutputStream = super.getSocketOutputStream(socket);
        return this.wire.enabled() ? new LoggingOutputStream(socketOutputStream, this.wire) : socketOutputStream;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.DefaultBHttpClientConnection
    protected void onResponseReceived(HttpResponse httpResponse) {
        if (httpResponse == null || !this.headerlog.isDebugEnabled()) {
            return;
        }
        this.headerlog.debug(getId() + " << " + httpResponse.getStatusLine().toString());
        for (Header header : httpResponse.getAllHeaders()) {
            this.headerlog.debug(getId() + " << " + header.toString());
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.DefaultBHttpClientConnection
    protected void onRequestSubmitted(HttpRequest httpRequest) {
        if (httpRequest == null || !this.headerlog.isDebugEnabled()) {
            return;
        }
        this.headerlog.debug(getId() + " >> " + httpRequest.getRequestLine().toString());
        for (Header header : httpRequest.getAllHeaders()) {
            this.headerlog.debug(getId() + " >> " + header.toString());
        }
    }
}
