package io.reactivex.internal.operators.flowable;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscribers.FullArbiterSubscriber;
import io.reactivex.internal.subscriptions.FullArbiter;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public final class FlowableTimeoutTimed<T> extends AbstractFlowableWithUpstream<T, T> {
    static final Disposable NEW_TIMER = new Disposable() { // from class: io.reactivex.internal.operators.flowable.FlowableTimeoutTimed.1
        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return true;
        }
    };
    final Publisher<? extends T> other;
    final Scheduler scheduler;
    final long timeout;
    final TimeUnit unit;

    public FlowableTimeoutTimed(Publisher<T> publisher, long j, TimeUnit timeUnit, Scheduler scheduler, Publisher<? extends T> publisher2) {
        super(publisher);
        this.timeout = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.other = publisher2;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> subscriber) {
        if (this.other == null) {
            this.source.subscribe(new TimeoutTimedSubscriber(new SerializedSubscriber(subscriber), this.timeout, this.unit, this.scheduler.createWorker()));
        } else {
            this.source.subscribe(new TimeoutTimedOtherSubscriber(subscriber, this.timeout, this.unit, this.scheduler.createWorker(), this.other));
        }
    }

    static final class TimeoutTimedOtherSubscriber<T> implements Subscriber<T>, Disposable {
        final Subscriber<? super T> actual;
        final FullArbiter<T> arbiter;
        volatile boolean done;
        volatile long index;
        final Publisher<? extends T> other;

        /* renamed from: s */
        Subscription f683s;
        final long timeout;
        final AtomicReference<Disposable> timer = new AtomicReference<>();
        final TimeUnit unit;
        final Scheduler.Worker worker;

        TimeoutTimedOtherSubscriber(Subscriber<? super T> subscriber, long j, TimeUnit timeUnit, Scheduler.Worker worker, Publisher<? extends T> publisher) {
            this.actual = subscriber;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = worker;
            this.other = publisher;
            this.arbiter = new FullArbiter<>(subscriber, this, 8);
        }

        @Override // org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.f683s, subscription)) {
                this.f683s = subscription;
                if (this.arbiter.setSubscription(subscription)) {
                    this.actual.onSubscribe(this.arbiter);
                    scheduleTimeout(0L);
                }
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            long j = this.index + 1;
            this.index = j;
            if (this.arbiter.onNext(t, this.f683s)) {
                scheduleTimeout(j);
            }
        }

        void scheduleTimeout(final long j) {
            Disposable disposable = this.timer.get();
            if (disposable != null) {
                disposable.dispose();
            }
            if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m47m(this.timer, disposable, FlowableTimeoutTimed.NEW_TIMER)) {
                DisposableHelper.replace(this.timer, this.worker.schedule(new Runnable() { // from class: io.reactivex.internal.operators.flowable.FlowableTimeoutTimed.TimeoutTimedOtherSubscriber.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (j == TimeoutTimedOtherSubscriber.this.index) {
                            TimeoutTimedOtherSubscriber.this.done = true;
                            TimeoutTimedOtherSubscriber.this.f683s.cancel();
                            DisposableHelper.dispose(TimeoutTimedOtherSubscriber.this.timer);
                            TimeoutTimedOtherSubscriber.this.subscribeNext();
                            TimeoutTimedOtherSubscriber.this.worker.dispose();
                        }
                    }
                }, this.timeout, this.unit));
            }
        }

        void subscribeNext() {
            this.other.subscribe(new FullArbiterSubscriber(this.arbiter));
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            this.worker.dispose();
            DisposableHelper.dispose(this.timer);
            this.arbiter.onError(th, this.f683s);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.worker.dispose();
            DisposableHelper.dispose(this.timer);
            this.arbiter.onComplete(this.f683s);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.worker.dispose();
            DisposableHelper.dispose(this.timer);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.worker.isDisposed();
        }
    }

    static final class TimeoutTimedSubscriber<T> implements Subscriber<T>, Disposable, Subscription {
        final Subscriber<? super T> actual;
        volatile boolean done;
        volatile long index;

        /* renamed from: s */
        Subscription f684s;
        final long timeout;
        final AtomicReference<Disposable> timer = new AtomicReference<>();
        final TimeUnit unit;
        final Scheduler.Worker worker;

        TimeoutTimedSubscriber(Subscriber<? super T> subscriber, long j, TimeUnit timeUnit, Scheduler.Worker worker) {
            this.actual = subscriber;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = worker;
        }

        @Override // org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.f684s, subscription)) {
                this.f684s = subscription;
                this.actual.onSubscribe(this);
                scheduleTimeout(0L);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            long j = this.index + 1;
            this.index = j;
            this.actual.onNext(t);
            scheduleTimeout(j);
        }

        void scheduleTimeout(final long j) {
            Disposable disposable = this.timer.get();
            if (disposable != null) {
                disposable.dispose();
            }
            if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m47m(this.timer, disposable, FlowableTimeoutTimed.NEW_TIMER)) {
                DisposableHelper.replace(this.timer, this.worker.schedule(new Runnable() { // from class: io.reactivex.internal.operators.flowable.FlowableTimeoutTimed.TimeoutTimedSubscriber.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (j == TimeoutTimedSubscriber.this.index) {
                            TimeoutTimedSubscriber.this.done = true;
                            TimeoutTimedSubscriber.this.dispose();
                            TimeoutTimedSubscriber.this.actual.onError(new TimeoutException());
                        }
                    }
                }, this.timeout, this.unit));
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            dispose();
            this.actual.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            dispose();
            this.actual.onComplete();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.worker.dispose();
            DisposableHelper.dispose(this.timer);
            this.f684s.cancel();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.worker.isDisposed();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.f684s.request(j);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            dispose();
        }
    }
}
