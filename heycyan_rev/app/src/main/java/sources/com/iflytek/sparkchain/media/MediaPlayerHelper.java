package com.iflytek.sparkchain.media;

import android.content.Context;
import com.iflytek.sparkchain.media.player.InterfaceC2214c;

/* loaded from: classes2.dex */
public interface MediaPlayerHelper {
    void init(Context context, InterfaceC2214c interfaceC2214c);

    boolean isPlaying();

    void pause();

    void play();

    void release();

    void resume();

    void stop();
}
