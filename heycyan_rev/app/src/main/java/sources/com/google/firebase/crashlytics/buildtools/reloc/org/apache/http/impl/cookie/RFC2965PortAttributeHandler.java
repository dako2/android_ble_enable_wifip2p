package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.ClientCookie;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CommonCookieAttributeHandler;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.Cookie;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieOrigin;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieRestrictionViolationException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.MalformedCookieException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.SetCookie;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.SetCookie2;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.util.StringTokenizer;

/* loaded from: classes2.dex */
public class RFC2965PortAttributeHandler implements CommonCookieAttributeHandler {
    private static int[] parsePortAttribute(String str) throws MalformedCookieException, NumberFormatException {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        int[] iArr = new int[stringTokenizer.countTokens()];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            try {
                int i2 = Integer.parseInt(stringTokenizer.nextToken().trim());
                iArr[i] = i2;
                if (i2 < 0) {
                    throw new MalformedCookieException("Invalid Port attribute.");
                }
                i++;
            } catch (NumberFormatException e) {
                throw new MalformedCookieException("Invalid Port attribute: " + e.getMessage());
            }
        }
        return iArr;
    }

    private static boolean portMatch(int i, int[] iArr) {
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieAttributeHandler
    public void parse(SetCookie setCookie, String str) throws MalformedCookieException {
        Args.notNull(setCookie, "Cookie");
        if (setCookie instanceof SetCookie2) {
            SetCookie2 setCookie2 = (SetCookie2) setCookie;
            if (str == null || str.trim().isEmpty()) {
                return;
            }
            setCookie2.setPorts(parsePortAttribute(str));
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieAttributeHandler
    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        int port = cookieOrigin.getPort();
        if ((cookie instanceof ClientCookie) && ((ClientCookie) cookie).containsAttribute(ClientCookie.PORT_ATTR) && !portMatch(port, cookie.getPorts())) {
            throw new CookieRestrictionViolationException("Port attribute violates RFC 2965: Request port not found in cookie's port list.");
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieAttributeHandler
    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        int port = cookieOrigin.getPort();
        if ((cookie instanceof ClientCookie) && ((ClientCookie) cookie).containsAttribute(ClientCookie.PORT_ATTR)) {
            return cookie.getPorts() != null && portMatch(port, cookie.getPorts());
        }
        return true;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CommonCookieAttributeHandler
    public String getAttributeName() {
        return ClientCookie.PORT_ATTR;
    }
}
