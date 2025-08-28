package com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor;

import com.google.firebase.crashlytics.buildtools.Buildtools;
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.NamedRange;
import com.google.firebase.crashlytics.buildtools.utils.p011io.ByteReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes2.dex */
public class DefaultNamedRangesResolver implements NamedRangesResolver {
    private final int addressSize;
    private final ByteReader byteReader;
    private final long rangesSectionOffset;

    public DefaultNamedRangesResolver(ByteReader byteReader, int i, long j) {
        this.byteReader = byteReader;
        this.addressSize = i;
        this.rangesSectionOffset = j;
    }

    @Override // com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.NamedRangesResolver
    public List<NamedRange> resolveNamedRanges(long j, String str, long j2) {
        LinkedList linkedList = new LinkedList();
        try {
            long currentOffset = this.byteReader.getCurrentOffset();
            this.byteReader.seek(this.rangesSectionOffset + j);
            while (true) {
                long j3 = this.byteReader.readLong(this.addressSize);
                long j4 = this.byteReader.readLong(this.addressSize);
                if (j3 == 0 && j4 == 0) {
                    this.byteReader.seek(currentOffset);
                    return linkedList;
                }
                if (j3 == -1) {
                    j2 = j4;
                } else if (j3 < j4) {
                    linkedList.add(new NamedRange(str, Long.valueOf(j3 + j2), Long.valueOf(j4 + j2)));
                }
            }
        } catch (IOException e) {
            Buildtools.logE("Could not properly resolve range entries", e);
            return Collections.emptyList();
        }
    }
}
