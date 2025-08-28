package com.oudmon.ble.base.communication.bigData.resp;

/* loaded from: classes2.dex */
public class VolumeControlResponse extends BaseResponse {
    private int currVolumeCall;
    private int currVolumeMusic;
    private int currVolumeSystem;
    private int currVolumeType;
    private int maxVolumeCall;
    private int maxVolumeMusic;
    private int maxVolumeSystem;
    private int minVolumeCall;
    private int minVolumeMusic;
    private int minVolumeSystem;
    private byte[] subData;

    @Override // com.oudmon.ble.base.communication.bigData.resp.BaseResponse
    public boolean acceptData(byte[] bArr) {
        this.subData = bArr;
        this.minVolumeMusic = bArr[8];
        this.maxVolumeMusic = bArr[9];
        this.currVolumeMusic = bArr[10];
        this.minVolumeCall = bArr[12];
        this.maxVolumeCall = bArr[13];
        this.currVolumeCall = bArr[14];
        this.minVolumeSystem = bArr[16];
        this.maxVolumeSystem = bArr[17];
        this.currVolumeSystem = bArr[18];
        this.currVolumeType = bArr[19];
        return false;
    }

    public byte[] getSubData() {
        return this.subData;
    }

    public int getCurrVolumeType() {
        return this.currVolumeType;
    }

    public int getMinVolumeMusic() {
        return this.minVolumeMusic;
    }

    public int getMaxVolumeMusic() {
        return this.maxVolumeMusic;
    }

    public int getCurrVolumeMusic() {
        return this.currVolumeMusic;
    }

    public int getMinVolumeSystem() {
        return this.minVolumeSystem;
    }

    public int getMaxVolumeSystem() {
        return this.maxVolumeSystem;
    }

    public int getCurrVolumeSystem() {
        return this.currVolumeSystem;
    }

    public int getMinVolumeCall() {
        return this.minVolumeCall;
    }

    public int getMaxVolumeCall() {
        return this.maxVolumeCall;
    }

    public int getCurrVolumeCall() {
        return this.currVolumeCall;
    }
}
