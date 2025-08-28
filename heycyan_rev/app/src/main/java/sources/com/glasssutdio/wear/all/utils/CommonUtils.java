package com.glasssutdio.wear.all.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import com.glasssutdio.wear.all.Constant;
import com.glasssutdio.wear.all.pref.MMKVConfig;
import com.glasssutdio.wear.all.pref.UserConfig;
import com.glasssutdio.wear.home.bean.GlassesType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CommonUtils.kt */
@Metadata(m606d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\u0006J\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, m607d2 = {"Lcom/glasssutdio/wear/all/utils/CommonUtils;", "", "()V", "copyClip", "", "copyStr", "", "c", "Landroid/content/Context;", "getAvatarUrl", "getCurrentGlassTypeByHw", "Lcom/glasssutdio/wear/home/bean/GlassesType;", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class CommonUtils {
    public static final CommonUtils INSTANCE = new CommonUtils();

    private CommonUtils() {
    }

    public final String getAvatarUrl() {
        return MMKVConfig.getString$default(MMKVConfig.INSTANCE.getInstance(), Constant.AVATAR_KEY + UserConfig.INSTANCE.getInstance().getUid(), null, 2, null);
    }

    public final void copyClip(String copyStr, Context c) {
        Intrinsics.checkNotNullParameter(c, "c");
        try {
            Object systemService = c.getSystemService("clipboard");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
            ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText("Label", copyStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final GlassesType getCurrentGlassTypeByHw() {
        switch (UserConfig.INSTANCE.getInstance().getGlassesModel()) {
            case 1:
                return GlassesType.A02;
            case 2:
                return GlassesType.AO3;
            case 3:
                return GlassesType.AM01;
            case 4:
                return GlassesType.KEY10;
            case 5:
                return GlassesType.KEY22;
            case 6:
                return GlassesType.KEY21;
            case 7:
                return GlassesType.KEY23;
            case 8:
                return GlassesType.KEY31;
            case 9:
                return GlassesType.KEY41;
            case 10:
                return GlassesType.KEY42;
            case 11:
                return GlassesType.KEY43;
            default:
                return GlassesType.A02;
        }
    }
}
