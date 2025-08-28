package com.google.android.gms.internal.mlkit_language_id_bundled;

/* compiled from: com.google.mlkit:language-id@@17.0.4 */
/* loaded from: classes.dex */
final class zbk extends zbj {
    static final zbj zba = new zbk(new Object[0], 0);
    final transient Object[] zbb;
    private final transient int zbc;

    zbk(Object[] objArr, int i) {
        this.zbb = objArr;
        this.zbc = i;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zbd.zba(i, this.zbc, "index");
        Object obj = this.zbb[i];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zbc;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbj, com.google.android.gms.internal.mlkit_language_id_bundled.zbg
    final int zba(Object[] objArr, int i) {
        System.arraycopy(this.zbb, 0, objArr, 0, this.zbc);
        return this.zbc;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbg
    final int zbb() {
        return this.zbc;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbg
    final int zbc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbg
    final Object[] zbe() {
        return this.zbb;
    }
}
