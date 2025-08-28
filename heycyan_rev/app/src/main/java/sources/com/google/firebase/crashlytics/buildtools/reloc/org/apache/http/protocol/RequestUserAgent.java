package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequestInterceptor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.CoreProtocolPNames;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.io.IOException;

/* loaded from: classes2.dex */
public class RequestUserAgent implements HttpRequestInterceptor {
    private final String userAgent;

    public RequestUserAgent(String str) {
        this.userAgent = str;
    }

    public RequestUserAgent() {
        this(null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequestInterceptor
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        Args.notNull(httpRequest, "HTTP request");
        if (httpRequest.containsHeader("User-Agent")) {
            return;
        }
        HttpParams params = httpRequest.getParams();
        String str = params != null ? (String) params.getParameter(CoreProtocolPNames.USER_AGENT) : null;
        if (str == null) {
            str = this.userAgent;
        }
        if (str != null) {
            httpRequest.addHeader("User-Agent", str);
        }
    }
}
