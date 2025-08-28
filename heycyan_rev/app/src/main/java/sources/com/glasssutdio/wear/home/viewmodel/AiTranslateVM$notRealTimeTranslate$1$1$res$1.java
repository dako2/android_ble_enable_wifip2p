package com.glasssutdio.wear.home.viewmodel;

import com.glasssutdio.wear.p003ai.bean.TranslateBean;
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
@Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "Lcom/glasssutdio/wear/ai/bean/TranslateBean;", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
@DebugMetadata(m619c = "com.glasssutdio.wear.home.viewmodel.AiTranslateVM$notRealTimeTranslate$1$1$res$1", m620f = "AiTranslateVM.kt", m621i = {}, m622l = {571}, m623m = "invokeSuspend", m624n = {}, m625s = {})
/* loaded from: classes.dex */
final class AiTranslateVM$notRealTimeTranslate$1$1$res$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super TranslateBean>, Object> {
    final /* synthetic */ String $asrContent;
    final /* synthetic */ String $from;
    final /* synthetic */ String $to;
    int label;
    final /* synthetic */ AiTranslateVM this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AiTranslateVM$notRealTimeTranslate$1$1$res$1(AiTranslateVM aiTranslateVM, String str, String str2, String str3, Continuation<? super AiTranslateVM$notRealTimeTranslate$1$1$res$1> continuation) {
        super(2, continuation);
        this.this$0 = aiTranslateVM;
        this.$from = str;
        this.$to = str2;
        this.$asrContent = str3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AiTranslateVM$notRealTimeTranslate$1$1$res$1(this.this$0, this.$from, this.$to, this.$asrContent, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super TranslateBean> continuation) {
        return ((AiTranslateVM$notRealTimeTranslate$1$1$res$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.this$0.translateWithCoroutine(this.$from, this.$to, String.valueOf(this.$asrContent), this);
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
