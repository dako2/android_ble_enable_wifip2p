package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.conn.tsccm;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.util.Date;
import java.util.concurrent.locks.Condition;

@Deprecated
/* loaded from: classes2.dex */
public class WaitingThread {
    private boolean aborted;
    private final Condition cond;
    private final RouteSpecificPool pool;
    private Thread waiter;

    public WaitingThread(Condition condition, RouteSpecificPool routeSpecificPool) {
        Args.notNull(condition, "Condition");
        this.cond = condition;
        this.pool = routeSpecificPool;
    }

    public final Condition getCondition() {
        return this.cond;
    }

    public final RouteSpecificPool getPool() {
        return this.pool;
    }

    public final Thread getThread() {
        return this.waiter;
    }

    public boolean await(Date date) throws InterruptedException {
        boolean zAwaitUntil;
        if (this.waiter != null) {
            throw new IllegalStateException("A thread is already waiting on this object.\ncaller: " + Thread.currentThread() + "\nwaiter: " + this.waiter);
        }
        if (this.aborted) {
            throw new InterruptedException("Operation interrupted");
        }
        this.waiter = Thread.currentThread();
        try {
            if (date != null) {
                zAwaitUntil = this.cond.awaitUntil(date);
            } else {
                this.cond.await();
                zAwaitUntil = true;
            }
            if (this.aborted) {
                throw new InterruptedException("Operation interrupted");
            }
            return zAwaitUntil;
        } finally {
            this.waiter = null;
        }
    }

    public void wakeup() {
        if (this.waiter == null) {
            throw new IllegalStateException("Nobody waiting on this object.");
        }
        this.cond.signalAll();
    }

    public void interrupt() {
        this.aborted = true;
        this.cond.signalAll();
    }
}
