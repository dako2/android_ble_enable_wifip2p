package com.glasssutdio.wear.ble.glass.thread;

/* loaded from: classes.dex */
public class WakeUpTask implements IDo {
    private boolean noWait;
    private WorkThread workThread;

    public WakeUpTask(WorkThread workThread) {
        this.workThread = workThread;
    }

    @Override // com.glasssutdio.wear.ble.glass.thread.IDo
    public void iDo() {
        if (this.noWait) {
            this.workThread.wakeUpNoSleep();
        } else {
            this.workThread.wakeUp();
        }
    }

    public boolean isNoWait() {
        return this.noWait;
    }

    public void setNoWait(boolean noWait) {
        this.noWait = noWait;
    }
}
