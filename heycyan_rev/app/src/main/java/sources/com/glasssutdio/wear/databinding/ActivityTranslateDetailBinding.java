package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityTranslateDetailBinding implements ViewBinding {
    public final ConstraintLayout clsBottom;
    public final ConstraintLayout clsTop;
    public final EditText etBottom;
    public final EditText etTop;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvDstHint;
    public final TextView tvSrcHint;
    public final View viewBg1;
    public final View viewBg2;
    public final View viewBottom;
    public final View viewBottomBg1;
    public final View viewBottomBg2;

    private ActivityTranslateDetailBinding(ConstraintLayout rootView, ConstraintLayout clsBottom, ConstraintLayout clsTop, EditText etBottom, EditText etTop, ConstraintLayout main, LayoutTitleBarBinding title, TextView tvDstHint, TextView tvSrcHint, View viewBg1, View viewBg2, View viewBottom, View viewBottomBg1, View viewBottomBg2) {
        this.rootView = rootView;
        this.clsBottom = clsBottom;
        this.clsTop = clsTop;
        this.etBottom = etBottom;
        this.etTop = etTop;
        this.main = main;
        this.title = title;
        this.tvDstHint = tvDstHint;
        this.tvSrcHint = tvSrcHint;
        this.viewBg1 = viewBg1;
        this.viewBg2 = viewBg2;
        this.viewBottom = viewBottom;
        this.viewBottomBg1 = viewBottomBg1;
        this.viewBottomBg2 = viewBottomBg2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityTranslateDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityTranslateDetailBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_translate_detail, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityTranslateDetailBinding bind(View rootView) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        View viewFindChildViewById3;
        View viewFindChildViewById4;
        View viewFindChildViewById5;
        int i = C0775R.id.cls_bottom;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.cls_top;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                i = C0775R.id.et_bottom;
                EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i);
                if (editText != null) {
                    i = C0775R.id.et_top;
                    EditText editText2 = (EditText) ViewBindings.findChildViewById(rootView, i);
                    if (editText2 != null) {
                        ConstraintLayout constraintLayout3 = (ConstraintLayout) rootView;
                        i = C0775R.id.title;
                        View viewFindChildViewById6 = ViewBindings.findChildViewById(rootView, i);
                        if (viewFindChildViewById6 != null) {
                            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById6);
                            i = C0775R.id.tv_dst_hint;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView != null) {
                                i = C0775R.id.tv_src_hint;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                if (textView2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_bg_1))) != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_bg_2))) != null && (viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_bottom))) != null && (viewFindChildViewById4 = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_bottom_bg_1))) != null && (viewFindChildViewById5 = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_bottom_bg_2))) != null) {
                                    return new ActivityTranslateDetailBinding(constraintLayout3, constraintLayout, constraintLayout2, editText, editText2, constraintLayout3, layoutTitleBarBindingBind, textView, textView2, viewFindChildViewById, viewFindChildViewById2, viewFindChildViewById3, viewFindChildViewById4, viewFindChildViewById5);
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
