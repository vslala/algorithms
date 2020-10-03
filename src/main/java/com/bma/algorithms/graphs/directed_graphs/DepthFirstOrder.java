package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.graphs.Graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

public class DepthFirstOrder {

    private Graph graph;
    private boolean[] marked;
    private Deque<Integer> reversePost;

    public DepthFirstOrder(Graph graph) {
        reversePost = new ArrayDeque<>();
        this.graph = graph;
        marked = new boolean[graph.vertices()];
        IntStream.range(0, graph.vertices())
                .forEach(vertex -> {
                    if (!marked[vertex])
                        dfs(graph, vertex);
                });
    }

    private void dfs(Graph graph, int vertex) {
        marked[vertex] = true;
        graph.adj(vertex).forEach(adj -> {
            if (!marked[adj])
                dfs(graph, adj);
        });
        reversePost.push(vertex);
    }


    public Deque<Integer> postOrder() {
        return reversePost;
    }
}
