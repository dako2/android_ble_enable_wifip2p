package io.reactivex.internal.schedulers;

import io.reactivex.Scheduler;

/* loaded from: classes2.dex */
public final class NewThreadScheduler extends Scheduler {
    private static final NewThreadScheduler INSTANCE = new NewThreadScheduler();
    private static final String THREAD_NAME_PREFIX = "RxNewThreadScheduler";
    private static final String KEY_NEWTHREAD_PRIORITY = "rx2.newthread-priority";
    private static final RxThreadFactory THREAD_FACTORY = new RxThreadFactory(THREAD_NAME_PREFIX, Math.max(1, Math.min(10, Integer.getInteger(KEY_NEWTHREAD_PRIORITY, 5).intValue())));

    public static NewThreadScheduler instance() {
        return INSTANCE;
    }

    private NewThreadScheduler() {
    }

    @Override // io.reactivex.Scheduler
    public Scheduler.Worker createWorker() {
        return new NewThreadWorker(THREAD_FACTORY);
    }
}
