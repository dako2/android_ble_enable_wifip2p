package com.tencent.mmkv;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.util.Log;
import dalvik.annotation.optimization.FastNative;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public class MMKV implements SharedPreferences, SharedPreferences.Editor {
    private static final int ASHMEM_MODE = 8;
    private static final int BACKUP_MODE = 16;
    private static final int CONTEXT_MODE_MULTI_PROCESS = 4;
    public static final int ExpireInDay = 86400;
    public static final int ExpireInHour = 3600;
    public static final int ExpireInMinute = 60;
    public static final int ExpireInMonth = 2592000;
    public static final int ExpireInYear = 946080000;
    public static final int ExpireNever = 0;
    public static final int MULTI_PROCESS_MODE = 2;
    public static final int READ_ONLY_MODE = 32;
    public static final int SINGLE_PROCESS_MODE = 1;
    private static final Set<Long> checkedHandleSet;
    private static MMKVHandler gCallbackHandler;
    private static MMKVContentChangeNotification gContentChangeNotify;
    private static boolean gWantLogReDirecting;
    private static final MMKVLogLevel[] index2LogLevel;
    private static boolean isProcessModeCheckerEnabled;
    private static final EnumMap<MMKVLogLevel, Integer> logLevel2Index;
    private static final HashMap<String, Parcelable.Creator<?>> mCreators;
    private static final EnumMap<MMKVRecoverStrategic, Integer> recoverIndex;
    private static String rootDir;
    private final long nativeHandle;

    public interface LibLoader {
        void loadLibrary(String libName);
    }

    private native long actualSize(long handle);

    private native String[] allKeys(long handle, boolean filterExpire);

    public static native long backupAllToDirectory(String dstDir);

    public static native boolean backupOneToDirectory(String mmapID, String dstDir, String rootPath);

    private static native boolean checkProcessMode(long handle);

    private native boolean containsKey(long handle, String key);

    private native long count(long handle, boolean filterExpire);

    private static native long createNB(int size);

    private native boolean decodeBool(long handle, String key, boolean defaultValue);

    private native byte[] decodeBytes(long handle, String key);

    private native double decodeDouble(long handle, String key, double defaultValue);

    private native float decodeFloat(long handle, String key, float defaultValue);

    private native int decodeInt(long handle, String key, int defaultValue);

    private native long decodeLong(long handle, String key, long defaultValue);

    private native String decodeString(long handle, String key, String defaultValue);

    private native String[] decodeStringSet(long handle, String key);

    private static native void destroyNB(long pointer, int size);

    private native boolean encodeBool(long handle, String key, boolean value);

    private native boolean encodeBool_2(long handle, String key, boolean value, int expireDurationInSecond);

    private native boolean encodeBytes(long handle, String key, byte[] value);

    private native boolean encodeBytes_2(long handle, String key, byte[] value, int expireDurationInSecond);

    private native boolean encodeDouble(long handle, String key, double value);

    private native boolean encodeDouble_2(long handle, String key, double value, int expireDurationInSecond);

    private native boolean encodeFloat(long handle, String key, float value);

    private native boolean encodeFloat_2(long handle, String key, float value, int expireDurationInSecond);

    private native boolean encodeInt(long handle, String key, int value);

    private native boolean encodeInt_2(long handle, String key, int value, int expireDurationInSecond);

    private native boolean encodeLong(long handle, String key, long value);

    private native boolean encodeLong_2(long handle, String key, long value, int expireDurationInSecond);

    private native boolean encodeSet(long handle, String key, String[] value);

    private native boolean encodeSet_2(long handle, String key, String[] value, int expireDurationInSecond);

    private native boolean encodeString(long handle, String key, String value);

    private native boolean encodeString_2(long handle, String key, String value, int expireDurationInSecond);

    private static native long getDefaultMMKV(int mode, String cryptKey);

    private static native long getMMKVWithAshmemFD(String mmapID, int fd, int metaFD, String cryptKey);

    private static native long getMMKVWithID(String mmapID, int mode, String cryptKey, String rootPath, long expectedCapacity);

    private static native long getMMKVWithIDAndSize(String mmapID, int size, int mode, String cryptKey);

    private native boolean isCompareBeforeSetEnabled();

    @FastNative
    private native boolean isEncryptionEnabled();

    @FastNative
    private native boolean isExpirationEnabled();

    public static native boolean isFileValid(String mmapID, String rootPath);

    private static native void jniInitialize(String rootDir2, String cacheDir, int level, boolean wantLogReDirecting);

    @FastNative
    private native void nativeEnableCompareBeforeSet();

    public static native void onExit();

    public static native int pageSize();

    public static native boolean removeStorage(String mmapID, String rootPath);

    private native void removeValueForKey(long handle, String key);

    public static native long restoreAllFromDirectory(String srcDir);

    public static native boolean restoreOneMMKVFromDirectory(String mmapID, String srcDir, String rootPath);

    private static native void setCallbackHandler(boolean logReDirecting, boolean hasCallback);

    private static native void setLogLevel(int level);

    private static native void setWantsContentChangeNotify(boolean needsNotify);

    private native void sync(boolean sync);

    private native long totalSize(long handle);

    private native int valueSize(long handle, String key, boolean actualSize);

    public static native String version();

    private native int writeValueToNB(long handle, String key, long pointer, int size);

    public native int ashmemFD();

    public native int ashmemMetaFD();

    public native void checkContentChangedByOuterProcess();

    public native void checkReSetCryptKey(String cryptKey);

    public native void clearAll();

    public native void clearAllWithKeepingSpace();

    public native void clearMemoryCache();

    public native void close();

    public native String cryptKey();

    public native boolean disableAutoKeyExpire();

    public native void disableCompareBeforeSet();

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        return this;
    }

    public native boolean enableAutoKeyExpire(int expireDurationInSecond);

    public native boolean isMultiProcess();

    public native boolean isReadOnly();

    public native void lock();

    public native String mmapID();

    public native boolean reKey(String cryptKey);

    public native void removeValuesForKeys(String[] arrKeys);

    public native void trim();

    public native boolean tryLock();

    public native void unlock();

    static {
        EnumMap<MMKVRecoverStrategic, Integer> enumMap = new EnumMap<>(MMKVRecoverStrategic.class);
        recoverIndex = enumMap;
        enumMap.put((EnumMap<MMKVRecoverStrategic, Integer>) MMKVRecoverStrategic.OnErrorDiscard, (MMKVRecoverStrategic) 0);
        enumMap.put((EnumMap<MMKVRecoverStrategic, Integer>) MMKVRecoverStrategic.OnErrorRecover, (MMKVRecoverStrategic) 1);
        EnumMap<MMKVLogLevel, Integer> enumMap2 = new EnumMap<>(MMKVLogLevel.class);
        logLevel2Index = enumMap2;
        enumMap2.put((EnumMap<MMKVLogLevel, Integer>) MMKVLogLevel.LevelDebug, (MMKVLogLevel) 0);
        enumMap2.put((EnumMap<MMKVLogLevel, Integer>) MMKVLogLevel.LevelInfo, (MMKVLogLevel) 1);
        enumMap2.put((EnumMap<MMKVLogLevel, Integer>) MMKVLogLevel.LevelWarning, (MMKVLogLevel) 2);
        enumMap2.put((EnumMap<MMKVLogLevel, Integer>) MMKVLogLevel.LevelError, (MMKVLogLevel) 3);
        enumMap2.put((EnumMap<MMKVLogLevel, Integer>) MMKVLogLevel.LevelNone, (MMKVLogLevel) 4);
        index2LogLevel = new MMKVLogLevel[]{MMKVLogLevel.LevelDebug, MMKVLogLevel.LevelInfo, MMKVLogLevel.LevelWarning, MMKVLogLevel.LevelError, MMKVLogLevel.LevelNone};
        checkedHandleSet = new HashSet();
        rootDir = null;
        isProcessModeCheckerEnabled = true;
        mCreators = new HashMap<>();
        gWantLogReDirecting = false;
    }

    public static String initialize(Context context) {
        return initialize(context, context.getFilesDir().getAbsolutePath() + "/mmkv", null, MMKVLogLevel.LevelInfo, null);
    }

    public static String initialize(Context context, MMKVLogLevel logLevel) {
        return initialize(context, context.getFilesDir().getAbsolutePath() + "/mmkv", null, logLevel, null);
    }

    public static String initialize(Context context, LibLoader loader) {
        return initialize(context, context.getFilesDir().getAbsolutePath() + "/mmkv", loader, MMKVLogLevel.LevelInfo, null);
    }

    public static String initialize(Context context, LibLoader loader, MMKVLogLevel logLevel) {
        return initialize(context, context.getFilesDir().getAbsolutePath() + "/mmkv", loader, logLevel, null);
    }

    public static String initialize(Context context, String rootDir2) {
        return initialize(context, rootDir2, null, MMKVLogLevel.LevelInfo, null);
    }

    public static String initialize(Context context, String rootDir2, MMKVLogLevel logLevel) {
        return initialize(context, rootDir2, null, logLevel, null);
    }

    public static String initialize(Context context, String rootDir2, LibLoader loader) {
        return initialize(context, rootDir2, loader, MMKVLogLevel.LevelInfo, null);
    }

    public static String initialize(Context context, String rootDir2, LibLoader loader, MMKVLogLevel logLevel) {
        return initialize(context, rootDir2, loader, logLevel, null);
    }

    public static String initialize(Context context, String rootDir2, LibLoader loader, MMKVLogLevel logLevel, MMKVHandler handler) {
        if (!Process.is64Bit()) {
            throw new UnsupportedArchitectureException("MMKV 2.0+ requires 64-bit App, use 1.3.x instead.");
        }
        if ((context.getApplicationInfo().flags & 2) == 0) {
            disableProcessModeChecker();
        } else {
            enableProcessModeChecker();
        }
        String absolutePath = context.getCacheDir().getAbsolutePath();
        gCallbackHandler = handler;
        if (handler != null && handler.wantLogRedirecting()) {
            gWantLogReDirecting = true;
        }
        String strDoInitialize = doInitialize(rootDir2, absolutePath, loader, logLevel, gWantLogReDirecting);
        if (gCallbackHandler != null) {
            setCallbackHandler(gWantLogReDirecting, true);
        }
        return strDoInitialize;
    }

    private static String doInitialize(String rootDir2, String cacheDir, LibLoader loader, MMKVLogLevel logLevel, boolean wantLogReDirecting) {
        if (loader != null) {
            if (BuildConfig.FLAVOR.equals("SharedCpp")) {
                loader.loadLibrary("c++_shared");
            }
            loader.loadLibrary("mmkv");
        } else {
            if (BuildConfig.FLAVOR.equals("SharedCpp")) {
                System.loadLibrary("c++_shared");
            }
            System.loadLibrary("mmkv");
        }
        jniInitialize(rootDir2, cacheDir, logLevel2Int(logLevel), wantLogReDirecting);
        rootDir = rootDir2;
        return rootDir2;
    }

    @Deprecated
    public static String initialize(String rootDir2) {
        return doInitialize(rootDir2, rootDir2 + "/.tmp", null, MMKVLogLevel.LevelInfo, false);
    }

    @Deprecated
    public static String initialize(String rootDir2, MMKVLogLevel logLevel) {
        return doInitialize(rootDir2, rootDir2 + "/.tmp", null, logLevel, false);
    }

    @Deprecated
    public static String initialize(String rootDir2, LibLoader loader) {
        return doInitialize(rootDir2, rootDir2 + "/.tmp", loader, MMKVLogLevel.LevelInfo, false);
    }

    @Deprecated
    public static String initialize(String rootDir2, LibLoader loader, MMKVLogLevel logLevel) {
        return doInitialize(rootDir2, rootDir2 + "/.tmp", loader, logLevel, false);
    }

    public static String getRootDir() {
        return rootDir;
    }

    /* renamed from: com.tencent.mmkv.MMKV$1 */
    static /* synthetic */ class C24341 {
        static final /* synthetic */ int[] $SwitchMap$com$tencent$mmkv$MMKVLogLevel;

        static {
            int[] iArr = new int[MMKVLogLevel.values().length];
            $SwitchMap$com$tencent$mmkv$MMKVLogLevel = iArr;
            try {
                iArr[MMKVLogLevel.LevelDebug.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tencent$mmkv$MMKVLogLevel[MMKVLogLevel.LevelWarning.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tencent$mmkv$MMKVLogLevel[MMKVLogLevel.LevelError.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tencent$mmkv$MMKVLogLevel[MMKVLogLevel.LevelNone.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tencent$mmkv$MMKVLogLevel[MMKVLogLevel.LevelInfo.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private static int logLevel2Int(MMKVLogLevel level) {
        int i = C24341.$SwitchMap$com$tencent$mmkv$MMKVLogLevel[level.ordinal()];
        if (i == 1) {
            return 0;
        }
        int i2 = 2;
        if (i != 2) {
            i2 = 3;
            if (i != 3) {
                i2 = 4;
                if (i != 4) {
                    return 1;
                }
            }
        }
        return i2;
    }

    public static void setLogLevel(MMKVLogLevel level) {
        setLogLevel(logLevel2Int(level));
    }

    public static MMKV mmkvWithID(String mmapID) throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        return checkProcessMode(getMMKVWithID(mmapID, 1, null, null, 0L), mmapID, 1);
    }

    public static MMKV mmkvWithID(String mmapID, int mode) throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        return checkProcessMode(getMMKVWithID(mmapID, mode, null, null, 0L), mmapID, mode);
    }

    public static MMKV mmkvWithID(String mmapID, int mode, long expectedCapacity) throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        return checkProcessMode(getMMKVWithID(mmapID, mode, null, null, expectedCapacity), mmapID, mode);
    }

    public static MMKV mmkvWithID(String mmapID, int mode, String cryptKey) throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        return checkProcessMode(getMMKVWithID(mmapID, mode, cryptKey, null, 0L), mmapID, mode);
    }

    public static MMKV mmkvWithID(String mmapID, String rootPath) throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        return checkProcessMode(getMMKVWithID(mmapID, 1, null, rootPath, 0L), mmapID, 1);
    }

    public static MMKV mmkvWithID(String mmapID, String rootPath, long expectedCapacity) throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        return checkProcessMode(getMMKVWithID(mmapID, 1, null, rootPath, expectedCapacity), mmapID, 1);
    }

    public static MMKV mmkvWithID(String mmapID, int mode, String cryptKey, String rootPath, long expectedCapacity) throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        return checkProcessMode(getMMKVWithID(mmapID, mode, cryptKey, rootPath, expectedCapacity), mmapID, mode);
    }

    public static MMKV mmkvWithID(String mmapID, int mode, String cryptKey, String rootPath) throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        return checkProcessMode(getMMKVWithID(mmapID, mode, cryptKey, rootPath, 0L), mmapID, mode);
    }

    public static MMKV backedUpMMKVWithID(String mmapID, int mode, String cryptKey, String rootPath) throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        int i = mode | 16;
        return checkProcessMode(getMMKVWithID(mmapID, i, cryptKey, rootPath, 0L), mmapID, i);
    }

    public static MMKV mmkvWithAshmemID(Context context, String mmapID, int size, int mode, String cryptKey) throws RuntimeException {
        MMKV mmkv;
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        String processNameByPID = MMKVContentProvider.getProcessNameByPID(context, Process.myPid());
        if (processNameByPID == null || processNameByPID.length() == 0) {
            simpleLog(MMKVLogLevel.LevelError, "process name detect fail, try again later");
            throw new IllegalStateException("process name detect fail, try again later");
        }
        if (processNameByPID.contains(":")) {
            Uri uriContentUri = MMKVContentProvider.contentUri(context);
            if (uriContentUri == null) {
                simpleLog(MMKVLogLevel.LevelError, "MMKVContentProvider has invalid authority");
                throw new IllegalStateException("MMKVContentProvider has invalid authority");
            }
            simpleLog(MMKVLogLevel.LevelInfo, "getting parcelable mmkv in process, Uri = " + uriContentUri);
            Bundle bundle = new Bundle();
            bundle.putInt("KEY_SIZE", size);
            bundle.putInt("KEY_MODE", mode);
            if (cryptKey != null) {
                bundle.putString("KEY_CRYPT", cryptKey);
            }
            Bundle bundleCall = context.getContentResolver().call(uriContentUri, "mmkvFromAshmemID", mmapID, bundle);
            if (bundleCall != null) {
                bundleCall.setClassLoader(ParcelableMMKV.class.getClassLoader());
                ParcelableMMKV parcelableMMKV = (ParcelableMMKV) bundleCall.getParcelable("KEY");
                if (parcelableMMKV != null && (mmkv = parcelableMMKV.toMMKV()) != null) {
                    simpleLog(MMKVLogLevel.LevelInfo, mmkv.mmapID() + " fd = " + mmkv.ashmemFD() + ", meta fd = " + mmkv.ashmemMetaFD());
                    return mmkv;
                }
            }
        }
        simpleLog(MMKVLogLevel.LevelInfo, "getting mmkv in main process");
        long mMKVWithIDAndSize = getMMKVWithIDAndSize(mmapID, size, mode | 8, cryptKey);
        if (mMKVWithIDAndSize != 0) {
            return new MMKV(mMKVWithIDAndSize);
        }
        throw new IllegalStateException("Fail to create an Ashmem MMKV instance [" + mmapID + "]");
    }

    public static MMKV defaultMMKV() throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        return checkProcessMode(getDefaultMMKV(1, null), "DefaultMMKV", 1);
    }

    public static MMKV defaultMMKV(int mode, String cryptKey) throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        return checkProcessMode(getDefaultMMKV(mode, cryptKey), "DefaultMMKV", mode);
    }

    private static MMKV checkProcessMode(long handle, String mmapID, int mode) throws RuntimeException {
        String str;
        if (handle == 0) {
            throw new RuntimeException("Fail to create an MMKV instance [" + mmapID + "] in JNI");
        }
        if (!isProcessModeCheckerEnabled) {
            return new MMKV(handle);
        }
        Set<Long> set = checkedHandleSet;
        synchronized (set) {
            if (!set.contains(Long.valueOf(handle))) {
                if (!checkProcessMode(handle)) {
                    if (mode == 1) {
                        str = "Opening a multi-process MMKV instance [" + mmapID + "] with SINGLE_PROCESS_MODE!";
                    } else {
                        str = ("Opening an MMKV instance [" + mmapID + "] with MULTI_PROCESS_MODE, ") + "while it's already been opened with SINGLE_PROCESS_MODE by someone somewhere else!";
                    }
                    throw new IllegalArgumentException(str);
                }
                set.add(Long.valueOf(handle));
            }
        }
        return new MMKV(handle);
    }

    public static void enableProcessModeChecker() {
        synchronized (checkedHandleSet) {
            isProcessModeCheckerEnabled = true;
        }
        Log.i("MMKV", "Enable checkProcessMode()");
    }

    public static void disableProcessModeChecker() {
        synchronized (checkedHandleSet) {
            isProcessModeCheckerEnabled = false;
        }
        Log.i("MMKV", "Disable checkProcessMode()");
    }

    public boolean encode(String key, boolean value) {
        return encodeBool(this.nativeHandle, key, value);
    }

    public boolean encode(String key, boolean value, int expireDurationInSecond) {
        return encodeBool_2(this.nativeHandle, key, value, expireDurationInSecond);
    }

    public boolean decodeBool(String key) {
        return decodeBool(this.nativeHandle, key, false);
    }

    public boolean decodeBool(String key, boolean defaultValue) {
        return decodeBool(this.nativeHandle, key, defaultValue);
    }

    public boolean encode(String key, int value) {
        return encodeInt(this.nativeHandle, key, value);
    }

    public boolean encode(String key, int value, int expireDurationInSecond) {
        return encodeInt_2(this.nativeHandle, key, value, expireDurationInSecond);
    }

    public int decodeInt(String key) {
        return decodeInt(this.nativeHandle, key, 0);
    }

    public int decodeInt(String key, int defaultValue) {
        return decodeInt(this.nativeHandle, key, defaultValue);
    }

    public boolean encode(String key, long value) {
        return encodeLong(this.nativeHandle, key, value);
    }

    public boolean encode(String key, long value, int expireDurationInSecond) {
        return encodeLong_2(this.nativeHandle, key, value, expireDurationInSecond);
    }

    public long decodeLong(String key) {
        return decodeLong(this.nativeHandle, key, 0L);
    }

    public long decodeLong(String key, long defaultValue) {
        return decodeLong(this.nativeHandle, key, defaultValue);
    }

    public boolean encode(String key, float value) {
        return encodeFloat(this.nativeHandle, key, value);
    }

    public boolean encode(String key, float value, int expireDurationInSecond) {
        return encodeFloat_2(this.nativeHandle, key, value, expireDurationInSecond);
    }

    public float decodeFloat(String key) {
        return decodeFloat(this.nativeHandle, key, 0.0f);
    }

    public float decodeFloat(String key, float defaultValue) {
        return decodeFloat(this.nativeHandle, key, defaultValue);
    }

    public boolean encode(String key, double value) {
        return encodeDouble(this.nativeHandle, key, value);
    }

    public boolean encode(String key, double value, int expireDurationInSecond) {
        return encodeDouble_2(this.nativeHandle, key, value, expireDurationInSecond);
    }

    public double decodeDouble(String key) {
        return decodeDouble(this.nativeHandle, key, 0.0d);
    }

    public double decodeDouble(String key, double defaultValue) {
        return decodeDouble(this.nativeHandle, key, defaultValue);
    }

    public boolean encode(String key, String value) {
        return encodeString(this.nativeHandle, key, value);
    }

    public boolean encode(String key, String value, int expireDurationInSecond) {
        return encodeString_2(this.nativeHandle, key, value, expireDurationInSecond);
    }

    public String decodeString(String key) {
        return decodeString(this.nativeHandle, key, null);
    }

    public String decodeString(String key, String defaultValue) {
        return decodeString(this.nativeHandle, key, defaultValue);
    }

    public boolean encode(String key, Set<String> value) {
        return encodeSet(this.nativeHandle, key, value == null ? null : (String[]) value.toArray(new String[0]));
    }

    public boolean encode(String key, Set<String> value, int expireDurationInSecond) {
        return encodeSet_2(this.nativeHandle, key, value == null ? null : (String[]) value.toArray(new String[0]), expireDurationInSecond);
    }

    public Set<String> decodeStringSet(String key) {
        return decodeStringSet(key, (Set<String>) null);
    }

    public Set<String> decodeStringSet(String key, Set<String> defaultValue) {
        return decodeStringSet(key, defaultValue, HashSet.class);
    }

    public Set<String> decodeStringSet(String key, Set<String> defaultValue, Class<? extends Set> cls) throws IllegalAccessException, InstantiationException {
        String[] strArrDecodeStringSet = decodeStringSet(this.nativeHandle, key);
        if (strArrDecodeStringSet == null) {
            return defaultValue;
        }
        try {
            Set<String> setNewInstance = cls.newInstance();
            setNewInstance.addAll(Arrays.asList(strArrDecodeStringSet));
            return setNewInstance;
        } catch (IllegalAccessException | InstantiationException unused) {
            return defaultValue;
        }
    }

    public boolean encode(String key, byte[] value) {
        return encodeBytes(this.nativeHandle, key, value);
    }

    public boolean encode(String key, byte[] value, int expireDurationInSecond) {
        return encodeBytes_2(this.nativeHandle, key, value, expireDurationInSecond);
    }

    public byte[] decodeBytes(String key) {
        return decodeBytes(key, (byte[]) null);
    }

    public byte[] decodeBytes(String key, byte[] defaultValue) {
        byte[] bArrDecodeBytes = decodeBytes(this.nativeHandle, key);
        return bArrDecodeBytes != null ? bArrDecodeBytes : defaultValue;
    }

    private byte[] getParcelableByte(Parcelable value) {
        Parcel parcelObtain = Parcel.obtain();
        value.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        return bArrMarshall;
    }

    public boolean encode(String key, Parcelable value) {
        if (value == null) {
            return encodeBytes(this.nativeHandle, key, null);
        }
        return encodeBytes(this.nativeHandle, key, getParcelableByte(value));
    }

    public boolean encode(String key, Parcelable value, int expireDurationInSecond) {
        if (value == null) {
            return encodeBytes_2(this.nativeHandle, key, null, expireDurationInSecond);
        }
        return encodeBytes_2(this.nativeHandle, key, getParcelableByte(value), expireDurationInSecond);
    }

    public <T extends Parcelable> T decodeParcelable(String str, Class<T> cls) {
        return (T) decodeParcelable(str, cls, null);
    }

    public <T extends Parcelable> T decodeParcelable(String key, Class<T> tClass, T defaultValue) {
        byte[] bArrDecodeBytes;
        Parcelable.Creator<?> creator;
        if (tClass == null || (bArrDecodeBytes = decodeBytes(this.nativeHandle, key)) == null) {
            return defaultValue;
        }
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArrDecodeBytes, 0, bArrDecodeBytes.length);
        parcelObtain.setDataPosition(0);
        try {
            String string = tClass.toString();
            HashMap<String, Parcelable.Creator<?>> map = mCreators;
            synchronized (map) {
                creator = map.get(string);
                if (creator == null && (creator = (Parcelable.Creator) tClass.getField("CREATOR").get(null)) != null) {
                    map.put(string, creator);
                }
            }
            if (creator != null) {
                return (T) creator.createFromParcel(parcelObtain);
            }
            throw new Exception("Parcelable protocol requires a non-null static Parcelable.Creator object called CREATOR on class " + string);
        } catch (Exception e) {
            simpleLog(MMKVLogLevel.LevelError, e.toString());
            return defaultValue;
        } finally {
            parcelObtain.recycle();
        }
    }

    public int getValueSize(String key) {
        return valueSize(this.nativeHandle, key, false);
    }

    public int getValueActualSize(String key) {
        return valueSize(this.nativeHandle, key, true);
    }

    public boolean containsKey(String key) {
        return containsKey(this.nativeHandle, key);
    }

    public String[] allKeys() {
        return allKeys(this.nativeHandle, false);
    }

    public String[] allNonExpireKeys() {
        return allKeys(this.nativeHandle, true);
    }

    public long count() {
        return count(this.nativeHandle, false);
    }

    public long countNonExpiredKeys() {
        return count(this.nativeHandle, true);
    }

    public long totalSize() {
        return totalSize(this.nativeHandle);
    }

    public long actualSize() {
        return actualSize(this.nativeHandle);
    }

    public void removeValueForKey(String key) {
        removeValueForKey(this.nativeHandle, key);
    }

    public void sync() {
        sync(true);
    }

    public void async() {
        sync(false);
    }

    public static boolean isFileValid(String mmapID) {
        return isFileValid(mmapID, null);
    }

    public static boolean removeStorage(String mmapID) {
        return removeStorage(mmapID, null);
    }

    public int importFromSharedPreferences(SharedPreferences preferences) {
        Map<String, ?> all = preferences.getAll();
        if (all == null || all.size() <= 0) {
            return 0;
        }
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key != null && value != null) {
                if (value instanceof Boolean) {
                    encodeBool(this.nativeHandle, key, ((Boolean) value).booleanValue());
                } else if (value instanceof Integer) {
                    encodeInt(this.nativeHandle, key, ((Integer) value).intValue());
                } else if (value instanceof Long) {
                    encodeLong(this.nativeHandle, key, ((Long) value).longValue());
                } else if (value instanceof Float) {
                    encodeFloat(this.nativeHandle, key, ((Float) value).floatValue());
                } else if (value instanceof Double) {
                    encodeDouble(this.nativeHandle, key, ((Double) value).doubleValue());
                } else if (value instanceof String) {
                    encodeString(this.nativeHandle, key, (String) value);
                } else if (value instanceof Set) {
                    encode(key, (Set<String>) value);
                } else {
                    simpleLog(MMKVLogLevel.LevelError, "unknown type: " + value.getClass());
                }
            }
        }
        return all.size();
    }

    public void enableCompareBeforeSet() {
        if (isExpirationEnabled()) {
            Log.e("MMKV", "enableCompareBeforeSet is invalid when Expiration is on");
        }
        if (isEncryptionEnabled()) {
            Log.e("MMKV", "enableCompareBeforeSet is invalid when key encryption is on");
        }
        nativeEnableCompareBeforeSet();
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        throw new UnsupportedOperationException("Intentionally Not Supported. Use allKeys() instead, getAll() not implement because type-erasure inside mmkv");
    }

    @Override // android.content.SharedPreferences
    public String getString(String key, String defValue) {
        return decodeString(this.nativeHandle, key, defValue);
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor putString(String key, String value) {
        encodeString(this.nativeHandle, key, value);
        return this;
    }

    public SharedPreferences.Editor putString(String key, String value, int expireDurationInSecond) {
        encodeString_2(this.nativeHandle, key, value, expireDurationInSecond);
        return this;
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String key, Set<String> defValues) {
        return decodeStringSet(key, defValues);
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor putStringSet(String key, Set<String> values) {
        encode(key, values);
        return this;
    }

    public SharedPreferences.Editor putStringSet(String key, Set<String> values, int expireDurationInSecond) {
        encode(key, values, expireDurationInSecond);
        return this;
    }

    public SharedPreferences.Editor putBytes(String key, byte[] bytes) {
        encode(key, bytes);
        return this;
    }

    public SharedPreferences.Editor putBytes(String key, byte[] bytes, int expireDurationInSecond) {
        encode(key, bytes, expireDurationInSecond);
        return this;
    }

    public byte[] getBytes(String key, byte[] defValue) {
        return decodeBytes(key, defValue);
    }

    @Override // android.content.SharedPreferences
    public int getInt(String key, int defValue) {
        return decodeInt(this.nativeHandle, key, defValue);
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor putInt(String key, int value) {
        encodeInt(this.nativeHandle, key, value);
        return this;
    }

    public SharedPreferences.Editor putInt(String key, int value, int expireDurationInSecond) {
        encodeInt_2(this.nativeHandle, key, value, expireDurationInSecond);
        return this;
    }

    @Override // android.content.SharedPreferences
    public long getLong(String key, long defValue) {
        return decodeLong(this.nativeHandle, key, defValue);
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor putLong(String key, long value) {
        encodeLong(this.nativeHandle, key, value);
        return this;
    }

    public SharedPreferences.Editor putLong(String key, long value, int expireDurationInSecond) {
        encodeLong_2(this.nativeHandle, key, value, expireDurationInSecond);
        return this;
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String key, float defValue) {
        return decodeFloat(this.nativeHandle, key, defValue);
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor putFloat(String key, float value) {
        encodeFloat(this.nativeHandle, key, value);
        return this;
    }

    public SharedPreferences.Editor putFloat(String key, float value, int expireDurationInSecond) {
        encodeFloat_2(this.nativeHandle, key, value, expireDurationInSecond);
        return this;
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String key, boolean defValue) {
        return decodeBool(this.nativeHandle, key, defValue);
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor putBoolean(String key, boolean value) {
        encodeBool(this.nativeHandle, key, value);
        return this;
    }

    public SharedPreferences.Editor putBoolean(String key, boolean value, int expireDurationInSecond) {
        encodeBool_2(this.nativeHandle, key, value, expireDurationInSecond);
        return this;
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor remove(String key) {
        removeValueForKey(key);
        return this;
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor clear() {
        clearAll();
        return this;
    }

    @Override // android.content.SharedPreferences.Editor
    @Deprecated
    public boolean commit() {
        sync(true);
        return true;
    }

    @Override // android.content.SharedPreferences.Editor
    @Deprecated
    public void apply() {
        sync(false);
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String key) {
        return containsKey(key);
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        throw new UnsupportedOperationException("Intentionally Not implement in MMKV");
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        throw new UnsupportedOperationException("Intentionally Not implement in MMKV");
    }

    public static MMKV mmkvWithAshmemFD(String mmapID, int fd, int metaFD, String cryptKey) throws RuntimeException {
        long mMKVWithAshmemFD = getMMKVWithAshmemFD(mmapID, fd, metaFD, cryptKey);
        if (mMKVWithAshmemFD == 0) {
            throw new RuntimeException("Fail to create an ashmem MMKV instance [" + mmapID + "] in JNI");
        }
        return new MMKV(mMKVWithAshmemFD);
    }

    public static NativeBuffer createNativeBuffer(int size) {
        long jCreateNB = createNB(size);
        if (jCreateNB <= 0) {
            return null;
        }
        return new NativeBuffer(jCreateNB, size);
    }

    public static void destroyNativeBuffer(NativeBuffer buffer) {
        destroyNB(buffer.pointer, buffer.size);
    }

    public int writeValueToNativeBuffer(String key, NativeBuffer buffer) {
        return writeValueToNB(this.nativeHandle, key, buffer.pointer, buffer.size);
    }

    public static void registerHandler(MMKVHandler handler) {
        gCallbackHandler = handler;
        boolean zWantLogRedirecting = handler.wantLogRedirecting();
        gWantLogReDirecting = zWantLogRedirecting;
        setCallbackHandler(zWantLogRedirecting, true);
    }

    public static void unregisterHandler() {
        gCallbackHandler = null;
        setCallbackHandler(false, false);
        gWantLogReDirecting = false;
    }

    private static int onMMKVCRCCheckFail(String mmapID) {
        MMKVRecoverStrategic mMKVRecoverStrategicOnMMKVCRCCheckFail = MMKVRecoverStrategic.OnErrorDiscard;
        MMKVHandler mMKVHandler = gCallbackHandler;
        if (mMKVHandler != null) {
            mMKVRecoverStrategicOnMMKVCRCCheckFail = mMKVHandler.onMMKVCRCCheckFail(mmapID);
        }
        simpleLog(MMKVLogLevel.LevelInfo, "Recover strategic for " + mmapID + " is " + mMKVRecoverStrategicOnMMKVCRCCheckFail);
        Integer num = recoverIndex.get(mMKVRecoverStrategicOnMMKVCRCCheckFail);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    private static int onMMKVFileLengthError(String mmapID) {
        MMKVRecoverStrategic mMKVRecoverStrategicOnMMKVFileLengthError = MMKVRecoverStrategic.OnErrorDiscard;
        MMKVHandler mMKVHandler = gCallbackHandler;
        if (mMKVHandler != null) {
            mMKVRecoverStrategicOnMMKVFileLengthError = mMKVHandler.onMMKVFileLengthError(mmapID);
        }
        simpleLog(MMKVLogLevel.LevelInfo, "Recover strategic for " + mmapID + " is " + mMKVRecoverStrategicOnMMKVFileLengthError);
        Integer num = recoverIndex.get(mMKVRecoverStrategicOnMMKVFileLengthError);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    private static void mmkvLogImp(int level, String file, int line, String function, String message) {
        MMKVHandler mMKVHandler = gCallbackHandler;
        if (mMKVHandler != null && gWantLogReDirecting) {
            mMKVHandler.mmkvLog(index2LogLevel[level], file, line, function, message);
            return;
        }
        int i = C24341.$SwitchMap$com$tencent$mmkv$MMKVLogLevel[index2LogLevel[level].ordinal()];
        if (i == 1) {
            Log.d("MMKV", message);
            return;
        }
        if (i == 2) {
            Log.w("MMKV", message);
        } else if (i == 3) {
            Log.e("MMKV", message);
        } else {
            if (i != 5) {
                return;
            }
            Log.i("MMKV", message);
        }
    }

    private static void simpleLog(MMKVLogLevel level, String message) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[r0.length - 1];
        Integer num = logLevel2Index.get(level);
        mmkvLogImp(num == null ? 0 : num.intValue(), stackTraceElement.getFileName(), stackTraceElement.getLineNumber(), stackTraceElement.getMethodName(), message);
    }

    public static void registerContentChangeNotify(MMKVContentChangeNotification notify) {
        gContentChangeNotify = notify;
        setWantsContentChangeNotify(notify != null);
    }

    public static void unregisterContentChangeNotify() {
        gContentChangeNotify = null;
        setWantsContentChangeNotify(false);
    }

    private static void onContentChangedByOuterProcess(String mmapID) {
        MMKVContentChangeNotification mMKVContentChangeNotification = gContentChangeNotify;
        if (mMKVContentChangeNotification != null) {
            mMKVContentChangeNotification.onContentChangedByOuterProcess(mmapID);
        }
    }

    private MMKV(long handle) {
        this.nativeHandle = handle;
    }
}
