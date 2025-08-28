package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class DialogPhotoSelectTypeBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvAlbums;
    public final TextView tvCancel;
    public final TextView tvTake;

    private DialogPhotoSelectTypeBinding(ConstraintLayout rootView, TextView tvAlbums, TextView tvCancel, TextView tvTake) {
        this.rootView = rootView;
        this.tvAlbums = tvAlbums;
        this.tvCancel = tvCancel;
        this.tvTake = tvTake;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogPhotoSelectTypeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogPhotoSelectTypeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.dialog_photo_select_type, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogPhotoSelectTypeBinding bind(View rootView) {
        int i = C0775R.id.tv_albums;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
        if (textView != null) {
            i = C0775R.id.tv_cancel;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
            if (textView2 != null) {
                i = C0775R.id.tv_take;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView3 != null) {
                    return new DialogPhotoSelectTypeBinding((ConstraintLayout) rootView, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
