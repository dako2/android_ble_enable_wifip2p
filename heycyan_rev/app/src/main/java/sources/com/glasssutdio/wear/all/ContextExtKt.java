package com.glasssutdio.wear.all;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.widget.PopupWindow;
import androidx.exifinterface.media.ExifInterface;
import androidx.viewbinding.ViewBinding;
import java.lang.reflect.InvocationTargetException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ContextExt.kt */
@Metadata(m606d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001aV\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\u001a\b\n\u0010\n\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\f0\u000bH\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\r"}, m607d2 = {"createPopupWithBinding", "Landroid/widget/PopupWindow;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/viewbinding/ViewBinding;", "Landroid/content/Context;", "width", "", "height", "focusable", "", "init", "Lkotlin/Function2;", "", "app_release"}, m608k = 2, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class ContextExtKt {
    public static /* synthetic */ PopupWindow createPopupWithBinding$default(Context context, int i, int i2, boolean z, Function2 init, int i3, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if ((i3 & 1) != 0) {
            i = -1;
        }
        if ((i3 & 2) != 0) {
            i2 = -2;
        }
        if ((i3 & 4) != 0) {
            z = true;
        }
        if ((i3 & 8) != 0) {
            Intrinsics.needClassReification();
            init = C07991.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(init, "init");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Object objInvoke = ViewBinding.class.getMethod("inflate", LayoutInflater.class).invoke(null, LayoutInflater.from(context));
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        ViewBinding viewBinding = (ViewBinding) objInvoke;
        PopupWindow popupWindow = new PopupWindow(viewBinding.getRoot(), i, i2, z);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setSoftInputMode(20);
        popupWindow.setOutsideTouchable(true);
        init.invoke(viewBinding, popupWindow);
        return popupWindow;
    }

    public static final /* synthetic */ <T extends ViewBinding> PopupWindow createPopupWithBinding(Context context, int i, int i2, boolean z, Function2<? super T, ? super PopupWindow, Unit> init) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(init, "init");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Object objInvoke = ViewBinding.class.getMethod("inflate", LayoutInflater.class).invoke(null, LayoutInflater.from(context));
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        ViewBinding viewBinding = (ViewBinding) objInvoke;
        PopupWindow popupWindow = new PopupWindow(viewBinding.getRoot(), i, i2, z);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setSoftInputMode(20);
        popupWindow.setOutsideTouchable(true);
        init.invoke(viewBinding, popupWindow);
        return popupWindow;
    }
}
