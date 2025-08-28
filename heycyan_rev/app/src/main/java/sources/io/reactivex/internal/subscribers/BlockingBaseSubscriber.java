package io.reactivex.internal.subscribers;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.concurrent.CountDownLatch;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public abstract class BlockingBaseSubscriber<T> extends CountDownLatch implements Subscriber<T> {
    volatile boolean cancelled;
    Throwable error;

    /* renamed from: s */
    Subscription f866s;
    T value;

    public BlockingBaseSubscriber() {
        super(1);
    }

    @Override // org.reactivestreams.Subscriber
    public final void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.f866s, subscription)) {
            this.f866s = subscription;
            if (this.cancelled) {
                return;
            }
            subscription.request(Long.MAX_VALUE);
            if (this.cancelled) {
                this.f866s = SubscriptionHelper.CANCELLED;
                subscription.cancel();
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public final void onComplete() {
        countDown();
    }

    public final T blockingGet() {
        if (getCount() != 0) {
            try {
                await();
            } catch (InterruptedException e) {
                Subscription subscription = this.f866s;
                this.f866s = SubscriptionHelper.CANCELLED;
                if (subscription != null) {
                    subscription.cancel();
                }
                throw ExceptionHelper.wrapOrThrow(e);
            }
        }
        Throwable th = this.error;
        if (th != null) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
        return this.value;
    }
}
