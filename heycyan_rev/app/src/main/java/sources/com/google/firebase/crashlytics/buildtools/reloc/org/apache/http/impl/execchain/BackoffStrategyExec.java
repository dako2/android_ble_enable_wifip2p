package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.execchain;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.BackoffManager;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ConnectionBackoffStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.CloseableHttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpExecutionAware;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpRequestWrapper;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol.HttpClientContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoute;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;

/* loaded from: classes2.dex */
public class BackoffStrategyExec implements ClientExecChain {
    private final BackoffManager backoffManager;
    private final ConnectionBackoffStrategy connectionBackoffStrategy;
    private final ClientExecChain requestExecutor;

    public BackoffStrategyExec(ClientExecChain clientExecChain, ConnectionBackoffStrategy connectionBackoffStrategy, BackoffManager backoffManager) {
        Args.notNull(clientExecChain, "HTTP client request executor");
        Args.notNull(connectionBackoffStrategy, "Connection backoff strategy");
        Args.notNull(backoffManager, "Backoff manager");
        this.requestExecutor = clientExecChain;
        this.connectionBackoffStrategy = connectionBackoffStrategy;
        this.backoffManager = backoffManager;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.execchain.ClientExecChain
    public CloseableHttpResponse execute(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware) throws HttpException, IOException {
        Args.notNull(httpRoute, "HTTP route");
        Args.notNull(httpRequestWrapper, "HTTP request");
        Args.notNull(httpClientContext, "HTTP context");
        try {
            CloseableHttpResponse closeableHttpResponseExecute = this.requestExecutor.execute(httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware);
            if (this.connectionBackoffStrategy.shouldBackoff(closeableHttpResponseExecute)) {
                this.backoffManager.backOff(httpRoute);
            } else {
                this.backoffManager.probe(httpRoute);
            }
            return closeableHttpResponseExecute;
        } catch (Exception e) {
            if (this.connectionBackoffStrategy.shouldBackoff(e)) {
                this.backoffManager.backOff(httpRoute);
            }
            if (e instanceof RuntimeException) {
                throw ((RuntimeException) e);
            }
            if (e instanceof HttpException) {
                throw ((HttpException) e);
            }
            if (e instanceof IOException) {
                throw ((IOException) e);
            }
            throw new UndeclaredThrowableException(e);
        }
    }
}
