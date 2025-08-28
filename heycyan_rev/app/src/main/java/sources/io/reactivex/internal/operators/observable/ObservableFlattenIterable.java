package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Iterator;

/* loaded from: classes2.dex */
public final class ObservableFlattenIterable<T, R> extends AbstractObservableWithUpstream<T, R> {
    final Function<? super T, ? extends Iterable<? extends R>> mapper;

    public ObservableFlattenIterable(ObservableSource<T> observableSource, Function<? super T, ? extends Iterable<? extends R>> function) {
        super(observableSource);
        this.mapper = function;
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super R> observer) {
        this.source.subscribe(new FlattenIterableObserver(observer, this.mapper));
    }

    static final class FlattenIterableObserver<T, R> implements Observer<T>, Disposable {
        final Observer<? super R> actual;

        /* renamed from: d */
        Disposable f777d;
        final Function<? super T, ? extends Iterable<? extends R>> mapper;

        FlattenIterableObserver(Observer<? super R> observer, Function<? super T, ? extends Iterable<? extends R>> function) {
            this.actual = observer;
            this.mapper = function;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f777d, disposable)) {
                this.f777d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.f777d == DisposableHelper.DISPOSED) {
                return;
            }
            try {
                Iterator<? extends R> it = this.mapper.apply(t).iterator();
                Observer<? super R> observer = this.actual;
                while (it.hasNext()) {
                    try {
                        try {
                            observer.onNext((Object) ObjectHelper.requireNonNull(it.next(), "The iterator returned a null value"));
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            this.f777d.dispose();
                            onError(th);
                            return;
                        }
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        this.f777d.dispose();
                        onError(th2);
                        return;
                    }
                }
            } catch (Throwable th3) {
                Exceptions.throwIfFatal(th3);
                this.f777d.dispose();
                onError(th3);
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.f777d == DisposableHelper.DISPOSED) {
                RxJavaPlugins.onError(th);
            } else {
                this.f777d = DisposableHelper.DISPOSED;
                this.actual.onError(th);
            }
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.f777d == DisposableHelper.DISPOSED) {
                return;
            }
            this.f777d = DisposableHelper.DISPOSED;
            this.actual.onComplete();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.f777d.isDisposed();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.f777d.dispose();
            this.f777d = DisposableHelper.DISPOSED;
        }
    }
}
