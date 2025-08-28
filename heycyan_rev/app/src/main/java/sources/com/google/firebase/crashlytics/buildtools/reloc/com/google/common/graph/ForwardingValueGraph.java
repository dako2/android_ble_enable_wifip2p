package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph;

import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.util.Optional;
import java.util.Set;

/* loaded from: classes2.dex */
abstract class ForwardingValueGraph<N, V> extends AbstractValueGraph<N, V> {
    protected abstract ValueGraph<N, V> delegate();

    ForwardingValueGraph() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.PredecessorsFunction, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
        return predecessors((ForwardingValueGraph<N, V>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.SuccessorsFunction, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
        return successors((ForwardingValueGraph<N, V>) obj);
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
        return delegate().predecessors((ValueGraph<N, V>) n);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.SuccessorsFunction, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
    public Set<N> successors(N n) {
        return delegate().successors((ValueGraph<N, V>) n);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractValueGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractBaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph
    public int degree(N n) {
        return delegate().degree(n);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractValueGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractBaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph
    public int inDegree(N n) {
        return delegate().inDegree(n);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractValueGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractBaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph
    public int outDegree(N n) {
        return delegate().outDegree(n);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractValueGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractBaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(N n, N n2) {
        return delegate().hasEdgeConnecting(n, n2);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractValueGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.ValueGraph
    public Optional<V> edgeValue(N n, N n2) {
        return delegate().edgeValue(n, n2);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.ValueGraph
    @NullableDecl
    public V edgeValueOrDefault(N n, N n2, @NullableDecl V v) {
        return delegate().edgeValueOrDefault(n, n2, v);
    }
}
