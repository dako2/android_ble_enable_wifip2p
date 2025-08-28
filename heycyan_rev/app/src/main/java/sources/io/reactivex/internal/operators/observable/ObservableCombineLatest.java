package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
public final class ObservableCombineLatest<T, R> extends Observable<R> {
    final int bufferSize;
    final Function<? super Object[], ? extends R> combiner;
    final boolean delayError;
    final ObservableSource<? extends T>[] sources;
    final Iterable<? extends ObservableSource<? extends T>> sourcesIterable;

    public ObservableCombineLatest(ObservableSource<? extends T>[] observableSourceArr, Iterable<? extends ObservableSource<? extends T>> iterable, Function<? super Object[], ? extends R> function, int i, boolean z) {
        this.sources = observableSourceArr;
        this.sourcesIterable = iterable;
        this.combiner = function;
        this.bufferSize = i;
        this.delayError = z;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super R> observer) {
        int length;
        ObservableSource<? extends T>[] observableSourceArr = this.sources;
        if (observableSourceArr == null) {
            observableSourceArr = new Observable[8];
            length = 0;
            for (ObservableSource<? extends T> observableSource : this.sourcesIterable) {
                if (length == observableSourceArr.length) {
                    ObservableSource<? extends T>[] observableSourceArr2 = new ObservableSource[(length >> 2) + length];
                    System.arraycopy(observableSourceArr, 0, observableSourceArr2, 0, length);
                    observableSourceArr = observableSourceArr2;
                }
                observableSourceArr[length] = observableSource;
                length++;
            }
        } else {
            length = observableSourceArr.length;
        }
        int i = length;
        if (i == 0) {
            EmptyDisposable.complete(observer);
        } else {
            new LatestCoordinator(observer, this.combiner, i, this.bufferSize, this.delayError).subscribe(observableSourceArr);
        }
    }

    static final class LatestCoordinator<T, R> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 8567835998786448817L;
        int active;
        final Observer<? super R> actual;
        volatile boolean cancelled;
        final Function<? super Object[], ? extends R> combiner;
        int complete;
        final boolean delayError;
        volatile boolean done;
        final AtomicThrowable errors = new AtomicThrowable();
        final T[] latest;
        final CombinerObserver<T, R>[] observers;
        final SpscLinkedArrayQueue<Object> queue;

        LatestCoordinator(Observer<? super R> observer, Function<? super Object[], ? extends R> function, int i, int i2, boolean z) {
            this.actual = observer;
            this.combiner = function;
            this.delayError = z;
            this.latest = (T[]) new Object[i];
            this.observers = new CombinerObserver[i];
            this.queue = new SpscLinkedArrayQueue<>(i2);
        }

        public void subscribe(ObservableSource<? extends T>[] observableSourceArr) {
            CombinerObserver<T, R>[] combinerObserverArr = this.observers;
            int length = combinerObserverArr.length;
            for (int i = 0; i < length; i++) {
                combinerObserverArr[i] = new CombinerObserver<>(this, i);
            }
            lazySet(0);
            this.actual.onSubscribe(this);
            for (int i2 = 0; i2 < length && !this.cancelled; i2++) {
                observableSourceArr[i2].subscribe(combinerObserverArr[i2]);
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            if (getAndIncrement() == 0) {
                cancel(this.queue);
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void cancel(SpscLinkedArrayQueue<?> spscLinkedArrayQueue) {
            clear(spscLinkedArrayQueue);
            for (CombinerObserver<T, R> combinerObserver : this.observers) {
                combinerObserver.dispose();
            }
        }

        void clear(SpscLinkedArrayQueue<?> spscLinkedArrayQueue) {
            synchronized (this) {
                Arrays.fill(this.latest, (Object) null);
            }
            spscLinkedArrayQueue.clear();
        }

        void combine(T t, int i) {
            CombinerObserver<T, R> combinerObserver = this.observers[i];
            synchronized (this) {
                if (this.cancelled) {
                    return;
                }
                T[] tArr = this.latest;
                int length = tArr.length;
                T t2 = tArr[i];
                int i2 = this.active;
                if (t2 == null) {
                    i2++;
                    this.active = i2;
                }
                int i3 = this.complete;
                if (t == null) {
                    i3++;
                    this.complete = i3;
                } else {
                    tArr[i] = t;
                }
                boolean z = i2 == length;
                if (i3 == length || (t == null && t2 == null)) {
                    this.done = true;
                } else if (t != null && z) {
                    this.queue.offer(combinerObserver, tArr.clone());
                } else if (t == null && this.errors.get() != null) {
                    this.done = true;
                }
                if (z || t == null) {
                    drain();
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x003c, code lost:
        
            r10 = addAndGet(-r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0041, code lost:
        
            if (r10 != 0) goto L31;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0043, code lost:
        
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            SpscLinkedArrayQueue<?> spscLinkedArrayQueue = this.queue;
            Observer<? super R> observer = this.actual;
            boolean z = this.delayError;
            int iAddAndGet = 1;
            while (!checkTerminated(this.done, spscLinkedArrayQueue.isEmpty(), observer, spscLinkedArrayQueue, z)) {
                while (true) {
                    boolean z2 = this.done;
                    boolean z3 = ((CombinerObserver) spscLinkedArrayQueue.poll()) == null;
                    if (checkTerminated(z2, z3, observer, spscLinkedArrayQueue, z)) {
                        return;
                    }
                    if (z3) {
                        break;
                    }
                    try {
                        observer.onNext((Object) ObjectHelper.requireNonNull(this.combiner.apply((Object[]) spscLinkedArrayQueue.poll()), "The combiner returned a null"));
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        this.cancelled = true;
                        cancel(spscLinkedArrayQueue);
                        observer.onError(th);
                        return;
                    }
                }
            }
        }

        boolean checkTerminated(boolean z, boolean z2, Observer<?> observer, SpscLinkedArrayQueue<?> spscLinkedArrayQueue, boolean z3) {
            if (this.cancelled) {
                cancel(spscLinkedArrayQueue);
                return true;
            }
            if (!z) {
                return false;
            }
            if (z3) {
                if (!z2) {
                    return false;
                }
                clear(this.queue);
                Throwable thTerminate = this.errors.terminate();
                if (thTerminate != null) {
                    observer.onError(thTerminate);
                } else {
                    observer.onComplete();
                }
                return true;
            }
            if (this.errors.get() != null) {
                cancel(spscLinkedArrayQueue);
                observer.onError(this.errors.terminate());
                return true;
            }
            if (!z2) {
                return false;
            }
            clear(this.queue);
            observer.onComplete();
            return true;
        }

        void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                return;
            }
            RxJavaPlugins.onError(th);
        }
    }

    static final class CombinerObserver<T, R> implements Observer<T> {
        final int index;
        final LatestCoordinator<T, R> parent;

        /* renamed from: s */
        final AtomicReference<Disposable> f754s = new AtomicReference<>();

        CombinerObserver(LatestCoordinator<T, R> latestCoordinator, int i) {
            this.parent = latestCoordinator;
            this.index = i;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this.f754s, disposable);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            this.parent.combine(t, this.index);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.parent.onError(th);
            this.parent.combine(null, this.index);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.parent.combine(null, this.index);
        }

        public void dispose() {
            DisposableHelper.dispose(this.f754s);
        }
    }
}
