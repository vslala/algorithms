package com.bma.algorithms.graphs.directed_graphs;

import java.util.Stack;
import java.util.stream.IntStream;

/**
 * Definition
 * --------------
 * Vertices v and w are strongly connected if there
 * is a directed path from v to w and a directed path from w to v.
 *
 * Key Property
 * --------------
 * Strong connectivity is an equivalence relation.
 *  - v is  strongly connected to v.
 *  - if v is strongly connected to w, then  w is strongly connected to v.
 *  - if v is strongly connected to w and w to x, then v  is strongly connected to x.
 *
 *  Def. A strong component is a maximal subset of strongly connected vertices.
 *
 *  Applications
 *  -------------
 *  - Ecological Food Web Graph (Vertex = Species; Edge = from producer to consumer)
 *      Strong Component. Subset of species with common energy flow
 *  - Software Modules (Vertex = Software Module; Edge = from module to dependency)
 *      Strong Component. subset of mutually interacting modules
 *          Approach 1: Package Strong Components together
 *          Approach 2: Use to improve design!
 */

public class StronglyConnectedComponents {
    /**
     * Algorithm. Kosaraju-Sharir
     * Phase 1: Compute Reverse PostOrder in G-reverse
     * Phase 2: Run DFS in G, visiting unmarked vertices in reverse postorder of G-reverse.
     */
    private Digraph dag;
    private boolean marked[];
    private int count = 0;
    private int id[];

    public StronglyConnectedComponents(Digraph dag) {
        this.dag = dag;
        marked = new boolean[dag.vertices()];
        id = new int[dag.vertices()];
    }

    /**
     * Phase 1
     *  - Reverse the Graph (G-reverse)
     *  - DFS Traverse the G-reverse graph to obtain the ReversePostOrder
     *
     * Traverse the graph using DFS and push the visted vertices into a stack.
     * Iterate the stack to get the reverse order
     *
     * @return
     */
    private Stack<Integer> reversePostOrder() {
        Digraph reverse = dag.reverse();
        Stack<Integer> postOrder = new Stack<>();
        for (int vertex = 0; vertex < reverse.vertices(); vertex++) {
            if (!marked[vertex])
                dfs(reverse, vertex, postOrder);
        }

        IntStream.range(0, dag.vertices()).forEach(vertex -> marked[vertex]  = false);
        return postOrder;
    }

    private void dfs(Digraph reverse, int vertex, Stack<Integer> postOrder) {
        marked[vertex] = true;
        for (int w: reverse.adj(vertex)) {
            if (!marked[w])
                dfs(reverse, w, postOrder);
        }
        postOrder.push(vertex);
    }

    private void dfs(Digraph dag, int vertex) {
        marked[vertex] = true;
        id[vertex] = count;
        for (int w: dag.adj(vertex))
            if (!marked[w])
                dfs(dag, w);
    }

    /**
     * Phase 2
     * ----------
     *  - Run DFS on the original graph in the ReversePostOrder
     */
    public int[] connectedComponents() {
        for (int v: reversePostOrder()) {
            if (!marked[v]) {
                dfs(dag, v);
                count++;
            }
        }

        return id;
    }
}
