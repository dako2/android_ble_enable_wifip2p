package com.iflytek.sparkchain.media.player;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpStatus;
import com.iflytek.sparkchain.core.media.player.PcmPlayer;
import com.iflytek.sparkchain.media.record.C2218a;
import com.iflytek.sparkchain.media.speech.SpeechError;
import com.iflytek.sparkchain.utils.FuncAdapter;
import com.iflytek.sparkchain.utils.constants.ErrorCode;
import com.iflytek.sparkchain.utils.log.LogUtil;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.iflytek.sparkchain.media.player.b */
/* loaded from: classes2.dex */
public class C2213b {
    public static final int BUFFERING = 1;
    public static final int INIT = 0;
    private static final int MIN_SLEEP = 5;
    private static final int MSG_ERROR = 0;
    private static final int MSG_PAUSE = 1;
    private static final int MSG_PERCENT = 3;
    private static final int MSG_RESUME = 2;
    private static final int MSG_STOPED = 4;
    public static final int PAUSED = 3;
    public static final int PLAYING = 2;
    public static final int STOPED = 4;
    private static final String TAG = "PcmPlayer";
    private final int BYTES_OF_PER_SAMPLE;
    private final int FADE_PERIOD;
    private final int FADE_TIME;
    private final float MAX_VOL;
    private final float MIN_VOL;
    private final float PER;
    AudioManager.OnAudioFocusChangeListener afChangeListener;
    private AtomicBoolean isExist;
    private InterfaceC2214c listener;
    private boolean mAudioFocus;
    private Object mAudioLock;
    private AudioTrack mAudioTrack;
    private C2218a mBuffer;
    private int mBufferSize;
    private boolean mBufferingFadingEnable;
    private boolean mChangeListenerFlag;
    private Context mContext;
    private int mCurEndPos;
    private float mCurFadingPeriod;
    private float mCurVol;
    Condition mEndCondition;
    ReentrantLock mEndLock;
    private boolean mFading;
    private boolean mFadingEnable;
    private int mFadingSize;
    private int mPerPlaySize;
    private volatile int mPlaytate;
    private boolean mRequestFocus;
    private int mStreamType;
    private Object mSyncObj;
    private float mTargetVol;
    private d mThread;
    private Handler mUihandler;

    /* renamed from: com.iflytek.sparkchain.media.player.b$a */
    class a implements AudioManager.OnAudioFocusChangeListener {
        a() {
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            if (i == -2 || i == -3 || i == -1) {
                LogUtil.m562d(C2213b.TAG, "pause start");
                if (C2213b.this.pause()) {
                    LogUtil.m562d(C2213b.TAG, "pause success");
                    C2213b.this.mChangeListenerFlag = true;
                    C2213b.this.onPause();
                    return;
                }
                return;
            }
            if (i == 1) {
                LogUtil.m562d(C2213b.TAG, "resume start");
                if (C2213b.this.mChangeListenerFlag) {
                    C2213b.this.mChangeListenerFlag = false;
                    if (C2213b.this.resume()) {
                        LogUtil.m562d(C2213b.TAG, "resume success");
                        C2213b.this.onResume();
                    }
                }
            }
        }
    }

    /* renamed from: com.iflytek.sparkchain.media.player.b$b */
    class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                C2213b.this.onError((SpeechError) message.obj);
                return;
            }
            if (i == 1) {
                C2213b.this.onPause();
                return;
            }
            if (i == 2) {
                C2213b.this.onResume();
            } else if (i == 3) {
                C2213b.this.onPercent(message.arg1, message.arg2);
            } else {
                if (i != 4) {
                    return;
                }
                C2213b.this.onStop();
            }
        }
    }

    /* renamed from: com.iflytek.sparkchain.media.player.b$c */
    class c implements InterfaceC2214c {

        /* renamed from: a */
        final /* synthetic */ PcmPlayer.PcmPlayerListener f496a;

        c(PcmPlayer.PcmPlayerListener pcmPlayerListener) {
            this.f496a = pcmPlayerListener;
        }

        @Override // com.iflytek.sparkchain.media.player.InterfaceC2214c
        public void onError(SpeechError speechError) {
            this.f496a.onError((com.iflytek.sparkchain.core.media.speech.SpeechError) speechError);
        }

        @Override // com.iflytek.sparkchain.media.player.InterfaceC2214c
        public void onPause() {
            this.f496a.onPaused();
        }

        @Override // com.iflytek.sparkchain.media.player.InterfaceC2214c
        public void onPercent(int i, int i2, int i3) {
            this.f496a.onPercent(i, i2, i3);
        }

        @Override // com.iflytek.sparkchain.media.player.InterfaceC2214c
        public void onResume() {
            this.f496a.onResume();
        }

        @Override // com.iflytek.sparkchain.media.player.InterfaceC2214c
        public void onStop() {
            this.f496a.onStoped();
        }
    }

    /* renamed from: com.iflytek.sparkchain.media.player.b$d */
    private class d extends Thread {

        /* renamed from: a */
        private int f498a;

        /* renamed from: com.iflytek.sparkchain.media.player.b$d$a */
        class a implements AudioTrack.OnPlaybackPositionUpdateListener {
            a() {
            }

            @Override // android.media.AudioTrack.OnPlaybackPositionUpdateListener
            public void onMarkerReached(AudioTrack audioTrack) {
                LogUtil.m567i("PcmPlayer onMarkerReached");
                C2213b.this.mEndLock.lock();
                try {
                    C2213b.this.mEndCondition.signalAll();
                } catch (Exception unused) {
                } catch (Throwable th) {
                    C2213b.this.mEndLock.unlock();
                    throw th;
                }
                C2213b.this.mEndLock.unlock();
            }

            @Override // android.media.AudioTrack.OnPlaybackPositionUpdateListener
            public void onPeriodicNotification(AudioTrack audioTrack) {
            }
        }

        private d() {
            this.f498a = C2213b.this.mStreamType;
        }

        /* synthetic */ d(C2213b c2213b, a aVar) {
            this();
        }

        /* renamed from: a */
        public int m538a() {
            return this.f498a;
        }

        /* renamed from: a */
        public void m539a(int i) {
            this.f498a = i;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            C2213b c2213b;
            try {
                try {
                    LogUtil.m562d(C2213b.TAG, "start player");
                    LogUtil.m562d(C2213b.TAG, "mAudioFocus= " + C2213b.this.mAudioFocus);
                    if (C2213b.this.mAudioFocus) {
                        FuncAdapter.Lock(C2213b.this.mContext, Boolean.valueOf(C2213b.this.mRequestFocus), C2213b.this.afChangeListener);
                    } else {
                        FuncAdapter.Lock(C2213b.this.mContext, Boolean.valueOf(C2213b.this.mRequestFocus), null);
                    }
                    C2213b.this.mBuffer.beginRead();
                    synchronized (C2213b.this.mSyncObj) {
                        if (C2213b.this.mPlaytate != 4 && C2213b.this.mPlaytate != 3) {
                            C2213b.this.mPlaytate = 2;
                        }
                    }
                    C2213b.this.startFadeIn();
                } catch (Exception e) {
                    LogUtil.m564e(e);
                    Message.obtain(C2213b.this.mUihandler, 0, new SpeechError(ErrorCode.ERROR_PLAY_MEDIA)).sendToTarget();
                    synchronized (C2213b.this.mSyncObj) {
                        C2213b.this.mPlaytate = 4;
                        if (C2213b.this.mAudioTrack != null) {
                            C2213b.this.mAudioTrack.release();
                            C2213b.this.mAudioTrack = null;
                        }
                        if (C2213b.this.mAudioFocus) {
                        }
                    }
                }
                while (true) {
                    if (C2213b.this.isExist.get()) {
                        break;
                    }
                    C2213b.this.prepAudioPlayer();
                    if (C2213b.this.mPlaytate == 2 || C2213b.this.mPlaytate == 1 || C2213b.this.mFading) {
                        if (C2213b.this.mBuffer.playAble()) {
                            if (C2213b.this.setState(1, 2)) {
                                Message.obtain(C2213b.this.mUihandler, 2).sendToTarget();
                                LogUtil.m561d("BUFFERING to PLAYING  fading ");
                                C2213b.this.startFadeIn();
                            }
                            int playPercent = C2213b.this.mBuffer.getPlayPercent();
                            C2218a.a palyAudioInfo = C2213b.this.mBuffer.getPalyAudioInfo();
                            if (palyAudioInfo != null) {
                                C2213b.this.mCurEndPos = palyAudioInfo.f501a;
                                Message.obtain(C2213b.this.mUihandler, 3, playPercent, palyAudioInfo.f504d).sendToTarget();
                            }
                            if (C2213b.this.mAudioTrack.getPlayState() != 3) {
                                C2213b.this.mAudioTrack.play();
                            }
                            if (C2213b.this.mBufferingFadingEnable) {
                                if (!C2213b.this.mBuffer.isBufferingFinished() && !C2213b.this.mBuffer.hasMoreBuffer(C2213b.this.mFadingSize) && Math.abs(C2213b.this.mTargetVol - 0.0f) >= 0.1f) {
                                    LogUtil.m561d("no more size  fading ");
                                    C2213b.this.startFadeOut();
                                } else if (2 == C2213b.this.mPlaytate && ((C2213b.this.mBuffer.isBufferingFinished() || C2213b.this.mBuffer.hasMoreBuffer(C2213b.this.mFadingSize)) && Math.abs(C2213b.this.mTargetVol - 1.0f) >= 0.1f)) {
                                    LogUtil.m561d("has buffer  fading ");
                                    C2213b.this.startFadeIn();
                                }
                            }
                            if (C2213b.this.mFading) {
                                C2213b.this.fading();
                            }
                            C2213b.this.mBuffer.writeTrack(C2213b.this.mAudioTrack, C2213b.this.mPerPlaySize);
                        } else if (C2213b.this.mBuffer.isOver()) {
                            LogUtil.m561d("play stoped");
                            int playbackHeadPosition = C2213b.this.mAudioTrack.getPlaybackHeadPosition();
                            int totalSize = (int) (C2213b.this.mBuffer.getTotalSize() / 2);
                            if (totalSize > playbackHeadPosition && C2213b.this.mEndLock.tryLock()) {
                                C2213b.this.mAudioTrack.setNotificationMarkerPosition(totalSize);
                                LogUtil.m567i("PcmPlayer setNotificationMarkerPosition");
                                C2213b.this.mAudioTrack.setPlaybackPositionUpdateListener(new a());
                                try {
                                    try {
                                        C2213b.this.mEndCondition.await(1000L, TimeUnit.MILLISECONDS);
                                        c2213b = C2213b.this;
                                    } catch (InterruptedException e2) {
                                        LogUtil.m567i("pcmplayer interrupted");
                                        e2.printStackTrace();
                                        c2213b = C2213b.this;
                                    }
                                    c2213b.mEndLock.unlock();
                                } catch (Throwable th) {
                                    C2213b.this.mEndLock.unlock();
                                    throw th;
                                }
                            }
                            synchronized (C2213b.this.mSyncObj) {
                                LogUtil.m567i("pcmplayer isover stop:" + C2213b.this.mPlaytate);
                                if (C2213b.this.mPlaytate != 4) {
                                    C2213b.this.mPlaytate = 4;
                                    Message.obtain(C2213b.this.mUihandler, 4).sendToTarget();
                                }
                                C2213b.this.mFading = false;
                            }
                        } else if (C2213b.this.mFading) {
                            C2213b.this.mFading = false;
                        } else {
                            if (C2213b.this.setState(2, 1)) {
                                LogUtil.m561d("play onpaused!");
                                Message.obtain(C2213b.this.mUihandler, 1).sendToTarget();
                            }
                            Thread.sleep(5L);
                        }
                    } else if (C2213b.this.mPlaytate == 3) {
                        if (2 != C2213b.this.mAudioTrack.getPlayState()) {
                            C2213b.this.mAudioTrack.pause();
                            LogUtil.m561d("pause done");
                            Message.obtain(C2213b.this.mUihandler, 1).sendToTarget();
                            if (C2213b.this.mFading) {
                                C2213b.this.setSilence();
                            }
                        }
                        Thread.sleep(5L);
                    } else if (4 == C2213b.this.mPlaytate) {
                        C2213b.this.setSilence();
                        break;
                    }
                    C2213b.this.mThread = null;
                    LogUtil.m562d(C2213b.TAG, "player stopped");
                }
                if (C2213b.this.mAudioTrack != null) {
                    C2213b.this.mAudioTrack.stop();
                }
                synchronized (C2213b.this.mSyncObj) {
                    C2213b.this.mPlaytate = 4;
                }
                if (C2213b.this.mAudioTrack != null) {
                    C2213b.this.mAudioTrack.release();
                    C2213b.this.mAudioTrack = null;
                }
                if (C2213b.this.mAudioFocus) {
                    FuncAdapter.UnLock(C2213b.this.mContext, Boolean.valueOf(C2213b.this.mRequestFocus), C2213b.this.afChangeListener);
                } else {
                    FuncAdapter.UnLock(C2213b.this.mContext, Boolean.valueOf(C2213b.this.mRequestFocus), null);
                }
                C2213b.this.mThread = null;
                LogUtil.m562d(C2213b.TAG, "player stopped");
            } catch (Throwable th2) {
                synchronized (C2213b.this.mSyncObj) {
                    C2213b.this.mPlaytate = 4;
                    if (C2213b.this.mAudioTrack != null) {
                        C2213b.this.mAudioTrack.release();
                        C2213b.this.mAudioTrack = null;
                    }
                    if (C2213b.this.mAudioFocus) {
                        FuncAdapter.UnLock(C2213b.this.mContext, Boolean.valueOf(C2213b.this.mRequestFocus), C2213b.this.afChangeListener);
                    } else {
                        FuncAdapter.UnLock(C2213b.this.mContext, Boolean.valueOf(C2213b.this.mRequestFocus), null);
                    }
                    C2213b.this.mThread = null;
                    LogUtil.m562d(C2213b.TAG, "player stopped");
                    throw th2;
                }
            }
        }
    }

    public C2213b(Context context) {
        this.mAudioTrack = null;
        this.mBuffer = null;
        this.mContext = null;
        this.mThread = null;
        this.listener = null;
        this.mPlaytate = 0;
        this.mAudioFocus = true;
        this.mStreamType = 3;
        this.mRequestFocus = false;
        this.mChangeListenerFlag = false;
        this.mAudioLock = new Object();
        this.mSyncObj = this;
        this.BYTES_OF_PER_SAMPLE = 2;
        this.FADE_TIME = HttpStatus.SC_INTERNAL_SERVER_ERROR;
        this.FADE_PERIOD = 50;
        this.mPerPlaySize = 1600;
        this.MAX_VOL = 1.0f;
        this.MIN_VOL = 0.0f;
        this.PER = 0.1f;
        this.mFadingSize = 16000;
        this.mCurVol = 0.0f;
        this.mTargetVol = 1.0f;
        this.mCurFadingPeriod = 0.1f;
        this.mFading = false;
        this.isExist = new AtomicBoolean(true);
        this.mBufferingFadingEnable = false;
        this.mFadingEnable = false;
        ReentrantLock reentrantLock = new ReentrantLock();
        this.mEndLock = reentrantLock;
        this.mEndCondition = reentrantLock.newCondition();
        this.afChangeListener = new a();
        this.mCurEndPos = 0;
        this.mUihandler = new b(Looper.getMainLooper());
        this.mContext = context;
    }

    public C2213b(Context context, int i, boolean z, boolean z2, boolean z3) {
        this.mAudioTrack = null;
        this.mBuffer = null;
        this.mContext = null;
        this.mThread = null;
        this.listener = null;
        this.mPlaytate = 0;
        this.mAudioFocus = true;
        this.mStreamType = 3;
        this.mRequestFocus = false;
        this.mChangeListenerFlag = false;
        this.mAudioLock = new Object();
        this.mSyncObj = this;
        this.BYTES_OF_PER_SAMPLE = 2;
        this.FADE_TIME = HttpStatus.SC_INTERNAL_SERVER_ERROR;
        this.FADE_PERIOD = 50;
        this.mPerPlaySize = 1600;
        this.MAX_VOL = 1.0f;
        this.MIN_VOL = 0.0f;
        this.PER = 0.1f;
        this.mFadingSize = 16000;
        this.mCurVol = 0.0f;
        this.mTargetVol = 1.0f;
        this.mCurFadingPeriod = 0.1f;
        this.mFading = false;
        this.isExist = new AtomicBoolean(true);
        this.mBufferingFadingEnable = false;
        this.mFadingEnable = false;
        ReentrantLock reentrantLock = new ReentrantLock();
        this.mEndLock = reentrantLock;
        this.mEndCondition = reentrantLock.newCondition();
        this.afChangeListener = new a();
        this.mCurEndPos = 0;
        this.mUihandler = new b(Looper.getMainLooper());
        this.mContext = context;
        this.mStreamType = i;
        this.mRequestFocus = z;
        this.mFadingEnable = z2;
        this.mBufferingFadingEnable = z3;
    }

    private void createAudio() throws Exception {
        LogUtil.m562d(TAG, "createAudio start");
        int rate = this.mBuffer.getRate();
        this.mBufferSize = AudioTrack.getMinBufferSize(rate, 2, 2);
        int i = rate / 1000;
        this.mPerPlaySize = i * 100;
        this.mFadingSize = i * 1000;
        if (this.mAudioTrack != null) {
            release();
        }
        LogUtil.m562d(TAG, "createAudio || mStreamType = " + this.mStreamType + ", buffer size: " + this.mBufferSize);
        this.mAudioTrack = new AudioTrack(this.mStreamType, rate, 2, 2, this.mBufferSize * 2, 1);
        this.mBuffer.setAudioTrackBuffSize(this.mBufferSize * 2);
        int i2 = this.mBufferSize;
        if (i2 == -2 || i2 == -1) {
            throw new Exception();
        }
        LogUtil.m562d(TAG, "createAudio end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onError(SpeechError speechError) {
        InterfaceC2214c interfaceC2214c = this.listener;
        if (interfaceC2214c != null) {
            interfaceC2214c.onError(speechError);
            this.listener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPause() {
        InterfaceC2214c interfaceC2214c = this.listener;
        if (interfaceC2214c != null) {
            interfaceC2214c.onPause();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPercent(int i, int i2) {
        if (this.listener == null || this.mPlaytate == 4) {
            return;
        }
        this.listener.onPercent(i, i2, this.mCurEndPos);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onResume() {
        InterfaceC2214c interfaceC2214c = this.listener;
        if (interfaceC2214c != null) {
            interfaceC2214c.onResume();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStop() {
        InterfaceC2214c interfaceC2214c = this.listener;
        if (interfaceC2214c != null) {
            interfaceC2214c.onStop();
            this.listener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepAudioPlayer() throws Exception {
        d dVar = this.mThread;
        if (this.mAudioTrack == null || !(dVar == null || dVar.m538a() == this.mStreamType)) {
            LogUtil.m562d(TAG, "prepAudioPlayer || audiotrack is null or stream type is change.");
            createAudio();
            if (dVar != null) {
                dVar.m539a(this.mStreamType);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setState(int i, int i2) {
        boolean z;
        synchronized (this.mSyncObj) {
            if (i == this.mPlaytate) {
                this.mPlaytate = i2;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public void fading() {
        if (!this.mFadingEnable) {
            this.mFading = false;
            return;
        }
        synchronized (this.mSyncObj) {
            if (Math.abs(this.mTargetVol - this.mCurVol) < 0.1f) {
                this.mCurVol = this.mTargetVol;
                this.mFading = false;
                LogUtil.m561d("fading finish");
            } else {
                this.mCurVol += this.mCurFadingPeriod;
            }
        }
        AudioTrack audioTrack = this.mAudioTrack;
        float f = this.mCurVol;
        audioTrack.setStereoVolume(f, f);
    }

    public int getState() {
        return this.mPlaytate;
    }

    public boolean pause() {
        if (this.mPlaytate == 4 || this.mPlaytate == 3) {
            return false;
        }
        LogUtil.m561d("pause start fade out");
        startFadeOut();
        this.mPlaytate = 3;
        return true;
    }

    public boolean play(C2218a c2218a) {
        boolean z;
        LogUtil.m562d(TAG, "play mPlaytate= " + this.mPlaytate + ",mAudioFocus= " + this.mAudioFocus);
        synchronized (this.mSyncObj) {
            z = false;
            if (this.mPlaytate == 4 || this.mPlaytate == 0 || this.mPlaytate == 3 || this.mThread == null) {
                if (this.mThread == null) {
                    this.mThread = new d(this, null);
                    this.mPlaytate = 0;
                }
                this.mBuffer = c2218a;
                this.isExist.set(false);
                this.mThread.start();
                z = true;
            }
        }
        return z;
    }

    public boolean rePlay(C2218a c2218a) {
        setState(0);
        return play(c2218a);
    }

    public boolean reSetStatus() {
        setState(0);
        return true;
    }

    public void release() {
        synchronized (this.mAudioLock) {
            AudioTrack audioTrack = this.mAudioTrack;
            if (audioTrack != null) {
                if (audioTrack.getPlayState() == 3) {
                    this.mAudioTrack.stop();
                }
                this.mAudioTrack.release();
                this.mAudioTrack = null;
            }
            LogUtil.m562d(TAG, "mAudioTrack released");
        }
    }

    public boolean resume() {
        boolean state = setState(3, 2);
        FuncAdapter.Lock(this.mContext, Boolean.valueOf(this.mRequestFocus), this.afChangeListener);
        if (state) {
            LogUtil.m561d("resume start fade in");
            Message.obtain(this.mUihandler, 2).sendToTarget();
            startFadeIn();
        }
        return state;
    }

    public void setAudioFocus(boolean z) {
        LogUtil.m562d(TAG, "setAudioFocus " + z);
        this.mAudioFocus = z;
    }

    public void setListener(PcmPlayer.PcmPlayerListener pcmPlayerListener) {
        setListener(new c(pcmPlayerListener));
    }

    public void setListener(InterfaceC2214c interfaceC2214c) {
        this.listener = interfaceC2214c;
    }

    public void setSilence() {
        LogUtil.m561d("fading set silence");
        synchronized (this.mSyncObj) {
            if (Math.abs(0.0f - this.mTargetVol) < 0.1f) {
                this.mCurVol = 0.0f;
                this.mFading = false;
            }
        }
        AudioTrack audioTrack = this.mAudioTrack;
        float f = this.mCurVol;
        audioTrack.setStereoVolume(f, f);
    }

    protected void setState(int i) {
        this.mPlaytate = i;
    }

    public void setStreamType(int i) {
        LogUtil.m562d(TAG, "setmStreamType || streamType = " + i);
        this.mStreamType = i;
    }

    public void startFadeIn() {
        if (this.mFadingEnable) {
            synchronized (this.mSyncObj) {
                LogUtil.m561d("start fade in");
                this.mFading = true;
                this.mTargetVol = 1.0f;
                this.mCurFadingPeriod = 0.1f;
            }
        }
    }

    public void startFadeOut() {
        if (this.mFadingEnable) {
            synchronized (this.mSyncObj) {
                LogUtil.m561d("start fade out");
                this.mFading = true;
                this.mTargetVol = 0.0f;
                this.mCurFadingPeriod = -0.1f;
            }
        }
    }

    public boolean stop() {
        if (4 != this.mPlaytate) {
            LogUtil.m561d("stop start fade out");
            startFadeOut();
        }
        synchronized (this.mSyncObj) {
            this.mPlaytate = 4;
        }
        return true;
    }
}
