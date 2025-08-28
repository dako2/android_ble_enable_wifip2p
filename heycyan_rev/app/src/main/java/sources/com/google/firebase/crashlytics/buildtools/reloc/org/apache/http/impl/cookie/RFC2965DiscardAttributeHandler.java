package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.ClientCookie;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CommonCookieAttributeHandler;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.Cookie;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieOrigin;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.MalformedCookieException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.SetCookie;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.SetCookie2;

/* loaded from: classes2.dex */
public class RFC2965DiscardAttributeHandler implements CommonCookieAttributeHandler {
    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieAttributeHandler
    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        return true;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieAttributeHandler
    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieAttributeHandler
    public void parse(SetCookie setCookie, String str) throws MalformedCookieException {
        if (setCookie instanceof SetCookie2) {
            ((SetCookie2) setCookie).setDiscard(true);
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CommonCookieAttributeHandler
    public String getAttributeName() {
        return ClientCookie.DISCARD_ATTR;
    }
}
