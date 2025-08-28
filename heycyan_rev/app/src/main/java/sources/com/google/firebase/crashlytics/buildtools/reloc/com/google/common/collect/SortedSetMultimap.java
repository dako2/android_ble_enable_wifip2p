package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect;

import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

/* loaded from: classes2.dex */
public interface SortedSetMultimap<K, V> extends SetMultimap<K, V> {
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.SetMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap
    Map<K, Collection<V>> asMap();

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.SetMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap
    SortedSet<V> get(@NullableDecl K k);

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.SetMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap
    SortedSet<V> removeAll(@NullableDecl Object obj);

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.SetMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap
    SortedSet<V> replaceValues(K k, Iterable<? extends V> iterable);

    Comparator<? super V> valueComparator();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.SetMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap
    /* bridge */ /* synthetic */ default Collection get(@NullableDecl Object obj) {
        return get((SortedSetMultimap<K, V>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.SetMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap
    /* bridge */ /* synthetic */ default Set get(@NullableDecl Object obj) {
        return get((SortedSetMultimap<K, V>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.SetMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap
    /* bridge */ /* synthetic */ default Collection replaceValues(Object obj, Iterable iterable) {
        return replaceValues((SortedSetMultimap<K, V>) obj, iterable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.SetMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap
    /* bridge */ /* synthetic */ default Set replaceValues(Object obj, Iterable iterable) {
        return replaceValues((SortedSetMultimap<K, V>) obj, iterable);
    }
}
