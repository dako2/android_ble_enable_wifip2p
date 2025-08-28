package com.google.android.gms.internal.mlkit_common;

import androidx.core.app.NotificationCompat;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes.dex */
final class zzgu implements ObjectEncoder {
    static final zzgu zza = new zzgu();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;
    private static final FieldDescriptor zzh;
    private static final FieldDescriptor zzi;
    private static final FieldDescriptor zzj;
    private static final FieldDescriptor zzk;
    private static final FieldDescriptor zzl;
    private static final FieldDescriptor zzm;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("deviceInfo");
        zzbf zzbfVar = new zzbf();
        zzbfVar.zza(1);
        zzb = builder.withProperty(zzbfVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("nnapiInfo");
        zzbf zzbfVar2 = new zzbf();
        zzbfVar2.zza(2);
        zzc = builder2.withProperty(zzbfVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("gpuInfo");
        zzbf zzbfVar3 = new zzbf();
        zzbfVar3.zza(3);
        zzd = builder3.withProperty(zzbfVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("pipelineIdentifier");
        zzbf zzbfVar4 = new zzbf();
        zzbfVar4.zza(4);
        zze = builder4.withProperty(zzbfVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("acceptedConfigurations");
        zzbf zzbfVar5 = new zzbf();
        zzbfVar5.zza(5);
        zzf = builder5.withProperty(zzbfVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("action");
        zzbf zzbfVar6 = new zzbf();
        zzbfVar6.zza(6);
        zzg = builder6.withProperty(zzbfVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder(NotificationCompat.CATEGORY_STATUS);
        zzbf zzbfVar7 = new zzbf();
        zzbfVar7.zza(7);
        zzh = builder7.withProperty(zzbfVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("customErrors");
        zzbf zzbfVar8 = new zzbf();
        zzbfVar8.zza(8);
        zzi = builder8.withProperty(zzbfVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("benchmarkStatus");
        zzbf zzbfVar9 = new zzbf();
        zzbfVar9.zza(9);
        zzj = builder9.withProperty(zzbfVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("validationTestResult");
        zzbf zzbfVar10 = new zzbf();
        zzbfVar10.zza(10);
        zzk = builder10.withProperty(zzbfVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("timestampUs");
        zzbf zzbfVar11 = new zzbf();
        zzbfVar11.zza(11);
        zzl = builder11.withProperty(zzbfVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("elapsedUs");
        zzbf zzbfVar12 = new zzbf();
        zzbfVar12.zza(12);
        zzm = builder12.withProperty(zzbfVar12.zzb()).build();
    }

    private zzgu() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
