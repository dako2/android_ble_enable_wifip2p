package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpec;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpecFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpecProvider;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.params.CookieSpecPNames;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import java.util.Collection;

@Deprecated
/* loaded from: classes2.dex */
public class RFC2109SpecFactory implements CookieSpecFactory, CookieSpecProvider {
    private final CookieSpec cookieSpec;

    public RFC2109SpecFactory(String[] strArr, boolean z) {
        this.cookieSpec = new RFC2109Spec(strArr, z);
    }

    public RFC2109SpecFactory() {
        this(null, false);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpecFactory
    public CookieSpec newInstance(HttpParams httpParams) {
        if (httpParams != null) {
            Collection collection = (Collection) httpParams.getParameter(CookieSpecPNames.DATE_PATTERNS);
            return new RFC2109Spec(collection != null ? (String[]) collection.toArray(new String[collection.size()]) : null, httpParams.getBooleanParameter(CookieSpecPNames.SINGLE_COOKIE_HEADER, false));
        }
        return new RFC2109Spec();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpecProvider
    public CookieSpec create(HttpContext httpContext) {
        return this.cookieSpec;
    }
}
