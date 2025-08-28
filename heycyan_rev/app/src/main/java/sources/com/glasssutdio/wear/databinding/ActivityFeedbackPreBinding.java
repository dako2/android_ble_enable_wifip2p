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
public final class ActivityFeedbackPreBinding implements ViewBinding {
    public final ConstraintLayout clsDevice;
    public final ConstraintLayout clsFeedbackType;
    public final ImageView ivArrow2;
    public final ImageView ivArrow3;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvConfirm;
    public final TextView tvDeviceName;
    public final TextView tvTypeHint;
    public final TextView tvTypeName;

    private ActivityFeedbackPreBinding(ConstraintLayout rootView, ConstraintLayout clsDevice, ConstraintLayout clsFeedbackType, ImageView ivArrow2, ImageView ivArrow3, ConstraintLayout main, LayoutTitleBarBinding title, TextView tvConfirm, TextView tvDeviceName, TextView tvTypeHint, TextView tvTypeName) {
        this.rootView = rootView;
        this.clsDevice = clsDevice;
        this.clsFeedbackType = clsFeedbackType;
        this.ivArrow2 = ivArrow2;
        this.ivArrow3 = ivArrow3;
        this.main = main;
        this.title = title;
        this.tvConfirm = tvConfirm;
        this.tvDeviceName = tvDeviceName;
        this.tvTypeHint = tvTypeHint;
        this.tvTypeName = tvTypeName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityFeedbackPreBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityFeedbackPreBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_feedback_pre, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityFeedbackPreBinding bind(View rootView) {
        int i = C0775R.id.cls_device;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.cls_feedback_type;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                i = C0775R.id.iv_arrow2;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                if (imageView != null) {
                    i = C0775R.id.iv_arrow3;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                    if (imageView2 != null) {
                        ConstraintLayout constraintLayout3 = (ConstraintLayout) rootView;
                        i = C0775R.id.title;
                        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
                        if (viewFindChildViewById != null) {
                            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                            i = C0775R.id.tv_confirm;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView != null) {
                                i = C0775R.id.tv_device_name;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                if (textView2 != null) {
                                    i = C0775R.id.tv_type_hint;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                    if (textView3 != null) {
                                        i = C0775R.id.tv_type_name;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                        if (textView4 != null) {
                                            return new ActivityFeedbackPreBinding(constraintLayout3, constraintLayout, constraintLayout2, imageView, imageView2, constraintLayout3, layoutTitleBarBindingBind, textView, textView2, textView3, textView4);
                                        }
                                    }
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
