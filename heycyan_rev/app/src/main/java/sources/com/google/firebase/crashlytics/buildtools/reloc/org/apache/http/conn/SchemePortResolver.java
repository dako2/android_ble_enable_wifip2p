package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;

/* loaded from: classes2.dex */
public interface SchemePortResolver {
    int resolve(HttpHost httpHost) throws UnsupportedSchemeException;
}
