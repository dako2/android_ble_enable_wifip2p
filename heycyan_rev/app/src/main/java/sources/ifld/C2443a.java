package ifld;

import android.accessibilityservice.AccessibilityService;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

/* renamed from: ifld.a */
/* loaded from: classes2.dex */
public class C2443a {
    /* renamed from: a */
    public static AccessibilityNodeInfo m598a(AccessibilityService accessibilityService, String str, String str2, String str3) {
        AccessibilityNodeInfo rootInActiveWindow;
        if ((!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str3)) && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null) {
            for (AccessibilityNodeInfo accessibilityNodeInfo : rootInActiveWindow.findAccessibilityNodeInfosByViewId(str)) {
                String string = TextUtils.isEmpty(accessibilityNodeInfo.getText()) ? "" : accessibilityNodeInfo.getText().toString();
                String string2 = TextUtils.isEmpty(accessibilityNodeInfo.getContentDescription()) ? "" : accessibilityNodeInfo.getContentDescription().toString();
                Log.w("SparkChain", "nText: " + string + " nDesc: " + string2 + " name: " + accessibilityNodeInfo.getViewIdResourceName());
                if (TextUtils.isEmpty(string2)) {
                    if (str3.equals(string2)) {
                        return accessibilityNodeInfo;
                    }
                } else if (str2.equals(string)) {
                    return accessibilityNodeInfo;
                }
            }
        }
        return null;
    }
}
