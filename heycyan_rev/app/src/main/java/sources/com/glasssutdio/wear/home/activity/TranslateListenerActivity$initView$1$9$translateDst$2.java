package com.glasssutdio.wear.home.activity;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: TranslateListenerActivity.kt */
@Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
@DebugMetadata(m619c = "com.glasssutdio.wear.home.activity.TranslateListenerActivity$initView$1$9$translateDst$2", m620f = "TranslateListenerActivity.kt", m621i = {}, m622l = {TypedValues.AttributesType.TYPE_PIVOT_TARGET}, m623m = "invokeSuspend", m624n = {}, m625s = {})
/* loaded from: classes.dex */
final class TranslateListenerActivity$initView$1$9$translateDst$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ TranslateListenerActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TranslateListenerActivity$initView$1$9$translateDst$2(TranslateListenerActivity translateListenerActivity, Continuation<? super TranslateListenerActivity$initView$1$9$translateDst$2> continuation) {
        super(2, continuation);
        this.this$0 = translateListenerActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TranslateListenerActivity$initView$1$9$translateDst$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TranslateListenerActivity$initView$1$9$translateDst$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws InterruptedException {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (DelayKt.delay(5000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        this.this$0.flushSentence();
        return Unit.INSTANCE;
    }
}
