package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.util.concurrent;

import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
abstract class InterruptibleTask<T> extends AtomicReference<Runnable> implements Runnable {
    private static final Runnable DONE;
    private static final Runnable INTERRUPTING;

    abstract void afterRanInterruptibly(@NullableDecl T t, @NullableDecl Throwable th);

    abstract boolean isDone();

    abstract T runInterruptibly() throws Exception;

    abstract String toPendingString();

    private static final class DoNothingRunnable implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
        }

        private DoNothingRunnable() {
        }
    }

    InterruptibleTask() {
    }

    static {
        DONE = new DoNothingRunnable();
        INTERRUPTING = new DoNothingRunnable();
    }

    @Override // java.lang.Runnable
    public final void run() {
        T tRunInterruptibly;
        Thread threadCurrentThread = Thread.currentThread();
        if (compareAndSet(null, threadCurrentThread)) {
            boolean zIsDone = isDone();
            if (zIsDone) {
                tRunInterruptibly = null;
            } else {
                try {
                    tRunInterruptibly = runInterruptibly();
                } catch (Throwable th) {
                    if (!compareAndSet(threadCurrentThread, DONE)) {
                        while (get() == INTERRUPTING) {
                            Thread.yield();
                        }
                    }
                    if (zIsDone) {
                        return;
                    }
                    afterRanInterruptibly(null, th);
                    return;
                }
            }
            if (!compareAndSet(threadCurrentThread, DONE)) {
                while (get() == INTERRUPTING) {
                    Thread.yield();
                }
            }
            if (zIsDone) {
                return;
            }
            afterRanInterruptibly(tRunInterruptibly, null);
        }
    }

    final void interruptTask() {
        Runnable runnable = get();
        if ((runnable instanceof Thread) && compareAndSet(runnable, INTERRUPTING)) {
            ((Thread) runnable).interrupt();
            set(DONE);
        }
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        String str;
        Runnable runnable = get();
        if (runnable == DONE) {
            str = "running=[DONE]";
        } else if (runnable == INTERRUPTING) {
            str = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            str = "running=[RUNNING ON " + ((Thread) runnable).getName() + "]";
        } else {
            str = "running=[NOT STARTED YET]";
        }
        return str + ", " + toPendingString();
    }
}
