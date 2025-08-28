package com.hjq.permissions;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes2.dex */
final class PermissionUtils {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    PermissionUtils() {
    }

    static boolean checkSelfPermission(Context context, String str) {
        return context.checkSelfPermission(str) == 0;
    }

    static boolean checkOpNoThrow(Context context, String str, int i) throws ClassNotFoundException {
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String packageName = context.getApplicationContext().getPackageName();
        int i2 = applicationInfo.uid;
        try {
            Class<?> cls = Class.forName(AppOpsManager.class.getName());
            try {
                i = ((Integer) cls.getDeclaredField(str).get(Integer.class)).intValue();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            return ((Integer) cls.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class).invoke(appOpsManager, Integer.valueOf(i), Integer.valueOf(i2), packageName)).intValue() == 0;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | RuntimeException | InvocationTargetException unused) {
            return true;
        }
    }

    static boolean checkOpNoThrow(Context context, String str) {
        int iCheckOpNoThrow;
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
        if (AndroidVersion.isAndroid10()) {
            iCheckOpNoThrow = appOpsManager.unsafeCheckOpNoThrow(str, context.getApplicationInfo().uid, context.getPackageName());
        } else {
            iCheckOpNoThrow = appOpsManager.checkOpNoThrow(str, context.getApplicationInfo().uid, context.getPackageName());
        }
        return iCheckOpNoThrow == 0;
    }

    static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
        if (AndroidVersion.getAndroidVersionCode() == 31) {
            try {
                return ((Boolean) PackageManager.class.getMethod("shouldShowRequestPermissionRationale", String.class).invoke(activity.getApplication().getPackageManager(), str)).booleanValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return activity.shouldShowRequestPermissionRationale(str);
    }

    static void postActivityResult(List<String> list, Runnable runnable) {
        long j = 300;
        long j2 = AndroidVersion.isAndroid11() ? 200L : 300L;
        if (PhoneRomUtils.isEmui() || PhoneRomUtils.isHarmonyOs()) {
            if (!AndroidVersion.isAndroid8()) {
                j = 500;
            }
        } else {
            j = (PhoneRomUtils.isMiui() && AndroidVersion.isAndroid11() && containsPermission(list, Permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)) ? 1000L : j2;
        }
        postDelayed(runnable, j);
    }

    static void postDelayed(Runnable runnable, long j) {
        HANDLER.postDelayed(runnable, j);
    }

    static boolean isDebugMode(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    static AndroidManifestInfo getAndroidManifestInfo(Context context) throws NoSuchMethodException, SecurityException {
        AndroidManifestInfo androidManifest;
        int iFindApkPathCookie = findApkPathCookie(context, context.getApplicationInfo().sourceDir);
        AndroidManifestInfo androidManifestInfo = null;
        if (iFindApkPathCookie == 0) {
            return null;
        }
        try {
            androidManifest = AndroidManifestParser.parseAndroidManifest(context, iFindApkPathCookie);
        } catch (IOException e) {
            e = e;
        } catch (XmlPullParserException e2) {
            e = e2;
        }
        try {
            if (TextUtils.equals(context.getPackageName(), androidManifest.packageName)) {
                return androidManifest;
            }
            return null;
        } catch (IOException | XmlPullParserException e3) {
            e = e3;
            androidManifestInfo = androidManifest;
            e.printStackTrace();
            return androidManifestInfo;
        }
    }

    static <T> ArrayList<T> asArrayList(T... tArr) {
        ArrayList<T> arrayList = new ArrayList<>(tArr != null ? tArr.length : 0);
        if (tArr != null && tArr.length != 0) {
            for (T t : tArr) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    @SafeVarargs
    static <T> ArrayList<T> asArrayLists(T[]... tArr) {
        ArrayList<T> arrayList = new ArrayList<>();
        if (tArr != null && tArr.length != 0) {
            for (T[] tArr2 : tArr) {
                arrayList.addAll(asArrayList(tArr2));
            }
        }
        return arrayList;
    }

    static Activity findActivity(Context context) {
        while (!(context instanceof Activity)) {
            if (!(context instanceof ContextWrapper) || (context = ((ContextWrapper) context).getBaseContext()) == null) {
                return null;
            }
        }
        return (Activity) context;
    }

    static int findApkPathCookie(Context context, String str) throws NoSuchMethodException, SecurityException {
        AssetManager assets = context.getAssets();
        try {
            if (AndroidVersion.getTargetSdkVersionCode(context) >= 28 && AndroidVersion.getAndroidVersionCode() >= 28 && AndroidVersion.getAndroidVersionCode() < 30) {
                Method declaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
                declaredMethod.setAccessible(true);
                Method method = (Method) declaredMethod.invoke(AssetManager.class, "findCookieForPath", new Class[]{String.class});
                if (method != null) {
                    method.setAccessible(true);
                    Integer num = (Integer) method.invoke(context.getAssets(), str);
                    if (num != null) {
                        return num.intValue();
                    }
                }
            }
            Integer num2 = (Integer) assets.getClass().getDeclaredMethod("addAssetPath", String.class).invoke(assets, str);
            if (num2 != null) {
                return num2.intValue();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
        return 0;
    }

    static boolean isScopedStorage(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null || !bundle.containsKey("ScopedStorage")) {
                return false;
            }
            return bundle.getBoolean("ScopedStorage");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    static void lockActivityOrientation(Activity activity) {
        try {
            int i = activity.getResources().getConfiguration().orientation;
            if (i == 1) {
                activity.setRequestedOrientation(isActivityReverse(activity) ? 9 : 1);
            } else if (i == 2) {
                activity.setRequestedOrientation(isActivityReverse(activity) ? 8 : 0);
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    static boolean isActivityReverse(Activity activity) {
        Display defaultDisplay;
        if (AndroidVersion.isAndroid11()) {
            defaultDisplay = activity.getDisplay();
        } else {
            WindowManager windowManager = activity.getWindowManager();
            defaultDisplay = windowManager != null ? windowManager.getDefaultDisplay() : null;
        }
        if (defaultDisplay == null) {
            return false;
        }
        int rotation = defaultDisplay.getRotation();
        return rotation == 2 || rotation == 3;
    }

    static boolean areActivityIntent(Context context, Intent intent) {
        if (intent == null) {
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        if (AndroidVersion.isAndroid13()) {
            return !packageManager.queryIntentActivities(intent, PackageManager.ResolveInfoFlags.of(65536L)).isEmpty();
        }
        return !packageManager.queryIntentActivities(intent, 65536).isEmpty();
    }

    static boolean equalsPermission(String str, String str2) {
        int length = str.length();
        if (length != str2.length()) {
            return false;
        }
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    static boolean containsPermission(String[] strArr, String str) {
        return containsPermission(Arrays.asList(strArr), str);
    }

    static boolean containsPermission(Collection<String> collection, String str) {
        if (collection.isEmpty()) {
            return false;
        }
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            if (equalsPermission(it.next(), str)) {
                return true;
            }
        }
        return false;
    }

    static Uri getPackageNameUri(Context context) {
        return Uri.parse("package:" + context.getPackageName());
    }
}
