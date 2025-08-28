package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthScope;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.Credentials;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.NTCredentials;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.UsernamePasswordCredentials;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.CredentialsProvider;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public class SystemDefaultCredentialsProvider implements CredentialsProvider {
    private static final Map<String, String> SCHEME_MAP;
    private final BasicCredentialsProvider internal = new BasicCredentialsProvider();

    static {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        SCHEME_MAP = concurrentHashMap;
        concurrentHashMap.put("Basic".toUpperCase(Locale.ROOT), "Basic");
        concurrentHashMap.put("Digest".toUpperCase(Locale.ROOT), "Digest");
        concurrentHashMap.put("NTLM".toUpperCase(Locale.ROOT), "NTLM");
        concurrentHashMap.put("Negotiate".toUpperCase(Locale.ROOT), "SPNEGO");
        concurrentHashMap.put("Kerberos".toUpperCase(Locale.ROOT), "Kerberos");
    }

    private static String translateScheme(String str) {
        if (str == null) {
            return null;
        }
        String str2 = SCHEME_MAP.get(str);
        return str2 != null ? str2 : str;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.CredentialsProvider
    public void setCredentials(AuthScope authScope, Credentials credentials) {
        this.internal.setCredentials(authScope, credentials);
    }

    private static PasswordAuthentication getSystemCreds(String str, AuthScope authScope, Authenticator.RequestorType requestorType) {
        return Authenticator.requestPasswordAuthentication(authScope.getHost(), null, authScope.getPort(), str, null, translateScheme(authScope.getScheme()), null, requestorType);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.CredentialsProvider
    public Credentials getCredentials(AuthScope authScope) {
        String schemeName;
        String property;
        String property2;
        String property3;
        Args.notNull(authScope, "Auth scope");
        Credentials credentials = this.internal.getCredentials(authScope);
        if (credentials != null) {
            return credentials;
        }
        if (authScope.getHost() != null) {
            HttpHost origin = authScope.getOrigin();
            if (origin != null) {
                schemeName = origin.getSchemeName();
            } else {
                schemeName = authScope.getPort() == 443 ? "https" : HttpHost.DEFAULT_SCHEME_NAME;
            }
            PasswordAuthentication systemCreds = getSystemCreds(schemeName, authScope, Authenticator.RequestorType.SERVER);
            if (systemCreds == null) {
                systemCreds = getSystemCreds(schemeName, authScope, Authenticator.RequestorType.PROXY);
            }
            if (systemCreds == null && (property = System.getProperty(schemeName + ".proxyHost")) != null && (property2 = System.getProperty(schemeName + ".proxyPort")) != null) {
                try {
                    if (authScope.match(new AuthScope(property, Integer.parseInt(property2))) >= 0 && (property3 = System.getProperty(schemeName + ".proxyUser")) != null) {
                        String property4 = System.getProperty(schemeName + ".proxyPassword");
                        systemCreds = new PasswordAuthentication(property3, property4 != null ? property4.toCharArray() : new char[0]);
                    }
                } catch (NumberFormatException unused) {
                }
            }
            if (systemCreds != null) {
                String property5 = System.getProperty("http.auth.ntlm.domain");
                if (property5 != null) {
                    return new NTCredentials(systemCreds.getUserName(), new String(systemCreds.getPassword()), null, property5);
                }
                if ("NTLM".equalsIgnoreCase(authScope.getScheme())) {
                    return new NTCredentials(systemCreds.getUserName(), new String(systemCreds.getPassword()), null, null);
                }
                return new UsernamePasswordCredentials(systemCreds.getUserName(), new String(systemCreds.getPassword()));
            }
        }
        return null;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.CredentialsProvider
    public void clear() {
        this.internal.clear();
    }
}
