package io.reactivex.internal.operators.completable;

import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
public final class CompletableToSingle<T> extends Single<T> {
    final T completionValue;
    final Callable<? extends T> completionValueSupplier;
    final CompletableSource source;

    public CompletableToSingle(CompletableSource completableSource, Callable<? extends T> callable, T t) {
        this.source = completableSource;
        this.completionValue = t;
        this.completionValueSupplier = callable;
    }

    @Override // io.reactivex.Single
    protected void subscribeActual(final SingleObserver<? super T> singleObserver) {
        this.source.subscribe(new CompletableObserver() { // from class: io.reactivex.internal.operators.completable.CompletableToSingle.1
            @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
            public void onComplete() {
                T tCall;
                if (CompletableToSingle.this.completionValueSupplier != null) {
                    try {
                        tCall = CompletableToSingle.this.completionValueSupplier.call();
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        singleObserver.onError(th);
                        return;
                    }
                } else {
                    tCall = CompletableToSingle.this.completionValue;
                }
                if (tCall == null) {
                    singleObserver.onError(new NullPointerException("The value supplied is null"));
                } else {
                    singleObserver.onSuccess(tCall);
                }
            }

            @Override // io.reactivex.CompletableObserver
            public void onError(Throwable th) {
                singleObserver.onError(th);
            }

            @Override // io.reactivex.CompletableObserver
            public void onSubscribe(Disposable disposable) {
                singleObserver.onSubscribe(disposable);
            }
        });
    }
}
