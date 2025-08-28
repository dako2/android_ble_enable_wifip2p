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
import com.glasssutdio.wear.all.view.PcmWaveformView;

/* loaded from: classes.dex */
public final class RecycleViewImageShowDetailRecordingBinding implements ViewBinding {
    public final ImageView ivPlayRecording;
    public final ImageView ivSoundRecording;
    public final SeekBar pbProgressRecording;
    private final ConstraintLayout rootView;
    public final TextView tvCurrentRecording;
    public final TextView tvEndRecording;
    public final TextView tvStartRecording;
    public final PcmWaveformView waveView;

    private RecycleViewImageShowDetailRecordingBinding(ConstraintLayout rootView, ImageView ivPlayRecording, ImageView ivSoundRecording, SeekBar pbProgressRecording, TextView tvCurrentRecording, TextView tvEndRecording, TextView tvStartRecording, PcmWaveformView waveView) {
        this.rootView = rootView;
        this.ivPlayRecording = ivPlayRecording;
        this.ivSoundRecording = ivSoundRecording;
        this.pbProgressRecording = pbProgressRecording;
        this.tvCurrentRecording = tvCurrentRecording;
        this.tvEndRecording = tvEndRecording;
        this.tvStartRecording = tvStartRecording;
        this.waveView = waveView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleViewImageShowDetailRecordingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleViewImageShowDetailRecordingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.recycle_view_image_show_detail_recording, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleViewImageShowDetailRecordingBinding bind(View rootView) {
        int i = C0775R.id.iv_play_recording;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.iv_sound_recording;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView2 != null) {
                i = C0775R.id.pb_progress_recording;
                SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(rootView, i);
                if (seekBar != null) {
                    i = C0775R.id.tv_current_recording;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView != null) {
                        i = C0775R.id.tv_end_recording;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView2 != null) {
                            i = C0775R.id.tv_start_recording;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView3 != null) {
                                i = C0775R.id.wave_view;
                                PcmWaveformView pcmWaveformView = (PcmWaveformView) ViewBindings.findChildViewById(rootView, i);
                                if (pcmWaveformView != null) {
                                    return new RecycleViewImageShowDetailRecordingBinding((ConstraintLayout) rootView, imageView, imageView2, seekBar, textView, textView2, textView3, pcmWaveformView);
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
