package com.glasssutdio.wear.all.pref;

import android.os.Parcelable;
import com.tencent.mmkv.MMKV;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class SpUtils {
    private static SpUtils mInstance;

    /* renamed from: mv */
    private static MMKV f167mv;

    private SpUtils() {
        f167mv = MMKV.defaultMMKV();
    }

    public static SpUtils getInstance() {
        if (mInstance == null) {
            synchronized (SpUtils.class) {
                if (mInstance == null) {
                    mInstance = new SpUtils();
                }
            }
        }
        return mInstance;
    }

    public static void encode(String key, Object object) {
        if (object instanceof String) {
            f167mv.encode(key, (String) object);
            return;
        }
        if (object instanceof Integer) {
            f167mv.encode(key, ((Integer) object).intValue());
            return;
        }
        if (object instanceof Boolean) {
            f167mv.encode(key, ((Boolean) object).booleanValue());
            return;
        }
        if (object instanceof Float) {
            f167mv.encode(key, ((Float) object).floatValue());
            return;
        }
        if (object instanceof Long) {
            f167mv.encode(key, ((Long) object).longValue());
            return;
        }
        if (object instanceof Double) {
            f167mv.encode(key, ((Double) object).doubleValue());
        } else if (object instanceof byte[]) {
            f167mv.encode(key, (byte[]) object);
        } else {
            f167mv.encode(key, object.toString());
        }
    }

    public static <Set> void encodeSet(String key, HashSet<String> sets) {
        f167mv.encode(key, sets);
    }

    public static void encodeParcelable(String key, Parcelable obj) {
        f167mv.encode(key, obj);
    }

    public static Integer decodeInt(String key) {
        return Integer.valueOf(f167mv.decodeInt(key, 0));
    }

    public static Double decodeDouble(String key) {
        return Double.valueOf(f167mv.decodeDouble(key, 0.0d));
    }

    public static Long decodeLong(String key) {
        return Long.valueOf(f167mv.decodeLong(key, 0L));
    }

    public static Boolean decodeBoolean(String key) {
        return Boolean.valueOf(f167mv.decodeBool(key, false));
    }

    public static Float decodeFloat(String key) {
        return Float.valueOf(f167mv.decodeFloat(key, 0.0f));
    }

    public static byte[] decodeBytes(String key) {
        return f167mv.decodeBytes(key);
    }

    public static String decodeString(String key) {
        return f167mv.decodeString(key, "");
    }

    public static Set<String> decodeStringSet(String key) {
        return f167mv.decodeStringSet(key, Collections.emptySet());
    }

    public static Parcelable decodeParcelable(String key) {
        return f167mv.decodeParcelable(key, null);
    }

    public static void removeKey(String key) {
        f167mv.removeValueForKey(key);
    }

    public static void clearAll() {
        f167mv.clearAll();
    }
}
