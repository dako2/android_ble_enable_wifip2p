package com.google.mlkit.common.internal.model;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzad;
import com.google.android.gms.internal.mlkit_common.zzi;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.digest.MessageDigestAlgorithms;
import com.google.mlkit.common.model.LocalModel;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.mlkit:common@@18.4.0 */
/* loaded from: classes2.dex */
public class ModelUtils {
    private static final GmsLogger zza = new GmsLogger("ModelUtils", "");

    /* compiled from: com.google.mlkit:common@@18.4.0 */
    public static abstract class AutoMLManifest {
        public abstract String getLabelsFile();

        public abstract String getModelFile();

        public abstract String getModelType();
    }

    /* compiled from: com.google.mlkit:common@@18.4.0 */
    public static abstract class ModelLoggingInfo {
        static ModelLoggingInfo zza(long j, String str, boolean z) {
            return new AutoValue_ModelUtils_ModelLoggingInfo(j, zzad.zzb(str), z);
        }

        public abstract String getHash();

        public abstract long getSize();

        public abstract boolean isManifestModel();
    }

    private ModelUtils() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0106 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ModelLoggingInfo getModelLoggingInfo(Context context, LocalModel localModel) throws Throwable {
        long length;
        Throwable th;
        IOException e;
        InputStream inputStreamOpen;
        String strZzc;
        String assetFilePath = localModel.getAssetFilePath();
        String absoluteFilePath = localModel.getAbsoluteFilePath();
        Uri uri = localModel.getUri();
        ?? r5 = 0;
        if (assetFilePath != null) {
            if (localModel.isManifestFile() && (assetFilePath = zzb(context, assetFilePath, true)) == null) {
                return null;
            }
            try {
                AssetFileDescriptor assetFileDescriptorOpenFd = context.getAssets().openFd(assetFilePath);
                try {
                    length = assetFileDescriptorOpenFd.getLength();
                    if (assetFileDescriptorOpenFd != null) {
                        assetFileDescriptorOpenFd.close();
                    }
                } finally {
                }
            } catch (IOException e2) {
                zza.m190e("ModelUtils", "Failed to open model file", e2);
                return null;
            }
        } else if (absoluteFilePath != null) {
            if (localModel.isManifestFile() && (absoluteFilePath = zzb(context, absoluteFilePath, false)) == null) {
                return null;
            }
            length = new File(absoluteFilePath).length();
        } else {
            if (uri == null) {
                zza.m189e("ModelUtils", "Local model doesn't have any valid path.");
                return null;
            }
            try {
                AssetFileDescriptor assetFileDescriptorZza = zzi.zza(context, uri, "r");
                try {
                    length = assetFileDescriptorZza.getLength();
                    if (assetFileDescriptorZza != null) {
                        assetFileDescriptorZza.close();
                    }
                } finally {
                }
            } catch (IOException e3) {
                zza.m190e("ModelUtils", "Failed to open model file", e3);
                return null;
            }
        }
        SharedPrefManager sharedPrefManager = (SharedPrefManager) MlKitContext.getInstance().get(SharedPrefManager.class);
        String string = assetFilePath != null ? assetFilePath : absoluteFilePath != null ? absoluteFilePath : ((Uri) Preconditions.checkNotNull(uri)).toString();
        String strZza = sharedPrefManager.zza(string, length);
        if (strZza != null) {
            return ModelLoggingInfo.zza(length, strZza, localModel.isManifestFile());
        }
        try {
            try {
                inputStreamOpen = assetFilePath != null ? context.getAssets().open(assetFilePath) : absoluteFilePath != null ? new FileInputStream(new File(absoluteFilePath)) : zzi.zzb(context, (Uri) Preconditions.checkNotNull(uri));
                if (inputStreamOpen != null) {
                    try {
                        strZzc = zzc(inputStreamOpen);
                    } catch (IOException e4) {
                        e = e4;
                        zza.m190e("ModelUtils", "Failed to open model file", e);
                        if (inputStreamOpen != null) {
                            try {
                                inputStreamOpen.close();
                            } catch (IOException e5) {
                                zza.m190e("ModelUtils", "Failed to close model file", e5);
                            }
                        }
                        return null;
                    }
                } else {
                    strZzc = null;
                }
                if (strZzc != null) {
                    sharedPrefManager.zzb(string, length, strZzc);
                }
                ModelLoggingInfo modelLoggingInfoZza = ModelLoggingInfo.zza(length, strZzc, localModel.isManifestFile());
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                    } catch (IOException e6) {
                        zza.m190e("ModelUtils", "Failed to close model file", e6);
                    }
                }
                return modelLoggingInfoZza;
            } catch (Throwable th2) {
                th = th2;
                r5 = context;
                if (r5 != 0) {
                    try {
                        r5.close();
                    } catch (IOException e7) {
                        zza.m190e("ModelUtils", "Failed to close model file", e7);
                    }
                }
                throw th;
            }
        } catch (IOException e8) {
            e = e8;
            inputStreamOpen = null;
        } catch (Throwable th3) {
            th = th3;
            if (r5 != 0) {
            }
            throw th;
        }
    }

    public static String getSHA256(File file) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                String strZzc = zzc(fileInputStream);
                fileInputStream.close();
                return strZzc;
            } finally {
            }
        } catch (IOException e) {
            zza.m189e("ModelUtils", "Failed to create FileInputStream for model: ".concat(e.toString()));
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x002d, code lost:
    
        if (new java.io.File(r6).exists() == false) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static AutoMLManifest parseManifestFile(String str, boolean z, Context context) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        byte[] bArr;
        GmsLogger gmsLogger = zza;
        gmsLogger.m187d("ModelUtils", "Manifest file path: ".concat(String.valueOf(str)));
        if (z) {
            try {
                InputStream inputStreamOpen = context.getAssets().open(str);
                if (inputStreamOpen != null) {
                    inputStreamOpen.close();
                }
                try {
                    if (str.isEmpty()) {
                        bArr = new byte[0];
                    } else {
                        InputStream inputStreamOpen2 = z ? context.getAssets().open(str) : new FileInputStream(new File(str));
                        try {
                            int iAvailable = inputStreamOpen2.available();
                            byte[] bArr2 = new byte[iAvailable];
                            inputStreamOpen2.read(bArr2, 0, iAvailable);
                            if (inputStreamOpen2 != null) {
                                inputStreamOpen2.close();
                            }
                            bArr = bArr2;
                        } finally {
                        }
                    }
                    String str2 = new String(bArr, "UTF-8");
                    gmsLogger.m187d("ModelUtils", "Json string from the manifest file: ".concat(str2));
                    JSONObject jSONObject = new JSONObject(str2);
                    return new AutoValue_ModelUtils_AutoMLManifest(jSONObject.getString("modelType"), jSONObject.getString("modelFile"), jSONObject.getString("labelsFile"));
                } catch (IOException | JSONException e) {
                    zza.m190e("ModelUtils", "Error parsing the manifest file.", e);
                    return null;
                }
            } catch (IOException unused) {
                zza.m189e("ModelUtils", "Manifest file does not exist.");
                return null;
            }
        }
    }

    public static boolean zza(File file, String str) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        String sha256 = getSHA256(file);
        zza.m187d("ModelUtils", "Calculated hash value is: ".concat(String.valueOf(sha256)));
        return str.equals(sha256);
    }

    private static String zzb(Context context, String str, boolean z) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        AutoMLManifest manifestFile = parseManifestFile(str, z, context);
        if (manifestFile != null) {
            return new File(new File(str).getParent(), manifestFile.getModelFile()).toString();
        }
        zza.m189e("ModelUtils", "Failed to parse manifest file.");
        return null;
    }

    private static String zzc(InputStream inputStream) throws NoSuchAlgorithmException, IOException {
        int i;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256);
            byte[] bArr = new byte[1048576];
            while (true) {
                int i2 = inputStream.read(bArr);
                if (i2 == -1) {
                    break;
                }
                messageDigest.update(bArr, 0, i2);
            }
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (IOException unused) {
            zza.m189e("ModelUtils", "Failed to read model file");
            return null;
        } catch (NoSuchAlgorithmException unused2) {
            zza.m189e("ModelUtils", "Do not have SHA-256 algorithm");
            return null;
        }
    }
}
