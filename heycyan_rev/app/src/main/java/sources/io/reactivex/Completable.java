package io.reactivex;

import com.androidnetworking.common.ANConstants;
import io.reactivex.annotations.BackpressureKind;
import io.reactivex.annotations.BackpressureSupport;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.FuseToFlowable;
import io.reactivex.internal.fuseable.FuseToMaybe;
import io.reactivex.internal.fuseable.FuseToObservable;
import io.reactivex.internal.observers.BlockingMultiObserver;
import io.reactivex.internal.observers.CallbackCompletableObserver;
import io.reactivex.internal.observers.EmptyCompletableObserver;
import io.reactivex.internal.operators.completable.CompletableAmb;
import io.reactivex.internal.operators.completable.CompletableConcat;
import io.reactivex.internal.operators.completable.CompletableConcatArray;
import io.reactivex.internal.operators.completable.CompletableConcatIterable;
import io.reactivex.internal.operators.completable.CompletableCreate;
import io.reactivex.internal.operators.completable.CompletableDefer;
import io.reactivex.internal.operators.completable.CompletableDelay;
import io.reactivex.internal.operators.completable.CompletableDisposeOn;
import io.reactivex.internal.operators.completable.CompletableDoOnEvent;
import io.reactivex.internal.operators.completable.CompletableEmpty;
import io.reactivex.internal.operators.completable.CompletableError;
import io.reactivex.internal.operators.completable.CompletableErrorSupplier;
import io.reactivex.internal.operators.completable.CompletableFromAction;
import io.reactivex.internal.operators.completable.CompletableFromCallable;
import io.reactivex.internal.operators.completable.CompletableFromObservable;
import io.reactivex.internal.operators.completable.CompletableFromPublisher;
import io.reactivex.internal.operators.completable.CompletableFromRunnable;
import io.reactivex.internal.operators.completable.CompletableFromSingle;
import io.reactivex.internal.operators.completable.CompletableFromUnsafeSource;
import io.reactivex.internal.operators.completable.CompletableLift;
import io.reactivex.internal.operators.completable.CompletableMerge;
import io.reactivex.internal.operators.completable.CompletableMergeArray;
import io.reactivex.internal.operators.completable.CompletableMergeDelayErrorArray;
import io.reactivex.internal.operators.completable.CompletableMergeDelayErrorIterable;
import io.reactivex.internal.operators.completable.CompletableMergeIterable;
import io.reactivex.internal.operators.completable.CompletableNever;
import io.reactivex.internal.operators.completable.CompletableObserveOn;
import io.reactivex.internal.operators.completable.CompletableOnErrorComplete;
import io.reactivex.internal.operators.completable.CompletablePeek;
import io.reactivex.internal.operators.completable.CompletableResumeNext;
import io.reactivex.internal.operators.completable.CompletableSubscribeOn;
import io.reactivex.internal.operators.completable.CompletableTimeout;
import io.reactivex.internal.operators.completable.CompletableTimer;
import io.reactivex.internal.operators.completable.CompletableToFlowable;
import io.reactivex.internal.operators.completable.CompletableToObservable;
import io.reactivex.internal.operators.completable.CompletableToSingle;
import io.reactivex.internal.operators.completable.CompletableUsing;
import io.reactivex.internal.operators.flowable.FlowableDelaySubscriptionOther;
import io.reactivex.internal.operators.maybe.MaybeDelayWithCompletable;
import io.reactivex.internal.operators.maybe.MaybeFromCompletable;
import io.reactivex.internal.operators.observable.ObservableDelaySubscriptionOther;
import io.reactivex.internal.operators.single.SingleDelayWithCompletable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;

/* loaded from: classes2.dex */
public abstract class Completable implements CompletableSource {
    protected abstract void subscribeActual(CompletableObserver completableObserver);

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable ambArray(CompletableSource... completableSourceArr) {
        ObjectHelper.requireNonNull(completableSourceArr, "sources is null");
        if (completableSourceArr.length == 0) {
            return complete();
        }
        if (completableSourceArr.length == 1) {
            return wrap(completableSourceArr[0]);
        }
        return RxJavaPlugins.onAssembly(new CompletableAmb(completableSourceArr, null));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable amb(Iterable<? extends CompletableSource> iterable) {
        ObjectHelper.requireNonNull(iterable, "sources is null");
        return RxJavaPlugins.onAssembly(new CompletableAmb(null, iterable));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable complete() {
        return RxJavaPlugins.onAssembly(CompletableEmpty.INSTANCE);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable concatArray(CompletableSource... completableSourceArr) {
        ObjectHelper.requireNonNull(completableSourceArr, "sources is null");
        if (completableSourceArr.length == 0) {
            return complete();
        }
        if (completableSourceArr.length == 1) {
            return wrap(completableSourceArr[0]);
        }
        return RxJavaPlugins.onAssembly(new CompletableConcatArray(completableSourceArr));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable concat(Iterable<? extends CompletableSource> iterable) {
        ObjectHelper.requireNonNull(iterable, "sources is null");
        return RxJavaPlugins.onAssembly(new CompletableConcatIterable(iterable));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    public static Completable concat(Publisher<? extends CompletableSource> publisher) {
        return concat(publisher, 2);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    public static Completable concat(Publisher<? extends CompletableSource> publisher, int i) {
        ObjectHelper.requireNonNull(publisher, "sources is null");
        ObjectHelper.verifyPositive(i, ANConstants.PREFETCH);
        return RxJavaPlugins.onAssembly(new CompletableConcat(publisher, i));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable create(CompletableOnSubscribe completableOnSubscribe) {
        ObjectHelper.requireNonNull(completableOnSubscribe, "source is null");
        return RxJavaPlugins.onAssembly(new CompletableCreate(completableOnSubscribe));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable unsafeCreate(CompletableSource completableSource) {
        ObjectHelper.requireNonNull(completableSource, "source is null");
        if (completableSource instanceof Completable) {
            throw new IllegalArgumentException("Use of unsafeCreate(Completable)!");
        }
        return RxJavaPlugins.onAssembly(new CompletableFromUnsafeSource(completableSource));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable defer(Callable<? extends CompletableSource> callable) {
        ObjectHelper.requireNonNull(callable, "completableSupplier");
        return RxJavaPlugins.onAssembly(new CompletableDefer(callable));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable error(Callable<? extends Throwable> callable) {
        ObjectHelper.requireNonNull(callable, "errorSupplier is null");
        return RxJavaPlugins.onAssembly(new CompletableErrorSupplier(callable));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable error(Throwable th) {
        ObjectHelper.requireNonNull(th, "error is null");
        return RxJavaPlugins.onAssembly(new CompletableError(th));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable fromAction(Action action) {
        ObjectHelper.requireNonNull(action, "run is null");
        return RxJavaPlugins.onAssembly(new CompletableFromAction(action));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable fromCallable(Callable<?> callable) {
        ObjectHelper.requireNonNull(callable, "callable is null");
        return RxJavaPlugins.onAssembly(new CompletableFromCallable(callable));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable fromFuture(Future<?> future) {
        ObjectHelper.requireNonNull(future, "future is null");
        return fromAction(Functions.futureAction(future));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable fromRunnable(Runnable runnable) {
        ObjectHelper.requireNonNull(runnable, "run is null");
        return RxJavaPlugins.onAssembly(new CompletableFromRunnable(runnable));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static <T> Completable fromObservable(ObservableSource<T> observableSource) {
        ObjectHelper.requireNonNull(observableSource, "observable is null");
        return RxJavaPlugins.onAssembly(new CompletableFromObservable(observableSource));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    public static <T> Completable fromPublisher(Publisher<T> publisher) {
        ObjectHelper.requireNonNull(publisher, "publisher is null");
        return RxJavaPlugins.onAssembly(new CompletableFromPublisher(publisher));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static <T> Completable fromSingle(SingleSource<T> singleSource) {
        ObjectHelper.requireNonNull(singleSource, "single is null");
        return RxJavaPlugins.onAssembly(new CompletableFromSingle(singleSource));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable mergeArray(CompletableSource... completableSourceArr) {
        ObjectHelper.requireNonNull(completableSourceArr, "sources is null");
        if (completableSourceArr.length == 0) {
            return complete();
        }
        if (completableSourceArr.length == 1) {
            return wrap(completableSourceArr[0]);
        }
        return RxJavaPlugins.onAssembly(new CompletableMergeArray(completableSourceArr));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable merge(Iterable<? extends CompletableSource> iterable) {
        ObjectHelper.requireNonNull(iterable, "sources is null");
        return RxJavaPlugins.onAssembly(new CompletableMergeIterable(iterable));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    public static Completable merge(Publisher<? extends CompletableSource> publisher) {
        return merge0(publisher, Integer.MAX_VALUE, false);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    public static Completable merge(Publisher<? extends CompletableSource> publisher, int i) {
        return merge0(publisher, i, false);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    private static Completable merge0(Publisher<? extends CompletableSource> publisher, int i, boolean z) {
        ObjectHelper.requireNonNull(publisher, "sources is null");
        ObjectHelper.verifyPositive(i, "maxConcurrency");
        return RxJavaPlugins.onAssembly(new CompletableMerge(publisher, i, z));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable mergeArrayDelayError(CompletableSource... completableSourceArr) {
        ObjectHelper.requireNonNull(completableSourceArr, "sources is null");
        return RxJavaPlugins.onAssembly(new CompletableMergeDelayErrorArray(completableSourceArr));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable mergeDelayError(Iterable<? extends CompletableSource> iterable) {
        ObjectHelper.requireNonNull(iterable, "sources is null");
        return RxJavaPlugins.onAssembly(new CompletableMergeDelayErrorIterable(iterable));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    public static Completable mergeDelayError(Publisher<? extends CompletableSource> publisher) {
        return merge0(publisher, Integer.MAX_VALUE, true);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    public static Completable mergeDelayError(Publisher<? extends CompletableSource> publisher, int i) {
        return merge0(publisher, i, true);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable never() {
        return RxJavaPlugins.onAssembly(CompletableNever.INSTANCE);
    }

    @SchedulerSupport(SchedulerSupport.COMPUTATION)
    public static Completable timer(long j, TimeUnit timeUnit) {
        return timer(j, timeUnit, Schedulers.computation());
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    public static Completable timer(long j, TimeUnit timeUnit, Scheduler scheduler) {
        ObjectHelper.requireNonNull(timeUnit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new CompletableTimer(j, timeUnit, scheduler));
    }

    private static NullPointerException toNpe(Throwable th) {
        NullPointerException nullPointerException = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
        nullPointerException.initCause(th);
        return nullPointerException;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static <R> Completable using(Callable<R> callable, Function<? super R, ? extends CompletableSource> function, Consumer<? super R> consumer) {
        return using(callable, function, consumer, true);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static <R> Completable using(Callable<R> callable, Function<? super R, ? extends CompletableSource> function, Consumer<? super R> consumer, boolean z) {
        ObjectHelper.requireNonNull(callable, "resourceSupplier is null");
        ObjectHelper.requireNonNull(function, "completableFunction is null");
        ObjectHelper.requireNonNull(consumer, "disposer is null");
        return RxJavaPlugins.onAssembly(new CompletableUsing(callable, function, consumer, z));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public static Completable wrap(CompletableSource completableSource) {
        ObjectHelper.requireNonNull(completableSource, "source is null");
        if (completableSource instanceof Completable) {
            return RxJavaPlugins.onAssembly((Completable) completableSource);
        }
        return RxJavaPlugins.onAssembly(new CompletableFromUnsafeSource(completableSource));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable ambWith(CompletableSource completableSource) {
        ObjectHelper.requireNonNull(completableSource, "other is null");
        return ambArray(this, completableSource);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final <T> Observable<T> andThen(ObservableSource<T> observableSource) {
        ObjectHelper.requireNonNull(observableSource, "next is null");
        return RxJavaPlugins.onAssembly(new ObservableDelaySubscriptionOther(observableSource, toObservable()));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    public final <T> Flowable<T> andThen(Publisher<T> publisher) {
        ObjectHelper.requireNonNull(publisher, "next is null");
        return RxJavaPlugins.onAssembly(new FlowableDelaySubscriptionOther(publisher, toFlowable()));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final <T> Single<T> andThen(SingleSource<T> singleSource) {
        ObjectHelper.requireNonNull(singleSource, "next is null");
        return RxJavaPlugins.onAssembly(new SingleDelayWithCompletable(singleSource, this));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final <T> Maybe<T> andThen(MaybeSource<T> maybeSource) {
        ObjectHelper.requireNonNull(maybeSource, "next is null");
        return RxJavaPlugins.onAssembly(new MaybeDelayWithCompletable(maybeSource, this));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable andThen(CompletableSource completableSource) {
        return concatWith(completableSource);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final void blockingAwait() {
        BlockingMultiObserver blockingMultiObserver = new BlockingMultiObserver();
        subscribe(blockingMultiObserver);
        blockingMultiObserver.blockingGet();
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final boolean blockingAwait(long j, TimeUnit timeUnit) {
        BlockingMultiObserver blockingMultiObserver = new BlockingMultiObserver();
        subscribe(blockingMultiObserver);
        return blockingMultiObserver.blockingAwait(j, timeUnit);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Throwable blockingGet() {
        BlockingMultiObserver blockingMultiObserver = new BlockingMultiObserver();
        subscribe(blockingMultiObserver);
        return blockingMultiObserver.blockingGetError();
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Throwable blockingGet(long j, TimeUnit timeUnit) {
        ObjectHelper.requireNonNull(timeUnit, "unit is null");
        BlockingMultiObserver blockingMultiObserver = new BlockingMultiObserver();
        subscribe(blockingMultiObserver);
        return blockingMultiObserver.blockingGetError(j, timeUnit);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable compose(CompletableTransformer completableTransformer) {
        return wrap(completableTransformer.apply(this));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable concatWith(CompletableSource completableSource) {
        ObjectHelper.requireNonNull(completableSource, "other is null");
        return concatArray(this, completableSource);
    }

    @SchedulerSupport(SchedulerSupport.COMPUTATION)
    public final Completable delay(long j, TimeUnit timeUnit) {
        return delay(j, timeUnit, Schedulers.computation(), false);
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    public final Completable delay(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return delay(j, timeUnit, scheduler, false);
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    public final Completable delay(long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        ObjectHelper.requireNonNull(timeUnit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new CompletableDelay(this, j, timeUnit, scheduler, z));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable doOnComplete(Action action) {
        return doOnLifecycle(Functions.emptyConsumer(), Functions.emptyConsumer(), action, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable doOnDispose(Action action) {
        return doOnLifecycle(Functions.emptyConsumer(), Functions.emptyConsumer(), Functions.EMPTY_ACTION, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION, action);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable doOnError(Consumer<? super Throwable> consumer) {
        return doOnLifecycle(Functions.emptyConsumer(), consumer, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable doOnEvent(Consumer<? super Throwable> consumer) {
        ObjectHelper.requireNonNull(consumer, "onEvent is null");
        return RxJavaPlugins.onAssembly(new CompletableDoOnEvent(this, consumer));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    private Completable doOnLifecycle(Consumer<? super Disposable> consumer, Consumer<? super Throwable> consumer2, Action action, Action action2, Action action3, Action action4) {
        ObjectHelper.requireNonNull(consumer, "onSubscribe is null");
        ObjectHelper.requireNonNull(consumer2, "onError is null");
        ObjectHelper.requireNonNull(action, "onComplete is null");
        ObjectHelper.requireNonNull(action2, "onTerminate is null");
        ObjectHelper.requireNonNull(action3, "onAfterTerminate is null");
        ObjectHelper.requireNonNull(action4, "onDispose is null");
        return RxJavaPlugins.onAssembly(new CompletablePeek(this, consumer, consumer2, action, action2, action3, action4));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable doOnSubscribe(Consumer<? super Disposable> consumer) {
        return doOnLifecycle(consumer, Functions.emptyConsumer(), Functions.EMPTY_ACTION, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable doOnTerminate(Action action) {
        return doOnLifecycle(Functions.emptyConsumer(), Functions.emptyConsumer(), Functions.EMPTY_ACTION, action, Functions.EMPTY_ACTION, Functions.EMPTY_ACTION);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable doAfterTerminate(Action action) {
        return doOnLifecycle(Functions.emptyConsumer(), Functions.emptyConsumer(), Functions.EMPTY_ACTION, Functions.EMPTY_ACTION, action, Functions.EMPTY_ACTION);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable lift(CompletableOperator completableOperator) {
        ObjectHelper.requireNonNull(completableOperator, "onLift is null");
        return RxJavaPlugins.onAssembly(new CompletableLift(this, completableOperator));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable mergeWith(CompletableSource completableSource) {
        ObjectHelper.requireNonNull(completableSource, "other is null");
        return mergeArray(this, completableSource);
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    public final Completable observeOn(Scheduler scheduler) {
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new CompletableObserveOn(this, scheduler));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable onErrorComplete() {
        return onErrorComplete(Functions.alwaysTrue());
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable onErrorComplete(Predicate<? super Throwable> predicate) {
        ObjectHelper.requireNonNull(predicate, "predicate is null");
        return RxJavaPlugins.onAssembly(new CompletableOnErrorComplete(this, predicate));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable onErrorResumeNext(Function<? super Throwable, ? extends CompletableSource> function) {
        ObjectHelper.requireNonNull(function, "errorMapper is null");
        return RxJavaPlugins.onAssembly(new CompletableResumeNext(this, function));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable repeat() {
        return fromPublisher(toFlowable().repeat());
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable repeat(long j) {
        return fromPublisher(toFlowable().repeat(j));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable repeatUntil(BooleanSupplier booleanSupplier) {
        return fromPublisher(toFlowable().repeatUntil(booleanSupplier));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable repeatWhen(Function<? super Flowable<Object>, ? extends Publisher<Object>> function) {
        return fromPublisher(toFlowable().repeatWhen(function));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable retry() {
        return fromPublisher(toFlowable().retry());
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable retry(BiPredicate<? super Integer, ? super Throwable> biPredicate) {
        return fromPublisher(toFlowable().retry(biPredicate));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable retry(long j) {
        return fromPublisher(toFlowable().retry(j));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable retry(Predicate<? super Throwable> predicate) {
        return fromPublisher(toFlowable().retry(predicate));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable retryWhen(Function<? super Flowable<Throwable>, ? extends Publisher<Object>> function) {
        return fromPublisher(toFlowable().retryWhen(function));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Completable startWith(CompletableSource completableSource) {
        ObjectHelper.requireNonNull(completableSource, "other is null");
        return concatArray(completableSource, this);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final <T> Observable<T> startWith(Observable<T> observable) {
        ObjectHelper.requireNonNull(observable, "other is null");
        return observable.concatWith(toObservable());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    public final <T> Flowable<T> startWith(Publisher<T> publisher) {
        ObjectHelper.requireNonNull(publisher, "other is null");
        return toFlowable().startWith((Publisher) publisher);
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Disposable subscribe() {
        EmptyCompletableObserver emptyCompletableObserver = new EmptyCompletableObserver();
        subscribe(emptyCompletableObserver);
        return emptyCompletableObserver;
    }

    @Override // io.reactivex.CompletableSource
    @SchedulerSupport(SchedulerSupport.NONE)
    public final void subscribe(CompletableObserver completableObserver) {
        ObjectHelper.requireNonNull(completableObserver, "s is null");
        try {
            subscribeActual(RxJavaPlugins.onSubscribe(this, completableObserver));
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
            throw toNpe(th);
        }
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final <E extends CompletableObserver> E subscribeWith(E e) {
        subscribe(e);
        return e;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Disposable subscribe(Action action, Consumer<? super Throwable> consumer) {
        ObjectHelper.requireNonNull(consumer, "onError is null");
        ObjectHelper.requireNonNull(action, "onComplete is null");
        CallbackCompletableObserver callbackCompletableObserver = new CallbackCompletableObserver(consumer, action);
        subscribe(callbackCompletableObserver);
        return callbackCompletableObserver;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final Disposable subscribe(Action action) {
        ObjectHelper.requireNonNull(action, "onComplete is null");
        CallbackCompletableObserver callbackCompletableObserver = new CallbackCompletableObserver(action);
        subscribe(callbackCompletableObserver);
        return callbackCompletableObserver;
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    public final Completable subscribeOn(Scheduler scheduler) {
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new CompletableSubscribeOn(this, scheduler));
    }

    @SchedulerSupport(SchedulerSupport.COMPUTATION)
    public final Completable timeout(long j, TimeUnit timeUnit) {
        return timeout0(j, timeUnit, Schedulers.computation(), null);
    }

    @SchedulerSupport(SchedulerSupport.COMPUTATION)
    public final Completable timeout(long j, TimeUnit timeUnit, CompletableSource completableSource) {
        ObjectHelper.requireNonNull(completableSource, "other is null");
        return timeout0(j, timeUnit, Schedulers.computation(), completableSource);
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    public final Completable timeout(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return timeout0(j, timeUnit, scheduler, null);
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    public final Completable timeout(long j, TimeUnit timeUnit, Scheduler scheduler, CompletableSource completableSource) {
        ObjectHelper.requireNonNull(completableSource, "other is null");
        return timeout0(j, timeUnit, scheduler, completableSource);
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    private Completable timeout0(long j, TimeUnit timeUnit, Scheduler scheduler, CompletableSource completableSource) {
        ObjectHelper.requireNonNull(timeUnit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new CompletableTimeout(this, j, timeUnit, scheduler, completableSource));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    /* renamed from: to */
    public final <U> U m599to(Function<? super Completable, U> function) {
        try {
            return function.apply(this);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SchedulerSupport(SchedulerSupport.NONE)
    @BackpressureSupport(BackpressureKind.FULL)
    public final <T> Flowable<T> toFlowable() {
        if (this instanceof FuseToFlowable) {
            return ((FuseToFlowable) this).fuseToFlowable();
        }
        return RxJavaPlugins.onAssembly(new CompletableToFlowable(this));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SchedulerSupport(SchedulerSupport.NONE)
    public final <T> Maybe<T> toMaybe() {
        if (this instanceof FuseToMaybe) {
            return ((FuseToMaybe) this).fuseToMaybe();
        }
        return RxJavaPlugins.onAssembly(new MaybeFromCompletable(this));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SchedulerSupport(SchedulerSupport.NONE)
    public final <T> Observable<T> toObservable() {
        if (this instanceof FuseToObservable) {
            return ((FuseToObservable) this).fuseToObservable();
        }
        return RxJavaPlugins.onAssembly(new CompletableToObservable(this));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final <T> Single<T> toSingle(Callable<? extends T> callable) {
        ObjectHelper.requireNonNull(callable, "completionValueSupplier is null");
        return RxJavaPlugins.onAssembly(new CompletableToSingle(this, callable, null));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final <T> Single<T> toSingleDefault(T t) {
        ObjectHelper.requireNonNull(t, "completionValue is null");
        return RxJavaPlugins.onAssembly(new CompletableToSingle(this, null, t));
    }

    @SchedulerSupport(SchedulerSupport.CUSTOM)
    public final Completable unsubscribeOn(Scheduler scheduler) {
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new CompletableDisposeOn(this, scheduler));
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final TestObserver<Void> test() {
        TestObserver<Void> testObserver = new TestObserver<>();
        subscribe(testObserver);
        return testObserver;
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    public final TestObserver<Void> test(boolean z) {
        TestObserver<Void> testObserver = new TestObserver<>();
        if (z) {
            testObserver.cancel();
        }
        subscribe(testObserver);
        return testObserver;
    }
}
