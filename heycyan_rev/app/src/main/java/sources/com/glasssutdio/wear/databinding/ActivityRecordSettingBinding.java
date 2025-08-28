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
public final class ActivityRecordSettingBinding implements ViewBinding {
    public final ConstraintLayout cslDirection;
    public final ConstraintLayout cslDuration;
    public final ConstraintLayout cslDurationAudio;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tv3;
    public final TextView tv4;
    public final TextView tv5;
    public final TextView tv6;
    public final TextView tvAudioDuration;

    private ActivityRecordSettingBinding(ConstraintLayout rootView, ConstraintLayout cslDirection, ConstraintLayout cslDuration, ConstraintLayout cslDurationAudio, ConstraintLayout main, LayoutTitleBarBinding title, TextView tv3, TextView tv4, TextView tv5, TextView tv6, TextView tvAudioDuration) {
        this.rootView = rootView;
        this.cslDirection = cslDirection;
        this.cslDuration = cslDuration;
        this.cslDurationAudio = cslDurationAudio;
        this.main = main;
        this.title = title;
        this.tv3 = tv3;
        this.tv4 = tv4;
        this.tv5 = tv5;
        this.tv6 = tv6;
        this.tvAudioDuration = tvAudioDuration;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityRecordSettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityRecordSettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_record_setting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityRecordSettingBinding bind(View rootView) {
        int i = C0775R.id.csl_direction;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.csl_duration;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                i = C0775R.id.csl_duration_audio;
                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                if (constraintLayout3 != null) {
                    ConstraintLayout constraintLayout4 = (ConstraintLayout) rootView;
                    i = C0775R.id.title;
                    View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
                    if (viewFindChildViewById != null) {
                        LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                        i = C0775R.id.tv_3;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView != null) {
                            i = C0775R.id.tv_4;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView2 != null) {
                                i = C0775R.id.tv_5;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                if (textView3 != null) {
                                    i = C0775R.id.tv_6;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                    if (textView4 != null) {
                                        i = C0775R.id.tv_audio_duration;
                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                        if (textView5 != null) {
                                            return new ActivityRecordSettingBinding(constraintLayout4, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5);
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
