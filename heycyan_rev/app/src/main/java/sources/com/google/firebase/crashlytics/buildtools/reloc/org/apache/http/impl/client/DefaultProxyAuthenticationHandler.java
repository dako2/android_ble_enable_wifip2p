package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.Header;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.MalformedChallengeException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.params.AuthPNames;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.util.List;
import java.util.Map;

@Deprecated
/* loaded from: classes2.dex */
public class DefaultProxyAuthenticationHandler extends AbstractAuthenticationHandler {
    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.AuthenticationHandler
    public boolean isAuthenticationRequested(HttpResponse httpResponse, HttpContext httpContext) {
        Args.notNull(httpResponse, "HTTP response");
        return httpResponse.getStatusLine().getStatusCode() == 407;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.AuthenticationHandler
    public Map<String, Header> getChallenges(HttpResponse httpResponse, HttpContext httpContext) throws MalformedChallengeException {
        Args.notNull(httpResponse, "HTTP response");
        return parseChallenges(httpResponse.getHeaders("Proxy-Authenticate"));
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.AbstractAuthenticationHandler
    protected List<String> getAuthPreferences(HttpResponse httpResponse, HttpContext httpContext) {
        List<String> list = (List) httpResponse.getParams().getParameter(AuthPNames.PROXY_AUTH_PREF);
        return list != null ? list : super.getAuthPreferences(httpResponse, httpContext);
    }
}
