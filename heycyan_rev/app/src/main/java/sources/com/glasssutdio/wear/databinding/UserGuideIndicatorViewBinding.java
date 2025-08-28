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
public final class UserGuideIndicatorViewBinding implements ViewBinding {
    public final ImageView ivIndicator;
    private final ConstraintLayout rootView;

    private UserGuideIndicatorViewBinding(ConstraintLayout rootView, ImageView ivIndicator) {
        this.rootView = rootView;
        this.ivIndicator = ivIndicator;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static UserGuideIndicatorViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static UserGuideIndicatorViewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.user_guide_indicator_view, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static UserGuideIndicatorViewBinding bind(View rootView) {
        int i = C0775R.id.iv_indicator;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            return new UserGuideIndicatorViewBinding((ConstraintLayout) rootView, imageView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
