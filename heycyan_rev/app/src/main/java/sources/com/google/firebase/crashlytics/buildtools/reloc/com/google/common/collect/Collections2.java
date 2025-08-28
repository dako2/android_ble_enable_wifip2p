package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Preconditions;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Predicate;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Predicates;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Collections2;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.math.IntMath;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.FileUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public final class Collections2 {
    private Collections2() {
    }

    public static <E> Collection<E> filter(Collection<E> collection, Predicate<? super E> predicate) {
        if (collection instanceof FilteredCollection) {
            return ((FilteredCollection) collection).createCombined(predicate);
        }
        return new FilteredCollection((Collection) Preconditions.checkNotNull(collection), (Predicate) Preconditions.checkNotNull(predicate));
    }

    static boolean safeContains(Collection<?> collection, @NullableDecl Object obj) {
        Preconditions.checkNotNull(collection);
        try {
            return collection.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    static boolean safeRemove(Collection<?> collection, @NullableDecl Object obj) {
        Preconditions.checkNotNull(collection);
        try {
            return collection.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    static class FilteredCollection<E> extends AbstractCollection<E> {
        final Predicate<? super E> predicate;
        final Collection<E> unfiltered;

        FilteredCollection(Collection<E> collection, Predicate<? super E> predicate) {
            this.unfiltered = collection;
            this.predicate = predicate;
        }

        FilteredCollection<E> createCombined(Predicate<? super E> predicate) {
            return new FilteredCollection<>(this.unfiltered, Predicates.and(this.predicate, predicate));
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(E e) {
            Preconditions.checkArgument(this.predicate.apply(e));
            return this.unfiltered.add(e);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            Iterator<? extends E> it = collection.iterator();
            while (it.hasNext()) {
                Preconditions.checkArgument(this.predicate.apply(it.next()));
            }
            return this.unfiltered.addAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            Iterables.removeIf(this.unfiltered, this.predicate);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(@NullableDecl Object obj) {
            if (Collections2.safeContains(this.unfiltered, obj)) {
                return this.predicate.apply(obj);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            return Collections2.containsAllImpl(this, collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return !Iterables.any(this.unfiltered, this.predicate);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            return Iterators.filter(this.unfiltered.iterator(), this.predicate);
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Spliterator<E> spliterator() {
            return CollectSpliterators.filter(this.unfiltered.spliterator(), this.predicate);
        }

        @Override // java.lang.Iterable
        public void forEach(final Consumer<? super E> consumer) {
            Preconditions.checkNotNull(consumer);
            this.unfiltered.forEach(new Consumer() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Collections2$FilteredCollection$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.m324xdd68b97c(consumer, obj);
                }
            });
        }

        /* renamed from: lambda$forEach$0$com-google-firebase-crashlytics-buildtools-reloc-com-google-common-collect-Collections2$FilteredCollection */
        /* synthetic */ void m324xdd68b97c(Consumer consumer, Object obj) {
            if (this.predicate.test(obj)) {
                consumer.accept(obj);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            return contains(obj) && this.unfiltered.remove(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(final Collection<?> collection) {
            collection.getClass();
            return removeIf(new java.util.function.Predicate() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Collections2$FilteredCollection$$ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return collection.contains(obj);
                }
            });
        }

        static /* synthetic */ boolean lambda$retainAll$1(Collection collection, Object obj) {
            return !collection.contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(final Collection<?> collection) {
            return removeIf(new java.util.function.Predicate() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Collections2$FilteredCollection$$ExternalSyntheticLambda2
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Collections2.FilteredCollection.lambda$retainAll$1(collection, obj);
                }
            });
        }

        @Override // java.util.Collection
        public boolean removeIf(final java.util.function.Predicate<? super E> predicate) {
            Preconditions.checkNotNull(predicate);
            return this.unfiltered.removeIf(new java.util.function.Predicate() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Collections2$FilteredCollection$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.f$0.m325x4e192169(predicate, obj);
                }
            });
        }

        /* renamed from: lambda$removeIf$2$com-google-firebase-crashlytics-buildtools-reloc-com-google-common-collect-Collections2$FilteredCollection */
        /* synthetic */ boolean m325x4e192169(java.util.function.Predicate predicate, Object obj) {
            return this.predicate.apply(obj) && predicate.test(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            Iterator<E> it = this.unfiltered.iterator();
            int i = 0;
            while (it.hasNext()) {
                if (this.predicate.apply(it.next())) {
                    i++;
                }
            }
            return i;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return Lists.newArrayList(iterator()).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) Lists.newArrayList(iterator()).toArray(tArr);
        }
    }

    public static <F, T> Collection<T> transform(Collection<F> collection, Function<? super F, T> function) {
        return new TransformedCollection(collection, function);
    }

    static class TransformedCollection<F, T> extends AbstractCollection<T> {
        final Collection<F> fromCollection;
        final Function<? super F, ? extends T> function;

        TransformedCollection(Collection<F> collection, Function<? super F, ? extends T> function) {
            this.fromCollection = (Collection) Preconditions.checkNotNull(collection);
            this.function = (Function) Preconditions.checkNotNull(function);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.fromCollection.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.fromCollection.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.transform(this.fromCollection.iterator(), this.function);
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Spliterator<T> spliterator() {
            return CollectSpliterators.map(this.fromCollection.spliterator(), this.function);
        }

        @Override // java.lang.Iterable
        public void forEach(final Consumer<? super T> consumer) {
            Preconditions.checkNotNull(consumer);
            this.fromCollection.forEach(new Consumer() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Collections2$TransformedCollection$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.m326x84f33342(consumer, obj);
                }
            });
        }

        /* renamed from: lambda$forEach$0$com-google-firebase-crashlytics-buildtools-reloc-com-google-common-collect-Collections2$TransformedCollection */
        /* synthetic */ void m326x84f33342(Consumer consumer, Object obj) {
            consumer.accept(this.function.apply(obj));
        }

        @Override // java.util.Collection
        public boolean removeIf(final java.util.function.Predicate<? super T> predicate) {
            Preconditions.checkNotNull(predicate);
            return this.fromCollection.removeIf(new java.util.function.Predicate() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Collections2$TransformedCollection$$ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.f$0.m327x769989f4(predicate, obj);
                }
            });
        }

        /* renamed from: lambda$removeIf$1$com-google-firebase-crashlytics-buildtools-reloc-com-google-common-collect-Collections2$TransformedCollection */
        /* synthetic */ boolean m327x769989f4(java.util.function.Predicate predicate, Object obj) {
            return predicate.test(this.function.apply(obj));
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.fromCollection.size();
        }
    }

    static boolean containsAllImpl(Collection<?> collection, Collection<?> collection2) {
        Iterator<?> it = collection2.iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    static String toStringImpl(Collection<?> collection) {
        StringBuilder sbAppend = newStringBuilderForCollection(collection.size()).append('[');
        boolean z = true;
        for (Object obj : collection) {
            if (!z) {
                sbAppend.append(", ");
            }
            if (obj == collection) {
                sbAppend.append("(this Collection)");
            } else {
                sbAppend.append(obj);
            }
            z = false;
        }
        return sbAppend.append(']').toString();
    }

    static StringBuilder newStringBuilderForCollection(int i) {
        CollectPreconditions.checkNonnegative(i, "size");
        return new StringBuilder((int) Math.min(i * 8, FileUtils.ONE_GB));
    }

    static <T> Collection<T> cast(Iterable<T> iterable) {
        return (Collection) iterable;
    }

    public static <E extends Comparable<? super E>> Collection<List<E>> orderedPermutations(Iterable<E> iterable) {
        return orderedPermutations(iterable, Ordering.natural());
    }

    public static <E> Collection<List<E>> orderedPermutations(Iterable<E> iterable, Comparator<? super E> comparator) {
        return new OrderedPermutationCollection(iterable, comparator);
    }

    private static final class OrderedPermutationCollection<E> extends AbstractCollection<List<E>> {
        final Comparator<? super E> comparator;
        final ImmutableList<E> inputList;
        final int size;

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return false;
        }

        OrderedPermutationCollection(Iterable<E> iterable, Comparator<? super E> comparator) {
            ImmutableList<E> immutableListSortedCopyOf = ImmutableList.sortedCopyOf(comparator, iterable);
            this.inputList = immutableListSortedCopyOf;
            this.comparator = comparator;
            this.size = calculateSize(immutableListSortedCopyOf, comparator);
        }

        private static <E> int calculateSize(List<E> list, Comparator<? super E> comparator) {
            int i = 1;
            int iSaturatedMultiply = 1;
            int i2 = 1;
            while (i < list.size()) {
                if (comparator.compare(list.get(i - 1), list.get(i)) < 0) {
                    iSaturatedMultiply = IntMath.saturatedMultiply(iSaturatedMultiply, IntMath.binomial(i, i2));
                    if (iSaturatedMultiply == Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                    i2 = 0;
                }
                i++;
                i2++;
            }
            return IntMath.saturatedMultiply(iSaturatedMultiply, IntMath.binomial(i, i2));
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<List<E>> iterator() {
            return new OrderedPermutationIterator(this.inputList, this.comparator);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(@NullableDecl Object obj) {
            if (!(obj instanceof List)) {
                return false;
            }
            return Collections2.isPermutation(this.inputList, (List) obj);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return "orderedPermutationCollection(" + this.inputList + ")";
        }
    }

    private static final class OrderedPermutationIterator<E> extends AbstractIterator<List<E>> {
        final Comparator<? super E> comparator;

        @NullableDecl
        List<E> nextPermutation;

        OrderedPermutationIterator(List<E> list, Comparator<? super E> comparator) {
            this.nextPermutation = Lists.newArrayList(list);
            this.comparator = comparator;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractIterator
        public List<E> computeNext() {
            List<E> list = this.nextPermutation;
            if (list == null) {
                return endOfData();
            }
            ImmutableList immutableListCopyOf = ImmutableList.copyOf((Collection) list);
            calculateNextPermutation();
            return immutableListCopyOf;
        }

        void calculateNextPermutation() {
            int iFindNextJ = findNextJ();
            if (iFindNextJ == -1) {
                this.nextPermutation = null;
                return;
            }
            Collections.swap(this.nextPermutation, iFindNextJ, findNextL(iFindNextJ));
            Collections.reverse(this.nextPermutation.subList(iFindNextJ + 1, this.nextPermutation.size()));
        }

        int findNextJ() {
            for (int size = this.nextPermutation.size() - 2; size >= 0; size--) {
                if (this.comparator.compare(this.nextPermutation.get(size), this.nextPermutation.get(size + 1)) < 0) {
                    return size;
                }
            }
            return -1;
        }

        int findNextL(int i) {
            E e = this.nextPermutation.get(i);
            for (int size = this.nextPermutation.size() - 1; size > i; size--) {
                if (this.comparator.compare(e, this.nextPermutation.get(size)) < 0) {
                    return size;
                }
            }
            throw new AssertionError("this statement should be unreachable");
        }
    }

    public static <E> Collection<List<E>> permutations(Collection<E> collection) {
        return new PermutationCollection(ImmutableList.copyOf((Collection) collection));
    }

    private static final class PermutationCollection<E> extends AbstractCollection<List<E>> {
        final ImmutableList<E> inputList;

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return false;
        }

        PermutationCollection(ImmutableList<E> immutableList) {
            this.inputList = immutableList;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return IntMath.factorial(this.inputList.size());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<List<E>> iterator() {
            return new PermutationIterator(this.inputList);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(@NullableDecl Object obj) {
            if (!(obj instanceof List)) {
                return false;
            }
            return Collections2.isPermutation(this.inputList, (List) obj);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return "permutations(" + this.inputList + ")";
        }
    }

    private static class PermutationIterator<E> extends AbstractIterator<List<E>> {

        /* renamed from: c */
        final int[] f299c;

        /* renamed from: j */
        int f300j;
        final List<E> list;

        /* renamed from: o */
        final int[] f301o;

        PermutationIterator(List<E> list) {
            this.list = new ArrayList(list);
            int size = list.size();
            int[] iArr = new int[size];
            this.f299c = iArr;
            int[] iArr2 = new int[size];
            this.f301o = iArr2;
            Arrays.fill(iArr, 0);
            Arrays.fill(iArr2, 1);
            this.f300j = Integer.MAX_VALUE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.AbstractIterator
        public List<E> computeNext() {
            if (this.f300j <= 0) {
                return endOfData();
            }
            ImmutableList immutableListCopyOf = ImmutableList.copyOf((Collection) this.list);
            calculateNextPermutation();
            return immutableListCopyOf;
        }

        void calculateNextPermutation() {
            int size = this.list.size() - 1;
            this.f300j = size;
            if (size == -1) {
                return;
            }
            int i = 0;
            while (true) {
                int[] iArr = this.f299c;
                int i2 = this.f300j;
                int i3 = iArr[i2];
                int i4 = this.f301o[i2] + i3;
                if (i4 < 0) {
                    switchDirection();
                } else if (i4 != i2 + 1) {
                    Collections.swap(this.list, (i2 - i3) + i, (i2 - i4) + i);
                    this.f299c[this.f300j] = i4;
                    return;
                } else {
                    if (i2 == 0) {
                        return;
                    }
                    i++;
                    switchDirection();
                }
            }
        }

        void switchDirection() {
            int[] iArr = this.f301o;
            int i = this.f300j;
            iArr[i] = -iArr[i];
            this.f300j = i - 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isPermutation(List<?> list, List<?> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        return HashMultiset.create(list).equals(HashMultiset.create(list2));
    }
}
