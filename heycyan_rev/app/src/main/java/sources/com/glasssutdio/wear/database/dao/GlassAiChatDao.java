package com.glasssutdio.wear.database.dao;

import com.glasssutdio.wear.database.entity.AiChatEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: GlassAiChatDao.kt */
@Metadata(m606d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H'J#\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\bJ&\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH'J1\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, m607d2 = {"Lcom/glasssutdio/wear/database/dao/GlassAiChatDao;", "Lcom/glasssutdio/wear/database/dao/BaseDao;", "Lcom/glasssutdio/wear/database/entity/AiChatEntity;", "deleteByUidAndTimestamp", "", "uid", "", "chatTimestamp", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryAllByUid", "queryByUidAndTimestamp", "queryHistoryChatList", "", "lastTimestamp", "limit", "", "updateLikeUnlikeStatus", "isLike", "", "isUnlike", "(JJZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public interface GlassAiChatDao extends BaseDao<AiChatEntity> {
    Object deleteByUidAndTimestamp(long j, long j2, Continuation<? super Unit> continuation);

    AiChatEntity queryAllByUid(long uid);

    Object queryByUidAndTimestamp(long j, long j2, Continuation<? super AiChatEntity> continuation);

    List<AiChatEntity> queryHistoryChatList(long uid, long lastTimestamp, int limit);

    Object updateLikeUnlikeStatus(long j, long j2, boolean z, boolean z2, Continuation<? super Unit> continuation);
}
