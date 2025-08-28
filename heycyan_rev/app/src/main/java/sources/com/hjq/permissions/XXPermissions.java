package com.hjq.permissions;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.FragmentActivity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class XXPermissions {
    public static final int REQUEST_CODE = 1025;
    private static Boolean sCheckMode;
    private static OnPermissionInterceptor sInterceptor;
    private Boolean mCheckMode;
    private final Context mContext;
    private OnPermissionInterceptor mInterceptor;
    private final List<String> mPermissions = new ArrayList();

    public static XXPermissions with(Context context) {
        return new XXPermissions(context);
    }

    public static XXPermissions with(Fragment fragment) {
        return with(fragment.getActivity());
    }

    public static XXPermissions with(androidx.fragment.app.Fragment fragment) {
        return with(fragment.getActivity());
    }

    public static void setCheckMode(boolean z) {
        sCheckMode = Boolean.valueOf(z);
    }

    public static void setInterceptor(OnPermissionInterceptor onPermissionInterceptor) {
        sInterceptor = onPermissionInterceptor;
    }

    public static OnPermissionInterceptor getInterceptor() {
        if (sInterceptor == null) {
            sInterceptor = new OnPermissionInterceptor() { // from class: com.hjq.permissions.XXPermissions.1
            };
        }
        return sInterceptor;
    }

    private XXPermissions(Context context) {
        this.mContext = context;
    }

    public XXPermissions permission(String str) {
        if (str == null || PermissionUtils.containsPermission(this.mPermissions, str)) {
            return this;
        }
        this.mPermissions.add(str);
        return this;
    }

    public XXPermissions permission(String... strArr) {
        return permission(PermissionUtils.asArrayList(strArr));
    }

    public XXPermissions permission(String[]... strArr) {
        return permission(PermissionUtils.asArrayLists(strArr));
    }

    public XXPermissions permission(List<String> list) {
        if (list != null && !list.isEmpty()) {
            for (String str : list) {
                if (!PermissionUtils.containsPermission(this.mPermissions, str)) {
                    this.mPermissions.add(str);
                }
            }
        }
        return this;
    }

    public XXPermissions interceptor(OnPermissionInterceptor onPermissionInterceptor) {
        this.mInterceptor = onPermissionInterceptor;
        return this;
    }

    public XXPermissions unchecked() {
        this.mCheckMode = false;
        return this;
    }

    public void request(OnPermissionCallback onPermissionCallback) throws NoSuchMethodException, SecurityException {
        if (this.mContext == null) {
            return;
        }
        if (this.mInterceptor == null) {
            this.mInterceptor = getInterceptor();
        }
        Context context = this.mContext;
        OnPermissionInterceptor onPermissionInterceptor = this.mInterceptor;
        ArrayList arrayList = new ArrayList(this.mPermissions);
        boolean zIsCheckMode = isCheckMode(context);
        Activity activityFindActivity = PermissionUtils.findActivity(context);
        if (PermissionChecker.checkActivityStatus(activityFindActivity, zIsCheckMode) && PermissionChecker.checkPermissionArgument(arrayList, zIsCheckMode)) {
            if (zIsCheckMode) {
                AndroidManifestInfo androidManifestInfo = PermissionUtils.getAndroidManifestInfo(context);
                PermissionChecker.checkMediaLocationPermission(context, arrayList);
                PermissionChecker.checkStoragePermission(context, arrayList, androidManifestInfo);
                PermissionChecker.checkBodySensorsPermission(arrayList);
                PermissionChecker.checkLocationPermission(arrayList);
                PermissionChecker.checkPictureInPicturePermission(activityFindActivity, arrayList, androidManifestInfo);
                PermissionChecker.checkNotificationListenerPermission(arrayList, androidManifestInfo);
                PermissionChecker.checkNearbyDevicesPermission(arrayList, androidManifestInfo);
                PermissionChecker.checkReadMediaVisualUserSelectedPermission(arrayList);
                PermissionChecker.checkTargetSdkVersion(context, arrayList);
                PermissionChecker.checkManifestPermissions(context, arrayList, androidManifestInfo);
            }
            PermissionChecker.optimizeDeprecatedPermission(arrayList);
            if (PermissionApi.isGrantedPermissions(context, arrayList)) {
                onPermissionInterceptor.grantedPermissionRequest(activityFindActivity, arrayList, arrayList, true, onPermissionCallback);
                onPermissionInterceptor.finishPermissionRequest(activityFindActivity, arrayList, true, onPermissionCallback);
            } else {
                onPermissionInterceptor.launchPermissionRequest(activityFindActivity, arrayList, onPermissionCallback);
            }
        }
    }

    public boolean revokeOnKill() {
        Context context = this.mContext;
        if (context == null) {
            return false;
        }
        List<String> list = this.mPermissions;
        if (list.isEmpty() || !AndroidVersion.isAndroid13()) {
            return false;
        }
        try {
            if (list.size() == 1) {
                context.revokeSelfPermissionOnKill(list.get(0));
            } else {
                context.revokeSelfPermissionsOnKill(list);
            }
            return true;
        } catch (IllegalArgumentException e) {
            if (isCheckMode(context)) {
                throw e;
            }
            e.printStackTrace();
            return false;
        }
    }

    private boolean isCheckMode(Context context) {
        if (this.mCheckMode == null) {
            if (sCheckMode == null) {
                sCheckMode = Boolean.valueOf(PermissionUtils.isDebugMode(context));
            }
            this.mCheckMode = sCheckMode;
        }
        return this.mCheckMode.booleanValue();
    }

    public static boolean isGranted(Context context, String... strArr) {
        return isGranted(context, PermissionUtils.asArrayList(strArr));
    }

    public static boolean isGranted(Context context, String[]... strArr) {
        return isGranted(context, PermissionUtils.asArrayLists(strArr));
    }

    public static boolean isGranted(Context context, List<String> list) {
        return PermissionApi.isGrantedPermissions(context, list);
    }

    public static List<String> getDenied(Context context, String... strArr) {
        return getDenied(context, PermissionUtils.asArrayList(strArr));
    }

    public static List<String> getDenied(Context context, String[]... strArr) {
        return getDenied(context, PermissionUtils.asArrayLists(strArr));
    }

    public static List<String> getDenied(Context context, List<String> list) {
        return PermissionApi.getDeniedPermissions(context, list);
    }

    public static boolean isSpecial(String str) {
        return PermissionApi.isSpecialPermission(str);
    }

    public static boolean containsSpecial(String... strArr) {
        return containsSpecial(PermissionUtils.asArrayList(strArr));
    }

    public static boolean containsSpecial(List<String> list) {
        return PermissionApi.containsSpecialPermission(list);
    }

    public static boolean isDoNotAskAgainPermissions(Activity activity, String... strArr) {
        return isDoNotAskAgainPermissions(activity, PermissionUtils.asArrayList(strArr));
    }

    public static boolean isDoNotAskAgainPermissions(Activity activity, String[]... strArr) {
        return isDoNotAskAgainPermissions(activity, PermissionUtils.asArrayLists(strArr));
    }

    public static boolean isDoNotAskAgainPermissions(Activity activity, List<String> list) {
        return PermissionApi.isDoNotAskAgainPermissions(activity, list);
    }

    public static void startPermissionActivity(Context context) {
        startPermissionActivity(context, new ArrayList(0));
    }

    public static void startPermissionActivity(Context context, String... strArr) {
        startPermissionActivity(context, PermissionUtils.asArrayList(strArr));
    }

    public static void startPermissionActivity(Context context, String[]... strArr) {
        startPermissionActivity(context, PermissionUtils.asArrayLists(strArr));
    }

    public static void startPermissionActivity(Context context, List<String> list) {
        Activity activityFindActivity = PermissionUtils.findActivity(context);
        if (activityFindActivity != null) {
            startPermissionActivity(activityFindActivity, list);
            return;
        }
        Intent smartPermissionIntent = PermissionApi.getSmartPermissionIntent(context, list);
        if (!(context instanceof Activity)) {
            smartPermissionIntent.addFlags(268435456);
        }
        StartActivityManager.startActivity(context, smartPermissionIntent);
    }

    public static void startPermissionActivity(Activity activity) {
        startPermissionActivity(activity, (List<String>) new ArrayList(0));
    }

    public static void startPermissionActivity(Activity activity, String... strArr) {
        startPermissionActivity(activity, (List<String>) PermissionUtils.asArrayList(strArr));
    }

    public static void startPermissionActivity(Activity activity, String[]... strArr) {
        startPermissionActivity(activity, (List<String>) PermissionUtils.asArrayLists(strArr));
    }

    public static void startPermissionActivity(Activity activity, List<String> list) {
        startPermissionActivity(activity, list, 1025);
    }

    public static void startPermissionActivity(Activity activity, List<String> list, int i) {
        StartActivityManager.startActivityForResult(activity, PermissionApi.getSmartPermissionIntent(activity, list), i);
    }

    public static void startPermissionActivity(Activity activity, String str, OnPermissionPageCallback onPermissionPageCallback) {
        startPermissionActivity(activity, PermissionUtils.asArrayList(str), onPermissionPageCallback);
    }

    public static void startPermissionActivity(Activity activity, String[] strArr, OnPermissionPageCallback onPermissionPageCallback) {
        startPermissionActivity(activity, PermissionUtils.asArrayLists(strArr), onPermissionPageCallback);
    }

    public static void startPermissionActivity(Activity activity, List<String> list, OnPermissionPageCallback onPermissionPageCallback) {
        if (list.isEmpty()) {
            StartActivityManager.startActivity(activity, PermissionIntentManager.getApplicationDetailsIntent(activity));
        } else {
            PermissionPageFragment.launch(activity, list, onPermissionPageCallback);
        }
    }

    public static void startPermissionActivity(Fragment fragment) {
        startPermissionActivity(fragment, new ArrayList(0));
    }

    public static void startPermissionActivity(Fragment fragment, String... strArr) {
        startPermissionActivity(fragment, PermissionUtils.asArrayList(strArr));
    }

    public static void startPermissionActivity(Fragment fragment, String[]... strArr) {
        startPermissionActivity(fragment, PermissionUtils.asArrayLists(strArr));
    }

    public static void startPermissionActivity(Fragment fragment, List<String> list) {
        startPermissionActivity(fragment, list, 1025);
    }

    public static void startPermissionActivity(Fragment fragment, List<String> list, int i) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            return;
        }
        if (list.isEmpty()) {
            StartActivityManager.startActivity(fragment, PermissionIntentManager.getApplicationDetailsIntent(activity));
        } else {
            StartActivityManager.startActivityForResult(fragment, PermissionApi.getSmartPermissionIntent(activity, list), i);
        }
    }

    public static void startPermissionActivity(Fragment fragment, String str, OnPermissionPageCallback onPermissionPageCallback) {
        startPermissionActivity(fragment, PermissionUtils.asArrayList(str), onPermissionPageCallback);
    }

    public static void startPermissionActivity(Fragment fragment, String[] strArr, OnPermissionPageCallback onPermissionPageCallback) {
        startPermissionActivity(fragment, PermissionUtils.asArrayLists(strArr), onPermissionPageCallback);
    }

    public static void startPermissionActivity(Fragment fragment, List<String> list, OnPermissionPageCallback onPermissionPageCallback) {
        Activity activity = fragment.getActivity();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        if (AndroidVersion.isAndroid4_2() && activity.isDestroyed()) {
            return;
        }
        if (list.isEmpty()) {
            StartActivityManager.startActivity(fragment, PermissionIntentManager.getApplicationDetailsIntent(activity));
        } else {
            PermissionPageFragment.launch(activity, list, onPermissionPageCallback);
        }
    }

    public static void startPermissionActivity(androidx.fragment.app.Fragment fragment) {
        startPermissionActivity(fragment, new ArrayList());
    }

    public static void startPermissionActivity(androidx.fragment.app.Fragment fragment, String... strArr) {
        startPermissionActivity(fragment, PermissionUtils.asArrayList(strArr));
    }

    public static void startPermissionActivity(androidx.fragment.app.Fragment fragment, String[]... strArr) {
        startPermissionActivity(fragment, PermissionUtils.asArrayLists(strArr));
    }

    public static void startPermissionActivity(androidx.fragment.app.Fragment fragment, List<String> list) {
        startPermissionActivity(fragment, list, 1025);
    }

    public static void startPermissionActivity(androidx.fragment.app.Fragment fragment, List<String> list, int i) {
        FragmentActivity activity = fragment.getActivity();
        if (activity == null) {
            return;
        }
        if (list.isEmpty()) {
            StartActivityManager.startActivity(fragment, PermissionIntentManager.getApplicationDetailsIntent(activity));
        } else {
            StartActivityManager.startActivityForResult(fragment, PermissionApi.getSmartPermissionIntent(activity, list), i);
        }
    }

    public static void startPermissionActivity(androidx.fragment.app.Fragment fragment, String str, OnPermissionPageCallback onPermissionPageCallback) {
        startPermissionActivity(fragment, PermissionUtils.asArrayList(str), onPermissionPageCallback);
    }

    public static void startPermissionActivity(androidx.fragment.app.Fragment fragment, String[] strArr, OnPermissionPageCallback onPermissionPageCallback) {
        startPermissionActivity(fragment, PermissionUtils.asArrayLists(strArr), onPermissionPageCallback);
    }

    public static void startPermissionActivity(androidx.fragment.app.Fragment fragment, List<String> list, OnPermissionPageCallback onPermissionPageCallback) {
        FragmentActivity activity = fragment.getActivity();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        if (AndroidVersion.isAndroid4_2() && activity.isDestroyed()) {
            return;
        }
        if (list.isEmpty()) {
            StartActivityManager.startActivity(fragment, PermissionIntentManager.getApplicationDetailsIntent(activity));
        } else {
            PermissionPageFragment.launch(activity, list, onPermissionPageCallback);
        }
    }
}
