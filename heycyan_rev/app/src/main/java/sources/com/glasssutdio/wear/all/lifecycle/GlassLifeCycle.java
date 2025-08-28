package com.glasssutdio.wear.all.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.ActivityHelper;
import com.glasssutdio.wear.ble.glass.DeviceReconnect;
import com.glasssutdio.wear.ble.glass.thread.ThreadManager;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.SimpleKeyReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.BatteryRsp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlassLifeCycle.kt */
@Metadata(m606d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u0014\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0010H\u0016J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0017\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0019"}, m607d2 = {"Lcom/glasssutdio/wear/all/lifecycle/GlassLifeCycle;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "()V", "mCount", "", "getMCount", "()I", "setMCount", "(I)V", "isBand", "", "onActivityCreated", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "StaticParam", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class GlassLifeCycle implements Application.ActivityLifecycleCallbacks {

    /* renamed from: StaticParam, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static boolean isForeground;
    private int mCount;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(outState, "outState");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public final int getMCount() {
        return this.mCount;
    }

    public final void setMCount(int i) {
        this.mCount = i;
    }

    /* compiled from: GlassLifeCycle.kt */
    @Metadata(m606d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m607d2 = {"Lcom/glasssutdio/wear/all/lifecycle/GlassLifeCycle$StaticParam;", "", "()V", "isForeground", "", "()Z", "setForeground", "(Z)V", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    /* renamed from: com.glasssutdio.wear.all.lifecycle.GlassLifeCycle$StaticParam, reason: from kotlin metadata */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isForeground() {
            return GlassLifeCycle.isForeground;
        }

        public final void setForeground(boolean z) {
            GlassLifeCycle.isForeground = z;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ActivityHelper.INSTANCE.addActivity(activity);
        int i = this.mCount + 1;
        this.mCount = i;
        if (i == 1) {
            isForeground = true;
            if (BleOperateManager.getInstance().isConnected()) {
                CommandHandle.getInstance().executeReqCmd(new SimpleKeyReq((byte) 3), new ICommandResponse() { // from class: com.glasssutdio.wear.all.lifecycle.GlassLifeCycle$$ExternalSyntheticLambda0
                    @Override // com.oudmon.ble.base.communication.ICommandResponse
                    public final void onDataResponse(BaseRspCmd baseRspCmd) {
                        GlassLifeCycle.onActivityCreated$lambda$0((BatteryRsp) baseRspCmd);
                    }
                });
            }
            Log.i("QcLifeCycle", "后台到前台----当前是前台");
            if (BleOperateManager.getInstance().isConnected()) {
                return;
            }
            BleOperateManager.getInstance().setReConnectMac(UserConfig.INSTANCE.getInstance().getDeviceAddress());
            DeviceReconnect.INSTANCE.getGetInstance().connectWithScanValidation(UserConfig.INSTANCE.getInstance().getDeviceAddress());
            ThreadManager.getInstance().reSetLastConnectTime(3);
            ThreadManager.getInstance().wakeUpNotWait();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onActivityCreated$lambda$0(BatteryRsp batteryRsp) {
        UserConfig.INSTANCE.getInstance().setBattery(batteryRsp.getBatteryValue());
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        int i = this.mCount - 1;
        this.mCount = i;
        if (i == 0) {
            isForeground = false;
            Log.i("QcLifeCycle", "前台到后台----当前是后台");
            ThreadManager.getInstance().setSleepMin();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ActivityHelper.INSTANCE.removeActivity(activity);
    }

    private final boolean isBand() {
        return !TextUtils.isEmpty(UserConfig.INSTANCE.getInstance().getDeviceAddress());
    }
}
