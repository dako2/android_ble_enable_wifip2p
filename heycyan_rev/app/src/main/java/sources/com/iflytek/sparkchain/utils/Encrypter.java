package com.iflytek.sparkchain.utils;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.digest.MessageDigestAlgorithms;
import com.iflytek.sparkchain.utils.log.LogUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes2.dex */
public class Encrypter {
    public static final int BUFFER_SIZE = 1024;
    public static final byte GZIP_KEY = 5;
    private static final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCt8H0BF3SquJmk6xIo2bTldgvtazLIeSbR4cle\np7zeUAtI/mC7UgFl8xXFCTemVambyQFnM5GsZOI1BpAMJO7N/YHRX7hvCZG6D0rEXQEdKXhKFIBQ\nmOYRYZP042vWRcKZ6iQLdLYmyg6tIzjYVfH0f6YX8OLIU7fy0TA/c88rzwIDAQAB";
    private static final int SDK_VERSION_KITKAT = 19;

    public static synchronized String MD5(String str) {
        StringBuffer stringBuffer;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[charArray.length];
            for (int i = 0; i < charArray.length; i++) {
                bArr[i] = (byte) charArray[i];
            }
            byte[] bArrDigest = messageDigest.digest(bArr);
            stringBuffer = new StringBuffer();
            for (byte b : bArrDigest) {
                int i2 = b & 255;
                if (i2 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
        } catch (Exception e) {
            LogUtil.m564e(e);
            return "";
        }
        return stringBuffer.toString();
    }

    public static int byteArrayToInt(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            i += (bArr[i2] & 255) << ((3 - i2) * 8);
        }
        return i;
    }

    public static synchronized String cut16MD5(String str) {
        LogUtil.m561d("cut16MD5 start:" + str);
        String strMD5 = MD5(str);
        LogUtil.m561d("cut16MD5 start:" + strMD5);
        StringBuffer stringBuffer = new StringBuffer();
        if (strMD5 != null && strMD5.length() != 0) {
            LogUtil.m561d("cut16MD5 md5 size is:" + strMD5.length());
            for (int i = 0; i < strMD5.length(); i++) {
                if (i % 2 == 0) {
                    LogUtil.m561d("cut16MD5 result i:" + strMD5.charAt(i));
                    stringBuffer.append(strMD5.charAt(i));
                }
                LogUtil.m561d("cut16MD5 result i = :" + i);
            }
            LogUtil.m561d("cut16MD5 result:" + ((Object) stringBuffer));
            return stringBuffer.toString();
        }
        return null;
    }

    public static byte[] encrypt(byte[] bArr) {
        try {
            byte[] encoded = AESUtil.generateKey().getEncoded();
            byte[] bArrEncrypt = bArr != null ? AESUtil.encrypt(zip(bArr), encoded) : null;
            byte[] bArrEncryptByPublicKey = RSAUtil.encryptByPublicKey(encoded, RSAUtil.loadPublicKey(RSA_PUBLIC_KEY));
            if (bArrEncryptByPublicKey != null && bArrEncrypt != null) {
                int length = bArrEncryptByPublicKey.length;
                int length2 = bArrEncrypt.length;
                byte[] bArr2 = new byte[length + length2 + 10];
                bArr2[0] = 1;
                System.arraycopy(intToByteArray(length), 0, bArr2, 1, 4);
                System.arraycopy(bArrEncryptByPublicKey, 0, bArr2, 5, length);
                bArr2[length + 5] = 3;
                System.arraycopy(intToByteArray(length2), 0, bArr2, length + 6, 4);
                System.arraycopy(bArrEncrypt, 0, bArr2, length + 10, length2);
                return bArr2;
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(5:(2:38|5)|(2:40|6)|32|18|22) */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0048, code lost:
    
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0049, code lost:
    
        r8.printStackTrace();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0052 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.io.FileInputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getFileMd5(File file) throws Throwable {
        ?? fileInputStream;
        FileInputStream fileInputStream2;
        String string = null;
        if (!file.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Exception e) {
            e = e;
            fileInputStream = 0;
        } catch (Throwable th) {
            th = th;
            fileInputStream = string;
            if (fileInputStream != 0) {
            }
            throw th;
        }
        try {
            MappedByteBuffer map = fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(map);
            string = new BigInteger(1, messageDigest.digest()).toString(16);
            fileInputStream2 = fileInputStream;
        } catch (Exception e2) {
            e = e2;
            try {
                e.printStackTrace();
                fileInputStream2 = fileInputStream;
                if (fileInputStream != 0) {
                    fileInputStream2.close();
                }
                return string;
            } catch (Throwable th2) {
                th = th2;
                string = fileInputStream;
                fileInputStream = string;
                if (fileInputStream != 0) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (fileInputStream != 0) {
            }
            throw th;
        }
        fileInputStream2.close();
        return string;
    }

    public static byte[] intToByteArray(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] lightcode(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ 5);
        }
        return bArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x003d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.OutputStream, java.util.zip.GZIPOutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static byte[] zip(byte[] bArr) throws Throwable {
        ?? gZIPOutputStream;
        byte[] byteArray = null;
        if (bArr == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e2) {
            e = e2;
            gZIPOutputStream = 0;
        } catch (Throwable th) {
            th = th;
            gZIPOutputStream = byteArray;
            if (gZIPOutputStream != 0) {
            }
            byteArrayOutputStream.close();
            throw th;
        }
        try {
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.finish();
            byteArray = byteArrayOutputStream.toByteArray();
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
        } catch (IOException e3) {
            e = e3;
            try {
                e.printStackTrace();
                if (gZIPOutputStream != 0) {
                    gZIPOutputStream.close();
                }
                byteArrayOutputStream.close();
                return byteArray;
            } catch (Throwable th2) {
                th = th2;
                byteArray = gZIPOutputStream;
                gZIPOutputStream = byteArray;
                if (gZIPOutputStream != 0) {
                    try {
                        gZIPOutputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                        throw th;
                    }
                }
                byteArrayOutputStream.close();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (gZIPOutputStream != 0) {
            }
            byteArrayOutputStream.close();
            throw th;
        }
        return byteArray;
    }

    public static byte[] zip5xDecode(byte[] bArr) throws Throwable {
        Throwable th;
        GZIPInputStream gZIPInputStream;
        IOException iOException;
        byte[] bArr2;
        GZIPInputStream gZIPInputStream2 = null;
        byte[] byteArray = null;
        gZIPInputStream2 = null;
        if (bArr == null) {
            return null;
        }
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ 5);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            } catch (IOException e) {
                iOException = e;
                bArr2 = null;
            }
        } catch (Throwable th2) {
            GZIPInputStream gZIPInputStream3 = gZIPInputStream2;
            th = th2;
            gZIPInputStream = gZIPInputStream3;
        }
        try {
            byte[] bArr3 = new byte[1024];
            while (true) {
                int i2 = gZIPInputStream.read(bArr3, 0, 1024);
                if (i2 == -1) {
                    byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.flush();
                    try {
                        gZIPInputStream.close();
                        byteArrayInputStream.close();
                        byteArrayOutputStream.close();
                        return byteArray;
                    } catch (IOException e2) {
                        LogUtil.m564e(e2);
                        return byteArray;
                    }
                }
                byteArrayOutputStream.write(bArr3, 0, i2);
            }
        } catch (IOException e3) {
            bArr2 = byteArray;
            gZIPInputStream2 = gZIPInputStream;
            iOException = e3;
            LogUtil.m564e(iOException);
            if (gZIPInputStream2 != null) {
                try {
                    gZIPInputStream2.close();
                } catch (IOException e4) {
                    LogUtil.m564e(e4);
                    return bArr2;
                }
            }
            byteArrayInputStream.close();
            byteArrayOutputStream.close();
            return bArr2;
        } catch (Throwable th3) {
            th = th3;
            if (gZIPInputStream != null) {
                try {
                    gZIPInputStream.close();
                } catch (IOException e5) {
                    LogUtil.m564e(e5);
                    throw th;
                }
            }
            byteArrayInputStream.close();
            byteArrayOutputStream.close();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0052 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static byte[] zip5xEncode(byte[] bArr) throws Throwable {
        GZIPOutputStream gZIPOutputStream;
        IOException e;
        GZIPOutputStream gZIPOutputStream2 = null;
        if (bArr == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    try {
                        gZIPOutputStream.write(bArr);
                        gZIPOutputStream.finish();
                        bArr = byteArrayOutputStream.toByteArray();
                        for (int i = 0; i < bArr.length; i++) {
                            try {
                                bArr[i] = (byte) (bArr[i] ^ 5);
                            } catch (IOException e2) {
                                e = e2;
                                try {
                                    LogUtil.m564e(e);
                                    if (gZIPOutputStream != null) {
                                        gZIPOutputStream.close();
                                    }
                                    byteArrayOutputStream.close();
                                    return bArr;
                                } catch (Throwable th) {
                                    th = th;
                                    gZIPOutputStream2 = gZIPOutputStream;
                                    gZIPOutputStream = gZIPOutputStream2;
                                    if (gZIPOutputStream != null) {
                                    }
                                    byteArrayOutputStream.close();
                                    throw th;
                                }
                            }
                        }
                        gZIPOutputStream.close();
                        byteArrayOutputStream.close();
                    } catch (Throwable th2) {
                        th = th2;
                        if (gZIPOutputStream != null) {
                            try {
                                gZIPOutputStream.close();
                            } catch (IOException e3) {
                                LogUtil.m564e(e3);
                                throw th;
                            }
                        }
                        byteArrayOutputStream.close();
                        throw th;
                    }
                } catch (IOException e4) {
                    e = e4;
                    bArr = null;
                }
            } catch (IOException e5) {
                LogUtil.m564e(e5);
            }
        } catch (IOException e6) {
            gZIPOutputStream = null;
            e = e6;
            bArr = null;
        } catch (Throwable th3) {
            th = th3;
            gZIPOutputStream = gZIPOutputStream2;
            if (gZIPOutputStream != null) {
            }
            byteArrayOutputStream.close();
            throw th;
        }
        return bArr;
    }
}
