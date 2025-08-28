package com.glasssutdio.wear.ble.connect;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.GFileUtilKt;
import com.glasssutdio.wear.api.NetState;
import com.glasssutdio.wear.api.response.DevicePictureResp;
import com.glasssutdio.wear.ble.connect.bean.SmartWatch;
import com.glasssutdio.wear.depository.DeviceSettingDepository;
import com.glasssutdio.wear.depository.bean.DevicePictureBean;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.kotlin.DownloadTaskExtensionKt;
import com.oudmon.qc_utils.date.DateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: DeviceBindViewModel.kt */
@Metadata(m606d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001aB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0002J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0015R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, m607d2 = {"Lcom/glasssutdio/wear/ble/connect/DeviceBindViewModel;", "Landroidx/lifecycle/ViewModel;", "deviceSettingRepository", "Lcom/glasssutdio/wear/depository/DeviceSettingDepository;", "(Lcom/glasssutdio/wear/depository/DeviceSettingDepository;)V", "_picUiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/glasssutdio/wear/ble/connect/DeviceBindViewModel$DevicePictureUI;", "deviceList", "", "Lcom/glasssutdio/wear/ble/connect/bean/SmartWatch;", "getDeviceList", "()Ljava/util/List;", "picUiState", "Landroidx/lifecycle/LiveData;", "getPicUiState", "()Landroidx/lifecycle/LiveData;", "addSystemBondedDevice", "createGlideTask", "Lcom/liulishuo/okdownload/DownloadTask;", "url", "", "fileName", "devicePicture", "", "hardwareVersion", "DevicePictureUI", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class DeviceBindViewModel extends ViewModel {
    private final MutableLiveData<DevicePictureUI> _picUiState;
    private final List<SmartWatch> deviceList;
    private final DeviceSettingDepository deviceSettingRepository;

    public DeviceBindViewModel(DeviceSettingDepository deviceSettingRepository) {
        Intrinsics.checkNotNullParameter(deviceSettingRepository, "deviceSettingRepository");
        this.deviceSettingRepository = deviceSettingRepository;
        this.deviceList = new ArrayList();
        this._picUiState = new MutableLiveData<>();
    }

    public final List<SmartWatch> getDeviceList() {
        return this.deviceList;
    }

    public final LiveData<DevicePictureUI> getPicUiState() {
        return this._picUiState;
    }

    public final List<SmartWatch> addSystemBondedDevice() {
        ArrayList arrayList = new ArrayList();
        Set<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        if (bondedDevices.size() > 0) {
            Iterator<BluetoothDevice> it = bondedDevices.iterator();
            while (it.hasNext()) {
                BluetoothDevice next = it.next();
                if ((next != null ? next.getName() : null) != null) {
                    next.getAddress();
                }
            }
        }
        return arrayList;
    }

    /* compiled from: DeviceBindViewModel.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.ble.connect.DeviceBindViewModel$devicePicture$1", m620f = "DeviceBindViewModel.kt", m621i = {}, m622l = {46, 47}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.ble.connect.DeviceBindViewModel$devicePicture$1 */
    static final class C08561 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hardwareVersion;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08561(String str, Continuation<? super C08561> continuation) {
            super(2, continuation);
            this.$hardwareVersion = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DeviceBindViewModel.this.new C08561(this.$hardwareVersion, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08561) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DeviceBindViewModel.this.deviceSettingRepository.getDevicePictureFromLocal(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
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
            if (((Flow) obj).collect(new AnonymousClass1(DeviceBindViewModel.this, this.$hardwareVersion), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        /* compiled from: DeviceBindViewModel.kt */
        @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m607d2 = {"<anonymous>", "", "it", "Lcom/glasssutdio/wear/depository/bean/DevicePictureBean;", "emit", "(Lcom/glasssutdio/wear/depository/bean/DevicePictureBean;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        /* renamed from: com.glasssutdio.wear.ble.connect.DeviceBindViewModel$devicePicture$1$1, reason: invalid class name */
        static final class AnonymousClass1<T> implements FlowCollector {
            final /* synthetic */ String $hardwareVersion;
            final /* synthetic */ DeviceBindViewModel this$0;

            AnonymousClass1(DeviceBindViewModel deviceBindViewModel, String str) {
                this.this$0 = deviceBindViewModel;
                this.$hardwareVersion = str;
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object emit(DevicePictureBean devicePictureBean, Continuation<? super Unit> continuation) {
                DeviceBindViewModel$devicePicture$1$1$emit$1 deviceBindViewModel$devicePicture$1$1$emit$1;
                AnonymousClass1<T> anonymousClass1;
                if (continuation instanceof DeviceBindViewModel$devicePicture$1$1$emit$1) {
                    deviceBindViewModel$devicePicture$1$1$emit$1 = (DeviceBindViewModel$devicePicture$1$1$emit$1) continuation;
                    if ((deviceBindViewModel$devicePicture$1$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                        deviceBindViewModel$devicePicture$1$1$emit$1.label -= Integer.MIN_VALUE;
                    } else {
                        deviceBindViewModel$devicePicture$1$1$emit$1 = new DeviceBindViewModel$devicePicture$1$1$emit$1(this, continuation);
                    }
                }
                Object devicePictureFromServer = deviceBindViewModel$devicePicture$1$1$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = deviceBindViewModel$devicePicture$1$1$emit$1.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(devicePictureFromServer);
                    if (devicePictureBean != null) {
                        if (devicePictureBean.getLocalUrl().length() == 0) {
                            DeviceSettingDepository deviceSettingDepository = this.this$0.deviceSettingRepository;
                            String str = this.$hardwareVersion;
                            deviceBindViewModel$devicePicture$1$1$emit$1.L$0 = this;
                            deviceBindViewModel$devicePicture$1$1$emit$1.label = 1;
                            devicePictureFromServer = deviceSettingDepository.getDevicePictureFromServer(str, deviceBindViewModel$devicePicture$1$1$emit$1);
                            if (devicePictureFromServer == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            anonymousClass1 = this;
                        } else {
                            this.this$0._picUiState.postValue(new DevicePictureUI(devicePictureBean.getPictureUrl(), devicePictureBean.getLocalUrl()));
                        }
                    }
                    return Unit.INSTANCE;
                }
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(devicePictureFromServer);
                    return Unit.INSTANCE;
                }
                anonymousClass1 = (AnonymousClass1) deviceBindViewModel$devicePicture$1$1$emit$1.L$0;
                ResultKt.throwOnFailure(devicePictureFromServer);
                final String str2 = anonymousClass1.$hardwareVersion;
                final DeviceBindViewModel deviceBindViewModel = anonymousClass1.this$0;
                FlowCollector<? super T> flowCollector = new FlowCollector() { // from class: com.glasssutdio.wear.ble.connect.DeviceBindViewModel.devicePicture.1.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                        return emit((NetState<DevicePictureResp>) obj, (Continuation<? super Unit>) continuation2);
                    }

                    public final Object emit(NetState<DevicePictureResp> netState, Continuation<? super Unit> continuation2) {
                        if (netState.getRetCode() != 0) {
                            deviceBindViewModel._picUiState.postValue(new DevicePictureUI("", ""));
                        } else {
                            final String str3 = str2 + new DateUtil().getUnixTimestamp();
                            final DevicePictureResp devicePictureRespIsSuccess = netState.isSuccess();
                            DeviceBindViewModel deviceBindViewModel2 = deviceBindViewModel;
                            Intrinsics.checkNotNull(devicePictureRespIsSuccess);
                            DownloadTask downloadTaskCreateGlideTask = deviceBindViewModel2.createGlideTask(devicePictureRespIsSuccess.getPictureUrl(), str3 + ".png");
                            final String str4 = str2;
                            final DeviceBindViewModel deviceBindViewModel3 = deviceBindViewModel;
                            DownloadTaskExtensionKt.enqueue2$default(downloadTaskCreateGlideTask, null, new Function3<DownloadTask, EndCause, Exception, Unit>() { // from class: com.glasssutdio.wear.ble.connect.DeviceBindViewModel.devicePicture.1.1.1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask, EndCause endCause, Exception exc) {
                                    invoke2(downloadTask, endCause, exc);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(DownloadTask downloadTask, EndCause cause, Exception exc) {
                                    Intrinsics.checkNotNullParameter(downloadTask, "<anonymous parameter 0>");
                                    Intrinsics.checkNotNullParameter(cause, "cause");
                                    if (cause == EndCause.COMPLETED) {
                                        DevicePictureBean devicePictureBean2 = new DevicePictureBean(str4, devicePictureRespIsSuccess.getPictureUrl(), GFileUtilKt.getDCIMFile().getPath() + IOUtils.DIR_SEPARATOR_UNIX + str3 + ".png");
                                        deviceBindViewModel3.deviceSettingRepository.saveDevicePicture(devicePictureBean2);
                                        deviceBindViewModel3._picUiState.postValue(new DevicePictureUI(devicePictureBean2.getPictureUrl(), devicePictureBean2.getLocalUrl()));
                                    }
                                }
                            }, 1, null);
                        }
                        return Unit.INSTANCE;
                    }
                };
                deviceBindViewModel$devicePicture$1$1$emit$1.L$0 = null;
                deviceBindViewModel$devicePicture$1$1$emit$1.label = 2;
                if (((Flow) devicePictureFromServer).collect(flowCollector, deviceBindViewModel$devicePicture$1$1$emit$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                return emit((DevicePictureBean) obj, (Continuation<? super Unit>) continuation);
            }
        }
    }

    public final void devicePicture(String hardwareVersion) {
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C08561(hardwareVersion, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DownloadTask createGlideTask(String url, String fileName) {
        DownloadTask downloadTaskBuild = new DownloadTask.Builder(url, GFileUtilKt.getDCIMFile()).setFilename(fileName).setMinIntervalMillisCallbackProcess(64).setPassIfAlreadyCompleted(false).build();
        Intrinsics.checkNotNullExpressionValue(downloadTaskBuild, "build(...)");
        return downloadTaskBuild;
    }

    /* compiled from: DeviceBindViewModel.kt */
    @Metadata(m606d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, m607d2 = {"Lcom/glasssutdio/wear/ble/connect/DeviceBindViewModel$DevicePictureUI;", "", "url", "", "localUrl", "(Ljava/lang/String;Ljava/lang/String;)V", "getLocalUrl", "()Ljava/lang/String;", "getUrl", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final /* data */ class DevicePictureUI {
        private final String localUrl;
        private final String url;

        public static /* synthetic */ DevicePictureUI copy$default(DevicePictureUI devicePictureUI, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = devicePictureUI.url;
            }
            if ((i & 2) != 0) {
                str2 = devicePictureUI.localUrl;
            }
            return devicePictureUI.copy(str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* renamed from: component2, reason: from getter */
        public final String getLocalUrl() {
            return this.localUrl;
        }

        public final DevicePictureUI copy(String url, String localUrl) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(localUrl, "localUrl");
            return new DevicePictureUI(url, localUrl);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DevicePictureUI)) {
                return false;
            }
            DevicePictureUI devicePictureUI = (DevicePictureUI) other;
            return Intrinsics.areEqual(this.url, devicePictureUI.url) && Intrinsics.areEqual(this.localUrl, devicePictureUI.localUrl);
        }

        public int hashCode() {
            return (this.url.hashCode() * 31) + this.localUrl.hashCode();
        }

        public String toString() {
            return "DevicePictureUI(url=" + this.url + ", localUrl=" + this.localUrl + ')';
        }

        public DevicePictureUI(String url, String localUrl) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(localUrl, "localUrl");
            this.url = url;
            this.localUrl = localUrl;
        }

        public final String getLocalUrl() {
            return this.localUrl;
        }

        public final String getUrl() {
            return this.url;
        }
    }
}
