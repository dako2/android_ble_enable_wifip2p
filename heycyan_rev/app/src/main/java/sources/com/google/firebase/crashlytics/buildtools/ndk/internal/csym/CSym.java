package com.google.firebase.crashlytics.buildtools.ndk.internal.csym;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes2.dex */
public final class CSym {
    private final String _architecture;
    private final List<String> _files;
    private final List<Range> _ranges;
    private final List<String> _symbols;
    private final String _type;
    private final String _uuid;

    public static final class Range implements Comparable<Range> {
        public final String file;
        public final int insertionIndex;
        public final long lineNumber;
        public final long offset;
        public final long size;
        public final String symbol;

        public Range(int i, long j, long j2, String str, String str2, long j3) {
            this.insertionIndex = i;
            this.offset = j;
            this.size = j2;
            this.symbol = str;
            this.file = str2;
            this.lineNumber = j3;
        }

        public Range(long j, long j2, String str, String str2, long j3) {
            this(-1, j, j2, str, str2, j3);
        }

        @Override // java.lang.Comparable
        public int compareTo(Range range) {
            int iCompareTo = Long.valueOf(this.offset).compareTo(Long.valueOf(range.offset));
            return iCompareTo != 0 ? iCompareTo : Integer.valueOf(this.insertionIndex).compareTo(Integer.valueOf(range.insertionIndex));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Range range = (Range) obj;
            if (this.lineNumber != range.lineNumber || this.offset != range.offset || this.size != range.size) {
                return false;
            }
            String str = this.file;
            if (str == null ? range.file != null : !str.equals(range.file)) {
                return false;
            }
            String str2 = this.symbol;
            String str3 = range.symbol;
            return str2 == null ? str3 == null : str2.equals(str3);
        }

        public int hashCode() {
            long j = this.offset;
            long j2 = this.size;
            int i = ((((int) (j ^ (j >>> 32))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
            String str = this.symbol;
            int iHashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.file;
            int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            long j3 = this.lineNumber;
            return iHashCode2 + ((int) ((j3 >>> 32) ^ j3));
        }

        public String toString() {
            return "{ file:" + this.file + ", symbol:" + this.symbol + ", offset:" + this.offset + ", size:" + this.size + ", line:" + this.lineNumber + " }";
        }
    }

    public static final class Builder {
        private final String _architecture;
        private final String _type;
        private final String _uuid;
        private final List<Range> _ranges = new ArrayList();
        private int insertionIndex = 0;

        public Builder(String str, String str2, String str3) {
            this._uuid = str;
            this._type = str2;
            this._architecture = str3;
        }

        public Builder addRange(long j, long j2, String str) {
            return addRange(j, j2, str, null);
        }

        public Builder addRange(long j, long j2, String str, String str2) {
            return addRange(j, j2, str, str2, -1L);
        }

        public Builder addRange(long j, long j2, String str, String str2, long j3) {
            List<Range> list = this._ranges;
            int i = this.insertionIndex;
            this.insertionIndex = i + 1;
            list.add(new Range(i, j, j2, str, str2, j3));
            return this;
        }

        public CSym build() {
            return new CSym(this);
        }
    }

    private CSym(Builder builder) {
        this._ranges = new ArrayList();
        ArrayList<Range> arrayList = new ArrayList(builder._ranges);
        Collections.sort(arrayList);
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        long j = -1;
        for (Range range : arrayList) {
            if (range.file != null) {
                hashSet.add(range.file);
            }
            if (range.symbol != null) {
                hashSet2.add(range.symbol);
            }
            if (range.offset == j) {
                this._ranges.set(r3.size() - 1, range);
            } else {
                this._ranges.add(range);
            }
            j = range.offset;
        }
        this._files = Collections.unmodifiableList(new ArrayList(hashSet));
        this._symbols = Collections.unmodifiableList(new ArrayList(hashSet2));
        this._uuid = builder._uuid;
        this._type = builder._type;
        this._architecture = builder._architecture;
    }

    public String getArchitecture() {
        return this._architecture;
    }

    public String getUUID() {
        return this._uuid;
    }

    public String getType() {
        return this._type;
    }

    public List<String> getFiles() {
        return this._files;
    }

    public List<String> getSymbols() {
        return this._symbols;
    }

    public List<Range> getRanges() {
        return this._ranges;
    }
}
