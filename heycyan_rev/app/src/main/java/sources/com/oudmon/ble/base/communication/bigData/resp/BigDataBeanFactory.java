package com.oudmon.ble.base.communication.bigData.resp;

import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;

/* loaded from: classes2.dex */
public class BigDataBeanFactory {
    public static BaseResponse createBean(int i) {
        if (i == -3) {
            return new PictureThumbnailsResponse();
        }
        if (i == 46) {
            return new ClassBluetoothResponse();
        }
        if (i == 81) {
            return new VolumeControlResponse();
        }
        if (i == 89) {
            return new AiChatResponse();
        }
        if (i != 115) {
            switch (i) {
                case 64:
                    return new SyncTimeResponse();
                case 65:
                    return new GlassModelControlResponse();
                case 66:
                    return new BatteryResponse();
                case 67:
                    return new DeviceInfoResponse();
                case 68:
                    return new GlassesAiVoiceRsp();
                default:
                    switch (i) {
                        case 70:
                            return new GlassesWearRsp();
                        case 71:
                            return new GlassesTouchSupportRsp();
                        case EMachine.EM_68HC05 /* 72 */:
                            return new GlassesAiVoicePlayStatusRsp();
                        default:
                            return null;
                    }
            }
        }
        return new GlassesDeviceNotifyRsp();
    }
}
