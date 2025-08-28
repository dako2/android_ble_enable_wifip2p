package io.reactivex.subscribers;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public abstract class ResourceSubscriber<T> implements Subscriber<T>, Disposable {

    /* renamed from: s */
    private final AtomicReference<Subscription> f908s = new AtomicReference<>();
    private final ListCompositeDisposable resources = new ListCompositeDisposable();
    private final AtomicLong missedRequested = new AtomicLong();

    public final void add(Disposable disposable) {
        ObjectHelper.requireNonNull(disposable, "resource is null");
        this.resources.add(disposable);
    }

    @Override // org.reactivestreams.Subscriber
    public final void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.deferredSetOnce(this.f908s, this.missedRequested, subscription)) {
            onStart();
        }
    }

    protected void onStart() {
        request(Long.MAX_VALUE);
    }

    protected final void request(long j) {
        SubscriptionHelper.deferredRequest(this.f908s, this.missedRequested, j);
    }

    @Override // io.reactivex.disposables.Disposable
    public final void dispose() {
        if (SubscriptionHelper.cancel(this.f908s)) {
            this.resources.dispose();
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public final boolean isDisposed() {
        return SubscriptionHelper.isCancelled(this.f908s.get());
    }
}
