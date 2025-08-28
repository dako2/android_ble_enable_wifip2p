package com.glasssutdio.wear.database.dao;

import com.glasssutdio.wear.database.entity.TranslateEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: TranslateDao.kt */
@Metadata(m606d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\bJ'\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH§@ø\u0001\u0000¢\u0006\u0002\u0010\rJ!\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\bJ/\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J1\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J)\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0013H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, m607d2 = {"Lcom/glasssutdio/wear/database/dao/TranslateDao;", "Lcom/glasssutdio/wear/database/dao/BaseDao;", "Lcom/glasssutdio/wear/database/entity/TranslateEntity;", "deleteByUidAndTimestamp", "", "uid", "", "createTime", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryAll", "", "originType", "", "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryByCreateTime", "queryByUidCreateTime", "(JIJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTranslate", "srcContent", "", "dstContent", "(JJLjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTranslateTitle", "translate", "(JJLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public interface TranslateDao extends BaseDao<TranslateEntity> {
    Object deleteByUidAndTimestamp(long j, long j2, Continuation<? super Unit> continuation);

    Object queryAll(long j, int i, Continuation<? super List<TranslateEntity>> continuation);

    Object queryByCreateTime(long j, long j2, Continuation<? super TranslateEntity> continuation);

    Object queryByUidCreateTime(long j, int i, long j2, Continuation<? super List<TranslateEntity>> continuation);

    Object updateTranslate(long j, long j2, String str, String str2, Continuation<? super Unit> continuation);

    Object updateTranslateTitle(long j, long j2, String str, Continuation<? super Unit> continuation);
}
