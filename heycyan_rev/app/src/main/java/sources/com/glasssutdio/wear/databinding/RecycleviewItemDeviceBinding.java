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

/* loaded from: classes.dex */
public final class RecycleviewItemDeviceBinding implements ViewBinding {
    public final ImageView image1;
    public final TextView rcvDeviceAddress;
    public final TextView rcvDeviceName;
    public final TextView rcvTvConnect;
    private final ConstraintLayout rootView;

    private RecycleviewItemDeviceBinding(ConstraintLayout rootView, ImageView image1, TextView rcvDeviceAddress, TextView rcvDeviceName, TextView rcvTvConnect) {
        this.rootView = rootView;
        this.image1 = image1;
        this.rcvDeviceAddress = rcvDeviceAddress;
        this.rcvDeviceName = rcvDeviceName;
        this.rcvTvConnect = rcvTvConnect;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemDeviceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemDeviceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.recycleview_item_device, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemDeviceBinding bind(View rootView) {
        int i = C0775R.id.image_1;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
        if (imageView != null) {
            i = C0775R.id.rcv_device_address;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
            if (textView != null) {
                i = C0775R.id.rcv_device_name;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView2 != null) {
                    i = C0775R.id.rcv_tv_connect;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView3 != null) {
                        return new RecycleviewItemDeviceBinding((ConstraintLayout) rootView, imageView, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
