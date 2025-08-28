package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ConnectionReuseStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoutePlanner;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.DefaultConnectionReuseStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.NoConnectionReuseStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.PoolingClientConnectionManager;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.ProxySelectorRoutePlanner;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.SchemeRegistryFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import java.net.ProxySelector;

@Deprecated
/* loaded from: classes2.dex */
public class SystemDefaultHttpClient extends DefaultHttpClient {
    public SystemDefaultHttpClient(HttpParams httpParams) {
        super(null, httpParams);
    }

    public SystemDefaultHttpClient() {
        super(null, null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.AbstractHttpClient
    protected ClientConnectionManager createClientConnectionManager() throws NumberFormatException {
        PoolingClientConnectionManager poolingClientConnectionManager = new PoolingClientConnectionManager(SchemeRegistryFactory.createSystemDefault());
        if ("true".equalsIgnoreCase(System.getProperty("http.keepAlive", "true"))) {
            int i = Integer.parseInt(System.getProperty("http.maxConnections", "5"));
            poolingClientConnectionManager.setDefaultMaxPerRoute(i);
            poolingClientConnectionManager.setMaxTotal(i * 2);
        }
        return poolingClientConnectionManager;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.AbstractHttpClient
    protected HttpRoutePlanner createHttpRoutePlanner() {
        return new ProxySelectorRoutePlanner(getConnectionManager().getSchemeRegistry(), ProxySelector.getDefault());
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.AbstractHttpClient
    protected ConnectionReuseStrategy createConnectionReuseStrategy() {
        if ("true".equalsIgnoreCase(System.getProperty("http.keepAlive", "true"))) {
            return new DefaultConnectionReuseStrategy();
        }
        return new NoConnectionReuseStrategy();
    }
}
