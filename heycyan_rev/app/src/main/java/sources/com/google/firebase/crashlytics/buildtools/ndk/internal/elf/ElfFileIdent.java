package com.google.firebase.crashlytics.buildtools.ndk.internal.elf;

import com.oudmon.ble.base.communication.LargeDataHandler;

/* loaded from: classes2.dex */
public final class ElfFileIdent {
    private static final int EI_ABIVERSION = 8;
    private static final int EI_CLASS = 4;
    private static final int EI_DATA = 5;
    private static final byte[] EI_MAG = {Byte.MAX_VALUE, LargeDataHandler.ACTION_DEVICE_HEART_BEAT, 76, 70};
    public static final int EI_NIDENT = 16;
    private static final int EI_OSABI = 7;
    private static final int EI_VERSION = 6;
    public static final int ELFCLASS32 = 1;
    public static final int ELFCLASS64 = 2;
    public static final int ELFCLASSNONE = 0;
    public static final int ELFDATA2LSB = 1;
    public static final int ELFDATA2MSB = 2;
    public static final int ELFDATANONE = 0;
    private final byte[] _identBuffer;

    public ElfFileIdent(byte[] bArr) {
        this._identBuffer = bArr;
    }

    public int getElfClass() {
        return this._identBuffer[4];
    }

    public int getDataEncoding() {
        return this._identBuffer[5];
    }

    public int getElfVersion() {
        return this._identBuffer[6];
    }

    public int getOSABI() {
        return this._identBuffer[7];
    }

    public int getABIVersion() {
        return this._identBuffer[8];
    }

    public boolean isElf() {
        int i = 0;
        while (true) {
            byte[] bArr = EI_MAG;
            if (i >= bArr.length) {
                return true;
            }
            if (bArr[i] != this._identBuffer[i]) {
                return false;
            }
            i++;
        }
    }
}
