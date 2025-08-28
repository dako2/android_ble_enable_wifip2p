package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol;

@Deprecated
/* loaded from: classes2.dex */
public class SyncBasicHttpContext extends BasicHttpContext {
    public SyncBasicHttpContext(HttpContext httpContext) {
        super(httpContext);
    }

    public SyncBasicHttpContext() {
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.BasicHttpContext, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext
    public synchronized Object getAttribute(String str) {
        return super.getAttribute(str);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.BasicHttpContext, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext
    public synchronized void setAttribute(String str, Object obj) {
        super.setAttribute(str, obj);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.BasicHttpContext, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext
    public synchronized Object removeAttribute(String str) {
        return super.removeAttribute(str);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.BasicHttpContext
    public synchronized void clear() {
        super.clear();
    }
}
