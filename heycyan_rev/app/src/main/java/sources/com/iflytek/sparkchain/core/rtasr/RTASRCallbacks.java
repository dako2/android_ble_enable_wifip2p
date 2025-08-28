package com.iflytek.sparkchain.core.rtasr;

import com.iflytek.sparkchain.core.rtasr.RTASR;

/* loaded from: classes2.dex */
public interface RTASRCallbacks {
    default void onBeginOfSpeech() {
        System.out.println("检测到说话开始");
    }

    default void onEndOfSpeech() {
        System.out.println("检测到说话结束");
    }

    void onError(RTASR.RtAsrError rtAsrError, Object obj);

    void onResult(RTASR.RtAsrResult rtAsrResult, Object obj);
}
