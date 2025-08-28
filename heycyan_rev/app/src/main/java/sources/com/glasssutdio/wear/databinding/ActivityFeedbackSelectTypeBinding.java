package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityFeedbackSelectTypeBinding implements ViewBinding {
    public final ConstraintLayout main;
    public final RecyclerView rcyQuestion;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvDesc1;

    private ActivityFeedbackSelectTypeBinding(ConstraintLayout rootView, ConstraintLayout main, RecyclerView rcyQuestion, LayoutTitleBarBinding title, TextView tvDesc1) {
        this.rootView = rootView;
        this.main = main;
        this.rcyQuestion = rcyQuestion;
        this.title = title;
        this.tvDesc1 = tvDesc1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityFeedbackSelectTypeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityFeedbackSelectTypeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_feedback_select_type, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityFeedbackSelectTypeBinding bind(View rootView) {
        View viewFindChildViewById;
        ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
        int i = C0775R.id.rcy_question;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
        if (recyclerView != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i = C0775R.id.title))) != null) {
            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
            i = C0775R.id.tv_desc_1;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
            if (textView != null) {
                return new ActivityFeedbackSelectTypeBinding(constraintLayout, constraintLayout, recyclerView, layoutTitleBarBindingBind, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
