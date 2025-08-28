package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.view.photoview.PhotoView;

/* loaded from: classes.dex */
public final class RecycleViewImageShowDetailImageBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final PhotoView showImageSrc;

    private RecycleViewImageShowDetailImageBinding(ConstraintLayout rootView, PhotoView showImageSrc) {
        this.rootView = rootView;
        this.showImageSrc = showImageSrc;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleViewImageShowDetailImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleViewImageShowDetailImageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.recycle_view_image_show_detail_image, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleViewImageShowDetailImageBinding bind(View rootView) {
        int i = C0775R.id.show_image_src;
        PhotoView photoView = (PhotoView) ViewBindings.findChildViewById(rootView, i);
        if (photoView != null) {
            return new RecycleViewImageShowDetailImageBinding((ConstraintLayout) rootView, photoView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
