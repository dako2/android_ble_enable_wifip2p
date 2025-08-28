package com.iflytek.sparkchain.core.asr;

import com.iflytek.sparkchain.core.asr.ASR;

/* loaded from: classes2.dex */
public interface AsrCallbacks {
    default void onBeginOfSpeech() {
        System.out.println("检测到说话开始");
    }

    default void onEndOfSpeech() {
        System.out.println("检测到说话结束");
    }

    void onError(ASR.ASRError aSRError, Object obj);

    default void onRecordVolume(double d, int i) {
        System.out.println("检测到说话分贝");
    }

    void onResult(ASR.ASRResult aSRResult, Object obj);
}
