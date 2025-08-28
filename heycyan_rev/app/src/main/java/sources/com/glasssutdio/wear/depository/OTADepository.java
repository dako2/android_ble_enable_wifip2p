package com.glasssutdio.wear.depository;

import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.api.NetState;
import com.glasssutdio.wear.api.QcResponse;
import com.glasssutdio.wear.api.QcResponseKt;
import com.glasssutdio.wear.api.QcRetrofitClient;
import com.glasssutdio.wear.api.QcService;
import com.glasssutdio.wear.api.RetCodeValue;
import com.glasssutdio.wear.api.request.LastOtaRequest;
import com.glasssutdio.wear.api.response.FirmwareOtaResp;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import p000.QcRetrofitChinaClient;

/* compiled from: OTADepository.kt */
@Metadata(m606d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J-\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\nJ-\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, m607d2 = {"Lcom/glasssutdio/wear/depository/OTADepository;", "", "()V", "checkOtaFromServer", "Lkotlinx/coroutines/flow/Flow;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/api/response/FirmwareOtaResp;", "hardwareVersion", "", "romVersion", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkOtaFromServerChina", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class OTADepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<OTADepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<OTADepository>() { // from class: com.glasssutdio.wear.depository.OTADepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final OTADepository invoke() {
            return new OTADepository();
        }
    });

    /* compiled from: OTADepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/api/response/FirmwareOtaResp;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.OTADepository$checkOtaFromServer$2", m620f = "OTADepository.kt", m621i = {1, 2}, m622l = {18, 26, 32, 34}, m623m = "invokeSuspend", m624n = {"$this$flow", "$this$flow"}, m625s = {"L$0", "L$0"})
    /* renamed from: com.glasssutdio.wear.depository.OTADepository$checkOtaFromServer$2 */
    static final class C09492 extends SuspendLambda implements Function2<FlowCollector<? super NetState<FirmwareOtaResp>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hardwareVersion;
        final /* synthetic */ String $romVersion;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09492(String str, String str2, Continuation<? super C09492> continuation) {
            super(2, continuation);
            this.$hardwareVersion = str;
            this.$romVersion = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09492 c09492 = new C09492(this.$hardwareVersion, this.$romVersion, continuation);
            c09492.L$0 = obj;
            return c09492;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09492) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:40:0x00e4 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            FlowCollector flowCollector;
            Object lastOta;
            Object objSuccess;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                flowCollector = (FlowCollector) this.L$0;
                if (this.$hardwareVersion.length() == 0 || this.$romVersion.length() == 0) {
                    this.label = 1;
                    if (flowCollector.emit(new NetState(false, null, RetCodeValue.ErrorCode_1, null, 11, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                int i2 = UserConfig.INSTANCE.getInstance().getDebug() ? 3 : 2;
                QcService qcServiceService = QcRetrofitClient.INSTANCE.service();
                String str = this.$hardwareVersion;
                String str2 = this.$romVersion;
                String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                String country = Locale.getDefault().getCountry();
                Intrinsics.checkNotNullExpressionValue(country, "getCountry(...)");
                this.L$0 = flowCollector;
                this.label = 2;
                lastOta = qcServiceService.getLastOta(new LastOtaRequest(1, 1L, str, str2, 1, deviceAddressNoClear, country, i2), this);
                if (lastOta == coroutine_suspended) {
                    return coroutine_suspended;
                }
                this.L$0 = flowCollector;
                this.label = 3;
                objSuccess = QcResponseKt.success((QcResponse) lastOta, new AnonymousClass1(flowCollector, null), this);
                if (objSuccess == coroutine_suspended) {
                }
            } else {
                if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                if (i == 2) {
                    flowCollector = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    lastOta = obj;
                    this.L$0 = flowCollector;
                    this.label = 3;
                    objSuccess = QcResponseKt.success((QcResponse) lastOta, new AnonymousClass1(flowCollector, null), this);
                    if (objSuccess == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 3) {
                        if (i != 4) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    flowCollector = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    objSuccess = obj;
                }
            }
            this.L$0 = null;
            this.label = 4;
            if (QcResponseKt.error((QcResponse) objSuccess, new AnonymousClass2(flowCollector, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        /* compiled from: OTADepository.kt */
        @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "otaResp", "Lcom/glasssutdio/wear/api/response/FirmwareOtaResp;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.OTADepository$checkOtaFromServer$2$1", m620f = "OTADepository.kt", m621i = {}, m622l = {33}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.OTADepository$checkOtaFromServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, FirmwareOtaResp, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<FirmwareOtaResp>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, FirmwareOtaResp firmwareOtaResp, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = firmwareOtaResp;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    FirmwareOtaResp firmwareOtaResp = (FirmwareOtaResp) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, firmwareOtaResp, 0, null, 9, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* compiled from: OTADepository.kt */
        @Metadata(m606d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", "", "<anonymous parameter 1>", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.OTADepository$checkOtaFromServer$2$2", m620f = "OTADepository.kt", m621i = {}, m622l = {35}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.OTADepository$checkOtaFromServer$2$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function4<CoroutineScope, Integer, String, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<FirmwareOtaResp>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Continuation<? super AnonymousClass2> continuation) {
                super(4, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, String str, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), str, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, String str, Continuation<? super Unit> continuation) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$$this$flow, continuation);
                anonymousClass2.I$0 = i;
                return anonymousClass2.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, null, 11, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final Object checkOtaFromServer(String str, String str2, Continuation<? super Flow<NetState<FirmwareOtaResp>>> continuation) {
        return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C09492(str, str2, null)), new C09503(null)), Dispatchers.getIO()), new C09514(null));
    }

    /* compiled from: OTADepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/api/response/FirmwareOtaResp;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.OTADepository$checkOtaFromServer$3", m620f = "OTADepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.OTADepository$checkOtaFromServer$3 */
    static final class C09503 extends SuspendLambda implements Function2<FlowCollector<? super NetState<FirmwareOtaResp>>, Continuation<? super Unit>, Object> {
        int label;

        C09503(Continuation<? super C09503> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09503(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09503) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: OTADepository.kt */
    @Metadata(m606d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/api/response/FirmwareOtaResp;", "it", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.OTADepository$checkOtaFromServer$4", m620f = "OTADepository.kt", m621i = {}, m622l = {43}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.OTADepository$checkOtaFromServer$4 */
    static final class C09514 extends SuspendLambda implements Function3<FlowCollector<? super NetState<FirmwareOtaResp>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C09514(Continuation<? super C09514> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C09514 c09514 = new C09514(continuation);
            c09514.L$0 = flowCollector;
            return c09514.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(false, null, RetCodeValue.ErrorCode_0, null, 11, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: OTADepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/api/response/FirmwareOtaResp;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.OTADepository$checkOtaFromServerChina$2", m620f = "OTADepository.kt", m621i = {1, 2}, m622l = {49, 57, 63, 65}, m623m = "invokeSuspend", m624n = {"$this$flow", "$this$flow"}, m625s = {"L$0", "L$0"})
    /* renamed from: com.glasssutdio.wear.depository.OTADepository$checkOtaFromServerChina$2 */
    static final class C09522 extends SuspendLambda implements Function2<FlowCollector<? super NetState<FirmwareOtaResp>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hardwareVersion;
        final /* synthetic */ String $romVersion;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09522(String str, String str2, Continuation<? super C09522> continuation) {
            super(2, continuation);
            this.$hardwareVersion = str;
            this.$romVersion = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09522 c09522 = new C09522(this.$hardwareVersion, this.$romVersion, continuation);
            c09522.L$0 = obj;
            return c09522;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09522) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:40:0x00e4 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            FlowCollector flowCollector;
            Object lastOtaChina;
            Object objSuccess;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                flowCollector = (FlowCollector) this.L$0;
                if (this.$hardwareVersion.length() == 0 || this.$romVersion.length() == 0) {
                    this.label = 1;
                    if (flowCollector.emit(new NetState(false, null, RetCodeValue.ErrorCode_1, null, 11, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                int i2 = UserConfig.INSTANCE.getInstance().getDebug() ? 3 : 2;
                QcService service = QcRetrofitChinaClient.INSTANCE.getService();
                String str = this.$hardwareVersion;
                String str2 = this.$romVersion;
                String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                String country = Locale.getDefault().getCountry();
                Intrinsics.checkNotNullExpressionValue(country, "getCountry(...)");
                this.L$0 = flowCollector;
                this.label = 2;
                lastOtaChina = service.getLastOtaChina(new LastOtaRequest(1, 1L, str, str2, 1, deviceAddressNoClear, country, i2), this);
                if (lastOtaChina == coroutine_suspended) {
                    return coroutine_suspended;
                }
                this.L$0 = flowCollector;
                this.label = 3;
                objSuccess = QcResponseKt.success((QcResponse) lastOtaChina, new AnonymousClass1(flowCollector, null), this);
                if (objSuccess == coroutine_suspended) {
                }
            } else {
                if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                if (i == 2) {
                    flowCollector = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    lastOtaChina = obj;
                    this.L$0 = flowCollector;
                    this.label = 3;
                    objSuccess = QcResponseKt.success((QcResponse) lastOtaChina, new AnonymousClass1(flowCollector, null), this);
                    if (objSuccess == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 3) {
                        if (i != 4) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    flowCollector = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    objSuccess = obj;
                }
            }
            this.L$0 = null;
            this.label = 4;
            if (QcResponseKt.error((QcResponse) objSuccess, new AnonymousClass2(flowCollector, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        /* compiled from: OTADepository.kt */
        @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "otaResp", "Lcom/glasssutdio/wear/api/response/FirmwareOtaResp;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.OTADepository$checkOtaFromServerChina$2$1", m620f = "OTADepository.kt", m621i = {}, m622l = {64}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.OTADepository$checkOtaFromServerChina$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, FirmwareOtaResp, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<FirmwareOtaResp>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, FirmwareOtaResp firmwareOtaResp, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = firmwareOtaResp;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    FirmwareOtaResp firmwareOtaResp = (FirmwareOtaResp) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, firmwareOtaResp, 0, null, 9, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* compiled from: OTADepository.kt */
        @Metadata(m606d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", "", "<anonymous parameter 1>", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.OTADepository$checkOtaFromServerChina$2$2", m620f = "OTADepository.kt", m621i = {}, m622l = {66}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.OTADepository$checkOtaFromServerChina$2$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function4<CoroutineScope, Integer, String, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<FirmwareOtaResp>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Continuation<? super AnonymousClass2> continuation) {
                super(4, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, String str, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), str, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, String str, Continuation<? super Unit> continuation) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$$this$flow, continuation);
                anonymousClass2.I$0 = i;
                return anonymousClass2.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, null, 11, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final Object checkOtaFromServerChina(String str, String str2, Continuation<? super Flow<NetState<FirmwareOtaResp>>> continuation) {
        return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C09522(str, str2, null)), new C09533(null)), Dispatchers.getIO()), new C09544(null));
    }

    /* compiled from: OTADepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/api/response/FirmwareOtaResp;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.OTADepository$checkOtaFromServerChina$3", m620f = "OTADepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.OTADepository$checkOtaFromServerChina$3 */
    static final class C09533 extends SuspendLambda implements Function2<FlowCollector<? super NetState<FirmwareOtaResp>>, Continuation<? super Unit>, Object> {
        int label;

        C09533(Continuation<? super C09533> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09533(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09533) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: OTADepository.kt */
    @Metadata(m606d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/api/response/FirmwareOtaResp;", "it", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.OTADepository$checkOtaFromServerChina$4", m620f = "OTADepository.kt", m621i = {}, m622l = {74}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.OTADepository$checkOtaFromServerChina$4 */
    static final class C09544 extends SuspendLambda implements Function3<FlowCollector<? super NetState<FirmwareOtaResp>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C09544(Continuation<? super C09544> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<FirmwareOtaResp>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C09544 c09544 = new C09544(continuation);
            c09544.L$0 = flowCollector;
            return c09544.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(false, null, RetCodeValue.ErrorCode_0, null, 11, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: OTADepository.kt */
    @Metadata(m606d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/depository/OTADepository$Companion;", "", "()V", "getInstance", "Lcom/glasssutdio/wear/depository/OTADepository;", "getGetInstance", "()Lcom/glasssutdio/wear/depository/OTADepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OTADepository getGetInstance() {
            return (OTADepository) OTADepository.getInstance$delegate.getValue();
        }
    }
}
