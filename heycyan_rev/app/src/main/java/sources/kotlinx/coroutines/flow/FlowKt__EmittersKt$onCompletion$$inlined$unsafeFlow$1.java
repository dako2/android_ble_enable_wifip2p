package kotlinx.coroutines.flow;

import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.flow.internal.SafeCollector;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: SafeCollector.common.kt */
@Metadata(m606d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, m607d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, m608k = 1, m609mv = {1, 8, 0}, m611xi = 48)
/* loaded from: classes3.dex */
public final class FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1<T> implements Flow<T> {
    final /* synthetic */ Function3 $action$inlined;
    final /* synthetic */ Flow $this_onCompletion$inlined;

    /* compiled from: SafeCollector.common.kt */
    @Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
    @DebugMetadata(m619c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1", m620f = "Emitters.kt", m621i = {0, 0, 1, 2}, m622l = {EMachine.EM_XGATE, 122, 129}, m623m = "collect", m624n = {"this", "$this$onCompletion_u24lambda_u242", "e", "sc"}, m625s = {"L$0", "L$1", "L$0", "L$0"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1 */
    public static final class C28031 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C28031(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.this.collect(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x008b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00b2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) throws Throwable {
        C28031 c28031;
        FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1<T> flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
        ThrowingCollector throwingCollector;
        Function3 function3;
        SafeCollector safeCollector;
        Throwable th;
        SafeCollector safeCollector2;
        Object objInvoke;
        if (continuation instanceof C28031) {
            c28031 = (C28031) continuation;
            if ((c28031.label & Integer.MIN_VALUE) != 0) {
                c28031.label -= Integer.MIN_VALUE;
            } else {
                c28031 = new C28031(continuation);
            }
        }
        Object obj = c28031.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c28031.label;
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    Throwable th2 = (Throwable) c28031.L$0;
                    ResultKt.throwOnFailure(obj);
                    throw th2;
                }
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                safeCollector2 = (SafeCollector) c28031.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    safeCollector2.releaseIntercepted();
                    return Unit.INSTANCE;
                } catch (Throwable th3) {
                    th = th3;
                    safeCollector2.releaseIntercepted();
                    throw th;
                }
            }
            flowCollector = (FlowCollector) c28031.L$1;
            flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 = (FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1) c28031.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                safeCollector = new SafeCollector(flowCollector, c28031.getContext());
                try {
                    Function3 function32 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.$action$inlined;
                    c28031.L$0 = safeCollector;
                    c28031.L$1 = null;
                    c28031.label = 3;
                    InlineMarker.mark(6);
                    objInvoke = function32.invoke(safeCollector, null, c28031);
                    InlineMarker.mark(7);
                    if (objInvoke != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    safeCollector2 = safeCollector;
                    safeCollector2.releaseIntercepted();
                    return Unit.INSTANCE;
                } catch (Throwable th4) {
                    th = th4;
                    safeCollector2 = safeCollector;
                    safeCollector2.releaseIntercepted();
                    throw th;
                }
            } catch (Throwable th5) {
                th = th5;
                throwingCollector = new ThrowingCollector(th);
                function3 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.$action$inlined;
                c28031.L$0 = th;
                c28031.L$1 = null;
                c28031.label = 2;
                if (FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(throwingCollector, function3, th, c28031) != coroutine_suspended) {
                    return coroutine_suspended;
                }
                throw th;
            }
        }
        ResultKt.throwOnFailure(obj);
        try {
            Flow flow = this.$this_onCompletion$inlined;
            c28031.L$0 = this;
            c28031.L$1 = flowCollector;
            c28031.label = 1;
            if (flow.collect(flowCollector, c28031) == coroutine_suspended) {
                return coroutine_suspended;
            }
            flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 = this;
            safeCollector = new SafeCollector(flowCollector, c28031.getContext());
            Function3 function322 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.$action$inlined;
            c28031.L$0 = safeCollector;
            c28031.L$1 = null;
            c28031.label = 3;
            InlineMarker.mark(6);
            objInvoke = function322.invoke(safeCollector, null, c28031);
            InlineMarker.mark(7);
            if (objInvoke != coroutine_suspended) {
            }
        } catch (Throwable th6) {
            th = th6;
            flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 = this;
            throwingCollector = new ThrowingCollector(th);
            function3 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.$action$inlined;
            c28031.L$0 = th;
            c28031.L$1 = null;
            c28031.label = 2;
            if (FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(throwingCollector, function3, th, c28031) != coroutine_suspended) {
            }
        }
    }

    public FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1(Flow flow, Function3 function3) {
        this.$this_onCompletion$inlined = flow;
        this.$action$inlined = function3;
    }
}
