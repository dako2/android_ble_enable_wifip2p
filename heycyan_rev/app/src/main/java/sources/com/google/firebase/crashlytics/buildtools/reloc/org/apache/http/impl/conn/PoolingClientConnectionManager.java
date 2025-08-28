package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogConfigurationException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionOperator;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ConnectionPoolTimeoutException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.DnsResolver;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedClientConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoute;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.scheme.SchemeRegistry;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.ConnPoolControl;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.PoolStats;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Asserts;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Deprecated
/* loaded from: classes2.dex */
public class PoolingClientConnectionManager implements ClientConnectionManager, ConnPoolControl<HttpRoute> {
    private final DnsResolver dnsResolver;
    private final Log log;
    private final ClientConnectionOperator operator;
    private final HttpConnPool pool;
    private final SchemeRegistry schemeRegistry;

    public PoolingClientConnectionManager(SchemeRegistry schemeRegistry) {
        this(schemeRegistry, -1L, TimeUnit.MILLISECONDS);
    }

    public PoolingClientConnectionManager(SchemeRegistry schemeRegistry, DnsResolver dnsResolver) {
        this(schemeRegistry, -1L, TimeUnit.MILLISECONDS, dnsResolver);
    }

    public PoolingClientConnectionManager() {
        this(SchemeRegistryFactory.createDefault());
    }

    public PoolingClientConnectionManager(SchemeRegistry schemeRegistry, long j, TimeUnit timeUnit) {
        this(schemeRegistry, j, timeUnit, new SystemDefaultDnsResolver());
    }

    public PoolingClientConnectionManager(SchemeRegistry schemeRegistry, long j, TimeUnit timeUnit, DnsResolver dnsResolver) throws LogConfigurationException {
        Log log = LogFactory.getLog(getClass());
        this.log = log;
        Args.notNull(schemeRegistry, "Scheme registry");
        Args.notNull(dnsResolver, "DNS resolver");
        this.schemeRegistry = schemeRegistry;
        this.dnsResolver = dnsResolver;
        ClientConnectionOperator clientConnectionOperatorCreateConnectionOperator = createConnectionOperator(schemeRegistry);
        this.operator = clientConnectionOperatorCreateConnectionOperator;
        this.pool = new HttpConnPool(log, clientConnectionOperatorCreateConnectionOperator, 2, 20, j, timeUnit);
    }

    protected void finalize() throws Throwable {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }

    protected ClientConnectionOperator createConnectionOperator(SchemeRegistry schemeRegistry) {
        return new DefaultClientConnectionOperator(schemeRegistry, this.dnsResolver);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager
    public SchemeRegistry getSchemeRegistry() {
        return this.schemeRegistry;
    }

    private String format(HttpRoute httpRoute, Object obj) {
        StringBuilder sb = new StringBuilder("[route: ");
        sb.append(httpRoute).append("]");
        if (obj != null) {
            sb.append("[state: ").append(obj).append("]");
        }
        return sb.toString();
    }

    private String formatStats(HttpRoute httpRoute) {
        StringBuilder sb = new StringBuilder("[total kept alive: ");
        PoolStats totalStats = this.pool.getTotalStats();
        PoolStats stats = this.pool.getStats(httpRoute);
        sb.append(totalStats.getAvailable()).append("; route allocated: ");
        sb.append(stats.getLeased() + stats.getAvailable());
        sb.append(" of ").append(stats.getMax()).append("; total allocated: ");
        sb.append(totalStats.getLeased() + totalStats.getAvailable());
        sb.append(" of ").append(totalStats.getMax()).append("]");
        return sb.toString();
    }

    private String format(HttpPoolEntry httpPoolEntry) {
        StringBuilder sb = new StringBuilder("[id: ");
        sb.append(httpPoolEntry.getId()).append("][route: ");
        sb.append(httpPoolEntry.getRoute()).append("]");
        Object state = httpPoolEntry.getState();
        if (state != null) {
            sb.append("[state: ").append(state).append("]");
        }
        return sb.toString();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager
    public ClientConnectionRequest requestConnection(HttpRoute httpRoute, Object obj) {
        Args.notNull(httpRoute, "HTTP route");
        if (this.log.isDebugEnabled()) {
            this.log.debug("Connection request: " + format(httpRoute, obj) + formatStats(httpRoute));
        }
        final Future<HttpPoolEntry> futureLease = this.pool.lease(httpRoute, obj);
        return new ClientConnectionRequest() { // from class: com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.PoolingClientConnectionManager.1
            @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionRequest
            public void abortRequest() {
                futureLease.cancel(true);
            }

            @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionRequest
            public ManagedClientConnection getConnection(long j, TimeUnit timeUnit) throws ConnectionPoolTimeoutException, InterruptedException {
                return PoolingClientConnectionManager.this.leaseConnection(futureLease, j, timeUnit);
            }
        };
    }

    ManagedClientConnection leaseConnection(Future<HttpPoolEntry> future, long j, TimeUnit timeUnit) throws ExecutionException, ConnectionPoolTimeoutException, InterruptedException, TimeoutException {
        try {
            HttpPoolEntry httpPoolEntry = future.get(j, timeUnit);
            if (httpPoolEntry == null || future.isCancelled()) {
                throw new InterruptedException();
            }
            Asserts.check(httpPoolEntry.getConnection() != null, "Pool entry with no connection");
            if (this.log.isDebugEnabled()) {
                this.log.debug("Connection leased: " + format(httpPoolEntry) + formatStats(httpPoolEntry.getRoute()));
            }
            return new ManagedClientConnectionImpl(this, this.operator, httpPoolEntry);
        } catch (ExecutionException e) {
            e = e;
            Throwable cause = e.getCause();
            if (cause != null) {
                e = cause;
            }
            this.log.error("Unexpected exception leasing connection from pool", e);
            throw new InterruptedException();
        } catch (TimeoutException unused) {
            throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ae A[Catch: all -> 0x00df, TryCatch #2 {, blocks: (B:8:0x001e, B:10:0x0024, B:34:0x009d, B:36:0x00ae, B:37:0x00d2, B:40:0x00d5, B:41:0x00de, B:12:0x0026, B:14:0x002c, B:16:0x0032, B:19:0x0037, B:21:0x003f, B:22:0x0046, B:27:0x0052, B:31:0x0063, B:33:0x007d, B:26:0x0050), top: B:49:0x001e, inners: #0 }] */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void releaseConnection(ManagedClientConnection managedClientConnection, long j, TimeUnit timeUnit) {
        String str;
        Args.check(managedClientConnection instanceof ManagedClientConnectionImpl, "Connection class mismatch, connection not obtained from this manager");
        ManagedClientConnectionImpl managedClientConnectionImpl = (ManagedClientConnectionImpl) managedClientConnection;
        Asserts.check(managedClientConnectionImpl.getManager() == this, "Connection not obtained from this manager");
        synchronized (managedClientConnectionImpl) {
            HttpPoolEntry httpPoolEntryDetach = managedClientConnectionImpl.detach();
            if (httpPoolEntryDetach == null) {
                return;
            }
            try {
                if (managedClientConnectionImpl.isOpen() && !managedClientConnectionImpl.isMarkedReusable()) {
                    try {
                        managedClientConnectionImpl.shutdown();
                    } catch (IOException e) {
                        if (this.log.isDebugEnabled()) {
                            this.log.debug("I/O exception shutting down released connection", e);
                        }
                    }
                    if (managedClientConnectionImpl.isMarkedReusable()) {
                    }
                    this.pool.release((HttpConnPool) httpPoolEntryDetach, managedClientConnectionImpl.isMarkedReusable());
                    if (this.log.isDebugEnabled()) {
                    }
                    return;
                }
                if (managedClientConnectionImpl.isMarkedReusable()) {
                    httpPoolEntryDetach.updateExpiry(j, timeUnit != null ? timeUnit : TimeUnit.MILLISECONDS);
                    if (this.log.isDebugEnabled()) {
                        if (j > 0) {
                            str = "for " + j + HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR + timeUnit;
                        } else {
                            str = "indefinitely";
                        }
                        this.log.debug("Connection " + format(httpPoolEntryDetach) + " can be kept alive " + str);
                    }
                }
                this.pool.release((HttpConnPool) httpPoolEntryDetach, managedClientConnectionImpl.isMarkedReusable());
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Connection released: " + format(httpPoolEntryDetach) + formatStats(httpPoolEntryDetach.getRoute()));
                }
                return;
            } catch (Throwable th) {
                this.pool.release((HttpConnPool) httpPoolEntryDetach, managedClientConnectionImpl.isMarkedReusable());
                throw th;
            }
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager
    public void shutdown() {
        this.log.debug("Connection manager is shutting down");
        try {
            this.pool.shutdown();
        } catch (IOException e) {
            this.log.debug("I/O exception shutting down connection manager", e);
        }
        this.log.debug("Connection manager shut down");
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager
    public void closeIdleConnections(long j, TimeUnit timeUnit) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Closing connections idle longer than " + j + HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR + timeUnit);
        }
        this.pool.closeIdle(j, timeUnit);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager
    public void closeExpiredConnections() {
        this.log.debug("Closing expired connections");
        this.pool.closeExpired();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.ConnPoolControl
    public int getMaxTotal() {
        return this.pool.getMaxTotal();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.ConnPoolControl
    public void setMaxTotal(int i) {
        this.pool.setMaxTotal(i);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.ConnPoolControl
    public int getDefaultMaxPerRoute() {
        return this.pool.getDefaultMaxPerRoute();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.ConnPoolControl
    public void setDefaultMaxPerRoute(int i) {
        this.pool.setDefaultMaxPerRoute(i);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.ConnPoolControl
    public int getMaxPerRoute(HttpRoute httpRoute) {
        return this.pool.getMaxPerRoute(httpRoute);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.ConnPoolControl
    public void setMaxPerRoute(HttpRoute httpRoute, int i) {
        this.pool.setMaxPerRoute(httpRoute, i);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.ConnPoolControl
    public PoolStats getTotalStats() {
        return this.pool.getTotalStats();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.ConnPoolControl
    public PoolStats getStats(HttpRoute httpRoute) {
        return this.pool.getStats(httpRoute);
    }
}
