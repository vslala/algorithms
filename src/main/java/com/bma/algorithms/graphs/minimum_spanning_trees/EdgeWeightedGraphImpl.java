package com.bma.algorithms.graphs.minimum_spanning_trees;

import com.bma.algorithms.graphs.model.Bag;
import com.bma.algorithms.graphs.model.Edge;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class EdgeWeightedGraphImpl implements EdgeWeightedGraph {

    private final int vertices;
    private final Bag<Edge>[] adj;
    private int edgeCount = 0;

    public EdgeWeightedGraphImpl(int vertices) {
        this.vertices = vertices;
        adj = new Bag[vertices];
        IntStream.range(0, vertices).forEach(vertex -> {
            adj[vertex] = new Bag<>();
        });
    }

    @SneakyThrows
    public EdgeWeightedGraphImpl(Path filePath) {
        this.vertices = Files.readAllLines(filePath)
                .stream()
                .map(line -> line.split(","))
                .map(parts -> parts[0].split("->"))
                .map(parts -> Math.max(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])))
                .max(Comparator.comparingInt(x->x))
                .orElseThrow() + 1;
        adj = new Bag[vertices];
        IntStream.range(0, vertices).forEach(vertex -> {
            adj[vertex] = new Bag<>();
        });

        Files.readAllLines(filePath)
                .stream()
                .map(line -> line.split(","))
                .forEach(parts -> {
                    int p = Integer.parseInt(parts[0].split("->")[0]);
                    int q = Integer.parseInt(parts[0].split("->")[1]);
                    double w = Double.parseDouble(parts[1]);
                    addEdge(new Edge(p, q, w));
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
