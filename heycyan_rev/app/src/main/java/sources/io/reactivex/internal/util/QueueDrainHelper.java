package io.reactivex.internal.util;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public final class QueueDrainHelper {
    static final long COMPLETED_MASK = Long.MIN_VALUE;
    static final long REQUESTED_MASK = Long.MAX_VALUE;

    private QueueDrainHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static <T, U> void drainLoop(SimpleQueue<T> simpleQueue, Subscriber<? super U> subscriber, boolean z, QueueDrain<T, U> queueDrain) {
        int iLeave = 1;
        while (!checkTerminated(queueDrain.done(), simpleQueue.isEmpty(), subscriber, z, simpleQueue, queueDrain)) {
            long jRequested = queueDrain.requested();
            long j = 0;
            while (j != jRequested) {
                boolean zDone = queueDrain.done();
                try {
                    T tPoll = simpleQueue.poll();
                    boolean z2 = tPoll == null;
                    if (checkTerminated(zDone, z2, subscriber, z, simpleQueue, queueDrain)) {
                        return;
                    }
                    if (z2) {
                        break;
                    } else if (queueDrain.accept(subscriber, tPoll)) {
                        j++;
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    subscriber.onError(th);
                    return;
                }
            }
            if (j != 0 && jRequested != Long.MAX_VALUE) {
                queueDrain.produced(j);
            }
            iLeave = queueDrain.leave(-iLeave);
            if (iLeave == 0) {
                return;
            }
        }
    }

    public static <T, U> void drainMaxLoop(SimpleQueue<T> simpleQueue, Subscriber<? super U> subscriber, boolean z, Disposable disposable, QueueDrain<T, U> queueDrain) {
        int iLeave = 1;
        while (true) {
            boolean zDone = queueDrain.done();
            try {
                T tPoll = simpleQueue.poll();
                boolean z2 = tPoll == null;
                if (checkTerminated(zDone, z2, subscriber, z, simpleQueue, queueDrain)) {
                    if (disposable != null) {
                        disposable.dispose();
                        return;
                    }
                    return;
                } else if (!z2) {
                    long jRequested = queueDrain.requested();
                    if (jRequested != 0) {
                        if (queueDrain.accept(subscriber, tPoll) && jRequested != Long.MAX_VALUE) {
                            queueDrain.produced(1L);
                        }
                    } else {
                        simpleQueue.clear();
                        if (disposable != null) {
                            disposable.dispose();
                        }
                        subscriber.onError(new MissingBackpressureException("Could not emit value due to lack of requests."));
                        return;
                    }
                } else {
                    iLeave = queueDrain.leave(-iLeave);
                    if (iLeave == 0) {
                        return;
                    }
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                subscriber.onError(th);
                return;
            }
        }
    }

    public static <T, U> boolean checkTerminated(boolean z, boolean z2, Subscriber<?> subscriber, boolean z3, SimpleQueue<?> simpleQueue, QueueDrain<T, U> queueDrain) {
        if (queueDrain.cancelled()) {
            simpleQueue.clear();
            return true;
        }
        if (!z) {
            return false;
        }
        if (z3) {
            if (!z2) {
                return false;
            }
            Throwable thError = queueDrain.error();
            if (thError != null) {
                subscriber.onError(thError);
            } else {
                subscriber.onComplete();
            }
            return true;
        }
        Throwable thError2 = queueDrain.error();
        if (thError2 != null) {
            simpleQueue.clear();
            subscriber.onError(thError2);
            return true;
        }
        if (!z2) {
            return false;
        }
        subscriber.onComplete();
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0033, code lost:
    
        r1 = r15.leave(-r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0038, code lost:
    
        if (r1 != 0) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003a, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static <T, U> void drainLoop(SimpleQueue<T> simpleQueue, Observer<? super U> observer, boolean z, Disposable disposable, ObservableQueueDrain<T, U> observableQueueDrain) {
        int iLeave = 1;
        while (!checkTerminated(observableQueueDrain.done(), simpleQueue.isEmpty(), observer, z, simpleQueue, disposable, observableQueueDrain)) {
            while (true) {
                boolean zDone = observableQueueDrain.done();
                try {
                    T tPoll = simpleQueue.poll();
                    boolean z2 = tPoll == null;
                    if (checkTerminated(zDone, z2, observer, z, simpleQueue, disposable, observableQueueDrain)) {
                        return;
                    }
                    if (z2) {
                        break;
                    } else {
                        observableQueueDrain.accept(observer, tPoll);
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    observer.onError(th);
                    return;
                }
            }
        }
    }

    public static <T, U> boolean checkTerminated(boolean z, boolean z2, Observer<?> observer, boolean z3, SimpleQueue<?> simpleQueue, Disposable disposable, ObservableQueueDrain<T, U> observableQueueDrain) {
        if (observableQueueDrain.cancelled()) {
            simpleQueue.clear();
            disposable.dispose();
            return true;
        }
        if (!z) {
            return false;
        }
        if (z3) {
            if (!z2) {
                return false;
            }
            disposable.dispose();
            Throwable thError = observableQueueDrain.error();
            if (thError != null) {
                observer.onError(thError);
            } else {
                observer.onComplete();
            }
            return true;
        }
        Throwable thError2 = observableQueueDrain.error();
        if (thError2 != null) {
            simpleQueue.clear();
            disposable.dispose();
            observer.onError(thError2);
            return true;
        }
        if (!z2) {
            return false;
        }
        disposable.dispose();
        observer.onComplete();
        return true;
    }

    public static <T> SimpleQueue<T> createQueue(int i) {
        if (i < 0) {
            return new SpscLinkedArrayQueue(-i);
        }
        return new SpscArrayQueue(i);
    }

    public static void request(Subscription subscription, int i) {
        subscription.request(i < 0 ? Long.MAX_VALUE : i);
    }

    public static <T> boolean postCompleteRequest(long j, Subscriber<? super T> subscriber, Queue<T> queue, AtomicLong atomicLong, BooleanSupplier booleanSupplier) {
        long j2;
        do {
            j2 = atomicLong.get();
        } while (!atomicLong.compareAndSet(j2, BackpressureHelper.addCap(Long.MAX_VALUE & j2, j) | (j2 & Long.MIN_VALUE)));
        if (j2 != Long.MIN_VALUE) {
            return false;
        }
        postCompleteDrain(j | Long.MIN_VALUE, subscriber, queue, atomicLong, booleanSupplier);
        return true;
    }

    static boolean isCancelled(BooleanSupplier booleanSupplier) {
        try {
            return booleanSupplier.getAsBoolean();
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            return true;
        }
    }

    static <T> boolean postCompleteDrain(long j, Subscriber<? super T> subscriber, Queue<T> queue, AtomicLong atomicLong, BooleanSupplier booleanSupplier) {
        long j2 = j & Long.MIN_VALUE;
        while (true) {
            if (j2 != j) {
                if (isCancelled(booleanSupplier)) {
                    return true;
                }
                T tPoll = queue.poll();
                if (tPoll == null) {
                    subscriber.onComplete();
                    return true;
                }
                subscriber.onNext(tPoll);
                j2++;
            } else {
                if (isCancelled(booleanSupplier)) {
                    return true;
                }
                if (queue.isEmpty()) {
                    subscriber.onComplete();
                    return true;
                }
                j = atomicLong.get();
                if (j == j2) {
                    long jAddAndGet = atomicLong.addAndGet(-(j2 & Long.MAX_VALUE));
                    if ((Long.MAX_VALUE & jAddAndGet) == 0) {
                        return false;
                    }
                    j = jAddAndGet;
                    j2 = jAddAndGet & Long.MIN_VALUE;
                } else {
                    continue;
                }
            }
        }
    }

    public static <T> void postComplete(Subscriber<? super T> subscriber, Queue<T> queue, AtomicLong atomicLong, BooleanSupplier booleanSupplier) {
        long j;
        long j2;
        if (queue.isEmpty()) {
            subscriber.onComplete();
            return;
        }
        if (postCompleteDrain(atomicLong.get(), subscriber, queue, atomicLong, booleanSupplier)) {
            return;
        }
        do {
            j = atomicLong.get();
            if ((j & Long.MIN_VALUE) != 0) {
                return;
            } else {
                j2 = j | Long.MIN_VALUE;
            }
        } while (!atomicLong.compareAndSet(j, j2));
        if (j != 0) {
            postCompleteDrain(j2, subscriber, queue, atomicLong, booleanSupplier);
        }
    }
}
