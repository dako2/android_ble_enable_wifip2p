package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph;

import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.util.Set;

/* loaded from: classes2.dex */
public interface Graph<N> extends BaseGraph<N> {
    Set<N> adjacentNodes(N n);

    boolean allowsSelfLoops();

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph
    int degree(N n);

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph
    Set<EndpointPair<N>> edges();

    boolean equals(@NullableDecl Object obj);

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph
    boolean hasEdgeConnecting(N n, N n2);

    int hashCode();

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph
    int inDegree(N n);

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph
    Set<EndpointPair<N>> incidentEdges(N n);

    boolean isDirected();

    ElementOrder<N> nodeOrder();

    Set<N> nodes();

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph
    int outDegree(N n);

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.PredecessorsFunction, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
    Set<N> predecessors(N n);

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.SuccessorsFunction, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
    Set<N> successors(N n);

    /* JADX WARN: Multi-variable type inference failed */
    /* bridge */ /* synthetic */ default Iterable predecessors(Object obj) {
        return predecessors((Graph<N>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* bridge */ /* synthetic */ default Iterable successors(Object obj) {
        return successors((Graph<N>) obj);
    }
}
