package com.glasssutdio.wear.depository;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.bean.Req.LoginReq;
import com.glasssutdio.wear.all.bean.Req.ResetPwdReq;
import com.glasssutdio.wear.all.bean.Req.UpdateUserReq;
import com.glasssutdio.wear.all.bean.ResetPwdModel;
import com.glasssutdio.wear.api.NetState;
import com.glasssutdio.wear.api.QcResponse;
import com.glasssutdio.wear.api.QcResponseKt;
import com.glasssutdio.wear.api.QcRetrofitClient;
import com.glasssutdio.wear.api.RetCodeValue;
import com.glasssutdio.wear.home.album.water.WatermarkGenerator;
import com.glasssutdio.wear.home.bean.LoginResModel;
import com.glasssutdio.wear.home.bean.UserModel;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import okhttp3.RequestBody;

/* compiled from: LoginDepository.kt */
@Metadata(m606d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000  2\u00020\u0001:\u0001 B\u0005¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ-\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00050\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\fJ%\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00050\u00042\u0006\u0010\u000f\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ%\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00050\u00042\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J%\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00050\u00042\u0006\u0010\u000f\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ%\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00050\u00042\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J%\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0012\u001a\u00020\u0018H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J%\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00050\u00042\u0006\u0010\u0012\u001a\u00020\u001bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ%\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00050\u00042\u0006\u0010\u0012\u001a\u00020\u001eH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, m607d2 = {"Lcom/glasssutdio/wear/depository/LoginDepository;", "", "()V", "emailSendCode", "Lkotlinx/coroutines/flow/Flow;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/all/bean/ResetPwdModel;", "email", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRegisterCode", "appName", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserInfo", "Lcom/glasssutdio/wear/home/bean/UserModel;", "uid", "login", "Lcom/glasssutdio/wear/home/bean/LoginResModel;", "req", "Lcom/glasssutdio/wear/all/bean/Req/LoginReq;", "(Lcom/glasssutdio/wear/all/bean/Req/LoginReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logoff", "registerByEmail", "resetPassword", "Lcom/glasssutdio/wear/all/bean/Req/ResetPwdReq;", "(Lcom/glasssutdio/wear/all/bean/Req/ResetPwdReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUserInfo", "Lcom/glasssutdio/wear/all/bean/Req/UpdateUserReq;", "(Lcom/glasssutdio/wear/all/bean/Req/UpdateUserReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadImg", "Lokhttp3/RequestBody;", "(Lokhttp3/RequestBody;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class LoginDepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<LoginDepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<LoginDepository>() { // from class: com.glasssutdio.wear.depository.LoginDepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LoginDepository invoke() {
            return new LoginDepository();
        }
    });

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/home/bean/LoginResModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$registerByEmail$2", m620f = "LoginDepository.kt", m621i = {0, 1}, m622l = {30, 30, 32}, m623m = "invokeSuspend", m624n = {"$this$flow", "$this$flow"}, m625s = {"L$0", "L$0"})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$registerByEmail$2 */
    static final class C09372 extends SuspendLambda implements Function2<FlowCollector<? super NetState<LoginResModel>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ LoginReq $req;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09372(LoginReq loginReq, Continuation<? super C09372> continuation) {
            super(2, continuation);
            this.$req = loginReq;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09372 c09372 = new C09372(this.$req, continuation);
            c09372.L$0 = obj;
            return c09372;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<LoginResModel>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09372) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
                Object objRegisterByEmail = QcRetrofitClient.INSTANCE.service().registerByEmail(this.$req, this);
                if (objRegisterByEmail == coroutine_suspended) {
                    return coroutine_suspended;
                }
                flowCollector = flowCollector2;
                obj = objRegisterByEmail;
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

        /* compiled from: LoginDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "res", "Lcom/glasssutdio/wear/home/bean/LoginResModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$registerByEmail$2$1", m620f = "LoginDepository.kt", m621i = {}, m622l = {ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.LoginDepository$registerByEmail$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, LoginResModel, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<LoginResModel>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<LoginResModel>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, LoginResModel loginResModel, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = loginResModel;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    LoginResModel loginResModel = (LoginResModel) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, loginResModel, 0, null, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: LoginDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", "", NotificationCompat.CATEGORY_MESSAGE, ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$registerByEmail$2$2", m620f = "LoginDepository.kt", m621i = {}, m622l = {33}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.LoginDepository$registerByEmail$2$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function4<CoroutineScope, Integer, String, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<LoginResModel>> $$this$flow;
            /* synthetic */ int I$0;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(FlowCollector<? super NetState<LoginResModel>> flowCollector, Continuation<? super AnonymousClass2> continuation) {
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

    public final Object registerByEmail(LoginReq loginReq, Continuation<? super Flow<NetState<LoginResModel>>> continuation) {
        return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C09372(loginReq, null)), new C09383(null)), Dispatchers.getIO()), new C09394(null));
    }

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/home/bean/LoginResModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$registerByEmail$3", m620f = "LoginDepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$registerByEmail$3 */
    static final class C09383 extends SuspendLambda implements Function2<FlowCollector<? super NetState<LoginResModel>>, Continuation<? super Unit>, Object> {
        int label;

        C09383(Continuation<? super C09383> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09383(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<LoginResModel>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09383) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/home/bean/LoginResModel;", "it", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$registerByEmail$4", m620f = "LoginDepository.kt", m621i = {}, m622l = {40}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$registerByEmail$4 */
    static final class C09394 extends SuspendLambda implements Function3<FlowCollector<? super NetState<LoginResModel>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C09394(Continuation<? super C09394> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<LoginResModel>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C09394 c09394 = new C09394(continuation);
            c09394.L$0 = flowCollector;
            c09394.L$1 = th;
            return c09394.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/home/bean/LoginResModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$login$2", m620f = "LoginDepository.kt", m621i = {0, 1, 2, 3}, m622l = {46, 46, 48, 52}, m623m = "invokeSuspend", m624n = {"$this$flow", "$this$flow", "$this$flow", "e"}, m625s = {"L$0", "L$0", "L$0", "L$0"})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$login$2 */
    static final class C09312 extends SuspendLambda implements Function2<FlowCollector<? super NetState<LoginResModel>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ LoginReq $req;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09312(LoginReq loginReq, Continuation<? super C09312> continuation) {
            super(2, continuation);
            this.$req = loginReq;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09312 c09312 = new C09312(this.$req, continuation);
            c09312.L$0 = obj;
            return c09312;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<LoginResModel>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09312) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x008a A[RETURN] */
        /* JADX WARN: Type inference failed for: r1v0, types: [int, kotlinx.coroutines.flow.FlowCollector] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Exception exc;
            FlowCollector flowCollector;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ?? r1 = this.label;
            try {
            } catch (Exception e) {
                this.L$0 = e;
                this.label = 4;
                if (r1.emit(new NetState(false, null, -1, GlobalKt.getString(C0775R.string.request_glass_2), 3, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                exc = e;
            }
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                flowCollector = (FlowCollector) this.L$0;
                this.L$0 = flowCollector;
                this.label = 1;
                obj = QcRetrofitClient.INSTANCE.service().login(this.$req, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (r1 != 1) {
                    if (r1 == 2) {
                        flowCollector = (FlowCollector) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        this.L$0 = flowCollector;
                        this.label = 3;
                        if (QcResponseKt.error((QcResponse) obj, new AnonymousClass2(flowCollector, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                    if (r1 == 3) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    if (r1 != 4) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    exc = (Exception) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    exc.printStackTrace();
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
            this.L$0 = flowCollector;
            this.label = 3;
            if (QcResponseKt.error((QcResponse) obj, new AnonymousClass2(flowCollector, null), this) == coroutine_suspended) {
            }
            return Unit.INSTANCE;
        }

        /* compiled from: LoginDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "res", "Lcom/glasssutdio/wear/home/bean/LoginResModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$login$2$1", m620f = "LoginDepository.kt", m621i = {}, m622l = {47}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.LoginDepository$login$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, LoginResModel, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<LoginResModel>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<LoginResModel>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, LoginResModel loginResModel, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = loginResModel;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    LoginResModel loginResModel = (LoginResModel) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, loginResModel, 0, null, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: LoginDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", "", NotificationCompat.CATEGORY_MESSAGE, ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$login$2$2", m620f = "LoginDepository.kt", m621i = {}, m622l = {49}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.LoginDepository$login$2$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function4<CoroutineScope, Integer, String, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<LoginResModel>> $$this$flow;
            /* synthetic */ int I$0;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(FlowCollector<? super NetState<LoginResModel>> flowCollector, Continuation<? super AnonymousClass2> continuation) {
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

    public final Object login(LoginReq loginReq, Continuation<? super Flow<NetState<LoginResModel>>> continuation) {
        return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C09312(loginReq, null)), new C09323(null)), Dispatchers.getIO()), new C09334(null));
    }

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/home/bean/LoginResModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$login$3", m620f = "LoginDepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$login$3 */
    static final class C09323 extends SuspendLambda implements Function2<FlowCollector<? super NetState<LoginResModel>>, Continuation<? super Unit>, Object> {
        int label;

        C09323(Continuation<? super C09323> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09323(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<LoginResModel>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09323) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/home/bean/LoginResModel;", "it", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$login$4", m620f = "LoginDepository.kt", m621i = {}, m622l = {58}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$login$4 */
    static final class C09334 extends SuspendLambda implements Function3<FlowCollector<? super NetState<LoginResModel>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C09334(Continuation<? super C09334> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<LoginResModel>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C09334 c09334 = new C09334(continuation);
            c09334.L$0 = flowCollector;
            c09334.L$1 = th;
            return c09334.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/home/bean/UserModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$getUserInfo$2", m620f = "LoginDepository.kt", m621i = {0, 1}, m622l = {64, 64, 66}, m623m = "invokeSuspend", m624n = {"$this$flow", "$this$flow"}, m625s = {"L$0", "L$0"})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$getUserInfo$2 */
    static final class C09282 extends SuspendLambda implements Function2<FlowCollector<? super NetState<UserModel>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $uid;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09282(String str, Continuation<? super C09282> continuation) {
            super(2, continuation);
            this.$uid = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09282 c09282 = new C09282(this.$uid, continuation);
            c09282.L$0 = obj;
            return c09282;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<UserModel>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09282) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
                Object userInfo = QcRetrofitClient.INSTANCE.service().getUserInfo(this.$uid, this);
                if (userInfo == coroutine_suspended) {
                    return coroutine_suspended;
                }
                flowCollector = flowCollector2;
                obj = userInfo;
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

        /* compiled from: LoginDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "res", "Lcom/glasssutdio/wear/home/bean/UserModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$getUserInfo$2$1", m620f = "LoginDepository.kt", m621i = {}, m622l = {65}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.LoginDepository$getUserInfo$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, UserModel, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<UserModel>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<UserModel>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, UserModel userModel, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = userModel;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    UserModel userModel = (UserModel) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, userModel, 0, null, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: LoginDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", "", NotificationCompat.CATEGORY_MESSAGE, ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$getUserInfo$2$2", m620f = "LoginDepository.kt", m621i = {}, m622l = {67}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.LoginDepository$getUserInfo$2$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function4<CoroutineScope, Integer, String, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<UserModel>> $$this$flow;
            /* synthetic */ int I$0;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(FlowCollector<? super NetState<UserModel>> flowCollector, Continuation<? super AnonymousClass2> continuation) {
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

    public final Object getUserInfo(String str, Continuation<? super Flow<NetState<UserModel>>> continuation) {
        return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C09282(str, null)), new C09293(null)), Dispatchers.getIO()), new C09304(null));
    }

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/home/bean/UserModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$getUserInfo$3", m620f = "LoginDepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$getUserInfo$3 */
    static final class C09293 extends SuspendLambda implements Function2<FlowCollector<? super NetState<UserModel>>, Continuation<? super Unit>, Object> {
        int label;

        C09293(Continuation<? super C09293> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09293(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<UserModel>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09293) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/home/bean/UserModel;", "it", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$getUserInfo$4", m620f = "LoginDepository.kt", m621i = {}, m622l = {74}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$getUserInfo$4 */
    static final class C09304 extends SuspendLambda implements Function3<FlowCollector<? super NetState<UserModel>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C09304(Continuation<? super C09304> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<UserModel>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C09304 c09304 = new C09304(continuation);
            c09304.L$0 = flowCollector;
            c09304.L$1 = th;
            return c09304.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$logoff$2", m620f = "LoginDepository.kt", m621i = {0, 1}, m622l = {EMachine.EM_HUANY, EMachine.EM_HUANY, EMachine.EM_AVR}, m623m = "invokeSuspend", m624n = {"$this$flow", "$this$flow"}, m625s = {"L$0", "L$0"})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$logoff$2 */
    static final class C09342 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $uid;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09342(String str, Continuation<? super C09342> continuation) {
            super(2, continuation);
            this.$uid = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09342 c09342 = new C09342(this.$uid, continuation);
            c09342.L$0 = obj;
            return c09342;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09342) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
                Object objLogoff = QcRetrofitClient.INSTANCE.service().logoff(this.$uid, this);
                if (objLogoff == coroutine_suspended) {
                    return coroutine_suspended;
                }
                flowCollector = flowCollector2;
                obj = objLogoff;
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

        /* compiled from: LoginDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "res", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$logoff$2$1", m620f = "LoginDepository.kt", m621i = {}, m622l = {EMachine.EM_PRISM}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.LoginDepository$logoff$2$1, reason: invalid class name */
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

        /* compiled from: LoginDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", "", NotificationCompat.CATEGORY_MESSAGE, ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$logoff$2$2", m620f = "LoginDepository.kt", m621i = {}, m622l = {EMachine.EM_FR30}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.LoginDepository$logoff$2$2, reason: invalid class name */
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

    public final Object logoff(String str, Continuation<? super Flow<NetState<String>>> continuation) {
        return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C09342(str, null)), new C09353(null)), Dispatchers.getIO()), new C09364(null));
    }

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$logoff$3", m620f = "LoginDepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$logoff$3 */
    static final class C09353 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
        int label;

        C09353(Continuation<? super C09353> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09353(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09353) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "", "it", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$logoff$4", m620f = "LoginDepository.kt", m621i = {}, m622l = {EMachine.EM_PJ}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$logoff$4 */
    static final class C09364 extends SuspendLambda implements Function3<FlowCollector<? super NetState<String>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C09364(Continuation<? super C09364> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C09364 c09364 = new C09364(continuation);
            c09364.L$0 = flowCollector;
            c09364.L$1 = th;
            return c09364.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/home/bean/UserModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$updateUserInfo$2", m620f = "LoginDepository.kt", m621i = {0, 1}, m622l = {EMachine.EM_NS32K, EMachine.EM_NS32K, EMachine.EM_SNP1K}, m623m = "invokeSuspend", m624n = {"$this$flow", "$this$flow"}, m625s = {"L$0", "L$0"})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$updateUserInfo$2 */
    static final class C09432 extends SuspendLambda implements Function2<FlowCollector<? super NetState<UserModel>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ UpdateUserReq $req;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09432(UpdateUserReq updateUserReq, Continuation<? super C09432> continuation) {
            super(2, continuation);
            this.$req = updateUserReq;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09432 c09432 = new C09432(this.$req, continuation);
            c09432.L$0 = obj;
            return c09432;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<UserModel>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09432) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
                Object objUpdateUserInfo = QcRetrofitClient.INSTANCE.service().updateUserInfo(this.$req, this);
                if (objUpdateUserInfo == coroutine_suspended) {
                    return coroutine_suspended;
                }
                flowCollector = flowCollector2;
                obj = objUpdateUserInfo;
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

        /* compiled from: LoginDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "res", "Lcom/glasssutdio/wear/home/bean/UserModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$updateUserInfo$2$1", m620f = "LoginDepository.kt", m621i = {}, m622l = {EMachine.EM_TPC}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.LoginDepository$updateUserInfo$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, UserModel, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<UserModel>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<UserModel>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, UserModel userModel, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = userModel;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    UserModel userModel = (UserModel) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, userModel, 0, null, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: LoginDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", "", NotificationCompat.CATEGORY_MESSAGE, ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$updateUserInfo$2$2", m620f = "LoginDepository.kt", m621i = {}, m622l = {100}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.LoginDepository$updateUserInfo$2$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function4<CoroutineScope, Integer, String, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<UserModel>> $$this$flow;
            /* synthetic */ int I$0;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(FlowCollector<? super NetState<UserModel>> flowCollector, Continuation<? super AnonymousClass2> continuation) {
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

    public final Object updateUserInfo(UpdateUserReq updateUserReq, Continuation<? super Flow<NetState<UserModel>>> continuation) {
        return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C09432(updateUserReq, null)), new C09443(null)), Dispatchers.getIO()), new C09454(null));
    }

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/home/bean/UserModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$updateUserInfo$3", m620f = "LoginDepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$updateUserInfo$3 */
    static final class C09443 extends SuspendLambda implements Function2<FlowCollector<? super NetState<UserModel>>, Continuation<? super Unit>, Object> {
        int label;

        C09443(Continuation<? super C09443> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09443(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<UserModel>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09443) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/home/bean/UserModel;", "it", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$updateUserInfo$4", m620f = "LoginDepository.kt", m621i = {}, m622l = {EMachine.EM_SE_C33}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$updateUserInfo$4 */
    static final class C09454 extends SuspendLambda implements Function3<FlowCollector<? super NetState<UserModel>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C09454(Continuation<? super C09454> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<UserModel>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C09454 c09454 = new C09454(continuation);
            c09454.L$0 = flowCollector;
            c09454.L$1 = th;
            return c09454.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/all/bean/ResetPwdModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$emailSendCode$2", m620f = "LoginDepository.kt", m621i = {0, 1}, m622l = {EMachine.EM_ALTERA_NIOS2, EMachine.EM_ALTERA_NIOS2, EMachine.EM_XGATE}, m623m = "invokeSuspend", m624n = {"$this$flow", "$this$flow"}, m625s = {"L$0", "L$0"})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$emailSendCode$2 */
    static final class C09222 extends SuspendLambda implements Function2<FlowCollector<? super NetState<ResetPwdModel>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $email;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09222(String str, Continuation<? super C09222> continuation) {
            super(2, continuation);
            this.$email = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09222 c09222 = new C09222(this.$email, continuation);
            c09222.L$0 = obj;
            return c09222;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<ResetPwdModel>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09222) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x0089 A[RETURN] */
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
                Object objEmailSendCode = QcRetrofitClient.INSTANCE.service().emailSendCode(new ResetPwdReq(null, this.$email, null, 5, null), this);
                if (objEmailSendCode == coroutine_suspended) {
                    return coroutine_suspended;
                }
                flowCollector = flowCollector2;
                obj = objEmailSendCode;
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

        /* compiled from: LoginDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "res", "Lcom/glasssutdio/wear/all/bean/ResetPwdModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$emailSendCode$2$1", m620f = "LoginDepository.kt", m621i = {}, m622l = {EMachine.EM_CRX}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.LoginDepository$emailSendCode$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, ResetPwdModel, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<ResetPwdModel>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<ResetPwdModel>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, ResetPwdModel resetPwdModel, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = resetPwdModel;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    ResetPwdModel resetPwdModel = (ResetPwdModel) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, resetPwdModel, 0, null, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: LoginDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", "", NotificationCompat.CATEGORY_MESSAGE, ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$emailSendCode$2$2", m620f = "LoginDepository.kt", m621i = {}, m622l = {EMachine.EM_C166}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.LoginDepository$emailSendCode$2$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function4<CoroutineScope, Integer, String, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<ResetPwdModel>> $$this$flow;
            /* synthetic */ int I$0;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(FlowCollector<? super NetState<ResetPwdModel>> flowCollector, Continuation<? super AnonymousClass2> continuation) {
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

    public final Object emailSendCode(String str, Continuation<? super Flow<NetState<ResetPwdModel>>> continuation) {
        return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C09222(str, null)), new C09233(null)), Dispatchers.getIO()), new C09244(null));
    }

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/all/bean/ResetPwdModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$emailSendCode$3", m620f = "LoginDepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$emailSendCode$3 */
    static final class C09233 extends SuspendLambda implements Function2<FlowCollector<? super NetState<ResetPwdModel>>, Continuation<? super Unit>, Object> {
        int label;

        C09233(Continuation<? super C09233> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09233(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<ResetPwdModel>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09233) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/all/bean/ResetPwdModel;", "it", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$emailSendCode$4", m620f = "LoginDepository.kt", m621i = {}, m622l = {123}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$emailSendCode$4 */
    static final class C09244 extends SuspendLambda implements Function3<FlowCollector<? super NetState<ResetPwdModel>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C09244(Continuation<? super C09244> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<ResetPwdModel>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C09244 c09244 = new C09244(continuation);
            c09244.L$0 = flowCollector;
            c09244.L$1 = th;
            return c09244.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/all/bean/ResetPwdModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$resetPassword$2", m620f = "LoginDepository.kt", m621i = {0, 1}, m622l = {129, 129, EMachine.EM_TSK3000}, m623m = "invokeSuspend", m624n = {"$this$flow", "$this$flow"}, m625s = {"L$0", "L$0"})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$resetPassword$2 */
    static final class C09402 extends SuspendLambda implements Function2<FlowCollector<? super NetState<ResetPwdModel>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ ResetPwdReq $req;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09402(ResetPwdReq resetPwdReq, Continuation<? super C09402> continuation) {
            super(2, continuation);
            this.$req = resetPwdReq;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09402 c09402 = new C09402(this.$req, continuation);
            c09402.L$0 = obj;
            return c09402;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<ResetPwdModel>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09402) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
                Object objResetPassword = QcRetrofitClient.INSTANCE.service().resetPassword(this.$req, this);
                if (objResetPassword == coroutine_suspended) {
                    return coroutine_suspended;
                }
                flowCollector = flowCollector2;
                obj = objResetPassword;
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

        /* compiled from: LoginDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "res", "Lcom/glasssutdio/wear/all/bean/ResetPwdModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$resetPassword$2$1", m620f = "LoginDepository.kt", m621i = {}, m622l = {WatermarkGenerator.WATERMARK_HEIGHT_DP}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.LoginDepository$resetPassword$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, ResetPwdModel, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<ResetPwdModel>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<ResetPwdModel>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, ResetPwdModel resetPwdModel, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = resetPwdModel;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    ResetPwdModel resetPwdModel = (ResetPwdModel) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, resetPwdModel, 0, null, 9, null), this) == coroutine_suspended) {
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

        /* compiled from: LoginDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", "", NotificationCompat.CATEGORY_MESSAGE, ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$resetPassword$2$2", m620f = "LoginDepository.kt", m621i = {}, m622l = {EMachine.EM_RS08}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.LoginDepository$resetPassword$2$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function4<CoroutineScope, Integer, String, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<ResetPwdModel>> $$this$flow;
            /* synthetic */ int I$0;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(FlowCollector<? super NetState<ResetPwdModel>> flowCollector, Continuation<? super AnonymousClass2> continuation) {
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

    public final Object resetPassword(ResetPwdReq resetPwdReq, Continuation<? super Flow<NetState<ResetPwdModel>>> continuation) {
        return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C09402(resetPwdReq, null)), new C09413(null)), Dispatchers.getIO()), new C09424(null));
    }

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/all/bean/ResetPwdModel;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$resetPassword$3", m620f = "LoginDepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$resetPassword$3 */
    static final class C09413 extends SuspendLambda implements Function2<FlowCollector<? super NetState<ResetPwdModel>>, Continuation<? super Unit>, Object> {
        int label;

        C09413(Continuation<? super C09413> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09413(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<ResetPwdModel>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09413) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "Lcom/glasssutdio/wear/all/bean/ResetPwdModel;", "it", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$resetPassword$4", m620f = "LoginDepository.kt", m621i = {}, m622l = {EMachine.EM_SE_C17}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$resetPassword$4 */
    static final class C09424 extends SuspendLambda implements Function3<FlowCollector<? super NetState<ResetPwdModel>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C09424(Continuation<? super C09424> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<ResetPwdModel>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C09424 c09424 = new C09424(continuation);
            c09424.L$0 = flowCollector;
            c09424.L$1 = th;
            return c09424.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$uploadImg$2", m620f = "LoginDepository.kt", m621i = {0, 1}, m622l = {145, 145, 147}, m623m = "invokeSuspend", m624n = {"$this$flow", "$this$flow"}, m625s = {"L$0", "L$0"})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$uploadImg$2 */
    static final class C09462 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ RequestBody $req;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09462(RequestBody requestBody, Continuation<? super C09462> continuation) {
            super(2, continuation);
            this.$req = requestBody;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09462 c09462 = new C09462(this.$req, continuation);
            c09462.L$0 = obj;
            return c09462;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09462) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
                Object objUploadImg = QcRetrofitClient.INSTANCE.service().uploadImg(this.$req, this);
                if (objUploadImg == coroutine_suspended) {
                    return coroutine_suspended;
                }
                flowCollector = flowCollector2;
                obj = objUploadImg;
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

        /* compiled from: LoginDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "res", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$uploadImg$2$1", m620f = "LoginDepository.kt", m621i = {}, m622l = {146}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.LoginDepository$uploadImg$2$1, reason: invalid class name */
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

        /* compiled from: LoginDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", "", NotificationCompat.CATEGORY_MESSAGE, ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$uploadImg$2$2", m620f = "LoginDepository.kt", m621i = {}, m622l = {148}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.LoginDepository$uploadImg$2$2, reason: invalid class name */
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

    public final Object uploadImg(RequestBody requestBody, Continuation<? super Flow<NetState<String>>> continuation) {
        return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C09462(requestBody, null)), new C09473(null)), Dispatchers.getIO()), new C09484(null));
    }

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$uploadImg$3", m620f = "LoginDepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$uploadImg$3 */
    static final class C09473 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
        int label;

        C09473(Continuation<? super C09473> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09473(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09473) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "", "it", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$uploadImg$4", m620f = "LoginDepository.kt", m621i = {}, m622l = {155}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$uploadImg$4 */
    static final class C09484 extends SuspendLambda implements Function3<FlowCollector<? super NetState<String>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C09484(Continuation<? super C09484> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C09484 c09484 = new C09484(continuation);
            c09484.L$0 = flowCollector;
            c09484.L$1 = th;
            return c09484.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$getRegisterCode$2", m620f = "LoginDepository.kt", m621i = {0, 1}, m622l = {EMachine.EM_R32C, EMachine.EM_R32C, EMachine.EM_HEXAGON}, m623m = "invokeSuspend", m624n = {"$this$flow", "$this$flow"}, m625s = {"L$0", "L$0"})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$getRegisterCode$2 */
    static final class C09252 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $appName;
        final /* synthetic */ String $email;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09252(String str, String str2, Continuation<? super C09252> continuation) {
            super(2, continuation);
            this.$email = str;
            this.$appName = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09252 c09252 = new C09252(this.$email, this.$appName, continuation);
            c09252.L$0 = obj;
            return c09252;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09252) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x007f A[RETURN] */
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
                Object objRegisterGetCode = QcRetrofitClient.INSTANCE.service().registerGetCode(this.$email, this.$appName, this);
                if (objRegisterGetCode == coroutine_suspended) {
                    return coroutine_suspended;
                }
                flowCollector = flowCollector2;
                obj = objRegisterGetCode;
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

        /* compiled from: LoginDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "res", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$getRegisterCode$2$1", m620f = "LoginDepository.kt", m621i = {}, m622l = {EMachine.EM_TRIMEDIA}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.LoginDepository$getRegisterCode$2$1, reason: invalid class name */
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

        /* compiled from: LoginDepository.kt */
        @Metadata(m606d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", "", NotificationCompat.CATEGORY_MESSAGE, ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
        @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$getRegisterCode$2$2", m620f = "LoginDepository.kt", m621i = {}, m622l = {EMachine.EM_8051}, m623m = "invokeSuspend", m624n = {}, m625s = {})
        /* renamed from: com.glasssutdio.wear.depository.LoginDepository$getRegisterCode$2$2, reason: invalid class name */
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

    public final Object getRegisterCode(String str, String str2, Continuation<? super Flow<NetState<String>>> continuation) {
        return FlowKt.m2434catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C09252(str, str2, null)), new C09263(null)), Dispatchers.getIO()), new C09274(null));
    }

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$getRegisterCode$3", m620f = "LoginDepository.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$getRegisterCode$3 */
    static final class C09263 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
        int label;

        C09263(Continuation<? super C09263> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09263(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C09263) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/glasssutdio/wear/api/NetState;", "", "it", ""}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.depository.LoginDepository$getRegisterCode$4", m620f = "LoginDepository.kt", m621i = {}, m622l = {EMachine.EM_CRAYNV2}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.depository.LoginDepository$getRegisterCode$4 */
    static final class C09274 extends SuspendLambda implements Function3<FlowCollector<? super NetState<String>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C09274(Continuation<? super C09274> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C09274 c09274 = new C09274(continuation);
            c09274.L$0 = flowCollector;
            c09274.L$1 = th;
            return c09274.invokeSuspend(Unit.INSTANCE);
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

    /* compiled from: LoginDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/depository/LoginDepository$Companion;", "", "()V", "getInstance", "Lcom/glasssutdio/wear/depository/LoginDepository;", "getGetInstance", "()Lcom/glasssutdio/wear/depository/LoginDepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LoginDepository getGetInstance() {
            return (LoginDepository) LoginDepository.getInstance$delegate.getValue();
        }
    }
}
