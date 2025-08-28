package androidx.profileinstaller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.os.Build;
import androidx.concurrent.futures.ResolvableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* loaded from: classes.dex */
public final class ProfileVerifier {
    private static final String CUR_PROFILES_BASE_DIR = "/data/misc/profiles/cur/0/";
    private static final String PROFILE_FILE_NAME = "primary.prof";
    private static final String PROFILE_INSTALLED_CACHE_FILE_NAME = "profileInstalled";
    private static final String REF_PROFILES_BASE_DIR = "/data/misc/profiles/ref/";
    private static final String TAG = "ProfileVerifier";
    private static final ResolvableFuture<CompilationStatus> sFuture = ResolvableFuture.create();
    private static final Object SYNC_OBJ = new Object();
    private static CompilationStatus sCompilationStatus = null;

    private ProfileVerifier() {
    }

    public static CompilationStatus writeProfileVerification(Context context) {
        return writeProfileVerification(context, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x00fd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x00ac A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x002d A[Catch: all -> 0x003f, IOException -> 0x0042, TRY_ENTER, TRY_LEAVE, TryCatch #5 {IOException -> 0x0042, blocks: (B:14:0x0016, B:21:0x002d, B:31:0x003e, B:30:0x003b), top: B:115:0x0016 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static CompilationStatus writeProfileVerification(Context context, boolean z) {
        int i;
        boolean z2;
        boolean z3;
        long length;
        boolean z4;
        File file;
        Cache fromFile;
        Cache cache;
        AssetFileDescriptor assetFileDescriptorOpenFd;
        CompilationStatus compilationStatus;
        if (!z && (compilationStatus = sCompilationStatus) != null) {
            return compilationStatus;
        }
        synchronized (SYNC_OBJ) {
            if (!z) {
                CompilationStatus compilationStatus2 = sCompilationStatus;
                if (compilationStatus2 != null) {
                    return compilationStatus2;
                }
                i = 0;
                try {
                    assetFileDescriptorOpenFd = context.getAssets().openFd("dexopt/baseline.prof");
                    try {
                        z2 = assetFileDescriptorOpenFd.getLength() <= 0;
                        if (assetFileDescriptorOpenFd != null) {
                            assetFileDescriptorOpenFd.close();
                        }
                    } finally {
                    }
                } catch (IOException unused) {
                    z2 = false;
                }
                if (Build.VERSION.SDK_INT >= 28 && Build.VERSION.SDK_INT != 30) {
                    File file2 = new File(new File(REF_PROFILES_BASE_DIR, context.getPackageName()), PROFILE_FILE_NAME);
                    long length2 = file2.length();
                    z3 = !file2.exists() && length2 > 0;
                    File file3 = new File(new File(CUR_PROFILES_BASE_DIR, context.getPackageName()), PROFILE_FILE_NAME);
                    length = file3.length();
                    z4 = !file3.exists() && length > 0;
                    try {
                        long packageLastUpdateTime = getPackageLastUpdateTime(context);
                        file = new File(context.getFilesDir(), PROFILE_INSTALLED_CACHE_FILE_NAME);
                        if (file.exists()) {
                            fromFile = null;
                        } else {
                            try {
                                fromFile = Cache.readFromFile(file);
                            } catch (IOException unused2) {
                                return setCompilationStatus(131072, z3, z4, z2);
                            }
                        }
                        if (fromFile == null && fromFile.mPackageLastUpdateTime == packageLastUpdateTime && fromFile.mResultCode != 2) {
                            i = fromFile.mResultCode;
                        } else if (z2) {
                            i = CompilationStatus.RESULT_CODE_ERROR_NO_PROFILE_EMBEDDED;
                        } else if (z3) {
                            i = 1;
                        } else if (z4) {
                            i = 2;
                        }
                        if (z && z4 && i != 1) {
                            i = 2;
                        }
                        if (fromFile != null && fromFile.mResultCode == 2 && i == 1 && length2 < fromFile.mInstalledCurrentProfileSize) {
                            i = 3;
                        }
                        cache = new Cache(1, i, packageLastUpdateTime, length);
                        if (fromFile != null || !fromFile.equals(cache)) {
                            try {
                                cache.writeOnFile(file);
                            } catch (IOException unused3) {
                                i = CompilationStatus.f83xf2722a21;
                            }
                        }
                        return setCompilationStatus(i, z3, z4, z2);
                    } catch (PackageManager.NameNotFoundException unused4) {
                        return setCompilationStatus(65536, z3, z4, z2);
                    }
                }
                return setCompilationStatus(262144, false, false, z2);
            }
            i = 0;
            assetFileDescriptorOpenFd = context.getAssets().openFd("dexopt/baseline.prof");
            if (assetFileDescriptorOpenFd.getLength() <= 0) {
            }
            if (assetFileDescriptorOpenFd != null) {
            }
            if (Build.VERSION.SDK_INT >= 28) {
                File file22 = new File(new File(REF_PROFILES_BASE_DIR, context.getPackageName()), PROFILE_FILE_NAME);
                long length22 = file22.length();
                if (file22.exists()) {
                    File file32 = new File(new File(CUR_PROFILES_BASE_DIR, context.getPackageName()), PROFILE_FILE_NAME);
                    length = file32.length();
                    if (file32.exists()) {
                        long packageLastUpdateTime2 = getPackageLastUpdateTime(context);
                        file = new File(context.getFilesDir(), PROFILE_INSTALLED_CACHE_FILE_NAME);
                        if (file.exists()) {
                        }
                        if (fromFile == null) {
                            if (z2) {
                            }
                        }
                        if (z) {
                            i = 2;
                        }
                        if (fromFile != null) {
                            i = 3;
                        }
                        cache = new Cache(1, i, packageLastUpdateTime2, length);
                        if (fromFile != null) {
                            cache.writeOnFile(file);
                        }
                        return setCompilationStatus(i, z3, z4, z2);
                    }
                }
            }
            return setCompilationStatus(262144, false, false, z2);
        }
    }

    private static CompilationStatus setCompilationStatus(int i, boolean z, boolean z2, boolean z3) {
        CompilationStatus compilationStatus = new CompilationStatus(i, z, z2, z3);
        sCompilationStatus = compilationStatus;
        sFuture.set(compilationStatus);
        return sCompilationStatus;
    }

    private static long getPackageLastUpdateTime(Context context) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        if (Build.VERSION.SDK_INT >= 33) {
            return Api33Impl.getPackageInfo(packageManager, context).lastUpdateTime;
        }
        return packageManager.getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
    }

    public static ListenableFuture<CompilationStatus> getCompilationStatusAsync() {
        return sFuture;
    }

    static class Cache {
        private static final int SCHEMA = 1;
        final long mInstalledCurrentProfileSize;
        final long mPackageLastUpdateTime;
        final int mResultCode;
        final int mSchema;

        Cache(int i, int i2, long j, long j2) {
            this.mSchema = i;
            this.mResultCode = i2;
            this.mPackageLastUpdateTime = j;
            this.mInstalledCurrentProfileSize = j2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof Cache)) {
                return false;
            }
            Cache cache = (Cache) obj;
            return this.mResultCode == cache.mResultCode && this.mPackageLastUpdateTime == cache.mPackageLastUpdateTime && this.mSchema == cache.mSchema && this.mInstalledCurrentProfileSize == cache.mInstalledCurrentProfileSize;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.mResultCode), Long.valueOf(this.mPackageLastUpdateTime), Integer.valueOf(this.mSchema), Long.valueOf(this.mInstalledCurrentProfileSize));
        }

        void writeOnFile(File file) throws IOException {
            file.delete();
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            try {
                dataOutputStream.writeInt(this.mSchema);
                dataOutputStream.writeInt(this.mResultCode);
                dataOutputStream.writeLong(this.mPackageLastUpdateTime);
                dataOutputStream.writeLong(this.mInstalledCurrentProfileSize);
                dataOutputStream.close();
            } catch (Throwable th) {
                try {
                    dataOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }

        static Cache readFromFile(File file) throws IOException {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            try {
                Cache cache = new Cache(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readLong(), dataInputStream.readLong());
                dataInputStream.close();
                return cache;
            } catch (Throwable th) {
                try {
                    dataInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    public static class CompilationStatus {
        public static final int RESULT_CODE_COMPILED_WITH_PROFILE = 1;
        public static final int RESULT_CODE_COMPILED_WITH_PROFILE_NON_MATCHING = 3;
        public static final int RESULT_CODE_ERROR_CACHE_FILE_EXISTS_BUT_CANNOT_BE_READ = 131072;

        /* renamed from: RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE */
        public static final int f83xf2722a21 = 196608;
        private static final int RESULT_CODE_ERROR_CODE_BIT_SHIFT = 16;
        public static final int RESULT_CODE_ERROR_NO_PROFILE_EMBEDDED = 327680;
        public static final int RESULT_CODE_ERROR_PACKAGE_NAME_DOES_NOT_EXIST = 65536;
        public static final int RESULT_CODE_ERROR_UNSUPPORTED_API_VERSION = 262144;

        @Deprecated
        public static final int RESULT_CODE_NO_PROFILE = 0;
        public static final int RESULT_CODE_NO_PROFILE_INSTALLED = 0;
        public static final int RESULT_CODE_PROFILE_ENQUEUED_FOR_COMPILATION = 2;
        private final boolean mHasCurrentProfile;
        private final boolean mHasEmbeddedProfile;
        private final boolean mHasReferenceProfile;
        final int mResultCode;

        @Retention(RetentionPolicy.SOURCE)
        public @interface ResultCode {
        }

        CompilationStatus(int i, boolean z, boolean z2, boolean z3) {
            this.mResultCode = i;
            this.mHasCurrentProfile = z2;
            this.mHasReferenceProfile = z;
            this.mHasEmbeddedProfile = z3;
        }

        public int getProfileInstallResultCode() {
            return this.mResultCode;
        }

        public boolean isCompiledWithProfile() {
            return this.mHasReferenceProfile;
        }

        public boolean hasProfileEnqueuedForCompilation() {
            return this.mHasCurrentProfile;
        }

        public boolean appApkHasEmbeddedProfile() {
            return this.mHasEmbeddedProfile;
        }
    }

    private static class Api33Impl {
        private Api33Impl() {
        }

        static PackageInfo getPackageInfo(PackageManager packageManager, Context context) throws PackageManager.NameNotFoundException {
            return packageManager.getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(0L));
        }
    }
}
