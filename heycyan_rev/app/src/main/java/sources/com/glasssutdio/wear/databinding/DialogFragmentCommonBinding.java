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
public final class DialogFragmentCommonBinding implements ViewBinding {
    public final ImageView ivClose;
    private final ConstraintLayout rootView;
    public final TextView tvCancel;
    public final TextView tvConfirm;
    public final TextView tvContent;
    public final TextView tvTitle;

    private DialogFragmentCommonBinding(ConstraintLayout rootView, ImageView ivClose, TextView tvCancel, TextView tvConfirm, TextView tvContent, TextView tvTitle) {
        this.rootView = rootView;
        this.ivClose = ivClose;
        this.tvCancel = tvCancel;
        this.tvConfirm = tvConfirm;
        this.tvContent = tvContent;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogFragmentCommonBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogFragmentCommonBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.dialog_fragment_common, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogFragmentCommonBinding bind(View rootView) {
        int i = C0775R.id.iv_close;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.tv_cancel;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
            if (textView != null) {
                i = C0775R.id.tv_confirm;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView2 != null) {
                    i = C0775R.id.tv_content;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView3 != null) {
                        i = C0775R.id.tv_title;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView4 != null) {
                            return new DialogFragmentCommonBinding((ConstraintLayout) rootView, imageView, textView, textView2, textView3, textView4);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
