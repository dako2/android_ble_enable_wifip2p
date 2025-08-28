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
public final class ActivityAiTranslateBinding implements ViewBinding {
    public final ConstraintLayout clsOneToOne;
    public final ConstraintLayout clsSimultaneousInterpretation;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvTokenTitle;

    private ActivityAiTranslateBinding(ConstraintLayout rootView, ConstraintLayout clsOneToOne, ConstraintLayout clsSimultaneousInterpretation, ConstraintLayout main, LayoutTitleBarBinding title, TextView tvTokenTitle) {
        this.rootView = rootView;
        this.clsOneToOne = clsOneToOne;
        this.clsSimultaneousInterpretation = clsSimultaneousInterpretation;
        this.main = main;
        this.title = title;
        this.tvTokenTitle = tvTokenTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiTranslateBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAiTranslateBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_ai_translate, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiTranslateBinding bind(View rootView) {
        int i = C0775R.id.cls_one_to_one;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.cls_simultaneous_interpretation;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                ConstraintLayout constraintLayout3 = (ConstraintLayout) rootView;
                i = C0775R.id.title;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
                if (viewFindChildViewById != null) {
                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                    i = C0775R.id.tv_token_title;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView != null) {
                        return new ActivityAiTranslateBinding(constraintLayout3, constraintLayout, constraintLayout2, constraintLayout3, layoutTitleBarBindingBind, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
