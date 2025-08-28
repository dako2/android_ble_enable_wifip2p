package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import java.util.ArrayDeque;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public final class FlowableSkipLast<T> extends AbstractFlowableWithUpstream<T, T> {
    final int skip;

    public FlowableSkipLast(Publisher<T> publisher, int i) {
        super(publisher);
        this.skip = i;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe(new SkipLastSubscriber(subscriber, this.skip));
    }

    static final class SkipLastSubscriber<T> extends ArrayDeque<T> implements Subscriber<T>, Subscription {
        private static final long serialVersionUID = -3807491841935125653L;
        final Subscriber<? super T> actual;

        /* renamed from: s */
        Subscription f667s;
        final int skip;

        SkipLastSubscriber(Subscriber<? super T> subscriber, int i) {
            super(i);
            this.actual = subscriber;
            this.skip = i;
        }

        @Override // org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.f667s, subscription)) {
                this.f667s = subscription;
                this.actual.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.skip == size()) {
                this.actual.onNext(poll());
            } else {
                this.f667s.request(1L);
            }
            offer(t);
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
            this.f667s.request(j);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.f667s.cancel();
        }
    }
}
