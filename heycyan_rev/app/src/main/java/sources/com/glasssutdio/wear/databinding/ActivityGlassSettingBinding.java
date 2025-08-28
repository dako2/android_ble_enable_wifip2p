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
public final class ActivityGlassSettingBinding implements ViewBinding {
    public final ImageView appBack;
    public final ConstraintLayout ctlAbout;
    public final ConstraintLayout ctlCancelPair;
    public final ConstraintLayout ctlReset;
    public final ConstraintLayout ctlRestart;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TextView tvTitle;

    private ActivityGlassSettingBinding(ConstraintLayout rootView, ImageView appBack, ConstraintLayout ctlAbout, ConstraintLayout ctlCancelPair, ConstraintLayout ctlReset, ConstraintLayout ctlRestart, ConstraintLayout main, TextView tvTitle) {
        this.rootView = rootView;
        this.appBack = appBack;
        this.ctlAbout = ctlAbout;
        this.ctlCancelPair = ctlCancelPair;
        this.ctlReset = ctlReset;
        this.ctlRestart = ctlRestart;
        this.main = main;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityGlassSettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityGlassSettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_glass_setting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityGlassSettingBinding bind(View rootView) {
        int i = C0775R.id.app_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.ctl_about;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout != null) {
                i = C0775R.id.ctl_cancel_pair;
                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                if (constraintLayout2 != null) {
                    i = C0775R.id.ctl_reset;
                    ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                    if (constraintLayout3 != null) {
                        i = C0775R.id.ctl_restart;
                        ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                        if (constraintLayout4 != null) {
                            ConstraintLayout constraintLayout5 = (ConstraintLayout) rootView;
                            i = C0775R.id.tvTitle;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView != null) {
                                return new ActivityGlassSettingBinding(constraintLayout5, imageView, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, constraintLayout5, textView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
