package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.internal.AbortFlowException;
import kotlinx.coroutines.flow.internal.FlowExceptions_commonKt;

/* compiled from: Limit.kt */
@Metadata(m606d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aV\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000323\b\u0004\u0010\u0004\u001a-\b\u0001\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0005H\u0080Hø\u0001\u0000¢\u0006\u0002\u0010\f\u001a$\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f\u001aH\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0005ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a+\u0010\u0012\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00132\u0006\u0010\b\u001a\u0002H\u0002H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a$\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f\u001aH\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0005ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001ap\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00190\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0019*\b\u0012\u0004\u0012\u0002H\u00020\u00032D\b\u0001\u0010\u001a\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00190\u0013\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u001b¢\u0006\u0002\b\u001cø\u0001\u0000¢\u0006\u0002\u0010\u001d\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, m607d2 = {"collectWhile", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/Flow;", "predicate", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "value", "Lkotlin/coroutines/Continuation;", "", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "drop", "count", "", "dropWhile", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "emitAbort", "Lkotlinx/coroutines/flow/FlowCollector;", "emitAbort$FlowKt__LimitKt", "(Lkotlinx/coroutines/flow/FlowCollector;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "take", "takeWhile", "transformWhile", "R", "transform", "Lkotlin/Function3;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "kotlinx-coroutines-core"}, m608k = 5, m609mv = {1, 8, 0}, m611xi = 48, m612xs = "kotlinx/coroutines/flow/FlowKt")
/* loaded from: classes3.dex */
final /* synthetic */ class FlowKt__LimitKt {

    /* compiled from: Limit.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = EMachine.EM_ECOG16)
    @DebugMetadata(m619c = "kotlinx.coroutines.flow.FlowKt__LimitKt", m620f = "Limit.kt", m621i = {0}, m622l = {EMachine.EM_DSP24}, m623m = "collectWhile", m624n = {"collector"}, m625s = {"L$0"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__LimitKt$collectWhile$1 */
    static final class C28181<T> extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C28181(Continuation<? super C28181> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__LimitKt.collectWhile(null, null, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object emitAbort$FlowKt__LimitKt(FlowCollector<? super T> flowCollector, T t, Continuation<? super Unit> continuation) {
        FlowKt__LimitKt$emitAbort$1 flowKt__LimitKt$emitAbort$1;
        if (continuation instanceof FlowKt__LimitKt$emitAbort$1) {
            flowKt__LimitKt$emitAbort$1 = (FlowKt__LimitKt$emitAbort$1) continuation;
            if ((flowKt__LimitKt$emitAbort$1.label & Integer.MIN_VALUE) != 0) {
                flowKt__LimitKt$emitAbort$1.label -= Integer.MIN_VALUE;
            } else {
                flowKt__LimitKt$emitAbort$1 = new FlowKt__LimitKt$emitAbort$1(continuation);
            }
        }
        Object obj = flowKt__LimitKt$emitAbort$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = flowKt__LimitKt$emitAbort$1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            flowKt__LimitKt$emitAbort$1.L$0 = flowCollector;
            flowKt__LimitKt$emitAbort$1.label = 1;
            if (flowCollector.emit(t, flowKt__LimitKt$emitAbort$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            flowCollector = (FlowCollector) flowKt__LimitKt$emitAbort$1.L$0;
            ResultKt.throwOnFailure(obj);
        }
        throw new AbortFlowException(flowCollector);
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* compiled from: Limit.kt */
    @Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@"}, m607d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.flow.FlowKt__LimitKt$transformWhile$1", m620f = "Limit.kt", m621i = {0}, m622l = {152}, m623m = "invokeSuspend", m624n = {"collector$iv"}, m625s = {"L$0"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__LimitKt$transformWhile$1 */
    static final class C28191<R> extends SuspendLambda implements Function2<FlowCollector<? super R>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Flow<T> $this_transformWhile;
        final /* synthetic */ Function3<FlowCollector<? super R>, T, Continuation<? super Boolean>, Object> $transform;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C28191(Flow<? extends T> flow, Function3<? super FlowCollector<? super R>, ? super T, ? super Continuation<? super Boolean>, ? extends Object> function3, Continuation<? super C28191> continuation) {
            super(2, continuation);
            this.$this_transformWhile = flow;
            this.$transform = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C28191 c28191 = new C28191(this.$this_transformWhile, this.$transform, continuation);
            c28191.L$0 = obj;
            return c28191;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super R> flowCollector, Continuation<? super Unit> continuation) {
            return ((C28191) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            C2817xdf1aa1b6 c2817xdf1aa1b6;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                Flow<T> flow = this.$this_transformWhile;
                C2817xdf1aa1b6 c2817xdf1aa1b62 = new C2817xdf1aa1b6(this.$transform, flowCollector);
                try {
                    this.L$0 = c2817xdf1aa1b62;
                    this.label = 1;
                    if (flow.collect(c2817xdf1aa1b62, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } catch (AbortFlowException e) {
                    e = e;
                    c2817xdf1aa1b6 = c2817xdf1aa1b62;
                    FlowExceptions_commonKt.checkOwnership(e, c2817xdf1aa1b6);
                    return Unit.INSTANCE;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c2817xdf1aa1b6 = (C2817xdf1aa1b6) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (AbortFlowException e2) {
                    e = e2;
                    FlowExceptions_commonKt.checkOwnership(e, c2817xdf1aa1b6);
                    return Unit.INSTANCE;
                }
            }
            return Unit.INSTANCE;
        }
    }

    public static final <T, R> Flow<R> transformWhile(Flow<? extends T> flow, Function3<? super FlowCollector<? super R>, ? super T, ? super Continuation<? super Boolean>, ? extends Object> function3) {
        return FlowKt.flow(new C28191(flow, function3, null));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object collectWhile(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        C28181 c28181;
        FlowCollector<? super Object> flowCollector;
        if (continuation instanceof C28181) {
            c28181 = (C28181) continuation;
            if ((c28181.label & Integer.MIN_VALUE) != 0) {
                c28181.label -= Integer.MIN_VALUE;
            } else {
                c28181 = new C28181(continuation);
            }
        }
        Object obj = c28181.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c28181.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector<? super Object> flowKt__LimitKt$collectWhile$collector$1 = new FlowKt__LimitKt$collectWhile$collector$1<>(function2);
            try {
                c28181.L$0 = flowKt__LimitKt$collectWhile$collector$1;
                c28181.label = 1;
                if (flow.collect(flowKt__LimitKt$collectWhile$collector$1, c28181) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } catch (AbortFlowException e) {
                e = e;
                flowCollector = flowKt__LimitKt$collectWhile$collector$1;
                FlowExceptions_commonKt.checkOwnership(e, flowCollector);
                return Unit.INSTANCE;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            flowCollector = (FlowKt__LimitKt$collectWhile$collector$1) c28181.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (AbortFlowException e2) {
                e = e2;
                FlowExceptions_commonKt.checkOwnership(e, flowCollector);
                return Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }

    private static final <T> Object collectWhile$$forInline(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        FlowKt__LimitKt$collectWhile$collector$1 flowKt__LimitKt$collectWhile$collector$1 = new FlowKt__LimitKt$collectWhile$collector$1(function2);
        try {
            InlineMarker.mark(0);
            flow.collect(flowKt__LimitKt$collectWhile$collector$1, continuation);
            InlineMarker.mark(1);
        } catch (AbortFlowException e) {
            FlowExceptions_commonKt.checkOwnership(e, flowKt__LimitKt$collectWhile$collector$1);
        }
        return Unit.INSTANCE;
    }

    public static final <T> Flow<T> drop(final Flow<? extends T> flow, final int i) {
        if (i < 0) {
            throw new IllegalArgumentException(("Drop count should be non-negative, but had " + i).toString());
        }
        return new Flow<T>() { // from class: kotlinx.coroutines.flow.FlowKt__LimitKt$drop$$inlined$unsafeFlow$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
                Object objCollect = flow.collect(new FlowKt__LimitKt$drop$2$1(new Ref.IntRef(), i, flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
    }

    public static final <T> Flow<T> dropWhile(final Flow<? extends T> flow, final Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return new Flow<T>() { // from class: kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
                Object objCollect = flow.collect(new FlowKt__LimitKt$dropWhile$1$1(new Ref.BooleanRef(), flowCollector, function2), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
    }

    public static final <T> Flow<T> take(Flow<? extends T> flow, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException(("Requested element count " + i + " should be positive").toString());
        }
        return new FlowKt__LimitKt$take$$inlined$unsafeFlow$1(flow, i);
    }

    public static final <T> Flow<T> takeWhile(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return new FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1(flow, function2);
    }
}
