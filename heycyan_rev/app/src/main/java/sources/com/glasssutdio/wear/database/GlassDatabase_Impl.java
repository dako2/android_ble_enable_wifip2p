package com.glasssutdio.wear.database;

import android.database.SQLException;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.p002db.SupportSQLiteDatabase;
import androidx.sqlite.p002db.SupportSQLiteOpenHelper;
import com.glasssutdio.wear.database.dao.GlassAiChatDao;
import com.glasssutdio.wear.database.dao.GlassAiChatDao_Impl;
import com.glasssutdio.wear.database.dao.GlassAlbumDao;
import com.glasssutdio.wear.database.dao.GlassAlbumDao_Impl;
import com.glasssutdio.wear.database.dao.GlassDeviceSettingDao;
import com.glasssutdio.wear.database.dao.GlassDeviceSettingDao_Impl;
import com.glasssutdio.wear.database.dao.TranslateDao;
import com.glasssutdio.wear.database.dao.TranslateDao_Impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class GlassDatabase_Impl extends GlassDatabase {
    private volatile GlassAiChatDao _glassAiChatDao;
    private volatile GlassAlbumDao _glassAlbumDao;
    private volatile GlassDeviceSettingDao _glassDeviceSettingDao;
    private volatile TranslateDao _translateDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(final DatabaseConfiguration config) {
        return config.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(new RoomOpenHelper(config, new RoomOpenHelper.Delegate(10) { // from class: com.glasssutdio.wear.database.GlassDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(final SupportSQLiteDatabase db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(final SupportSQLiteDatabase db) throws SQLException {
                db.execSQL("CREATE TABLE IF NOT EXISTS `glass_album` (`file_name` TEXT NOT NULL, `mac` TEXT NOT NULL, `file_path` TEXT NOT NULL, `video_first_frame` TEXT NOT NULL, `file_type` INTEGER NOT NULL, `video_length` INTEGER NOT NULL, `file_date` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `h_c` INTEGER NOT NULL, `user_like` INTEGER NOT NULL, `eis_in_progress` INTEGER NOT NULL, PRIMARY KEY(`file_name`, `mac`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `device_setting` (`mac` TEXT NOT NULL, `setting_action` TEXT NOT NULL, `content` TEXT NOT NULL, PRIMARY KEY(`mac`, `setting_action`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `ai_chat_entity` (`uid` INTEGER NOT NULL, `role` TEXT NOT NULL, `content` TEXT NOT NULL, `chat_type` INTEGER NOT NULL, `file_path` TEXT NOT NULL, `local_file_path` TEXT NOT NULL, `chat_timestamp` INTEGER NOT NULL, `uploaded` INTEGER NOT NULL, `is_like` INTEGER NOT NULL, `is_unlike` INTEGER NOT NULL, PRIMARY KEY(`uid`, `chat_timestamp`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `translate_entity` (`uid` INTEGER NOT NULL, `create_time` INTEGER NOT NULL, `src_content` TEXT NOT NULL, `dst_content` TEXT NOT NULL, `origin_type` INTEGER NOT NULL, `translate_title` TEXT NOT NULL, PRIMARY KEY(`uid`, `create_time`))");
                db.execSQL(RoomMasterTable.CREATE_QUERY);
                db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'ccf9d48ee93799ab99a593d001b3959d')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(final SupportSQLiteDatabase db) throws SQLException {
                db.execSQL("DROP TABLE IF EXISTS `glass_album`");
                db.execSQL("DROP TABLE IF EXISTS `device_setting`");
                db.execSQL("DROP TABLE IF EXISTS `ai_chat_entity`");
                db.execSQL("DROP TABLE IF EXISTS `translate_entity`");
                List list = GlassDatabase_Impl.this.mCallbacks;
                if (list != null) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        ((RoomDatabase.Callback) it.next()).onDestructiveMigration(db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onCreate(final SupportSQLiteDatabase db) {
                List list = GlassDatabase_Impl.this.mCallbacks;
                if (list != null) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        ((RoomDatabase.Callback) it.next()).onCreate(db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(final SupportSQLiteDatabase db) {
                GlassDatabase_Impl.this.mDatabase = db;
                GlassDatabase_Impl.this.internalInitInvalidationTracker(db);
                List list = GlassDatabase_Impl.this.mCallbacks;
                if (list != null) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        ((RoomDatabase.Callback) it.next()).onOpen(db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(final SupportSQLiteDatabase db) throws SQLException {
                DBUtil.dropFtsSyncTriggers(db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public RoomOpenHelper.ValidationResult onValidateSchema(final SupportSQLiteDatabase db) {
                HashMap map = new HashMap(11);
                map.put("file_name", new TableInfo.Column("file_name", "TEXT", true, 1, null, 1));
                map.put("mac", new TableInfo.Column("mac", "TEXT", true, 2, null, 1));
                map.put("file_path", new TableInfo.Column("file_path", "TEXT", true, 0, null, 1));
                map.put("video_first_frame", new TableInfo.Column("video_first_frame", "TEXT", true, 0, null, 1));
                map.put("file_type", new TableInfo.Column("file_type", "INTEGER", true, 0, null, 1));
                map.put("video_length", new TableInfo.Column("video_length", "INTEGER", true, 0, null, 1));
                map.put("file_date", new TableInfo.Column("file_date", "TEXT", true, 0, null, 1));
                map.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, 1));
                map.put("h_c", new TableInfo.Column("h_c", "INTEGER", true, 0, null, 1));
                map.put("user_like", new TableInfo.Column("user_like", "INTEGER", true, 0, null, 1));
                map.put("eis_in_progress", new TableInfo.Column("eis_in_progress", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo = new TableInfo("glass_album", map, new HashSet(0), new HashSet(0));
                TableInfo tableInfo2 = TableInfo.read(db, "glass_album");
                if (!tableInfo.equals(tableInfo2)) {
                    return new RoomOpenHelper.ValidationResult(false, "glass_album(com.glasssutdio.wear.database.entity.GlassAlbumEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
                }
                HashMap map2 = new HashMap(3);
                map2.put("mac", new TableInfo.Column("mac", "TEXT", true, 1, null, 1));
                map2.put("setting_action", new TableInfo.Column("setting_action", "TEXT", true, 2, null, 1));
                map2.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, 1));
                TableInfo tableInfo3 = new TableInfo("device_setting", map2, new HashSet(0), new HashSet(0));
                TableInfo tableInfo4 = TableInfo.read(db, "device_setting");
                if (!tableInfo3.equals(tableInfo4)) {
                    return new RoomOpenHelper.ValidationResult(false, "device_setting(com.glasssutdio.wear.database.entity.DeviceSettingEntity).\n Expected:\n" + tableInfo3 + "\n Found:\n" + tableInfo4);
                }
                HashMap map3 = new HashMap(10);
                map3.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1, null, 1));
                map3.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, 1));
                map3.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, 1));
                map3.put("chat_type", new TableInfo.Column("chat_type", "INTEGER", true, 0, null, 1));
                map3.put("file_path", new TableInfo.Column("file_path", "TEXT", true, 0, null, 1));
                map3.put("local_file_path", new TableInfo.Column("local_file_path", "TEXT", true, 0, null, 1));
                map3.put("chat_timestamp", new TableInfo.Column("chat_timestamp", "INTEGER", true, 2, null, 1));
                map3.put("uploaded", new TableInfo.Column("uploaded", "INTEGER", true, 0, null, 1));
                map3.put("is_like", new TableInfo.Column("is_like", "INTEGER", true, 0, null, 1));
                map3.put("is_unlike", new TableInfo.Column("is_unlike", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo5 = new TableInfo("ai_chat_entity", map3, new HashSet(0), new HashSet(0));
                TableInfo tableInfo6 = TableInfo.read(db, "ai_chat_entity");
                if (!tableInfo5.equals(tableInfo6)) {
                    return new RoomOpenHelper.ValidationResult(false, "ai_chat_entity(com.glasssutdio.wear.database.entity.AiChatEntity).\n Expected:\n" + tableInfo5 + "\n Found:\n" + tableInfo6);
                }
                HashMap map4 = new HashMap(6);
                map4.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1, null, 1));
                map4.put("create_time", new TableInfo.Column("create_time", "INTEGER", true, 2, null, 1));
                map4.put("src_content", new TableInfo.Column("src_content", "TEXT", true, 0, null, 1));
                map4.put("dst_content", new TableInfo.Column("dst_content", "TEXT", true, 0, null, 1));
                map4.put("origin_type", new TableInfo.Column("origin_type", "INTEGER", true, 0, null, 1));
                map4.put("translate_title", new TableInfo.Column("translate_title", "TEXT", true, 0, null, 1));
                TableInfo tableInfo7 = new TableInfo("translate_entity", map4, new HashSet(0), new HashSet(0));
                TableInfo tableInfo8 = TableInfo.read(db, "translate_entity");
                if (!tableInfo7.equals(tableInfo8)) {
                    return new RoomOpenHelper.ValidationResult(false, "translate_entity(com.glasssutdio.wear.database.entity.TranslateEntity).\n Expected:\n" + tableInfo7 + "\n Found:\n" + tableInfo8);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "ccf9d48ee93799ab99a593d001b3959d", "712cd8a46717cf75e7ad34a9f4849e86")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "glass_album", "device_setting", "ai_chat_entity", "translate_entity");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() throws SQLException {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `glass_album`");
            writableDatabase.execSQL("DELETE FROM `device_setting`");
            writableDatabase.execSQL("DELETE FROM `ai_chat_entity`");
            writableDatabase.execSQL("DELETE FROM `translate_entity`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap map = new HashMap();
        map.put(GlassAlbumDao.class, GlassAlbumDao_Impl.getRequiredConverters());
        map.put(GlassDeviceSettingDao.class, GlassDeviceSettingDao_Impl.getRequiredConverters());
        map.put(GlassAiChatDao.class, GlassAiChatDao_Impl.getRequiredConverters());
        map.put(TranslateDao.class, TranslateDao_Impl.getRequiredConverters());
        return map;
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
        return new ArrayList();
    }

    @Override // com.glasssutdio.wear.database.GlassDatabase
    public GlassAlbumDao glassAlbumDao() {
        GlassAlbumDao glassAlbumDao;
        if (this._glassAlbumDao != null) {
            return this._glassAlbumDao;
        }
        synchronized (this) {
            if (this._glassAlbumDao == null) {
                this._glassAlbumDao = new GlassAlbumDao_Impl(this);
            }
            glassAlbumDao = this._glassAlbumDao;
        }
        return glassAlbumDao;
    }

    @Override // com.glasssutdio.wear.database.GlassDatabase
    public GlassDeviceSettingDao glassDeviceSettingDao() {
        GlassDeviceSettingDao glassDeviceSettingDao;
        if (this._glassDeviceSettingDao != null) {
            return this._glassDeviceSettingDao;
        }
        synchronized (this) {
            if (this._glassDeviceSettingDao == null) {
                this._glassDeviceSettingDao = new GlassDeviceSettingDao_Impl(this);
            }
            glassDeviceSettingDao = this._glassDeviceSettingDao;
        }
        return glassDeviceSettingDao;
    }

    @Override // com.glasssutdio.wear.database.GlassDatabase
    public GlassAiChatDao glassAiChatDao() {
        GlassAiChatDao glassAiChatDao;
        if (this._glassAiChatDao != null) {
            return this._glassAiChatDao;
        }
        synchronized (this) {
            if (this._glassAiChatDao == null) {
                this._glassAiChatDao = new GlassAiChatDao_Impl(this);
            }
            glassAiChatDao = this._glassAiChatDao;
        }
        return glassAiChatDao;
    }

    @Override // com.glasssutdio.wear.database.GlassDatabase
    public TranslateDao translateDao() {
        TranslateDao translateDao;
        if (this._translateDao != null) {
            return this._translateDao;
        }
        synchronized (this) {
            if (this._translateDao == null) {
                this._translateDao = new TranslateDao_Impl(this);
            }
            translateDao = this._translateDao;
        }
        return translateDao;
    }
}
