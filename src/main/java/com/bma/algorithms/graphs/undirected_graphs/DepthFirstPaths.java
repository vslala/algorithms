package com.bma.algorithms.graphs.undirected_graphs;

import com.bma.algorithms.graphs.Digraph;
import com.bma.algorithms.graphs.Paths;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.stream.Collectors;

class DepthFirstPaths implements Paths {
    private final int sourceVertex;
    private final boolean[] marked; // to check if the vertex has been visited before
    private final int[] edgeTo;
    private final StringBuilder lastSearch = new StringBuilder();

    public DepthFirstPaths(Digraph graph, int sourceVertex) {
        this.sourceVertex = sourceVertex;
        marked = new boolean[graph.vertices()];
        edgeTo = new int[graph.vertices()];
        dfs(graph, sourceVertex);
    }

    private void dfs(Digraph graph, int vertex) {
        marked[vertex] = true;                      // mark the vertex as visited
        graph.adj(vertex).forEach(adjVertex -> {    // visit each adj vertex
            if (! marked[adjVertex]) {              // check if vertex is not visited
                dfs(graph, adjVertex);              // recursively perform dfs
                edgeTo[adjVertex] = vertex;         // keep record of the visited edge
            }
        });

    }

    @Override
    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    /**
     * returns the path from source vertex to the given vertex
     * @param destination
     * @return
     */
    @Override
    public Iterable<Integer> pathTo(int destination) {
        if (! hasPathTo(destination)) return Collections.emptyList();

        Deque<Integer> path = new ArrayDeque<>();
        for (int p = destination; p != sourceVertex; p = edgeTo[p])
            path.push(p);

        path.push(destination);

        // cache string representation of this search
        lastSearch.append(path.stream().map(String::valueOf).collect(Collectors.joining("\t->\t")));

        return path;
    }

    public String toString() {
        return lastSearch.toString();
    }
}
