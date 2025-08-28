package io.reactivex.internal.operators.flowable;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.Flowable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.LinkedArrayList;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public final class FlowableCache<T> extends AbstractFlowableWithUpstream<T, T> {
    final AtomicBoolean once;
    final CacheState<T> state;

    public FlowableCache(Flowable<T> flowable, int i) {
        super(flowable);
        this.state = new CacheState<>(flowable, i);
        this.once = new AtomicBoolean();
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> subscriber) {
        ReplaySubscription<T> replaySubscription = new ReplaySubscription<>(subscriber, this.state);
        this.state.addChild(replaySubscription);
        subscriber.onSubscribe(replaySubscription);
        if (this.once.get() || !this.once.compareAndSet(false, true)) {
            return;
        }
        this.state.connect();
    }

    boolean isConnected() {
        return this.state.isConnected;
    }

    boolean hasSubscribers() {
        return this.state.subscribers.get().length != 0;
    }

    int cachedEventCount() {
        return this.state.size();
    }

    static final class CacheState<T> extends LinkedArrayList implements Subscriber<T> {
        static final ReplaySubscription[] EMPTY = new ReplaySubscription[0];
        static final ReplaySubscription[] TERMINATED = new ReplaySubscription[0];
        final AtomicReference<Subscription> connection;
        volatile boolean isConnected;
        final Flowable<? extends T> source;
        boolean sourceDone;
        final AtomicReference<ReplaySubscription<T>[]> subscribers;

        CacheState(Flowable<? extends T> flowable, int i) {
            super(i);
            this.connection = new AtomicReference<>();
            this.source = flowable;
            this.subscribers = new AtomicReference<>(EMPTY);
        }

        public void addChild(ReplaySubscription<T> replaySubscription) {
            ReplaySubscription<T>[] replaySubscriptionArr;
            ReplaySubscription[] replaySubscriptionArr2;
            do {
                replaySubscriptionArr = this.subscribers.get();
                if (replaySubscriptionArr == TERMINATED) {
                    return;
                }
                int length = replaySubscriptionArr.length;
                replaySubscriptionArr2 = new ReplaySubscription[length + 1];
                System.arraycopy(replaySubscriptionArr, 0, replaySubscriptionArr2, 0, length);
                replaySubscriptionArr2[length] = replaySubscription;
            } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m47m(this.subscribers, replaySubscriptionArr, replaySubscriptionArr2));
        }

        public void removeChild(ReplaySubscription<T> replaySubscription) {
            ReplaySubscription<T>[] replaySubscriptionArr;
            ReplaySubscription[] replaySubscriptionArr2;
            do {
                replaySubscriptionArr = this.subscribers.get();
                int length = replaySubscriptionArr.length;
                if (length == 0) {
                    return;
                }
                int i = 0;
                while (true) {
                    if (i >= length) {
                        i = -1;
                        break;
                    } else if (replaySubscriptionArr[i].equals(replaySubscription)) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (i < 0 || length == 1) {
                    return;
                }
                replaySubscriptionArr2 = new ReplaySubscription[length - 1];
                System.arraycopy(replaySubscriptionArr, 0, replaySubscriptionArr2, 0, i);
                System.arraycopy(replaySubscriptionArr, i + 1, replaySubscriptionArr2, i, (length - i) - 1);
            } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m47m(this.subscribers, replaySubscriptionArr, replaySubscriptionArr2));
        }

        @Override // org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this.connection, subscription)) {
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void connect() {
            this.source.subscribe(this);
            this.isConnected = true;
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.sourceDone) {
                return;
            }
            add(NotificationLite.next(t));
            for (ReplaySubscription<T> replaySubscription : this.subscribers.get()) {
                replaySubscription.replay();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (!this.sourceDone) {
                this.sourceDone = true;
                add(NotificationLite.error(th));
                SubscriptionHelper.cancel(this.connection);
                for (ReplaySubscription<T> replaySubscription : this.subscribers.getAndSet(TERMINATED)) {
                    replaySubscription.replay();
                }
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.sourceDone) {
                return;
            }
            this.sourceDone = true;
            add(NotificationLite.complete());
            SubscriptionHelper.cancel(this.connection);
            for (ReplaySubscription<T> replaySubscription : this.subscribers.getAndSet(TERMINATED)) {
                replaySubscription.replay();
            }
        }
    }

    static final class ReplaySubscription<T> extends AtomicInteger implements Subscription {
        private static final long CANCELLED = -1;
        private static final long serialVersionUID = -2557562030197141021L;
        final Subscriber<? super T> child;
        Object[] currentBuffer;
        int currentIndexInBuffer;
        int index;
        final AtomicLong requested = new AtomicLong();
        final CacheState<T> state;

        ReplaySubscription(Subscriber<? super T> subscriber, CacheState<T> cacheState) {
            this.child = subscriber;
            this.state = cacheState;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            long j2;
            if (SubscriptionHelper.validate(j)) {
                do {
                    j2 = this.requested.get();
                    if (j2 == -1) {
                        return;
                    }
                } while (!this.requested.compareAndSet(j2, BackpressureHelper.addCap(j2, j)));
                replay();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.requested.getAndSet(-1L) != -1) {
                this.state.removeChild(this);
            }
        }

        public void replay() {
            if (getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super T> subscriber = this.child;
            AtomicLong atomicLong = this.requested;
            int i = 1;
            int iAddAndGet = 1;
            while (true) {
                long j = atomicLong.get();
                if (j < 0) {
                    return;
                }
                int size = this.state.size();
                if (size != 0) {
                    Object[] objArrHead = this.currentBuffer;
                    if (objArrHead == null) {
                        objArrHead = this.state.head();
                        this.currentBuffer = objArrHead;
                    }
                    int length = objArrHead.length - i;
                    int i2 = this.index;
                    int i3 = this.currentIndexInBuffer;
                    int i4 = 0;
                    while (i2 < size && j > 0) {
                        if (atomicLong.get() == -1) {
                            return;
                        }
                        if (i3 == length) {
                            objArrHead = (Object[]) objArrHead[length];
                            i3 = 0;
                        }
                        if (NotificationLite.accept(objArrHead[i3], subscriber)) {
                            return;
                        }
                        i3++;
                        i2++;
                        j--;
                        i4++;
                    }
                    if (atomicLong.get() == -1) {
                        return;
                    }
                    if (j == 0) {
                        Object obj = objArrHead[i3];
                        if (NotificationLite.isComplete(obj)) {
                            subscriber.onComplete();
                            return;
                        } else if (NotificationLite.isError(obj)) {
                            subscriber.onError(NotificationLite.getError(obj));
                            return;
                        }
                    }
                    if (i4 != 0) {
                        BackpressureHelper.producedCancel(atomicLong, i4);
                    }
                    this.index = i2;
                    this.currentIndexInBuffer = i3;
                    this.currentBuffer = objArrHead;
                }
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                } else {
                    i = 1;
                }
            }
        }
    }
}
