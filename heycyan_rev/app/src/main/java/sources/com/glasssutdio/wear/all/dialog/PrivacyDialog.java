package com.glasssutdio.wear.all.dialog;

import android.app.Dialog;
import android.content.Context;
import com.glasssutdio.wear.C0775R;

/* loaded from: classes.dex */
public class PrivacyDialog extends Dialog {
    public PrivacyDialog(Context context) {
        super(context, C0775R.style.PrivacyThemeDialog);
        setContentView(C0775R.layout.dialog_privary);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }
}
