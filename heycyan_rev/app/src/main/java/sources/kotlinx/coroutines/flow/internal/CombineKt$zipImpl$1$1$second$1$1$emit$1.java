package kotlinx.coroutines.flow.internal;

import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$second$1;

/* compiled from: Combine.kt */
@Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
@DebugMetadata(m619c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$second$1$1", m620f = "Combine.kt", m621i = {}, m622l = {EMachine.EM_MN10200}, m623m = "emit", m624n = {}, m625s = {})
/* loaded from: classes3.dex */
final class CombineKt$zipImpl$1$1$second$1$1$emit$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CombineKt$zipImpl$1$1$second$1.C28911<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    CombineKt$zipImpl$1$1$second$1$1$emit$1(CombineKt$zipImpl$1$1$second$1.C28911<? super T> c28911, Continuation<? super CombineKt$zipImpl$1$1$second$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = c28911;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}
