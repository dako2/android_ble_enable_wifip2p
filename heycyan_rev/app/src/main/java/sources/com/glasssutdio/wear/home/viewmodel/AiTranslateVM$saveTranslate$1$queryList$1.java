package com.glasssutdio.wear.home.viewmodel;

import com.glasssutdio.wear.database.entity.TranslateEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AiTranslateVM.kt */
@Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, m607d2 = {"<anonymous>", "", "Lcom/glasssutdio/wear/database/entity/TranslateEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
@DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.AiTranslateVM$saveTranslate$1$queryList$1", m620f = "AiTranslateVM.kt", m621i = {}, m622l = {121}, m623m = "invokeSuspend", m624n = {}, m625s = {})
/* loaded from: classes.dex */
final class AiTranslateVM$saveTranslate$1$queryList$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends TranslateEntity>>, Object> {
    final /* synthetic */ TranslateEntity $entity;
    int label;
    final /* synthetic */ AiTranslateVM this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AiTranslateVM$saveTranslate$1$queryList$1(AiTranslateVM aiTranslateVM, TranslateEntity translateEntity, Continuation<? super AiTranslateVM$saveTranslate$1$queryList$1> continuation) {
        super(2, continuation);
        this.this$0 = aiTranslateVM;
        this.$entity = translateEntity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AiTranslateVM$saveTranslate$1$queryList$1(this.this$0, this.$entity, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends TranslateEntity>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<TranslateEntity>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<TranslateEntity>> continuation) {
        return ((AiTranslateVM$saveTranslate$1$queryList$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.this$0.translateDepository.queryByUidCreateTime(this.$entity.getUid(), this.$entity.getOriginType(), this.$entity.getCreateTime(), this);
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
