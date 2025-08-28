package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityAccountSafetyBinding implements ViewBinding {
    public final ConstraintLayout clsPassword;
    public final ConstraintLayout clsPhone;
    public final ConstraintLayout clsSafetySetting;
    public final ImageView ivArrow;
    public final ImageView ivArrow1;
    public final ImageView ivArrow2;
    public final LinearLayout ll1;
    public final LinearLayout ll2;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvAccountTitle;
    public final TextView tvHintPwd;
    public final TextView tvLogout;
    public final TextView tvPhone;
    public final TextView tvPwd;
    public final TextView tvSafetySetting;

    private ActivityAccountSafetyBinding(ConstraintLayout rootView, ConstraintLayout clsPassword, ConstraintLayout clsPhone, ConstraintLayout clsSafetySetting, ImageView ivArrow, ImageView ivArrow1, ImageView ivArrow2, LinearLayout ll1, LinearLayout ll2, ConstraintLayout main, LayoutTitleBarBinding title, TextView tvAccountTitle, TextView tvHintPwd, TextView tvLogout, TextView tvPhone, TextView tvPwd, TextView tvSafetySetting) {
        this.rootView = rootView;
        this.clsPassword = clsPassword;
        this.clsPhone = clsPhone;
        this.clsSafetySetting = clsSafetySetting;
        this.ivArrow = ivArrow;
        this.ivArrow1 = ivArrow1;
        this.ivArrow2 = ivArrow2;
        this.ll1 = ll1;
        this.ll2 = ll2;
        this.main = main;
        this.title = title;
        this.tvAccountTitle = tvAccountTitle;
        this.tvHintPwd = tvHintPwd;
        this.tvLogout = tvLogout;
        this.tvPhone = tvPhone;
        this.tvPwd = tvPwd;
        this.tvSafetySetting = tvSafetySetting;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAccountSafetyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAccountSafetyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_account_safety, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAccountSafetyBinding bind(View rootView) {
        int i = C0775R.id.cls_password;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.cls_phone;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                i = C0775R.id.cls_safety_setting;
                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                if (constraintLayout3 != null) {
                    i = C0775R.id.iv_arrow;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                    if (imageView != null) {
                        i = C0775R.id.iv_arrow_1;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                        if (imageView2 != null) {
                            i = C0775R.id.iv_arrow2;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                            if (imageView3 != null) {
                                i = C0775R.id.ll_1;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i);
                                if (linearLayout != null) {
                                    i = C0775R.id.ll_2;
                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i);
                                    if (linearLayout2 != null) {
                                        ConstraintLayout constraintLayout4 = (ConstraintLayout) rootView;
                                        i = C0775R.id.title;
                                        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
                                        if (viewFindChildViewById != null) {
                                            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                            i = C0775R.id.tv_account_title;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                                            if (textView != null) {
                                                i = C0775R.id.tv_hint_pwd;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                if (textView2 != null) {
                                                    i = C0775R.id.tv_logout;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                    if (textView3 != null) {
                                                        i = C0775R.id.tv_phone;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                        if (textView4 != null) {
                                                            i = C0775R.id.tv_pwd;
                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                            if (textView5 != null) {
                                                                i = C0775R.id.tv_safety_setting;
                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                if (textView6 != null) {
                                                                    return new ActivityAccountSafetyBinding(constraintLayout4, constraintLayout, constraintLayout2, constraintLayout3, imageView, imageView2, imageView3, linearLayout, linearLayout2, constraintLayout4, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5, textView6);
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
