package com.hjq.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.VpnService;
import java.util.Collections;

/* loaded from: classes2.dex */
class PermissionDelegateImplBase implements PermissionDelegate {
    PermissionDelegateImplBase() {
    }

    @Override // com.hjq.permissions.PermissionDelegate
    public boolean isGrantedPermission(Context context, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.BIND_VPN_SERVICE)) {
            return isGrantedVpnPermission(context);
        }
        return true;
    }

    @Override // com.hjq.permissions.PermissionDelegate
    public boolean isDoNotAskAgainPermission(Activity activity, String str) {
        PermissionUtils.equalsPermission(str, Permission.BIND_VPN_SERVICE);
        return false;
    }

    @Override // com.hjq.permissions.PermissionDelegate
    public boolean recheckPermissionResult(Context context, String str, boolean z) {
        if (PermissionApi.isSpecialPermission(str)) {
            return isGrantedPermission(context, str);
        }
        return PermissionHelper.findAndroidVersionByPermission(str) > AndroidVersion.getAndroidVersionCode() ? isGrantedPermission(context, str) : z;
    }

    @Override // com.hjq.permissions.PermissionDelegate
    public Intent getPermissionSettingIntent(Context context, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.BIND_VPN_SERVICE)) {
            return getVpnPermissionIntent(context);
        }
        return PermissionIntentManager.getApplicationDetailsIntent(context, Collections.singletonList(str));
    }

    private static boolean isGrantedVpnPermission(Context context) {
        return VpnService.prepare(context) == null;
    }

    private static Intent getVpnPermissionIntent(Context context) {
        Intent intentPrepare = VpnService.prepare(context);
        return !PermissionUtils.areActivityIntent(context, intentPrepare) ? PermissionIntentManager.getApplicationDetailsIntent(context) : intentPrepare;
    }

    static Intent getApplicationDetailsIntent(Context context) {
        return PermissionIntentManager.getApplicationDetailsIntent(context);
    }
}
