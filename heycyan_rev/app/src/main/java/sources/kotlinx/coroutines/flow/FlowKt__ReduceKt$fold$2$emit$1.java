package kotlinx.coroutines.flow;

import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.FlowKt__ReduceKt;

/* compiled from: Reduce.kt */
@Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = EMachine.EM_ECOG16)
@DebugMetadata(m619c = "kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$2", m620f = "Reduce.kt", m621i = {}, m622l = {45}, m623m = "emit", m624n = {}, m625s = {})
/* loaded from: classes3.dex */
public final class FlowKt__ReduceKt$fold$2$emit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowKt__ReduceKt.C28352<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__ReduceKt$fold$2$emit$1(FlowKt__ReduceKt.C28352<? super T> c28352, Continuation<? super FlowKt__ReduceKt$fold$2$emit$1> continuation) {
        super(continuation);
        this.this$0 = c28352;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}
