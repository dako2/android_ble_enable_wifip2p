package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.ClientCookie;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
final class zzer implements ObjectEncoder {
    static final zzer zza = new zzer();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;
    private static final FieldDescriptor zzh;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("renderer");
        zzbf zzbfVar = new zzbf();
        zzbfVar.zza(1);
        zzb = builder.withProperty(zzbfVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("vendor");
        zzbf zzbfVar2 = new zzbf();
        zzbfVar2.zza(2);
        zzc = builder2.withProperty(zzbfVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder(ClientCookie.VERSION_ATTR);
        zzbf zzbfVar3 = new zzbf();
        zzbfVar3.zza(3);
        zzd = builder3.withProperty(zzbfVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("maxImages");
        zzbf zzbfVar4 = new zzbf();
        zzbfVar4.zza(4);
        zze = builder4.withProperty(zzbfVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("maxSsbo");
        zzbf zzbfVar5 = new zzbf();
        zzbfVar5.zza(5);
        zzf = builder5.withProperty(zzbfVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("workGroupSizes");
        zzbf zzbfVar6 = new zzbf();
        zzbfVar6.zza(6);
        zzg = builder6.withProperty(zzbfVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("errorCode");
        zzbf zzbfVar7 = new zzbf();
        zzbfVar7.zza(7);
        zzh = builder7.withProperty(zzbfVar7.zzb()).build();
    }

    private zzer() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
