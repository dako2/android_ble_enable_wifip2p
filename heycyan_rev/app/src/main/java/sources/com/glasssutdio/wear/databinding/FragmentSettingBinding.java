package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class FragmentSettingBinding implements ViewBinding {
    public final ConstraintLayout clsAbout;
    public final ConstraintLayout clsAccount;
    public final ConstraintLayout clsFaq;
    public final ConstraintLayout clsFeedback;
    public final ConstraintLayout clsGuide;
    public final ConstraintLayout clsPermission;
    public final ConstraintLayout clsTop;
    public final ImageFilterView ivAvatar;
    public final ImageView ivNew;
    public final LinearLayout ll1;
    public final LinearLayout ll2;
    private final ConstraintLayout rootView;
    public final TextView tvAbout;
    public final TextView tvEdit;
    public final TextView tvFaq;
    public final TextView tvPermissionHint;
    public final TextView tvTitle;
    public final TextView tvUserName;

    private FragmentSettingBinding(ConstraintLayout rootView, ConstraintLayout clsAbout, ConstraintLayout clsAccount, ConstraintLayout clsFaq, ConstraintLayout clsFeedback, ConstraintLayout clsGuide, ConstraintLayout clsPermission, ConstraintLayout clsTop, ImageFilterView ivAvatar, ImageView ivNew, LinearLayout ll1, LinearLayout ll2, TextView tvAbout, TextView tvEdit, TextView tvFaq, TextView tvPermissionHint, TextView tvTitle, TextView tvUserName) {
        this.rootView = rootView;
        this.clsAbout = clsAbout;
        this.clsAccount = clsAccount;
        this.clsFaq = clsFaq;
        this.clsFeedback = clsFeedback;
        this.clsGuide = clsGuide;
        this.clsPermission = clsPermission;
        this.clsTop = clsTop;
        this.ivAvatar = ivAvatar;
        this.ivNew = ivNew;
        this.ll1 = ll1;
        this.ll2 = ll2;
        this.tvAbout = tvAbout;
        this.tvEdit = tvEdit;
        this.tvFaq = tvFaq;
        this.tvPermissionHint = tvPermissionHint;
        this.tvTitle = tvTitle;
        this.tvUserName = tvUserName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentSettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.fragment_setting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentSettingBinding bind(View rootView) {
        int i = C0775R.id.cls_about;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.cls_account;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                i = C0775R.id.cls_faq;
                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                if (constraintLayout3 != null) {
                    i = C0775R.id.cls_feedback;
                    ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                    if (constraintLayout4 != null) {
                        i = C0775R.id.cls_guide;
                        ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                        if (constraintLayout5 != null) {
                            i = C0775R.id.cls_permission;
                            ConstraintLayout constraintLayout6 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                            if (constraintLayout6 != null) {
                                i = C0775R.id.cls_top;
                                ConstraintLayout constraintLayout7 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                if (constraintLayout7 != null) {
                                    i = C0775R.id.iv_avatar;
                                    ImageFilterView imageFilterView = (ImageFilterView) ViewBindings.findChildViewById(rootView, i);
                                    if (imageFilterView != null) {
                                        i = C0775R.id.iv_new;
                                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                        if (imageView != null) {
                                            i = C0775R.id.ll_1;
                                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i);
                                            if (linearLayout != null) {
                                                i = C0775R.id.ll_2;
                                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i);
                                                if (linearLayout2 != null) {
                                                    i = C0775R.id.tv_about;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                    if (textView != null) {
                                                        i = C0775R.id.tv_edit;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                        if (textView2 != null) {
                                                            i = C0775R.id.tv_faq;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                            if (textView3 != null) {
                                                                i = C0775R.id.tv_permission_hint;
                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                if (textView4 != null) {
                                                                    i = C0775R.id.tvTitle;
                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                    if (textView5 != null) {
                                                                        i = C0775R.id.tv_user_name;
                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                        if (textView6 != null) {
                                                                            return new FragmentSettingBinding((ConstraintLayout) rootView, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, constraintLayout5, constraintLayout6, constraintLayout7, imageFilterView, imageView, linearLayout, linearLayout2, textView, textView2, textView3, textView4, textView5, textView6);
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
