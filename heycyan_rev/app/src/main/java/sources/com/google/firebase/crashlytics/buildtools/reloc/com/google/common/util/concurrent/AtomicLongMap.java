package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.util.concurrent;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Preconditions;
import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;

/* loaded from: classes2.dex */
public final class AtomicLongMap<K> implements Serializable {

    @MonotonicNonNullDecl
    private transient Map<K, Long> asMap;
    private final ConcurrentHashMap<K, Long> map;

    static /* synthetic */ long lambda$put$4(long j, long j2) {
        return j;
    }

    private AtomicLongMap(ConcurrentHashMap<K, Long> concurrentHashMap) {
        this.map = (ConcurrentHashMap) Preconditions.checkNotNull(concurrentHashMap);
    }

    public static <K> AtomicLongMap<K> create() {
        return new AtomicLongMap<>(new ConcurrentHashMap());
    }

    public static <K> AtomicLongMap<K> create(Map<? extends K, ? extends Long> map) {
        AtomicLongMap<K> atomicLongMapCreate = create();
        atomicLongMapCreate.putAll(map);
        return atomicLongMapCreate;
    }

    public long get(K k) {
        return this.map.getOrDefault(k, 0L).longValue();
    }

    public long incrementAndGet(K k) {
        return addAndGet(k, 1L);
    }

    public long decrementAndGet(K k) {
        return addAndGet(k, -1L);
    }

    public long addAndGet(K k, long j) {
        return accumulateAndGet(k, j, new AtomicLongMap$$ExternalSyntheticLambda2());
    }

    public long getAndIncrement(K k) {
        return getAndAdd(k, 1L);
    }

    public long getAndDecrement(K k) {
        return getAndAdd(k, -1L);
    }

    public long getAndAdd(K k, long j) {
        return getAndAccumulate(k, j, new AtomicLongMap$$ExternalSyntheticLambda2());
    }

    public long updateAndGet(K k, final LongUnaryOperator longUnaryOperator) {
        Preconditions.checkNotNull(longUnaryOperator);
        return this.map.compute(k, new BiFunction() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.util.concurrent.AtomicLongMap$$ExternalSyntheticLambda4
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                Long l = (Long) obj2;
                return Long.valueOf(longUnaryOperator.applyAsLong(l == null ? 0L : l.longValue()));
            }
        }).longValue();
    }

    public long getAndUpdate(K k, final LongUnaryOperator longUnaryOperator) {
        Preconditions.checkNotNull(longUnaryOperator);
        final AtomicLong atomicLong = new AtomicLong();
        this.map.compute(k, new BiFunction() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.util.concurrent.AtomicLongMap$$ExternalSyntheticLambda1
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return AtomicLongMap.lambda$getAndUpdate$1(atomicLong, longUnaryOperator, obj, (Long) obj2);
            }
        });
        return atomicLong.get();
    }

    static /* synthetic */ Long lambda$getAndUpdate$1(AtomicLong atomicLong, LongUnaryOperator longUnaryOperator, Object obj, Long l) {
        long jLongValue = l == null ? 0L : l.longValue();
        atomicLong.set(jLongValue);
        return Long.valueOf(longUnaryOperator.applyAsLong(jLongValue));
    }

    public long accumulateAndGet(K k, final long j, final LongBinaryOperator longBinaryOperator) {
        Preconditions.checkNotNull(longBinaryOperator);
        return updateAndGet(k, new LongUnaryOperator() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.util.concurrent.AtomicLongMap$$ExternalSyntheticLambda9
            @Override // java.util.function.LongUnaryOperator
            public final long applyAsLong(long j2) {
                return longBinaryOperator.applyAsLong(j2, j);
            }
        });
    }

    public long getAndAccumulate(K k, final long j, final LongBinaryOperator longBinaryOperator) {
        Preconditions.checkNotNull(longBinaryOperator);
        return getAndUpdate(k, new LongUnaryOperator() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.util.concurrent.AtomicLongMap$$ExternalSyntheticLambda7
            @Override // java.util.function.LongUnaryOperator
            public final long applyAsLong(long j2) {
                return longBinaryOperator.applyAsLong(j2, j);
            }
        });
    }

    public long put(K k, final long j) {
        return getAndUpdate(k, new LongUnaryOperator() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.util.concurrent.AtomicLongMap$$ExternalSyntheticLambda3
            @Override // java.util.function.LongUnaryOperator
            public final long applyAsLong(long j2) {
                return AtomicLongMap.lambda$put$4(j, j2);
            }
        });
    }

    public void putAll(Map<? extends K, ? extends Long> map) {
        map.forEach(new BiConsumer() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.util.concurrent.AtomicLongMap$$ExternalSyntheticLambda8
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f$0.put(obj, ((Long) obj2).longValue());
            }
        });
    }

    public long remove(K k) {
        Long lRemove = this.map.remove(k);
        if (lRemove == null) {
            return 0L;
        }
        return lRemove.longValue();
    }

    boolean remove(K k, long j) {
        return this.map.remove(k, Long.valueOf(j));
    }

    public boolean removeIfZero(K k) {
        return remove(k, 0L);
    }

    static /* synthetic */ boolean lambda$removeAllZeros$5(Long l) {
        return l.longValue() == 0;
    }

    public void removeAllZeros() {
        this.map.values().removeIf(new Predicate() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.util.concurrent.AtomicLongMap$$ExternalSyntheticLambda6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return AtomicLongMap.lambda$removeAllZeros$5((Long) obj);
            }
        });
    }

    public long sum() {
        return this.map.values().stream().mapToLong(new ToLongFunction() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.util.concurrent.AtomicLongMap$$ExternalSyntheticLambda5
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return ((Long) obj).longValue();
            }
        }).sum();
    }

    public Map<K, Long> asMap() {
        Map<K, Long> map = this.asMap;
        if (map != null) {
            return map;
        }
        Map<K, Long> mapCreateAsMap = createAsMap();
        this.asMap = mapCreateAsMap;
        return mapCreateAsMap;
    }

    private Map<K, Long> createAsMap() {
        return Collections.unmodifiableMap(this.map);
    }

    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public void clear() {
        this.map.clear();
    }

    public String toString() {
        return this.map.toString();
    }

    long putIfAbsent(K k, final long j) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        Long lCompute = this.map.compute(k, new BiFunction() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.util.concurrent.AtomicLongMap$$ExternalSyntheticLambda0
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return AtomicLongMap.lambda$putIfAbsent$6(atomicBoolean, j, obj, (Long) obj2);
            }
        });
        if (atomicBoolean.get()) {
            return 0L;
        }
        return lCompute.longValue();
    }

    static /* synthetic */ Long lambda$putIfAbsent$6(AtomicBoolean atomicBoolean, long j, Object obj, Long l) {
        if (l != null && l.longValue() != 0) {
            return l;
        }
        atomicBoolean.set(true);
        return Long.valueOf(j);
    }

    boolean replace(K k, long j, long j2) {
        if (j == 0) {
            return putIfAbsent(k, j2) == 0;
        }
        return this.map.replace(k, Long.valueOf(j), Long.valueOf(j2));
    }
}
