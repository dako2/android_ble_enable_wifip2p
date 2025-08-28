package io.reactivex.subscribers;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public abstract class DefaultSubscriber<T> implements Subscriber<T> {

    /* renamed from: s */
    private Subscription f906s;

    @Override // org.reactivestreams.Subscriber
    public final void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.f906s, subscription)) {
            this.f906s = subscription;
            onStart();
        }
    }

    protected final void request(long j) {
        Subscription subscription = this.f906s;
        if (subscription != null) {
            subscription.request(j);
        }
    }

    protected final void cancel() {
        Subscription subscription = this.f906s;
        this.f906s = SubscriptionHelper.CANCELLED;
        subscription.cancel();
    }

    protected void onStart() {
        request(Long.MAX_VALUE);
    }
}
