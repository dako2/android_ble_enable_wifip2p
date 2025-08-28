package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityAiLanguageBinding implements ViewBinding {
    public final ConstraintLayout clsContent;
    public final ConstraintLayout clsSystem;
    public final EditText etSearch;
    public final ImageView ivChecked;
    public final ConstraintLayout main;
    public final RecyclerView rcyLanguage;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvOtherHint;
    public final TextView tvSystemHint;

    private ActivityAiLanguageBinding(ConstraintLayout rootView, ConstraintLayout clsContent, ConstraintLayout clsSystem, EditText etSearch, ImageView ivChecked, ConstraintLayout main, RecyclerView rcyLanguage, LayoutTitleBarBinding title, TextView tvOtherHint, TextView tvSystemHint) {
        this.rootView = rootView;
        this.clsContent = clsContent;
        this.clsSystem = clsSystem;
        this.etSearch = etSearch;
        this.ivChecked = ivChecked;
        this.main = main;
        this.rcyLanguage = rcyLanguage;
        this.title = title;
        this.tvOtherHint = tvOtherHint;
        this.tvSystemHint = tvSystemHint;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiLanguageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAiLanguageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_ai_language, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiLanguageBinding bind(View rootView) {
        View viewFindChildViewById;
        int i = C0775R.id.cls_content;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.cls_system;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                i = C0775R.id.et_search;
                EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i);
                if (editText != null) {
                    i = C0775R.id.iv_checked;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                    if (imageView != null) {
                        ConstraintLayout constraintLayout3 = (ConstraintLayout) rootView;
                        i = C0775R.id.rcy_language;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
                        if (recyclerView != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i = C0775R.id.title))) != null) {
                            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                            i = C0775R.id.tv_other_hint;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView != null) {
                                i = C0775R.id.tv_system_hint;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                if (textView2 != null) {
                                    return new ActivityAiLanguageBinding(constraintLayout3, constraintLayout, constraintLayout2, editText, imageView, constraintLayout3, recyclerView, layoutTitleBarBindingBind, textView, textView2);
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
