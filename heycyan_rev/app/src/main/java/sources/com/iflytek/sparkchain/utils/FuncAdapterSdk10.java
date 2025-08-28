package com.iflytek.sparkchain.utils;

import android.content.Context;
import android.media.AudioManager;
import android.view.View;
import com.iflytek.sparkchain.utils.log.LogUtil;

/* loaded from: classes2.dex */
public class FuncAdapterSdk10 {
    private static int avoidValue;

    public static void CloseHardWareAccelerate(View view) {
        view.setLayerType(1, null);
    }

    public static boolean Lock(Context context, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        try {
            LogUtil.m561d("start request music_stream focus");
            ((AudioManager) context.getSystemService("audio")).requestAudioFocus(onAudioFocusChangeListener, 3, 2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean UnLock(Context context, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        try {
            LogUtil.m561d("start abandon audio focus");
            ((AudioManager) context.getSystemService("audio")).abandonAudioFocus(onAudioFocusChangeListener);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public void avoidSystemError(int i) {
        LogUtil.m561d("avoidSystemError");
        avoidValue = i;
    }
}
