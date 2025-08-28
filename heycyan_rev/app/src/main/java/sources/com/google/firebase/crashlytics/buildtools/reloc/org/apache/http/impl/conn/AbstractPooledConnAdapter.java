package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.OperatedClientConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoute;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import java.io.IOException;

@Deprecated
/* loaded from: classes2.dex */
public abstract class AbstractPooledConnAdapter extends AbstractClientConnAdapter {
    protected volatile AbstractPoolEntry poolEntry;

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedHttpClientConnection
    public String getId() {
        return null;
    }

    protected AbstractPooledConnAdapter(ClientConnectionManager clientConnectionManager, AbstractPoolEntry abstractPoolEntry) {
        super(clientConnectionManager, abstractPoolEntry.connection);
        this.poolEntry = abstractPoolEntry;
    }

    @Deprecated
    protected AbstractPoolEntry getPoolEntry() {
        return this.poolEntry;
    }

    protected void assertValid(AbstractPoolEntry abstractPoolEntry) {
        if (isReleased() || abstractPoolEntry == null) {
            throw new ConnectionShutdownException();
        }
    }

    @Deprecated
    protected final void assertAttached() {
        if (this.poolEntry == null) {
            throw new ConnectionShutdownException();
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.AbstractClientConnAdapter
    protected synchronized void detach() {
        this.poolEntry = null;
        super.detach();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.HttpRoutedConnection
    public HttpRoute getRoute() {
        AbstractPoolEntry poolEntry = getPoolEntry();
        assertValid(poolEntry);
        if (poolEntry.tracker == null) {
            return null;
        }
        return poolEntry.tracker.toRoute();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public void open(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams) throws IOException {
        AbstractPoolEntry poolEntry = getPoolEntry();
        assertValid(poolEntry);
        poolEntry.open(httpRoute, httpContext, httpParams);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public void tunnelTarget(boolean z, HttpParams httpParams) throws IOException {
        AbstractPoolEntry poolEntry = getPoolEntry();
        assertValid(poolEntry);
        poolEntry.tunnelTarget(z, httpParams);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public void tunnelProxy(HttpHost httpHost, boolean z, HttpParams httpParams) throws IOException {
        AbstractPoolEntry poolEntry = getPoolEntry();
        assertValid(poolEntry);
        poolEntry.tunnelProxy(httpHost, z, httpParams);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public void layerProtocol(HttpContext httpContext, HttpParams httpParams) throws IOException {
        AbstractPoolEntry poolEntry = getPoolEntry();
        assertValid(poolEntry);
        poolEntry.layerProtocol(httpContext, httpParams);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        AbstractPoolEntry poolEntry = getPoolEntry();
        if (poolEntry != null) {
            poolEntry.shutdownEntry();
        }
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        if (wrappedConnection != null) {
            wrappedConnection.close();
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection
    public void shutdown() throws IOException {
        AbstractPoolEntry poolEntry = getPoolEntry();
        if (poolEntry != null) {
            poolEntry.shutdownEntry();
        }
        OperatedClientConnection wrappedConnection = getWrappedConnection();
        if (wrappedConnection != null) {
            wrappedConnection.shutdown();
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public Object getState() {
        AbstractPoolEntry poolEntry = getPoolEntry();
        assertValid(poolEntry);
        return poolEntry.getState();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection
    public void setState(Object obj) {
        AbstractPoolEntry poolEntry = getPoolEntry();
        assertValid(poolEntry);
        poolEntry.setState(obj);
    }
}
