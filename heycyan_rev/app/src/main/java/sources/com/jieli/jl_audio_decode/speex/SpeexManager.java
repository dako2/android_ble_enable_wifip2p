package com.jieli.jl_audio_decode.speex;

import com.jieli.jl_audio_decode.callback.OnDecodeStreamCallback;
import com.jieli.jl_audio_decode.callback.OnStateCallback;
import com.jieli.jl_audio_decode.callback.OnThreadFinishListener;
import com.jieli.jl_audio_decode.constant.DecodeConstant;
import com.jieli.jl_audio_decode.constant.ErrorCode;
import com.jieli.jl_audio_decode.exceptions.SpeexException;
import com.jieli.jl_audio_decode.opus.model.OpusOption;
import com.jieli.jl_audio_decode.tool.AddStreamDataThread;
import com.jieli.jl_audio_decode.tool.BaseManager;
import com.jieli.jl_audio_decode.tool.DecodeFileThread;
import com.jieli.jl_audio_decode.tool.EncodeFileThread;

/* loaded from: classes2.dex */
public class SpeexManager extends BaseManager {
    private static final String TAG = "SpeexManager";
    private AddStreamDataThread mAddStreamDataThread;
    private volatile OnStateCallback mDecodeFileCallback;
    private DecodeFileThread mDecodeFileThread;
    private volatile OnDecodeStreamCallback mDecodeStreamCallback;
    private volatile OnStateCallback mEncodeFileCallback;
    private EncodeFileThread mEncodeFileThread;
    private volatile long managerAddr = initNativeID();

    private native int decodeAudioFile(String str, String str2, long j);

    private native void decodeAudioStream(int i, long j);

    private native int encodeAudioFile(String str, String str2, long j);

    private native int getAudioStreamState(long j);

    private native long initNativeID();

    private native int nativeDestroy(long j);

    private native boolean saveAudioStream(byte[] bArr, long j);

    static {
        try {
            System.loadLibrary(DecodeConstant.JL_SPEEX_LIB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SpeexManager() throws SpeexException {
        if (this.managerAddr == 0) {
            throw new SpeexException("Can not load jl_speex lib");
        }
    }

    public void release() {
        if (isDecodeStream()) {
            stopDecodeStream();
        }
        stopEncodeFileThread();
        stopDecodeFileThread();
        stopAddDataThread();
        setDecodeFileCallback(null);
        setEncodeFileCallback(null);
        setDecodeStreamCallback(null);
        if (nativeDestroy(this.managerAddr) == 0) {
            this.managerAddr = 0L;
        }
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    public boolean isDecodeStream() {
        return getAudioStreamState(this.managerAddr) == 1;
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    public void encodeFile(String str, String str2, OnStateCallback onStateCallback) {
        if (this.mEncodeFileThread == null) {
            setEncodeFileCallback(onStateCallback);
            startEncodeFileThread(str, str2);
        } else if (onStateCallback != null) {
            onStateCallback.onError(ErrorCode.ERR_IN_PROCESS, "In process, please wait.");
        }
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    public void decodeFile(String str, String str2, OnStateCallback onStateCallback) {
        if (this.mDecodeFileThread == null) {
            setDecodeFileCallback(onStateCallback);
            startDecodeFileThread(str, str2);
        } else if (onStateCallback != null) {
            onStateCallback.onError(ErrorCode.ERR_IN_PROCESS, "In process, please wait.");
        }
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    public void startDecodeStream(OnDecodeStreamCallback onDecodeStreamCallback) {
        if (!isDecodeStream()) {
            setDecodeStreamCallback(onDecodeStreamCallback);
            nativeDecodeStream(1);
        } else if (onDecodeStreamCallback != null) {
            onDecodeStreamCallback.onError(ErrorCode.ERR_IN_PROCESS, "Decoding Stream is working, please don't open again.");
        }
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    public void stopDecodeStream() {
        if (isDecodeStream()) {
            nativeDecodeStream(0);
        }
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    public void writeAudioStream(byte[] bArr) throws InterruptedException {
        if (isDecodeStream()) {
            addStreamData(bArr);
        }
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    protected int nativeEncodeFile(String str, String str2) {
        if (this.managerAddr == 0) {
            return -1000;
        }
        return encodeAudioFile(str, str2, this.managerAddr);
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    protected int nativeDecodeFile(String str, String str2) {
        if (this.managerAddr == 0) {
            return -1000;
        }
        return decodeAudioFile(str, str2, this.managerAddr);
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    protected int nativeDecodeFile(String str, String str2, OpusOption opusOption) {
        return nativeDecodeFile(str, str2);
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    protected void nativeDecodeStream(int i) {
        decodeAudioStream(i, this.managerAddr);
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    protected boolean nativeAddAudioStream(int i, byte[] bArr) {
        if (this.managerAddr == 0) {
            return false;
        }
        return saveAudioStream(bArr, this.managerAddr);
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    protected int nativeGetAudioStreamState() {
        return getAudioStreamState(this.managerAddr);
    }

    private void startDecodeFileThread(String str, String str2) {
        if (this.mDecodeFileThread == null) {
            DecodeFileThread decodeFileThread = new DecodeFileThread(this, str, str2, null, this.mDecodeFileCallback, new OnThreadFinishListener() { // from class: com.jieli.jl_audio_decode.speex.SpeexManager$$ExternalSyntheticLambda2
                @Override // com.jieli.jl_audio_decode.callback.OnThreadFinishListener
                public final void onThreadFinish(long j) {
                    this.f$0.m580x669b7a72(j);
                }
            });
            this.mDecodeFileThread = decodeFileThread;
            decodeFileThread.start();
        }
    }

    /* renamed from: lambda$startDecodeFileThread$0$com-jieli-jl_audio_decode-speex-SpeexManager */
    /* synthetic */ void m580x669b7a72(long j) {
        DecodeFileThread decodeFileThread = this.mDecodeFileThread;
        if (decodeFileThread == null || decodeFileThread.getId() != j) {
            return;
        }
        this.mDecodeFileCallback = null;
        setDecodeFileCallback(null);
    }

    private void stopDecodeFileThread() {
        DecodeFileThread decodeFileThread = this.mDecodeFileThread;
        if (decodeFileThread != null) {
            decodeFileThread.interrupt();
            this.mDecodeFileThread = null;
        }
    }

    private void startEncodeFileThread(String str, String str2) {
        if (this.mEncodeFileThread == null) {
            EncodeFileThread encodeFileThread = new EncodeFileThread(this, str, str2, this.mEncodeFileCallback, new OnThreadFinishListener() { // from class: com.jieli.jl_audio_decode.speex.SpeexManager$$ExternalSyntheticLambda0
                @Override // com.jieli.jl_audio_decode.callback.OnThreadFinishListener
                public final void onThreadFinish(long j) {
                    this.f$0.m581x49d478e9(j);
                }
            });
            this.mEncodeFileThread = encodeFileThread;
            encodeFileThread.start();
        }
    }

    /* renamed from: lambda$startEncodeFileThread$1$com-jieli-jl_audio_decode-speex-SpeexManager */
    /* synthetic */ void m581x49d478e9(long j) {
        EncodeFileThread encodeFileThread = this.mEncodeFileThread;
        if (encodeFileThread == null || encodeFileThread.getId() != j) {
            return;
        }
        this.mEncodeFileThread = null;
        setEncodeFileCallback(null);
    }

    private void stopEncodeFileThread() {
        EncodeFileThread encodeFileThread = this.mEncodeFileThread;
        if (encodeFileThread != null) {
            encodeFileThread.interrupt();
            this.mEncodeFileThread = null;
        }
    }

    private void addStreamData(byte[] bArr) throws InterruptedException {
        if (bArr != null) {
            if (this.mAddStreamDataThread == null) {
                AddStreamDataThread addStreamDataThread = new AddStreamDataThread(this, 1, this.mDecodeStreamCallback, new OnThreadFinishListener() { // from class: com.jieli.jl_audio_decode.speex.SpeexManager$$ExternalSyntheticLambda1
                    @Override // com.jieli.jl_audio_decode.callback.OnThreadFinishListener
                    public final void onThreadFinish(long j) {
                        this.f$0.m579x5e219afb(j);
                    }
                });
                this.mAddStreamDataThread = addStreamDataThread;
                addStreamDataThread.start();
            }
            this.mAddStreamDataThread.addStreamData(bArr);
        }
    }

    /* renamed from: lambda$addStreamData$2$com-jieli-jl_audio_decode-speex-SpeexManager */
    /* synthetic */ void m579x5e219afb(long j) {
        AddStreamDataThread addStreamDataThread = this.mAddStreamDataThread;
        if (addStreamDataThread == null || addStreamDataThread.getId() != j) {
            return;
        }
        this.mAddStreamDataThread = null;
        setDecodeStreamCallback(null);
    }

    private void stopAddDataThread() {
        AddStreamDataThread addStreamDataThread = this.mAddStreamDataThread;
        if (addStreamDataThread != null) {
            addStreamDataThread.stopThread();
            this.mAddStreamDataThread = null;
        }
    }

    protected void onStateCallback(int i, int i2, int i3, String str) {
        if (i == 1) {
            if (this.mDecodeFileCallback != null) {
                if (i2 == 0) {
                    this.mDecodeFileCallback.onComplete(str);
                    return;
                } else if (i2 == 1) {
                    this.mDecodeFileCallback.onStart();
                    return;
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    this.mDecodeFileCallback.onError(i3, str);
                    return;
                }
            }
            return;
        }
        if (i == 2) {
            if (this.mEncodeFileCallback != null) {
                if (i2 == 0) {
                    this.mEncodeFileCallback.onComplete(str);
                    return;
                } else if (i2 == 1) {
                    this.mEncodeFileCallback.onStart();
                    return;
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    this.mEncodeFileCallback.onError(i3, str);
                    return;
                }
            }
            return;
        }
        if (i == 3 && this.mDecodeStreamCallback != null) {
            if (i2 == 0) {
                this.mDecodeStreamCallback.onComplete(str);
            } else if (i2 == 1) {
                this.mDecodeStreamCallback.onStart();
            } else {
                if (i2 != 2) {
                    return;
                }
                this.mDecodeStreamCallback.onError(i3, str);
            }
        }
    }

    protected void onDecodeStreamReceive(int i, byte[] bArr) {
        if (this.mDecodeStreamCallback != null) {
            this.mDecodeStreamCallback.onDecodeStream(bArr);
        }
    }

    public void setDecodeFileCallback(OnStateCallback onStateCallback) {
        this.mDecodeFileCallback = onStateCallback;
    }

    public void setEncodeFileCallback(OnStateCallback onStateCallback) {
        this.mEncodeFileCallback = onStateCallback;
    }

    public void setDecodeStreamCallback(OnDecodeStreamCallback onDecodeStreamCallback) {
        this.mDecodeStreamCallback = onDecodeStreamCallback;
    }
}
