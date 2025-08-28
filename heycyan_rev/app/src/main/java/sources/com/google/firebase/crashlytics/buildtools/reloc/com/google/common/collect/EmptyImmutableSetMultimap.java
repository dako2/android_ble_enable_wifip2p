package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect;

/* loaded from: classes2.dex */
class EmptyImmutableSetMultimap extends ImmutableSetMultimap<Object, Object> {
    static final EmptyImmutableSetMultimap INSTANCE = new EmptyImmutableSetMultimap();
    private static final long serialVersionUID = 0;

    private EmptyImmutableSetMultimap() {
        super(ImmutableMap.m358of(), 0, null);
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
