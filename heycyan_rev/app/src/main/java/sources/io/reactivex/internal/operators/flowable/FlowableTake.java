package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicBoolean;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public final class FlowableTake<T> extends AbstractFlowableWithUpstream<T, T> {
    final long limit;

    public FlowableTake(Publisher<T> publisher, long j) {
        super(publisher);
        this.limit = j;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe(new TakeSubscriber(subscriber, this.limit));
    }

    static final class TakeSubscriber<T> extends AtomicBoolean implements Subscriber<T>, Subscription {
        private static final long serialVersionUID = -5636543848937116287L;
        final Subscriber<? super T> actual;
        boolean done;
        final long limit;
        long remaining;
        Subscription subscription;

        TakeSubscriber(Subscriber<? super T> subscriber, long j) {
            this.actual = subscriber;
            this.limit = j;
            this.remaining = j;
        }

        @Override // org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.subscription, subscription)) {
                this.subscription = subscription;
                if (this.limit == 0) {
                    subscription.cancel();
                    this.done = true;
                    EmptySubscription.complete(this.actual);
                    return;
                }
                this.actual.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            long j = this.remaining;
            long j2 = j - 1;
            this.remaining = j2;
            if (j > 0) {
                boolean z = j2 == 0;
                this.actual.onNext(t);
                if (z) {
                    this.subscription.cancel();
                    onComplete();
                }
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                return;
            }
            this.done = true;
            this.subscription.cancel();
            this.actual.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.actual.onComplete();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                if (!get() && compareAndSet(false, true) && j >= this.limit) {
                    this.subscription.request(Long.MAX_VALUE);
                } else {
                    this.subscription.request(j);
                }
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.subscription.cancel();
        }
    }
}
