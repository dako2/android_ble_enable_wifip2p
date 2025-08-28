package com.iflytek.sparkchain.core.chain.p013qa;

import com.iflytek.sparkchain.core.chain.base.ChainError;

/* loaded from: classes2.dex */
public interface KnowBotError extends ChainError {
    int getErrCode();

    String getErrMsg();

    Object getUserContext();
}
