package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpec;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpecFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpecProvider;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;

@Deprecated
/* loaded from: classes2.dex */
public class IgnoreSpecFactory implements CookieSpecFactory, CookieSpecProvider {
    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpecFactory
    public CookieSpec newInstance(HttpParams httpParams) {
        return new IgnoreSpec();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpecProvider
    public CookieSpec create(HttpContext httpContext) {
        return new IgnoreSpec();
    }
}
