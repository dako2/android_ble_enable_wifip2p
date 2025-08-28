package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.view.VerificationCodeEditText;

/* loaded from: classes.dex */
public final class ActivityLogoffByCodeBinding implements ViewBinding {
    public final VerificationCodeEditText etCode;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvCode;
    public final TextView tvConfirm;
    public final TextView tvPhone;
    public final TextView tvSendHint;

    private ActivityLogoffByCodeBinding(ConstraintLayout rootView, VerificationCodeEditText etCode, ConstraintLayout main, LayoutTitleBarBinding title, TextView tvCode, TextView tvConfirm, TextView tvPhone, TextView tvSendHint) {
        this.rootView = rootView;
        this.etCode = etCode;
        this.main = main;
        this.title = title;
        this.tvCode = tvCode;
        this.tvConfirm = tvConfirm;
        this.tvPhone = tvPhone;
        this.tvSendHint = tvSendHint;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityLogoffByCodeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityLogoffByCodeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_logoff_by_code, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityLogoffByCodeBinding bind(View rootView) {
        int i = C0775R.id.et_code;
        VerificationCodeEditText verificationCodeEditText = (VerificationCodeEditText) ViewBindings.findChildViewById(rootView, i);
        if (verificationCodeEditText != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
            i = C0775R.id.title;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
            if (viewFindChildViewById != null) {
                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                i = C0775R.id.tv_code;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView != null) {
                    i = C0775R.id.tv_confirm;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView2 != null) {
                        i = C0775R.id.tv_phone;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView3 != null) {
                            i = C0775R.id.tv_send_hint;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView4 != null) {
                                return new ActivityLogoffByCodeBinding(constraintLayout, verificationCodeEditText, constraintLayout, layoutTitleBarBindingBind, textView, textView2, textView3, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
