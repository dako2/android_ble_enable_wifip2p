package io.reactivex.subscribers;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public abstract class DisposableSubscriber<T> implements Subscriber<T>, Disposable {

    /* renamed from: s */
    final AtomicReference<Subscription> f907s = new AtomicReference<>();

    @Override // org.reactivestreams.Subscriber
    public final void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this.f907s, subscription)) {
            onStart();
        }
    }

    protected void onStart() {
        this.f907s.get().request(Long.MAX_VALUE);
    }

    protected final void request(long j) {
        this.f907s.get().request(j);
    }

    protected final void cancel() {
        dispose();
    }

    @Override // io.reactivex.disposables.Disposable
    public final boolean isDisposed() {
        return this.f907s.get() == SubscriptionHelper.CANCELLED;
    }

    @Override // io.reactivex.disposables.Disposable
    public final void dispose() {
        SubscriptionHelper.cancel(this.f907s);
    }
}
