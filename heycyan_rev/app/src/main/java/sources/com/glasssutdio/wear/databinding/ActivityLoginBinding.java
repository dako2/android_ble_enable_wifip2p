package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityLoginBinding implements ViewBinding {
    public final SetCodeViewBinding codeView;
    public final EditText etAccount;
    public final EditText etPwd;
    public final FrameLayout flCodeView;
    public final ImageView ivAppName;
    public final ImageView ivChecked;
    public final ImageView ivDivider;
    public final ImageView ivSeePwd;
    public final LinearLayout llAccount;
    public final LinearLayout llBottom;
    public final ConstraintLayout llPwd;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TextView tvAgreement;
    public final TextView tvForgetPwd;
    public final TextView tvLogin;
    public final TextView tvPwdHint;
    public final TextView tvSkip;
    public final TextView tvSwitchLogin;

    private ActivityLoginBinding(ConstraintLayout rootView, SetCodeViewBinding codeView, EditText etAccount, EditText etPwd, FrameLayout flCodeView, ImageView ivAppName, ImageView ivChecked, ImageView ivDivider, ImageView ivSeePwd, LinearLayout llAccount, LinearLayout llBottom, ConstraintLayout llPwd, ConstraintLayout main, TextView tvAgreement, TextView tvForgetPwd, TextView tvLogin, TextView tvPwdHint, TextView tvSkip, TextView tvSwitchLogin) {
        this.rootView = rootView;
        this.codeView = codeView;
        this.etAccount = etAccount;
        this.etPwd = etPwd;
        this.flCodeView = flCodeView;
        this.ivAppName = ivAppName;
        this.ivChecked = ivChecked;
        this.ivDivider = ivDivider;
        this.ivSeePwd = ivSeePwd;
        this.llAccount = llAccount;
        this.llBottom = llBottom;
        this.llPwd = llPwd;
        this.main = main;
        this.tvAgreement = tvAgreement;
        this.tvForgetPwd = tvForgetPwd;
        this.tvLogin = tvLogin;
        this.tvPwdHint = tvPwdHint;
        this.tvSkip = tvSkip;
        this.tvSwitchLogin = tvSwitchLogin;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_login, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityLoginBinding bind(View rootView) {
        int i = C0775R.id.code_view;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
        if (viewFindChildViewById != null) {
            SetCodeViewBinding setCodeViewBindingBind = SetCodeViewBinding.bind(viewFindChildViewById);
            i = C0775R.id.et_account;
            EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i);
            if (editText != null) {
                i = C0775R.id.et_pwd;
                EditText editText2 = (EditText) ViewBindings.findChildViewById(rootView, i);
                if (editText2 != null) {
                    i = C0775R.id.fl_code_view;
                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, i);
                    if (frameLayout != null) {
                        i = C0775R.id.iv_app_name;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                        if (imageView != null) {
                            i = C0775R.id.iv_checked;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                            if (imageView2 != null) {
                                i = C0775R.id.iv_divider;
                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                if (imageView3 != null) {
                                    i = C0775R.id.iv_see_pwd;
                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                    if (imageView4 != null) {
                                        i = C0775R.id.ll_account;
                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i);
                                        if (linearLayout != null) {
                                            i = C0775R.id.ll_bottom;
                                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i);
                                            if (linearLayout2 != null) {
                                                i = C0775R.id.ll_pwd;
                                                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                                if (constraintLayout != null) {
                                                    ConstraintLayout constraintLayout2 = (ConstraintLayout) rootView;
                                                    i = C0775R.id.tv_agreement;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                    if (textView != null) {
                                                        i = C0775R.id.tv_forget_pwd;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                        if (textView2 != null) {
                                                            i = C0775R.id.tv_login;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                            if (textView3 != null) {
                                                                i = C0775R.id.tv_pwd_hint;
                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                if (textView4 != null) {
                                                                    i = C0775R.id.tv_skip;
                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                    if (textView5 != null) {
                                                                        i = C0775R.id.tv_switch_login;
                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                        if (textView6 != null) {
                                                                            return new ActivityLoginBinding(constraintLayout2, setCodeViewBindingBind, editText, editText2, frameLayout, imageView, imageView2, imageView3, imageView4, linearLayout, linearLayout2, constraintLayout, constraintLayout2, textView, textView2, textView3, textView4, textView5, textView6);
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
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
