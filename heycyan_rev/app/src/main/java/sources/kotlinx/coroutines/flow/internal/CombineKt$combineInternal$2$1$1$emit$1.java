package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.internal.CombineKt;

/* compiled from: Combine.kt */
@Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
@DebugMetadata(m619c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1$1", m620f = "Combine.kt", m621i = {}, m622l = {32, 33}, m623m = "emit", m624n = {}, m625s = {})
/* loaded from: classes3.dex */
final class CombineKt$combineInternal$2$1$1$emit$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CombineKt.C28882.AnonymousClass1.C30241<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    CombineKt$combineInternal$2$1$1$emit$1(CombineKt.C28882.AnonymousClass1.C30241<? super T> c30241, Continuation<? super CombineKt$combineInternal$2$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = c30241;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}
