package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;
import java.io.IOException;

/* loaded from: classes2.dex */
public class BasicResponseHandler extends AbstractResponseHandler<String> {
    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.AbstractResponseHandler
    public String handleEntity(HttpEntity httpEntity) throws IOException {
        return EntityUtils.toString(httpEntity);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.AbstractResponseHandler, com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ResponseHandler
    public String handleResponse(HttpResponse httpResponse) throws IOException {
        return (String) super.handleResponse(httpResponse);
    }
}
