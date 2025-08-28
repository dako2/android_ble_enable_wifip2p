package com.iflytek.sparkchain.media.record;

import com.iflytek.sparkchain.media.speech.SpeechError;

/* renamed from: com.iflytek.sparkchain.media.record.b */
/* loaded from: classes2.dex */
public interface InterfaceC2219b {
    void onBuffer(byte[] bArr, int i, int i2);

    void onDecibel(int i);

    void onError(SpeechError speechError);

    void onRelease();

    void onStart(boolean z);
}
