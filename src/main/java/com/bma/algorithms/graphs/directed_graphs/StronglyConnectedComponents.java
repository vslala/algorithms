package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.stdlib.StdOut;

import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

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

    private void dfs(Digraph dag, int vertex) {
        marked[vertex] = true;
        id[vertex] = count;
        for (int w: dag.adj(vertex))
            if (!marked[w])
                dfs(dag, w);
    }

    private Iterable<Integer> reversePostOrder(Digraph dag) {
        var reverseDag = dag.reverse();
        Stack<Integer> reversePostOrder = new Stack<>();
        marked = new boolean[reverseDag.vertices()];
        IntStream.range(0, reverseDag.vertices())
                .forEach(vertex ->  {
                    if (!marked[vertex]) {
                        dfsR(reverseDag, vertex, reversePostOrder);
                    }
                });
        return reversePostOrder;
    }

    private void dfsR(Digraph reverseDag, int vertex, Stack<Integer> reversePostOrder) {
        marked[vertex] = true;
        reverseDag.adj(vertex).forEach(w -> {
            if (!marked[w]) {
                dfsR(reverseDag, w, reversePostOrder);
            }
        });
        reversePostOrder.push(vertex);
    }

    /**
     * Phase 1
     * - Reverse the Graph (G-reverse)
     * - DFS Traverse the G-reverse graph to obtain the ReversePostOrder
     *
     * Traverse the graph using DFS and push the visited vertices into a stack after the call.
     * Iterate the stack to get the reverse order
     *
     * Phase 2
     * ----------
     *  - Run DFS on the original graph in the ReversePostOrder
     */
    public int count() {
        var depthFirstOrder = new DepthFirstOrder(this.dag.reverse());
        System.out.println("PRE:\t" + StreamSupport.stream(depthFirstOrder.pre().spliterator(), false)
            .map(String::valueOf).collect(Collectors.joining(" ")));

        System.out.println("POST:\t" + StreamSupport.stream(depthFirstOrder.post().spliterator(),  false)
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));

        System.out.println("R-POST:\t" + StreamSupport.stream(reversePostOrder(dag).spliterator(),false)
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));

        var reversePostOrder = List.of(
                1,0,2,4,5,3,11,9,12,10,6,7,8
        );

        for (int v: depthFirstOrder.reversePost()) {
            System.out.print(v + " ");
            if (!marked[v]) {
                dfs(dag, v);
                count += 1;
            }
        }

        return count;

    }
}
