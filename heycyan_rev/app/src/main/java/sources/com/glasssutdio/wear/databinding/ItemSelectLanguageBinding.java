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
public final class ItemSelectLanguageBinding implements ViewBinding {
    public final ImageView ivChecked;
    private final ConstraintLayout rootView;
    public final TextView tvName;
    public final View viewLine;

    private ItemSelectLanguageBinding(ConstraintLayout rootView, ImageView ivChecked, TextView tvName, View viewLine) {
        this.rootView = rootView;
        this.ivChecked = ivChecked;
        this.tvName = tvName;
        this.viewLine = viewLine;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemSelectLanguageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemSelectLanguageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.item_select_language, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemSelectLanguageBinding bind(View rootView) {
        View viewFindChildViewById;
        int i = C0775R.id.iv_checked;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.tv_name;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
            if (textView != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_line))) != null) {
                return new ItemSelectLanguageBinding((ConstraintLayout) rootView, imageView, textView, viewFindChildViewById);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
