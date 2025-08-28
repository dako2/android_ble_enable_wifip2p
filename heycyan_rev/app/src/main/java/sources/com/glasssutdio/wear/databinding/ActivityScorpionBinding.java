package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.view.GlassSwitchCompat;

/* loaded from: classes.dex */
public final class ActivityScorpionBinding implements ViewBinding {
    public final ImageView appBack;
    public final ConstraintLayout ctlAutoPause;
    public final ConstraintLayout ctlAutoVoice;
    public final GlassSwitchCompat gsc1;
    public final GlassSwitchCompat gsc2;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TextView tv1;
    public final TextView tv1Tip;
    public final TextView tv2;
    public final TextView tv2Tip;
    public final TextView tvTitle;

    private ActivityScorpionBinding(ConstraintLayout rootView, ImageView appBack, ConstraintLayout ctlAutoPause, ConstraintLayout ctlAutoVoice, GlassSwitchCompat gsc1, GlassSwitchCompat gsc2, ConstraintLayout main, TextView tv1, TextView tv1Tip, TextView tv2, TextView tv2Tip, TextView tvTitle) {
        this.rootView = rootView;
        this.appBack = appBack;
        this.ctlAutoPause = ctlAutoPause;
        this.ctlAutoVoice = ctlAutoVoice;
        this.gsc1 = gsc1;
        this.gsc2 = gsc2;
        this.main = main;
        this.tv1 = tv1;
        this.tv1Tip = tv1Tip;
        this.tv2 = tv2;
        this.tv2Tip = tv2Tip;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityScorpionBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityScorpionBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_scorpion, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityScorpionBinding bind(View rootView) {
        int i = C0775R.id.app_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.ctl_auto_pause;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout != null) {
                i = C0775R.id.ctl_auto_voice;
                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                if (constraintLayout2 != null) {
                    i = C0775R.id.gsc_1;
                    GlassSwitchCompat glassSwitchCompat = (GlassSwitchCompat) ViewBindings.findChildViewById(rootView, i);
                    if (glassSwitchCompat != null) {
                        i = C0775R.id.gsc_2;
                        GlassSwitchCompat glassSwitchCompat2 = (GlassSwitchCompat) ViewBindings.findChildViewById(rootView, i);
                        if (glassSwitchCompat2 != null) {
                            ConstraintLayout constraintLayout3 = (ConstraintLayout) rootView;
                            i = C0775R.id.tv_1;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView != null) {
                                i = C0775R.id.tv_1_tip;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                if (textView2 != null) {
                                    i = C0775R.id.tv_2;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                    if (textView3 != null) {
                                        i = C0775R.id.tv_2_tip;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                        if (textView4 != null) {
                                            i = C0775R.id.tvTitle;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                            if (textView5 != null) {
                                                return new ActivityScorpionBinding(constraintLayout3, imageView, constraintLayout, constraintLayout2, glassSwitchCompat, glassSwitchCompat2, constraintLayout3, textView, textView2, textView3, textView4, textView5);
                                            }
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
