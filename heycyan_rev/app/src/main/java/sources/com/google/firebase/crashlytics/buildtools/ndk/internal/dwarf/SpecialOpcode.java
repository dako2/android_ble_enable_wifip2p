package com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf;

import com.google.firebase.crashlytics.buildtools.utils.p011io.ByteReader;
import java.io.IOException;

/* loaded from: classes2.dex */
public class SpecialOpcode implements DebugLineOpcode {
    private final int _opcode;

    public SpecialOpcode(int i) {
        this._opcode = i;
    }

    @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DebugLineOpcode
    public boolean process(DebugLineContext debugLineContext, ByteReader byteReader) throws IOException {
        int i = this._opcode - debugLineContext.header.opcodeBase;
        int i2 = i / debugLineContext.header.lineRange;
        int i3 = debugLineContext.header.minInstructionLength * ((debugLineContext.reg.opIndex + i2) / debugLineContext.header.maximumOperationsPerInstruction);
        int i4 = debugLineContext.header.lineBase + (i % debugLineContext.header.lineRange);
        debugLineContext.reg.opIndex = (debugLineContext.reg.opIndex + i2) % debugLineContext.header.maximumOperationsPerInstruction;
        debugLineContext.reg.address += i3;
        debugLineContext.reg.line += i4;
        debugLineContext.reg.isBasicBlock = false;
        return true;
    }
}
