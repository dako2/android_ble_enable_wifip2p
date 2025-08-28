package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ConnectionReuseStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;

/* loaded from: classes2.dex */
public class NoConnectionReuseStrategy implements ConnectionReuseStrategy {
    public static final NoConnectionReuseStrategy INSTANCE = new NoConnectionReuseStrategy();

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ConnectionReuseStrategy
    public boolean keepAlive(HttpResponse httpResponse, HttpContext httpContext) {
        return false;
    }
}
