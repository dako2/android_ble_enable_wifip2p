package com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Optional;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Maps;
import java.util.List;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public class NamedRanges {
    public TreeMap<Long, NamedRange> _byStartAddress = Maps.newTreeMap();

    public NamedRanges(List<NamedRange> list) {
        for (NamedRange namedRange : list) {
            this._byStartAddress.put(namedRange.start, namedRange);
        }
    }

    public Optional<NamedRange> rangeFor(long j) {
        NamedRange namedRangeFindClosest;
        if (this._byStartAddress.containsKey(Long.valueOf(j))) {
            namedRangeFindClosest = this._byStartAddress.get(Long.valueOf(j));
        } else {
            namedRangeFindClosest = findClosest(this._byStartAddress, j);
        }
        if (namedRangeFindClosest == null) {
            return Optional.absent();
        }
        if (namedRangeFindClosest.contains(j)) {
            return Optional.m311of(namedRangeFindClosest);
        }
        return Optional.absent();
    }

    private static NamedRange findClosest(TreeMap<Long, NamedRange> treeMap, long j) {
        Long lLowerKey = treeMap.lowerKey(Long.valueOf(j));
        if (lLowerKey != null) {
            return treeMap.get(lLowerKey);
        }
        return null;
    }
}
