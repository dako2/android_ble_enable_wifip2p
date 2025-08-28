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
public final class ActivityVoiceBinding implements ViewBinding {
    public final ImageView appBack;
    public final ConstraintLayout ctlVoice1;
    public final ConstraintLayout ctlVoice2;
    public final ConstraintLayout ctlVoice3;
    public final GlassSwitchCompat gsc1;
    public final GlassSwitchCompat gsc2;
    public final GlassSwitchCompat gsc3;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final TextView tv1;
    public final TextView tv1Tip;
    public final TextView tv2;
    public final TextView tv2Tip;
    public final TextView tv3;
    public final TextView tv3Tip;
    public final TextView tvTitle;

    private ActivityVoiceBinding(ConstraintLayout rootView, ImageView appBack, ConstraintLayout ctlVoice1, ConstraintLayout ctlVoice2, ConstraintLayout ctlVoice3, GlassSwitchCompat gsc1, GlassSwitchCompat gsc2, GlassSwitchCompat gsc3, ConstraintLayout main, TextView tv1, TextView tv1Tip, TextView tv2, TextView tv2Tip, TextView tv3, TextView tv3Tip, TextView tvTitle) {
        this.rootView = rootView;
        this.appBack = appBack;
        this.ctlVoice1 = ctlVoice1;
        this.ctlVoice2 = ctlVoice2;
        this.ctlVoice3 = ctlVoice3;
        this.gsc1 = gsc1;
        this.gsc2 = gsc2;
        this.gsc3 = gsc3;
        this.main = main;
        this.tv1 = tv1;
        this.tv1Tip = tv1Tip;
        this.tv2 = tv2;
        this.tv2Tip = tv2Tip;
        this.tv3 = tv3;
        this.tv3Tip = tv3Tip;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityVoiceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityVoiceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_voice, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityVoiceBinding bind(View rootView) {
        int i = C0775R.id.app_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.ctl_voice_1;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout != null) {
                i = C0775R.id.ctl_voice_2;
                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                if (constraintLayout2 != null) {
                    i = C0775R.id.ctl_voice_3;
                    ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                    if (constraintLayout3 != null) {
                        i = C0775R.id.gsc_1;
                        GlassSwitchCompat glassSwitchCompat = (GlassSwitchCompat) ViewBindings.findChildViewById(rootView, i);
                        if (glassSwitchCompat != null) {
                            i = C0775R.id.gsc_2;
                            GlassSwitchCompat glassSwitchCompat2 = (GlassSwitchCompat) ViewBindings.findChildViewById(rootView, i);
                            if (glassSwitchCompat2 != null) {
                                i = C0775R.id.gsc_3;
                                GlassSwitchCompat glassSwitchCompat3 = (GlassSwitchCompat) ViewBindings.findChildViewById(rootView, i);
                                if (glassSwitchCompat3 != null) {
                                    ConstraintLayout constraintLayout4 = (ConstraintLayout) rootView;
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
                                                    i = C0775R.id.tv_3;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                    if (textView5 != null) {
                                                        i = C0775R.id.tv_3_tip;
                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                        if (textView6 != null) {
                                                            i = C0775R.id.tvTitle;
                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                            if (textView7 != null) {
                                                                return new ActivityVoiceBinding(constraintLayout4, imageView, constraintLayout, constraintLayout2, constraintLayout3, glassSwitchCompat, glassSwitchCompat2, glassSwitchCompat3, constraintLayout4, textView, textView2, textView3, textView4, textView5, textView6, textView7);
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
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
