package com.glasssutdio.wear.p003ai.spark;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class TimeoutTaskUtils {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);

    public static ScheduledFuture<?> schedule(Runnable task, long delay, TimeUnit unit) {
        return scheduler.schedule(task, delay, unit);
    }

    public static <T> ScheduledFuture<T> schedule(Callable<T> task, long delay, TimeUnit unit) {
        return scheduler.schedule(task, delay, unit);
    }

    public static ScheduledFuture<?> scheduleAt(Runnable task, long timestamp) {
        long jCurrentTimeMillis = timestamp - System.currentTimeMillis();
        if (jCurrentTimeMillis < 0) {
            jCurrentTimeMillis = 0;
        }
        return scheduler.schedule(task, jCurrentTimeMillis, TimeUnit.MILLISECONDS);
    }

    public static void shutdown() {
        scheduler.shutdown();
    }
}
