package io.reactivex.internal.schedulers;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
public final class SchedulerPoolFactory {
    public static final boolean PURGE_ENABLED;
    static final String PURGE_ENABLED_KEY = "rx2.purge-enabled";
    public static final int PURGE_PERIOD_SECONDS;
    static final String PURGE_PERIOD_SECONDS_KEY = "rx2.purge-period-seconds";
    static final AtomicReference<ScheduledExecutorService> PURGE_THREAD = new AtomicReference<>();
    static final Map<ScheduledThreadPoolExecutor, Object> POOLS = new ConcurrentHashMap();

    private SchedulerPoolFactory() {
        throw new IllegalStateException("No instances!");
    }

    static {
        int i;
        Properties properties = System.getProperties();
        boolean z = true;
        iIntValue = 1;
        int iIntValue = 1;
        if (properties.containsKey(PURGE_ENABLED_KEY)) {
            boolean z2 = Boolean.getBoolean(PURGE_ENABLED_KEY);
            if (z2 && properties.containsKey(PURGE_PERIOD_SECONDS_KEY)) {
                iIntValue = Integer.getInteger(PURGE_PERIOD_SECONDS_KEY, 1).intValue();
            }
            i = iIntValue;
            z = z2;
        } else {
            i = 1;
        }
        PURGE_ENABLED = z;
        PURGE_PERIOD_SECONDS = i;
        start();
    }

    public static void start() {
        while (true) {
            AtomicReference<ScheduledExecutorService> atomicReference = PURGE_THREAD;
            ScheduledExecutorService scheduledExecutorService = atomicReference.get();
            if (scheduledExecutorService != null && !scheduledExecutorService.isShutdown()) {
                return;
            }
            ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, new RxThreadFactory("RxSchedulerPurge"));
            if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m47m(atomicReference, scheduledExecutorService, scheduledExecutorServiceNewScheduledThreadPool)) {
                Runnable runnable = new Runnable() { // from class: io.reactivex.internal.schedulers.SchedulerPoolFactory.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            Iterator it = new ArrayList(SchedulerPoolFactory.POOLS.keySet()).iterator();
                            while (it.hasNext()) {
                                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) it.next();
                                if (scheduledThreadPoolExecutor.isShutdown()) {
                                    SchedulerPoolFactory.POOLS.remove(scheduledThreadPoolExecutor);
                                } else {
                                    scheduledThreadPoolExecutor.purge();
                                }
                            }
                        } catch (Throwable th) {
                            RxJavaPlugins.onError(th);
                        }
                    }
                };
                int i = PURGE_PERIOD_SECONDS;
                scheduledExecutorServiceNewScheduledThreadPool.scheduleAtFixedRate(runnable, i, i, TimeUnit.SECONDS);
                return;
            }
            scheduledExecutorServiceNewScheduledThreadPool.shutdownNow();
        }
    }

    public static void shutdown() {
        PURGE_THREAD.get().shutdownNow();
        POOLS.clear();
    }

    public static ScheduledExecutorService create(ThreadFactory threadFactory) {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        if (scheduledExecutorServiceNewScheduledThreadPool instanceof ScheduledThreadPoolExecutor) {
            POOLS.put((ScheduledThreadPoolExecutor) scheduledExecutorServiceNewScheduledThreadPool, scheduledExecutorServiceNewScheduledThreadPool);
        }
        return scheduledExecutorServiceNewScheduledThreadPool;
    }
}
