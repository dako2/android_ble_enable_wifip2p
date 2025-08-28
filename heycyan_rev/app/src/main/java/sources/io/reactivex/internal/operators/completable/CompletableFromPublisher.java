package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public final class CompletableFromPublisher<T> extends Completable {
    final Publisher<T> flowable;

    public CompletableFromPublisher(Publisher<T> publisher) {
        this.flowable = publisher;
    }

    @Override // io.reactivex.Completable
    protected void subscribeActual(CompletableObserver completableObserver) {
        this.flowable.subscribe(new FromPublisherSubscriber(completableObserver));
    }

    static final class FromPublisherSubscriber<T> implements Subscriber<T>, Disposable {

        /* renamed from: cs */
        final CompletableObserver f585cs;

        /* renamed from: s */
        Subscription f586s;

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
        }

        FromPublisherSubscriber(CompletableObserver completableObserver) {
            this.f585cs = completableObserver;
        }

        @Override // org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.f586s, subscription)) {
                this.f586s = subscription;
                this.f585cs.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.f585cs.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.f585cs.onComplete();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.f586s.cancel();
            this.f586s = SubscriptionHelper.CANCELLED;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.f586s == SubscriptionHelper.CANCELLED;
        }
    }
}
