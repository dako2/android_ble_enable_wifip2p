package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.config;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import java.net.InetAddress;
import java.util.Collection;

/* loaded from: classes2.dex */
public class RequestConfig implements Cloneable {
    public static final RequestConfig DEFAULT = new Builder().build();
    private final boolean authenticationEnabled;
    private final boolean circularRedirectsAllowed;
    private final int connectTimeout;
    private final int connectionRequestTimeout;
    private final boolean contentCompressionEnabled;
    private final String cookieSpec;
    private final boolean expectContinueEnabled;
    private final InetAddress localAddress;
    private final int maxRedirects;
    private final HttpHost proxy;
    private final Collection<String> proxyPreferredAuthSchemes;
    private final boolean redirectsEnabled;
    private final boolean relativeRedirectsAllowed;
    private final int socketTimeout;
    private final boolean staleConnectionCheckEnabled;
    private final Collection<String> targetPreferredAuthSchemes;

    protected RequestConfig() {
        this(false, null, null, false, null, false, false, false, 0, false, null, null, 0, 0, 0, true);
    }

    RequestConfig(boolean z, HttpHost httpHost, InetAddress inetAddress, boolean z2, String str, boolean z3, boolean z4, boolean z5, int i, boolean z6, Collection<String> collection, Collection<String> collection2, int i2, int i3, int i4, boolean z7) {
        this.expectContinueEnabled = z;
        this.proxy = httpHost;
        this.localAddress = inetAddress;
        this.staleConnectionCheckEnabled = z2;
        this.cookieSpec = str;
        this.redirectsEnabled = z3;
        this.relativeRedirectsAllowed = z4;
        this.circularRedirectsAllowed = z5;
        this.maxRedirects = i;
        this.authenticationEnabled = z6;
        this.targetPreferredAuthSchemes = collection;
        this.proxyPreferredAuthSchemes = collection2;
        this.connectionRequestTimeout = i2;
        this.connectTimeout = i3;
        this.socketTimeout = i4;
        this.contentCompressionEnabled = z7;
    }

    public boolean isExpectContinueEnabled() {
        return this.expectContinueEnabled;
    }

    public HttpHost getProxy() {
        return this.proxy;
    }

    public InetAddress getLocalAddress() {
        return this.localAddress;
    }

    @Deprecated
    public boolean isStaleConnectionCheckEnabled() {
        return this.staleConnectionCheckEnabled;
    }

    public String getCookieSpec() {
        return this.cookieSpec;
    }

    public boolean isRedirectsEnabled() {
        return this.redirectsEnabled;
    }

    public boolean isRelativeRedirectsAllowed() {
        return this.relativeRedirectsAllowed;
    }

    public boolean isCircularRedirectsAllowed() {
        return this.circularRedirectsAllowed;
    }

    public int getMaxRedirects() {
        return this.maxRedirects;
    }

    public boolean isAuthenticationEnabled() {
        return this.authenticationEnabled;
    }

    public Collection<String> getTargetPreferredAuthSchemes() {
        return this.targetPreferredAuthSchemes;
    }

    public Collection<String> getProxyPreferredAuthSchemes() {
        return this.proxyPreferredAuthSchemes;
    }

    public int getConnectionRequestTimeout() {
        return this.connectionRequestTimeout;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public int getSocketTimeout() {
        return this.socketTimeout;
    }

    @Deprecated
    public boolean isDecompressionEnabled() {
        return this.contentCompressionEnabled;
    }

    public boolean isContentCompressionEnabled() {
        return this.contentCompressionEnabled;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public RequestConfig m887clone() throws CloneNotSupportedException {
        return (RequestConfig) super.clone();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[expectContinueEnabled=");
        sb.append(this.expectContinueEnabled);
        sb.append(", proxy=").append(this.proxy);
        sb.append(", localAddress=").append(this.localAddress);
        sb.append(", cookieSpec=").append(this.cookieSpec);
        sb.append(", redirectsEnabled=").append(this.redirectsEnabled);
        sb.append(", relativeRedirectsAllowed=").append(this.relativeRedirectsAllowed);
        sb.append(", maxRedirects=").append(this.maxRedirects);
        sb.append(", circularRedirectsAllowed=").append(this.circularRedirectsAllowed);
        sb.append(", authenticationEnabled=").append(this.authenticationEnabled);
        sb.append(", targetPreferredAuthSchemes=").append(this.targetPreferredAuthSchemes);
        sb.append(", proxyPreferredAuthSchemes=").append(this.proxyPreferredAuthSchemes);
        sb.append(", connectionRequestTimeout=").append(this.connectionRequestTimeout);
        sb.append(", connectTimeout=").append(this.connectTimeout);
        sb.append(", socketTimeout=").append(this.socketTimeout);
        sb.append(", contentCompressionEnabled=").append(this.contentCompressionEnabled);
        sb.append("]");
        return sb.toString();
    }

    public static Builder custom() {
        return new Builder();
    }

    public static Builder copy(RequestConfig requestConfig) {
        return new Builder().setExpectContinueEnabled(requestConfig.isExpectContinueEnabled()).setProxy(requestConfig.getProxy()).setLocalAddress(requestConfig.getLocalAddress()).setStaleConnectionCheckEnabled(requestConfig.isStaleConnectionCheckEnabled()).setCookieSpec(requestConfig.getCookieSpec()).setRedirectsEnabled(requestConfig.isRedirectsEnabled()).setRelativeRedirectsAllowed(requestConfig.isRelativeRedirectsAllowed()).setCircularRedirectsAllowed(requestConfig.isCircularRedirectsAllowed()).setMaxRedirects(requestConfig.getMaxRedirects()).setAuthenticationEnabled(requestConfig.isAuthenticationEnabled()).setTargetPreferredAuthSchemes(requestConfig.getTargetPreferredAuthSchemes()).setProxyPreferredAuthSchemes(requestConfig.getProxyPreferredAuthSchemes()).setConnectionRequestTimeout(requestConfig.getConnectionRequestTimeout()).setConnectTimeout(requestConfig.getConnectTimeout()).setSocketTimeout(requestConfig.getSocketTimeout()).setDecompressionEnabled(requestConfig.isDecompressionEnabled()).setContentCompressionEnabled(requestConfig.isContentCompressionEnabled());
    }

    public static class Builder {
        private boolean circularRedirectsAllowed;
        private String cookieSpec;
        private boolean expectContinueEnabled;
        private InetAddress localAddress;
        private HttpHost proxy;
        private Collection<String> proxyPreferredAuthSchemes;
        private Collection<String> targetPreferredAuthSchemes;
        private boolean staleConnectionCheckEnabled = false;
        private boolean redirectsEnabled = true;
        private int maxRedirects = 50;
        private boolean relativeRedirectsAllowed = true;
        private boolean authenticationEnabled = true;
        private int connectionRequestTimeout = -1;
        private int connectTimeout = -1;
        private int socketTimeout = -1;
        private boolean contentCompressionEnabled = true;

        Builder() {
        }

        public Builder setExpectContinueEnabled(boolean z) {
            this.expectContinueEnabled = z;
            return this;
        }

        public Builder setProxy(HttpHost httpHost) {
            this.proxy = httpHost;
            return this;
        }

        public Builder setLocalAddress(InetAddress inetAddress) {
            this.localAddress = inetAddress;
            return this;
        }

        @Deprecated
        public Builder setStaleConnectionCheckEnabled(boolean z) {
            this.staleConnectionCheckEnabled = z;
            return this;
        }

        public Builder setCookieSpec(String str) {
            this.cookieSpec = str;
            return this;
        }

        public Builder setRedirectsEnabled(boolean z) {
            this.redirectsEnabled = z;
            return this;
        }

        public Builder setRelativeRedirectsAllowed(boolean z) {
            this.relativeRedirectsAllowed = z;
            return this;
        }

        public Builder setCircularRedirectsAllowed(boolean z) {
            this.circularRedirectsAllowed = z;
            return this;
        }

        public Builder setMaxRedirects(int i) {
            this.maxRedirects = i;
            return this;
        }

        public Builder setAuthenticationEnabled(boolean z) {
            this.authenticationEnabled = z;
            return this;
        }

        public Builder setTargetPreferredAuthSchemes(Collection<String> collection) {
            this.targetPreferredAuthSchemes = collection;
            return this;
        }

        public Builder setProxyPreferredAuthSchemes(Collection<String> collection) {
            this.proxyPreferredAuthSchemes = collection;
            return this;
        }

        public Builder setConnectionRequestTimeout(int i) {
            this.connectionRequestTimeout = i;
            return this;
        }

        public Builder setConnectTimeout(int i) {
            this.connectTimeout = i;
            return this;
        }

        public Builder setSocketTimeout(int i) {
            this.socketTimeout = i;
            return this;
        }

        @Deprecated
        public Builder setDecompressionEnabled(boolean z) {
            this.contentCompressionEnabled = z;
            return this;
        }

        public Builder setContentCompressionEnabled(boolean z) {
            this.contentCompressionEnabled = z;
            return this;
        }

        public RequestConfig build() {
            return new RequestConfig(this.expectContinueEnabled, this.proxy, this.localAddress, this.staleConnectionCheckEnabled, this.cookieSpec, this.redirectsEnabled, this.relativeRedirectsAllowed, this.circularRedirectsAllowed, this.maxRedirects, this.authenticationEnabled, this.targetPreferredAuthSchemes, this.proxyPreferredAuthSchemes, this.connectionRequestTimeout, this.connectTimeout, this.socketTimeout, this.contentCompressionEnabled);
        }
    }
}
