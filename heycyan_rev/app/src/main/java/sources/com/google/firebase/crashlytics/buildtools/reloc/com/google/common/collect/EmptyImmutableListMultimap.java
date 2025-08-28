package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect;

/* loaded from: classes2.dex */
class EmptyImmutableListMultimap extends ImmutableListMultimap<Object, Object> {
    static final EmptyImmutableListMultimap INSTANCE = new EmptyImmutableListMultimap();
    private static final long serialVersionUID = 0;

    private EmptyImmutableListMultimap() {
        super(ImmutableMap.m358of(), 0);
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
