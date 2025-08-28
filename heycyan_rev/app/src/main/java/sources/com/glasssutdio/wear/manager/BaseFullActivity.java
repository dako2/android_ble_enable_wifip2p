package com.glasssutdio.wear.manager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Display;
import android.view.WindowManager;
import androidx.fragment.app.FragmentActivity;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.dialog.LoadingDialog;
import com.glasssutdio.wear.all.utils.bar.NavigationBarUtil;
import com.glasssutdio.wear.all.utils.bar.StatusBarUtil;
import com.google.android.gms.location.DeviceOrientationRequest;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseFullActivity.kt */
@Metadata(m606d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u000eH&J\b\u0010\u0012\u001a\u00020\u0010H\u0016J\u0006\u0010\u0013\u001a\u00020\u0010J\b\u0010\u0014\u001a\u00020\u000eH\u0016J\u0012\u0010\u0015\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u000eH\u0014J\u0006\u0010\u0019\u001a\u00020\u000eR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m607d2 = {"Lcom/glasssutdio/wear/manager/BaseFullActivity;", "Landroidx/fragment/app/FragmentActivity;", "()V", "activity", "Landroid/app/Activity;", "getActivity", "()Landroid/app/Activity;", "setActivity", "(Landroid/app/Activity;)V", "dialog", "Lcom/glasssutdio/wear/all/dialog/LoadingDialog;", "handler", "Landroid/os/Handler;", "dismissLoadingDialog", "", "immerseNavigationBar", "", "initView", "isBarIconDark", "isDialogShowing", "observer", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "showLoadingDialog", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public abstract class BaseFullActivity extends FragmentActivity {
    private Activity activity;
    private LoadingDialog dialog;
    private final Handler handler;

    public boolean immerseNavigationBar() {
        return true;
    }

    public abstract void initView();

    public boolean isBarIconDark() {
        return true;
    }

    public void observer() {
    }

    public BaseFullActivity() {
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.glasssutdio.wear.manager.BaseFullActivity$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    protected final Activity getActivity() {
        return this.activity;
    }

    protected final void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseFullActivity baseFullActivity = this;
        this.activity = baseFullActivity;
        initView();
        observer();
        StatusBarUtil.setTranslucentStatus(baseFullActivity);
        StatusBarUtil.setRootViewFitsSystemWindows(baseFullActivity, false);
        StatusBarUtil.setStatusBarDarkTheme(baseFullActivity, isBarIconDark());
        NavigationBarUtil.INSTANCE.setNavigationBarColor(baseFullActivity, C0775R.color.transparent);
        if (immerseNavigationBar()) {
            NavigationBarUtil.INSTANCE.immersiveNavigationBar(baseFullActivity);
        }
        NavigationBarUtil.INSTANCE.setNavigationBarTheme(baseFullActivity, isBarIconDark());
        NavigationBarUtil.INSTANCE.showHideNavigationBar(baseFullActivity, false);
    }

    public final void showLoadingDialog() {
        try {
            Activity activity = this.activity;
            if (activity != null) {
                Intrinsics.checkNotNull(activity);
                if (activity.isDestroyed()) {
                    return;
                }
                if (this.dialog == null) {
                    WindowManager windowManager = getWindowManager();
                    Intrinsics.checkNotNullExpressionValue(windowManager, "getWindowManager(...)");
                    Display defaultDisplay = windowManager.getDefaultDisplay();
                    Intrinsics.checkNotNullExpressionValue(defaultDisplay, "getDefaultDisplay(...)");
                    this.dialog = new LoadingDialog.Builder(this).setScreenAndStatus(defaultDisplay.getHeight(), StatusBarUtil.getStatusBarHeight(this)).build();
                }
                LoadingDialog loadingDialog = this.dialog;
                Intrinsics.checkNotNull(loadingDialog);
                loadingDialog.show();
                this.handler.postDelayed(new Runnable() { // from class: com.glasssutdio.wear.manager.BaseFullActivity$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseFullActivity.showLoadingDialog$lambda$0(this.f$0);
                    }
                }, DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showLoadingDialog$lambda$0(BaseFullActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissLoadingDialog();
    }

    public final boolean isDialogShowing() {
        LoadingDialog loadingDialog;
        if (this.activity == null || (loadingDialog = this.dialog) == null) {
            return false;
        }
        Intrinsics.checkNotNull(loadingDialog);
        return loadingDialog.isShowing();
    }

    public final void dismissLoadingDialog() {
        LoadingDialog loadingDialog;
        try {
            Activity activity = this.activity;
            if (activity != null) {
                Intrinsics.checkNotNull(activity);
                if (activity.isDestroyed() || (loadingDialog = this.dialog) == null) {
                    return;
                }
                Intrinsics.checkNotNull(loadingDialog);
                if (loadingDialog.isShowing()) {
                    LoadingDialog loadingDialog2 = this.dialog;
                    Intrinsics.checkNotNull(loadingDialog2);
                    loadingDialog2.dismiss();
                }
            }
        } catch (Exception unused) {
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        dismissLoadingDialog();
        super.onDestroy();
    }
}
