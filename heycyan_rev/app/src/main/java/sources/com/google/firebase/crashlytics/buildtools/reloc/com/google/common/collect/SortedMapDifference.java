package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.MapDifference;
import java.util.SortedMap;

/* loaded from: classes2.dex */
public interface SortedMapDifference<K, V> extends MapDifference<K, V> {
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.MapDifference
    SortedMap<K, MapDifference.ValueDifference<V>> entriesDiffering();

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.MapDifference
    SortedMap<K, V> entriesInCommon();

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.MapDifference
    SortedMap<K, V> entriesOnlyOnLeft();

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.MapDifference
    SortedMap<K, V> entriesOnlyOnRight();
}
