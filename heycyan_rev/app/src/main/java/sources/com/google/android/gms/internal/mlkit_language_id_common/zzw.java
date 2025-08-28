package com.google.android.gms.internal.mlkit_language_id_common;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.FileUtils;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes.dex */
public abstract class zzw implements Map, Serializable {

    @CheckForNull
    private transient zzx zza;

    @CheckForNull
    private transient zzx zzb;

    @CheckForNull
    private transient zzq zzc;

    zzw() {
    }

    public static zzw zzc(Object obj, Object obj2) {
        zzn.zza("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
        return zzad.zzg(1, new Object[]{"optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID}, null);
    }

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean containsKey(@CheckForNull Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public final boolean containsValue(@CheckForNull Object obj) {
        return values().contains(obj);
    }

    @Override // java.util.Map
    public final boolean equals(@CheckForNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    @CheckForNull
    public abstract Object get(@CheckForNull Object obj);

    @Override // java.util.Map
    @CheckForNull
    public final Object getOrDefault(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return zzae.zza(entrySet());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set keySet() {
        zzx zzxVar = this.zzb;
        if (zzxVar != null) {
            return zzxVar;
        }
        zzx zzxVarZze = zze();
        this.zzb = zzxVarZze;
        return zzxVarZze;
    }

    @Override // java.util.Map
    @CheckForNull
    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @CheckForNull
    @Deprecated
    public final Object remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        if (size < 0) {
            throw new IllegalArgumentException("size cannot be negative but was: " + size);
        }
        StringBuilder sb = new StringBuilder((int) Math.min(size * 8, FileUtils.ONE_GB));
        sb.append('{');
        boolean z = true;
        for (Map.Entry entry : entrySet()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            z = false;
        }
        sb.append('}');
        return sb.toString();
    }

    abstract zzq zza();

    @Override // java.util.Map
    /* renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzq values() {
        zzq zzqVar = this.zzc;
        if (zzqVar != null) {
            return zzqVar;
        }
        zzq zzqVarZza = zza();
        this.zzc = zzqVarZza;
        return zzqVarZza;
    }

    abstract zzx zzd();

    abstract zzx zze();

    @Override // java.util.Map
    /* renamed from: zzf, reason: merged with bridge method [inline-methods] */
    public final zzx entrySet() {
        zzx zzxVar = this.zza;
        if (zzxVar != null) {
            return zzxVar;
        }
        zzx zzxVarZzd = zzd();
        this.zza = zzxVarZzd;
        return zzxVarZzd;
    }
}
