package com.glasssutdio.wear.database.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p002db.SupportSQLiteStatement;
import com.glasssutdio.wear.database.entity.DeviceSettingEntity;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class GlassDeviceSettingDao_Impl implements GlassDeviceSettingDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<DeviceSettingEntity> __deletionAdapterOfDeviceSettingEntity;
    private final EntityInsertionAdapter<DeviceSettingEntity> __insertionAdapterOfDeviceSettingEntity;
    private final SharedSQLiteStatement __preparedStmtOfDeleteDataWhereMacNotNull;
    private final EntityDeletionOrUpdateAdapter<DeviceSettingEntity> __updateAdapterOfDeviceSettingEntity;

    public GlassDeviceSettingDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfDeviceSettingEntity = new EntityInsertionAdapter<DeviceSettingEntity>(__db) { // from class: com.glasssutdio.wear.database.dao.GlassDeviceSettingDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `device_setting` (`mac`,`setting_action`,`content`) VALUES (?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(final SupportSQLiteStatement statement, final DeviceSettingEntity entity) {
                statement.bindString(1, entity.getMac());
                statement.bindString(2, entity.getSettingAction());
                statement.bindString(3, entity.getContent());
            }
        };
        this.__deletionAdapterOfDeviceSettingEntity = new EntityDeletionOrUpdateAdapter<DeviceSettingEntity>(__db) { // from class: com.glasssutdio.wear.database.dao.GlassDeviceSettingDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "DELETE FROM `device_setting` WHERE `mac` = ? AND `setting_action` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(final SupportSQLiteStatement statement, final DeviceSettingEntity entity) {
                statement.bindString(1, entity.getMac());
                statement.bindString(2, entity.getSettingAction());
            }
        };
        this.__updateAdapterOfDeviceSettingEntity = new EntityDeletionOrUpdateAdapter<DeviceSettingEntity>(__db) { // from class: com.glasssutdio.wear.database.dao.GlassDeviceSettingDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "UPDATE OR ABORT `device_setting` SET `mac` = ?,`setting_action` = ?,`content` = ? WHERE `mac` = ? AND `setting_action` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(final SupportSQLiteStatement statement, final DeviceSettingEntity entity) {
                statement.bindString(1, entity.getMac());
                statement.bindString(2, entity.getSettingAction());
                statement.bindString(3, entity.getContent());
                statement.bindString(4, entity.getMac());
                statement.bindString(5, entity.getSettingAction());
            }
        };
        this.__preparedStmtOfDeleteDataWhereMacNotNull = new SharedSQLiteStatement(__db) { // from class: com.glasssutdio.wear.database.dao.GlassDeviceSettingDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM device_setting where mac =? ";
            }
        };
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void insertAll(final List<DeviceSettingEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfDeviceSettingEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void insert(final DeviceSettingEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfDeviceSettingEntity.insert((EntityInsertionAdapter<DeviceSettingEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void delete(final DeviceSettingEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfDeviceSettingEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void deleteList(final List<DeviceSettingEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfDeviceSettingEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void deleteSome(final DeviceSettingEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfDeviceSettingEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void update(final DeviceSettingEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfDeviceSettingEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.GlassDeviceSettingDao
    public void deleteDataWhereMacNotNull(final String mac) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteDataWhereMacNotNull.acquire();
        supportSQLiteStatementAcquire.bindString(1, mac);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfDeleteDataWhereMacNotNull.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.glasssutdio.wear.database.dao.GlassDeviceSettingDao
    public DeviceSettingEntity queryByMacAndAction(final String mac, final String action) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * from device_setting where mac= ? and setting_action=?", 2);
        roomSQLiteQueryAcquire.bindString(1, mac);
        roomSQLiteQueryAcquire.bindString(2, action);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? new DeviceSettingEntity(cursorQuery.getString(CursorUtil.getColumnIndexOrThrow(cursorQuery, "mac")), cursorQuery.getString(CursorUtil.getColumnIndexOrThrow(cursorQuery, "setting_action")), cursorQuery.getString(CursorUtil.getColumnIndexOrThrow(cursorQuery, "content"))) : null;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
