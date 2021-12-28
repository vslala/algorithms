package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.graphs.Digraph;
import com.bma.algorithms.graphs.Paths;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;

class PathMeta {
    int sourceVertex;
    boolean[] marked;
    int[] edgeTo;

    public PathMeta(int sourceVertex, int totalVertices) {
        this.sourceVertex = sourceVertex;
        marked = new boolean[totalVertices];
        edgeTo = new int[totalVertices];
    }
}

class DirectedDepthFirstPaths implements Paths {

    private final PathMeta pathMeta;

    public DirectedDepthFirstPaths(Digraph graph, int sourceVertex) {
        this.pathMeta = new PathMeta(sourceVertex, graph.vertices());
        dfs(graph, sourceVertex);
    }

    private void dfs(Digraph graph, int sourceVertex) {
        pathMeta.marked[sourceVertex] = true;
        graph.adj(sourceVertex).forEach(vertex -> {
            if (!pathMeta.marked[vertex]) {
                dfs(graph, vertex);
                pathMeta.edgeTo[vertex] = sourceVertex;
            }
        });
    }

    @Override
    public boolean hasPathTo(int vertex) {
        return pathMeta.marked[vertex];
    }

    @Override
    public Iterable<Integer> pathTo(int dest) {
        if (!hasPathTo(dest)) return Collections.emptyList();

        Deque<Integer> path = new ArrayDeque<>();
        for (int p = dest; p != pathMeta.sourceVertex; p = pathMeta.edgeTo[p])
            path.push(p);

        return path;
    }
}
