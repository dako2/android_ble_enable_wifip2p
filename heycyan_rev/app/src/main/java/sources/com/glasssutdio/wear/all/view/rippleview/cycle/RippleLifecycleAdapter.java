package com.glasssutdio.wear.all.view.rippleview.cycle;

import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RippleLifecycleAdapter.kt */
@Metadata(m606d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\u0006H\u0007J\b\u0010\b\u001a\u00020\u0006H\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/all/view/rippleview/cycle/RippleLifecycleAdapter;", "Landroidx/lifecycle/LifecycleObserver;", "lifecycle", "Lcom/glasssutdio/wear/all/view/rippleview/cycle/BaseLifecycle;", "(Lcom/glasssutdio/wear/all/view/rippleview/cycle/BaseLifecycle;)V", "onLifecycleDestroy", "", "onLifecyclePause", "onLifecycleResume", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class RippleLifecycleAdapter implements LifecycleObserver {
    private final BaseLifecycle lifecycle;

    public RippleLifecycleAdapter(BaseLifecycle lifecycle) {
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        this.lifecycle = lifecycle;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public final void onLifecycleResume() {
        Log.i(String.valueOf(getClass().getSimpleName()), "onLifecycleResume");
        this.lifecycle.onResume();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public final void onLifecyclePause() {
        Log.i(String.valueOf(getClass().getSimpleName()), "onLifecyclePause");
        this.lifecycle.onPause();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public final void onLifecycleDestroy() {
        Log.i(String.valueOf(getClass().getSimpleName()), "onLifecycleDestroy");
        this.lifecycle.onDestroy();
    }
}
