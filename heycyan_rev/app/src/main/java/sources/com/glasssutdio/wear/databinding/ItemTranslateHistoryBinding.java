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
import com.glasssutdio.wear.all.view.swipemenu.EasySwipeMenuLayout;

/* loaded from: classes.dex */
public final class ItemTranslateHistoryBinding implements ViewBinding {
    public final ConstraintLayout clsEmpty;
    public final ConstraintLayout clsItem;
    public final ConstraintLayout clsRight;
    public final ImageView ivDelete;
    private final EasySwipeMenuLayout rootView;
    public final TextView tvContent;
    public final TextView tvTime;
    public final TextView tvTranslateTitle;

    private ItemTranslateHistoryBinding(EasySwipeMenuLayout rootView, ConstraintLayout clsEmpty, ConstraintLayout clsItem, ConstraintLayout clsRight, ImageView ivDelete, TextView tvContent, TextView tvTime, TextView tvTranslateTitle) {
        this.rootView = rootView;
        this.clsEmpty = clsEmpty;
        this.clsItem = clsItem;
        this.clsRight = clsRight;
        this.ivDelete = ivDelete;
        this.tvContent = tvContent;
        this.tvTime = tvTime;
        this.tvTranslateTitle = tvTranslateTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public EasySwipeMenuLayout getRoot() {
        return this.rootView;
    }

    public static ItemTranslateHistoryBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemTranslateHistoryBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.item_translate_history, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemTranslateHistoryBinding bind(View rootView) {
        int i = C0775R.id.cls_empty;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.cls_item;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                i = C0775R.id.cls_right;
                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                if (constraintLayout3 != null) {
                    i = C0775R.id.iv_delete;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                    if (imageView != null) {
                        i = C0775R.id.tv_content;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView != null) {
                            i = C0775R.id.tv_time;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView2 != null) {
                                i = C0775R.id.tv_translate_title;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                if (textView3 != null) {
                                    return new ItemTranslateHistoryBinding((EasySwipeMenuLayout) rootView, constraintLayout, constraintLayout2, constraintLayout3, imageView, textView, textView2, textView3);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
