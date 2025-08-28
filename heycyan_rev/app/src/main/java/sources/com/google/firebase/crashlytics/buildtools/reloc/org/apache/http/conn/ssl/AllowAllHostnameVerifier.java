package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ssl;

@Deprecated
/* loaded from: classes2.dex */
public class AllowAllHostnameVerifier extends AbstractVerifier {
    public static final AllowAllHostnameVerifier INSTANCE = new AllowAllHostnameVerifier();

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ssl.X509HostnameVerifier
    public final void verify(String str, String[] strArr, String[] strArr2) {
    }

    public final String toString() {
        return "ALLOW_ALL";
    }
}
