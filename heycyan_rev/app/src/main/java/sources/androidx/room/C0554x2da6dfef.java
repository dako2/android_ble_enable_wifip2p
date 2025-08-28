package androidx.room;

import androidx.sqlite.p002db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AutoClosingRoomOpenHelper.kt */
@Metadata(m608k = 3, m609mv = {1, 8, 0}, m611xi = 48)
/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$yieldIfContendedSafely$2 */
/* loaded from: classes.dex */
/* synthetic */ class C0554x2da6dfef extends FunctionReferenceImpl implements Function1<SupportSQLiteDatabase, Boolean> {
    public static final C0554x2da6dfef INSTANCE = new C0554x2da6dfef();

    C0554x2da6dfef() {
        super(1, SupportSQLiteDatabase.class, "yieldIfContendedSafely", "yieldIfContendedSafely()Z", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Boolean invoke(SupportSQLiteDatabase p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return Boolean.valueOf(p0.yieldIfContendedSafely());
    }
}
