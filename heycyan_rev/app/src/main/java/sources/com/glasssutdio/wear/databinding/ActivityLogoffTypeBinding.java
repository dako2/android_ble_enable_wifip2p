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
public final class ActivityLogoffTypeBinding implements ViewBinding {
    public final ConstraintLayout clsPhone;
    public final ConstraintLayout clsPwd;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvHint;

    private ActivityLogoffTypeBinding(ConstraintLayout rootView, ConstraintLayout clsPhone, ConstraintLayout clsPwd, ConstraintLayout main, LayoutTitleBarBinding title, TextView tvHint) {
        this.rootView = rootView;
        this.clsPhone = clsPhone;
        this.clsPwd = clsPwd;
        this.main = main;
        this.title = title;
        this.tvHint = tvHint;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityLogoffTypeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityLogoffTypeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_logoff_type, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityLogoffTypeBinding bind(View rootView) {
        int i = C0775R.id.cls_phone;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.cls_pwd;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                ConstraintLayout constraintLayout3 = (ConstraintLayout) rootView;
                i = C0775R.id.title;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
                if (viewFindChildViewById != null) {
                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                    i = C0775R.id.tv_hint;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView != null) {
                        return new ActivityLogoffTypeBinding(constraintLayout3, constraintLayout, constraintLayout2, constraintLayout3, layoutTitleBarBindingBind, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
