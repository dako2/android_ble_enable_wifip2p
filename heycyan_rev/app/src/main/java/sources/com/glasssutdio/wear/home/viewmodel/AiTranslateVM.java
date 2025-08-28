package com.glasssutdio.wear.home.viewmodel;

import android.os.Handler;
import android.os.Looper;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.GlassApplication;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.database.entity.TranslateEntity;
import com.glasssutdio.wear.depository.TranslateDepository;
import com.glasssutdio.wear.home.album.water.WatermarkGenerator;
import com.glasssutdio.wear.p003ai.bean.AsrSrcBean;
import com.glasssutdio.wear.p003ai.bean.TranslateBean;
import com.glasssutdio.wear.p003ai.bean.TranslateResult;
import com.glasssutdio.wear.p003ai.spark.MachineTranslationMain;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.TokenParser;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okhttp3.internal.p017ws.WebSocketProtocol;

/* compiled from: AiTranslateVM.kt */
@Metadata(m606d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0019\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010C\u001a\u00020DJ\u000e\u0010E\u001a\u00020D2\u0006\u0010F\u001a\u00020GJ\u0006\u0010H\u001a\u00020DJ\u0006\u0010I\u001a\u00020/J@\u0010J\u001a\u00020D2\u0006\u0010K\u001a\u00020\u00182\b\u0010L\u001a\u0004\u0018\u00010\u00062\u0006\u0010M\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u00062\u0006\u0010O\u001a\u00020\u00062\u0006\u0010P\u001a\u00020/2\u0006\u0010Q\u001a\u00020\u0006J@\u0010R\u001a\u00020D2\u0006\u0010K\u001a\u00020\u00182\b\u0010L\u001a\u0004\u0018\u00010\u00062\u0006\u0010M\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u00062\u0006\u0010O\u001a\u00020\u00062\u0006\u0010P\u001a\u00020/2\u0006\u0010Q\u001a\u00020\u0006J\u000e\u0010S\u001a\u00020D2\u0006\u0010F\u001a\u00020GJ@\u0010T\u001a\u00020D2\u0006\u0010K\u001a\u00020\u00182\b\u0010L\u001a\u0004\u0018\u00010\u00062\u0006\u0010M\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u00062\u0006\u0010O\u001a\u00020\u00062\u0006\u0010P\u001a\u00020/2\u0006\u0010Q\u001a\u00020\u0006J@\u0010U\u001a\u00020D2\u0006\u0010K\u001a\u00020\u00182\b\u0010L\u001a\u0004\u0018\u00010\u00062\u0006\u0010M\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u00062\u0006\u0010O\u001a\u00020\u00062\u0006\u0010P\u001a\u00020/2\u0006\u0010Q\u001a\u00020\u0006J0\u0010V\u001a\u00020D2\u0006\u0010M\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u00062\u0006\u0010L\u001a\u00020\u00062\u0006\u0010K\u001a\u00020\u00182\u0006\u0010Q\u001a\u00020\u0006H\u0002J0\u0010W\u001a\u00020D2\u0006\u0010M\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u00062\u0006\u0010L\u001a\u00020\u00062\u0006\u0010K\u001a\u00020\u00182\u0006\u0010Q\u001a\u00020\u0006H\u0002J\u000e\u0010X\u001a\u00020D2\u0006\u0010Y\u001a\u00020)J@\u0010Z\u001a\u00020D2\u0006\u0010K\u001a\u00020\u00182\b\u0010L\u001a\u0004\u0018\u00010\u00062\u0006\u0010M\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u00062\u0006\u0010O\u001a\u00020\u00062\u0006\u0010P\u001a\u00020/2\u0006\u0010Q\u001a\u00020\u0006J+\u0010[\u001a\u0004\u0018\u00010:2\u0006\u0010M\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u00062\u0006\u0010\\\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010]J\u0016\u0010^\u001a\u00020D2\u0006\u0010F\u001a\u00020G2\u0006\u0010_\u001a\u00020\u0006R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001a\u0010\u000e\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\u00060\u001ej\u0002`\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\b\"\u0004\b&\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010'\u001a\b\u0012\u0004\u0012\u00020)0(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R \u0010.\u001a\b\u0012\u0004\u0012\u00020/0(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010+\"\u0004\b1\u0010-R \u00102\u001a\b\u0012\u0004\u0012\u00020/0(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010+\"\u0004\b4\u0010-R&\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)060(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010+\"\u0004\b8\u0010-R \u00109\u001a\b\u0012\u0004\u0012\u00020:0(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010+\"\u0004\b<\u0010-R \u0010=\u001a\b\u0012\u0004\u0012\u00020>0(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010+\"\u0004\b@\u0010-R\u000e\u0010A\u001a\u00020BX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006`"}, m607d2 = {"Lcom/glasssutdio/wear/home/viewmodel/AiTranslateVM;", "Landroidx/lifecycle/ViewModel;", "translateDepository", "Lcom/glasssutdio/wear/depository/TranslateDepository;", "(Lcom/glasssutdio/wear/depository/TranslateDepository;)V", "lastBreakSid", "", "getLastBreakSid", "()Ljava/lang/String;", "setLastBreakSid", "(Ljava/lang/String;)V", "lastSid", "getLastSid", "setLastSid", "lastSrcSid", "getLastSrcSid", "setLastSrcSid", "machineTranslationMain", "Lcom/glasssutdio/wear/ai/spark/MachineTranslationMain;", "getMachineTranslationMain", "()Lcom/glasssutdio/wear/ai/spark/MachineTranslationMain;", "machineTranslationMain$delegate", "Lkotlin/Lazy;", "realTimeOffSet", "", "getRealTimeOffSet", "()I", "setRealTimeOffSet", "(I)V", "srcSb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getSrcSb", "()Ljava/lang/StringBuilder;", "setSrcSb", "(Ljava/lang/StringBuilder;)V", "temp", "getTemp", "setTemp", "translateDetailLD", "Landroidx/lifecycle/MutableLiveData;", "Lcom/glasssutdio/wear/database/entity/TranslateEntity;", "getTranslateDetailLD", "()Landroidx/lifecycle/MutableLiveData;", "setTranslateDetailLD", "(Landroidx/lifecycle/MutableLiveData;)V", "translateEmptyLD", "", "getTranslateEmptyLD", "setTranslateEmptyLD", "translateFailLD", "getTranslateFailLD", "setTranslateFailLD", "translateHistoryLD", "", "getTranslateHistoryLD", "setTranslateHistoryLD", "translateLD", "Lcom/glasssutdio/wear/ai/bean/TranslateBean;", "getTranslateLD", "setTranslateLD", "translateSrcLD", "Lcom/glasssutdio/wear/ai/bean/AsrSrcBean;", "getTranslateSrcLD", "setTranslateSrcLD", "translationMutex", "Lkotlinx/coroutines/sync/Mutex;", "cleanTranslateCache", "", "deleteByUidAndTimestamp", "createTime", "", "getAllTranslateHistory", "isNeedOld", "notRealTimeTranslate", NotificationCompat.CATEGORY_STATUS, "asrContent", TypedValues.TransitionType.S_FROM, TypedValues.TransitionType.S_TO, "dest", "isRealTime", "sid", "notRealTimeTranslateOld", "queryByCreateTime", "realTimeTranslate", "realTimeTranslateOld", "realTranslate", "realTranslateOld", "saveTranslate", "entity", "textToTranslate", "translateWithCoroutine", "text", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTranslateTitle", "translateTitle", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AiTranslateVM extends ViewModel {
    private String lastBreakSid;
    private String lastSid;
    private String lastSrcSid;

    /* renamed from: machineTranslationMain$delegate, reason: from kotlin metadata */
    private final Lazy machineTranslationMain;
    private int realTimeOffSet;
    private StringBuilder srcSb;
    private String temp;
    private final TranslateDepository translateDepository;
    private MutableLiveData<TranslateEntity> translateDetailLD;
    private MutableLiveData<Boolean> translateEmptyLD;
    private MutableLiveData<Boolean> translateFailLD;
    private MutableLiveData<List<TranslateEntity>> translateHistoryLD;
    private MutableLiveData<TranslateBean> translateLD;
    private MutableLiveData<AsrSrcBean> translateSrcLD;
    private final Mutex translationMutex;

    public AiTranslateVM(TranslateDepository translateDepository) {
        Intrinsics.checkNotNullParameter(translateDepository, "translateDepository");
        this.translateDepository = translateDepository;
        this.translateLD = new MutableLiveData<>();
        this.translateSrcLD = new MutableLiveData<>();
        this.translateFailLD = new MutableLiveData<>();
        this.translateEmptyLD = new MutableLiveData<>();
        this.translateDetailLD = new MutableLiveData<>();
        this.translateHistoryLD = new MutableLiveData<>();
        this.machineTranslationMain = LazyKt.lazy(new Function0<MachineTranslationMain>() { // from class: com.glasssutdio.wear.home.viewmodel.AiTranslateVM$machineTranslationMain$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MachineTranslationMain invoke() {
                return new MachineTranslationMain();
            }
        });
        this.srcSb = new StringBuilder();
        this.temp = "";
        this.lastSid = "";
        this.lastSrcSid = "";
        this.lastBreakSid = "";
        this.translationMutex = MutexKt.Mutex$default(false, 1, null);
    }

    public final MutableLiveData<TranslateBean> getTranslateLD() {
        return this.translateLD;
    }

    public final void setTranslateLD(MutableLiveData<TranslateBean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.translateLD = mutableLiveData;
    }

    public final MutableLiveData<AsrSrcBean> getTranslateSrcLD() {
        return this.translateSrcLD;
    }

    public final void setTranslateSrcLD(MutableLiveData<AsrSrcBean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.translateSrcLD = mutableLiveData;
    }

    public final MutableLiveData<Boolean> getTranslateFailLD() {
        return this.translateFailLD;
    }

    public final void setTranslateFailLD(MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.translateFailLD = mutableLiveData;
    }

    public final MutableLiveData<Boolean> getTranslateEmptyLD() {
        return this.translateEmptyLD;
    }

    public final void setTranslateEmptyLD(MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.translateEmptyLD = mutableLiveData;
    }

    public final MutableLiveData<TranslateEntity> getTranslateDetailLD() {
        return this.translateDetailLD;
    }

    public final void setTranslateDetailLD(MutableLiveData<TranslateEntity> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.translateDetailLD = mutableLiveData;
    }

    public final MutableLiveData<List<TranslateEntity>> getTranslateHistoryLD() {
        return this.translateHistoryLD;
    }

    public final void setTranslateHistoryLD(MutableLiveData<List<TranslateEntity>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.translateHistoryLD = mutableLiveData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MachineTranslationMain getMachineTranslationMain() {
        return (MachineTranslationMain) this.machineTranslationMain.getValue();
    }

    /* compiled from: AiTranslateVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.AiTranslateVM$deleteByUidAndTimestamp$1", m620f = "AiTranslateVM.kt", m621i = {}, m622l = {EMachine.EM_FIREPATH}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.AiTranslateVM$deleteByUidAndTimestamp$1 */
    static final class C10641 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $createTime;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10641(long j, Continuation<? super C10641> continuation) {
            super(2, continuation);
            this.$createTime = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AiTranslateVM.this.new C10641(this.$createTime, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10641) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (AiTranslateVM.this.translateDepository.deleteByUidAndTimestamp(UserConfig.INSTANCE.getInstance().getUid(), this.$createTime, this) == coroutine_suspended) {
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

    public final void deleteByUidAndTimestamp(long createTime) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10641(createTime, null), 2, null);
    }

    /* compiled from: AiTranslateVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.AiTranslateVM$queryByCreateTime$1", m620f = "AiTranslateVM.kt", m621i = {}, m622l = {EMachine.EM_M32R}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.AiTranslateVM$queryByCreateTime$1 */
    static final class C10681 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $createTime;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10681(long j, Continuation<? super C10681> continuation) {
            super(2, continuation);
            this.$createTime = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AiTranslateVM.this.new C10681(this.$createTime, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10681) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MutableLiveData mutableLiveData;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                MutableLiveData<TranslateEntity> translateDetailLD = AiTranslateVM.this.getTranslateDetailLD();
                this.L$0 = translateDetailLD;
                this.label = 1;
                Object objQueryByCreateTime = AiTranslateVM.this.translateDepository.queryByCreateTime(UserConfig.INSTANCE.getInstance().getUid(), this.$createTime, this);
                if (objQueryByCreateTime == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutableLiveData = translateDetailLD;
                obj = objQueryByCreateTime;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mutableLiveData = (MutableLiveData) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            mutableLiveData.postValue(obj);
            return Unit.INSTANCE;
        }
    }

    public final void queryByCreateTime(long createTime) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10681(createTime, null), 2, null);
    }

    /* compiled from: AiTranslateVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.AiTranslateVM$getAllTranslateHistory$1", m620f = "AiTranslateVM.kt", m621i = {}, m622l = {EMachine.EM_CR}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.AiTranslateVM$getAllTranslateHistory$1 */
    static final class C10651 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C10651(Continuation<? super C10651> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C10651 c10651 = AiTranslateVM.this.new C10651(continuation);
            c10651.L$0 = obj;
            return c10651;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10651) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = BuildersKt__Builders_commonKt.async$default((CoroutineScope) this.L$0, null, null, new AiTranslateVM$getAllTranslateHistory$1$list$1(AiTranslateVM.this, null), 3, null).await(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            AiTranslateVM.this.getTranslateHistoryLD().postValue(CollectionsKt.toMutableList((Collection) obj));
            return Unit.INSTANCE;
        }
    }

    public final void getAllTranslateHistory() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10651(null), 2, null);
    }

    /* compiled from: AiTranslateVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.AiTranslateVM$updateTranslateTitle$1", m620f = "AiTranslateVM.kt", m621i = {}, m622l = {EMachine.EM_UNICORE}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.AiTranslateVM$updateTranslateTitle$1 */
    static final class C10731 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $createTime;
        final /* synthetic */ String $translateTitle;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10731(long j, String str, Continuation<? super C10731> continuation) {
            super(2, continuation);
            this.$createTime = j;
            this.$translateTitle = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AiTranslateVM.this.new C10731(this.$createTime, this.$translateTitle, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10731) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (AiTranslateVM.this.translateDepository.updateTranslateTitle(UserConfig.INSTANCE.getInstance().getUid(), this.$createTime, this.$translateTitle, this) == coroutine_suspended) {
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

    public final void updateTranslateTitle(long createTime, String translateTitle) {
        Intrinsics.checkNotNullParameter(translateTitle, "translateTitle");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10731(createTime, translateTitle, null), 2, null);
    }

    /* compiled from: AiTranslateVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.AiTranslateVM$saveTranslate$1", m620f = "AiTranslateVM.kt", m621i = {}, m622l = {WebSocketProtocol.PAYLOAD_SHORT, WatermarkGenerator.WATERMARK_HEIGHT_DP}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.AiTranslateVM$saveTranslate$1 */
    static final class C10711 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ TranslateEntity $entity;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10711(TranslateEntity translateEntity, Continuation<? super C10711> continuation) {
            super(2, continuation);
            this.$entity = translateEntity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C10711 c10711 = AiTranslateVM.this.new C10711(this.$entity, continuation);
            c10711.L$0 = obj;
            return c10711;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10711) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = BuildersKt__Builders_commonKt.async$default((CoroutineScope) this.L$0, null, null, new AiTranslateVM$saveTranslate$1$queryList$1(AiTranslateVM.this, this.$entity, null), 3, null).await(this);
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
            if (((List) obj).isEmpty()) {
                AiTranslateVM.this.translateDepository.addTranslate(this.$entity);
            } else {
                this.label = 2;
                if (AiTranslateVM.this.translateDepository.updateTranslate(this.$entity.getUid(), this.$entity.getCreateTime(), this.$entity.getSrcContent(), this.$entity.getDstContent(), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    public final void saveTranslate(TranslateEntity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10711(entity, null), 2, null);
    }

    public final boolean isNeedOld() {
        String language = Locale.getDefault().getLanguage();
        Intrinsics.checkNotNullExpressionValue(language, "getLanguage(...)");
        return StringsKt.startsWith$default(language, "zh", false, 2, (Object) null) || GlassApplication.INSTANCE.getGetInstance().getPingGoogle();
    }

    public final void textToTranslate(int status, String asrContent, String from, String to, String dest, boolean isRealTime, String sid) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        Intrinsics.checkNotNullParameter(dest, "dest");
        Intrinsics.checkNotNullParameter(sid, "sid");
        synchronized (this) {
            if (isRealTime) {
                if (isNeedOld()) {
                    realTimeTranslateOld(status, asrContent, from, to, dest, true, sid);
                } else {
                    realTimeTranslate(status, asrContent, from, to, dest, true, sid);
                }
            } else if (isNeedOld()) {
                notRealTimeTranslateOld(status, asrContent, from, to, dest, false, sid);
            } else {
                notRealTimeTranslate(status, asrContent, from, to, dest, false, sid);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final StringBuilder getSrcSb() {
        return this.srcSb;
    }

    public final void setSrcSb(StringBuilder sb) {
        Intrinsics.checkNotNullParameter(sb, "<set-?>");
        this.srcSb = sb;
    }

    public final String getTemp() {
        return this.temp;
    }

    public final void setTemp(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.temp = str;
    }

    public final String getLastSid() {
        return this.lastSid;
    }

    public final void setLastSid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lastSid = str;
    }

    public final String getLastSrcSid() {
        return this.lastSrcSid;
    }

    public final void setLastSrcSid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lastSrcSid = str;
    }

    public final String getLastBreakSid() {
        return this.lastBreakSid;
    }

    public final void setLastBreakSid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lastBreakSid = str;
    }

    public final int getRealTimeOffSet() {
        return this.realTimeOffSet;
    }

    public final void setRealTimeOffSet(int i) {
        this.realTimeOffSet = i;
    }

    public final void realTimeTranslate(int status, String asrContent, String from, String to, String dest, boolean isRealTime, String sid) {
        boolean z;
        String str;
        int i;
        String str2;
        int i2;
        String str3;
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        Intrinsics.checkNotNullParameter(dest, "dest");
        Intrinsics.checkNotNullParameter(sid, "sid");
        XLog.m137i("交互sid:" + sid + ",isRealTime：" + isRealTime + ",status:" + status + ",content:" + asrContent);
        if (status == 0) {
            return;
        }
        if (status == 1) {
            if (!Intrinsics.areEqual(this.lastSrcSid, sid)) {
                XLog.m137i("上一次未返回3状态，本次直接清除上次缓存");
                cleanTranslateCache();
            }
            this.temp = this.srcSb.toString() + asrContent;
            XLog.m137i("asrContent11111 添加：" + (!Intrinsics.areEqual(this.lastSrcSid, sid)) + TokenParser.f390SP + sid + " :===" + this.temp);
            if (this.temp.length() > 0) {
                this.translateSrcLD.postValue(new AsrSrcBean(this.temp, sid, status, false, 8, null));
                this.lastSrcSid = sid;
            } else {
                XLog.m137i("空翻译111：" + this.temp);
                this.translateEmptyLD.postValue(true);
            }
            int i3 = this.realTimeOffSet;
            if (i3 == 0 || i3 == 3) {
                if (this.temp.length() > 0) {
                    z = true;
                    i = 3;
                    str = sid;
                    realTranslate(from, to, this.temp, status, sid);
                    this.realTimeOffSet = 0;
                } else {
                    z = true;
                    i = 3;
                    str = sid;
                }
                this.realTimeOffSet += z ? 1 : 0;
            } else {
                z = true;
                i = 3;
                str = sid;
            }
        } else {
            z = true;
            str = sid;
            i = 3;
        }
        if (status != 2) {
            str2 = "toString(...)";
            i2 = i;
        } else {
            if (Intrinsics.areEqual(this.lastBreakSid, str)) {
                this.srcSb.append(asrContent);
                XLog.m137i("asrContent22222 " + str + "  旧:===" + ((Object) this.srcSb));
            } else {
                this.srcSb.append(asrContent);
                XLog.m137i("asrContent22222 " + str + "  新:===" + ((Object) this.srcSb) + ",arcContent:" + asrContent + ":lastBreakSid:" + this.lastBreakSid + ",,," + str);
            }
            XLog.m137i("asrContent22222 " + str + "   :===" + ((Object) this.srcSb));
            String string = this.srcSb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            if (string.length() <= 0) {
                str2 = "toString(...)";
                str3 = str;
                i2 = i;
                this.translateEmptyLD.postValue(Boolean.valueOf(z));
                XLog.m137i("空翻译2222：" + ((Object) this.srcSb));
            } else {
                MutableLiveData<AsrSrcBean> mutableLiveData = this.translateSrcLD;
                String string2 = this.srcSb.toString();
                Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                str3 = str;
                mutableLiveData.postValue(new AsrSrcBean(string2, sid, status, false, 8, null));
                String string3 = this.srcSb.toString();
                Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                i2 = i;
                str2 = "toString(...)";
                realTranslate(from, to, string3, status, sid);
            }
            this.lastBreakSid = str3;
        }
        if (status == i2) {
            String string4 = this.srcSb.toString();
            Intrinsics.checkNotNullExpressionValue(string4, str2);
            if (string4.length() > 0) {
                String string5 = this.srcSb.toString();
                Intrinsics.checkNotNullExpressionValue(string5, str2);
                realTranslate(from, to, string5, status, sid);
            } else {
                XLog.m137i("空翻译333：" + ((Object) this.srcSb));
            }
            cleanTranslateCache();
        }
    }

    public final void realTimeTranslateOld(int status, String asrContent, String from, String to, String dest, boolean isRealTime, String sid) {
        boolean z;
        String str;
        int i;
        String str2;
        int i2;
        String str3;
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        Intrinsics.checkNotNullParameter(dest, "dest");
        Intrinsics.checkNotNullParameter(sid, "sid");
        XLog.m137i("交互sid:" + sid + ",isRealTime：" + isRealTime + ",status:" + status + ",content:" + asrContent);
        if (status == 0) {
            return;
        }
        if (status == 1) {
            if (!Intrinsics.areEqual(this.lastSrcSid, sid)) {
                XLog.m137i("上一次未返回3状态Old，本次直接清除上次缓存");
                cleanTranslateCache();
            }
            this.temp = this.srcSb.toString() + asrContent;
            XLog.m137i("asrContent11111 添加：" + (!Intrinsics.areEqual(this.lastSrcSid, sid)) + TokenParser.f390SP + sid + " :===" + this.temp);
            if (this.temp.length() > 0) {
                this.translateSrcLD.postValue(new AsrSrcBean(this.temp, sid, status, false, 8, null));
                this.lastSrcSid = sid;
            } else {
                XLog.m137i("空翻译111：" + this.temp);
                this.translateEmptyLD.postValue(true);
            }
            int i3 = this.realTimeOffSet;
            if ((i3 == 0 || i3 == 3) && this.temp.length() > 0) {
                z = true;
                i = 3;
                str = sid;
                realTranslateOld(from, to, this.temp, status, sid);
                this.realTimeOffSet = 0;
            } else {
                z = true;
                i = 3;
                str = sid;
            }
            this.realTimeOffSet += z ? 1 : 0;
        } else {
            z = true;
            str = sid;
            i = 3;
        }
        if (status != 2) {
            str2 = "toString(...)";
            i2 = i;
        } else {
            if (Intrinsics.areEqual(this.lastBreakSid, str)) {
                this.srcSb.append(asrContent);
                XLog.m137i("asrContent22222 " + str + "  旧:===" + ((Object) this.srcSb));
            } else {
                this.srcSb.append(asrContent);
                XLog.m137i("asrContent22222 " + str + "  新:===" + ((Object) this.srcSb) + ",arcContent:" + asrContent + ":lastBreakSid:" + this.lastBreakSid + ",,," + str);
            }
            XLog.m137i("asrContent22222 " + str + "   :===" + ((Object) this.srcSb));
            String string = this.srcSb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            if (string.length() <= 0) {
                str2 = "toString(...)";
                str3 = str;
                i2 = i;
                this.translateEmptyLD.postValue(Boolean.valueOf(z));
                XLog.m137i("空翻译2222：" + ((Object) this.srcSb));
            } else {
                MutableLiveData<AsrSrcBean> mutableLiveData = this.translateSrcLD;
                String string2 = this.srcSb.toString();
                Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                str3 = str;
                mutableLiveData.postValue(new AsrSrcBean(string2, sid, status, false, 8, null));
                String string3 = this.srcSb.toString();
                Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                i2 = i;
                str2 = "toString(...)";
                realTranslateOld(from, to, string3, status, sid);
            }
            this.lastBreakSid = str3;
        }
        if (status == i2) {
            String string4 = this.srcSb.toString();
            Intrinsics.checkNotNullExpressionValue(string4, str2);
            if (string4.length() > 0) {
                String string5 = this.srcSb.toString();
                Intrinsics.checkNotNullExpressionValue(string5, str2);
                realTranslateOld(from, to, string5, status, sid);
            } else {
                XLog.m137i("空翻译333：" + ((Object) this.srcSb));
            }
            cleanTranslateCache();
        }
    }

    /* compiled from: AiTranslateVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.AiTranslateVM$realTranslateOld$1", m620f = "AiTranslateVM.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.AiTranslateVM$realTranslateOld$1 */
    static final class C10701 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $asrContent;
        final /* synthetic */ String $from;
        final /* synthetic */ String $sid;
        final /* synthetic */ int $status;
        final /* synthetic */ String $to;
        int label;
        final /* synthetic */ AiTranslateVM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10701(String str, String str2, AiTranslateVM aiTranslateVM, String str3, int i, String str4, Continuation<? super C10701> continuation) {
            super(2, continuation);
            this.$from = str;
            this.$to = str2;
            this.this$0 = aiTranslateVM;
            this.$asrContent = str3;
            this.$status = i;
            this.$sid = str4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C10701(this.$from, this.$to, this.this$0, this.$asrContent, this.$status, this.$sid, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10701) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String dst;
            String dst2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (!Intrinsics.areEqual(this.$from, this.$to)) {
                if (Intrinsics.areEqual(this.$from, "cn")) {
                    TranslateBean translateBeanTranslateOld = this.this$0.getMachineTranslationMain().translateOld("cn", this.$to, this.$asrContent.toString());
                    if (translateBeanTranslateOld == null || (dst2 = translateBeanTranslateOld.getTrans_result().getDst()) == null || dst2.length() == 0) {
                        XLog.m132e("翻译失败11,status:" + this.$status);
                        this.this$0.getTranslateFailLD().postValue(Boxing.boxBoolean(true));
                        return Unit.INSTANCE;
                    }
                    translateBeanTranslateOld.setNotRealTime(false);
                    translateBeanTranslateOld.setSid(this.$sid);
                    translateBeanTranslateOld.setStatus(this.$status);
                    this.this$0.getTranslateLD().postValue(translateBeanTranslateOld);
                } else {
                    TranslateBean translateBeanTranslateOld2 = this.this$0.getMachineTranslationMain().translateOld(this.$from, "cn", this.$asrContent.toString());
                    if (translateBeanTranslateOld2 == null || (dst = translateBeanTranslateOld2.getTrans_result().getDst()) == null || dst.length() == 0) {
                        XLog.m132e("翻译失败22,status:" + this.$status);
                        this.this$0.getTranslateFailLD().postValue(Boxing.boxBoolean(true));
                        return Unit.INSTANCE;
                    }
                    if (!Intrinsics.areEqual("cn", this.$to)) {
                        TranslateBean translateBeanTranslateOld3 = this.this$0.getMachineTranslationMain().translateOld("cn", this.$to, translateBeanTranslateOld2.getTrans_result().getDst());
                        if (translateBeanTranslateOld3 != null) {
                            translateBeanTranslateOld3.setNotRealTime(false);
                            translateBeanTranslateOld3.setSid(this.$sid);
                            translateBeanTranslateOld3.setStatus(this.$status);
                            this.this$0.getTranslateLD().postValue(translateBeanTranslateOld3);
                        } else {
                            this.this$0.getTranslateFailLD().postValue(Boxing.boxBoolean(true));
                        }
                    } else {
                        translateBeanTranslateOld2.setNotRealTime(false);
                        translateBeanTranslateOld2.setSid(this.$sid);
                        translateBeanTranslateOld2.setStatus(this.$status);
                        this.this$0.getTranslateLD().postValue(translateBeanTranslateOld2);
                    }
                }
            } else {
                TranslateBean translateBean = new TranslateBean();
                translateBean.setTo(this.$to);
                translateBean.setFrom(this.$from);
                translateBean.setNotRealTime(false);
                translateBean.setSid(this.$sid);
                translateBean.setStatus(this.$status);
                TranslateResult translateResult = new TranslateResult();
                translateResult.setSrc(this.$asrContent.toString());
                translateResult.setDst(this.$asrContent.toString());
                translateBean.setTrans_result(translateResult);
                this.this$0.getTranslateLD().postValue(translateBean);
            }
            this.this$0.setLastSid(this.$sid);
            return Unit.INSTANCE;
        }
    }

    private final void realTranslateOld(String from, String to, String asrContent, int status, String sid) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10701(from, to, this, asrContent, status, sid, null), 2, null);
    }

    /* compiled from: AiTranslateVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.AiTranslateVM$notRealTimeTranslateOld$1", m620f = "AiTranslateVM.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.AiTranslateVM$notRealTimeTranslateOld$1 */
    static final class C10671 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $asrContent;
        final /* synthetic */ String $from;
        final /* synthetic */ boolean $isRealTime;
        final /* synthetic */ String $sid;
        final /* synthetic */ int $status;
        final /* synthetic */ String $to;
        int label;
        final /* synthetic */ AiTranslateVM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10671(String str, boolean z, int i, String str2, AiTranslateVM aiTranslateVM, String str3, String str4, Continuation<? super C10671> continuation) {
            super(2, continuation);
            this.$sid = str;
            this.$isRealTime = z;
            this.$status = i;
            this.$asrContent = str2;
            this.this$0 = aiTranslateVM;
            this.$from = str3;
            this.$to = str4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C10671(this.$sid, this.$isRealTime, this.$status, this.$asrContent, this.this$0, this.$from, this.$to, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10671) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String dst;
            String dst2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            XLog.m137i("交互sid:" + this.$sid + ",isRealTime：" + this.$isRealTime + ",status:" + this.$status + ",content:" + this.$asrContent);
            if (this.$status == 0) {
                return Unit.INSTANCE;
            }
            MutableLiveData<AsrSrcBean> translateSrcLD = this.this$0.getTranslateSrcLD();
            String str = this.$asrContent;
            if (str == null) {
                str = "";
            }
            translateSrcLD.postValue(new AsrSrcBean(str, this.$sid, 2, true));
            if (!Intrinsics.areEqual(this.$from, this.$to)) {
                if (Intrinsics.areEqual(this.$from, "cn")) {
                    TranslateBean translateBeanTranslateOld = this.this$0.getMachineTranslationMain().translateOld("cn", this.$to, String.valueOf(this.$asrContent));
                    if (translateBeanTranslateOld == null || (dst2 = translateBeanTranslateOld.getTrans_result().getDst()) == null || dst2.length() == 0) {
                        XLog.m132e("翻译失败");
                        this.this$0.getTranslateFailLD().postValue(Boxing.boxBoolean(true));
                        return Unit.INSTANCE;
                    }
                    translateBeanTranslateOld.setNotRealTime(true);
                    translateBeanTranslateOld.setSid(this.$sid);
                    translateBeanTranslateOld.setStatus(this.$status);
                    this.this$0.getTranslateLD().postValue(translateBeanTranslateOld);
                } else {
                    TranslateBean translateBeanTranslateOld2 = this.this$0.getMachineTranslationMain().translateOld(this.$from, "cn", String.valueOf(this.$asrContent));
                    if (translateBeanTranslateOld2 == null || (dst = translateBeanTranslateOld2.getTrans_result().getDst()) == null || dst.length() == 0) {
                        XLog.m132e("翻译失败");
                        this.this$0.getTranslateFailLD().postValue(Boxing.boxBoolean(true));
                        return Unit.INSTANCE;
                    }
                    if (!Intrinsics.areEqual("cn", this.$to)) {
                        TranslateBean translateBeanTranslateOld3 = this.this$0.getMachineTranslationMain().translateOld("cn", this.$to, translateBeanTranslateOld2.getTrans_result().getDst());
                        if (translateBeanTranslateOld3 != null) {
                            translateBeanTranslateOld3.setNotRealTime(true);
                            translateBeanTranslateOld3.setSid(this.$sid);
                            translateBeanTranslateOld3.setStatus(this.$status);
                            this.this$0.getTranslateLD().postValue(translateBeanTranslateOld3);
                        } else {
                            this.this$0.getTranslateFailLD().postValue(Boxing.boxBoolean(true));
                        }
                    } else {
                        translateBeanTranslateOld2.setNotRealTime(true);
                        translateBeanTranslateOld2.setSid(this.$sid);
                        translateBeanTranslateOld2.setStatus(this.$status);
                        this.this$0.getTranslateLD().postValue(translateBeanTranslateOld2);
                    }
                }
            } else {
                TranslateBean translateBean = new TranslateBean();
                translateBean.setTo(this.$to);
                translateBean.setFrom(this.$from);
                translateBean.setNotRealTime(true);
                translateBean.setSid(this.$sid);
                translateBean.setStatus(this.$status);
                TranslateResult translateResult = new TranslateResult();
                translateResult.setSrc(String.valueOf(this.$asrContent));
                translateResult.setDst(String.valueOf(this.$asrContent));
                translateBean.setTrans_result(translateResult);
                this.this$0.getTranslateLD().postValue(translateBean);
            }
            return Unit.INSTANCE;
        }
    }

    public final void notRealTimeTranslateOld(int status, String asrContent, String from, String to, String dest, boolean isRealTime, String sid) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        Intrinsics.checkNotNullParameter(dest, "dest");
        Intrinsics.checkNotNullParameter(sid, "sid");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10671(sid, isRealTime, status, asrContent, this, from, to, null), 2, null);
    }

    public final void cleanTranslateCache() {
        StringsKt.clear(this.srcSb);
        this.temp = "";
    }

    /* compiled from: AiTranslateVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.AiTranslateVM$realTranslate$1", m620f = "AiTranslateVM.kt", m621i = {0, 0, 1}, m622l = {TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, 519}, m623m = "invokeSuspend", m624n = {"$this$launch", "$this$withLock_u24default$iv", "$this$withLock_u24default$iv"}, m625s = {"L$0", "L$1", "L$0"})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.AiTranslateVM$realTranslate$1 */
    static final class C10691 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $asrContent;
        final /* synthetic */ String $from;
        final /* synthetic */ String $sid;
        final /* synthetic */ int $status;
        final /* synthetic */ String $to;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10691(String str, String str2, int i, String str3, String str4, Continuation<? super C10691> continuation) {
            super(2, continuation);
            this.$from = str;
            this.$to = str2;
            this.$status = i;
            this.$sid = str3;
            this.$asrContent = str4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C10691 c10691 = AiTranslateVM.this.new C10691(this.$from, this.$to, this.$status, this.$sid, this.$asrContent, continuation);
            c10691.L$0 = obj;
            return c10691;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10691) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r6v0 */
        /* JADX WARN: Type inference failed for: r6v1 */
        /* JADX WARN: Type inference failed for: r6v2 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Mutex mutex;
            String str;
            String str2;
            Mutex mutex2;
            int i;
            AiTranslateVM aiTranslateVM;
            String str3;
            CoroutineScope coroutineScope;
            String str4;
            String str5;
            Mutex mutex3;
            Object objAwait;
            int i2;
            String str6;
            TranslateBean translateBean;
            String dst;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            ?? r6 = 1;
            try {
                try {
                    if (i3 == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                        Mutex mutex4 = AiTranslateVM.this.translationMutex;
                        str = this.$from;
                        str2 = this.$to;
                        int i4 = this.$status;
                        AiTranslateVM aiTranslateVM2 = AiTranslateVM.this;
                        String str7 = this.$sid;
                        String str8 = this.$asrContent;
                        this.L$0 = coroutineScope2;
                        this.L$1 = mutex4;
                        this.L$2 = str;
                        this.L$3 = str2;
                        this.L$4 = aiTranslateVM2;
                        this.L$5 = str7;
                        this.L$6 = str8;
                        this.I$0 = i4;
                        this.label = 1;
                        if (mutex4.lock(null, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        mutex2 = mutex4;
                        i = i4;
                        aiTranslateVM = aiTranslateVM2;
                        str3 = str7;
                        coroutineScope = coroutineScope2;
                        str4 = str8;
                    } else {
                        if (i3 != 1) {
                            if (i3 != 2) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            i2 = this.I$0;
                            str6 = (String) this.L$2;
                            AiTranslateVM aiTranslateVM3 = (AiTranslateVM) this.L$1;
                            mutex = (Mutex) this.L$0;
                            try {
                                ResultKt.throwOnFailure(obj);
                                mutex3 = mutex;
                                aiTranslateVM = aiTranslateVM3;
                                objAwait = obj;
                                translateBean = (TranslateBean) objAwait;
                                if (translateBean != null && (dst = translateBean.getTrans_result().getDst()) != null && dst.length() != 0) {
                                    translateBean.setNotRealTime(false);
                                    translateBean.setSid(str6);
                                    translateBean.setStatus(i2);
                                    aiTranslateVM.getTranslateLD().postValue(translateBean);
                                    str3 = str6;
                                    aiTranslateVM.setLastSid(str3);
                                    Unit unit = Unit.INSTANCE;
                                    mutex3.unlock(null);
                                    return Unit.INSTANCE;
                                }
                                XLog.m132e("翻译失败11,status:" + i2);
                                aiTranslateVM.getTranslateFailLD().postValue(Boxing.boxBoolean(true));
                                Unit unit2 = Unit.INSTANCE;
                                mutex3.unlock(null);
                                return unit2;
                            } catch (Throwable th) {
                                th = th;
                                mutex.unlock(null);
                                throw th;
                            }
                        }
                        i = this.I$0;
                        String str9 = (String) this.L$6;
                        str3 = (String) this.L$5;
                        AiTranslateVM aiTranslateVM4 = (AiTranslateVM) this.L$4;
                        str2 = (String) this.L$3;
                        str = (String) this.L$2;
                        Mutex mutex5 = (Mutex) this.L$1;
                        CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        mutex2 = mutex5;
                        coroutineScope = coroutineScope3;
                        str4 = str9;
                        aiTranslateVM = aiTranslateVM4;
                    }
                    if (!Intrinsics.areEqual(str, str5)) {
                        mutex3 = mutex2;
                        Deferred deferredAsync$default = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new AiTranslateVM$realTranslate$1$1$res$1(aiTranslateVM, str, str5, str4, null), 3, null);
                        this.L$0 = mutex3;
                        this.L$1 = aiTranslateVM;
                        this.L$2 = str3;
                        this.L$3 = null;
                        this.L$4 = null;
                        this.L$5 = null;
                        this.L$6 = null;
                        this.I$0 = i;
                        this.label = 2;
                        objAwait = deferredAsync$default.await(this);
                        if (objAwait == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        i2 = i;
                        str6 = str3;
                        translateBean = (TranslateBean) objAwait;
                        if (translateBean != null) {
                            translateBean.setNotRealTime(false);
                            translateBean.setSid(str6);
                            translateBean.setStatus(i2);
                            aiTranslateVM.getTranslateLD().postValue(translateBean);
                            str3 = str6;
                            aiTranslateVM.setLastSid(str3);
                            Unit unit3 = Unit.INSTANCE;
                            mutex3.unlock(null);
                            return Unit.INSTANCE;
                        }
                        XLog.m132e("翻译失败11,status:" + i2);
                        aiTranslateVM.getTranslateFailLD().postValue(Boxing.boxBoolean(true));
                        Unit unit22 = Unit.INSTANCE;
                        mutex3.unlock(null);
                        return unit22;
                    }
                    mutex3 = mutex2;
                    TranslateBean translateBean2 = new TranslateBean();
                    translateBean2.setTo(str5);
                    translateBean2.setFrom(str);
                    translateBean2.setNotRealTime(false);
                    translateBean2.setSid(str3);
                    translateBean2.setStatus(i);
                    TranslateResult translateResult = new TranslateResult();
                    translateResult.setSrc(str4.toString());
                    translateResult.setDst(str4.toString());
                    translateBean2.setTrans_result(translateResult);
                    aiTranslateVM.getTranslateLD().postValue(translateBean2);
                    aiTranslateVM.setLastSid(str3);
                    Unit unit32 = Unit.INSTANCE;
                    mutex3.unlock(null);
                    return Unit.INSTANCE;
                } catch (Throwable th2) {
                    th = th2;
                    r6 = mutex2;
                    mutex = r6;
                    mutex.unlock(null);
                    throw th;
                }
                str5 = str2;
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    private final void realTranslate(String from, String to, String asrContent, int status, String sid) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10691(from, to, status, sid, asrContent, null), 2, null);
    }

    /* compiled from: AiTranslateVM.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.AiTranslateVM$notRealTimeTranslate$1", m620f = "AiTranslateVM.kt", m621i = {0, 0, 1}, m622l = {TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, 571}, m623m = "invokeSuspend", m624n = {"$this$launch", "$this$withLock_u24default$iv", "$this$withLock_u24default$iv"}, m625s = {"L$0", "L$1", "L$0"})
    /* renamed from: com.glasssutdio.wear.home.viewmodel.AiTranslateVM$notRealTimeTranslate$1 */
    static final class C10661 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $asrContent;
        final /* synthetic */ String $from;
        final /* synthetic */ boolean $isRealTime;
        final /* synthetic */ String $sid;
        final /* synthetic */ int $status;
        final /* synthetic */ String $to;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        boolean Z$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10661(String str, boolean z, int i, String str2, String str3, String str4, Continuation<? super C10661> continuation) {
            super(2, continuation);
            this.$sid = str;
            this.$isRealTime = z;
            this.$status = i;
            this.$asrContent = str2;
            this.$from = str3;
            this.$to = str4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C10661 c10661 = AiTranslateVM.this.new C10661(this.$sid, this.$isRealTime, this.$status, this.$asrContent, this.$from, this.$to, continuation);
            c10661.L$0 = obj;
            return c10661;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10661) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            String str;
            AiTranslateVM aiTranslateVM;
            String str2;
            CoroutineScope coroutineScope;
            Mutex mutex;
            String str3;
            boolean z;
            int i;
            String str4;
            Mutex mutex2;
            Object objAwait;
            AiTranslateVM aiTranslateVM2;
            TranslateBean translateBean;
            String dst;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            try {
                if (i2 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                    Mutex mutex3 = AiTranslateVM.this.translationMutex;
                    String str5 = this.$sid;
                    boolean z2 = this.$isRealTime;
                    int i3 = this.$status;
                    str = this.$asrContent;
                    AiTranslateVM aiTranslateVM3 = AiTranslateVM.this;
                    String str6 = this.$from;
                    String str7 = this.$to;
                    this.L$0 = coroutineScope2;
                    this.L$1 = mutex3;
                    this.L$2 = str5;
                    this.L$3 = str;
                    this.L$4 = aiTranslateVM3;
                    this.L$5 = str6;
                    this.L$6 = str7;
                    this.Z$0 = z2;
                    this.I$0 = i3;
                    this.label = 1;
                    if (mutex3.lock(null, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    aiTranslateVM = aiTranslateVM3;
                    str2 = str7;
                    coroutineScope = coroutineScope2;
                    mutex = mutex3;
                    str3 = str5;
                    z = z2;
                    i = i3;
                    str4 = str6;
                } else {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        int i4 = this.I$0;
                        aiTranslateVM2 = (AiTranslateVM) this.L$2;
                        String str8 = (String) this.L$1;
                        mutex2 = (Mutex) this.L$0;
                        try {
                            ResultKt.throwOnFailure(obj);
                            str3 = str8;
                            i = i4;
                            objAwait = obj;
                            translateBean = (TranslateBean) objAwait;
                            if (translateBean != null && (dst = translateBean.getTrans_result().getDst()) != null && dst.length() != 0) {
                                translateBean.setNotRealTime(true);
                                translateBean.setSid(str3);
                                translateBean.setStatus(i);
                                aiTranslateVM2.getTranslateLD().postValue(translateBean);
                                Unit unit = Unit.INSTANCE;
                                mutex2.unlock(null);
                                return Unit.INSTANCE;
                            }
                            XLog.m132e("翻译失败,status:" + i);
                            aiTranslateVM2.getTranslateFailLD().postValue(Boxing.boxBoolean(true));
                            Unit unit2 = Unit.INSTANCE;
                            mutex2.unlock(null);
                            return unit2;
                        } catch (Throwable th) {
                            th = th;
                            mutex2.unlock(null);
                            throw th;
                        }
                    }
                    i = this.I$0;
                    z = this.Z$0;
                    String str9 = (String) this.L$6;
                    str4 = (String) this.L$5;
                    AiTranslateVM aiTranslateVM4 = (AiTranslateVM) this.L$4;
                    str = (String) this.L$3;
                    String str10 = (String) this.L$2;
                    Mutex mutex4 = (Mutex) this.L$1;
                    CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    aiTranslateVM = aiTranslateVM4;
                    coroutineScope = coroutineScope3;
                    str2 = str9;
                    mutex = mutex4;
                    str3 = str10;
                }
                XLog.m137i("交互sid:" + str3 + ",isRealTime：" + z + ",status:" + i + ",content:" + str);
                if (i == 0) {
                    Unit unit3 = Unit.INSTANCE;
                    mutex.unlock(null);
                    return unit3;
                }
                aiTranslateVM.getTranslateSrcLD().postValue(new AsrSrcBean(str == null ? "" : str, str3, 2, true));
                if (Intrinsics.areEqual(str4, str2)) {
                    TranslateBean translateBean2 = new TranslateBean();
                    translateBean2.setTo(str2);
                    translateBean2.setFrom(str4);
                    translateBean2.setNotRealTime(true);
                    translateBean2.setSid(str3);
                    translateBean2.setStatus(i);
                    TranslateResult translateResult = new TranslateResult();
                    translateResult.setSrc(String.valueOf(str));
                    translateResult.setDst(String.valueOf(str));
                    translateBean2.setTrans_result(translateResult);
                    aiTranslateVM.getTranslateLD().postValue(translateBean2);
                    mutex2 = mutex;
                    Unit unit4 = Unit.INSTANCE;
                    mutex2.unlock(null);
                    return Unit.INSTANCE;
                }
                Deferred deferredAsync$default = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new AiTranslateVM$notRealTimeTranslate$1$1$res$1(aiTranslateVM, str4, str2, str, null), 3, null);
                this.L$0 = mutex;
                this.L$1 = str3;
                this.L$2 = aiTranslateVM;
                this.L$3 = null;
                this.L$4 = null;
                this.L$5 = null;
                this.L$6 = null;
                this.I$0 = i;
                this.label = 2;
                objAwait = deferredAsync$default.await(this);
                if (objAwait == coroutine_suspended) {
                    return coroutine_suspended;
                }
                aiTranslateVM2 = aiTranslateVM;
                mutex2 = mutex;
                translateBean = (TranslateBean) objAwait;
                if (translateBean != null) {
                    translateBean.setNotRealTime(true);
                    translateBean.setSid(str3);
                    translateBean.setStatus(i);
                    aiTranslateVM2.getTranslateLD().postValue(translateBean);
                    Unit unit42 = Unit.INSTANCE;
                    mutex2.unlock(null);
                    return Unit.INSTANCE;
                }
                XLog.m132e("翻译失败,status:" + i);
                aiTranslateVM2.getTranslateFailLD().postValue(Boxing.boxBoolean(true));
                Unit unit22 = Unit.INSTANCE;
                mutex2.unlock(null);
                return unit22;
            } catch (Throwable th2) {
                th = th2;
                mutex2 = mutex;
                mutex2.unlock(null);
                throw th;
            }
        }
    }

    public final void notRealTimeTranslate(int status, String asrContent, String from, String to, String dest, boolean isRealTime, String sid) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        Intrinsics.checkNotNullParameter(dest, "dest");
        Intrinsics.checkNotNullParameter(sid, "sid");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C10661(sid, isRealTime, status, asrContent, from, to, null), 2, null);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.glasssutdio.wear.home.viewmodel.AiTranslateVM$translateWithCoroutine$2$callback$1] */
    public final Object translateWithCoroutine(String str, String str2, String str3, Continuation<? super TranslateBean> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        final ?? r2 = new MachineTranslationMain.OnTranslateListener() { // from class: com.glasssutdio.wear.home.viewmodel.AiTranslateVM$translateWithCoroutine$2$callback$1
            private final AtomicBoolean isCompleted = new AtomicBoolean(false);

            @Override // com.glasssutdio.wear.ai.spark.MachineTranslationMain.OnTranslateListener
            public void onTranslate(TranslateBean translateBean) {
                if (this.isCompleted.compareAndSet(false, true)) {
                    GlobalKt.resumeIfActive(cancellableContinuationImpl2, translateBean);
                }
            }

            @Override // com.glasssutdio.wear.ai.spark.MachineTranslationMain.OnTranslateListener
            public void onTranslateError(int errorCode) {
                if (this.isCompleted.compareAndSet(false, true)) {
                    GlobalKt.resumeIfActive(cancellableContinuationImpl2, null);
                }
            }
        };
        getMachineTranslationMain().setListener(null);
        getMachineTranslationMain().setListener((MachineTranslationMain.OnTranslateListener) r2);
        getMachineTranslationMain().translate(str, str2, str3);
        cancellableContinuationImpl2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: com.glasssutdio.wear.home.viewmodel.AiTranslateVM$translateWithCoroutine$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                Handler handler = new Handler(Looper.getMainLooper());
                final AiTranslateVM aiTranslateVM = this.this$0;
                final AiTranslateVM$translateWithCoroutine$2$callback$1 aiTranslateVM$translateWithCoroutine$2$callback$1 = r2;
                handler.post(new Runnable() { // from class: com.glasssutdio.wear.home.viewmodel.AiTranslateVM$translateWithCoroutine$2$1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (Intrinsics.areEqual(aiTranslateVM.getMachineTranslationMain().getListener(), aiTranslateVM$translateWithCoroutine$2$callback$1)) {
                            aiTranslateVM.getMachineTranslationMain().setListener(null);
                        }
                    }
                });
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
