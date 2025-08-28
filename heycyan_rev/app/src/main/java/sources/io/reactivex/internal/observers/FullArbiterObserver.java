package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.ObserverFullArbiter;

/* loaded from: classes2.dex */
public final class FullArbiterObserver<T> implements Observer<T> {
    final ObserverFullArbiter<T> arbiter;

    /* renamed from: s */
    Disposable f565s;

    public FullArbiterObserver(ObserverFullArbiter<T> observerFullArbiter) {
        this.arbiter = observerFullArbiter;
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.f565s, disposable)) {
            this.f565s = disposable;
            this.arbiter.setDisposable(disposable);
        }
    }

    @Override // io.reactivex.Observer
    public void onNext(T t) {
        this.arbiter.onNext(t, this.f565s);
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        this.arbiter.onError(th, this.f565s);
    }

    @Override // io.reactivex.Observer
    public void onComplete() {
        this.arbiter.onComplete(this.f565s);
    }
}
