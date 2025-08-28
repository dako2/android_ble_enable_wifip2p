package com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf;

import com.google.firebase.crashlytics.buildtools.utils.p011io.ByteReader;
import java.io.IOException;

/* loaded from: classes2.dex */
public class StandardOpcodeConstAddPC implements DebugLineOpcode {
    private static final int CONST_OPCODE = 255;

    @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DebugLineOpcode
    public boolean process(DebugLineContext debugLineContext, ByteReader byteReader) throws IOException {
        debugLineContext.reg.address += ((255 - debugLineContext.header.opcodeBase) / debugLineContext.header.lineRange) * debugLineContext.header.minInstructionLength;
        return false;
    }
}
