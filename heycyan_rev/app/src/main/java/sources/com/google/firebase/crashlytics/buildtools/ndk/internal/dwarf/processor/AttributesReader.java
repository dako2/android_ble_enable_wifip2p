package com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor;

import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DebugAbbrevEntry;
import java.io.IOException;
import java.util.List;

/* loaded from: classes2.dex */
public interface AttributesReader<T> {
    T readAttributes(List<DebugAbbrevEntry.Attribute> list) throws IOException;
}
