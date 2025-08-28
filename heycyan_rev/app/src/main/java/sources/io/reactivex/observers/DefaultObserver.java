package io.reactivex.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

/* loaded from: classes2.dex */
public abstract class DefaultObserver<T> implements Observer<T> {

    /* renamed from: s */
    private Disposable f893s;

    protected void onStart() {
    }

    @Override // io.reactivex.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.f893s, disposable)) {
            this.f893s = disposable;
            onStart();
        }
    }

    protected final void cancel() {
        Disposable disposable = this.f893s;
        this.f893s = DisposableHelper.DISPOSED;
        disposable.dispose();
    }
}
