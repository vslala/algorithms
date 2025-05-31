package com.bma.problemsolving.leetcode.java.dailychallenge;

import java.util.*;

/**
 * Solves “Maximize the Number of Target Nodes After Connecting Trees II” by:
 * 1. Computing, for each tree, how many nodes lie at even vs. odd distance from node 0.
 * 2. Recording each node’s parity (even/odd distance) in a map.
 * 3. On tree 1, for each node i, summing its own parity-count with the best parity-count from tree 2.
 *
 * <p>Time complexity: O(n₁ + n₂), where n₁ and n₂ are the sizes of tree 1 and tree 2 (each is edges.length+1).
 * Uses two BFS traversals and O(n) extra space for adjacency lists, visited flags, and parity maps.</p>
 *
 * @author Varun Shrivastava
 * @github  www.github.com/vslala
 * @date    29/05/2025
 */
class MaximumNumberOfTargetNodesAfterConnectingTreePartTwo {

    /**
     * Holds the count of even-level and odd-level nodes from a BFS,
     * plus a map of each node’s individual parity label.
     *
     * @param evenCount   number of nodes at even distance from root
     * @param oddCount    number of nodes at odd distance from root
     * @param evenOddDict map from node index to its parity label ("even" or "odd")
     */
    private record Pad(int evenCount, int oddCount, Map<Integer, String> evenOddDict) {}

    /**
     * For two separate trees (given as edge arrays), computes for each node in the first tree
     * the maximum possible number of “target nodes” (nodes at even distance from the connection point)
     * after linking exactly one node in tree 1 to one node in tree 2.
     *
     * <p>Steps:</p>
     * <ol>
     *   <li>Compute {@link Pad} for tree 1 and tree 2 using {@link #countEvenOdd}.</li>
     *   <li>Find the best parity-count in tree 2: {@code max(count2.evenCount, count2.oddCount)}.</li>
     *   <li>For each node i in tree 1, if i is even-level use {@code count1.evenCount}, else {@code count1.oddCount},
     *       then add the best tree 2 value.</li>
     * </ol>
     *
     * @param edges1 adjacency list of tree 1, as n₁–1 edges over nodes [0..n₁−1]
     * @param edges2 adjacency list of tree 2, as n₂–1 edges over nodes [0..n₂−1]
     * @return length-n₁ array where result[i] = maximum target-node count when connecting at node i
     */
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        Pad count1 = countEvenOdd(edges1);
        Pad count2 = countEvenOdd(edges2);

        // best number of targets we can get on the second tree
        int best2 = Math.max(count2.evenCount, count2.oddCount);

        int n = edges1.length + 1;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            // choose the parity-count from tree1 for node i, then add best2
            String parity = count1.evenOddDict.get(i);
            int base = parity.equals("even") ? count1.evenCount : count1.oddCount;
            res[i] = base + best2;
        }

        return res;
    }

    /**
     * Builds an adjacency list from the given edge array, runs a BFS from node 0,
     * and records:
     * <ul>
     *   <li>How many nodes appear at even distance (level) vs. odd distance;</li>
     *   <li>A map of each node’s own parity label (“even” or “odd”).</li>
     * </ul>
     *
     * @param edges n−1 edges connecting nodes [0..n−1] in a tree
     * @return a {@link Pad} object containing the even/odd counts and per-node labels
     */
    private Pad countEvenOdd(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> adj = this.buildAdjListMatrix(edges, n);

        Map<Integer, String> parityMap = new HashMap<>(n);
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited[0] = true;

        int evenCount = 0, oddCount = 0;
        int level = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                assert !q.isEmpty();
                int node = q.poll();

                // tally and label
                if ((level & 1) == 0) {
                    evenCount++;
                    parityMap.put(node, "even");
                } else {
                    oddCount++;
                    parityMap.put(node, "odd");
                }

                // enqueue unvisited neighbors
                for (int nei : adj.get(node)) {
                    if (!visited[nei]) {
                        visited[nei] = true;
                        q.offer(nei);
                    }
                }
            }
            level++;
        }

        return new Pad(evenCount, oddCount, parityMap);
    }

    private List<List<Integer>> buildAdjListMatrix(int[][] edges, int n) {
        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        return adj;
    }
}
