package com.glasssutdio.wear.database.dao;

import com.glasssutdio.wear.database.entity.DeviceSettingEntity;
import kotlin.Metadata;

/* compiled from: GlassDeviceSettingDao.kt */
@Metadata(m606d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H'¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/database/dao/GlassDeviceSettingDao;", "Lcom/glasssutdio/wear/database/dao/BaseDao;", "Lcom/glasssutdio/wear/database/entity/DeviceSettingEntity;", "deleteDataWhereMacNotNull", "", "mac", "", "queryByMacAndAction", "action", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public interface GlassDeviceSettingDao extends BaseDao<DeviceSettingEntity> {
    void deleteDataWhereMacNotNull(String mac);

    DeviceSettingEntity queryByMacAndAction(String mac, String action);
}
