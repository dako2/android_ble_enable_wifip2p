package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.Header;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HeaderElement;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.params.CookiePolicy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.ClientCookie;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CommonCookieAttributeHandler;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.Cookie;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieAttributeHandler;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieOrigin;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieRestrictionViolationException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.InterfaceC2034SM;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.MalformedCookieException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BufferedHeader;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.CharArrayBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes2.dex */
public class RFC2965Spec extends RFC2109Spec {
    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie.RFC2109Spec, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpec
    public int getVersion() {
        return 1;
    }

    public RFC2965Spec() {
        this((String[]) null, false);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public RFC2965Spec(String[] strArr, boolean z) {
        CommonCookieAttributeHandler[] commonCookieAttributeHandlerArr = new CommonCookieAttributeHandler[10];
        commonCookieAttributeHandlerArr[0] = new RFC2965VersionAttributeHandler();
        commonCookieAttributeHandlerArr[1] = new BasicPathHandler() { // from class: com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie.RFC2965Spec.1
            @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie.BasicPathHandler, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieAttributeHandler
            public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
                if (!match(cookie, cookieOrigin)) {
                    throw new CookieRestrictionViolationException("Illegal 'path' attribute \"" + cookie.getPath() + "\". Path of origin: \"" + cookieOrigin.getPath() + "\"");
                }
            }
        };
        commonCookieAttributeHandlerArr[2] = new RFC2965DomainAttributeHandler();
        commonCookieAttributeHandlerArr[3] = new RFC2965PortAttributeHandler();
        commonCookieAttributeHandlerArr[4] = new BasicMaxAgeHandler();
        commonCookieAttributeHandlerArr[5] = new BasicSecureHandler();
        commonCookieAttributeHandlerArr[6] = new BasicCommentHandler();
        commonCookieAttributeHandlerArr[7] = new BasicExpiresHandler(strArr != null ? (String[]) strArr.clone() : DATE_PATTERNS);
        commonCookieAttributeHandlerArr[8] = new RFC2965CommentUrlAttributeHandler();
        commonCookieAttributeHandlerArr[9] = new RFC2965DiscardAttributeHandler();
        super(z, commonCookieAttributeHandlerArr);
    }

    RFC2965Spec(boolean z, CommonCookieAttributeHandler... commonCookieAttributeHandlerArr) {
        super(z, commonCookieAttributeHandlerArr);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie.RFC2109Spec, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpec
    public List<Cookie> parse(Header header, CookieOrigin cookieOrigin) throws MalformedCookieException {
        Args.notNull(header, "Header");
        Args.notNull(cookieOrigin, "Cookie origin");
        if (!header.getName().equalsIgnoreCase("Set-Cookie2")) {
            throw new MalformedCookieException("Unrecognized cookie header '" + header.toString() + "'");
        }
        return createCookies(header.getElements(), adjustEffectiveHost(cookieOrigin));
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie.CookieSpecBase
    protected List<Cookie> parse(HeaderElement[] headerElementArr, CookieOrigin cookieOrigin) throws MalformedCookieException {
        return createCookies(headerElementArr, adjustEffectiveHost(cookieOrigin));
    }

    private List<Cookie> createCookies(HeaderElement[] headerElementArr, CookieOrigin cookieOrigin) throws MalformedCookieException {
        ArrayList arrayList = new ArrayList(headerElementArr.length);
        for (HeaderElement headerElement : headerElementArr) {
            String name = headerElement.getName();
            String value = headerElement.getValue();
            if (name == null || name.isEmpty()) {
                throw new MalformedCookieException("Cookie name may not be empty");
            }
            BasicClientCookie2 basicClientCookie2 = new BasicClientCookie2(name, value);
            basicClientCookie2.setPath(getDefaultPath(cookieOrigin));
            basicClientCookie2.setDomain(getDefaultDomain(cookieOrigin));
            basicClientCookie2.setPorts(new int[]{cookieOrigin.getPort()});
            NameValuePair[] parameters = headerElement.getParameters();
            HashMap map = new HashMap(parameters.length);
            for (int length = parameters.length - 1; length >= 0; length--) {
                NameValuePair nameValuePair = parameters[length];
                map.put(nameValuePair.getName().toLowerCase(Locale.ROOT), nameValuePair);
            }
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                NameValuePair nameValuePair2 = (NameValuePair) ((Map.Entry) it.next()).getValue();
                String lowerCase = nameValuePair2.getName().toLowerCase(Locale.ROOT);
                basicClientCookie2.setAttribute(lowerCase, nameValuePair2.getValue());
                CookieAttributeHandler cookieAttributeHandlerFindAttribHandler = findAttribHandler(lowerCase);
                if (cookieAttributeHandlerFindAttribHandler != null) {
                    cookieAttributeHandlerFindAttribHandler.parse(basicClientCookie2, nameValuePair2.getValue());
                }
            }
            arrayList.add(basicClientCookie2);
        }
        return arrayList;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie.RFC2109Spec, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie.CookieSpecBase, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpec
    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        super.validate(cookie, adjustEffectiveHost(cookieOrigin));
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie.CookieSpecBase, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpec
    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        return super.match(cookie, adjustEffectiveHost(cookieOrigin));
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie.RFC2109Spec
    protected void formatCookieAsVer(CharArrayBuffer charArrayBuffer, Cookie cookie, int i) {
        String attribute;
        int[] ports;
        super.formatCookieAsVer(charArrayBuffer, cookie, i);
        if (!(cookie instanceof ClientCookie) || (attribute = ((ClientCookie) cookie).getAttribute(ClientCookie.PORT_ATTR)) == null) {
            return;
        }
        charArrayBuffer.append("; $Port");
        charArrayBuffer.append("=\"");
        if (!attribute.trim().isEmpty() && (ports = cookie.getPorts()) != null) {
            int length = ports.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (i2 > 0) {
                    charArrayBuffer.append(",");
                }
                charArrayBuffer.append(Integer.toString(ports[i2]));
            }
        }
        charArrayBuffer.append("\"");
    }

    private static CookieOrigin adjustEffectiveHost(CookieOrigin cookieOrigin) {
        String host = cookieOrigin.getHost();
        for (int i = 0; i < host.length(); i++) {
            char cCharAt = host.charAt(i);
            if (cCharAt == '.' || cCharAt == ':') {
                return cookieOrigin;
            }
        }
        return new CookieOrigin(host + ".local", cookieOrigin.getPort(), cookieOrigin.getPath(), cookieOrigin.isSecure());
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie.RFC2109Spec, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpec
    public Header getVersionHeader() {
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(40);
        charArrayBuffer.append(InterfaceC2034SM.COOKIE2);
        charArrayBuffer.append(": ");
        charArrayBuffer.append("$Version=");
        charArrayBuffer.append(Integer.toString(getVersion()));
        return new BufferedHeader(charArrayBuffer);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie.RFC2109Spec
    public String toString() {
        return CookiePolicy.RFC_2965;
    }
}
