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
public final class ActivityCompleteProfileBinding implements ViewBinding {
    public final ConstraintLayout clsAccount;
    public final ConstraintLayout clsBirthday;
    public final ConstraintLayout clsSex;
    public final EditText etUsername;
    public final ImageView ivClearEt;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TextView tvBirthday;
    public final TextView tvBirthdayHint;
    public final TextView tvConfirm;
    public final TextView tvHint;
    public final TextView tvPwdHint;
    public final TextView tvSex;
    public final TextView tvSexHint;
    public final TextView tvSkip;
    public final TextView tvTitle;

    private ActivityCompleteProfileBinding(ConstraintLayout rootView, ConstraintLayout clsAccount, ConstraintLayout clsBirthday, ConstraintLayout clsSex, EditText etUsername, ImageView ivClearEt, ConstraintLayout main, TextView tvBirthday, TextView tvBirthdayHint, TextView tvConfirm, TextView tvHint, TextView tvPwdHint, TextView tvSex, TextView tvSexHint, TextView tvSkip, TextView tvTitle) {
        this.rootView = rootView;
        this.clsAccount = clsAccount;
        this.clsBirthday = clsBirthday;
        this.clsSex = clsSex;
        this.etUsername = etUsername;
        this.ivClearEt = ivClearEt;
        this.main = main;
        this.tvBirthday = tvBirthday;
        this.tvBirthdayHint = tvBirthdayHint;
        this.tvConfirm = tvConfirm;
        this.tvHint = tvHint;
        this.tvPwdHint = tvPwdHint;
        this.tvSex = tvSex;
        this.tvSexHint = tvSexHint;
        this.tvSkip = tvSkip;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityCompleteProfileBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityCompleteProfileBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_complete_profile, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityCompleteProfileBinding bind(View rootView) {
        int i = C0775R.id.cls_account;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.cls_birthday;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                i = C0775R.id.cls_sex;
                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                if (constraintLayout3 != null) {
                    i = C0775R.id.et_username;
                    EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i);
                    if (editText != null) {
                        i = C0775R.id.iv_clear_et;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                        if (imageView != null) {
                            ConstraintLayout constraintLayout4 = (ConstraintLayout) rootView;
                            i = C0775R.id.tv_birthday;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView != null) {
                                i = C0775R.id.tv_birthday_hint;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                if (textView2 != null) {
                                    i = C0775R.id.tv_confirm;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                    if (textView3 != null) {
                                        i = C0775R.id.tv_hint;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                        if (textView4 != null) {
                                            i = C0775R.id.tv_pwd_hint;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                            if (textView5 != null) {
                                                i = C0775R.id.tv_sex;
                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                if (textView6 != null) {
                                                    i = C0775R.id.tv_sex_hint;
                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                    if (textView7 != null) {
                                                        i = C0775R.id.tv_skip;
                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                        if (textView8 != null) {
                                                            i = C0775R.id.tv_title;
                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                            if (textView9 != null) {
                                                                return new ActivityCompleteProfileBinding(constraintLayout4, constraintLayout, constraintLayout2, constraintLayout3, editText, imageView, constraintLayout4, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9);
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
