package com.google.mlkit.common.internal.model;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzia;
import com.google.android.gms.internal.mlkit_common.zzik;
import com.google.android.gms.internal.mlkit_common.zziu;
import com.google.android.gms.internal.mlkit_common.zziv;
import com.google.android.gms.internal.mlkit_common.zzjd;
import com.google.android.gms.internal.mlkit_common.zzmi;
import com.google.android.gms.internal.mlkit_common.zzml;
import com.google.android.gms.internal.mlkit_common.zzmt;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import com.google.mlkit.common.sdkinternal.model.ModelInfoRetrieverInterop;
import com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager;
import com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager;
import com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface;
import java.util.Set;
import java.util.concurrent.Callable;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes2.dex */
public final class zzg implements RemoteModelManagerInterface {
    private final MlKitContext zza;
    private final zzmi zzb;

    public zzg(MlKitContext mlKitContext) {
        zzmi zzmiVarZzb = zzmt.zzb("common");
        this.zza = mlKitContext;
        this.zzb = zzmiVarZzb;
    }

    private final RemoteModelDownloadManager zze(CustomRemoteModel customRemoteModel) {
        RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(this.zza, customRemoteModel, null, new ModelFileHelper(this.zza), new zza(this.zza, customRemoteModel.getUniqueModelNameForPersist()));
        MlKitContext mlKitContext = this.zza;
        return RemoteModelDownloadManager.getInstance(mlKitContext, customRemoteModel, new ModelFileHelper(mlKitContext), remoteModelFileManager, (ModelInfoRetrieverInterop) mlKitContext.get(ModelInfoRetrieverInterop.class));
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface
    public final /* bridge */ /* synthetic */ Task deleteDownloadedModel(RemoteModel remoteModel) {
        final CustomRemoteModel customRemoteModel = (CustomRemoteModel) remoteModel;
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.mlkit.common.internal.model.zzc
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzb(customRemoteModel, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener() { // from class: com.google.mlkit.common.internal.model.zzd
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.zza.zzc(task);
            }
        });
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface
    public final /* bridge */ /* synthetic */ Task download(RemoteModel remoteModel, DownloadConditions downloadConditions) {
        final RemoteModelDownloadManager remoteModelDownloadManagerZze = zze((CustomRemoteModel) remoteModel);
        remoteModelDownloadManagerZze.setDownloadConditions(downloadConditions);
        return Tasks.forResult(null).onSuccessTask(MLTaskExecutor.workerThreadExecutor(), new SuccessContinuation() { // from class: com.google.mlkit.common.internal.model.zzb
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return remoteModelDownloadManagerZze.ensureModelDownloaded();
            }
        });
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface
    public final Task<Set<CustomRemoteModel>> getDownloadedModels() {
        return Tasks.forException(new MlKitException("Custom Remote model does not support listing downloaded models", 12));
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface
    public final /* bridge */ /* synthetic */ Task isModelDownloaded(RemoteModel remoteModel) {
        final CustomRemoteModel customRemoteModel = (CustomRemoteModel) remoteModel;
        return MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.mlkit.common.internal.model.zze
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zza.zza(customRemoteModel);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: com.google.mlkit.common.internal.model.zzf
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.zza.zzd(task);
            }
        });
    }

    final /* synthetic */ Boolean zza(CustomRemoteModel customRemoteModel) throws Exception {
        return Boolean.valueOf(zze(customRemoteModel).isModelDownloadedAndValid());
    }

    final /* synthetic */ void zzb(CustomRemoteModel customRemoteModel, TaskCompletionSource taskCompletionSource) {
        try {
            new ModelFileHelper(this.zza).deleteAllModels(ModelType.CUSTOM, (String) Preconditions.checkNotNull(customRemoteModel.getModelName()));
            taskCompletionSource.setResult(null);
        } catch (RuntimeException e) {
            taskCompletionSource.setException(new MlKitException("Internal error has occurred when executing ML Kit tasks", 13, e));
        }
    }

    final /* synthetic */ void zzc(Task task) {
        boolean zIsSuccessful = task.isSuccessful();
        zzmi zzmiVar = this.zzb;
        zziv zzivVar = new zziv();
        zzia zziaVar = new zzia();
        zziaVar.zzb(zzjd.CUSTOM);
        zziaVar.zza(Boolean.valueOf(zIsSuccessful));
        zzivVar.zze(zziaVar.zzc());
        zzmiVar.zzd(zzml.zzf(zzivVar), zziu.REMOTE_MODEL_DELETE_ON_DEVICE);
    }

    final /* synthetic */ void zzd(Task task) {
        boolean zBooleanValue = ((Boolean) task.getResult()).booleanValue();
        zzmi zzmiVar = this.zzb;
        zziv zzivVar = new zziv();
        zzik zzikVar = new zzik();
        zzikVar.zzb(zzjd.CUSTOM);
        zzikVar.zza(Boolean.valueOf(zBooleanValue));
        zzivVar.zzg(zzikVar.zzc());
        zzmiVar.zzd(zzml.zzf(zzivVar), zziu.REMOTE_MODEL_IS_DOWNLOADED);
    }
}
