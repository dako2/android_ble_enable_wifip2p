package com.glasssutdio.wear.all.utils.bar;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class SystemBarTintManager {
    public static final int DEFAULT_TINT_COLOR = -1728053248;
    private static String sNavBarOverride;
    private final SystemBarConfig mConfig;
    private boolean mNavBarAvailable;
    private boolean mNavBarTintEnabled;
    private View mNavBarTintView;
    private boolean mStatusBarAvailable;
    private boolean mStatusBarTintEnabled;
    private View mStatusBarTintView;

    static {
        try {
            Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class);
            declaredMethod.setAccessible(true);
            sNavBarOverride = (String) declaredMethod.invoke(null, "qemu.hw.mainkeys");
        } catch (Throwable unused) {
            sNavBarOverride = null;
        }
    }

    public SystemBarTintManager(Activity activity) {
        Window window = activity.getWindow();
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        TypedArray typedArrayObtainStyledAttributes = activity.obtainStyledAttributes(new int[]{R.attr.windowTranslucentStatus, R.attr.windowTranslucentNavigation});
        try {
            this.mStatusBarAvailable = typedArrayObtainStyledAttributes.getBoolean(0, false);
            this.mNavBarAvailable = typedArrayObtainStyledAttributes.getBoolean(1, false);
            typedArrayObtainStyledAttributes.recycle();
            WindowManager.LayoutParams attributes = window.getAttributes();
            if ((67108864 & attributes.flags) != 0) {
                this.mStatusBarAvailable = true;
            }
            if ((attributes.flags & 134217728) != 0) {
                this.mNavBarAvailable = true;
            }
            SystemBarConfig systemBarConfig = new SystemBarConfig(activity, this.mStatusBarAvailable, this.mNavBarAvailable);
            this.mConfig = systemBarConfig;
            if (!systemBarConfig.hasNavigtionBar()) {
                this.mNavBarAvailable = false;
            }
            if (this.mStatusBarAvailable) {
                setupStatusBarView(activity, viewGroup);
            }
            if (this.mNavBarAvailable) {
                setupNavBarView(activity, viewGroup);
            }
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void setStatusBarTintEnabled(boolean enabled) {
        this.mStatusBarTintEnabled = enabled;
        if (this.mStatusBarAvailable) {
            this.mStatusBarTintView.setVisibility(enabled ? 0 : 8);
        }
    }

    public void setNavigationBarTintEnabled(boolean enabled) {
        this.mNavBarTintEnabled = enabled;
        if (this.mNavBarAvailable) {
            this.mNavBarTintView.setVisibility(enabled ? 0 : 8);
        }
    }

    public void setTintColor(int color) {
        setStatusBarTintColor(color);
        setNavigationBarTintColor(color);
    }

    public void setTintResource(int res) {
        setStatusBarTintResource(res);
        setNavigationBarTintResource(res);
    }

    public void setTintDrawable(Drawable drawable) {
        setStatusBarTintDrawable(drawable);
        setNavigationBarTintDrawable(drawable);
    }

    public void setTintAlpha(float alpha) {
        setStatusBarAlpha(alpha);
        setNavigationBarAlpha(alpha);
    }

    public void setStatusBarTintColor(int color) {
        if (this.mStatusBarAvailable) {
            this.mStatusBarTintView.setBackgroundColor(color);
        }
    }

    public void setStatusBarTintResource(int res) {
        if (this.mStatusBarAvailable) {
            this.mStatusBarTintView.setBackgroundResource(res);
        }
    }

    public void setStatusBarTintDrawable(Drawable drawable) {
        if (this.mStatusBarAvailable) {
            this.mStatusBarTintView.setBackgroundDrawable(drawable);
        }
    }

    public void setStatusBarAlpha(float alpha) {
        if (this.mStatusBarAvailable) {
            this.mStatusBarTintView.setAlpha(alpha);
        }
    }

    public void setNavigationBarTintColor(int color) {
        if (this.mNavBarAvailable) {
            this.mNavBarTintView.setBackgroundColor(color);
        }
    }

    public void setNavigationBarTintResource(int res) {
        if (this.mNavBarAvailable) {
            this.mNavBarTintView.setBackgroundResource(res);
        }
    }

    public void setNavigationBarTintDrawable(Drawable drawable) {
        if (this.mNavBarAvailable) {
            this.mNavBarTintView.setBackgroundDrawable(drawable);
        }
    }

    public void setNavigationBarAlpha(float alpha) {
        if (this.mNavBarAvailable) {
            this.mNavBarTintView.setAlpha(alpha);
        }
    }

    public SystemBarConfig getConfig() {
        return this.mConfig;
    }

    public boolean isStatusBarTintEnabled() {
        return this.mStatusBarTintEnabled;
    }

    public boolean isNavBarTintEnabled() {
        return this.mNavBarTintEnabled;
    }

    private void setupStatusBarView(Context context, ViewGroup decorViewGroup) {
        this.mStatusBarTintView = new View(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, this.mConfig.getStatusBarHeight());
        layoutParams.gravity = 48;
        if (this.mNavBarAvailable && !this.mConfig.isNavigationAtBottom()) {
            layoutParams.rightMargin = this.mConfig.getNavigationBarWidth();
        }
        this.mStatusBarTintView.setLayoutParams(layoutParams);
        this.mStatusBarTintView.setBackgroundColor(DEFAULT_TINT_COLOR);
        this.mStatusBarTintView.setVisibility(8);
        decorViewGroup.addView(this.mStatusBarTintView);
    }

    private void setupNavBarView(Context context, ViewGroup decorViewGroup) {
        FrameLayout.LayoutParams layoutParams;
        this.mNavBarTintView = new View(context);
        if (this.mConfig.isNavigationAtBottom()) {
            layoutParams = new FrameLayout.LayoutParams(-1, this.mConfig.getNavigationBarHeight());
            layoutParams.gravity = 80;
        } else {
            layoutParams = new FrameLayout.LayoutParams(this.mConfig.getNavigationBarWidth(), -1);
            layoutParams.gravity = 5;
        }
        this.mNavBarTintView.setLayoutParams(layoutParams);
        this.mNavBarTintView.setBackgroundColor(DEFAULT_TINT_COLOR);
        this.mNavBarTintView.setVisibility(8);
        decorViewGroup.addView(this.mNavBarTintView);
    }

    public static class SystemBarConfig {
        private static final String NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME = "navigation_bar_height_landscape";
        private static final String NAV_BAR_HEIGHT_RES_NAME = "navigation_bar_height";
        private static final String NAV_BAR_WIDTH_RES_NAME = "navigation_bar_width";
        private static final String SHOW_NAV_BAR_RES_NAME = "config_showNavigationBar";
        private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";
        private final int mActionBarHeight;
        private final boolean mHasNavigationBar;
        private final boolean mInPortrait;
        private final int mNavigationBarHeight;
        private final int mNavigationBarWidth;
        private final float mSmallestWidthDp;
        private final int mStatusBarHeight;
        private final boolean mTranslucentNavBar;
        private final boolean mTranslucentStatusBar;

        private SystemBarConfig(Activity activity, boolean translucentStatusBar, boolean traslucentNavBar) {
            Resources resources = activity.getResources();
            this.mInPortrait = resources.getConfiguration().orientation == 1;
            this.mSmallestWidthDp = getSmallestWidthDp(activity);
            this.mStatusBarHeight = getInternalDimensionSize(resources, STATUS_BAR_HEIGHT_RES_NAME);
            this.mActionBarHeight = getActionBarHeight(activity);
            int navigationBarHeight = getNavigationBarHeight(activity);
            this.mNavigationBarHeight = navigationBarHeight;
            this.mNavigationBarWidth = getNavigationBarWidth(activity);
            this.mHasNavigationBar = navigationBarHeight > 0;
            this.mTranslucentStatusBar = translucentStatusBar;
            this.mTranslucentNavBar = traslucentNavBar;
        }

        private int getActionBarHeight(Context context) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
            return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        }

        private int getNavigationBarHeight(Context context) {
            String str;
            Resources resources = context.getResources();
            if (!hasNavBar(context)) {
                return 0;
            }
            if (this.mInPortrait) {
                str = NAV_BAR_HEIGHT_RES_NAME;
            } else {
                str = NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME;
            }
            return getInternalDimensionSize(resources, str);
        }

        private int getNavigationBarWidth(Context context) {
            Resources resources = context.getResources();
            if (hasNavBar(context)) {
                return getInternalDimensionSize(resources, NAV_BAR_WIDTH_RES_NAME);
            }
            return 0;
        }

        private boolean hasNavBar(Context context) throws Resources.NotFoundException {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier(SHOW_NAV_BAR_RES_NAME, "bool", "android");
            if (identifier != 0) {
                boolean z = resources.getBoolean(identifier);
                if ("1".equals(SystemBarTintManager.sNavBarOverride)) {
                    return false;
                }
                if ("0".equals(SystemBarTintManager.sNavBarOverride)) {
                    return true;
                }
                return z;
            }
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }

        private int getInternalDimensionSize(Resources res, String key) {
            int identifier = res.getIdentifier(key, "dimen", "android");
            if (identifier > 0) {
                return res.getDimensionPixelSize(identifier);
            }
            return 0;
        }

        private float getSmallestWidthDp(Activity activity) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
            return Math.min(displayMetrics.widthPixels / displayMetrics.density, displayMetrics.heightPixels / displayMetrics.density);
        }

        public boolean isNavigationAtBottom() {
            return this.mSmallestWidthDp >= 600.0f || this.mInPortrait;
        }

        public int getStatusBarHeight() {
            return this.mStatusBarHeight;
        }

        public int getActionBarHeight() {
            return this.mActionBarHeight;
        }

        public boolean hasNavigtionBar() {
            return this.mHasNavigationBar;
        }

        public int getNavigationBarHeight() {
            return this.mNavigationBarHeight;
        }

        public int getNavigationBarWidth() {
            return this.mNavigationBarWidth;
        }

        public int getPixelInsetTop(boolean withActionBar) {
            return (this.mTranslucentStatusBar ? this.mStatusBarHeight : 0) + (withActionBar ? this.mActionBarHeight : 0);
        }

        public int getPixelInsetBottom() {
            if (this.mTranslucentNavBar && isNavigationAtBottom()) {
                return this.mNavigationBarHeight;
            }
            return 0;
        }

        public int getPixelInsetRight() {
            if (!this.mTranslucentNavBar || isNavigationAtBottom()) {
                return 0;
            }
            return this.mNavigationBarWidth;
        }
    }
}
