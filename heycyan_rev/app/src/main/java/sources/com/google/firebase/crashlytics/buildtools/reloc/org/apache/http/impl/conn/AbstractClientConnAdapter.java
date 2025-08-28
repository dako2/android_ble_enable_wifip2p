package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnectionMetrics;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntityEnclosingRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.OperatedClientConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

@Deprecated
/* loaded from: classes2.dex */
public abstract class AbstractClientConnAdapter implements ManagedClientConnection, HttpContext {
    private final ClientConnectionManager connManager;
    private volatile OperatedClientConnection wrappedConnection;
    private volatile boolean markedReusable = false;
    private volatile boolean released = false;
    private volatile long duration = Long.MAX_VALUE;

    protected AbstractClientConnAdapter(ClientConnectionManager clientConnectionManager, OperatedClientConnection operatedClientConnection) {
        this.connManager = clientConnectionManager;
        this.wrappedConnection = operatedClientConnection;
    }

    protected synchronized void detach() {
        this.wrappedConnection = null;
        this.duration = Long.MAX_VALUE;
    }

    protected OperatedClientConnection getWrappedConnection() {
        return this.wrappedConnection;
    }

    protected ClientConnectionManager getManager() {
        return this.connManager;
    }

    @Deprecated
    protected final void assertNotAborted() throws InterruptedIOException {
        if (isReleased()) {
            throw new InterruptedIOException("Connection has been shut down");
        }
    }

    protected boolean isReleased() {
        return this.released;
    }

    protected final void assertValid(OperatedClientConnection operatedClientConnection) throws ConnectionShutdownException {
        if (isReleased() || operatedClientConnection == null) {
            throw new ConnectionShutdownException();
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public boolean isOpen() {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        if (wrappedConnection == null) {
            return false;
        }
        return wrappedConnection.isOpen();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public boolean isStale() {
        OperatedClientConnection wrappedConnection;
        if (isReleased() || (wrappedConnection = getWrappedConnection()) == null) {
            return true;
        }
        return wrappedConnection.isStale();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public void setSocketTimeout(int i) throws ConnectionShutdownException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        wrappedConnection.setSocketTimeout(i);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public int getSocketTimeout() throws ConnectionShutdownException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        return wrappedConnection.getSocketTimeout();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public HttpConnectionMetrics getMetrics() throws ConnectionShutdownException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        return wrappedConnection.getMetrics();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection
    public void flush() throws ConnectionShutdownException, IOException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        wrappedConnection.flush();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection
    public boolean isResponseAvailable(int i) throws ConnectionShutdownException, IOException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        return wrappedConnection.isResponseAvailable(i);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection
    public void receiveResponseEntity(HttpResponse httpResponse) throws HttpException, ConnectionShutdownException, IOException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        unmarkReusable();
        wrappedConnection.receiveResponseEntity(httpResponse);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection
    public HttpResponse receiveResponseHeader() throws HttpException, ConnectionShutdownException, IOException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        unmarkReusable();
        return wrappedConnection.receiveResponseHeader();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection
    public void sendRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) throws HttpException, ConnectionShutdownException, IOException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        unmarkReusable();
        wrappedConnection.sendRequestEntity(httpEntityEnclosingRequest);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection
    public void sendRequestHeader(HttpRequest httpRequest) throws HttpException, ConnectionShutdownException, IOException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        unmarkReusable();
        wrappedConnection.sendRequestHeader(httpRequest);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpInetConnection
    public InetAddress getLocalAddress() throws ConnectionShutdownException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        return wrappedConnection.getLocalAddress();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpInetConnection
    public int getLocalPort() throws ConnectionShutdownException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        return wrappedConnection.getLocalPort();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpInetConnection
    public InetAddress getRemoteAddress() throws ConnectionShutdownException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        return wrappedConnection.getRemoteAddress();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpInetConnection
    public int getRemotePort() throws ConnectionShutdownException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        return wrappedConnection.getRemotePort();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.HttpRoutedConnection
    public boolean isSecure() throws ConnectionShutdownException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        return wrappedConnection.isSecure();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedHttpClientConnection
    public void bind(Socket socket) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedHttpClientConnection
    public Socket getSocket() throws ConnectionShutdownException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        if (isOpen()) {
            return wrappedConnection.getSocket();
        }
        return null;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.HttpRoutedConnection, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedHttpClientConnection
    public SSLSession getSSLSession() throws ConnectionShutdownException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        if (!isOpen()) {
            return null;
        }
        Socket socket = wrappedConnection.getSocket();
        if (socket instanceof SSLSocket) {
            return ((SSLSocket) socket).getSession();
        }
        return null;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public void markReusable() {
        this.markedReusable = true;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public void unmarkReusable() {
        this.markedReusable = false;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public boolean isMarkedReusable() {
        return this.markedReusable;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public void setIdleDuration(long j, TimeUnit timeUnit) {
        if (j > 0) {
            this.duration = timeUnit.toMillis(j);
        } else {
            this.duration = -1L;
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ConnectionReleaseTrigger
    public synchronized void releaseConnection() {
        if (this.released) {
            return;
        }
        this.released = true;
        this.connManager.releaseConnection(this, this.duration, TimeUnit.MILLISECONDS);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ConnectionReleaseTrigger
    public synchronized void abortConnection() {
        if (this.released) {
            return;
        }
        this.released = true;
        unmarkReusable();
        try {
            shutdown();
        } catch (IOException unused) {
        }
        this.connManager.releaseConnection(this, this.duration, TimeUnit.MILLISECONDS);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext
    public Object getAttribute(String str) throws ConnectionShutdownException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        if (wrappedConnection instanceof HttpContext) {
            return ((HttpContext) wrappedConnection).getAttribute(str);
        }
        return null;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext
    public Object removeAttribute(String str) throws ConnectionShutdownException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        if (wrappedConnection instanceof HttpContext) {
            return ((HttpContext) wrappedConnection).removeAttribute(str);
        }
        return null;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext
    public void setAttribute(String str, Object obj) throws ConnectionShutdownException {
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        assertValid(wrappedConnection);
        if (wrappedConnection instanceof HttpContext) {
            ((HttpContext) wrappedConnection).setAttribute(str, obj);
        }
    }
}
