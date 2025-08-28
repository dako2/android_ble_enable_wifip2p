package com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.filefilter;

import java.util.List;

/* loaded from: classes2.dex */
public interface ConditionalFileFilter {
    void addFileFilter(IOFileFilter iOFileFilter);

    List<IOFileFilter> getFileFilters();

    boolean removeFileFilter(IOFileFilter iOFileFilter);

    void setFileFilters(List<IOFileFilter> list);
}
