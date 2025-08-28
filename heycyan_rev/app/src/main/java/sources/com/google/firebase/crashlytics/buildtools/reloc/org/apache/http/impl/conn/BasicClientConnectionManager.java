package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionOperator;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoute;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.scheme.SchemeRegistry;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Asserts;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Deprecated
/* loaded from: classes2.dex */
public class BasicClientConnectionManager implements ClientConnectionManager {
    private static final AtomicLong COUNTER = new AtomicLong();
    public static final String MISUSE_MESSAGE = "Invalid use of BasicClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.";
    private ManagedClientConnectionImpl conn;
    private final ClientConnectionOperator connOperator;
    private final Log log;
    private HttpPoolEntry poolEntry;
    private final SchemeRegistry schemeRegistry;
    private volatile boolean shutdown;

    public BasicClientConnectionManager(SchemeRegistry schemeRegistry) {
        this.log = LogFactory.getLog(getClass());
        Args.notNull(schemeRegistry, "Scheme registry");
        this.schemeRegistry = schemeRegistry;
        this.connOperator = createConnectionOperator(schemeRegistry);
    }

    public BasicClientConnectionManager() {
        this(SchemeRegistryFactory.createDefault());
    }

    protected void finalize() throws Throwable {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager
    public SchemeRegistry getSchemeRegistry() {
        return this.schemeRegistry;
    }

    protected ClientConnectionOperator createConnectionOperator(SchemeRegistry schemeRegistry) {
        return new DefaultClientConnectionOperator(schemeRegistry);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager
    public final ClientConnectionRequest requestConnection(final HttpRoute httpRoute, final Object obj) {
        return new ClientConnectionRequest() { // from class: com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.BasicClientConnectionManager.1
            @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionRequest
            public void abortRequest() {
            }

            @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionRequest
            public ManagedClientConnection getConnection(long j, TimeUnit timeUnit) {
                return BasicClientConnectionManager.this.getConnection(httpRoute, obj);
            }
        };
    }

    private void assertNotShutdown() {
        Asserts.check(!this.shutdown, "Connection manager has been shut down");
    }

    ManagedClientConnection getConnection(HttpRoute httpRoute, Object obj) {
        ManagedClientConnectionImpl managedClientConnectionImpl;
        Args.notNull(httpRoute, "Route");
        synchronized (this) {
            assertNotShutdown();
            if (this.log.isDebugEnabled()) {
                this.log.debug("Get connection for route " + httpRoute);
            }
            Asserts.check(this.conn == null, MISUSE_MESSAGE);
            HttpPoolEntry httpPoolEntry = this.poolEntry;
            if (httpPoolEntry != null && !httpPoolEntry.getPlannedRoute().equals(httpRoute)) {
                this.poolEntry.close();
                this.poolEntry = null;
            }
            if (this.poolEntry == null) {
                this.poolEntry = new HttpPoolEntry(this.log, Long.toString(COUNTER.getAndIncrement()), httpRoute, this.connOperator.createConnection(), 0L, TimeUnit.MILLISECONDS);
            }
            if (this.poolEntry.isExpired(System.currentTimeMillis())) {
                this.poolEntry.close();
                this.poolEntry.getTracker().reset();
            }
            managedClientConnectionImpl = new ManagedClientConnectionImpl(this, this.connOperator, this.poolEntry);
            this.conn = managedClientConnectionImpl;
        }
        return managedClientConnectionImpl;
    }

    private void shutdownConnection(HttpClientConnection httpClientConnection) {
        try {
            httpClientConnection.shutdown();
        } catch (IOException e) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("I/O exception shutting down connection", e);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager
    public void releaseConnection(ManagedClientConnection managedClientConnection, long j, TimeUnit timeUnit) {
        String str;
        Args.check(managedClientConnection instanceof ManagedClientConnectionImpl, "Connection class mismatch, connection not obtained from this manager");
        ManagedClientConnectionImpl managedClientConnectionImpl = (ManagedClientConnectionImpl) managedClientConnection;
        synchronized (managedClientConnectionImpl) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("Releasing connection " + managedClientConnection);
            }
            if (managedClientConnectionImpl.getPoolEntry() == null) {
                return;
            }
            Asserts.check(managedClientConnectionImpl.getManager() == this, "Connection not obtained from this manager");
            synchronized (this) {
                if (this.shutdown) {
                    shutdownConnection(managedClientConnectionImpl);
                    return;
                }
                try {
                    if (managedClientConnectionImpl.isOpen() && !managedClientConnectionImpl.isMarkedReusable()) {
                        shutdownConnection(managedClientConnectionImpl);
                    }
                    if (managedClientConnectionImpl.isMarkedReusable()) {
                        this.poolEntry.updateExpiry(j, timeUnit != null ? timeUnit : TimeUnit.MILLISECONDS);
                        if (this.log.isDebugEnabled()) {
                            if (j > 0) {
                                str = "for " + j + HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR + timeUnit;
                            } else {
                                str = "indefinitely";
                            }
                            this.log.debug("Connection can be kept alive " + str);
                        }
                    }
                } finally {
                    managedClientConnectionImpl.detach();
                    this.conn = null;
                    if (this.poolEntry.isClosed()) {
                        this.poolEntry = null;
                    }
                }
            }
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager
    public void closeExpiredConnections() {
        synchronized (this) {
            assertNotShutdown();
            long jCurrentTimeMillis = System.currentTimeMillis();
            HttpPoolEntry httpPoolEntry = this.poolEntry;
            if (httpPoolEntry != null && httpPoolEntry.isExpired(jCurrentTimeMillis)) {
                this.poolEntry.close();
                this.poolEntry.getTracker().reset();
            }
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager
    public void closeIdleConnections(long j, TimeUnit timeUnit) {
        Args.notNull(timeUnit, "Time unit");
        synchronized (this) {
            assertNotShutdown();
            long millis = timeUnit.toMillis(j);
            if (millis < 0) {
                millis = 0;
            }
            long jCurrentTimeMillis = System.currentTimeMillis() - millis;
            HttpPoolEntry httpPoolEntry = this.poolEntry;
            if (httpPoolEntry != null && httpPoolEntry.getUpdated() <= jCurrentTimeMillis) {
                this.poolEntry.close();
                this.poolEntry.getTracker().reset();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager
    public void shutdown() {
        synchronized (this) {
            this.shutdown = true;
            try {
                HttpPoolEntry httpPoolEntry = this.poolEntry;
                if (httpPoolEntry != null) {
                    httpPoolEntry.close();
                }
            } finally {
                this.poolEntry = null;
                this.conn = null;
            }
        }
    }
}
