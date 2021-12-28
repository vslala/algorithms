package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.graphs.Digraph;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

@RequiredArgsConstructor
class TopologicalSort {
    /**
     * Only works with DAG  (Directed Acyclic Graph)
     * Topological order will be in a way that all the nodes
     * points to the top.
     *
     * Steps
     * 1. Run DFS
     * 2. Return vertices in reverse PostOrder
     *
     * Proposition: Reverse DFS PostOrder of a DAG is a Topological order.
     *
     * Applications
     *  - Cycle Detection (Java compiler does cycle detection for circular dependencies)
     *  - SpreadSheet Recalculation (Microsoft Excel does cycle detection)
     */
    private final Digraph dag;
    private boolean marked[];
    private Stack<Integer> reversePost;

    public List<Integer> sort() {
        marked = new boolean[dag.vertices()];
        reversePost  = new Stack<>();

        IntStream.range(0, dag.vertices()).forEach(vertex -> {
            if (!marked[vertex]) dfs(dag, vertex);
        });

        List<Integer> sorted = new ArrayList<>();
        while (!reversePost.isEmpty()) {
            sorted.add(reversePost.pop());
        }

        return sorted;
    }

    private void dfs(Digraph dag, int vertex) {
        marked[vertex] = true;
        for (int w: dag.adj(vertex))
            if (!marked[w])
                dfs(dag, w);

        reversePost.push(vertex);
    }
}
