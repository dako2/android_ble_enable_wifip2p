package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Preconditions;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;

/* loaded from: classes2.dex */
public abstract class ImmutableBiMap<K, V> extends ImmutableBiMapFauxverideShim<K, V> implements BiMap<K, V> {
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.BiMap
    public abstract ImmutableBiMap<V, K> inverse();

    public static <T, K, V> Collector<T, ?, ImmutableBiMap<K, V>> toImmutableBiMap(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2) {
        return CollectCollectors.toImmutableBiMap(function, function2);
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m331of() {
        return RegularImmutableBiMap.EMPTY;
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m332of(K k, V v) {
        return new SingletonImmutableBiMap(k, v);
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m333of(K k, V v, K k2, V v2) {
        return RegularImmutableBiMap.fromEntries(entryOf(k, v), entryOf(k2, v2));
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m334of(K k, V v, K k2, V v2, K k3, V v3) {
        return RegularImmutableBiMap.fromEntries(entryOf(k, v), entryOf(k2, v2), entryOf(k3, v3));
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m335of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        return RegularImmutableBiMap.fromEntries(entryOf(k, v), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4));
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m336of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return RegularImmutableBiMap.fromEntries(entryOf(k, v), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5));
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public static <K, V> Builder<K, V> builderWithExpectedSize(int i) {
        CollectPreconditions.checkNonnegative(i, "expectedSize");
        return new Builder<>(i);
    }

    public static final class Builder<K, V> extends ImmutableMap.Builder<K, V> {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMap.Builder
        public /* bridge */ /* synthetic */ ImmutableMap.Builder put(Object obj, Object obj2) {
            return put((Builder<K, V>) obj, obj2);
        }

        public Builder() {
        }

        Builder(int i) {
            super(i);
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> put(K k, V v) {
            super.put((Builder<K, V>) k, (K) v);
            return this;
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            super.put((Map.Entry) entry);
            return this;
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
            super.putAll((Map) map);
            return this;
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            super.putAll((Iterable) iterable);
            return this;
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> orderEntriesByValue(Comparator<? super V> comparator) {
            super.orderEntriesByValue((Comparator) comparator);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> combine(ImmutableMap.Builder<K, V> builder) {
            super.combine((ImmutableMap.Builder) builder);
            return this;
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMap.Builder
        public ImmutableBiMap<K, V> build() {
            int i = this.size;
            if (i == 0) {
                return ImmutableBiMap.m331of();
            }
            if (i == 1) {
                return ImmutableBiMap.m332of((Object) this.entries[0].getKey(), (Object) this.entries[0].getValue());
            }
            if (this.valueComparator != null) {
                if (this.entriesUsed) {
                    this.entries = (Map.Entry[]) Arrays.copyOf(this.entries, this.size);
                }
                Arrays.sort(this.entries, 0, this.size, Ordering.from(this.valueComparator).onResultOf(Maps.valueFunction()));
            }
            this.entriesUsed = true;
            return RegularImmutableBiMap.fromEntryArray(this.size, this.entries);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMap.Builder
        public ImmutableBiMap<K, V> buildJdkBacked() {
            Preconditions.checkState(this.valueComparator == null, "buildJdkBacked is for tests only, doesn't support orderEntriesByValue");
            int i = this.size;
            if (i == 0) {
                return ImmutableBiMap.m331of();
            }
            if (i == 1) {
                return ImmutableBiMap.m332of((Object) this.entries[0].getKey(), (Object) this.entries[0].getValue());
            }
            this.entriesUsed = true;
            return RegularImmutableBiMap.fromEntryArray(this.size, this.entries);
        }
    }

    public static <K, V> ImmutableBiMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        if (map instanceof ImmutableBiMap) {
            ImmutableBiMap<K, V> immutableBiMap = (ImmutableBiMap) map;
            if (!immutableBiMap.isPartialView()) {
                return immutableBiMap;
            }
        }
        return copyOf((Iterable) map.entrySet());
    }

    public static <K, V> ImmutableBiMap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        Map.Entry[] entryArr = (Map.Entry[]) Iterables.toArray(iterable, EMPTY_ENTRY_ARRAY);
        int length = entryArr.length;
        if (length == 0) {
            return m331of();
        }
        if (length == 1) {
            Map.Entry entry = entryArr[0];
            return m332of(entry.getKey(), entry.getValue());
        }
        return RegularImmutableBiMap.fromEntries(entryArr);
    }

    ImmutableBiMap() {
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMap, java.util.Map, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.BiMap
    public ImmutableSet<V> values() {
        return inverse().keySet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMap
    public final ImmutableSet<V> createValues() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.BiMap
    @Deprecated
    public V forcePut(K k, V v) {
        throw new UnsupportedOperationException();
    }

    private static class SerializedForm extends ImmutableMap.SerializedForm {
        private static final long serialVersionUID = 0;

        SerializedForm(ImmutableBiMap<?, ?> immutableBiMap) {
            super(immutableBiMap);
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMap.SerializedForm
        Object readResolve() {
            return createMap(new Builder());
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMap
    Object writeReplace() {
        return new SerializedForm(this);
    }
}
