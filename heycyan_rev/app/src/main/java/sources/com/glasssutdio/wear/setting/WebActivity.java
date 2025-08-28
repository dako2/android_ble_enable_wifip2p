package com.glasssutdio.wear.setting;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.databinding.ActivityWebBinding;
import com.glasssutdio.wear.manager.BaseSettingActivity;
import com.iflytek.sparkchain.utils.DataUtil;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WebActivity.kt */
@Metadata(m606d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0012\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\b\u0010\u000b\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\f"}, m607d2 = {"Lcom/glasssutdio/wear/setting/WebActivity;", "Lcom/glasssutdio/wear/manager/BaseSettingActivity;", "()V", "binding", "Lcom/glasssutdio/wear/databinding/ActivityWebBinding;", "handleBack", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class WebActivity extends BaseSettingActivity {
    private ActivityWebBinding binding;

    @Override // com.glasssutdio.wear.manager.BaseSettingActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        ActivityWebBinding activityWebBindingInflate = ActivityWebBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityWebBindingInflate, "inflate(...)");
        this.binding = activityWebBindingInflate;
        if (activityWebBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWebBindingInflate = null;
        }
        setContentView(activityWebBindingInflate.getRoot());
        setupViews();
    }

    private final void setupViews() {
        ActivityWebBinding activityWebBinding = this.binding;
        ActivityWebBinding activityWebBinding2 = null;
        if (activityWebBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWebBinding = null;
        }
        activityWebBinding.titleBar.appBack.setOnClickListener(new View.OnClickListener() { // from class: com.glasssutdio.wear.setting.WebActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebActivity.setupViews$lambda$1$lambda$0(this.f$0, view);
            }
        });
        Bundle extras = getIntent().getExtras();
        String string = extras != null ? extras.getString("url") : null;
        XLog.m137i(string);
        ActivityWebBinding activityWebBinding3 = this.binding;
        if (activityWebBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityWebBinding2 = activityWebBinding3;
        }
        WebView webView = activityWebBinding2.webView;
        Intrinsics.checkNotNullExpressionValue(webView, "webView");
        WebSettings settings = webView.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings, "getSettings(...)");
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setDefaultTextEncodingName(DataUtil.UTF8);
        settings.setCacheMode(-1);
        webView.setWebViewClient(new WebViewClient() { // from class: com.glasssutdio.wear.setting.WebActivity.setupViews.2
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (view == null) {
                    return true;
                }
                view.loadUrl(String.valueOf(request != null ? request.getUrl() : null));
                return true;
            }

            @Override // android.webkit.WebViewClient
            @Deprecated(message = "Deprecated in Java")
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null || view == null) {
                    return true;
                }
                view.loadUrl(url);
                return true;
            }
        });
        if (string != null) {
            webView.loadUrl(string);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupViews$lambda$1$lambda$0(WebActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.handleBack();
    }

    private final void handleBack() {
        ActivityWebBinding activityWebBinding = this.binding;
        if (activityWebBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWebBinding = null;
        }
        WebView webView = activityWebBinding.webView;
        Intrinsics.checkNotNullExpressionValue(webView, "webView");
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        handleBack();
    }
}
