package com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor;

import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DWForm;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DebugAbbrevEntry;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.CompilationUnitContext;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Charsets;
import com.google.firebase.crashlytics.buildtools.utils.p011io.ByteReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class SkipAttributesReader implements AttributesReader<Void> {
    private final CompilationUnitContext.Header cuHeader;
    private final ByteReader reader;

    @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.AttributesReader
    public /* bridge */ /* synthetic */ Void readAttributes(List list) throws IOException {
        return readAttributes2((List<DebugAbbrevEntry.Attribute>) list);
    }

    public SkipAttributesReader(ByteReader byteReader, CompilationUnitContext.Header header) {
        this.reader = byteReader;
        this.cuHeader = header;
    }

    @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.AttributesReader
    /* renamed from: readAttributes, reason: avoid collision after fix types in other method */
    public Void readAttributes2(List<DebugAbbrevEntry.Attribute> list) throws IOException {
        Iterator<DebugAbbrevEntry.Attribute> it = list.iterator();
        while (it.hasNext()) {
            skipDebugInfoEntryAttribute(this.reader, it.next(), this.cuHeader);
        }
        return null;
    }

    /* renamed from: com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.SkipAttributesReader$1 */
    static /* synthetic */ class C15051 {

        /* renamed from: $SwitchMap$com$google$firebase$crashlytics$buildtools$ndk$internal$dwarf$DWForm */
        static final /* synthetic */ int[] f251x171e477a;

        static {
            int[] iArr = new int[DWForm.values().length];
            f251x171e477a = iArr;
            try {
                iArr[DWForm.ADDR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f251x171e477a[DWForm.FLAG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f251x171e477a[DWForm.DATA1.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f251x171e477a[DWForm.REF1.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f251x171e477a[DWForm.REF2.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f251x171e477a[DWForm.DATA2.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f251x171e477a[DWForm.REF4.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f251x171e477a[DWForm.DATA4.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f251x171e477a[DWForm.REF8.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f251x171e477a[DWForm.DATA8.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f251x171e477a[DWForm.REF_SIG8.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f251x171e477a[DWForm.UDATA.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f251x171e477a[DWForm.REF_UDATA.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f251x171e477a[DWForm.REF_ADDR.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f251x171e477a[DWForm.SEC_OFFSET.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f251x171e477a[DWForm.STRP.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f251x171e477a[DWForm.BLOCK1.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f251x171e477a[DWForm.BLOCK2.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f251x171e477a[DWForm.BLOCK4.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f251x171e477a[DWForm.BLOCK.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f251x171e477a[DWForm.EXPRLOC.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f251x171e477a[DWForm.SDATA.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f251x171e477a[DWForm.STRING.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f251x171e477a[DWForm.FLAG_PRESENT.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
        }
    }

    private static void skipDebugInfoEntryAttribute(ByteReader byteReader, DebugAbbrevEntry.Attribute attribute, CompilationUnitContext.Header header) throws IOException {
        int i;
        switch (C15051.f251x171e477a[attribute.form.ordinal()]) {
            case 1:
                byteReader.readLong(header.addressSize);
                break;
            case 2:
            case 3:
            case 4:
                byteReader.readBytes(1);
                break;
            case 5:
            case 6:
                byteReader.readBytes(2);
                break;
            case 7:
            case 8:
                byteReader.readBytes(4);
                break;
            case 9:
            case 10:
            case 11:
                byteReader.readBytes(8);
                break;
            case 12:
            case 13:
                byteReader.readULEB128();
                break;
            case 14:
                if (header.version < 3) {
                    i = header.addressSize;
                } else {
                    i = header.referenceSize;
                }
                byteReader.readBytes(i);
                break;
            case 15:
            case 16:
                byteReader.readBytes(header.referenceSize);
                break;
            case 17:
                readBytesWithBlockSize(byteReader, 1);
                break;
            case 18:
                readBytesWithBlockSize(byteReader, 2);
                break;
            case 19:
                readBytesWithBlockSize(byteReader, 4);
                break;
            case 20:
            case 21:
                readBytesWithBlockSize(byteReader);
                break;
            case 22:
                byteReader.readSLEB128();
                break;
            case 23:
                byteReader.readNullTerminatedString(Charsets.UTF_8);
                break;
        }
    }

    private static byte[] readBytesWithBlockSize(ByteReader byteReader, int i) throws IOException {
        return byteReader.readBytes(byteReader.readInt(i));
    }

    private static byte[] readBytesWithBlockSize(ByteReader byteReader) throws IOException {
        return byteReader.readBytes(byteReader.readULEB128());
    }
}
