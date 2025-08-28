package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HeaderElement;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CommonCookieAttributeHandler;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.Cookie;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieAttributeHandler;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieOrigin;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.MalformedCookieException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes2.dex */
public abstract class CookieSpecBase extends AbstractCookieSpec {
    public CookieSpecBase() {
    }

    protected CookieSpecBase(HashMap<String, CookieAttributeHandler> map) {
        super(map);
    }

    protected CookieSpecBase(CommonCookieAttributeHandler... commonCookieAttributeHandlerArr) {
        super(commonCookieAttributeHandlerArr);
    }

    protected static String getDefaultPath(CookieOrigin cookieOrigin) {
        String path = cookieOrigin.getPath();
        int iLastIndexOf = path.lastIndexOf(47);
        if (iLastIndexOf < 0) {
            return path;
        }
        if (iLastIndexOf == 0) {
            iLastIndexOf = 1;
        }
        return path.substring(0, iLastIndexOf);
    }

    protected static String getDefaultDomain(CookieOrigin cookieOrigin) {
        return cookieOrigin.getHost();
    }

    protected List<Cookie> parse(HeaderElement[] headerElementArr, CookieOrigin cookieOrigin) throws MalformedCookieException {
        ArrayList arrayList = new ArrayList(headerElementArr.length);
        for (HeaderElement headerElement : headerElementArr) {
            String name = headerElement.getName();
            String value = headerElement.getValue();
            if (name != null && !name.isEmpty()) {
                BasicClientCookie basicClientCookie = new BasicClientCookie(name, value);
                basicClientCookie.setPath(getDefaultPath(cookieOrigin));
                basicClientCookie.setDomain(getDefaultDomain(cookieOrigin));
                NameValuePair[] parameters = headerElement.getParameters();
                for (int length = parameters.length - 1; length >= 0; length--) {
                    NameValuePair nameValuePair = parameters[length];
                    String lowerCase = nameValuePair.getName().toLowerCase(Locale.ROOT);
                    basicClientCookie.setAttribute(lowerCase, nameValuePair.getValue());
                    CookieAttributeHandler cookieAttributeHandlerFindAttribHandler = findAttribHandler(lowerCase);
                    if (cookieAttributeHandlerFindAttribHandler != null) {
                        cookieAttributeHandlerFindAttribHandler.parse(basicClientCookie, nameValuePair.getValue());
                    }
                }
                arrayList.add(basicClientCookie);
            }
        }
        return arrayList;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpec
    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        Iterator<CookieAttributeHandler> it = getAttribHandlers().iterator();
        while (it.hasNext()) {
            it.next().validate(cookie, cookieOrigin);
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpec
    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        Iterator<CookieAttributeHandler> it = getAttribHandlers().iterator();
        while (it.hasNext()) {
            if (!it.next().match(cookie, cookieOrigin)) {
                return false;
            }
        }
        return true;
    }
}
