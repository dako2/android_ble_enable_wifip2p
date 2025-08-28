package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base;

import java.lang.ref.SoftReference;

/* loaded from: classes2.dex */
public abstract class FinalizableSoftReference<T> extends SoftReference<T> implements FinalizableReference {
    protected FinalizableSoftReference(T t, FinalizableReferenceQueue finalizableReferenceQueue) {
        super(t, finalizableReferenceQueue.queue);
        finalizableReferenceQueue.cleanUp();
    }
}
