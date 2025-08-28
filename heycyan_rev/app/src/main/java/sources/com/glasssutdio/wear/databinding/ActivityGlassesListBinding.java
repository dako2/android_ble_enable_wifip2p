package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityGlassesListBinding implements ViewBinding {
    public final ImageView appBack;
    public final RecyclerView deviceListRcv;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TextView tvTitle;

    private ActivityGlassesListBinding(ConstraintLayout rootView, ImageView appBack, RecyclerView deviceListRcv, ConstraintLayout main, TextView tvTitle) {
        this.rootView = rootView;
        this.appBack = appBack;
        this.deviceListRcv = deviceListRcv;
        this.main = main;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityGlassesListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityGlassesListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_glasses_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityGlassesListBinding bind(View rootView) {
        int i = C0775R.id.app_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.device_list_rcv;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
            if (recyclerView != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
                i = C0775R.id.tvTitle;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView != null) {
                    return new ActivityGlassesListBinding(constraintLayout, imageView, recyclerView, constraintLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
