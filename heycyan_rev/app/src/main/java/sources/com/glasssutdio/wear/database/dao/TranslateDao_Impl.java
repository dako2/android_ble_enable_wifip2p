package com.glasssutdio.wear.database.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p002db.SupportSQLiteStatement;
import com.glasssutdio.wear.database.entity.TranslateEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* loaded from: classes.dex */
public final class TranslateDao_Impl implements TranslateDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<TranslateEntity> __deletionAdapterOfTranslateEntity;
    private final EntityInsertionAdapter<TranslateEntity> __insertionAdapterOfTranslateEntity;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByUidAndTimestamp;
    private final SharedSQLiteStatement __preparedStmtOfUpdateTranslate;
    private final SharedSQLiteStatement __preparedStmtOfUpdateTranslateTitle;
    private final EntityDeletionOrUpdateAdapter<TranslateEntity> __updateAdapterOfTranslateEntity;

    public TranslateDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfTranslateEntity = new EntityInsertionAdapter<TranslateEntity>(__db) { // from class: com.glasssutdio.wear.database.dao.TranslateDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `translate_entity` (`uid`,`create_time`,`src_content`,`dst_content`,`origin_type`,`translate_title`) VALUES (?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(final SupportSQLiteStatement statement, final TranslateEntity entity) {
                statement.bindLong(1, entity.getUid());
                statement.bindLong(2, entity.getCreateTime());
                statement.bindString(3, entity.getSrcContent());
                statement.bindString(4, entity.getDstContent());
                statement.bindLong(5, entity.getOriginType());
                statement.bindString(6, entity.getTranslateTitle());
            }
        };
        this.__deletionAdapterOfTranslateEntity = new EntityDeletionOrUpdateAdapter<TranslateEntity>(__db) { // from class: com.glasssutdio.wear.database.dao.TranslateDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "DELETE FROM `translate_entity` WHERE `uid` = ? AND `create_time` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(final SupportSQLiteStatement statement, final TranslateEntity entity) {
                statement.bindLong(1, entity.getUid());
                statement.bindLong(2, entity.getCreateTime());
            }
        };
        this.__updateAdapterOfTranslateEntity = new EntityDeletionOrUpdateAdapter<TranslateEntity>(__db) { // from class: com.glasssutdio.wear.database.dao.TranslateDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "UPDATE OR ABORT `translate_entity` SET `uid` = ?,`create_time` = ?,`src_content` = ?,`dst_content` = ?,`origin_type` = ?,`translate_title` = ? WHERE `uid` = ? AND `create_time` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(final SupportSQLiteStatement statement, final TranslateEntity entity) {
                statement.bindLong(1, entity.getUid());
                statement.bindLong(2, entity.getCreateTime());
                statement.bindString(3, entity.getSrcContent());
                statement.bindString(4, entity.getDstContent());
                statement.bindLong(5, entity.getOriginType());
                statement.bindString(6, entity.getTranslateTitle());
                statement.bindLong(7, entity.getUid());
                statement.bindLong(8, entity.getCreateTime());
            }
        };
        this.__preparedStmtOfDeleteByUidAndTimestamp = new SharedSQLiteStatement(__db) { // from class: com.glasssutdio.wear.database.dao.TranslateDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM translate_entity WHERE uid = ? AND create_time = ?";
            }
        };
        this.__preparedStmtOfUpdateTranslate = new SharedSQLiteStatement(__db) { // from class: com.glasssutdio.wear.database.dao.TranslateDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE translate_entity SET src_content = ?, dst_content = ? WHERE uid = ? AND create_time = ?";
            }
        };
        this.__preparedStmtOfUpdateTranslateTitle = new SharedSQLiteStatement(__db) { // from class: com.glasssutdio.wear.database.dao.TranslateDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE translate_entity SET  translate_title = ? WHERE uid = ? AND create_time = ?";
            }
        };
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void insertAll(final List<TranslateEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfTranslateEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void insert(final TranslateEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfTranslateEntity.insert((EntityInsertionAdapter<TranslateEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void delete(final TranslateEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfTranslateEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void deleteList(final List<TranslateEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfTranslateEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void deleteSome(final TranslateEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfTranslateEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void update(final TranslateEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfTranslateEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.TranslateDao
    public Object deleteByUidAndTimestamp(final long uid, final long createTime, final Continuation<? super Unit> $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.glasssutdio.wear.database.dao.TranslateDao_Impl.7
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement supportSQLiteStatementAcquire = TranslateDao_Impl.this.__preparedStmtOfDeleteByUidAndTimestamp.acquire();
                supportSQLiteStatementAcquire.bindLong(1, uid);
                supportSQLiteStatementAcquire.bindLong(2, createTime);
                try {
                    TranslateDao_Impl.this.__db.beginTransaction();
                    try {
                        supportSQLiteStatementAcquire.executeUpdateDelete();
                        TranslateDao_Impl.this.__db.setTransactionSuccessful();
                        return Unit.INSTANCE;
                    } finally {
                        TranslateDao_Impl.this.__db.endTransaction();
                    }
                } finally {
                    TranslateDao_Impl.this.__preparedStmtOfDeleteByUidAndTimestamp.release(supportSQLiteStatementAcquire);
                }
            }
        }, $completion);
    }

    @Override // com.glasssutdio.wear.database.dao.TranslateDao
    public Object updateTranslate(final long uid, final long createTime, final String srcContent, final String dstContent, final Continuation<? super Unit> $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.glasssutdio.wear.database.dao.TranslateDao_Impl.8
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement supportSQLiteStatementAcquire = TranslateDao_Impl.this.__preparedStmtOfUpdateTranslate.acquire();
                supportSQLiteStatementAcquire.bindString(1, srcContent);
                supportSQLiteStatementAcquire.bindString(2, dstContent);
                supportSQLiteStatementAcquire.bindLong(3, uid);
                supportSQLiteStatementAcquire.bindLong(4, createTime);
                try {
                    TranslateDao_Impl.this.__db.beginTransaction();
                    try {
                        supportSQLiteStatementAcquire.executeUpdateDelete();
                        TranslateDao_Impl.this.__db.setTransactionSuccessful();
                        return Unit.INSTANCE;
                    } finally {
                        TranslateDao_Impl.this.__db.endTransaction();
                    }
                } finally {
                    TranslateDao_Impl.this.__preparedStmtOfUpdateTranslate.release(supportSQLiteStatementAcquire);
                }
            }
        }, $completion);
    }

    @Override // com.glasssutdio.wear.database.dao.TranslateDao
    public Object updateTranslateTitle(final long uid, final long createTime, final String translate, final Continuation<? super Unit> $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.glasssutdio.wear.database.dao.TranslateDao_Impl.9
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement supportSQLiteStatementAcquire = TranslateDao_Impl.this.__preparedStmtOfUpdateTranslateTitle.acquire();
                supportSQLiteStatementAcquire.bindString(1, translate);
                supportSQLiteStatementAcquire.bindLong(2, uid);
                supportSQLiteStatementAcquire.bindLong(3, createTime);
                try {
                    TranslateDao_Impl.this.__db.beginTransaction();
                    try {
                        supportSQLiteStatementAcquire.executeUpdateDelete();
                        TranslateDao_Impl.this.__db.setTransactionSuccessful();
                        return Unit.INSTANCE;
                    } finally {
                        TranslateDao_Impl.this.__db.endTransaction();
                    }
                } finally {
                    TranslateDao_Impl.this.__preparedStmtOfUpdateTranslateTitle.release(supportSQLiteStatementAcquire);
                }
            }
        }, $completion);
    }

    @Override // com.glasssutdio.wear.database.dao.TranslateDao
    public Object queryAll(final long uid, final int originType, final Continuation<? super List<TranslateEntity>> $completion) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * from translate_entity where uid=? AND origin_type=?", 2);
        roomSQLiteQueryAcquire.bindLong(1, uid);
        roomSQLiteQueryAcquire.bindLong(2, originType);
        return CoroutinesRoom.execute(this.__db, false, DBUtil.createCancellationSignal(), new Callable<List<TranslateEntity>>() { // from class: com.glasssutdio.wear.database.dao.TranslateDao_Impl.10
            @Override // java.util.concurrent.Callable
            public List<TranslateEntity> call() throws Exception {
                Cursor cursorQuery = DBUtil.query(TranslateDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "uid");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "create_time");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "src_content");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dst_content");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "origin_type");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "translate_title");
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(new TranslateEntity(cursorQuery.getLong(columnIndexOrThrow), cursorQuery.getLong(columnIndexOrThrow2), cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.getString(columnIndexOrThrow6)));
                    }
                    return arrayList;
                } finally {
                    cursorQuery.close();
                    roomSQLiteQueryAcquire.release();
                }
            }
        }, $completion);
    }

    @Override // com.glasssutdio.wear.database.dao.TranslateDao
    public Object queryByUidCreateTime(final long uid, final int originType, final long createTime, final Continuation<? super List<TranslateEntity>> $completion) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM translate_entity WHERE uid = ? AND origin_type = ? AND create_time = ?", 3);
        roomSQLiteQueryAcquire.bindLong(1, uid);
        roomSQLiteQueryAcquire.bindLong(2, originType);
        roomSQLiteQueryAcquire.bindLong(3, createTime);
        return CoroutinesRoom.execute(this.__db, false, DBUtil.createCancellationSignal(), new Callable<List<TranslateEntity>>() { // from class: com.glasssutdio.wear.database.dao.TranslateDao_Impl.11
            @Override // java.util.concurrent.Callable
            public List<TranslateEntity> call() throws Exception {
                Cursor cursorQuery = DBUtil.query(TranslateDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "uid");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "create_time");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "src_content");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dst_content");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "origin_type");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "translate_title");
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(new TranslateEntity(cursorQuery.getLong(columnIndexOrThrow), cursorQuery.getLong(columnIndexOrThrow2), cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getInt(columnIndexOrThrow5), cursorQuery.getString(columnIndexOrThrow6)));
                    }
                    return arrayList;
                } finally {
                    cursorQuery.close();
                    roomSQLiteQueryAcquire.release();
                }
            }
        }, $completion);
    }

    @Override // com.glasssutdio.wear.database.dao.TranslateDao
    public Object queryByCreateTime(final long uid, final long createTime, final Continuation<? super TranslateEntity> $completion) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM translate_entity WHERE uid = ? AND create_time = ?", 2);
        roomSQLiteQueryAcquire.bindLong(1, uid);
        roomSQLiteQueryAcquire.bindLong(2, createTime);
        return CoroutinesRoom.execute(this.__db, false, DBUtil.createCancellationSignal(), new Callable<TranslateEntity>() { // from class: com.glasssutdio.wear.database.dao.TranslateDao_Impl.12
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public TranslateEntity call() throws Exception {
                Cursor cursorQuery = DBUtil.query(TranslateDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    return cursorQuery.moveToFirst() ? new TranslateEntity(cursorQuery.getLong(CursorUtil.getColumnIndexOrThrow(cursorQuery, "uid")), cursorQuery.getLong(CursorUtil.getColumnIndexOrThrow(cursorQuery, "create_time")), cursorQuery.getString(CursorUtil.getColumnIndexOrThrow(cursorQuery, "src_content")), cursorQuery.getString(CursorUtil.getColumnIndexOrThrow(cursorQuery, "dst_content")), cursorQuery.getInt(CursorUtil.getColumnIndexOrThrow(cursorQuery, "origin_type")), cursorQuery.getString(CursorUtil.getColumnIndexOrThrow(cursorQuery, "translate_title"))) : null;
                } finally {
                    cursorQuery.close();
                    roomSQLiteQueryAcquire.release();
                }
            }
        }, $completion);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
