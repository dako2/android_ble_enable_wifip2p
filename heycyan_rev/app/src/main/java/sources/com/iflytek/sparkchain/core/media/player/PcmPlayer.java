package com.iflytek.sparkchain.core.media.player;

import android.content.Context;
import com.iflytek.sparkchain.core.media.speech.SpeechError;
import com.iflytek.sparkchain.media.player.C2213b;
import com.iflytek.sparkchain.media.record.C2218a;

@Deprecated
/* loaded from: classes2.dex */
public class PcmPlayer extends C2213b {

    public interface PcmPlayerListener {
        void onError(SpeechError speechError);

        void onPaused();

        void onPercent(int i, int i2, int i3);

        void onResume();

        void onStoped();
    }

    public PcmPlayer(Context context) {
        super(context);
    }

    public PcmPlayer(Context context, int i, boolean z, boolean z2, boolean z3) {
        super(context, i, z, z2, z3);
    }

    public boolean play(C2218a c2218a, PcmPlayerListener pcmPlayerListener) {
        setListener(pcmPlayerListener);
        return play(c2218a);
    }

    public boolean rePlay(C2218a c2218a, PcmPlayerListener pcmPlayerListener) {
        super.setState(0);
        return play(c2218a, pcmPlayerListener);
    }
}
