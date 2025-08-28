package com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor;

import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DWAttribute;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DWForm;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DebugAbbrevEntry;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.CompilationUnitContext;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Charsets;
import com.google.firebase.crashlytics.buildtools.utils.p011io.ByteReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class DefaultAttributesReader<T> implements AttributesReader<T> {
    private final AttributeProcessor<T> attributeProcessor;
    private final CompilationUnitContext.Header cuHeader;
    private final long debugStrOffset;
    private final ByteReader reader;
    private final ReferenceBytesConverter referenceBytesConverter;

    public DefaultAttributesReader(ByteReader byteReader, CompilationUnitContext.Header header, ReferenceBytesConverter referenceBytesConverter, AttributeProcessor<T> attributeProcessor, long j) {
        this.reader = byteReader;
        this.cuHeader = header;
        this.referenceBytesConverter = referenceBytesConverter;
        this.attributeProcessor = attributeProcessor;
        this.debugStrOffset = j;
    }

    @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.AttributesReader
    public T readAttributes(List<DebugAbbrevEntry.Attribute> list) throws IOException {
        Iterator<DebugAbbrevEntry.Attribute> it = list.iterator();
        while (it.hasNext()) {
            processDebugInfoEntryAttribute(this.reader, this.cuHeader, this.referenceBytesConverter, it.next(), this.attributeProcessor, this.debugStrOffset);
        }
        return this.attributeProcessor.finishProcessingAttributes();
    }

    /* renamed from: com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.DefaultAttributesReader$1 */
    static /* synthetic */ class C15031 {

        /* renamed from: $SwitchMap$com$google$firebase$crashlytics$buildtools$ndk$internal$dwarf$DWForm */
        static final /* synthetic */ int[] f249x171e477a;

        static {
            int[] iArr = new int[DWForm.values().length];
            f249x171e477a = iArr;
            try {
                iArr[DWForm.ADDR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f249x171e477a[DWForm.REF1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f249x171e477a[DWForm.REF2.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f249x171e477a[DWForm.REF4.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f249x171e477a[DWForm.REF8.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f249x171e477a[DWForm.REF_UDATA.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f249x171e477a[DWForm.REF_ADDR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f249x171e477a[DWForm.SEC_OFFSET.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f249x171e477a[DWForm.BLOCK1.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f249x171e477a[DWForm.BLOCK2.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f249x171e477a[DWForm.BLOCK4.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f249x171e477a[DWForm.BLOCK.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f249x171e477a[DWForm.EXPRLOC.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f249x171e477a[DWForm.DATA1.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f249x171e477a[DWForm.DATA2.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f249x171e477a[DWForm.DATA4.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f249x171e477a[DWForm.DATA8.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f249x171e477a[DWForm.REF_SIG8.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f249x171e477a[DWForm.SDATA.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f249x171e477a[DWForm.UDATA.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f249x171e477a[DWForm.FLAG.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f249x171e477a[DWForm.FLAG_PRESENT.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f249x171e477a[DWForm.STRING.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f249x171e477a[DWForm.STRP.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
        }
    }

    private static void processDebugInfoEntryAttribute(ByteReader byteReader, CompilationUnitContext.Header header, ReferenceBytesConverter referenceBytesConverter, DebugAbbrevEntry.Attribute attribute, AttributeProcessor attributeProcessor, long j) throws IOException {
        int i;
        switch (C15031.f249x171e477a[attribute.form.ordinal()]) {
            case 1:
                attributeProcessor.processAttribute(attribute.name, byteReader.readLong(header.addressSize));
                break;
            case 2:
                attributeProcessor.processAttribute(attribute.name, referenceBytesConverter.asLongValue(byteReader.readBytes(1)) + header.offset);
                break;
            case 3:
                attributeProcessor.processAttribute(attribute.name, referenceBytesConverter.asLongValue(byteReader.readBytes(2)) + header.offset);
                break;
            case 4:
                attributeProcessor.processAttribute(attribute.name, referenceBytesConverter.asLongValue(byteReader.readBytes(4)) + header.offset);
                break;
            case 5:
                attributeProcessor.processAttribute(attribute.name, referenceBytesConverter.asLongValue(byteReader.readBytes(8)) + header.offset);
                break;
            case 6:
                attributeProcessor.processAttribute(attribute.name, byteReader.readULEB128() + header.offset);
                break;
            case 7:
                DWAttribute dWAttribute = attribute.name;
                if (header.version < 3) {
                    i = header.addressSize;
                } else {
                    i = header.referenceSize;
                }
                attributeProcessor.processAttribute(dWAttribute, byteReader.readLong(i));
                break;
            case 8:
                attributeProcessor.processAttribute(attribute.name, byteReader.readLong(header.referenceSize));
                break;
            case 9:
                attributeProcessor.processAttribute(attribute.name, attribute.form, readBytesWithBlockSize(byteReader, 1));
                break;
            case 10:
                attributeProcessor.processAttribute(attribute.name, attribute.form, readBytesWithBlockSize(byteReader, 2));
                break;
            case 11:
                attributeProcessor.processAttribute(attribute.name, attribute.form, readBytesWithBlockSize(byteReader, 4));
                break;
            case 12:
            case 13:
                attributeProcessor.processAttribute(attribute.name, attribute.form, readBytesWithBlockSize(byteReader));
                break;
            case 14:
                attributeProcessor.processAttribute(attribute.name, attribute.form, byteReader.readBytes(1));
                break;
            case 15:
                attributeProcessor.processAttribute(attribute.name, attribute.form, byteReader.readBytes(2));
                break;
            case 16:
                attributeProcessor.processAttribute(attribute.name, attribute.form, byteReader.readBytes(4));
                break;
            case 17:
            case 18:
                attributeProcessor.processAttribute(attribute.name, attribute.form, byteReader.readBytes(8));
                break;
            case 19:
                attributeProcessor.processAttribute(attribute.name, byteReader.readSLEB128());
                break;
            case 20:
                attributeProcessor.processAttribute(attribute.name, byteReader.readULEB128());
                break;
            case 21:
                attributeProcessor.processAttribute(attribute.name, byteReader.readLong(1));
                break;
            case 22:
                attributeProcessor.processAttribute(attribute.name, 1L);
                break;
            case 23:
                attributeProcessor.processAttribute(attribute.name, byteReader.readNullTerminatedString(Charsets.UTF_8));
                break;
            case 24:
                attributeProcessor.processAttribute(attribute.name, readStringFromTable(byteReader, header.referenceSize, j));
                break;
            default:
                attributeProcessor.processAttribute(attribute.name, 0L);
                break;
        }
    }

    private static byte[] readBytesWithBlockSize(ByteReader byteReader, int i) throws IOException {
        return byteReader.readBytes(byteReader.readInt(i));
    }

    private static byte[] readBytesWithBlockSize(ByteReader byteReader) throws IOException {
        return byteReader.readBytes(byteReader.readULEB128());
    }

    private static String readStringFromTable(ByteReader byteReader, int i, long j) throws IOException {
        long j2 = byteReader.readLong(i);
        long currentOffset = byteReader.getCurrentOffset();
        byteReader.seek(j + j2);
        String nullTerminatedString = byteReader.readNullTerminatedString(Charsets.UTF_8);
        byteReader.seek(currentOffset);
        return nullTerminatedString;
    }
}
