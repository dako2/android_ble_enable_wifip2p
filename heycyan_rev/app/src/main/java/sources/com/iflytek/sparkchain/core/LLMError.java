package com.iflytek.sparkchain.core;

/* loaded from: classes2.dex */
public interface LLMError extends LLMBaseOutput {
    int getErrCode();

    String getErrMsg();
}
