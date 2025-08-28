package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Preconditions;
import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public interface Multimap<K, V> {
    Map<K, Collection<V>> asMap();

    void clear();

    boolean containsEntry(@NullableDecl Object obj, @NullableDecl Object obj2);

    boolean containsKey(@NullableDecl Object obj);

    boolean containsValue(@NullableDecl Object obj);

    Collection<Map.Entry<K, V>> entries();

    boolean equals(@NullableDecl Object obj);

    Collection<V> get(@NullableDecl K k);

    int hashCode();

    boolean isEmpty();

    Set<K> keySet();

    Multiset<K> keys();

    boolean put(@NullableDecl K k, @NullableDecl V v);

    boolean putAll(Multimap<? extends K, ? extends V> multimap);

    boolean putAll(@NullableDecl K k, Iterable<? extends V> iterable);

    boolean remove(@NullableDecl Object obj, @NullableDecl Object obj2);

    Collection<V> removeAll(@NullableDecl Object obj);

    Collection<V> replaceValues(@NullableDecl K k, Iterable<? extends V> iterable);

    int size();

    Collection<V> values();

    default void forEach(final BiConsumer<? super K, ? super V> biConsumer) {
        Preconditions.checkNotNull(biConsumer);
        entries().forEach(new Consumer() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Map.Entry entry = (Map.Entry) obj;
                biConsumer.accept(entry.getKey(), entry.getValue());
            }
        });
    }
}
