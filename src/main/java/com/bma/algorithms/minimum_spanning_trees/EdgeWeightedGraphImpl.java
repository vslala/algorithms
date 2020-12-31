package com.bma.algorithms.minimum_spanning_trees;

import com.bma.algorithms.graphs.Bag;
import com.bma.algorithms.graphs.EdgeWeightedGraph;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EdgeWeightedGraphImpl implements EdgeWeightedGraph {

    private int vertices;
    Bag<Edge>[] adj;
    private int edgeCount = 0;

    public EdgeWeightedGraphImpl(int vertices) {
        this.vertices = vertices;
        adj = new Bag[vertices];
        IntStream.range(0, vertices).forEach(vertex -> {
            adj[vertex] = new Bag<>();
        });
    }

    @Override
    public void addEdge(Edge edge) {
        int v = edge.either();
        int w = edge.other(v);
        adj[v].add(edge);
        adj[w].add(edge);
        edgeCount++;
    }

    @Override
    public int totalEdges() {
        return edgeCount;
    }

    @Override
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    @Override
    public Iterable<Edge> edges() {
        return Arrays.stream(adj)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public int totalVertices() {
        return vertices;
    }

    @Override
    public boolean removeEdge(Edge edge) {
        int v = edge.either();
        return adj[v].remove(edge);
    }


}
