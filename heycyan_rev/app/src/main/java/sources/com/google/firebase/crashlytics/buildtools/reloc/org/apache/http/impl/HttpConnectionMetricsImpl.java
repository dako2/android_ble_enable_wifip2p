package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnectionMetrics;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.HttpTransportMetrics;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class HttpConnectionMetricsImpl implements HttpConnectionMetrics {
    public static final String RECEIVED_BYTES_COUNT = "http.received-bytes-count";
    public static final String REQUEST_COUNT = "http.request-count";
    public static final String RESPONSE_COUNT = "http.response-count";
    public static final String SENT_BYTES_COUNT = "http.sent-bytes-count";
    private final HttpTransportMetrics inTransportMetric;
    private Map<String, Object> metricsCache;
    private final HttpTransportMetrics outTransportMetric;
    private long requestCount = 0;
    private long responseCount = 0;

    public HttpConnectionMetricsImpl(HttpTransportMetrics httpTransportMetrics, HttpTransportMetrics httpTransportMetrics2) {
        this.inTransportMetric = httpTransportMetrics;
        this.outTransportMetric = httpTransportMetrics2;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnectionMetrics
    public long getReceivedBytesCount() {
        HttpTransportMetrics httpTransportMetrics = this.inTransportMetric;
        if (httpTransportMetrics != null) {
            return httpTransportMetrics.getBytesTransferred();
        }
        return -1L;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnectionMetrics
    public long getSentBytesCount() {
        HttpTransportMetrics httpTransportMetrics = this.outTransportMetric;
        if (httpTransportMetrics != null) {
            return httpTransportMetrics.getBytesTransferred();
        }
        return -1L;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnectionMetrics
    public long getRequestCount() {
        return this.requestCount;
    }

    public void incrementRequestCount() {
        this.requestCount++;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnectionMetrics
    public long getResponseCount() {
        return this.responseCount;
    }

    public void incrementResponseCount() {
        this.responseCount++;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnectionMetrics
    public Object getMetric(String str) {
        Map<String, Object> map = this.metricsCache;
        Object obj = map != null ? map.get(str) : null;
        if (obj != null) {
            return obj;
        }
        if (REQUEST_COUNT.equals(str)) {
            return Long.valueOf(this.requestCount);
        }
        if (RESPONSE_COUNT.equals(str)) {
            return Long.valueOf(this.responseCount);
        }
        if (RECEIVED_BYTES_COUNT.equals(str)) {
            HttpTransportMetrics httpTransportMetrics = this.inTransportMetric;
            if (httpTransportMetrics != null) {
                return Long.valueOf(httpTransportMetrics.getBytesTransferred());
            }
            return null;
        }
        if (!SENT_BYTES_COUNT.equals(str)) {
            return obj;
        }
        HttpTransportMetrics httpTransportMetrics2 = this.outTransportMetric;
        if (httpTransportMetrics2 != null) {
            return Long.valueOf(httpTransportMetrics2.getBytesTransferred());
        }
        return null;
    }

    public void setMetric(String str, Object obj) {
        if (this.metricsCache == null) {
            this.metricsCache = new HashMap();
        }
        this.metricsCache.put(str, obj);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpConnectionMetrics
    public void reset() {
        HttpTransportMetrics httpTransportMetrics = this.outTransportMetric;
        if (httpTransportMetrics != null) {
            httpTransportMetrics.reset();
        }
        HttpTransportMetrics httpTransportMetrics2 = this.inTransportMetric;
        if (httpTransportMetrics2 != null) {
            httpTransportMetrics2.reset();
        }
        this.requestCount = 0L;
        this.responseCount = 0L;
        this.metricsCache = null;
    }
}
