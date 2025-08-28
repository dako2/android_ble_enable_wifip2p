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
public final class SetCodeViewBinding implements ViewBinding {
    public final VerificationCodeEditText etCode;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TextView tvCode;
    public final TextView tvPhone;
    public final TextView tvSendHint;

    private SetCodeViewBinding(ConstraintLayout rootView, VerificationCodeEditText etCode, ConstraintLayout main, TextView tvCode, TextView tvPhone, TextView tvSendHint) {
        this.rootView = rootView;
        this.etCode = etCode;
        this.main = main;
        this.tvCode = tvCode;
        this.tvPhone = tvPhone;
        this.tvSendHint = tvSendHint;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static SetCodeViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SetCodeViewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.set_code_view, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SetCodeViewBinding bind(View rootView) {
        int i = C0775R.id.et_code;
        VerificationCodeEditText verificationCodeEditText = (VerificationCodeEditText) ViewBindings.findChildViewById(rootView, i);
        if (verificationCodeEditText != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
            i = C0775R.id.tv_code;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
            if (textView != null) {
                i = C0775R.id.tv_phone;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView2 != null) {
                    i = C0775R.id.tv_send_hint;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView3 != null) {
                        return new SetCodeViewBinding(constraintLayout, verificationCodeEditText, constraintLayout, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
