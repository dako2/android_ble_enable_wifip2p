package com.glasssutdio.wear.all.utils.bar;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class StatusBarUtil {
    public static final int TYPE_FLYME = 1;
    public static final int TYPE_M = 3;
    public static final int TYPE_MIUI = 0;

    @Retention(RetentionPolicy.SOURCE)
    @interface ViewType {
    }

    public static void setStatusBarColor(Activity activity, int colorId) {
        activity.getWindow().setStatusBarColor(colorId);
    }

    public static void setTranslucentStatus(Activity activity) {
        Window window = activity.getWindow();
        window.getDecorView().setSystemUiVisibility(1280);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(0);
    }

    public static void setRootViewFitsSystemWindows(Activity activity, boolean fitSystemWindows) {
        ViewGroup viewGroup;
        ViewGroup viewGroup2 = (ViewGroup) activity.findViewById(R.id.content);
        if (viewGroup2.getChildCount() <= 0 || (viewGroup = (ViewGroup) viewGroup2.getChildAt(0)) == null) {
            return;
        }
        viewGroup.setFitsSystemWindows(fitSystemWindows);
    }

    public static boolean setStatusBarDarkTheme(Activity activity, boolean dark) {
        setStatusBarFontIconDark(activity, 3, dark);
        return true;
    }

    public static boolean setStatusBarFontIconDark(Activity activity, int type, boolean dark) {
        if (type == 0) {
            return setMiuiUI(activity, dark);
        }
        if (type == 1) {
            return setFlymeUI(activity, dark);
        }
        return setCommonUI(activity, dark);
    }

    public static boolean setCommonUI(Activity activity, boolean dark) {
        View decorView = activity.getWindow().getDecorView();
        if (decorView == null) {
            return false;
        }
        int systemUiVisibility = decorView.getSystemUiVisibility();
        int i = dark ? systemUiVisibility | 8192 : systemUiVisibility & (-8193);
        if (decorView.getSystemUiVisibility() == i) {
            return true;
        }
        decorView.setSystemUiVisibility(i);
        return true;
    }

    public static boolean setFlymeUI(Activity activity, boolean dark) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        try {
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            declaredField.setAccessible(true);
            declaredField2.setAccessible(true);
            int i = declaredField.getInt(null);
            int i2 = declaredField2.getInt(attributes);
            declaredField2.setInt(attributes, dark ? i2 | i : (~i) & i2);
            window.setAttributes(attributes);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean setMiuiUI(Activity activity, boolean dark) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            Window window = activity.getWindow();
            Class<?> cls = activity.getWindow().getClass();
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method declaredMethod = cls.getDeclaredMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
            declaredMethod.setAccessible(true);
            if (dark) {
                declaredMethod.invoke(window, Integer.valueOf(i), Integer.valueOf(i));
            } else {
                declaredMethod.invoke(window, 0, Integer.valueOf(i));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }
}
