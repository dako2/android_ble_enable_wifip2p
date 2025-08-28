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
public final class ActivityUserSplashGuideBinding implements ViewBinding {
    public final ImageView image1;
    public final ImageView ivClose;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TextView tvContent1;
    public final TextView tvHint;
    public final TextView tvStart;
    public final TextView tvTitle1;

    private ActivityUserSplashGuideBinding(ConstraintLayout rootView, ImageView image1, ImageView ivClose, ConstraintLayout main, TextView tvContent1, TextView tvHint, TextView tvStart, TextView tvTitle1) {
        this.rootView = rootView;
        this.image1 = image1;
        this.ivClose = ivClose;
        this.main = main;
        this.tvContent1 = tvContent1;
        this.tvHint = tvHint;
        this.tvStart = tvStart;
        this.tvTitle1 = tvTitle1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityUserSplashGuideBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityUserSplashGuideBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_user_splash_guide, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityUserSplashGuideBinding bind(View rootView) {
        int i = C0775R.id.image_1;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.iv_close;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView2 != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
                i = C0775R.id.tv_content_1;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView != null) {
                    i = C0775R.id.tv_hint;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView2 != null) {
                        i = C0775R.id.tv_start;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView3 != null) {
                            i = C0775R.id.tv_title_1;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView4 != null) {
                                return new ActivityUserSplashGuideBinding(constraintLayout, imageView, imageView2, constraintLayout, textView, textView2, textView3, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
