package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public final class FlowableUsing<T, D> extends Flowable<T> {
    final Consumer<? super D> disposer;
    final boolean eager;
    final Callable<? extends D> resourceSupplier;
    final Function<? super D, ? extends Publisher<? extends T>> sourceSupplier;

    public FlowableUsing(Callable<? extends D> callable, Function<? super D, ? extends Publisher<? extends T>> function, Consumer<? super D> consumer, boolean z) {
        this.resourceSupplier = callable;
        this.sourceSupplier = function;
        this.disposer = consumer;
        this.eager = z;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        try {
            D dCall = this.resourceSupplier.call();
            try {
                this.sourceSupplier.apply(dCall).subscribe(new UsingSubscriber(subscriber, dCall, this.disposer, this.eager));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                try {
                    this.disposer.accept(dCall);
                    EmptySubscription.error(th, subscriber);
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    EmptySubscription.error(new CompositeException(th, th2), subscriber);
                }
            }
        } catch (Throwable th3) {
            Exceptions.throwIfFatal(th3);
            EmptySubscription.error(th3, subscriber);
        }
    }

    static final class UsingSubscriber<T, D> extends AtomicBoolean implements Subscriber<T>, Subscription {
        private static final long serialVersionUID = 5904473792286235046L;
        final Subscriber<? super T> actual;
        final Consumer<? super D> disposer;
        final boolean eager;
        final D resource;

        /* renamed from: s */
        Subscription f688s;

        UsingSubscriber(Subscriber<? super T> subscriber, D d, Consumer<? super D> consumer, boolean z) {
            this.actual = subscriber;
            this.resource = d;
            this.disposer = consumer;
            this.eager = z;
        }

        @Override // org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.f688s, subscription)) {
                this.f688s = subscription;
                this.actual.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.actual.onNext(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.eager) {
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept(this.resource);
                        th = null;
                    } catch (Throwable th2) {
                        th = th2;
                        Exceptions.throwIfFatal(th);
                    }
                } else {
                    th = null;
                }
                this.f688s.cancel();
                if (th != null) {
                    this.actual.onError(new CompositeException(th, th));
                    return;
                } else {
                    this.actual.onError(th);
                    return;
                }
            }
            this.actual.onError(th);
            this.f688s.cancel();
            disposeAfter();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.eager) {
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept(this.resource);
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        this.actual.onError(th);
                        return;
                    }
                }
                this.f688s.cancel();
                this.actual.onComplete();
                return;
            }
            this.actual.onComplete();
            this.f688s.cancel();
            disposeAfter();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.f688s.request(j);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            disposeAfter();
            this.f688s.cancel();
        }

        void disposeAfter() {
            if (compareAndSet(false, true)) {
                try {
                    this.disposer.accept(this.resource);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    RxJavaPlugins.onError(th);
                }
            }
        }
    }
}
