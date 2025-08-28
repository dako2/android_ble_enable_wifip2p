package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public final class MaybeDelayOtherPublisher<T, U> extends AbstractMaybeWithUpstream<T, T> {
    final Publisher<U> other;

    public MaybeDelayOtherPublisher(MaybeSource<T> maybeSource, Publisher<U> publisher) {
        super(maybeSource);
        this.other = publisher;
    }

    @Override // io.reactivex.Maybe
    protected void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new DelayMaybeObserver(maybeObserver, this.other));
    }

    static final class DelayMaybeObserver<T, U> implements MaybeObserver<T>, Disposable {

        /* renamed from: d */
        Disposable f707d;
        final OtherSubscriber<T> other;
        final Publisher<U> otherSource;

        DelayMaybeObserver(MaybeObserver<? super T> maybeObserver, Publisher<U> publisher) {
            this.other = new OtherSubscriber<>(maybeObserver);
            this.otherSource = publisher;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.f707d.dispose();
            this.f707d = DisposableHelper.DISPOSED;
            SubscriptionHelper.cancel(this.other);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return SubscriptionHelper.isCancelled(this.other.get());
        }

        @Override // io.reactivex.MaybeObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f707d, disposable)) {
                this.f707d = disposable;
                this.other.actual.onSubscribe(this);
            }
        }

        @Override // io.reactivex.MaybeObserver
        public void onSuccess(T t) {
            this.f707d = DisposableHelper.DISPOSED;
            this.other.value = t;
            subscribeNext();
        }

        @Override // io.reactivex.MaybeObserver
        public void onError(Throwable th) {
            this.f707d = DisposableHelper.DISPOSED;
            this.other.error = th;
            subscribeNext();
        }

        @Override // io.reactivex.MaybeObserver
        public void onComplete() {
            this.f707d = DisposableHelper.DISPOSED;
            subscribeNext();
        }

        void subscribeNext() {
            this.otherSource.subscribe(this.other);
        }
    }

    static final class OtherSubscriber<T> extends AtomicReference<Subscription> implements Subscriber<Object> {
        private static final long serialVersionUID = -1215060610805418006L;
        final MaybeObserver<? super T> actual;
        Throwable error;
        T value;

        OtherSubscriber(MaybeObserver<? super T> maybeObserver) {
            this.actual = maybeObserver;
        }

        @Override // org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                subscription.request(Long.MAX_VALUE);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(Object obj) {
            Subscription subscription = get();
            if (subscription != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                subscription.cancel();
                onComplete();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            Throwable th2 = this.error;
            if (th2 == null) {
                this.actual.onError(th);
            } else {
                this.actual.onError(new CompositeException(th2, th));
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            Throwable th = this.error;
            if (th != null) {
                this.actual.onError(th);
                return;
            }
            T t = this.value;
            if (t != null) {
                this.actual.onSuccess(t);
            } else {
                this.actual.onComplete();
            }
        }
    }
}
