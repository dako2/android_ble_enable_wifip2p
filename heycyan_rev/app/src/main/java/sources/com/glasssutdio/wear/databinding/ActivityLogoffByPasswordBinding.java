package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityLogoffByPasswordBinding implements ViewBinding {
    public final EditText etPwd;
    public final ImageView ivSeePwd;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvConfirm;
    public final TextView tvHint;

    private ActivityLogoffByPasswordBinding(ConstraintLayout rootView, EditText etPwd, ImageView ivSeePwd, ConstraintLayout main, LayoutTitleBarBinding title, TextView tvConfirm, TextView tvHint) {
        this.rootView = rootView;
        this.etPwd = etPwd;
        this.ivSeePwd = ivSeePwd;
        this.main = main;
        this.title = title;
        this.tvConfirm = tvConfirm;
        this.tvHint = tvHint;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityLogoffByPasswordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityLogoffByPasswordBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_logoff_by_password, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityLogoffByPasswordBinding bind(View rootView) {
        int i = C0775R.id.et_pwd;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i);
        if (editText != null) {
            i = C0775R.id.iv_see_pwd;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
                i = C0775R.id.title;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
                if (viewFindChildViewById != null) {
                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                    i = C0775R.id.tv_confirm;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView != null) {
                        i = C0775R.id.tv_hint;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView2 != null) {
                            return new ActivityLogoffByPasswordBinding(constraintLayout, editText, imageView, constraintLayout, layoutTitleBarBindingBind, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
