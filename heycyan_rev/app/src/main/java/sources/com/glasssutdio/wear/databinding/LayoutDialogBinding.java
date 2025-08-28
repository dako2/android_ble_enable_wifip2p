package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.view.CircleProgressView;

/* loaded from: classes.dex */
public final class LayoutDialogBinding implements ViewBinding {
    public final CircleProgressView loadingProgress;
    private final FrameLayout rootView;

    private LayoutDialogBinding(FrameLayout rootView, CircleProgressView loadingProgress) {
        this.rootView = rootView;
        this.loadingProgress = loadingProgress;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDialogBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.layout_dialog, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDialogBinding bind(View rootView) {
        int i = C0775R.id.loading_progress;
        CircleProgressView circleProgressView = (CircleProgressView) ViewBindings.findChildViewById(rootView, i);
        if (circleProgressView != null) {
            return new LayoutDialogBinding((FrameLayout) rootView, circleProgressView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
