package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.p009io;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.HttpTransportMetrics;

/* loaded from: classes2.dex */
public class HttpTransportMetricsImpl implements HttpTransportMetrics {
    private long bytesTransferred = 0;

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.HttpTransportMetrics
    public long getBytesTransferred() {
        return this.bytesTransferred;
    }

    public void setBytesTransferred(long j) {
        this.bytesTransferred = j;
    }

    public void incrementBytesTransferred(long j) {
        this.bytesTransferred += j;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.p010io.HttpTransportMetrics
    public void reset() {
        this.bytesTransferred = 0L;
    }
}
