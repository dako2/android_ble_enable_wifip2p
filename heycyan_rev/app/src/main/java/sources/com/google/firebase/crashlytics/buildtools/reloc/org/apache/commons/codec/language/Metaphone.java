package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.language;

import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.EncoderException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.StringEncoder;
import java.util.Locale;

/* loaded from: classes2.dex */
public class Metaphone implements StringEncoder {
    private static final String FRONTV = "EIY";
    private static final String VARSON = "CSPTG";
    private static final String VOWELS = "AEIOU";
    private int maxCodeLen = 4;

    private boolean isLastChar(int i, int i2) {
        return i2 + 1 == i;
    }

    /* JADX WARN: Removed duplicated region for block: B:131:0x020b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String metaphone(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return "";
        }
        if (length == 1) {
            return str.toUpperCase(Locale.ENGLISH);
        }
        char[] charArray = str.toUpperCase(Locale.ENGLISH).toCharArray();
        StringBuilder sb = new StringBuilder(40);
        StringBuilder sb2 = new StringBuilder(10);
        int i = 0;
        char c = charArray[0];
        if (c != 'A') {
            if (c == 'G' || c == 'K' || c == 'P') {
                if (charArray[1] == 'N') {
                    sb.append(charArray, 1, charArray.length - 1);
                } else {
                    sb.append(charArray);
                }
            } else if (c == 'W') {
                char c2 = charArray[1];
                if (c2 == 'R') {
                    sb.append(charArray, 1, charArray.length - 1);
                } else if (c2 == 'H') {
                    sb.append(charArray, 1, charArray.length - 1);
                    sb.setCharAt(0, 'W');
                } else {
                    sb.append(charArray);
                }
            } else if (c == 'X') {
                charArray[0] = 'S';
                sb.append(charArray);
            } else {
                sb.append(charArray);
            }
        } else if (charArray[1] == 'E') {
            sb.append(charArray, 1, charArray.length - 1);
        } else {
            sb.append(charArray);
        }
        int length2 = sb.length();
        while (sb2.length() < getMaxCodeLen() && i < length2) {
            char cCharAt = sb.charAt(i);
            if (cCharAt == 'C' || !isPreviousChar(sb, i, cCharAt)) {
                switch (cCharAt) {
                    case 'A':
                    case 'E':
                    case EMachine.EM_SVX /* 73 */:
                    case EMachine.EM_ZSP /* 79 */:
                    case EMachine.EM_D10V /* 85 */:
                        if (i == 0) {
                            sb2.append(cCharAt);
                            break;
                        }
                        break;
                    case 'B':
                        if (!isPreviousChar(sb, i, 'M') || !isLastChar(length2, i)) {
                            sb2.append(cCharAt);
                            break;
                        }
                        break;
                    case 'C':
                        if (!isPreviousChar(sb, i, 'S') || isLastChar(length2, i) || FRONTV.indexOf(sb.charAt(i + 1)) < 0) {
                            if (regionMatch(sb, i, "CIA")) {
                                sb2.append('X');
                                break;
                            } else if (!isLastChar(length2, i) && FRONTV.indexOf(sb.charAt(i + 1)) >= 0) {
                                sb2.append('S');
                                break;
                            } else if ((!isPreviousChar(sb, i, 'S') || !isNextChar(sb, i, 'H')) && isNextChar(sb, i, 'H')) {
                                if (i == 0 && length2 >= 3 && isVowel(sb, 2)) {
                                    sb2.append('K');
                                    break;
                                } else {
                                    sb2.append('X');
                                    break;
                                }
                            } else {
                                sb2.append('K');
                                break;
                            }
                        }
                        break;
                    case 'D':
                        if (!isLastChar(length2, i + 1) && isNextChar(sb, i, 'G')) {
                            int i2 = i + 2;
                            if (FRONTV.indexOf(sb.charAt(i2)) >= 0) {
                                sb2.append('J');
                                i = i2;
                                break;
                            }
                        } else {
                            sb2.append('T');
                            break;
                        }
                        break;
                    case 'F':
                    case 'J':
                    case 'L':
                    case EMachine.EM_JAVELIN /* 77 */:
                    case EMachine.EM_FIREPATH /* 78 */:
                    case EMachine.EM_PRISM /* 82 */:
                        sb2.append(cCharAt);
                        break;
                    case 'G':
                        int i3 = i + 1;
                        if ((!isLastChar(length2, i3) || !isNextChar(sb, i, 'H')) && ((isLastChar(length2, i3) || !isNextChar(sb, i, 'H') || isVowel(sb, i + 2)) && (i <= 0 || (!regionMatch(sb, i, "GN") && !regionMatch(sb, i, "GNED"))))) {
                            boolean zIsPreviousChar = isPreviousChar(sb, i, 'G');
                            if (!isLastChar(length2, i) && FRONTV.indexOf(sb.charAt(i3)) >= 0 && !zIsPreviousChar) {
                                sb2.append('J');
                                break;
                            } else {
                                sb2.append('K');
                                break;
                            }
                        }
                        break;
                    case EMachine.EM_68HC05 /* 72 */:
                        if (!isLastChar(length2, i) && ((i <= 0 || VARSON.indexOf(sb.charAt(i - 1)) < 0) && isVowel(sb, i + 1))) {
                            sb2.append('H');
                            break;
                        }
                        break;
                    case EMachine.EM_VAX /* 75 */:
                        if (i <= 0 || !isPreviousChar(sb, i, 'C')) {
                            sb2.append(cCharAt);
                            break;
                        }
                        break;
                    case EMachine.EM_MMIX /* 80 */:
                        if (isNextChar(sb, i, 'H')) {
                            sb2.append('F');
                            break;
                        } else {
                            sb2.append(cCharAt);
                            break;
                        }
                    case EMachine.EM_HUANY /* 81 */:
                        sb2.append('K');
                        break;
                    case EMachine.EM_AVR /* 83 */:
                        if (regionMatch(sb, i, "SH") || regionMatch(sb, i, "SIO") || regionMatch(sb, i, "SIA")) {
                            sb2.append('X');
                            break;
                        } else {
                            sb2.append('S');
                            break;
                        }
                        break;
                    case EMachine.EM_FR30 /* 84 */:
                        if (regionMatch(sb, i, "TIA") || regionMatch(sb, i, "TIO")) {
                            sb2.append('X');
                            break;
                        } else if (!regionMatch(sb, i, "TCH")) {
                            if (regionMatch(sb, i, "TH")) {
                                sb2.append('0');
                                break;
                            } else {
                                sb2.append('T');
                                break;
                            }
                        }
                        break;
                    case EMachine.EM_D30V /* 86 */:
                        sb2.append('F');
                        break;
                    case EMachine.EM_V850 /* 87 */:
                    case EMachine.EM_MN10300 /* 89 */:
                        if (!isLastChar(length2, i) && isVowel(sb, i + 1)) {
                            sb2.append(cCharAt);
                            break;
                        }
                        break;
                    case EMachine.EM_M32R /* 88 */:
                        sb2.append('K');
                        sb2.append('S');
                        break;
                    case EMachine.EM_MN10200 /* 90 */:
                        sb2.append('S');
                        break;
                }
                i++;
            } else {
                i++;
            }
            if (sb2.length() > getMaxCodeLen()) {
                sb2.setLength(getMaxCodeLen());
            }
        }
        return sb2.toString();
    }

    private boolean isVowel(StringBuilder sb, int i) {
        return VOWELS.indexOf(sb.charAt(i)) >= 0;
    }

    private boolean isPreviousChar(StringBuilder sb, int i, char c) {
        return i > 0 && i < sb.length() && sb.charAt(i - 1) == c;
    }

    private boolean isNextChar(StringBuilder sb, int i, char c) {
        return i >= 0 && i < sb.length() - 1 && sb.charAt(i + 1) == c;
    }

    private boolean regionMatch(StringBuilder sb, int i, String str) {
        if (i < 0 || (str.length() + i) - 1 >= sb.length()) {
            return false;
        }
        return sb.substring(i, str.length() + i).equals(str);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof String)) {
            throw new EncoderException("Parameter supplied to Metaphone encode is not of type java.lang.String");
        }
        return metaphone((String) obj);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return metaphone(str);
    }

    public boolean isMetaphoneEqual(String str, String str2) {
        return metaphone(str).equals(metaphone(str2));
    }

    public int getMaxCodeLen() {
        return this.maxCodeLen;
    }

    public void setMaxCodeLen(int i) {
        this.maxCodeLen = i;
    }
}
