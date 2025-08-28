package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.concurrent;

/* loaded from: classes2.dex */
public interface FutureCallback<T> {
    void cancelled();

    void completed(T t);

    void failed(Exception exc);
}
