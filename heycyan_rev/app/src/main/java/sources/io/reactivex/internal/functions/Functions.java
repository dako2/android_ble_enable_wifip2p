package io.reactivex.internal.functions;

import io.reactivex.Notification;
import io.reactivex.Scheduler;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function5;
import io.reactivex.functions.Function6;
import io.reactivex.functions.Function7;
import io.reactivex.functions.Function8;
import io.reactivex.functions.Function9;
import io.reactivex.functions.LongConsumer;
import io.reactivex.functions.Predicate;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Timed;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public final class Functions {
    static final Function<Object, Object> IDENTITY = new Function<Object, Object>() { // from class: io.reactivex.internal.functions.Functions.9
        @Override // io.reactivex.functions.Function
        public Object apply(Object obj) {
            return obj;
        }

        public String toString() {
            return "IdentityFunction";
        }
    };
    public static final Runnable EMPTY_RUNNABLE = new Runnable() { // from class: io.reactivex.internal.functions.Functions.10
        @Override // java.lang.Runnable
        public void run() {
        }

        public String toString() {
            return "EmptyRunnable";
        }
    };
    public static final Action EMPTY_ACTION = new Action() { // from class: io.reactivex.internal.functions.Functions.11
        @Override // io.reactivex.functions.Action
        public void run() {
        }

        public String toString() {
            return "EmptyAction";
        }
    };
    static final Consumer<Object> EMPTY_CONSUMER = new Consumer<Object>() { // from class: io.reactivex.internal.functions.Functions.12
        @Override // io.reactivex.functions.Consumer
        public void accept(Object obj) {
        }

        public String toString() {
            return "EmptyConsumer";
        }
    };
    public static final Consumer<Throwable> ERROR_CONSUMER = new Consumer<Throwable>() { // from class: io.reactivex.internal.functions.Functions.13
        @Override // io.reactivex.functions.Consumer
        public void accept(Throwable th) {
            RxJavaPlugins.onError(th);
        }
    };
    public static final LongConsumer EMPTY_LONG_CONSUMER = new LongConsumer() { // from class: io.reactivex.internal.functions.Functions.14
        @Override // io.reactivex.functions.LongConsumer
        public void accept(long j) {
        }
    };
    static final Predicate<Object> ALWAYS_TRUE = new Predicate<Object>() { // from class: io.reactivex.internal.functions.Functions.15
        @Override // io.reactivex.functions.Predicate
        public boolean test(Object obj) {
            return true;
        }
    };
    static final Predicate<Object> ALWAYS_FALSE = new Predicate<Object>() { // from class: io.reactivex.internal.functions.Functions.16
        @Override // io.reactivex.functions.Predicate
        public boolean test(Object obj) {
            return false;
        }
    };
    static final Callable<Object> NULL_SUPPLIER = new Callable<Object>() { // from class: io.reactivex.internal.functions.Functions.17
        @Override // java.util.concurrent.Callable
        public Object call() {
            return null;
        }
    };
    static final Comparator<Object> NATURAL_COMPARATOR = new Comparator<Object>() { // from class: io.reactivex.internal.functions.Functions.18
        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    };
    public static final Consumer<Subscription> REQUEST_MAX = new Consumer<Subscription>() { // from class: io.reactivex.internal.functions.Functions.19
        @Override // io.reactivex.functions.Consumer
        public void accept(Subscription subscription) throws Exception {
            subscription.request(Long.MAX_VALUE);
        }
    };

    private Functions() {
        throw new IllegalStateException("No instances!");
    }

    public static <T1, T2, R> Function<Object[], R> toFunction(final BiFunction<? super T1, ? super T2, ? extends R> biFunction) {
        ObjectHelper.requireNonNull(biFunction, "f is null");
        return new Function<Object[], R>() { // from class: io.reactivex.internal.functions.Functions.1
            @Override // io.reactivex.functions.Function
            public R apply(Object[] objArr) throws Exception {
                if (objArr.length != 2) {
                    throw new IllegalArgumentException("Array of size 2 expected but got " + objArr.length);
                }
                return (R) biFunction.apply(objArr[0], objArr[1]);
            }
        };
    }

    public static <T1, T2, T3, R> Function<Object[], R> toFunction(final Function3<T1, T2, T3, R> function3) {
        ObjectHelper.requireNonNull(function3, "f is null");
        return new Function<Object[], R>() { // from class: io.reactivex.internal.functions.Functions.2
            @Override // io.reactivex.functions.Function
            public R apply(Object[] objArr) throws Exception {
                if (objArr.length != 3) {
                    throw new IllegalArgumentException("Array of size 3 expected but got " + objArr.length);
                }
                return (R) function3.apply(objArr[0], objArr[1], objArr[2]);
            }
        };
    }

    public static <T1, T2, T3, T4, R> Function<Object[], R> toFunction(final Function4<T1, T2, T3, T4, R> function4) {
        ObjectHelper.requireNonNull(function4, "f is null");
        return new Function<Object[], R>() { // from class: io.reactivex.internal.functions.Functions.3
            @Override // io.reactivex.functions.Function
            public R apply(Object[] objArr) throws Exception {
                if (objArr.length != 4) {
                    throw new IllegalArgumentException("Array of size 4 expected but got " + objArr.length);
                }
                return (R) function4.apply(objArr[0], objArr[1], objArr[2], objArr[3]);
            }
        };
    }

    public static <T1, T2, T3, T4, T5, R> Function<Object[], R> toFunction(final Function5<T1, T2, T3, T4, T5, R> function5) {
        ObjectHelper.requireNonNull(function5, "f is null");
        return new Function<Object[], R>() { // from class: io.reactivex.internal.functions.Functions.4
            @Override // io.reactivex.functions.Function
            public R apply(Object[] objArr) throws Exception {
                if (objArr.length != 5) {
                    throw new IllegalArgumentException("Array of size 5 expected but got " + objArr.length);
                }
                return (R) function5.apply(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            }
        };
    }

    public static <T1, T2, T3, T4, T5, T6, R> Function<Object[], R> toFunction(final Function6<T1, T2, T3, T4, T5, T6, R> function6) {
        ObjectHelper.requireNonNull(function6, "f is null");
        return new Function<Object[], R>() { // from class: io.reactivex.internal.functions.Functions.5
            @Override // io.reactivex.functions.Function
            public R apply(Object[] objArr) throws Exception {
                if (objArr.length != 6) {
                    throw new IllegalArgumentException("Array of size 6 expected but got " + objArr.length);
                }
                return (R) function6.apply(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5]);
            }
        };
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> Function<Object[], R> toFunction(final Function7<T1, T2, T3, T4, T5, T6, T7, R> function7) {
        ObjectHelper.requireNonNull(function7, "f is null");
        return new Function<Object[], R>() { // from class: io.reactivex.internal.functions.Functions.6
            @Override // io.reactivex.functions.Function
            public R apply(Object[] objArr) throws Exception {
                if (objArr.length != 7) {
                    throw new IllegalArgumentException("Array of size 7 expected but got " + objArr.length);
                }
                return (R) function7.apply(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6]);
            }
        };
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Function<Object[], R> toFunction(final Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> function8) {
        ObjectHelper.requireNonNull(function8, "f is null");
        return new Function<Object[], R>() { // from class: io.reactivex.internal.functions.Functions.7
            @Override // io.reactivex.functions.Function
            public R apply(Object[] objArr) throws Exception {
                if (objArr.length != 8) {
                    throw new IllegalArgumentException("Array of size 8 expected but got " + objArr.length);
                }
                return (R) function8.apply(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7]);
            }
        };
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Function<Object[], R> toFunction(final Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> function9) {
        ObjectHelper.requireNonNull(function9, "f is null");
        return new Function<Object[], R>() { // from class: io.reactivex.internal.functions.Functions.8
            @Override // io.reactivex.functions.Function
            public R apply(Object[] objArr) throws Exception {
                if (objArr.length != 9) {
                    throw new IllegalArgumentException("Array of size 9 expected but got " + objArr.length);
                }
                return (R) function9.apply(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7], objArr[8]);
            }
        };
    }

    public static <T> Function<T, T> identity() {
        return (Function<T, T>) IDENTITY;
    }

    public static <T> Consumer<T> emptyConsumer() {
        return (Consumer<T>) EMPTY_CONSUMER;
    }

    public static <T> Predicate<T> alwaysTrue() {
        return (Predicate<T>) ALWAYS_TRUE;
    }

    public static <T> Predicate<T> alwaysFalse() {
        return (Predicate<T>) ALWAYS_FALSE;
    }

    public static <T> Callable<T> nullSupplier() {
        return (Callable<T>) NULL_SUPPLIER;
    }

    public static <T> Comparator<T> naturalOrder() {
        return (Comparator<T>) NATURAL_COMPARATOR;
    }

    static final class FutureAction implements Action {
        final Future<?> future;

        FutureAction(Future<?> future) {
            this.future = future;
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            this.future.get();
        }
    }

    public static Action futureAction(Future<?> future) {
        return new FutureAction(future);
    }

    static final class JustValue<T, U> implements Callable<U>, Function<T, U> {
        final U value;

        JustValue(U u) {
            this.value = u;
        }

        @Override // java.util.concurrent.Callable
        public U call() throws Exception {
            return this.value;
        }

        @Override // io.reactivex.functions.Function
        public U apply(T t) throws Exception {
            return this.value;
        }
    }

    public static <T> Callable<T> justCallable(T t) {
        return new JustValue(t);
    }

    public static <T, U> Function<T, U> justFunction(U u) {
        return new JustValue(u);
    }

    static final class CastToClass<T, U> implements Function<T, U> {
        final Class<U> clazz;

        CastToClass(Class<U> cls) {
            this.clazz = cls;
        }

        @Override // io.reactivex.functions.Function
        public U apply(T t) throws Exception {
            return this.clazz.cast(t);
        }
    }

    public static <T, U> Function<T, U> castFunction(Class<U> cls) {
        return new CastToClass(cls);
    }

    static final class ArrayListCapacityCallable<T> implements Callable<List<T>> {
        final int capacity;

        ArrayListCapacityCallable(int i) {
            this.capacity = i;
        }

        @Override // java.util.concurrent.Callable
        public List<T> call() throws Exception {
            return new ArrayList(this.capacity);
        }
    }

    public static <T> Callable<List<T>> createArrayList(int i) {
        return new ArrayListCapacityCallable(i);
    }

    static final class EqualsPredicate<T> implements Predicate<T> {
        final T value;

        EqualsPredicate(T t) {
            this.value = t;
        }

        @Override // io.reactivex.functions.Predicate
        public boolean test(T t) throws Exception {
            return ObjectHelper.equals(t, this.value);
        }
    }

    public static <T> Predicate<T> equalsWith(T t) {
        return new EqualsPredicate(t);
    }

    enum HashSetCallable implements Callable<Set<Object>> {
        INSTANCE;

        @Override // java.util.concurrent.Callable
        public Set<Object> call() throws Exception {
            return new HashSet();
        }
    }

    public static <T> Callable<Set<T>> createHashSet() {
        return HashSetCallable.INSTANCE;
    }

    static final class NotificationOnNext<T> implements Consumer<T> {
        final Consumer<? super Notification<T>> onNotification;

        NotificationOnNext(Consumer<? super Notification<T>> consumer) {
            this.onNotification = consumer;
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(T t) throws Exception {
            this.onNotification.accept(Notification.createOnNext(t));
        }
    }

    static final class NotificationOnError<T> implements Consumer<Throwable> {
        final Consumer<? super Notification<T>> onNotification;

        NotificationOnError(Consumer<? super Notification<T>> consumer) {
            this.onNotification = consumer;
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(Throwable th) throws Exception {
            this.onNotification.accept(Notification.createOnError(th));
        }
    }

    static final class NotificationOnComplete<T> implements Action {
        final Consumer<? super Notification<T>> onNotification;

        NotificationOnComplete(Consumer<? super Notification<T>> consumer) {
            this.onNotification = consumer;
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            this.onNotification.accept(Notification.createOnComplete());
        }
    }

    public static <T> Consumer<T> notificationOnNext(Consumer<? super Notification<T>> consumer) {
        return new NotificationOnNext(consumer);
    }

    public static <T> Consumer<Throwable> notificationOnError(Consumer<? super Notification<T>> consumer) {
        return new NotificationOnError(consumer);
    }

    public static <T> Action notificationOnComplete(Consumer<? super Notification<T>> consumer) {
        return new NotificationOnComplete(consumer);
    }

    static final class ActionConsumer<T> implements Consumer<T> {
        final Action action;

        ActionConsumer(Action action) {
            this.action = action;
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(T t) throws Exception {
            this.action.run();
        }
    }

    public static <T> Consumer<T> actionConsumer(Action action) {
        return new ActionConsumer(action);
    }

    static final class ClassFilter<T, U> implements Predicate<T> {
        final Class<U> clazz;

        ClassFilter(Class<U> cls) {
            this.clazz = cls;
        }

        @Override // io.reactivex.functions.Predicate
        public boolean test(T t) throws Exception {
            return this.clazz.isInstance(t);
        }
    }

    public static <T, U> Predicate<T> isInstanceOf(Class<U> cls) {
        return new ClassFilter(cls);
    }

    static final class BooleanSupplierPredicateReverse<T> implements Predicate<T> {
        final BooleanSupplier supplier;

        BooleanSupplierPredicateReverse(BooleanSupplier booleanSupplier) {
            this.supplier = booleanSupplier;
        }

        @Override // io.reactivex.functions.Predicate
        public boolean test(T t) throws Exception {
            return !this.supplier.getAsBoolean();
        }
    }

    public static <T> Predicate<T> predicateReverseFor(BooleanSupplier booleanSupplier) {
        return new BooleanSupplierPredicateReverse(booleanSupplier);
    }

    static final class TimestampFunction<T> implements Function<T, Timed<T>> {
        final Scheduler scheduler;
        final TimeUnit unit;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.functions.Function
        public /* bridge */ /* synthetic */ Object apply(Object obj) throws Exception {
            return apply((TimestampFunction<T>) obj);
        }

        TimestampFunction(TimeUnit timeUnit, Scheduler scheduler) {
            this.unit = timeUnit;
            this.scheduler = scheduler;
        }

        @Override // io.reactivex.functions.Function
        public Timed<T> apply(T t) throws Exception {
            return new Timed<>(t, this.scheduler.now(this.unit), this.unit);
        }
    }

    public static <T> Function<T, Timed<T>> timestampWith(TimeUnit timeUnit, Scheduler scheduler) {
        return new TimestampFunction(timeUnit, scheduler);
    }

    static final class ToMapKeySelector<K, T> implements BiConsumer<Map<K, T>, T> {
        private final Function<? super T, ? extends K> keySelector;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.functions.BiConsumer
        public /* bridge */ /* synthetic */ void accept(Object obj, Object obj2) throws Exception {
            accept((Map<K, Map<K, T>>) obj, (Map<K, T>) obj2);
        }

        ToMapKeySelector(Function<? super T, ? extends K> function) {
            this.keySelector = function;
        }

        public void accept(Map<K, T> map, T t) throws Exception {
            map.put(this.keySelector.apply(t), t);
        }
    }

    public static <T, K> BiConsumer<Map<K, T>, T> toMapKeySelector(Function<? super T, ? extends K> function) {
        return new ToMapKeySelector(function);
    }

    static final class ToMapKeyValueSelector<K, V, T> implements BiConsumer<Map<K, V>, T> {
        private final Function<? super T, ? extends K> keySelector;
        private final Function<? super T, ? extends V> valueSelector;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.functions.BiConsumer
        public /* bridge */ /* synthetic */ void accept(Object obj, Object obj2) throws Exception {
            accept((Map) obj, (Map<K, V>) obj2);
        }

        ToMapKeyValueSelector(Function<? super T, ? extends V> function, Function<? super T, ? extends K> function2) {
            this.valueSelector = function;
            this.keySelector = function2;
        }

        public void accept(Map<K, V> map, T t) throws Exception {
            map.put(this.keySelector.apply(t), this.valueSelector.apply(t));
        }
    }

    public static <T, K, V> BiConsumer<Map<K, V>, T> toMapKeyValueSelector(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2) {
        return new ToMapKeyValueSelector(function2, function);
    }

    static final class ToMultimapKeyValueSelector<K, V, T> implements BiConsumer<Map<K, Collection<V>>, T> {
        private final Function<? super K, ? extends Collection<? super V>> collectionFactory;
        private final Function<? super T, ? extends K> keySelector;
        private final Function<? super T, ? extends V> valueSelector;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.functions.BiConsumer
        public /* bridge */ /* synthetic */ void accept(Object obj, Object obj2) throws Exception {
            accept((Map) obj, (Map<K, Collection<V>>) obj2);
        }

        ToMultimapKeyValueSelector(Function<? super K, ? extends Collection<? super V>> function, Function<? super T, ? extends V> function2, Function<? super T, ? extends K> function3) {
            this.collectionFactory = function;
            this.valueSelector = function2;
            this.keySelector = function3;
        }

        public void accept(Map<K, Collection<V>> map, T t) throws Exception {
            K kApply = this.keySelector.apply(t);
            Collection<? super V> collectionApply = (Collection) map.get(kApply);
            if (collectionApply == null) {
                collectionApply = this.collectionFactory.apply(kApply);
                map.put(kApply, collectionApply);
            }
            collectionApply.add(this.valueSelector.apply(t));
        }
    }

    public static <T, K, V> BiConsumer<Map<K, Collection<V>>, T> toMultimapKeyValueSelector(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2, Function<? super K, ? extends Collection<? super V>> function3) {
        return new ToMultimapKeyValueSelector(function3, function2, function);
    }

    enum NaturalComparator implements Comparator<Object> {
        INSTANCE;

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    public static <T> Comparator<T> naturalComparator() {
        return NaturalComparator.INSTANCE;
    }

    static final class ListSorter<T> implements Function<List<T>, List<T>> {
        private final Comparator<? super T> comparator;

        ListSorter(Comparator<? super T> comparator) {
            this.comparator = comparator;
        }

        @Override // io.reactivex.functions.Function
        public List<T> apply(List<T> list) {
            Collections.sort(list, this.comparator);
            return list;
        }
    }

    public static <T> Function<List<T>, List<T>> listSorter(Comparator<? super T> comparator) {
        return new ListSorter(comparator);
    }
}
