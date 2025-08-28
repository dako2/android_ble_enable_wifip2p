package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.auth;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthScheme;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthSchemeFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthSchemeProvider;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;

/* loaded from: classes2.dex */
public class NTLMSchemeFactory implements AuthSchemeFactory, AuthSchemeProvider {
    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthSchemeFactory
    public AuthScheme newInstance(HttpParams httpParams) {
        return new NTLMScheme();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthSchemeProvider
    public AuthScheme create(HttpContext httpContext) {
        return new NTLMScheme();
    }
}
