package com.bma.problemsolving.leetcode.java.dailychallenge;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date   31/05/2025
 */
class SnakesAndLadders {

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        List<Integer> flatBoard = flattenBoard(board, n);
//        HashMap<Integer, List<Integer>> graph = buildAdjGraph(flatBoard);

//        AtomicInteger shortestPath = new AtomicInteger(Integer.MAX_VALUE);
//        HashSet<Integer> visited = new HashSet<>();
//        visited.add(0); // we start on “square 1”, which is index 0 in flatBoard
//        dfs(graph, /*node=*/0, /*end=*/flatBoard.size() - 1, visited, /*depth=*/0, shortestPath);
        int path = bfs(board.length, flatBoard);

        return path;
    }

    private int bfs(int n, List<Integer> flat) {
        int target = n * n - 1;
        boolean[] visited = new boolean[n * n];
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited[0] = true;
        int moves = 0;

        while (!q.isEmpty()) {
            int levelSize = q.size();
            // Process all nodes at current 'moves' distance
            for (int i = 0; i < levelSize; i++) {
                int cur = q.poll();
                if (cur == target) {
                    return moves;
                }

                // Try all dice throws from 1 to 6
                for (int die = 1; die <= 6; die++) {
                    int nxt = cur + die;
                    if (nxt > target) break;

                    // If there's a snake or ladder, jump to (flat[nxt] - 1);
                    // otherwise stay at nxt.
                    int destination = (flat.get(nxt) == -1) ? nxt : (flat.get(nxt) - 1);

                    if (!visited[destination]) {
                        visited[destination] = true;
                        q.offer(destination);
                    }
                }
            }
            moves++;
        }

        return -1; // unreachable
    }

    // DFS (with pruning) to find the minimum number of moves from ’node’ to ’end’
    private void dfs(
            HashMap<Integer, List<Integer>> graph,
            int node,
            int end,
            HashSet<Integer> visited,
            int depth,
            AtomicInteger shortestPath
    ) {
        // Prune if we already exceed the best‐so‐far
        if (depth >= shortestPath.get()) {
            return;
        }

        // Base case: reached the last square
        if (node == end) {
            shortestPath.set(depth);
            return;
        }

        // Explore each neighbor (i.e. each possible die roll from here)
        for (int next : graph.getOrDefault(node, Collections.emptyList())) {
            if (!visited.contains(next)) {
                visited.add(next);
                dfs(graph, next, end, visited, depth + 1, shortestPath);
                visited.remove(next);
            }
        }
    }

    /**
     * Build adjacency list so that from every index i (0 … n*n-1),
     * you can “roll a die” (1..6 steps). If flatBoard.get(j) != -1,
     * you follow the snake/ladder to (flatBoard.get(j) - 1).
     *
     * flatBoard.get(j) is either –1 or a 1-based destination square.
     * Subtract 1 to convert into a 0-based index in flatBoard.
     */
    private HashMap<Integer, List<Integer>> buildAdjGraph(List<Integer> flatBoard) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        int N = flatBoard.size();

        for (int i = 0; i < N; i++) {
            List<Integer> edges = new ArrayList<>();
            for (int roll = 1; roll <= 6; roll++) {
                int j = i + roll;
                if (j >= N) break;

                int dest;
                int boardVal = flatBoard.get(j);
                if (boardVal == -1) {
                    // No snake/ladder: stay on j
                    dest = j;
                } else {
                    // Jump to (boardVal - 1) because boardVal is 1-based
                    dest = boardVal - 1;
                }
                edges.add(dest);
            }
            graph.put(i, edges);
        }

        return graph;
    }

    /**
     * “Snaking” flatten:
     *  - Start from bottom‐left row (row = n-1), left→right
     *  - Next row (row = n-2), right→left
     *  - … zigzag up until row = 0
     *
     * This produces a List<Integer> of length n*n, where index 0 = square 1, index 1 = square 2, etc.
     * Each entry is either –1 or a 1-based target, exactly as given in the original 2D board.
     */
    private List<Integer> flattenBoard(int[][] board, int n) {
        List<Integer> ls = new ArrayList<>(n * n);
        boolean leftToRight = true;

        for (int row = n - 1; row >= 0; row--) {
            if (leftToRight) {
                for (int col = 0; col < n; col++) {
                    ls.add(board[row][col]);
                }
            } else {
                for (int col = n - 1; col >= 0; col--) {
                    ls.add(board[row][col]);
                }
            }
            leftToRight = !leftToRight;
        }

        return ls;
    }
}
