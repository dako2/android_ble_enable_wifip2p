package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ItemChatImageBinding implements ViewBinding {
    public final ConstraintLayout clsItem;
    public final ImageView imageContent;
    public final ImageView ivChecked;
    public final ImageView photo1;
    private final ConstraintLayout rootView;

    private ItemChatImageBinding(ConstraintLayout rootView, ConstraintLayout clsItem, ImageView imageContent, ImageView ivChecked, ImageView photo1) {
        this.rootView = rootView;
        this.clsItem = clsItem;
        this.imageContent = imageContent;
        this.ivChecked = ivChecked;
        this.photo1 = photo1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemChatImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemChatImageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.item_chat_image, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemChatImageBinding bind(View rootView) {
        ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
        int i = C0775R.id.image_content;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.iv_checked;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView2 != null) {
                i = C0775R.id.photo_1;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                if (imageView3 != null) {
                    return new ItemChatImageBinding(constraintLayout, constraintLayout, imageView, imageView2, imageView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
