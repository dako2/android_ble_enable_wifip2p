package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ItemGridImageBinding implements ViewBinding {
    public final ImageFilterView image;
    public final ImageView ivDelete;
    private final ConstraintLayout rootView;

    private ItemGridImageBinding(ConstraintLayout rootView, ImageFilterView image, ImageView ivDelete) {
        this.rootView = rootView;
        this.image = image;
        this.ivDelete = ivDelete;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemGridImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemGridImageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.item_grid_image, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemGridImageBinding bind(View rootView) {
        int i = C0775R.id.image;
        ImageFilterView imageFilterView = (ImageFilterView) ViewBindings.findChildViewById(rootView, i);
        if (imageFilterView != null) {
            i = C0775R.id.iv_delete;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView != null) {
                return new ItemGridImageBinding((ConstraintLayout) rootView, imageFilterView, imageView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
