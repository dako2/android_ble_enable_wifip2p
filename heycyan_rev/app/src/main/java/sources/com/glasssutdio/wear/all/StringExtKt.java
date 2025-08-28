package com.glasssutdio.wear.all;

import com.elvishew.xlog.XLog;
import java.util.List;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: StringExt.kt */
@Metadata(m606d1 = {"\u0000(\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\f\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0001\u001a\n\u0010\u0002\u001a\u00020\u0001*\u00020\u0001\u001a\u0014\u0010\u0003\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u001a%\u0010\u0006\u001a\u00020\u0007*\u0004\u0018\u00010\u00012\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0002\b\n\u001a\f\u0010\u000b\u001a\u00020\f*\u0004\u0018\u00010\u0001\u001a\n\u0010\r\u001a\u00020\u0007*\u00020\u0001\u001a\n\u0010\u000e\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u000f\u001a\u00020\f*\u00020\u0001¨\u0006\u0010"}, m607d2 = {"deleteWhitespace", "", "formatPhoneNumber", "hideEmail", "visiblePrefixChars", "", "ifNotBlank", "", "notNullAction", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "isEmail", "", "log", "mobileFormat", "verifyPwdNotValid", "app_release"}, m608k = 2, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class StringExtKt {
    public static final String mobileFormat(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return new Regex("(\\d{3})\\d{4}(\\d{4})").replace(str, "$1****$2");
    }

    public static final void ifNotBlank(String str, Function1<? super String, Unit> notNullAction) {
        Intrinsics.checkNotNullParameter(notNullAction, "notNullAction");
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return;
        }
        notNullAction.invoke(str);
    }

    public static final boolean verifyPwdNotValid(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return str.length() < 8 || str.length() > 20;
    }

    public static final String deleteWhitespace(String str) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return "";
        }
        int length = str.length();
        char[] cArr = new char[length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (!Character.isWhitespace(str.charAt(i2))) {
                cArr[i] = str.charAt(i2);
                i++;
            }
        }
        return i == length ? str : new String(cArr, 0, i);
    }

    public static final void log(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        XLog.m127d(str);
    }

    public static final boolean isEmail(String str) {
        if (str != null) {
            return Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", 2).matcher(str).matches();
        }
        return false;
    }

    public static /* synthetic */ String hideEmail$default(String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 1;
        }
        return hideEmail(str, i);
    }

    public static final String hideEmail(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        String str2 = str;
        if (!StringsKt.contains$default((CharSequence) str2, (CharSequence) "@", false, 2, (Object) null)) {
            return str;
        }
        List listSplit$default = StringsKt.split$default((CharSequence) str2, new String[]{"@"}, false, 2, 2, (Object) null);
        String str3 = (String) listSplit$default.get(0);
        String str4 = (String) listSplit$default.get(1);
        if (str3.length() > i) {
            return StringsKt.take(str3, i) + "***@" + str4;
        }
        if (str3.length() > 0) {
            return StringsKt.take(str3, i) + "***@" + str4;
        }
        return "***@" + str4;
    }

    public static final String formatPhoneNumber(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (str.length() != 11) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        String strSubstring = str.substring(0, 3);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        StringBuilder sbAppend = sb.append(strSubstring).append("****");
        String strSubstring2 = str.substring(7);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        return sbAppend.append(strSubstring2).toString();
    }
}
