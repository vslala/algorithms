package com.bma.algorithms.graphs.minimum_spanning_trees;

import com.bma.algorithms.graphs.model.Edge;
import com.bma.algorithms.priorityqueues.MinHeap;

/**
 * Prim's Lazy Algorithm for computing Minimum Spanning Tree
 * ----------------------------------------------------------
 * Another algorithm for finding the minimum spanning tree from a griven graph.
 * This is a lazy algorithm in a sense that the next min-edge is computed lazily.
 * The idea is pretty simple-
 * 1. Visit all the incident edges from the vertex v and add them to priority queue (min heap)
 * 2. Delete the min-edge from the priority queue and check if it has already been processed or marked
 * 3. If the edge has not been processed before than add it to the MST
 * 4. Repeat the process until the queue is empty or total edge count reaches to V - 1.
 *
 * @author varun.shrivastava
 */
class PrimsAlgorithmLazy {

    private boolean[] marked;
    private MinHeap<Edge> pq = new MinHeap<>();
    private EdgeWeightedGraph mst;

    public PrimsAlgorithmLazy(EdgeWeightedGraph graph, int sourceVertex) {
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
    }

    public PrimsAlgorithmLazy(EdgeWeightedGraph graph) {
        this(graph, 0);
    }

    public EdgeWeightedGraph minimumSpanningTree() {
        return mst;
    }

    private void visit(EdgeWeightedGraph graph, int sourceVertex) {
        marked[sourceVertex] = true;
        for (Edge edge : graph.adj(sourceVertex)) {
            if (!marked[edge.other(sourceVertex)])
                pq.add(edge);
        }
    }

    public double minWeightOfSpanningTree() {
        double minWeight = 0.0d;
        for (Edge edge : mst.edges()) {
            minWeight += edge.getWeight();
        }

        return minWeight;
    }
}
