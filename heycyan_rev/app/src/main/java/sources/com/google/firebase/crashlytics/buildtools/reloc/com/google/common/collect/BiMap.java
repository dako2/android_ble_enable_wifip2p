package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect;

import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public interface BiMap<K, V> extends Map<K, V> {
    @NullableDecl
    V forcePut(@NullableDecl K k, @NullableDecl V v);

    BiMap<V, K> inverse();

    @NullableDecl
    V put(@NullableDecl K k, @NullableDecl V v);

    void putAll(Map<? extends K, ? extends V> map);

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.BiMap
    Set<V> values();
}
