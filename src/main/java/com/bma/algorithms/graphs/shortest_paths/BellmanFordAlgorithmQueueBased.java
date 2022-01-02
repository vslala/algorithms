package com.bma.algorithms.graphs.shortest_paths;

import com.bma.algorithms.graphs.EdgeWeightedCycleFinder;
import com.bma.algorithms.graphs.directed_graphs.EdgeWeightedDigraph;
import com.bma.algorithms.graphs.model.DirectedEdge;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Bellman-Ford Algorithm (Queue Based)
 * --------------------------------------
 * The idea of a queue based implementation is that only add the edges whose weights are updated during relaxation.
 * If we compare this with the simple implementation, there we were iterating over every edge twice and relaxing them,
 * but only a few edges were needed to be relaxed in the second iteration. Queue based implementation only relaxes required edges
 * that can never go more than E + V. If they are being relaxed more than V - 1 times that means there exists a negative cycle.
 *
 * The idea is simple:
 * 1. Add the starting vertex on the queue
 * 2. Iterate over all the adjacent edges connected to that vertex 'v'
 * 3. Relax each connected edge
 * 4. Check if the edge is not already on the queue, then add it to the queue (this check is important otherwise we might end-up processing the same edge multiple times)
 * 5. At the end of the queue, the shortest paths have been calculated.
 *
 * Cycle Detection:
 * This is another important feature of the Bellman-Ford algorithm. It is capable of detecting negative cycles (if there are any) and terminates the program.
 *
 * Time Complexity:
 * -----------------
 * Typical Case: E + V
 * Worst Case: EV
 *
 * @author varun.shrivastava
 */
class BellmanFordAlgorithmQueueBased implements SingleSourceShortestPath {
    private final double[] distTo;
    private final DirectedEdge[] edgeTo;
    private final Deque<Integer> queue;
    private final boolean[] onQ;
    private final int source;
    private Iterable<DirectedEdge> cycle;

    public BellmanFordAlgorithmQueueBased(EdgeWeightedDigraph digraph, int source) {
        this.source = source;
        this.distTo = new double[digraph.totalVertices()];
        this.edgeTo = new DirectedEdge[digraph.totalVertices()];
        this.onQ = new boolean[digraph.totalVertices()];
        this.queue = new ArrayDeque<>();

        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[source] = 0;

        queue.offer(source);
        onQ[source] = true;

        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.poll();
            onQ[v] = false;

            for (DirectedEdge connectedEdge: digraph.adj(v)) {
                relax(connectedEdge);
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
            if (!onQ[v]) {
                queue.offer(v);
                onQ[v] = true;
            }
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
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    @Override
    public double farthestReachableDistance() {
        double farthest = 0;
        for (double distSoFar : distTo) {
            if (distSoFar == Double.POSITIVE_INFINITY) continue;
            farthest = Math.max(farthest, distSoFar);
        }

        return farthest;
    }

    @Override
    public boolean hasNegativeCycle() {
        int n = edgeTo.length;
        EdgeWeightedDigraph spt = EdgeWeightedDigraph.createDigraph(n);
        for (DirectedEdge directedEdge : edgeTo) if (directedEdge != null) spt.addEdge(directedEdge);

        EdgeWeightedCycleFinder cycleFinder = new EdgeWeightedCycleFinder(spt);
        if (cycleFinder.hasNegativeCycle()) cycle = cycleFinder.cycle();

        return cycleFinder.hasNegativeCycle();
    }

    @Override
    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }
}
