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
public final class ActivityDeviceBindBinding implements ViewBinding {
    public final ConstraintLayout ctlConnecting;
    public final ConstraintLayout ctlSearchAgain;
    public final ConstraintLayout ctlSearchIng;
    public final RecyclerView deviceRcv;
    public final ImageView imageConnecting;
    public final ImageView imageNo1;
    public final ImageView ivSearching;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvConnecting;
    public final TextView tvContent1;
    public final TextView tvContentNo1;
    public final TextView tvRestartSearch;
    public final TextView tvScanNoStatus;
    public final TextView tvScanStatus;
    public final TextView tvSearch1;

    private ActivityDeviceBindBinding(ConstraintLayout rootView, ConstraintLayout ctlConnecting, ConstraintLayout ctlSearchAgain, ConstraintLayout ctlSearchIng, RecyclerView deviceRcv, ImageView imageConnecting, ImageView imageNo1, ImageView ivSearching, LayoutTitleBarBinding titleBar, TextView tvConnecting, TextView tvContent1, TextView tvContentNo1, TextView tvRestartSearch, TextView tvScanNoStatus, TextView tvScanStatus, TextView tvSearch1) {
        this.rootView = rootView;
        this.ctlConnecting = ctlConnecting;
        this.ctlSearchAgain = ctlSearchAgain;
        this.ctlSearchIng = ctlSearchIng;
        this.deviceRcv = deviceRcv;
        this.imageConnecting = imageConnecting;
        this.imageNo1 = imageNo1;
        this.ivSearching = ivSearching;
        this.titleBar = titleBar;
        this.tvConnecting = tvConnecting;
        this.tvContent1 = tvContent1;
        this.tvContentNo1 = tvContentNo1;
        this.tvRestartSearch = tvRestartSearch;
        this.tvScanNoStatus = tvScanNoStatus;
        this.tvScanStatus = tvScanStatus;
        this.tvSearch1 = tvSearch1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDeviceBindBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDeviceBindBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_device_bind, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityDeviceBindBinding bind(View rootView) {
        View viewFindChildViewById;
        int i = C0775R.id.ctl_connecting;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
        if (constraintLayout != null) {
            i = C0775R.id.ctl_search_again;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
            if (constraintLayout2 != null) {
                i = C0775R.id.ctl_search_ing;
                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, i);
                if (constraintLayout3 != null) {
                    i = C0775R.id.device_rcv;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
                    if (recyclerView != null) {
                        i = C0775R.id.image_connecting;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                        if (imageView != null) {
                            i = C0775R.id.image_no_1;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                            if (imageView2 != null) {
                                i = C0775R.id.iv_searching;
                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                if (imageView3 != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i = C0775R.id.titleBar))) != null) {
                                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                    i = C0775R.id.tv_connecting;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                                    if (textView != null) {
                                        i = C0775R.id.tv_content_1;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                        if (textView2 != null) {
                                            i = C0775R.id.tv_content_no_1;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                            if (textView3 != null) {
                                                i = C0775R.id.tv_restart_search;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                if (textView4 != null) {
                                                    i = C0775R.id.tv_scan_no_status;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                    if (textView5 != null) {
                                                        i = C0775R.id.tv_scan_status;
                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                        if (textView6 != null) {
                                                            i = C0775R.id.tv_search_1;
                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                                            if (textView7 != null) {
                                                                return new ActivityDeviceBindBinding((ConstraintLayout) rootView, constraintLayout, constraintLayout2, constraintLayout3, recyclerView, imageView, imageView2, imageView3, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5, textView6, textView7);
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
