package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http;

/* loaded from: classes2.dex */
public interface HttpEntityEnclosingRequest extends HttpRequest {
    boolean expectContinue();

    HttpEntity getEntity();

    void setEntity(HttpEntity httpEntity);
}
