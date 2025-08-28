package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.eventbus;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.eventbus.EventBus;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.config.CookieSpecs;
import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
public class AsyncEventBus extends EventBus {
    public AsyncEventBus(String str, Executor executor) {
        super(str, executor, Dispatcher.legacyAsync(), EventBus.LoggingHandler.INSTANCE);
    }

    public AsyncEventBus(Executor executor, SubscriberExceptionHandler subscriberExceptionHandler) {
        super(CookieSpecs.DEFAULT, executor, Dispatcher.legacyAsync(), subscriberExceptionHandler);
    }

    public AsyncEventBus(Executor executor) {
        super(CookieSpecs.DEFAULT, executor, Dispatcher.legacyAsync(), EventBus.LoggingHandler.INSTANCE);
    }
}
