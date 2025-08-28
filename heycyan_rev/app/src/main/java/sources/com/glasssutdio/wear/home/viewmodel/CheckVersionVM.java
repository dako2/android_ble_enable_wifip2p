package com.glasssutdio.wear.home.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.glasssutdio.wear.all.bean.Req.CheckVersionReq;
import com.glasssutdio.wear.api.NetState;
import com.glasssutdio.wear.depository.SettingDepository;
import com.glasssutdio.wear.home.bean.AppUpgradeModel;
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

/* compiled from: CheckVersionVM.kt */
@Metadata(m606d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0007\"\u0004\b\r\u0010\t¨\u0006\u0012"}, m607d2 = {"Lcom/glasssutdio/wear/home/viewmodel/CheckVersionVM;", "Landroidx/lifecycle/ViewModel;", "()V", "checkVersionLD", "Landroidx/lifecycle/MutableLiveData;", "", "getCheckVersionLD", "()Landroidx/lifecycle/MutableLiveData;", "setCheckVersionLD", "(Landroidx/lifecycle/MutableLiveData;)V", "failLD", "Lcom/glasssutdio/wear/home/bean/RequestFailModel;", "getFailLD", "setFailLD", "appLastVersion", "", "req", "Lcom/glasssutdio/wear/all/bean/Req/CheckVersionReq;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class CheckVersionVM extends ViewModel {
    private MutableLiveData<RequestFailModel> failLD = new MutableLiveData<>();
    private MutableLiveData<String> checkVersionLD = new MutableLiveData<>();

    public final MutableLiveData<RequestFailModel> getFailLD() {
        return this.failLD;
    }

    public final void setFailLD(MutableLiveData<RequestFailModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.failLD = mutableLiveData;
    }

    public final MutableLiveData<String> getCheckVersionLD() {
        return this.checkVersionLD;
    }

    public final void setCheckVersionLD(MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.checkVersionLD = mutableLiveData;
    }

    /* compiled from: CheckVersionVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.CheckVersionVM$appLastVersion$1", m620f = "CheckVersionVM.kt", m621i = {}, m622l = {19}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.CheckVersionVM$appLastVersion$1 */
    static final class C10741 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ CheckVersionReq $req;
        int label;
        final /* synthetic */ CheckVersionVM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10741(CheckVersionReq checkVersionReq, CheckVersionVM checkVersionVM, Continuation<? super C10741> continuation) {
            super(2, continuation);
            this.$req = checkVersionReq;
            this.this$0 = checkVersionVM;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C10741(this.$req, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10741) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<NetState<AppUpgradeModel>> flowAppLastVersion = SettingDepository.INSTANCE.getGetInstance().appLastVersion(this.$req);
                final CheckVersionVM checkVersionVM = this.this$0;
                this.label = 1;
                if (flowAppLastVersion.collect(new FlowCollector() { // from class: com.glasssutdio.wear.home.viewmodel.CheckVersionVM.appLastVersion.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((NetState<AppUpgradeModel>) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(NetState<AppUpgradeModel> netState, Continuation<? super Unit> continuation) {
                        if (netState.getRetCode() == 0) {
                            checkVersionVM.getCheckVersionLD().postValue("");
                        } else {
                            checkVersionVM.getFailLD().postValue(new RequestFailModel(netState.getRetCode(), netState.getMsg(), 0, 4, null));
                        }
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
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

    public final void appLastVersion(CheckVersionReq req) {
        Intrinsics.checkNotNullParameter(req, "req");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10741(req, this, null), 2, null);
    }
}
