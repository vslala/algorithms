package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.graphs.Digraph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;

public class DynamicDigraph implements Digraph {
    private Map<Integer, Set<Integer>> vertices = new HashMap<>();
    private final Path graphPath;

    public DynamicDigraph(Path graphPath) throws IOException {
        this.graphPath = graphPath;
        initVertices(graphPath);
        initEdges(graphPath);
    }

    private void initEdges(Path graphPath) throws IOException {
        Files.readAllLines(graphPath).stream()
                .filter(line -> !line.isEmpty())
                .forEach(line -> {
                    String[] parts = line.trim().split("->");
                    final int v1 = parseInt(parts[0]);
                    final int v2 = parseInt(parts[1]);
                    vertices.get(v1).add(v2);
                });
    }

    private void initVertices(Path graphPath) throws IOException {
        Files.readAllLines(graphPath).stream()
                .filter(line -> !line.isEmpty())
                .forEach(line -> {
                    String[] parts = line.trim().split("->");
                    final int v1 = parseInt(parts[0]);
                    final int v2 = parseInt(parts[1]);
                    vertices.put(v1, new HashSet<>());
                    vertices.put(v2, new HashSet<>());
                });
    }

    @Override
    public void addEdge(int fromVertex, int toVertex) {
        vertices.get(fromVertex).add(toVertex);
    }

    @Override
    public Iterable<Integer> adj(int vertex) {
        return vertices.get(vertex);
    }

    @Override
    public int vertices() {
        return vertices.size();
    }

    @Override
    public int edges() {
        AtomicInteger edges = new AtomicInteger();
        vertices.forEach((v, adj) -> edges.addAndGet(adj.size()));
        return edges.get();
    }
}
