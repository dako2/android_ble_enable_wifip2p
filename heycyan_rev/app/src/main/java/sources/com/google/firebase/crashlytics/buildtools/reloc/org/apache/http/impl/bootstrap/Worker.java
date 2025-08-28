package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.bootstrap;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ExceptionLogger;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpServerConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.BasicHttpContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpCoreContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpService;
import java.io.IOException;

/* loaded from: classes2.dex */
class Worker implements Runnable {
    private final HttpServerConnection conn;
    private final ExceptionLogger exceptionLogger;
    private final HttpService httpservice;

    Worker(HttpService httpService, HttpServerConnection httpServerConnection, ExceptionLogger exceptionLogger) {
        this.httpservice = httpService;
        this.conn = httpServerConnection;
        this.exceptionLogger = exceptionLogger;
    }

    public HttpServerConnection getConnection() {
        return this.conn;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                try {
                    BasicHttpContext basicHttpContext = new BasicHttpContext();
                    HttpCoreContext httpCoreContextAdapt = HttpCoreContext.adapt(basicHttpContext);
                    while (!Thread.interrupted() && this.conn.isOpen()) {
                        this.httpservice.handleRequest(this.conn, httpCoreContextAdapt);
                        basicHttpContext.clear();
                    }
                    this.conn.close();
                    this.conn.shutdown();
                } catch (Exception e) {
                    this.exceptionLogger.log(e);
                    this.conn.shutdown();
                }
            } catch (Throwable th) {
                try {
                    this.conn.shutdown();
                } catch (IOException e2) {
                    this.exceptionLogger.log(e2);
                }
                throw th;
            }
        } catch (IOException e3) {
            this.exceptionLogger.log(e3);
        }
    }
}
