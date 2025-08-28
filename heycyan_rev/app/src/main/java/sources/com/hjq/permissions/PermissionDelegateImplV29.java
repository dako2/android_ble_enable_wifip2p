package com.hjq.permissions;

import android.app.Activity;
import android.content.Context;

/* loaded from: classes2.dex */
class PermissionDelegateImplV29 extends PermissionDelegateImplV28 {
    PermissionDelegateImplV29() {
    }

    @Override // com.hjq.permissions.PermissionDelegateImplV28, com.hjq.permissions.PermissionDelegateImplV26, com.hjq.permissions.PermissionDelegateImplV23, com.hjq.permissions.PermissionDelegateImplV21, com.hjq.permissions.PermissionDelegateImplV19, com.hjq.permissions.PermissionDelegateImplV18, com.hjq.permissions.PermissionDelegateImplBase, com.hjq.permissions.PermissionDelegate
    public boolean isGrantedPermission(Context context, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.ACCESS_MEDIA_LOCATION)) {
            if (!AndroidVersion.isAndroid6()) {
                return true;
            }
            if (AndroidVersion.isAndroid10()) {
                return isGrantedReadStoragePermission(context) && PermissionUtils.checkSelfPermission(context, Permission.ACCESS_MEDIA_LOCATION);
            }
            return PermissionUtils.checkSelfPermission(context, Permission.READ_EXTERNAL_STORAGE);
        }
        if (PermissionUtils.equalsPermission(str, Permission.ACCESS_BACKGROUND_LOCATION)) {
            if (!AndroidVersion.isAndroid6()) {
                return true;
            }
            if (!AndroidVersion.isAndroid10()) {
                return PermissionUtils.checkSelfPermission(context, Permission.ACCESS_FINE_LOCATION);
            }
            return PermissionUtils.checkSelfPermission(context, str);
        }
        if (PermissionUtils.equalsPermission(str, Permission.ACTIVITY_RECOGNITION)) {
            if (AndroidVersion.isAndroid10()) {
                return PermissionUtils.checkSelfPermission(context, str);
            }
            return true;
        }
        return super.isGrantedPermission(context, str);
    }

    @Override // com.hjq.permissions.PermissionDelegateImplV28, com.hjq.permissions.PermissionDelegateImplV26, com.hjq.permissions.PermissionDelegateImplV23, com.hjq.permissions.PermissionDelegateImplV21, com.hjq.permissions.PermissionDelegateImplV19, com.hjq.permissions.PermissionDelegateImplV18, com.hjq.permissions.PermissionDelegateImplBase, com.hjq.permissions.PermissionDelegate
    public boolean isDoNotAskAgainPermission(Activity activity, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.ACCESS_BACKGROUND_LOCATION)) {
            if (!AndroidVersion.isAndroid6()) {
                return false;
            }
            if (!AndroidVersion.isAndroid10()) {
                return (PermissionUtils.checkSelfPermission(activity, Permission.ACCESS_FINE_LOCATION) || PermissionUtils.shouldShowRequestPermissionRationale(activity, Permission.ACCESS_FINE_LOCATION)) ? false : true;
            }
            if (PermissionUtils.checkSelfPermission(activity, Permission.ACCESS_FINE_LOCATION)) {
                return (PermissionUtils.checkSelfPermission(activity, str) || PermissionUtils.shouldShowRequestPermissionRationale(activity, str)) ? false : true;
            }
            return !PermissionUtils.shouldShowRequestPermissionRationale(activity, Permission.ACCESS_FINE_LOCATION);
        }
        if (PermissionUtils.equalsPermission(str, Permission.ACCESS_MEDIA_LOCATION)) {
            if (AndroidVersion.isAndroid6()) {
                return !AndroidVersion.isAndroid10() ? (PermissionUtils.checkSelfPermission(activity, Permission.READ_EXTERNAL_STORAGE) || PermissionUtils.shouldShowRequestPermissionRationale(activity, Permission.READ_EXTERNAL_STORAGE)) ? false : true : (!isGrantedReadStoragePermission(activity) || PermissionUtils.checkSelfPermission(activity, str) || PermissionUtils.shouldShowRequestPermissionRationale(activity, str)) ? false : true;
            }
            return false;
        }
        if (PermissionUtils.equalsPermission(str, Permission.ACTIVITY_RECOGNITION)) {
            if (AndroidVersion.isAndroid10()) {
                return (PermissionUtils.checkSelfPermission(activity, str) || PermissionUtils.shouldShowRequestPermissionRationale(activity, str)) ? false : true;
            }
            return false;
        }
        return super.isDoNotAskAgainPermission(activity, str);
    }

    private boolean isGrantedReadStoragePermission(Context context) {
        if (AndroidVersion.isAndroid13() && AndroidVersion.getTargetSdkVersionCode(context) >= 33) {
            return PermissionUtils.checkSelfPermission(context, Permission.READ_MEDIA_IMAGES) || isGrantedPermission(context, Permission.MANAGE_EXTERNAL_STORAGE);
        }
        if (!AndroidVersion.isAndroid11() || AndroidVersion.getTargetSdkVersionCode(context) < 30) {
            return PermissionUtils.checkSelfPermission(context, Permission.READ_EXTERNAL_STORAGE);
        }
        return PermissionUtils.checkSelfPermission(context, Permission.READ_EXTERNAL_STORAGE) || isGrantedPermission(context, Permission.MANAGE_EXTERNAL_STORAGE);
    }
}
