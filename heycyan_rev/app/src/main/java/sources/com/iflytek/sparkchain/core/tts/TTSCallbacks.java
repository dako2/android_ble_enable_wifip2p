package com.iflytek.sparkchain.core.tts;

import android.util.Log;
import com.iflytek.sparkchain.core.tts.TTS;

/* loaded from: classes2.dex */
public interface TTSCallbacks {
    void onError(TTS.TTSError tTSError, Object obj);

    default void onEvent(TTS.TTSEvent tTSEvent, Object obj) {
        Log.v("TTSCallbacks", "default   onEvent:" + tTSEvent.getPhoneme() + "," + tTSEvent.getType());
    }

    void onResult(TTS.TTSResult tTSResult, Object obj);
}
