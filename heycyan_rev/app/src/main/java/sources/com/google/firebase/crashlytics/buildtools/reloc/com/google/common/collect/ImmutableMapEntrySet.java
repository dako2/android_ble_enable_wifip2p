package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect;

import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.io.Serializable;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
abstract class ImmutableMapEntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {
    abstract ImmutableMap<K, V> map();

    static final class RegularEntrySet<K, V> extends ImmutableMapEntrySet<K, V> {
        private final transient ImmutableList<Map.Entry<K, V>> entries;
        private final transient ImmutableMap<K, V> map;

        RegularEntrySet(ImmutableMap<K, V> immutableMap, Map.Entry<K, V>[] entryArr) {
            this(immutableMap, ImmutableList.asImmutableList(entryArr));
        }

        RegularEntrySet(ImmutableMap<K, V> immutableMap, ImmutableList<Map.Entry<K, V>> immutableList) {
            this.map = immutableMap;
            this.entries = immutableList;
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMapEntrySet
        ImmutableMap<K, V> map() {
            return this.map;
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection
        int copyIntoArray(Object[] objArr, int i) {
            return this.entries.copyIntoArray(objArr, i);
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableSet, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.SortedIterable
        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return this.entries.iterator();
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection, java.util.Collection, java.lang.Iterable
        public Spliterator<Map.Entry<K, V>> spliterator() {
            return this.entries.spliterator();
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super Map.Entry<K, V>> consumer) {
            this.entries.forEach(consumer);
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableSet
        ImmutableList<Map.Entry<K, V>> createAsList() {
            return new RegularImmutableAsList(this, this.entries);
        }
    }

    ImmutableMapEntrySet() {
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return map().size();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(@NullableDecl Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        V v = map().get(entry.getKey());
        return v != null && v.equals(entry.getValue());
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection
    boolean isPartialView() {
        return map().isPartialView();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableSet
    boolean isHashCodeFast() {
        return map().isHashCodeFast();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableSet, java.util.Collection, java.util.Set
    public int hashCode() {
        return map().hashCode();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableSet, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection
    Object writeReplace() {
        return new EntrySetSerializedForm(map());
    }

    private static class EntrySetSerializedForm<K, V> implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap<K, V> map;

        EntrySetSerializedForm(ImmutableMap<K, V> immutableMap) {
            this.map = immutableMap;
        }

        Object readResolve() {
            return this.map.entrySet();
        }
    }
}
