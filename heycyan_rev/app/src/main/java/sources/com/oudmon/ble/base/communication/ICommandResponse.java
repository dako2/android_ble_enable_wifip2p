package com.oudmon.ble.base.communication;

import com.oudmon.ble.base.communication.rsp.BaseRspCmd;

/* loaded from: classes2.dex */
public interface ICommandResponse<T extends BaseRspCmd> {
    void onDataResponse(T t);
}
