package com.glasssutdio.wear.all.utils.bar;

import android.R;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import com.google.android.material.internal.ViewUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NavigationBarUtil.kt */
@Metadata(m606d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J$\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b\u0018\u00010\u000bJ\u000e\u0010\r\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u000f\u001a\u00020\u0004J\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\fJ\u0016\u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\f¨\u0006\u0014"}, m607d2 = {"Lcom/glasssutdio/wear/all/utils/bar/NavigationBarUtil;", "", "()V", "getNavigationBarHeight", "", "context", "Landroid/app/Activity;", "hasNavigationBars", "", "activity", "action", "Lkotlin/Function1;", "", "immersiveNavigationBar", "setNavigationBarColor", TypedValues.Custom.S_COLOR, "setNavigationBarTheme", "dark", "showHideNavigationBar", "isShow", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class NavigationBarUtil {
    public static final NavigationBarUtil INSTANCE = new NavigationBarUtil();

    private NavigationBarUtil() {
    }

    public final void setNavigationBarColor(Activity context, int color) {
        Intrinsics.checkNotNullParameter(context, "context");
        Window window = context.getWindow();
        window.setNavigationBarColor(ContextCompat.getColor(context, color));
        window.addFlags(134217728);
    }

    public final void immersiveNavigationBar(Activity context) {
        Intrinsics.checkNotNullParameter(context, "context");
        View decorView = context.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | ViewUtils.EDGE_TO_EDGE_FLAGS);
        context.getWindow().setNavigationBarColor(0);
    }

    public final void setNavigationBarTheme(Activity context, boolean dark) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (Build.VERSION.SDK_INT >= 26) {
            View decorView = context.getWindow().getDecorView();
            Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
            int systemUiVisibility = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(dark ? systemUiVisibility | 16 : systemUiVisibility & (-17));
        }
    }

    public final int getNavigationBarHeight(Activity context) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(context, "context");
        int identifier = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier <= 0) {
            return 0;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(identifier);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        context.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        if (displayMetrics.heightPixels > i) {
            return dimensionPixelSize;
        }
        return 0;
    }

    public final void showHideNavigationBar(Activity activity, boolean isShow) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        WindowInsetsControllerCompat windowInsetsController = ViewCompat.getWindowInsetsController(activity.findViewById(R.id.content));
        if (windowInsetsController != null) {
            if (isShow) {
                windowInsetsController.show(WindowInsetsCompat.Type.navigationBars());
                windowInsetsController.setSystemBarsBehavior(1);
            } else {
                windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars());
                windowInsetsController.setSystemBarsBehavior(2);
            }
        }
    }

    public final void hasNavigationBars(Activity activity, final Function1<? super Boolean, Unit> action) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        View viewFindViewById = activity.findViewById(R.id.content);
        if (viewFindViewById.isAttachedToWindow()) {
            WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(viewFindViewById);
            if (rootWindowInsets != null) {
                boolean z = rootWindowInsets.isVisible(WindowInsetsCompat.Type.navigationBars()) && rootWindowInsets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom > 0;
                if (action != null) {
                    action.invoke(Boolean.valueOf(z));
                    return;
                }
                return;
            }
            return;
        }
        viewFindViewById.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.glasssutdio.wear.all.utils.bar.NavigationBarUtil.hasNavigationBars.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View v) {
                Intrinsics.checkNotNullParameter(v, "v");
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View v) {
                Intrinsics.checkNotNullParameter(v, "v");
                WindowInsetsCompat rootWindowInsets2 = ViewCompat.getRootWindowInsets(v);
                if (rootWindowInsets2 != null) {
                    boolean z2 = rootWindowInsets2.isVisible(WindowInsetsCompat.Type.navigationBars()) && rootWindowInsets2.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom > 0;
                    Function1<Boolean, Unit> function1 = action;
                    if (function1 != null) {
                        function1.invoke(Boolean.valueOf(z2));
                    }
                }
            }
        });
    }
}
