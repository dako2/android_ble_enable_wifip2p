package com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf;

import com.google.firebase.crashlytics.buildtools.utils.p011io.ByteReader;
import java.io.IOException;

/* loaded from: classes2.dex */
public class StandardOpcodeAdvancePC implements DebugLineOpcode {
    @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DebugLineOpcode
    public boolean process(DebugLineContext debugLineContext, ByteReader byteReader) throws IOException {
        debugLineContext.reg.address += byteReader.readULEB128() * debugLineContext.header.minInstructionLength;
        return false;
    }
}
