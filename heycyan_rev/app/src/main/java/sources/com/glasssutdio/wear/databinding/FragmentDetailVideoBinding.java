package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class FragmentDetailVideoBinding implements ViewBinding {
    public final ImageView ivPlay;
    public final ImageView ivSound;
    public final ImageView ivThumbnail;
    public final SeekBar pbProgress;
    private final ConstraintLayout rootView;
    public final TextView tvEnd;
    public final TextView tvStart;
    public final VideoView videoView;

    private FragmentDetailVideoBinding(ConstraintLayout rootView, ImageView ivPlay, ImageView ivSound, ImageView ivThumbnail, SeekBar pbProgress, TextView tvEnd, TextView tvStart, VideoView videoView) {
        this.rootView = rootView;
        this.ivPlay = ivPlay;
        this.ivSound = ivSound;
        this.ivThumbnail = ivThumbnail;
        this.pbProgress = pbProgress;
        this.tvEnd = tvEnd;
        this.tvStart = tvStart;
        this.videoView = videoView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentDetailVideoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentDetailVideoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.fragment_detail_video, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentDetailVideoBinding bind(View rootView) {
        int i = C0775R.id.iv_play;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.iv_sound;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView2 != null) {
                i = C0775R.id.iv_thumbnail;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                if (imageView3 != null) {
                    i = C0775R.id.pb_progress;
                    SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(rootView, i);
                    if (seekBar != null) {
                        i = C0775R.id.tv_end;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView != null) {
                            i = C0775R.id.tv_start;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView2 != null) {
                                i = C0775R.id.videoView;
                                VideoView videoView = (VideoView) ViewBindings.findChildViewById(rootView, i);
                                if (videoView != null) {
                                    return new FragmentDetailVideoBinding((ConstraintLayout) rootView, imageView, imageView2, imageView3, seekBar, textView, textView2, videoView);
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
