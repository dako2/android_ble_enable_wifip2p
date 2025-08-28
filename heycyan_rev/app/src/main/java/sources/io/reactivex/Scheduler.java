package io.reactivex;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public abstract class Scheduler {
    static final long CLOCK_DRIFT_TOLERANCE_NANOSECONDS = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());

    public abstract Worker createWorker();

    public void shutdown() {
    }

    public void start() {
    }

    public static long clockDriftTolerance() {
        return CLOCK_DRIFT_TOLERANCE_NANOSECONDS;
    }

    public long now(TimeUnit timeUnit) {
        return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public Disposable scheduleDirect(Runnable runnable) {
        return scheduleDirect(runnable, 0L, TimeUnit.NANOSECONDS);
    }

    public Disposable scheduleDirect(Runnable runnable, long j, TimeUnit timeUnit) {
        final Worker workerCreateWorker = createWorker();
        final Runnable runnableOnSchedule = RxJavaPlugins.onSchedule(runnable);
        workerCreateWorker.schedule(new Runnable() { // from class: io.reactivex.Scheduler.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    runnableOnSchedule.run();
                } finally {
                    workerCreateWorker.dispose();
                }
            }
        }, j, timeUnit);
        return workerCreateWorker;
    }

    public Disposable schedulePeriodicallyDirect(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        Worker workerCreateWorker = createWorker();
        PeriodicDirectTask periodicDirectTask = new PeriodicDirectTask(RxJavaPlugins.onSchedule(runnable), workerCreateWorker);
        Disposable disposableSchedulePeriodically = workerCreateWorker.schedulePeriodically(periodicDirectTask, j, j2, timeUnit);
        return disposableSchedulePeriodically == EmptyDisposable.INSTANCE ? disposableSchedulePeriodically : periodicDirectTask;
    }

    public static abstract class Worker implements Disposable {
        public abstract Disposable schedule(Runnable runnable, long j, TimeUnit timeUnit);

        public Disposable schedule(Runnable runnable) {
            return schedule(runnable, 0L, TimeUnit.NANOSECONDS);
        }

        public Disposable schedulePeriodically(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            SequentialDisposable sequentialDisposable = new SequentialDisposable();
            SequentialDisposable sequentialDisposable2 = new SequentialDisposable(sequentialDisposable);
            Runnable runnableOnSchedule = RxJavaPlugins.onSchedule(runnable);
            long nanos = timeUnit.toNanos(j2);
            long jNow = now(TimeUnit.NANOSECONDS);
            Disposable disposableSchedule = schedule(new PeriodicTask(jNow + timeUnit.toNanos(j), runnableOnSchedule, jNow, sequentialDisposable2, nanos), j, timeUnit);
            if (disposableSchedule == EmptyDisposable.INSTANCE) {
                return disposableSchedule;
            }
            sequentialDisposable.replace(disposableSchedule);
            return sequentialDisposable2;
        }

        public long now(TimeUnit timeUnit) {
            return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        final class PeriodicTask implements Runnable {
            long count;
            final Runnable decoratedRun;
            long lastNowNanoseconds;
            final long periodInNanoseconds;

            /* renamed from: sd */
            final SequentialDisposable f556sd;
            long startInNanoseconds;

            PeriodicTask(long j, Runnable runnable, long j2, SequentialDisposable sequentialDisposable, long j3) {
                this.decoratedRun = runnable;
                this.f556sd = sequentialDisposable;
                this.periodInNanoseconds = j3;
                this.lastNowNanoseconds = j2;
                this.startInNanoseconds = j;
            }

            @Override // java.lang.Runnable
            public void run() {
                long j;
                this.decoratedRun.run();
                if (this.f556sd.isDisposed()) {
                    return;
                }
                long jNow = Worker.this.now(TimeUnit.NANOSECONDS);
                long j2 = Scheduler.CLOCK_DRIFT_TOLERANCE_NANOSECONDS + jNow;
                long j3 = this.lastNowNanoseconds;
                if (j2 < j3 || jNow >= j3 + this.periodInNanoseconds + Scheduler.CLOCK_DRIFT_TOLERANCE_NANOSECONDS) {
                    long j4 = this.periodInNanoseconds;
                    long j5 = jNow + j4;
                    long j6 = this.count + 1;
                    this.count = j6;
                    this.startInNanoseconds = j5 - (j4 * j6);
                    j = j5;
                } else {
                    long j7 = this.startInNanoseconds;
                    long j8 = this.count + 1;
                    this.count = j8;
                    j = j7 + (j8 * this.periodInNanoseconds);
                }
                this.lastNowNanoseconds = jNow;
                this.f556sd.replace(Worker.this.schedule(this, j - jNow, TimeUnit.NANOSECONDS));
            }
        }
    }

    static class PeriodicDirectTask implements Runnable, Disposable {
        volatile boolean disposed;
        final Runnable run;
        final Worker worker;

        PeriodicDirectTask(Runnable runnable, Worker worker) {
            this.run = runnable;
            this.worker = worker;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.disposed) {
                return;
            }
            try {
                this.run.run();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.worker.dispose();
                throw ExceptionHelper.wrapOrThrow(th);
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.disposed = true;
            this.worker.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }
    }
}
