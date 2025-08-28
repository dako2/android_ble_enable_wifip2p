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
import com.glasssutdio.wear.all.view.PasswordEditText;

/* loaded from: classes.dex */
public final class ActivityRetrieveBinding implements ViewBinding {
    public final EditText etCode;
    public final EditText etEmail;
    public final ImageView ivBack;
    public final ConstraintLayout main;
    public final PasswordEditText petPassword;
    public final PasswordEditText petPasswordConfirm;
    private final ConstraintLayout rootView;
    public final TextView tvAccount;
    public final TextView tvCode;
    public final TextView tvConfirm;
    public final TextView tvConfirmPwdHint;
    public final TextView tvGetCode;
    public final TextView tvPwdHint;
    public final TextView tvTitle;

    private ActivityRetrieveBinding(ConstraintLayout rootView, EditText etCode, EditText etEmail, ImageView ivBack, ConstraintLayout main, PasswordEditText petPassword, PasswordEditText petPasswordConfirm, TextView tvAccount, TextView tvCode, TextView tvConfirm, TextView tvConfirmPwdHint, TextView tvGetCode, TextView tvPwdHint, TextView tvTitle) {
        this.rootView = rootView;
        this.etCode = etCode;
        this.etEmail = etEmail;
        this.ivBack = ivBack;
        this.main = main;
        this.petPassword = petPassword;
        this.petPasswordConfirm = petPasswordConfirm;
        this.tvAccount = tvAccount;
        this.tvCode = tvCode;
        this.tvConfirm = tvConfirm;
        this.tvConfirmPwdHint = tvConfirmPwdHint;
        this.tvGetCode = tvGetCode;
        this.tvPwdHint = tvPwdHint;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityRetrieveBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityRetrieveBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_retrieve, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityRetrieveBinding bind(View rootView) {
        int i = C0775R.id.et_code;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i);
        if (editText != null) {
            i = C0775R.id.et_email;
            EditText editText2 = (EditText) ViewBindings.findChildViewById(rootView, i);
            if (editText2 != null) {
                i = C0775R.id.iv_back;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                if (imageView != null) {
                    ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
                    i = C0775R.id.pet_password;
                    PasswordEditText passwordEditText = (PasswordEditText) ViewBindings.findChildViewById(rootView, i);
                    if (passwordEditText != null) {
                        i = C0775R.id.pet_password_confirm;
                        PasswordEditText passwordEditText2 = (PasswordEditText) ViewBindings.findChildViewById(rootView, i);
                        if (passwordEditText2 != null) {
                            i = C0775R.id.tv_account;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView != null) {
                                i = C0775R.id.tv_code;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                if (textView2 != null) {
                                    i = C0775R.id.tv_confirm;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                    if (textView3 != null) {
                                        i = C0775R.id.tv_confirm_pwd_hint;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                        if (textView4 != null) {
                                            i = C0775R.id.tv_get_code;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                            if (textView5 != null) {
                                                i = C0775R.id.tv_pwd_hint;
                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                if (textView6 != null) {
                                                    i = C0775R.id.tv_title;
                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                    if (textView7 != null) {
                                                        return new ActivityRetrieveBinding(constraintLayout, editText, editText2, imageView, constraintLayout, passwordEditText, passwordEditText2, textView, textView2, textView3, textView4, textView5, textView6, textView7);
                                                    }
                                                }
                                            }
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
