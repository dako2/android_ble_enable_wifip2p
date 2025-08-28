package com.elvishew.xlog.internal.util;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.TokenParser;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

/* loaded from: classes.dex */
public class ObjectToStringUtil {
    public static String bundleToString(Bundle bundle) {
        if (bundle == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append("Bundle[{");
        bundleToShortString(bundle, sb);
        sb.append("}]");
        return sb.toString();
    }

    public static String intentToString(Intent intent) {
        if (intent == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append("Intent { ");
        intentToShortString(intent, sb);
        sb.append(" }");
        return sb.toString();
    }

    private static void bundleToShortString(Bundle bundle, StringBuilder sb) {
        boolean z = true;
        for (String str : bundle.keySet()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(str).append('=');
            Object obj = bundle.get(str);
            if (obj instanceof int[]) {
                sb.append(Arrays.toString((int[]) obj));
            } else if (obj instanceof byte[]) {
                sb.append(Arrays.toString((byte[]) obj));
            } else if (obj instanceof boolean[]) {
                sb.append(Arrays.toString((boolean[]) obj));
            } else if (obj instanceof short[]) {
                sb.append(Arrays.toString((short[]) obj));
            } else if (obj instanceof long[]) {
                sb.append(Arrays.toString((long[]) obj));
            } else if (obj instanceof float[]) {
                sb.append(Arrays.toString((float[]) obj));
            } else if (obj instanceof double[]) {
                sb.append(Arrays.toString((double[]) obj));
            } else if (obj instanceof String[]) {
                sb.append(Arrays.toString((String[]) obj));
            } else if (obj instanceof CharSequence[]) {
                sb.append(Arrays.toString((CharSequence[]) obj));
            } else if (obj instanceof Parcelable[]) {
                sb.append(Arrays.toString((Parcelable[]) obj));
            } else if (obj instanceof Bundle) {
                sb.append(bundleToString((Bundle) obj));
            } else {
                sb.append(obj);
            }
            z = false;
        }
    }

    private static void intentToShortString(Intent intent, StringBuilder sb) {
        boolean z;
        String action = intent.getAction();
        boolean z2 = false;
        boolean z3 = true;
        if (action != null) {
            sb.append("act=").append(action);
            z = false;
        } else {
            z = true;
        }
        Set<String> categories = intent.getCategories();
        if (categories != null) {
            if (!z) {
                sb.append(TokenParser.f390SP);
            }
            sb.append("cat=[");
            for (String str : categories) {
                if (!z3) {
                    sb.append(',');
                }
                sb.append(str);
                z3 = false;
            }
            sb.append("]");
            z = false;
        }
        Uri data = intent.getData();
        if (data != null) {
            if (!z) {
                sb.append(TokenParser.f390SP);
            }
            sb.append("dat=");
            sb.append(uriToSafeString(data));
            z = false;
        }
        String type = intent.getType();
        if (type != null) {
            if (!z) {
                sb.append(TokenParser.f390SP);
            }
            sb.append("typ=").append(type);
            z = false;
        }
        int flags = intent.getFlags();
        if (flags != 0) {
            if (!z) {
                sb.append(TokenParser.f390SP);
            }
            sb.append("flg=0x").append(Integer.toHexString(flags));
            z = false;
        }
        String str2 = intent.getPackage();
        if (str2 != null) {
            if (!z) {
                sb.append(TokenParser.f390SP);
            }
            sb.append("pkg=").append(str2);
            z = false;
        }
        ComponentName component = intent.getComponent();
        if (component != null) {
            if (!z) {
                sb.append(TokenParser.f390SP);
            }
            sb.append("cmp=").append(component.flattenToShortString());
            z = false;
        }
        Rect sourceBounds = intent.getSourceBounds();
        if (sourceBounds != null) {
            if (!z) {
                sb.append(TokenParser.f390SP);
            }
            sb.append("bnds=").append(sourceBounds.toShortString());
            z = false;
        }
        if (intent.getClipData() != null) {
            if (!z) {
                sb.append(TokenParser.f390SP);
            }
            sb.append("(has clip)");
        } else {
            z2 = z;
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (!z2) {
                sb.append(TokenParser.f390SP);
            }
            sb.append("extras={");
            bundleToShortString(extras, sb);
            sb.append('}');
        }
        Intent selector = intent.getSelector();
        if (selector != null) {
            sb.append(" sel=");
            intentToShortString(selector, sb);
            sb.append("}");
        }
    }

    private static String uriToSafeString(Uri uri) throws NoSuchMethodException, SecurityException {
        try {
            Method declaredMethod = Uri.class.getDeclaredMethod("toSafeString", new Class[0]);
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(uri, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return uri.toString();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return uri.toString();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return uri.toString();
        }
    }
}
