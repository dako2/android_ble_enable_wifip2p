package io.reactivex.internal.operators.flowable;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public final class FlowableWithLatestFrom<T, U, R> extends AbstractFlowableWithUpstream<T, R> {
    final BiFunction<? super T, ? super U, ? extends R> combiner;
    final Publisher<? extends U> other;

    public FlowableWithLatestFrom(Publisher<T> publisher, BiFunction<? super T, ? super U, ? extends R> biFunction, Publisher<? extends U> publisher2) {
        super(publisher);
        this.combiner = biFunction;
        this.other = publisher2;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super R> subscriber) {
        final WithLatestFromSubscriber withLatestFromSubscriber = new WithLatestFromSubscriber(new SerializedSubscriber(subscriber), this.combiner);
        this.other.subscribe(new Subscriber<U>() { // from class: io.reactivex.internal.operators.flowable.FlowableWithLatestFrom.1
            @Override // org.reactivestreams.Subscriber
            public void onComplete() {
            }

            @Override // org.reactivestreams.Subscriber
            public void onSubscribe(Subscription subscription) {
                if (withLatestFromSubscriber.setOther(subscription)) {
                    subscription.request(Long.MAX_VALUE);
                }
            }

            @Override // org.reactivestreams.Subscriber
            public void onNext(U u) {
                withLatestFromSubscriber.lazySet(u);
            }

            @Override // org.reactivestreams.Subscriber
            public void onError(Throwable th) {
                withLatestFromSubscriber.otherError(th);
            }
        });
        this.source.subscribe(withLatestFromSubscriber);
    }

    static final class WithLatestFromSubscriber<T, U, R> extends AtomicReference<U> implements Subscriber<T>, Subscription {
        private static final long serialVersionUID = -312246233408980075L;
        final Subscriber<? super R> actual;
        final BiFunction<? super T, ? super U, ? extends R> combiner;

        /* renamed from: s */
        final AtomicReference<Subscription> f702s = new AtomicReference<>();
        final AtomicReference<Subscription> other = new AtomicReference<>();

        WithLatestFromSubscriber(Subscriber<? super R> subscriber, BiFunction<? super T, ? super U, ? extends R> biFunction) {
            this.actual = subscriber;
            this.combiner = biFunction;
        }

        @Override // org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this.f702s, subscription)) {
                this.actual.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            U u = get();
            if (u != null) {
                try {
                    this.actual.onNext(this.combiner.apply(t, u));
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    cancel();
                    this.actual.onError(th);
                }
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            SubscriptionHelper.cancel(this.other);
            this.actual.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            SubscriptionHelper.cancel(this.other);
            this.actual.onComplete();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.f702s.get().request(j);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.f702s.get().cancel();
            SubscriptionHelper.cancel(this.other);
        }

        public boolean setOther(Subscription subscription) {
            return SubscriptionHelper.setOnce(this.other, subscription);
        }

        public void otherError(Throwable th) {
            if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m47m(this.f702s, null, SubscriptionHelper.CANCELLED)) {
                EmptySubscription.error(th, this.actual);
            } else if (this.f702s.get() != SubscriptionHelper.CANCELLED) {
                cancel();
                this.actual.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }
    }
}
