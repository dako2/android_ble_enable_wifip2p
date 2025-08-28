package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: BufferedChannel.kt */
@Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
@DebugMetadata(m619c = "kotlinx.coroutines.channels.BufferedChannel", m620f = "BufferedChannel.kt", m621i = {0, 0, 0, 0}, m622l = {3056}, m623m = "receiveCatchingOnNoWaiterSuspend-GKJJFZk", m624n = {"this", "segment", "index", "r"}, m625s = {"L$0", "L$1", "I$0", "J$0"})
/* loaded from: classes3.dex */
final class BufferedChannel$receiveCatchingOnNoWaiterSuspend$1 extends ContinuationImpl {
    int I$0;
    long J$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BufferedChannel<E> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BufferedChannel$receiveCatchingOnNoWaiterSuspend$1(BufferedChannel<E> bufferedChannel, Continuation<? super BufferedChannel$receiveCatchingOnNoWaiterSuspend$1> continuation) {
        super(continuation);
        this.this$0 = bufferedChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM2408receiveCatchingOnNoWaiterSuspendGKJJFZk = this.this$0.m2408receiveCatchingOnNoWaiterSuspendGKJJFZk(null, 0, 0L, this);
        return objM2408receiveCatchingOnNoWaiterSuspendGKJJFZk == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM2408receiveCatchingOnNoWaiterSuspendGKJJFZk : ChannelResult.m2415boximpl(objM2408receiveCatchingOnNoWaiterSuspendGKJJFZk);
    }
}
