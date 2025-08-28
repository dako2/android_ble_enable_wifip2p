package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityWelcomeBinding implements ViewBinding {
    public final Button btnWelcome;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;

    private ActivityWelcomeBinding(ConstraintLayout rootView, Button btnWelcome, ConstraintLayout main) {
        this.rootView = rootView;
        this.btnWelcome = btnWelcome;
        this.main = main;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityWelcomeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityWelcomeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_welcome, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityWelcomeBinding bind(View rootView) {
        int i = C0775R.id.btn_welcome;
        Button button = (Button) ViewBindings.findChildViewById(rootView, i);
        if (button != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
            return new ActivityWelcomeBinding(constraintLayout, button, constraintLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
