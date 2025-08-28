package com.glasssutdio.wear.depository;

import com.glasssutdio.wear.GlassApplication;
import com.glasssutdio.wear.database.GlassDatabase;
import com.glasssutdio.wear.database.dao.TranslateDao;
import com.glasssutdio.wear.database.entity.TranslateEntity;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TranslateDepository.kt */
@Metadata(m606d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ!\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\rJ'\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J!\u0010\u0013\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\rJ/\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u000bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J1\u0010\u0016\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ)\u0010\u001b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u0018H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, m607d2 = {"Lcom/glasssutdio/wear/depository/TranslateDepository;", "", "()V", "translateDao", "Lcom/glasssutdio/wear/database/dao/TranslateDao;", "addTranslate", "", "timer", "Lcom/glasssutdio/wear/database/entity/TranslateEntity;", "deleteByUidAndTimestamp", "uid", "", "createTime", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTranslateHistory", "", "originType", "", "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryByCreateTime", "queryByUidCreateTime", "(JIJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTranslate", "srcContent", "", "dstContent", "(JJLjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTranslateTitle", "translateTitle", "(JJLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class TranslateDepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<TranslateDepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<TranslateDepository>() { // from class: com.glasssutdio.wear.depository.TranslateDepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TranslateDepository invoke() {
            return new TranslateDepository();
        }
    });
    private final TranslateDao translateDao = GlassDatabase.INSTANCE.getDatabase(GlassApplication.INSTANCE.getCONTEXT()).translateDao();

    public final Object getAllTranslateHistory(long j, int i, Continuation<? super List<TranslateEntity>> continuation) {
        return this.translateDao.queryAll(j, i, continuation);
    }

    public final Object queryByUidCreateTime(long j, int i, long j2, Continuation<? super List<TranslateEntity>> continuation) {
        return this.translateDao.queryByUidCreateTime(j, i, j2, continuation);
    }

    public final Object deleteByUidAndTimestamp(long j, long j2, Continuation<? super Unit> continuation) {
        Object objDeleteByUidAndTimestamp = this.translateDao.deleteByUidAndTimestamp(j, j2, continuation);
        return objDeleteByUidAndTimestamp == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDeleteByUidAndTimestamp : Unit.INSTANCE;
    }

    public final Object queryByCreateTime(long j, long j2, Continuation<? super TranslateEntity> continuation) {
        return this.translateDao.queryByCreateTime(j, j2, continuation);
    }

    public final Object updateTranslateTitle(long j, long j2, String str, Continuation<? super Unit> continuation) {
        Object objUpdateTranslateTitle = this.translateDao.updateTranslateTitle(j, j2, str, continuation);
        return objUpdateTranslateTitle == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objUpdateTranslateTitle : Unit.INSTANCE;
    }

    public final Object updateTranslate(long j, long j2, String str, String str2, Continuation<? super Unit> continuation) {
        Object objUpdateTranslate = this.translateDao.updateTranslate(j, j2, str, str2, continuation);
        return objUpdateTranslate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objUpdateTranslate : Unit.INSTANCE;
    }

    public final void addTranslate(TranslateEntity timer) {
        Intrinsics.checkNotNullParameter(timer, "timer");
        this.translateDao.insert(timer);
    }

    /* compiled from: TranslateDepository.kt */
    @Metadata(m606d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m607d2 = {"Lcom/glasssutdio/wear/depository/TranslateDepository$Companion;", "", "()V", "getInstance", "Lcom/glasssutdio/wear/depository/TranslateDepository;", "getGetInstance", "()Lcom/glasssutdio/wear/depository/TranslateDepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TranslateDepository getGetInstance() {
            return (TranslateDepository) TranslateDepository.getInstance$delegate.getValue();
        }
    }
}
