package com.glasssutdio.wear.p003ai.p004vm;

import com.glasssutdio.wear.database.entity.AiChatEntity;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AiChatViewModel.kt */
@Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "Lcom/glasssutdio/wear/database/entity/AiChatEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
@DebugMetadata(m619c = "com.glasssutdio.wear.ai.vm.AiChatViewModel$updateAiChatModelLikeOrOnLike$1$aiChatModel$1", m620f = "AiChatViewModel.kt", m621i = {}, m622l = {EMachine.EM_CRX}, m623m = "invokeSuspend", m624n = {}, m625s = {})
/* loaded from: classes.dex */
final class AiChatViewModel$updateAiChatModelLikeOrOnLike$1$aiChatModel$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AiChatEntity>, Object> {
    final /* synthetic */ long $chatTimestamp;
    final /* synthetic */ long $uid;
    int label;
    final /* synthetic */ AiChatViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AiChatViewModel$updateAiChatModelLikeOrOnLike$1$aiChatModel$1(AiChatViewModel aiChatViewModel, long j, long j2, Continuation<? super AiChatViewModel$updateAiChatModelLikeOrOnLike$1$aiChatModel$1> continuation) {
        super(2, continuation);
        this.this$0 = aiChatViewModel;
        this.$uid = j;
        this.$chatTimestamp = j2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AiChatViewModel$updateAiChatModelLikeOrOnLike$1$aiChatModel$1(this.this$0, this.$uid, this.$chatTimestamp, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AiChatEntity> continuation) {
        return ((AiChatViewModel$updateAiChatModelLikeOrOnLike$1$aiChatModel$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.this$0.aiChatDepository.queryByUidAndTimestamp(this.$uid, this.$chatTimestamp, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
