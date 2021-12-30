package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.graphs.model.Bag;
import com.bma.algorithms.graphs.model.DirectedEdge;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
class EdgeWeightedDigraphImpl implements EdgeWeightedDigraph {
    List<Bag<DirectedEdge>> adj;
    int totalVertices;
    int totalEdges;
    int[] inDegree;
    int[] outDegree;

    @SneakyThrows
    public EdgeWeightedDigraphImpl(Path filePath) {
        List<String> allLines = Files.readAllLines(filePath);
        init(Integer.parseInt(allLines.get(0)));

        for (int i = 1; i < allLines.size(); i++) {
            String[] parts = allLines.get(i).split(",");
            DirectedEdge directedEdge = new DirectedEdge(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()), Double.parseDouble(parts[2].trim()));
            addEdge(directedEdge);
        }

    }

    public EdgeWeightedDigraphImpl(int vertices) {
        init(vertices);
    }

    private void init(int vertices) {
        this.totalVertices = vertices;
        this.inDegree = new int[totalVertices];
        this.outDegree = new int[totalVertices];
        this.adj = new ArrayList<>(totalVertices);
        IntStream.range(0, totalVertices)
                .forEach(vertex -> adj.add(new Bag<>()));
    }

    @Override
    public int totalVertices() {
        return totalVertices;
    }

    @Override
    public int totalEdges() {
        return totalEdges;
    }

    @Override
    public void addEdge(DirectedEdge e) {
        adj.get(e.from()).add(e);
        outDegree[e.from()]++;
        inDegree[e.to()]++;
        totalEdges++;
    }

    @Override
    public Iterable<DirectedEdge> adj(int v) {
        return adj.get(v);
    }

    @Override
    public int outDegree(int v) {
        return outDegree[v];
    }

    @Override
    public int inDegree(int v) {
        return inDegree[v];
    }

    @Override
    public Iterable<DirectedEdge> edges() {
        List<DirectedEdge> edges = new ArrayList<>();
        for (Bag<DirectedEdge> directedEdges : adj) {
            edges.addAll(directedEdges);
        }

        return edges;
    }
}
