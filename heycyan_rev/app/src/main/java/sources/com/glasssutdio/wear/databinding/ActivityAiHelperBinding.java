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
import com.glasssutdio.wear.all.view.GlassSwitchCompat;

/* loaded from: classes.dex */
public final class ActivityAiHelperBinding implements ViewBinding {
    public final ConstraintLayout cslSwitch;
    public final ConstraintLayout cslTimbre;
    public final GlassSwitchCompat gsc1;
    public final ConstraintLayout main;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tv1;
    public final TextView tv2;
    public final TextView tv3;
    public final TextView tv4;
    public final TextView tv5;

    private ActivityAiHelperBinding(ConstraintLayout rootView, ConstraintLayout cslSwitch, ConstraintLayout cslTimbre, GlassSwitchCompat gsc1, ConstraintLayout main, RecyclerView recyclerView, LayoutTitleBarBinding title, TextView tv1, TextView tv2, TextView tv3, TextView tv4, TextView tv5) {
        this.rootView = rootView;
        this.cslSwitch = cslSwitch;
        this.cslTimbre = cslTimbre;
        this.gsc1 = gsc1;
        this.main = main;
        this.recyclerView = recyclerView;
        this.title = title;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
        this.tv4 = tv4;
        this.tv5 = tv5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiHelperBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAiHelperBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_ai_helper, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiHelperBinding bind(View rootView) {
        View viewFindChildViewById;
        int i = C0775R.id.csl_switch;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.csl_timbre;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                i = C0775R.id.gsc_1;
                GlassSwitchCompat glassSwitchCompat = (GlassSwitchCompat) ViewBindings.findChildViewById(rootView, i);
                if (glassSwitchCompat != null) {
                    ConstraintLayout constraintLayout3 = (ConstraintLayout) rootView;
                    i = C0775R.id.recyclerView;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
                    if (recyclerView != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i = C0775R.id.title))) != null) {
                        LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                        i = C0775R.id.tv_1;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView != null) {
                            i = C0775R.id.tv_2;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView2 != null) {
                                i = C0775R.id.tv_3;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                if (textView3 != null) {
                                    i = C0775R.id.tv_4;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                    if (textView4 != null) {
                                        i = C0775R.id.tv_5;
                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                        if (textView5 != null) {
                                            return new ActivityAiHelperBinding(constraintLayout3, constraintLayout, constraintLayout2, glassSwitchCompat, constraintLayout3, recyclerView, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5);
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
