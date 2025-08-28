package com.iflytek.sparkchain.media.record;

import android.media.AudioRecord;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.iflytek.sparkchain.core.media.record.PcmRecorder;
import com.iflytek.sparkchain.media.RecorderHelper;
import com.iflytek.sparkchain.media.listener.DecibelListener;
import com.iflytek.sparkchain.media.speech.SpeechError;
import com.iflytek.sparkchain.utils.constants.ErrorCode;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public class PcmRecorder extends Thread {
    public static final int MSG_RELEASE_RECORD = 2;
    public static final int MSG_START_RECORD = 0;
    public static final int MSG_STOP_RECORD = 1;
    public static final int RATE16K = 16000;
    public static final int READ_INTERVAL40MS = 40;
    private static final int RECORD_BUFFER_TIMES_FOR_FRAME = 4;
    private static final String TAG = "PcmRecorder";
    private startFinishListener finishListener;
    private int mAudioSource;
    public Handler mRecordHandle;
    private final short DEFAULT_BIT_SAMPLES = 16;
    private byte[] mDataBuffer = null;
    private AudioRecord mRecorder = null;
    private InterfaceC2219b mOutListener = null;
    private AtomicBoolean exit = new AtomicBoolean(false);
    private double checkDataSum = 0.0d;
    private double checkStandDev = 0.0d;
    private int mRate = 16000;
    private int mInterval = 40;
    private int mReadInterval = 40;
    private short mChannel = 1;
    private int audioFormat = 2;
    private int mBufferSize = 0;

    @Deprecated
    public static class Builder {
        private DecibelListener decibelListener;
        private int audioSource = 1;
        private int rate = 16000;
        private short channel = 1;
        private int audioFormat = 2;
        private int bufferSize = 0;

        public static Builder build() {
            return new Builder();
        }

        public int getAudioFormat() {
            return this.audioFormat;
        }

        public int getAudioSource() {
            return this.audioSource;
        }

        public int getBufferSize() {
            return this.bufferSize;
        }

        public short getChannel() {
            return this.channel;
        }

        public DecibelListener getDecibelListener() {
            return this.decibelListener;
        }

        public int getRate() {
            return this.rate;
        }

        public Builder setAudioFormat(int i) {
            this.audioFormat = i;
            return this;
        }

        public Builder setAudioSource(int i) {
            this.audioSource = i;
            return this;
        }

        public Builder setBufferSize(int i) {
            this.bufferSize = i;
            return this;
        }

        public Builder setChannel(short s) {
            this.channel = s;
            return this;
        }

        public Builder setDecibelListener(DecibelListener decibelListener) {
            this.decibelListener = decibelListener;
            return this;
        }

        public Builder setRate(int i) {
            this.rate = i;
            return this;
        }
    }

    private interface startFinishListener {
        void onFinish();
    }

    public PcmRecorder(RecorderHelper.Params params, InterfaceC2219b interfaceC2219b) {
        setListener(interfaceC2219b);
        init(params.getSource(), params.getRate(), params.getChannel(), params.getFormat(), params.getSize());
    }

    public PcmRecorder(Builder builder, PcmRecorder.PcmRecordListener pcmRecordListener) {
        setListener(pcmRecordListener);
        init(builder.audioSource, builder.rate, builder.channel, builder.audioFormat, builder.bufferSize);
    }

    private int calculateVolume(byte[] bArr) {
        double dAbs = 0.0d;
        for (int i = 0; i < bArr.length; i += 2) {
            int i2 = (bArr[i] & 255) + ((bArr[i + 1] & 255) << 8);
            if (i2 >= 32768) {
                i2 = 65535 - i2;
            }
            dAbs += Math.abs(i2);
        }
        return (int) (Math.log10(((dAbs / bArr.length) / 2.0d) + 1.0d) * 10.0d);
    }

    private double checkAudio(byte[] bArr, int i) {
        double dPow = 0.0d;
        if (bArr == null || i <= 0) {
            return 0.0d;
        }
        double d = 0.0d;
        for (byte b : bArr) {
            d += b;
        }
        double length = d / bArr.length;
        for (byte b2 : bArr) {
            dPow += Math.pow(b2 - length, 2.0d);
        }
        return Math.sqrt(dPow / (bArr.length - 1));
    }

    private void init(int i, int i2, short s, int i3, int i4) {
        this.mAudioSource = i;
        this.mRate = i2;
        this.mChannel = s;
        this.audioFormat = i3;
        this.mBufferSize = i4;
        int i5 = this.mInterval;
        if (i5 < 40 || i5 > 100) {
            this.mInterval = 40;
        }
        this.mReadInterval = 10;
        setPriority(10);
        setFinishListener(new startFinishListener() { // from class: com.iflytek.sparkchain.media.record.PcmRecorder.1
            boolean isInit = false;

            @Override // com.iflytek.sparkchain.media.record.PcmRecorder.startFinishListener
            public void onFinish() {
                if (this.isInit) {
                    return;
                }
                PcmRecorder.this.startRecording();
                this.isInit = true;
            }
        });
        start();
        Log.i(TAG, "START RUN");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initRetry() throws InterruptedException, SpeechError {
        if (this.exit.get()) {
            return;
        }
        try {
            initRecord(this.mChannel, this.mRate, this.mInterval, this.audioFormat, this.mBufferSize);
        } catch (Exception unused) {
            Thread.sleep(40L);
            throw new SpeechError(ErrorCode.ERROR_AUDIO_RECORD);
        }
    }

    private int readRecordData() throws SpeechError {
        InterfaceC2219b interfaceC2219b;
        String str;
        AudioRecord audioRecord = this.mRecorder;
        if (audioRecord == null) {
            str = "mRecorder is null";
        } else {
            if (this.mOutListener != null) {
                byte[] bArr = this.mDataBuffer;
                int i = audioRecord.read(bArr, 0, bArr.length);
                if (i > 0 && (interfaceC2219b = this.mOutListener) != null) {
                    interfaceC2219b.onBuffer(this.mDataBuffer, 0, i);
                    this.mOutListener.onDecibel(calculateVolume(this.mDataBuffer));
                } else if (i < 0) {
                    Log.e(TAG, "Record read data error: " + i);
                    InterfaceC2219b interfaceC2219b2 = this.mOutListener;
                    if (interfaceC2219b2 != null) {
                        interfaceC2219b2.onError(new SpeechError(ErrorCode.ERROR_AUDIO_RECORD));
                    }
                    throw new SpeechError(ErrorCode.ERROR_AUDIO_RECORD);
                }
                return i;
            }
            str = "mOutListener is null";
        }
        Log.e(TAG, str);
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void release() {
        synchronized (this) {
            try {
            } catch (Exception e) {
                Log.e(TAG, "Exception：" + e.toString());
            }
            if (this.mRecorder != null) {
                Log.d(TAG, "release record begin");
                this.mRecorder.release();
                this.mRecorder = null;
                InterfaceC2219b interfaceC2219b = this.mOutListener;
                if (interfaceC2219b != null) {
                    interfaceC2219b.onRelease();
                    this.mOutListener = null;
                }
                Log.d(TAG, "release record over");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRecord() throws InterruptedException, SpeechError {
        InterfaceC2219b interfaceC2219b = this.mOutListener;
        boolean z = true;
        if (interfaceC2219b != null) {
            interfaceC2219b.onStart(true);
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        while (!this.exit.get()) {
            int recordData = readRecordData();
            if (z) {
                this.checkDataSum += recordData;
                double d = this.checkStandDev;
                byte[] bArr = this.mDataBuffer;
                this.checkStandDev = d + checkAudio(bArr, bArr.length);
                Log.e(TAG, "checkAudio:checkStandDev=" + this.checkStandDev);
                if (System.currentTimeMillis() - jCurrentTimeMillis >= 1000) {
                    if (this.checkDataSum == 0.0d || this.checkStandDev == 0.0d) {
                        Log.e(TAG, "checkDataSum=" + this.checkDataSum + ",checkStandDev=" + this.checkStandDev);
                        Log.e(TAG, "cannot get record permission, get invalid audio data.");
                        throw new SpeechError(ErrorCode.ERROR_AUDIO_RECORD);
                    }
                    z = false;
                }
            }
            if (this.mDataBuffer.length > recordData) {
                Log.i(TAG, "current record read size is less than buffer size: " + recordData);
                Thread.sleep(this.mReadInterval);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRetry() throws IllegalStateException, InterruptedException, SpeechError {
        int i = 0;
        while (!this.exit.get()) {
            try {
                this.mRecorder.startRecording();
                if (this.mRecorder.getRecordingState() == 3) {
                    return;
                }
                Log.e(TAG, "recorder state is not recoding");
                throw new SpeechError(ErrorCode.ERROR_AUDIO_RECORD);
            } catch (Exception unused) {
                i++;
                if (i >= 10) {
                    Log.e(TAG, "recoder start failed");
                    throw new SpeechError(ErrorCode.ERROR_AUDIO_RECORD);
                }
                Thread.sleep(40L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stop(boolean z) {
        if (z) {
            synchronized (this) {
                try {
                    Log.d(TAG, "stopRecord...release");
                    AudioRecord audioRecord = this.mRecorder;
                    if (audioRecord != null) {
                        if (3 == audioRecord.getRecordingState() && 1 == this.mRecorder.getState()) {
                            Log.d(TAG, "stopRecord releaseRecording ing...");
                            this.mRecorder.release();
                            Log.d(TAG, "stopRecord releaseRecording end...");
                            this.mRecorder = null;
                        }
                        InterfaceC2219b interfaceC2219b = this.mOutListener;
                        if (interfaceC2219b != null) {
                            interfaceC2219b.onRelease();
                            this.mOutListener = null;
                        }
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Exception: " + e.toString());
                }
            }
        }
        Log.d(TAG, "stop record");
    }

    protected void finalize() throws Throwable {
        Log.d(TAG, "[finalize] release recoder");
        releaseRecord();
        super.finalize();
    }

    protected void initRecord(short s, int i, int i2, int i3, int i4) throws SpeechError {
        if (this.mRecorder != null) {
            Log.d(TAG, "[initRecord] recoder release first");
            release();
        }
        int i5 = (i2 * i) / 1000;
        int i6 = ((i5 * 64) * s) / 8;
        int i7 = s == 1 ? 2 : 3;
        int minBufferSize = AudioRecord.getMinBufferSize(i, i7, i3);
        AudioRecord audioRecord = new AudioRecord(this.mAudioSource, i, i7, i3, i6 < minBufferSize ? minBufferSize : i6);
        this.mRecorder = audioRecord;
        if (i4 == 0) {
            i4 = ((i5 * s) * 16) / 8;
        }
        this.mDataBuffer = new byte[i4];
        if (audioRecord.getState() != 1) {
            Log.d(TAG, "create AudioRecord errormRecorder state" + this.mRecorder.getState());
            InterfaceC2219b interfaceC2219b = this.mOutListener;
            if (interfaceC2219b != null) {
                interfaceC2219b.onError(new SpeechError(ErrorCode.ERROR_AUDIO_RECORD));
            }
            throw new SpeechError(ErrorCode.ERROR_AUDIO_RECORD);
        }
    }

    public void releaseRecord() {
        if (this.mRecordHandle == null) {
            Log.e(TAG, "mRecordHandle is NULL");
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 2;
        this.mRecordHandle.sendMessage(messageObtain);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Log.i("PcmRecord", "run....");
        try {
            initRetry();
            startRetry();
            Looper.prepare();
            this.mRecordHandle = new Handler() { // from class: com.iflytek.sparkchain.media.record.PcmRecorder.3
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    try {
                        int i = message.what;
                        if (i == 0) {
                            Log.i("mRecordHandle: ", "msg.what: " + message.what);
                            PcmRecorder.this.exit.set(false);
                            if (PcmRecorder.this.mRecorder == null) {
                                PcmRecorder.this.initRetry();
                                PcmRecorder.this.startRetry();
                            }
                            PcmRecorder.this.startRecord();
                            return;
                        }
                        if (i == 1) {
                            Log.i("mRecordHandle: ", "msg.what: " + message.what);
                            PcmRecorder.this.stop(((Boolean) message.obj).booleanValue());
                        } else {
                            if (i != 2) {
                                return;
                            }
                            Log.i("mRecordHandle: ", "msg.what: " + message.what);
                            PcmRecorder.this.release();
                        }
                    } catch (Exception e) {
                        Log.e(PcmRecorder.TAG, "Exception：" + e.getMessage());
                        if (PcmRecorder.this.mOutListener != null) {
                            PcmRecorder.this.mOutListener.onError(new SpeechError(ErrorCode.ERROR_AUDIO_RECORD));
                        }
                    }
                }
            };
            this.finishListener.onFinish();
            Looper.loop();
        } catch (Exception e) {
            Log.e("PcmRecord", "Exception：" + e.getMessage());
            InterfaceC2219b interfaceC2219b = this.mOutListener;
            if (interfaceC2219b != null) {
                interfaceC2219b.onError(new SpeechError(ErrorCode.ERROR_AUDIO_RECORD));
            }
        }
    }

    public void setFinishListener(startFinishListener startfinishlistener) {
        this.finishListener = startfinishlistener;
    }

    public void setListener(final PcmRecorder.PcmRecordListener pcmRecordListener) {
        this.mOutListener = new InterfaceC2219b() { // from class: com.iflytek.sparkchain.media.record.PcmRecorder.2
            @Override // com.iflytek.sparkchain.media.record.InterfaceC2219b
            public void onBuffer(byte[] bArr, int i, int i2) {
                pcmRecordListener.onRecordBuffer(bArr, i, i2);
            }

            @Override // com.iflytek.sparkchain.media.record.InterfaceC2219b
            public void onDecibel(int i) {
                pcmRecordListener.onDecibel(i);
            }

            @Override // com.iflytek.sparkchain.media.record.InterfaceC2219b
            public void onError(SpeechError speechError) {
                pcmRecordListener.onError((com.iflytek.sparkchain.core.media.speech.SpeechError) speechError);
            }

            @Override // com.iflytek.sparkchain.media.record.InterfaceC2219b
            public void onRelease() {
                pcmRecordListener.onRecordReleased();
            }

            @Override // com.iflytek.sparkchain.media.record.InterfaceC2219b
            public void onStart(boolean z) {
                pcmRecordListener.onRecordStarted(z);
            }
        };
    }

    public void setListener(InterfaceC2219b interfaceC2219b) {
        this.mOutListener = interfaceC2219b;
    }

    public void startRecording() {
        if (this.mRecordHandle == null) {
            Log.e(TAG, "mRecordHandle is NULL");
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 0;
        this.mRecordHandle.sendMessage(messageObtain);
    }

    public void stopRecord(boolean z) {
        if (this.mRecordHandle == null) {
            Log.e(TAG, "mRecordHandle is NULL");
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        messageObtain.obj = Boolean.valueOf(z);
        Log.i("mRecordHandle: ", "stopRecord " + messageObtain.what);
        this.exit.set(true);
        this.mRecordHandle.sendMessage(messageObtain);
    }
}
