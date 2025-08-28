package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.Header;
import java.util.List;

/* loaded from: classes2.dex */
public interface CookieSpec {
    List<Header> formatCookies(List<Cookie> list);

    int getVersion();

    Header getVersionHeader();

    boolean match(Cookie cookie, CookieOrigin cookieOrigin);

    List<Cookie> parse(Header header, CookieOrigin cookieOrigin) throws MalformedCookieException;

    void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException;
}
