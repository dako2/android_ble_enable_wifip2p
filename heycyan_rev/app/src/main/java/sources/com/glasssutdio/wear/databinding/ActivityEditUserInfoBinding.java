package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityEditUserInfoBinding implements ViewBinding {
    public final ConstraintLayout clsAvatar;
    public final ConstraintLayout clsBirthday;
    public final ConstraintLayout clsNickname;
    public final ConstraintLayout clsSex;
    public final ImageView ivArrow;
    public final ImageView ivArrow1;
    public final ImageView ivArrow2;
    public final ImageView ivArrow3;
    public final ImageFilterView ivAvatar;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvBirthday;
    public final TextView tvNickname;
    public final TextView tvSex;

    private ActivityEditUserInfoBinding(ConstraintLayout rootView, ConstraintLayout clsAvatar, ConstraintLayout clsBirthday, ConstraintLayout clsNickname, ConstraintLayout clsSex, ImageView ivArrow, ImageView ivArrow1, ImageView ivArrow2, ImageView ivArrow3, ImageFilterView ivAvatar, ConstraintLayout main, LayoutTitleBarBinding title, TextView tvBirthday, TextView tvNickname, TextView tvSex) {
        this.rootView = rootView;
        this.clsAvatar = clsAvatar;
        this.clsBirthday = clsBirthday;
        this.clsNickname = clsNickname;
        this.clsSex = clsSex;
        this.ivArrow = ivArrow;
        this.ivArrow1 = ivArrow1;
        this.ivArrow2 = ivArrow2;
        this.ivArrow3 = ivArrow3;
        this.ivAvatar = ivAvatar;
        this.main = main;
        this.title = title;
        this.tvBirthday = tvBirthday;
        this.tvNickname = tvNickname;
        this.tvSex = tvSex;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityEditUserInfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityEditUserInfoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_edit_user_info, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityEditUserInfoBinding bind(View rootView) {
        int i = C0775R.id.cls_avatar;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.cls_birthday;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                i = C0775R.id.cls_nickname;
                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                if (constraintLayout3 != null) {
                    i = C0775R.id.cls_sex;
                    ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                    if (constraintLayout4 != null) {
                        i = C0775R.id.iv_arrow;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                        if (imageView != null) {
                            i = C0775R.id.iv_arrow_1;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                            if (imageView2 != null) {
                                i = C0775R.id.iv_arrow_2;
                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                if (imageView3 != null) {
                                    i = C0775R.id.iv_arrow_3;
                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                    if (imageView4 != null) {
                                        i = C0775R.id.iv_avatar;
                                        ImageFilterView imageFilterView = (ImageFilterView) ViewBindings.findChildViewById(rootView, i);
                                        if (imageFilterView != null) {
                                            ConstraintLayout constraintLayout5 = (ConstraintLayout) rootView;
                                            i = C0775R.id.title;
                                            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
                                            if (viewFindChildViewById != null) {
                                                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                                i = C0775R.id.tv_birthday;
                                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                if (textView != null) {
                                                    i = C0775R.id.tv_nickname;
                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                    if (textView2 != null) {
                                                        i = C0775R.id.tv_sex;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                        if (textView3 != null) {
                                                            return new ActivityEditUserInfoBinding(constraintLayout5, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, imageView, imageView2, imageView3, imageView4, imageFilterView, constraintLayout5, layoutTitleBarBindingBind, textView, textView2, textView3);
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
