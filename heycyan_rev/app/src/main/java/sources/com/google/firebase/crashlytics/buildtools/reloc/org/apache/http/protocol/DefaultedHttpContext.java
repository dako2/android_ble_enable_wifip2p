package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;

@Deprecated
/* loaded from: classes2.dex */
public final class DefaultedHttpContext implements HttpContext {
    private final HttpContext defaults;
    private final HttpContext local;

    public DefaultedHttpContext(HttpContext httpContext, HttpContext httpContext2) {
        this.local = (HttpContext) Args.notNull(httpContext, "HTTP context");
        this.defaults = httpContext2;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext
    public Object getAttribute(String str) {
        Object attribute = this.local.getAttribute(str);
        return attribute == null ? this.defaults.getAttribute(str) : attribute;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext
    public Object removeAttribute(String str) {
        return this.local.removeAttribute(str);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext
    public void setAttribute(String str, Object obj) {
        this.local.setAttribute(str, obj);
    }

    public HttpContext getDefaults() {
        return this.defaults;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[local: ");
        sb.append(this.local);
        sb.append("defaults: ").append(this.defaults);
        sb.append("]");
        return sb.toString();
    }
}
