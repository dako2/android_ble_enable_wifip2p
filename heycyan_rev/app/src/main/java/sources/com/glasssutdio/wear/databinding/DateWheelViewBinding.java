package com.glasssutdio.wear.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public final class DateWheelViewBinding implements ViewBinding {
    public final RecyclerView rcyDay;
    public final RecyclerView rcyMonth;
    public final RecyclerView rcyYear;
    private final ConstraintLayout rootView;

    private DateWheelViewBinding(ConstraintLayout rootView, RecyclerView rcyDay, RecyclerView rcyMonth, RecyclerView rcyYear) {
        this.rootView = rootView;
        this.rcyDay = rcyDay;
        this.rcyMonth = rcyMonth;
        this.rcyYear = rcyYear;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DateWheelViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DateWheelViewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(C0775R.layout.date_wheel_view, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DateWheelViewBinding bind(View rootView) {
        int i = C0775R.id.rcy_day;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
        if (recyclerView != null) {
            i = C0775R.id.rcy_month;
            RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
            if (recyclerView2 != null) {
                i = C0775R.id.rcy_year;
                RecyclerView recyclerView3 = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
                if (recyclerView3 != null) {
                    return new DateWheelViewBinding((ConstraintLayout) rootView, recyclerView, recyclerView2, recyclerView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
