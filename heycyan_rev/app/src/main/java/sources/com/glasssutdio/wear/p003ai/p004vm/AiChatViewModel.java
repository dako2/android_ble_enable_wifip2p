package com.glasssutdio.wear.p003ai.p004vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.GlobalKt;
import com.glasssutdio.wear.all.ThreadExtKt;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.database.entity.AiChatEntity;
import com.glasssutdio.wear.depository.AiChatDepository;
import com.glasssutdio.wear.depository.bean.ChatEnumAction;
import com.glasssutdio.wear.p003ai.bean.ChatMessage;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.resp.BaseResponse;
import com.oudmon.ble.base.communication.bigData.resp.GlassModelControlResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AiChatViewModel.kt */
@Metadata(m606d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001:\u00013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u001e\u001a\u00020\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\n0!J\u0016\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0010J\u000e\u0010&\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020(J\u0006\u0010)\u001a\u00020\u001fJ\u0006\u0010*\u001a\u00020\u001fJ\u0006\u0010+\u001a\u00020\u001fJ3\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020$2\u0006\u0010.\u001a\u00020$2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u00101J\u0006\u00102\u001a\u00020\u001fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082D¢\u0006\u0002\n\u0000R \u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\f\"\u0004\b\u0019\u0010\u000eR\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u001b8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u00064"}, m607d2 = {"Lcom/glasssutdio/wear/ai/vm/AiChatViewModel;", "Landroidx/lifecycle/ViewModel;", "aiChatDepository", "Lcom/glasssutdio/wear/depository/AiChatDepository;", "(Lcom/glasssutdio/wear/depository/AiChatDepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/glasssutdio/wear/ai/vm/AiChatViewModel$AiChatUI;", "chatList", "", "Lcom/glasssutdio/wear/ai/bean/ChatMessage;", "getChatList", "()Ljava/util/List;", "setChatList", "(Ljava/util/List;)V", "emptyLD", "", "getEmptyLD", "()Landroidx/lifecycle/MutableLiveData;", "setEmptyLD", "(Landroidx/lifecycle/MutableLiveData;)V", "limit", "", "loadMore", "getLoadMore", "setLoadMore", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "deleteAiHistoryList", "", "models", "", "queryAiData", "lastTimestamp", "", "first", "setChatGptCallback", "chatGptCallback", "Lcom/glasssutdio/wear/depository/AiChatDepository$ChatGptCallback;", "startRecord", "startVideo", "takePicture", "updateAiChatModelLikeOrOnLike", "uid", "chatTimestamp", "isLike", "isUnLike", "(JJLjava/lang/Boolean;Ljava/lang/Boolean;)V", "visionAiStart", "AiChatUI", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class AiChatViewModel extends ViewModel {
    private final MutableLiveData<AiChatUI> _uiState;
    private final AiChatDepository aiChatDepository;
    private List<ChatMessage> chatList;
    private MutableLiveData<Boolean> emptyLD;
    private final int limit;
    private List<ChatMessage> loadMore;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void takePicture$lambda$1(int i, GlassModelControlResponse glassModelControlResponse) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void visionAiStart$lambda$0(int i, GlassModelControlResponse glassModelControlResponse) {
    }

    public AiChatViewModel(AiChatDepository aiChatDepository) {
        Intrinsics.checkNotNullParameter(aiChatDepository, "aiChatDepository");
        this.aiChatDepository = aiChatDepository;
        this.limit = 50;
        this.chatList = new ArrayList();
        this.loadMore = new ArrayList();
        this.emptyLD = new MutableLiveData<>();
        this._uiState = new MutableLiveData<>();
    }

    public final List<ChatMessage> getChatList() {
        return this.chatList;
    }

    public final void setChatList(List<ChatMessage> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.chatList = list;
    }

    public final List<ChatMessage> getLoadMore() {
        return this.loadMore;
    }

    public final void setLoadMore(List<ChatMessage> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.loadMore = list;
    }

    public final MutableLiveData<Boolean> getEmptyLD() {
        return this.emptyLD;
    }

    public final void setEmptyLD(MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.emptyLD = mutableLiveData;
    }

    public final LiveData<AiChatUI> getUiState() {
        return this._uiState;
    }

    public final void queryAiData(final long lastTimestamp, final boolean first) {
        if (first) {
            this.chatList.clear();
        }
        this.loadMore.clear();
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<AiChatViewModel, Unit>() { // from class: com.glasssutdio.wear.ai.vm.AiChatViewModel.queryAiData.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AiChatViewModel aiChatViewModel) {
                invoke2(aiChatViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AiChatViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                List<AiChatEntity> listQueryChatHistory = ktxRunOnBgSingle.aiChatDepository.queryChatHistory(UserConfig.INSTANCE.getInstance().getUid(), lastTimestamp, ktxRunOnBgSingle.limit);
                if (listQueryChatHistory.isEmpty()) {
                    XLog.m137i("没有数据了");
                    ktxRunOnBgSingle.getEmptyLD().postValue(true);
                    return;
                }
                boolean z = first;
                for (AiChatEntity aiChatEntity : listQueryChatHistory) {
                    String localFilePath = aiChatEntity.getLocalFilePath();
                    if (aiChatEntity.getLocalFilePath().length() == 0) {
                        localFilePath = aiChatEntity.getFilePath();
                    }
                    String str = localFilePath;
                    boolean zAreEqual = Intrinsics.areEqual(aiChatEntity.getRole(), ChatEnumAction.USER);
                    if (z) {
                        ktxRunOnBgSingle.getChatList().add(new ChatMessage(aiChatEntity.getUid(), aiChatEntity.getContent(), str, zAreEqual, aiChatEntity.getChatType(), aiChatEntity.getChatTimestamp(), false, aiChatEntity.isLike(), aiChatEntity.isUnlike(), false, false, false, false, null, 0, false, 65088, null));
                    } else {
                        ktxRunOnBgSingle.getLoadMore().add(new ChatMessage(aiChatEntity.getUid(), aiChatEntity.getContent(), str, zAreEqual, aiChatEntity.getChatType(), aiChatEntity.getChatTimestamp(), false, false, false, false, false, false, false, null, 0, false, 65472, null));
                    }
                }
                ktxRunOnBgSingle._uiState.postValue(new AiChatUI(true, first));
            }
        });
    }

    /* compiled from: AiChatViewModel.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.ai.vm.AiChatViewModel$deleteAiHistoryList$1", m620f = "AiChatViewModel.kt", m621i = {}, m622l = {101}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.ai.vm.AiChatViewModel$deleteAiHistoryList$1 */
    static final class C07961 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<ChatMessage> $models;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ AiChatViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07961(List<ChatMessage> list, AiChatViewModel aiChatViewModel, Continuation<? super C07961> continuation) {
            super(2, continuation);
            this.$models = list;
            this.this$0 = aiChatViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C07961(this.$models, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07961) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AiChatViewModel aiChatViewModel;
            Iterator it;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                List<ChatMessage> list = this.$models;
                aiChatViewModel = this.this$0;
                it = list.iterator();
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                it = (Iterator) this.L$1;
                aiChatViewModel = (AiChatViewModel) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            while (it.hasNext()) {
                ChatMessage chatMessage = (ChatMessage) it.next();
                AiChatDepository aiChatDepository = aiChatViewModel.aiChatDepository;
                long uid = chatMessage.getUid();
                long chatTimeStamp = chatMessage.getChatTimeStamp();
                this.L$0 = aiChatViewModel;
                this.L$1 = it;
                this.label = 1;
                if (aiChatDepository.deleteByUidAndTimestamp(uid, chatTimeStamp, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    public final void deleteAiHistoryList(List<ChatMessage> models) {
        Intrinsics.checkNotNullParameter(models, "models");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C07961(models, this, null), 2, null);
    }

    /* compiled from: AiChatViewModel.kt */
    @Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
    @DebugMetadata(m619c = "com.glasssutdio.wear.ai.vm.AiChatViewModel$updateAiChatModelLikeOrOnLike$1", m620f = "AiChatViewModel.kt", m621i = {}, m622l = {EMachine.EM_DSPIC30F, EMachine.EM_M32C}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: com.glasssutdio.wear.ai.vm.AiChatViewModel$updateAiChatModelLikeOrOnLike$1 */
    static final class C07981 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $chatTimestamp;
        final /* synthetic */ Boolean $isLike;
        final /* synthetic */ Boolean $isUnLike;
        final /* synthetic */ long $uid;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07981(long j, long j2, Boolean bool, Boolean bool2, Continuation<? super C07981> continuation) {
            super(2, continuation);
            this.$uid = j;
            this.$chatTimestamp = j2;
            this.$isLike = bool;
            this.$isUnLike = bool2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C07981 c07981 = AiChatViewModel.this.new C07981(this.$uid, this.$chatTimestamp, this.$isLike, this.$isUnLike, continuation);
            c07981.L$0 = obj;
            return c07981;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07981) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = BuildersKt__Builders_commonKt.async$default((CoroutineScope) this.L$0, Dispatchers.getIO(), null, new AiChatViewModel$updateAiChatModelLikeOrOnLike$1$aiChatModel$1(AiChatViewModel.this, this.$uid, this.$chatTimestamp, null), 2, null).await(this);
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
            AiChatEntity aiChatEntity = (AiChatEntity) obj;
            if (aiChatEntity != null) {
                AiChatViewModel aiChatViewModel = AiChatViewModel.this;
                long j = this.$uid;
                long j2 = this.$chatTimestamp;
                Boolean bool = this.$isLike;
                Boolean bool2 = this.$isUnLike;
                AiChatDepository aiChatDepository = aiChatViewModel.aiChatDepository;
                boolean zBooleanValue = bool != null ? bool.booleanValue() : aiChatEntity.isLike();
                boolean zBooleanValue2 = bool2 != null ? bool2.booleanValue() : aiChatEntity.isUnlike();
                this.label = 2;
                if (aiChatDepository.updateLikeUnlikeStatus(j, j2, zBooleanValue, zBooleanValue2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    public final void updateAiChatModelLikeOrOnLike(long uid, long chatTimestamp, Boolean isLike, Boolean isUnLike) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C07981(uid, chatTimestamp, isLike, isUnLike, null), 2, null);
    }

    public final void setChatGptCallback(AiChatDepository.ChatGptCallback chatGptCallback) {
        Intrinsics.checkNotNullParameter(chatGptCallback, "chatGptCallback");
        AiChatDepository.INSTANCE.getGetInstance().setChatGptCallback(chatGptCallback);
    }

    public final void visionAiStart() {
        LargeDataHandler.getInstance().glassesControl(new byte[]{2, 1, 6, 2, 2}, new ILargeDataResponse() { // from class: com.glasssutdio.wear.ai.vm.AiChatViewModel$$ExternalSyntheticLambda2
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, BaseResponse baseResponse) {
                AiChatViewModel.visionAiStart$lambda$0(i, (GlassModelControlResponse) baseResponse);
            }
        });
    }

    public final void takePicture() {
        LargeDataHandler.getInstance().glassesControl(new byte[]{2, 1, 1}, new ILargeDataResponse() { // from class: com.glasssutdio.wear.ai.vm.AiChatViewModel$$ExternalSyntheticLambda3
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, BaseResponse baseResponse) {
                AiChatViewModel.takePicture$lambda$1(i, (GlassModelControlResponse) baseResponse);
            }
        });
    }

    public final void startVideo() {
        LargeDataHandler.getInstance().glassesControl(new byte[]{2, 1, (byte) 2}, new ILargeDataResponse() { // from class: com.glasssutdio.wear.ai.vm.AiChatViewModel$$ExternalSyntheticLambda1
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, BaseResponse baseResponse) {
                AiChatViewModel.startVideo$lambda$2(this.f$0, i, (GlassModelControlResponse) baseResponse);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startVideo$lambda$2(AiChatViewModel this$0, int i, final GlassModelControlResponse glassModelControlResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (glassModelControlResponse.getDataType() == 1) {
            if (glassModelControlResponse.getErrorCode() == 0) {
                XLog.m136i(Integer.valueOf(glassModelControlResponse.getWorkTypeIng()));
                ThreadExtKt.ktxRunOnUi(this$0, new Function1<AiChatViewModel, Unit>() { // from class: com.glasssutdio.wear.ai.vm.AiChatViewModel$startVideo$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AiChatViewModel aiChatViewModel) {
                        invoke2(aiChatViewModel);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AiChatViewModel ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        switch (glassModelControlResponse.getWorkTypeIng()) {
                            case 1:
                            case 6:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_40), 0, 1, null);
                                break;
                            case 2:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_36), 0, 1, null);
                                break;
                            case 4:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_45), 0, 1, null);
                                break;
                            case 5:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_38), 0, 1, null);
                                break;
                            case 7:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_37), 0, 1, null);
                                break;
                            case 8:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_39), 0, 1, null);
                                break;
                        }
                    }
                });
            } else {
                ThreadExtKt.ktxRunOnUi(this$0, new Function1<AiChatViewModel, Unit>() { // from class: com.glasssutdio.wear.ai.vm.AiChatViewModel$startVideo$1$2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AiChatViewModel aiChatViewModel) {
                        invoke2(aiChatViewModel);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AiChatViewModel ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.g_fire_28), 0, 1, null);
                    }
                });
            }
        }
    }

    public final void startRecord() {
        LargeDataHandler.getInstance().glassesControl(new byte[]{2, 1, (byte) 8}, new ILargeDataResponse() { // from class: com.glasssutdio.wear.ai.vm.AiChatViewModel$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, BaseResponse baseResponse) {
                AiChatViewModel.startRecord$lambda$3(this.f$0, i, (GlassModelControlResponse) baseResponse);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startRecord$lambda$3(AiChatViewModel this$0, int i, final GlassModelControlResponse glassModelControlResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (glassModelControlResponse.getDataType() == 1) {
            if (glassModelControlResponse.getErrorCode() == 0) {
                XLog.m136i(Integer.valueOf(glassModelControlResponse.getWorkTypeIng()));
                ThreadExtKt.ktxRunOnUi(this$0, new Function1<AiChatViewModel, Unit>() { // from class: com.glasssutdio.wear.ai.vm.AiChatViewModel$startRecord$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AiChatViewModel aiChatViewModel) {
                        invoke2(aiChatViewModel);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AiChatViewModel ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        switch (glassModelControlResponse.getWorkTypeIng()) {
                            case 1:
                            case 6:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_40), 0, 1, null);
                                break;
                            case 2:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_36), 0, 1, null);
                                break;
                            case 4:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_45), 0, 1, null);
                                break;
                            case 5:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_38), 0, 1, null);
                                break;
                            case 7:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_37), 0, 1, null);
                                break;
                            case 8:
                                GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.album_glass_39), 0, 1, null);
                                break;
                        }
                    }
                });
            } else {
                ThreadExtKt.ktxRunOnUi(this$0, new Function1<AiChatViewModel, Unit>() { // from class: com.glasssutdio.wear.ai.vm.AiChatViewModel$startRecord$1$2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AiChatViewModel aiChatViewModel) {
                        invoke2(aiChatViewModel);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AiChatViewModel ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        GlobalKt.showToast$default(GlobalKt.getString(C0775R.string.g_fire_30), 0, 1, null);
                    }
                });
            }
        }
    }

    /* compiled from: AiChatViewModel.kt */
    @Metadata(m606d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007¨\u0006\u0014"}, m607d2 = {"Lcom/glasssutdio/wear/ai/vm/AiChatViewModel$AiChatUI;", "", "refresh", "", "first", "(ZZ)V", "getFirst", "()Z", "setFirst", "(Z)V", "getRefresh", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final /* data */ class AiChatUI {
        private boolean first;
        private final boolean refresh;

        public static /* synthetic */ AiChatUI copy$default(AiChatUI aiChatUI, boolean z, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = aiChatUI.refresh;
            }
            if ((i & 2) != 0) {
                z2 = aiChatUI.first;
            }
            return aiChatUI.copy(z, z2);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getRefresh() {
            return this.refresh;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getFirst() {
            return this.first;
        }

        public final AiChatUI copy(boolean refresh, boolean first) {
            return new AiChatUI(refresh, first);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AiChatUI)) {
                return false;
            }
            AiChatUI aiChatUI = (AiChatUI) other;
            return this.refresh == aiChatUI.refresh && this.first == aiChatUI.first;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.refresh;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            boolean z2 = this.first;
            return i + (z2 ? 1 : z2 ? 1 : 0);
        }

        public String toString() {
            return "AiChatUI(refresh=" + this.refresh + ", first=" + this.first + ')';
        }

        public AiChatUI(boolean z, boolean z2) {
            this.refresh = z;
            this.first = z2;
        }

        public final boolean getRefresh() {
            return this.refresh;
        }

        public final boolean getFirst() {
            return this.first;
        }

        public final void setFirst(boolean z) {
            this.first = z;
        }
    }
}
