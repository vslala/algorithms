package com.bma.algorithms.graphs;

import com.bma.algorithms.graphs.directed_graphs.EdgeWeightedDigraph;
import com.bma.algorithms.graphs.model.DirectedEdge;

import java.util.ArrayDeque;
import java.util.Deque;

public class EdgeWeightedCycleFinder {

    private final EdgeWeightedDigraph edgeWeightedDigraph;
    private final boolean[] onStack;
    private final DirectedEdge[] edgeTo;
    private final boolean[] visited;
    private Deque<DirectedEdge> cycle;

    public EdgeWeightedCycleFinder(EdgeWeightedDigraph edgeWeightedDigraph) {
        this.edgeWeightedDigraph = edgeWeightedDigraph;
        this.onStack = new boolean[edgeWeightedDigraph.totalVertices()];
        this.visited = new boolean[edgeWeightedDigraph.totalVertices()];
        this.edgeTo = new DirectedEdge[edgeWeightedDigraph.totalVertices()];

        for (int v = 0; v < edgeWeightedDigraph.totalVertices(); v++) {
            if (!visited[v]) dfs(v);
        }

    }

    /**
     * Iterate over each adjacent edge and check if it forms a cycle
     * if a cycle exists then iterate over the edges and put them in the stack
     * @param currVertex
     */
    private void dfs(int currVertex) {
        visited[currVertex] = true;
        onStack[currVertex] = true;
        for (DirectedEdge edge: edgeWeightedDigraph.adj(currVertex)) {
            int v = edge.to();
            if (hasCycle()) {
                return;
            }  else if (!visited[v]) {
                edgeTo[v] = edge;
                dfs(v);
            } else if (onStack[v]) {
                cycle = new ArrayDeque<>();
                // if there is a cycle then the same vertex will repeat and that's where we have to stop
                for (int x = currVertex; x != v; x = edgeTo[x].from()) cycle.push(edgeTo[x]);
                // this is the current edge that created the cycle
                cycle.push(edge);
            }
        }
        onStack[currVertex] = false;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }

    /**
     * Check if there exists a cycle
     * @return
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * If there exists a cycle check if that cycle results in a negative weight
     * @return
     */
    public boolean hasNegativeCycle() {
        double weight = 0;
        if (cycle != null) {
            for (DirectedEdge directedEdge : cycle) weight += directedEdge.weight();
            return weight < 0;
        }

        return false;
    }
}
