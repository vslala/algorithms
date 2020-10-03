package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.graphs.Graph;
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

public class DirectedDepthFirstPaths implements Paths {

    private PathMeta pathMeta;

    public DirectedDepthFirstPaths(Graph graph, int sourceVertex) {
        this.pathMeta = pathMeta = new PathMeta(sourceVertex, graph.vertices());
        dfs(graph, sourceVertex);
    }

    private void dfs(Graph graph, int sourceVertex) {
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
