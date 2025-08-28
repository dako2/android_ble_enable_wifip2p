package com.iflytek.sparkchain.core.asr;

import android.media.AudioRecord;
import android.os.Process;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public class AudioRecorder {
    private static AudioRecorder mInstance;
    private final String TAG;
    private int audioFormat;
    private int bufferSize;
    private AudioDataCallback callback;
    private int channels;
    private AtomicBoolean isStart;
    private AudioRecord mRecorder;
    private String path;
    Runnable recordRunnable;
    private Thread recordThread;
    private int sampleRateInHz;

    public interface AudioDataCallback {
        void onAudioData(byte[] bArr, int i);

        void onAudioVolume(double d, int i);
    }

    public AudioRecorder() {
        this(16000, 2, 16);
    }

    public AudioRecorder(int i, int i2, int i3) {
        this.TAG = "AudioRecorder";
        this.sampleRateInHz = 16000;
        this.audioFormat = 2;
        this.channels = 16;
        this.isStart = new AtomicBoolean();
        this.path = "/sdcard/iflytek/audio.wav";
        this.recordRunnable = new Runnable() { // from class: com.iflytek.sparkchain.core.asr.AudioRecorder.1
            @Override // java.lang.Runnable
            public void run() throws IllegalStateException {
                try {
                    try {
                        if (AudioRecorder.this.mRecorder != null) {
                            Process.setThreadPriority(-19);
                            byte[] bArr = new byte[AudioRecorder.this.bufferSize];
                            if (AudioRecorder.this.mRecorder.getState() != 1) {
                                AudioRecorder.this.stopRecord();
                                if (AudioRecorder.this.mRecorder != null) {
                                    AudioRecorder.this.mRecorder.stop();
                                    AudioRecorder.this.mRecorder.release();
                                    AudioRecorder.this.mRecorder = null;
                                    return;
                                }
                                return;
                            }
                            AudioRecorder.this.mRecorder.startRecording();
                            while (AudioRecorder.this.isStart.get()) {
                                synchronized (this) {
                                    if (AudioRecorder.this.mRecorder != null) {
                                        int i4 = AudioRecorder.this.mRecorder.read(bArr, 0, AudioRecorder.this.bufferSize);
                                        if (i4 != -3 && i4 != -2) {
                                            if (i4 != 0 && i4 != -1 && AudioRecorder.this.isStart.get()) {
                                                int i5 = i4 / 2;
                                                double d = 0.0d;
                                                for (int i6 = 0; i6 < i4; i6 += 2) {
                                                    double d2 = (short) ((bArr[i6] & 255) | ((bArr[i6 + 1] & 255) << 8));
                                                    d += d2 * d2;
                                                }
                                                double dSqrt = Math.sqrt(d / i5);
                                                double dLog10 = dSqrt > 1.0E-10d ? Math.log10(dSqrt / 32767.0d) * 20.0d : -120.0d;
                                                AudioRecorder.this.callback.onAudioVolume(dLog10, dLog10 > -60.0d ? (int) Math.min(9.0d, Math.max(0.0d, ((60.0d + dLog10) * 9.0d) / 40.0d)) : 0);
                                                AudioRecorder.this.callback.onAudioData(bArr, i4);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (AudioRecorder.this.mRecorder == null) {
                            return;
                        }
                    } catch (Exception e) {
                        Log.w("AudioRecorder", "录音异常:" + e.toString());
                        e.printStackTrace();
                        if (AudioRecorder.this.mRecorder == null) {
                            return;
                        }
                    }
                    AudioRecorder.this.mRecorder.stop();
                    AudioRecorder.this.mRecorder.release();
                    AudioRecorder.this.mRecorder = null;
                } catch (Throwable th) {
                    if (AudioRecorder.this.mRecorder != null) {
                        AudioRecorder.this.mRecorder.stop();
                        AudioRecorder.this.mRecorder.release();
                        AudioRecorder.this.mRecorder = null;
                    }
                    throw th;
                }
            }
        };
        this.sampleRateInHz = i;
        this.audioFormat = i2;
        this.channels = i3;
        this.bufferSize = AudioRecord.getMinBufferSize(i, i3, i2);
        this.mRecorder = new AudioRecord(1, i, i3, i2, this.bufferSize);
    }

    public static AudioRecorder getInstance() {
        if (mInstance == null) {
            synchronized (AudioRecorder.class) {
                if (mInstance == null) {
                    mInstance = new AudioRecorder();
                }
            }
        }
        return mInstance;
    }

    private void startThread() {
        destroyThread();
        this.isStart.set(true);
        Log.i("AudioRecorder", "recordThread：" + (this.recordThread == null));
        if (this.recordThread == null) {
            Thread thread = new Thread(this.recordRunnable);
            this.recordThread = thread;
            thread.start();
        }
    }

    public void destroyThread() {
        synchronized (this) {
            try {
                try {
                    this.isStart.set(false);
                    Thread thread = this.recordThread;
                    if (thread != null && thread.isAlive()) {
                        try {
                            try {
                                this.recordThread.interrupt();
                                this.recordThread.join();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            this.recordThread = null;
                        } finally {
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } finally {
            }
        }
    }

    public void registerCallBack(AudioDataCallback audioDataCallback) {
        this.callback = audioDataCallback;
    }

    public void startRecord() {
        try {
            startThread();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopRecord() {
        destroyThread();
        synchronized (this) {
            try {
                try {
                    if (this.callback != null) {
                        this.callback = null;
                    }
                    AudioRecord audioRecord = this.mRecorder;
                    if (audioRecord != null) {
                        if (audioRecord.getState() == 1) {
                            this.mRecorder.stop();
                        }
                        AudioRecord audioRecord2 = this.mRecorder;
                        if (audioRecord2 != null) {
                            audioRecord2.release();
                        }
                        this.mRecorder = null;
                    }
                    if (mInstance != null) {
                        mInstance = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } finally {
                mInstance = null;
            }
        }
    }

    protected void writeDataToFile(String str, byte[] bArr, boolean z) throws IOException {
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileChannel channel = new FileOutputStream(str, z).getChannel();
            channel.write(ByteBuffer.wrap(bArr));
            channel.force(true);
            channel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            Log.e("AudioRecorder", "writeFile:" + e2.toString());
        }
    }
}
