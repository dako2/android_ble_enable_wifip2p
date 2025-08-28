package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class FragmentAlbumVideoBinding implements ViewBinding {
    public final ConstraintLayout ctlNoData;
    public final ImageView image1;
    public final RecyclerView rcvAlbumVideo;
    private final ConstraintLayout rootView;
    public final TextView tv1;

    private FragmentAlbumVideoBinding(ConstraintLayout rootView, ConstraintLayout ctlNoData, ImageView image1, RecyclerView rcvAlbumVideo, TextView tv1) {
        this.rootView = rootView;
        this.ctlNoData = ctlNoData;
        this.image1 = image1;
        this.rcvAlbumVideo = rcvAlbumVideo;
        this.tv1 = tv1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentAlbumVideoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentAlbumVideoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.fragment_album_video, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentAlbumVideoBinding bind(View rootView) {
        int i = C0775R.id.ctl_no_data;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.image_1;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView != null) {
                i = C0775R.id.rcv_album_video;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
                if (recyclerView != null) {
                    i = C0775R.id.tv_1;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView != null) {
                        return new FragmentAlbumVideoBinding((ConstraintLayout) rootView, constraintLayout, imageView, recyclerView, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
