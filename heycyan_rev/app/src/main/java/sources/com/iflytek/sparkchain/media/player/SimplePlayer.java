package com.iflytek.sparkchain.media.player;

import android.media.AudioTrack;
import android.util.Log;

/* loaded from: classes2.dex */
public class SimplePlayer {
    private static final int MIN_SLEEP = 5;
    public static final int STATE_MSG_ID = 16;
    private static final String TAG = "AudioPlayer";
    private InterfaceC2214c listener;
    private C2212a mAudioParam;
    private AudioTrack mAudioTrack;
    private byte[] mData;
    private PlayAudioThread mPlayAudioThread;
    private PlayerListener oldListener;
    private boolean mBReady = false;
    private Object mSyncObj = this;
    private boolean mThreadExitFlag = false;
    private int mPrimePlaySize = 0;
    private int mPlayOffset = 0;
    private int mPlayState = 0;

    private class PlayAudioThread extends Thread {
        private PlayAudioThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                try {
                    SimplePlayer.this.oldListener.onStart();
                    synchronized (SimplePlayer.this.mSyncObj) {
                        if (SimplePlayer.this.mPlayState != 0 && SimplePlayer.this.mPlayState != 3) {
                            SimplePlayer.this.mPlayState = 2;
                        }
                    }
                    while (true) {
                        if (SimplePlayer.this.mThreadExitFlag) {
                            SimplePlayer.this.oldListener.onStop();
                            break;
                        }
                        if (SimplePlayer.this.mPlayState == 3) {
                            SimplePlayer.this.oldListener.onPause();
                            SimplePlayer.this.mAudioTrack.pause();
                        } else if (SimplePlayer.this.mPlayState == 2) {
                            SimplePlayer.this.mAudioTrack.play();
                        }
                        SimplePlayer.this.mAudioTrack.write(SimplePlayer.this.mData, SimplePlayer.this.mPlayOffset, SimplePlayer.this.mPrimePlaySize);
                        SimplePlayer simplePlayer = SimplePlayer.this;
                        SimplePlayer.access$712(simplePlayer, simplePlayer.mPrimePlaySize);
                        if (SimplePlayer.this.mPlayOffset >= SimplePlayer.this.mData.length) {
                            SimplePlayer.this.onPlayComplete();
                            break;
                        }
                    }
                    synchronized (SimplePlayer.this.mSyncObj) {
                        SimplePlayer.this.mPlayState = 1;
                    }
                    if (SimplePlayer.this.mAudioTrack == null) {
                        return;
                    }
                } catch (Exception e) {
                    Log.e(SimplePlayer.TAG, "play media failed:" + e);
                    synchronized (SimplePlayer.this.mSyncObj) {
                        SimplePlayer.this.mPlayState = 1;
                        if (SimplePlayer.this.mAudioTrack == null) {
                            return;
                        }
                    }
                }
                SimplePlayer.this.mAudioTrack.release();
                SimplePlayer.this.mAudioTrack = null;
            } catch (Throwable th) {
                synchronized (SimplePlayer.this.mSyncObj) {
                    SimplePlayer.this.mPlayState = 1;
                    if (SimplePlayer.this.mAudioTrack != null) {
                        SimplePlayer.this.mAudioTrack.release();
                        SimplePlayer.this.mAudioTrack = null;
                    }
                    throw th;
                }
            }
        }
    }

    @Deprecated
    public interface PlayerListener {
        void onCompleted();

        void onError(int i);

        void onPause();

        void onStart();

        void onStop();
    }

    @Deprecated
    public SimplePlayer(PlayerListener playerListener) {
        this.oldListener = playerListener;
    }

    @Deprecated
    public SimplePlayer(PlayerListener playerListener, C2212a c2212a) {
        this.oldListener = playerListener;
        setAudioParam(c2212a);
    }

    public SimplePlayer(InterfaceC2214c interfaceC2214c) {
        setListener(interfaceC2214c);
    }

    public SimplePlayer(InterfaceC2214c interfaceC2214c, C2212a c2212a) {
        setListener(interfaceC2214c);
        setAudioParam(c2212a);
    }

    static /* synthetic */ int access$712(SimplePlayer simplePlayer, int i) {
        int i2 = simplePlayer.mPlayOffset + i;
        simplePlayer.mPlayOffset = i2;
        return i2;
    }

    private void createAudioTrack() throws Exception {
        C2212a c2212a = this.mAudioParam;
        int minBufferSize = AudioTrack.getMinBufferSize(c2212a.f491a, c2212a.f492b, c2212a.f493c);
        this.mPrimePlaySize = minBufferSize * 2;
        C2212a c2212a2 = this.mAudioParam;
        this.mAudioTrack = new AudioTrack(3, c2212a2.f491a, c2212a2.f492b, c2212a2.f493c, minBufferSize, 1);
    }

    private void releaseAudioTrack() throws IllegalStateException {
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack != null) {
            audioTrack.stop();
            this.mAudioTrack.release();
            this.mAudioTrack = null;
        }
    }

    private boolean setPlayState(int i) {
        synchronized (this.mSyncObj) {
            this.mPlayState = i;
        }
        return true;
    }

    private void startThread() {
        if (this.mPlayAudioThread == null) {
            this.mThreadExitFlag = false;
            PlayAudioThread playAudioThread = new PlayAudioThread();
            this.mPlayAudioThread = playAudioThread;
            playAudioThread.start();
        }
    }

    private void stopThread() {
        if (this.mPlayAudioThread != null) {
            this.mThreadExitFlag = true;
            this.mPlayAudioThread = null;
        }
    }

    public void onPlayComplete() {
        this.mPlayAudioThread = null;
        if (this.mPlayState != 3) {
            setPlayState(1);
            PlayerListener playerListener = this.oldListener;
            if (playerListener != null) {
                playerListener.onCompleted();
            }
        }
    }

    public boolean pause() {
        if (!this.mBReady) {
            return false;
        }
        if (this.mPlayState != 2) {
            return true;
        }
        setPlayState(3);
        stopThread();
        return true;
    }

    public boolean play() {
        if (!this.mBReady) {
            return false;
        }
        int i = this.mPlayState;
        if (i != 1) {
            if (i == 3) {
            }
            return true;
        }
        this.mPlayOffset = 0;
        setPlayState(2);
        startThread();
        return true;
    }

    public boolean prepare() {
        if (this.mData != null && this.mAudioParam != null) {
            if (this.mBReady) {
                return true;
            }
            try {
                createAudioTrack();
                this.mBReady = true;
                setPlayState(1);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean release() throws IllegalStateException {
        stop();
        releaseAudioTrack();
        this.mBReady = false;
        setPlayState(0);
        return true;
    }

    public boolean resume() {
        return true;
    }

    public void setAudioParam(C2212a c2212a) {
        this.mAudioParam = c2212a;
    }

    public void setDataSource(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        this.mData = bArr;
    }

    public void setListener(PlayerListener playerListener) {
        this.oldListener = playerListener;
    }

    public void setListener(InterfaceC2214c interfaceC2214c) {
        this.listener = interfaceC2214c;
    }

    public boolean stop() {
        if (!this.mBReady) {
            return false;
        }
        setPlayState(1);
        stopThread();
        return true;
    }
}
