package io.reactivex.internal.operators.flowable;

import io.reactivex.Scheduler;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public final class FlowableDelay<T> extends AbstractFlowableWithUpstream<T, T> {
    final long delay;
    final boolean delayError;
    final Scheduler scheduler;
    final TimeUnit unit;

    public FlowableDelay(Publisher<T> publisher, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        super(publisher);
        this.delay = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.delayError = z;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe(new DelaySubscriber(this.delayError ? subscriber : new SerializedSubscriber(subscriber), this.delay, this.unit, this.scheduler.createWorker(), this.delayError));
    }

    static final class DelaySubscriber<T> implements Subscriber<T>, Subscription {
        final Subscriber<? super T> actual;
        final long delay;
        final boolean delayError;

        /* renamed from: s */
        Subscription f615s;
        final TimeUnit unit;

        /* renamed from: w */
        final Scheduler.Worker f616w;

        DelaySubscriber(Subscriber<? super T> subscriber, long j, TimeUnit timeUnit, Scheduler.Worker worker, boolean z) {
            this.actual = subscriber;
            this.delay = j;
            this.unit = timeUnit;
            this.f616w = worker;
            this.delayError = z;
        }

        @Override // org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.f615s, subscription)) {
                this.f615s = subscription;
                this.actual.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(final T t) {
            this.f616w.schedule(new Runnable() { // from class: io.reactivex.internal.operators.flowable.FlowableDelay.DelaySubscriber.1
                @Override // java.lang.Runnable
                public void run() {
                    DelaySubscriber.this.actual.onNext((Object) t);
                }
            }, this.delay, this.unit);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(final Throwable th) {
            this.f616w.schedule(new Runnable() { // from class: io.reactivex.internal.operators.flowable.FlowableDelay.DelaySubscriber.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        DelaySubscriber.this.actual.onError(th);
                    } finally {
                        DelaySubscriber.this.f616w.dispose();
                    }
                }
            }, this.delayError ? this.delay : 0L, this.unit);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.f616w.schedule(new Runnable() { // from class: io.reactivex.internal.operators.flowable.FlowableDelay.DelaySubscriber.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        DelaySubscriber.this.actual.onComplete();
                    } finally {
                        DelaySubscriber.this.f616w.dispose();
                    }
                }
            }, this.delay, this.unit);
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.f615s.request(j);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.f616w.dispose();
            this.f615s.cancel();
        }
    }
}
