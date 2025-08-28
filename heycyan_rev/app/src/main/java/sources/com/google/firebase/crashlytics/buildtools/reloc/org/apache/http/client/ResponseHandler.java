package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import java.io.IOException;

/* loaded from: classes2.dex */
public interface ResponseHandler<T> {
    T handleResponse(HttpResponse httpResponse) throws IOException;
}
