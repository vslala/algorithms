package com.bma.problemsolving.leetcode.java.trees.minimumspanningtree;

import com.bma.algorithms.disjointsets.DisjointSet;
import com.bma.algorithms.graphs.minimum_spanning_trees.EdgeWeightedGraph;
import com.bma.algorithms.graphs.model.Edge;
import com.bma.algorithms.sort.elementary.Util;

import java.util.PriorityQueue;

/**
 * Min Cost to Connect All Points
 * --------------------------------
 * You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 * Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
 *
 * @author varun.shrivastava
 */
class MinCostToConnectAllPoints {

    private EdgeWeightedGraph edgeWeightedGraph;
    public int minCostConnectPoints(int[][] points) {
        int totalVertices = points.length;
        edgeWeightedGraph = EdgeWeightedGraph.createUnDirectedGraph(totalVertices);

        // constructing a graph where every vertex is connected to every other vertex with a given weight
        for (int u = 0; u < totalVertices; u++) {
            int[] point1 = points[u];
            for (int v = u + 1; v < totalVertices; v++) {
                int[] point2 = points[v];
                int w = Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);  // manhattan distance equation

                Edge edge = new Edge(u, v, w);
                edgeWeightedGraph.addEdge(edge);
            }
        }

        PriorityQueue<Edge> minHeap = new PriorityQueue<>();
        edgeWeightedGraph.edges().forEach(minHeap::add);

        int result = 0;
        int edgeCount = 0;
        DisjointSet quickUnion = DisjointSet.quickUnion(totalVertices);
        while (!minHeap.isEmpty() && edgeCount != totalVertices - 1) {
            Edge edge = minHeap.remove();
            int u = edge.either();
            int v = edge.other(u);

            Util.println(edge);
            if (!quickUnion.connected(u, v)) {
                quickUnion.union(u, v);
                result += edge.getWeight();
                edgeCount += 1;
            }
        }

        return result;
    }
}
