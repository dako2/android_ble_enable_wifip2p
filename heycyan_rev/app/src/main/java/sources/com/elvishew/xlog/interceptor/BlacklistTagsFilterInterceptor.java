package com.elvishew.xlog.interceptor;

import com.elvishew.xlog.LogItem;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes.dex */
public class BlacklistTagsFilterInterceptor extends AbstractFilterInterceptor {
    private Iterable<String> blacklistTags;

    public BlacklistTagsFilterInterceptor(String... strArr) {
        this(Arrays.asList(strArr));
    }

    public BlacklistTagsFilterInterceptor(Iterable<String> iterable) {
        iterable.getClass();
        this.blacklistTags = iterable;
    }

    @Override // com.elvishew.xlog.interceptor.AbstractFilterInterceptor
    protected boolean reject(LogItem logItem) {
        Iterable<String> iterable = this.blacklistTags;
        if (iterable == null) {
            return false;
        }
        Iterator<String> it = iterable.iterator();
        while (it.hasNext()) {
            if (logItem.tag.equals(it.next())) {
                return true;
            }
        }
        return false;
    }
}
