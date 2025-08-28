package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect;

import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.function.ObjIntConsumer;

/* loaded from: classes2.dex */
public final class LinkedHashMultiset<E> extends AbstractMapBasedMultiset<E> {
    private static final long serialVersionUID = 0;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMapBasedMultiset, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultiset, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ int add(@NullableDecl Object obj, int i) {
        return super.add(obj, i);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMapBasedMultiset, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ boolean contains(@NullableDecl Object obj) {
        return super.contains(obj);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMapBasedMultiset, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ int count(@NullableDecl Object obj) {
        return super.count(obj);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultiset, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Set elementSet() {
        return super.elementSet();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMapBasedMultiset, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultiset, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMapBasedMultiset, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ void forEachEntry(ObjIntConsumer objIntConsumer) {
        super.forEachEntry(objIntConsumer);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMapBasedMultiset, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMapBasedMultiset, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultiset, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ int remove(@NullableDecl Object obj, int i) {
        return super.remove(obj, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMapBasedMultiset, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultiset, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ int setCount(@NullableDecl Object obj, int i) {
        return super.setCount(obj, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMultiset, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ boolean setCount(@NullableDecl Object obj, int i, int i2) {
        return super.setCount(obj, i, i2);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractMapBasedMultiset, java.util.AbstractCollection, java.util.Collection, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    public static <E> LinkedHashMultiset<E> create() {
        return new LinkedHashMultiset<>();
    }

    public static <E> LinkedHashMultiset<E> create(int i) {
        return new LinkedHashMultiset<>(i);
    }

    public static <E> LinkedHashMultiset<E> create(Iterable<? extends E> iterable) {
        LinkedHashMultiset<E> linkedHashMultisetCreate = create(Multisets.inferDistinctElements(iterable));
        Iterables.addAll(linkedHashMultisetCreate, iterable);
        return linkedHashMultisetCreate;
    }

    private LinkedHashMultiset() {
        super(new LinkedHashMap());
    }

    private LinkedHashMultiset(int i) {
        super(Maps.newLinkedHashMapWithExpectedSize(i));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Serialization.writeMultiset(this, objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        int count = Serialization.readCount(objectInputStream);
        setBackingMap(new LinkedHashMap());
        Serialization.populateMultiset(this, objectInputStream, count);
    }
}
