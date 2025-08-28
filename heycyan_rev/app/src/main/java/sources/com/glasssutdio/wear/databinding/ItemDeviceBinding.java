package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class ItemDeviceBinding implements ViewBinding {
    private final CardView rootView;
    public final TextView tvDeviceAddress;
    public final TextView tvDeviceDetails;
    public final TextView tvDeviceName;

    private ItemDeviceBinding(CardView rootView, TextView tvDeviceAddress, TextView tvDeviceDetails, TextView tvDeviceName) {
        this.rootView = rootView;
        this.tvDeviceAddress = tvDeviceAddress;
        this.tvDeviceDetails = tvDeviceDetails;
        this.tvDeviceName = tvDeviceName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static ItemDeviceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemDeviceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.item_device, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemDeviceBinding bind(View rootView) {
        int i = C0775R.id.tvDeviceAddress;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
        if (textView != null) {
            i = C0775R.id.tvDeviceDetails;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
            if (textView2 != null) {
                i = C0775R.id.tvDeviceName;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView3 != null) {
                    return new ItemDeviceBinding((CardView) rootView, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
