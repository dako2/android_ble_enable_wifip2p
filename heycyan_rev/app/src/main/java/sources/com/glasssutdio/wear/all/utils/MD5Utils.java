package com.glasssutdio.wear.all.utils;

import android.util.Log;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.digest.MessageDigestAlgorithms;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes.dex */
public class MD5Utils {
    public static String getMD5(String input) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            Log.e("MD5Utils", "MD5 algorithm not found", e);
            return null;
        }
    }
}
