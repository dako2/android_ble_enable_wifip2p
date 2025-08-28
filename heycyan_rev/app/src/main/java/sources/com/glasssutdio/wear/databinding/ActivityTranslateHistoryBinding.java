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
public final class ActivityTranslateHistoryBinding implements ViewBinding {
    public final ConstraintLayout chatNoData;
    public final ImageView ivImage;
    public final ConstraintLayout main;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvDesc;

    private ActivityTranslateHistoryBinding(ConstraintLayout rootView, ConstraintLayout chatNoData, ImageView ivImage, ConstraintLayout main, RecyclerView recyclerView, LayoutTitleBarBinding title, TextView tvDesc) {
        this.rootView = rootView;
        this.chatNoData = chatNoData;
        this.ivImage = ivImage;
        this.main = main;
        this.recyclerView = recyclerView;
        this.title = title;
        this.tvDesc = tvDesc;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityTranslateHistoryBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityTranslateHistoryBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_translate_history, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityTranslateHistoryBinding bind(View rootView) {
        View viewFindChildViewById;
        int i = C0775R.id.chat_no_data;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.ivImage;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView != null) {
                ConstraintLayout constraintLayout2 = (ConstraintLayout) rootView;
                i = C0775R.id.recyclerView;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
                if (recyclerView != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i = C0775R.id.title))) != null) {
                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                    i = C0775R.id.tv_desc;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView != null) {
                        return new ActivityTranslateHistoryBinding(constraintLayout2, constraintLayout, imageView, constraintLayout2, recyclerView, layoutTitleBarBindingBind, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
