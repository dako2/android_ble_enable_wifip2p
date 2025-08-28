package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.subscribers.DefaultSubscriber;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.reactivestreams.Publisher;

/* loaded from: classes2.dex */
public final class BlockingFlowableMostRecent<T> implements Iterable<T> {
    final T initialValue;
    final Publisher<? extends T> source;

    public BlockingFlowableMostRecent(Publisher<? extends T> publisher, T t) {
        this.source = publisher;
        this.initialValue = t;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        MostRecentSubscriber mostRecentSubscriber = new MostRecentSubscriber(this.initialValue);
        this.source.subscribe(mostRecentSubscriber);
        return mostRecentSubscriber.getIterable();
    }

    static final class MostRecentSubscriber<T> extends DefaultSubscriber<T> {
        volatile Object value;

        MostRecentSubscriber(T t) {
            this.value = NotificationLite.next(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.value = NotificationLite.complete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.value = NotificationLite.error(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.value = NotificationLite.next(t);
        }

        public Iterator<T> getIterable() {
            return new Iterator<T>() { // from class: io.reactivex.internal.operators.flowable.BlockingFlowableMostRecent.MostRecentSubscriber.1
                private Object buf;

                @Override // java.util.Iterator
                public boolean hasNext() {
                    this.buf = MostRecentSubscriber.this.value;
                    return !NotificationLite.isComplete(r0);
                }

                @Override // java.util.Iterator
                public T next() {
                    try {
                        if (this.buf == null) {
                            this.buf = MostRecentSubscriber.this.value;
                        }
                        if (NotificationLite.isComplete(this.buf)) {
                            throw new NoSuchElementException();
                        }
                        if (NotificationLite.isError(this.buf)) {
                            throw ExceptionHelper.wrapOrThrow(NotificationLite.getError(this.buf));
                        }
                        return (T) NotificationLite.getValue(this.buf);
                    } finally {
                        this.buf = null;
                    }
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException("Read only iterator");
                }
            };
        }
    }
}
