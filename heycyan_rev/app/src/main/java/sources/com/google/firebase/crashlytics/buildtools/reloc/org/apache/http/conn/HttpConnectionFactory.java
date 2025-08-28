package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.config.ConnectionConfig;

/* loaded from: classes2.dex */
public interface HttpConnectionFactory<T, C extends HttpConnection> {
    C create(T t, ConnectionConfig connectionConfig);
}
