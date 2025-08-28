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
public final class ActivitySafetySettingBinding implements ViewBinding {
    public final ConstraintLayout clsLogoff;
    public final ImageView ivArrow2;
    public final LinearLayout ll1;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvSafetySetting;

    private ActivitySafetySettingBinding(ConstraintLayout rootView, ConstraintLayout clsLogoff, ImageView ivArrow2, LinearLayout ll1, ConstraintLayout main, LayoutTitleBarBinding title, TextView tvSafetySetting) {
        this.rootView = rootView;
        this.clsLogoff = clsLogoff;
        this.ivArrow2 = ivArrow2;
        this.ll1 = ll1;
        this.main = main;
        this.title = title;
        this.tvSafetySetting = tvSafetySetting;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySafetySettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySafetySettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_safety_setting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivitySafetySettingBinding bind(View rootView) {
        int i = C0775R.id.cls_logoff;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.iv_arrow2;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView != null) {
                i = C0775R.id.ll_1;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i);
                if (linearLayout != null) {
                    ConstraintLayout constraintLayout2 = (ConstraintLayout) rootView;
                    i = C0775R.id.title;
                    View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
                    if (viewFindChildViewById != null) {
                        LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                        i = C0775R.id.tv_safety_setting;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView != null) {
                            return new ActivitySafetySettingBinding(constraintLayout2, constraintLayout, imageView, linearLayout, constraintLayout2, layoutTitleBarBindingBind, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
