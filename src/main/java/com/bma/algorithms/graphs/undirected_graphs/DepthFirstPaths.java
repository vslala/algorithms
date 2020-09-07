package com.bma.algorithms.graphs.undirected_graphs;

import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;

public class DepthFirstPaths implements Paths {
    private Graph graph;
    private int sourceVertex;
    private boolean[] marked; // to check if the vertex has been visited before
    private int[] edgeTo;
    private StringBuilder lastSearch = new StringBuilder();

    public DepthFirstPaths(Graph graph, int sourceVertex) {
        this.graph = graph;
        this.sourceVertex = sourceVertex;
        marked = new boolean[graph.vertices()];
        edgeTo = new int[graph.vertices()];
        dfs(graph, sourceVertex);
    }

    private void dfs(Graph graph, int vertex) {
        marked[vertex] = true;  // mark the vertex as visited
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

    @Override
    public Iterable<Integer> pathTo(int destination) {
        if (! hasPathTo(destination)) return Collections.emptyList();

        Stack<Integer> path = new Stack<>();
        for (int p = destination; p != sourceVertex; p = edgeTo[p]) {
            path.push(p);
        }
        path.push(destination);

        // cache string representation of this search
        lastSearch.append(path.stream().map(String::valueOf).collect(Collectors.joining("\t->\t")));

        return path;
    }

    public String toString() {
        return lastSearch.toString();
    }
}
