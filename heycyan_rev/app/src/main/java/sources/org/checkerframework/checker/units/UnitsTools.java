package org.checkerframework.checker.units;

/* loaded from: classes3.dex */
public class UnitsTools {

    /* renamed from: A */
    public static final int f937A = 1;

    /* renamed from: C */
    public static final int f938C = 1;

    /* renamed from: K */
    public static final int f939K = 1;

    /* renamed from: cd */
    public static final int f940cd = 1;
    public static final double deg = 1.0d;

    /* renamed from: g */
    public static final int f941g = 1;

    /* renamed from: h */
    public static final int f942h = 1;

    /* renamed from: kg */
    public static final int f943kg = 1;

    /* renamed from: km */
    public static final int f944km = 1;
    public static final int km2 = 1;
    public static final int kmPERh = 1;

    /* renamed from: m */
    public static final int f945m = 1;

    /* renamed from: m2 */
    public static final int f946m2 = 1;
    public static final int mPERs = 1;
    public static final int mPERs2 = 1;
    public static final int min = 1;

    /* renamed from: mm */
    public static final int f947mm = 1;
    public static final int mm2 = 1;
    public static final int mol = 1;
    public static final double rad = 1.0d;

    /* renamed from: s */
    public static final int f948s = 1;

    public static int fromCelsiusToKelvin(int i) {
        return i + 273;
    }

    public static int fromHourToMinute(int i) {
        return i * 60;
    }

    public static int fromKelvinToCelsius(int i) {
        return i - 273;
    }

    public static int fromKiloGramToGram(int i) {
        return i * 1000;
    }

    public static double fromKiloMeterPerHourToMeterPerSecond(double d) {
        return d / 3.6d;
    }

    public static int fromKiloMeterToMeter(int i) {
        return i * 1000;
    }

    public static double fromMeterPerSecondToKiloMeterPerHour(double d) {
        return d * 3.6d;
    }

    public static int fromMeterToMilliMeter(int i) {
        return i * 1000;
    }

    public static int fromMinuteToSecond(int i) {
        return i * 60;
    }

    public static double toRadians(double d) {
        return Math.toRadians(d);
    }

    public static double toDegrees(double d) {
        return Math.toDegrees(d);
    }

    public static int fromMilliMeterToMeter(int i) {
        return i / 1000;
    }

    public static int fromMeterToKiloMeter(int i) {
        return i / 1000;
    }

    public static int fromGramToKiloGram(int i) {
        return i / 1000;
    }

    public static int fromSecondToMinute(int i) {
        return i / 60;
    }

    public static int fromMinuteToHour(int i) {
        return i / 60;
    }
}
