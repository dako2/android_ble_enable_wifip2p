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
public final class ActivityVideoLengthBinding implements ViewBinding {
    public final ImageView appBack;
    public final ConstraintLayout ctlVideo1;
    public final ConstraintLayout ctlVideo2;
    public final ConstraintLayout ctlVideo3;
    public final ConstraintLayout ctlVideo4;
    public final ImageView image1;
    public final ImageView image2;
    public final ImageView image3;
    public final ImageView image4;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TextView tv1;
    public final TextView tv2;
    public final TextView tv3;
    public final TextView tv4;
    public final TextView tvTitle;

    private ActivityVideoLengthBinding(ConstraintLayout rootView, ImageView appBack, ConstraintLayout ctlVideo1, ConstraintLayout ctlVideo2, ConstraintLayout ctlVideo3, ConstraintLayout ctlVideo4, ImageView image1, ImageView image2, ImageView image3, ImageView image4, ConstraintLayout main, TextView tv1, TextView tv2, TextView tv3, TextView tv4, TextView tvTitle) {
        this.rootView = rootView;
        this.appBack = appBack;
        this.ctlVideo1 = ctlVideo1;
        this.ctlVideo2 = ctlVideo2;
        this.ctlVideo3 = ctlVideo3;
        this.ctlVideo4 = ctlVideo4;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.main = main;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
        this.tv4 = tv4;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityVideoLengthBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityVideoLengthBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_video_length, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityVideoLengthBinding bind(View rootView) {
        int i = C0775R.id.app_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.ctl_video_1;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout != null) {
                i = C0775R.id.ctl_video_2;
                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                if (constraintLayout2 != null) {
                    i = C0775R.id.ctl_video_3;
                    ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                    if (constraintLayout3 != null) {
                        i = C0775R.id.ctl_video_4;
                        ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                        if (constraintLayout4 != null) {
                            i = C0775R.id.image_1;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                            if (imageView2 != null) {
                                i = C0775R.id.image_2;
                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                if (imageView3 != null) {
                                    i = C0775R.id.image_3;
                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                    if (imageView4 != null) {
                                        i = C0775R.id.image_4;
                                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                        if (imageView5 != null) {
                                            ConstraintLayout constraintLayout5 = (ConstraintLayout) rootView;
                                            i = C0775R.id.tv_1;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                                            if (textView != null) {
                                                i = C0775R.id.tv_2;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                if (textView2 != null) {
                                                    i = C0775R.id.tv_3;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                    if (textView3 != null) {
                                                        i = C0775R.id.tv_4;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                        if (textView4 != null) {
                                                            i = C0775R.id.tvTitle;
                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                            if (textView5 != null) {
                                                                return new ActivityVideoLengthBinding(constraintLayout5, imageView, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, imageView2, imageView3, imageView4, imageView5, constraintLayout5, textView, textView2, textView3, textView4, textView5);
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
