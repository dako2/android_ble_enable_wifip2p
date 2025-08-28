package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpHost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.RouteInfo;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.LangUtils;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public final class HttpRoute implements RouteInfo, Cloneable {
    private final RouteInfo.LayerType layered;
    private final InetAddress localAddress;
    private final List<HttpHost> proxyChain;
    private final boolean secure;
    private final HttpHost targetHost;
    private final RouteInfo.TunnelType tunnelled;

    private HttpRoute(HttpHost httpHost, InetAddress inetAddress, List<HttpHost> list, boolean z, RouteInfo.TunnelType tunnelType, RouteInfo.LayerType layerType) {
        Args.notNull(httpHost, "Target host");
        this.targetHost = normalize(httpHost);
        this.localAddress = inetAddress;
        if (list != null && !list.isEmpty()) {
            this.proxyChain = new ArrayList(list);
        } else {
            this.proxyChain = null;
        }
        if (tunnelType == RouteInfo.TunnelType.TUNNELLED) {
            Args.check(this.proxyChain != null, "Proxy required if tunnelled");
        }
        this.secure = z;
        this.tunnelled = tunnelType == null ? RouteInfo.TunnelType.PLAIN : tunnelType;
        this.layered = layerType == null ? RouteInfo.LayerType.PLAIN : layerType;
    }

    private static int getDefaultPort(String str) {
        if (HttpHost.DEFAULT_SCHEME_NAME.equalsIgnoreCase(str)) {
            return 80;
        }
        return "https".equalsIgnoreCase(str) ? 443 : -1;
    }

    private static HttpHost normalize(HttpHost httpHost) {
        if (httpHost.getPort() >= 0) {
            return httpHost;
        }
        InetAddress address = httpHost.getAddress();
        String schemeName = httpHost.getSchemeName();
        if (address != null) {
            return new HttpHost(address, getDefaultPort(schemeName), schemeName);
        }
        return new HttpHost(httpHost.getHostName(), getDefaultPort(schemeName), schemeName);
    }

    public HttpRoute(HttpHost httpHost, InetAddress inetAddress, HttpHost[] httpHostArr, boolean z, RouteInfo.TunnelType tunnelType, RouteInfo.LayerType layerType) {
        this(httpHost, inetAddress, (List<HttpHost>) (httpHostArr != null ? Arrays.asList(httpHostArr) : null), z, tunnelType, layerType);
    }

    public HttpRoute(HttpHost httpHost, InetAddress inetAddress, HttpHost httpHost2, boolean z, RouteInfo.TunnelType tunnelType, RouteInfo.LayerType layerType) {
        this(httpHost, inetAddress, (List<HttpHost>) (httpHost2 != null ? Collections.singletonList(httpHost2) : null), z, tunnelType, layerType);
    }

    public HttpRoute(HttpHost httpHost, InetAddress inetAddress, boolean z) {
        this(httpHost, inetAddress, (List<HttpHost>) Collections.emptyList(), z, RouteInfo.TunnelType.PLAIN, RouteInfo.LayerType.PLAIN);
    }

    public HttpRoute(HttpHost httpHost) {
        this(httpHost, (InetAddress) null, (List<HttpHost>) Collections.emptyList(), false, RouteInfo.TunnelType.PLAIN, RouteInfo.LayerType.PLAIN);
    }

    public HttpRoute(HttpHost httpHost, InetAddress inetAddress, HttpHost httpHost2, boolean z) {
        this(httpHost, inetAddress, (List<HttpHost>) Collections.singletonList(Args.notNull(httpHost2, "Proxy host")), z, z ? RouteInfo.TunnelType.TUNNELLED : RouteInfo.TunnelType.PLAIN, z ? RouteInfo.LayerType.LAYERED : RouteInfo.LayerType.PLAIN);
    }

    public HttpRoute(HttpHost httpHost, HttpHost httpHost2) {
        this(httpHost, null, httpHost2, false);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.RouteInfo
    public final HttpHost getTargetHost() {
        return this.targetHost;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.RouteInfo
    public final InetAddress getLocalAddress() {
        return this.localAddress;
    }

    public final InetSocketAddress getLocalSocketAddress() {
        if (this.localAddress != null) {
            return new InetSocketAddress(this.localAddress, 0);
        }
        return null;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.RouteInfo
    public final int getHopCount() {
        List<HttpHost> list = this.proxyChain;
        if (list != null) {
            return 1 + list.size();
        }
        return 1;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.RouteInfo
    public final HttpHost getHopTarget(int i) {
        Args.notNegative(i, "Hop index");
        int hopCount = getHopCount();
        Args.check(i < hopCount, "Hop index exceeds tracked route length");
        if (i < hopCount - 1) {
            return this.proxyChain.get(i);
        }
        return this.targetHost;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.RouteInfo
    public final HttpHost getProxyHost() {
        List<HttpHost> list = this.proxyChain;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return this.proxyChain.get(0);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.RouteInfo
    public final RouteInfo.TunnelType getTunnelType() {
        return this.tunnelled;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.RouteInfo
    public final boolean isTunnelled() {
        return this.tunnelled == RouteInfo.TunnelType.TUNNELLED;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.RouteInfo
    public final RouteInfo.LayerType getLayerType() {
        return this.layered;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.RouteInfo
    public final boolean isLayered() {
        return this.layered == RouteInfo.LayerType.LAYERED;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.routing.RouteInfo
    public final boolean isSecure() {
        return this.secure;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HttpRoute)) {
            return false;
        }
        HttpRoute httpRoute = (HttpRoute) obj;
        return this.secure == httpRoute.secure && this.tunnelled == httpRoute.tunnelled && this.layered == httpRoute.layered && LangUtils.equals(this.targetHost, httpRoute.targetHost) && LangUtils.equals(this.localAddress, httpRoute.localAddress) && LangUtils.equals(this.proxyChain, httpRoute.proxyChain);
    }

    public final int hashCode() {
        int iHashCode = LangUtils.hashCode(LangUtils.hashCode(17, this.targetHost), this.localAddress);
        List<HttpHost> list = this.proxyChain;
        if (list != null) {
            Iterator<HttpHost> it = list.iterator();
            while (it.hasNext()) {
                iHashCode = LangUtils.hashCode(iHashCode, it.next());
            }
        }
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(iHashCode, this.secure), this.tunnelled), this.layered);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((getHopCount() * 30) + 50);
        InetAddress inetAddress = this.localAddress;
        if (inetAddress != null) {
            sb.append(inetAddress);
            sb.append("->");
        }
        sb.append('{');
        if (this.tunnelled == RouteInfo.TunnelType.TUNNELLED) {
            sb.append('t');
        }
        if (this.layered == RouteInfo.LayerType.LAYERED) {
            sb.append('l');
        }
        if (this.secure) {
            sb.append('s');
        }
        sb.append("}->");
        List<HttpHost> list = this.proxyChain;
        if (list != null) {
            Iterator<HttpHost> it = list.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append("->");
            }
        }
        sb.append(this.targetHost);
        return sb.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
