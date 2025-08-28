package com.iflytek.sparkchain.core.chain.p013qa;

import com.iflytek.sparkchain.core.chain.base.ChainEvent;

/* loaded from: classes2.dex */
public interface KnowBotEvent extends ChainEvent {
    int getEventID();

    String getEventMsg();

    Object getUserContext();
}
