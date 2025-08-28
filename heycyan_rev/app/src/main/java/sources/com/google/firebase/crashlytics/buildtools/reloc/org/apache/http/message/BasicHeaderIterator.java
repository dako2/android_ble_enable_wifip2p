package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.Header;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HeaderIterator;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.Args;
import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
public class BasicHeaderIterator implements HeaderIterator {
    protected final Header[] allHeaders;
    protected int currentIndex = findNext(-1);
    protected String headerName;

    public BasicHeaderIterator(Header[] headerArr, String str) {
        this.allHeaders = (Header[]) Args.notNull(headerArr, "Header array");
        this.headerName = str;
    }

    protected int findNext(int i) {
        if (i < -1) {
            return -1;
        }
        int length = this.allHeaders.length - 1;
        boolean zFilterHeader = false;
        while (!zFilterHeader && i < length) {
            i++;
            zFilterHeader = filterHeader(i);
        }
        if (zFilterHeader) {
            return i;
        }
        return -1;
    }

    protected boolean filterHeader(int i) {
        String str = this.headerName;
        return str == null || str.equalsIgnoreCase(this.allHeaders[i].getName());
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HeaderIterator, java.util.Iterator
    public boolean hasNext() {
        return this.currentIndex >= 0;
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HeaderIterator
    public Header nextHeader() throws NoSuchElementException {
        int i = this.currentIndex;
        if (i < 0) {
            throw new NoSuchElementException("Iteration already finished.");
        }
        this.currentIndex = findNext(i);
        return this.allHeaders[i];
    }

    @Override // java.util.Iterator
    public final Object next() throws NoSuchElementException {
        return nextHeader();
    }

    @Override // java.util.Iterator
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Removing headers is not supported.");
    }
}
