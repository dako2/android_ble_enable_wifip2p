package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Preconditions;
import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.io.Serializable;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
final class ImmutableMapKeySet<K, V> extends IndexedImmutableSet<K> {
    private final ImmutableMap<K, V> map;

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection
    boolean isPartialView() {
        return true;
    }

    ImmutableMapKeySet(ImmutableMap<K, V> immutableMap) {
        this.map = immutableMap;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.map.size();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.IndexedImmutableSet, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableSet, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.SortedIterable
    public UnmodifiableIterator<K> iterator() {
        return this.map.keyIterator();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.IndexedImmutableSet, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection, java.util.Collection, java.lang.Iterable
    public Spliterator<K> spliterator() {
        return this.map.keySpliterator();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(@NullableDecl Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.IndexedImmutableSet
    K get(int i) {
        return this.map.entrySet().asList().get(i).getKey();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.IndexedImmutableSet, java.lang.Iterable
    public void forEach(final Consumer<? super K> consumer) {
        Preconditions.checkNotNull(consumer);
        this.map.forEach(new BiConsumer() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMapKeySet$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                consumer.accept(obj);
            }
        });
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableSet, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection
    Object writeReplace() {
        return new KeySetSerializedForm(this.map);
    }

    private static class KeySetSerializedForm<K> implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap<K, ?> map;

        KeySetSerializedForm(ImmutableMap<K, ?> immutableMap) {
            this.map = immutableMap;
        }

        Object readResolve() {
            return this.map.keySet();
        }
    }
}
