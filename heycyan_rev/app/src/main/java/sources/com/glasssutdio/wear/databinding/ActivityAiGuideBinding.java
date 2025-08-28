package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityAiGuideBinding implements ViewBinding {
    public final ConstraintLayout clsPhoto;
    public final ConstraintLayout clsVoice;
    public final ImageView ivClose;
    public final ImageView ivLogo;
    public final TextView ivSkip;
    public final ConstraintLayout main;
    public final RecyclerView rcyPhoto;
    public final RecyclerView rcyVoice;
    private final ConstraintLayout rootView;
    public final TextView tvContinue;
    public final TextView tvDesc1;
    public final TextView tvDesc2;
    public final TextView tvTitle;
    public final TextView tvTitle1;
    public final TextView tvTitle2;
    public final TextView tvTitle3;
    public final TextView tvTitle4;

    private ActivityAiGuideBinding(ConstraintLayout rootView, ConstraintLayout clsPhoto, ConstraintLayout clsVoice, ImageView ivClose, ImageView ivLogo, TextView ivSkip, ConstraintLayout main, RecyclerView rcyPhoto, RecyclerView rcyVoice, TextView tvContinue, TextView tvDesc1, TextView tvDesc2, TextView tvTitle, TextView tvTitle1, TextView tvTitle2, TextView tvTitle3, TextView tvTitle4) {
        this.rootView = rootView;
        this.clsPhoto = clsPhoto;
        this.clsVoice = clsVoice;
        this.ivClose = ivClose;
        this.ivLogo = ivLogo;
        this.ivSkip = ivSkip;
        this.main = main;
        this.rcyPhoto = rcyPhoto;
        this.rcyVoice = rcyVoice;
        this.tvContinue = tvContinue;
        this.tvDesc1 = tvDesc1;
        this.tvDesc2 = tvDesc2;
        this.tvTitle = tvTitle;
        this.tvTitle1 = tvTitle1;
        this.tvTitle2 = tvTitle2;
        this.tvTitle3 = tvTitle3;
        this.tvTitle4 = tvTitle4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiGuideBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAiGuideBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_ai_guide, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiGuideBinding bind(View rootView) {
        int i = C0775R.id.cls_photo;
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
                        i = C0775R.id.iv_skip;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView != null) {
                            ConstraintLayout constraintLayout3 = (ConstraintLayout) rootView;
                            i = C0775R.id.rcy_photo;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
                            if (recyclerView != null) {
                                i = C0775R.id.rcy_voice;
                                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
                                if (recyclerView2 != null) {
                                    i = C0775R.id.tv_continue;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                    if (textView2 != null) {
                                        i = C0775R.id.tv_desc_1;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                        if (textView3 != null) {
                                            i = C0775R.id.tv_desc_2;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                            if (textView4 != null) {
                                                i = C0775R.id.tv_title;
                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                if (textView5 != null) {
                                                    i = C0775R.id.tv_title_1;
                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                    if (textView6 != null) {
                                                        i = C0775R.id.tv_title_2;
                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                        if (textView7 != null) {
                                                            i = C0775R.id.tv_title_3;
                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                            if (textView8 != null) {
                                                                i = C0775R.id.tv_title_4;
                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                if (textView9 != null) {
                                                                    return new ActivityAiGuideBinding(constraintLayout3, constraintLayout, constraintLayout2, imageView, imageView2, textView, constraintLayout3, recyclerView, recyclerView2, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9);
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
