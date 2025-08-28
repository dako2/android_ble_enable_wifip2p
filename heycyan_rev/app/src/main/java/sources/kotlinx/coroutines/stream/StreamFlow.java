package kotlinx.coroutines.stream;

import androidx.exifinterface.media.ExifInterface;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.Volatile;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: Stream.kt */
@Metadata(m606d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u001f\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\fR\t\u0010\u0006\u001a\u00020\u0007X\u0082\u0004R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, m607d2 = {"Lkotlinx/coroutines/stream/StreamFlow;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/Flow;", "stream", "Ljava/util/stream/Stream;", "(Ljava/util/stream/Stream;)V", "consumed", "Lkotlinx/atomicfu/AtomicBoolean;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, m608k = 1, m609mv = {1, 8, 0}, m611xi = 48)
/* loaded from: classes3.dex */
final class StreamFlow<T> implements Flow<T> {
    private static final AtomicIntegerFieldUpdater consumed$FU = AtomicIntegerFieldUpdater.newUpdater(StreamFlow.class, "consumed");

    @Volatile
    private volatile int consumed = 0;
    private final Stream<T> stream;

    /* compiled from: Stream.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.stream.StreamFlow", m620f = "Stream.kt", m621i = {0, 0}, m622l = {26}, m623m = "collect", m624n = {"this", "collector"}, m625s = {"L$0", "L$1"})
    /* renamed from: kotlinx.coroutines.stream.StreamFlow$collect$1 */
    static final class C29051 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ StreamFlow<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C29051(StreamFlow<T> streamFlow, Continuation<? super C29051> continuation) {
            super(continuation);
            this.this$0 = streamFlow;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.collect(null, this);
        }
    }

    public StreamFlow(Stream<T> stream) {
        this.stream = stream;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) throws Throwable {
        C29051 c29051;
        StreamFlow<T> streamFlow;
        FlowCollector flowCollector2;
        Iterator<T> it;
        if (continuation instanceof C29051) {
            c29051 = (C29051) continuation;
            if ((c29051.label & Integer.MIN_VALUE) != 0) {
                c29051.label -= Integer.MIN_VALUE;
            } else {
                c29051 = new C29051(this, continuation);
            }
        }
        Object obj = c29051.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c29051.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (!consumed$FU.compareAndSet(this, 0, 1)) {
                throw new IllegalStateException("Stream.consumeAsFlow can be collected only once".toString());
            }
            try {
                streamFlow = this;
                flowCollector2 = flowCollector;
                it = this.stream.iterator();
            } catch (Throwable th) {
                th = th;
                streamFlow = this;
                streamFlow.stream.close();
                throw th;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (Iterator) c29051.L$2;
            FlowCollector flowCollector3 = (FlowCollector) c29051.L$1;
            streamFlow = (StreamFlow) c29051.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                flowCollector2 = flowCollector3;
            } catch (Throwable th2) {
                th = th2;
                streamFlow.stream.close();
                throw th;
            }
        }
        while (it.hasNext()) {
            T next = it.next();
            c29051.L$0 = streamFlow;
            c29051.L$1 = flowCollector2;
            c29051.L$2 = it;
            c29051.label = 1;
            if (flowCollector2.emit(next, c29051) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        streamFlow.stream.close();
        return Unit.INSTANCE;
    }
}
