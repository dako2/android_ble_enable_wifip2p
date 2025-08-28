package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.FlowKt__CountKt;

/* compiled from: Count.kt */
@Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
@DebugMetadata(m619c = "kotlinx.coroutines.flow.FlowKt__CountKt$count$4", m620f = "Count.kt", m621i = {0}, m622l = {30}, m623m = "emit", m624n = {"this"}, m625s = {"L$0"})
/* loaded from: classes3.dex */
final class FlowKt__CountKt$count$4$emit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowKt__CountKt.C27964<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    FlowKt__CountKt$count$4$emit$1(FlowKt__CountKt.C27964<? super T> c27964, Continuation<? super FlowKt__CountKt$count$4$emit$1> continuation) {
        super(continuation);
        this.this$0 = c27964;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}
