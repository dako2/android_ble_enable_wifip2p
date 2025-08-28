package com.google.mlkit.common.sdkinternal.model;

import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzmi;
import com.google.android.gms.internal.mlkit_common.zzml;
import com.google.android.gms.internal.mlkit_common.zzmt;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.LocalModel;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.Constants;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes2.dex */
public class CustomModelLoader {
    private static final GmsLogger zza = new GmsLogger("CustomModelLoader", "");
    private static final Map zzb = new HashMap();
    private final MlKitContext zzc;
    private final LocalModel zzd;
    private final CustomRemoteModel zze;
    private final RemoteModelDownloadManager zzf;
    private final RemoteModelFileManager zzg;
    private final zzmi zzh;
    private boolean zzi;

    /* compiled from: com.google.mlkit:common@@18.4.0 */
    public interface CustomModelLoaderHelper {
        void logLoad() throws MlKitException;

        boolean tryLoad(LocalModel localModel) throws MlKitException;
    }

    private CustomModelLoader(MlKitContext mlKitContext, LocalModel localModel, CustomRemoteModel customRemoteModel) {
        if (customRemoteModel != null) {
            RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(mlKitContext, customRemoteModel, null, new ModelFileHelper(mlKitContext), new com.google.mlkit.common.internal.model.zza(mlKitContext, customRemoteModel.getUniqueModelNameForPersist()));
            this.zzg = remoteModelFileManager;
            this.zzf = RemoteModelDownloadManager.getInstance(mlKitContext, customRemoteModel, new ModelFileHelper(mlKitContext), remoteModelFileManager, (ModelInfoRetrieverInterop) mlKitContext.get(ModelInfoRetrieverInterop.class));
            this.zzi = true;
        } else {
            this.zzg = null;
            this.zzf = null;
        }
        this.zzc = mlKitContext;
        this.zzd = localModel;
        this.zze = customRemoteModel;
        this.zzh = zzmt.zzb("common");
    }

    public static synchronized CustomModelLoader getInstance(MlKitContext mlKitContext, LocalModel localModel, CustomRemoteModel customRemoteModel) {
        String string;
        Map map;
        string = customRemoteModel == null ? ((LocalModel) Preconditions.checkNotNull(localModel)).toString() : customRemoteModel.getUniqueModelNameForPersist();
        map = zzb;
        if (!map.containsKey(string)) {
            map.put(string, new CustomModelLoader(mlKitContext, localModel, customRemoteModel));
        }
        return (CustomModelLoader) map.get(string);
    }

    private final File zza() throws MlKitException {
        String strZzb = ((RemoteModelFileManager) Preconditions.checkNotNull(this.zzg)).zzb();
        if (strZzb == null) {
            zza.m187d("CustomModelLoader", "No existing model file");
            return null;
        }
        File file = new File(strZzb);
        File[] fileArrListFiles = file.listFiles();
        return ((File[]) Preconditions.checkNotNull(fileArrListFiles)).length == 1 ? fileArrListFiles[0] : file;
    }

    private final void zzb() throws MlKitException {
        ((RemoteModelDownloadManager) Preconditions.checkNotNull(this.zzf)).removeOrCancelDownload();
    }

    private static final LocalModel zzc(File file) {
        if (file.isDirectory()) {
            LocalModel.Builder builder = new LocalModel.Builder();
            builder.setAbsoluteManifestFilePath(new File(file.getAbsolutePath(), Constants.AUTOML_IMAGE_LABELING_MANIFEST_JSON_FILE_NAME).toString());
            return builder.build();
        }
        LocalModel.Builder builder2 = new LocalModel.Builder();
        builder2.setAbsoluteFilePath(file.getAbsolutePath());
        return builder2.build();
    }

    public synchronized LocalModel createLocalModelByLatestExistingModel() throws MlKitException {
        zza.m187d("CustomModelLoader", "Try to get the latest existing model file.");
        File fileZza = zza();
        if (fileZza == null) {
            return null;
        }
        return zzc(fileZza);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00a3 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a5 A[Catch: all -> 0x00ab, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:8:0x0025, B:10:0x002d, B:25:0x00a5, B:11:0x0031, B:13:0x0050, B:16:0x0059, B:17:0x0072, B:19:0x007a, B:20:0x0096), top: B:31:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized LocalModel createLocalModelByNewlyDownloadedModel() throws MlKitException {
        File fileZzi;
        GmsLogger gmsLogger = zza;
        gmsLogger.m187d("CustomModelLoader", "Try to get newly downloaded model file.");
        Long downloadingId = ((RemoteModelDownloadManager) Preconditions.checkNotNull(this.zzf)).getDownloadingId();
        String downloadingModelHash = this.zzf.getDownloadingModelHash();
        if (downloadingId == null || downloadingModelHash == null) {
            gmsLogger.m187d("CustomModelLoader", "No new model is downloading.");
            zzb();
        } else {
            Integer downloadingModelStatusCode = this.zzf.getDownloadingModelStatusCode();
            if (downloadingModelStatusCode == null) {
                zzb();
            } else {
                new StringBuilder("Download Status code: ").append(downloadingModelStatusCode);
                gmsLogger.m187d("CustomModelLoader", "Download Status code: ".concat(downloadingModelStatusCode.toString()));
                if (downloadingModelStatusCode.intValue() == 8) {
                    fileZzi = this.zzf.zzi(downloadingModelHash);
                    if (fileZzi != null) {
                        gmsLogger.m187d("CustomModelLoader", "Moved the downloaded model to private folder successfully: ".concat(String.valueOf(fileZzi.getParent())));
                        this.zzf.updateLatestModelHashAndType(downloadingModelHash);
                        if (fileZzi != null) {
                            return null;
                        }
                        return zzc(fileZzi);
                    }
                } else if (downloadingModelStatusCode.intValue() == 16) {
                    this.zzh.zze(zzml.zzg(), (RemoteModel) Preconditions.checkNotNull(this.zze), false, this.zzf.getFailureReason(downloadingId));
                    zzb();
                }
            }
        }
        fileZzi = null;
        if (fileZzi != null) {
        }
    }

    public void deleteLatestExistingModel() throws MlKitException {
        File fileZza = zza();
        if (fileZza != null) {
            ((RemoteModelFileManager) Preconditions.checkNotNull(this.zzg)).zzc(fileZza);
            SharedPrefManager.getInstance(this.zzc).clearLatestModelHash((RemoteModel) Preconditions.checkNotNull(this.zze));
        }
    }

    public void deleteOldModels(LocalModel localModel) throws MlKitException {
        File parentFile = new File((String) Preconditions.checkNotNull(localModel.getAbsoluteFilePath())).getParentFile();
        if (!((RemoteModelFileManager) Preconditions.checkNotNull(this.zzg)).zzd((File) Preconditions.checkNotNull(parentFile))) {
            zza.m189e("CustomModelLoader", "Failed to delete old models");
        } else {
            zza.m187d("CustomModelLoader", "All old models are deleted.");
            this.zzg.zza(parentFile);
        }
    }

    public synchronized void load(CustomModelLoaderHelper customModelLoaderHelper) throws MlKitException {
        LocalModel localModelCreateLocalModelByLatestExistingModel = this.zzd;
        if (localModelCreateLocalModelByLatestExistingModel == null) {
            localModelCreateLocalModelByLatestExistingModel = createLocalModelByNewlyDownloadedModel();
        }
        if (localModelCreateLocalModelByLatestExistingModel == null) {
            localModelCreateLocalModelByLatestExistingModel = createLocalModelByLatestExistingModel();
        }
        if (localModelCreateLocalModelByLatestExistingModel == null) {
            throw new MlKitException("Model is not available.", 14);
        }
        while (!customModelLoaderHelper.tryLoad(localModelCreateLocalModelByLatestExistingModel)) {
            if (this.zze != null) {
                deleteLatestExistingModel();
                localModelCreateLocalModelByLatestExistingModel = createLocalModelByLatestExistingModel();
            } else {
                localModelCreateLocalModelByLatestExistingModel = null;
            }
            if (localModelCreateLocalModelByLatestExistingModel == null) {
                customModelLoaderHelper.logLoad();
                return;
            }
        }
        if (this.zze != null && this.zzi) {
            deleteOldModels((LocalModel) Preconditions.checkNotNull(localModelCreateLocalModelByLatestExistingModel));
            this.zzi = false;
        }
        customModelLoaderHelper.logLoad();
    }
}
