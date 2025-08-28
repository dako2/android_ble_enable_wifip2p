package com.oudmon.ble.base.bluetooth;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import com.oudmon.ble.base.communication.rsp.AppRevisionResp;
import com.oudmon.ble.base.communication.rsp.AppSportRsp;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.BatteryRsp;
import com.oudmon.ble.base.communication.rsp.BloodOxygenSettingRsp;
import com.oudmon.ble.base.communication.rsp.BloodSugarLipidsSettingRsp;
import com.oudmon.ble.base.communication.rsp.BpDataRsp;
import com.oudmon.ble.base.communication.rsp.BpSettingRsp;
import com.oudmon.ble.base.communication.rsp.CameraNotifyRsp;
import com.oudmon.ble.base.communication.rsp.DegreeSwitchRsp;
import com.oudmon.ble.base.communication.rsp.DeviceAvatarRsp;
import com.oudmon.ble.base.communication.rsp.DeviceNotifyRsp;
import com.oudmon.ble.base.communication.rsp.DeviceSupportFunctionRsp;
import com.oudmon.ble.base.communication.rsp.DisplayTimeRsp;
import com.oudmon.ble.base.communication.rsp.DndRsp;
import com.oudmon.ble.base.communication.rsp.FindPhoneRsp;
import com.oudmon.ble.base.communication.rsp.GlassModelControlResp;
import com.oudmon.ble.base.communication.rsp.HRVRsp;
import com.oudmon.ble.base.communication.rsp.HRVSettingRsp;
import com.oudmon.ble.base.communication.rsp.HeartRateSettingRsp;
import com.oudmon.ble.base.communication.rsp.MusicCommandRsp;
import com.oudmon.ble.base.communication.rsp.MuslimRsp;
import com.oudmon.ble.base.communication.rsp.MuslimTargetRsp;
import com.oudmon.ble.base.communication.rsp.PackageLengthRsp;
import com.oudmon.ble.base.communication.rsp.PalmScreenRsp;
import com.oudmon.ble.base.communication.rsp.PhoneNotifyRsp;
import com.oudmon.ble.base.communication.rsp.PressureRsp;
import com.oudmon.ble.base.communication.rsp.PressureSettingRsp;
import com.oudmon.ble.base.communication.rsp.ReadAlarmRsp;
import com.oudmon.ble.base.communication.rsp.ReadBlePressureRsp;
import com.oudmon.ble.base.communication.rsp.ReadDetailSportDataRsp;
import com.oudmon.ble.base.communication.rsp.ReadHeartRateRsp;
import com.oudmon.ble.base.communication.rsp.ReadMessagePushRsp;
import com.oudmon.ble.base.communication.rsp.ReadSitLongRsp;
import com.oudmon.ble.base.communication.rsp.ReadSleepDetailsRsp;
import com.oudmon.ble.base.communication.rsp.RealTimeHeartRateRsp;
import com.oudmon.ble.base.communication.rsp.SetTimeRsp;
import com.oudmon.ble.base.communication.rsp.SimpleStatusRsp;
import com.oudmon.ble.base.communication.rsp.StartHeartRateRsp;
import com.oudmon.ble.base.communication.rsp.TargetSettingRsp;
import com.oudmon.ble.base.communication.rsp.TimeFormatRsp;
import com.oudmon.ble.base.communication.rsp.TodaySportDataRsp;
import com.oudmon.ble.base.communication.rsp.TouchControlResp;
import com.oudmon.ble.base.communication.rsp.WeatherForecastRsp;

/* loaded from: classes2.dex */
public class BeanFactory {
    public static BaseRspCmd createBean(int i, int i2) {
        if (i == 1) {
            return new SetTimeRsp();
        }
        if (i == 2) {
            return new CameraNotifyRsp();
        }
        if (i == 3) {
            return new BatteryRsp();
        }
        if (i == 5) {
            return new PalmScreenRsp();
        }
        if (i == 6) {
            return new DndRsp();
        }
        if (i == 12) {
            return new BpSettingRsp();
        }
        if (i == 13) {
            return new BpDataRsp();
        }
        if (i == 25) {
            return new DegreeSwitchRsp();
        }
        if (i == 26) {
            return new WeatherForecastRsp();
        }
        if (i == 33) {
            return new TargetSettingRsp();
        }
        if (i == 34) {
            return new FindPhoneRsp();
        }
        if (i == 67) {
            return new ReadDetailSportDataRsp();
        }
        if (i == 68) {
            return new ReadSleepDetailsRsp();
        }
        if (i == 114) {
            return new SimpleStatusRsp();
        }
        if (i != 115) {
            switch (i) {
                case -223:
                    return new AppRevisionResp();
                case 10:
                    return new TimeFormatRsp();
                case 17:
                    return new PhoneNotifyRsp();
                case 38:
                    return new ReadSitLongRsp();
                case 40:
                    return new ReadAlarmRsp();
                case 44:
                    return new BloodOxygenSettingRsp();
                case 47:
                    return new PackageLengthRsp();
                case 50:
                    return new DeviceAvatarRsp();
                case 54:
                    return new PressureSettingRsp();
                case 55:
                    return new PressureRsp();
                case 56:
                    return new HRVSettingRsp();
                case 57:
                    return new HRVRsp();
                case 58:
                    return new BloodSugarLipidsSettingRsp();
                case 59:
                    return new TouchControlResp();
                case 60:
                    return new DeviceSupportFunctionRsp();
                case 62:
                    return new GlassModelControlResp();
                case EMachine.EM_68HC05 /* 72 */:
                    return new TodaySportDataRsp();
                case EMachine.EM_NS32K /* 97 */:
                    return new ReadMessagePushRsp();
                case 105:
                    return new StartHeartRateRsp();
                case EMachine.EM_CE /* 119 */:
                    return new AppSportRsp();
                case EMachine.EM_M32C /* 120 */:
                    break;
                case 122:
                    return new MuslimRsp();
                case 123:
                    return new MuslimTargetRsp();
                default:
                    switch (i) {
                        case 20:
                            return new ReadBlePressureRsp();
                        case 21:
                            return new ReadHeartRateRsp();
                        case 22:
                            return new HeartRateSettingRsp();
                        default:
                            switch (i) {
                                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                                    return new MusicCommandRsp();
                                case 30:
                                    return new RealTimeHeartRateRsp();
                                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
                                    return new DisplayTimeRsp();
                                default:
                                    return null;
                            }
                    }
            }
        }
        return new DeviceNotifyRsp();
    }
}
