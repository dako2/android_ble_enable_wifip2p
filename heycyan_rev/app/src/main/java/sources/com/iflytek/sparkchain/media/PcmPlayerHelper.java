package com.iflytek.sparkchain.media;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import com.iflytek.sparkchain.core.media.player.PcmPlayer;
import com.iflytek.sparkchain.media.player.C2213b;
import com.iflytek.sparkchain.media.player.InterfaceC2214c;
import com.iflytek.sparkchain.media.record.C2218a;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class PcmPlayerHelper implements MediaPlayerHelper, PcmPlayerWriter {
    private Context mContext;
    private C2213b mPlayer = null;
    private C2218a mBuffer = null;
    private AtomicBoolean isInit = new AtomicBoolean(false);
    private AtomicInteger totalPercent = new AtomicInteger(100);

    private static class Holder {
        private static PcmPlayerHelper instance = new PcmPlayerHelper();

        private Holder() {
        }
    }

    protected PcmPlayerHelper() {
    }

    private void doInitPlayer(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        if (this.mPlayer == null) {
            this.mPlayer = new C2213b(applicationContext, 3, true, false, false);
        }
        this.isInit.set(true);
    }

    public static PcmPlayerHelper getInst() {
        return Holder.instance;
    }

    @Override // com.iflytek.sparkchain.media.MediaPlayerHelper
    public void init(Context context, InterfaceC2214c interfaceC2214c) {
        doInitPlayer(context);
        this.mPlayer.setListener(interfaceC2214c);
    }

    @Deprecated
    public void initPlayer(Context context) {
        doInitPlayer(context);
    }

    public boolean isInit() {
        return this.isInit.get();
    }

    @Override // com.iflytek.sparkchain.media.MediaPlayerHelper
    public boolean isPlaying() {
        C2213b c2213b = this.mPlayer;
        return c2213b != null && c2213b.getState() == 2;
    }

    @Override // com.iflytek.sparkchain.media.MediaPlayerHelper
    public void pause() {
        C2213b c2213b = this.mPlayer;
        if (c2213b != null) {
            c2213b.pause();
        }
    }

    @Override // com.iflytek.sparkchain.media.MediaPlayerHelper
    public void play() {
        C2213b c2213b = this.mPlayer;
        if (c2213b == null) {
            return;
        }
        if (c2213b.getState() == 2 || this.mPlayer.getState() == 3) {
            this.mPlayer.stop();
            SystemClock.sleep(200L);
        }
        try {
            this.mPlayer.play(this.mBuffer);
        } catch (IllegalThreadStateException e) {
            Log.w("AEE", "play:" + e.toString());
        }
    }

    @Override // com.iflytek.sparkchain.media.MediaPlayerHelper
    public void release() {
        this.mContext = null;
        C2213b c2213b = this.mPlayer;
        if (c2213b != null) {
            c2213b.stop();
            this.mPlayer.release();
        }
        this.mPlayer = null;
        this.mBuffer = null;
        this.isInit.set(false);
    }

    public void resetBuffer(int i) {
        if (this.mPlayer == null) {
            return;
        }
        C2218a c2218a = this.mBuffer;
        if (c2218a == null) {
            this.mBuffer = new C2218a(this.mContext, 16000, i <= 0 ? 1 : i, null, 100);
        } else {
            c2218a.reset(this.mContext, 16000, i <= 0 ? 1 : i, null, 100);
            this.mBuffer.deleteFile();
        }
    }

    public void resetPercent(int i) {
        if (this.isInit.get()) {
            this.totalPercent.set(i);
        }
    }

    @Override // com.iflytek.sparkchain.media.MediaPlayerHelper
    public void resume() {
        C2213b c2213b = this.mPlayer;
        if (c2213b != null) {
            c2213b.resume();
        }
    }

    public void setLocalDataSize(int i) {
        resetBuffer(1);
        this.mBuffer.setMaxFileSize(i);
    }

    @Deprecated
    public synchronized int startPlay(PcmPlayer.PcmPlayerListener pcmPlayerListener) {
        C2213b c2213b = this.mPlayer;
        if (c2213b == null) {
            return -1;
        }
        c2213b.setListener(pcmPlayerListener);
        play();
        return 0;
    }

    @Override // com.iflytek.sparkchain.media.MediaPlayerHelper
    public void stop() {
        C2213b c2213b = this.mPlayer;
        if (c2213b != null) {
            c2213b.stop();
        }
        resetBuffer(0);
    }

    public boolean write(ArrayList<byte[]> arrayList, int i, int i2) {
        C2218a c2218a = this.mBuffer;
        if (c2218a == null) {
            return false;
        }
        return c2218a.writeStream(arrayList, this.totalPercent.get(), i, i2);
    }

    @Override // com.iflytek.sparkchain.media.PcmPlayerWriter
    public boolean write(byte[] bArr, int i, int i2) {
        if (this.mBuffer == null) {
            return false;
        }
        ArrayList<byte[]> arrayList = new ArrayList<>();
        arrayList.add(bArr);
        return this.mBuffer.writeStream(arrayList, this.totalPercent.get(), i, i2);
    }
}
