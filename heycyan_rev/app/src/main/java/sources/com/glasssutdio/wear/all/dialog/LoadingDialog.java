package com.glasssutdio.wear.all.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.WindowManager;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.view.CircleProgressView;

/* loaded from: classes.dex */
public class LoadingDialog extends AlertDialog {
    private Builder mBuilder;
    private CircleProgressView mLoadingView;

    private LoadingDialog(Builder builder) {
        super(builder.mContext, C0775R.style.loading_dialog);
        this.mBuilder = builder;
        setCancelable(builder.mCancelable);
        setCanceledOnTouchOutside(this.mBuilder.mCanceledOnTouchOutside);
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0775R.layout.layout_dialog);
        this.mLoadingView = (CircleProgressView) findViewById(C0775R.id.loading_progress);
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.glasssutdio.wear.all.dialog.LoadingDialog.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialog) {
                LoadingDialog.this.mLoadingView.setVisibility(8);
            }
        });
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        this.mLoadingView.setVisibility(0);
        this.mLoadingView.startProgressAnimation();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 80;
        attributes.width = -1;
        attributes.height = this.mBuilder.screenHeight - this.mBuilder.statusBar;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(attributes);
    }

    public Builder getBuilder() {
        return this.mBuilder;
    }

    public static class Builder {
        private Context mContext;
        private CharSequence mLoadText;
        private int screenHeight;
        private int statusBar;
        private int mDelay = 1;
        private boolean mCancelable = true;
        private boolean mCanceledOnTouchOutside = true;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder delay(int delay) {
            this.mDelay = delay;
            return this;
        }

        public Builder loadText(CharSequence loadText) {
            this.mLoadText = loadText;
            return this;
        }

        public Builder loadText(int resId) {
            this.mLoadText = this.mContext.getString(resId);
            return this;
        }

        public Builder cancelable(boolean cancelable) {
            this.mCancelable = cancelable;
            this.mCanceledOnTouchOutside = cancelable;
            return this;
        }

        public Builder setScreenAndStatus(int height, int bar) {
            this.screenHeight = height;
            this.statusBar = bar;
            return this;
        }

        public Builder canceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.mCanceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public LoadingDialog build() {
            return new LoadingDialog(this);
        }

        public LoadingDialog show() {
            LoadingDialog loadingDialogBuild = build();
            loadingDialogBuild.show();
            return loadingDialogBuild;
        }
    }
}
