package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.Header;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntityEnclosingRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.utils.CloneUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HTTP;

/* loaded from: classes2.dex */
public abstract class HttpEntityEnclosingRequestBase extends HttpRequestBase implements HttpEntityEnclosingRequest {
    private HttpEntity entity;

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntityEnclosingRequest
    public HttpEntity getEntity() {
        return this.entity;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntityEnclosingRequest
    public void setEntity(HttpEntity httpEntity) {
        this.entity = httpEntity;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntityEnclosingRequest
    public boolean expectContinue() {
        Header firstHeader = getFirstHeader("Expect");
        return firstHeader != null && HTTP.EXPECT_CONTINUE.equalsIgnoreCase(firstHeader.getValue());
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.AbstractExecutionAwareRequest
    public Object clone() throws CloneNotSupportedException {
        HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase = (HttpEntityEnclosingRequestBase) super.clone();
        HttpEntity httpEntity = this.entity;
        if (httpEntity != null) {
            httpEntityEnclosingRequestBase.entity = (HttpEntity) CloneUtils.cloneObject(httpEntity);
        }
        return httpEntityEnclosingRequestBase;
    }
}
