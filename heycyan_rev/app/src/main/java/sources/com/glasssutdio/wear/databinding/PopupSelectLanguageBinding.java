package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class PopupSelectLanguageBinding implements ViewBinding {
    public final ConstraintLayout clsContent;
    public final EditText etSearch;
    public final RecyclerView rcyLanguage;
    private final ConstraintLayout rootView;

    private PopupSelectLanguageBinding(ConstraintLayout rootView, ConstraintLayout clsContent, EditText etSearch, RecyclerView rcyLanguage) {
        this.rootView = rootView;
        this.clsContent = clsContent;
        this.etSearch = etSearch;
        this.rcyLanguage = rcyLanguage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static PopupSelectLanguageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static PopupSelectLanguageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.popup_select_language, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PopupSelectLanguageBinding bind(View rootView) {
        int i = C0775R.id.cls_content;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.et_search;
            EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i);
            if (editText != null) {
                i = C0775R.id.rcy_language;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
                if (recyclerView != null) {
                    return new PopupSelectLanguageBinding((ConstraintLayout) rootView, constraintLayout, editText, recyclerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
