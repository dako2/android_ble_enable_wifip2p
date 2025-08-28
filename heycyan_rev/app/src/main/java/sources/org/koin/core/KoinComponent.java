package org.koin.core;

import kotlin.Metadata;
import org.koin.core.context.GlobalContext;

/* compiled from: KoinComponent.kt */
@Metadata(m605bv = {1, 0, 3}, m606d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m607d2 = {"Lorg/koin/core/KoinComponent;", "", "getKoin", "Lorg/koin/core/Koin;", "koin-core"}, m608k = 1, m609mv = {1, 1, 15})
/* loaded from: classes3.dex */
public interface KoinComponent {
    Koin getKoin();

    /* compiled from: KoinComponent.kt */
    @Metadata(m605bv = {1, 0, 3}, m608k = 3, m609mv = {1, 1, 15})
    public static final class DefaultImpls {
        public static Koin getKoin(KoinComponent koinComponent) {
            return GlobalContext.get().getKoin();
        }
    }
}
