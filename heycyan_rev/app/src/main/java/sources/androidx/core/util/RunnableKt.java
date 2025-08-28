package androidx.core.util;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: Runnable.kt */
@Metadata(m606d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002¨\u0006\u0004"}, m607d2 = {"asRunnable", "Ljava/lang/Runnable;", "Lkotlin/coroutines/Continuation;", "", "core-ktx_release"}, m608k = 2, m609mv = {1, 8, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class RunnableKt {
    public static final Runnable asRunnable(Continuation<? super Unit> continuation) {
        return new ContinuationRunnable(continuation);
    }
}
