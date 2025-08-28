package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes2.dex */
public class StopHeartRateReq extends BaseReqCmd {
    private byte[] data;

    private StopHeartRateReq(byte b, byte b2, byte b3) {
        super(Constants.CMD_STOP_HEART_RATE);
        this.data = new byte[]{b, b2, b3};
    }

    public static StopHeartRateReq stopHeartRate(byte b) {
        return new StopHeartRateReq((byte) 1, b, (byte) 0);
    }

    public static StopHeartRateReq stopBloodPressure(byte b, byte b2) {
        return new StopHeartRateReq((byte) 2, b, b2);
    }

    public static StopHeartRateReq stopBloodOxygen(byte b) {
        return new StopHeartRateReq((byte) 3, b, (byte) 0);
    }

    public static StopHeartRateReq stopFatigue(byte b) {
        return new StopHeartRateReq((byte) 4, b, (byte) 0);
    }

    public static StopHeartRateReq stopHealthCheck() {
        return new StopHeartRateReq((byte) 5, (byte) 0, (byte) 0);
    }

    public static StopHeartRateReq stopTemperatureCheck() {
        return new StopHeartRateReq((byte) 11, (byte) 0, (byte) 0);
    }

    public static StopHeartRateReq stopEcg(int i) {
        return new StopHeartRateReq((byte) 7, (byte) i, (byte) 0);
    }

    public static StopHeartRateReq stopPressure(byte b) {
        return new StopHeartRateReq((byte) 8, b, (byte) 0);
    }

    public static StopHeartRateReq stopBloodSugar(byte b) {
        return new StopHeartRateReq((byte) 9, b, (byte) 0);
    }

    public static StopHeartRateReq stopHrv(byte b) {
        return new StopHeartRateReq((byte) 10, b, (byte) 0);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.data;
    }
}
