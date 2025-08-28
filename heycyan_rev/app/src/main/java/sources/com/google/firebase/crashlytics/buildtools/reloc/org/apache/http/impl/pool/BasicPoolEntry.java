package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.pool;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.PoolEntry;
import java.io.IOException;

/* loaded from: classes2.dex */
public class BasicPoolEntry extends PoolEntry<HttpHost, HttpClientConnection> {
    public BasicPoolEntry(String str, HttpHost httpHost, HttpClientConnection httpClientConnection) {
        super(str, httpHost, httpClientConnection);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.PoolEntry
    public void close() {
        try {
            getConnection().close();
        } catch (IOException unused) {
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.pool.PoolEntry
    public boolean isClosed() {
        return !getConnection().isOpen();
    }
}
