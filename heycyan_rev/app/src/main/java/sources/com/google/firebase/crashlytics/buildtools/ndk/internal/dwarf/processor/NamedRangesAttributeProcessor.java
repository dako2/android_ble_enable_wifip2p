package com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor;

import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DWAttribute;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DWForm;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.NamedRange;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public class NamedRangesAttributeProcessor implements AttributeProcessor<List<NamedRange>> {
    private final CompilationUnitContext cuContext;
    private boolean isDeclaration;
    private boolean isHighPcAddress;
    private boolean isInline;
    private String linkageName;
    private String name;
    private final NamedRangesResolver namedRangesResolver;
    private final long offset;
    private long specification = -1;
    private long abstractOrigin = -1;
    private long highPc = -1;
    private long lowPc = -1;
    private long rangesOffset = -1;

    public NamedRangesAttributeProcessor(long j, CompilationUnitContext compilationUnitContext, NamedRangesResolver namedRangesResolver) {
        this.offset = j;
        this.cuContext = compilationUnitContext;
        this.namedRangesResolver = namedRangesResolver;
    }

    /* renamed from: com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.NamedRangesAttributeProcessor$1 */
    static /* synthetic */ class C15041 {

        /* renamed from: $SwitchMap$com$google$firebase$crashlytics$buildtools$ndk$internal$dwarf$DWAttribute */
        static final /* synthetic */ int[] f250x3e3fb1a6;

        static {
            int[] iArr = new int[DWAttribute.values().length];
            f250x3e3fb1a6 = iArr;
            try {
                iArr[DWAttribute.HIGH_PC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f250x3e3fb1a6[DWAttribute.INLINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f250x3e3fb1a6[DWAttribute.DECLARATION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f250x3e3fb1a6[DWAttribute.SPECIFICATION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f250x3e3fb1a6[DWAttribute.ABSTRACT_ORIGIN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f250x3e3fb1a6[DWAttribute.LOW_PC.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f250x3e3fb1a6[DWAttribute.RANGES.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f250x3e3fb1a6[DWAttribute.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f250x3e3fb1a6[DWAttribute.LINKAGE_NAME.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.AttributeProcessor
    public void processAttribute(DWAttribute dWAttribute, DWForm dWForm, byte[] bArr) {
        int i = C15041.f250x3e3fb1a6[dWAttribute.ordinal()];
        if (i == 1) {
            this.highPc = this.cuContext.fileContext.referenceBytesConverter.asLongValue(bArr);
            this.isHighPcAddress = false;
        } else {
            if (i != 2) {
                return;
            }
            this.isInline = true;
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.AttributeProcessor
    public void processAttribute(DWAttribute dWAttribute, long j) {
        int i = C15041.f250x3e3fb1a6[dWAttribute.ordinal()];
        if (i == 1) {
            this.highPc = j;
            this.isHighPcAddress = true;
            return;
        }
        if (i == 3) {
            this.isDeclaration = j == 1;
            return;
        }
        if (i == 4) {
            this.specification = j;
            return;
        }
        if (i == 5) {
            this.abstractOrigin = j;
        } else if (i == 6) {
            this.lowPc = j;
        } else {
            if (i != 7) {
                return;
            }
            this.rangesOffset = j;
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.AttributeProcessor
    public void processAttribute(DWAttribute dWAttribute, String str) {
        int i = C15041.f250x3e3fb1a6[dWAttribute.ordinal()];
        if (i == 8) {
            this.name = str;
        } else {
            if (i != 9) {
                return;
            }
            this.linkageName = str;
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.AttributeProcessor
    public List<NamedRange> finishProcessingAttributes() {
        String str = (String) Optional.fromNullable(this.linkageName).mo303or(Optional.fromNullable(this.name)).orNull();
        if (str == null) {
            if (this.specification >= 0) {
                str = this.cuContext.specificationMap.get(Long.valueOf(this.specification));
            } else {
                str = this.abstractOrigin >= 0 ? this.cuContext.abstractOriginMap.get(Long.valueOf(this.abstractOrigin)) : null;
            }
        }
        if (str != null) {
            if (this.isDeclaration) {
                this.cuContext.specificationMap.put(Long.valueOf(this.offset), str);
            }
            if (this.isInline) {
                this.cuContext.abstractOriginMap.put(Long.valueOf(this.offset), str);
            }
        } else {
            str = "<unknown>";
        }
        String str2 = str;
        long j = this.lowPc;
        if (j >= 0) {
            long j2 = this.highPc;
            if (j2 >= 0) {
                if (!this.isHighPcAddress) {
                    this.highPc = j2 + j;
                }
                return Collections.singletonList(new NamedRange(str2, Long.valueOf(this.lowPc), Long.valueOf(this.highPc)));
            }
        }
        long j3 = this.rangesOffset;
        if (j3 >= 0) {
            return this.namedRangesResolver.resolveNamedRanges(j3, str2, this.cuContext.getLowPc());
        }
        return Collections.emptyList();
    }
}
