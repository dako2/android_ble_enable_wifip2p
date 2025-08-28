package com.glasssutdio.wear.all.view.rippleview.cycle;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.glasssutdio.wear.all.view.rippleview.RippleView;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RippleLifecycle.kt */
@Metadata(m606d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00030\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, m607d2 = {"Lcom/glasssutdio/wear/all/view/rippleview/cycle/RippleLifecycle;", "Lcom/glasssutdio/wear/all/view/rippleview/cycle/BaseLifecycle;", "view", "Lcom/glasssutdio/wear/all/view/rippleview/RippleView;", "(Lcom/glasssutdio/wear/all/view/rippleview/RippleView;)V", TypedValues.Custom.S_REFERENCE, "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "onDestroy", "", "onPause", "onResume", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class RippleLifecycle implements BaseLifecycle {
    private final WeakReference<RippleView> reference;

    public RippleLifecycle(RippleView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.reference = new WeakReference<>(view);
    }

    @Override // com.glasssutdio.wear.all.view.rippleview.cycle.BaseLifecycle
    public void onResume() {
        RippleView rippleView = this.reference.get();
        if (rippleView != null) {
            rippleView.onResume();
        }
    }

    @Override // com.glasssutdio.wear.all.view.rippleview.cycle.BaseLifecycle
    public void onPause() {
        RippleView rippleView = this.reference.get();
        if (rippleView != null) {
            rippleView.onPause();
        }
    }

    @Override // com.glasssutdio.wear.all.view.rippleview.cycle.BaseLifecycle
    public void onDestroy() {
        RippleView rippleView = this.reference.get();
        if (rippleView != null) {
            rippleView.onStop();
        }
    }
}
