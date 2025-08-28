package com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf;

import com.google.firebase.crashlytics.buildtools.Buildtools;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DebugAbbrevEntry;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.CompilationUnitContext;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.CompileUnitAttributeProcessor;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.DefaultAttributesReader;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.DefaultNamedRangesResolver;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.FileContext;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.MissingSectionNamedRangesResolver;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.NamedRangesAttributeProcessor;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.NamedRangesResolver;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.ReferenceBytesConverter;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.SkipAttributesReader;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.DebugElfSectionHeaders;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfDataParser;
import com.google.firebase.crashlytics.buildtools.ndk.internal.elf.ElfSectionHeader;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Predicate;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Collections2;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Lists;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Maps;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Ordering;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Sets;
import com.google.firebase.crashlytics.buildtools.utils.p011io.ByteReader;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public class DwarfDataParser {
    private static final NamedRangesResolver MISSING_SECTION_RESOLVER = new MissingSectionNamedRangesResolver();
    private static final Set<DWTag> RELEVANT_TAGS = Sets.newHashSet(DWTag.SUBPROGRAM, DWTag.INLINED_SUBROUTINE);
    private final ByteOrder byteOrder;
    private final ByteReader byteReader;
    private final DebugElfSectionHeaders debugHeaders;
    private final boolean isDebugFeatureFlagEnabled;

    public DwarfDataParser(ByteReader byteReader, ByteOrder byteOrder, DebugElfSectionHeaders debugElfSectionHeaders, boolean z) {
        this.byteReader = byteReader;
        this.byteOrder = byteOrder;
        this.debugHeaders = debugElfSectionHeaders;
        this.isDebugFeatureFlagEnabled = z;
    }

    public void parse(ElfDataParser.ContentHandler contentHandler) throws IOException {
        List<DebugLineEntry> debugLineDataAtIndex;
        this.byteReader.seek(this.debugHeaders.debugInfo.shOffset);
        long j = this.debugHeaders.debugInfo.shOffset + this.debugHeaders.debugInfo.shSize;
        FileContext fileContext = new FileContext(this.debugHeaders, new ReferenceBytesConverter(this.byteOrder));
        int i = 0;
        while (this.byteReader.getCurrentOffset() != j) {
            CompilationUnitContext compilationUnit = readCompilationUnit(this.byteReader, fileContext, this.isDebugFeatureFlagEnabled);
            int i2 = compilationUnit.header.addressSize;
            NamedRanges namedRanges = new NamedRanges(compilationUnit.namedRanges);
            long currentOffset = this.byteReader.getCurrentOffset();
            if (this.isDebugFeatureFlagEnabled) {
                Optional<Long> debugLineOffset = compilationUnit.getDebugLineOffset();
                if (debugLineOffset.isPresent()) {
                    debugLineDataAtIndex = readDebugLineData(this.byteReader, this.debugHeaders.debugLine.shOffset + debugLineOffset.get().longValue(), i2);
                } else {
                    debugLineDataAtIndex = Collections.emptyList();
                }
            } else {
                debugLineDataAtIndex = readDebugLineDataAtIndex(this.byteReader, i, this.debugHeaders.debugLine, i2);
            }
            contentHandler.processDebugInfoCompilationUnit(namedRanges, debugLineDataAtIndex);
            this.byteReader.seek(currentOffset);
            i++;
        }
    }

    private static List<DebugLineEntry> readDebugLineData(ByteReader byteReader, long j, int i) throws IOException {
        DebugLineStateMachine debugLineStateMachine = new DebugLineStateMachine();
        try {
            byteReader.seek(j);
            return debugLineStateMachine.runFromCurrentOffset(byteReader, i);
        } catch (DwarfException e) {
            Buildtools.logE("Could not parse debug data.", e);
            return Collections.emptyList();
        }
    }

    private static List<DebugLineEntry> readDebugLineDataAtIndex(ByteReader byteReader, int i, ElfSectionHeader elfSectionHeader, int i2) throws IOException {
        DebugLineStateMachine debugLineStateMachine = new DebugLineStateMachine();
        long j = elfSectionHeader.shSize + elfSectionHeader.shOffset;
        try {
            byteReader.seek(elfSectionHeader.shOffset);
            return debugLineStateMachine.runForIndex(byteReader, i, j, i2);
        } catch (DwarfException e) {
            Buildtools.logE("Could not parse debug data.", e);
            return Collections.emptyList();
        }
    }

    private static CompilationUnitContext readCompilationUnit(ByteReader byteReader, FileContext fileContext, boolean z) throws IOException {
        long currentOffset = byteReader.getCurrentOffset() - fileContext.debugSectionHeaders.debugInfo.shOffset;
        int i = 4;
        long j = byteReader.readLong(4);
        if (j == -1) {
            i = 8;
            j = byteReader.readLong(8);
        }
        return readCompilationUnit(byteReader, currentOffset, j, i, fileContext, z);
    }

    private static CompilationUnitContext readCompilationUnit(ByteReader byteReader, long j, long j2, int i, FileContext fileContext, boolean z) throws IOException {
        long currentOffset = byteReader.getCurrentOffset() + j2;
        int i2 = byteReader.readInt(2);
        long j3 = byteReader.readLong(i);
        int i3 = byteReader.readInt(1);
        long currentOffset2 = byteReader.getCurrentOffset();
        CompilationUnitContext.Header header = new CompilationUnitContext.Header(j, j2, i2, j3, i3, i);
        TreeMap treeMapNewTreeMap = Maps.newTreeMap();
        TreeMap treeMapNewTreeMap2 = Maps.newTreeMap();
        if (z) {
            HashMap<Integer, DebugAbbrevEntry> debugAbbrevEntries = readDebugAbbrevEntries(byteReader, fileContext.debugSectionHeaders.debugAbbrev.shOffset + j3);
            byteReader.seek(currentOffset2);
            return processCompilationUnit(byteReader, fileContext, header, treeMapNewTreeMap, treeMapNewTreeMap2, debugAbbrevEntries);
        }
        CompilationUnitContext compilationUnitContext = new CompilationUnitContext(fileContext, header, treeMapNewTreeMap, treeMapNewTreeMap2);
        byteReader.seek(currentOffset);
        return compilationUnitContext;
    }

    private static CompilationUnitContext processCompilationUnit(ByteReader byteReader, FileContext fileContext, CompilationUnitContext.Header header, Map<Long, String> map, Map<Long, String> map2, Map<Integer, DebugAbbrevEntry> map3) throws IOException {
        DebugAbbrevEntry debugAbbrevEntry = map3.get(Integer.valueOf(byteReader.readULEB128()));
        CompilationUnitContext compilationUnitContextProcessCompilationUnitEntry = processCompilationUnitEntry(byteReader, fileContext, header, map, map2, debugAbbrevEntry.attributes);
        if (debugAbbrevEntry.hasChildren) {
            compilationUnitContextProcessCompilationUnitEntry.namedRanges.addAll(processChildDebugInfoEntries(byteReader, compilationUnitContextProcessCompilationUnitEntry, map3));
        }
        return compilationUnitContextProcessCompilationUnitEntry;
    }

    private static CompilationUnitContext processCompilationUnitEntry(ByteReader byteReader, FileContext fileContext, CompilationUnitContext.Header header, Map<Long, String> map, Map<Long, String> map2, List<DebugAbbrevEntry.Attribute> list) throws IOException {
        return new CompilationUnitContext(fileContext, header, map, map2, (CompilationUnitContext.EntryData) new DefaultAttributesReader(byteReader, header, fileContext.referenceBytesConverter, new CompileUnitAttributeProcessor(fileContext.referenceBytesConverter), fileContext.debugSectionHeaders.debugStr.shOffset).readAttributes(list));
    }

    private static HashMap<Integer, DebugAbbrevEntry> readDebugAbbrevEntries(ByteReader byteReader, long j) throws IOException {
        byteReader.seek(j);
        HashMap<Integer, DebugAbbrevEntry> mapNewHashMap = Maps.newHashMap();
        while (true) {
            int uleb128 = byteReader.readULEB128();
            if (uleb128 == 0) {
                return mapNewHashMap;
            }
            mapNewHashMap.put(Integer.valueOf(uleb128), new DebugAbbrevEntry(uleb128, byteReader.readULEB128(), byteReader.readByte() != 0, readDebugAbbrevEntryAttributes(byteReader)));
        }
    }

    private static List<DebugAbbrevEntry.Attribute> readDebugAbbrevEntryAttributes(ByteReader byteReader) throws IOException {
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        while (true) {
            int uleb128 = byteReader.readULEB128();
            int uleb1282 = byteReader.readULEB128();
            if (uleb128 == 0 && uleb1282 == 0) {
                return linkedListNewLinkedList;
            }
            linkedListNewLinkedList.add(new DebugAbbrevEntry.Attribute(uleb128, uleb1282));
        }
    }

    private static List<NamedRange> processChildDebugInfoEntries(ByteReader byteReader, CompilationUnitContext compilationUnitContext, Map<Integer, DebugAbbrevEntry> map) throws IOException {
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        long j = compilationUnitContext.fileContext.debugSectionHeaders.debugInfo.shOffset;
        long currentOffset = byteReader.getCurrentOffset() - j;
        int uleb128 = byteReader.readULEB128();
        long currentOffset2 = currentOffset;
        while (uleb128 > 0) {
            DebugAbbrevEntry debugAbbrevEntry = map.get(Integer.valueOf(uleb128));
            List<NamedRange> listProcessDebugInfoEntry = processDebugInfoEntry(byteReader, compilationUnitContext, currentOffset2, debugAbbrevEntry.tag, debugAbbrevEntry.attributes);
            if (debugAbbrevEntry.hasChildren) {
                listProcessDebugInfoEntry = interleaveRanges(listProcessDebugInfoEntry, processChildDebugInfoEntries(byteReader, compilationUnitContext, map));
            }
            linkedListNewLinkedList.addAll(listProcessDebugInfoEntry);
            currentOffset2 = byteReader.getCurrentOffset() - j;
            uleb128 = byteReader.readULEB128();
        }
        return linkedListNewLinkedList;
    }

    private static List<NamedRange> processDebugInfoEntry(ByteReader byteReader, CompilationUnitContext compilationUnitContext, long j, DWTag dWTag, List<DebugAbbrevEntry.Attribute> list) throws IOException {
        NamedRangesResolver defaultNamedRangesResolver;
        if (!RELEVANT_TAGS.contains(dWTag)) {
            new SkipAttributesReader(byteReader, compilationUnitContext.header).readAttributes2(list);
            return Collections.emptyList();
        }
        ElfSectionHeader elfSectionHeader = compilationUnitContext.fileContext.debugSectionHeaders.debugRanges;
        if (elfSectionHeader != null) {
            defaultNamedRangesResolver = new DefaultNamedRangesResolver(byteReader, compilationUnitContext.header.addressSize, elfSectionHeader.shOffset);
        } else {
            defaultNamedRangesResolver = MISSING_SECTION_RESOLVER;
        }
        return (List) new DefaultAttributesReader(byteReader, compilationUnitContext.header, compilationUnitContext.fileContext.referenceBytesConverter, new NamedRangesAttributeProcessor(j, compilationUnitContext, defaultNamedRangesResolver), compilationUnitContext.fileContext.debugSectionHeaders.debugStr.shOffset).readAttributes(list);
    }

    private static List<NamedRange> interleaveRanges(List<NamedRange> list, List<NamedRange> list2) {
        if (list.isEmpty()) {
            return list2;
        }
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        for (NamedRange namedRange : list) {
            linkedListNewLinkedList.addAll(interleave(namedRange, Collections2.filter(list2, isChildOf(namedRange))));
        }
        return linkedListNewLinkedList;
    }

    private static List<NamedRange> interleave(NamedRange namedRange, Collection<NamedRange> collection) {
        if (collection.isEmpty()) {
            return Lists.newArrayList(namedRange);
        }
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        long jLongValue = namedRange.start.longValue();
        long jLongValue2 = namedRange.end.longValue();
        for (NamedRange namedRange2 : Ordering.natural().immutableSortedCopy(collection)) {
            long jLongValue3 = namedRange2.end.longValue();
            if (namedRange2.start.longValue() > jLongValue) {
                arrayListNewArrayList.add(new NamedRange(namedRange.name, Long.valueOf(jLongValue), namedRange2.start));
            }
            arrayListNewArrayList.add(namedRange2);
            jLongValue = namedRange2.end.longValue();
            jLongValue2 = jLongValue3;
        }
        if (jLongValue2 < namedRange.end.longValue()) {
            arrayListNewArrayList.add(new NamedRange(namedRange.name, Long.valueOf(jLongValue2), namedRange.end));
        }
        return arrayListNewArrayList;
    }

    private static Predicate<NamedRange> isChildOf(final NamedRange namedRange) {
        return new Predicate<NamedRange>() { // from class: com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DwarfDataParser.1
            @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Predicate
            public boolean apply(NamedRange namedRange2) {
                return namedRange2 != null && namedRange.contains(namedRange2);
            }
        };
    }
}
