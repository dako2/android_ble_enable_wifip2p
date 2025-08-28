package com.jieli.jl_audio_decode.opus.model;

/* loaded from: classes2.dex */
public class OpusOption {
    private boolean hasHead;
    private int channel = 1;
    private int sampleRate = 16000;
    private int packetSize = 40;

    public boolean isHasHead() {
        return this.hasHead;
    }

    public OpusOption setHasHead(boolean z) {
        this.hasHead = z;
        return this;
    }

    public int getChannel() {
        return this.channel;
    }

    public OpusOption setChannel(int i) {
        if (i == 1 || i == 2) {
            this.channel = i;
        }
        return this;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public OpusOption setSampleRate(int i) {
        if (i == 8000 || i == 16000 || i == 32000 || i == 48000) {
            this.sampleRate = i;
        }
        return this;
    }

    public int getPacketSize() {
        return this.packetSize;
    }

    public OpusOption setPacketSize(int i) {
        if (i > 0) {
            this.packetSize = i;
        }
        return this;
    }

    public String toString() {
        return "OpusOption{hasHead=" + this.hasHead + ", channel=" + this.channel + ", sampleRate=" + this.sampleRate + ", packetSize=" + this.packetSize + '}';
    }
}
