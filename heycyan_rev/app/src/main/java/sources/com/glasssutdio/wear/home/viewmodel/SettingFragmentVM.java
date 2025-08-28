package com.glasssutdio.wear.home.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.glasssutdio.wear.all.bean.Req.UpdateUserReq;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.all.utils.MoshiUtilsKt;
import com.glasssutdio.wear.api.NetState;
import com.glasssutdio.wear.bus.RefreshUserEvent;
import com.glasssutdio.wear.depository.LoginDepository;
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
import org.greenrobot.eventbus.EventBus;

/* compiled from: SettingFragmentVM.kt */
@Metadata(m606d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cR\u0014\u0010\u0003\u001a\u00020\u0004X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\r¨\u0006\u001d"}, m607d2 = {"Lcom/glasssutdio/wear/home/viewmodel/SettingFragmentVM;", "Landroidx/lifecycle/ViewModel;", "()V", "REQ_TYPE_USER_INFO", "", "getREQ_TYPE_USER_INFO$app_release", "()I", "failLD", "Landroidx/lifecycle/MutableLiveData;", "Lcom/glasssutdio/wear/home/bean/RequestFailModel;", "getFailLD", "()Landroidx/lifecycle/MutableLiveData;", "setFailLD", "(Landroidx/lifecycle/MutableLiveData;)V", "updateLD", "", "getUpdateLD", "setUpdateLD", "userInfoLD", "Lcom/glasssutdio/wear/home/bean/UserModel;", "getUserInfoLD", "setUserInfoLD", "getUserInfo", "", "uid", "", "updateUserInfo", "req", "Lcom/glasssutdio/wear/all/bean/Req/UpdateUserReq;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class SettingFragmentVM extends ViewModel {
    private final int REQ_TYPE_USER_INFO = 1;
    private MutableLiveData<UserModel> userInfoLD = new MutableLiveData<>();
    private MutableLiveData<RequestFailModel> failLD = new MutableLiveData<>();
    private MutableLiveData<Boolean> updateLD = new MutableLiveData<>();

    /* renamed from: getREQ_TYPE_USER_INFO$app_release, reason: from getter */
    public final int getREQ_TYPE_USER_INFO() {
        return this.REQ_TYPE_USER_INFO;
    }

    public final MutableLiveData<UserModel> getUserInfoLD() {
        return this.userInfoLD;
    }

    public final void setUserInfoLD(MutableLiveData<UserModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.userInfoLD = mutableLiveData;
    }

    public final MutableLiveData<RequestFailModel> getFailLD() {
        return this.failLD;
    }

    public final void setFailLD(MutableLiveData<RequestFailModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.failLD = mutableLiveData;
    }

    public final MutableLiveData<Boolean> getUpdateLD() {
        return this.updateLD;
    }

    public final void setUpdateLD(MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.updateLD = mutableLiveData;
    }

    /* compiled from: SettingFragmentVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.SettingFragmentVM$getUserInfo$1", m620f = "SettingFragmentVM.kt", m621i = {}, m622l = {26, 26}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.SettingFragmentVM$getUserInfo$1 */
    static final class C10841 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $uid;
        int label;
        final /* synthetic */ SettingFragmentVM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10841(String str, SettingFragmentVM settingFragmentVM, Continuation<? super C10841> continuation) {
            super(2, continuation);
            this.$uid = str;
            this.this$0 = settingFragmentVM;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C10841(this.$uid, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10841) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = LoginDepository.INSTANCE.getGetInstance().getUserInfo(this.$uid, this);
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
            final SettingFragmentVM settingFragmentVM = this.this$0;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.glasssutdio.wear.home.viewmodel.SettingFragmentVM.getUserInfo.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<UserModel>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<UserModel> netState, Continuation<? super Unit> continuation) {
                    if (netState.getRetCode() == 0) {
                        UserConfig companion = UserConfig.INSTANCE.getInstance();
                        UserModel userModelIsSuccess = netState.isSuccess();
                        Intrinsics.checkNotNull(userModelIsSuccess, "null cannot be cast to non-null type com.glasssutdio.wear.home.bean.UserModel");
                        companion.setUserJson(MoshiUtilsKt.toJson(userModelIsSuccess));
                        MutableLiveData<UserModel> userInfoLD = settingFragmentVM.getUserInfoLD();
                        UserModel userModelIsSuccess2 = netState.isSuccess();
                        Intrinsics.checkNotNull(userModelIsSuccess2, "null cannot be cast to non-null type com.glasssutdio.wear.home.bean.UserModel");
                        userInfoLD.postValue(userModelIsSuccess2);
                    } else {
                        settingFragmentVM.getFailLD().postValue(new RequestFailModel(netState.getRetCode(), netState.getMsg(), settingFragmentVM.getREQ_TYPE_USER_INFO()));
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getUserInfo(String uid) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10841(uid, this, null), 2, null);
    }

    /* compiled from: SettingFragmentVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.SettingFragmentVM$updateUserInfo$1", m620f = "SettingFragmentVM.kt", m621i = {}, m622l = {39, 39}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.SettingFragmentVM$updateUserInfo$1 */
    static final class C10851 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ UpdateUserReq $req;
        int label;
        final /* synthetic */ SettingFragmentVM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10851(UpdateUserReq updateUserReq, SettingFragmentVM settingFragmentVM, Continuation<? super C10851> continuation) {
            super(2, continuation);
            this.$req = updateUserReq;
            this.this$0 = settingFragmentVM;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C10851(this.$req, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10851) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = LoginDepository.INSTANCE.getGetInstance().updateUserInfo(this.$req, this);
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
            final SettingFragmentVM settingFragmentVM = this.this$0;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.glasssutdio.wear.home.viewmodel.SettingFragmentVM.updateUserInfo.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<UserModel>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<UserModel> netState, Continuation<? super Unit> continuation) {
                    if (netState.getRetCode() == 0) {
                        EventBus.getDefault().post(new RefreshUserEvent());
                        settingFragmentVM.getUpdateLD().postValue(Boxing.boxBoolean(true));
                    } else {
                        settingFragmentVM.getFailLD().postValue(new RequestFailModel(netState.getRetCode(), netState.getMsg(), 0, 4, null));
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void updateUserInfo(UpdateUserReq req) {
        Intrinsics.checkNotNullParameter(req, "req");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10851(req, this, null), 2, null);
    }
}
