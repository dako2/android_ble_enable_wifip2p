package androidx.lifecycle;

import androidx.arch.core.executor.ArchTaskExecutor;
import kotlin.Metadata;

/* compiled from: LifecycleRegistry.android.kt */
@Metadata(m606d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0001¨\u0006\u0002"}, m607d2 = {"isMainThread", "", "lifecycle-runtime_release"}, m608k = 2, m609mv = {1, 8, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class LifecycleRegistry_androidKt {
    public static final boolean isMainThread() {
        return ArchTaskExecutor.getInstance().isMainThread();
    }
}
