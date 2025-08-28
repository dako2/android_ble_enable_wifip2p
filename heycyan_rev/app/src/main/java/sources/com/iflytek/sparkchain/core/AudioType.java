package com.iflytek.sparkchain.core;

@Deprecated
/* loaded from: classes2.dex */
public enum AudioType {
    MP3("mp3"),
    WAV("wav"),
    AAC("aac"),
    PCM("pcm");


    /* renamed from: a */
    private final String f434a;

    AudioType(String str) {
        this.f434a = str;
    }

    public String getValue() {
        return this.f434a;
    }
}
