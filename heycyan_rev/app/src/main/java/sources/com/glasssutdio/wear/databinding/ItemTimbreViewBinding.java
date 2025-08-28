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
public final class ItemTimbreViewBinding implements ViewBinding {
    public final ConstraintLayout clsRoot;
    public final ImageView ivChecked;
    public final ImageView ivPeople;
    private final ConstraintLayout rootView;
    public final TextView tvTimbre;

    private ItemTimbreViewBinding(ConstraintLayout rootView, ConstraintLayout clsRoot, ImageView ivChecked, ImageView ivPeople, TextView tvTimbre) {
        this.rootView = rootView;
        this.clsRoot = clsRoot;
        this.ivChecked = ivChecked;
        this.ivPeople = ivPeople;
        this.tvTimbre = tvTimbre;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemTimbreViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemTimbreViewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.item_timbre_view, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemTimbreViewBinding bind(View rootView) {
        ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
        int i = C0775R.id.iv_checked;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.iv_people;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView2 != null) {
                i = C0775R.id.tv_timbre;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView != null) {
                    return new ItemTimbreViewBinding(constraintLayout, constraintLayout, imageView, imageView2, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
