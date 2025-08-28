package androidx.constraintlayout.widget;

import android.util.Log;
import androidx.constraintlayout.core.Metrics;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.TokenParser;
import java.text.DecimalFormat;

/* loaded from: classes.dex */
public class ConstraintLayoutStatistics {
    public static final int DURATION_OF_CHILD_MEASURES = 5;
    public static final int DURATION_OF_LAYOUT = 7;
    public static final int DURATION_OF_MEASURES = 6;
    public static final int NUMBER_OF_CHILD_MEASURES = 4;
    public static final int NUMBER_OF_CHILD_VIEWS = 3;
    public static final int NUMBER_OF_EQUATIONS = 9;
    public static final int NUMBER_OF_LAYOUTS = 1;
    public static final int NUMBER_OF_ON_MEASURES = 2;
    public static final int NUMBER_OF_SIMPLE_EQUATIONS = 10;
    public static final int NUMBER_OF_VARIABLES = 8;
    ConstraintLayout mConstraintLayout;
    private final Metrics mMetrics;
    private static int MAX_WORD = 25;
    private static final String WORD_PAD = new String(new char[MAX_WORD]).replace((char) 0, TokenParser.f390SP);

    public ConstraintLayoutStatistics(ConstraintLayout constraintLayout) {
        this.mMetrics = new Metrics();
        attach(constraintLayout);
    }

    public ConstraintLayoutStatistics(ConstraintLayoutStatistics constraintLayoutStatistics) {
        Metrics metrics = new Metrics();
        this.mMetrics = metrics;
        metrics.copy(constraintLayoutStatistics.mMetrics);
    }

    public void attach(ConstraintLayout constraintLayout) {
        constraintLayout.fillMetrics(this.mMetrics);
        this.mConstraintLayout = constraintLayout;
    }

    public void detach() {
        ConstraintLayout constraintLayout = this.mConstraintLayout;
        if (constraintLayout != null) {
            constraintLayout.fillMetrics(null);
        }
    }

    public void reset() {
        this.mMetrics.reset();
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public ConstraintLayoutStatistics m670clone() {
        return new ConstraintLayoutStatistics(this);
    }

    private String fmt(DecimalFormat decimalFormat, float f, int i) {
        String str = new String(new char[i]).replace((char) 0, TokenParser.f390SP) + decimalFormat.format(f);
        return str.substring(str.length() - i);
    }

    public void logSummary(String str) {
        log(str);
    }

    private void log(String str) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        Log.v(str, "CL Perf: --------  Performance .(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")  ------ ");
        DecimalFormat decimalFormat = new DecimalFormat("###.000");
        Log.v(str, log(decimalFormat, 5));
        Log.v(str, log(decimalFormat, 7));
        Log.v(str, log(decimalFormat, 6));
        Log.v(str, log(1));
        Log.v(str, log(2));
        Log.v(str, log(3));
        Log.v(str, log(4));
        Log.v(str, log(8));
        Log.v(str, log(9));
        Log.v(str, log(10));
    }

    private String log(DecimalFormat decimalFormat, int i) {
        String strFmt = fmt(decimalFormat, getValue(i) * 1.0E-6f, 7);
        String str = WORD_PAD + geName(i);
        return "CL Perf: " + (str.substring(str.length() - MAX_WORD) + " = ") + strFmt;
    }

    private String log(int i) {
        String string = Long.toString(getValue(i));
        String str = WORD_PAD + geName(i);
        return "CL Perf: " + (str.substring(str.length() - MAX_WORD) + " = ") + string;
    }

    private String compare(DecimalFormat decimalFormat, ConstraintLayoutStatistics constraintLayoutStatistics, int i) {
        String str = fmt(decimalFormat, getValue(i) * 1.0E-6f, 7) + " -> " + fmt(decimalFormat, constraintLayoutStatistics.getValue(i) * 1.0E-6f, 7) + "ms";
        String str2 = WORD_PAD + geName(i);
        return "CL Perf: " + (str2.substring(str2.length() - MAX_WORD) + " = ") + str;
    }

    private String compare(ConstraintLayoutStatistics constraintLayoutStatistics, int i) {
        String str = getValue(i) + " -> " + constraintLayoutStatistics.getValue(i);
        String str2 = WORD_PAD + geName(i);
        return "CL Perf: " + (str2.substring(str2.length() - MAX_WORD) + " = ") + str;
    }

    public void logSummary(String str, ConstraintLayoutStatistics constraintLayoutStatistics) {
        if (constraintLayoutStatistics == null) {
            log(str);
            return;
        }
        DecimalFormat decimalFormat = new DecimalFormat("###.000");
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        Log.v(str, "CL Perf: -=  Performance .(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")  =- ");
        Log.v(str, compare(decimalFormat, constraintLayoutStatistics, 5));
        Log.v(str, compare(decimalFormat, constraintLayoutStatistics, 7));
        Log.v(str, compare(decimalFormat, constraintLayoutStatistics, 6));
        Log.v(str, compare(constraintLayoutStatistics, 1));
        Log.v(str, compare(constraintLayoutStatistics, 2));
        Log.v(str, compare(constraintLayoutStatistics, 3));
        Log.v(str, compare(constraintLayoutStatistics, 4));
        Log.v(str, compare(constraintLayoutStatistics, 8));
        Log.v(str, compare(constraintLayoutStatistics, 9));
        Log.v(str, compare(constraintLayoutStatistics, 10));
    }

    public long getValue(int i) {
        switch (i) {
            case 1:
                return this.mMetrics.mNumberOfLayouts;
            case 2:
                return this.mMetrics.mMeasureCalls;
            case 3:
                return this.mMetrics.mChildCount;
            case 4:
                return this.mMetrics.mNumberOfMeasures;
            case 5:
                return this.mMetrics.measuresWidgetsDuration;
            case 6:
                return this.mMetrics.mMeasureDuration;
            case 7:
                return this.mMetrics.measuresLayoutDuration;
            case 8:
                return this.mMetrics.mVariables;
            case 9:
                return this.mMetrics.mEquations;
            case 10:
                return this.mMetrics.mSimpleEquations;
            default:
                return 0L;
        }
    }

    String geName(int i) {
        switch (i) {
            case 1:
                return "NumberOfLayouts";
            case 2:
                return "MeasureCalls";
            case 3:
                return "ChildCount";
            case 4:
                return "ChildrenMeasures";
            case 5:
                return "MeasuresWidgetsDuration ";
            case 6:
                return "MeasureDuration";
            case 7:
                return "MeasuresLayoutDuration";
            case 8:
                return "SolverVariables";
            case 9:
                return "SolverEquations";
            case 10:
                return "SimpleEquations";
            default:
                return "";
        }
    }
}
