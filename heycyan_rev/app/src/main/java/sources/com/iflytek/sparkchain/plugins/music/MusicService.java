package com.iflytek.sparkchain.plugins.music;

import android.accessibilityservice.AccessibilityService;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import ifld.C2443a;

/* loaded from: classes2.dex */
public class MusicService extends AccessibilityService {

    /* renamed from: a */
    private final String f510a = "SparkChain";

    /* renamed from: a */
    public static boolean m547a(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return false;
        }
        if (accessibilityNodeInfo.isClickable()) {
            accessibilityNodeInfo.performAction(16);
            return true;
        }
        AccessibilityNodeInfo parent = accessibilityNodeInfo.getParent();
        if (parent == null) {
            return false;
        }
        boolean zM547a = m547a(parent);
        parent.recycle();
        return zM547a;
    }

    /* renamed from: b */
    public void m548b(AccessibilityNodeInfo accessibilityNodeInfo) {
        CharSequence text = accessibilityNodeInfo.getText();
        if (text == null) {
            text = "";
        }
        Bundle bundle = new Bundle();
        bundle.putInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SELECTION_START_INT, 0);
        bundle.putInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SELECTION_END_INT, text.length());
        accessibilityNodeInfo.performAction(1);
        accessibilityNodeInfo.performAction(131072, bundle);
        accessibilityNodeInfo.performAction(32768);
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) throws InterruptedException {
        CharSequence packageName = accessibilityEvent.getPackageName();
        if (32 == accessibilityEvent.getEventType() && !TextUtils.isEmpty(packageName)) {
            Log.d("SparkChain", ((Object) packageName) + HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR);
            if ("com.netease.cloudmusic".equals(packageName.toString())) {
                Log.d("SparkChain", accessibilityEvent.toString());
                AccessibilityNodeInfo accessibilityNodeInfoM598a = C2443a.m598a(this, "com.netease.cloudmusic:id/searchBar", "", "");
                if (accessibilityNodeInfoM598a != null) {
                    m547a(accessibilityNodeInfoM598a);
                    accessibilityNodeInfoM598a.recycle();
                } else {
                    Log.d("SparkChain", "searchBar not found");
                }
                AccessibilityNodeInfo accessibilityNodeInfoM598a2 = C2443a.m598a(this, "com.netease.cloudmusic:id/search_src_text", "", "");
                if (accessibilityNodeInfoM598a2 == null) {
                    Log.d("SparkChain", "searchSrcText not found");
                    return;
                }
                m548b(accessibilityNodeInfoM598a2);
                AccessibilityNodeInfo accessibilityNodeInfoM598a3 = C2443a.m598a(this, "com.netease.cloudmusic:id/toSearch", "", "");
                if (accessibilityNodeInfoM598a3 != null) {
                    if (m547a(accessibilityNodeInfoM598a3)) {
                        try {
                            Thread.sleep(3000L);
                            AccessibilityNodeInfo accessibilityNodeInfoM598a4 = C2443a.m598a(this, "com.netease.cloudmusic:id/actionView", "", "");
                            if (accessibilityNodeInfoM598a4 != null) {
                                m547a(accessibilityNodeInfoM598a4);
                                accessibilityNodeInfoM598a4.recycle();
                            } else {
                                Log.d("SparkChain", "playAll not found");
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    accessibilityNodeInfoM598a3.recycle();
                } else {
                    Log.d("SparkChain", "toSearch not found");
                }
                accessibilityNodeInfoM598a2.recycle();
            }
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onInterrupt() {
        Log.e("SparkChain", "MusicService Interrupted");
    }

    @Override // android.accessibilityservice.AccessibilityService
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.e("SparkChain", "MusicService Connected");
    }
}
