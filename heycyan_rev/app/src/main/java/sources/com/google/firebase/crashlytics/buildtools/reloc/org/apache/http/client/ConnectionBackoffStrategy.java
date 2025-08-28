package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;

/* loaded from: classes2.dex */
public interface ConnectionBackoffStrategy {
    boolean shouldBackoff(HttpResponse httpResponse);

    boolean shouldBackoff(Throwable th);
}
