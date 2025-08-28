package com.iflytek.sparkchain.media;

import android.content.Context;
import com.iflytek.sparkchain.media.player.C2212a;
import com.iflytek.sparkchain.media.player.InterfaceC2214c;
import com.iflytek.sparkchain.media.player.SimplePlayer;
import com.iflytek.sparkchain.media.speech.SpeechError;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: classes2.dex */
public class SimplePlayerHelper implements MediaPlayerHelper, SimplePlayerWriter {
    private SimplePlayer simplePlayer = null;

    private static class Holder {
        private static final SimplePlayerHelper instance = new SimplePlayerHelper();

        private Holder() {
        }
    }

    protected SimplePlayerHelper() {
    }

    private C2212a getAudioParam() {
        C2212a c2212a = new C2212a();
        c2212a.f491a = 16000;
        c2212a.f492b = 2;
        c2212a.f493c = 2;
        return c2212a;
    }

    public static SimplePlayerHelper getInst() {
        return Holder.instance;
    }

    private byte[] getPCMData(String str) throws IOException {
        File file = new File(str);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[(int) file.length()];
            try {
                fileInputStream.read(bArr);
                return bArr;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.iflytek.sparkchain.media.MediaPlayerHelper
    public void init(Context context, InterfaceC2214c interfaceC2214c) {
        SimplePlayer simplePlayer = this.simplePlayer;
        if (simplePlayer != null) {
            simplePlayer.stop();
            this.simplePlayer = null;
        }
        this.simplePlayer = new SimplePlayer(interfaceC2214c);
        this.simplePlayer.setAudioParam(getAudioParam());
    }

    @Deprecated
    public void init(String str, SimplePlayer.PlayerListener playerListener) {
        init(getPCMData(str), playerListener);
    }

    @Deprecated
    public void init(byte[] bArr, final SimplePlayer.PlayerListener playerListener) {
        init((Context) null, new InterfaceC2214c() { // from class: com.iflytek.sparkchain.media.SimplePlayerHelper.1
            @Override // com.iflytek.sparkchain.media.player.InterfaceC2214c
            public void onError(SpeechError speechError) {
                playerListener.onError(speechError.getErrorCode());
            }

            public void onFinish() {
                playerListener.onCompleted();
            }

            @Override // com.iflytek.sparkchain.media.player.InterfaceC2214c
            public void onPause() {
                playerListener.onPause();
            }

            @Override // com.iflytek.sparkchain.media.player.InterfaceC2214c
            public void onPercent(int i, int i2, int i3) {
            }

            @Override // com.iflytek.sparkchain.media.player.InterfaceC2214c
            public void onResume() {
            }

            public void onStart() {
                playerListener.onStart();
            }

            @Override // com.iflytek.sparkchain.media.player.InterfaceC2214c
            public void onStop() {
                playerListener.onStop();
            }
        });
        write(bArr);
    }

    @Override // com.iflytek.sparkchain.media.MediaPlayerHelper
    public boolean isPlaying() {
        return false;
    }

    @Override // com.iflytek.sparkchain.media.MediaPlayerHelper
    public void pause() {
        SimplePlayer simplePlayer = this.simplePlayer;
        if (simplePlayer != null) {
            simplePlayer.pause();
        }
    }

    @Override // com.iflytek.sparkchain.media.MediaPlayerHelper
    public void play() {
        SimplePlayer simplePlayer = this.simplePlayer;
        if (simplePlayer != null) {
            simplePlayer.play();
        }
    }

    @Override // com.iflytek.sparkchain.media.MediaPlayerHelper
    public void release() {
        SimplePlayer simplePlayer = this.simplePlayer;
        if (simplePlayer != null) {
            simplePlayer.release();
        }
    }

    @Override // com.iflytek.sparkchain.media.MediaPlayerHelper
    public void resume() {
        SimplePlayer simplePlayer = this.simplePlayer;
        if (simplePlayer != null) {
            simplePlayer.resume();
        }
    }

    @Override // com.iflytek.sparkchain.media.MediaPlayerHelper
    public void stop() {
        SimplePlayer simplePlayer = this.simplePlayer;
        if (simplePlayer != null) {
            simplePlayer.stop();
        }
    }

    @Override // com.iflytek.sparkchain.media.SimplePlayerWriter
    public boolean write(String str) {
        return write(getPCMData(str));
    }

    @Override // com.iflytek.sparkchain.media.SimplePlayerWriter
    public boolean write(byte[] bArr) {
        this.simplePlayer.setDataSource(bArr);
        this.simplePlayer.prepare();
        return true;
    }
}
