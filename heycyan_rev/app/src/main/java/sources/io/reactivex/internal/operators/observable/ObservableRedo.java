package io.reactivex.internal.operators.observable;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.ToNotificationObserver;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public final class ObservableRedo<T> extends AbstractObservableWithUpstream<T, T> {
    final Function<? super Observable<Notification<Object>>, ? extends ObservableSource<?>> manager;

    public ObservableRedo(ObservableSource<T> observableSource, Function<? super Observable<Notification<Object>>, ? extends ObservableSource<?>> function) {
        super(observableSource);
        this.manager = function;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        Subject<T> serialized = BehaviorSubject.create().toSerialized();
        final RedoObserver redoObserver = new RedoObserver(observer, serialized, this.source);
        observer.onSubscribe(redoObserver.arbiter);
        try {
            ((ObservableSource) ObjectHelper.requireNonNull(this.manager.apply(serialized), "The function returned a null ObservableSource")).subscribe(new ToNotificationObserver(new Consumer<Notification<Object>>() { // from class: io.reactivex.internal.operators.observable.ObservableRedo.1
                @Override // io.reactivex.functions.Consumer
                public void accept(Notification<Object> notification) {
                    redoObserver.handle(notification);
                }
            }));
            redoObserver.handle(Notification.createOnNext(0));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            observer.onError(th);
        }
    }

    static final class RedoObserver<T> extends AtomicBoolean implements Observer<T> {
        private static final long serialVersionUID = -1151903143112844287L;
        final Observer<? super T> actual;
        final ObservableSource<? extends T> source;
        final Subject<Notification<Object>> subject;
        final AtomicInteger wip = new AtomicInteger();
        final SequentialDisposable arbiter = new SequentialDisposable();

        RedoObserver(Observer<? super T> observer, Subject<Notification<Object>> subject, ObservableSource<? extends T> observableSource) {
            this.actual = observer;
            this.subject = subject;
            this.source = observableSource;
            lazySet(true);
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            this.arbiter.replace(disposable);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            this.actual.onNext(t);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (compareAndSet(false, true)) {
                this.subject.onNext(Notification.createOnError(th));
            }
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (compareAndSet(false, true)) {
                this.subject.onNext(Notification.createOnComplete());
            }
        }

        void handle(Notification<Object> notification) {
            int iAddAndGet = 1;
            if (compareAndSet(true, false)) {
                if (notification.isOnError()) {
                    this.arbiter.dispose();
                    this.actual.onError(notification.getError());
                    return;
                }
                if (notification.isOnNext()) {
                    if (this.wip.getAndIncrement() == 0) {
                        while (!this.arbiter.isDisposed()) {
                            this.source.subscribe(this);
                            iAddAndGet = this.wip.addAndGet(-iAddAndGet);
                            if (iAddAndGet == 0) {
                                return;
                            }
                        }
                        return;
                    }
                    return;
                }
                this.arbiter.dispose();
                this.actual.onComplete();
            }
        }
    }
}
