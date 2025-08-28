package com.iflytek.sparkchain.utils;

import android.content.Context;
import android.os.Environment;
import android.os.MemoryFile;
import android.text.TextUtils;
import com.iflytek.sparkchain.media.record.C2220c;
import com.iflytek.sparkchain.utils.log.LogUtil;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes2.dex */
public class FileUtil {
    private static final String CHILD_FOLDER_NAME = "res";
    private static final String FOLDER_NAME = "msc";
    private static final String FORMAT_PCM = "pcm";
    private static final String FORMAT_WAV = "wav";
    public static final String RES_SUFFIX = ".jet";
    private static String userPath = "";

    public static boolean checkFileMD5(String str, String str2) {
        if (str.equals(Encrypter.getFileMd5(new File(str2)))) {
            return true;
        }
        deleteFile(str2);
        return false;
    }

    public static String codeString(File file) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            int i = (bufferedInputStream.read() << 8) + bufferedInputStream.read();
            return i != 4094 ? i != 61371 ? i != 65279 ? "GBK" : "YTF-16BE" : "UTF-8" : "Unicode";
        } catch (FileNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void copyAssetsFile(Context context, String str, String str2) throws Throwable {
        Throwable th;
        InputStream inputStreamOpen;
        FileOutputStream fileOutputStream = null;
        try {
            inputStreamOpen = context.getAssets().open(str);
            try {
                makeDir(str2);
                FileOutputStream fileOutputStream2 = new FileOutputStream(str2, false);
                try {
                    byte[] bArr = new byte[2048];
                    while (true) {
                        int i = inputStreamOpen.read(bArr);
                        if (i <= 0) {
                            inputStreamOpen.close();
                            fileOutputStream2.close();
                            return;
                        }
                        fileOutputStream2.write(bArr, 0, i);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = fileOutputStream2;
                    if (inputStreamOpen != null) {
                        inputStreamOpen.close();
                    }
                    if (fileOutputStream == null) {
                        throw th;
                    }
                    fileOutputStream.close();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            inputStreamOpen = null;
        }
    }

    public static void deleteFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static boolean formatPcm(String str, String str2, int i) {
        if (TextUtils.isEmpty(str) || FORMAT_PCM.equals(str)) {
            return true;
        }
        if (FORMAT_WAV.equals(str)) {
            return pcm2Wav(str2, i);
        }
        LogUtil.m564e("format not supported");
        return false;
    }

    public static String getResFilePath(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = System.currentTimeMillis() + "";
        }
        String absolutePath = ("mounted".equals(Environment.getExternalStorageState()) ? Environment.getExternalStorageDirectory() : context.getFilesDir()).getAbsolutePath();
        if (!absolutePath.endsWith("/")) {
            absolutePath = absolutePath + "/";
        }
        String str2 = (absolutePath + "msc/") + "res/";
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        String str3 = str2 + str;
        return !str3.endsWith(RES_SUFFIX) ? str3 + RES_SUFFIX : str3;
    }

    public static String getUserPath(Context context) {
        if (!TextUtils.isEmpty(userPath)) {
            return userPath;
        }
        String absolutePath = context.getFilesDir().getAbsolutePath();
        if (!absolutePath.endsWith("/")) {
            absolutePath = absolutePath + "/";
        }
        String str = absolutePath + "msclib/";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        userPath = str;
        return str;
    }

    public static void makeDir(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file = new File(str);
        if (!str.endsWith("/")) {
            file = file.getParentFile();
        }
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    public static boolean pcm2Wav(String str, int i) {
        try {
            C2220c c2220c = new C2220c(new File(str), i);
            c2220c.m546c();
            c2220c.m541a();
            return true;
        } catch (IOException e) {
            LogUtil.m564e(e);
            return false;
        }
    }

    public static byte[] readFile(String str) throws Throwable {
        Throwable th;
        FileInputStream fileInputStream;
        byte[] bArr;
        IOException e;
        FileInputStream fileInputStream2 = null;
        byte[] bArr2 = null;
        fileInputStream2 = null;
        try {
            try {
                File file = new File(str);
                if (!file.exists()) {
                    return null;
                }
                fileInputStream = new FileInputStream(file);
                try {
                    bArr2 = new byte[fileInputStream.available()];
                    fileInputStream.read(bArr2);
                    try {
                        fileInputStream.close();
                        return bArr2;
                    } catch (IOException e2) {
                        e = e2;
                        LogUtil.m564e(e);
                        return bArr2;
                    }
                } catch (Exception e3) {
                    e = e3;
                    byte[] bArr3 = bArr2;
                    fileInputStream2 = fileInputStream;
                    bArr = bArr3;
                    LogUtil.m564e(e);
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e4) {
                            bArr2 = bArr;
                            e = e4;
                            LogUtil.m564e(e);
                            return bArr2;
                        }
                    }
                    return bArr;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e5) {
                            LogUtil.m564e(e5);
                        }
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
                bArr = null;
            }
        } catch (Throwable th3) {
            FileInputStream fileInputStream3 = fileInputStream2;
            th = th3;
            fileInputStream = fileInputStream3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v12, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.io.InputStream] */
    public static byte[] readFileFromAssets(Context context, String str) throws Throwable {
        Object obj;
        IOException e;
        ?? Open;
        ?? r0 = 0;
        r0 = 0;
        InputStream inputStream = null;
        try {
            try {
                Open = context.getAssets().open(str);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e2) {
            e = e2;
            obj = null;
        }
        try {
            r0 = new byte[Open.available()];
            Open.read(r0);
            try {
                Open.close();
                r0 = r0;
            } catch (IOException e3) {
                e = e3;
                LogUtil.m564e(e);
                return r0;
            }
        } catch (Exception e4) {
            e = e4;
            Object obj2 = r0;
            inputStream = Open;
            obj = obj2;
            LogUtil.m564e(e);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    r0 = obj;
                    e = e5;
                    LogUtil.m564e(e);
                    return r0;
                }
            }
            r0 = obj;
            return r0;
        } catch (Throwable th2) {
            th = th2;
            r0 = Open;
            if (r0 != 0) {
                try {
                    r0.close();
                } catch (IOException e6) {
                    LogUtil.m564e(e6);
                }
            }
            throw th;
        }
        return r0;
    }

    public static boolean saveFile(MemoryFile memoryFile, long j, String str) throws Throwable {
        FileOutputStream fileOutputStream;
        boolean z = false;
        if (memoryFile == null || TextUtils.isEmpty(str)) {
            return false;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    deleteFile(str);
                    makeDir(str);
                    fileOutputStream = new FileOutputStream(str);
                } catch (Exception e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
            }
        } catch (Exception unused) {
        }
        try {
            byte[] bArr = new byte[65536];
            int i = 0;
            while (true) {
                long j2 = i;
                if (j2 >= j) {
                    break;
                }
                long j3 = j - j2;
                if (j3 > 65536) {
                    j3 = 65536;
                }
                int i2 = (int) j3;
                memoryFile.readBytes(bArr, i, 0, i2);
                fileOutputStream.write(bArr, 0, i2);
                i += i2;
            }
            z = true;
            fileOutputStream.close();
        } catch (Exception e2) {
            e = e2;
            fileOutputStream2 = fileOutputStream;
            LogUtil.m564e(e);
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            return z;
        } catch (Throwable th2) {
            th = th2;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
        return z;
    }

    public static boolean saveFile(ConcurrentLinkedQueue<byte[]> concurrentLinkedQueue, String str) throws Throwable {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                makeDir(str);
                fileOutputStream = new FileOutputStream(str);
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
            fileOutputStream = fileOutputStream2;
        }
        try {
            Iterator<byte[]> it = concurrentLinkedQueue.iterator();
            while (it.hasNext()) {
                fileOutputStream.write(it.next());
            }
            fileOutputStream.close();
            return true;
        } catch (Exception e2) {
            e = e2;
            fileOutputStream2 = fileOutputStream;
            LogUtil.m564e(e);
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Exception e3) {
                    LogUtil.m564e(e3);
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e4) {
                    LogUtil.m564e(e4);
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x003a A[Catch: Exception -> 0x003d, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x003d, blocks: (B:19:0x002b, B:28:0x003a), top: B:36:0x0001 }] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean saveFile(byte[] bArr, String str, boolean z, int i) throws Throwable {
        RandomAccessFile randomAccessFile;
        Exception e;
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                if (!z) {
                    try {
                        deleteFile(str);
                    } catch (Exception e2) {
                        e = e2;
                        LogUtil.m564e(e);
                        bArr = 0;
                        bArr = 0;
                        if (randomAccessFile2 != null) {
                            randomAccessFile2.close();
                        }
                        return bArr;
                    }
                }
                makeDir(str);
                randomAccessFile = new RandomAccessFile(str, "rw");
            } catch (Throwable th) {
                th = th;
                randomAccessFile = null;
            }
        } catch (Exception unused) {
        }
        try {
            if (z) {
                randomAccessFile.seek(i < 0 ? randomAccessFile.length() : i);
            } else {
                randomAccessFile.setLength(0L);
            }
            randomAccessFile.write(bArr);
            bArr = 1;
            randomAccessFile.close();
        } catch (Exception e3) {
            e = e3;
            randomAccessFile2 = randomAccessFile;
            LogUtil.m564e(e);
            bArr = 0;
            bArr = 0;
            if (randomAccessFile2 != null) {
            }
            return bArr;
        } catch (Throwable th2) {
            th = th2;
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
        return bArr;
    }
}
