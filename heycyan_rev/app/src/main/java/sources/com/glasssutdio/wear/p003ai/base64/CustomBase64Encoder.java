package com.glasssutdio.wear.p003ai.base64;

import android.util.Base64;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class CustomBase64Encoder {
    public static String imageToBase64(String imagePath) throws IOException {
        try {
            File file = new File(imagePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[(int) file.length()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return "data:image/jpeg;base64," + Base64.encodeToString(bArr, 2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
