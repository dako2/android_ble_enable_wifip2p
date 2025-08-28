package com.elvishew.xlog.interceptor;

import com.elvishew.xlog.LogItem;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes.dex */
public class WhitelistTagsFilterInterceptor extends AbstractFilterInterceptor {
    private Iterable<String> whitelistTags;

    public WhitelistTagsFilterInterceptor(String... strArr) {
        this(Arrays.asList(strArr));
    }

    public WhitelistTagsFilterInterceptor(Iterable<String> iterable) {
        iterable.getClass();
        this.whitelistTags = iterable;
    }

    @Override // com.elvishew.xlog.interceptor.AbstractFilterInterceptor
    protected boolean reject(LogItem logItem) {
        Iterable<String> iterable = this.whitelistTags;
        if (iterable == null) {
            return true;
        }
        Iterator<String> it = iterable.iterator();
        while (it.hasNext()) {
            if (logItem.tag.equals(it.next())) {
                return false;
            }
        }
        return true;
    }
}
