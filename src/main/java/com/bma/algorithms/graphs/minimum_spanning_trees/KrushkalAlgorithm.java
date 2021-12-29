package com.bma.algorithms.graphs.minimum_spanning_trees;

import com.bma.algorithms.disjointsets.DisjointSet;
import com.bma.algorithms.graphs.model.Edge;

import java.util.PriorityQueue;

/**
 * Krushkal Algorithm
 * ---------------------
 * This is an elegant algorithm (can be proofed with the special case of greedy algorithm) which is used to find the
 * minimum spanning tree in a graph in Elog(E) time
 * The idea is pretty simple:
 * 1. Add all the edges to a MinHeap (minimum edge weight first)
 * 2. Pick next minimum edge from the heap and check if it will create a cycle (cycle detection can be done in log(v) using QuickFind)
 * 3. If no cycle is formed, then add that edge to the Minimum Spanning Tree
 *
 * Time Complexity:
 * ---------------
 * Operation        | Frequency |   Time Per Operation
 * ------------------------------------------------------------ |
 * Build MinHeap    | 1         |   E                           |
 * delete-min       | E         |   log E                       |
 * union            | V         |   log*V (* is for ackerman)   |
 * connected        | E         |   log*V (* is for ackerman)   |
 *
 * @author varun.shrivastava
 */
class KrushkalAlgorithm {
    private final EdgeWeightedGraph mst;

    public KrushkalAlgorithm(EdgeWeightedGraph graph) {
        mst = new EdgeWeightedGraphImpl(graph.totalVertices());
        PriorityQueue<Edge> minPq = new PriorityQueue<>();
        graph.edges().forEach(minPq::add);

        DisjointSet quickFind = DisjointSet.quickFind(graph.totalVertices());
        while (!minPq.isEmpty() && mst.totalEdges() != graph.totalVertices() - 1) {
            Edge edge = minPq.remove();
            int p = edge.either();
            int q = edge.other(p);
            if (!quickFind.connected(p, q)) {
                mst.addEdge(edge);
                quickFind.union(p, q);
            }
        }
    }

    public EdgeWeightedGraph spanningTree() {
        return mst;
    }

}
