package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpUriRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ClientConnectionManager;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.HttpParams;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import java.io.IOException;

/* loaded from: classes2.dex */
public interface HttpClient {
    HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) throws IOException;

    HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException;

    HttpResponse execute(HttpUriRequest httpUriRequest) throws IOException;

    HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException;

    <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) throws IOException;

    <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException;

    <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) throws IOException;

    <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException;

    @Deprecated
    ClientConnectionManager getConnectionManager();

    @Deprecated
    HttpParams getParams();
}
