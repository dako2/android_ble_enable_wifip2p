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
public final class ItemCommonSelectViewBinding implements ViewBinding {
    public final ConstraintLayout clsRoot;
    public final ImageView ivChecked;
    private final ConstraintLayout rootView;
    public final TextView tvDesc;
    public final TextView tvName;
    public final View viewLine;

    private ItemCommonSelectViewBinding(ConstraintLayout rootView, ConstraintLayout clsRoot, ImageView ivChecked, TextView tvDesc, TextView tvName, View viewLine) {
        this.rootView = rootView;
        this.clsRoot = clsRoot;
        this.ivChecked = ivChecked;
        this.tvDesc = tvDesc;
        this.tvName = tvName;
        this.viewLine = viewLine;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemCommonSelectViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemCommonSelectViewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.item_common_select_view, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemCommonSelectViewBinding bind(View rootView) {
        View viewFindChildViewById;
        ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
        int i = C0775R.id.iv_checked;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.tv_desc;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
            if (textView != null) {
                i = C0775R.id.tv_name;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_line))) != null) {
                    return new ItemCommonSelectViewBinding(constraintLayout, constraintLayout, imageView, textView, textView2, viewFindChildViewById);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
