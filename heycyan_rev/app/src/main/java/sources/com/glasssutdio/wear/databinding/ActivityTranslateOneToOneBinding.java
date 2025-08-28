package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.view.rippleview.RippleView;

/* loaded from: classes.dex */
public final class ActivityTranslateOneToOneBinding implements ViewBinding {
    public final ConstraintLayout clsBottom;
    public final ConstraintLayout clsMenuBottom;
    public final ConstraintLayout clsMenuTop;
    public final ConstraintLayout clsTop;
    public final TextView etBottom;
    public final TextView etTop;
    public final ImageView ivMicBottom;
    public final ImageView ivMicTop;
    public final ImageView ivMicTop2;
    public final LinearLayout main;
    public final RecyclerView rcyBottom;
    public final RecyclerView rcyTop;
    public final RippleView rippleBottom;
    public final RippleView rippleTop;
    public final RippleView rippleTop2;
    private final LinearLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvLanguageBottom;
    public final TextView tvLanguageTop;
    public final View viewBg1;
    public final View viewBg2;
    public final View viewBottomBg1;
    public final View viewBottomBg2;

    private ActivityTranslateOneToOneBinding(LinearLayout rootView, ConstraintLayout clsBottom, ConstraintLayout clsMenuBottom, ConstraintLayout clsMenuTop, ConstraintLayout clsTop, TextView etBottom, TextView etTop, ImageView ivMicBottom, ImageView ivMicTop, ImageView ivMicTop2, LinearLayout main, RecyclerView rcyBottom, RecyclerView rcyTop, RippleView rippleBottom, RippleView rippleTop, RippleView rippleTop2, LayoutTitleBarBinding title, TextView tvLanguageBottom, TextView tvLanguageTop, View viewBg1, View viewBg2, View viewBottomBg1, View viewBottomBg2) {
        this.rootView = rootView;
        this.clsBottom = clsBottom;
        this.clsMenuBottom = clsMenuBottom;
        this.clsMenuTop = clsMenuTop;
        this.clsTop = clsTop;
        this.etBottom = etBottom;
        this.etTop = etTop;
        this.ivMicBottom = ivMicBottom;
        this.ivMicTop = ivMicTop;
        this.ivMicTop2 = ivMicTop2;
        this.main = main;
        this.rcyBottom = rcyBottom;
        this.rcyTop = rcyTop;
        this.rippleBottom = rippleBottom;
        this.rippleTop = rippleTop;
        this.rippleTop2 = rippleTop2;
        this.title = title;
        this.tvLanguageBottom = tvLanguageBottom;
        this.tvLanguageTop = tvLanguageTop;
        this.viewBg1 = viewBg1;
        this.viewBg2 = viewBg2;
        this.viewBottomBg1 = viewBottomBg1;
        this.viewBottomBg2 = viewBottomBg2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityTranslateOneToOneBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityTranslateOneToOneBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_translate_one_to_one, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityTranslateOneToOneBinding bind(View rootView) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        View viewFindChildViewById3;
        View viewFindChildViewById4;
        View viewFindChildViewById5;
        int i = C0775R.id.cls_bottom;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.cls_menu_bottom;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                i = C0775R.id.cls_menu_top;
                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                if (constraintLayout3 != null) {
                    i = C0775R.id.cls_top;
                    ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                    if (constraintLayout4 != null) {
                        i = C0775R.id.et_bottom;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView != null) {
                            i = C0775R.id.et_top;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView2 != null) {
                                i = C0775R.id.iv_mic_bottom;
                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                if (imageView != null) {
                                    i = C0775R.id.iv_mic_top;
                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                    if (imageView2 != null) {
                                        i = C0775R.id.iv_mic_top_2;
                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                        if (imageView3 != null) {
                                            LinearLayout linearLayout = (LinearLayout) rootView;
                                            i = C0775R.id.rcy_bottom;
                                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
                                            if (recyclerView != null) {
                                                i = C0775R.id.rcy_top;
                                                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
                                                if (recyclerView2 != null) {
                                                    i = C0775R.id.ripple_bottom;
                                                    RippleView rippleView = (RippleView) ViewBindings.findChildViewById(rootView, i);
                                                    if (rippleView != null) {
                                                        i = C0775R.id.ripple_top;
                                                        RippleView rippleView2 = (RippleView) ViewBindings.findChildViewById(rootView, i);
                                                        if (rippleView2 != null) {
                                                            i = C0775R.id.ripple_top2;
                                                            RippleView rippleView3 = (RippleView) ViewBindings.findChildViewById(rootView, i);
                                                            if (rippleView3 != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i = C0775R.id.title))) != null) {
                                                                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                                                i = C0775R.id.tv_language_bottom;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                if (textView3 != null) {
                                                                    i = C0775R.id.tv_language_top;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                    if (textView4 != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_bg_1))) != null && (viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_bg_2))) != null && (viewFindChildViewById4 = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_bottom_bg_1))) != null && (viewFindChildViewById5 = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_bottom_bg_2))) != null) {
                                                                        return new ActivityTranslateOneToOneBinding(linearLayout, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, textView, textView2, imageView, imageView2, imageView3, linearLayout, recyclerView, recyclerView2, rippleView, rippleView2, rippleView3, layoutTitleBarBindingBind, textView3, textView4, viewFindChildViewById2, viewFindChildViewById3, viewFindChildViewById4, viewFindChildViewById5);
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
