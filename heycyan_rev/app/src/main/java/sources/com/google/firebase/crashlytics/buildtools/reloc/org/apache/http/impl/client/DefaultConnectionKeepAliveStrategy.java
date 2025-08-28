package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HeaderElement;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ConnectionKeepAliveStrategy;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicHeaderElementIterator;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HTTP;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;

/* loaded from: classes2.dex */
public class DefaultConnectionKeepAliveStrategy implements ConnectionKeepAliveStrategy {
    public static final DefaultConnectionKeepAliveStrategy INSTANCE = new DefaultConnectionKeepAliveStrategy();

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.ConnectionKeepAliveStrategy
    public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        Args.notNull(httpResponse, "HTTP response");
        BasicHeaderElementIterator basicHeaderElementIterator = new BasicHeaderElementIterator(httpResponse.headerIterator(HTTP.CONN_KEEP_ALIVE));
        while (basicHeaderElementIterator.hasNext()) {
            HeaderElement headerElementNextElement = basicHeaderElementIterator.nextElement();
            String name = headerElementNextElement.getName();
            String value = headerElementNextElement.getValue();
            if (value != null && name.equalsIgnoreCase("timeout")) {
                try {
                    return Long.parseLong(value) * 1000;
                } catch (NumberFormatException unused) {
                    continue;
                }
            }
        }
        return -1L;
    }
}
