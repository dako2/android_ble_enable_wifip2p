package com.glasssutdio.wear.all.utils.audio;

import java.io.FileInputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class YuvFrameReader {
    private FileInputStream fis;
    private int frameSize;

    public YuvFrameReader(String yuvFilePath, int width, int height) throws IOException {
        this.fis = new FileInputStream(yuvFilePath);
        this.frameSize = ((width * height) * 3) / 2;
    }

    public byte[] readNextFrame() throws IOException {
        byte[] bArr = new byte[this.frameSize];
        if (this.fis.read(bArr) == -1) {
            return null;
        }
        return bArr;
    }

    public void close() throws IOException {
        FileInputStream fileInputStream = this.fis;
        if (fileInputStream != null) {
            fileInputStream.close();
        }
    }
}
