package com.jieli.jl_audio_decode.opus;

import com.jieli.jl_audio_decode.callback.OnDecodeStreamCallback;
import com.jieli.jl_audio_decode.callback.OnEncodeStreamCallback;
import com.jieli.jl_audio_decode.callback.OnStateCallback;
import com.jieli.jl_audio_decode.callback.OnThreadFinishListener;
import com.jieli.jl_audio_decode.constant.DecodeConstant;
import com.jieli.jl_audio_decode.constant.ErrorCode;
import com.jieli.jl_audio_decode.exceptions.OpusException;
import com.jieli.jl_audio_decode.opus.model.OpusOption;
import com.jieli.jl_audio_decode.tool.AddStreamDataThread;
import com.jieli.jl_audio_decode.tool.BaseManager;
import com.jieli.jl_audio_decode.tool.DecodeFileThread;
import com.jieli.jl_audio_decode.tool.EncodeFileThread;

/* loaded from: classes2.dex */
public class OpusManager extends BaseManager {
    private static final String TAG = "OpusManager";
    private AddStreamDataThread mAddStreamDataThread;
    private DecodeFileThread mDecodeFileThread;
    private volatile OnStateCallback mDecodeStateCb;
    private volatile OnDecodeStreamCallback mDecodeStreamCb;
    private EncodeFileThread mEncodeFileThread;
    private volatile OnStateCallback mEncodeStateCb;
    private volatile OnEncodeStreamCallback mEncodeStreamCb;
    private AddStreamDataThread mEncodeStreamDataThread;
    private volatile long managerAddr = initNativeID();

    private native int decodeAudioFile(String str, String str2, long j, OpusOption opusOption);

    private native void decodeAudioStream(int i, long j, OpusOption opusOption);

    private native int encodeOpusFile(String str, String str2, long j);

    private native void encodeOpusStream(int i, long j);

    private native int getAudioStreamState(long j);

    private native int getEncodeStreamState(long j);

    private native long initNativeID();

    private native int nativeDestroy(long j);

    private native boolean saveAudioStream(byte[] bArr, long j);

    private native boolean savePcmStream(byte[] bArr, long j);

    static {
        try {
            System.loadLibrary(DecodeConstant.JL_OPUS_LIB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OpusManager() throws OpusException {
        if (this.managerAddr == 0) {
            throw new OpusException("Can not load jl_opus lib");
        }
    }

    public void release() {
        if (isDecodeStream()) {
            stopDecodeStream();
        }
        if (isEncodeStream()) {
            stopEncodeStream();
        }
        stopDecodeFileThread();
        stopEncodeFileThread();
        stopAddDataThread();
        stopAddEncodeDataThread();
        setDecodeStateCb(null);
        setDecodeStateCb(null);
        setDecodeStateCb(null);
        setEncodeStreamCb(null);
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
            setEncodeStateCb(onStateCallback);
            startEncodeFileThread(str, str2);
        } else if (onStateCallback != null) {
            onStateCallback.onError(ErrorCode.ERR_IN_PROCESS, "In process, please wait.");
        }
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    public void decodeFile(String str, String str2, OnStateCallback onStateCallback) {
        decodeFile(str, str2, new OpusOption(), onStateCallback);
    }

    public void decodeFile(String str, String str2, OpusOption opusOption, OnStateCallback onStateCallback) {
        if (this.mDecodeFileThread == null) {
            setDecodeStateCb(onStateCallback);
            startDecodeFileThread(str, str2, opusOption);
        } else if (onStateCallback != null) {
            onStateCallback.onError(ErrorCode.ERR_IN_PROCESS, "In process, please wait.");
        }
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    public void startDecodeStream(OnDecodeStreamCallback onDecodeStreamCallback) {
        startDecodeStream(new OpusOption(), onDecodeStreamCallback);
    }

    public void startDecodeStream(OpusOption opusOption, OnDecodeStreamCallback onDecodeStreamCallback) {
        if (!isDecodeStream()) {
            setDecodeStreamCb(onDecodeStreamCallback);
            nativeDecodeStream(1, opusOption);
        } else if (onDecodeStreamCallback != null) {
            onDecodeStreamCallback.onError(ErrorCode.ERR_IN_PROCESS, "Decoding Stream is working, please don't open again.");
        }
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    public void stopDecodeStream() {
        if (isDecodeStream()) {
            nativeDecodeStream(0, new OpusOption());
        }
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    public void writeAudioStream(byte[] bArr) throws InterruptedException {
        if (isDecodeStream()) {
            addStreamData(bArr);
        }
    }

    public boolean isEncodeStream() {
        return getEncodeStreamState(this.managerAddr) == 1;
    }

    public void startEncodeStream(OnEncodeStreamCallback onEncodeStreamCallback) {
        if (!isEncodeStream()) {
            setEncodeStreamCb(onEncodeStreamCallback);
            encodeOpusStream(1, this.managerAddr);
        } else if (onEncodeStreamCallback != null) {
            onEncodeStreamCallback.onError(ErrorCode.ERR_IN_PROCESS, "Encoding Stream is working, please don't open again.");
        }
    }

    public void stopEncodeStream() {
        if (isEncodeStream()) {
            encodeOpusStream(0, this.managerAddr);
        }
    }

    public void writeEncodeStream(byte[] bArr) throws InterruptedException {
        if (isEncodeStream()) {
            addEncodeStreamData(bArr);
        }
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    protected int nativeEncodeFile(String str, String str2) {
        return encodeOpusFile(str, str2, this.managerAddr);
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    @Deprecated
    protected int nativeDecodeFile(String str, String str2) {
        return nativeDecodeFile(str, str2, new OpusOption());
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    protected int nativeDecodeFile(String str, String str2, OpusOption opusOption) {
        if (opusOption == null) {
            opusOption = new OpusOption();
        }
        return decodeAudioFile(str, str2, this.managerAddr, opusOption);
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    @Deprecated
    protected void nativeDecodeStream(int i) {
        nativeDecodeStream(i, new OpusOption());
    }

    protected void nativeDecodeStream(int i, OpusOption opusOption) {
        decodeAudioStream(i, this.managerAddr, opusOption);
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    protected boolean nativeAddAudioStream(int i, byte[] bArr) {
        if (i == 0) {
            return savePcmStream(bArr, this.managerAddr);
        }
        return saveAudioStream(bArr, this.managerAddr);
    }

    @Override // com.jieli.jl_audio_decode.tool.BaseManager
    protected int nativeGetAudioStreamState() {
        return getAudioStreamState(this.managerAddr);
    }

    private void setDecodeStateCb(OnStateCallback onStateCallback) {
        this.mDecodeStateCb = onStateCallback;
    }

    private void setEncodeStateCb(OnStateCallback onStateCallback) {
        this.mEncodeStateCb = onStateCallback;
    }

    private void setDecodeStreamCb(OnDecodeStreamCallback onDecodeStreamCallback) {
        this.mDecodeStreamCb = onDecodeStreamCallback;
    }

    private void setEncodeStreamCb(OnEncodeStreamCallback onEncodeStreamCallback) {
        this.mEncodeStreamCb = onEncodeStreamCallback;
    }

    private void startDecodeFileThread(String str, String str2, OpusOption opusOption) {
        if (this.mDecodeFileThread == null) {
            DecodeFileThread decodeFileThread = new DecodeFileThread(this, str, str2, opusOption, this.mDecodeStateCb, new OnThreadFinishListener() { // from class: com.jieli.jl_audio_decode.opus.OpusManager$$ExternalSyntheticLambda1
                @Override // com.jieli.jl_audio_decode.callback.OnThreadFinishListener
                public final void onThreadFinish(long j) {
                    this.f$0.m577xc8be3c8e(j);
                }
            });
            this.mDecodeFileThread = decodeFileThread;
            decodeFileThread.start();
        }
    }

    /* renamed from: lambda$startDecodeFileThread$0$com-jieli-jl_audio_decode-opus-OpusManager */
    /* synthetic */ void m577xc8be3c8e(long j) {
        DecodeFileThread decodeFileThread = this.mDecodeFileThread;
        if (decodeFileThread == null || decodeFileThread.getId() != j) {
            return;
        }
        this.mDecodeFileThread = null;
        setDecodeStateCb(null);
    }

    private void stopDecodeFileThread() {
        DecodeFileThread decodeFileThread = this.mDecodeFileThread;
        if (decodeFileThread != null) {
            decodeFileThread.interrupt();
        }
    }

    private void startEncodeFileThread(String str, String str2) {
        if (this.mEncodeFileThread == null) {
            EncodeFileThread encodeFileThread = new EncodeFileThread(this, str, str2, this.mEncodeStateCb, new OnThreadFinishListener() { // from class: com.jieli.jl_audio_decode.opus.OpusManager$$ExternalSyntheticLambda3
                @Override // com.jieli.jl_audio_decode.callback.OnThreadFinishListener
                public final void onThreadFinish(long j) {
                    this.f$0.m578x7c426cc5(j);
                }
            });
            this.mEncodeFileThread = encodeFileThread;
            encodeFileThread.start();
        }
    }

    /* renamed from: lambda$startEncodeFileThread$1$com-jieli-jl_audio_decode-opus-OpusManager */
    /* synthetic */ void m578x7c426cc5(long j) {
        EncodeFileThread encodeFileThread = this.mEncodeFileThread;
        if (encodeFileThread == null || encodeFileThread.getId() != j) {
            return;
        }
        this.mEncodeFileThread = null;
        setEncodeStateCb(null);
    }

    private void stopEncodeFileThread() {
        EncodeFileThread encodeFileThread = this.mEncodeFileThread;
        if (encodeFileThread != null) {
            encodeFileThread.interrupt();
        }
    }

    private void addStreamData(byte[] bArr) throws InterruptedException {
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        if (this.mAddStreamDataThread == null) {
            AddStreamDataThread addStreamDataThread = new AddStreamDataThread(this, 2, this.mDecodeStreamCb, new OnThreadFinishListener() { // from class: com.jieli.jl_audio_decode.opus.OpusManager$$ExternalSyntheticLambda0
                @Override // com.jieli.jl_audio_decode.callback.OnThreadFinishListener
                public final void onThreadFinish(long j) {
                    this.f$0.m576x8334eb57(j);
                }
            });
            this.mAddStreamDataThread = addStreamDataThread;
            addStreamDataThread.start();
        }
        this.mAddStreamDataThread.addStreamData(bArr);
    }

    /* renamed from: lambda$addStreamData$2$com-jieli-jl_audio_decode-opus-OpusManager */
    /* synthetic */ void m576x8334eb57(long j) {
        AddStreamDataThread addStreamDataThread = this.mAddStreamDataThread;
        if (addStreamDataThread == null || addStreamDataThread.getId() != j) {
            return;
        }
        this.mAddStreamDataThread = null;
        setDecodeStreamCb(null);
    }

    private void stopAddDataThread() {
        AddStreamDataThread addStreamDataThread = this.mAddStreamDataThread;
        if (addStreamDataThread != null) {
            addStreamDataThread.stopThread();
        }
    }

    private void addEncodeStreamData(byte[] bArr) throws InterruptedException {
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        if (this.mEncodeStreamDataThread == null) {
            AddStreamDataThread addStreamDataThread = new AddStreamDataThread(this, 0, this.mEncodeStreamCb, new OnThreadFinishListener() { // from class: com.jieli.jl_audio_decode.opus.OpusManager$$ExternalSyntheticLambda2
                @Override // com.jieli.jl_audio_decode.callback.OnThreadFinishListener
                public final void onThreadFinish(long j) {
                    this.f$0.m575xa67688c0(j);
                }
            });
            this.mEncodeStreamDataThread = addStreamDataThread;
            addStreamDataThread.start();
        }
        this.mEncodeStreamDataThread.addStreamData(bArr);
    }

    /* renamed from: lambda$addEncodeStreamData$3$com-jieli-jl_audio_decode-opus-OpusManager */
    /* synthetic */ void m575xa67688c0(long j) {
        AddStreamDataThread addStreamDataThread = this.mEncodeStreamDataThread;
        if (addStreamDataThread == null || addStreamDataThread.getId() != j) {
            return;
        }
        this.mEncodeStreamDataThread = null;
        setEncodeStreamCb(null);
    }

    private void stopAddEncodeDataThread() {
        AddStreamDataThread addStreamDataThread = this.mEncodeStreamDataThread;
        if (addStreamDataThread != null) {
            addStreamDataThread.stopThread();
        }
    }

    protected void onStateCallback(int i, int i2, int i3, String str) {
        if (i == 1) {
            if (this.mDecodeStateCb == null) {
                return;
            }
            if (i2 == 0) {
                this.mDecodeStateCb.onComplete(str);
                return;
            } else if (i2 == 1) {
                this.mDecodeStateCb.onStart();
                return;
            } else {
                if (i2 != 2) {
                    return;
                }
                this.mDecodeStateCb.onError(i3, str);
                return;
            }
        }
        if (i == 2) {
            if (this.mEncodeStateCb == null) {
                return;
            }
            if (i2 == 0) {
                this.mEncodeStateCb.onComplete(str);
                return;
            } else if (i2 == 1) {
                this.mEncodeStateCb.onStart();
                return;
            } else {
                if (i2 != 2) {
                    return;
                }
                this.mEncodeStateCb.onError(i3, str);
                return;
            }
        }
        if (i == 3) {
            if (this.mDecodeStreamCb == null) {
                return;
            }
            if (i2 == 0) {
                this.mDecodeStreamCb.onComplete(str);
                return;
            } else if (i2 == 1) {
                this.mDecodeStreamCb.onStart();
                return;
            } else {
                if (i2 != 2) {
                    return;
                }
                this.mDecodeStreamCb.onError(i3, str);
                return;
            }
        }
        if (i == 4 && this.mEncodeStreamCb != null) {
            if (i2 == 0) {
                this.mEncodeStreamCb.onComplete(str);
            } else if (i2 == 1) {
                this.mEncodeStreamCb.onStart();
            } else {
                if (i2 != 2) {
                    return;
                }
                this.mEncodeStreamCb.onError(i3, str);
            }
        }
    }

    protected void onDecodeStreamReceive(int i, byte[] bArr) {
        if (this.mDecodeStreamCb == null) {
            return;
        }
        this.mDecodeStreamCb.onDecodeStream(bArr);
    }

    protected void onEncodeStreamReceive(int i, byte[] bArr) {
        if (this.mEncodeStreamCb == null) {
            return;
        }
        this.mEncodeStreamCb.onEncodeStream(bArr);
    }
}
