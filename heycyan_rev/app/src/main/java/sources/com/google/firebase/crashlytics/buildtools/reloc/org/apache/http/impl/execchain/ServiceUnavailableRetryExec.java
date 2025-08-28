package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.execchain;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.Header;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ServiceUnavailableRetryStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.CloseableHttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpExecutionAware;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpRequestWrapper;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol.HttpClientContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoute;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.io.IOException;
import java.io.InterruptedIOException;

/* loaded from: classes2.dex */
public class ServiceUnavailableRetryExec implements ClientExecChain {
    private final Log log = LogFactory.getLog(getClass());
    private final ClientExecChain requestExecutor;
    private final ServiceUnavailableRetryStrategy retryStrategy;

    public ServiceUnavailableRetryExec(ClientExecChain clientExecChain, ServiceUnavailableRetryStrategy serviceUnavailableRetryStrategy) {
        Args.notNull(clientExecChain, "HTTP request executor");
        Args.notNull(serviceUnavailableRetryStrategy, "Retry strategy");
        this.requestExecutor = clientExecChain;
        this.retryStrategy = serviceUnavailableRetryStrategy;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.execchain.ClientExecChain
    public CloseableHttpResponse execute(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware) throws InterruptedException, HttpException, IOException {
        CloseableHttpResponse closeableHttpResponseExecute;
        Header[] allHeaders = httpRequestWrapper.getAllHeaders();
        int i = 1;
        while (true) {
            closeableHttpResponseExecute = this.requestExecutor.execute(httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware);
            try {
                if (!this.retryStrategy.retryRequest(closeableHttpResponseExecute, i, httpClientContext) || !RequestEntityProxy.isRepeatable(httpRequestWrapper)) {
                    break;
                }
                closeableHttpResponseExecute.close();
                long retryInterval = this.retryStrategy.getRetryInterval();
                if (retryInterval > 0) {
                    try {
                        this.log.trace("Wait for " + retryInterval);
                        Thread.sleep(retryInterval);
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException();
                    }
                }
                httpRequestWrapper.setHeaders(allHeaders);
                i++;
            } catch (RuntimeException e) {
                closeableHttpResponseExecute.close();
                throw e;
            }
        }
        return closeableHttpResponseExecute;
    }
}
