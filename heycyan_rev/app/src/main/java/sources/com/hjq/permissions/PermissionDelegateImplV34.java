package com.hjq.permissions;

import android.app.Activity;
import android.content.Context;

/* loaded from: classes2.dex */
class PermissionDelegateImplV34 extends PermissionDelegateImplV33 {
    PermissionDelegateImplV34() {
    }

    @Override // com.hjq.permissions.PermissionDelegateImplV33, com.hjq.permissions.PermissionDelegateImplV31, com.hjq.permissions.PermissionDelegateImplV30, com.hjq.permissions.PermissionDelegateImplV29, com.hjq.permissions.PermissionDelegateImplV28, com.hjq.permissions.PermissionDelegateImplV26, com.hjq.permissions.PermissionDelegateImplV23, com.hjq.permissions.PermissionDelegateImplV21, com.hjq.permissions.PermissionDelegateImplV19, com.hjq.permissions.PermissionDelegateImplV18, com.hjq.permissions.PermissionDelegateImplBase, com.hjq.permissions.PermissionDelegate
    public boolean isGrantedPermission(Context context, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.READ_MEDIA_VISUAL_USER_SELECTED)) {
            if (AndroidVersion.isAndroid14()) {
                return PermissionUtils.checkSelfPermission(context, str);
            }
            return true;
        }
        return super.isGrantedPermission(context, str);
    }

    @Override // com.hjq.permissions.PermissionDelegateImplV33, com.hjq.permissions.PermissionDelegateImplV31, com.hjq.permissions.PermissionDelegateImplV30, com.hjq.permissions.PermissionDelegateImplV29, com.hjq.permissions.PermissionDelegateImplV28, com.hjq.permissions.PermissionDelegateImplV26, com.hjq.permissions.PermissionDelegateImplV23, com.hjq.permissions.PermissionDelegateImplV21, com.hjq.permissions.PermissionDelegateImplV19, com.hjq.permissions.PermissionDelegateImplV18, com.hjq.permissions.PermissionDelegateImplBase, com.hjq.permissions.PermissionDelegate
    public boolean isDoNotAskAgainPermission(Activity activity, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.READ_MEDIA_VISUAL_USER_SELECTED)) {
            return (!AndroidVersion.isAndroid14() || PermissionUtils.checkSelfPermission(activity, str) || PermissionUtils.shouldShowRequestPermissionRationale(activity, str)) ? false : true;
        }
        return super.isDoNotAskAgainPermission(activity, str);
    }

    @Override // com.hjq.permissions.PermissionDelegateImplV33, com.hjq.permissions.PermissionDelegateImplV23, com.hjq.permissions.PermissionDelegateImplBase, com.hjq.permissions.PermissionDelegate
    public boolean recheckPermissionResult(Context context, String str, boolean z) {
        if (AndroidVersion.isAndroid14() && PermissionUtils.containsPermission(new String[]{Permission.READ_MEDIA_IMAGES, Permission.READ_MEDIA_VIDEO}, str)) {
            return isGrantedPermission(context, Permission.READ_MEDIA_VISUAL_USER_SELECTED);
        }
        return super.recheckPermissionResult(context, str, z);
    }
}
