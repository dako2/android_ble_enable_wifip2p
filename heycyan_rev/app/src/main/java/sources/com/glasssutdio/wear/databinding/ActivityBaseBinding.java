package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityBaseBinding implements ViewBinding {
    public final LinearLayout baseSetting;
    public final FrameLayout frameLayoutContentPlace;
    private final LinearLayout rootView;

    private ActivityBaseBinding(LinearLayout rootView, LinearLayout baseSetting, FrameLayout frameLayoutContentPlace) {
        this.rootView = rootView;
        this.baseSetting = baseSetting;
        this.frameLayoutContentPlace = frameLayoutContentPlace;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityBaseBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityBaseBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_base, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityBaseBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = C0775R.id.frame_layout_content_place;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, i);
        if (frameLayout != null) {
            return new ActivityBaseBinding(linearLayout, linearLayout, frameLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
