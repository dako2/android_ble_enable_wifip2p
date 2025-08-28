package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpStatus;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthScheme;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.MalformedChallengeException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.config.RequestConfig;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import java.util.Collection;
import java.util.Map;
import java.util.Queue;

/* loaded from: classes2.dex */
public class ProxyAuthenticationStrategy extends AuthenticationStrategyImpl {
    public static final ProxyAuthenticationStrategy INSTANCE = new ProxyAuthenticationStrategy();

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.AuthenticationStrategyImpl, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.AuthenticationStrategy
    public /* bridge */ /* synthetic */ void authFailed(HttpHost httpHost, AuthScheme authScheme, HttpContext httpContext) {
        super.authFailed(httpHost, authScheme, httpContext);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.AuthenticationStrategyImpl, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.AuthenticationStrategy
    public /* bridge */ /* synthetic */ void authSucceeded(HttpHost httpHost, AuthScheme authScheme, HttpContext httpContext) {
        super.authSucceeded(httpHost, authScheme, httpContext);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.AuthenticationStrategyImpl, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.AuthenticationStrategy
    public /* bridge */ /* synthetic */ Map getChallenges(HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) throws MalformedChallengeException {
        return super.getChallenges(httpHost, httpResponse, httpContext);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.AuthenticationStrategyImpl, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.AuthenticationStrategy
    public /* bridge */ /* synthetic */ boolean isAuthenticationRequested(HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) {
        return super.isAuthenticationRequested(httpHost, httpResponse, httpContext);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.AuthenticationStrategyImpl, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.AuthenticationStrategy
    public /* bridge */ /* synthetic */ Queue select(Map map, HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) throws MalformedChallengeException {
        return super.select(map, httpHost, httpResponse, httpContext);
    }

    public ProxyAuthenticationStrategy() {
        super(HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED, "Proxy-Authenticate");
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.AuthenticationStrategyImpl
    Collection<String> getPreferredAuthSchemes(RequestConfig requestConfig) {
        return requestConfig.getProxyPreferredAuthSchemes();
    }
}
