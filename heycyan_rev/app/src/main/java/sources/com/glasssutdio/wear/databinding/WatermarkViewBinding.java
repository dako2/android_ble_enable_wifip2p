package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class WatermarkViewBinding implements ViewBinding {
    public final LinearLayout llRoot;
    private final ConstraintLayout rootView;
    public final TextView tvName;
    public final TextView tvTime;
    public final View viewLine;

    private WatermarkViewBinding(ConstraintLayout rootView, LinearLayout llRoot, TextView tvName, TextView tvTime, View viewLine) {
        this.rootView = rootView;
        this.llRoot = llRoot;
        this.tvName = tvName;
        this.tvTime = tvTime;
        this.viewLine = viewLine;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static WatermarkViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static WatermarkViewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.watermark_view, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static WatermarkViewBinding bind(View rootView) {
        View viewFindChildViewById;
        int i = C0775R.id.ll_root;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i);
        if (linearLayout != null) {
            i = C0775R.id.tv_name;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
            if (textView != null) {
                i = C0775R.id.tv_time;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_line))) != null) {
                    return new WatermarkViewBinding((ConstraintLayout) rootView, linearLayout, textView, textView2, viewFindChildViewById);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
