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
import com.glasssutdio.wear.all.view.DateWheelView;

/* loaded from: classes.dex */
public final class DialogDateSelectBinding implements ViewBinding {
    public final DateWheelView dateWheel;
    public final ConstraintLayout dialogView;
    public final ImageView ivClose;
    public final ImageView ivConfirm;
    private final ConstraintLayout rootView;
    public final TextView tvTitle;

    private DialogDateSelectBinding(ConstraintLayout rootView, DateWheelView dateWheel, ConstraintLayout dialogView, ImageView ivClose, ImageView ivConfirm, TextView tvTitle) {
        this.rootView = rootView;
        this.dateWheel = dateWheel;
        this.dialogView = dialogView;
        this.ivClose = ivClose;
        this.ivConfirm = ivConfirm;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogDateSelectBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogDateSelectBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.dialog_date_select, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogDateSelectBinding bind(View rootView) {
        int i = C0775R.id.date_wheel;
        DateWheelView dateWheelView = (DateWheelView) ViewBindings.findChildViewById(rootView, i);
        if (dateWheelView != null) {
            i = C0775R.id.dialog_view;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout != null) {
                i = C0775R.id.iv_close;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                if (imageView != null) {
                    i = C0775R.id.iv_confirm;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                    if (imageView2 != null) {
                        i = C0775R.id.tv_title;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView != null) {
                            return new DialogDateSelectBinding((ConstraintLayout) rootView, dateWheelView, constraintLayout, imageView, imageView2, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
