package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.Volatile;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: StateFlow.kt */
@Metadata(m606d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00042\b\u0012\u0004\u0012\u0002H\u00010\u00052\b\u0012\u0004\u0012\u0002H\u00010\u0006B\r\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u001f\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\u001d\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00028\u00002\u0006\u0010 \u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020\u0003H\u0014J\u001d\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030$2\u0006\u0010%\u001a\u00020\u0011H\u0014¢\u0006\u0002\u0010&J\u0019\u0010'\u001a\u00020(2\u0006\u0010\u0012\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010)J&\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000+2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u000200H\u0016J\b\u00101\u001a\u00020(H\u0016J\u0015\u00102\u001a\u00020\u001e2\u0006\u0010\u0012\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00103J\u001a\u00104\u001a\u00020\u001e2\b\u00105\u001a\u0004\u0018\u00010\b2\u0006\u00106\u001a\u00020\bH\u0002R\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000bX\u0082\u0004R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00028\u00008V@VX\u0096\u000e¢\u0006\u0012\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u00067"}, m607d2 = {"Lkotlinx/coroutines/flow/StateFlowImpl;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/internal/AbstractSharedFlow;", "Lkotlinx/coroutines/flow/StateFlowSlot;", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlinx/coroutines/flow/CancellableFlow;", "Lkotlinx/coroutines/flow/internal/FusibleFlow;", "initialState", "", "(Ljava/lang/Object;)V", "_state", "Lkotlinx/atomicfu/AtomicRef;", "replayCache", "", "getReplayCache", "()Ljava/util/List;", "sequence", "", "value", "getValue$annotations", "()V", "getValue", "()Ljava/lang/Object;", "setValue", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "compareAndSet", "", "expect", "update", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "createSlot", "createSlotArray", "", "size", "(I)[Lkotlinx/coroutines/flow/StateFlowSlot;", "emit", "", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fuse", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "resetReplayCache", "tryEmit", "(Ljava/lang/Object;)Z", "updateState", "expectedState", "newState", "kotlinx-coroutines-core"}, m608k = 1, m609mv = {1, 8, 0}, m611xi = 48)
/* loaded from: classes3.dex */
final class StateFlowImpl<T> extends AbstractSharedFlow<StateFlowSlot> implements MutableStateFlow<T>, CancellableFlow<T>, FusibleFlow<T> {
    private static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(StateFlowImpl.class, Object.class, "_state");

    @Volatile
    private volatile Object _state;
    private int sequence;

    /* compiled from: StateFlow.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.flow.StateFlowImpl", m620f = "StateFlow.kt", m621i = {0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, m622l = {384, 396, 401}, m623m = "collect", m624n = {"this", "collector", "slot", "this", "collector", "slot", "collectorJob", "newState", "this", "collector", "slot", "collectorJob", "oldState"}, m625s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"})
    /* renamed from: kotlinx.coroutines.flow.StateFlowImpl$collect$1 */
    static final class C28811 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ StateFlowImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C28811(StateFlowImpl<T> stateFlowImpl, Continuation<? super C28811> continuation) {
            super(continuation);
            this.this$0 = stateFlowImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.collect(null, this);
        }
    }

    public static /* synthetic */ void getValue$annotations() {
    }

    public StateFlowImpl(Object obj) {
        this._state = obj;
    }

    @Override // kotlinx.coroutines.flow.MutableStateFlow, kotlinx.coroutines.flow.StateFlow
    public T getValue() {
        Symbol symbol = NullSurrogateKt.NULL;
        T t = (T) _state$FU.get(this);
        if (t == symbol) {
            return null;
        }
        return t;
    }

    @Override // kotlinx.coroutines.flow.MutableStateFlow
    public void setValue(T t) {
        if (t == null) {
            t = (T) NullSurrogateKt.NULL;
        }
        updateState(null, t);
    }

    @Override // kotlinx.coroutines.flow.MutableStateFlow
    public boolean compareAndSet(T expect, T update) {
        if (expect == null) {
            expect = (T) NullSurrogateKt.NULL;
        }
        if (update == null) {
            update = (T) NullSurrogateKt.NULL;
        }
        return updateState(expect, update);
    }

    @Override // kotlinx.coroutines.flow.SharedFlow
    public List<T> getReplayCache() {
        return CollectionsKt.listOf(getValue());
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public boolean tryEmit(T value) {
        setValue(value);
        return true;
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow, kotlinx.coroutines.flow.FlowCollector
    public Object emit(T t, Continuation<? super Unit> continuation) {
        setValue(t);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public void resetReplayCache() {
        throw new UnsupportedOperationException("MutableStateFlow.resetReplayCache is not supported");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Path cross not found for [B:38:0x00b6, B:40:0x00bc], limit reached: 57 */
    /* JADX WARN: Path cross not found for [B:40:0x00bc, B:38:0x00b6], limit reached: 57 */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b1 A[Catch: all -> 0x0074, TryCatch #1 {all -> 0x0074, blocks: (B:14:0x003f, B:34:0x00a9, B:36:0x00b1, B:38:0x00b6, B:48:0x00d7, B:50:0x00dd, B:40:0x00bc, B:44:0x00c3, B:19:0x005d, B:22:0x0070, B:33:0x0098), top: B:59:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b6 A[Catch: all -> 0x0074, TryCatch #1 {all -> 0x0074, blocks: (B:14:0x003f, B:34:0x00a9, B:36:0x00b1, B:38:0x00b6, B:48:0x00d7, B:50:0x00dd, B:40:0x00bc, B:44:0x00c3, B:19:0x005d, B:22:0x0070, B:33:0x0098), top: B:59:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00dd A[Catch: all -> 0x0074, TRY_LEAVE, TryCatch #1 {all -> 0x0074, blocks: (B:14:0x003f, B:34:0x00a9, B:36:0x00b1, B:38:0x00b6, B:48:0x00d7, B:50:0x00dd, B:40:0x00bc, B:44:0x00c3, B:19:0x005d, B:22:0x0070, B:33:0x0098), top: B:59:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Type inference failed for: r12v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v8, types: [java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x00db -> B:34:0x00a9). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x00ed -> B:34:0x00a9). Please report as a decompilation issue!!! */
    @Override // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(FlowCollector<? super T> flowCollector, Continuation<?> continuation) throws Throwable {
        C28811 c28811;
        StateFlowImpl<T> stateFlowImpl;
        StateFlowSlot stateFlowSlot;
        FlowCollector flowCollector2;
        Job job;
        Object obj;
        StateFlowSlot stateFlowSlot2;
        boolean zTakePending;
        T t;
        if (continuation instanceof C28811) {
            c28811 = (C28811) continuation;
            if ((c28811.label & Integer.MIN_VALUE) != 0) {
                c28811.label -= Integer.MIN_VALUE;
            } else {
                c28811 = new C28811(this, continuation);
            }
        }
        Object obj2 = c28811.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c28811.label;
        ?? r6 = 1;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj2);
                StateFlowSlot stateFlowSlotAllocateSlot = allocateSlot();
                try {
                    if (flowCollector instanceof SubscribedFlowCollector) {
                        c28811.L$0 = this;
                        c28811.L$1 = flowCollector;
                        c28811.L$2 = stateFlowSlotAllocateSlot;
                        c28811.label = 1;
                        if (((SubscribedFlowCollector) flowCollector).onSubscription(c28811) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    stateFlowImpl = this;
                    stateFlowSlot = stateFlowSlotAllocateSlot;
                } catch (Throwable th) {
                    th = th;
                    stateFlowImpl = this;
                    r6 = stateFlowSlotAllocateSlot;
                    stateFlowImpl.freeSlot((AbstractSharedFlowSlot) r6);
                    throw th;
                }
            } else if (i == 1) {
                StateFlowSlot stateFlowSlot3 = (StateFlowSlot) c28811.L$2;
                flowCollector = (FlowCollector) c28811.L$1;
                stateFlowImpl = (StateFlowImpl) c28811.L$0;
                ResultKt.throwOnFailure(obj2);
                stateFlowSlot = stateFlowSlot3;
            } else if (i == 2) {
                obj = c28811.L$4;
                job = (Job) c28811.L$3;
                StateFlowSlot stateFlowSlot4 = (StateFlowSlot) c28811.L$2;
                flowCollector2 = (FlowCollector) c28811.L$1;
                stateFlowImpl = (StateFlowImpl) c28811.L$0;
                ResultKt.throwOnFailure(obj2);
                stateFlowSlot2 = stateFlowSlot4;
                zTakePending = stateFlowSlot2.takePending();
                r6 = stateFlowSlot2;
                if (!zTakePending) {
                }
                ?? r12 = _state$FU.get(stateFlowImpl);
                if (job != null) {
                }
                if (obj != null) {
                }
                if (r12 != NullSurrogateKt.NULL) {
                }
                c28811.L$0 = stateFlowImpl;
                c28811.L$1 = flowCollector2;
                c28811.L$2 = r6;
                c28811.L$3 = job;
                c28811.L$4 = r12;
                c28811.label = 2;
                if (flowCollector2.emit(t, c28811) != coroutine_suspended) {
                }
            } else {
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                obj = c28811.L$4;
                job = (Job) c28811.L$3;
                StateFlowSlot stateFlowSlot5 = (StateFlowSlot) c28811.L$2;
                flowCollector2 = (FlowCollector) c28811.L$1;
                stateFlowImpl = (StateFlowImpl) c28811.L$0;
                ResultKt.throwOnFailure(obj2);
                r6 = stateFlowSlot5;
                ?? r122 = _state$FU.get(stateFlowImpl);
                if (job != null) {
                    JobKt.ensureActive(job);
                }
                if (obj != null) {
                    stateFlowSlot2 = r6;
                    if (!Intrinsics.areEqual(obj, (Object) r122)) {
                    }
                    zTakePending = stateFlowSlot2.takePending();
                    r6 = stateFlowSlot2;
                    if (!zTakePending) {
                        c28811.L$0 = stateFlowImpl;
                        c28811.L$1 = flowCollector2;
                        c28811.L$2 = stateFlowSlot2;
                        c28811.L$3 = job;
                        c28811.L$4 = obj;
                        c28811.label = 3;
                        Object objAwaitPending = stateFlowSlot2.awaitPending(c28811);
                        r6 = stateFlowSlot2;
                        if (objAwaitPending == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    ?? r1222 = _state$FU.get(stateFlowImpl);
                    if (job != null) {
                    }
                    if (obj != null) {
                    }
                }
                t = r1222 != NullSurrogateKt.NULL ? null : r1222;
                c28811.L$0 = stateFlowImpl;
                c28811.L$1 = flowCollector2;
                c28811.L$2 = r6;
                c28811.L$3 = job;
                c28811.L$4 = r1222;
                c28811.label = 2;
                if (flowCollector2.emit(t, c28811) != coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = r1222;
                stateFlowSlot2 = r6;
                zTakePending = stateFlowSlot2.takePending();
                r6 = stateFlowSlot2;
                if (!zTakePending) {
                }
                ?? r12222 = _state$FU.get(stateFlowImpl);
                if (job != null) {
                }
                if (obj != null) {
                }
                if (r12222 != NullSurrogateKt.NULL) {
                }
                c28811.L$0 = stateFlowImpl;
                c28811.L$1 = flowCollector2;
                c28811.L$2 = r6;
                c28811.L$3 = job;
                c28811.L$4 = r12222;
                c28811.label = 2;
                if (flowCollector2.emit(t, c28811) != coroutine_suspended) {
                }
            }
            flowCollector2 = flowCollector;
            job = (Job) c28811.get$context().get(Job.INSTANCE);
            obj = null;
            r6 = stateFlowSlot;
            ?? r122222 = _state$FU.get(stateFlowImpl);
            if (job != null) {
            }
            if (obj != null) {
            }
            if (r122222 != NullSurrogateKt.NULL) {
            }
            c28811.L$0 = stateFlowImpl;
            c28811.L$1 = flowCollector2;
            c28811.L$2 = r6;
            c28811.L$3 = job;
            c28811.L$4 = r122222;
            c28811.label = 2;
            if (flowCollector2.emit(t, c28811) != coroutine_suspended) {
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public StateFlowSlot createSlot() {
        return new StateFlowSlot();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public StateFlowSlot[] createSlotArray(int size) {
        return new StateFlowSlot[size];
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow<T> fuse(CoroutineContext context, int capacity, BufferOverflow onBufferOverflow) {
        return StateFlowKt.fuseStateFlow(this, context, capacity, onBufferOverflow);
    }

    private final boolean updateState(Object expectedState, Object newState) {
        int i;
        StateFlowSlot[] slots;
        synchronized (this) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (expectedState != null && !Intrinsics.areEqual(obj, expectedState)) {
                return false;
            }
            if (Intrinsics.areEqual(obj, newState)) {
                return true;
            }
            atomicReferenceFieldUpdater.set(this, newState);
            int i2 = this.sequence;
            if ((i2 & 1) == 0) {
                int i3 = i2 + 1;
                this.sequence = i3;
                StateFlowSlot[] slots2 = getSlots();
                Unit unit = Unit.INSTANCE;
                while (true) {
                    StateFlowSlot[] stateFlowSlotArr = slots2;
                    if (stateFlowSlotArr != null) {
                        for (StateFlowSlot stateFlowSlot : stateFlowSlotArr) {
                            if (stateFlowSlot != null) {
                                stateFlowSlot.makePending();
                            }
                        }
                    }
                    synchronized (this) {
                        i = this.sequence;
                        if (i == i3) {
                            this.sequence = i3 + 1;
                            return true;
                        }
                        slots = getSlots();
                        Unit unit2 = Unit.INSTANCE;
                    }
                    slots2 = slots;
                    i3 = i;
                }
            } else {
                this.sequence = i2 + 2;
                return true;
            }
        }
    }
}
