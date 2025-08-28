package com.glasssutdio.wear.home.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.glasssutdio.wear.all.bean.Req.LoginReq;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.MD5Utils;
import com.glasssutdio.wear.api.NetState;
import com.glasssutdio.wear.depository.LoginDepository;
import com.glasssutdio.wear.home.bean.LoginResModel;
import com.glasssutdio.wear.home.bean.RequestFailModel;
import com.glasssutdio.wear.home.bean.UserModel;
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

/* compiled from: LoginActivityVM.kt */
@Metadata(m606d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u000fJ\u000e\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001cJ\u0016\u0010!\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cR \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0007\"\u0004\b\r\u0010\tR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0007\"\u0004\b\u0011\u0010\tR \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0007\"\u0004\b\u0014\u0010\tR \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0007\"\u0004\b\u0018\u0010\t¨\u0006\""}, m607d2 = {"Lcom/glasssutdio/wear/home/viewmodel/LoginActivityVM;", "Landroidx/lifecycle/ViewModel;", "()V", "failLD", "Landroidx/lifecycle/MutableLiveData;", "Lcom/glasssutdio/wear/home/bean/RequestFailModel;", "getFailLD", "()Landroidx/lifecycle/MutableLiveData;", "setFailLD", "(Landroidx/lifecycle/MutableLiveData;)V", "loginResLD", "Lcom/glasssutdio/wear/home/bean/LoginResModel;", "getLoginResLD", "setLoginResLD", "logoffLD", "", "getLogoffLD", "setLogoffLD", "userInfoFailLD", "getUserInfoFailLD", "setUserInfoFailLD", "userInfoLD", "Lcom/glasssutdio/wear/home/bean/UserModel;", "getUserInfoLD", "setUserInfoLD", "login", "", "account", "", "password", "isEmail", "logoff", "uid", "registerByEmail", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class LoginActivityVM extends ViewModel {
    private MutableLiveData<LoginResModel> loginResLD = new MutableLiveData<>();
    private MutableLiveData<RequestFailModel> failLD = new MutableLiveData<>();
    private MutableLiveData<RequestFailModel> userInfoFailLD = new MutableLiveData<>();
    private MutableLiveData<Boolean> logoffLD = new MutableLiveData<>();
    private MutableLiveData<UserModel> userInfoLD = new MutableLiveData<>();

    public final MutableLiveData<LoginResModel> getLoginResLD() {
        return this.loginResLD;
    }

    public final void setLoginResLD(MutableLiveData<LoginResModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.loginResLD = mutableLiveData;
    }

    public final MutableLiveData<RequestFailModel> getFailLD() {
        return this.failLD;
    }

    public final void setFailLD(MutableLiveData<RequestFailModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.failLD = mutableLiveData;
    }

    public final MutableLiveData<RequestFailModel> getUserInfoFailLD() {
        return this.userInfoFailLD;
    }

    public final void setUserInfoFailLD(MutableLiveData<RequestFailModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.userInfoFailLD = mutableLiveData;
    }

    public final MutableLiveData<Boolean> getLogoffLD() {
        return this.logoffLD;
    }

    public final void setLogoffLD(MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.logoffLD = mutableLiveData;
    }

    /* compiled from: LoginActivityVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.LoginActivityVM$logoff$1", m620f = "LoginActivityVM.kt", m621i = {}, m622l = {28, 28}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.LoginActivityVM$logoff$1 */
    static final class C10761 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $uid;
        int label;
        final /* synthetic */ LoginActivityVM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10761(String str, LoginActivityVM loginActivityVM, Continuation<? super C10761> continuation) {
            super(2, continuation);
            this.$uid = str;
            this.this$0 = loginActivityVM;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C10761(this.$uid, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10761) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = LoginDepository.INSTANCE.getGetInstance().logoff(this.$uid, this);
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
            final LoginActivityVM loginActivityVM = this.this$0;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.glasssutdio.wear.home.viewmodel.LoginActivityVM.logoff.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<String>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<String> netState, Continuation<? super Unit> continuation) {
                    if (netState.getRetCode() == 0) {
                        loginActivityVM.getLogoffLD().postValue(Boxing.boxBoolean(true));
                    } else {
                        loginActivityVM.getFailLD().postValue(new RequestFailModel(netState.getRetCode(), netState.getMsg(), 0, 4, null));
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void logoff(String uid) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10761(uid, this, null), 2, null);
    }

    public final MutableLiveData<UserModel> getUserInfoLD() {
        return this.userInfoLD;
    }

    public final void setUserInfoLD(MutableLiveData<UserModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.userInfoLD = mutableLiveData;
    }

    /* compiled from: LoginActivityVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.LoginActivityVM$registerByEmail$1", m620f = "LoginActivityVM.kt", m621i = {}, m622l = {45, 45}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.LoginActivityVM$registerByEmail$1 */
    static final class C10771 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $account;
        final /* synthetic */ String $password;
        int label;
        final /* synthetic */ LoginActivityVM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10771(String str, String str2, LoginActivityVM loginActivityVM, Continuation<? super C10771> continuation) {
            super(2, continuation);
            this.$account = str;
            this.$password = str2;
            this.this$0 = loginActivityVM;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C10771(this.$account, this.$password, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10771) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = LoginDepository.INSTANCE.getGetInstance().registerByEmail(new LoginReq(this.$account, this.$password, 2, null, 8, null), this);
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
            final LoginActivityVM loginActivityVM = this.this$0;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.glasssutdio.wear.home.viewmodel.LoginActivityVM.registerByEmail.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<LoginResModel>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<LoginResModel> netState, Continuation<? super Unit> continuation) {
                    if (netState.getRetCode() == 0) {
                        MutableLiveData<LoginResModel> loginResLD = loginActivityVM.getLoginResLD();
                        LoginResModel loginResModelIsSuccess = netState.isSuccess();
                        Intrinsics.checkNotNull(loginResModelIsSuccess, "null cannot be cast to non-null type com.glasssutdio.wear.home.bean.LoginResModel");
                        loginResLD.postValue(loginResModelIsSuccess);
                    } else {
                        loginActivityVM.getFailLD().postValue(new RequestFailModel(netState.getRetCode(), netState.getMsg(), 0, 4, null));
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void registerByEmail(String account, String password) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(password, "password");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10771(account, password, this, null), 2, null);
    }

    public static /* synthetic */ void login$default(LoginActivityVM loginActivityVM, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        loginActivityVM.login(str, str2, z);
    }

    /* compiled from: LoginActivityVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.LoginActivityVM$login$1", m620f = "LoginActivityVM.kt", m621i = {}, m622l = {58, 58}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.LoginActivityVM$login$1 */
    static final class C10751 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $account;
        final /* synthetic */ boolean $isEmail;
        final /* synthetic */ String $md5Pwd;
        int label;
        final /* synthetic */ LoginActivityVM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10751(String str, String str2, boolean z, LoginActivityVM loginActivityVM, Continuation<? super C10751> continuation) {
            super(2, continuation);
            this.$account = str;
            this.$md5Pwd = str2;
            this.$isEmail = z;
            this.this$0 = loginActivityVM;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C10751(this.$account, this.$md5Pwd, this.$isEmail, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10751) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LoginDepository getInstance = LoginDepository.INSTANCE.getGetInstance();
                String str = this.$account;
                String md5Pwd = this.$md5Pwd;
                Intrinsics.checkNotNullExpressionValue(md5Pwd, "$md5Pwd");
                int i2 = this.$isEmail ? 2 : 1;
                this.label = 1;
                obj = getInstance.login(new LoginReq(str, md5Pwd, i2, null, 8, null), this);
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
            final boolean z = this.$isEmail;
            final String str2 = this.$account;
            final String str3 = this.$md5Pwd;
            final LoginActivityVM loginActivityVM = this.this$0;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.glasssutdio.wear.home.viewmodel.LoginActivityVM.login.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<LoginResModel>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<LoginResModel> netState, Continuation<? super Unit> continuation) {
                    if (netState.getRetCode() == 0) {
                        UserConfig.INSTANCE.getInstance().setLastLoginTimeStamp(System.currentTimeMillis());
                        UserConfig.INSTANCE.getInstance().setLastLoginType(z ? 3 : 1);
                        UserConfig.INSTANCE.getInstance().setLastLoginAccount(str2);
                        UserConfig companion = UserConfig.INSTANCE.getInstance();
                        String md5Pwd2 = str3;
                        Intrinsics.checkNotNullExpressionValue(md5Pwd2, "$md5Pwd");
                        companion.setLastLoginPwd(md5Pwd2);
                        MutableLiveData<LoginResModel> loginResLD = loginActivityVM.getLoginResLD();
                        LoginResModel loginResModelIsSuccess = netState.isSuccess();
                        Intrinsics.checkNotNull(loginResModelIsSuccess, "null cannot be cast to non-null type com.glasssutdio.wear.home.bean.LoginResModel");
                        loginResLD.postValue(loginResModelIsSuccess);
                    } else {
                        loginActivityVM.getFailLD().postValue(new RequestFailModel(netState.getRetCode(), netState.getMsg(), 0, 4, null));
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void login(String account, String password, boolean isEmail) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(password, "password");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10751(account, MD5Utils.getMD5(password), isEmail, this, null), 2, null);
    }
}
