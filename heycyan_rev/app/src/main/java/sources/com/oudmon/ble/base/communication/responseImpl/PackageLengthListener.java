package com.oudmon.ble.base.communication.responseImpl;

import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.JPackageManager;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.rsp.PackageLengthRsp;

/* loaded from: classes2.dex */
public class PackageLengthListener implements ICommandResponse<PackageLengthRsp> {
    @Override // com.oudmon.ble.base.communication.ICommandResponse
    public void onDataResponse(PackageLengthRsp packageLengthRsp) {
        XLog.m137i("GpsOnlineListener.. onDataResponse.. mData: " + packageLengthRsp.mData);
        JPackageManager.getInstance().setLength(Math.max(packageLengthRsp.mData, 244));
        LargeDataHandler.getInstance().packageLength();
    }
}
