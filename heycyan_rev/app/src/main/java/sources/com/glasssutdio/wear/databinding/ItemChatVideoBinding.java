package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ItemChatVideoBinding implements ViewBinding {
    public final ImageView ivChecked;
    private final ConstraintLayout rootView;
    public final VideoView videoMessage;

    private ItemChatVideoBinding(ConstraintLayout rootView, ImageView ivChecked, VideoView videoMessage) {
        this.rootView = rootView;
        this.ivChecked = ivChecked;
        this.videoMessage = videoMessage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemChatVideoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemChatVideoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.item_chat_video, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemChatVideoBinding bind(View rootView) {
        int i = C0775R.id.iv_checked;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.videoMessage;
            VideoView videoView = (VideoView) ViewBindings.findChildViewById(rootView, i);
            if (videoView != null) {
                return new ItemChatVideoBinding((ConstraintLayout) rootView, imageView, videoView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
