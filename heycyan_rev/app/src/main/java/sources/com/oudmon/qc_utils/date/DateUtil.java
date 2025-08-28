package com.oudmon.qc_utils.date;

import android.os.Build;
import com.tencent.mmkv.MMKV;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class DateUtil {
    public static long Hour_S_Min = 3600;
    public static final String dFyyyyMMdd1 = "yyyy-MM-dd";
    public static final String yyyyMMdd_HHmm = "yyyy-MM-dd HH:mm";
    public static final String yyyyMMdd_HHmmss = "yyyy-MM-dd HH:mm:ss";

    /* renamed from: c */
    private Calendar f538c;
    public static final Locale localeObject = new Locale("en");
    private static final ThreadLocal<SimpleDateFormat> dFMMdd = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.qc_utils.date.DateUtil.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM/dd", DateUtil.localeObject);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFMMdd_HHmm = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.qc_utils.date.DateUtil.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM-dd HH:mm", DateUtil.localeObject);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFyyyyMM = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.qc_utils.date.DateUtil.3
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM", DateUtil.localeObject);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFyyyyMMdd = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.qc_utils.date.DateUtil.4
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd", DateUtil.localeObject);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dfddMMyyy = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.qc_utils.date.DateUtil.5
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("dd-MM-yyyy", DateUtil.localeObject);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFyyyyMMdd_HHmm = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.qc_utils.date.DateUtil.6
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm", DateUtil.localeObject);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFyyyyMMdd_HHmmss = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.qc_utils.date.DateUtil.7
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", DateUtil.localeObject);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFHHmm = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.qc_utils.date.DateUtil.8
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm", DateUtil.localeObject);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFHHmmss = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.qc_utils.date.DateUtil.9
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss", DateUtil.localeObject);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dFSyyyyMMdd = new ThreadLocal<SimpleDateFormat>() { // from class: com.oudmon.qc_utils.date.DateUtil.10
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd", DateUtil.localeObject);
        }
    };
    public static SimpleDateFormat yyyyMMdd_HHmmssF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat dFyyyyMMddF = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat dFyyyyMMddmmF = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public enum DateFormater {
        MMdd,
        MMdd_HHmm,
        yyyyMM,
        yyyyMMdd,
        yyyyMMdd_HHmm,
        yyyyMMdd_HHmmss,
        HHmm,
        HHmmss,
        yyyyMMddHHmm,
        SyyyyMMdd,
        dFyyyyMMdd,
        dFHHmm,
        dfddMMyyy
    }

    public long getZeroTime() {
        return new DateUtil(getYear(), getMonth(), getDay()).getUnixTimestamp();
    }

    public String getZeroTimeYyyyMMdd_HHmmssDate() {
        return new DateUtil(getYear(), getMonth(), getDay()).getYyyyMMdd_HHmmssDate();
    }

    public long getZeroTime1() {
        return new DateUtil(getYear(), getMonth(), getDay()).getTimestamp();
    }

    public static long getFirstDayMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, 0);
        calendar.set(5, 1);
        return calendar.getTimeInMillis();
    }

    public static DateUtil getFirstDayOfMonth(DateUtil dateUtil) {
        return new DateUtil(getFirstDayMonth(dateUtil.toDate()), false);
    }

    public static DateUtil getLastDayOfMonth(DateUtil dateUtil) {
        return new DateUtil(getLastDayMonth(dateUtil.toDate()), false);
    }

    public static long getLastDayMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, 0);
        calendar.set(5, calendar.getActualMaximum(5));
        return calendar.getTimeInMillis();
    }

    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(5);
    }

    public int getTodayMin() {
        return Math.round((this.f538c.getTimeInMillis() - getZeroTime1()) / 60000) + 1;
    }

    public int getTodayMinNoPlus() {
        return Math.round((this.f538c.getTimeInMillis() - getZeroTime1()) / 60000);
    }

    public static boolean isSameDay(Date date, Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar.get(1) == calendar2.get(1) && calendar.get(2) == calendar2.get(2) && calendar.get(5) == calendar2.get(5);
    }

    public boolean isSameDay(long j, boolean z) {
        DateUtil dateUtil = new DateUtil(j, z);
        return dateUtil.getYear() == getYear() && dateUtil.getMonth() == getMonth() && dateUtil.getDay() == getDay();
    }

    public static boolean isSameMonth(Date date, Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar.get(1) == calendar2.get(1) && calendar.get(2) == calendar2.get(2);
    }

    public static long getPreOrNextTimeByDay(long j) {
        return System.currentTimeMillis() - (j * 86400000);
    }

    public static long getGMTDate(long j) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            String str = simpleDateFormat.format(new Date(1000 * j));
            return new DateUtil(Integer.parseInt(str.substring(0, 4)), Integer.parseInt(str.substring(5, 7)), Integer.parseInt(str.substring(8, 10)), Integer.parseInt(str.substring(11, 13)), Integer.parseInt(str.substring(14, 16)), 0).getUnixTimestamp();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            DateUtil dateUtil = new DateUtil(j, true);
            dateUtil.setHour(0);
            dateUtil.setMinute(0);
            dateUtil.setSecond(0);
            return dateUtil.getUnixTimestamp();
        }
    }

    public static String dayMinToStrShow(int i) {
        String str;
        String str2;
        String str3;
        int i2 = i / 60;
        int i3 = i % 60;
        if (i2 > 0) {
            if (i2 < 10) {
                str2 = "0" + i2;
            } else {
                str2 = i2 + "";
            }
            if (i3 < 10) {
                str3 = "0" + i3;
            } else {
                str3 = i3 + "";
            }
            return str2 + "h" + str3 + "min";
        }
        if (i3 < 10) {
            str = "0" + i3;
        } else {
            str = i3 + "";
        }
        return str + "min";
    }

    public static DateUtil valueOf(String str) {
        Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}");
        try {
            if (Pattern.compile("[0-9]{2}-[0-9]{2}").matcher(str).matches()) {
                return parse(str, DateFormater.MMdd);
            }
            if (Pattern.compile("[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}").matcher(str).matches()) {
                return parse(str, DateFormater.MMdd_HHmm);
            }
            if (Pattern.compile("[0-9]{4}-[0-9]{2}").matcher(str).matches()) {
                return parse(str, DateFormater.yyyyMM);
            }
            if (Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}").matcher(str).matches()) {
                return parse(str, DateFormater.yyyyMMdd);
            }
            if (Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}").matcher(str).matches()) {
                return parse(str, DateFormater.yyyyMMdd_HHmm);
            }
            if (Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}").matcher(str).matches()) {
                return parse(str, DateFormater.yyyyMMdd_HHmmss);
            }
            if (Pattern.compile("[0-9]{2}:[0-9]{2}").matcher(str).matches()) {
                return parse(str, DateFormater.HHmm);
            }
            if (Pattern.compile("[0-9]{2}:[0-9]{2}:[0-9]{2}").matcher(str).matches()) {
                return parse(str, DateFormater.HHmmss);
            }
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static DateUtil parse(String str, DateFormater dateFormater) throws ParseException {
        Date date;
        int iOrdinal = dateFormater.ordinal();
        if (iOrdinal == 10) {
            date = dFyyyyMMdd.get().parse(str);
        } else if (iOrdinal != 12) {
            switch (iOrdinal) {
                case 0:
                    date = dFMMdd.get().parse(str);
                    break;
                case 1:
                    date = dFMMdd_HHmm.get().parse(str);
                    break;
                case 2:
                    date = dFyyyyMM.get().parse(str);
                    break;
                case 3:
                    date = dFSyyyyMMdd.get().parse(str);
                    break;
                case 4:
                    date = dFyyyyMMdd_HHmm.get().parse(str);
                    break;
                case 5:
                    date = dFyyyyMMdd_HHmmss.get().parse(str);
                    break;
                case 6:
                    date = dFHHmm.get().parse(str);
                    break;
                case 7:
                    date = dFHHmmss.get().parse(str);
                    break;
                default:
                    date = null;
                    break;
            }
        } else {
            date = dfddMMyyy.get().parse(str);
        }
        return new DateUtil(date);
    }

    public DateUtil() {
        this.f538c = Calendar.getInstance();
    }

    public DateUtil(TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance();
        this.f538c = calendar;
        calendar.setTimeZone(timeZone);
    }

    public DateUtil(long j, boolean z) {
        Calendar calendar = Calendar.getInstance();
        this.f538c = calendar;
        if (z) {
            calendar.setTimeInMillis(j * 1000);
        } else {
            calendar.setTimeInMillis(j);
        }
    }

    public DateUtil(long j, boolean z, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance();
        this.f538c = calendar;
        calendar.setTimeZone(timeZone);
        if (z) {
            this.f538c.setTimeInMillis(j * 1000);
        } else {
            this.f538c.setTimeInMillis(j);
        }
    }

    public DateUtil(Date date) {
        Calendar calendar = Calendar.getInstance();
        this.f538c = calendar;
        calendar.setTime(date);
    }

    public DateUtil(int i, int i2, int i3) {
        this(i, i2, i3, 0, 0, 0);
    }

    public boolean futureDate() {
        return getUnixTimestamp() > new DateUtil().getUnixTimestamp();
    }

    public DateUtil(int i, int i2, int i3, int i4, int i5) {
        this(i, i2, i3, i4, i5, 0);
    }

    public DateUtil(int i, int i2, int i3, int i4, int i5, int i6) {
        Calendar calendar = Calendar.getInstance();
        this.f538c = calendar;
        calendar.set(1, i);
        this.f538c.set(2, i2 - 1);
        this.f538c.set(5, i3);
        this.f538c.set(11, i4);
        this.f538c.set(12, i5);
        this.f538c.set(13, i6);
    }

    public DateUtil(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        this.f538c = calendar;
        calendar.set(11, i);
        this.f538c.set(12, i2);
    }

    public boolean isToday() {
        DateUtil dateUtil = new DateUtil();
        return getYear() == dateUtil.getYear() && getMonth() == dateUtil.getMonth() && getDay() == dateUtil.getDay();
    }

    public boolean isYesterday() {
        DateUtil dateUtil = new DateUtil();
        dateUtil.addDay(-1);
        return getYear() == dateUtil.getYear() && getMonth() == dateUtil.getMonth() && getDay() == dateUtil.getDay();
    }

    public boolean isSameWeek(int i) {
        return i == new DateUtil(new Date()).getWeekOfYear();
    }

    public boolean isSameMonth(int i, int i2) {
        return i == getMonth() && getYear() == i2;
    }

    public int daysBetweenMe(DateUtil dateUtil) {
        return (int) (Math.abs(getZeroTime() - dateUtil.getZeroTime()) / 86400);
    }

    public static String dayMinToStr(int i) {
        String str;
        String str2;
        int i2 = i / 60;
        int i3 = i % 60;
        if (i2 < 10) {
            str = "0" + i2;
        } else {
            str = i2 + "";
        }
        if (i3 < 10) {
            str2 = "0" + i3;
        } else {
            str2 = i3 + "";
        }
        return str + ":" + str2;
    }

    public static String dayMinToStrChina(int i) {
        String str;
        String str2;
        String str3;
        int i2 = i / 60;
        int i3 = i % 60;
        if (i2 == 0) {
            if (i3 < 10) {
                str3 = "0" + i3;
            } else {
                str3 = i3 + "";
            }
            return str3 + "分";
        }
        if (i2 < 10) {
            str = "0" + i2;
        } else {
            str = i2 + "";
        }
        if (i3 < 10) {
            str2 = "0" + i3;
        } else {
            str2 = i3 + "";
        }
        return str + "时" + str2 + "分";
    }

    public static String formatMillis(long j) {
        long j2 = j / 60000;
        long j3 = (j % 60000) / 1000;
        return (j2 < 10 ? "0" : "") + j2 + (j3 < 10 ? ":0" : ":") + j3;
    }

    public static String formatMillisToMinutesSecondsTenths(long j) {
        long j2 = j / 60000;
        long j3 = (j % 60000) / 1000;
        long j4 = (j % 1000) / 100;
        return (j2 < 10 ? "0" : "") + j2 + (j3 < 10 ? ":0" : ":") + j3;
    }

    public static String secondToStr(int i) {
        String str;
        String str2;
        String str3;
        int i2 = i / MMKV.ExpireInHour;
        int i3 = (i % MMKV.ExpireInHour) / 60;
        int i4 = i % 60;
        if (i2 < 10) {
            str = "0" + i2;
        } else {
            str = i2 + "";
        }
        if (i3 < 10) {
            str2 = "0" + i3;
        } else {
            str2 = i3 + "";
        }
        if (i4 < 10) {
            str3 = "0" + i4;
        } else {
            str3 = i4 + "";
        }
        return i2 == 0 ? str2 + ":" + str3 : str + ":" + str2 + ":" + str3;
    }

    public static String dayMinToStrSymbol(int i) {
        String str;
        String str2;
        int i2 = i / 60;
        int i3 = i % 60;
        if (i2 < 10) {
            str = "0" + i2;
        } else {
            str = i2 + "";
        }
        if (i3 < 10) {
            str2 = "0" + i3;
        } else {
            str2 = i3 + "";
        }
        return str + "'" + str2 + "''";
    }

    public static String minsToHHmmdd(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = i / MMKV.ExpireInHour;
        stringBuffer.append((i2 < 10 ? new StringBuilder("0") : new StringBuilder("")).append(i2).append(":").toString());
        int i3 = i % MMKV.ExpireInHour;
        int i4 = i3 / 60;
        stringBuffer.append((i4 < 10 ? new StringBuilder("0") : new StringBuilder("")).append(i4).append(":").toString());
        int i5 = i3 % 60;
        stringBuffer.append((i5 < 10 ? new StringBuilder("0") : new StringBuilder("")).append(i5).toString());
        return stringBuffer.toString();
    }

    public Date toDate() {
        return this.f538c.getTime();
    }

    public String toFormatString(DateFormater dateFormater) {
        Date date = toDate();
        switch (dateFormater) {
            case MMdd:
                return dFMMdd.get().format(date);
            case MMdd_HHmm:
                return dFMMdd_HHmm.get().format(date);
            case yyyyMM:
                return dFyyyyMM.get().format(date);
            case yyyyMMdd:
            case dFyyyyMMdd:
                return dFyyyyMMdd.get().format(date);
            case yyyyMMdd_HHmm:
                return dFyyyyMMdd_HHmm.get().format(date);
            case yyyyMMdd_HHmmss:
                return dFyyyyMMdd_HHmmss.get().format(date);
            case HHmm:
                return dFHHmm.get().format(date);
            case HHmmss:
                return dFHHmmss.get().format(date);
            case yyyyMMddHHmm:
            case dFHHmm:
            default:
                return "Unknown";
            case SyyyyMMdd:
                return dFSyyyyMMdd.get().format(date);
            case dfddMMyyy:
                return dfddMMyyy.get().format(date);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Date String2Date(String str, String str2) {
        char c;
        try {
            int iHashCode = str.hashCode();
            if (iHashCode != -1172057030) {
                if (iHashCode != -159776256) {
                    c = (iHashCode == 1333195168 && str.equals("yyyy-MM-dd HH:mm:ss")) ? (char) 0 : (char) 65535;
                } else if (str.equals("yyyy-MM-dd")) {
                    c = 2;
                }
            } else if (str.equals("yyyy-MM-dd HH:mm")) {
                c = 1;
            }
            if (c == 0) {
                return yyyyMMdd_HHmmssF.parse(str2);
            }
            if (c == 1) {
                return dFyyyyMMddmmF.parse(str2);
            }
            if (c != 2) {
                return null;
            }
            return dFyyyyMMddF.parse(str2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getMMddDate() {
        return toFormatString(DateFormater.MMdd);
    }

    public String getMMdd_HHmmDate() {
        return toFormatString(DateFormater.MMdd_HHmm);
    }

    public String getY_M_D() {
        return toFormatString(DateFormater.dFyyyyMMdd);
    }

    public String getD_M_Y() {
        return toFormatString(DateFormater.dfddMMyyy);
    }

    public String getY_M_D_H_M_S() {
        return toFormatString(DateFormater.yyyyMMdd_HHmmss);
    }

    public String getY_M_D_H_M() {
        return toFormatString(DateFormater.yyyyMMdd_HHmm);
    }

    public String getYyyyMMDate() {
        return toFormatString(DateFormater.yyyyMM);
    }

    public String getYyyyMMddDate() {
        return toFormatString(DateFormater.yyyyMMdd);
    }

    public String getYyyyMMdd_HHmmDate() {
        return toFormatString(DateFormater.yyyyMMdd_HHmm);
    }

    public String getYyyyMMdd_HHmmssDate() {
        return toFormatString(DateFormater.yyyyMMdd_HHmmss);
    }

    public String getHHmmDate() {
        return toFormatString(DateFormater.HHmm);
    }

    public String getHHmmssDate() {
        return toFormatString(DateFormater.HHmmss);
    }

    public String getSyyyyMMddDate() {
        return toFormatString(DateFormater.SyyyyMMdd);
    }

    public String getyyyyMMddDate() {
        return toFormatString(DateFormater.yyyyMMdd);
    }

    public int getYear() {
        return this.f538c.get(1);
    }

    public void setYear(int i) {
        this.f538c.set(1, i);
    }

    public int getMonth() {
        return this.f538c.get(2) + 1;
    }

    public void setMonth(int i) {
        this.f538c.set(2, i - 1);
    }

    public int getDay() {
        return this.f538c.get(5);
    }

    public int getDaysOfThisMonth() {
        return this.f538c.get(5);
    }

    public void setDay(int i) {
        this.f538c.set(5, i);
    }

    public void addDay(int i) {
        this.f538c.add(5, i);
    }

    public void addMonth(int i) {
        this.f538c.add(2, i);
    }

    public int getHour() {
        return this.f538c.get(11);
    }

    public void setHour(int i) {
        this.f538c.set(11, i);
    }

    public int getMinute() {
        return this.f538c.get(12);
    }

    public void setMinute(int i) {
        this.f538c.set(12, i);
    }

    public int getSecond() {
        return this.f538c.get(13);
    }

    public void setSecond(int i) {
        this.f538c.set(13, i);
    }

    public long getTimestamp() {
        return this.f538c.getTimeInMillis();
    }

    public void setTimestamp(long j) {
        this.f538c.setTimeInMillis(j);
    }

    public long getUnixTimestamp() {
        return this.f538c.getTimeInMillis() / 1000;
    }

    public void setUnixTimestamp(long j) {
        this.f538c.setTimeInMillis(j * 1000);
    }

    public int getDayOfWeek() {
        return this.f538c.get(7);
    }

    public int getWeekOfYear() {
        return this.f538c.get(3);
    }

    public int getWeekOfMonth() {
        return this.f538c.get(4);
    }

    public String getMonDate() {
        int dayOfWeek = getDayOfWeek();
        Calendar calendar = this.f538c;
        calendar.add(5, calendar.getFirstDayOfWeek() - dayOfWeek);
        return new SimpleDateFormat("yyyyMMdd").format(this.f538c.getTime());
    }

    public String toString() {
        return getYyyyMMdd_HHmmssDate();
    }

    public static String getTime(long j) {
        long jCurrentTimeMillis = (System.currentTimeMillis() - j) / 86400000;
        DateUtil dateUtil = new DateUtil(j, false);
        if (jCurrentTimeMillis > 0) {
            return dateUtil.getYyyyMMddDate();
        }
        return dateUtil.getHHmmDate();
    }

    public static long getSunDayTimeFromWeek() {
        return Calendar.getInstance().getTime().getTime() - ((r0.get(7) - 1) * 86400000);
    }

    public static DateUtil firstDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(1);
        calendar.setMinimalDaysInFirstWeek(7);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(4, 0);
        calendar.set(7, 1);
        return new DateUtil(calendar.getTime());
    }

    public static DateUtil firstDayOfWeekRing(DateUtil dateUtil) {
        Date date = new Date(dateUtil.getTimestamp());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(2);
        int i = calendar.get(7);
        if (i == 1) {
            i = 8;
        }
        calendar.add(5, -(i - 2));
        return new DateUtil(calendar.getTime());
    }

    public static LocalDate getWeekStartDateFromTimestamp(long j) {
        LocalDate localDate = Build.VERSION.SDK_INT >= 26 ? Instant.ofEpochMilli(j).atZone(ZoneId.systemDefault()).toLocalDate() : null;
        if (Build.VERSION.SDK_INT >= 26) {
            return localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        }
        return null;
    }

    public static int getDayOfMonth() {
        Calendar.getInstance().setTime(new Date());
        return r0.get(5) - 1;
    }

    public static Date getDateByWeekMagin(int i) {
        return new Date(getSunDayTimeFromWeek() + (i * 86400000));
    }

    public static int differentDaysByMillisecond(Date date, Date date2) {
        return (int) ((date2.getTime() - date.getTime()) / 86400000);
    }

    public static long dateStr2Stamp(String str) {
        try {
            return Long.parseLong(String.valueOf(new SimpleDateFormat("yyyyMMdd").parse(str).getTime()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static long dateY_M_D2Stamp(String str) {
        try {
            return Long.parseLong(String.valueOf(new SimpleDateFormat("yyyy-MM-dd").parse(str).getTime()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static String getMarginMin(long j, long j2) {
        return ((j - j2) / 60) + "";
    }

    public static int getWhatDay(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j * 1000);
        int i = calendar.get(7) != 5 ? calendar.get(7) != 4 ? calendar.get(7) != 3 ? calendar.get(7) != 2 ? calendar.get(7) != 1 ? calendar.get(7) == 7 ? 6 : 0 : 0 : 1 : 2 : 3 : 4;
        if (calendar.get(7) == 6) {
            return 5;
        }
        return i;
    }

    public static int getWhatDayRing(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j * 1000);
        int i = calendar.get(7) == 7 ? 5 : 0;
        if (calendar.get(7) == 1) {
            i = 6;
        }
        int i2 = calendar.get(7) != 5 ? calendar.get(7) != 4 ? calendar.get(7) != 3 ? calendar.get(7) != 2 ? i : 0 : 1 : 2 : 3;
        if (calendar.get(7) == 6) {
            return 4;
        }
        return i2;
    }

    public static int getAgeByBirthday(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (calendar.before(date)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int i = calendar.get(1);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar.get(5);
        calendar.setTime(date);
        int i4 = calendar.get(1);
        int i5 = calendar.get(2) + 1;
        int i6 = i - i4;
        return i2 <= i5 ? (i2 != i5 || i3 < calendar.get(5)) ? i6 - 1 : i6 : i6;
    }

    public String fetchToDay(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return i + "";
    }

    public static Date convertTimezone(Date date, String str) {
        return convertTimezone(date, TimeZone.getTimeZone(str));
    }

    public static Date convertTimezone(Date date, String str, String str2) {
        return convertTimezone(date, TimeZone.getTimeZone(str), TimeZone.getTimeZone(str2));
    }

    public static Date convertTimezone(Date date, TimeZone timeZone) {
        return convertTimezone(date, TimeZone.getDefault(), timeZone);
    }

    public static Date convertTimezone(Date date, TimeZone timeZone, TimeZone timeZone2) {
        Calendar calendar = Calendar.getInstance();
        long time = date.getTime();
        calendar.setTimeZone(timeZone);
        calendar.setTimeInMillis(time);
        int i = calendar.get(15);
        calendar.setTimeZone(timeZone2);
        calendar.setTimeInMillis(time);
        return new Date((time + (calendar.get(15) + calendar.get(16))) - i);
    }
}
