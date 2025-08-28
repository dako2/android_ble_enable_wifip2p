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
public final class ActivityAiWakeUpBinding implements ViewBinding {
    public final ConstraintLayout clsTouch;
    public final ConstraintLayout clsVoice;
    public final ImageView ivPlay;
    public final ImageView ivTouch;
    public final ImageView ivVoice;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tv4;
    public final TextView tv5;
    public final TextView tvDesc1;
    public final TextView tvDesc2;
    public final TextView tvTitle2;
    public final TextView tvTitle3;

    private ActivityAiWakeUpBinding(ConstraintLayout rootView, ConstraintLayout clsTouch, ConstraintLayout clsVoice, ImageView ivPlay, ImageView ivTouch, ImageView ivVoice, ConstraintLayout main, LayoutTitleBarBinding title, TextView tv4, TextView tv5, TextView tvDesc1, TextView tvDesc2, TextView tvTitle2, TextView tvTitle3) {
        this.rootView = rootView;
        this.clsTouch = clsTouch;
        this.clsVoice = clsVoice;
        this.ivPlay = ivPlay;
        this.ivTouch = ivTouch;
        this.ivVoice = ivVoice;
        this.main = main;
        this.title = title;
        this.tv4 = tv4;
        this.tv5 = tv5;
        this.tvDesc1 = tvDesc1;
        this.tvDesc2 = tvDesc2;
        this.tvTitle2 = tvTitle2;
        this.tvTitle3 = tvTitle3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiWakeUpBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAiWakeUpBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_ai_wake_up, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiWakeUpBinding bind(View rootView) {
        int i = C0775R.id.cls_touch;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.cls_voice;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                i = C0775R.id.iv_play;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                if (imageView != null) {
                    i = C0775R.id.iv_touch;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                    if (imageView2 != null) {
                        i = C0775R.id.iv_voice;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                        if (imageView3 != null) {
                            ConstraintLayout constraintLayout3 = (ConstraintLayout) rootView;
                            i = C0775R.id.title;
                            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
                            if (viewFindChildViewById != null) {
                                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                i = C0775R.id.tv_4;
                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                                if (textView != null) {
                                    i = C0775R.id.tv_5;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                    if (textView2 != null) {
                                        i = C0775R.id.tv_desc_1;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                        if (textView3 != null) {
                                            i = C0775R.id.tv_desc_2;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                            if (textView4 != null) {
                                                i = C0775R.id.tv_title_2;
                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                if (textView5 != null) {
                                                    i = C0775R.id.tv_title_3;
                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                    if (textView6 != null) {
                                                        return new ActivityAiWakeUpBinding(constraintLayout3, constraintLayout, constraintLayout2, imageView, imageView2, imageView3, constraintLayout3, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5, textView6);
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
