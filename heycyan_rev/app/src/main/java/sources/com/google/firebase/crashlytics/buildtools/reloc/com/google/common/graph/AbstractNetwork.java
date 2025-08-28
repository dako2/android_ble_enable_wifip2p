package com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Predicate;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableSet;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Iterators;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Maps;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Sets;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.math.IntMath;
import com.google.firebase.crashlytics.buildtools.reloc.org.checkerframework.checker.nullness.compatqual.NullableDecl;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/* loaded from: classes2.dex */
public abstract class AbstractNetwork<N, E> implements Network<N, E> {

    /* renamed from: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractNetwork$1 */
    class C18121 extends AbstractGraph<N> {
        C18121() {
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.PredecessorsFunction, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
        public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
            return predecessors((C18121) obj);
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.SuccessorsFunction, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
        public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
            return successors((C18121) obj);
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
        public Set<N> nodes() {
            return AbstractNetwork.this.nodes();
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractBaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph
        public Set<EndpointPair<N>> edges() {
            if (AbstractNetwork.this.allowsParallelEdges()) {
                return super.edges();
            }
            return new AbstractSet<EndpointPair<N>>() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractNetwork.1.1
                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<EndpointPair<N>> iterator() {
                    return Iterators.transform(AbstractNetwork.this.edges().iterator(), new Function<E, EndpointPair<N>>() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractNetwork.1.1.1
                        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function, java.util.function.Function
                        public /* bridge */ /* synthetic */ Object apply(Object obj) {
                            return apply((C30231) obj);
                        }

                        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function, java.util.function.Function
                        public EndpointPair<N> apply(E e) {
                            return AbstractNetwork.this.incidentNodes(e);
                        }
                    });
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public int size() {
                    return AbstractNetwork.this.edges().size();
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean contains(@NullableDecl Object obj) {
                    if (!(obj instanceof EndpointPair)) {
                        return false;
                    }
                    EndpointPair endpointPair = (EndpointPair) obj;
                    return C18121.this.isDirected() == endpointPair.isOrdered() && C18121.this.nodes().contains(endpointPair.nodeU()) && C18121.this.successors((C18121) endpointPair.nodeU()).contains(endpointPair.nodeV());
                }
            };
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
        public ElementOrder<N> nodeOrder() {
            return AbstractNetwork.this.nodeOrder();
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
        public boolean isDirected() {
            return AbstractNetwork.this.isDirected();
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
        public boolean allowsSelfLoops() {
            return AbstractNetwork.this.allowsSelfLoops();
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
        public Set<N> adjacentNodes(N n) {
            return AbstractNetwork.this.adjacentNodes(n);
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.PredecessorsFunction, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
        public Set<N> predecessors(N n) {
            return AbstractNetwork.this.predecessors((Object) n);
        }

        @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.BaseGraph, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.SuccessorsFunction, com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Graph
        public Set<N> successors(N n) {
            return AbstractNetwork.this.successors((Object) n);
        }
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Network
    public Graph<N> asGraph() {
        return new C18121();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Network
    public int degree(N n) {
        if (isDirected()) {
            return IntMath.saturatedAdd(inEdges(n).size(), outEdges(n).size());
        }
        return IntMath.saturatedAdd(incidentEdges(n).size(), edgesConnecting(n, n).size());
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Network
    public int inDegree(N n) {
        return isDirected() ? inEdges(n).size() : degree(n);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Network
    public int outDegree(N n) {
        return isDirected() ? outEdges(n).size() : degree(n);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Network
    public Set<E> adjacentEdges(E e) {
        EndpointPair<N> endpointPairIncidentNodes = incidentNodes(e);
        return Sets.difference(Sets.union(incidentEdges(endpointPairIncidentNodes.nodeU()), incidentEdges(endpointPairIncidentNodes.nodeV())), ImmutableSet.m383of((Object) e));
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Network
    public Set<E> edgesConnecting(N n, N n2) {
        Set<E> setOutEdges = outEdges(n);
        Set<E> setInEdges = inEdges(n2);
        if (setOutEdges.size() <= setInEdges.size()) {
            return Collections.unmodifiableSet(Sets.filter(setOutEdges, connectedPredicate(n, n2)));
        }
        return Collections.unmodifiableSet(Sets.filter(setInEdges, connectedPredicate(n2, n)));
    }

    private Predicate<E> connectedPredicate(final N n, final N n2) {
        return new Predicate<E>() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractNetwork.2
            @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Predicate
            public boolean apply(E e) {
                return AbstractNetwork.this.incidentNodes(e).adjacentNode(n).equals(n2);
            }
        };
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Network
    public Optional<E> edgeConnecting(N n, N n2) {
        Set<E> setEdgesConnecting = edgesConnecting(n, n2);
        int size = setEdgesConnecting.size();
        if (size == 0) {
            return Optional.empty();
        }
        if (size == 1) {
            return Optional.of(setEdgesConnecting.iterator().next());
        }
        throw new IllegalArgumentException(String.format("Cannot call edgeConnecting() when parallel edges exist between %s and %s. Consider calling edgesConnecting() instead.", n, n2));
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Network
    @NullableDecl
    public E edgeConnectingOrNull(N n, N n2) {
        return edgeConnecting(n, n2).orElse(null);
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Network
    public boolean hasEdgeConnecting(N n, N n2) {
        return !edgesConnecting(n, n2).isEmpty();
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Network
    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Network)) {
            return false;
        }
        Network network = (Network) obj;
        return isDirected() == network.isDirected() && nodes().equals(network.nodes()) && edgeIncidentNodesMap(this).equals(edgeIncidentNodesMap(network));
    }

    @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.Network
    public final int hashCode() {
        return edgeIncidentNodesMap(this).hashCode();
    }

    public String toString() {
        return "isDirected: " + isDirected() + ", allowsParallelEdges: " + allowsParallelEdges() + ", allowsSelfLoops: " + allowsSelfLoops() + ", nodes: " + nodes() + ", edges: " + edgeIncidentNodesMap(this);
    }

    private static <N, E> Map<E, EndpointPair<N>> edgeIncidentNodesMap(final Network<N, E> network) {
        return Maps.asMap(network.edges(), new Function<E, EndpointPair<N>>() { // from class: com.google.firebase.crashlytics.buildtools.reloc.com.google.common.graph.AbstractNetwork.3
            @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function, java.util.function.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply((C18143) obj);
            }

            @Override // com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Function, java.util.function.Function
            public EndpointPair<N> apply(E e) {
                return network.incidentNodes(e);
            }
        });
    }
}
