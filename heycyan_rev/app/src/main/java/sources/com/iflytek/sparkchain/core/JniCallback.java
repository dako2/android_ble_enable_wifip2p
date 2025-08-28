package com.iflytek.sparkchain.core;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.crashlytics.buildtools.ndk.NativeSymbolGenerator;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.FilenameUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import com.iflytek.sparkchain.utils.ByteUtil;
import com.iflytek.sparkchain.utils.DeviceIdUtil;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class JniCallback extends DeviceIdUtil {
    private static final String license_dir = "dir_empty";
    private static final String license_extension = "extension";
    private static final String license_sp_key = "protocol_sp";
    private static final String license_version = "d";

    public class JniAuthTimer {
        public int code;
        public long expireTime;
        public long leftTime;

        public JniAuthTimer() {
        }
    }

    public class JniOutput {
        public int code;
        public byte[] data;

        public JniOutput() {
        }

        public List<AiResponse> formatData() {
            return JniCallback.collectData(this.data, false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00c4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void authFormatFilt(String str) throws JSONException {
        String string;
        String string2;
        if (DeviceIdUtil.getContext() == null) {
            return;
        }
        SharedPreferences sharedPreferences = DeviceIdUtil.getContext().getSharedPreferences(str, 0);
        String strEncodeAuthInfo = "";
        if (sharedPreferences.getString(license_extension, "").isEmpty()) {
            if (!sharedPreferences.getString("level", "").isEmpty()) {
                String string3 = sharedPreferences.getString("level", "");
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("level", string3);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String strEncodeAuthInfo2 = encodeAuthInfo(jSONObject.toString());
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putString(license_extension, strEncodeAuthInfo2);
                editorEdit.remove("level");
                editorEdit.apply();
            }
            if (sharedPreferences.getString(license_sp_key, "").isEmpty()) {
                return;
            }
            String strDecodeAuthInfoMd5 = decodeAuthInfoMd5(sharedPreferences.getString(license_sp_key, ""));
            if (strDecodeAuthInfoMd5.isEmpty()) {
                return;
            }
            try {
                JSONObject jSONObject2 = new JSONObject(strDecodeAuthInfoMd5);
                string = jSONObject2.getString("license");
                try {
                    string2 = jSONObject2.getString("device");
                    try {
                        String string4 = jSONObject2.getString("level");
                        if (!string4.isEmpty()) {
                            try {
                                new JSONObject().put("level", string4);
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                            strEncodeAuthInfo = encodeAuthInfo(jSONObject2.toString());
                        }
                    } catch (JSONException unused) {
                        Log.w("Auth", "authFormatFilt rawStr imperfect");
                        SharedPreferences.Editor editorEdit2 = sharedPreferences.edit();
                        if (!string.isEmpty()) {
                        }
                        if (!string2.isEmpty()) {
                        }
                        if (!strEncodeAuthInfo.isEmpty()) {
                        }
                        editorEdit2.remove(license_sp_key);
                        editorEdit2.apply();
                    }
                } catch (JSONException unused2) {
                    string2 = "";
                }
            } catch (JSONException unused3) {
                string = "";
                string2 = string;
            }
            SharedPreferences.Editor editorEdit22 = sharedPreferences.edit();
            if (!string.isEmpty()) {
                editorEdit22.putString("license", string);
            }
            if (!string2.isEmpty()) {
                editorEdit22.putString("device", string2);
            }
            if (!strEncodeAuthInfo.isEmpty()) {
                editorEdit22.putString(license_extension, strEncodeAuthInfo);
            }
            editorEdit22.remove(license_sp_key);
            editorEdit22.apply();
        }
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static boolean chatOnError(int i, String str, int i2, int i3, String str2) {
        ChatListener chatListener = AiHelper.getInst().getChatListener();
        if (chatListener == null) {
            return false;
        }
        chatListener.onChatError(new AIChatHandle(AiHelper.getInst().getUsrContext(i), str, i2), i3, str2);
        return true;
    }

    public static boolean chatOnOutput(int i, String str, int i2, String str2, String str3, String str4, int i3) {
        ChatListener chatListener = AiHelper.getInst().getChatListener();
        if (chatListener == null) {
            return false;
        }
        chatListener.onChatOutput(new AIChatHandle(AiHelper.getInst().getUsrContext(i), str, i2), str2, str3, str4, i3);
        return true;
    }

    public static boolean chatTokenCount(int i, String str, int i2, int i3, int i4, int i5) {
        ChatListener chatListener = AiHelper.getInst().getChatListener();
        if (chatListener == null) {
            return false;
        }
        AIChatHandle aIChatHandle = new AIChatHandle(AiHelper.getInst().getUsrContext(i), str, i2);
        aIChatHandle.setUsrContextIndex(i);
        chatListener.onChatToken(aIChatHandle, i3, i4, i5);
        return true;
    }

    public static void clearKey(String str, String str2) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        if (!str2.equals(license_dir)) {
            doReflex(str2);
        }
        if (DeviceIdUtil.getContext() == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = DeviceIdUtil.getContext().getSharedPreferences(str.replace(IOUtils.DIR_SEPARATOR_UNIX, FilenameUtils.EXTENSION_SEPARATOR), 0).edit();
        editorEdit.clear();
        editorEdit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0049 A[EDGE_INSN: B:33:0x0049->B:28:0x0049 BREAK  A[LOOP:0: B:3:0x0007->B:30:0x0050], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0049 A[EDGE_INSN: B:35:0x0049->B:28:0x0049 BREAK  A[LOOP:0: B:3:0x0007->B:30:0x0050], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static List<AiResponse> collectData(byte[] bArr, boolean z) {
        int i;
        int i2;
        ArrayList arrayList = new ArrayList();
        int length = bArr.length;
        int i3 = 0;
        while (true) {
            AiResponse aiResponse = new AiResponse();
            int i4 = i3 + 4;
            if (i4 > length) {
                break;
            }
            aiResponse.setType(ByteUtil.byteArrayToInt(bArr, i3));
            if (z) {
                i = i4 + 4;
                if (i > length) {
                    break;
                }
                int iByteArrayToInt = ByteUtil.byteArrayToInt(bArr, i4);
                if (iByteArrayToInt <= 0) {
                    i2 = i4 + 4;
                    if (i2 > length) {
                        break;
                    }
                    int iByteArrayToInt2 = ByteUtil.byteArrayToInt(bArr, i4);
                    if (iByteArrayToInt2 > 0) {
                        i4 = i2 + iByteArrayToInt2;
                        if (i4 > length) {
                            break;
                        }
                        aiResponse.setValue(bArr, i2, iByteArrayToInt2);
                        aiResponse.setLen(iByteArrayToInt2);
                    }
                    i3 = i4;
                    arrayList.add(aiResponse);
                } else {
                    i4 = i + iByteArrayToInt;
                    if (i4 > length) {
                        break;
                    }
                    aiResponse.setKey(bArr, i, iByteArrayToInt);
                    i2 = i4 + 4;
                    if (i2 > length) {
                    }
                }
            } else {
                int i5 = i3 + 8;
                if (i5 > length) {
                    break;
                }
                aiResponse.setStatus(ByteUtil.byteArrayToInt(bArr, i4));
                i4 = i5;
                i = i4 + 4;
                if (i > length) {
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x003a A[EDGE_INSN: B:28:0x003a->B:22:0x003a BREAK  A[LOOP:0: B:3:0x0007->B:24:0x0041], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static List<AiResponse> collectDataEvent(byte[] bArr) {
        int i;
        ArrayList arrayList = new ArrayList();
        int length = bArr.length;
        int i2 = 0;
        while (true) {
            AiResponse aiResponse = new AiResponse();
            int i3 = i2 + 4;
            if (i3 <= length) {
                aiResponse.setType(ByteUtil.byteArrayToInt(bArr, i2));
                int i4 = i2 + 8;
                if (i4 > length) {
                    break;
                }
                int iByteArrayToInt = ByteUtil.byteArrayToInt(bArr, i3);
                if (iByteArrayToInt <= 0) {
                    i = i3 + 4;
                    if (i > length) {
                        break;
                    }
                    int iByteArrayToInt2 = ByteUtil.byteArrayToInt(bArr, i3);
                    if (iByteArrayToInt2 > 0) {
                        i3 = i + iByteArrayToInt2;
                        if (i3 > length) {
                            break;
                        }
                        aiResponse.setValue(bArr, i, iByteArrayToInt2);
                        aiResponse.setLen(iByteArrayToInt2);
                    }
                    i2 = i3;
                    arrayList.add(aiResponse);
                } else {
                    i3 = i4 + iByteArrayToInt;
                    if (i3 > length) {
                        break;
                    }
                    aiResponse.setKey(bArr, i4, iByteArrayToInt);
                    i = i3 + 4;
                    if (i > length) {
                    }
                }
            } else {
                break;
            }
        }
        return arrayList;
    }

    public static String decodeAuthInfo(String str) {
        if (str.isEmpty()) {
            return "";
        }
        String strHexStringToString = hexStringToString(str);
        return !strHexStringToString.substring(0, 1).equals(license_version) ? "" : strHexStringToString.substring(1);
    }

    public static String decodeAuthInfoMd5(String str) {
        if (str.isEmpty()) {
            return "";
        }
        String strHexStringToString = hexStringToString(str);
        return (strHexStringToString.substring(0, 1).equals(license_version) && getEnMD5String(strHexStringToString.substring(33)).equals(strHexStringToString.substring(1, 33))) ? strHexStringToString.substring(33) : "";
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0027 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String delExtValueFromStr(String str, String str2) {
        JSONObject jSONObject;
        if (str.isEmpty()) {
            return "";
        }
        String strDecodeAuthInfo = decodeAuthInfo(str);
        if (strDecodeAuthInfo.isEmpty()) {
            return "";
        }
        try {
            jSONObject = new JSONObject(strDecodeAuthInfo);
            try {
                jSONObject.remove(str2);
            } catch (JSONException e) {
                e = e;
                e.printStackTrace();
                if (jSONObject != null) {
                }
            }
        } catch (JSONException e2) {
            e = e2;
            jSONObject = null;
        }
        return jSONObject != null ? "" : encodeAuthInfo(jSONObject.toString());
    }

    public static void delKey(String str, String str2, String str3) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        SharedPreferences.Editor editorEdit;
        if (!str2.equals(license_dir)) {
            doReflex(str2);
        }
        if (DeviceIdUtil.getContext() == null) {
            return;
        }
        SharedPreferences sharedPreferences = DeviceIdUtil.getContext().getSharedPreferences(str.replace(IOUtils.DIR_SEPARATOR_UNIX, FilenameUtils.EXTENSION_SEPARATOR), 0);
        if (isExtensionKey(str3)) {
            String strDelExtValueFromStr = delExtValueFromStr(sharedPreferences.getString(license_extension, ""), str3);
            if (strDelExtValueFromStr.isEmpty()) {
                return;
            }
            editorEdit = sharedPreferences.edit();
            editorEdit.putString(license_extension, strDelExtValueFromStr);
        } else {
            editorEdit = sharedPreferences.edit();
            editorEdit.remove(str3);
        }
        editorEdit.apply();
    }

    private static void doReflex(String str) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        try {
            Field declaredField = ContextWrapper.class.getDeclaredField("mBase");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(DeviceIdUtil.getContext().getApplicationContext());
            Field declaredField2 = obj.getClass().getDeclaredField("mPreferencesDir");
            declaredField2.setAccessible(true);
            declaredField2.set(obj, new File(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encodeAuthInfo(String str) {
        StringBuffer stringBuffer = new StringBuffer(license_version);
        stringBuffer.append(str);
        return stringToHexString(stringBuffer.toString());
    }

    public static String encodeAuthInfoMd5(String str) {
        StringBuffer stringBuffer = new StringBuffer(license_version);
        stringBuffer.append(getEnMD5String(str)).append(str);
        return stringToHexString(stringBuffer.toString());
    }

    private static String findNativeLibraryPath(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return ((PathClassLoader) context.getClassLoader()).findLibrary(str);
    }

    public static String getEnMD5String(String str) throws NoSuchAlgorithmException {
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            messageDigest.reset();
            messageDigest.update(bytes);
            byte[] bArrDigest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bArrDigest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getExtValueFromStr(String str, String str2) {
        String strDecodeAuthInfo = decodeAuthInfo(str);
        if (strDecodeAuthInfo.isEmpty()) {
            return "";
        }
        try {
            return new JSONObject(strDecodeAuthInfo).getString(str2);
        } catch (JSONException unused) {
            return "";
        }
    }

    public static String getKey(String str, String str2, String str3) throws IllegalAccessException, JSONException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        if (!str2.equals(license_dir)) {
            doReflex(str2);
        }
        if (DeviceIdUtil.getContext() == null) {
            return "";
        }
        String strReplace = str.replace(IOUtils.DIR_SEPARATOR_UNIX, FilenameUtils.EXTENSION_SEPARATOR);
        authFormatFilt(strReplace);
        SharedPreferences sharedPreferences = DeviceIdUtil.getContext().getSharedPreferences(strReplace, 0);
        return isExtensionKey(str3) ? getExtValueFromStr(sharedPreferences.getString(license_extension, ""), str3) : sharedPreferences.getString(str3, "");
    }

    /* renamed from: gp */
    public static String m525gp(String str) {
        if (str.startsWith(NativeSymbolGenerator.LIB_PREFIX)) {
            str = str.substring(3);
        }
        if (str.endsWith(".so")) {
            str = str.substring(0, str.length() - 3);
        }
        return findNativeLibraryPath(Auth.m510c().m511a().getApplicationContext(), str);
    }

    private static byte[] hexStrToBytes(String str) {
        if (TextUtils.isEmpty(str) || str.length() == 0) {
            return null;
        }
        if (str.length() % 2 == 1) {
            str = "0" + str;
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        char[] charArray = str.toCharArray();
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            bArr[i] = (byte) ((charToByte(charArray[i]) << 4) | charToByte(charArray[i2]));
            i = i2;
        }
        return bArr;
    }

    public static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static String hexStringToString(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String strReplace = str.replace(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR, "");
        int length = strReplace.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            try {
                bArr[i] = (byte) (Integer.parseInt(strReplace.substring(i2, i2 + 2), 16) & 255);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            return new String(bArr, "gbk");
        } catch (Exception e2) {
            e2.printStackTrace();
            return strReplace;
        }
    }

    public static boolean isExtensionKey(String str) {
        return (str.isEmpty() || str.equals("device") || str.equals("license")) ? false : true;
    }

    public static void onError(String str, int i, int i2, int i3, String str2) {
        AiResponseListener listener = AiHelper.getInst().getListener();
        if (listener != null) {
            listener.onError(str, i, i3, str2, AiHelper.getInst().getUsrContext(i2));
        }
    }

    public static boolean onEvent(String str, int i, int i2, int i3, byte[] bArr) {
        AiResponseListener listener = AiHelper.getInst().getListener();
        if (listener == null) {
            return false;
        }
        listener.onEvent(str, i, i3, collectData(bArr, true), AiHelper.getInst().getUsrContext(i2));
        return true;
    }

    public static boolean onResult(String str, int i, int i2, byte[] bArr) {
        AiResponseListener listener = AiHelper.getInst().getListener();
        if (listener == null) {
            Log.w("AEELog", "onResult：listener == null");
            return false;
        }
        List<AiResponse> listCollectData = collectData(bArr, false);
        if (listCollectData.isEmpty()) {
            Log.e("AEELog", "outputs null in jni!");
            return true;
        }
        listener.onResult(str, i, listCollectData, AiHelper.getInst().getUsrContext(i2));
        return true;
    }

    public static void sdkStateChange(int i, int i2) {
        Auth.m510c().m512a(i, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String setExtValueToStr(String str, String str2, String str3) throws JSONException {
        JSONObject jSONObject;
        if (!str.isEmpty()) {
            String strDecodeAuthInfo = decodeAuthInfo(str);
            if (strDecodeAuthInfo.isEmpty()) {
                jSONObject = null;
            } else {
                try {
                    jSONObject = new JSONObject(strDecodeAuthInfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.put(str2, str3);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return encodeAuthInfo(jSONObject.toString());
    }

    public static void setKey(String str, String str2, String str3, String str4) throws IllegalAccessException, JSONException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        SharedPreferences.Editor editorEdit;
        if (!str2.equals(license_dir)) {
            doReflex(str2);
        }
        if (DeviceIdUtil.getContext() == null) {
            return;
        }
        SharedPreferences sharedPreferences = DeviceIdUtil.getContext().getSharedPreferences(str, 0);
        if (isExtensionKey(str3)) {
            String extValueToStr = setExtValueToStr(sharedPreferences.getString(license_extension, ""), str3, str4);
            editorEdit = sharedPreferences.edit();
            editorEdit.putString(license_extension, extValueToStr);
        } else {
            editorEdit = sharedPreferences.edit();
            editorEdit.putString(str3, str4);
        }
        editorEdit.apply();
    }

    public static String stringToHexString(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            stringBuffer.append(Integer.toHexString(str.charAt(i)));
        }
        return stringBuffer.toString();
    }
}
