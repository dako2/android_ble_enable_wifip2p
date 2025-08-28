package io.reactivex.internal.operators.flowable;

import io.reactivex.Notification;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public final class FlowableDematerialize<T> extends AbstractFlowableWithUpstream<Notification<T>, T> {
    public FlowableDematerialize(Publisher<Notification<T>> publisher) {
        super(publisher);
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe(new DematerializeSubscriber(subscriber));
    }

    static final class DematerializeSubscriber<T> implements Subscriber<Notification<T>>, Subscription {
        final Subscriber<? super T> actual;
        boolean done;

        /* renamed from: s */
        Subscription f617s;

        DematerializeSubscriber(Subscriber<? super T> subscriber) {
            this.actual = subscriber;
        }

        @Override // org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.f617s, subscription)) {
                this.f617s = subscription;
                this.actual.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(Notification<T> notification) {
            if (this.done) {
                if (notification.isOnError()) {
                    RxJavaPlugins.onError(notification.getError());
                }
            } else if (notification.isOnError()) {
                this.f617s.cancel();
                onError(notification.getError());
            } else if (notification.isOnComplete()) {
                this.f617s.cancel();
                onComplete();
            } else {
                this.actual.onNext(notification.getValue());
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
            } else {
                this.done = true;
                this.actual.onError(th);
            }
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
            this.f617s.request(j);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.f617s.cancel();
        }
    }
}
