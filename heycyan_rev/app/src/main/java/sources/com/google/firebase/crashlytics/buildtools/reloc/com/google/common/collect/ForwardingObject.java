package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect;

/* loaded from: classes2.dex */
public abstract class ForwardingObject {
    protected abstract Object delegate();

    protected ForwardingObject() {
    }

    public String toString() {
        return delegate().toString();
    }
}
