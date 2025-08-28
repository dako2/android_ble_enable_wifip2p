package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.util.PublicSuffixMatcher;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.Cookie;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieOrigin;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpec;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpecProvider;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.MalformedCookieException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;

/* loaded from: classes2.dex */
public class RFC6265CookieSpecProvider implements CookieSpecProvider {
    private final CompatibilityLevel compatibilityLevel;
    private volatile CookieSpec cookieSpec;
    private final PublicSuffixMatcher publicSuffixMatcher;

    public enum CompatibilityLevel {
        STRICT,
        RELAXED,
        IE_MEDIUM_SECURITY
    }

    public RFC6265CookieSpecProvider(CompatibilityLevel compatibilityLevel, PublicSuffixMatcher publicSuffixMatcher) {
        this.compatibilityLevel = compatibilityLevel == null ? CompatibilityLevel.RELAXED : compatibilityLevel;
        this.publicSuffixMatcher = publicSuffixMatcher;
    }

    public RFC6265CookieSpecProvider(PublicSuffixMatcher publicSuffixMatcher) {
        this(CompatibilityLevel.RELAXED, publicSuffixMatcher);
    }

    public RFC6265CookieSpecProvider() {
        this(CompatibilityLevel.RELAXED, null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpecProvider
    public CookieSpec create(HttpContext httpContext) {
        if (this.cookieSpec == null) {
            synchronized (this) {
                if (this.cookieSpec == null) {
                    int i = C20572.f383x14f07ea0[this.compatibilityLevel.ordinal()];
                    if (i == 1) {
                        this.cookieSpec = new RFC6265StrictSpec(new BasicPathHandler(), PublicSuffixDomainFilter.decorate(new BasicDomainHandler(), this.publicSuffixMatcher), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicExpiresHandler(RFC6265StrictSpec.DATE_PATTERNS));
                    } else if (i == 2) {
                        this.cookieSpec = new RFC6265LaxSpec(new BasicPathHandler() { // from class: com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie.RFC6265CookieSpecProvider.1
                            @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie.BasicPathHandler, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieAttributeHandler
                            public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
                            }
                        }, PublicSuffixDomainFilter.decorate(new BasicDomainHandler(), this.publicSuffixMatcher), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicExpiresHandler(RFC6265StrictSpec.DATE_PATTERNS));
                    } else {
                        this.cookieSpec = new RFC6265LaxSpec(new BasicPathHandler(), PublicSuffixDomainFilter.decorate(new BasicDomainHandler(), this.publicSuffixMatcher), new LaxMaxAgeHandler(), new BasicSecureHandler(), new LaxExpiresHandler());
                    }
                }
            }
        }
        return this.cookieSpec;
    }

    /* renamed from: com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.cookie.RFC6265CookieSpecProvider$2 */
    static /* synthetic */ class C20572 {

        /* renamed from: $SwitchMap$org$apache$http$impl$cookie$RFC6265CookieSpecProvider$CompatibilityLevel */
        static final /* synthetic */ int[] f383x14f07ea0;

        static {
            int[] iArr = new int[CompatibilityLevel.values().length];
            f383x14f07ea0 = iArr;
            try {
                iArr[CompatibilityLevel.STRICT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f383x14f07ea0[CompatibilityLevel.IE_MEDIUM_SECURITY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
