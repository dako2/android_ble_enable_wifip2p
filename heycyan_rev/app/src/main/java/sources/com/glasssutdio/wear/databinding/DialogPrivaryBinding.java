package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class DialogPrivaryBinding implements ViewBinding {
    public final Button btnEnter;
    public final Button btnExit;
    public final LinearLayout layoutToDo;
    public final View line;
    private final ConstraintLayout rootView;
    public final TextView tvNote;
    public final TextView tvPrivacyTips;

    private DialogPrivaryBinding(ConstraintLayout rootView, Button btnEnter, Button btnExit, LinearLayout layoutToDo, View line, TextView tvNote, TextView tvPrivacyTips) {
        this.rootView = rootView;
        this.btnEnter = btnEnter;
        this.btnExit = btnExit;
        this.layoutToDo = layoutToDo;
        this.line = line;
        this.tvNote = tvNote;
        this.tvPrivacyTips = tvPrivacyTips;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogPrivaryBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogPrivaryBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.dialog_privary, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogPrivaryBinding bind(View rootView) {
        View viewFindChildViewById;
        int i = C0775R.id.btn_enter;
        Button button = (Button) ViewBindings.findChildViewById(rootView, i);
        if (button != null) {
            i = C0775R.id.btn_exit;
            Button button2 = (Button) ViewBindings.findChildViewById(rootView, i);
            if (button2 != null) {
                i = C0775R.id.layout_to_do;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i);
                if (linearLayout != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i = C0775R.id.line))) != null) {
                    i = C0775R.id.tv_note;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView != null) {
                        i = C0775R.id.tv_privacy_tips;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView2 != null) {
                            return new DialogPrivaryBinding((ConstraintLayout) rootView, button, button2, linearLayout, viewFindChildViewById, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
