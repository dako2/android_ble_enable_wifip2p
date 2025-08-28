package com.google.firebase.crashlytics.buildtools.ndk.internal.elf;

import com.google.firebase.crashlytics.buildtools.Buildtools;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DebugLineEntry;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DwarfDataParser;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.NamedRanges;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Charsets;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Predicate;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Lists;
import com.google.firebase.crashlytics.buildtools.utils.p011io.ByteReader;
import com.google.firebase.crashlytics.buildtools.utils.p011io.RandomAccessFileInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes2.dex */
public class ElfDataParser {
    private static final String[] ARM_ARCH = {"Pre-v4", "4", "4T", "5T", "5TE", "5TEJ", "6", "6KZ", "6T2", "6K", "7", "6-M", "6S-M", "7E-M", "8"};
    private static final String ARM_ATTR_PUBLIC_SECTION = "aeabi";
    private static final int ARM_ATTR_TAG_FILE_ATTRIBUTES = 1;
    private static final String ELF_NOTE_GNU = "GNU";
    private static final long NT_GNU_BUILD_ID = 3;
    private static final int SHT_ARM_ATTRIBUTES = 1879048195;
    private ByteOrder _byteOrder;
    private final ByteReader _fileReader;
    private int _wordSize;

    public interface ContentHandler {
        void endProcessingSymbols();

        void processArmVersion(String str);

        void processBuildId(byte[] bArr);

        void processDebugInfoCompilationUnit(NamedRanges namedRanges, List<DebugLineEntry> list);

        void processElfHeader(ElfFileHeader elfFileHeader);

        void processElfSymbols(List<ElfSymbol> list);

        void processSectionHeaders(ElfSectionHeaders elfSectionHeaders);

        void startProcessingSymbols();
    }

    private static long notePadding(long j) {
        return (j + NT_GNU_BUILD_ID) & (-4);
    }

    public static void parse(File file, ContentHandler contentHandler, boolean z) throws IOException {
        ByteReader byteReader = null;
        try {
            ByteReader byteReader2 = new ByteReader(new RandomAccessFileInputStream(file));
            try {
                new ElfDataParser(byteReader2).parse(contentHandler, z);
                byteReader2.close();
            } catch (Throwable th) {
                th = th;
                byteReader = byteReader2;
                if (byteReader != null) {
                    byteReader.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public ElfDataParser(ByteReader byteReader) {
        this._fileReader = byteReader;
    }

    public void parse(ContentHandler contentHandler) throws IOException {
        parse(contentHandler, false);
    }

    public void parse(ContentHandler contentHandler, boolean z) throws IOException {
        ElfFileIdent elfFileIdentInitializeReader = initializeReader();
        if (contentHandler == null) {
            contentHandler = new NullContentHandler();
        }
        parseElf(elfFileIdentInitializeReader, contentHandler, z);
    }

    private ElfFileIdent initializeReader() throws IOException {
        ByteOrder byteOrder;
        ElfFileIdent elfFileIdent = readElfFileIdent(this._fileReader);
        if (!elfFileIdent.isElf()) {
            throw new IllegalArgumentException("Input is not a valid ELF file.");
        }
        if (elfFileIdent.getDataEncoding() == 2) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        }
        this._byteOrder = byteOrder;
        this._wordSize = elfFileIdent.getElfClass() == 2 ? 8 : 4;
        this._fileReader.setByteOrder(this._byteOrder);
        return elfFileIdent;
    }

    private void parseElf(ElfFileIdent elfFileIdent, ContentHandler contentHandler, boolean z) throws IOException {
        ElfFileHeader elfFileHeader = readElfFileHeader(this._fileReader, elfFileIdent, this._wordSize);
        contentHandler.processElfHeader(elfFileHeader);
        ElfSectionHeaders elfSectionHeaders = readElfSectionHeaders(this._fileReader, elfFileHeader, this._wordSize);
        contentHandler.processSectionHeaders(elfSectionHeaders);
        Optional<byte[]> buildId = getBuildId(elfSectionHeaders);
        if (!buildId.isPresent()) {
            Buildtools.logD("Crashlytics could not find a build ID.");
            return;
        }
        contentHandler.processBuildId(buildId.get());
        Optional<String> armVersion = readArmVersion(elfFileHeader, elfSectionHeaders);
        if (armVersion.isPresent()) {
            contentHandler.processArmVersion(armVersion.get());
        }
        contentHandler.startProcessingSymbols();
        boolean zHasDebugInfo = elfSectionHeaders.hasDebugInfo();
        if (!z || !zHasDebugInfo) {
            contentHandler.processElfSymbols(readElfSymbols(elfSectionHeaders, elfFileIdent.getElfClass()));
        }
        Optional<DebugElfSectionHeaders> optionalFrom = DebugElfSectionHeaders.from(elfSectionHeaders);
        if (optionalFrom.isPresent()) {
            new DwarfDataParser(this._fileReader, this._byteOrder, optionalFrom.get(), z).parse(contentHandler);
        }
        contentHandler.endProcessingSymbols();
    }

    public Optional<byte[]> getBuildId(ElfSectionHeaders elfSectionHeaders) throws IOException {
        Optional<byte[]> gnuBuildId = getGnuBuildId(elfSectionHeaders);
        return !gnuBuildId.isPresent() ? getTextSectionHash(elfSectionHeaders) : gnuBuildId;
    }

    private Optional<byte[]> getGnuBuildId(ElfSectionHeaders elfSectionHeaders) throws IOException {
        return readGnuBuildIdNote(elfSectionHeaders).transform(new Function<ElfNote, byte[]>() { // from class: com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.1
            @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function, java.util.function.Function
            public byte[] apply(ElfNote elfNote) {
                return elfNote.desc;
            }
        });
    }

    private Optional<byte[]> getTextSectionHash(ElfSectionHeaders elfSectionHeaders) throws IOException {
        return readTextPage(elfSectionHeaders, 16).transform(new Function<byte[], byte[]>() { // from class: com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.2
            @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function, java.util.function.Function
            public byte[] apply(byte[] bArr) {
                byte[] bArr2 = new byte[16];
                for (int i = 0; i < bArr.length; i++) {
                    int i2 = i % 16;
                    bArr2[i2] = (byte) (bArr2[i2] ^ bArr[i]);
                }
                return bArr2;
            }
        });
    }

    private Optional<byte[]> readTextPage(ElfSectionHeaders elfSectionHeaders, int i) throws IOException {
        Optional<ElfSectionHeader> optionalFindHeader = elfSectionHeaders.findHeader(new Predicate<ElfSectionHeader>() { // from class: com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.3
            @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Predicate
            public boolean apply(ElfSectionHeader elfSectionHeader) {
                return elfSectionHeader.shNameString.equals(".text") && elfSectionHeader.shType == 1;
            }
        });
        if (optionalFindHeader.isPresent()) {
            this._fileReader.seek(optionalFindHeader.get().shOffset);
            return Optional.m311of(this._fileReader.readBytes((((((int) Math.min(r5.shSize, 4096L)) + i) - 1) / i) * i));
        }
        return Optional.absent();
    }

    private Optional<ElfNote> readGnuBuildIdNote(ElfSectionHeaders elfSectionHeaders) throws IOException {
        Optional<ElfSectionHeader> optionalFindHeader = elfSectionHeaders.findHeader(new Predicate<ElfSectionHeader>() { // from class: com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.4
            @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Predicate
            public boolean apply(ElfSectionHeader elfSectionHeader) {
                return elfSectionHeader.shNameString.equals(".note.gnu.build-id") && elfSectionHeader.shType == 7;
            }
        });
        if (optionalFindHeader.isPresent()) {
            ElfNote elfNote = readElfNote(optionalFindHeader.get().shOffset);
            if (ELF_NOTE_GNU.equals(elfNote.name) && NT_GNU_BUILD_ID == elfNote.type) {
                return Optional.m311of(elfNote);
            }
        }
        return Optional.absent();
    }

    private ElfNote readElfNote(long j) throws IOException {
        this._fileReader.seek(j);
        long j2 = this._fileReader.readLong(4);
        long j3 = this._fileReader.readLong(4);
        long j4 = this._fileReader.readLong(4);
        String nullTerminatedString = this._fileReader.readNullTerminatedString(Charsets.UTF_8);
        this._fileReader.readBytes((int) (notePadding(j2) - j2));
        return new ElfNote(nullTerminatedString, j4, this._fileReader.readBytes((int) j3));
    }

    private List<ElfSymbol> readElfSymbols(ElfSectionHeaders elfSectionHeaders, int i) throws IOException {
        LinkedList linkedList = new LinkedList();
        for (ElfSectionHeader elfSectionHeader : elfSectionHeaders.getList()) {
            if (elfSectionHeader.shType == 2) {
                linkedList.addAll(readElfSymbols(elfSectionHeader, elfSectionHeaders, i));
            }
        }
        return linkedList;
    }

    private List<ElfSymbol> readElfSymbols(ElfSectionHeader elfSectionHeader, ElfSectionHeaders elfSectionHeaders, int i) throws IOException {
        Optional<ElfSectionHeader> headerByIndex = elfSectionHeaders.getHeaderByIndex(elfSectionHeader.shLink);
        if (!headerByIndex.isPresent()) {
            return Collections.emptyList();
        }
        return readSymbolTable(elfSectionHeader.shOffset, ((int) elfSectionHeader.shSize) / ((int) elfSectionHeader.shEntSize), headerByIndex.get().shOffset, i);
    }

    private List<ElfSymbol> readSymbolTable(long j, int i, long j2, int i2) throws IOException {
        this._fileReader.seek(j);
        ArrayList<ElfSymbol> arrayList = new ArrayList(i);
        for (int i3 = 0; i3 < i; i3++) {
            ElfSymbol elfSymbol = new ElfSymbol();
            if (i2 == 2) {
                elfSymbol.stName = this._fileReader.readInt(4);
                elfSymbol.stInfo = this._fileReader.readByte();
                elfSymbol.stOther = this._fileReader.readByte();
                elfSymbol.stShndx = this._fileReader.readShort(2);
                elfSymbol.stValue = this._fileReader.readLong(this._wordSize);
                elfSymbol.stSize = this._fileReader.readLong(this._wordSize);
            } else {
                elfSymbol.stName = this._fileReader.readInt(4);
                elfSymbol.stValue = this._fileReader.readLong(this._wordSize);
                elfSymbol.stSize = this._fileReader.readLong(this._wordSize);
                elfSymbol.stInfo = this._fileReader.readByte();
                elfSymbol.stOther = this._fileReader.readByte();
                elfSymbol.stShndx = this._fileReader.readShort(2);
            }
            arrayList.add(elfSymbol);
        }
        this._fileReader.seek(j2);
        for (ElfSymbol elfSymbol2 : arrayList) {
            this._fileReader.seek(elfSymbol2.stName + j2);
            elfSymbol2.stNameString = this._fileReader.readNullTerminatedString(Charsets.UTF_8);
        }
        return arrayList;
    }

    private Optional<String> readArmVersion(ElfFileHeader elfFileHeader, ElfSectionHeaders elfSectionHeaders) throws IOException {
        Optional<String> optionalAbsent = Optional.absent();
        if (elfFileHeader.eMachine != 40) {
            return optionalAbsent;
        }
        Optional<ElfSectionHeader> optionalFindHeader = elfSectionHeaders.findHeader(new Predicate<ElfSectionHeader>() { // from class: com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.5
            @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Predicate
            public boolean apply(ElfSectionHeader elfSectionHeader) {
                return elfSectionHeader.shType == ElfDataParser.SHT_ARM_ATTRIBUTES;
            }
        });
        if (!optionalFindHeader.isPresent()) {
            return optionalAbsent;
        }
        ElfSectionHeader elfSectionHeader = optionalFindHeader.get();
        return readArmVersion(elfSectionHeader.shOffset, elfSectionHeader.shSize);
    }

    private Optional<String> readArmVersion(long j, long j2) throws IOException {
        this._fileReader.seek(j);
        if (this._fileReader.readByte() != 65) {
            throw new IllegalArgumentException(String.format("Invalid data found at offset %d.", Long.valueOf(j)));
        }
        long j3 = j2 - 1;
        while (j3 > 0) {
            long j4 = this._fileReader.readInt(4);
            if (j4 > j3) {
                throw new IOException(String.format("Section size %d is greater than remaining data length %d.", Long.valueOf(j4), Long.valueOf(j3)));
            }
            j3 -= j4;
            long length = (j4 - 4) - (r0.length() - 1);
            if (this._fileReader.readNullTerminatedString(Charsets.UTF_8).equals(ARM_ATTR_PUBLIC_SECTION)) {
                return findArmVersionInSection(this._fileReader, length);
            }
            ByteReader byteReader = this._fileReader;
            byteReader.seek(byteReader.getCurrentOffset() + length);
        }
        Buildtools.logD("Crashlytics did not find an ARM public attributes subsection.");
        return Optional.absent();
    }

    private Optional<String> findArmVersionInSection(ByteReader byteReader, long j) throws IOException {
        while (j > 0) {
            byte b = byteReader.readByte();
            long j2 = byteReader.readInt(4);
            if (j2 > j) {
                throw new IOException(String.format("Subsection size %d is greater than parent section size %d.", Long.valueOf(j2), Long.valueOf(j)));
            }
            j -= j2;
            long j3 = j2 - 5;
            if (b == 1) {
                return findArmVersionInSubSection(byteReader, j3);
            }
            byteReader.seek(byteReader.getCurrentOffset() + j3);
        }
        Buildtools.logD("Crashlytics did not find an ARM file attributes subsection.");
        return Optional.absent();
    }

    private Optional<String> findArmVersionInSubSection(ByteReader byteReader, long j) throws IOException {
        long currentOffset = byteReader.getCurrentOffset() + j;
        while (byteReader.getCurrentOffset() < currentOffset) {
            int uleb128 = byteReader.readULEB128();
            if (uleb128 != 4 && uleb128 != 5) {
                if (uleb128 == 6) {
                    return Optional.m311of(ARM_ARCH[byteReader.readULEB128()]);
                }
                if (uleb128 != 32 && uleb128 != 65 && uleb128 != 67) {
                    byteReader.readULEB128();
                }
            }
            byteReader.readNullTerminatedString(Charsets.UTF_8);
        }
        Buildtools.logD("Crashlytics did not find an ARM architecture field.");
        return Optional.absent();
    }

    private static ElfFileIdent readElfFileIdent(ByteReader byteReader) throws IOException {
        byteReader.seek(0L);
        return new ElfFileIdent(byteReader.readBytes(16));
    }

    private static ElfFileHeader readElfFileHeader(ByteReader byteReader, ElfFileIdent elfFileIdent, int i) throws IOException {
        byteReader.seek(16L);
        ElfFileHeader elfFileHeader = new ElfFileHeader(elfFileIdent);
        elfFileHeader.eType = byteReader.readInt(2);
        elfFileHeader.eMachine = byteReader.readInt(2);
        elfFileHeader.eVersion = byteReader.readLong(4);
        elfFileHeader.eEntry = byteReader.readLong(i);
        elfFileHeader.ePhoff = byteReader.readLong(i);
        elfFileHeader.eShoff = byteReader.readLong(i);
        elfFileHeader.eFlags = byteReader.readLong(4);
        elfFileHeader.eEhsize = byteReader.readInt(2);
        elfFileHeader.ePhentsize = byteReader.readInt(2);
        elfFileHeader.ePhnum = byteReader.readInt(2);
        elfFileHeader.eShentsize = byteReader.readInt(2);
        elfFileHeader.eShnum = byteReader.readInt(2);
        elfFileHeader.eShstrndx = byteReader.readInt(2);
        return elfFileHeader;
    }

    private static ElfSectionHeaders readElfSectionHeaders(ByteReader byteReader, ElfFileHeader elfFileHeader, int i) throws IOException {
        byteReader.seek(elfFileHeader.eShoff);
        ArrayList<ElfSectionHeader> arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(elfFileHeader.eShnum);
        for (int i2 = 0; i2 < elfFileHeader.eShnum; i2++) {
            ElfSectionHeader elfSectionHeader = new ElfSectionHeader();
            elfSectionHeader.shName = byteReader.readInt(4);
            elfSectionHeader.shType = byteReader.readInt(4);
            elfSectionHeader.shFlags = byteReader.readLong(i);
            elfSectionHeader.shAddr = byteReader.readLong(i);
            elfSectionHeader.shOffset = byteReader.readLong(i);
            elfSectionHeader.shSize = byteReader.readLong(i);
            elfSectionHeader.shLink = byteReader.readInt(4);
            elfSectionHeader.shInfo = byteReader.readInt(4);
            elfSectionHeader.shAddrAlign = byteReader.readLong(i);
            elfSectionHeader.shEntSize = byteReader.readLong(i);
            arrayListNewArrayListWithCapacity.add(elfSectionHeader);
        }
        ElfSectionHeader elfSectionHeader2 = (ElfSectionHeader) arrayListNewArrayListWithCapacity.get(elfFileHeader.eShstrndx);
        byteReader.seek(elfSectionHeader2.shOffset);
        for (ElfSectionHeader elfSectionHeader3 : arrayListNewArrayListWithCapacity) {
            byteReader.seek(elfSectionHeader2.shOffset + elfSectionHeader3.shName);
            elfSectionHeader3.shNameString = byteReader.readNullTerminatedString(Charsets.UTF_8);
        }
        return new ElfSectionHeaders(arrayListNewArrayListWithCapacity);
    }

    private static class ElfNote {
        public final byte[] desc;
        public final String name;
        public final long type;

        public ElfNote(String str, long j, byte[] bArr) {
            this.name = str;
            this.type = j;
            this.desc = bArr;
        }
    }

    private static final class NullContentHandler implements ContentHandler {
        @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.ContentHandler
        public void endProcessingSymbols() {
        }

        @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.ContentHandler
        public void processArmVersion(String str) {
        }

        @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.ContentHandler
        public void processBuildId(byte[] bArr) {
        }

        @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.ContentHandler
        public void processDebugInfoCompilationUnit(NamedRanges namedRanges, List<DebugLineEntry> list) {
        }

        @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.ContentHandler
        public void processElfHeader(ElfFileHeader elfFileHeader) {
        }

        @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.ContentHandler
        public void processElfSymbols(List<ElfSymbol> list) {
        }

        @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.ContentHandler
        public void processSectionHeaders(ElfSectionHeaders elfSectionHeaders) {
        }

        @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.ContentHandler
        public void startProcessingSymbols() {
        }

        private NullContentHandler() {
        }
    }
}
