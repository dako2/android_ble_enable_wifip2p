package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes2.dex */
public class DeviceSupportFunctionRsp extends BaseRspCmd {
    public boolean supportAPPRevision;
    public boolean supportBlePair;
    public boolean supportDrink;
    public boolean supportGesture;
    public boolean supportHeart;
    public boolean supportLongSit;
    public boolean supportMoslin;
    public boolean supportRingCamera;
    public boolean supportRingEbook;
    public boolean supportRingGame;
    public boolean supportRingMusic;
    public boolean supportRingPhoneCall;
    public boolean supportRingVideo;
    public boolean supportTouch;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        byte b = bArr[1];
        this.supportTouch = (b & 1) != 0;
        this.supportMoslin = (b & 2) != 0;
        this.supportAPPRevision = (b & 4) != 0;
        this.supportBlePair = (b & 8) != 0;
        this.supportGesture = (b & 128) != 0;
        byte b2 = bArr[2];
        this.supportRingMusic = (b2 & 1) != 0;
        this.supportRingVideo = (b2 & 2) != 0;
        this.supportRingEbook = (b2 & 4) != 0;
        this.supportRingCamera = (b2 & 8) != 0;
        this.supportRingPhoneCall = (b2 & 16) != 0;
        this.supportRingGame = (b2 & 32) != 0;
        this.supportHeart = (b2 & 64) != 0;
        byte b3 = bArr[4];
        this.supportLongSit = (b3 & 4) != 0;
        this.supportDrink = (b3 & 8) != 0;
        return false;
    }
}
