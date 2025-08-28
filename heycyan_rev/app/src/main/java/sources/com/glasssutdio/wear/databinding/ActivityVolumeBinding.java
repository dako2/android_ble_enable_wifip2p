package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityVolumeBinding implements ViewBinding {
    public final ConstraintLayout clsVolumeCall;
    public final ConstraintLayout clsVolumeMusic;
    public final ConstraintLayout clsVolumeSystem;
    public final ImageView ivHomeVolumeCall;
    public final ImageView ivHomeVolumeMusic;
    public final ImageView ivHomeVolumeSystem;
    public final ConstraintLayout main;
    public final SeekBar pbCall;
    public final SeekBar pbMusic;
    public final SeekBar pbSystem;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvHint;
    public final TextView tvVolumeTitleCall;
    public final TextView tvVolumeTitleMusic;
    public final TextView tvVolumeTitleSystem;

    private ActivityVolumeBinding(ConstraintLayout rootView, ConstraintLayout clsVolumeCall, ConstraintLayout clsVolumeMusic, ConstraintLayout clsVolumeSystem, ImageView ivHomeVolumeCall, ImageView ivHomeVolumeMusic, ImageView ivHomeVolumeSystem, ConstraintLayout main, SeekBar pbCall, SeekBar pbMusic, SeekBar pbSystem, LayoutTitleBarBinding title, TextView tvHint, TextView tvVolumeTitleCall, TextView tvVolumeTitleMusic, TextView tvVolumeTitleSystem) {
        this.rootView = rootView;
        this.clsVolumeCall = clsVolumeCall;
        this.clsVolumeMusic = clsVolumeMusic;
        this.clsVolumeSystem = clsVolumeSystem;
        this.ivHomeVolumeCall = ivHomeVolumeCall;
        this.ivHomeVolumeMusic = ivHomeVolumeMusic;
        this.ivHomeVolumeSystem = ivHomeVolumeSystem;
        this.main = main;
        this.pbCall = pbCall;
        this.pbMusic = pbMusic;
        this.pbSystem = pbSystem;
        this.title = title;
        this.tvHint = tvHint;
        this.tvVolumeTitleCall = tvVolumeTitleCall;
        this.tvVolumeTitleMusic = tvVolumeTitleMusic;
        this.tvVolumeTitleSystem = tvVolumeTitleSystem;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityVolumeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityVolumeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_volume, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityVolumeBinding bind(View rootView) {
        View viewFindChildViewById;
        int i = C0775R.id.cls_volume_call;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.cls_volume_music;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                i = C0775R.id.cls_volume_system;
                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                if (constraintLayout3 != null) {
                    i = C0775R.id.iv_home_volume_call;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                    if (imageView != null) {
                        i = C0775R.id.iv_home_volume_music;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                        if (imageView2 != null) {
                            i = C0775R.id.iv_home_volume_system;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                            if (imageView3 != null) {
                                ConstraintLayout constraintLayout4 = (ConstraintLayout) rootView;
                                i = C0775R.id.pb_call;
                                SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(rootView, i);
                                if (seekBar != null) {
                                    i = C0775R.id.pb_music;
                                    SeekBar seekBar2 = (SeekBar) ViewBindings.findChildViewById(rootView, i);
                                    if (seekBar2 != null) {
                                        i = C0775R.id.pb_system;
                                        SeekBar seekBar3 = (SeekBar) ViewBindings.findChildViewById(rootView, i);
                                        if (seekBar3 != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i = C0775R.id.title))) != null) {
                                            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                            i = C0775R.id.tv_hint;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                                            if (textView != null) {
                                                i = C0775R.id.tv_volume_title_call;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                if (textView2 != null) {
                                                    i = C0775R.id.tv_volume_title_music;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                    if (textView3 != null) {
                                                        i = C0775R.id.tv_volume_title_system;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                        if (textView4 != null) {
                                                            return new ActivityVolumeBinding(constraintLayout4, constraintLayout, constraintLayout2, constraintLayout3, imageView, imageView2, imageView3, constraintLayout4, seekBar, seekBar2, seekBar3, layoutTitleBarBindingBind, textView, textView2, textView3, textView4);
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
