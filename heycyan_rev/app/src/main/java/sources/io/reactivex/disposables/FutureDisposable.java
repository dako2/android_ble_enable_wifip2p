package io.reactivex.disposables;

import java.util.concurrent.Future;

/* loaded from: classes2.dex */
final class FutureDisposable extends ReferenceDisposable<Future<?>> {
    private static final long serialVersionUID = 6545242830671168775L;
    private final boolean allowInterrupt;

    FutureDisposable(Future<?> future, boolean z) {
        super(future);
        this.allowInterrupt = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.reactivex.disposables.ReferenceDisposable
    public void onDisposed(Future<?> future) {
        future.cancel(this.allowInterrupt);
    }
}
