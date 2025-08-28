package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.UserTokenHandler;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;

/* loaded from: classes2.dex */
public class NoopUserTokenHandler implements UserTokenHandler {
    public static final NoopUserTokenHandler INSTANCE = new NoopUserTokenHandler();

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.UserTokenHandler
    public Object getUserToken(HttpContext httpContext) {
        return null;
    }
}
