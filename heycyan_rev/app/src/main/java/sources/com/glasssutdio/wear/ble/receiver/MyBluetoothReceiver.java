package com.glasssutdio.wear.ble.receiver;

import android.bluetooth.BluetoothDevice;
import androidx.core.app.NotificationCompat;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.GlassApplication;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.api.NetState;
import com.glasssutdio.wear.ble.glass.DeviceCmdInit;
import com.glasssutdio.wear.bus.BluetoothEvent;
import com.glasssutdio.wear.depository.DeviceSettingDepository;
import com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver;
import com.oudmon.ble.base.communication.LargeDataHandler;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.greenrobot.eventbus.EventBus;

/* compiled from: MyBluetoothReceiver.kt */
@Metadata(m606d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u001a\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0004H\u0002J&\u0010\u000e\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u001c\u0010\u0014\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0015\u001a\u00020\u0004H\u0016¨\u0006\u0016"}, m607d2 = {"Lcom/glasssutdio/wear/ble/receiver/MyBluetoothReceiver;", "Lcom/oudmon/ble/base/bluetooth/QCBluetoothCallbackCloneReceiver;", "()V", "bleStatus", "", NotificationCompat.CATEGORY_STATUS, "", "newState", "connectStatue", "device", "Landroid/bluetooth/BluetoothDevice;", "connected", "", "initCmd", "onCharacteristicChange", "address", "", "uuid", "data", "", "onCharacteristicRead", "onServiceDiscovered", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class MyBluetoothReceiver extends QCBluetoothCallbackCloneReceiver {
    @Override // com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver
    public void connectStatue(BluetoothDevice device, boolean connected) {
        if (device != null && connected) {
            if (device.getName() != null) {
                UserConfig companion = UserConfig.INSTANCE.getInstance();
                String name = device.getName();
                Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                companion.setDeviceName(name);
                UserConfig companion2 = UserConfig.INSTANCE.getInstance();
                String name2 = device.getName();
                Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
                companion2.setDeviceNameNoClear(name2);
            }
            UserConfig.INSTANCE.getInstance().setDeviceBind(true);
            UserConfig companion3 = UserConfig.INSTANCE.getInstance();
            String address = device.getAddress();
            Intrinsics.checkNotNullExpressionValue(address, "getAddress(...)");
            companion3.setDeviceAddress(address);
            UserConfig companion4 = UserConfig.INSTANCE.getInstance();
            String address2 = device.getAddress();
            Intrinsics.checkNotNullExpressionValue(address2, "getAddress(...)");
            companion4.setClassicBluetoothMac(address2);
            UserConfig companion5 = UserConfig.INSTANCE.getInstance();
            String address3 = device.getAddress();
            Intrinsics.checkNotNullExpressionValue(address3, "getAddress(...)");
            companion5.setDeviceAddressNoClear(address3);
            if (StringsKt.contains$default((CharSequence) UserConfig.INSTANCE.getInstance().getDeviceName(), (CharSequence) "_", false, 2, (Object) null)) {
                String strSubstring = (String) StringsKt.split$default((CharSequence) UserConfig.INSTANCE.getInstance().getDeviceName(), new String[]{"_"}, false, 0, 6, (Object) null).get(0);
                String strReplace$default = StringsKt.replace$default(UserConfig.INSTANCE.getInstance().getDeviceAddress(), ":", "", false, 4, (Object) null);
                if (strSubstring.length() > 20) {
                    strSubstring = strSubstring.substring(0, 20);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                }
                UserConfig.INSTANCE.getInstance().setGlassDeviceWifiName(strSubstring + '_' + strReplace$default);
                UserConfig.INSTANCE.getInstance().setGlassDeviceWifiPassword("123456789");
            } else {
                UserConfig.INSTANCE.getInstance().setGlassDeviceWifiName(UserConfig.INSTANCE.getInstance().getDeviceName() + '_' + StringsKt.replace$default(UserConfig.INSTANCE.getInstance().getDeviceAddress(), ":", "", false, 4, (Object) null));
                UserConfig.INSTANCE.getInstance().setGlassDeviceWifiPassword("123456789");
            }
            if (UserConfig.INSTANCE.getInstance().getUniqueIdHw().length() == 0 || StringsKt.equals(UserConfig.INSTANCE.getInstance().getUniqueIdHw(), "null", true)) {
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C08571(null), 3, null);
                return;
            } else {
                XLog.m137i("uniqueId->:" + UserConfig.INSTANCE.getInstance().getUniqueIdHw());
                return;
            }
        }
        EventBus.getDefault().post(new BluetoothEvent(false));
    }

    /* compiled from: MyBluetoothReceiver.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.ble.receiver.MyBluetoothReceiver$connectStatue$1", m620f = "MyBluetoothReceiver.kt", m621i = {}, m622l = {54, 55}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.ble.receiver.MyBluetoothReceiver$connectStatue$1 */
    static final class C08571 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C08571(Continuation<? super C08571> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C08571(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08571) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DeviceSettingDepository.INSTANCE.getGetInstance().getUniqueIdFromServer(UserConfig.INSTANCE.getInstance().getDeviceAddress(), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.glasssutdio.wear.ble.receiver.MyBluetoothReceiver.connectStatue.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<String>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<String> netState, Continuation<? super Unit> continuation) {
                    XLog.m137i(String.valueOf(netState.isSuccess()));
                    UserConfig.INSTANCE.getInstance().setUniqueIdHw(String.valueOf(netState.isSuccess()));
                    GlassApplication.INSTANCE.getGetInstance().initSpark();
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver
    public void onServiceDiscovered() {
        ThreadExtKt.ktxRunOnBgSingleBle(this, new Function1<MyBluetoothReceiver, Unit>() { // from class: com.glasssutdio.wear.ble.receiver.MyBluetoothReceiver.onServiceDiscovered.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MyBluetoothReceiver myBluetoothReceiver) {
                invoke2(myBluetoothReceiver);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MyBluetoothReceiver ktxRunOnBgSingleBle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingleBle, "$this$ktxRunOnBgSingleBle");
                LargeDataHandler.getInstance().initEnable();
                EventBus.getDefault().post(new BluetoothEvent(true));
                ktxRunOnBgSingleBle.initCmd();
            }
        });
    }

    @Override // com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver
    public void onCharacteristicChange(String address, String uuid, final byte[] data) {
        if (data != null) {
            ThreadExtKt.ktxRunOnBgSingleBle(this, new Function1<MyBluetoothReceiver, Unit>() { // from class: com.glasssutdio.wear.ble.receiver.MyBluetoothReceiver.onCharacteristicChange.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(MyBluetoothReceiver myBluetoothReceiver) {
                    invoke2(myBluetoothReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(MyBluetoothReceiver ktxRunOnBgSingleBle) {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgSingleBle, "$this$ktxRunOnBgSingleBle");
                    BleCommonDataParseKt.parseBleData(data);
                }
            });
        }
    }

    @Override // com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver
    public void onCharacteristicRead(final String uuid, final byte[] data) {
        if (uuid == null || data == null) {
            return;
        }
        ThreadExtKt.ktxRunOnBgSingleBle(this, new Function1<MyBluetoothReceiver, Unit>() { // from class: com.glasssutdio.wear.ble.receiver.MyBluetoothReceiver.onCharacteristicRead.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MyBluetoothReceiver myBluetoothReceiver) {
                invoke2(myBluetoothReceiver);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MyBluetoothReceiver ktxRunOnBgSingleBle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingleBle, "$this$ktxRunOnBgSingleBle");
                BleCommonDataParseKt.parseDeviceInfoData(uuid, data);
            }
        });
    }

    @Override // com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver
    public void bleStatus(int status, int newState) {
        super.bleStatus(status, newState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initCmd() {
        XLog.m137i("initCmd");
        DeviceCmdInit.INSTANCE.getGetInstance().initDeviceSetting();
    }
}
