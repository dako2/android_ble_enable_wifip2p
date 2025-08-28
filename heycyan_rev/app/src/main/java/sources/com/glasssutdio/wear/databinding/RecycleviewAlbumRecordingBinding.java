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
import com.glasssutdio.wear.all.view.CircleProgressView;

/* loaded from: classes.dex */
public final class RecycleviewAlbumRecordingBinding implements ViewBinding {
    public final CircleProgressView eisProgress;
    public final ImageView imageSelect;
    private final ConstraintLayout rootView;
    public final ImageView showImageSrc;
    public final TextView tvVideoLength;

    private RecycleviewAlbumRecordingBinding(ConstraintLayout rootView, CircleProgressView eisProgress, ImageView imageSelect, ImageView showImageSrc, TextView tvVideoLength) {
        this.rootView = rootView;
        this.eisProgress = eisProgress;
        this.imageSelect = imageSelect;
        this.showImageSrc = showImageSrc;
        this.tvVideoLength = tvVideoLength;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewAlbumRecordingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewAlbumRecordingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.recycleview_album_recording, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewAlbumRecordingBinding bind(View rootView) {
        int i = C0775R.id.eis_progress;
        CircleProgressView circleProgressView = (CircleProgressView) ViewBindings.findChildViewById(rootView, i);
        if (circleProgressView != null) {
            i = C0775R.id.image_select;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView != null) {
                i = C0775R.id.show_image_src;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                if (imageView2 != null) {
                    i = C0775R.id.tv_video_length;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView != null) {
                        return new RecycleviewAlbumRecordingBinding((ConstraintLayout) rootView, circleProgressView, imageView, imageView2, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
