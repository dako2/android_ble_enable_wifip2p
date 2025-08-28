package com.iflytek.sparkchain.core.chain.p013qa;

import com.iflytek.sparkchain.core.chain.base.ChainCallbacks;

/* loaded from: classes2.dex */
public interface KnowBotCallbacks extends ChainCallbacks<KnowBotResult, KnowBotEvent, KnowBotError> {
    @Override // com.iflytek.sparkchain.core.chain.base.ChainCallbacks
    void onError(KnowBotError knowBotError, Object obj);

    @Override // com.iflytek.sparkchain.core.chain.base.ChainCallbacks
    void onEvent(KnowBotEvent knowBotEvent, Object obj);

    @Override // com.iflytek.sparkchain.core.chain.base.ChainCallbacks
    void onOutput(KnowBotResult knowBotResult, Object obj);
}
