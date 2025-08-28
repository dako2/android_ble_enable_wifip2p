package com.jieli.jl_audio_decode.tool;

import com.jieli.jl_audio_decode.callback.OnDecodeStreamCallback;
import com.jieli.jl_audio_decode.callback.OnStateCallback;
import com.jieli.jl_audio_decode.opus.model.OpusOption;

/* loaded from: classes2.dex */
public abstract class BaseManager {
    public abstract void decodeFile(String str, String str2, OnStateCallback onStateCallback);

    public abstract void encodeFile(String str, String str2, OnStateCallback onStateCallback);

    public abstract boolean isDecodeStream();

    protected abstract boolean nativeAddAudioStream(int i, byte[] bArr);

    protected abstract int nativeDecodeFile(String str, String str2);

    protected abstract int nativeDecodeFile(String str, String str2, OpusOption opusOption);

    protected abstract void nativeDecodeStream(int i);

    protected abstract int nativeEncodeFile(String str, String str2);

    protected abstract int nativeGetAudioStreamState();

    public abstract void startDecodeStream(OnDecodeStreamCallback onDecodeStreamCallback);

    public abstract void stopDecodeStream();

    public abstract void writeAudioStream(byte[] bArr);
}
