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
public final class RecycleviewDeviceImageItemBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final ImageView showImageSrc;
    public final TextView tvTitleText;

    private RecycleviewDeviceImageItemBinding(ConstraintLayout rootView, ImageView showImageSrc, TextView tvTitleText) {
        this.rootView = rootView;
        this.showImageSrc = showImageSrc;
        this.tvTitleText = tvTitleText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewDeviceImageItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewDeviceImageItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.recycleview_device_image_item, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewDeviceImageItemBinding bind(View rootView) {
        int i = C0775R.id.show_image_src;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.tv_title_text;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
            if (textView != null) {
                return new RecycleviewDeviceImageItemBinding((ConstraintLayout) rootView, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
