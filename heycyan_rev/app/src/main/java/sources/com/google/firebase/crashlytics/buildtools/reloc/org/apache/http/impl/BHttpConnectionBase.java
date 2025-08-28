package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ConnectionClosedException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.Header;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnectionMetrics;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpInetConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpMessage;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.config.MessageConstraints;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.BasicHttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.ContentLengthStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.entity.LaxContentLengthStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.entity.StrictContentLengthStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.ChunkedInputStream;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.ChunkedOutputStream;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.ContentLengthInputStream;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.ContentLengthOutputStream;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.EmptyInputStream;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.HttpTransportMetricsImpl;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.IdentityInputStream;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.IdentityOutputStream;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.SessionInputBufferImpl;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io.SessionOutputBufferImpl;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionInputBuffer;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.SessionOutputBuffer;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.NetUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
public class BHttpConnectionBase implements HttpConnection, HttpInetConnection {
    private final HttpConnectionMetricsImpl connMetrics;
    private final SessionInputBufferImpl inbuffer;
    private final ContentLengthStrategy incomingContentStrategy;
    private final MessageConstraints messageConstraints;
    private final SessionOutputBufferImpl outbuffer;
    private final ContentLengthStrategy outgoingContentStrategy;
    private final AtomicReference<Socket> socketHolder;

    protected BHttpConnectionBase(int i, int i2, CharsetDecoder charsetDecoder, CharsetEncoder charsetEncoder, MessageConstraints messageConstraints, ContentLengthStrategy contentLengthStrategy, ContentLengthStrategy contentLengthStrategy2) {
        Args.positive(i, "Buffer size");
        HttpTransportMetricsImpl httpTransportMetricsImpl = new HttpTransportMetricsImpl();
        HttpTransportMetricsImpl httpTransportMetricsImpl2 = new HttpTransportMetricsImpl();
        this.inbuffer = new SessionInputBufferImpl(httpTransportMetricsImpl, i, -1, messageConstraints != null ? messageConstraints : MessageConstraints.DEFAULT, charsetDecoder);
        this.outbuffer = new SessionOutputBufferImpl(httpTransportMetricsImpl2, i, i2, charsetEncoder);
        this.messageConstraints = messageConstraints;
        this.connMetrics = new HttpConnectionMetricsImpl(httpTransportMetricsImpl, httpTransportMetricsImpl2);
        this.incomingContentStrategy = contentLengthStrategy != null ? contentLengthStrategy : LaxContentLengthStrategy.INSTANCE;
        this.outgoingContentStrategy = contentLengthStrategy2 != null ? contentLengthStrategy2 : StrictContentLengthStrategy.INSTANCE;
        this.socketHolder = new AtomicReference<>();
    }

    protected void ensureOpen() throws IOException {
        Socket socket = this.socketHolder.get();
        if (socket == null) {
            throw new ConnectionClosedException("Connection is closed");
        }
        if (!this.inbuffer.isBound()) {
            this.inbuffer.bind(getSocketInputStream(socket));
        }
        if (this.outbuffer.isBound()) {
            return;
        }
        this.outbuffer.bind(getSocketOutputStream(socket));
    }

    protected InputStream getSocketInputStream(Socket socket) throws IOException {
        return socket.getInputStream();
    }

    protected OutputStream getSocketOutputStream(Socket socket) throws IOException {
        return socket.getOutputStream();
    }

    protected void bind(Socket socket) throws IOException {
        Args.notNull(socket, "Socket");
        this.socketHolder.set(socket);
        this.inbuffer.bind(null);
        this.outbuffer.bind(null);
    }

    protected SessionInputBuffer getSessionInputBuffer() {
        return this.inbuffer;
    }

    protected SessionOutputBuffer getSessionOutputBuffer() {
        return this.outbuffer;
    }

    protected void doFlush() throws IOException {
        this.outbuffer.flush();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public boolean isOpen() {
        return this.socketHolder.get() != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Socket getSocket() {
        return this.socketHolder.get();
    }

    protected OutputStream createOutputStream(long j, SessionOutputBuffer sessionOutputBuffer) {
        if (j == -2) {
            return new ChunkedOutputStream(2048, sessionOutputBuffer);
        }
        if (j == -1) {
            return new IdentityOutputStream(sessionOutputBuffer);
        }
        return new ContentLengthOutputStream(sessionOutputBuffer, j);
    }

    protected OutputStream prepareOutput(HttpMessage httpMessage) throws HttpException {
        return createOutputStream(this.outgoingContentStrategy.determineLength(httpMessage), this.outbuffer);
    }

    protected InputStream createInputStream(long j, SessionInputBuffer sessionInputBuffer) {
        if (j == -2) {
            return new ChunkedInputStream(sessionInputBuffer, this.messageConstraints);
        }
        if (j == -1) {
            return new IdentityInputStream(sessionInputBuffer);
        }
        if (j == 0) {
            return EmptyInputStream.INSTANCE;
        }
        return new ContentLengthInputStream(sessionInputBuffer, j);
    }

    protected HttpEntity prepareInput(HttpMessage httpMessage) throws HttpException {
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        long jDetermineLength = this.incomingContentStrategy.determineLength(httpMessage);
        InputStream inputStreamCreateInputStream = createInputStream(jDetermineLength, this.inbuffer);
        if (jDetermineLength == -2) {
            basicHttpEntity.setChunked(true);
            basicHttpEntity.setContentLength(-1L);
            basicHttpEntity.setContent(inputStreamCreateInputStream);
        } else if (jDetermineLength == -1) {
            basicHttpEntity.setChunked(false);
            basicHttpEntity.setContentLength(-1L);
            basicHttpEntity.setContent(inputStreamCreateInputStream);
        } else {
            basicHttpEntity.setChunked(false);
            basicHttpEntity.setContentLength(jDetermineLength);
            basicHttpEntity.setContent(inputStreamCreateInputStream);
        }
        Header firstHeader = httpMessage.getFirstHeader("Content-Type");
        if (firstHeader != null) {
            basicHttpEntity.setContentType(firstHeader);
        }
        Header firstHeader2 = httpMessage.getFirstHeader("Content-Encoding");
        if (firstHeader2 != null) {
            basicHttpEntity.setContentEncoding(firstHeader2);
        }
        return basicHttpEntity;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpInetConnection
    public InetAddress getLocalAddress() {
        Socket socket = this.socketHolder.get();
        if (socket != null) {
            return socket.getLocalAddress();
        }
        return null;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpInetConnection
    public int getLocalPort() {
        Socket socket = this.socketHolder.get();
        if (socket != null) {
            return socket.getLocalPort();
        }
        return -1;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpInetConnection
    public InetAddress getRemoteAddress() {
        Socket socket = this.socketHolder.get();
        if (socket != null) {
            return socket.getInetAddress();
        }
        return null;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpInetConnection
    public int getRemotePort() {
        Socket socket = this.socketHolder.get();
        if (socket != null) {
            return socket.getPort();
        }
        return -1;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public void setSocketTimeout(int i) throws SocketException {
        Socket socket = this.socketHolder.get();
        if (socket != null) {
            try {
                socket.setSoTimeout(i);
            } catch (SocketException unused) {
            }
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public int getSocketTimeout() {
        Socket socket = this.socketHolder.get();
        if (socket != null) {
            try {
                return socket.getSoTimeout();
            } catch (SocketException unused) {
            }
        }
        return -1;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public void shutdown() throws IOException {
        Socket andSet = this.socketHolder.getAndSet(null);
        if (andSet != null) {
            try {
                andSet.setSoLinger(true, 0);
            } catch (IOException unused) {
            } catch (Throwable th) {
                andSet.close();
                throw th;
            }
            andSet.close();
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Socket andSet = this.socketHolder.getAndSet(null);
        if (andSet != null) {
            try {
                this.inbuffer.clear();
                this.outbuffer.flush();
                try {
                    try {
                        andSet.shutdownOutput();
                    } catch (IOException unused) {
                    }
                    andSet.shutdownInput();
                } catch (IOException | UnsupportedOperationException unused2) {
                }
            } finally {
                andSet.close();
            }
        }
    }

    private int fillInputBuffer(int i) throws IOException {
        Socket socket = this.socketHolder.get();
        int soTimeout = socket.getSoTimeout();
        try {
            socket.setSoTimeout(i);
            return this.inbuffer.fillBuffer();
        } finally {
            socket.setSoTimeout(soTimeout);
        }
    }

    protected boolean awaitInput(int i) throws IOException {
        if (this.inbuffer.hasBufferedData()) {
            return true;
        }
        fillInputBuffer(i);
        return this.inbuffer.hasBufferedData();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public boolean isStale() {
        if (!isOpen()) {
            return true;
        }
        try {
            return fillInputBuffer(1) < 0;
        } catch (SocketTimeoutException unused) {
            return false;
        } catch (IOException unused2) {
            return true;
        }
    }

    protected void incrementRequestCount() {
        this.connMetrics.incrementRequestCount();
    }

    protected void incrementResponseCount() {
        this.connMetrics.incrementResponseCount();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public HttpConnectionMetrics getMetrics() {
        return this.connMetrics;
    }

    public String toString() {
        Socket socket = this.socketHolder.get();
        if (socket != null) {
            StringBuilder sb = new StringBuilder();
            SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
            SocketAddress localSocketAddress = socket.getLocalSocketAddress();
            if (remoteSocketAddress != null && localSocketAddress != null) {
                NetUtils.formatAddress(sb, localSocketAddress);
                sb.append("<->");
                NetUtils.formatAddress(sb, remoteSocketAddress);
            }
            return sb.toString();
        }
        return "[Not bound]";
    }
}
