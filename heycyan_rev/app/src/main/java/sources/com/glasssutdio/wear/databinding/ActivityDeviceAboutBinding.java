package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityDeviceAboutBinding implements ViewBinding {
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding title;
    public final TextView tvAppVersion;
    public final TextView tvBleName;
    public final TextView tvHwVersion;
    public final TextView tvSwVersion;
    public final TextView tvWifiHwVersion;
    public final TextView tvWifiSwVersion;

    private ActivityDeviceAboutBinding(ConstraintLayout rootView, ConstraintLayout main, LayoutTitleBarBinding title, TextView tvAppVersion, TextView tvBleName, TextView tvHwVersion, TextView tvSwVersion, TextView tvWifiHwVersion, TextView tvWifiSwVersion) {
        this.rootView = rootView;
        this.main = main;
        this.title = title;
        this.tvAppVersion = tvAppVersion;
        this.tvBleName = tvBleName;
        this.tvHwVersion = tvHwVersion;
        this.tvSwVersion = tvSwVersion;
        this.tvWifiHwVersion = tvWifiHwVersion;
        this.tvWifiSwVersion = tvWifiSwVersion;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDeviceAboutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDeviceAboutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_device_about, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityDeviceAboutBinding bind(View rootView) {
        ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
        int i = C0775R.id.title;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
        if (viewFindChildViewById != null) {
            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
            i = C0775R.id.tv_app_version;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
            if (textView != null) {
                i = C0775R.id.tv_ble_name;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView2 != null) {
                    i = C0775R.id.tv_hw_version;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView3 != null) {
                        i = C0775R.id.tv_sw_version;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView4 != null) {
                            i = C0775R.id.tv_wifi_hw_version;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView5 != null) {
                                i = C0775R.id.tv_wifi_sw_version;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i);
                                if (textView6 != null) {
                                    return new ActivityDeviceAboutBinding(constraintLayout, constraintLayout, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5, textView6);
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
