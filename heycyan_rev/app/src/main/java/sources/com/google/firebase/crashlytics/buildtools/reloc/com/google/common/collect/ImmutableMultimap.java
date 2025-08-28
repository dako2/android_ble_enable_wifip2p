package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Preconditions;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multiset;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Serialization;
import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/* loaded from: classes2.dex */
public abstract class ImmutableMultimap<K, V> extends AbstractMultimap<K, V> implements Serializable {
    private static final long serialVersionUID = 0;
    final transient ImmutableMap<K, ? extends ImmutableCollection<V>> map;
    final transient int size;

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap
    public abstract ImmutableCollection<V> get(K k);

    public abstract ImmutableMultimap<V, K> inverse();

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ boolean containsEntry(@NullableDecl Object obj, @NullableDecl Object obj2) {
        return super.containsEntry(obj, obj2);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap
    public /* bridge */ /* synthetic */ boolean equals(@NullableDecl Object obj) {
        return super.equals(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap
    public /* bridge */ /* synthetic */ Collection get(Object obj) {
        return get((ImmutableMultimap<K, V>) obj);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap
    @Deprecated
    public /* bridge */ /* synthetic */ Collection replaceValues(Object obj, Iterable iterable) {
        return replaceValues((ImmutableMultimap<K, V>) obj, iterable);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    /* renamed from: of */
    public static <K, V> ImmutableMultimap<K, V> m364of() {
        return ImmutableListMultimap.m352of();
    }

    /* renamed from: of */
    public static <K, V> ImmutableMultimap<K, V> m365of(K k, V v) {
        return ImmutableListMultimap.m353of((Object) k, (Object) v);
    }

    /* renamed from: of */
    public static <K, V> ImmutableMultimap<K, V> m366of(K k, V v, K k2, V v2) {
        return ImmutableListMultimap.m354of((Object) k, (Object) v, (Object) k2, (Object) v2);
    }

    /* renamed from: of */
    public static <K, V> ImmutableMultimap<K, V> m367of(K k, V v, K k2, V v2, K k3, V v3) {
        return ImmutableListMultimap.m355of((Object) k, (Object) v, (Object) k2, (Object) v2, (Object) k3, (Object) v3);
    }

    /* renamed from: of */
    public static <K, V> ImmutableMultimap<K, V> m368of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        return ImmutableListMultimap.m356of((Object) k, (Object) v, (Object) k2, (Object) v2, (Object) k3, (Object) v3, (Object) k4, (Object) v4);
    }

    /* renamed from: of */
    public static <K, V> ImmutableMultimap<K, V> m369of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return ImmutableListMultimap.m357of((Object) k, (Object) v, (Object) k2, (Object) v2, (Object) k3, (Object) v3, (Object) k4, (Object) v4, (Object) k5, (Object) v5);
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public static class Builder<K, V> {
        Map<K, Collection<V>> builderMap = Platform.preservesInsertionOrderOnPutsMap();

        @MonotonicNonNullDecl
        Comparator<? super K> keyComparator;

        @MonotonicNonNullDecl
        Comparator<? super V> valueComparator;

        Collection<V> newMutableValueCollection() {
            return new ArrayList();
        }

        public Builder<K, V> put(K k, V v) {
            CollectPreconditions.checkEntryNotNull(k, v);
            Collection<V> collection = this.builderMap.get(k);
            if (collection == null) {
                Map<K, Collection<V>> map = this.builderMap;
                Collection<V> collectionNewMutableValueCollection = newMutableValueCollection();
                map.put(k, collectionNewMutableValueCollection);
                collection = collectionNewMutableValueCollection;
            }
            collection.add(v);
            return this;
        }

        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            return put(entry.getKey(), entry.getValue());
        }

        public Builder<K, V> putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            Iterator<? extends Map.Entry<? extends K, ? extends V>> it = iterable.iterator();
            while (it.hasNext()) {
                put(it.next());
            }
            return this;
        }

        public Builder<K, V> putAll(K k, Iterable<? extends V> iterable) {
            if (k == null) {
                throw new NullPointerException("null key in entry: null=" + Iterables.toString(iterable));
            }
            Collection<V> collection = this.builderMap.get(k);
            if (collection != null) {
                for (V v : iterable) {
                    CollectPreconditions.checkEntryNotNull(k, v);
                    collection.add(v);
                }
                return this;
            }
            Iterator<? extends V> it = iterable.iterator();
            if (!it.hasNext()) {
                return this;
            }
            Collection<V> collectionNewMutableValueCollection = newMutableValueCollection();
            while (it.hasNext()) {
                V next = it.next();
                CollectPreconditions.checkEntryNotNull(k, next);
                collectionNewMutableValueCollection.add(next);
            }
            this.builderMap.put(k, collectionNewMutableValueCollection);
            return this;
        }

        public Builder<K, V> putAll(K k, V... vArr) {
            return putAll((Builder<K, V>) k, Arrays.asList(vArr));
        }

        public Builder<K, V> putAll(Multimap<? extends K, ? extends V> multimap) {
            for (Map.Entry<? extends K, Collection<? extends V>> entry : multimap.asMap().entrySet()) {
                putAll((Builder<K, V>) entry.getKey(), entry.getValue());
            }
            return this;
        }

        public Builder<K, V> orderKeysBy(Comparator<? super K> comparator) {
            this.keyComparator = (Comparator) Preconditions.checkNotNull(comparator);
            return this;
        }

        public Builder<K, V> orderValuesBy(Comparator<? super V> comparator) {
            this.valueComparator = (Comparator) Preconditions.checkNotNull(comparator);
            return this;
        }

        Builder<K, V> combine(Builder<K, V> builder) {
            for (Map.Entry<K, Collection<V>> entry : builder.builderMap.entrySet()) {
                putAll((Builder<K, V>) entry.getKey(), entry.getValue());
            }
            return this;
        }

        public ImmutableMultimap<K, V> build() {
            Collection collectionEntrySet = this.builderMap.entrySet();
            Comparator<? super K> comparator = this.keyComparator;
            if (comparator != null) {
                collectionEntrySet = Ordering.from(comparator).onKeys().immutableSortedCopy(collectionEntrySet);
            }
            return ImmutableListMultimap.fromMapEntries(collectionEntrySet, this.valueComparator);
        }
    }

    public static <K, V> ImmutableMultimap<K, V> copyOf(Multimap<? extends K, ? extends V> multimap) {
        if (multimap instanceof ImmutableMultimap) {
            ImmutableMultimap<K, V> immutableMultimap = (ImmutableMultimap) multimap;
            if (!immutableMultimap.isPartialView()) {
                return immutableMultimap;
            }
        }
        return ImmutableListMultimap.copyOf((Multimap) multimap);
    }

    public static <K, V> ImmutableMultimap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        return ImmutableListMultimap.copyOf((Iterable) iterable);
    }

    static class FieldSettersHolder {
        static final Serialization.FieldSetter<ImmutableMultimap> MAP_FIELD_SETTER = Serialization.getFieldSetter(ImmutableMultimap.class, "map");
        static final Serialization.FieldSetter<ImmutableMultimap> SIZE_FIELD_SETTER = Serialization.getFieldSetter(ImmutableMultimap.class, "size");

        FieldSettersHolder() {
        }
    }

    ImmutableMultimap(ImmutableMap<K, ? extends ImmutableCollection<V>> immutableMap, int i) {
        this.map = immutableMap;
        this.size = i;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap
    @Deprecated
    public ImmutableCollection<V> removeAll(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap
    @Deprecated
    public ImmutableCollection<V> replaceValues(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap
    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap
    @Deprecated
    public boolean put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap
    @Deprecated
    public boolean putAll(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap
    @Deprecated
    public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap
    @Deprecated
    public boolean remove(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    boolean isPartialView() {
        return this.map.isPartialView();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap
    public boolean containsKey(@NullableDecl Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap
    public boolean containsValue(@NullableDecl Object obj) {
        return obj != null && super.containsValue(obj);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap
    public int size() {
        return this.size;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap
    public ImmutableSet<K> keySet() {
        return this.map.keySet();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap
    Set<K> createKeySet() {
        throw new AssertionError("unreachable");
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ListMultimap
    public ImmutableMap<K, Collection<V>> asMap() {
        return this.map;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap
    Map<K, Collection<V>> createAsMap() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap
    public ImmutableCollection<Map.Entry<K, V>> entries() {
        return (ImmutableCollection) super.entries();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap
    public ImmutableCollection<Map.Entry<K, V>> createEntries() {
        return new EntryCollection(this);
    }

    private static class EntryCollection<K, V> extends ImmutableCollection<Map.Entry<K, V>> {
        private static final long serialVersionUID = 0;
        final ImmutableMultimap<K, V> multimap;

        EntryCollection(ImmutableMultimap<K, V> immutableMultimap) {
            this.multimap = immutableMultimap;
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.SortedIterable
        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return this.multimap.entryIterator();
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection
        boolean isPartialView() {
            return this.multimap.isPartialView();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.multimap.size();
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return this.multimap.containsEntry(entry.getKey(), entry.getValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap
    public UnmodifiableIterator<Map.Entry<K, V>> entryIterator() {
        return new UnmodifiableIterator<Map.Entry<K, V>>() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMultimap.1
            final Iterator<? extends Map.Entry<K, ? extends ImmutableCollection<V>>> asMapItr;
            K currentKey = null;
            Iterator<V> valueItr = Iterators.emptyIterator();

            {
                this.asMapItr = ImmutableMultimap.this.map.entrySet().iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.valueItr.hasNext() || this.asMapItr.hasNext();
            }

            @Override // java.util.Iterator
            public Map.Entry<K, V> next() {
                if (!this.valueItr.hasNext()) {
                    Map.Entry<K, ? extends ImmutableCollection<V>> next = this.asMapItr.next();
                    this.currentKey = next.getKey();
                    this.valueItr = next.getValue().iterator();
                }
                return Maps.immutableEntry(this.currentKey, this.valueItr.next());
            }
        };
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap
    Spliterator<Map.Entry<K, V>> entrySpliterator() {
        return CollectSpliterators.flatMap(asMap().entrySet().spliterator(), new Function() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMultimap$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ImmutableMultimap.lambda$entrySpliterator$1((Map.Entry) obj);
            }
        }, (this instanceof SetMultimap ? 1 : 0) | 64, size());
    }

    static /* synthetic */ Spliterator lambda$entrySpliterator$1(Map.Entry entry) {
        final Object key = entry.getKey();
        return CollectSpliterators.map(((Collection) entry.getValue()).spliterator(), new Function() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMultimap$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Maps.immutableEntry(key, obj);
            }
        });
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap
    public void forEach(final BiConsumer<? super K, ? super V> biConsumer) {
        Preconditions.checkNotNull(biConsumer);
        asMap().forEach(new BiConsumer() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMultimap$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((Collection) obj2).forEach(new Consumer() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMultimap$$ExternalSyntheticLambda1
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj3) {
                        biConsumer.accept(obj, obj3);
                    }
                });
            }
        });
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap
    public ImmutableMultiset<K> keys() {
        return (ImmutableMultiset) super.keys();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap
    public ImmutableMultiset<K> createKeys() {
        return new Keys();
    }

    class Keys extends ImmutableMultiset<K> {
        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection
        boolean isPartialView() {
            return true;
        }

        Keys() {
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMultiset, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            return ImmutableMultimap.this.containsKey(obj);
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multiset
        public int count(@NullableDecl Object obj) {
            ImmutableCollection<V> immutableCollection = ImmutableMultimap.this.map.get(obj);
            if (immutableCollection == null) {
                return 0;
            }
            return immutableCollection.size();
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMultiset, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multiset
        public ImmutableSet<K> elementSet() {
            return ImmutableMultimap.this.keySet();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multiset
        public int size() {
            return ImmutableMultimap.this.size();
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMultiset
        Multiset.Entry<K> getEntry(int i) {
            Map.Entry<K, ? extends ImmutableCollection<V>> entry = ImmutableMultimap.this.map.entrySet().asList().get(i);
            return Multisets.immutableEntry(entry.getKey(), entry.getValue().size());
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMultiset, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection
        Object writeReplace() {
            return new KeysSerializedForm(ImmutableMultimap.this);
        }
    }

    private static final class KeysSerializedForm implements Serializable {
        final ImmutableMultimap<?, ?> multimap;

        KeysSerializedForm(ImmutableMultimap<?, ?> immutableMultimap) {
            this.multimap = immutableMultimap;
        }

        Object readResolve() {
            return this.multimap.keys();
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimap
    public ImmutableCollection<V> values() {
        return (ImmutableCollection) super.values();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap
    public ImmutableCollection<V> createValues() {
        return new Values(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultimap
    public UnmodifiableIterator<V> valueIterator() {
        return new UnmodifiableIterator<V>() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMultimap.2
            Iterator<? extends ImmutableCollection<V>> valueCollectionItr;
            Iterator<V> valueItr = Iterators.emptyIterator();

            {
                this.valueCollectionItr = ImmutableMultimap.this.map.values().iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.valueItr.hasNext() || this.valueCollectionItr.hasNext();
            }

            @Override // java.util.Iterator
            public V next() {
                if (!this.valueItr.hasNext()) {
                    this.valueItr = this.valueCollectionItr.next().iterator();
                }
                return this.valueItr.next();
            }
        };
    }

    private static final class Values<K, V> extends ImmutableCollection<V> {
        private static final long serialVersionUID = 0;
        private final transient ImmutableMultimap<K, V> multimap;

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection
        boolean isPartialView() {
            return true;
        }

        Values(ImmutableMultimap<K, V> immutableMultimap) {
            this.multimap = immutableMultimap;
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            return this.multimap.containsValue(obj);
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.SortedIterable
        public UnmodifiableIterator<V> iterator() {
            return this.multimap.valueIterator();
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableCollection
        int copyIntoArray(Object[] objArr, int i) {
            UnmodifiableIterator<? extends ImmutableCollection<V>> it = this.multimap.map.values().iterator();
            while (it.hasNext()) {
                i = it.next().copyIntoArray(objArr, i);
            }
            return i;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.multimap.size();
        }
    }
}
