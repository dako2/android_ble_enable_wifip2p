package kotlinx.coroutines;

import kotlin.Metadata;

/* compiled from: MultithreadedDispatchers.common.kt */
@Metadata(m606d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0014\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, m607d2 = {"newSingleThreadContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Lkotlinx/coroutines/CloseableCoroutineDispatcher;", "name", "", "kotlinx-coroutines-core"}, m608k = 5, m609mv = {1, 8, 0}, m611xi = 48, m612xs = "kotlinx/coroutines/ThreadPoolDispatcherKt")
/* loaded from: classes3.dex */
final /* synthetic */ class ThreadPoolDispatcherKt__MultithreadedDispatchers_commonKt {
    public static final ExecutorCoroutineDispatcher newSingleThreadContext(String str) {
        return ThreadPoolDispatcherKt.newFixedThreadPoolContext(1, str);
    }
}
