package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedHttpClientConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoute;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.AbstractConnPool;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.ConnFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.PoolEntryCallback;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes2.dex */
class CPool extends AbstractConnPool<HttpRoute, ManagedHttpClientConnection, CPoolEntry> {
    private static final AtomicLong COUNTER = new AtomicLong();
    private final Log log;
    private final long timeToLive;
    private final TimeUnit tunit;

    public CPool(ConnFactory<HttpRoute, ManagedHttpClientConnection> connFactory, int i, int i2, long j, TimeUnit timeUnit) {
        super(connFactory, i, i2);
        this.log = LogFactory.getLog(CPool.class);
        this.timeToLive = j;
        this.tunit = timeUnit;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.AbstractConnPool
    public CPoolEntry createEntry(HttpRoute httpRoute, ManagedHttpClientConnection managedHttpClientConnection) {
        return new CPoolEntry(this.log, Long.toString(COUNTER.getAndIncrement()), httpRoute, managedHttpClientConnection, this.timeToLive, this.tunit);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.AbstractConnPool
    public boolean validate(CPoolEntry cPoolEntry) {
        return !cPoolEntry.getConnection().isStale();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.AbstractConnPool
    protected void enumAvailable(PoolEntryCallback<HttpRoute, ManagedHttpClientConnection> poolEntryCallback) {
        super.enumAvailable(poolEntryCallback);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.AbstractConnPool
    protected void enumLeased(PoolEntryCallback<HttpRoute, ManagedHttpClientConnection> poolEntryCallback) {
        super.enumLeased(poolEntryCallback);
    }
}
