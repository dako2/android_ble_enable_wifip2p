package org.checkerframework.checker.i18nformatter;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Locale;
import org.checkerframework.checker.i18nformatter.qual.I18nChecksFormat;
import org.checkerframework.checker.i18nformatter.qual.I18nConversionCategory;
import org.checkerframework.checker.i18nformatter.qual.I18nValidFormat;

/* loaded from: classes3.dex */
public class I18nFormatUtil {
    public static void tryFormatSatisfiability(String str) throws IllegalFormatException {
        MessageFormat.format(str, null);
    }

    public static I18nConversionCategory[] formatParameterCategories(String str) throws IllegalFormatException, NumberFormatException {
        tryFormatSatisfiability(str);
        I18nConversion[] i18nConversionArr = MessageFormatParser.parse(str);
        HashMap map = new HashMap();
        int iMax = -1;
        for (I18nConversion i18nConversion : i18nConversionArr) {
            int i = i18nConversion.index;
            map.put(Integer.valueOf(i), I18nConversionCategory.intersect(i18nConversion.category, map.containsKey(Integer.valueOf(i)) ? (I18nConversionCategory) map.get(Integer.valueOf(i)) : I18nConversionCategory.UNUSED));
            iMax = Math.max(iMax, i);
        }
        I18nConversionCategory[] i18nConversionCategoryArr = new I18nConversionCategory[iMax + 1];
        for (int i2 = 0; i2 <= iMax; i2++) {
            i18nConversionCategoryArr[i2] = map.containsKey(Integer.valueOf(i2)) ? (I18nConversionCategory) map.get(Integer.valueOf(i2)) : I18nConversionCategory.UNUSED;
        }
        return i18nConversionCategoryArr;
    }

    @I18nChecksFormat
    public static boolean hasFormat(String str, I18nConversionCategory... i18nConversionCategoryArr) throws IllegalFormatException, NumberFormatException {
        I18nConversionCategory[] parameterCategories = formatParameterCategories(str);
        if (parameterCategories.length != i18nConversionCategoryArr.length) {
            return false;
        }
        for (int i = 0; i < i18nConversionCategoryArr.length; i++) {
            if (!I18nConversionCategory.isSubsetOf(i18nConversionCategoryArr[i], parameterCategories[i])) {
                return false;
            }
        }
        return true;
    }

    @I18nValidFormat
    public static boolean isFormat(String str) {
        try {
            formatParameterCategories(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static class I18nConversion {
        public I18nConversionCategory category;
        public int index;

        public I18nConversion(int i, I18nConversionCategory i18nConversionCategory) {
            this.index = i;
            this.category = i18nConversionCategory;
        }

        public String toString() {
            return this.category.toString() + "(index: " + this.index + ")";
        }
    }

    private static class MessageFormatParser {
        private static final int MODIFIER_CURRENCY = 1;
        private static final int MODIFIER_DEFAULT = 0;
        private static final int MODIFIER_INTEGER = 3;
        private static final int MODIFIER_PERCENT = 2;
        private static final int SEG_INDEX = 1;
        private static final int SEG_MODIFIER = 3;
        private static final int SEG_RAW = 0;
        private static final int SEG_TYPE = 2;
        private static final int TYPE_CHOICE = 4;
        private static final int TYPE_DATE = 2;
        private static final int TYPE_NULL = 0;
        private static final int TYPE_NUMBER = 1;
        private static final int TYPE_TIME = 3;
        private static List<Integer> argumentIndices;
        private static List<I18nConversionCategory> categories;
        private static Locale locale;
        public static int maxOffset;
        private static int numFormat;
        private static final String[] TYPE_KEYWORDS = {"", "number", "date", "time", "choice"};
        private static final String[] NUMBER_MODIFIER_KEYWORDS = {"", "currency", "percent", TypedValues.Custom.S_INT};
        private static final String[] DATE_TIME_MODIFIER_KEYWORDS = {"", "short", "medium", "long", "full"};

        private MessageFormatParser() {
        }

        public static I18nConversion[] parse(String str) throws NumberFormatException {
            categories = new ArrayList();
            argumentIndices = new ArrayList();
            locale = Locale.getDefault(Locale.Category.FORMAT);
            applyPattern(str);
            I18nConversion[] i18nConversionArr = new I18nConversion[numFormat];
            for (int i = 0; i < numFormat; i++) {
                i18nConversionArr[i] = new I18nConversion(argumentIndices.get(i).intValue(), categories.get(i));
            }
            return i18nConversionArr;
        }

        private static void applyPattern(String str) throws NumberFormatException {
            StringBuilder[] sbArr = new StringBuilder[4];
            sbArr[0] = new StringBuilder();
            numFormat = 0;
            maxOffset = -1;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            boolean z = false;
            while (i < str.length()) {
                char cCharAt = str.charAt(i);
                if (i3 == 0) {
                    if (cCharAt == '\'') {
                        int i4 = i + 1;
                        if (i4 >= str.length() || str.charAt(i4) != '\'') {
                            z = !z;
                        } else {
                            sbArr[i3].append(cCharAt);
                            i = i4;
                        }
                    } else if (cCharAt == '{' && !z) {
                        if (sbArr[1] == null) {
                            sbArr[1] = new StringBuilder();
                        }
                        i3 = 1;
                    } else {
                        sbArr[i3].append(cCharAt);
                    }
                } else if (z) {
                    sbArr[i3].append(cCharAt);
                    if (cCharAt == '\'') {
                        z = false;
                    }
                } else if (cCharAt != ' ') {
                    if (cCharAt == '\'') {
                        sbArr[i3].append(cCharAt);
                        z = true;
                    } else if (cCharAt != ',') {
                        if (cCharAt == '{') {
                            i2++;
                            sbArr[i3].append(cCharAt);
                        } else if (cCharAt != '}') {
                            sbArr[i3].append(cCharAt);
                        } else if (i2 == 0) {
                            makeFormat(i, numFormat, sbArr);
                            numFormat++;
                            sbArr[1] = null;
                            sbArr[2] = null;
                            sbArr[3] = null;
                            i3 = 0;
                        } else {
                            i2--;
                            sbArr[i3].append(cCharAt);
                        }
                    } else if (i3 < 3) {
                        i3++;
                        if (sbArr[i3] == null) {
                            sbArr[i3] = new StringBuilder();
                        }
                    } else {
                        sbArr[i3].append(cCharAt);
                    }
                } else if (i3 != 2 || sbArr[2].length() > 0) {
                    sbArr[i3].append(cCharAt);
                }
                i++;
            }
            if (i2 != 0 || i3 == 0) {
                return;
            }
            maxOffset = -1;
            throw new IllegalArgumentException("Unmatched braces in the pattern");
        }

        private static void makeFormat(int i, int i2, StringBuilder[] sbArr) throws NumberFormatException {
            I18nConversionCategory i18nConversionCategory;
            int iFindKeyword;
            String[] strArr = new String[sbArr.length];
            for (int i3 = 0; i3 < sbArr.length; i3++) {
                StringBuilder sb = sbArr[i3];
                strArr[i3] = sb != null ? sb.toString() : "";
            }
            try {
                int i4 = Integer.parseInt(strArr[1]);
                if (i4 < 0) {
                    throw new IllegalArgumentException("negative argument number: " + i4);
                }
                int i5 = maxOffset;
                maxOffset = i2;
                argumentIndices.add(Integer.valueOf(i4));
                if (strArr[2].length() == 0 || (iFindKeyword = findKeyword(strArr[2], TYPE_KEYWORDS)) == 0) {
                    i18nConversionCategory = I18nConversionCategory.GENERAL;
                } else if (iFindKeyword == 1) {
                    int iFindKeyword2 = findKeyword(strArr[3], NUMBER_MODIFIER_KEYWORDS);
                    if (iFindKeyword2 != 0 && iFindKeyword2 != 1 && iFindKeyword2 != 2 && iFindKeyword2 != 3) {
                        try {
                            new DecimalFormat(strArr[3], DecimalFormatSymbols.getInstance(locale));
                        } catch (IllegalArgumentException e) {
                            maxOffset = i5;
                            throw e;
                        }
                    }
                    i18nConversionCategory = I18nConversionCategory.NUMBER;
                } else if (iFindKeyword == 2 || iFindKeyword == 3) {
                    String str = strArr[3];
                    String[] strArr2 = DATE_TIME_MODIFIER_KEYWORDS;
                    int iFindKeyword3 = findKeyword(str, strArr2);
                    if (iFindKeyword3 < 0 || iFindKeyword3 >= strArr2.length) {
                        try {
                            new SimpleDateFormat(strArr[3], locale);
                        } catch (IllegalArgumentException e2) {
                            maxOffset = i5;
                            throw e2;
                        }
                    }
                    i18nConversionCategory = I18nConversionCategory.DATE;
                } else if (iFindKeyword == 4) {
                    if (strArr[3].length() == 0) {
                        throw new IllegalArgumentException("Choice Pattern requires Subformat Pattern: " + strArr[3]);
                    }
                    try {
                        new ChoiceFormat(strArr[3]);
                        i18nConversionCategory = I18nConversionCategory.NUMBER;
                    } catch (Exception e3) {
                        maxOffset = i5;
                        throw new IllegalArgumentException("Choice Pattern incorrect: " + strArr[3], e3);
                    }
                } else {
                    maxOffset = i5;
                    throw new IllegalArgumentException("unknown format type: " + strArr[2]);
                }
                categories.add(i18nConversionCategory);
            } catch (NumberFormatException e4) {
                throw new IllegalArgumentException("can't parse argument number: " + strArr[1], e4);
            }
        }

        private static final int findKeyword(String str, String[] strArr) {
            for (int i = 0; i < strArr.length; i++) {
                if (str.equals(strArr[i])) {
                    return i;
                }
            }
            String lowerCase = str.trim().toLowerCase(Locale.ROOT);
            if (lowerCase == str) {
                return -1;
            }
            for (int i2 = 0; i2 < strArr.length; i2++) {
                if (lowerCase.equals(strArr[i2])) {
                    return i2;
                }
            }
            return -1;
        }
    }
}
