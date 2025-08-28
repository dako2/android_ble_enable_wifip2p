package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthScheme;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthState;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.Credentials;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.UserTokenHandler;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol.HttpClientContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ManagedHttpClientConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import java.security.Principal;
import javax.net.ssl.SSLSession;

/* loaded from: classes2.dex */
public class DefaultUserTokenHandler implements UserTokenHandler {
    public static final DefaultUserTokenHandler INSTANCE = new DefaultUserTokenHandler();

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.UserTokenHandler
    public Object getUserToken(HttpContext httpContext) {
        Principal authPrincipal;
        SSLSession sSLSession;
        HttpClientContext httpClientContextAdapt = HttpClientContext.adapt(httpContext);
        AuthState targetAuthState = httpClientContextAdapt.getTargetAuthState();
        if (targetAuthState != null) {
            authPrincipal = getAuthPrincipal(targetAuthState);
            if (authPrincipal == null) {
                authPrincipal = getAuthPrincipal(httpClientContextAdapt.getProxyAuthState());
            }
        } else {
            authPrincipal = null;
        }
        if (authPrincipal != null) {
            return authPrincipal;
        }
        HttpConnection connection = httpClientContextAdapt.getConnection();
        return (connection.isOpen() && (connection instanceof ManagedHttpClientConnection) && (sSLSession = ((ManagedHttpClientConnection) connection).getSSLSession()) != null) ? sSLSession.getLocalPrincipal() : authPrincipal;
    }

    private static Principal getAuthPrincipal(AuthState authState) {
        Credentials credentials;
        AuthScheme authScheme = authState.getAuthScheme();
        if (authScheme == null || !authScheme.isComplete() || !authScheme.isConnectionBased() || (credentials = authState.getCredentials()) == null) {
            return null;
        }
        return credentials.getUserPrincipal();
    }
}
