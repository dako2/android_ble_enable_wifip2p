package iflb;

import java.util.Locale;

/* renamed from: iflb.a */
/* loaded from: classes2.dex */
public class C2438a {

    /* renamed from: a */
    private static Locale f540a = Locale.CHINA;

    /* renamed from: a */
    public static String m595a(int i) {
        String[] strArr = C2439b.f543c;
        if (f540a.equals(Locale.US)) {
            strArr = C2440c.f547c;
        } else if (f540a.equals(Locale.TRADITIONAL_CHINESE)) {
            strArr = C2441d.f551c;
        }
        return (i <= 0 || i >= strArr.length) ? m596b(1) : strArr[i];
    }

    /* renamed from: b */
    public static String m596b(int i) {
        String[] strArr = C2439b.f544d;
        if (f540a.equals(Locale.US)) {
            strArr = C2440c.f548d;
        } else if (f540a.equals(Locale.TRADITIONAL_CHINESE)) {
            strArr = C2441d.f552d;
        }
        return (i < 0 || i >= strArr.length) ? "" : strArr[i];
    }
}
