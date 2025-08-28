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
public final class ActivityTranslateListenerBinding implements ViewBinding {
    public final ConstraintLayout cclStart;
    public final ConstraintLayout clsBottom;
    public final ConstraintLayout clsTop;
    public final TextView etBottom;
    public final TextView etTop;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final ImageView tImage1;
    public final ImageView tImage2;
    public final LayoutTitleBarBinding title;
    public final TextView tvLanguageBottom;
    public final TextView tvLanguageTop;
    public final ImageView tvPlayTranslate;
    public final ConstraintLayout tvSave;
    public final TextView tvVoiceSave;
    public final TextView tvVoiceStart;
    public final View viewBg1;
    public final View viewBg2;
    public final View viewBottom;
    public final View viewBottomBg1;
    public final View viewBottomBg2;

    private ActivityTranslateListenerBinding(ConstraintLayout rootView, ConstraintLayout cclStart, ConstraintLayout clsBottom, ConstraintLayout clsTop, TextView etBottom, TextView etTop, ConstraintLayout main, ImageView tImage1, ImageView tImage2, LayoutTitleBarBinding title, TextView tvLanguageBottom, TextView tvLanguageTop, ImageView tvPlayTranslate, ConstraintLayout tvSave, TextView tvVoiceSave, TextView tvVoiceStart, View viewBg1, View viewBg2, View viewBottom, View viewBottomBg1, View viewBottomBg2) {
        this.rootView = rootView;
        this.cclStart = cclStart;
        this.clsBottom = clsBottom;
        this.clsTop = clsTop;
        this.etBottom = etBottom;
        this.etTop = etTop;
        this.main = main;
        this.tImage1 = tImage1;
        this.tImage2 = tImage2;
        this.title = title;
        this.tvLanguageBottom = tvLanguageBottom;
        this.tvLanguageTop = tvLanguageTop;
        this.tvPlayTranslate = tvPlayTranslate;
        this.tvSave = tvSave;
        this.tvVoiceSave = tvVoiceSave;
        this.tvVoiceStart = tvVoiceStart;
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

    public static ActivityTranslateListenerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityTranslateListenerBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_translate_listener, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityTranslateListenerBinding bind(View rootView) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        View viewFindChildViewById3;
        View viewFindChildViewById4;
        View viewFindChildViewById5;
        View viewFindChildViewById6;
        int i = C0775R.id.ccl_start;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.cls_bottom;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                i = C0775R.id.cls_top;
                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                if (constraintLayout3 != null) {
                    i = C0775R.id.et_bottom;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView != null) {
                        i = C0775R.id.et_top;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView2 != null) {
                            ConstraintLayout constraintLayout4 = (ConstraintLayout) rootView;
                            i = C0775R.id.t_image_1;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                            if (imageView != null) {
                                i = C0775R.id.t_image_2;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                if (imageView2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i = C0775R.id.title))) != null) {
                                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                    i = C0775R.id.tv_language_bottom;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                    if (textView3 != null) {
                                        i = C0775R.id.tv_language_top;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                        if (textView4 != null) {
                                            i = C0775R.id.tv_play_translate;
                                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                            if (imageView3 != null) {
                                                i = C0775R.id.tv_save;
                                                ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                                                if (constraintLayout5 != null) {
                                                    i = C0775R.id.tv_voice_save;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                    if (textView5 != null) {
                                                        i = C0775R.id.tv_voice_start;
                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                        if (textView6 != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_bg_1))) != null && (viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_bg_2))) != null && (viewFindChildViewById4 = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_bottom))) != null && (viewFindChildViewById5 = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_bottom_bg_1))) != null && (viewFindChildViewById6 = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_bottom_bg_2))) != null) {
                                                            return new ActivityTranslateListenerBinding(constraintLayout4, constraintLayout, constraintLayout2, constraintLayout3, textView, textView2, constraintLayout4, imageView, imageView2, layoutTitleBarBindingBind, textView3, textView4, imageView3, constraintLayout5, textView5, textView6, viewFindChildViewById2, viewFindChildViewById3, viewFindChildViewById4, viewFindChildViewById5, viewFindChildViewById6);
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
