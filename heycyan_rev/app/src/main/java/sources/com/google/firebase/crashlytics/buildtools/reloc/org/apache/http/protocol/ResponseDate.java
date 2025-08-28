package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponseInterceptor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.io.IOException;

/* loaded from: classes2.dex */
public class ResponseDate implements HttpResponseInterceptor {
    private static final HttpDateGenerator DATE_GENERATOR = new HttpDateGenerator();

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponseInterceptor
    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        Args.notNull(httpResponse, "HTTP response");
        if (httpResponse.getStatusLine().getStatusCode() < 200 || httpResponse.containsHeader("Date")) {
            return;
        }
        httpResponse.setHeader("Date", DATE_GENERATOR.getCurrentDate());
    }
}
