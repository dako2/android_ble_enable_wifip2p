package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.concurrent.Cancellable;

/* loaded from: classes2.dex */
public interface HttpExecutionAware {
    boolean isAborted();

    void setCancellable(Cancellable cancellable);
}
