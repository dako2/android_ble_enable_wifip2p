package com.google.firebase.crashlytics.buildtools.api.net.proxy;

import com.google.firebase.crashlytics.buildtools.Buildtools;
import java.io.IOException;

/* loaded from: classes2.dex */
public class DefaultProxyFactory implements ProxyFactory {
    private static final ProxySettings DEFAULT_PROXY_SETTINGS = new ProxySettings(null, null, null, null);
    private static final String OVERRIDE_DEBUG_MSG_FMT = "Found proxy override specified in %s. [host=%s; port=%d; username=%s; pw=HIDDEN]";

    @Override // com.google.firebase.crashlytics.buildtools.api.net.proxy.ProxyFactory
    public ProxySettings create(ProtocolScheme protocolScheme) throws NumberFormatException, IOException {
        ProxySettings proxySettingsCreateFromProperties = createFromProperties(protocolScheme);
        if (proxySettingsCreateFromProperties == null) {
            proxySettingsCreateFromProperties = createFromEnvironment(protocolScheme);
        }
        return proxySettingsCreateFromProperties == null ? DEFAULT_PROXY_SETTINGS : proxySettingsCreateFromProperties;
    }

    /* renamed from: com.google.firebase.crashlytics.buildtools.api.net.proxy.DefaultProxyFactory$1 */
    static /* synthetic */ class C14951 {

        /* renamed from: $SwitchMap$com$google$firebase$crashlytics$buildtools$api$net$proxy$ProtocolScheme */
        static final /* synthetic */ int[] f246xa14bb1db;

        static {
            int[] iArr = new int[ProtocolScheme.values().length];
            f246xa14bb1db = iArr;
            try {
                iArr[ProtocolScheme.HTTP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f246xa14bb1db[ProtocolScheme.HTTPS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private ProxySettings createFromProperties(ProtocolScheme protocolScheme) throws NumberFormatException {
        String property;
        String property2;
        String property3;
        String property4;
        int i = C14951.f246xa14bb1db[protocolScheme.ordinal()];
        if (i == 1) {
            property = System.getProperty(Constants.HTTP_PROXY_HOST_PROP);
            property2 = System.getProperty(Constants.HTTP_PROXY_PORT_PROP);
            property3 = System.getProperty(Constants.HTTP_PROXY_USER_PROP);
            property4 = System.getProperty(Constants.HTTP_PROXY_PASSWORD_PROP);
        } else if (i != 2) {
            property = null;
            property2 = null;
            property3 = null;
            property4 = null;
        } else {
            property = System.getProperty(Constants.HTTPS_PROXY_HOST_PROP);
            property2 = System.getProperty(Constants.HTTPS_PROXY_PORT_PROP);
            property3 = System.getProperty(Constants.HTTPS_PROXY_USER_PROP);
            property4 = System.getProperty(Constants.HTTPS_PROXY_PASSWORD_PROP);
        }
        if (property == null && property2 == null && property3 == null && property4 == null) {
            return null;
        }
        Integer numValueOf = property2 != null ? Integer.valueOf(Integer.parseInt(property2)) : null;
        Buildtools.logD(String.format(OVERRIDE_DEBUG_MSG_FMT, "properties", property, numValueOf, property3));
        return new ProxySettings(property, numValueOf, property3, property4);
    }

    private ProxySettings createFromEnvironment(ProtocolScheme protocolScheme) throws NumberFormatException {
        String str;
        int i = C14951.f246xa14bb1db[protocolScheme.ordinal()];
        if (i == 1) {
            str = System.getenv().get(Constants.HTTP_PROXY_ENV);
        } else {
            str = i != 2 ? null : System.getenv().get(Constants.HTTPS_PROXY_ENV);
        }
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split(":");
        if (strArrSplit.length != 3) {
            throw new IllegalArgumentException("Could not parse proxy string from environment variable value: " + str + "; expected: http[s]://host:port");
        }
        String strSubstring = strArrSplit[1].substring(2);
        Integer numValueOf = Integer.valueOf(Integer.parseInt(strArrSplit[2]));
        Buildtools.logD(String.format(OVERRIDE_DEBUG_MSG_FMT, "environment variable", strSubstring, numValueOf, null));
        return new ProxySettings(strSubstring, numValueOf, null, null);
    }
}
