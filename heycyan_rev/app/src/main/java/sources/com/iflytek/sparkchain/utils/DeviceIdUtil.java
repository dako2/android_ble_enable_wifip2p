package com.iflytek.sparkchain.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.iflytek.sparkchain.core.Auth;
import java.io.File;

/* loaded from: classes2.dex */
public class DeviceIdUtil {
    /* renamed from: fa */
    public static String m549fa() {
        try {
            return ((TelephonyManager) getContext().getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            Log.i("AEE", "fa:" + e.toString());
            return "";
        }
    }

    /* renamed from: fb */
    public static String m550fb() {
        try {
            return Settings.Secure.getString(getContext().getContentResolver(), "android_id");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: fc */
    public static String m551fc() throws ClassNotFoundException {
        String serial;
        try {
            int i = Build.VERSION.SDK_INT;
            if (i >= 28) {
                serial = Build.getSerial();
            } else if (i > 24) {
                serial = Build.SERIAL;
            } else {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                serial = (String) cls.getMethod("get", String.class).invoke(cls, "ro.serialno");
            }
            if (!TextUtils.isEmpty(serial)) {
                return serial;
            }
            Class<?> cls2 = Class.forName("android.os.SystemProperties");
            return (String) cls2.getMethod("get", String.class, String.class).invoke(cls2, "gsm.scril.sn", null);
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: fd */
    public static String m552fd() {
        return "";
    }

    /* renamed from: fe */
    public static String m553fe() {
        return Auth.m510c().m519d();
    }

    /* renamed from: ff */
    public static boolean m554ff() {
        String[] strArr = {"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/"};
        for (int i = 0; i < 8; i++) {
            if (new File(strArr[i] + "su").exists()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: fg */
    public static String m555fg() throws PackageManager.NameNotFoundException {
        try {
            PackageInfo packageInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 64);
            if (packageInfo == null) {
                return "";
            }
            try {
                return new String(packageInfo.signatures[0].toByteArray());
            } catch (Throwable th) {
                th.printStackTrace();
                return "";
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    /* renamed from: fh */
    public static String m556fh() {
        return getContext().getPackageName();
    }

    protected static Context getContext() {
        return Auth.m510c().m511a();
    }

    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }
}
