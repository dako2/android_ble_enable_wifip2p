package kotlinx.coroutines.flow;

import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: SafeCollector.common.kt */
@Metadata(m606d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, m607d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, m608k = 1, m609mv = {1, 8, 0}, m611xi = 48)
/* loaded from: classes3.dex */
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8 implements Flow<Long> {
    final /* synthetic */ long[] $this_asFlow$inlined;

    /* compiled from: SafeCollector.common.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8", m620f = "Builders.kt", m621i = {0, 0}, m622l = {EMachine.EM_C166}, m623m = "collect", m624n = {"$this$asFlow_u24lambda_u2415", "$this$forEach$iv"}, m625s = {"L$0", "L$1"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$1 */
    public static final class C27851 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C27851(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8.this.collect(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0064 -> B:19:0x0067). Please report as a decompilation issue!!! */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(FlowCollector<? super Long> flowCollector, Continuation<? super Unit> continuation) {
        C27851 c27851;
        FlowCollector flowCollector2;
        int i;
        int i2;
        long[] jArr;
        if (continuation instanceof C27851) {
            c27851 = (C27851) continuation;
            if ((c27851.label & Integer.MIN_VALUE) != 0) {
                c27851.label -= Integer.MIN_VALUE;
            } else {
                c27851 = new C27851(continuation);
            }
        }
        Object obj = c27851.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c27851.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            long[] jArr2 = this.$this_asFlow$inlined;
            int length = jArr2.length;
            flowCollector2 = flowCollector;
            i = length;
            i2 = 0;
            jArr = jArr2;
            if (i2 < i) {
            }
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = c27851.I$1;
            i2 = c27851.I$0;
            jArr = (long[]) c27851.L$1;
            FlowCollector flowCollector3 = (FlowCollector) c27851.L$0;
            ResultKt.throwOnFailure(obj);
            flowCollector2 = flowCollector3;
            i2++;
            if (i2 < i) {
                Long lBoxLong = Boxing.boxLong(jArr[i2]);
                c27851.L$0 = flowCollector2;
                c27851.L$1 = jArr;
                c27851.I$0 = i2;
                c27851.I$1 = i;
                c27851.label = 1;
                if (flowCollector2.emit(lBoxLong, c27851) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                i2++;
                if (i2 < i) {
                    return Unit.INSTANCE;
                }
            }
        }
    }

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8(long[] jArr) {
        this.$this_asFlow$inlined = jArr;
    }
}
