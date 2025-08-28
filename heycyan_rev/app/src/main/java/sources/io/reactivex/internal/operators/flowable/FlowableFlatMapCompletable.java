package io.reactivex.internal.operators.flowable;

import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public final class FlowableFlatMapCompletable<T> extends AbstractFlowableWithUpstream<T, T> {
    final boolean delayErrors;
    final Function<? super T, ? extends CompletableSource> mapper;
    final int maxConcurrency;

    public FlowableFlatMapCompletable(Publisher<T> publisher, Function<? super T, ? extends CompletableSource> function, boolean z, int i) {
        super(publisher);
        this.mapper = function;
        this.delayErrors = z;
        this.maxConcurrency = i;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe(new FlatMapCompletableMainSubscriber(subscriber, this.mapper, this.delayErrors, this.maxConcurrency));
    }

    static final class FlatMapCompletableMainSubscriber<T> extends BasicIntQueueSubscription<T> implements Subscriber<T> {
        private static final long serialVersionUID = 8443155186132538303L;
        final Subscriber<? super T> actual;
        final boolean delayErrors;
        final Function<? super T, ? extends CompletableSource> mapper;
        final int maxConcurrency;

        /* renamed from: s */
        Subscription f625s;
        final AtomicThrowable errors = new AtomicThrowable();
        final CompositeDisposable set = new CompositeDisposable();

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public void clear() {
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return true;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public T poll() throws Exception {
            return null;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            return i & 2;
        }

        FlatMapCompletableMainSubscriber(Subscriber<? super T> subscriber, Function<? super T, ? extends CompletableSource> function, boolean z, int i) {
            this.actual = subscriber;
            this.mapper = function;
            this.delayErrors = z;
            this.maxConcurrency = i;
            lazySet(1);
        }

        @Override // org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.f625s, subscription)) {
                this.f625s = subscription;
                this.actual.onSubscribe(this);
                int i = this.maxConcurrency;
                if (i == Integer.MAX_VALUE) {
                    subscription.request(Long.MAX_VALUE);
                } else {
                    subscription.request(i);
                }
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            try {
                CompletableSource completableSource = (CompletableSource) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null CompletableSource");
                getAndIncrement();
                InnerConsumer innerConsumer = new InnerConsumer();
                this.set.add(innerConsumer);
                completableSource.subscribe(innerConsumer);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.f625s.cancel();
                onError(th);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                if (this.delayErrors) {
                    if (decrementAndGet() == 0) {
                        this.actual.onError(this.errors.terminate());
                        return;
                    } else {
                        if (this.maxConcurrency != Integer.MAX_VALUE) {
                            this.f625s.request(1L);
                            return;
                        }
                        return;
                    }
                }
                cancel();
                if (getAndSet(0) > 0) {
                    this.actual.onError(this.errors.terminate());
                    return;
                }
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (decrementAndGet() == 0) {
                Throwable thTerminate = this.errors.terminate();
                if (thTerminate != null) {
                    this.actual.onError(thTerminate);
                    return;
                } else {
                    this.actual.onComplete();
                    return;
                }
            }
            if (this.maxConcurrency != Integer.MAX_VALUE) {
                this.f625s.request(1L);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.f625s.cancel();
            this.set.dispose();
        }

        void innerComplete(FlatMapCompletableMainSubscriber<T>.InnerConsumer innerConsumer) {
            this.set.delete(innerConsumer);
            onComplete();
        }

        void innerError(FlatMapCompletableMainSubscriber<T>.InnerConsumer innerConsumer, Throwable th) {
            this.set.delete(innerConsumer);
            onError(th);
        }

        final class InnerConsumer extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
            private static final long serialVersionUID = 8606673141535671828L;

            InnerConsumer() {
            }

            @Override // io.reactivex.CompletableObserver
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
            public void onComplete() {
                FlatMapCompletableMainSubscriber.this.innerComplete(this);
            }

            @Override // io.reactivex.CompletableObserver
            public void onError(Throwable th) {
                FlatMapCompletableMainSubscriber.this.innerError(this, th);
            }

            @Override // io.reactivex.disposables.Disposable
            public void dispose() {
                DisposableHelper.dispose(this);
            }

            @Override // io.reactivex.disposables.Disposable
            public boolean isDisposed() {
                return DisposableHelper.isDisposed(get());
            }
        }
    }
}
