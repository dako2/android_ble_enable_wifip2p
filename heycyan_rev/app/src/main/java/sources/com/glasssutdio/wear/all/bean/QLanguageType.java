package com.glasssutdio.wear.all.bean;

import com.glasssutdio.wear.all.Localization;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: QLanguageType.kt */
@Metadata(m606d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001e\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b ¨\u0006!"}, m607d2 = {"Lcom/glasssutdio/wear/all/bean/QLanguageType;", "", "languageName", "", "code", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "getLanguageName", "Chinese", "English", "French", "German", "Spain", "Japanese", "Italian", "Hindi", "Korean", "Thai", "Russian", "Vietnamese", "Malay", "Indonesian", "Greek", "CzechRepublic", "Romanian", "Swedish", "Dutch", "Polish", "Portuguese", "Arabic", "Persian", "Turkish", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public final class QLanguageType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ QLanguageType[] $VALUES;
    private final String code;
    private final String languageName;
    public static final QLanguageType Chinese = new QLanguageType("Chinese", 0, "简体中文", "cn");
    public static final QLanguageType English = new QLanguageType("English", 1, "English", "en");
    public static final QLanguageType French = new QLanguageType("French", 2, "Français", Localization.language);
    public static final QLanguageType German = new QLanguageType("German", 3, "Deutsch", "de");
    public static final QLanguageType Spain = new QLanguageType("Spain", 4, "Español", "es");
    public static final QLanguageType Japanese = new QLanguageType("Japanese", 5, "日本語", "ja");
    public static final QLanguageType Italian = new QLanguageType("Italian", 6, "Italiano", "it");
    public static final QLanguageType Hindi = new QLanguageType("Hindi", 7, "हिन्दी", "hi");
    public static final QLanguageType Korean = new QLanguageType("Korean", 8, "한국인", "ko");
    public static final QLanguageType Thai = new QLanguageType("Thai", 9, "แบบไทย", "th");
    public static final QLanguageType Russian = new QLanguageType("Russian", 10, "Русский", "ru");
    public static final QLanguageType Vietnamese = new QLanguageType("Vietnamese", 11, "Tiếng Việt", "vi");
    public static final QLanguageType Malay = new QLanguageType("Malay", 12, "Bahasa Melayu", "ms");
    public static final QLanguageType Indonesian = new QLanguageType("Indonesian", 13, "Bahasa Indonesia", BreakpointSQLiteKey.f521ID);
    public static final QLanguageType Greek = new QLanguageType("Greek", 14, "Eλληνικά", "el");
    public static final QLanguageType CzechRepublic = new QLanguageType("CzechRepublic", 15, "Čeština", "cs");
    public static final QLanguageType Romanian = new QLanguageType("Romanian", 16, "Română", "ro");
    public static final QLanguageType Swedish = new QLanguageType("Swedish", 17, "Svenska", "sv");
    public static final QLanguageType Dutch = new QLanguageType("Dutch", 18, "Nederlands", "nl");
    public static final QLanguageType Polish = new QLanguageType("Polish", 19, "Polski", "pl");
    public static final QLanguageType Portuguese = new QLanguageType("Portuguese", 20, "Português", "pt");
    public static final QLanguageType Arabic = new QLanguageType("Arabic", 21, "العربي", "ar");
    public static final QLanguageType Persian = new QLanguageType("Persian", 22, "فارسی", "fa");
    public static final QLanguageType Turkish = new QLanguageType("Turkish", 23, "Türkçe", "tr");

    private static final /* synthetic */ QLanguageType[] $values() {
        return new QLanguageType[]{Chinese, English, French, German, Spain, Japanese, Italian, Hindi, Korean, Thai, Russian, Vietnamese, Malay, Indonesian, Greek, CzechRepublic, Romanian, Swedish, Dutch, Polish, Portuguese, Arabic, Persian, Turkish};
    }

    public static EnumEntries<QLanguageType> getEntries() {
        return $ENTRIES;
    }

    public static QLanguageType valueOf(String str) {
        return (QLanguageType) Enum.valueOf(QLanguageType.class, str);
    }

    public static QLanguageType[] values() {
        return (QLanguageType[]) $VALUES.clone();
    }

    private QLanguageType(String str, int i, String str2, String str3) {
        this.languageName = str2;
        this.code = str3;
    }

    public final String getCode() {
        return this.code;
    }

    public final String getLanguageName() {
        return this.languageName;
    }

    static {
        QLanguageType[] qLanguageTypeArr$values = $values();
        $VALUES = qLanguageTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(qLanguageTypeArr$values);
    }
}
