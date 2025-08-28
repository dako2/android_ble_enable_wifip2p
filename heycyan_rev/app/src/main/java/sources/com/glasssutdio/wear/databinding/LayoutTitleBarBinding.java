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
public final class LayoutTitleBarBinding implements ViewBinding {
    public final ImageView appBack;
    public final ImageView ivMenu;
    public final ImageView ivMenu2;
    private final ConstraintLayout rootView;
    public final ConstraintLayout titleBar;
    public final TextView tvTitle;

    private LayoutTitleBarBinding(ConstraintLayout rootView, ImageView appBack, ImageView ivMenu, ImageView ivMenu2, ConstraintLayout titleBar, TextView tvTitle) {
        this.rootView = rootView;
        this.appBack = appBack;
        this.ivMenu = ivMenu;
        this.ivMenu2 = ivMenu2;
        this.titleBar = titleBar;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutTitleBarBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutTitleBarBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.layout_title_bar, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutTitleBarBinding bind(View rootView) {
        int i = C0775R.id.app_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.iv_menu;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView2 != null) {
                i = C0775R.id.iv_menu2;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                if (imageView3 != null) {
                    ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
                    i = C0775R.id.tvTitle;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView != null) {
                        return new LayoutTitleBarBinding(constraintLayout, imageView, imageView2, imageView3, constraintLayout, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
