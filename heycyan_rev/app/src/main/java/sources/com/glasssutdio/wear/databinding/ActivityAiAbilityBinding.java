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
public final class ActivityAiAbilityBinding implements ViewBinding {
    public final ConstraintLayout clsMore;
    public final ConstraintLayout clsMusic;
    public final ConstraintLayout clsPhone;
    public final ConstraintLayout clsTake;
    public final ImageView ivMore;
    public final ImageView ivMusic;
    public final ImageView ivPhone;
    public final ImageView ivTake;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tv10;
    public final TextView tv11;
    public final TextView tv6;
    public final TextView tv7;
    public final TextView tv8;
    public final TextView tv9;
    public final TextView tvDesc1;
    public final TextView tvDesc2;
    public final TextView tvTitle2;
    public final TextView tvTitle3;

    private ActivityAiAbilityBinding(ConstraintLayout rootView, ConstraintLayout clsMore, ConstraintLayout clsMusic, ConstraintLayout clsPhone, ConstraintLayout clsTake, ImageView ivMore, ImageView ivMusic, ImageView ivPhone, ImageView ivTake, ConstraintLayout main, LayoutTitleBarBinding title, TextView tv10, TextView tv11, TextView tv6, TextView tv7, TextView tv8, TextView tv9, TextView tvDesc1, TextView tvDesc2, TextView tvTitle2, TextView tvTitle3) {
        this.rootView = rootView;
        this.clsMore = clsMore;
        this.clsMusic = clsMusic;
        this.clsPhone = clsPhone;
        this.clsTake = clsTake;
        this.ivMore = ivMore;
        this.ivMusic = ivMusic;
        this.ivPhone = ivPhone;
        this.ivTake = ivTake;
        this.main = main;
        this.title = title;
        this.tv10 = tv10;
        this.tv11 = tv11;
        this.tv6 = tv6;
        this.tv7 = tv7;
        this.tv8 = tv8;
        this.tv9 = tv9;
        this.tvDesc1 = tvDesc1;
        this.tvDesc2 = tvDesc2;
        this.tvTitle2 = tvTitle2;
        this.tvTitle3 = tvTitle3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiAbilityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAiAbilityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_ai_ability, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiAbilityBinding bind(View rootView) {
        int i = C0775R.id.cls_more;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.cls_music;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                i = C0775R.id.cls_phone;
                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                if (constraintLayout3 != null) {
                    i = C0775R.id.cls_take;
                    ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                    if (constraintLayout4 != null) {
                        i = C0775R.id.iv_more;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                        if (imageView != null) {
                            i = C0775R.id.iv_music;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                            if (imageView2 != null) {
                                i = C0775R.id.iv_phone;
                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                if (imageView3 != null) {
                                    i = C0775R.id.iv_take;
                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                    if (imageView4 != null) {
                                        ConstraintLayout constraintLayout5 = (ConstraintLayout) rootView;
                                        i = C0775R.id.title;
                                        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
                                        if (viewFindChildViewById != null) {
                                            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                            i = C0775R.id.tv_10;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                                            if (textView != null) {
                                                i = C0775R.id.tv_11;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                if (textView2 != null) {
                                                    i = C0775R.id.tv_6;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                    if (textView3 != null) {
                                                        i = C0775R.id.tv_7;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                        if (textView4 != null) {
                                                            i = C0775R.id.tv_8;
                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                            if (textView5 != null) {
                                                                i = C0775R.id.tv_9;
                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                if (textView6 != null) {
                                                                    i = C0775R.id.tv_desc_1;
                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                    if (textView7 != null) {
                                                                        i = C0775R.id.tv_desc_2;
                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                        if (textView8 != null) {
                                                                            i = C0775R.id.tv_title_2;
                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                            if (textView9 != null) {
                                                                                i = C0775R.id.tv_title_3;
                                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                                if (textView10 != null) {
                                                                                    return new ActivityAiAbilityBinding(constraintLayout5, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, imageView, imageView2, imageView3, imageView4, constraintLayout5, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10);
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
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
