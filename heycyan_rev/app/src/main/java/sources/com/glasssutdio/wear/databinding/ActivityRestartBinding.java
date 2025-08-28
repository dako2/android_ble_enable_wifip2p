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
public final class ActivityRestartBinding implements ViewBinding {
    public final ImageView appBack;
    public final ImageView imageGlass;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TextView tv1;
    public final TextView tv2;
    public final TextView tv3;
    public final TextView tvTitle;

    private ActivityRestartBinding(ConstraintLayout rootView, ImageView appBack, ImageView imageGlass, ConstraintLayout main, TextView tv1, TextView tv2, TextView tv3, TextView tvTitle) {
        this.rootView = rootView;
        this.appBack = appBack;
        this.imageGlass = imageGlass;
        this.main = main;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityRestartBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityRestartBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_restart, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityRestartBinding bind(View rootView) {
        int i = C0775R.id.app_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.image_glass;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView2 != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
                i = C0775R.id.tv_1;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView != null) {
                    i = C0775R.id.tv_2;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView2 != null) {
                        i = C0775R.id.tv_3;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView3 != null) {
                            i = C0775R.id.tvTitle;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView4 != null) {
                                return new ActivityRestartBinding(constraintLayout, imageView, imageView2, constraintLayout, textView, textView2, textView3, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
