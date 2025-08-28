package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnectionMetrics;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntityEnclosingRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionOperator;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.OperatedClientConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoute;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.RouteTracker;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Asserts;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

@Deprecated
/* loaded from: classes2.dex */
class ManagedClientConnectionImpl implements ManagedClientConnection {
    private volatile long duration;
    private final ClientConnectionManager manager;
    private final ClientConnectionOperator operator;
    private volatile HttpPoolEntry poolEntry;
    private volatile boolean reusable;

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedHttpClientConnection
    public String getId() {
        return null;
    }

    ManagedClientConnectionImpl(ClientConnectionManager clientConnectionManager, ClientConnectionOperator clientConnectionOperator, HttpPoolEntry httpPoolEntry) {
        Args.notNull(clientConnectionManager, "Connection manager");
        Args.notNull(clientConnectionOperator, "Connection operator");
        Args.notNull(httpPoolEntry, "HTTP pool entry");
        this.manager = clientConnectionManager;
        this.operator = clientConnectionOperator;
        this.poolEntry = httpPoolEntry;
        this.reusable = false;
        this.duration = Long.MAX_VALUE;
    }

    HttpPoolEntry getPoolEntry() {
        return this.poolEntry;
    }

    HttpPoolEntry detach() {
        HttpPoolEntry httpPoolEntry = this.poolEntry;
        this.poolEntry = null;
        return httpPoolEntry;
    }

    public ClientConnectionManager getManager() {
        return this.manager;
    }

    private OperatedClientConnection getConnection() {
        HttpPoolEntry httpPoolEntry = this.poolEntry;
        if (httpPoolEntry == null) {
            return null;
        }
        return httpPoolEntry.getConnection();
    }

    private OperatedClientConnection ensureConnection() {
        HttpPoolEntry httpPoolEntry = this.poolEntry;
        if (httpPoolEntry == null) {
            throw new ConnectionShutdownException();
        }
        return httpPoolEntry.getConnection();
    }

    private HttpPoolEntry ensurePoolEntry() {
        HttpPoolEntry httpPoolEntry = this.poolEntry;
        if (httpPoolEntry != null) {
            return httpPoolEntry;
        }
        throw new ConnectionShutdownException();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        HttpPoolEntry httpPoolEntry = this.poolEntry;
        if (httpPoolEntry != null) {
            OperatedClientConnection connection = httpPoolEntry.getConnection();
            httpPoolEntry.getTracker().reset();
            connection.close();
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public void shutdown() throws IOException {
        HttpPoolEntry httpPoolEntry = this.poolEntry;
        if (httpPoolEntry != null) {
            OperatedClientConnection connection = httpPoolEntry.getConnection();
            httpPoolEntry.getTracker().reset();
            connection.shutdown();
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public boolean isOpen() {
        OperatedClientConnection connection = getConnection();
        if (connection != null) {
            return connection.isOpen();
        }
        return false;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public boolean isStale() {
        OperatedClientConnection connection = getConnection();
        if (connection != null) {
            return connection.isStale();
        }
        return true;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public void setSocketTimeout(int i) {
        ensureConnection().setSocketTimeout(i);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public int getSocketTimeout() {
        return ensureConnection().getSocketTimeout();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public HttpConnectionMetrics getMetrics() {
        return ensureConnection().getMetrics();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection
    public void flush() throws IOException {
        ensureConnection().flush();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection
    public boolean isResponseAvailable(int i) throws IOException {
        return ensureConnection().isResponseAvailable(i);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection
    public void receiveResponseEntity(HttpResponse httpResponse) throws HttpException, IOException {
        ensureConnection().receiveResponseEntity(httpResponse);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection
    public HttpResponse receiveResponseHeader() throws HttpException, IOException {
        return ensureConnection().receiveResponseHeader();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection
    public void sendRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) throws HttpException, IOException {
        ensureConnection().sendRequestEntity(httpEntityEnclosingRequest);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection
    public void sendRequestHeader(HttpRequest httpRequest) throws HttpException, IOException {
        ensureConnection().sendRequestHeader(httpRequest);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpInetConnection
    public InetAddress getLocalAddress() {
        return ensureConnection().getLocalAddress();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpInetConnection
    public int getLocalPort() {
        return ensureConnection().getLocalPort();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpInetConnection
    public InetAddress getRemoteAddress() {
        return ensureConnection().getRemoteAddress();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpInetConnection
    public int getRemotePort() {
        return ensureConnection().getRemotePort();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.HttpRoutedConnection
    public boolean isSecure() {
        return ensureConnection().isSecure();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedHttpClientConnection
    public void bind(Socket socket) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedHttpClientConnection
    public Socket getSocket() {
        return ensureConnection().getSocket();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.HttpRoutedConnection, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedHttpClientConnection
    public SSLSession getSSLSession() {
        Socket socket = ensureConnection().getSocket();
        if (socket instanceof SSLSocket) {
            return ((SSLSocket) socket).getSession();
        }
        return null;
    }

    public Object getAttribute(String str) {
        OperatedClientConnection operatedClientConnectionEnsureConnection = ensureConnection();
        if (operatedClientConnectionEnsureConnection instanceof HttpContext) {
            return ((HttpContext) operatedClientConnectionEnsureConnection).getAttribute(str);
        }
        return null;
    }

    public Object removeAttribute(String str) {
        OperatedClientConnection operatedClientConnectionEnsureConnection = ensureConnection();
        if (operatedClientConnectionEnsureConnection instanceof HttpContext) {
            return ((HttpContext) operatedClientConnectionEnsureConnection).removeAttribute(str);
        }
        return null;
    }

    public void setAttribute(String str, Object obj) {
        OperatedClientConnection operatedClientConnectionEnsureConnection = ensureConnection();
        if (operatedClientConnectionEnsureConnection instanceof HttpContext) {
            ((HttpContext) operatedClientConnectionEnsureConnection).setAttribute(str, obj);
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.HttpRoutedConnection
    public HttpRoute getRoute() {
        return ensurePoolEntry().getEffectiveRoute();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public void open(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams) throws IOException {
        OperatedClientConnection connection;
        Args.notNull(httpRoute, "Route");
        Args.notNull(httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.poolEntry == null) {
                throw new ConnectionShutdownException();
            }
            Asserts.notNull(this.poolEntry.getTracker(), "Route tracker");
            Asserts.check(!r0.isConnected(), "Connection already open");
            connection = this.poolEntry.getConnection();
        }
        HttpHost proxyHost = httpRoute.getProxyHost();
        this.operator.openConnection(connection, proxyHost != null ? proxyHost : httpRoute.getTargetHost(), httpRoute.getLocalAddress(), httpContext, httpParams);
        synchronized (this) {
            if (this.poolEntry == null) {
                throw new InterruptedIOException();
            }
            RouteTracker tracker = this.poolEntry.getTracker();
            if (proxyHost == null) {
                tracker.connectTarget(connection.isSecure());
            } else {
                tracker.connectProxy(proxyHost, connection.isSecure());
            }
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public void tunnelTarget(boolean z, HttpParams httpParams) throws IOException {
        HttpHost targetHost;
        OperatedClientConnection connection;
        Args.notNull(httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.poolEntry == null) {
                throw new ConnectionShutdownException();
            }
            RouteTracker tracker = this.poolEntry.getTracker();
            Asserts.notNull(tracker, "Route tracker");
            Asserts.check(tracker.isConnected(), "Connection not open");
            Asserts.check(!tracker.isTunnelled(), "Connection is already tunnelled");
            targetHost = tracker.getTargetHost();
            connection = this.poolEntry.getConnection();
        }
        connection.update(null, targetHost, z, httpParams);
        synchronized (this) {
            if (this.poolEntry == null) {
                throw new InterruptedIOException();
            }
            this.poolEntry.getTracker().tunnelTarget(z);
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public void tunnelProxy(HttpHost httpHost, boolean z, HttpParams httpParams) throws IOException {
        OperatedClientConnection connection;
        Args.notNull(httpHost, "Next proxy");
        Args.notNull(httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.poolEntry == null) {
                throw new ConnectionShutdownException();
            }
            RouteTracker tracker = this.poolEntry.getTracker();
            Asserts.notNull(tracker, "Route tracker");
            Asserts.check(tracker.isConnected(), "Connection not open");
            connection = this.poolEntry.getConnection();
        }
        connection.update(null, httpHost, z, httpParams);
        synchronized (this) {
            if (this.poolEntry == null) {
                throw new InterruptedIOException();
            }
            this.poolEntry.getTracker().tunnelProxy(httpHost, z);
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public void layerProtocol(HttpContext httpContext, HttpParams httpParams) throws IOException {
        HttpHost targetHost;
        OperatedClientConnection connection;
        Args.notNull(httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.poolEntry == null) {
                throw new ConnectionShutdownException();
            }
            RouteTracker tracker = this.poolEntry.getTracker();
            Asserts.notNull(tracker, "Route tracker");
            Asserts.check(tracker.isConnected(), "Connection not open");
            Asserts.check(tracker.isTunnelled(), "Protocol layering without a tunnel not supported");
            Asserts.check(!tracker.isLayered(), "Multiple protocol layering not supported");
            targetHost = tracker.getTargetHost();
            connection = this.poolEntry.getConnection();
        }
        this.operator.updateSecureConnection(connection, targetHost, httpContext, httpParams);
        synchronized (this) {
            if (this.poolEntry == null) {
                throw new InterruptedIOException();
            }
            this.poolEntry.getTracker().layerProtocol(connection.isSecure());
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public Object getState() {
        return ensurePoolEntry().getState();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public void setState(Object obj) {
        ensurePoolEntry().setState(obj);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public void markReusable() {
        this.reusable = true;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public void unmarkReusable() {
        this.reusable = false;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public boolean isMarkedReusable() {
        return this.reusable;
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
    public void releaseConnection() {
        synchronized (this) {
            if (this.poolEntry == null) {
                return;
            }
            this.manager.releaseConnection(this, this.duration, TimeUnit.MILLISECONDS);
            this.poolEntry = null;
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ConnectionReleaseTrigger
    public void abortConnection() {
        synchronized (this) {
            if (this.poolEntry == null) {
                return;
            }
            this.reusable = false;
            try {
                this.poolEntry.getConnection().shutdown();
            } catch (IOException unused) {
            }
            this.manager.releaseConnection(this, this.duration, TimeUnit.MILLISECONDS);
            this.poolEntry = null;
        }
    }
}
