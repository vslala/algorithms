package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.graphs.Graph;

import java.util.concurrent.atomic.AtomicInteger;

public class KosarajuSharirSCC {
    private boolean[] marked;
    private int[] componentId;
    private AtomicInteger count = new AtomicInteger();

    public KosarajuSharirSCC(Graph graph) {
        marked = new boolean[graph.vertices()];
        componentId = new int[graph.vertices()];
        // retrieve the post order of the graph
        // TODO: pass the reverse graph to Depth First Order; Refer evernote;
        DepthFirstOrder depthFirstOrder = new DepthFirstOrder(graph);
        depthFirstOrder.postOrder().forEach(vertex -> {
            if (!marked[vertex]) {
                dfs(graph, vertex);
                count.incrementAndGet();
            }
        });
    }

    private void dfs(Graph graph, int vertex) {
        marked[vertex] = true;
        componentId[vertex] = count.get();
        graph.adj(vertex).forEach(adj -> {
            if (!marked[adj])
                dfs(graph, adj);
        });
    }

    public boolean stronglyConnected(int v, int w) {
        return componentId[v] == componentId[w];
    }
}
