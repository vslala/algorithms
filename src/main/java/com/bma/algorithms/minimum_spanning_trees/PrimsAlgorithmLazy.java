package com.bma.algorithms.minimum_spanning_trees;

import com.bma.algorithms.graphs.EdgeWeightedGraph;
import com.bma.algorithms.priorityqueues.MinHeap;

public class PrimsAlgorithmLazy {

    private boolean[] marked;
    private MinHeap<Edge> pq = new MinHeap<>();
    private EdgeWeightedGraph mst;

    public EdgeWeightedGraph mst(EdgeWeightedGraph graph, int sourceVertex) {
        marked = new boolean[graph.totalVertices()];
        mst = new EdgeWeightedGraphImpl(graph.totalVertices());

        visit(graph,  sourceVertex);

        while (!pq.isEmpty())  {
            Edge e = pq.remove();
            int v = e.either();
            int w = e.other(v);

            if (marked[v] && marked[w]) continue;
            mst.addEdge(e);

            if (!marked[v]) visit(graph, v);
            if (!marked[w]) visit(graph, w);
        }

        return mst;
    }

    private void visit(EdgeWeightedGraph graph, int sourceVertex) {
        marked[sourceVertex] = true;
        for (Edge edge : graph.adj(sourceVertex)) {
            if (!marked[edge.other(sourceVertex)])
                pq.add(edge);
        }
    }
}
