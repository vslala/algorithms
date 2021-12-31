package com.bma.algorithms.graphs.shortest_paths;

import com.bma.algorithms.graphs.directed_graphs.EdgeWeightedDigraph;
import com.bma.algorithms.graphs.model.DirectedEdge;

import java.util.*;

/**
 * Dijkstra Algorithm
 * -------------------
 * This is an elegant algorithm for computing Single Source Shortest Paths in a graph with huge level of applications.
 * This algorithm is based on the correctness of edge relaxation. Relaxation is the heart of this algorithm.
 * The overall runtime of the algorithm is quite heavily dependent on the underlying MinHeap implementation.
 * The best results can be seen by Fibonnaci Heap.
 * <p>
 * Time Complexity
 * ---------------
 * Fibonacci Heap   | O(E + V logV)
 * Binary Heap      | O(V + E logV)
 * <p>
 * Space Complexity: O(V)
 * distTo   | V
 * edgeTo   | V
 * <p>
 * The idea is simple:
 * 1. Mark all the vertices as POSITIVE_INFINITY
 * 2. Mark the source edge distTo[source] = 0.0 (this is the starting point)
 * 3. Traverse all the connected edges and 'Relax' them
 * 4. Then pick the next min-weight edge from the heap and keep relaxing until all the edges have been visited or destination has been reached
 *
 * @author varun.shrivastava
 */
class DijkstraAlgorithm implements SingleSourceShortestPath {

    private final EdgeWeightedDigraph digraph;
    private final int source;
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private Set<DirectedEdge> visited;

    public DijkstraAlgorithm(EdgeWeightedDigraph digraph, int source) {
        this.digraph = digraph;
        this.source = source;

        this.distTo = new double[digraph.totalVertices()];
        this.edgeTo = new DirectedEdge[digraph.totalEdges()];
        this.visited = new HashSet<>();

        // initialize all the vertices to infinity except the source vertex
        for (int v = 0; v < digraph.totalVertices(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = 0;

        // Add source edge to the min-heap priority queue
        PriorityQueue<DirectedEdge> minHeap = new PriorityQueue<>();
        minHeap.add(new DirectedEdge(source, source, 0.0));

        // iterate over the heap and relax each adjacent edge
        while (!minHeap.isEmpty()) {
            DirectedEdge edge = minHeap.remove();
            if (!visited.contains(edge)) {
                Iterable<DirectedEdge> connectedEdges = digraph.adj(edge.to());
                for (DirectedEdge nextEdge : connectedEdges) {
                    relax(nextEdge);
                    minHeap.add(nextEdge);
                }

                visited.add(edge);
            }
        }
    }

    /**
     * Edge relaxation will make sure that the value of distTo[v] never increases
     * However, it will always be a monotone decreasing
     *
     * @param edge edge to relax
     */
    private void relax(DirectedEdge edge) {
        int u = edge.from();
        int v = edge.to();
        double w = edge.weight();

        // if we found a smaller distance to the incident edge 'v'
        // then update its distance with the new shorter distance
        if (distTo[v] > distTo[u] + w) {
            distTo[v] = distTo[u] + w;
            edgeTo[v] = edge;
        }
    }

    @Override
    public double distTo(int v) {
        return distTo[v];
    }

    @Override
    public Iterable<DirectedEdge> pathTo(int v) {
        DirectedEdge node = edgeTo[v];
        Deque<DirectedEdge> path = new ArrayDeque<>();
        path.add(node);

        DirectedEdge parent = node;
        while (parent.from() != source) {
            parent = edgeTo[node.from()];
            path.add(parent);
            node = parent;
        }

        return path;
    }

    @Override
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    @Override
    public double farthestReachableDistance() {
        double farthest = -1;
        for (double to : distTo) {
            if (to == Double.POSITIVE_INFINITY) continue;
            farthest = Math.max(to, farthest);
        }

        return farthest;
    }
}
