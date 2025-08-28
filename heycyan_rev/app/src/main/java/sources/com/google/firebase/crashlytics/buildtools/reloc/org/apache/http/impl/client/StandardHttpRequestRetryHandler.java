package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpDelete;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPut;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpTrace;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public class StandardHttpRequestRetryHandler extends DefaultHttpRequestRetryHandler {
    private final Map<String, Boolean> idempotentMethods;

    public StandardHttpRequestRetryHandler(int i, boolean z) {
        super(i, z);
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        this.idempotentMethods = concurrentHashMap;
        concurrentHashMap.put(HttpGet.METHOD_NAME, Boolean.TRUE);
        concurrentHashMap.put("HEAD", Boolean.TRUE);
        concurrentHashMap.put(HttpPut.METHOD_NAME, Boolean.TRUE);
        concurrentHashMap.put(HttpDelete.METHOD_NAME, Boolean.TRUE);
        concurrentHashMap.put("OPTIONS", Boolean.TRUE);
        concurrentHashMap.put(HttpTrace.METHOD_NAME, Boolean.TRUE);
    }

    public StandardHttpRequestRetryHandler() {
        this(3, false);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpRequestRetryHandler
    protected boolean handleAsIdempotent(HttpRequest httpRequest) {
        Boolean bool = this.idempotentMethods.get(httpRequest.getRequestLine().getMethod().toUpperCase(Locale.ROOT));
        return bool != null && bool.booleanValue();
    }
}
