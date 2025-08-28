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
public final class ActivityGuideSettingBinding implements ViewBinding {
    public final TextView btn2;
    public final ConstraintLayout ctlPhone;
    public final ConstraintLayout ctlWhatsapp;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tv1;
    public final TextView tv2;
    public final TextView tv3;
    public final TextView tvBg;
    public final TextView tvTest1;

    private ActivityGuideSettingBinding(ConstraintLayout rootView, TextView btn2, ConstraintLayout ctlPhone, ConstraintLayout ctlWhatsapp, ConstraintLayout main, LayoutTitleBarBinding title, TextView tv1, TextView tv2, TextView tv3, TextView tvBg, TextView tvTest1) {
        this.rootView = rootView;
        this.btn2 = btn2;
        this.ctlPhone = ctlPhone;
        this.ctlWhatsapp = ctlWhatsapp;
        this.main = main;
        this.title = title;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
        this.tvBg = tvBg;
        this.tvTest1 = tvTest1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityGuideSettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityGuideSettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_guide_setting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityGuideSettingBinding bind(View rootView) {
        int i = C0775R.id.btn_2;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
        if (textView != null) {
            i = C0775R.id.ctl_phone;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout != null) {
                i = C0775R.id.ctl_whatsapp;
                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                if (constraintLayout2 != null) {
                    ConstraintLayout constraintLayout3 = (ConstraintLayout) rootView;
                    i = C0775R.id.title;
                    View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
                    if (viewFindChildViewById != null) {
                        LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                        i = C0775R.id.tv_1;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView2 != null) {
                            i = C0775R.id.tv_2;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView3 != null) {
                                i = C0775R.id.tv_3;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                if (textView4 != null) {
                                    i = C0775R.id.tv_bg;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                    if (textView5 != null) {
                                        i = C0775R.id.tv_test_1;
                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                        if (textView6 != null) {
                                            return new ActivityGuideSettingBinding(constraintLayout3, textView, constraintLayout, constraintLayout2, constraintLayout3, layoutTitleBarBindingBind, textView2, textView3, textView4, textView5, textView6);
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
