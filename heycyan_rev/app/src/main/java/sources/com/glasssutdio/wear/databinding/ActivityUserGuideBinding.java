package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityUserGuideBinding implements ViewBinding {
    public final FrameLayout flTitle;
    public final ImageView ivClose;
    public final ImageView ivLast;
    public final ImageView ivNext;
    public final TextView ivSkip;
    public final ConstraintLayout llBottom;
    public final LinearLayout llIndicator;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvIndex2;
    public final ViewPager2 viewPager2;

    private ActivityUserGuideBinding(ConstraintLayout rootView, FrameLayout flTitle, ImageView ivClose, ImageView ivLast, ImageView ivNext, TextView ivSkip, ConstraintLayout llBottom, LinearLayout llIndicator, ConstraintLayout main, LayoutTitleBarBinding title, TextView tvIndex2, ViewPager2 viewPager2) {
        this.rootView = rootView;
        this.flTitle = flTitle;
        this.ivClose = ivClose;
        this.ivLast = ivLast;
        this.ivNext = ivNext;
        this.ivSkip = ivSkip;
        this.llBottom = llBottom;
        this.llIndicator = llIndicator;
        this.main = main;
        this.title = title;
        this.tvIndex2 = tvIndex2;
        this.viewPager2 = viewPager2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityUserGuideBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityUserGuideBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_user_guide, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityUserGuideBinding bind(View rootView) {
        int i = C0775R.id.fl_title;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, i);
        if (frameLayout != null) {
            i = C0775R.id.iv_close;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView != null) {
                i = C0775R.id.iv_last;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                if (imageView2 != null) {
                    i = C0775R.id.iv_next;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                    if (imageView3 != null) {
                        i = C0775R.id.iv_skip;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView != null) {
                            i = C0775R.id.ll_bottom;
                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                            if (constraintLayout != null) {
                                i = C0775R.id.ll_indicator;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i);
                                if (linearLayout != null) {
                                    ConstraintLayout constraintLayout2 = (ConstraintLayout) rootView;
                                    i = C0775R.id.title;
                                    View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
                                    if (viewFindChildViewById != null) {
                                        LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                        i = C0775R.id.tv_index2;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                        if (textView2 != null) {
                                            i = C0775R.id.viewPager2;
                                            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(rootView, i);
                                            if (viewPager2 != null) {
                                                return new ActivityUserGuideBinding(constraintLayout2, frameLayout, imageView, imageView2, imageView3, textView, constraintLayout, linearLayout, constraintLayout2, layoutTitleBarBindingBind, textView2, viewPager2);
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
