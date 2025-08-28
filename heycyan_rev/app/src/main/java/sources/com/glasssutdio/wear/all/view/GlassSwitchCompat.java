package com.glasssutdio.wear.all.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.SwitchCompat;

/* loaded from: classes.dex */
public class GlassSwitchCompat extends SwitchCompat {
    private Context context;

    public GlassSwitchCompat(Context context) {
        super(context);
        this.context = context;
    }

    public GlassSwitchCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public GlassSwitchCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }
}
