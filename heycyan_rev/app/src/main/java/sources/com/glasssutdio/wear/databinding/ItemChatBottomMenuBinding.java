package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ItemChatBottomMenuBinding implements ViewBinding {
    public final ImageView aiIvSound;
    public final ImageView ivCopy;
    public final ImageView ivLike;
    public final ImageView ivUnlike;
    private final LinearLayout rootView;

    private ItemChatBottomMenuBinding(LinearLayout rootView, ImageView aiIvSound, ImageView ivCopy, ImageView ivLike, ImageView ivUnlike) {
        this.rootView = rootView;
        this.aiIvSound = aiIvSound;
        this.ivCopy = ivCopy;
        this.ivLike = ivLike;
        this.ivUnlike = ivUnlike;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemChatBottomMenuBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemChatBottomMenuBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.item_chat_bottom_menu, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemChatBottomMenuBinding bind(View rootView) {
        int i = C0775R.id.ai_iv_sound;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.iv_copy;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView2 != null) {
                i = C0775R.id.iv_like;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                if (imageView3 != null) {
                    i = C0775R.id.iv_unlike;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                    if (imageView4 != null) {
                        return new ItemChatBottomMenuBinding((LinearLayout) rootView, imageView, imageView2, imageView3, imageView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
