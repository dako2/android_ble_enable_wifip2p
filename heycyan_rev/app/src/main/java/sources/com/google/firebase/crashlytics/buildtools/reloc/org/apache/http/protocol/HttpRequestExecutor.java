package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntityEnclosingRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpVersion;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ProtocolException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ProtocolVersion;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.io.IOException;

/* loaded from: classes2.dex */
public class HttpRequestExecutor {
    public static final int DEFAULT_WAIT_FOR_CONTINUE = 3000;
    private final int waitForContinue;

    public HttpRequestExecutor(int i) {
        this.waitForContinue = Args.positive(i, "Wait for continue time");
    }

    public HttpRequestExecutor() {
        this(3000);
    }

    protected boolean canResponseHaveBody(HttpRequest httpRequest, HttpResponse httpResponse) {
        int statusCode;
        return ("HEAD".equalsIgnoreCase(httpRequest.getRequestLine().getMethod()) || (statusCode = httpResponse.getStatusLine().getStatusCode()) < 200 || statusCode == 204 || statusCode == 304 || statusCode == 205) ? false : true;
    }

    public HttpResponse execute(HttpRequest httpRequest, HttpClientConnection httpClientConnection, HttpContext httpContext) throws HttpException, IOException {
        Args.notNull(httpRequest, "HTTP request");
        Args.notNull(httpClientConnection, "Client connection");
        Args.notNull(httpContext, "HTTP context");
        try {
            HttpResponse httpResponseDoSendRequest = doSendRequest(httpRequest, httpClientConnection, httpContext);
            return httpResponseDoSendRequest == null ? doReceiveResponse(httpRequest, httpClientConnection, httpContext) : httpResponseDoSendRequest;
        } catch (HttpException e) {
            closeConnection(httpClientConnection);
            throw e;
        } catch (IOException e2) {
            closeConnection(httpClientConnection);
            throw e2;
        } catch (RuntimeException e3) {
            closeConnection(httpClientConnection);
            throw e3;
        }
    }

    private static void closeConnection(HttpClientConnection httpClientConnection) {
        try {
            httpClientConnection.close();
        } catch (IOException unused) {
        }
    }

    public void preProcess(HttpRequest httpRequest, HttpProcessor httpProcessor, HttpContext httpContext) throws HttpException, IOException {
        Args.notNull(httpRequest, "HTTP request");
        Args.notNull(httpProcessor, "HTTP processor");
        Args.notNull(httpContext, "HTTP context");
        httpContext.setAttribute("http.request", httpRequest);
        httpProcessor.process(httpRequest, httpContext);
    }

    protected HttpResponse doSendRequest(HttpRequest httpRequest, HttpClientConnection httpClientConnection, HttpContext httpContext) throws HttpException, IOException {
        Args.notNull(httpRequest, "HTTP request");
        Args.notNull(httpClientConnection, "Client connection");
        Args.notNull(httpContext, "HTTP context");
        httpContext.setAttribute("http.connection", httpClientConnection);
        httpContext.setAttribute("http.request_sent", Boolean.FALSE);
        httpClientConnection.sendRequestHeader(httpRequest);
        HttpResponse httpResponse = null;
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            ProtocolVersion protocolVersion = httpRequest.getRequestLine().getProtocolVersion();
            HttpEntityEnclosingRequest httpEntityEnclosingRequest = (HttpEntityEnclosingRequest) httpRequest;
            boolean z = true;
            if (httpEntityEnclosingRequest.expectContinue() && !protocolVersion.lessEquals(HttpVersion.HTTP_1_0)) {
                httpClientConnection.flush();
                if (httpClientConnection.isResponseAvailable(this.waitForContinue)) {
                    HttpResponse httpResponseReceiveResponseHeader = httpClientConnection.receiveResponseHeader();
                    if (canResponseHaveBody(httpRequest, httpResponseReceiveResponseHeader)) {
                        httpClientConnection.receiveResponseEntity(httpResponseReceiveResponseHeader);
                    }
                    int statusCode = httpResponseReceiveResponseHeader.getStatusLine().getStatusCode();
                    if (statusCode >= 200) {
                        z = false;
                        httpResponse = httpResponseReceiveResponseHeader;
                    } else if (statusCode != 100) {
                        throw new ProtocolException("Unexpected response: " + httpResponseReceiveResponseHeader.getStatusLine());
                    }
                }
            }
            if (z) {
                httpClientConnection.sendRequestEntity(httpEntityEnclosingRequest);
            }
        }
        httpClientConnection.flush();
        httpContext.setAttribute("http.request_sent", Boolean.TRUE);
        return httpResponse;
    }

    protected HttpResponse doReceiveResponse(HttpRequest httpRequest, HttpClientConnection httpClientConnection, HttpContext httpContext) throws HttpException, IOException {
        Args.notNull(httpRequest, "HTTP request");
        Args.notNull(httpClientConnection, "Client connection");
        Args.notNull(httpContext, "HTTP context");
        HttpResponse httpResponseReceiveResponseHeader = null;
        int statusCode = 0;
        while (true) {
            if (httpResponseReceiveResponseHeader != null && statusCode >= 200) {
                return httpResponseReceiveResponseHeader;
            }
            httpResponseReceiveResponseHeader = httpClientConnection.receiveResponseHeader();
            statusCode = httpResponseReceiveResponseHeader.getStatusLine().getStatusCode();
            if (statusCode < 100) {
                throw new ProtocolException("Invalid response: " + httpResponseReceiveResponseHeader.getStatusLine());
            }
            if (canResponseHaveBody(httpRequest, httpResponseReceiveResponseHeader)) {
                httpClientConnection.receiveResponseEntity(httpResponseReceiveResponseHeader);
            }
        }
    }

    public void postProcess(HttpResponse httpResponse, HttpProcessor httpProcessor, HttpContext httpContext) throws HttpException, IOException {
        Args.notNull(httpResponse, "HTTP response");
        Args.notNull(httpProcessor, "HTTP processor");
        Args.notNull(httpContext, "HTTP context");
        httpContext.setAttribute("http.response", httpResponse);
        httpProcessor.process(httpResponse, httpContext);
    }
}
