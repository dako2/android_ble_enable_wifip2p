package io.reactivex.internal.operators.flowable;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.subscribers.BlockingSubscriber;
import io.reactivex.internal.subscribers.LambdaSubscriber;
import io.reactivex.internal.util.BlockingHelper;
import io.reactivex.internal.util.BlockingIgnoringReceiver;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import java.util.concurrent.LinkedBlockingQueue;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

/* loaded from: classes2.dex */
public final class FlowableBlockingSubscribe {
    private FlowableBlockingSubscribe() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> void subscribe(Publisher<? extends T> publisher, Subscriber<? super T> subscriber) {
        Object objPoll;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        BlockingSubscriber blockingSubscriber = new BlockingSubscriber(linkedBlockingQueue);
        publisher.subscribe(blockingSubscriber);
        do {
            try {
                if (blockingSubscriber.isCancelled()) {
                    return;
                }
                objPoll = linkedBlockingQueue.poll();
                if (objPoll == null) {
                    if (blockingSubscriber.isCancelled()) {
                        return;
                    } else {
                        objPoll = linkedBlockingQueue.take();
                    }
                }
                if (blockingSubscriber.isCancelled() || publisher == BlockingSubscriber.TERMINATED) {
                    return;
                }
            } catch (InterruptedException e) {
                blockingSubscriber.cancel();
                subscriber.onError(e);
                return;
            }
        } while (!NotificationLite.acceptFull(objPoll, subscriber));
    }

    public static <T> void subscribe(Publisher<? extends T> publisher) {
        BlockingIgnoringReceiver blockingIgnoringReceiver = new BlockingIgnoringReceiver();
        LambdaSubscriber lambdaSubscriber = new LambdaSubscriber(Functions.emptyConsumer(), blockingIgnoringReceiver, blockingIgnoringReceiver, Functions.REQUEST_MAX);
        publisher.subscribe(lambdaSubscriber);
        BlockingHelper.awaitForComplete(blockingIgnoringReceiver, lambdaSubscriber);
        Throwable th = blockingIgnoringReceiver.error;
        if (th != null) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    public static <T> void subscribe(Publisher<? extends T> publisher, Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action) {
        subscribe(publisher, new LambdaSubscriber(consumer, consumer2, action, Functions.REQUEST_MAX));
    }
}
