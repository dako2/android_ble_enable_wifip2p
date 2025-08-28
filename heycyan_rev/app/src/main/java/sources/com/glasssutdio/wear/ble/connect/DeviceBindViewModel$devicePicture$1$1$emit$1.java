package com.glasssutdio.wear.ble.connect;

import com.glasssutdio.wear.ble.connect.DeviceBindViewModel;
import com.glasssutdio.wear.depository.bean.DevicePictureBean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: DeviceBindViewModel.kt */
@Metadata(m608k = 3, m609mv = {1, 9, 0}, m611xi = 48)
@DebugMetadata(m619c = "com.glasssutdio.wear.ble.connect.DeviceBindViewModel$devicePicture$1$1", m620f = "DeviceBindViewModel.kt", m621i = {0}, m622l = {50, 51}, m623m = "emit", m624n = {"this"}, m625s = {"L$0"})
/* loaded from: classes.dex */
final class DeviceBindViewModel$devicePicture$1$1$emit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DeviceBindViewModel.C08561.AnonymousClass1<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    DeviceBindViewModel$devicePicture$1$1$emit$1(DeviceBindViewModel.C08561.AnonymousClass1<? super T> anonymousClass1, Continuation<? super DeviceBindViewModel$devicePicture$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = anonymousClass1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit((DevicePictureBean) null, (Continuation<? super Unit>) this);
    }
}
