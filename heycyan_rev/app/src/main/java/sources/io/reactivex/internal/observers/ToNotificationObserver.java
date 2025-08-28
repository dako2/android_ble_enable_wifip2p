package io.reactivex.internal.observers;

import io.reactivex.Notification;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;

/* loaded from: classes2.dex */
public final class ToNotificationObserver<T> implements Observer<T> {
    final Consumer<? super Notification<Object>> consumer;

    /* renamed from: s */
    Disposable f578s;

    public ToNotificationObserver(Consumer<? super Notification<Object>> consumer) {
        this.consumer = consumer;
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.f578s, disposable)) {
            this.f578s = disposable;
        }
    }

    @Override // io.reactivex.Observer
    public void onNext(T t) {
        if (t == null) {
            this.f578s.dispose();
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            return;
        }
        try {
            this.consumer.accept(Notification.createOnNext(t));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            this.f578s.dispose();
            onError(th);
        }
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        try {
            this.consumer.accept(Notification.createOnError(th));
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            RxJavaPlugins.onError(new CompositeException(th, th2));
        }
    }

    @Override // io.reactivex.Observer
    public void onComplete() {
        try {
            this.consumer.accept(Notification.createOnComplete());
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
        }
    }
}
