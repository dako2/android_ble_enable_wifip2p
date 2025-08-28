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
public final class ActivityAiGuideNextBinding implements ViewBinding {
    public final ConstraintLayout clsTouch;
    public final ConstraintLayout clsVoice;
    public final ImageView ivClose;
    public final ImageView ivLogo;
    public final ImageView ivPlay;
    public final TextView ivSkip;
    public final ImageView ivTouch;
    public final ImageView ivVoice;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TextView tv4;
    public final TextView tv5;
    public final TextView tv6;
    public final TextView tvDesc1;
    public final TextView tvDesc2;
    public final TextView tvTitle;
    public final TextView tvTitle2;
    public final TextView tvTitle3;

    private ActivityAiGuideNextBinding(ConstraintLayout rootView, ConstraintLayout clsTouch, ConstraintLayout clsVoice, ImageView ivClose, ImageView ivLogo, ImageView ivPlay, TextView ivSkip, ImageView ivTouch, ImageView ivVoice, ConstraintLayout main, TextView tv4, TextView tv5, TextView tv6, TextView tvDesc1, TextView tvDesc2, TextView tvTitle, TextView tvTitle2, TextView tvTitle3) {
        this.rootView = rootView;
        this.clsTouch = clsTouch;
        this.clsVoice = clsVoice;
        this.ivClose = ivClose;
        this.ivLogo = ivLogo;
        this.ivPlay = ivPlay;
        this.ivSkip = ivSkip;
        this.ivTouch = ivTouch;
        this.ivVoice = ivVoice;
        this.main = main;
        this.tv4 = tv4;
        this.tv5 = tv5;
        this.tv6 = tv6;
        this.tvDesc1 = tvDesc1;
        this.tvDesc2 = tvDesc2;
        this.tvTitle = tvTitle;
        this.tvTitle2 = tvTitle2;
        this.tvTitle3 = tvTitle3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiGuideNextBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAiGuideNextBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_ai_guide_next, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiGuideNextBinding bind(View rootView) {
        int i = C0775R.id.cls_touch;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.cls_voice;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                i = C0775R.id.iv_close;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                if (imageView != null) {
                    i = C0775R.id.iv_logo;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                    if (imageView2 != null) {
                        i = C0775R.id.iv_play;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                        if (imageView3 != null) {
                            i = C0775R.id.iv_skip;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView != null) {
                                i = C0775R.id.iv_touch;
                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                if (imageView4 != null) {
                                    i = C0775R.id.iv_voice;
                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                    if (imageView5 != null) {
                                        ConstraintLayout constraintLayout3 = (ConstraintLayout) rootView;
                                        i = C0775R.id.tv_4;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                        if (textView2 != null) {
                                            i = C0775R.id.tv_5;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                            if (textView3 != null) {
                                                i = C0775R.id.tv_6;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                if (textView4 != null) {
                                                    i = C0775R.id.tv_desc_1;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                    if (textView5 != null) {
                                                        i = C0775R.id.tv_desc_2;
                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                        if (textView6 != null) {
                                                            i = C0775R.id.tv_title;
                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                            if (textView7 != null) {
                                                                i = C0775R.id.tv_title_2;
                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                if (textView8 != null) {
                                                                    i = C0775R.id.tv_title_3;
                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                    if (textView9 != null) {
                                                                        return new ActivityAiGuideNextBinding(constraintLayout3, constraintLayout, constraintLayout2, imageView, imageView2, imageView3, textView, imageView4, imageView5, constraintLayout3, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9);
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
