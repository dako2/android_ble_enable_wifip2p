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
public final class ActivityAccountLogoffBinding implements ViewBinding {
    public final ImageView ivPhoneLogo;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvCancel;
    public final TextView tvConfirm;
    public final TextView tvHint;
    public final TextView tvHint2;

    private ActivityAccountLogoffBinding(ConstraintLayout rootView, ImageView ivPhoneLogo, ConstraintLayout main, LayoutTitleBarBinding title, TextView tvCancel, TextView tvConfirm, TextView tvHint, TextView tvHint2) {
        this.rootView = rootView;
        this.ivPhoneLogo = ivPhoneLogo;
        this.main = main;
        this.title = title;
        this.tvCancel = tvCancel;
        this.tvConfirm = tvConfirm;
        this.tvHint = tvHint;
        this.tvHint2 = tvHint2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAccountLogoffBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAccountLogoffBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_account_logoff, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAccountLogoffBinding bind(View rootView) {
        int i = C0775R.id.iv_phone_logo;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
            i = C0775R.id.title;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
            if (viewFindChildViewById != null) {
                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                i = C0775R.id.tv_cancel;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView != null) {
                    i = C0775R.id.tv_confirm;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView2 != null) {
                        i = C0775R.id.tv_hint;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView3 != null) {
                            i = C0775R.id.tv_hint2;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView4 != null) {
                                return new ActivityAccountLogoffBinding(constraintLayout, imageView, constraintLayout, layoutTitleBarBindingBind, textView, textView2, textView3, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
