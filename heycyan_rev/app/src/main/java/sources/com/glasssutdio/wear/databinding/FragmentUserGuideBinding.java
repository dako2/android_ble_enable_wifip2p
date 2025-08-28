package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class FragmentUserGuideBinding implements ViewBinding {
    public final ImageView ivImg;
    public final NestedScrollView main;
    private final NestedScrollView rootView;
    public final TextView tvDesc;
    public final TextView tvIndex;
    public final TextView tvSubTitle;
    public final TextView tvTime;
    public final TextView tvTitle;

    private FragmentUserGuideBinding(NestedScrollView rootView, ImageView ivImg, NestedScrollView main, TextView tvDesc, TextView tvIndex, TextView tvSubTitle, TextView tvTime, TextView tvTitle) {
        this.rootView = rootView;
        this.ivImg = ivImg;
        this.main = main;
        this.tvDesc = tvDesc;
        this.tvIndex = tvIndex;
        this.tvSubTitle = tvSubTitle;
        this.tvTime = tvTime;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    public static FragmentUserGuideBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentUserGuideBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.fragment_user_guide, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentUserGuideBinding bind(View rootView) {
        int i = C0775R.id.iv_img;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            NestedScrollView nestedScrollView = (NestedScrollView) rootView;
            i = C0775R.id.tv_desc;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
            if (textView != null) {
                i = C0775R.id.tv_index;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView2 != null) {
                    i = C0775R.id.tv_sub_title;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView3 != null) {
                        i = C0775R.id.tv_time;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView4 != null) {
                            i = C0775R.id.tv_title;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView5 != null) {
                                return new FragmentUserGuideBinding(nestedScrollView, imageView, nestedScrollView, textView, textView2, textView3, textView4, textView5);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
