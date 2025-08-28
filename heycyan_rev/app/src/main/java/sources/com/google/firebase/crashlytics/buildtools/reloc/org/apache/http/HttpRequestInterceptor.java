package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import java.io.IOException;

/* loaded from: classes2.dex */
public interface HttpRequestInterceptor {
    void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException;
}
