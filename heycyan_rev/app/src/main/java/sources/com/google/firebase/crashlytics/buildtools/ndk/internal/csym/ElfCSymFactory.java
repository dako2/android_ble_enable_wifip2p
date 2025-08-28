package com.google.firebase.crashlytics.buildtools.ndk.internal.csym;

import com.google.firebase.crashlytics.buildtools.Buildtools;
import com.google.firebase.crashlytics.buildtools.ndk.internal.csym.CSym;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DebugLineEntry;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.NamedRange;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.NamedRanges;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.EMachine;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfFileHeader;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfSectionHeaders;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfSymbol;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Lists;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Maps;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/* loaded from: classes2.dex */
public class ElfCSymFactory implements CSymFactory {
    private static final String DWARF_CSYM_TYPE = "dwarf_debug";
    private static final String ELF_CSYM_TYPE = "elf_symtab";
    private final boolean _featureUseDebugInfo;

    private static class DebugLineGroup {
        private static final Comparator<DebugLineEntry> ADDRESS_COMPARATOR = new Comparator<DebugLineEntry>() { // from class: com.google.firebase.crashlytics.buildtools.ndk.internal.csym.ElfCSymFactory.DebugLineGroup.1
            @Override // java.util.Comparator
            public int compare(DebugLineEntry debugLineEntry, DebugLineEntry debugLineEntry2) {
                long j = debugLineEntry.address;
                long j2 = debugLineEntry2.address;
                if (j < j2) {
                    return -1;
                }
                return j == j2 ? 0 : 1;
            }
        };
        private final TreeSet<DebugLineEntry> _lineEntries = new TreeSet<>(ADDRESS_COMPARATOR);
        public final long symbolAddr;
        public final String symbolName;
        public final long symbolSize;

        public DebugLineGroup(String str, long j, long j2) {
            this.symbolName = str;
            this.symbolAddr = j;
            this.symbolSize = j2;
        }

        public void add(DebugLineEntry debugLineEntry) {
            this._lineEntries.add(debugLineEntry);
        }

        public boolean hasEntries() {
            return !this._lineEntries.isEmpty();
        }

        public List<DebugLineEntry> entryList() {
            return new ArrayList(this._lineEntries);
        }
    }

    public ElfCSymFactory(boolean z) {
        this._featureUseDebugInfo = z;
    }

    @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.csym.CSymFactory
    public CSym createCSymFromFile(File file) throws CSymException, IOException {
        if (!file.isFile()) {
            throw new IllegalArgumentException("Invalid object file: " + file);
        }
        CSymFactoryHandler cSymFactoryHandler = new CSymFactoryHandler(this._featureUseDebugInfo);
        ElfDataParser.parse(file, cSymFactoryHandler, this._featureUseDebugInfo);
        return cSymFactoryHandler.getBuilder().build();
    }

    private static final class CSymFactoryHandler implements ElfDataParser.ContentHandler {
        private int _arch;
        private String _archName;
        private CSym.Builder _builder;
        private final boolean _featureUseDebugInfo;
        private boolean _hasDebugInfo;
        private boolean _isArm;
        private String _uuid;
        private final Map<Long, DebugLineGroup> _elfSymbolGroups = new HashMap();
        private final TreeMap<Long, ElfSymbol> _sortedSymbolIndex = new TreeMap<>();

        public CSymFactoryHandler(boolean z) {
            this._featureUseDebugInfo = z;
        }

        @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.ContentHandler
        public void processElfHeader(ElfFileHeader elfFileHeader) {
            int i = elfFileHeader.eMachine;
            this._arch = i;
            this._isArm = i == 40 || i == 183;
            this._archName = EMachine.getMachineName(i);
        }

        @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.ContentHandler
        public void processSectionHeaders(ElfSectionHeaders elfSectionHeaders) {
            this._hasDebugInfo = elfSectionHeaders.getHeaderByName(ElfSectionHeaders.SECTION_DEBUG_INFO).isPresent();
        }

        @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.ContentHandler
        public void processBuildId(byte[] bArr) {
            this._uuid = getBuildIdString(bArr);
        }

        @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.ContentHandler
        public void processArmVersion(String str) {
            if (this._arch == 40) {
                this._archName += String.format("v%s", str);
            }
        }

        @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.ContentHandler
        public void startProcessingSymbols() {
            String str;
            this._builder = new CSym.Builder(this._uuid, (this._featureUseDebugInfo && this._hasDebugInfo) ? ElfCSymFactory.DWARF_CSYM_TYPE : ElfCSymFactory.ELF_CSYM_TYPE, this._archName);
            if (this._hasDebugInfo) {
                str = this._featureUseDebugInfo ? "Using DWARF data for cSYM generation." : "Using ELF symbols with DWARF line number information for cSYM generation.";
            } else {
                str = "Using ELF data for cSYM generation.";
            }
            Buildtools.logD(str);
        }

        @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.ContentHandler
        public void processElfSymbols(List<ElfSymbol> list) {
            if (!this._hasDebugInfo) {
                populateBuilderWithElfSymbols(this._builder, list);
            } else {
                if (this._featureUseDebugInfo) {
                    return;
                }
                indexElfSymbols(list, this._sortedSymbolIndex, this._elfSymbolGroups, this._isArm);
            }
        }

        @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.ContentHandler
        public void processDebugInfoCompilationUnit(NamedRanges namedRanges, List<DebugLineEntry> list) {
            if (!this._featureUseDebugInfo) {
                populateElfSymbolGroups(list, this._sortedSymbolIndex, this._elfSymbolGroups, this._isArm);
            } else {
                populateBuilderWithDebugLineGroups(this._builder, createDwarfDebugLineGroups(namedRanges, list));
            }
        }

        @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser.ContentHandler
        public void endProcessingSymbols() {
            if (this._featureUseDebugInfo || !this._hasDebugInfo) {
                return;
            }
            populateBuilderWithDebugLineGroups(this._builder, Lists.newArrayList(this._elfSymbolGroups.values()));
        }

        public CSym.Builder getBuilder() {
            return this._builder;
        }

        private static void indexElfSymbols(List<ElfSymbol> list, TreeMap<Long, ElfSymbol> treeMap, Map<Long, DebugLineGroup> map, boolean z) {
            for (ElfSymbol elfSymbol : list) {
                if (!isArmMappingSymbol(elfSymbol)) {
                    long j = elfSymbol.stValue;
                    if (z) {
                        j &= -2;
                    }
                    long j2 = j;
                    treeMap.put(Long.valueOf(j2), elfSymbol);
                    if (isNecessarySymbol(elfSymbol)) {
                        map.put(Long.valueOf(j2), new DebugLineGroup(elfSymbol.stNameString, j2, elfSymbol.stSize));
                    }
                }
            }
        }

        private static void populateElfSymbolGroups(List<DebugLineEntry> list, TreeMap<Long, ElfSymbol> treeMap, Map<Long, DebugLineGroup> map, boolean z) {
            ElfSymbol elfSymbolFindEnclosingElfSymbol;
            for (DebugLineEntry debugLineEntry : list) {
                long j = debugLineEntry.address;
                if (treeMap.containsKey(Long.valueOf(j))) {
                    elfSymbolFindEnclosingElfSymbol = treeMap.get(Long.valueOf(j));
                } else {
                    elfSymbolFindEnclosingElfSymbol = findEnclosingElfSymbol(treeMap, j);
                }
                long j2 = elfSymbolFindEnclosingElfSymbol.stValue;
                if (z) {
                    j2 &= -2;
                }
                DebugLineGroup debugLineGroup = map.get(Long.valueOf(j2));
                if (debugLineGroup != null) {
                    debugLineGroup.add(debugLineEntry);
                }
            }
        }

        private static List<DebugLineGroup> createDwarfDebugLineGroups(NamedRanges namedRanges, List<DebugLineEntry> list) {
            HashMap mapNewHashMap = Maps.newHashMap();
            for (DebugLineEntry debugLineEntry : list) {
                Optional<V> optionalTransform = namedRanges.rangeFor(debugLineEntry.address).transform(new Function<NamedRange, DebugLineGroup>() { // from class: com.google.firebase.crashlytics.buildtools.ndk.internal.csym.ElfCSymFactory.CSymFactoryHandler.1
                    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function, java.util.function.Function
                    public DebugLineGroup apply(NamedRange namedRange) {
                        return new DebugLineGroup(namedRange.name, namedRange.start.longValue(), namedRange.end.longValue() - namedRange.start.longValue());
                    }
                });
                if (optionalTransform.isPresent()) {
                    DebugLineGroup debugLineGroup = (DebugLineGroup) optionalTransform.get();
                    DebugLineGroup debugLineGroup2 = (DebugLineGroup) Optional.fromNullable((DebugLineGroup) mapNewHashMap.get(Long.valueOf(debugLineGroup.symbolAddr))).mo305or((Optional) debugLineGroup);
                    debugLineGroup2.add(debugLineEntry);
                    mapNewHashMap.put(Long.valueOf(debugLineGroup2.symbolAddr), debugLineGroup2);
                }
            }
            return Lists.newArrayList(mapNewHashMap.values());
        }

        private static void populateBuilderWithElfSymbols(CSym.Builder builder, List<ElfSymbol> list) {
            for (ElfSymbol elfSymbol : list) {
                if (isNecessarySymbol(elfSymbol)) {
                    builder.addRange(elfSymbol.stValue, elfSymbol.stSize, elfSymbol.stNameString);
                }
            }
        }

        private static void populateBuilderWithDebugLineGroups(CSym.Builder builder, List<DebugLineGroup> list) {
            Iterator<DebugLineGroup> it;
            Iterator<DebugLineGroup> it2 = list.iterator();
            while (it2.hasNext()) {
                DebugLineGroup next = it2.next();
                String str = next.symbolName;
                long j = next.symbolAddr;
                long j2 = next.symbolSize;
                if (next.hasEntries()) {
                    List<DebugLineEntry> listEntryList = next.entryList();
                    int size = listEntryList.size() - 1;
                    int i = 0;
                    while (i < size) {
                        DebugLineEntry debugLineEntry = listEntryList.get(i);
                        int i2 = i + 1;
                        builder.addRange(debugLineEntry.address, listEntryList.get(i2).address - debugLineEntry.address, str, debugLineEntry.file, debugLineEntry.lineNumber);
                        size = size;
                        i = i2;
                        it2 = it2;
                    }
                    it = it2;
                    DebugLineEntry debugLineEntry2 = listEntryList.get(size);
                    builder.addRange(debugLineEntry2.address, (j + j2) - debugLineEntry2.address, str, debugLineEntry2.file, debugLineEntry2.lineNumber);
                } else {
                    it = it2;
                    builder.addRange(j, j2, str);
                }
                it2 = it;
            }
        }

        private static ElfSymbol findEnclosingElfSymbol(TreeMap<Long, ElfSymbol> treeMap, long j) {
            Long lLowerKey = treeMap.lowerKey(Long.valueOf(j));
            if (lLowerKey != null) {
                return treeMap.get(lLowerKey);
            }
            return null;
        }

        private static boolean isNecessarySymbol(ElfSymbol elfSymbol) {
            return (elfSymbol == null || (elfSymbol.stInfo & 15) != 2 || elfSymbol.stSize == 0) ? false : true;
        }

        private static boolean isArmMappingSymbol(ElfSymbol elfSymbol) {
            return (elfSymbol == null || elfSymbol.stNameString == null || (!elfSymbol.stNameString.startsWith("$a") && !elfSymbol.stNameString.startsWith("$d") && !elfSymbol.stNameString.startsWith("$t"))) ? false : true;
        }

        private static String getBuildIdString(byte[] bArr) {
            return buildIdBytesToString(bArr);
        }

        private static String buildIdBytesToString(byte[] bArr) {
            StringBuilder sb = new StringBuilder();
            for (byte b : bArr) {
                sb.append(String.format("%02x", Integer.valueOf(b & 255)));
            }
            return sb.toString();
        }
    }
}
