package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.auth;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthScheme;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthSchemeFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthSchemeProvider;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
public class BasicSchemeFactory implements AuthSchemeFactory, AuthSchemeProvider {
    private final Charset charset;

    public BasicSchemeFactory(Charset charset) {
        this.charset = charset;
    }

    public BasicSchemeFactory() {
        this(null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthSchemeFactory
    public AuthScheme newInstance(HttpParams httpParams) {
        return new BasicScheme();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthSchemeProvider
    public AuthScheme create(HttpContext httpContext) {
        return new BasicScheme(this.charset);
    }
}
