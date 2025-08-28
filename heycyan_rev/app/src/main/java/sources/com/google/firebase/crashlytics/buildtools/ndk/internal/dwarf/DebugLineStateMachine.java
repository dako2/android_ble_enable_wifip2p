package com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Charsets;
import com.google.firebase.crashlytics.buildtools.utils.p011io.ByteReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes2.dex */
public class DebugLineStateMachine {
    private static final int EXTENDED_OPCODE = 0;
    private boolean _dwarf64 = false;
    private static final DebugLineOpcode[] STANDARD_OPCODES = {null, new StandardOpcodeCopy(), new StandardOpcodeAdvancePC(), new StandardOpcodeAdvanceLine(), new StandardOpcodeSetFile(), new StandardOpcodeSetColumn(), new StandardOpcodeNegateStatement(), new StandardOpcodeSetBasicBlock(), new StandardOpcodeConstAddPC(), new StandardOpcodeFixedAdvancePC(), new StandardOpcodeSetPrologueEnd(), new StandardOpcodeSetEpilogueBegin(), new StandardOpcodeSetIsa()};
    private static final DebugLineOpcode[] EXTENDED_OPCODES = {null, new ExtendedOpcodeEndSequence(), new ExtendedOpcodeSetAddress(), new ExtendedOpcodeDefineFile(), new ExtendedOpcodeSetDiscriminator()};

    public List<DebugLineEntry> runFromCurrentOffset(ByteReader byteReader, int i) throws DwarfException, IOException {
        long initialLength = readInitialLength(byteReader);
        return readCompilationUnit(configureContext(byteReader, initialLength, i), byteReader, byteReader.getCurrentOffset() + initialLength);
    }

    public List<DebugLineEntry> runForIndex(ByteReader byteReader, int i, long j, int i2) throws DwarfException, IOException {
        for (int i3 = 0; i3 < i; i3++) {
            if (byteReader.getCurrentOffset() >= j) {
                throw new DwarfException("Unable to set appropriate line number section offset");
            }
            byteReader.seek(readInitialLength(byteReader) + byteReader.getCurrentOffset());
        }
        return runFromCurrentOffset(byteReader, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private DebugLineContext configureContext(ByteReader byteReader, long j, int i) throws IOException {
        int i2 = byteReader.readInt(2);
        long j2 = byteReader.readLong(this._dwarf64 ? 8 : 4);
        byte b = byteReader.readByte();
        byte b2 = i2 >= 4 ? byteReader.readByte() : (byte) 1;
        boolean z = byteReader.readByte() != 0;
        byte b3 = byteReader.readByte();
        byte b4 = byteReader.readByte();
        int i3 = byteReader.readByte();
        byte[] bArr = new byte[i3];
        for (int i4 = 1; i4 < i3; i4++) {
            bArr[i4] = byteReader.readByte();
        }
        DebugLineContext debugLineContext = new DebugLineContext(new DebugLineHeader(j, i2, j2, b, b2, z, b3, b4, i3, bArr), new DebugLineRegisters(z), i);
        String nullTerminatedString = byteReader.readNullTerminatedString(Charsets.UTF_8);
        while (nullTerminatedString.length() > 0) {
            debugLineContext.defineDirectory(nullTerminatedString);
            nullTerminatedString = byteReader.readNullTerminatedString(Charsets.UTF_8);
        }
        String nullTerminatedString2 = byteReader.readNullTerminatedString(Charsets.UTF_8);
        while (nullTerminatedString2.length() > 0) {
            debugLineContext.defineFile(nullTerminatedString2, byteReader.readULEB128(), byteReader.readULEB128(), byteReader.readULEB128());
            nullTerminatedString2 = byteReader.readNullTerminatedString(Charsets.UTF_8);
        }
        return debugLineContext;
    }

    private static List<DebugLineEntry> readCompilationUnit(DebugLineContext debugLineContext, ByteReader byteReader, long j) throws DwarfException, IOException {
        LinkedList linkedList = new LinkedList();
        while (byteReader.getCurrentOffset() < j) {
            if (processOpcode(debugLineContext, byteReader)) {
                linkedList.add(new DebugLineEntry(debugLineContext.reg.address, debugLineContext.getFileInfo(debugLineContext.reg.file).name, debugLineContext.reg.line));
            }
            if (debugLineContext.reg.isEndSequence) {
                debugLineContext.reg.reset();
            }
        }
        return linkedList;
    }

    private static boolean processOpcode(DebugLineContext debugLineContext, ByteReader byteReader) throws DwarfException, IOException {
        DebugLineOpcode opcode;
        int i = byteReader.readInt(1);
        if (i < 0) {
            throw new DwarfException("Could not process opcode " + i);
        }
        if (i >= debugLineContext.header.opcodeBase) {
            opcode = new SpecialOpcode(i);
        } else if (i == 0) {
            byteReader.readULEB128();
            opcode = getOpcode(byteReader.readInt(1), EXTENDED_OPCODES);
        } else {
            opcode = getOpcode(i, STANDARD_OPCODES);
        }
        return opcode.process(debugLineContext, byteReader);
    }

    private long readInitialLength(ByteReader byteReader) throws IOException {
        long j = byteReader.readLong(4);
        if (j != -1) {
            return j;
        }
        this._dwarf64 = true;
        return byteReader.readLong(8);
    }

    private static DebugLineOpcode getOpcode(int i, DebugLineOpcode[] debugLineOpcodeArr) throws DwarfException {
        if (i < 0 || i >= debugLineOpcodeArr.length) {
            throw new DwarfException("Unknown opcode: " + i);
        }
        return debugLineOpcodeArr[i];
    }
}
