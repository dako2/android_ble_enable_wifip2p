package com.glasssutdio.wear.ble.receiver;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.GlassApplication;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.ble.glass.DeviceReconnect;
import com.glasssutdio.wear.ble.glass.thread.ThreadManager;
import com.glasssutdio.wear.bus.BluetoothEvent;
import com.glasssutdio.wear.bus.EventType;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import com.hjq.permissions.Permission;
import com.liulishuo.okdownload.DownloadTask;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.DeviceManager;
import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.resp.BaseResponse;
import com.oudmon.ble.base.communication.bigData.resp.SyncTimeResponse;
import com.oudmon.ble.base.scan.BleScannerHelper;
import com.oudmon.ble.base.util.AppUtil;
import com.oudmon.qc_utils.bluetooth.BluetoothUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

/* compiled from: BluetoothReceiver.kt */
@Metadata(m606d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0002J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0002J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\b\u0010\u0015\u001a\u00020\u0011H\u0002J\u0018\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m607d2 = {"Lcom/glasssutdio/wear/ble/receiver/BluetoothReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "bleOpen", "", "btDevice", "Landroid/bluetooth/BluetoothDevice;", "btReconnect", "", "classicBluetoothRunnable", "Ljava/lang/Runnable;", "connectRunnable", "mHandler", "Landroid/os/Handler;", "numConnect", "uiRunnable", "beginConnect", "", "delayTime", "connectAgain", "disConnectDevice", "onOffBle", "onReceive", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "reConnect", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class BluetoothReceiver extends BroadcastReceiver {
    private BluetoothDevice btDevice;
    private int btReconnect;
    private boolean bleOpen = true;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private int numConnect = 1;
    private Runnable classicBluetoothRunnable = new Runnable() { // from class: com.glasssutdio.wear.ble.receiver.BluetoothReceiver$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            BluetoothReceiver.classicBluetoothRunnable$lambda$1(this.f$0);
        }
    };
    private Runnable uiRunnable = new Runnable() { // from class: com.glasssutdio.wear.ble.receiver.BluetoothReceiver$$ExternalSyntheticLambda1
        @Override // java.lang.Runnable
        public final void run() throws SecurityException {
            BluetoothReceiver.uiRunnable$lambda$2();
        }
    };
    private final Runnable connectRunnable = new Runnable() { // from class: com.glasssutdio.wear.ble.receiver.BluetoothReceiver$$ExternalSyntheticLambda2
        @Override // java.lang.Runnable
        public final void run() {
            BluetoothReceiver.connectRunnable$lambda$4(this.f$0);
        }
    };

    private final void onOffBle() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onReceive$lambda$0(int i, SyncTimeResponse syncTimeResponse) {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        BluetoothDevice bluetoothDevice;
        BluetoothDevice bluetoothDevice2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        if (action != null) {
            switch (action.hashCode()) {
                case -2128145023:
                    if (!action.equals("android.intent.action.SCREEN_OFF")) {
                        return;
                    }
                    break;
                case -1980154005:
                    if (!action.equals("android.intent.action.BATTERY_OKAY")) {
                        return;
                    }
                    break;
                case -1530327060:
                    if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                        if (Build.VERSION.SDK_INT < 31 || ActivityCompat.checkSelfPermission(context, Permission.BLUETOOTH_CONNECT) == 0) {
                            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                            if (intExtra == 10) {
                                this.numConnect = 1;
                                this.bleOpen = false;
                                onOffBle();
                                BleOperateManager.getInstance().setBluetoothTurnOff(false);
                                XLog.m137i("蓝牙关闭了 --> ");
                                disConnectDevice();
                                EventBus.getDefault().post(new BluetoothEvent(false));
                                return;
                            }
                            if (intExtra != 12) {
                                return;
                            }
                            XLog.m137i("蓝牙开启了 --> ");
                            BleOperateManager.getInstance().setBluetoothTurnOff(true);
                            this.bleOpen = true;
                            BleOperateManager.getInstance().setReConnectMac(UserConfig.INSTANCE.getInstance().getDeviceAddress());
                            DeviceReconnect.INSTANCE.getGetInstance().connectWithScanValidation(UserConfig.INSTANCE.getInstance().getDeviceAddress());
                            beginConnect(DownloadTask.Builder.DEFAULT_SYNC_BUFFER_INTERVAL_MILLIS);
                            this.mHandler.removeCallbacks(this.uiRunnable);
                            this.mHandler.post(this.uiRunnable);
                            if (UserConfig.INSTANCE.getInstance().getDeviceAddress().length() == 0) {
                                BleScannerHelper.getInstance().removeMacSystemBond(UserConfig.INSTANCE.getInstance().getClassicBluetoothMac());
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                case -1513032534:
                    if (!action.equals("android.intent.action.TIME_TICK")) {
                        return;
                    }
                    break;
                case -1454123155:
                    if (!action.equals("android.intent.action.SCREEN_ON")) {
                        return;
                    }
                    break;
                case -1172645946:
                    if (!action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                        return;
                    }
                    break;
                case -873536848:
                    if (!action.equals("android.intent.action.INPUT_METHOD_CHANGED")) {
                        return;
                    }
                    break;
                case -403228793:
                    if (!action.equals("android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                        return;
                    }
                    break;
                case -301431627:
                    if (action.equals("android.bluetooth.device.action.ACL_CONNECTED")) {
                        BluetoothDevice bluetoothDevice3 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        if (bluetoothDevice3 != null) {
                            XLog.m137i("蓝牙连接成功--> " + bluetoothDevice3.getName() + HelpFormatter.DEFAULT_LONG_OPT_PREFIX + bluetoothDevice3.getAddress());
                        }
                        String deviceAddress = UserConfig.INSTANCE.getInstance().getDeviceAddress();
                        Intrinsics.checkNotNull(bluetoothDevice3);
                        if (TextUtils.isEmpty(bluetoothDevice3.getAddress()) || !StringsKt.equals(bluetoothDevice3.getAddress(), deviceAddress, true)) {
                            return;
                        }
                        XLog.m137i("system-connect-ok");
                        this.numConnect = 1;
                        if (BleOperateManager.getInstance().isConnected()) {
                            return;
                        }
                        beginConnect(5000);
                        ThreadManager.getInstance().wakeUp();
                        return;
                    }
                    return;
                case -286614297:
                    if (!action.equals("android.intent.action.CAMERA_BUTTON")) {
                        return;
                    }
                    break;
                case 505380757:
                    if (action.equals("android.intent.action.TIME_SET")) {
                        XLog.m137i("手机系统改时间");
                        LargeDataHandler.getInstance().syncTime(new ILargeDataResponse() { // from class: com.glasssutdio.wear.ble.receiver.BluetoothReceiver$$ExternalSyntheticLambda4
                            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
                            public final void parseData(int i, BaseResponse baseResponse) {
                                BluetoothReceiver.onReceive$lambda$0(i, (SyncTimeResponse) baseResponse);
                            }
                        });
                        return;
                    }
                    return;
                case 823795052:
                    if (action.equals("android.intent.action.USER_PRESENT")) {
                        beginConnect(DownloadTask.Builder.DEFAULT_SYNC_BUFFER_INTERVAL_MILLIS);
                        return;
                    }
                    return;
                case 1041332296:
                    if (!action.equals("android.intent.action.DATE_CHANGED")) {
                        return;
                    }
                    break;
                case 1167529923:
                    if (action.equals("android.bluetooth.device.action.FOUND") && (bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")) != null && StringsKt.equals(bluetoothDevice.getAddress(), UserConfig.INSTANCE.getInstance().getClassicBluetoothMac(), true)) {
                        String deviceAddress2 = DeviceManager.getInstance().getDeviceAddress();
                        Intrinsics.checkNotNullExpressionValue(deviceAddress2, "getDeviceAddress(...)");
                        if (deviceAddress2.length() > 0) {
                            this.btDevice = bluetoothDevice;
                            this.mHandler.postDelayed(this.classicBluetoothRunnable, 5000L);
                            BleOperateManager.getInstance().createBondBluetoothJieLi(bluetoothDevice);
                            return;
                        }
                        return;
                    }
                    return;
                case 1821585647:
                    if (action.equals("android.bluetooth.device.action.ACL_DISCONNECTED") && BluetoothUtils.isEnabledBluetooth(context) && (bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")) != null) {
                        String deviceAddress3 = UserConfig.INSTANCE.getInstance().getDeviceAddress();
                        if (TextUtils.isEmpty(bluetoothDevice2.getAddress()) || !StringsKt.equals$default(deviceAddress3, bluetoothDevice2.getAddress(), false, 2, null)) {
                            return;
                        }
                        XLog.m137i("system-disconnect-ok");
                        this.numConnect = 1;
                        connectAgain(22000);
                        this.mHandler.post(this.uiRunnable);
                        return;
                    }
                    return;
                case 2116862345:
                    if (action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
                        BluetoothDevice bluetoothDevice4 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        switch (intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1)) {
                            case 10:
                                StringBuilder sb = new StringBuilder("蓝牙配对失败--> ");
                                Intrinsics.checkNotNull(bluetoothDevice4);
                                XLog.m137i(sb.append(bluetoothDevice4.getAddress()).toString());
                                if (StringsKt.equals(bluetoothDevice4.getAddress(), UserConfig.INSTANCE.getInstance().getClassicBluetoothMac(), true) && this.btReconnect >= 2 && UserConfig.INSTANCE.getInstance().getDeviceAddress().length() > 0) {
                                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                    String str = String.format(GlobalKt.getString(C0775R.string.h_glass_104), Arrays.copyOf(new Object[]{bluetoothDevice4.getName()}, 1));
                                    Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                                    GlobalKt.showToast$default(str, 0, 1, null);
                                    EventBus.getDefault().post(new EventType(15));
                                    break;
                                }
                                break;
                            case 11:
                                XLog.m137i("蓝牙正在配对--> ");
                                break;
                            case 12:
                                XLog.m137i("蓝牙配对成功--> ");
                                if (bluetoothDevice4 != null && StringsKt.equals(bluetoothDevice4.getAddress(), UserConfig.INSTANCE.getInstance().getClassicBluetoothMac(), true)) {
                                    EventBus.getDefault().post(new EventType(14));
                                    this.mHandler.removeCallbacks(this.classicBluetoothRunnable);
                                    break;
                                }
                                break;
                        }
                        this.mHandler.removeCallbacks(this.uiRunnable);
                        this.mHandler.post(this.uiRunnable);
                        return;
                    }
                    return;
                default:
                    return;
            }
            this.mHandler.removeCallbacks(this.uiRunnable);
            this.mHandler.postDelayed(this.uiRunnable, 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void classicBluetoothRunnable$lambda$1(BluetoothReceiver this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BluetoothDevice bluetoothDevice = this$0.btDevice;
        if (bluetoothDevice != null) {
            Intrinsics.checkNotNull(bluetoothDevice);
            if (StringsKt.equals(bluetoothDevice.getAddress(), UserConfig.INSTANCE.getInstance().getClassicBluetoothMac(), true)) {
                this$0.btReconnect++;
                BleOperateManager.getInstance().createBondBluetoothJieLi(this$0.btDevice);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void uiRunnable$lambda$2() throws SecurityException {
        if (!BleOperateManager.getInstance().isConnected()) {
            ThreadManager.getInstance().wakeUp();
        } else {
            if (AppUtil.isBackground(GlassApplication.INSTANCE.getCONTEXT())) {
                return;
            }
            AppUtil.isApplicationBroughtToBackground(GlassApplication.INSTANCE.getCONTEXT());
        }
    }

    private final void beginConnect(int delayTime) {
        this.mHandler.removeCallbacks(this.connectRunnable);
        if (this.bleOpen) {
            this.mHandler.postDelayed(this.connectRunnable, delayTime);
        }
    }

    private final void connectAgain(int delayTime) {
        if (!this.bleOpen || this.numConnect > 20) {
            return;
        }
        this.mHandler.postDelayed(this.connectRunnable, delayTime);
    }

    private final void disConnectDevice() {
        this.mHandler.postDelayed(new Runnable() { // from class: com.glasssutdio.wear.ble.receiver.BluetoothReceiver$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothReceiver.disConnectDevice$lambda$3();
            }
        }, 1500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void disConnectDevice$lambda$3() {
        if (BleOperateManager.getInstance().isConnected()) {
            BleOperateManager.getInstance().disconnect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void connectRunnable$lambda$4(BluetoothReceiver this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!TextUtils.isEmpty(UserConfig.INSTANCE.getInstance().getDeviceAddress()) && !BleOperateManager.getInstance().isConnected()) {
            this$0.reConnect();
            int i = this$0.numConnect;
            int i2 = i + (i / 10) + 1;
            this$0.numConnect = i2;
            this$0.connectAgain(((i2 / 10) + 1) * 60000);
            return;
        }
        this$0.numConnect = 1;
    }

    private final void reConnect() {
        BleOperateManager.getInstance().setReConnectMac(UserConfig.INSTANCE.getInstance().getDeviceAddress());
        DeviceReconnect.INSTANCE.getGetInstance().connectWithScanValidation(UserConfig.INSTANCE.getInstance().getDeviceAddress());
    }
}
