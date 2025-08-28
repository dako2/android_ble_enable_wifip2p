package org.koin.core.error;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InstanceCreationException.kt */
@Metadata(m605bv = {1, 0, 3}, m606d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u00060\u0001j\u0002`\u0002¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, m607d2 = {"Lorg/koin/core/error/InstanceCreationException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", NotificationCompat.CATEGORY_MESSAGE, "", "parent", "(Ljava/lang/String;Ljava/lang/Exception;)V", "koin-core"}, m608k = 1, m609mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final class InstanceCreationException extends Exception {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InstanceCreationException(String msg, Exception parent) {
        super(msg, parent);
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Intrinsics.checkParameterIsNotNull(parent, "parent");
    }
}
