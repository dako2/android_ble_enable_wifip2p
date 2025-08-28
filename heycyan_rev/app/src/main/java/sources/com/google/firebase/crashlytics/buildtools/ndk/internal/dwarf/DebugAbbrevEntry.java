package com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class DebugAbbrevEntry {
    public final List<Attribute> attributes;
    public final boolean hasChildren;
    public final int number;
    public final DWTag tag;

    public DebugAbbrevEntry(int i, int i2, boolean z, List<Attribute> list) {
        this.number = i;
        this.tag = DWTag.fromValue(i2);
        this.hasChildren = z;
        this.attributes = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.number + "\t" + this.tag + "\t" + (this.hasChildren ? "[has children]" : "[no children]") + IOUtils.LINE_SEPARATOR_UNIX);
        Iterator<Attribute> it = this.attributes.iterator();
        while (it.hasNext()) {
            sb.append("  ").append(it.next()).append(IOUtils.LINE_SEPARATOR_UNIX);
        }
        return sb.toString();
    }

    public static class Attribute {
        public final DWForm form;
        public final DWAttribute name;

        public Attribute(int i, int i2) {
            this.name = DWAttribute.fromValue(i);
            this.form = DWForm.fromValue(i2);
        }

        public String toString() {
            return this.name + "\t" + this.form;
        }
    }
}
