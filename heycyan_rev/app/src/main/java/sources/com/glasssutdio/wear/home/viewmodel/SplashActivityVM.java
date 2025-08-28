package com.glasssutdio.wear.home.viewmodel;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.glasssutdio.wear.all.bean.Req.LoginReq;
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
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: SplashActivityVM.kt */
@Metadata(m606d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u0014R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0007\"\u0004\b\r\u0010\t¨\u0006\u0015"}, m607d2 = {"Lcom/glasssutdio/wear/home/viewmodel/SplashActivityVM;", "Landroidx/lifecycle/ViewModel;", "()V", "loginFailLD", "Landroidx/lifecycle/MutableLiveData;", "Lcom/glasssutdio/wear/home/bean/RequestFailModel;", "getLoginFailLD", "()Landroidx/lifecycle/MutableLiveData;", "setLoginFailLD", "(Landroidx/lifecycle/MutableLiveData;)V", "loginResLD", "Lcom/glasssutdio/wear/home/bean/LoginResModel;", "getLoginResLD", "setLoginResLD", "login", "", "account", "", "md5Pwd", "isEmail", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class SplashActivityVM extends ViewModel {
    private MutableLiveData<LoginResModel> loginResLD = new MutableLiveData<>();
    private MutableLiveData<RequestFailModel> loginFailLD = new MutableLiveData<>();

    public final MutableLiveData<LoginResModel> getLoginResLD() {
        return this.loginResLD;
    }

    public final void setLoginResLD(MutableLiveData<LoginResModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.loginResLD = mutableLiveData;
    }

    public final MutableLiveData<RequestFailModel> getLoginFailLD() {
        return this.loginFailLD;
    }

    public final void setLoginFailLD(MutableLiveData<RequestFailModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.loginFailLD = mutableLiveData;
    }

    /* compiled from: SplashActivityVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.SplashActivityVM$login$1", m620f = "SplashActivityVM.kt", m621i = {}, m622l = {25, ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.SplashActivityVM$login$1 */
    static final class C10861 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $account;
        final /* synthetic */ boolean $isEmail;
        final /* synthetic */ String $md5Pwd;
        int label;
        final /* synthetic */ SplashActivityVM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10861(String str, String str2, boolean z, SplashActivityVM splashActivityVM, Continuation<? super C10861> continuation) {
            super(2, continuation);
            this.$account = str;
            this.$md5Pwd = str2;
            this.$isEmail = z;
            this.this$0 = splashActivityVM;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C10861(this.$account, this.$md5Pwd, this.$isEmail, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10861) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LoginDepository getInstance = LoginDepository.INSTANCE.getGetInstance();
                String str = this.$account;
                String str2 = this.$md5Pwd;
                int i2 = this.$isEmail ? 2 : 1;
                this.label = 1;
                obj = getInstance.login(new LoginReq(str, str2, i2, null, 8, null), this);
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
            final String str3 = this.$account;
            final String str4 = this.$md5Pwd;
            final SplashActivityVM splashActivityVM = this.this$0;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.glasssutdio.wear.home.viewmodel.SplashActivityVM.login.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<LoginResModel>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<LoginResModel> netState, Continuation<? super Unit> continuation) {
                    if (netState.getRetCode() == 0) {
                        UserConfig.INSTANCE.getInstance().setLastLoginTimeStamp(System.currentTimeMillis());
                        UserConfig.INSTANCE.getInstance().setLastLoginType(z ? 3 : 1);
                        UserConfig.INSTANCE.getInstance().setLastLoginAccount(str3);
                        UserConfig.INSTANCE.getInstance().setLastLoginPwd(str4);
                        MutableLiveData<LoginResModel> loginResLD = splashActivityVM.getLoginResLD();
                        LoginResModel loginResModelIsSuccess = netState.isSuccess();
                        Intrinsics.checkNotNull(loginResModelIsSuccess, "null cannot be cast to non-null type com.glasssutdio.wear.home.bean.LoginResModel");
                        loginResLD.postValue(loginResModelIsSuccess);
                    } else {
                        splashActivityVM.getLoginFailLD().postValue(new RequestFailModel(netState.getRetCode(), netState.getMsg(), 0, 4, null));
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ void login$default(SplashActivityVM splashActivityVM, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        splashActivityVM.login(str, str2, z);
    }

    public final void login(String account, String md5Pwd, boolean isEmail) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(md5Pwd, "md5Pwd");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10861(account, md5Pwd, isEmail, this, null), 2, null);
    }
}
