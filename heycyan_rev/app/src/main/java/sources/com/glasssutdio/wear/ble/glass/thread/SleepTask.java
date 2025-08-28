package com.glasssutdio.wear.ble.glass.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
public class SleepTask implements IDo {
    private Condition mCondition;
    private Lock mLock;

    public SleepTask(Lock lock, Condition condition) {
        this.mLock = lock;
        this.mCondition = condition;
    }

    @Override // com.glasssutdio.wear.ble.glass.thread.IDo
    public void iDo() {
        Lock lock = this.mLock;
        if (lock == null || this.mCondition == null) {
            return;
        }
        lock.lock();
        try {
            try {
                this.mCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            this.mLock.unlock();
        }
    }
}
