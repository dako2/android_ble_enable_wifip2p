package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ConnectionReuseStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.Header;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequestInterceptor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponseInterceptor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthSchemeProvider;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.AuthenticationStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.BackoffManager;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ConnectionBackoffStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.CookieStore;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.CredentialsProvider;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpRequestRetryHandler;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.RedirectStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ServiceUnavailableRetryStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.UserTokenHandler;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.config.RequestConfig;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.entity.InputStreamFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol.RequestAcceptEncoding;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol.RequestAddCookies;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol.RequestAuthCache;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol.RequestClientConnControl;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol.RequestDefaultHeaders;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol.RequestExpectContinue;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol.ResponseContentEncoding;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.protocol.ResponseProcessCookies;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.config.ConnectionConfig;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.config.Lookup;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.config.Registry;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.config.RegistryBuilder;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.config.SocketConfig;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ConnectionKeepAliveStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.DnsResolver;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.HttpClientConnectionManager;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.SchemePortResolver;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.HttpRoutePlanner;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.socket.ConnectionSocketFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.socket.PlainConnectionSocketFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ssl.DefaultHostnameVerifier;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ssl.X509HostnameVerifier;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.util.PublicSuffixMatcher;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.util.PublicSuffixMatcherLoader;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.CookieSpecProvider;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.NoConnectionReuseStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.auth.BasicSchemeFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.auth.DigestSchemeFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.auth.KerberosSchemeFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.auth.NTLMSchemeFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.auth.SPNegoSchemeFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.DefaultRoutePlanner;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.DefaultSchemePortResolver;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.execchain.BackoffStrategyExec;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.execchain.ClientExecChain;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.execchain.MainClientExec;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.execchain.ProtocolExec;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.execchain.RedirectExec;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.execchain.RetryExec;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.execchain.ServiceUnavailableRetryExec;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpProcessor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpProcessorBuilder;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpRequestExecutor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.ImmutableHttpProcessor;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.RequestContent;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.RequestTargetHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.RequestUserAgent;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.ssl.SSLContexts;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.TextUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.VersionInfo;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes2.dex */
public class HttpClientBuilder {
    private boolean authCachingDisabled;
    private Lookup<AuthSchemeProvider> authSchemeRegistry;
    private boolean automaticRetriesDisabled;
    private BackoffManager backoffManager;
    private List<Closeable> closeables;
    private HttpClientConnectionManager connManager;
    private boolean connManagerShared;
    private ConnectionBackoffStrategy connectionBackoffStrategy;
    private boolean connectionStateDisabled;
    private boolean contentCompressionDisabled;
    private Map<String, InputStreamFactory> contentDecoderMap;
    private boolean cookieManagementDisabled;
    private Lookup<CookieSpecProvider> cookieSpecRegistry;
    private CookieStore cookieStore;
    private CredentialsProvider credentialsProvider;
    private ConnectionConfig defaultConnectionConfig;
    private Collection<? extends Header> defaultHeaders;
    private RequestConfig defaultRequestConfig;
    private SocketConfig defaultSocketConfig;
    private DnsResolver dnsResolver;
    private boolean evictExpiredConnections;
    private boolean evictIdleConnections;
    private HostnameVerifier hostnameVerifier;
    private HttpProcessor httpprocessor;
    private ConnectionKeepAliveStrategy keepAliveStrategy;
    private long maxIdleTime;
    private TimeUnit maxIdleTimeUnit;
    private HttpHost proxy;
    private AuthenticationStrategy proxyAuthStrategy;
    private PublicSuffixMatcher publicSuffixMatcher;
    private boolean redirectHandlingDisabled;
    private RedirectStrategy redirectStrategy;
    private HttpRequestExecutor requestExec;
    private LinkedList<HttpRequestInterceptor> requestFirst;
    private LinkedList<HttpRequestInterceptor> requestLast;
    private LinkedList<HttpResponseInterceptor> responseFirst;
    private LinkedList<HttpResponseInterceptor> responseLast;
    private HttpRequestRetryHandler retryHandler;
    private ConnectionReuseStrategy reuseStrategy;
    private HttpRoutePlanner routePlanner;
    private SchemePortResolver schemePortResolver;
    private ServiceUnavailableRetryStrategy serviceUnavailStrategy;
    private SSLContext sslContext;
    private LayeredConnectionSocketFactory sslSocketFactory;
    private boolean systemProperties;
    private AuthenticationStrategy targetAuthStrategy;
    private String userAgent;
    private UserTokenHandler userTokenHandler;
    private int maxConnTotal = 0;
    private int maxConnPerRoute = 0;
    private long connTimeToLive = -1;
    private TimeUnit connTimeToLiveTimeUnit = TimeUnit.MILLISECONDS;

    protected ClientExecChain decorateMainExec(ClientExecChain clientExecChain) {
        return clientExecChain;
    }

    protected ClientExecChain decorateProtocolExec(ClientExecChain clientExecChain) {
        return clientExecChain;
    }

    public static HttpClientBuilder create() {
        return new HttpClientBuilder();
    }

    protected HttpClientBuilder() {
    }

    public final HttpClientBuilder setRequestExecutor(HttpRequestExecutor httpRequestExecutor) {
        this.requestExec = httpRequestExecutor;
        return this;
    }

    @Deprecated
    public final HttpClientBuilder setHostnameVerifier(X509HostnameVerifier x509HostnameVerifier) {
        this.hostnameVerifier = x509HostnameVerifier;
        return this;
    }

    public final HttpClientBuilder setSSLHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.hostnameVerifier = hostnameVerifier;
        return this;
    }

    public final HttpClientBuilder setPublicSuffixMatcher(PublicSuffixMatcher publicSuffixMatcher) {
        this.publicSuffixMatcher = publicSuffixMatcher;
        return this;
    }

    @Deprecated
    public final HttpClientBuilder setSslcontext(SSLContext sSLContext) {
        return setSSLContext(sSLContext);
    }

    public final HttpClientBuilder setSSLContext(SSLContext sSLContext) {
        this.sslContext = sSLContext;
        return this;
    }

    public final HttpClientBuilder setSSLSocketFactory(LayeredConnectionSocketFactory layeredConnectionSocketFactory) {
        this.sslSocketFactory = layeredConnectionSocketFactory;
        return this;
    }

    public final HttpClientBuilder setMaxConnTotal(int i) {
        this.maxConnTotal = i;
        return this;
    }

    public final HttpClientBuilder setMaxConnPerRoute(int i) {
        this.maxConnPerRoute = i;
        return this;
    }

    public final HttpClientBuilder setDefaultSocketConfig(SocketConfig socketConfig) {
        this.defaultSocketConfig = socketConfig;
        return this;
    }

    public final HttpClientBuilder setDefaultConnectionConfig(ConnectionConfig connectionConfig) {
        this.defaultConnectionConfig = connectionConfig;
        return this;
    }

    public final HttpClientBuilder setConnectionTimeToLive(long j, TimeUnit timeUnit) {
        this.connTimeToLive = j;
        this.connTimeToLiveTimeUnit = timeUnit;
        return this;
    }

    public final HttpClientBuilder setConnectionManager(HttpClientConnectionManager httpClientConnectionManager) {
        this.connManager = httpClientConnectionManager;
        return this;
    }

    public final HttpClientBuilder setConnectionManagerShared(boolean z) {
        this.connManagerShared = z;
        return this;
    }

    public final HttpClientBuilder setConnectionReuseStrategy(ConnectionReuseStrategy connectionReuseStrategy) {
        this.reuseStrategy = connectionReuseStrategy;
        return this;
    }

    public final HttpClientBuilder setKeepAliveStrategy(ConnectionKeepAliveStrategy connectionKeepAliveStrategy) {
        this.keepAliveStrategy = connectionKeepAliveStrategy;
        return this;
    }

    public final HttpClientBuilder setTargetAuthenticationStrategy(AuthenticationStrategy authenticationStrategy) {
        this.targetAuthStrategy = authenticationStrategy;
        return this;
    }

    public final HttpClientBuilder setProxyAuthenticationStrategy(AuthenticationStrategy authenticationStrategy) {
        this.proxyAuthStrategy = authenticationStrategy;
        return this;
    }

    public final HttpClientBuilder setUserTokenHandler(UserTokenHandler userTokenHandler) {
        this.userTokenHandler = userTokenHandler;
        return this;
    }

    public final HttpClientBuilder disableConnectionState() {
        this.connectionStateDisabled = true;
        return this;
    }

    public final HttpClientBuilder setSchemePortResolver(SchemePortResolver schemePortResolver) {
        this.schemePortResolver = schemePortResolver;
        return this;
    }

    public final HttpClientBuilder setUserAgent(String str) {
        this.userAgent = str;
        return this;
    }

    public final HttpClientBuilder setDefaultHeaders(Collection<? extends Header> collection) {
        this.defaultHeaders = collection;
        return this;
    }

    public final HttpClientBuilder addInterceptorFirst(HttpResponseInterceptor httpResponseInterceptor) {
        if (httpResponseInterceptor == null) {
            return this;
        }
        if (this.responseFirst == null) {
            this.responseFirst = new LinkedList<>();
        }
        this.responseFirst.addFirst(httpResponseInterceptor);
        return this;
    }

    public final HttpClientBuilder addInterceptorLast(HttpResponseInterceptor httpResponseInterceptor) {
        if (httpResponseInterceptor == null) {
            return this;
        }
        if (this.responseLast == null) {
            this.responseLast = new LinkedList<>();
        }
        this.responseLast.addLast(httpResponseInterceptor);
        return this;
    }

    public final HttpClientBuilder addInterceptorFirst(HttpRequestInterceptor httpRequestInterceptor) {
        if (httpRequestInterceptor == null) {
            return this;
        }
        if (this.requestFirst == null) {
            this.requestFirst = new LinkedList<>();
        }
        this.requestFirst.addFirst(httpRequestInterceptor);
        return this;
    }

    public final HttpClientBuilder addInterceptorLast(HttpRequestInterceptor httpRequestInterceptor) {
        if (httpRequestInterceptor == null) {
            return this;
        }
        if (this.requestLast == null) {
            this.requestLast = new LinkedList<>();
        }
        this.requestLast.addLast(httpRequestInterceptor);
        return this;
    }

    public final HttpClientBuilder disableCookieManagement() {
        this.cookieManagementDisabled = true;
        return this;
    }

    public final HttpClientBuilder disableContentCompression() {
        this.contentCompressionDisabled = true;
        return this;
    }

    public final HttpClientBuilder disableAuthCaching() {
        this.authCachingDisabled = true;
        return this;
    }

    public final HttpClientBuilder setHttpProcessor(HttpProcessor httpProcessor) {
        this.httpprocessor = httpProcessor;
        return this;
    }

    public final HttpClientBuilder setDnsResolver(DnsResolver dnsResolver) {
        this.dnsResolver = dnsResolver;
        return this;
    }

    public final HttpClientBuilder setRetryHandler(HttpRequestRetryHandler httpRequestRetryHandler) {
        this.retryHandler = httpRequestRetryHandler;
        return this;
    }

    public final HttpClientBuilder disableAutomaticRetries() {
        this.automaticRetriesDisabled = true;
        return this;
    }

    public final HttpClientBuilder setProxy(HttpHost httpHost) {
        this.proxy = httpHost;
        return this;
    }

    public final HttpClientBuilder setRoutePlanner(HttpRoutePlanner httpRoutePlanner) {
        this.routePlanner = httpRoutePlanner;
        return this;
    }

    public final HttpClientBuilder setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
        return this;
    }

    public final HttpClientBuilder disableRedirectHandling() {
        this.redirectHandlingDisabled = true;
        return this;
    }

    public final HttpClientBuilder setConnectionBackoffStrategy(ConnectionBackoffStrategy connectionBackoffStrategy) {
        this.connectionBackoffStrategy = connectionBackoffStrategy;
        return this;
    }

    public final HttpClientBuilder setBackoffManager(BackoffManager backoffManager) {
        this.backoffManager = backoffManager;
        return this;
    }

    public final HttpClientBuilder setServiceUnavailableRetryStrategy(ServiceUnavailableRetryStrategy serviceUnavailableRetryStrategy) {
        this.serviceUnavailStrategy = serviceUnavailableRetryStrategy;
        return this;
    }

    public final HttpClientBuilder setDefaultCookieStore(CookieStore cookieStore) {
        this.cookieStore = cookieStore;
        return this;
    }

    public final HttpClientBuilder setDefaultCredentialsProvider(CredentialsProvider credentialsProvider) {
        this.credentialsProvider = credentialsProvider;
        return this;
    }

    public final HttpClientBuilder setDefaultAuthSchemeRegistry(Lookup<AuthSchemeProvider> lookup) {
        this.authSchemeRegistry = lookup;
        return this;
    }

    public final HttpClientBuilder setDefaultCookieSpecRegistry(Lookup<CookieSpecProvider> lookup) {
        this.cookieSpecRegistry = lookup;
        return this;
    }

    public final HttpClientBuilder setContentDecoderRegistry(Map<String, InputStreamFactory> map) {
        this.contentDecoderMap = map;
        return this;
    }

    public final HttpClientBuilder setDefaultRequestConfig(RequestConfig requestConfig) {
        this.defaultRequestConfig = requestConfig;
        return this;
    }

    public final HttpClientBuilder useSystemProperties() {
        this.systemProperties = true;
        return this;
    }

    public final HttpClientBuilder evictExpiredConnections() {
        this.evictExpiredConnections = true;
        return this;
    }

    @Deprecated
    public final HttpClientBuilder evictIdleConnections(Long l, TimeUnit timeUnit) {
        return evictIdleConnections(l.longValue(), timeUnit);
    }

    public final HttpClientBuilder evictIdleConnections(long j, TimeUnit timeUnit) {
        this.evictIdleConnections = true;
        this.maxIdleTime = j;
        this.maxIdleTimeUnit = timeUnit;
        return this;
    }

    protected ClientExecChain createMainExec(HttpRequestExecutor httpRequestExecutor, HttpClientConnectionManager httpClientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy, HttpProcessor httpProcessor, AuthenticationStrategy authenticationStrategy, AuthenticationStrategy authenticationStrategy2, UserTokenHandler userTokenHandler) {
        return new MainClientExec(httpRequestExecutor, httpClientConnectionManager, connectionReuseStrategy, connectionKeepAliveStrategy, httpProcessor, authenticationStrategy, authenticationStrategy2, userTokenHandler);
    }

    protected void addCloseable(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        if (this.closeables == null) {
            this.closeables = new ArrayList();
        }
        this.closeables.add(closeable);
    }

    private static String[] split(String str) {
        if (TextUtils.isBlank(str)) {
            return null;
        }
        return str.split(" *, *");
    }

    public CloseableHttpClient build() throws NumberFormatException, IOException {
        HttpClientConnectionManager httpClientConnectionManager;
        HttpRoutePlanner defaultRoutePlanner;
        final HttpClientConnectionManager httpClientConnectionManager2;
        ArrayList arrayList;
        ConnectionSocketFactory sSLConnectionSocketFactory;
        PublicSuffixMatcher publicSuffixMatcher = this.publicSuffixMatcher;
        if (publicSuffixMatcher == null) {
            publicSuffixMatcher = PublicSuffixMatcherLoader.getDefault();
        }
        PublicSuffixMatcher publicSuffixMatcher2 = publicSuffixMatcher;
        HttpRequestExecutor httpRequestExecutor = this.requestExec;
        if (httpRequestExecutor == null) {
            httpRequestExecutor = new HttpRequestExecutor();
        }
        HttpRequestExecutor httpRequestExecutor2 = httpRequestExecutor;
        HttpClientConnectionManager httpClientConnectionManager3 = this.connManager;
        if (httpClientConnectionManager3 == null) {
            ConnectionSocketFactory sSLConnectionSocketFactory2 = this.sslSocketFactory;
            if (sSLConnectionSocketFactory2 == null) {
                String[] strArrSplit = this.systemProperties ? split(System.getProperty("https.protocols")) : null;
                String[] strArrSplit2 = this.systemProperties ? split(System.getProperty("https.cipherSuites")) : null;
                HostnameVerifier defaultHostnameVerifier = this.hostnameVerifier;
                if (defaultHostnameVerifier == null) {
                    defaultHostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher2);
                }
                if (this.sslContext != null) {
                    sSLConnectionSocketFactory = new SSLConnectionSocketFactory(this.sslContext, strArrSplit, strArrSplit2, defaultHostnameVerifier);
                } else if (this.systemProperties) {
                    sSLConnectionSocketFactory = new SSLConnectionSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault(), strArrSplit, strArrSplit2, defaultHostnameVerifier);
                } else {
                    sSLConnectionSocketFactory2 = new SSLConnectionSocketFactory(SSLContexts.createDefault(), defaultHostnameVerifier);
                }
                sSLConnectionSocketFactory2 = sSLConnectionSocketFactory;
            }
            Registry registryBuild = RegistryBuilder.create().register(HttpHost.DEFAULT_SCHEME_NAME, PlainConnectionSocketFactory.getSocketFactory()).register("https", sSLConnectionSocketFactory2).build();
            DnsResolver dnsResolver = this.dnsResolver;
            long j = this.connTimeToLive;
            TimeUnit timeUnit = this.connTimeToLiveTimeUnit;
            if (timeUnit == null) {
                timeUnit = TimeUnit.MILLISECONDS;
            }
            PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(registryBuild, null, null, dnsResolver, j, timeUnit);
            SocketConfig socketConfig = this.defaultSocketConfig;
            if (socketConfig != null) {
                poolingHttpClientConnectionManager.setDefaultSocketConfig(socketConfig);
            }
            ConnectionConfig connectionConfig = this.defaultConnectionConfig;
            if (connectionConfig != null) {
                poolingHttpClientConnectionManager.setDefaultConnectionConfig(connectionConfig);
            }
            if (this.systemProperties && "true".equalsIgnoreCase(System.getProperty("http.keepAlive", "true"))) {
                int i = Integer.parseInt(System.getProperty("http.maxConnections", "5"));
                poolingHttpClientConnectionManager.setDefaultMaxPerRoute(i);
                poolingHttpClientConnectionManager.setMaxTotal(i * 2);
            }
            int i2 = this.maxConnTotal;
            if (i2 > 0) {
                poolingHttpClientConnectionManager.setMaxTotal(i2);
            }
            int i3 = this.maxConnPerRoute;
            if (i3 > 0) {
                poolingHttpClientConnectionManager.setDefaultMaxPerRoute(i3);
            }
            httpClientConnectionManager = poolingHttpClientConnectionManager;
        } else {
            httpClientConnectionManager = httpClientConnectionManager3;
        }
        ConnectionReuseStrategy connectionReuseStrategy = this.reuseStrategy;
        if (connectionReuseStrategy == null) {
            if (!this.systemProperties || "true".equalsIgnoreCase(System.getProperty("http.keepAlive", "true"))) {
                connectionReuseStrategy = DefaultClientConnectionReuseStrategy.INSTANCE;
            } else {
                connectionReuseStrategy = NoConnectionReuseStrategy.INSTANCE;
            }
        }
        ConnectionReuseStrategy connectionReuseStrategy2 = connectionReuseStrategy;
        ConnectionKeepAliveStrategy connectionKeepAliveStrategy = this.keepAliveStrategy;
        if (connectionKeepAliveStrategy == null) {
            connectionKeepAliveStrategy = DefaultConnectionKeepAliveStrategy.INSTANCE;
        }
        ConnectionKeepAliveStrategy connectionKeepAliveStrategy2 = connectionKeepAliveStrategy;
        AuthenticationStrategy authenticationStrategy = this.targetAuthStrategy;
        if (authenticationStrategy == null) {
            authenticationStrategy = TargetAuthenticationStrategy.INSTANCE;
        }
        AuthenticationStrategy authenticationStrategy2 = authenticationStrategy;
        AuthenticationStrategy authenticationStrategy3 = this.proxyAuthStrategy;
        if (authenticationStrategy3 == null) {
            authenticationStrategy3 = ProxyAuthenticationStrategy.INSTANCE;
        }
        AuthenticationStrategy authenticationStrategy4 = authenticationStrategy3;
        UserTokenHandler userTokenHandler = this.userTokenHandler;
        if (userTokenHandler == null) {
            if (!this.connectionStateDisabled) {
                userTokenHandler = DefaultUserTokenHandler.INSTANCE;
            } else {
                userTokenHandler = NoopUserTokenHandler.INSTANCE;
            }
        }
        UserTokenHandler userTokenHandler2 = userTokenHandler;
        String userAgent = this.userAgent;
        if (userAgent == null) {
            if (this.systemProperties) {
                userAgent = System.getProperty("http.agent");
            }
            if (userAgent == null) {
                userAgent = VersionInfo.getUserAgent("Apache-HttpClient", "com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client", getClass());
            }
        }
        String str = userAgent;
        ClientExecChain clientExecChainDecorateMainExec = decorateMainExec(createMainExec(httpRequestExecutor2, httpClientConnectionManager, connectionReuseStrategy2, connectionKeepAliveStrategy2, new ImmutableHttpProcessor(new RequestTargetHost(), new RequestUserAgent(str)), authenticationStrategy2, authenticationStrategy4, userTokenHandler2));
        HttpProcessor httpProcessorBuild = this.httpprocessor;
        if (httpProcessorBuild == null) {
            HttpProcessorBuilder httpProcessorBuilderCreate = HttpProcessorBuilder.create();
            LinkedList<HttpRequestInterceptor> linkedList = this.requestFirst;
            if (linkedList != null) {
                Iterator<HttpRequestInterceptor> it = linkedList.iterator();
                while (it.hasNext()) {
                    httpProcessorBuilderCreate.addFirst(it.next());
                }
            }
            LinkedList<HttpResponseInterceptor> linkedList2 = this.responseFirst;
            if (linkedList2 != null) {
                Iterator<HttpResponseInterceptor> it2 = linkedList2.iterator();
                while (it2.hasNext()) {
                    httpProcessorBuilderCreate.addFirst(it2.next());
                }
            }
            httpProcessorBuilderCreate.addAll(new RequestDefaultHeaders(this.defaultHeaders), new RequestContent(), new RequestTargetHost(), new RequestClientConnControl(), new RequestUserAgent(str), new RequestExpectContinue());
            if (!this.cookieManagementDisabled) {
                httpProcessorBuilderCreate.add(new RequestAddCookies());
            }
            if (!this.contentCompressionDisabled) {
                if (this.contentDecoderMap != null) {
                    ArrayList arrayList2 = new ArrayList(this.contentDecoderMap.keySet());
                    Collections.sort(arrayList2);
                    httpProcessorBuilderCreate.add(new RequestAcceptEncoding(arrayList2));
                } else {
                    httpProcessorBuilderCreate.add(new RequestAcceptEncoding());
                }
            }
            if (!this.authCachingDisabled) {
                httpProcessorBuilderCreate.add(new RequestAuthCache());
            }
            if (!this.cookieManagementDisabled) {
                httpProcessorBuilderCreate.add(new ResponseProcessCookies());
            }
            if (!this.contentCompressionDisabled) {
                if (this.contentDecoderMap != null) {
                    RegistryBuilder registryBuilderCreate = RegistryBuilder.create();
                    for (Map.Entry<String, InputStreamFactory> entry : this.contentDecoderMap.entrySet()) {
                        registryBuilderCreate.register(entry.getKey(), entry.getValue());
                    }
                    httpProcessorBuilderCreate.add(new ResponseContentEncoding(registryBuilderCreate.build()));
                } else {
                    httpProcessorBuilderCreate.add(new ResponseContentEncoding());
                }
            }
            LinkedList<HttpRequestInterceptor> linkedList3 = this.requestLast;
            if (linkedList3 != null) {
                Iterator<HttpRequestInterceptor> it3 = linkedList3.iterator();
                while (it3.hasNext()) {
                    httpProcessorBuilderCreate.addLast(it3.next());
                }
            }
            LinkedList<HttpResponseInterceptor> linkedList4 = this.responseLast;
            if (linkedList4 != null) {
                Iterator<HttpResponseInterceptor> it4 = linkedList4.iterator();
                while (it4.hasNext()) {
                    httpProcessorBuilderCreate.addLast(it4.next());
                }
            }
            httpProcessorBuild = httpProcessorBuilderCreate.build();
        }
        ClientExecChain clientExecChainDecorateProtocolExec = decorateProtocolExec(new ProtocolExec(clientExecChainDecorateMainExec, httpProcessorBuild));
        if (!this.automaticRetriesDisabled) {
            HttpRequestRetryHandler httpRequestRetryHandler = this.retryHandler;
            if (httpRequestRetryHandler == null) {
                httpRequestRetryHandler = DefaultHttpRequestRetryHandler.INSTANCE;
            }
            clientExecChainDecorateProtocolExec = new RetryExec(clientExecChainDecorateProtocolExec, httpRequestRetryHandler);
        }
        HttpRoutePlanner httpRoutePlanner = this.routePlanner;
        if (httpRoutePlanner == null) {
            SchemePortResolver schemePortResolver = this.schemePortResolver;
            if (schemePortResolver == null) {
                schemePortResolver = DefaultSchemePortResolver.INSTANCE;
            }
            if (this.proxy != null) {
                defaultRoutePlanner = new DefaultProxyRoutePlanner(this.proxy, schemePortResolver);
            } else if (this.systemProperties) {
                defaultRoutePlanner = new SystemDefaultRoutePlanner(schemePortResolver, ProxySelector.getDefault());
            } else {
                defaultRoutePlanner = new DefaultRoutePlanner(schemePortResolver);
            }
        } else {
            defaultRoutePlanner = httpRoutePlanner;
        }
        ServiceUnavailableRetryStrategy serviceUnavailableRetryStrategy = this.serviceUnavailStrategy;
        if (serviceUnavailableRetryStrategy != null) {
            clientExecChainDecorateProtocolExec = new ServiceUnavailableRetryExec(clientExecChainDecorateProtocolExec, serviceUnavailableRetryStrategy);
        }
        if (!this.redirectHandlingDisabled) {
            RedirectStrategy redirectStrategy = this.redirectStrategy;
            if (redirectStrategy == null) {
                redirectStrategy = DefaultRedirectStrategy.INSTANCE;
            }
            clientExecChainDecorateProtocolExec = new RedirectExec(clientExecChainDecorateProtocolExec, defaultRoutePlanner, redirectStrategy);
        }
        if (this.backoffManager != null && this.connectionBackoffStrategy != null) {
            clientExecChainDecorateProtocolExec = new BackoffStrategyExec(clientExecChainDecorateProtocolExec, this.connectionBackoffStrategy, this.backoffManager);
        }
        Lookup lookupBuild = this.authSchemeRegistry;
        if (lookupBuild == null) {
            lookupBuild = RegistryBuilder.create().register("Basic", new BasicSchemeFactory()).register("Digest", new DigestSchemeFactory()).register("NTLM", new NTLMSchemeFactory()).register("Negotiate", new SPNegoSchemeFactory()).register("Kerberos", new KerberosSchemeFactory()).build();
        }
        Lookup<CookieSpecProvider> lookupCreateDefault = this.cookieSpecRegistry;
        if (lookupCreateDefault == null) {
            lookupCreateDefault = CookieSpecRegistries.createDefault(publicSuffixMatcher2);
        }
        CookieStore basicCookieStore = this.cookieStore;
        if (basicCookieStore == null) {
            basicCookieStore = new BasicCookieStore();
        }
        CredentialsProvider basicCredentialsProvider = this.credentialsProvider;
        if (basicCredentialsProvider == null) {
            if (this.systemProperties) {
                basicCredentialsProvider = new SystemDefaultCredentialsProvider();
            } else {
                basicCredentialsProvider = new BasicCredentialsProvider();
            }
        }
        ArrayList arrayList3 = this.closeables != null ? new ArrayList(this.closeables) : null;
        if (this.connManagerShared) {
            httpClientConnectionManager2 = httpClientConnectionManager;
            arrayList = arrayList3;
        } else {
            ArrayList arrayList4 = arrayList3 == null ? new ArrayList(1) : arrayList3;
            if (this.evictExpiredConnections || this.evictIdleConnections) {
                long j2 = this.maxIdleTime;
                if (j2 <= 0) {
                    j2 = 10;
                }
                TimeUnit timeUnit2 = this.maxIdleTimeUnit;
                if (timeUnit2 == null) {
                    timeUnit2 = TimeUnit.SECONDS;
                }
                httpClientConnectionManager2 = httpClientConnectionManager;
                final IdleConnectionEvictor idleConnectionEvictor = new IdleConnectionEvictor(httpClientConnectionManager, j2, timeUnit2, this.maxIdleTime, this.maxIdleTimeUnit);
                arrayList4.add(new Closeable() { // from class: com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.HttpClientBuilder.1
                    @Override // java.io.Closeable, java.lang.AutoCloseable
                    public void close() throws IOException {
                        idleConnectionEvictor.shutdown();
                        try {
                            idleConnectionEvictor.awaitTermination(1L, TimeUnit.SECONDS);
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                        }
                    }
                });
                idleConnectionEvictor.start();
            } else {
                httpClientConnectionManager2 = httpClientConnectionManager;
            }
            arrayList4.add(new Closeable() { // from class: com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.HttpClientBuilder.2
                @Override // java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    httpClientConnectionManager2.shutdown();
                }
            });
            arrayList = arrayList4;
        }
        RequestConfig requestConfig = this.defaultRequestConfig;
        if (requestConfig == null) {
            requestConfig = RequestConfig.DEFAULT;
        }
        return new InternalHttpClient(clientExecChainDecorateProtocolExec, httpClientConnectionManager2, defaultRoutePlanner, lookupCreateDefault, lookupBuild, basicCookieStore, basicCredentialsProvider, requestConfig, arrayList);
    }
}
