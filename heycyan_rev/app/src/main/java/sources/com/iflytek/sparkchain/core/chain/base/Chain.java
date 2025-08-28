package com.iflytek.sparkchain.core.chain.base;

import com.iflytek.sparkchain.core.chain.base.ChainCallbacks;

/* loaded from: classes2.dex */
public interface Chain<T extends ChainCallbacks> {
    void registerCallback(T t);
}
