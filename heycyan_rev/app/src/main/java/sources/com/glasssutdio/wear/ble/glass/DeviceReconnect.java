package com.glasssutdio.wear.ble.glass;

import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: DeviceReconnect.kt */
@Metadata(m606d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\b"}, m607d2 = {"Lcom/glasssutdio/wear/ble/glass/DeviceReconnect;", "", "()V", "connectWithScanValidation", "", "macAddress", "", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class DeviceReconnect {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<DeviceReconnect> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<DeviceReconnect>() { // from class: com.glasssutdio.wear.ble.glass.DeviceReconnect$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DeviceReconnect invoke() {
            return new DeviceReconnect(null);
        }
    });

    public /* synthetic */ DeviceReconnect(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private DeviceReconnect() {
    }

    public final void connectWithScanValidation(String macAddress) {
        if (UserConfig.INSTANCE.getInstance().getDeviceAddress().length() == 0) {
            XLog.m137i("手环已经解绑,不用重连");
        } else {
            BleOperateManager.getInstance().connectDirectly(macAddress);
        }
    }

    /* compiled from: DeviceReconnect.kt */
    @Metadata(m606d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/ble/glass/DeviceReconnect$Companion;", "", "()V", "getInstance", "Lcom/glasssutdio/wear/ble/glass/DeviceReconnect;", "getGetInstance", "()Lcom/glasssutdio/wear/ble/glass/DeviceReconnect;", "getInstance$delegate", "Lkotlin/Lazy;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DeviceReconnect getGetInstance() {
            return (DeviceReconnect) DeviceReconnect.getInstance$delegate.getValue();
        }
    }
}
