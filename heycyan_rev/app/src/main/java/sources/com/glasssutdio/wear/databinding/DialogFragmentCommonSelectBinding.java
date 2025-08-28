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
public final class DialogFragmentCommonSelectBinding implements ViewBinding {
    public final ConstraintLayout dialogView;
    public final ImageView ivClose;
    public final ImageView ivConfirm;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;
    public final TextView tvTitle;

    private DialogFragmentCommonSelectBinding(ConstraintLayout rootView, ConstraintLayout dialogView, ImageView ivClose, ImageView ivConfirm, RecyclerView recyclerView, TextView tvTitle) {
        this.rootView = rootView;
        this.dialogView = dialogView;
        this.ivClose = ivClose;
        this.ivConfirm = ivConfirm;
        this.recyclerView = recyclerView;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogFragmentCommonSelectBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogFragmentCommonSelectBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.dialog_fragment_common_select, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogFragmentCommonSelectBinding bind(View rootView) {
        int i = C0775R.id.dialog_view;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.iv_close;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView != null) {
                i = C0775R.id.iv_confirm;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                if (imageView2 != null) {
                    i = C0775R.id.recyclerView;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
                    if (recyclerView != null) {
                        i = C0775R.id.tv_title;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView != null) {
                            return new DialogFragmentCommonSelectBinding((ConstraintLayout) rootView, constraintLayout, imageView, imageView2, recyclerView, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
