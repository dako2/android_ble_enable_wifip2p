package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityAboutBinding implements ViewBinding {
    public final ConstraintLayout clsCheckUpdate;
    public final ImageView ivLogo;
    public final ImageView ivNew;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvAbout;
    public final TextView tvAgreement;
    public final TextView tvPolicy;
    public final TextView tvTitle;
    public final TextView tvVersion;

    private ActivityAboutBinding(ConstraintLayout rootView, ConstraintLayout clsCheckUpdate, ImageView ivLogo, ImageView ivNew, ConstraintLayout main, LayoutTitleBarBinding title, TextView tvAbout, TextView tvAgreement, TextView tvPolicy, TextView tvTitle, TextView tvVersion) {
        this.rootView = rootView;
        this.clsCheckUpdate = clsCheckUpdate;
        this.ivLogo = ivLogo;
        this.ivNew = ivNew;
        this.main = main;
        this.title = title;
        this.tvAbout = tvAbout;
        this.tvAgreement = tvAgreement;
        this.tvPolicy = tvPolicy;
        this.tvTitle = tvTitle;
        this.tvVersion = tvVersion;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAboutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAboutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_about, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAboutBinding bind(View rootView) {
        int i = C0775R.id.cls_check_update;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.iv_logo;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView != null) {
                i = C0775R.id.iv_new;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                if (imageView2 != null) {
                    ConstraintLayout constraintLayout2 = (ConstraintLayout) rootView;
                    i = C0775R.id.title;
                    View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
                    if (viewFindChildViewById != null) {
                        LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                        i = C0775R.id.tv_about;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView != null) {
                            i = C0775R.id.tv_agreement;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView2 != null) {
                                i = C0775R.id.tv_policy;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                if (textView3 != null) {
                                    i = C0775R.id.tv_title;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                    if (textView4 != null) {
                                        i = C0775R.id.tv_version;
                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                        if (textView5 != null) {
                                            return new ActivityAboutBinding(constraintLayout2, constraintLayout, imageView, imageView2, constraintLayout2, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
