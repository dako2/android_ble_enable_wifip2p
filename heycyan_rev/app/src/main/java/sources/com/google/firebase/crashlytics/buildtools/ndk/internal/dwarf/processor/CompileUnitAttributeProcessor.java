package com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor;

import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DWAttribute;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DWForm;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.CompilationUnitContext;

/* loaded from: classes2.dex */
public class CompileUnitAttributeProcessor implements AttributeProcessor<CompilationUnitContext.EntryData> {
    private long lowPc;
    private final ReferenceBytesConverter referenceBytesConverter;
    private long stmtList;

    @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.AttributeProcessor
    public void processAttribute(DWAttribute dWAttribute, String str) {
    }

    public CompileUnitAttributeProcessor(ReferenceBytesConverter referenceBytesConverter) {
        this.referenceBytesConverter = referenceBytesConverter;
    }

    /* renamed from: com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.CompileUnitAttributeProcessor$1 */
    static /* synthetic */ class C15021 {

        /* renamed from: $SwitchMap$com$google$firebase$crashlytics$buildtools$ndk$internal$dwarf$DWAttribute */
        static final /* synthetic */ int[] f248x3e3fb1a6;

        static {
            int[] iArr = new int[DWAttribute.values().length];
            f248x3e3fb1a6 = iArr;
            try {
                iArr[DWAttribute.STMT_LIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f248x3e3fb1a6[DWAttribute.LOW_PC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.AttributeProcessor
    public void processAttribute(DWAttribute dWAttribute, DWForm dWForm, byte[] bArr) {
        if (C15021.f248x3e3fb1a6[dWAttribute.ordinal()] != 1) {
            return;
        }
        this.stmtList = this.referenceBytesConverter.asLongValue(bArr);
    }

    @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.AttributeProcessor
    public void processAttribute(DWAttribute dWAttribute, long j) {
        int i = C15021.f248x3e3fb1a6[dWAttribute.ordinal()];
        if (i == 1) {
            this.stmtList = j;
        } else {
            if (i != 2) {
                return;
            }
            this.lowPc = j;
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.AttributeProcessor
    public CompilationUnitContext.EntryData finishProcessingAttributes() {
        return new CompilationUnitContext.EntryData(Long.valueOf(this.lowPc), Long.valueOf(this.stmtList));
    }
}
