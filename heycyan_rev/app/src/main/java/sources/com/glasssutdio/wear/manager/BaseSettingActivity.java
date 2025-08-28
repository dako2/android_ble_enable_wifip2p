package com.glasssutdio.wear.manager;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.dialog.LoadingDialog;
import com.glasssutdio.wear.bus.BusEvent;
import com.google.android.gms.location.DeviceOrientationRequest;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: BaseSettingActivity.kt */
@Metadata(m606d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u0018H\u0016J\u0012\u0010\u001a\u001a\u00020\u00122\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u0012H\u0014J\u0010\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 H\u0017J\u0012\u0010!\u001a\u00020\u00122\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0012\u0010!\u001a\u00020\u00122\b\b\u0001\u0010$\u001a\u00020\u0014H\u0016J\u0006\u0010%\u001a\u00020\u0012J\u000e\u0010&\u001a\u00020\u00122\u0006\u0010'\u001a\u00020\u0014J\u000e\u0010(\u001a\u00020\u00122\u0006\u0010'\u001a\u00020\u0014R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, m607d2 = {"Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "activity", "Landroid/app/Activity;", "getActivity", "()Landroid/app/Activity;", "setActivity", "(Landroid/app/Activity;)V", "activityWR", "Ljava/lang/ref/WeakReference;", "dialog", "Lcom/glasssutdio/wear/all/dialog/LoadingDialog;", "handler", "Landroid/os/Handler;", "mFrameLayoutContent", "Landroid/widget/FrameLayout;", "dismissLoadingDialog", "", "getStatusBarHeight", "", "context", "Landroid/content/Context;", "isDialogShowing", "", "needStatusBarPadding", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/glasssutdio/wear/bus/BusEvent;", "setContentView", "view", "Landroid/view/View;", "layoutResID", "showLoadingDialog", "showLoadingDialogTimeout", "timeout", "showLoadingDialogTimeoutNotCancel", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public class BaseSettingActivity extends AppCompatActivity {
    private Activity activity;
    private WeakReference<Activity> activityWR;
    private LoadingDialog dialog;
    private final Handler handler;
    private FrameLayout mFrameLayoutContent;

    public boolean needStatusBarPadding() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BusEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
    }

    public BaseSettingActivity() {
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.glasssutdio.wear.manager.BaseSettingActivity$handler$1
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
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        super.setContentView(C0775R.layout.activity_base);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(C0775R.id.base_setting), new OnApplyWindowInsetsListener() { // from class: com.glasssutdio.wear.manager.BaseSettingActivity$$ExternalSyntheticLambda2
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return BaseSettingActivity.onCreate$lambda$0(this.f$0, view, windowInsetsCompat);
            }
        });
        this.mFrameLayoutContent = (FrameLayout) findViewById(C0775R.id.frame_layout_content_place);
        getWindow().addFlags(Integer.MIN_VALUE);
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, C0775R.color.g_color_1));
        this.activity = this;
        Activity activity = this.activity;
        Intrinsics.checkNotNull(activity);
        this.activityWR = new WeakReference<>(activity);
        ActivityCollector.INSTANCE.pushTask(this.activityWR);
        EventBus.getDefault().register(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat onCreate$lambda$0(BaseSettingActivity this$0, View v, WindowInsetsCompat insets) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(insets, "insets");
        Insets insets2 = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(insets2, "getInsets(...)");
        v.setPadding(insets2.left, this$0.needStatusBarPadding() ? insets2.top : 0, insets2.right, insets2.bottom);
        return insets;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int layoutResID) {
        try {
            View viewInflate = LayoutInflater.from(this).inflate(layoutResID, (ViewGroup) this.mFrameLayoutContent, false);
            FrameLayout frameLayout = this.mFrameLayoutContent;
            if (frameLayout != null) {
                Intrinsics.checkNotNull(frameLayout);
                frameLayout.addView(viewInflate);
            } else {
                FrameLayout frameLayout2 = (FrameLayout) findViewById(C0775R.id.frame_layout_content_place);
                this.mFrameLayoutContent = frameLayout2;
                Intrinsics.checkNotNull(frameLayout2);
                frameLayout2.addView(viewInflate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view) {
        try {
            FrameLayout frameLayout = this.mFrameLayoutContent;
            if (frameLayout != null) {
                Intrinsics.checkNotNull(frameLayout);
                frameLayout.addView(view);
            } else {
                FrameLayout frameLayout2 = (FrameLayout) findViewById(C0775R.id.frame_layout_content_place);
                this.mFrameLayoutContent = frameLayout2;
                Intrinsics.checkNotNull(frameLayout2);
                frameLayout2.addView(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.activity = null;
        ActivityCollector.INSTANCE.removeTask(this.activityWR);
        EventBus.getDefault().unregister(this);
    }

    private final int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
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
                    this.dialog = new LoadingDialog.Builder(this).setScreenAndStatus(defaultDisplay.getHeight(), getStatusBarHeight(this)).build();
                }
                LoadingDialog loadingDialog = this.dialog;
                Intrinsics.checkNotNull(loadingDialog);
                loadingDialog.show();
                this.handler.postDelayed(new Runnable() { // from class: com.glasssutdio.wear.manager.BaseSettingActivity$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseSettingActivity.showLoadingDialog$lambda$1(this.f$0);
                    }
                }, DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showLoadingDialog$lambda$1(BaseSettingActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissLoadingDialog();
    }

    public final void showLoadingDialogTimeoutNotCancel(int timeout) {
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
                    this.dialog = new LoadingDialog.Builder(this).cancelable(false).setScreenAndStatus(defaultDisplay.getHeight(), getStatusBarHeight(this)).build();
                }
                LoadingDialog loadingDialog = this.dialog;
                Intrinsics.checkNotNull(loadingDialog);
                loadingDialog.show();
                LoadingDialog loadingDialog2 = this.dialog;
                Intrinsics.checkNotNull(loadingDialog2);
                loadingDialog2.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.glasssutdio.wear.manager.BaseSettingActivity.showLoadingDialogTimeoutNotCancel.1
                    @Override // android.content.DialogInterface.OnKeyListener
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode != 4) {
                            return false;
                        }
                        XLog.m137i("------------");
                        return true;
                    }
                });
                this.handler.postDelayed(new Runnable() { // from class: com.glasssutdio.wear.manager.BaseSettingActivity$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseSettingActivity.showLoadingDialogTimeoutNotCancel$lambda$2(this.f$0);
                    }
                }, timeout);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showLoadingDialogTimeoutNotCancel$lambda$2(BaseSettingActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissLoadingDialog();
    }

    public final void showLoadingDialogTimeout(int timeout) {
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
                    this.dialog = new LoadingDialog.Builder(this).setScreenAndStatus(defaultDisplay.getHeight(), getStatusBarHeight(this)).build();
                }
                LoadingDialog loadingDialog = this.dialog;
                Intrinsics.checkNotNull(loadingDialog);
                loadingDialog.show();
                this.handler.postDelayed(new Runnable() { // from class: com.glasssutdio.wear.manager.BaseSettingActivity$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseSettingActivity.showLoadingDialogTimeout$lambda$3(this.f$0);
                    }
                }, timeout);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showLoadingDialogTimeout$lambda$3(BaseSettingActivity this$0) {
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
}
