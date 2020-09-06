package com.bma.algorithms.undirected_graphs;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class BreadthFirstPaths implements Paths {

    private final int sourceVertext;
    private final GraphApi graph;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distance;
    private StringBuilder lastSearch = new StringBuilder();

    public BreadthFirstPaths(GraphApi graph, int sourceVertex) {
        this.graph = graph;
        this.sourceVertext = sourceVertex;
        marked = new boolean[graph.vertices()];
        edgeTo = new int[graph.vertices()];
        bfs(graph, sourceVertex);
    }

    private void bfs(GraphApi graph, int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        marked[vertex] = true;
        while (! queue.isEmpty()) {
            int currVertex = queue.remove();
            graph.adj(currVertex).forEach(adjVertex -> {
                if (! marked[adjVertex]) {
                    queue.add(adjVertex);
                    marked[adjVertex] = true;
                    edgeTo[adjVertex] = currVertex;
                }
            });
        }
    }

    @Override
    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    @Override
    public Iterable<Integer> pathTo(int destination) {
        if (!hasPathTo(destination)) return Collections.emptyList();

        var path = new Stack<Integer>();
        for (int p = destination; p != sourceVertext; p = edgeTo[p]) {
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
