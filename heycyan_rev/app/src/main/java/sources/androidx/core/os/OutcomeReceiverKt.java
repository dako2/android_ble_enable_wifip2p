package androidx.core.os;

import android.os.OutcomeReceiver;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* compiled from: OutcomeReceiver.kt */
@Metadata(m606d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\u001a.\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0007¨\u0006\u0006"}, m607d2 = {"asOutcomeReceiver", "Landroid/os/OutcomeReceiver;", "R", ExifInterface.LONGITUDE_EAST, "", "Lkotlin/coroutines/Continuation;", "core-ktx_release"}, m608k = 2, m609mv = {1, 8, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class OutcomeReceiverKt {
    public static final <R, E extends Throwable> OutcomeReceiver<R, E> asOutcomeReceiver(Continuation<? super R> continuation) {
        return new ContinuationOutcomeReceiver(continuation);
    }
}
