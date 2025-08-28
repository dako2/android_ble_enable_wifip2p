package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ssl;

import javax.net.ssl.SSLException;

@Deprecated
/* loaded from: classes2.dex */
public class BrowserCompatHostnameVerifier extends AbstractVerifier {
    public static final BrowserCompatHostnameVerifier INSTANCE = new BrowserCompatHostnameVerifier();

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ssl.X509HostnameVerifier
    public final void verify(String str, String[] strArr, String[] strArr2) throws SSLException {
        verify(str, strArr, strArr2, false);
    }

    public final String toString() {
        return "BROWSER_COMPATIBLE";
    }
}
