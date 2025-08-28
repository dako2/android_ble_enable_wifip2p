package io.reactivex.internal.operators.flowable;

import io.reactivex.Emitter;
import io.reactivex.Flowable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public final class FlowableGenerate<T, S> extends Flowable<T> {
    final Consumer<? super S> disposeState;
    final BiFunction<S, Emitter<T>, S> generator;
    final Callable<S> stateSupplier;

    public FlowableGenerate(Callable<S> callable, BiFunction<S, Emitter<T>, S> biFunction, Consumer<? super S> consumer) {
        this.stateSupplier = callable;
        this.generator = biFunction;
        this.disposeState = consumer;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        try {
            subscriber.onSubscribe(new GeneratorSubscription(subscriber, this.generator, this.disposeState, this.stateSupplier.call()));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptySubscription.error(th, subscriber);
        }
    }

    static final class GeneratorSubscription<T, S> extends AtomicLong implements Emitter<T>, Subscription {
        private static final long serialVersionUID = 7565982551505011832L;
        final Subscriber<? super T> actual;
        volatile boolean cancelled;
        final Consumer<? super S> disposeState;
        final BiFunction<S, ? super Emitter<T>, S> generator;
        S state;
        boolean terminate;

        GeneratorSubscription(Subscriber<? super T> subscriber, BiFunction<S, ? super Emitter<T>, S> biFunction, Consumer<? super S> consumer, S s) {
            this.actual = subscriber;
            this.generator = biFunction;
            this.disposeState = consumer;
            this.state = s;
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x004a, code lost:
        
            r8.state = r0;
            r9 = addAndGet(-r4);
         */
        @Override // org.reactivestreams.Subscription
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void request(long j) {
            if (SubscriptionHelper.validate(j) && BackpressureHelper.add(this, j) == 0) {
                S sApply = this.state;
                BiFunction<S, ? super Emitter<T>, S> biFunction = this.generator;
                do {
                    long j2 = 0;
                    while (true) {
                        if (j2 != j) {
                            if (this.cancelled) {
                                dispose(sApply);
                                return;
                            }
                            try {
                                sApply = biFunction.apply(sApply, this);
                                if (this.terminate) {
                                    this.cancelled = true;
                                    dispose(sApply);
                                    return;
                                }
                                j2++;
                            } catch (Throwable th) {
                                Exceptions.throwIfFatal(th);
                                this.cancelled = true;
                                this.actual.onError(th);
                                return;
                            }
                        } else {
                            j = get();
                            if (j2 == j) {
                                break;
                            }
                        }
                    }
                } while (j != 0);
            }
        }

        private void dispose(S s) {
            try {
                this.disposeState.accept(s);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            if (BackpressureHelper.add(this, 1L) == 0) {
                dispose(this.state);
            }
        }

        @Override // io.reactivex.Emitter
        public void onNext(T t) {
            if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            } else {
                this.actual.onNext(t);
            }
        }

        @Override // io.reactivex.Emitter
        public void onError(Throwable th) {
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            this.terminate = true;
            this.actual.onError(th);
        }

        @Override // io.reactivex.Emitter
        public void onComplete() {
            this.terminate = true;
            this.actual.onComplete();
        }
    }
}
