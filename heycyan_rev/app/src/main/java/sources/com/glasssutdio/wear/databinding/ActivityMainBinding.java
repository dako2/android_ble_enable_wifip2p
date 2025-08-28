package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityMainBinding implements ViewBinding {
    public final ConstraintLayout clsControl;
    public final FrameLayout homeContainer;
    public final ImageView imageTab0;
    public final ImageView imageTab1;
    public final ImageView imageTab2;
    public final ImageView imageTab3;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final ConstraintLayout tabView;
    public final TextView tvCheckAll;
    public final TextView tvDelete;
    public final TextView tvTab0;
    public final TextView tvTab1;
    public final TextView tvTab2;
    public final TextView tvTab3;

    private ActivityMainBinding(ConstraintLayout rootView, ConstraintLayout clsControl, FrameLayout homeContainer, ImageView imageTab0, ImageView imageTab1, ImageView imageTab2, ImageView imageTab3, ConstraintLayout main, ConstraintLayout tabView, TextView tvCheckAll, TextView tvDelete, TextView tvTab0, TextView tvTab1, TextView tvTab2, TextView tvTab3) {
        this.rootView = rootView;
        this.clsControl = clsControl;
        this.homeContainer = homeContainer;
        this.imageTab0 = imageTab0;
        this.imageTab1 = imageTab1;
        this.imageTab2 = imageTab2;
        this.imageTab3 = imageTab3;
        this.main = main;
        this.tabView = tabView;
        this.tvCheckAll = tvCheckAll;
        this.tvDelete = tvDelete;
        this.tvTab0 = tvTab0;
        this.tvTab1 = tvTab1;
        this.tvTab2 = tvTab2;
        this.tvTab3 = tvTab3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_main, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMainBinding bind(View rootView) {
        int i = C0775R.id.cls_control;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.homeContainer;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, i);
            if (frameLayout != null) {
                i = C0775R.id.image_tab_0;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                if (imageView != null) {
                    i = C0775R.id.image_tab_1;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                    if (imageView2 != null) {
                        i = C0775R.id.image_tab_2;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                        if (imageView3 != null) {
                            i = C0775R.id.image_tab_3;
                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                            if (imageView4 != null) {
                                ConstraintLayout constraintLayout2 = (ConstraintLayout) rootView;
                                i = C0775R.id.tab_view;
                                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                if (constraintLayout3 != null) {
                                    i = C0775R.id.tv_check_all;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                                    if (textView != null) {
                                        i = C0775R.id.tv_delete;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                        if (textView2 != null) {
                                            i = C0775R.id.tv_tab_0;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                            if (textView3 != null) {
                                                i = C0775R.id.tv_tab_1;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                if (textView4 != null) {
                                                    i = C0775R.id.tv_tab_2;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                    if (textView5 != null) {
                                                        i = C0775R.id.tv_tab_3;
                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                        if (textView6 != null) {
                                                            return new ActivityMainBinding(constraintLayout2, constraintLayout, frameLayout, imageView, imageView2, imageView3, imageView4, constraintLayout2, constraintLayout3, textView, textView2, textView3, textView4, textView5, textView6);
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
