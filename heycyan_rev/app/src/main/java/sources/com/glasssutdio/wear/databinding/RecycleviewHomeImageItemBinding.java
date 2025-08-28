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
public final class RecycleviewHomeImageItemBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final ImageView showImageSrc;
    public final TextView tvTitleSubText;
    public final TextView tvTitleText;

    private RecycleviewHomeImageItemBinding(ConstraintLayout rootView, ImageView showImageSrc, TextView tvTitleSubText, TextView tvTitleText) {
        this.rootView = rootView;
        this.showImageSrc = showImageSrc;
        this.tvTitleSubText = tvTitleSubText;
        this.tvTitleText = tvTitleText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewHomeImageItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewHomeImageItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.recycleview_home_image_item, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewHomeImageItemBinding bind(View rootView) {
        int i = C0775R.id.show_image_src;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.tv_title_sub_text;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
            if (textView != null) {
                i = C0775R.id.tv_title_text;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView2 != null) {
                    return new RecycleviewHomeImageItemBinding((ConstraintLayout) rootView, imageView, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
