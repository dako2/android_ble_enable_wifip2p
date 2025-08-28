package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Preconditions;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* loaded from: classes2.dex */
abstract class IndexedImmutableSet<E> extends ImmutableSet<E> {
    abstract E get(int i);

    IndexedImmutableSet() {
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableSet, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.SortedIterable
    public UnmodifiableIterator<E> iterator() {
        return asList().iterator();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection, java.util.Collection, java.lang.Iterable
    public Spliterator<E> spliterator() {
        return CollectSpliterators.indexed(size(), 1297, new IntFunction() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.IndexedImmutableSet$$ExternalSyntheticLambda0
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return this.f$0.get(i);
            }
        });
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer<? super E> consumer) {
        Preconditions.checkNotNull(consumer);
        int size = size();
        for (int i = 0; i < size; i++) {
            consumer.accept(get(i));
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection
    int copyIntoArray(Object[] objArr, int i) {
        return asList().copyIntoArray(objArr, i);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableSet
    ImmutableList<E> createAsList() {
        return new ImmutableAsList<E>() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.IndexedImmutableSet.1
            @Override // java.util.List
            public E get(int i) {
                return (E) IndexedImmutableSet.this.get(i);
            }

            @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableAsList, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection
            boolean isPartialView() {
                return IndexedImmutableSet.this.isPartialView();
            }

            @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableAsList, java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return IndexedImmutableSet.this.size();
            }

            @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableAsList
            ImmutableCollection<E> delegateCollection() {
                return IndexedImmutableSet.this;
            }
        };
    }
}
