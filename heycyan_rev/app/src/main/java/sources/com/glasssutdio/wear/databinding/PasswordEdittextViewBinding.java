package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class PasswordEdittextViewBinding implements ViewBinding {
    public final EditText etPwd;
    public final ImageView ivSeePwd;
    private final ConstraintLayout rootView;

    private PasswordEdittextViewBinding(ConstraintLayout rootView, EditText etPwd, ImageView ivSeePwd) {
        this.rootView = rootView;
        this.etPwd = etPwd;
        this.ivSeePwd = ivSeePwd;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static PasswordEdittextViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static PasswordEdittextViewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.password_edittext_view, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PasswordEdittextViewBinding bind(View rootView) {
        int i = C0775R.id.et_pwd;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i);
        if (editText != null) {
            i = C0775R.id.iv_see_pwd;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView != null) {
                return new PasswordEdittextViewBinding((ConstraintLayout) rootView, editText, imageView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
