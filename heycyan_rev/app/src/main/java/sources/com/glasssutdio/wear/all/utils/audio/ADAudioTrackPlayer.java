package com.glasssutdio.wear.all.utils.audio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class ADAudioTrackPlayer implements Runnable {
    public static int COMPLETED = 3;
    public static int PAUSED = 2;
    public static int PLAYING = 1;
    public static volatile ADAudioTrackPlayer instance;
    private AudioTrack aTrack;
    private boolean isPaused;
    private boolean isStop;
    private Context mContext;
    private InputStream mInputStream;
    private int mMinBufferSize;
    private String path;
    private ProgressCallback progressCallback;
    Thread thread;
    public long totalFrames;
    public long playedFrames = 0;
    private float volume = 1.0f;
    public int state = 0;
    private boolean isMuted = false;
    long lastNotifyTime = 0;
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    public boolean isFromUserChangeProgress = false;
    private int mFormat = 2;
    private boolean isBigendian = false;
    private int sampleRate = 16000;

    public interface ProgressCallback {
        void onProgressUpdate(long currentTime, long totalTime, int state);
    }

    public boolean isMuted() {
        return this.isMuted;
    }

    public String getPath() {
        return this.path;
    }

    public ProgressCallback getProgressCallback() {
        return this.progressCallback;
    }

    public void setProgressCallback(ProgressCallback progressCallback) {
        this.progressCallback = progressCallback;
    }

    @Override // java.lang.Runnable
    public void run() throws InterruptedException, IOException {
        int iWrite;
        this.lastNotifyTime = 0L;
        XLog.m137i("AudioThread start mMinBufferSize==> " + this.mMinBufferSize + "----" + (!this.isStop));
        while (!this.isStop) {
            if (this.isPaused) {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    XLog.m132e("Thread interrupted while paused: " + e.getMessage());
                }
            } else {
                try {
                    byte[] bArr = new byte[1024];
                    int i = this.mInputStream.read(bArr);
                    if (i == -1) {
                        this.isStop = true;
                        notifyCallback(COMPLETED);
                        return;
                    }
                    int i2 = this.mFormat;
                    if (i2 == 4) {
                        float[] fArrBytesToFloats = ByteUtil.bytesToFloats(bArr, i, this.isBigendian);
                        iWrite = this.aTrack.write(fArrBytesToFloats, 0, fArrBytesToFloats.length, 0);
                    } else if (i2 == 2) {
                        short[] sArrBytesToShorts = ByteUtil.bytesToShorts(bArr, i, this.isBigendian);
                        if (sArrBytesToShorts.length == 0) {
                            this.isStop = true;
                        }
                        iWrite = this.aTrack.write(sArrBytesToShorts, 0, sArrBytesToShorts.length);
                    } else {
                        iWrite = this.aTrack.write(bArr, 0, i);
                    }
                    if (iWrite <= 0) {
                        XLog.m132e("Failed to write data to AudioTrack. Frame count: " + iWrite);
                    }
                    this.playedFrames += iWrite;
                    long playedTime = getPlayedTime();
                    if (playedTime - this.lastNotifyTime >= 100 || this.aTrack.getState() == COMPLETED) {
                        notifyCallback(PLAYING);
                        this.lastNotifyTime = playedTime;
                    }
                } catch (Exception e2) {
                    XLog.m132e("IO error while reading audio data: " + e2.getMessage());
                    this.isStop = true;
                }
            }
        }
    }

    public static void initialize(Context context) {
        if (instance == null) {
            synchronized (ADAudioTrackPlayer.class) {
                if (instance == null) {
                    instance = new ADAudioTrackPlayer(context);
                }
            }
            return;
        }
        XLog.m152w("ADAudioTrackPlayer already initialized. Use reconfigure() to change audio source.");
    }

    public static ADAudioTrackPlayer getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ADAudioTrackPlayer not initialized. Call initialize() first.");
        }
        return instance;
    }

    public void setInstanceNull() {
        instance = null;
    }

    public ADAudioTrackPlayer(Context c) {
        this.mContext = c;
        initAudioTrackByBuilder(4, 16000, 2);
    }

    public void setPath(String path) throws IllegalStateException {
        stop();
        this.path = path;
        this.playedFrames = 0L;
        this.isFromUserChangeProgress = false;
        initInputStream(path);
        calculateTotalFrames();
        notifyCallback(PAUSED);
    }

    private void initAudioTrackByBuilder(int ch_layout, int sampleRate, int format) {
        this.mMinBufferSize = AudioTrack.getMinBufferSize(sampleRate, ch_layout, format);
        this.aTrack = new AudioTrack.Builder().setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(2).build()).setAudioFormat(new AudioFormat.Builder().setEncoding(format).setSampleRate(sampleRate).setChannelMask(ch_layout).build()).setBufferSizeInBytes(this.mMinBufferSize).build();
        XLog.m137i("aTrack init");
    }

    private void initInputStream(String path) {
        try {
            this.mInputStream = new FileInputStream(path);
        } catch (IOException e) {
            XLog.m132e("Failed to initialize input stream: " + e.getMessage());
        }
    }

    public void play() throws IllegalStateException {
        this.isStop = false;
        this.isPaused = false;
        AudioTrack audioTrack = this.aTrack;
        if (audioTrack == null || audioTrack.getState() == 0) {
            return;
        }
        initInputStream(this.path);
        final String str = this.path;
        Thread thread = new Thread(new Runnable() { // from class: com.glasssutdio.wear.all.utils.audio.ADAudioTrackPlayer$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException, IOException {
                this.f$0.m170xdfef833a(str);
            }
        });
        this.thread = thread;
        thread.start();
        this.aTrack.play();
        notifyCallback(PLAYING);
    }

    /* renamed from: lambda$play$0$com-glasssutdio-wear-all-utils-audio-ADAudioTrackPlayer */
    /* synthetic */ void m170xdfef833a(String str) throws InterruptedException, IOException {
        int iWrite;
        this.lastNotifyTime = 0L;
        XLog.m137i("AudioThread start mMinBufferSize==> " + this.mMinBufferSize);
        XLog.m137i(str + "-----" + this.path + "---" + this.isPaused);
        while (!this.isStop && str.equals(this.path)) {
            if (this.isPaused) {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    XLog.m132e("Thread interrupted while paused: " + e.getMessage());
                }
            } else {
                try {
                    byte[] bArr = new byte[1024];
                    int i = this.mInputStream.read(bArr);
                    if (i == -1) {
                        this.isStop = true;
                        notifyCallback(COMPLETED);
                        return;
                    }
                    int i2 = this.mFormat;
                    if (i2 == 4) {
                        float[] fArrBytesToFloats = ByteUtil.bytesToFloats(bArr, i, this.isBigendian);
                        iWrite = this.aTrack.write(fArrBytesToFloats, 0, fArrBytesToFloats.length, 0);
                    } else if (i2 == 2) {
                        short[] sArrBytesToShorts = ByteUtil.bytesToShorts(bArr, i, this.isBigendian);
                        if (sArrBytesToShorts.length == 0) {
                            this.isStop = true;
                        }
                        iWrite = this.aTrack.write(sArrBytesToShorts, 0, sArrBytesToShorts.length);
                    } else {
                        iWrite = this.aTrack.write(bArr, 0, i);
                    }
                    this.playedFrames += iWrite;
                    long playedTime = getPlayedTime();
                    if (playedTime - this.lastNotifyTime >= 100 || this.aTrack.getState() == COMPLETED) {
                        notifyCallback(PLAYING);
                        this.lastNotifyTime = playedTime;
                    }
                } catch (Exception e2) {
                    XLog.m132e("IO error while reading audio data: " + e2.getMessage());
                    this.isStop = true;
                }
            }
        }
    }

    public void pause() throws IllegalStateException {
        this.isPaused = true;
        AudioTrack audioTrack = this.aTrack;
        if (audioTrack == null || audioTrack.getState() == 0) {
            return;
        }
        this.aTrack.pause();
        notifyCallback(PAUSED);
    }

    public void restart() throws IllegalStateException {
        XLog.m137i("restart");
        stop();
        this.playedFrames = 0L;
        play();
    }

    public void resume() throws IllegalStateException {
        this.isPaused = false;
        this.isStop = false;
        StringBuilder sb = new StringBuilder("resume->");
        AudioTrack audioTrack = this.aTrack;
        XLog.m137i(sb.append(audioTrack != null ? Integer.valueOf(audioTrack.getState()) : "AudioTrack null").toString());
        AudioTrack audioTrack2 = this.aTrack;
        if (audioTrack2 != null) {
            if (audioTrack2.getState() == 0) {
                initAudioTrackByBuilder(4, this.sampleRate, this.mFormat);
            }
            this.aTrack.play();
            Thread thread = this.thread;
            if (thread == null || !thread.isAlive()) {
                Thread thread2 = new Thread(this);
                this.thread = thread2;
                thread2.start();
            }
            if (this.playedFrames >= this.totalFrames) {
                notifyCallback(COMPLETED);
            } else {
                notifyCallback(PLAYING);
            }
        }
    }

    public void stop() throws IllegalStateException {
        this.isStop = true;
        AudioTrack audioTrack = this.aTrack;
        if (audioTrack == null || audioTrack.getState() == 0) {
            return;
        }
        this.aTrack.stop();
    }

    public void release() throws IOException {
        try {
            AudioTrack audioTrack = this.aTrack;
            if (audioTrack != null) {
                audioTrack.release();
                this.aTrack = null;
            }
            InputStream inputStream = this.mInputStream;
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            XLog.m132e("Failed to close input stream: " + e.getMessage());
        }
    }

    public long getPlayedTime() {
        return (this.playedFrames * 1000) / this.sampleRate;
    }

    public long getTotalTime() {
        return (this.totalFrames * 1000) / this.sampleRate;
    }

    public void setVolume(float volume) {
        this.volume = volume;
        AudioTrack audioTrack = this.aTrack;
        if (audioTrack != null && audioTrack.getState() != 0) {
            this.aTrack.setVolume(volume);
        }
        this.isMuted = volume == 0.0f;
    }

    public void seekTo(float progress) throws IOException {
        if (progress < 0.0f || progress > 1.0f) {
            return;
        }
        calculateTotalFrames();
        this.lastNotifyTime = 0L;
        long j = (long) (this.totalFrames * progress);
        try {
            long bytesPerFrame = getBytesPerFrame() * j;
            this.mInputStream.close();
            FileInputStream fileInputStream = new FileInputStream(this.path);
            this.mInputStream = fileInputStream;
            long jSkip = fileInputStream.skip(bytesPerFrame);
            if (jSkip != bytesPerFrame) {
                XLog.m132e("Failed to skip the correct number of bytes. Expected: " + bytesPerFrame + ", Skipped: " + jSkip);
            }
            this.playedFrames = j;
        } catch (IOException e) {
            XLog.m132e("Seek error: " + e.getMessage());
        }
    }

    public void seekTo(float progress, boolean fromUser) throws IOException {
        if (progress < 0.0f || progress > 1.0f) {
            return;
        }
        this.isFromUserChangeProgress = fromUser;
        calculateTotalFrames();
        this.lastNotifyTime = 0L;
        long j = (long) (this.totalFrames * progress);
        try {
            long bytesPerFrame = getBytesPerFrame() * j;
            this.mInputStream.close();
            FileInputStream fileInputStream = new FileInputStream(this.path);
            this.mInputStream = fileInputStream;
            long jSkip = fileInputStream.skip(bytesPerFrame);
            if (jSkip != bytesPerFrame) {
                XLog.m132e("Failed to skip the correct number of bytes. Expected: " + bytesPerFrame + ", Skipped: " + jSkip);
            }
            this.playedFrames = j;
        } catch (IOException e) {
            XLog.m132e("Seek error: " + e.getMessage());
        }
    }

    private int getBytesPerFrame() {
        int i = this.mFormat;
        if (i == 2) {
            return 2;
        }
        if (i != 3) {
            return i != 4 ? 2 : 4;
        }
        return 1;
    }

    private void calculateTotalFrames() {
        this.totalFrames = new File(this.path).length() / getBytesPerFrame();
    }

    private void notifyCallback(final int state) {
        if (this.progressCallback != null) {
            long playedTime = (getPlayedTime() / 100) * 100;
            final long totalTime = (getTotalTime() / 100) * 100;
            final long j = state == COMPLETED ? totalTime : playedTime;
            this.state = state;
            this.mainHandler.post(new Runnable() { // from class: com.glasssutdio.wear.all.utils.audio.ADAudioTrackPlayer$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m169x51b3def5(j, totalTime, state);
                }
            });
        }
    }

    /* renamed from: lambda$notifyCallback$1$com-glasssutdio-wear-all-utils-audio-ADAudioTrackPlayer */
    /* synthetic */ void m169x51b3def5(long j, long j2, int i) {
        ProgressCallback progressCallback = this.progressCallback;
        if (progressCallback == null || this.isFromUserChangeProgress) {
            return;
        }
        progressCallback.onProgressUpdate(j, j2, i);
    }
}
