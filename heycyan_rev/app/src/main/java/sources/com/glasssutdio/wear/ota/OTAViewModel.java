package com.glasssutdio.wear.ota;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANConstants;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.GFileUtilKt;
import com.glasssutdio.wear.api.NetState;
import com.glasssutdio.wear.api.response.FirmwareOtaResp;
import com.glasssutdio.wear.bus.OTAFileStatusEvent;
import com.glasssutdio.wear.depository.OTADepository;
import com.glasssutdio.wear.ota.OTAViewModel;
import com.glasssutdio.wear.ota.bean.DFUInformationBean;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.greenrobot.eventbus.EventBus;

/* compiled from: OTAViewModel.kt */
@Metadata(m606d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0001\"B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u001b\u001a\u00020\u0017J\u001e\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u0019H\u0002J\u0016\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0011R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000b8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006#"}, m607d2 = {"Lcom/glasssutdio/wear/ota/OTAViewModel;", "Landroidx/lifecycle/ViewModel;", "otaRepository", "Lcom/glasssutdio/wear/depository/OTADepository;", "(Lcom/glasssutdio/wear/depository/OTADepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/glasssutdio/wear/ota/OTAViewModel$FirmwareUI;", "_updateUiInfo", "Lcom/glasssutdio/wear/api/response/FirmwareOtaResp;", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "updateUiInfo", "getUpdateUiInfo", "wifiNotBle", "", "getWifiNotBle", "()Z", "setWifiNotBle", "(Z)V", "checkOta", "", "hwName", "", "fmVersion", "checkOtaChina", "checkOtaDown", "downloadOtaBin", "url", "fileName", "saveDeviceDfuInformation", "firmwareOtaResp", "FirmwareUI", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class OTAViewModel extends ViewModel {
    private final MutableLiveData<FirmwareUI> _uiState;
    private final MutableLiveData<FirmwareOtaResp> _updateUiInfo;
    private final OTADepository otaRepository;
    private boolean wifiNotBle;

    public OTAViewModel(OTADepository otaRepository) {
        Intrinsics.checkNotNullParameter(otaRepository, "otaRepository");
        this.otaRepository = otaRepository;
        this._uiState = new MutableLiveData<>();
        this._updateUiInfo = new MutableLiveData<>();
    }

    public final LiveData<FirmwareUI> getUiState() {
        return this._uiState;
    }

    public final LiveData<FirmwareOtaResp> getUpdateUiInfo() {
        return this._updateUiInfo;
    }

    public final boolean getWifiNotBle() {
        return this.wifiNotBle;
    }

    public final void setWifiNotBle(boolean z) {
        this.wifiNotBle = z;
    }

    /* compiled from: OTAViewModel.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.ota.OTAViewModel$checkOta$1", m620f = "OTAViewModel.kt", m621i = {}, m622l = {39, 42}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.ota.OTAViewModel$checkOta$1 */
    static final class C11211 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $fmVersion;
        final /* synthetic */ String $hwName;
        final /* synthetic */ boolean $wifiNotBle;
        Object L$0;
        boolean Z$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C11211(String str, String str2, boolean z, Continuation<? super C11211> continuation) {
            super(2, continuation);
            this.$hwName = str;
            this.$fmVersion = str2;
            this.$wifiNotBle = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return OTAViewModel.this.new C11211(this.$hwName, this.$fmVersion, this.$wifiNotBle, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C11211) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final OTAViewModel oTAViewModel;
            final boolean z;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                OTADepository oTADepository = OTAViewModel.this.otaRepository;
                String str = this.$hwName;
                String str2 = this.$fmVersion;
                OTAViewModel oTAViewModel2 = OTAViewModel.this;
                boolean z2 = this.$wifiNotBle;
                this.L$0 = oTAViewModel2;
                this.Z$0 = z2;
                this.label = 1;
                obj = oTADepository.checkOtaFromServer(str, str2, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                oTAViewModel = oTAViewModel2;
                z = z2;
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                z = this.Z$0;
                oTAViewModel = (OTAViewModel) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            FlowCollector flowCollector = new FlowCollector() { // from class: com.glasssutdio.wear.ota.OTAViewModel$checkOta$1$1$1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<FirmwareOtaResp>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<FirmwareOtaResp> netState, Continuation<? super Unit> continuation) {
                    if (netState.getRetCode() == 0) {
                        FirmwareOtaResp firmwareOtaRespIsSuccess = netState.isSuccess();
                        if (firmwareOtaRespIsSuccess != null) {
                            oTAViewModel.saveDeviceDfuInformation(firmwareOtaRespIsSuccess, z);
                        }
                        MutableLiveData mutableLiveData = oTAViewModel._updateUiInfo;
                        FirmwareOtaResp firmwareOtaRespIsSuccess2 = netState.isSuccess();
                        Intrinsics.checkNotNull(firmwareOtaRespIsSuccess2);
                        mutableLiveData.postValue(firmwareOtaRespIsSuccess2);
                    } else if (z) {
                        oTAViewModel.checkOta(UserConfig.INSTANCE.getInstance().getHwVersion(), UserConfig.INSTANCE.getInstance().getFmVersion(), false);
                    } else {
                        oTAViewModel._uiState.postValue(new OTAViewModel.FirmwareUI(netState.getRetCode(), 0L, "", false, 8, null));
                    }
                    return Unit.INSTANCE;
                }
            };
            this.L$0 = null;
            this.label = 2;
            if (((Flow) obj).collect(flowCollector, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void checkOta(String hwName, String fmVersion, boolean wifiNotBle) {
        Intrinsics.checkNotNullParameter(hwName, "hwName");
        Intrinsics.checkNotNullParameter(fmVersion, "fmVersion");
        this.wifiNotBle = wifiNotBle;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C11211(hwName, fmVersion, wifiNotBle, null), 3, null);
    }

    public final void checkOtaDown(String hwName, String fmVersion, boolean wifiNotBle) {
        Intrinsics.checkNotNullParameter(hwName, "hwName");
        Intrinsics.checkNotNullParameter(fmVersion, "fmVersion");
        this.wifiNotBle = wifiNotBle;
        FirmwareOtaResp firmwareOtaResp = new FirmwareOtaResp();
        firmwareOtaResp.setVersion("WIFIA03_0.13.12_2504071600");
        firmwareOtaResp.setOs(1);
        firmwareOtaResp.setEnforceUpdate("1");
        firmwareOtaResp.setDownloadUrl("https://qcwxfactory.oss-cn-beijing.aliyuncs.com/bin/glasses/" + UserConfig.INSTANCE.getInstance().getHwVersionWifi() + ".swu");
        firmwareOtaResp.setOpenOrNot(3);
        firmwareOtaResp.setHardwareVersion("WIFIA03_V2.0");
        firmwareOtaResp.setEnforceUpdateFrom("");
        firmwareOtaResp.setEnforceUpdateTo("");
        firmwareOtaResp.setUploadDate("2025-04-30 15:52:52");
        saveDeviceDfuInformation(firmwareOtaResp, wifiNotBle);
        this._updateUiInfo.postValue(firmwareOtaResp);
        XLog.m137i("-------");
    }

    /* compiled from: OTAViewModel.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.ota.OTAViewModel$checkOtaChina$1", m620f = "OTAViewModel.kt", m621i = {}, m622l = {EMachine.EM_OPENRISC, EMachine.EM_VIDEOCORE}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.ota.OTAViewModel$checkOtaChina$1 */
    static final class C11221 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        C11221(Continuation<? super C11221> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return OTAViewModel.this.new C11221(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C11221) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final OTAViewModel oTAViewModel;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                OTADepository oTADepository = OTAViewModel.this.otaRepository;
                oTAViewModel = OTAViewModel.this;
                String hwVersion = UserConfig.INSTANCE.getInstance().getHwVersion();
                String fmVersion = UserConfig.INSTANCE.getInstance().getFmVersion();
                this.L$0 = oTAViewModel;
                this.label = 1;
                obj = oTADepository.checkOtaFromServerChina(hwVersion, fmVersion, this);
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
                oTAViewModel = (OTAViewModel) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            FlowCollector flowCollector = new FlowCollector() { // from class: com.glasssutdio.wear.ota.OTAViewModel$checkOtaChina$1$1$1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<FirmwareOtaResp>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<FirmwareOtaResp> netState, Continuation<? super Unit> continuation) {
                    MutableLiveData mutableLiveData = oTAViewModel._updateUiInfo;
                    FirmwareOtaResp firmwareOtaRespIsSuccess = netState.isSuccess();
                    Intrinsics.checkNotNull(firmwareOtaRespIsSuccess);
                    mutableLiveData.postValue(firmwareOtaRespIsSuccess);
                    return Unit.INSTANCE;
                }
            };
            this.L$0 = null;
            this.label = 2;
            if (((Flow) obj).collect(flowCollector, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void checkOtaChina() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C11221(null), 3, null);
    }

    public final void saveDeviceDfuInformation(FirmwareOtaResp firmwareOtaResp, boolean wifiNotBle) {
        Intrinsics.checkNotNullParameter(firmwareOtaResp, "firmwareOtaResp");
        DFUInformationBean dFUInformationBean = new DFUInformationBean();
        dFUInformationBean.setHardwareVersion(firmwareOtaResp.getHardwareVersion());
        dFUInformationBean.setLastVersion(firmwareOtaResp.getVersion());
        if (firmwareOtaResp.getIsEnforceUpdate().length() == 0) {
            dFUInformationBean.setEnforceUpdate(1);
        }
        dFUInformationBean.setOpenOrNot(firmwareOtaResp.getOpenOrNot());
        dFUInformationBean.setDownloadUrl(firmwareOtaResp.getDownloadUrl());
        if (dFUInformationBean.getLastVersion().length() > 0) {
            int openOrNot = dFUInformationBean.getOpenOrNot();
            boolean debug = UserConfig.INSTANCE.getInstance().getDebug();
            String str = dFUInformationBean.getLastVersion() + ".bin";
            if (wifiNotBle) {
                str = dFUInformationBean.getLastVersion() + ".swu";
            }
            if (debug) {
                if (openOrNot == 3) {
                    downloadOtaBin(dFUInformationBean.getDownloadUrl(), str);
                }
            } else if (openOrNot == 2) {
                downloadOtaBin(dFUInformationBean.getDownloadUrl(), str);
            }
        }
    }

    private final void downloadOtaBin(String url, final String fileName) {
        File binDirFile = GFileUtilKt.getBinDirFile();
        if (GFileUtilKt.fileExists(binDirFile.getAbsolutePath() + IOUtils.DIR_SEPARATOR_UNIX + fileName)) {
            GFileUtilKt.deleteFile(binDirFile.getAbsolutePath() + IOUtils.DIR_SEPARATOR_UNIX + fileName);
        }
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        AndroidNetworking.download(url, binDirFile.getAbsolutePath(), fileName).setTag((Object) fileName).setPriority(Priority.MEDIUM).build().setDownloadProgressListener(new DownloadProgressListener() { // from class: com.glasssutdio.wear.ota.OTAViewModel$$ExternalSyntheticLambda0
            @Override // com.androidnetworking.interfaces.DownloadProgressListener
            public final void onProgress(long j, long j2) {
                OTAViewModel.downloadOtaBin$lambda$0(intRef, this, fileName, j, j2);
            }
        }).startDownload(new DownloadListener() { // from class: com.glasssutdio.wear.ota.OTAViewModel.downloadOtaBin.2
            @Override // com.androidnetworking.interfaces.DownloadListener
            public void onDownloadComplete() {
                XLog.m137i("download dfu bin success");
                OTAViewModel.this._uiState.postValue(new FirmwareUI(0, 100L, fileName, true));
                EventBus.getDefault().post(new OTAFileStatusEvent(3, 100, true, fileName));
            }

            @Override // com.androidnetworking.interfaces.DownloadListener
            public void onError(ANError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                XLog.m137i(String.valueOf(error.getErrorCode()));
                XLog.m137i(error.getErrorDetail());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void downloadOtaBin$lambda$0(Ref.IntRef lastProgress, OTAViewModel this$0, String fileName, long j, long j2) {
        Intrinsics.checkNotNullParameter(lastProgress, "$lastProgress");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fileName, "$fileName");
        int i = (int) ((100 * j) / j2);
        if (i <= lastProgress.element || i - lastProgress.element != 1) {
            return;
        }
        XLog.m137i("下载进度: " + j + " -- " + j2 + " (" + i + "%)");
        lastProgress.element = i;
        this$0._uiState.postValue(new FirmwareUI(0, i, fileName, false));
        EventBus.getDefault().post(new OTAFileStatusEvent(3, i, false, fileName));
    }

    /* compiled from: OTAViewModel.kt */
    @Metadata(m606d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001b\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\tHÆ\u0003J1\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010 \u001a\u00020\t2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J\t\u0010#\u001a\u00020\u0007HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006$"}, m607d2 = {"Lcom/glasssutdio/wear/ota/OTAViewModel$FirmwareUI;", "", "retCode", "", "progressBar", "", "fileName", "", ANConstants.SUCCESS, "", "(IJLjava/lang/String;Z)V", "getFileName", "()Ljava/lang/String;", "setFileName", "(Ljava/lang/String;)V", "getProgressBar", "()J", "setProgressBar", "(J)V", "getRetCode", "()I", "setRetCode", "(I)V", "getSuccess", "()Z", "setSuccess", "(Z)V", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final /* data */ class FirmwareUI {
        private String fileName;
        private long progressBar;
        private int retCode;
        private boolean success;

        public FirmwareUI() {
            this(0, 0L, null, false, 15, null);
        }

        public static /* synthetic */ FirmwareUI copy$default(FirmwareUI firmwareUI, int i, long j, String str, boolean z, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = firmwareUI.retCode;
            }
            if ((i2 & 2) != 0) {
                j = firmwareUI.progressBar;
            }
            long j2 = j;
            if ((i2 & 4) != 0) {
                str = firmwareUI.fileName;
            }
            String str2 = str;
            if ((i2 & 8) != 0) {
                z = firmwareUI.success;
            }
            return firmwareUI.copy(i, j2, str2, z);
        }

        /* renamed from: component1, reason: from getter */
        public final int getRetCode() {
            return this.retCode;
        }

        /* renamed from: component2, reason: from getter */
        public final long getProgressBar() {
            return this.progressBar;
        }

        /* renamed from: component3, reason: from getter */
        public final String getFileName() {
            return this.fileName;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getSuccess() {
            return this.success;
        }

        public final FirmwareUI copy(int retCode, long progressBar, String fileName, boolean success) {
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            return new FirmwareUI(retCode, progressBar, fileName, success);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FirmwareUI)) {
                return false;
            }
            FirmwareUI firmwareUI = (FirmwareUI) other;
            return this.retCode == firmwareUI.retCode && this.progressBar == firmwareUI.progressBar && Intrinsics.areEqual(this.fileName, firmwareUI.fileName) && this.success == firmwareUI.success;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int iHashCode = ((((Integer.hashCode(this.retCode) * 31) + Long.hashCode(this.progressBar)) * 31) + this.fileName.hashCode()) * 31;
            boolean z = this.success;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return iHashCode + i;
        }

        public String toString() {
            return "FirmwareUI(retCode=" + this.retCode + ", progressBar=" + this.progressBar + ", fileName=" + this.fileName + ", success=" + this.success + ')';
        }

        public FirmwareUI(int i, long j, String fileName, boolean z) {
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            this.retCode = i;
            this.progressBar = j;
            this.fileName = fileName;
            this.success = z;
        }

        public final int getRetCode() {
            return this.retCode;
        }

        public final void setRetCode(int i) {
            this.retCode = i;
        }

        public final long getProgressBar() {
            return this.progressBar;
        }

        public final void setProgressBar(long j) {
            this.progressBar = j;
        }

        public /* synthetic */ FirmwareUI(int i, long j, String str, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? 0L : j, (i2 & 4) != 0 ? "" : str, (i2 & 8) != 0 ? false : z);
        }

        public final String getFileName() {
            return this.fileName;
        }

        public final void setFileName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.fileName = str;
        }

        public final boolean getSuccess() {
            return this.success;
        }

        public final void setSuccess(boolean z) {
            this.success = z;
        }
    }
}
