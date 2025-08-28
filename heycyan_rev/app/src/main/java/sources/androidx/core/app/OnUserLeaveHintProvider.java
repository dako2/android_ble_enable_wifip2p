package androidx.core.app;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;

/* compiled from: OnUserLeaveHintProvider.kt */
@Metadata(m606d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, m607d2 = {"Landroidx/core/app/OnUserLeaveHintProvider;", "", "addOnUserLeaveHintListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Ljava/lang/Runnable;", "removeOnUserLeaveHintListener", "core_release"}, m608k = 1, m609mv = {1, 8, 0}, m611xi = 48)
/* loaded from: classes.dex */
public interface OnUserLeaveHintProvider {
    void addOnUserLeaveHintListener(Runnable listener);

    void removeOnUserLeaveHintListener(Runnable listener);
}
