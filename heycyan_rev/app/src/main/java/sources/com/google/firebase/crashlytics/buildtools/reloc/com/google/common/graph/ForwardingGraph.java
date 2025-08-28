package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph;

import java.util.Set;

/* loaded from: classes2.dex */
abstract class ForwardingGraph<N> extends AbstractGraph<N> {
    protected abstract BaseGraph<N> delegate();

    ForwardingGraph() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.PredecessorsFunction, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
        return predecessors((ForwardingGraph<N>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.SuccessorsFunction, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
        return successors((ForwardingGraph<N>) obj);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
    public Set<N> nodes() {
        return delegate().nodes();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractBaseGraph
    protected long edgeCount() {
        return delegate().edges().size();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
    public boolean isDirected() {
        return delegate().isDirected();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
    public boolean allowsSelfLoops() {
        return delegate().allowsSelfLoops();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
    public ElementOrder<N> nodeOrder() {
        return delegate().nodeOrder();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
    public Set<N> adjacentNodes(N n) {
        return delegate().adjacentNodes(n);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.PredecessorsFunction, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
    public Set<N> predecessors(N n) {
        return delegate().predecessors((BaseGraph<N>) n);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.SuccessorsFunction, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
    public Set<N> successors(N n) {
        return delegate().successors((BaseGraph<N>) n);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractBaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph
    public int degree(N n) {
        return delegate().degree(n);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractBaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph
    public int inDegree(N n) {
        return delegate().inDegree(n);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractBaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph
    public int outDegree(N n) {
        return delegate().outDegree(n);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractBaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(N n, N n2) {
        return delegate().hasEdgeConnecting(n, n2);
    }
}
