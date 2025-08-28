package io.reactivex.internal.subscribers;

import io.reactivex.internal.subscriptions.FullArbiter;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public final class FullArbiterSubscriber<T> implements Subscriber<T> {
    final FullArbiter<T> arbiter;

    /* renamed from: s */
    Subscription f868s;

    public FullArbiterSubscriber(FullArbiter<T> fullArbiter) {
        this.arbiter = fullArbiter;
    }

    @Override // org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.f868s, subscription)) {
            this.f868s = subscription;
            this.arbiter.setSubscription(subscription);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        this.arbiter.onNext(t, this.f868s);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.arbiter.onError(th, this.f868s);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.arbiter.onComplete(this.f868s);
    }
}
