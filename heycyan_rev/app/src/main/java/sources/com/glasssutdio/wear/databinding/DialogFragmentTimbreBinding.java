package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class DialogFragmentTimbreBinding implements ViewBinding {
    public final ImageView ivClose;
    public final RecyclerView rcyTimbre;
    private final ConstraintLayout rootView;

    private DialogFragmentTimbreBinding(ConstraintLayout rootView, ImageView ivClose, RecyclerView rcyTimbre) {
        this.rootView = rootView;
        this.ivClose = ivClose;
        this.rcyTimbre = rcyTimbre;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogFragmentTimbreBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogFragmentTimbreBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.dialog_fragment_timbre, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogFragmentTimbreBinding bind(View rootView) {
        int i = C0775R.id.iv_close;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.rcy_timbre;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
            if (recyclerView != null) {
                return new DialogFragmentTimbreBinding((ConstraintLayout) rootView, imageView, recyclerView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
