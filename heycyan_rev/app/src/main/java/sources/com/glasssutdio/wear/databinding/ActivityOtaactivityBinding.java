package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityOtaactivityBinding implements ViewBinding {
    public final ImageView appBack;
    public final ConstraintLayout cslOtaWifi;
    public final Group groupOta;
    public final ConstraintLayout main;
    public final ImageView otaImage1;
    public final ImageView otaImage2;
    public final ProgressBar progressValue;
    private final ConstraintLayout rootView;
    public final TextView tvOta2;
    public final TextView tvOtaStart;
    public final TextView tvTextWifi;
    public final TextView tvTitle;
    public final TextView tvWarmingInfo;

    private ActivityOtaactivityBinding(ConstraintLayout rootView, ImageView appBack, ConstraintLayout cslOtaWifi, Group groupOta, ConstraintLayout main, ImageView otaImage1, ImageView otaImage2, ProgressBar progressValue, TextView tvOta2, TextView tvOtaStart, TextView tvTextWifi, TextView tvTitle, TextView tvWarmingInfo) {
        this.rootView = rootView;
        this.appBack = appBack;
        this.cslOtaWifi = cslOtaWifi;
        this.groupOta = groupOta;
        this.main = main;
        this.otaImage1 = otaImage1;
        this.otaImage2 = otaImage2;
        this.progressValue = progressValue;
        this.tvOta2 = tvOta2;
        this.tvOtaStart = tvOtaStart;
        this.tvTextWifi = tvTextWifi;
        this.tvTitle = tvTitle;
        this.tvWarmingInfo = tvWarmingInfo;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityOtaactivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityOtaactivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_otaactivity, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityOtaactivityBinding bind(View rootView) {
        int i = C0775R.id.app_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.csl_ota_wifi;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout != null) {
                i = C0775R.id.group_ota;
                Group group = (Group) ViewBindings.findChildViewById(rootView, i);
                if (group != null) {
                    ConstraintLayout constraintLayout2 = (ConstraintLayout) rootView;
                    i = C0775R.id.ota_image_1;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                    if (imageView2 != null) {
                        i = C0775R.id.ota_image_2;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                        if (imageView3 != null) {
                            i = C0775R.id.progressValue;
                            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, i);
                            if (progressBar != null) {
                                i = C0775R.id.tv_ota_2;
                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                                if (textView != null) {
                                    i = C0775R.id.tv_ota_start;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                    if (textView2 != null) {
                                        i = C0775R.id.tv_text_wifi;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                        if (textView3 != null) {
                                            i = C0775R.id.tvTitle;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                            if (textView4 != null) {
                                                i = C0775R.id.tv_warming_info;
                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                if (textView5 != null) {
                                                    return new ActivityOtaactivityBinding(constraintLayout2, imageView, constraintLayout, group, constraintLayout2, imageView2, imageView3, progressBar, textView, textView2, textView3, textView4, textView5);
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
