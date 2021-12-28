package com.bma.algorithms.graphs.directed_graphs;

import java.util.*;

class PathFinder {

    private final Map<String, List<String>> graph;
    private final String source;
    private Set<String> visited;
    private Map<String, String> edgeTo;

    public PathFinder(Map<String, List<String>> graph, String source) {
        this.graph = graph;
        this.source =  source;
        this.visited = new HashSet<>();
        this.edgeTo = new HashMap<>();
    }

    public List<String> findPath(String destination) {
        traverse(source);
        return computePath(destination);
    }

    private List<String> computePath(String destination) {
        var path = new LinkedList<String>();
        if (visited.contains(destination))
            for (String p = destination; !p.equals(source); p = edgeTo.get(p)) path.push(p);

        return path;
    }

    private void traverse(String vertex) {
        visited.add(vertex);
        graph.get(vertex).forEach(adj -> {
            if (! visited.contains(adj)) {
                traverse(adj);
                edgeTo.put(adj, vertex);
            }
        });
    }



}
