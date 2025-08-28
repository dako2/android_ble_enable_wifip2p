package com.glasssutdio.wear.depository;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.all.bean.Req.CheckVersionReq;
import com.glasssutdio.wear.all.bean.Req.FeedbackReq;
import com.glasssutdio.wear.all.bean.Req.UpdateUserLocationReq;
import com.glasssutdio.wear.api.NetState;
import com.glasssutdio.wear.api.QcNoDataResponse;
import com.glasssutdio.wear.api.QcNoDataResponseKt;
import com.glasssutdio.wear.api.QcResponse;
import com.glasssutdio.wear.api.QcResponseKt;
import com.glasssutdio.wear.api.QcRetrofitClient;
import com.glasssutdio.wear.api.RetCodeValue;
import com.glasssutdio.wear.home.bean.AppUpgradeModel;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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

/* compiled from: SettingDepository.kt */
@Metadata(m606d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00050\u00042\u0006\u0010\u0007\u001a\u00020\u000bJ%\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00050\u00042\u0006\u0010\r\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ%\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00050\u00042\u0006\u0010\u0007\u001a\u00020\u0011H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, m607d2 = {"Lcom/glasssutdio/wear/depository/SettingDepository;", "", "()V", "appLastVersion", "Lkotlinx/coroutines/flow/Flow;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/home/bean/AppUpgradeModel;", "req", "Lcom/glasssutdio/wear/all/bean/Req/CheckVersionReq;", "feedback", "", "Lcom/glasssutdio/wear/all/bean/Req/FeedbackReq;", "getDeviceScanConfigFromServer", "name", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUserLocation", "", "Lcom/glasssutdio/wear/all/bean/Req/UpdateUserLocationReq;", "(Lcom/glasssutdio/wear/all/bean/Req/UpdateUserLocationReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class SettingDepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<SettingDepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<SettingDepository>() { // from class: com.glasssutdio.wear.depository.SettingDepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SettingDepository invoke() {
            return new SettingDepository();
        }
    });

    /* compiled from: SettingDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$feedback$1", m620f = "SettingDepository.kt", m621i = {0, 1}, m622l = {26, 26, 28}, m623m = "invokeSuspend", m624n = {"$this$flow", "$this$flow"}, m625s = {"L$0", "L$0"})
    /* renamed from: com.glasssutdio.wear.depository.SettingDepository$feedback$1 */
    static final class C09581 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ FeedbackReq $req;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09581(FeedbackReq feedbackReq, Continuation<? super C09581> continuation) {
            super(2, continuation);
            this.$req = feedbackReq;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09581 c09581 = new C09581(this.$req, continuation);
            c09581.L$0 = obj;
            return c09581;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09581) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x007d A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            FlowCollector flowCollector;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector2 = (FlowCollector) this.L$0;
                this.L$0 = flowCollector2;
                this.label = 1;
                Object objFeedback = QcRetrofitClient.INSTANCE.service().feedback(this.$req, this);
                if (objFeedback == coroutine_suspended) {
                    return coroutine_suspended;
                }
                flowCollector = flowCollector2;
                obj = objFeedback;
            } else {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    flowCollector = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    this.L$0 = null;
                    this.label = 3;
                    if (QcResponseKt.error((QcResponse) obj, new AnonymousClass2(flowCollector, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                flowCollector = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            this.L$0 = flowCollector;
            this.label = 2;
            obj = QcResponseKt.success((QcResponse) obj, new AnonymousClass1(flowCollector, null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            this.L$0 = null;
            this.label = 3;
            if (QcResponseKt.error((QcResponse) obj, new AnonymousClass2(flowCollector, null), this) == coroutine_suspended) {
            }
            return Unit.INSTANCE;
        }

        /* compiled from: SettingDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "res", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$feedback$1$1", m620f = "SettingDepository.kt", m621i = {}, m622l = {27}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.SettingDepository$feedback$1$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, String, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<String>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, String str, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = str;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    String str = (String) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, str, 0, null, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: SettingDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", "", NotificationCompat.CATEGORY_MESSAGE, ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$feedback$1$2", m620f = "SettingDepository.kt", m621i = {}, m622l = {ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.SettingDepository$feedback$1$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function4<CoroutineScope, Integer, String, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<String>> $$this$flow;
            /* synthetic */ int I$0;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super AnonymousClass2> continuation) {
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
                anonymousClass2.L$0 = str;
                return anonymousClass2.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    String str = (String) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, str, 3, null), this) == coroutine_suspended) {
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

    public final Flow<NetState<String>> feedback(FeedbackReq req) {
        Intrinsics.checkNotNullParameter(req, "req");
        return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C09581(req, null)), new C09592(null)), Dispatchers.getIO()), new C09603(null));
    }

    /* compiled from: SettingDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$feedback$2", m620f = "SettingDepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.SettingDepository$feedback$2 */
    static final class C09592 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
        int label;

        C09592(Continuation<? super C09592> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09592(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09592) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: SettingDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "", "it", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$feedback$3", m620f = "SettingDepository.kt", m621i = {}, m622l = {36}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.SettingDepository$feedback$3 */
    static final class C09603 extends SuspendLambda implements Function3<FlowCollector<? super NetState<String>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C09603(Continuation<? super C09603> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C09603 c09603 = new C09603(continuation);
            c09603.L$0 = flowCollector;
            c09603.L$1 = th;
            return c09603.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                XLog.m136i((Throwable) this.L$1);
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(new NetState(false, null, RetCodeValue.ErrorCode_0, null, 11, null), this) == coroutine_suspended) {
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

    /* compiled from: SettingDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/home/bean/AppUpgradeModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$appLastVersion$1", m620f = "SettingDepository.kt", m621i = {0, 1}, m622l = {42, 42, 44}, m623m = "invokeSuspend", m624n = {"$this$flow", "$this$flow"}, m625s = {"L$0", "L$0"})
    /* renamed from: com.glasssutdio.wear.depository.SettingDepository$appLastVersion$1 */
    static final class C09551 extends SuspendLambda implements Function2<FlowCollector<? super NetState<AppUpgradeModel>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ CheckVersionReq $req;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09551(CheckVersionReq checkVersionReq, Continuation<? super C09551> continuation) {
            super(2, continuation);
            this.$req = checkVersionReq;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09551 c09551 = new C09551(this.$req, continuation);
            c09551.L$0 = obj;
            return c09551;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<AppUpgradeModel>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09551) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x007d A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            FlowCollector flowCollector;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector2 = (FlowCollector) this.L$0;
                this.L$0 = flowCollector2;
                this.label = 1;
                Object objAppLastVersion = QcRetrofitClient.INSTANCE.service().appLastVersion(this.$req, this);
                if (objAppLastVersion == coroutine_suspended) {
                    return coroutine_suspended;
                }
                flowCollector = flowCollector2;
                obj = objAppLastVersion;
            } else {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    flowCollector = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    this.L$0 = null;
                    this.label = 3;
                    if (QcResponseKt.error((QcResponse) obj, new AnonymousClass2(flowCollector, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                flowCollector = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            this.L$0 = flowCollector;
            this.label = 2;
            obj = QcResponseKt.success((QcResponse) obj, new AnonymousClass1(flowCollector, null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            this.L$0 = null;
            this.label = 3;
            if (QcResponseKt.error((QcResponse) obj, new AnonymousClass2(flowCollector, null), this) == coroutine_suspended) {
            }
            return Unit.INSTANCE;
        }

        /* compiled from: SettingDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "res", "Lcom/glasssutdio/wear/home/bean/AppUpgradeModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$appLastVersion$1$1", m620f = "SettingDepository.kt", m621i = {}, m622l = {43}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.SettingDepository$appLastVersion$1$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, AppUpgradeModel, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<AppUpgradeModel>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<AppUpgradeModel>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, AppUpgradeModel appUpgradeModel, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = appUpgradeModel;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    AppUpgradeModel appUpgradeModel = (AppUpgradeModel) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, appUpgradeModel, 0, null, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: SettingDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", "", NotificationCompat.CATEGORY_MESSAGE, ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$appLastVersion$1$2", m620f = "SettingDepository.kt", m621i = {}, m622l = {45}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.SettingDepository$appLastVersion$1$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function4<CoroutineScope, Integer, String, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<AppUpgradeModel>> $$this$flow;
            /* synthetic */ int I$0;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(FlowCollector<? super NetState<AppUpgradeModel>> flowCollector, Continuation<? super AnonymousClass2> continuation) {
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
                anonymousClass2.L$0 = str;
                return anonymousClass2.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    String str = (String) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, str, 3, null), this) == coroutine_suspended) {
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

    public final Flow<NetState<AppUpgradeModel>> appLastVersion(CheckVersionReq req) {
        Intrinsics.checkNotNullParameter(req, "req");
        return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C09551(req, null)), new C09562(null)), Dispatchers.getIO()), new C09573(null));
    }

    /* compiled from: SettingDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/home/bean/AppUpgradeModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$appLastVersion$2", m620f = "SettingDepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.SettingDepository$appLastVersion$2 */
    static final class C09562 extends SuspendLambda implements Function2<FlowCollector<? super NetState<AppUpgradeModel>>, Continuation<? super Unit>, Object> {
        int label;

        C09562(Continuation<? super C09562> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09562(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<AppUpgradeModel>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09562) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: SettingDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/home/bean/AppUpgradeModel;", "it", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$appLastVersion$3", m620f = "SettingDepository.kt", m621i = {}, m622l = {52}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.SettingDepository$appLastVersion$3 */
    static final class C09573 extends SuspendLambda implements Function3<FlowCollector<? super NetState<AppUpgradeModel>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C09573(Continuation<? super C09573> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<AppUpgradeModel>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C09573 c09573 = new C09573(continuation);
            c09573.L$0 = flowCollector;
            c09573.L$1 = th;
            return c09573.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                XLog.m136i((Throwable) this.L$1);
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(new NetState(false, null, RetCodeValue.ErrorCode_0, null, 11, null), this) == coroutine_suspended) {
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

    /* compiled from: SettingDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$getDeviceScanConfigFromServer$2", m620f = "SettingDepository.kt", m621i = {0, 1}, m622l = {59, 59, 61}, m623m = "invokeSuspend", m624n = {"$this$flow", "$this$flow"}, m625s = {"L$0", "L$0"})
    /* renamed from: com.glasssutdio.wear.depository.SettingDepository$getDeviceScanConfigFromServer$2 */
    static final class C09612 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $name;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09612(String str, Continuation<? super C09612> continuation) {
            super(2, continuation);
            this.$name = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09612 c09612 = new C09612(this.$name, continuation);
            c09612.L$0 = obj;
            return c09612;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09612) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x007d A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            FlowCollector flowCollector;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector2 = (FlowCollector) this.L$0;
                this.L$0 = flowCollector2;
                this.label = 1;
                Object objScanConfig = QcRetrofitClient.INSTANCE.service().scanConfig(this.$name, this);
                if (objScanConfig == coroutine_suspended) {
                    return coroutine_suspended;
                }
                flowCollector = flowCollector2;
                obj = objScanConfig;
            } else {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    flowCollector = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    this.L$0 = null;
                    this.label = 3;
                    if (QcResponseKt.error((QcResponse) obj, new AnonymousClass2(flowCollector, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                flowCollector = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            this.L$0 = flowCollector;
            this.label = 2;
            obj = QcResponseKt.success((QcResponse) obj, new AnonymousClass1(flowCollector, null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            this.L$0 = null;
            this.label = 3;
            if (QcResponseKt.error((QcResponse) obj, new AnonymousClass2(flowCollector, null), this) == coroutine_suspended) {
            }
            return Unit.INSTANCE;
        }

        /* compiled from: SettingDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$getDeviceScanConfigFromServer$2$1", m620f = "SettingDepository.kt", m621i = {}, m622l = {60}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.SettingDepository$getDeviceScanConfigFromServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, String, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<String>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, String str, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = str;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    String str = (String) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, str, 0, null, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: SettingDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", "", NotificationCompat.CATEGORY_MESSAGE, ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$getDeviceScanConfigFromServer$2$2", m620f = "SettingDepository.kt", m621i = {}, m622l = {62}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.SettingDepository$getDeviceScanConfigFromServer$2$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function4<CoroutineScope, Integer, String, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<String>> $$this$flow;
            /* synthetic */ int I$0;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super AnonymousClass2> continuation) {
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
                anonymousClass2.L$0 = str;
                return anonymousClass2.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    String str = (String) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, str, 3, null), this) == coroutine_suspended) {
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

    public final Object getDeviceScanConfigFromServer(String str, Continuation<? super Flow<NetState<String>>> continuation) {
        return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C09612(str, null)), new C09623(null)), Dispatchers.getIO()), new C09634(null));
    }

    /* compiled from: SettingDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$getDeviceScanConfigFromServer$3", m620f = "SettingDepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.SettingDepository$getDeviceScanConfigFromServer$3 */
    static final class C09623 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
        int label;

        C09623(Continuation<? super C09623> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09623(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09623) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: SettingDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "", "it", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$getDeviceScanConfigFromServer$4", m620f = "SettingDepository.kt", m621i = {}, m622l = {70}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.SettingDepository$getDeviceScanConfigFromServer$4 */
    static final class C09634 extends SuspendLambda implements Function3<FlowCollector<? super NetState<String>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C09634(Continuation<? super C09634> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C09634 c09634 = new C09634(continuation);
            c09634.L$0 = flowCollector;
            return c09634.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: SettingDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$updateUserLocation$2", m620f = "SettingDepository.kt", m621i = {0}, m622l = {EMachine.EM_JAVELIN, EMachine.EM_JAVELIN}, m623m = "invokeSuspend", m624n = {"$this$flow"}, m625s = {"L$0"})
    /* renamed from: com.glasssutdio.wear.depository.SettingDepository$updateUserLocation$2 */
    static final class C09642 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ UpdateUserLocationReq $req;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09642(UpdateUserLocationReq updateUserLocationReq, Continuation<? super C09642> continuation) {
            super(2, continuation);
            this.$req = updateUserLocationReq;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09642 c09642 = new C09642(this.$req, continuation);
            c09642.L$0 = obj;
            return c09642;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09642) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            FlowCollector flowCollector;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                flowCollector = (FlowCollector) this.L$0;
                this.L$0 = flowCollector;
                this.label = 1;
                obj = QcRetrofitClient.INSTANCE.service().updateLocation(this.$req, this);
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
                flowCollector = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            this.L$0 = null;
            this.label = 2;
            if (QcNoDataResponseKt.success((QcNoDataResponse) obj, new AnonymousClass1(flowCollector, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        /* compiled from: SettingDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "res", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$updateUserLocation$2$1", m620f = "SettingDepository.kt", m621i = {}, m622l = {EMachine.EM_FIREPATH}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.SettingDepository$updateUserLocation$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.I$0 = i;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, Boxing.boxInt(i2), 0, null, 9, null), this) == coroutine_suspended) {
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

    public final Object updateUserLocation(UpdateUserLocationReq updateUserLocationReq, Continuation<? super Flow<NetState<Integer>>> continuation) {
        return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C09642(updateUserLocationReq, null)), new C09653(null)), Dispatchers.getIO()), new C09664(null));
    }

    /* compiled from: SettingDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$updateUserLocation$3", m620f = "SettingDepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.SettingDepository$updateUserLocation$3 */
    static final class C09653 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        int label;

        C09653(Continuation<? super C09653> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09653(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09653) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: SettingDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "", "it", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.SettingDepository$updateUserLocation$4", m620f = "SettingDepository.kt", m621i = {}, m622l = {EMachine.EM_D10V}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.SettingDepository$updateUserLocation$4 */
    static final class C09664 extends SuspendLambda implements Function3<FlowCollector<? super NetState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C09664(Continuation<? super C09664> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C09664 c09664 = new C09664(continuation);
            c09664.L$0 = flowCollector;
            c09664.L$1 = th;
            return c09664.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                XLog.m136i((Throwable) this.L$1);
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(new NetState(false, null, RetCodeValue.ErrorCode_0, null, 11, null), this) == coroutine_suspended) {
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

    /* compiled from: SettingDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/depository/SettingDepository$Companion;", "", "()V", "getInstance", "Lcom/glasssutdio/wear/depository/SettingDepository;", "getGetInstance", "()Lcom/glasssutdio/wear/depository/SettingDepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SettingDepository getGetInstance() {
            return (SettingDepository) SettingDepository.getInstance$delegate.getValue();
        }
    }
}
