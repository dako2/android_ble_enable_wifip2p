package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.util.PublicSuffixMatcher;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CommonCookieAttributeHandler;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.Cookie;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieOrigin;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpec;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpecProvider;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.MalformedCookieException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;

/* loaded from: classes2.dex */
public class DefaultCookieSpecProvider implements CookieSpecProvider {
    private final CompatibilityLevel compatibilityLevel;
    private volatile CookieSpec cookieSpec;
    private final String[] datepatterns;
    private final boolean oneHeader;
    private final PublicSuffixMatcher publicSuffixMatcher;

    public enum CompatibilityLevel {
        DEFAULT,
        IE_MEDIUM_SECURITY
    }

    public DefaultCookieSpecProvider(CompatibilityLevel compatibilityLevel, PublicSuffixMatcher publicSuffixMatcher, String[] strArr, boolean z) {
        this.compatibilityLevel = compatibilityLevel == null ? CompatibilityLevel.DEFAULT : compatibilityLevel;
        this.publicSuffixMatcher = publicSuffixMatcher;
        this.datepatterns = strArr;
        this.oneHeader = z;
    }

    public DefaultCookieSpecProvider(CompatibilityLevel compatibilityLevel, PublicSuffixMatcher publicSuffixMatcher) {
        this(compatibilityLevel, publicSuffixMatcher, null, false);
    }

    public DefaultCookieSpecProvider(PublicSuffixMatcher publicSuffixMatcher) {
        this(CompatibilityLevel.DEFAULT, publicSuffixMatcher, null, false);
    }

    public DefaultCookieSpecProvider() {
        this(CompatibilityLevel.DEFAULT, null, null, false);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpecProvider
    public CookieSpec create(HttpContext httpContext) {
        if (this.cookieSpec == null) {
            synchronized (this) {
                if (this.cookieSpec == null) {
                    RFC2965Spec rFC2965Spec = new RFC2965Spec(this.oneHeader, new RFC2965VersionAttributeHandler(), new BasicPathHandler(), PublicSuffixDomainFilter.decorate(new RFC2965DomainAttributeHandler(), this.publicSuffixMatcher), new RFC2965PortAttributeHandler(), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicCommentHandler(), new RFC2965CommentUrlAttributeHandler(), new RFC2965DiscardAttributeHandler());
                    RFC2109Spec rFC2109Spec = new RFC2109Spec(this.oneHeader, new RFC2109VersionHandler(), new BasicPathHandler(), PublicSuffixDomainFilter.decorate(new RFC2109DomainHandler(), this.publicSuffixMatcher), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicCommentHandler());
                    CommonCookieAttributeHandler[] commonCookieAttributeHandlerArr = new CommonCookieAttributeHandler[5];
                    commonCookieAttributeHandlerArr[0] = PublicSuffixDomainFilter.decorate(new BasicDomainHandler(), this.publicSuffixMatcher);
                    commonCookieAttributeHandlerArr[1] = this.compatibilityLevel == CompatibilityLevel.IE_MEDIUM_SECURITY ? new BasicPathHandler() { // from class: com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie.DefaultCookieSpecProvider.1
                        @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie.BasicPathHandler, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieAttributeHandler
                        public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
                        }
                    } : new BasicPathHandler();
                    commonCookieAttributeHandlerArr[2] = new BasicSecureHandler();
                    commonCookieAttributeHandlerArr[3] = new BasicCommentHandler();
                    String[] strArr = this.datepatterns;
                    commonCookieAttributeHandlerArr[4] = new BasicExpiresHandler(strArr != null ? (String[]) strArr.clone() : new String[]{"EEE, dd-MMM-yy HH:mm:ss z"});
                    this.cookieSpec = new DefaultCookieSpec(rFC2965Spec, rFC2109Spec, new NetscapeDraftSpec(commonCookieAttributeHandlerArr));
                }
            }
        }
        return this.cookieSpec;
    }
}
