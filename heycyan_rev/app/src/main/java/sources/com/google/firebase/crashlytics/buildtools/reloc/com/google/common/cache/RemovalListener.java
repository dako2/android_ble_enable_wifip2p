package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.cache;

@FunctionalInterface
/* loaded from: classes2.dex */
public interface RemovalListener<K, V> {
    void onRemoval(RemovalNotification<K, V> removalNotification);
}
