package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph;

/* loaded from: classes2.dex */
public interface MutableGraph<N> extends Graph<N> {
    boolean addNode(N n);

    boolean putEdge(N n, N n2);

    boolean removeEdge(N n, N n2);

    boolean removeNode(N n);
}
