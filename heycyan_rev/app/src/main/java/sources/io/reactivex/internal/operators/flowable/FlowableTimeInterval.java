package io.reactivex.internal.operators.flowable;

import io.reactivex.Scheduler;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.schedulers.Timed;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public final class FlowableTimeInterval<T> extends AbstractFlowableWithUpstream<T, Timed<T>> {
    final Scheduler scheduler;
    final TimeUnit unit;

    public FlowableTimeInterval(Publisher<T> publisher, TimeUnit timeUnit, Scheduler scheduler) {
        super(publisher);
        this.scheduler = scheduler;
        this.unit = timeUnit;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super Timed<T>> subscriber) {
        this.source.subscribe(new TimeIntervalSubscriber(subscriber, this.unit, this.scheduler));
    }

    static final class TimeIntervalSubscriber<T> implements Subscriber<T>, Subscription {
        final Subscriber<? super Timed<T>> actual;
        long lastTime;

        /* renamed from: s */
        Subscription f680s;
        final Scheduler scheduler;
        final TimeUnit unit;

        TimeIntervalSubscriber(Subscriber<? super Timed<T>> subscriber, TimeUnit timeUnit, Scheduler scheduler) {
            this.actual = subscriber;
            this.scheduler = scheduler;
            this.unit = timeUnit;
        }

        @Override // org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.f680s, subscription)) {
                this.lastTime = this.scheduler.now(this.unit);
                this.f680s = subscription;
                this.actual.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            long jNow = this.scheduler.now(this.unit);
            long j = this.lastTime;
            this.lastTime = jNow;
            this.actual.onNext(new Timed(t, jNow - j, this.unit));
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.actual.onComplete();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.f680s.request(j);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.f680s.cancel();
        }
    }
}
