package com.bma.algorithms.graphs.undirected_graphs;

import com.bma.BMARuntimeException;
import com.bma.algorithms.graphs.Digraph;
import com.bma.algorithms.graphs.model.Bag;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

class GraphProcessor {
    private GraphProcessor() {}

    public static int degree(Digraph graph, int vertex) {
        AtomicInteger degree = new AtomicInteger(0);
        graph.adj(vertex).forEach(adjVertex -> degree.getAndIncrement());
        return degree.intValue();
    }

    public static int maxDegree(Digraph graph) {
        AtomicInteger maxDegree = new AtomicInteger(0);
        IntStream.range(0, graph.vertices()).map(vertex -> degree(graph, vertex)).forEach(degree -> {
            if (degree > maxDegree.intValue())
                maxDegree.set(degree);
        });
        return maxDegree.intValue();
    }

    public static double averageDegree(Digraph graph) {
        return 2.0 * graph.vertices() / graph.edges();
    }

    public static int numberOfSelfLoops(Digraph graph) {
        AtomicInteger selfLoops = new AtomicInteger(0);
        IntStream.range(0, graph.vertices()).forEach(vertex -> {
            graph.adj(vertex).forEach(adjVertex -> {
                if (vertex == adjVertex) selfLoops.getAndIncrement();
            });
        });
        return selfLoops.intValue();
    }
}

class AdjacencyListGraphImpl implements Digraph {

    private Bag<Integer>[] adj;

    public AdjacencyListGraphImpl(int vertices) {
        adj = new Bag[vertices];
        for (int vertex = 0; vertex < vertices; vertex++) {
            adj[vertex] = new Bag<>();
        }
    }

    @Override
    public void addEdge(int fromVertex, int toVertex) {
        adj[fromVertex].add(toVertex);
        adj[toVertex].add(fromVertex);
    }

    @Override
    public Iterable<Integer> adj(int vertex) {
        return adj[vertex];
    }

    @Override
    public int vertices() {
        return adj.length;
    }

    @Override
    public int edges() {
        int edges = 0;
        for (int v = 0; v < vertices(); v++)
            for (int w : adj(v))
                edges++;

        return edges / 2; // edges are calculated twice e.g. v-w & w-v
    }

    @Override
    public Digraph reverse() {
        throw new BMARuntimeException("No implementation provided for reverse() in AdjacencyListGraphImpl.");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, vertices()).mapToObj(vertex -> {
            sb.append(vertex);
            return adj(vertex);
        }).forEach(adjacentVertex -> sb.append("\t- ").append(adjacentVertex).append(System.lineSeparator()));
        return sb.toString();
    }
}
