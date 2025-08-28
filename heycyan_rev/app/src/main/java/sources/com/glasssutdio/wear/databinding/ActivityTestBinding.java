package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;
import com.glasssutdio.wear.all.view.BatteryImage;

/* loaded from: classes.dex */
public final class ActivityTestBinding implements ViewBinding {
    public final BatteryImage batteryView;
    public final Button btnSave;
    public final Button btnSelectVideo;
    public final Button btnStart;
    public final ConstraintLayout clsParent;
    public final ImageView ivImg;
    public final ImageView ivPlay;
    public final ImageView ivSearching;
    public final ImageView ivSound;
    public final LinearLayout llWatermark;
    public final ConstraintLayout main;
    public final SeekBar pbProgress;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvName;
    public final TextView tvPath;
    public final TextView tvSave;
    public final TextView tvTime;
    public final VideoView videoView;
    public final View viewLine;

    private ActivityTestBinding(ConstraintLayout rootView, BatteryImage batteryView, Button btnSave, Button btnSelectVideo, Button btnStart, ConstraintLayout clsParent, ImageView ivImg, ImageView ivPlay, ImageView ivSearching, ImageView ivSound, LinearLayout llWatermark, ConstraintLayout main, SeekBar pbProgress, LayoutTitleBarBinding title, TextView tvName, TextView tvPath, TextView tvSave, TextView tvTime, VideoView videoView, View viewLine) {
        this.rootView = rootView;
        this.batteryView = batteryView;
        this.btnSave = btnSave;
        this.btnSelectVideo = btnSelectVideo;
        this.btnStart = btnStart;
        this.clsParent = clsParent;
        this.ivImg = ivImg;
        this.ivPlay = ivPlay;
        this.ivSearching = ivSearching;
        this.ivSound = ivSound;
        this.llWatermark = llWatermark;
        this.main = main;
        this.pbProgress = pbProgress;
        this.title = title;
        this.tvName = tvName;
        this.tvPath = tvPath;
        this.tvSave = tvSave;
        this.tvTime = tvTime;
        this.videoView = videoView;
        this.viewLine = viewLine;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityTestBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityTestBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_test, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityTestBinding bind(View rootView) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        int i = C0775R.id.battery_view;
        BatteryImage batteryImage = (BatteryImage) ViewBindings.findChildViewById(rootView, i);
        if (batteryImage != null) {
            i = C0775R.id.btn_save;
            Button button = (Button) ViewBindings.findChildViewById(rootView, i);
            if (button != null) {
                i = C0775R.id.btn_select_video;
                Button button2 = (Button) ViewBindings.findChildViewById(rootView, i);
                if (button2 != null) {
                    i = C0775R.id.btn_start;
                    Button button3 = (Button) ViewBindings.findChildViewById(rootView, i);
                    if (button3 != null) {
                        i = C0775R.id.cls_parent;
                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                        if (constraintLayout != null) {
                            i = C0775R.id.iv_img;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                            if (imageView != null) {
                                i = C0775R.id.iv_play;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                if (imageView2 != null) {
                                    i = C0775R.id.iv_searching;
                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                    if (imageView3 != null) {
                                        i = C0775R.id.iv_sound;
                                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                        if (imageView4 != null) {
                                            i = C0775R.id.ll_watermark;
                                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i);
                                            if (linearLayout != null) {
                                                ConstraintLayout constraintLayout2 = (ConstraintLayout) rootView;
                                                i = C0775R.id.pb_progress;
                                                SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(rootView, i);
                                                if (seekBar != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i = C0775R.id.title))) != null) {
                                                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                                    i = C0775R.id.tv_name;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                    if (textView != null) {
                                                        i = C0775R.id.tv_path;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                        if (textView2 != null) {
                                                            i = C0775R.id.tv_save;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                            if (textView3 != null) {
                                                                i = C0775R.id.tv_time;
                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                                if (textView4 != null) {
                                                                    i = C0775R.id.video_view;
                                                                    VideoView videoView = (VideoView) ViewBindings.findChildViewById(rootView, i);
                                                                    if (videoView != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, (i = C0775R.id.view_line))) != null) {
                                                                        return new ActivityTestBinding(constraintLayout2, batteryImage, button, button2, button3, constraintLayout, imageView, imageView2, imageView3, imageView4, linearLayout, constraintLayout2, seekBar, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, videoView, viewFindChildViewById2);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
