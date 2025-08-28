package com.thanosfisherman.wifiutils.utils;

import android.content.Intent;
import android.os.Build;
import kotlin.Metadata;

/* compiled from: VersionUtil.kt */
@Metadata(m606d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\b\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, m607d2 = {"Lcom/thanosfisherman/wifiutils/utils/VersionUtil;", "", "()V", "getPanelIntent", "Landroid/content/Intent;", "is29AndAbove", "", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes2.dex */
public final class VersionUtil {
    public static final VersionUtil INSTANCE = new VersionUtil();

    private VersionUtil() {
    }

    public final boolean is29AndAbove() {
        return Build.VERSION.SDK_INT >= 29;
    }

    public final Intent getPanelIntent() {
        return new Intent("android.settings.panel.action.WIFI");
    }
}
