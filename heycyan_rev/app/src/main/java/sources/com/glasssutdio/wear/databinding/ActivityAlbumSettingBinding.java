package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.view.GlassSwitchCompat;

/* loaded from: classes.dex */
public final class ActivityAlbumSettingBinding implements ViewBinding {
    public final ConstraintLayout clsWatermark;
    public final GlassSwitchCompat gsc1;
    public final GlassSwitchCompat gsc2;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvAutoHint;
    public final TextView tvAutoHint2;
    public final TextView tvWatermarkHint;
    public final TextView tvWatermarkHint2;

    private ActivityAlbumSettingBinding(ConstraintLayout rootView, ConstraintLayout clsWatermark, GlassSwitchCompat gsc1, GlassSwitchCompat gsc2, ConstraintLayout main, LayoutTitleBarBinding title, TextView tvAutoHint, TextView tvAutoHint2, TextView tvWatermarkHint, TextView tvWatermarkHint2) {
        this.rootView = rootView;
        this.clsWatermark = clsWatermark;
        this.gsc1 = gsc1;
        this.gsc2 = gsc2;
        this.main = main;
        this.title = title;
        this.tvAutoHint = tvAutoHint;
        this.tvAutoHint2 = tvAutoHint2;
        this.tvWatermarkHint = tvWatermarkHint;
        this.tvWatermarkHint2 = tvWatermarkHint2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAlbumSettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAlbumSettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_album_setting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAlbumSettingBinding bind(View rootView) {
        int i = C0775R.id.cls_watermark;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.gsc_1;
            GlassSwitchCompat glassSwitchCompat = (GlassSwitchCompat) ViewBindings.findChildViewById(rootView, i);
            if (glassSwitchCompat != null) {
                i = C0775R.id.gsc_2;
                GlassSwitchCompat glassSwitchCompat2 = (GlassSwitchCompat) ViewBindings.findChildViewById(rootView, i);
                if (glassSwitchCompat2 != null) {
                    ConstraintLayout constraintLayout2 = (ConstraintLayout) rootView;
                    i = C0775R.id.title;
                    View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
                    if (viewFindChildViewById != null) {
                        LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                        i = C0775R.id.tv_auto_hint;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView != null) {
                            i = C0775R.id.tv_auto_hint_2;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView2 != null) {
                                i = C0775R.id.tv_watermark_hint;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                if (textView3 != null) {
                                    i = C0775R.id.tv_watermark_hint_2;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                    if (textView4 != null) {
                                        return new ActivityAlbumSettingBinding(constraintLayout2, constraintLayout, glassSwitchCompat, glassSwitchCompat2, constraintLayout2, layoutTitleBarBindingBind, textView, textView2, textView3, textView4);
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
