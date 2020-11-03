package com.bma.algorithms.minimum_spanning_trees;

import com.bma.algorithms.graphs.EdgeWeightedGraph;
import com.bma.algorithms.priorityqueues.MinHeap;
import com.bma.algorithms.sort.elementary.Util;

public class KrushkalAlgorithm {
    private final EdgeWeightedGraph graph;
    private final MinHeap<Edge> minPQ = new MinHeap<>();
    private final EdgeWeightedGraph mst;

    public KrushkalAlgorithm(EdgeWeightedGraph graph) {
        this.graph = graph;
        mst = new EdgeWeightedGraphImpl(graph.totalVertices());
    }

    public EdgeWeightedGraph spanningTree() {
        graph.edges().forEach(minPQ::add);
        while (! minPQ.isEmpty() && mst.totalEdges() < graph.totalVertices()) {
            final Edge edge = minPQ.remove();
            mst.addEdge(edge);
            if (new EdgeWeightedGraphProcessor<>().detectCycle(mst, edge.either(), mst.totalVertices())) {
                mst.removeEdge(edge);
            }
        }
        return mst;
    }

}
