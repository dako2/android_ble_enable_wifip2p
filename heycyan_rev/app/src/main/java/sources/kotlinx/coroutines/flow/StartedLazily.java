package kotlinx.coroutines.flow;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* compiled from: SharingStarted.kt */
@Metadata(m606d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, m607d2 = {"Lkotlinx/coroutines/flow/StartedLazily;", "Lkotlinx/coroutines/flow/SharingStarted;", "()V", "command", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/SharingCommand;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", "", "toString", "", "kotlinx-coroutines-core"}, m608k = 1, m609mv = {1, 8, 0}, m611xi = 48)
/* loaded from: classes3.dex */
final class StartedLazily implements SharingStarted {

    /* compiled from: SharingStarted.kt */
    @Metadata(m606d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, m607d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlinx/coroutines/flow/SharingCommand;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.flow.StartedLazily$command$1", m620f = "SharingStarted.kt", m621i = {}, m622l = {155}, m623m = "invokeSuspend", m624n = {}, m625s = {})
    /* renamed from: kotlinx.coroutines.flow.StartedLazily$command$1 */
    static final class C28781 extends SuspendLambda implements Function2<FlowCollector<? super SharingCommand>, Continuation<? super Unit>, Object> {
        final /* synthetic */ StateFlow<Integer> $subscriptionCount;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C28781(StateFlow<Integer> stateFlow, Continuation<? super C28781> continuation) {
            super(2, continuation);
            this.$subscriptionCount = stateFlow;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C28781 c28781 = new C28781(this.$subscriptionCount, continuation);
            c28781.L$0 = obj;
            return c28781;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super SharingCommand> flowCollector, Continuation<? super Unit> continuation) {
            return ((C28781) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                this.label = 1;
                if (this.$subscriptionCount.collect(new AnonymousClass1(booleanRef, flowCollector), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }

        /* compiled from: SharingStarted.kt */
        @Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m607d2 = {"<anonymous>", "", "count", "", "emit", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
        /* renamed from: kotlinx.coroutines.flow.StartedLazily$command$1$1, reason: invalid class name */
        static final class AnonymousClass1<T> implements FlowCollector {
            final /* synthetic */ FlowCollector<SharingCommand> $$this$flow;
            final /* synthetic */ Ref.BooleanRef $started;

            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(Ref.BooleanRef booleanRef, FlowCollector<? super SharingCommand> flowCollector) {
                this.$started = booleanRef;
                this.$$this$flow = flowCollector;
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object emit(int i, Continuation<? super Unit> continuation) {
                StartedLazily$command$1$1$emit$1 startedLazily$command$1$1$emit$1;
                if (continuation instanceof StartedLazily$command$1$1$emit$1) {
                    startedLazily$command$1$1$emit$1 = (StartedLazily$command$1$1$emit$1) continuation;
                    if ((startedLazily$command$1$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                        startedLazily$command$1$1$emit$1.label -= Integer.MIN_VALUE;
                    } else {
                        startedLazily$command$1$1$emit$1 = new StartedLazily$command$1$1$emit$1(this, continuation);
                    }
                }
                Object obj = startedLazily$command$1$1$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i2 = startedLazily$command$1$1$emit$1.label;
                if (i2 == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (i <= 0 || this.$started.element) {
                        return Unit.INSTANCE;
                    }
                    this.$started.element = true;
                    FlowCollector<SharingCommand> flowCollector = this.$$this$flow;
                    SharingCommand sharingCommand = SharingCommand.START;
                    startedLazily$command$1$1$emit$1.label = 1;
                    if (flowCollector.emit(sharingCommand, startedLazily$command$1$1$emit$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                return emit(((Number) obj).intValue(), (Continuation<? super Unit>) continuation);
            }
        }
    }

    @Override // kotlinx.coroutines.flow.SharingStarted
    public Flow<SharingCommand> command(StateFlow<Integer> subscriptionCount) {
        return FlowKt.flow(new C28781(subscriptionCount, null));
    }

    public String toString() {
        return "SharingStarted.Lazily";
    }
}
