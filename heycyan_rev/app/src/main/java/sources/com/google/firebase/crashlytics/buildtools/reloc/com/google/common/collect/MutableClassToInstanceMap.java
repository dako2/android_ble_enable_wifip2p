package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Preconditions;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.primitives.Primitives;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Function;

/* loaded from: classes2.dex */
public final class MutableClassToInstanceMap<B> extends ForwardingMap<Class<? extends B>, B> implements ClassToInstanceMap<B>, Serializable {
    private final Map<Class<? extends B>, B> delegate;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ForwardingMap, java.util.Map, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.BiMap
    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
        return put((Class<? extends Class<? extends B>>) obj, (Class<? extends B>) obj2);
    }

    public static <B> MutableClassToInstanceMap<B> create() {
        return new MutableClassToInstanceMap<>(new HashMap());
    }

    public static <B> MutableClassToInstanceMap<B> create(Map<Class<? extends B>, B> map) {
        return new MutableClassToInstanceMap<>(map);
    }

    private MutableClassToInstanceMap(Map<Class<? extends B>, B> map) {
        this.delegate = (Map) Preconditions.checkNotNull(map);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ForwardingMap, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ForwardingObject
    public Map<Class<? extends B>, B> delegate() {
        return this.delegate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <B> Map.Entry<Class<? extends B>, B> checkedEntry(final Map.Entry<Class<? extends B>, B> entry) {
        return new ForwardingMapEntry<Class<? extends B>, B>() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.MutableClassToInstanceMap.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ForwardingMapEntry, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ForwardingObject
            public Map.Entry<Class<? extends B>, B> delegate() {
                return entry;
            }

            @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ForwardingMapEntry, java.util.Map.Entry
            public B setValue(B b) {
                return (B) super.setValue(MutableClassToInstanceMap.cast(getKey(), b));
            }
        };
    }

    /* renamed from: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.MutableClassToInstanceMap$2 */
    class C17222 extends ForwardingSet<Map.Entry<Class<? extends B>, B>> {
        C17222() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ForwardingSet, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ForwardingCollection, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ForwardingObject
        public Set<Map.Entry<Class<? extends B>, B>> delegate() {
            return MutableClassToInstanceMap.this.delegate().entrySet();
        }

        @Override // java.util.Collection, java.lang.Iterable, java.util.Set
        public Spliterator<Map.Entry<Class<? extends B>, B>> spliterator() {
            return CollectSpliterators.map(delegate().spliterator(), new Function() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.MutableClassToInstanceMap$2$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return MutableClassToInstanceMap.checkedEntry((Map.Entry) obj);
                }
            });
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<Class<? extends B>, B>> iterator() {
            return new TransformedIterator<Map.Entry<Class<? extends B>, B>, Map.Entry<Class<? extends B>, B>>(delegate().iterator()) { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.MutableClassToInstanceMap.2.1
                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.TransformedIterator
                public Map.Entry<Class<? extends B>, B> transform(Map.Entry<Class<? extends B>, B> entry) {
                    return MutableClassToInstanceMap.checkedEntry(entry);
                }
            };
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) standardToArray(tArr);
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ForwardingMap, java.util.Map
    public Set<Map.Entry<Class<? extends B>, B>> entrySet() {
        return new C17222();
    }

    public B put(Class<? extends B> cls, B b) {
        return (B) super.put((MutableClassToInstanceMap<B>) cls, (Class<? extends B>) cast(cls, b));
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ForwardingMap, java.util.Map, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.BiMap
    public void putAll(Map<? extends Class<? extends B>, ? extends B> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            cast((Class) entry.getKey(), entry.getValue());
        }
        super.putAll(linkedHashMap);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ClassToInstanceMap
    public <T extends B> T putInstance(Class<T> cls, T t) {
        return (T) cast(cls, put((Class<? extends Class<T>>) cls, (Class<T>) t));
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ClassToInstanceMap
    public <T extends B> T getInstance(Class<T> cls) {
        return (T) cast(cls, get(cls));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <B, T extends B> T cast(Class<T> cls, B b) {
        return (T) Primitives.wrap(cls).cast(b);
    }

    private Object writeReplace() {
        return new SerializedForm(delegate());
    }

    private static final class SerializedForm<B> implements Serializable {
        private static final long serialVersionUID = 0;
        private final Map<Class<? extends B>, B> backingMap;

        SerializedForm(Map<Class<? extends B>, B> map) {
            this.backingMap = map;
        }

        Object readResolve() {
            return MutableClassToInstanceMap.create(this.backingMap);
        }
    }
}
