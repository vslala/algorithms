package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.graphs.Bag;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class AdjacencyListDigraphImpl implements Digraph {

    private final int vertices;
    private final Bag<Integer>[] bags;

    public AdjacencyListDigraphImpl(int vertices) {
        this.vertices = vertices;
        this.bags = new Bag[vertices];
        IntStream.range(0, vertices).forEach(vertex -> {
            bags[vertex] = new Bag<>();
        });
    }

    public AdjacencyListDigraphImpl(int vertices, Path dataFile) throws IOException {
        this.vertices = vertices;
        this.bags = new Bag[vertices];
        IntStream.range(0, vertices).forEach(vertex -> {
            bags[vertex] = new Bag<>();
        });

        Files.readAllLines(dataFile).forEach(line -> {
            String[] edge = line.split("->");
            addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
        });
    }

    @Override
    public Digraph reverse() {
        var reverse = new AdjacencyListDigraphImpl(this.vertices);
        for (int vertex = 0; vertex < this.vertices; vertex++) {
            for (int w: adj(vertex)) {
                reverse.addEdge(w, vertex);
            }
        }
        return reverse;
    }

    @Override
    public void addEdge(int fromVertex, int toVertex) {
        bags[fromVertex].add(toVertex);
    }

    @Override
    public Iterable<Integer> adj(int vertex) {
        return bags[vertex];
    }

    @Override
    public int vertices() {
        return vertices;
    }

    @Override
    public int edges() {
        return (int) Arrays.stream(bags).mapToLong(ArrayList::size).sum();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, vertices).forEach(vertex -> {
            sb.append(vertex).append(System.lineSeparator());
            bags[vertex].forEach(directedTo -> sb.append("\t->").append(directedTo).append(System.lineSeparator()));
        });
        return sb.toString();
    }
}
