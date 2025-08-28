package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: BufferedChannel.kt */
@Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
@DebugMetadata(m619c = "kotlinx.coroutines.channels.BufferedChannel", m620f = "BufferedChannel.kt", m621i = {}, m622l = {739}, m623m = "receiveCatching-JP2dKIU$suspendImpl", m624n = {}, m625s = {})
/* loaded from: classes3.dex */
final class BufferedChannel$receiveCatching$1<E> extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BufferedChannel<E> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BufferedChannel$receiveCatching$1(BufferedChannel<E> bufferedChannel, Continuation<? super BufferedChannel$receiveCatching$1> continuation) {
        super(continuation);
        this.this$0 = bufferedChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM2407receiveCatchingJP2dKIU$suspendImpl = BufferedChannel.m2407receiveCatchingJP2dKIU$suspendImpl(this.this$0, this);
        return objM2407receiveCatchingJP2dKIU$suspendImpl == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM2407receiveCatchingJP2dKIU$suspendImpl : ChannelResult.m2415boximpl(objM2407receiveCatchingJP2dKIU$suspendImpl);
    }
}
