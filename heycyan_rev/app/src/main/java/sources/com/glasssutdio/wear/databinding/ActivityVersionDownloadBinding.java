package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityVersionDownloadBinding implements ViewBinding {
    public final ImageView ivLogo;
    public final ConstraintLayout main;
    public final ProgressBar pbProgress;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvHint;

    private ActivityVersionDownloadBinding(ConstraintLayout rootView, ImageView ivLogo, ConstraintLayout main, ProgressBar pbProgress, LayoutTitleBarBinding title, TextView tvHint) {
        this.rootView = rootView;
        this.ivLogo = ivLogo;
        this.main = main;
        this.pbProgress = pbProgress;
        this.title = title;
        this.tvHint = tvHint;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityVersionDownloadBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityVersionDownloadBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_version_download, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityVersionDownloadBinding bind(View rootView) {
        View viewFindChildViewById;
        int i = C0775R.id.iv_logo;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
            i = C0775R.id.pb_progress;
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, i);
            if (progressBar != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i = C0775R.id.title))) != null) {
                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                i = C0775R.id.tv_hint;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView != null) {
                    return new ActivityVersionDownloadBinding(constraintLayout, imageView, constraintLayout, progressBar, layoutTitleBarBindingBind, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
