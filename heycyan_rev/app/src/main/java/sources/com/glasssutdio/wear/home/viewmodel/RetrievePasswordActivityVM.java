package com.glasssutdio.wear.home.viewmodel;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.glasssutdio.wear.all.bean.Req.LoginReq;
import com.glasssutdio.wear.all.bean.Req.ResetPwdReq;
import com.glasssutdio.wear.all.bean.ResetPwdModel;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.api.NetState;
import com.glasssutdio.wear.depository.LoginDepository;
import com.glasssutdio.wear.home.bean.LoginResModel;
import com.glasssutdio.wear.home.bean.RequestFailModel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: RetrievePasswordActivityVM.kt */
@Metadata(m606d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u001e\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u001eJ\u0016\u0010#\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u001eJ\u000e\u0010%\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020'R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR \u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0007\"\u0004\b\u0013\u0010\tR \u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0007\"\u0004\b\u0017\u0010\tR \u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00150\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0007\"\u0004\b\u001a\u0010\t¨\u0006("}, m607d2 = {"Lcom/glasssutdio/wear/home/viewmodel/RetrievePasswordActivityVM;", "Landroidx/lifecycle/ViewModel;", "()V", "failLD", "Landroidx/lifecycle/MutableLiveData;", "Lcom/glasssutdio/wear/home/bean/RequestFailModel;", "getFailLD", "()Landroidx/lifecycle/MutableLiveData;", "setFailLD", "(Landroidx/lifecycle/MutableLiveData;)V", "pageType", "", "getPageType", "()I", "setPageType", "(I)V", "registerLD", "Lcom/glasssutdio/wear/home/bean/LoginResModel;", "getRegisterLD", "setRegisterLD", "resetPwdLD", "", "getResetPwdLD", "setResetPwdLD", "sendEmailLD", "getSendEmailLD", "setSendEmailLD", "emailSendCode", "", "email", "", "registerByEmail", "account", "password", "verificationCode", "registerSendCode", "appName", "resetPassword", "req", "Lcom/glasssutdio/wear/all/bean/Req/ResetPwdReq;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class RetrievePasswordActivityVM extends ViewModel {
    private int pageType = 1;
    private MutableLiveData<RequestFailModel> failLD = new MutableLiveData<>();
    private MutableLiveData<LoginResModel> registerLD = new MutableLiveData<>();
    private MutableLiveData<Boolean> resetPwdLD = new MutableLiveData<>();
    private MutableLiveData<Boolean> sendEmailLD = new MutableLiveData<>();

    public final int getPageType() {
        return this.pageType;
    }

    public final void setPageType(int i) {
        this.pageType = i;
    }

    public final MutableLiveData<RequestFailModel> getFailLD() {
        return this.failLD;
    }

    public final void setFailLD(MutableLiveData<RequestFailModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.failLD = mutableLiveData;
    }

    public final MutableLiveData<LoginResModel> getRegisterLD() {
        return this.registerLD;
    }

    public final void setRegisterLD(MutableLiveData<LoginResModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.registerLD = mutableLiveData;
    }

    public final MutableLiveData<Boolean> getResetPwdLD() {
        return this.resetPwdLD;
    }

    public final void setResetPwdLD(MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.resetPwdLD = mutableLiveData;
    }

    public final MutableLiveData<Boolean> getSendEmailLD() {
        return this.sendEmailLD;
    }

    public final void setSendEmailLD(MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.sendEmailLD = mutableLiveData;
    }

    /* compiled from: RetrievePasswordActivityVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.RetrievePasswordActivityVM$resetPassword$1", m620f = "RetrievePasswordActivityVM.kt", m621i = {}, m622l = {ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS, ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.RetrievePasswordActivityVM$resetPassword$1 */
    static final class C10831 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ResetPwdReq $req;
        int label;
        final /* synthetic */ RetrievePasswordActivityVM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10831(ResetPwdReq resetPwdReq, RetrievePasswordActivityVM retrievePasswordActivityVM, Continuation<? super C10831> continuation) {
            super(2, continuation);
            this.$req = resetPwdReq;
            this.this$0 = retrievePasswordActivityVM;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C10831(this.$req, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10831) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = LoginDepository.INSTANCE.getGetInstance().resetPassword(this.$req, this);
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
            final ResetPwdReq resetPwdReq = this.$req;
            final RetrievePasswordActivityVM retrievePasswordActivityVM = this.this$0;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.glasssutdio.wear.home.viewmodel.RetrievePasswordActivityVM.resetPassword.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<ResetPwdModel>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<ResetPwdModel> netState, Continuation<? super Unit> continuation) {
                    if (netState.getRetCode() == 0) {
                        UserConfig companion = UserConfig.INSTANCE.getInstance();
                        String password = resetPwdReq.getPassword();
                        if (password == null) {
                            password = "";
                        }
                        companion.setLastLoginPwd(password);
                        UserConfig companion2 = UserConfig.INSTANCE.getInstance();
                        String email = resetPwdReq.getEmail();
                        companion2.setLastLoginAccount(email != null ? email : "");
                        retrievePasswordActivityVM.getResetPwdLD().postValue(Boxing.boxBoolean(true));
                    } else {
                        retrievePasswordActivityVM.getFailLD().postValue(new RequestFailModel(netState.getRetCode(), netState.getMsg(), 0, 4, null));
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void resetPassword(ResetPwdReq req) {
        Intrinsics.checkNotNullParameter(req, "req");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10831(req, this, null), 2, null);
    }

    /* compiled from: RetrievePasswordActivityVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.RetrievePasswordActivityVM$registerByEmail$1", m620f = "RetrievePasswordActivityVM.kt", m621i = {}, m622l = {45, 45}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.RetrievePasswordActivityVM$registerByEmail$1 */
    static final class C10811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $account;
        final /* synthetic */ String $password;
        final /* synthetic */ String $verificationCode;
        int label;
        final /* synthetic */ RetrievePasswordActivityVM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10811(String str, String str2, String str3, RetrievePasswordActivityVM retrievePasswordActivityVM, Continuation<? super C10811> continuation) {
            super(2, continuation);
            this.$account = str;
            this.$password = str2;
            this.$verificationCode = str3;
            this.this$0 = retrievePasswordActivityVM;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C10811(this.$account, this.$password, this.$verificationCode, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10811) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = LoginDepository.INSTANCE.getGetInstance().registerByEmail(new LoginReq(this.$account, this.$password, 2, this.$verificationCode), this);
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
            final RetrievePasswordActivityVM retrievePasswordActivityVM = this.this$0;
            final String str = this.$account;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.glasssutdio.wear.home.viewmodel.RetrievePasswordActivityVM.registerByEmail.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<LoginResModel>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<LoginResModel> netState, Continuation<? super Unit> continuation) {
                    if (netState.getRetCode() == 0) {
                        MutableLiveData<LoginResModel> registerLD = retrievePasswordActivityVM.getRegisterLD();
                        LoginResModel loginResModelIsSuccess = netState.isSuccess();
                        Intrinsics.checkNotNull(loginResModelIsSuccess, "null cannot be cast to non-null type com.glasssutdio.wear.home.bean.LoginResModel");
                        registerLD.postValue(loginResModelIsSuccess);
                        UserConfig.INSTANCE.getInstance().setLastLoginAccount(str);
                    } else {
                        retrievePasswordActivityVM.getFailLD().postValue(new RequestFailModel(netState.getRetCode(), netState.getMsg(), 0, 4, null));
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void registerByEmail(String account, String password, String verificationCode) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(verificationCode, "verificationCode");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10811(account, password, verificationCode, this, null), 2, null);
    }

    /* compiled from: RetrievePasswordActivityVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.RetrievePasswordActivityVM$emailSendCode$1", m620f = "RetrievePasswordActivityVM.kt", m621i = {}, m622l = {58, 58}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.RetrievePasswordActivityVM$emailSendCode$1 */
    static final class C10801 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $email;
        int label;
        final /* synthetic */ RetrievePasswordActivityVM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10801(String str, RetrievePasswordActivityVM retrievePasswordActivityVM, Continuation<? super C10801> continuation) {
            super(2, continuation);
            this.$email = str;
            this.this$0 = retrievePasswordActivityVM;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C10801(this.$email, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10801) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = LoginDepository.INSTANCE.getGetInstance().emailSendCode(this.$email, this);
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
            final RetrievePasswordActivityVM retrievePasswordActivityVM = this.this$0;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.glasssutdio.wear.home.viewmodel.RetrievePasswordActivityVM.emailSendCode.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<ResetPwdModel>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<ResetPwdModel> netState, Continuation<? super Unit> continuation) {
                    if (netState.getRetCode() == 0) {
                        retrievePasswordActivityVM.getSendEmailLD().postValue(Boxing.boxBoolean(true));
                    } else {
                        retrievePasswordActivityVM.getFailLD().postValue(new RequestFailModel(netState.getRetCode(), netState.getMsg(), 0, 4, null));
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void emailSendCode(String email) {
        Intrinsics.checkNotNullParameter(email, "email");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10801(email, this, null), 2, null);
    }

    /* compiled from: RetrievePasswordActivityVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.RetrievePasswordActivityVM$registerSendCode$1", m620f = "RetrievePasswordActivityVM.kt", m621i = {}, m622l = {70, 70}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.RetrievePasswordActivityVM$registerSendCode$1 */
    static final class C10821 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $appName;
        final /* synthetic */ String $email;
        int label;
        final /* synthetic */ RetrievePasswordActivityVM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10821(String str, String str2, RetrievePasswordActivityVM retrievePasswordActivityVM, Continuation<? super C10821> continuation) {
            super(2, continuation);
            this.$email = str;
            this.$appName = str2;
            this.this$0 = retrievePasswordActivityVM;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C10821(this.$email, this.$appName, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10821) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = LoginDepository.INSTANCE.getGetInstance().getRegisterCode(this.$email, this.$appName, this);
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
            final RetrievePasswordActivityVM retrievePasswordActivityVM = this.this$0;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.glasssutdio.wear.home.viewmodel.RetrievePasswordActivityVM.registerSendCode.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<String>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<String> netState, Continuation<? super Unit> continuation) {
                    if (netState.getRetCode() == 0) {
                        retrievePasswordActivityVM.getSendEmailLD().postValue(Boxing.boxBoolean(true));
                    } else {
                        retrievePasswordActivityVM.getFailLD().postValue(new RequestFailModel(netState.getRetCode(), netState.getMsg(), 0, 4, null));
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void registerSendCode(String email, String appName) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(appName, "appName");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10821(email, appName, this, null), 2, null);
    }
}
