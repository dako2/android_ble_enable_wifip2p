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
public final class FragmentDetailRecordBinding implements ViewBinding {
    public final ImageView ivPlay;
    public final ImageView ivSound;
    public final SeekBar pbProgress;
    private final ConstraintLayout rootView;
    public final TextView tvCurrent;
    public final TextView tvEnd;
    public final TextView tvStart;
    public final PcmWaveformView waveView;

    private FragmentDetailRecordBinding(ConstraintLayout rootView, ImageView ivPlay, ImageView ivSound, SeekBar pbProgress, TextView tvCurrent, TextView tvEnd, TextView tvStart, PcmWaveformView waveView) {
        this.rootView = rootView;
        this.ivPlay = ivPlay;
        this.ivSound = ivSound;
        this.pbProgress = pbProgress;
        this.tvCurrent = tvCurrent;
        this.tvEnd = tvEnd;
        this.tvStart = tvStart;
        this.waveView = waveView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentDetailRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentDetailRecordBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.fragment_detail_record, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentDetailRecordBinding bind(View rootView) {
        int i = C0775R.id.iv_play;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.iv_sound;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView2 != null) {
                i = C0775R.id.pb_progress;
                SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(rootView, i);
                if (seekBar != null) {
                    i = C0775R.id.tv_current;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView != null) {
                        i = C0775R.id.tv_end;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView2 != null) {
                            i = C0775R.id.tv_start;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView3 != null) {
                                i = C0775R.id.wave_view;
                                PcmWaveformView pcmWaveformView = (PcmWaveformView) ViewBindings.findChildViewById(rootView, i);
                                if (pcmWaveformView != null) {
                                    return new FragmentDetailRecordBinding((ConstraintLayout) rootView, imageView, imageView2, seekBar, textView, textView2, textView3, pcmWaveformView);
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
