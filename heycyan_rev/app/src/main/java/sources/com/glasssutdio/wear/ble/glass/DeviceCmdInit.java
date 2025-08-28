package com.glasssutdio.wear.ble.glass;

import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.GsonInstance;
import com.glasssutdio.wear.all.utils.MoshiUtilsKt;
import com.glasssutdio.wear.bus.EventType;
import com.glasssutdio.wear.bus.GlassesBatteryUpdateEvent;
import com.glasssutdio.wear.database.entity.DeviceSettingEntity;
import com.glasssutdio.wear.depository.DeviceSettingDepository;
import com.glasssutdio.wear.depository.bean.DeviceSetting;
import com.glasssutdio.wear.depository.bean.DeviceSettingAction;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.resp.BaseResponse;
import com.oudmon.ble.base.communication.bigData.resp.BatteryResponse;
import com.oudmon.ble.base.communication.bigData.resp.DeviceInfoResponse;
import com.oudmon.ble.base.communication.bigData.resp.GlassesTouchSupportRsp;
import com.oudmon.ble.base.communication.bigData.resp.SyncTimeResponse;
import com.oudmon.ble.base.communication.bigData.resp.VolumeControlResponse;
import com.oudmon.ble.base.communication.file.FileHandle;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* compiled from: DeviceCmdInit.kt */
@Metadata(m606d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004J\b\u0010\t\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m607d2 = {"Lcom/glasssutdio/wear/ble/glass/DeviceCmdInit;", "", "()V", "deviceSetting", "Lcom/glasssutdio/wear/depository/bean/DeviceSetting;", "init", "", "initDeviceSetting", "saveDeviceSetting", "syncDeviceSetting", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class DeviceCmdInit {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<DeviceCmdInit> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<DeviceCmdInit>() { // from class: com.glasssutdio.wear.ble.glass.DeviceCmdInit$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DeviceCmdInit invoke() {
            return new DeviceCmdInit();
        }
    });
    private DeviceSetting deviceSetting;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(int i, SyncTimeResponse syncTimeResponse) {
    }

    public final void initDeviceSetting() {
        init();
    }

    private final void init() {
        FileHandle.getInstance().clearCallback();
        LargeDataHandler.getInstance().syncTime(new ILargeDataResponse() { // from class: com.glasssutdio.wear.ble.glass.DeviceCmdInit$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, BaseResponse baseResponse) {
                DeviceCmdInit.init$lambda$0(i, (SyncTimeResponse) baseResponse);
            }
        });
        syncDeviceSetting();
    }

    private final void syncDeviceSetting() {
        BleOperateManager.getInstance().classicBluetoothStartScan();
        LargeDataHandler.getInstance().addBatteryCallBack("init", new ILargeDataResponse() { // from class: com.glasssutdio.wear.ble.glass.DeviceCmdInit$$ExternalSyntheticLambda1
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, BaseResponse baseResponse) {
                DeviceCmdInit.syncDeviceSetting$lambda$1(i, (BatteryResponse) baseResponse);
            }
        });
        LargeDataHandler.getInstance().syncBattery();
        LargeDataHandler.getInstance().syncDeviceInfo(new ILargeDataResponse() { // from class: com.glasssutdio.wear.ble.glass.DeviceCmdInit$$ExternalSyntheticLambda2
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, BaseResponse baseResponse) {
                DeviceCmdInit.syncDeviceSetting$lambda$2(i, (DeviceInfoResponse) baseResponse);
            }
        });
        LargeDataHandler.getInstance().wearFunctionSupport(new ILargeDataResponse() { // from class: com.glasssutdio.wear.ble.glass.DeviceCmdInit$$ExternalSyntheticLambda3
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, BaseResponse baseResponse) {
                DeviceCmdInit.syncDeviceSetting$lambda$3(i, (GlassesTouchSupportRsp) baseResponse);
            }
        });
        LargeDataHandler.getInstance().getVolumeControl(new ILargeDataResponse() { // from class: com.glasssutdio.wear.ble.glass.DeviceCmdInit$$ExternalSyntheticLambda4
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, BaseResponse baseResponse) {
                DeviceCmdInit.syncDeviceSetting$lambda$4(i, (VolumeControlResponse) baseResponse);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void syncDeviceSetting$lambda$1(int i, BatteryResponse batteryResponse) {
        UserConfig.INSTANCE.getInstance().setBattery(batteryResponse.getBattery());
        UserConfig companion = UserConfig.INSTANCE.getInstance();
        int battery = batteryResponse.getBattery();
        boolean z = false;
        if (1 <= battery && battery < 16) {
            z = true;
        }
        companion.setLowBattery(z);
        XLog.m137i("眼镜电量：" + batteryResponse.getBattery() + "---" + batteryResponse.isCharging());
        EventBus.getDefault().post(new GlassesBatteryUpdateEvent(batteryResponse.getBattery(), batteryResponse.isCharging()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void syncDeviceSetting$lambda$2(int i, DeviceInfoResponse deviceInfoResponse) {
        if (deviceInfoResponse != null) {
            UserConfig companion = UserConfig.INSTANCE.getInstance();
            String wifiFirmwareVersion = deviceInfoResponse.getWifiFirmwareVersion();
            Intrinsics.checkNotNullExpressionValue(wifiFirmwareVersion, "getWifiFirmwareVersion(...)");
            companion.setFmVersionWifi(wifiFirmwareVersion);
            UserConfig companion2 = UserConfig.INSTANCE.getInstance();
            String wifiHardwareVersion = deviceInfoResponse.getWifiHardwareVersion();
            Intrinsics.checkNotNullExpressionValue(wifiHardwareVersion, "getWifiHardwareVersion(...)");
            companion2.setHwVersionWifi(wifiHardwareVersion);
            UserConfig companion3 = UserConfig.INSTANCE.getInstance();
            String hardwareVersion = deviceInfoResponse.getHardwareVersion();
            Intrinsics.checkNotNullExpressionValue(hardwareVersion, "getHardwareVersion(...)");
            companion3.setHwVersion(hardwareVersion);
            UserConfig companion4 = UserConfig.INSTANCE.getInstance();
            String firmwareVersion = deviceInfoResponse.getFirmwareVersion();
            Intrinsics.checkNotNullExpressionValue(firmwareVersion, "getFirmwareVersion(...)");
            companion4.setFmVersion(firmwareVersion);
            EventBus.getDefault().post(new EventType(2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void syncDeviceSetting$lambda$3(int i, GlassesTouchSupportRsp glassesTouchSupportRsp) {
        if (glassesTouchSupportRsp != null) {
            XLog.m137i(GsonInstance.INSTANCE.getGson().toJson(glassesTouchSupportRsp));
            UserConfig.INSTANCE.getInstance().setSupportWear(glassesTouchSupportRsp.isWearCheckSupport());
            UserConfig.INSTANCE.getInstance().setSupportVolumeControl(glassesTouchSupportRsp.isVolumeControl());
            UserConfig.INSTANCE.getInstance().setSupportTranslate(glassesTouchSupportRsp.isTranslationSupport());
            UserConfig.INSTANCE.getInstance().setGlassesModel(glassesTouchSupportRsp.getGlassesModel());
            EventBus.getDefault().post(new EventType(16));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void syncDeviceSetting$lambda$4(int i, VolumeControlResponse volumeControlResponse) {
        if (volumeControlResponse != null) {
            UserConfig.INSTANCE.getInstance().setVolumeControl("" + volumeControlResponse.getMinVolumeMusic() + ',' + volumeControlResponse.getMaxVolumeMusic() + ',' + volumeControlResponse.getCurrVolumeMusic() + ',' + volumeControlResponse.getMinVolumeCall() + ',' + volumeControlResponse.getMaxVolumeCall() + ',' + volumeControlResponse.getCurrVolumeCall() + ',' + volumeControlResponse.getMinVolumeSystem() + ',' + volumeControlResponse.getMaxVolumeSystem() + ',' + volumeControlResponse.getCurrVolumeSystem() + ',' + volumeControlResponse.getCurrVolumeType());
            XLog.m137i(UserConfig.INSTANCE.getInstance().getVolumeControl());
            EventBus.getDefault().post(new EventType(19));
        }
    }

    public final void saveDeviceSetting(DeviceSetting deviceSetting) {
        Intrinsics.checkNotNullParameter(deviceSetting, "deviceSetting");
        DeviceSettingDepository.INSTANCE.getGetInstance().saveDeviceSetting(new DeviceSettingEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), DeviceSettingAction.GlassSetting, MoshiUtilsKt.toJson(deviceSetting)));
    }

    /* compiled from: DeviceCmdInit.kt */
    @Metadata(m606d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/ble/glass/DeviceCmdInit$Companion;", "", "()V", "getInstance", "Lcom/glasssutdio/wear/ble/glass/DeviceCmdInit;", "getGetInstance", "()Lcom/glasssutdio/wear/ble/glass/DeviceCmdInit;", "getInstance$delegate", "Lkotlin/Lazy;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DeviceCmdInit getGetInstance() {
            return (DeviceCmdInit) DeviceCmdInit.getInstance$delegate.getValue();
        }
    }
}
