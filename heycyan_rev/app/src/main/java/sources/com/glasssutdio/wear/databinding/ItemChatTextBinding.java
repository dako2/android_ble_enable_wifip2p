package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ItemChatTextBinding implements ViewBinding {
    public final ConstraintLayout clsAnswer;
    public final ConstraintLayout clsItem;
    public final FrameLayout flMenu;
    public final ImageView ivChecked;
    public final ImageView photo1;
    private final ConstraintLayout rootView;
    public final TextView textMessage;
    public final TextView textQuestion;

    private ItemChatTextBinding(ConstraintLayout rootView, ConstraintLayout clsAnswer, ConstraintLayout clsItem, FrameLayout flMenu, ImageView ivChecked, ImageView photo1, TextView textMessage, TextView textQuestion) {
        this.rootView = rootView;
        this.clsAnswer = clsAnswer;
        this.clsItem = clsItem;
        this.flMenu = flMenu;
        this.ivChecked = ivChecked;
        this.photo1 = photo1;
        this.textMessage = textMessage;
        this.textQuestion = textQuestion;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemChatTextBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemChatTextBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.item_chat_text, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemChatTextBinding bind(View rootView) {
        int i = C0775R.id.cls_answer;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            ConstraintLayout constraintLayout2 = (ConstraintLayout) rootView;
            i = C0775R.id.fl_menu;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, i);
            if (frameLayout != null) {
                i = C0775R.id.iv_checked;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                if (imageView != null) {
                    i = C0775R.id.photo_1;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                    if (imageView2 != null) {
                        i = C0775R.id.textMessage;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView != null) {
                            i = C0775R.id.textQuestion;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView2 != null) {
                                return new ItemChatTextBinding(constraintLayout2, constraintLayout, constraintLayout2, frameLayout, imageView, imageView2, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
