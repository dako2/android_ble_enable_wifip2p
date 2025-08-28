package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityShowMediaDetailBinding implements ViewBinding {
    public final ImageView appBack;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TextView tvDelete;
    public final TextView tvEdit;
    public final TextView tvLike;
    public final TextView tvSave;
    public final TextView tvShare;
    public final TextView tvSubTitle;
    public final TextView tvTitle;
    public final ViewPager2 viewPager2;

    private ActivityShowMediaDetailBinding(ConstraintLayout rootView, ImageView appBack, ConstraintLayout main, TextView tvDelete, TextView tvEdit, TextView tvLike, TextView tvSave, TextView tvShare, TextView tvSubTitle, TextView tvTitle, ViewPager2 viewPager2) {
        this.rootView = rootView;
        this.appBack = appBack;
        this.main = main;
        this.tvDelete = tvDelete;
        this.tvEdit = tvEdit;
        this.tvLike = tvLike;
        this.tvSave = tvSave;
        this.tvShare = tvShare;
        this.tvSubTitle = tvSubTitle;
        this.tvTitle = tvTitle;
        this.viewPager2 = viewPager2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityShowMediaDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityShowMediaDetailBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_show_media_detail, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityShowMediaDetailBinding bind(View rootView) {
        int i = C0775R.id.app_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
            i = C0775R.id.tv_delete;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
            if (textView != null) {
                i = C0775R.id.tv_edit;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView2 != null) {
                    i = C0775R.id.tv_like;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView3 != null) {
                        i = C0775R.id.tv_save;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView4 != null) {
                            i = C0775R.id.tv_share;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView5 != null) {
                                i = C0775R.id.tv_sub_title;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                if (textView6 != null) {
                                    i = C0775R.id.tvTitle;
                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                    if (textView7 != null) {
                                        i = C0775R.id.viewPager2;
                                        ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(rootView, i);
                                        if (viewPager2 != null) {
                                            return new ActivityShowMediaDetailBinding(constraintLayout, imageView, constraintLayout, textView, textView2, textView3, textView4, textView5, textView6, textView7, viewPager2);
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
