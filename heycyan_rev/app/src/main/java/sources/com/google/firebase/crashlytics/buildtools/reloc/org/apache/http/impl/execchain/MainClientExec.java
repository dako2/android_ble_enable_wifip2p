package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.execchain;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.LogFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ConnectionReuseStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpClientConnection;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntityEnclosingRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthProtocolState;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthState;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.AuthenticationStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.NonRepeatableRequestException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.UserTokenHandler;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.config.RequestConfig;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.CloseableHttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpExecutionAware;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpRequestWrapper;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol.HttpClientContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ConnectionKeepAliveStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ConnectionRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.HttpClientConnectionManager;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.BasicRouteDirector;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoute;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRouteDirector;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.RouteTracker;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.BufferedHttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.auth.HttpAuthenticator;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.ConnectionShutdownException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicHttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpProcessor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpRequestExecutor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.ImmutableHttpProcessor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.RequestTargetHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class MainClientExec implements ClientExecChain {
    private final HttpAuthenticator authenticator;
    private final HttpClientConnectionManager connManager;
    private final ConnectionKeepAliveStrategy keepAliveStrategy;
    private final Log log;
    private final AuthenticationStrategy proxyAuthStrategy;
    private final HttpProcessor proxyHttpProcessor;
    private final HttpRequestExecutor requestExecutor;
    private final ConnectionReuseStrategy reuseStrategy;
    private final HttpRouteDirector routeDirector;
    private final AuthenticationStrategy targetAuthStrategy;
    private final UserTokenHandler userTokenHandler;

    public MainClientExec(HttpRequestExecutor httpRequestExecutor, HttpClientConnectionManager httpClientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy, HttpProcessor httpProcessor, AuthenticationStrategy authenticationStrategy, AuthenticationStrategy authenticationStrategy2, UserTokenHandler userTokenHandler) {
        this.log = LogFactory.getLog(getClass());
        Args.notNull(httpRequestExecutor, "HTTP request executor");
        Args.notNull(httpClientConnectionManager, "Client connection manager");
        Args.notNull(connectionReuseStrategy, "Connection reuse strategy");
        Args.notNull(connectionKeepAliveStrategy, "Connection keep alive strategy");
        Args.notNull(httpProcessor, "Proxy HTTP processor");
        Args.notNull(authenticationStrategy, "Target authentication strategy");
        Args.notNull(authenticationStrategy2, "Proxy authentication strategy");
        Args.notNull(userTokenHandler, "User token handler");
        this.authenticator = new HttpAuthenticator();
        this.routeDirector = new BasicRouteDirector();
        this.requestExecutor = httpRequestExecutor;
        this.connManager = httpClientConnectionManager;
        this.reuseStrategy = connectionReuseStrategy;
        this.keepAliveStrategy = connectionKeepAliveStrategy;
        this.proxyHttpProcessor = httpProcessor;
        this.targetAuthStrategy = authenticationStrategy;
        this.proxyAuthStrategy = authenticationStrategy2;
        this.userTokenHandler = userTokenHandler;
    }

    public MainClientExec(HttpRequestExecutor httpRequestExecutor, HttpClientConnectionManager httpClientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy, AuthenticationStrategy authenticationStrategy, AuthenticationStrategy authenticationStrategy2, UserTokenHandler userTokenHandler) {
        this(httpRequestExecutor, httpClientConnectionManager, connectionReuseStrategy, connectionKeepAliveStrategy, new ImmutableHttpProcessor(new RequestTargetHost()), authenticationStrategy, authenticationStrategy2, userTokenHandler);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:159:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x02d0 A[Catch: Error -> 0x00b7, ConnectionShutdownException -> 0x00ce, RuntimeException -> 0x0348, IOException -> 0x034a, HttpException -> 0x034d, TryCatch #22 {HttpException -> 0x034d, blocks: (B:147:0x029a, B:162:0x02ca, B:164:0x02d0, B:166:0x02da, B:177:0x0311, B:179:0x031b, B:180:0x031e, B:182:0x0324, B:183:0x0327, B:167:0x02de, B:169:0x02e9, B:171:0x02ef, B:172:0x02f9, B:174:0x0301, B:176:0x0307, B:186:0x033c, B:195:0x0354, B:196:0x0357, B:198:0x035d, B:201:0x0364, B:203:0x036a, B:160:0x02b9), top: B:263:0x029a }] */
    /* JADX WARN: Removed duplicated region for block: B:226:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x03ad  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x03c8  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0245 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0338 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:290:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:291:? A[SYNTHETIC] */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.execchain.ClientExecChain
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public CloseableHttpResponse execute(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware) throws HttpException, IOException {
        RuntimeException runtimeException;
        ConnectionHolder connectionHolder;
        AuthState authState;
        IOException iOException;
        HttpException httpException;
        ConnectionHolder connectionHolder2;
        int i;
        int i2;
        AuthState authState2;
        HttpClientConnection httpClientConnection;
        String str;
        Object obj;
        HttpResponse response;
        Object userToken;
        AuthState authState3;
        HttpResponse httpResponseExecute;
        long keepAliveDuration;
        HttpResponse httpResponse;
        String str2;
        HttpResponse httpResponse2;
        HttpRoute httpRoute2 = httpRoute;
        HttpExecutionAware httpExecutionAware2 = httpExecutionAware;
        Args.notNull(httpRoute2, "HTTP route");
        Args.notNull(httpRequestWrapper, "HTTP request");
        Args.notNull(httpClientContext, "HTTP context");
        AuthState targetAuthState = httpClientContext.getTargetAuthState();
        if (targetAuthState == null) {
            targetAuthState = new AuthState();
            httpClientContext.setAttribute("http.auth.target-scope", targetAuthState);
        }
        AuthState authState4 = targetAuthState;
        AuthState proxyAuthState = httpClientContext.getProxyAuthState();
        if (proxyAuthState == null) {
            proxyAuthState = new AuthState();
            httpClientContext.setAttribute("http.auth.proxy-scope", proxyAuthState);
        }
        AuthState authState5 = proxyAuthState;
        if (httpRequestWrapper instanceof HttpEntityEnclosingRequest) {
            RequestEntityProxy.enhance((HttpEntityEnclosingRequest) httpRequestWrapper);
        }
        Object userToken2 = httpClientContext.getUserToken();
        ConnectionRequest connectionRequestRequestConnection = this.connManager.requestConnection(httpRoute2, userToken2);
        String str3 = "Request aborted";
        if (httpExecutionAware2 != null) {
            if (httpExecutionAware.isAborted()) {
                connectionRequestRequestConnection.cancel();
                throw new RequestAbortedException("Request aborted");
            }
            httpExecutionAware2.setCancellable(connectionRequestRequestConnection);
        }
        RequestConfig requestConfig = httpClientContext.getRequestConfig();
        try {
            int connectionRequestTimeout = requestConfig.getConnectionRequestTimeout();
            HttpClientConnection httpClientConnection2 = connectionRequestRequestConnection.get(connectionRequestTimeout > 0 ? connectionRequestTimeout : 0L, TimeUnit.MILLISECONDS);
            httpClientContext.setAttribute("http.connection", httpClientConnection2);
            if (requestConfig.isStaleConnectionCheckEnabled() && httpClientConnection2.isOpen()) {
                this.log.debug("Stale connection check");
                if (httpClientConnection2.isStale()) {
                    this.log.debug("Stale connection detected");
                    httpClientConnection2.close();
                }
            }
            ConnectionHolder connectionHolder3 = new ConnectionHolder(this.log, this.connManager, httpClientConnection2);
            if (httpExecutionAware2 != null) {
                try {
                    try {
                        httpExecutionAware2.setCancellable(connectionHolder3);
                    } catch (ConnectionShutdownException e) {
                        InterruptedIOException interruptedIOException = new InterruptedIOException("Connection has been shut down");
                        interruptedIOException.initCause(e);
                        throw interruptedIOException;
                    } catch (Error e2) {
                        this.connManager.shutdown();
                        throw e2;
                    }
                } catch (HttpException e3) {
                    httpException = e3;
                    connectionHolder = connectionHolder3;
                    connectionHolder.abortConnection();
                    throw httpException;
                } catch (IOException e4) {
                    iOException = e4;
                    connectionHolder = connectionHolder3;
                    authState = authState5;
                    connectionHolder.abortConnection();
                    if (authState.isConnectionBased()) {
                    }
                    if (authState4.isConnectionBased()) {
                    }
                } catch (RuntimeException e5) {
                    runtimeException = e5;
                    connectionHolder = connectionHolder3;
                    authState = authState5;
                    connectionHolder.abortConnection();
                    if (authState.isConnectionBased()) {
                    }
                    if (authState4.isConnectionBased()) {
                    }
                }
            }
            int i3 = 1;
            int i4 = 1;
            while (true) {
                if (i4 > i3 && !RequestEntityProxy.isRepeatable(httpRequestWrapper)) {
                    throw new NonRepeatableRequestException("Cannot retry request with a non-repeatable request entity.");
                }
                if (httpExecutionAware2 != null && httpExecutionAware.isAborted()) {
                    throw new RequestAbortedException(str3);
                }
                try {
                    if (httpClientConnection2.isOpen()) {
                        i = i4;
                        i2 = i3;
                        connectionHolder2 = connectionHolder3;
                        httpClientConnection = httpClientConnection2;
                        obj = userToken2;
                        authState = authState5;
                        str = str3;
                    } else {
                        try {
                            i = i4;
                            connectionHolder2 = connectionHolder3;
                            try {
                                try {
                                    this.log.debug("Opening connection " + httpRoute2);
                                    i2 = 1;
                                    authState2 = authState5;
                                    httpClientConnection = httpClientConnection2;
                                    authState = authState5;
                                    str = str3;
                                    obj = userToken2;
                                } catch (HttpException e6) {
                                    e = e6;
                                    httpException = e;
                                    connectionHolder = connectionHolder2;
                                    connectionHolder.abortConnection();
                                    throw httpException;
                                }
                            } catch (IOException e7) {
                                e = e7;
                                authState = authState5;
                                iOException = e;
                                connectionHolder = connectionHolder2;
                                connectionHolder.abortConnection();
                                if (authState.isConnectionBased()) {
                                }
                                if (authState4.isConnectionBased()) {
                                }
                            } catch (RuntimeException e8) {
                                e = e8;
                                authState = authState5;
                                runtimeException = e;
                                connectionHolder = connectionHolder2;
                                connectionHolder.abortConnection();
                                if (authState.isConnectionBased()) {
                                }
                                if (authState4.isConnectionBased()) {
                                }
                            }
                            try {
                                try {
                                    establishRoute(authState2, httpClientConnection2, httpRoute, httpRequestWrapper, httpClientContext);
                                } catch (TunnelRefusedException e9) {
                                    if (this.log.isDebugEnabled()) {
                                        this.log.debug(e9.getMessage());
                                    }
                                    response = e9.getResponse();
                                    connectionHolder = connectionHolder2;
                                    if (obj == null) {
                                        userToken = this.userTokenHandler.getUserToken(httpClientContext);
                                        httpClientContext.setAttribute("http.user-token", userToken);
                                    } else {
                                        userToken = obj;
                                    }
                                    if (userToken != null) {
                                        connectionHolder.setState(userToken);
                                    }
                                    HttpEntity entity = response.getEntity();
                                    if (entity != null && entity.isStreaming()) {
                                        return new HttpResponseProxy(response, connectionHolder);
                                    }
                                    connectionHolder.releaseConnection();
                                    return new HttpResponseProxy(response, null);
                                }
                            } catch (IOException e10) {
                                e = e10;
                                iOException = e;
                                connectionHolder = connectionHolder2;
                                connectionHolder.abortConnection();
                                if (authState.isConnectionBased()) {
                                }
                                if (authState4.isConnectionBased()) {
                                }
                            } catch (RuntimeException e11) {
                                e = e11;
                                runtimeException = e;
                                connectionHolder = connectionHolder2;
                                connectionHolder.abortConnection();
                                if (authState.isConnectionBased()) {
                                }
                                if (authState4.isConnectionBased()) {
                                }
                            }
                        } catch (HttpException e12) {
                            e = e12;
                            connectionHolder2 = connectionHolder3;
                        } catch (IOException e13) {
                            e = e13;
                            connectionHolder2 = connectionHolder3;
                        } catch (RuntimeException e14) {
                            e = e14;
                            connectionHolder2 = connectionHolder3;
                        }
                    }
                    try {
                        try {
                            int socketTimeout = requestConfig.getSocketTimeout();
                            if (socketTimeout >= 0) {
                                httpClientConnection.setSocketTimeout(socketTimeout);
                            }
                            if (httpExecutionAware2 != null && httpExecutionAware.isAborted()) {
                                throw new RequestAbortedException(str);
                            }
                            if (this.log.isDebugEnabled()) {
                                this.log.debug("Executing request " + httpRequestWrapper.getRequestLine());
                            }
                            if (!httpRequestWrapper.containsHeader("Authorization")) {
                                if (this.log.isDebugEnabled()) {
                                    this.log.debug("Target auth state: " + authState4.getState());
                                }
                                this.authenticator.generateAuthResponse(httpRequestWrapper, authState4, httpClientContext);
                            }
                        } catch (HttpException e15) {
                            e = e15;
                            connectionHolder = connectionHolder2;
                        }
                    } catch (IOException e16) {
                        e = e16;
                    } catch (RuntimeException e17) {
                        e = e17;
                    }
                    try {
                        try {
                            if (!httpRequestWrapper.containsHeader("Proxy-Authorization")) {
                                try {
                                    if (!httpRoute.isTunnelled()) {
                                        if (this.log.isDebugEnabled()) {
                                            this.log.debug("Proxy auth state: " + authState.getState());
                                        }
                                        authState3 = authState;
                                        try {
                                            this.authenticator.generateAuthResponse(httpRequestWrapper, authState3, httpClientContext);
                                        } catch (IOException e18) {
                                            iOException = e18;
                                            authState = authState3;
                                            connectionHolder = connectionHolder2;
                                            connectionHolder.abortConnection();
                                            if (authState.isConnectionBased()) {
                                            }
                                            if (authState4.isConnectionBased()) {
                                            }
                                        } catch (RuntimeException e19) {
                                            runtimeException = e19;
                                            authState = authState3;
                                            connectionHolder = connectionHolder2;
                                            connectionHolder.abortConnection();
                                            if (authState.isConnectionBased()) {
                                            }
                                            if (authState4.isConnectionBased()) {
                                            }
                                        }
                                    }
                                    httpResponseExecute = this.requestExecutor.execute(httpRequestWrapper, httpClientConnection, httpClientContext);
                                    if (!this.reuseStrategy.keepAlive(httpResponseExecute, httpClientContext)) {
                                        try {
                                            keepAliveDuration = this.keepAliveStrategy.getKeepAliveDuration(httpResponseExecute, httpClientContext);
                                            if (this.log.isDebugEnabled()) {
                                                if (keepAliveDuration > 0) {
                                                    str2 = "for " + keepAliveDuration + HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR + TimeUnit.MILLISECONDS;
                                                } else {
                                                    str2 = "indefinitely";
                                                }
                                                httpResponse = httpResponseExecute;
                                                this.log.debug("Connection can be kept alive " + str2);
                                            } else {
                                                httpResponse = httpResponseExecute;
                                            }
                                            connectionHolder = connectionHolder2;
                                        } catch (IOException e20) {
                                            e = e20;
                                            connectionHolder = connectionHolder2;
                                        } catch (RuntimeException e21) {
                                            e = e21;
                                            connectionHolder = connectionHolder2;
                                        }
                                        try {
                                            try {
                                                connectionHolder.setValidFor(keepAliveDuration, TimeUnit.MILLISECONDS);
                                                connectionHolder.markReusable();
                                            } catch (HttpException e22) {
                                                e = e22;
                                                httpException = e;
                                                connectionHolder.abortConnection();
                                                throw httpException;
                                            }
                                        } catch (IOException e23) {
                                            e = e23;
                                            iOException = e;
                                            authState = authState3;
                                            connectionHolder.abortConnection();
                                            if (authState.isConnectionBased()) {
                                            }
                                            if (authState4.isConnectionBased()) {
                                            }
                                        } catch (RuntimeException e24) {
                                            e = e24;
                                            runtimeException = e;
                                            authState = authState3;
                                            connectionHolder.abortConnection();
                                            if (authState.isConnectionBased()) {
                                            }
                                            if (authState4.isConnectionBased()) {
                                            }
                                        }
                                    } else {
                                        httpResponse = httpResponseExecute;
                                        connectionHolder = connectionHolder2;
                                        try {
                                            connectionHolder.markNonReusable();
                                        } catch (IOException e25) {
                                            e = e25;
                                            authState = authState3;
                                            iOException = e;
                                            connectionHolder.abortConnection();
                                            if (authState.isConnectionBased()) {
                                                authState.reset();
                                            }
                                            if (authState4.isConnectionBased()) {
                                                authState4.reset();
                                                throw iOException;
                                            }
                                            throw iOException;
                                        } catch (RuntimeException e26) {
                                            e = e26;
                                            authState = authState3;
                                            runtimeException = e;
                                            connectionHolder.abortConnection();
                                            if (authState.isConnectionBased()) {
                                                authState.reset();
                                            }
                                            if (authState4.isConnectionBased()) {
                                                authState4.reset();
                                                throw runtimeException;
                                            }
                                            throw runtimeException;
                                        }
                                    }
                                    httpResponse2 = httpResponse;
                                    authState = authState3;
                                    if (needAuthentication(authState4, authState3, httpRoute, httpResponse2, httpClientContext)) {
                                        response = httpResponse2;
                                        break;
                                    }
                                    HttpEntity entity2 = httpResponse2.getEntity();
                                    if (connectionHolder.isReusable()) {
                                        EntityUtils.consume(entity2);
                                    } else {
                                        httpClientConnection.close();
                                        if (authState.getState() == AuthProtocolState.SUCCESS && authState.isConnectionBased()) {
                                            this.log.debug("Resetting proxy auth state");
                                            authState.reset();
                                        }
                                        if (authState4.getState() == AuthProtocolState.SUCCESS && authState4.isConnectionBased()) {
                                            this.log.debug("Resetting target auth state");
                                            authState4.reset();
                                        }
                                    }
                                    HttpRequest original = httpRequestWrapper.getOriginal();
                                    if (!original.containsHeader("Authorization")) {
                                        httpRequestWrapper.removeHeaders("Authorization");
                                    }
                                    if (!original.containsHeader("Proxy-Authorization")) {
                                        httpRequestWrapper.removeHeaders("Proxy-Authorization");
                                    }
                                    i4 = i + 1;
                                    httpClientConnection2 = httpClientConnection;
                                    connectionHolder3 = connectionHolder;
                                    str3 = str;
                                    i3 = i2;
                                    authState5 = authState;
                                    userToken2 = obj;
                                    httpRoute2 = httpRoute;
                                    httpExecutionAware2 = httpExecutionAware;
                                } catch (IOException e27) {
                                    e = e27;
                                    iOException = e;
                                    connectionHolder = connectionHolder2;
                                    connectionHolder.abortConnection();
                                    if (authState.isConnectionBased()) {
                                    }
                                    if (authState4.isConnectionBased()) {
                                    }
                                } catch (RuntimeException e28) {
                                    e = e28;
                                    runtimeException = e;
                                    connectionHolder = connectionHolder2;
                                    connectionHolder.abortConnection();
                                    if (authState.isConnectionBased()) {
                                    }
                                    if (authState4.isConnectionBased()) {
                                    }
                                }
                            }
                            if (needAuthentication(authState4, authState3, httpRoute, httpResponse2, httpClientContext)) {
                            }
                        } catch (IOException e29) {
                            e = e29;
                            iOException = e;
                            connectionHolder.abortConnection();
                            if (authState.isConnectionBased()) {
                            }
                            if (authState4.isConnectionBased()) {
                            }
                        } catch (RuntimeException e30) {
                            e = e30;
                            runtimeException = e;
                            connectionHolder.abortConnection();
                            if (authState.isConnectionBased()) {
                            }
                            if (authState4.isConnectionBased()) {
                            }
                        }
                        httpResponseExecute = this.requestExecutor.execute(httpRequestWrapper, httpClientConnection, httpClientContext);
                        if (!this.reuseStrategy.keepAlive(httpResponseExecute, httpClientContext)) {
                        }
                        httpResponse2 = httpResponse;
                        authState = authState3;
                    } catch (IOException e31) {
                        e = e31;
                        authState = authState3;
                        connectionHolder = connectionHolder2;
                        iOException = e;
                        connectionHolder.abortConnection();
                        if (authState.isConnectionBased()) {
                        }
                        if (authState4.isConnectionBased()) {
                        }
                    } catch (RuntimeException e32) {
                        e = e32;
                        authState = authState3;
                        connectionHolder = connectionHolder2;
                        runtimeException = e;
                        connectionHolder.abortConnection();
                        if (authState.isConnectionBased()) {
                        }
                        if (authState4.isConnectionBased()) {
                        }
                    }
                    authState3 = authState;
                } catch (HttpException e33) {
                    e = e33;
                    connectionHolder = connectionHolder3;
                } catch (IOException e34) {
                    e = e34;
                    connectionHolder = connectionHolder3;
                    authState = authState5;
                } catch (RuntimeException e35) {
                    e = e35;
                    connectionHolder = connectionHolder3;
                    authState = authState5;
                }
            }
        } catch (InterruptedException e36) {
            Thread.currentThread().interrupt();
            throw new RequestAbortedException("Request aborted", e36);
        } catch (ExecutionException e37) {
            ExecutionException executionException = e37;
            Throwable cause = executionException.getCause();
            if (cause != null) {
                executionException = cause;
            }
            throw new RequestAbortedException("Request execution failed", executionException);
        }
    }

    void establishRoute(AuthState authState, HttpClientConnection httpClientConnection, HttpRoute httpRoute, HttpRequest httpRequest, HttpClientContext httpClientContext) throws HttpException, IOException {
        int iNextStep;
        int connectTimeout = httpClientContext.getRequestConfig().getConnectTimeout();
        RouteTracker routeTracker = new RouteTracker(httpRoute);
        do {
            HttpRoute route = routeTracker.toRoute();
            iNextStep = this.routeDirector.nextStep(httpRoute, route);
            switch (iNextStep) {
                case -1:
                    throw new HttpException("Unable to establish route: planned = " + httpRoute + "; current = " + route);
                case 0:
                    this.connManager.routeComplete(httpClientConnection, httpRoute, httpClientContext);
                    break;
                case 1:
                    this.connManager.connect(httpClientConnection, httpRoute, connectTimeout > 0 ? connectTimeout : 0, httpClientContext);
                    routeTracker.connectTarget(httpRoute.isSecure());
                    break;
                case 2:
                    this.connManager.connect(httpClientConnection, httpRoute, connectTimeout > 0 ? connectTimeout : 0, httpClientContext);
                    routeTracker.connectProxy(httpRoute.getProxyHost(), false);
                    break;
                case 3:
                    boolean zCreateTunnelToTarget = createTunnelToTarget(authState, httpClientConnection, httpRoute, httpRequest, httpClientContext);
                    this.log.debug("Tunnel to target created.");
                    routeTracker.tunnelTarget(zCreateTunnelToTarget);
                    break;
                case 4:
                    int hopCount = route.getHopCount() - 1;
                    boolean zCreateTunnelToProxy = createTunnelToProxy(httpRoute, hopCount, httpClientContext);
                    this.log.debug("Tunnel to proxy created.");
                    routeTracker.tunnelProxy(httpRoute.getHopTarget(hopCount), zCreateTunnelToProxy);
                    break;
                case 5:
                    this.connManager.upgrade(httpClientConnection, httpRoute, httpClientContext);
                    routeTracker.layerProtocol(httpRoute.isSecure());
                    break;
                default:
                    throw new IllegalStateException("Unknown step indicator " + iNextStep + " from RouteDirector.");
            }
        } while (iNextStep > 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x009a, code lost:
    
        if (r16.reuseStrategy.keepAlive(r7, r21) == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x009c, code lost:
    
        r16.log.debug("Connection kept alive");
        com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils.consume(r7.getEntity());
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00ab, code lost:
    
        r18.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean createTunnelToTarget(AuthState authState, HttpClientConnection httpClientConnection, HttpRoute httpRoute, HttpRequest httpRequest, HttpClientContext httpClientContext) throws HttpException, IOException {
        HttpResponse httpResponse;
        RequestConfig requestConfig = httpClientContext.getRequestConfig();
        int connectTimeout = requestConfig.getConnectTimeout();
        HttpHost targetHost = httpRoute.getTargetHost();
        HttpHost proxyHost = httpRoute.getProxyHost();
        BasicHttpRequest basicHttpRequest = new BasicHttpRequest("CONNECT", targetHost.toHostString(), httpRequest.getProtocolVersion());
        this.requestExecutor.preProcess(basicHttpRequest, this.proxyHttpProcessor, httpClientContext);
        while (true) {
            HttpResponse httpResponse2 = null;
            while (true) {
                if (httpResponse2 == null) {
                    if (!httpClientConnection.isOpen()) {
                        this.connManager.connect(httpClientConnection, httpRoute, connectTimeout > 0 ? connectTimeout : 0, httpClientContext);
                    }
                    basicHttpRequest.removeHeaders("Proxy-Authorization");
                    this.authenticator.generateAuthResponse(basicHttpRequest, authState, httpClientContext);
                    HttpResponse httpResponseExecute = this.requestExecutor.execute(basicHttpRequest, httpClientConnection, httpClientContext);
                    this.requestExecutor.postProcess(httpResponseExecute, this.proxyHttpProcessor, httpClientContext);
                    if (httpResponseExecute.getStatusLine().getStatusCode() < 200) {
                        throw new HttpException("Unexpected response to CONNECT request: " + httpResponseExecute.getStatusLine());
                    }
                    if (!requestConfig.isAuthenticationEnabled()) {
                        httpResponse = httpResponseExecute;
                    } else {
                        if (this.authenticator.isAuthenticationRequested(proxyHost, httpResponseExecute, this.proxyAuthStrategy, authState, httpClientContext) && this.authenticator.handleAuthChallenge(proxyHost, httpResponseExecute, this.proxyAuthStrategy, authState, httpClientContext)) {
                            break;
                        }
                        httpResponse = httpResponseExecute;
                    }
                    httpResponse2 = httpResponse;
                } else {
                    if (httpResponse2.getStatusLine().getStatusCode() <= 299) {
                        return false;
                    }
                    HttpEntity entity = httpResponse2.getEntity();
                    if (entity != null) {
                        httpResponse2.setEntity(new BufferedHttpEntity(entity));
                    }
                    httpClientConnection.close();
                    throw new TunnelRefusedException("CONNECT refused by proxy: " + httpResponse2.getStatusLine(), httpResponse2);
                }
            }
        }
    }

    private boolean createTunnelToProxy(HttpRoute httpRoute, int i, HttpClientContext httpClientContext) throws HttpException {
        throw new HttpException("Proxy chains are not supported.");
    }

    private boolean needAuthentication(AuthState authState, AuthState authState2, HttpRoute httpRoute, HttpResponse httpResponse, HttpClientContext httpClientContext) {
        if (!httpClientContext.getRequestConfig().isAuthenticationEnabled()) {
            return false;
        }
        HttpHost targetHost = httpClientContext.getTargetHost();
        if (targetHost == null) {
            targetHost = httpRoute.getTargetHost();
        }
        if (targetHost.getPort() < 0) {
            targetHost = new HttpHost(targetHost.getHostName(), httpRoute.getTargetHost().getPort(), targetHost.getSchemeName());
        }
        boolean zIsAuthenticationRequested = this.authenticator.isAuthenticationRequested(targetHost, httpResponse, this.targetAuthStrategy, authState, httpClientContext);
        HttpHost proxyHost = httpRoute.getProxyHost();
        if (proxyHost == null) {
            proxyHost = httpRoute.getTargetHost();
        }
        boolean zIsAuthenticationRequested2 = this.authenticator.isAuthenticationRequested(proxyHost, httpResponse, this.proxyAuthStrategy, authState2, httpClientContext);
        if (zIsAuthenticationRequested) {
            return this.authenticator.handleAuthChallenge(targetHost, httpResponse, this.targetAuthStrategy, authState, httpClientContext);
        }
        if (!zIsAuthenticationRequested2) {
            return false;
        }
        return this.authenticator.handleAuthChallenge(proxyHost, httpResponse, this.proxyAuthStrategy, authState2, httpClientContext);
    }
}
