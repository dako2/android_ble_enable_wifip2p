package com.glasssutdio.wear.ble.glass.thread;

import android.os.SystemClock;
import android.text.TextUtils;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.GlassApplication;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.DateUtil;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.qc_utils.bluetooth.BluetoothUtils;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
public class WorkThread extends Thread {
    private static final int SLEEP_INTERVAL_MAX = 60;
    private static final int SLEEP_INTERVAL_MIN = 30;
    private long lastConnectTime;
    private Condition mCondition;
    private Lock mLock;
    private AtomicInteger sleepTime;

    public WorkThread(String name, Lock lock, Condition condition) {
        super(name);
        this.sleepTime = new AtomicInteger(30);
        this.mLock = lock;
        this.mCondition = condition;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (true) {
            try {
                if (!BluetoothUtils.isEnabledBluetooth(GlassApplication.INSTANCE.getCONTEXT()) || TextUtils.isEmpty(UserConfig.INSTANCE.getInstance().getDeviceAddress())) {
                    needLock();
                }
                if (BleOperateManager.getInstance().isConnected()) {
                    this.sleepTime.getAndSet(30);
                    this.lastConnectTime = 0L;
                    needLock();
                } else if (this.sleepTime.get() <= 60) {
                    SystemClock.sleep(this.sleepTime.get() * 1000);
                    if (BleOperateManager.getInstance().isConnected()) {
                        this.sleepTime.getAndSet(30);
                        this.lastConnectTime = 0L;
                        needLock();
                    }
                    this.sleepTime.incrementAndGet();
                    BleOperateManager.getInstance().setReConnectMac(UserConfig.INSTANCE.getInstance().getDeviceAddress());
                    BleOperateManager.getInstance().connectWithScan(UserConfig.INSTANCE.getInstance().getDeviceAddress());
                } else {
                    long unixTimestamp = new DateUtil().getUnixTimestamp();
                    XLog.m137i("5分钟" + (this.lastConnectTime <= unixTimestamp) + this.lastConnectTime + "---" + unixTimestamp);
                    if (this.lastConnectTime <= unixTimestamp) {
                        SystemClock.sleep(300000L);
                        this.lastConnectTime = unixTimestamp + 300;
                        BleOperateManager.getInstance().setReConnectMac(UserConfig.INSTANCE.getInstance().getDeviceAddress());
                        BleOperateManager.getInstance().connectWithScan(UserConfig.INSTANCE.getInstance().getDeviceAddress());
                    }
                    needLock();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void needWait(long seconds) {
        if (seconds > 0) {
            this.mLock.lock();
            try {
                try {
                    this.mCondition.await(seconds, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                this.mLock.unlock();
            }
        }
    }

    private void needLock() {
        if (this.mCondition == null) {
            return;
        }
        this.mLock.lock();
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

    public void wakeUp() {
        if (BleOperateManager.getInstance().isConnected()) {
            this.sleepTime.getAndSet(30);
            return;
        }
        try {
            try {
                this.mLock.lock();
                this.mCondition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            this.mLock.unlock();
        }
    }

    public void wakeUpNoSleep() {
        try {
            this.sleepTime.getAndSet(0);
            this.mLock.lock();
            this.mCondition.signalAll();
        } finally {
            this.mLock.unlock();
        }
    }

    public void setSleepTimeMin() {
        this.sleepTime.getAndSet(30);
    }

    public void setLastConnectTime(long lastConnectTime) {
        this.lastConnectTime = lastConnectTime;
    }
}
