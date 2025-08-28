package com.hjq.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes2.dex */
class PermissionDelegateImplV26 extends PermissionDelegateImplV23 {
    PermissionDelegateImplV26() {
    }

    @Override // com.hjq.permissions.PermissionDelegateImplV23, com.hjq.permissions.PermissionDelegateImplV21, com.hjq.permissions.PermissionDelegateImplV19, com.hjq.permissions.PermissionDelegateImplV18, com.hjq.permissions.PermissionDelegateImplBase, com.hjq.permissions.PermissionDelegate
    public boolean isGrantedPermission(Context context, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.REQUEST_INSTALL_PACKAGES)) {
            if (AndroidVersion.isAndroid8()) {
                return isGrantedInstallPermission(context);
            }
            return true;
        }
        if (PermissionUtils.equalsPermission(str, Permission.PICTURE_IN_PICTURE)) {
            if (AndroidVersion.isAndroid8()) {
                return isGrantedPictureInPicturePermission(context);
            }
            return true;
        }
        if (PermissionUtils.equalsPermission(str, Permission.READ_PHONE_NUMBERS)) {
            if (!AndroidVersion.isAndroid6()) {
                return true;
            }
            if (!AndroidVersion.isAndroid8()) {
                return PermissionUtils.checkSelfPermission(context, Permission.READ_PHONE_STATE);
            }
            return PermissionUtils.checkSelfPermission(context, str);
        }
        if (PermissionUtils.equalsPermission(str, Permission.ANSWER_PHONE_CALLS)) {
            if (AndroidVersion.isAndroid8()) {
                return PermissionUtils.checkSelfPermission(context, str);
            }
            return true;
        }
        return super.isGrantedPermission(context, str);
    }

    @Override // com.hjq.permissions.PermissionDelegateImplV23, com.hjq.permissions.PermissionDelegateImplV21, com.hjq.permissions.PermissionDelegateImplV19, com.hjq.permissions.PermissionDelegateImplV18, com.hjq.permissions.PermissionDelegateImplBase, com.hjq.permissions.PermissionDelegate
    public boolean isDoNotAskAgainPermission(Activity activity, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.REQUEST_INSTALL_PACKAGES) || PermissionUtils.equalsPermission(str, Permission.PICTURE_IN_PICTURE)) {
            return false;
        }
        if (PermissionUtils.equalsPermission(str, Permission.READ_PHONE_NUMBERS)) {
            if (AndroidVersion.isAndroid6()) {
                return !AndroidVersion.isAndroid8() ? (PermissionUtils.checkSelfPermission(activity, Permission.READ_PHONE_STATE) || PermissionUtils.shouldShowRequestPermissionRationale(activity, Permission.READ_PHONE_STATE)) ? false : true : (PermissionUtils.checkSelfPermission(activity, str) || PermissionUtils.shouldShowRequestPermissionRationale(activity, str)) ? false : true;
            }
            return false;
        }
        if (PermissionUtils.equalsPermission(str, Permission.ANSWER_PHONE_CALLS)) {
            return (!AndroidVersion.isAndroid8() || PermissionUtils.checkSelfPermission(activity, str) || PermissionUtils.shouldShowRequestPermissionRationale(activity, str)) ? false : true;
        }
        return super.isDoNotAskAgainPermission(activity, str);
    }

    @Override // com.hjq.permissions.PermissionDelegateImplV23, com.hjq.permissions.PermissionDelegateImplV21, com.hjq.permissions.PermissionDelegateImplV19, com.hjq.permissions.PermissionDelegateImplV18, com.hjq.permissions.PermissionDelegateImplBase, com.hjq.permissions.PermissionDelegate
    public Intent getPermissionSettingIntent(Context context, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.REQUEST_INSTALL_PACKAGES)) {
            if (!AndroidVersion.isAndroid8()) {
                return getApplicationDetailsIntent(context);
            }
            return getInstallPermissionIntent(context);
        }
        if (PermissionUtils.equalsPermission(str, Permission.PICTURE_IN_PICTURE)) {
            if (!AndroidVersion.isAndroid8()) {
                return getApplicationDetailsIntent(context);
            }
            return getPictureInPicturePermissionIntent(context);
        }
        return super.getPermissionSettingIntent(context, str);
    }

    private static boolean isGrantedInstallPermission(Context context) {
        return context.getPackageManager().canRequestPackageInstalls();
    }

    private static Intent getInstallPermissionIntent(Context context) {
        Intent intent = new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES");
        intent.setData(PermissionUtils.getPackageNameUri(context));
        return !PermissionUtils.areActivityIntent(context, intent) ? getApplicationDetailsIntent(context) : intent;
    }

    private static boolean isGrantedPictureInPicturePermission(Context context) {
        return PermissionUtils.checkOpNoThrow(context, "android:picture_in_picture");
    }

    private static Intent getPictureInPicturePermissionIntent(Context context) {
        Intent intent = new Intent("android.settings.PICTURE_IN_PICTURE_SETTINGS");
        intent.setData(PermissionUtils.getPackageNameUri(context));
        return !PermissionUtils.areActivityIntent(context, intent) ? getApplicationDetailsIntent(context) : intent;
    }
}
