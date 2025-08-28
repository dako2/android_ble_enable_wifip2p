package com.google.android.material.bottomnavigation;

import android.content.Context;
import com.google.android.material.C1174R;
import com.google.android.material.navigation.NavigationBarItemView;

/* loaded from: classes2.dex */
public class BottomNavigationItemView extends NavigationBarItemView {
    public BottomNavigationItemView(Context context) {
        super(context);
    }

    @Override // com.google.android.material.navigation.NavigationBarItemView
    protected int getItemLayoutResId() {
        return C1174R.layout.design_bottom_navigation_item;
    }

    @Override // com.google.android.material.navigation.NavigationBarItemView
    protected int getItemDefaultMarginResId() {
        return C1174R.dimen.design_bottom_navigation_margin;
    }
}
