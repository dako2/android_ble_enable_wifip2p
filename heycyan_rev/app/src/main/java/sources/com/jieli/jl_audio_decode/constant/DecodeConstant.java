package com.jieli.jl_audio_decode.constant;

import com.jieli.jl_audio_decode.BuildConfig;

/* loaded from: classes2.dex */
public class DecodeConstant {
    public static final int DATA_TYPE_OPUS = 2;
    public static final int DATA_TYPE_PCM = 0;
    public static final int DATA_TYPE_SPX = 1;
    public static final String JL_OPUS_LIB = "jl_opus";
    public static final String JL_SPEEX_LIB = "jl_speex";
    public static final int STATE_ERROR = 2;
    public static final int STATE_IDLE = 0;
    public static final int STATE_WORKING = 1;
    public static final int TYPE_DECODE_FILE = 1;
    public static final int TYPE_DECODE_STREAM = 3;
    public static final int TYPE_ENCODE_FILE = 2;
    public static final int TYPE_ENCODE_STREAM = 4;

    public static int getLibVersionCode() {
        return 20004;
    }

    public static String getLibVersionName() {
        return BuildConfig.VERSION_NAME;
    }
}
