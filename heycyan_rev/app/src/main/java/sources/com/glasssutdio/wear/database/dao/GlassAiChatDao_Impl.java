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
import com.glasssutdio.wear.database.entity.AiChatEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* loaded from: classes.dex */
public final class GlassAiChatDao_Impl implements GlassAiChatDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<AiChatEntity> __deletionAdapterOfAiChatEntity;
    private final EntityInsertionAdapter<AiChatEntity> __insertionAdapterOfAiChatEntity;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByUidAndTimestamp;
    private final SharedSQLiteStatement __preparedStmtOfUpdateLikeUnlikeStatus;
    private final EntityDeletionOrUpdateAdapter<AiChatEntity> __updateAdapterOfAiChatEntity;

    public GlassAiChatDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfAiChatEntity = new EntityInsertionAdapter<AiChatEntity>(__db) { // from class: com.glasssutdio.wear.database.dao.GlassAiChatDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `ai_chat_entity` (`uid`,`role`,`content`,`chat_type`,`file_path`,`local_file_path`,`chat_timestamp`,`uploaded`,`is_like`,`is_unlike`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AiChatEntity aiChatEntity) {
                supportSQLiteStatement.bindLong(1, aiChatEntity.getUid());
                supportSQLiteStatement.bindString(2, aiChatEntity.getRole());
                supportSQLiteStatement.bindString(3, aiChatEntity.getContent());
                supportSQLiteStatement.bindLong(4, aiChatEntity.getChatType());
                supportSQLiteStatement.bindString(5, aiChatEntity.getFilePath());
                supportSQLiteStatement.bindString(6, aiChatEntity.getLocalFilePath());
                supportSQLiteStatement.bindLong(7, aiChatEntity.getChatTimestamp());
                supportSQLiteStatement.bindLong(8, aiChatEntity.getUploaded());
                supportSQLiteStatement.bindLong(9, aiChatEntity.isLike() ? 1L : 0L);
                supportSQLiteStatement.bindLong(10, aiChatEntity.isUnlike() ? 1L : 0L);
            }
        };
        this.__deletionAdapterOfAiChatEntity = new EntityDeletionOrUpdateAdapter<AiChatEntity>(__db) { // from class: com.glasssutdio.wear.database.dao.GlassAiChatDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "DELETE FROM `ai_chat_entity` WHERE `uid` = ? AND `chat_timestamp` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(final SupportSQLiteStatement statement, final AiChatEntity entity) {
                statement.bindLong(1, entity.getUid());
                statement.bindLong(2, entity.getChatTimestamp());
            }
        };
        this.__updateAdapterOfAiChatEntity = new EntityDeletionOrUpdateAdapter<AiChatEntity>(__db) { // from class: com.glasssutdio.wear.database.dao.GlassAiChatDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "UPDATE OR ABORT `ai_chat_entity` SET `uid` = ?,`role` = ?,`content` = ?,`chat_type` = ?,`file_path` = ?,`local_file_path` = ?,`chat_timestamp` = ?,`uploaded` = ?,`is_like` = ?,`is_unlike` = ? WHERE `uid` = ? AND `chat_timestamp` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AiChatEntity aiChatEntity) {
                supportSQLiteStatement.bindLong(1, aiChatEntity.getUid());
                supportSQLiteStatement.bindString(2, aiChatEntity.getRole());
                supportSQLiteStatement.bindString(3, aiChatEntity.getContent());
                supportSQLiteStatement.bindLong(4, aiChatEntity.getChatType());
                supportSQLiteStatement.bindString(5, aiChatEntity.getFilePath());
                supportSQLiteStatement.bindString(6, aiChatEntity.getLocalFilePath());
                supportSQLiteStatement.bindLong(7, aiChatEntity.getChatTimestamp());
                supportSQLiteStatement.bindLong(8, aiChatEntity.getUploaded());
                supportSQLiteStatement.bindLong(9, aiChatEntity.isLike() ? 1L : 0L);
                supportSQLiteStatement.bindLong(10, aiChatEntity.isUnlike() ? 1L : 0L);
                supportSQLiteStatement.bindLong(11, aiChatEntity.getUid());
                supportSQLiteStatement.bindLong(12, aiChatEntity.getChatTimestamp());
            }
        };
        this.__preparedStmtOfDeleteByUidAndTimestamp = new SharedSQLiteStatement(__db) { // from class: com.glasssutdio.wear.database.dao.GlassAiChatDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM ai_chat_entity WHERE uid = ? AND chat_timestamp = ?";
            }
        };
        this.__preparedStmtOfUpdateLikeUnlikeStatus = new SharedSQLiteStatement(__db) { // from class: com.glasssutdio.wear.database.dao.GlassAiChatDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE ai_chat_entity SET is_like = ?, is_unlike = ? WHERE uid = ? AND chat_timestamp = ?";
            }
        };
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void insertAll(final List<AiChatEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAiChatEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void insert(final AiChatEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAiChatEntity.insert((EntityInsertionAdapter<AiChatEntity>) element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void delete(final AiChatEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfAiChatEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void deleteList(final List<AiChatEntity> elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfAiChatEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void deleteSome(final AiChatEntity... elements) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfAiChatEntity.handleMultiple(elements);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.BaseDao
    public void update(final AiChatEntity element) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfAiChatEntity.handle(element);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.GlassAiChatDao
    public Object deleteByUidAndTimestamp(final long uid, final long chatTimestamp, final Continuation<? super Unit> $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.glasssutdio.wear.database.dao.GlassAiChatDao_Impl.6
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement supportSQLiteStatementAcquire = GlassAiChatDao_Impl.this.__preparedStmtOfDeleteByUidAndTimestamp.acquire();
                supportSQLiteStatementAcquire.bindLong(1, uid);
                supportSQLiteStatementAcquire.bindLong(2, chatTimestamp);
                try {
                    GlassAiChatDao_Impl.this.__db.beginTransaction();
                    try {
                        supportSQLiteStatementAcquire.executeUpdateDelete();
                        GlassAiChatDao_Impl.this.__db.setTransactionSuccessful();
                        return Unit.INSTANCE;
                    } finally {
                        GlassAiChatDao_Impl.this.__db.endTransaction();
                    }
                } finally {
                    GlassAiChatDao_Impl.this.__preparedStmtOfDeleteByUidAndTimestamp.release(supportSQLiteStatementAcquire);
                }
            }
        }, $completion);
    }

    @Override // com.glasssutdio.wear.database.dao.GlassAiChatDao
    public Object updateLikeUnlikeStatus(final long uid, final long chatTimestamp, final boolean isLike, final boolean isUnlike, final Continuation<? super Unit> $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.glasssutdio.wear.database.dao.GlassAiChatDao_Impl.7
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement supportSQLiteStatementAcquire = GlassAiChatDao_Impl.this.__preparedStmtOfUpdateLikeUnlikeStatus.acquire();
                supportSQLiteStatementAcquire.bindLong(1, isLike ? 1L : 0L);
                supportSQLiteStatementAcquire.bindLong(2, isUnlike ? 1L : 0L);
                supportSQLiteStatementAcquire.bindLong(3, uid);
                supportSQLiteStatementAcquire.bindLong(4, chatTimestamp);
                try {
                    GlassAiChatDao_Impl.this.__db.beginTransaction();
                    try {
                        supportSQLiteStatementAcquire.executeUpdateDelete();
                        GlassAiChatDao_Impl.this.__db.setTransactionSuccessful();
                        return Unit.INSTANCE;
                    } finally {
                        GlassAiChatDao_Impl.this.__db.endTransaction();
                    }
                } finally {
                    GlassAiChatDao_Impl.this.__preparedStmtOfUpdateLikeUnlikeStatus.release(supportSQLiteStatementAcquire);
                }
            }
        }, $completion);
    }

    @Override // com.glasssutdio.wear.database.dao.GlassAiChatDao
    public AiChatEntity queryAllByUid(final long uid) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * from ai_chat_entity where uid=?", 1);
        roomSQLiteQueryAcquire.bindLong(1, uid);
        this.__db.assertNotSuspendingTransaction();
        AiChatEntity aiChatEntity = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "uid");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "role");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "chat_type");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_path");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_file_path");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "chat_timestamp");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "uploaded");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_like");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_unlike");
            if (cursorQuery.moveToFirst()) {
                aiChatEntity = new AiChatEntity(cursorQuery.getLong(columnIndexOrThrow), cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getString(columnIndexOrThrow5), cursorQuery.getString(columnIndexOrThrow6), cursorQuery.getLong(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getInt(columnIndexOrThrow9) != 0, cursorQuery.getInt(columnIndexOrThrow10) != 0);
            }
            return aiChatEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.GlassAiChatDao
    public List<AiChatEntity> queryHistoryChatList(final long uid, final long lastTimestamp, final int limit) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * from ai_chat_entity where uid=? and chat_timestamp < ? order by chat_timestamp desc limit ? ", 3);
        roomSQLiteQueryAcquire.bindLong(1, uid);
        roomSQLiteQueryAcquire.bindLong(2, lastTimestamp);
        roomSQLiteQueryAcquire.bindLong(3, limit);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "uid");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "role");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "chat_type");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_path");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_file_path");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "chat_timestamp");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "uploaded");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_like");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_unlike");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new AiChatEntity(cursorQuery.getLong(columnIndexOrThrow), cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getString(columnIndexOrThrow5), cursorQuery.getString(columnIndexOrThrow6), cursorQuery.getLong(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getInt(columnIndexOrThrow9) != 0, cursorQuery.getInt(columnIndexOrThrow10) != 0));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.glasssutdio.wear.database.dao.GlassAiChatDao
    public Object queryByUidAndTimestamp(final long uid, final long chatTimestamp, final Continuation<? super AiChatEntity> $completion) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM ai_chat_entity WHERE uid = ? AND chat_timestamp = ?", 2);
        roomSQLiteQueryAcquire.bindLong(1, uid);
        roomSQLiteQueryAcquire.bindLong(2, chatTimestamp);
        return CoroutinesRoom.execute(this.__db, false, DBUtil.createCancellationSignal(), new Callable<AiChatEntity>() { // from class: com.glasssutdio.wear.database.dao.GlassAiChatDao_Impl.8
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public AiChatEntity call() throws Exception {
                AiChatEntity aiChatEntity = null;
                Cursor cursorQuery = DBUtil.query(GlassAiChatDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "uid");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "role");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "chat_type");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "file_path");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_file_path");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "chat_timestamp");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "uploaded");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_like");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_unlike");
                    if (cursorQuery.moveToFirst()) {
                        aiChatEntity = new AiChatEntity(cursorQuery.getLong(columnIndexOrThrow), cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.getString(columnIndexOrThrow5), cursorQuery.getString(columnIndexOrThrow6), cursorQuery.getLong(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getInt(columnIndexOrThrow9) != 0, cursorQuery.getInt(columnIndexOrThrow10) != 0);
                    }
                    return aiChatEntity;
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
