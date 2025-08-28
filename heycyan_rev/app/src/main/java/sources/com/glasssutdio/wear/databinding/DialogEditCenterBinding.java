package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class DialogEditCenterBinding implements ViewBinding {
    public final EditText etContent;
    public final ImageFilterView ivBg;
    public final ImageView ivClose;
    public final ImageView ivConfirm;
    private final ConstraintLayout rootView;
    public final TextView tvTitle;
    public final View viewLine;

    private DialogEditCenterBinding(ConstraintLayout rootView, EditText etContent, ImageFilterView ivBg, ImageView ivClose, ImageView ivConfirm, TextView tvTitle, View viewLine) {
        this.rootView = rootView;
        this.etContent = etContent;
        this.ivBg = ivBg;
        this.ivClose = ivClose;
        this.ivConfirm = ivConfirm;
        this.tvTitle = tvTitle;
        this.viewLine = viewLine;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogEditCenterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogEditCenterBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.dialog_edit_center, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogEditCenterBinding bind(View rootView) {
        View viewFindChildViewById;
        int i = C0775R.id.et_content;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i);
        if (editText != null) {
            i = C0775R.id.iv_bg;
            ImageFilterView imageFilterView = (ImageFilterView) ViewBindings.findChildViewById(rootView, i);
            if (imageFilterView != null) {
                i = C0775R.id.iv_close;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                if (imageView != null) {
                    i = C0775R.id.iv_confirm;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                    if (imageView2 != null) {
                        i = C0775R.id.tv_title;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_line))) != null) {
                            return new DialogEditCenterBinding((ConstraintLayout) rootView, editText, imageFilterView, imageView, imageView2, textView, viewFindChildViewById);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
