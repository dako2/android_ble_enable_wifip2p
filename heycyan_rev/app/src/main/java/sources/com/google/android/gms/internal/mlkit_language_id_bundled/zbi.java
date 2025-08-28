package com.google.android.gms.internal.mlkit_language_id_bundled;

import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:language-id@@17.0.4 */
/* loaded from: classes.dex */
final class zbi extends zbj {
    final transient int zba;
    final transient int zbb;
    final /* synthetic */ zbj zbc;

    zbi(zbj zbjVar, int i, int i2) {
        this.zbc = zbjVar;
        this.zba = i;
        this.zbb = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zbd.zba(i, this.zbb, "index");
        return this.zbc.get(i + this.zba);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zbb;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbj, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbg
    final int zbb() {
        return this.zbc.zbc() + this.zba + this.zbb;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbg
    final int zbc() {
        return this.zbc.zbc() + this.zba;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbg
    @CheckForNull
    final Object[] zbe() {
        return this.zbc.zbe();
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbj
    /* renamed from: zbf */
    public final zbj subList(int i, int i2) {
        zbd.zbc(i, i2, this.zbb);
        zbj zbjVar = this.zbc;
        int i3 = this.zba;
        return zbjVar.subList(i + i3, i2 + i3);
    }
}
