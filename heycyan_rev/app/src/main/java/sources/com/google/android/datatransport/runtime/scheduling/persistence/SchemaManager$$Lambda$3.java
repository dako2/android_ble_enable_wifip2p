package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager;

/*  JADX ERROR: ConcurrentModificationException in pass: FixAccessModifiers
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1013)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:967)
    	at jadx.core.dex.visitors.fixaccessmodifiers.FixAccessModifiers.fixClassVisibility(FixAccessModifiers.java:69)
    	at jadx.core.dex.visitors.fixaccessmodifiers.FixAccessModifiers.visit(FixAccessModifiers.java:45)
    */
/* loaded from: classes.dex */
final /* synthetic */ class SchemaManager$$Lambda$3 implements SchemaManager.Migration {
    private static final SchemaManager$$Lambda$3 instance = new SchemaManager$$Lambda$3();

    private SchemaManager$$Lambda$3() {
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager.Migration
    public void upgrade(SQLiteDatabase sQLiteDatabase) throws SQLException {
        sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN payload_encoding TEXT");
    }
}
