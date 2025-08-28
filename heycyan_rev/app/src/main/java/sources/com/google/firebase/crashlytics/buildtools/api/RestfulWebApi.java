package com.google.firebase.crashlytics.buildtools.api;

import com.google.firebase.crashlytics.buildtools.Buildtools;
import com.google.firebase.crashlytics.buildtools.api.net.Constants;
import com.google.firebase.crashlytics.buildtools.api.net.proxy.ProtocolScheme;
import com.google.firebase.crashlytics.buildtools.api.net.proxy.ProxyFactory;
import com.google.firebase.crashlytics.buildtools.api.net.proxy.ProxySettings;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.Header;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPut;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpRequestBase;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.FileEntity;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class RestfulWebApi implements WebApi {
    private final String _codeMappingApiUrl;
    private final ProxyFactory _proxyFactory;
    private String _userAgent;
    private String _clientVersion = null;
    private String _clientType = null;

    private static boolean isSuccess(int i) {
        return i >= 200 && i < 300;
    }

    public String toString() {
        return " ClientType: " + this._clientType + " (" + this._clientVersion + ")";
    }

    public RestfulWebApi(String str, ProxyFactory proxyFactory) {
        this._proxyFactory = proxyFactory;
        this._codeMappingApiUrl = str;
    }

    @Override // com.google.firebase.crashlytics.buildtools.api.WebApi
    public synchronized void setUserAgent(String str) {
        this._userAgent = str;
    }

    @Override // com.google.firebase.crashlytics.buildtools.api.WebApi
    public synchronized void setClientType(String str) {
        this._clientType = str;
    }

    @Override // com.google.firebase.crashlytics.buildtools.api.WebApi
    public synchronized void setClientVersion(String str) {
        this._clientVersion = str;
    }

    private void sendFile(URL url, File file, Map<String, String> map) throws IOException {
        Buildtools.logD("PUT file: " + file + " to URL: " + url);
        try {
            HttpPut httpPut = new HttpPut(url.toURI());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpPut.setHeader(entry.getKey(), entry.getValue());
            }
            applyCommonHeadersTo(httpPut);
            httpPut.setEntity(new FileEntity(file));
            ProxySettings proxySettingsCreate = this._proxyFactory.create(ProtocolScheme.getType(url));
            HttpClient clientFor = proxySettingsCreate.getClientFor();
            httpPut.setConfig(proxySettingsCreate.getConfig());
            Buildtools.logD("PUT headers:");
            for (Header header : httpPut.getAllHeaders()) {
                Buildtools.logD("\t" + header.getName() + " = " + header.getValue());
            }
            HttpResponse httpResponseExecute = clientFor.execute(httpPut);
            int statusCode = httpResponseExecute.getStatusLine().getStatusCode();
            Buildtools.logD("PUT response: [reqId=" + getRequestId(httpResponseExecute) + "] " + statusCode);
            if (!isSuccess(statusCode)) {
                throw new IOException("Unknown error while sending file, check network [" + file + "; response: " + httpResponseExecute.getStatusLine().getStatusCode() + HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR + httpResponseExecute.getStatusLine() + "]");
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.api.WebApi
    public void uploadFile(URL url, File file) throws IOException {
        sendFile(url, file, new HashMap());
    }

    @Override // com.google.firebase.crashlytics.buildtools.api.WebApi
    public void applyCommonHeadersTo(HttpRequestBase httpRequestBase) {
        String str = this._userAgent;
        if (str != null) {
            httpRequestBase.setHeader("User-Agent", str);
        }
        String str2 = this._clientType;
        if (str2 != null) {
            httpRequestBase.setHeader(Constants.Http.API_CLIENT_TYPE_HEADER, str2);
        }
        String str3 = this._clientVersion;
        if (str3 != null) {
            httpRequestBase.setHeader(Constants.Http.API_CLIENT_VERSION_HEADER, str3);
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.api.WebApi
    public String getCodeMappingApiUrl() {
        return this._codeMappingApiUrl;
    }

    private static String getRequestId(HttpResponse httpResponse) {
        Header firstHeader = httpResponse.getFirstHeader(Constants.Http.REQUEST_ID_HEADER);
        return firstHeader == null ? "null" : firstHeader.getValue();
    }
}
