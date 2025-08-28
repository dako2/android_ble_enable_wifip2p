package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedHttpClientConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoute;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.PoolEntry;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
class CPoolEntry extends PoolEntry<HttpRoute, ManagedHttpClientConnection> {
    private final Log log;
    private volatile boolean routeComplete;

    public CPoolEntry(Log log, String str, HttpRoute httpRoute, ManagedHttpClientConnection managedHttpClientConnection, long j, TimeUnit timeUnit) {
        super(str, httpRoute, managedHttpClientConnection, j, timeUnit);
        this.log = log;
    }

    public void markRouteComplete() {
        this.routeComplete = true;
    }

    public boolean isRouteComplete() {
        return this.routeComplete;
    }

    public void closeConnection() throws IOException {
        getConnection().close();
    }

    public void shutdownConnection() throws IOException {
        getConnection().shutdown();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.PoolEntry
    public boolean isExpired(long j) {
        boolean zIsExpired = super.isExpired(j);
        if (zIsExpired && this.log.isDebugEnabled()) {
            this.log.debug("Connection " + this + " expired @ " + new Date(getExpiry()));
        }
        return zIsExpired;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.PoolEntry
    public boolean isClosed() {
        return !getConnection().isOpen();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.PoolEntry
    public void close() {
        try {
            closeConnection();
        } catch (IOException e) {
            this.log.debug("I/O error closing connection", e);
        }
    }
}
