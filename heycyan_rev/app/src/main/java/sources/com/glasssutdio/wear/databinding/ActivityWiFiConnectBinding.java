package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ActivityWiFiConnectBinding implements ViewBinding {
    public final Button btnConnect;
    public final Button btnScan;
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;

    private ActivityWiFiConnectBinding(ConstraintLayout rootView, Button btnConnect, Button btnScan, ConstraintLayout main) {
        this.rootView = rootView;
        this.btnConnect = btnConnect;
        this.btnScan = btnScan;
        this.main = main;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityWiFiConnectBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityWiFiConnectBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.activity_wi_fi_connect, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityWiFiConnectBinding bind(View rootView) {
        int i = C0775R.id.btn_connect;
        Button button = (Button) ViewBindings.findChildViewById(rootView, i);
        if (button != null) {
            i = C0775R.id.btn_scan;
            Button button2 = (Button) ViewBindings.findChildViewById(rootView, i);
            if (button2 != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
                return new ActivityWiFiConnectBinding(constraintLayout, button, button2, constraintLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
