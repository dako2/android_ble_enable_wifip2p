package com.glasssutdio.wear.database.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p002db.SupportSQLiteStatement;
import com.glasssutdio.wear.database.entity.GlassAlbumEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class GlassAlbumDao_Impl implements GlassAlbumDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<GlassAlbumEntity> __deletionAdapterOfGlassAlbumEntity;
    private final EntityInsertionAdapter<GlassAlbumEntity> __insertionAdapterOfGlassAlbumEntity;
    private final EntityDeletionOrUpdateAdapter<GlassAlbumEntity> __updateAdapterOfGlassAlbumEntity;

    public GlassAlbumDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfGlassAlbumEntity = new EntityInsertionAdapter<GlassAlbumEntity>(__db) { // from class: com.glasssutdio.wear.database.dao.GlassAlbumDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `glass_album` (`file_name`,`mac`,`file_path`,`video_first_frame`,`file_type`,`video_length`,`file_date`,`timestamp`,`h_c`,`user_like`,`eis_in_progress`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, GlassAlbumEntity glassAlbumEntity) {
                supportSQLiteStatement.bindString(1, glassAlbumEntity.getFileName());
                supportSQLiteStatement.bindString(2, glassAlbumEntity.getMac());
                supportSQLiteStatement.bindString(3, glassAlbumEntity.getFilePath());
                supportSQLiteStatement.bindString(4, glassAlbumEntity.getVideoFirstFrame());
                supportSQLiteStatement.bindLong(5, glassAlbumEntity.getFileType());
                supportSQLiteStatement.bindLong(6, glassAlbumEntity.getVideoLength());
                supportSQLiteStatement.bindString(7, glassAlbumEntity.getFileDate());
                supportSQLiteStatement.bindLong(8, glassAlbumEntity.getTimestamp());
                supportSQLiteStatement.bindLong(9, glassAlbumEntity.getHorizontalCalibration());
                supportSQLiteStatement.bindLong(10, glassAlbumEntity.getUserLike());
                supportSQLiteStatement.bindLong(11, glassAlbumEntity.getEisInProgress() ? 1L : 0L);
            }
        };
        this.__deletionAdapterOfGlassAlbumEntity = new EntityDeletionOrUpdateAdapter<GlassAlbumEntity>(__db) { // from class: com.glasssutdio.wear.database.dao.GlassAlbumDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "DELETE FROM `glass_album` WHERE `file_name` = ? AND `mac` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(final SupportSQLiteStatement statement, final GlassAlbumEntity entity) {
                statement.bindString(1, entity.getFileName());
                statement.bindString(2, entity.getMac());
            }
        };
        this.__updateAdapterOfGlassAlbumEntity = new EntityDeletionOrUpdateAdapter<GlassAlbumEntity>(__db) { // from class: com.glasssutdio.wear.database.dao.GlassAlbumDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "UPDATE OR ABORT `glass_album` SET `file_name` = ?,`mac` = ?,`file_path` = ?,`video_first_frame` = ?,`file_type` = ?,`video_length` = ?,`file_date` = ?,`timestamp` = ?,`h_c` = ?,`user_like` = ?,`eis_in_progress` = ? WHERE `file_name` = ? AND `mac` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, GlassAlbumEntity glassAlbumEntity) {
                supportSQLiteStatement.bindString(1, glassAlbumEntity.getFileName());
                supportSQLiteStatement.bindString(2, glassAlbumEntity.getMac());
                supportSQLiteStatement.bindString(3, glassAlbumEntity.getFilePath());
                supportSQLiteStatement.bindString(4, glassAlbumEntity.getVideoFirstFrame());
                supportSQLiteStatement.bindLong(5, glassAlbumEntity.getFileType());
                supportSQLiteStatement.bindLong(6, glassAlbumEntity.getVideoLength());
                supportSQLiteStatement.bindString(7, glassAlbumEntity.getFileDate());
                supportSQLiteStatement.bindLong(8, glassAlbumEntity.getTimestamp());
                supportSQLiteStatement.bindLong(9, glassAlbumEntity.getHorizontalCalibration());
                supportSQLiteStatement.bindLong(10, glassAlbumEntity.getUserLike());
                supportSQLiteStatement.bindLong(11, glassAlbumEntity.getEisInProgress() ? 1L : 0L);
                supportSQLiteStatement.bindString(12, glassAlbumEntity.getFileName());
                supportSQLiteStatement.bindString(13, glassAlbumEntity.getMac());
            }
        };
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void insertAll(final List<GlassAlbumEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfGlassAlbumEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void insert(final GlassAlbumEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfGlassAlbumEntity.insert((EntityInsertionAdapter<GlassAlbumEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void delete(final GlassAlbumEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfGlassAlbumEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void deleteList(final List<GlassAlbumEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfGlassAlbumEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void deleteSome(final GlassAlbumEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfGlassAlbumEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void update(final GlassAlbumEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfGlassAlbumEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.GlassAlbumDao
    public GlassAlbumEntity queryAlbumFileByName(final String name, final String mac) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from glass_album where file_name=? and mac=?", 2);
        roomSQLiteQueryAcquire.bindString(1, name);
        roomSQLiteQueryAcquire.bindString(2, mac);
        this.__db.assertNotSuspendingTransaction();
        GlassAlbumEntity glassAlbumEntity = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mac");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_path");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "video_first_frame");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_type");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "video_length");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_date");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "timestamp");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "h_c");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "user_like");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "eis_in_progress");
            if (cursorQuery.moveToFirst()) {
                glassAlbumEntity = new GlassAlbumEntity();
                glassAlbumEntity.setFileName(cursorQuery.getString(columnIndexOrThrow));
                glassAlbumEntity.setMac(cursorQuery.getString(columnIndexOrThrow2));
                glassAlbumEntity.setFilePath(cursorQuery.getString(columnIndexOrThrow3));
                glassAlbumEntity.setVideoFirstFrame(cursorQuery.getString(columnIndexOrThrow4));
                glassAlbumEntity.setFileType(cursorQuery.getInt(columnIndexOrThrow5));
                glassAlbumEntity.setVideoLength(cursorQuery.getInt(columnIndexOrThrow6));
                glassAlbumEntity.setFileDate(cursorQuery.getString(columnIndexOrThrow7));
                glassAlbumEntity.setTimestamp(cursorQuery.getLong(columnIndexOrThrow8));
                glassAlbumEntity.setHorizontalCalibration(cursorQuery.getInt(columnIndexOrThrow9));
                glassAlbumEntity.setUserLike(cursorQuery.getInt(columnIndexOrThrow10));
                glassAlbumEntity.setEisInProgress(cursorQuery.getInt(columnIndexOrThrow11) != 0);
            }
            return glassAlbumEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.GlassAlbumDao
    public GlassAlbumEntity queryAlbumFilesByDate(final String date, final String mac) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from glass_album where file_name=? and mac=?", 2);
        roomSQLiteQueryAcquire.bindString(1, date);
        roomSQLiteQueryAcquire.bindString(2, mac);
        this.__db.assertNotSuspendingTransaction();
        GlassAlbumEntity glassAlbumEntity = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mac");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_path");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "video_first_frame");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_type");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "video_length");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_date");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "timestamp");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "h_c");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "user_like");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "eis_in_progress");
            if (cursorQuery.moveToFirst()) {
                glassAlbumEntity = new GlassAlbumEntity();
                glassAlbumEntity.setFileName(cursorQuery.getString(columnIndexOrThrow));
                glassAlbumEntity.setMac(cursorQuery.getString(columnIndexOrThrow2));
                glassAlbumEntity.setFilePath(cursorQuery.getString(columnIndexOrThrow3));
                glassAlbumEntity.setVideoFirstFrame(cursorQuery.getString(columnIndexOrThrow4));
                glassAlbumEntity.setFileType(cursorQuery.getInt(columnIndexOrThrow5));
                glassAlbumEntity.setVideoLength(cursorQuery.getInt(columnIndexOrThrow6));
                glassAlbumEntity.setFileDate(cursorQuery.getString(columnIndexOrThrow7));
                glassAlbumEntity.setTimestamp(cursorQuery.getLong(columnIndexOrThrow8));
                glassAlbumEntity.setHorizontalCalibration(cursorQuery.getInt(columnIndexOrThrow9));
                glassAlbumEntity.setUserLike(cursorQuery.getInt(columnIndexOrThrow10));
                glassAlbumEntity.setEisInProgress(cursorQuery.getInt(columnIndexOrThrow11) != 0);
            }
            return glassAlbumEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.GlassAlbumDao
    public List<GlassAlbumEntity> queryAllFile(final String mac) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from glass_album where mac=? order by timestamp desc", 1);
        roomSQLiteQueryAcquire.bindString(1, mac);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mac");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_path");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "video_first_frame");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_type");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "video_length");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_date");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "timestamp");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "h_c");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "user_like");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "eis_in_progress");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                GlassAlbumEntity glassAlbumEntity = new GlassAlbumEntity();
                glassAlbumEntity.setFileName(cursorQuery.getString(columnIndexOrThrow));
                glassAlbumEntity.setMac(cursorQuery.getString(columnIndexOrThrow2));
                glassAlbumEntity.setFilePath(cursorQuery.getString(columnIndexOrThrow3));
                glassAlbumEntity.setVideoFirstFrame(cursorQuery.getString(columnIndexOrThrow4));
                glassAlbumEntity.setFileType(cursorQuery.getInt(columnIndexOrThrow5));
                glassAlbumEntity.setVideoLength(cursorQuery.getInt(columnIndexOrThrow6));
                glassAlbumEntity.setFileDate(cursorQuery.getString(columnIndexOrThrow7));
                int i = columnIndexOrThrow;
                glassAlbumEntity.setTimestamp(cursorQuery.getLong(columnIndexOrThrow8));
                glassAlbumEntity.setHorizontalCalibration(cursorQuery.getInt(columnIndexOrThrow9));
                glassAlbumEntity.setUserLike(cursorQuery.getInt(columnIndexOrThrow10));
                glassAlbumEntity.setEisInProgress(cursorQuery.getInt(columnIndexOrThrow11) != 0);
                arrayList.add(glassAlbumEntity);
                columnIndexOrThrow = i;
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.GlassAlbumDao
    public List<GlassAlbumEntity> queryImageFileByteType(final String mac, final int type) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from glass_album where mac=? and file_type=? order by timestamp desc", 2);
        roomSQLiteQueryAcquire.bindString(1, mac);
        roomSQLiteQueryAcquire.bindLong(2, type);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mac");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_path");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "video_first_frame");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_type");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "video_length");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_date");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "timestamp");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "h_c");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "user_like");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "eis_in_progress");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                GlassAlbumEntity glassAlbumEntity = new GlassAlbumEntity();
                glassAlbumEntity.setFileName(cursorQuery.getString(columnIndexOrThrow));
                glassAlbumEntity.setMac(cursorQuery.getString(columnIndexOrThrow2));
                glassAlbumEntity.setFilePath(cursorQuery.getString(columnIndexOrThrow3));
                glassAlbumEntity.setVideoFirstFrame(cursorQuery.getString(columnIndexOrThrow4));
                glassAlbumEntity.setFileType(cursorQuery.getInt(columnIndexOrThrow5));
                glassAlbumEntity.setVideoLength(cursorQuery.getInt(columnIndexOrThrow6));
                glassAlbumEntity.setFileDate(cursorQuery.getString(columnIndexOrThrow7));
                int i = columnIndexOrThrow2;
                int i2 = columnIndexOrThrow3;
                glassAlbumEntity.setTimestamp(cursorQuery.getLong(columnIndexOrThrow8));
                glassAlbumEntity.setHorizontalCalibration(cursorQuery.getInt(columnIndexOrThrow9));
                glassAlbumEntity.setUserLike(cursorQuery.getInt(columnIndexOrThrow10));
                glassAlbumEntity.setEisInProgress(cursorQuery.getInt(columnIndexOrThrow11) != 0);
                arrayList.add(glassAlbumEntity);
                columnIndexOrThrow2 = i;
                columnIndexOrThrow3 = i2;
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.GlassAlbumDao
    public List<GlassAlbumEntity> queryLikeMedia(final String mac) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from glass_album where mac=? and user_like = 1 ", 1);
        roomSQLiteQueryAcquire.bindString(1, mac);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_name");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mac");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_path");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "video_first_frame");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_type");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "video_length");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_date");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "timestamp");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "h_c");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "user_like");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "eis_in_progress");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                GlassAlbumEntity glassAlbumEntity = new GlassAlbumEntity();
                glassAlbumEntity.setFileName(cursorQuery.getString(columnIndexOrThrow));
                glassAlbumEntity.setMac(cursorQuery.getString(columnIndexOrThrow2));
                glassAlbumEntity.setFilePath(cursorQuery.getString(columnIndexOrThrow3));
                glassAlbumEntity.setVideoFirstFrame(cursorQuery.getString(columnIndexOrThrow4));
                glassAlbumEntity.setFileType(cursorQuery.getInt(columnIndexOrThrow5));
                glassAlbumEntity.setVideoLength(cursorQuery.getInt(columnIndexOrThrow6));
                glassAlbumEntity.setFileDate(cursorQuery.getString(columnIndexOrThrow7));
                int i = columnIndexOrThrow;
                glassAlbumEntity.setTimestamp(cursorQuery.getLong(columnIndexOrThrow8));
                glassAlbumEntity.setHorizontalCalibration(cursorQuery.getInt(columnIndexOrThrow9));
                glassAlbumEntity.setUserLike(cursorQuery.getInt(columnIndexOrThrow10));
                glassAlbumEntity.setEisInProgress(cursorQuery.getInt(columnIndexOrThrow11) != 0);
                arrayList.add(glassAlbumEntity);
                columnIndexOrThrow = i;
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
