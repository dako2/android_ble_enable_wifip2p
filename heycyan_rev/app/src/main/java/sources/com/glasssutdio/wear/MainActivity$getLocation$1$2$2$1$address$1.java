package com.glasssutdio.wear;

import android.location.Address;
import android.location.Location;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MainActivity.kt */
@Metadata(m606d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, m607d2 = {"<anonymous>", "Landroid/location/Address;", "Lkotlinx/coroutines/CoroutineScope;"}, m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
@DebugMetadata(m619c = "com.glasssutdio.wear.MainActivity$getLocation$1$2$2$1$address$1", m620f = "MainActivity.kt", m621i = {}, m622l = {}, m623m = "invokeSuspend", m624n = {}, m625s = {})
/* loaded from: classes.dex */
final class MainActivity$getLocation$1$2$2$1$address$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Address>, Object> {
    final /* synthetic */ Location $location;
    final /* synthetic */ MainActivity $this_run;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainActivity$getLocation$1$2$2$1$address$1(MainActivity mainActivity, Location location, Continuation<? super MainActivity$getLocation$1$2$2$1$address$1> continuation) {
        super(2, continuation);
        this.$this_run = mainActivity;
        this.$location = location;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainActivity$getLocation$1$2$2$1$address$1(this.$this_run, this.$location, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Address> continuation) {
        return ((MainActivity$getLocation$1$2$2$1$address$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return this.$this_run.getCityName(this.$location.getLatitude(), this.$location.getLongitude());
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
