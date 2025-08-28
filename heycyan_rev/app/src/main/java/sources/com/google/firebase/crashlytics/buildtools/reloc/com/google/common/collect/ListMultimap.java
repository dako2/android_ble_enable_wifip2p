package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect;

import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public interface ListMultimap<K, V> extends Multimap<K, V> {
    Map<K, Collection<V>> asMap();

    boolean equals(@NullableDecl Object obj);

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap
    List<V> get(@NullableDecl K k);

    @Override // 
    List<V> removeAll(@NullableDecl Object obj);

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap
    List<V> replaceValues(K k, Iterable<? extends V> iterable);

    /* JADX WARN: Multi-variable type inference failed */
    /* bridge */ /* synthetic */ default Collection get(@NullableDecl Object obj) {
        return get((ListMultimap<K, V>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* bridge */ /* synthetic */ default Collection replaceValues(Object obj, Iterable iterable) {
        return replaceValues((ListMultimap<K, V>) obj, iterable);
    }
}
