package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.tsccm;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.AbstractPoolEntry;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.AbstractPooledConnAdapter;

@Deprecated
/* loaded from: classes2.dex */
public class BasicPooledConnAdapter extends AbstractPooledConnAdapter {
    protected BasicPooledConnAdapter(ThreadSafeClientConnManager threadSafeClientConnManager, AbstractPoolEntry abstractPoolEntry) {
        super(threadSafeClientConnManager, abstractPoolEntry);
        markReusable();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.AbstractClientConnAdapter
    protected ClientConnectionManager getManager() {
        return super.getManager();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.AbstractPooledConnAdapter
    protected AbstractPoolEntry getPoolEntry() {
        return super.getPoolEntry();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.AbstractPooledConnAdapter, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.AbstractClientConnAdapter
    protected void detach() {
        super.detach();
    }
}
