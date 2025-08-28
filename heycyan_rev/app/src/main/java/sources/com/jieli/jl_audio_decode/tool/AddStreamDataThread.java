package com.jieli.jl_audio_decode.tool;

import com.jieli.jl_audio_decode.callback.OnStateCallback;
import com.jieli.jl_audio_decode.callback.OnThreadFinishListener;
import com.jieli.jl_audio_decode.constant.ErrorCode;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes2.dex */
public class AddStreamDataThread extends OperationThread {
    private final LinkedBlockingQueue<byte[]> dataQueue;
    private final int dataType;
    private volatile boolean isLocked;
    private volatile boolean isRunning;

    @Override // com.jieli.jl_audio_decode.tool.OperationThread
    public int operation() {
        return 3;
    }

    public AddStreamDataThread(BaseManager baseManager, int i, OnStateCallback onStateCallback, OnThreadFinishListener onThreadFinishListener) {
        super("AddStreamDataThread", baseManager, onStateCallback, onThreadFinishListener);
        this.dataQueue = new LinkedBlockingQueue<>();
        this.dataType = i;
    }

    public void addStreamData(byte[] bArr) throws InterruptedException {
        if (bArr == null) {
            return;
        }
        try {
            this.dataQueue.put(bArr);
            if (this.dataQueue.size() == 1) {
                unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override // java.lang.Thread
    public synchronized void start() {
        this.isRunning = true;
        super.start();
    }

    public synchronized void stopThread() {
        this.isRunning = false;
        unlock();
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0030, code lost:
    
        callbackError(com.jieli.jl_audio_decode.constant.ErrorCode.ERR_OP_FAIL);
     */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void run() {
        if (this.manager != null) {
            synchronized (this.dataQueue) {
                while (true) {
                    if (!this.isRunning) {
                        break;
                    }
                    if (this.dataQueue.isEmpty()) {
                        lock();
                    } else {
                        byte[] bArrPoll = this.dataQueue.poll();
                        if (bArrPoll != null && bArrPoll.length != 0) {
                            try {
                                if (!this.manager.nativeAddAudioStream(this.dataType, bArrPoll)) {
                                    break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                callbackError(ErrorCode.ERR_IO_EXCEPTION, String.format(Locale.ENGLISH, "%s: %s", ErrorCode.getErrorMsg(ErrorCode.ERR_IO_EXCEPTION), e.getMessage()));
                                this.isRunning = false;
                            }
                        }
                    }
                }
            }
        } else {
            callbackError(-1);
        }
        this.isRunning = false;
        this.isLocked = false;
        this.dataQueue.clear();
        onThreadFinish();
    }

    private void lock() {
        if (this.isLocked) {
            return;
        }
        synchronized (this.dataQueue) {
            try {
                this.isLocked = true;
                this.dataQueue.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.isLocked = false;
        }
    }

    private void unlock() {
        if (this.isLocked) {
            synchronized (this.dataQueue) {
                try {
                    this.dataQueue.notifyAll();
                } catch (Exception e) {
                    e.printStackTrace();
                    this.isLocked = false;
                }
            }
        }
    }
}
