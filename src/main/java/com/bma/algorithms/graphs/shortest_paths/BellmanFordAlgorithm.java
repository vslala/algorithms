package com.bma.algorithms.graphs.shortest_paths;

import com.bma.algorithms.graphs.directed_graphs.EdgeWeightedDigraph;
import com.bma.algorithms.graphs.model.DirectedEdge;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Bellman Ford Algorithm
 * ------------------------
 * This is a pretty simple algorithm for finding the Single source shortest path with negative weights (which Dijkstra cannot).
 * It relies on the edge relaxation algorithm (just like Dijkstra) that guarantees that the identified path will be shorter.
 * We can also say that it gives birth to Dynamic Programming design technique which is used to optimize the algorithm by trading space and the fact that we solve the sub-problems multiple times to get to the desired result.
 *
 * This algorithm is capable of finding the negative weight cycles in the graph.
 *
 * The algorithm idea is pretty simple:
 * 1. Go through all edges and relax them
 * 2. Repeat one more time
 *
 * Time Complexity:
 * -----------------
 * |    Type                        | Typical Case  | Worst Case    |
 * |    ---                         |   ---         |   ---         |
 * |    Bellman Ford                |    EV         |   EV          |
 * |    Bellman Ford (Queue Based)  |    E + V      |   EV          |
 *
 * @author varun.shrivastava
 */
class BellmanFordAlgorithm implements SingleSourceShortestPath {

    private final int source;
    private final double[] distTo;
    private final DirectedEdge[] edgeTo;

    public BellmanFordAlgorithm(EdgeWeightedDigraph digraph, int source) {
        this.source = source;
        this.distTo = new double[digraph.totalVertices()];
        this.edgeTo = new DirectedEdge[digraph.totalVertices()];

        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[source] = 0;

        for (int i = 0; i < digraph.totalVertices(); i++) {
            for (DirectedEdge edge: digraph.adj(i)) {
                relax(edge);
            }
        }

        for (int i = 0; i < digraph.totalVertices(); i++) {
            for (DirectedEdge edge: digraph.adj(i)) {
                relax(edge);
            }
        }
    }

    private void relax(DirectedEdge edge) {
        int u = edge.from();
        int v = edge.to();
        double w = edge.weight();

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
        Deque<DirectedEdge> path = new ArrayDeque<>();
        int node = v;
        while (node != source) {
            DirectedEdge currEdge = edgeTo[node];
            path.push(currEdge);
            node = currEdge.from();
        }

        return path;
    }

    @Override
    public boolean hasPathTo(int v) {
        return distTo[v] != Double.POSITIVE_INFINITY;
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

    @Override
    public boolean hasNegativeCycle() {
        return false;
    }

    @Override
    public Iterable<DirectedEdge> negativeCycle() {
        return null;
    }
}
