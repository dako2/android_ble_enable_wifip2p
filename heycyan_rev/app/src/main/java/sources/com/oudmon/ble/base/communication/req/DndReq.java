package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.entity.StartEndTimeEntity;

/* loaded from: classes2.dex */
public class DndReq extends MixtureReq {
    private DndReq() {
        super((byte) 6);
        this.subData = new byte[]{1};
    }

    private DndReq(boolean z, StartEndTimeEntity startEndTimeEntity) {
        super((byte) 6);
        this.subData = new byte[]{2, (byte) (z ? 1 : 2), (byte) (startEndTimeEntity.getStartHour() & 255), (byte) (startEndTimeEntity.getStartMinute() & 255), (byte) (startEndTimeEntity.getEndHour() & 255), (byte) (startEndTimeEntity.getEndMinute() & 255)};
    }

    public static DndReq getReadInstance() {
        return new DndReq();
    }

    public static DndReq getWriteInstance(boolean z, StartEndTimeEntity startEndTimeEntity) {
        return new DndReq(z, startEndTimeEntity);
    }
}
