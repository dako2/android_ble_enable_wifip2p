package com.glasssutdio.wear.p003ai.spark;

import android.content.Context;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.depository.AiChatDepository;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes.dex */
public class AudioTrackManager {
    private static final int mAudioFormat = 2;
    private static final int mChannelConfig = 2;
    private static volatile AudioTrackManager mInstance = null;
    private static int mMode = 1;
    public static final int mSampleRateIn16KHz = 16000;
    public static final int mSampleRateIn24KHz = 24000;
    private static final int mStreamType = 3;
    private MyAudioCallback audioCallback;
    private AudioTrack mAudioTrack;
    private int mMinBufferSize;
    private Thread mPlayThread;
    private volatile boolean isPlaying = false;
    private int mSampleRate = 16000;
    private BlockingQueue<AudioByte> audioDataQueue = new LinkedBlockingQueue();
    String currentPlaySid = "";
    String lastPlaySid = "";
    Runnable playRunnable = new RunnableC07821();
    private Handler mHandler = new Handler(Looper.getMainLooper());

    interface MyAudioCallback {
        void endAudio(String sid, boolean complete);

        void onPlaying(String sid, String text);

        void startAudio(String sid, String text);
    }

    public enum sampleRateType {
        SAMPLE_RATE_16k,
        SAMPLE_RATE_24k
    }

    public void setAudioCallback(MyAudioCallback audioCallback) {
        this.audioCallback = audioCallback;
    }

    public AudioTrackManager(Context context) {
        initData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initData() {
        Log.d("AEE", "AudioTrackManager:sampleRate=" + this.mSampleRate);
        this.mMinBufferSize = AudioTrack.getMinBufferSize(this.mSampleRate, 2, 2) * 2;
        this.mAudioTrack = new AudioTrack(3, this.mSampleRate, 2, 2, this.mMinBufferSize, mMode);
    }

    public void setSampleRate(sampleRateType sampleRate) {
        int iOrdinal = sampleRate.ordinal();
        if (iOrdinal == 0) {
            this.mSampleRate = 16000;
        } else if (iOrdinal == 1) {
            this.mSampleRate = 24000;
        }
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack != null) {
            audioTrack.release();
            initData();
        }
    }

    public static AudioTrackManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (AudioTrackManager.class) {
                if (mInstance == null) {
                    mInstance = new AudioTrackManager(context);
                }
            }
        }
        return mInstance;
    }

    public void setAudioTrackNull() {
        mInstance = null;
    }

    public void restartPlay() throws IllegalStateException {
        this.audioDataQueue.clear();
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack == null || audioTrack.getState() != 1) {
            return;
        }
        this.mAudioTrack.stop();
    }

    public void startPlay(String sid, String text) throws IllegalStateException {
        try {
            if (this.isPlaying) {
                return;
            }
            this.isPlaying = true;
            Thread thread = new Thread(this.playRunnable);
            this.mPlayThread = thread;
            thread.start();
            AudioTrack audioTrack = this.mAudioTrack;
            if (audioTrack != null) {
                audioTrack.play();
            }
            MyAudioCallback myAudioCallback = this.audioCallback;
            if (myAudioCallback != null) {
                myAudioCallback.startAudio(sid, text);
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void stopPlay() throws IllegalStateException {
        try {
            if (this.isPlaying) {
                this.isPlaying = false;
                MyAudioCallback myAudioCallback = this.audioCallback;
                if (myAudioCallback != null) {
                    myAudioCallback.endAudio(this.currentPlaySid, false);
                    this.lastPlaySid = this.currentPlaySid;
                }
                Thread thread = this.mPlayThread;
                if (thread != null) {
                    thread.interrupt();
                }
                AudioTrack audioTrack = this.mAudioTrack;
                if (audioTrack != null) {
                    if (audioTrack.getState() == 1) {
                        this.mAudioTrack.stop();
                    }
                    this.mAudioTrack.release();
                    this.mAudioTrack = null;
                }
                this.audioDataQueue.clear();
            }
        } catch (IllegalStateException e) {
            XLog.m133e("停止播放时发生异常: ", e);
        }
    }

    public void feedPCMData(byte[] data) throws InterruptedException {
        if (!this.isPlaying || data == null) {
            return;
        }
        try {
            this.audioDataQueue.put(new AudioByte(data, ""));
        } catch (InterruptedException e) {
            XLog.m133e("Interrupted while adding audio data to queue: ", e);
        }
    }

    public void feedPCMDataWithSid(byte[] data, String sid, String text) throws InterruptedException {
        if (!this.isPlaying || data == null) {
            return;
        }
        try {
            this.audioDataQueue.put(new AudioByte(data, sid, text));
        } catch (InterruptedException e) {
            XLog.m133e("Interrupted while adding audio data to queue: ", e);
        }
    }

    /* renamed from: com.glasssutdio.wear.ai.spark.AudioTrackManager$1 */
    class RunnableC07821 implements Runnable {
        RunnableC07821() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x003a, code lost:
        
            r6.this$0.isPlaying = false;
            r6.this$0.mHandler.post(new com.glasssutdio.wear.p003ai.spark.AudioTrackManager$1$$ExternalSyntheticLambda0(r6));
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() throws IllegalStateException, SecurityException, IllegalArgumentException {
            Process.setThreadPriority(-19);
            while (true) {
                try {
                    try {
                        try {
                            if (!AudioTrackManager.this.isPlaying) {
                                break;
                            }
                            final AudioByte audioByte = (AudioByte) AudioTrackManager.this.audioDataQueue.poll();
                            if (audioByte != null) {
                                AudioTrackManager.this.currentPlaySid = audioByte.getSid();
                            }
                            if (audioByte == null) {
                                Thread.sleep(1000L);
                                audioByte = (AudioByte) AudioTrackManager.this.audioDataQueue.poll();
                                if (audioByte == null) {
                                    break;
                                }
                            }
                            if (AudioTrackManager.this.mAudioTrack != null) {
                                if (AudioTrackManager.this.mAudioTrack.getState() != 1) {
                                    AudioTrackManager.this.mAudioTrack.stop();
                                    AudioTrackManager.this.initData();
                                    AudioTrackManager.this.mAudioTrack.play();
                                }
                                AudioTrackManager.this.mAudioTrack.write(audioByte.getRawData(), 0, audioByte.getRawData().length);
                            }
                            if (AudioTrackManager.this.audioCallback != null) {
                                XLog.m127d("-----sid: " + audioByte.getSid() + " text: " + audioByte.getText());
                                AudioTrackManager.this.mHandler.post(new Runnable() { // from class: com.glasssutdio.wear.ai.spark.AudioTrackManager$1$$ExternalSyntheticLambda1
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        this.f$0.m759lambda$run$1$comglasssutdiowearaisparkAudioTrackManager$1(audioByte);
                                    }
                                });
                            }
                        } catch (InterruptedException e) {
                            XLog.m133e("Play thread interrupted: ", e);
                            if (AudioTrackManager.this.mAudioTrack == null || AudioTrackManager.this.mAudioTrack.getState() != 1) {
                                return;
                            }
                            AudioTrackManager.this.mAudioTrack.stop();
                            return;
                        }
                    } catch (IllegalStateException e2) {
                        e2.printStackTrace();
                        return;
                    }
                } catch (Throwable th) {
                    try {
                        if (AudioTrackManager.this.mAudioTrack != null && AudioTrackManager.this.mAudioTrack.getState() == 1) {
                            AudioTrackManager.this.mAudioTrack.stop();
                        }
                    } catch (IllegalStateException e3) {
                        e3.printStackTrace();
                    }
                    throw th;
                }
            }
            if (AudioTrackManager.this.mAudioTrack == null || AudioTrackManager.this.mAudioTrack.getState() != 1) {
                return;
            }
            AudioTrackManager.this.mAudioTrack.stop();
        }

        /* renamed from: lambda$run$0$com-glasssutdio-wear-ai-spark-AudioTrackManager$1, reason: not valid java name */
        /* synthetic */ void m758lambda$run$0$comglasssutdiowearaisparkAudioTrackManager$1() throws IllegalStateException {
            if (AiChatDepository.INSTANCE.getGetInstance().getRealTimeTTSQueue().isEmpty() && AudioTrackManager.this.audioCallback != null) {
                AudioTrackManager.this.audioCallback.endAudio(AudioTrackManager.this.currentPlaySid, true);
                AudioTrackManager audioTrackManager = AudioTrackManager.this;
                audioTrackManager.lastPlaySid = audioTrackManager.currentPlaySid;
            }
            if (AudioTrackManager.this.mAudioTrack != null) {
                AudioTrackManager.this.mAudioTrack.stop();
            }
        }

        /* renamed from: lambda$run$1$com-glasssutdio-wear-ai-spark-AudioTrackManager$1, reason: not valid java name */
        /* synthetic */ void m759lambda$run$1$comglasssutdiowearaisparkAudioTrackManager$1(AudioByte audioByte) {
            AudioTrackManager.this.audioCallback.onPlaying(audioByte.getSid(), audioByte.getText());
        }
    }

    public int getPlayState() {
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack != null) {
            return audioTrack.getPlayState();
        }
        return 1;
    }
}
