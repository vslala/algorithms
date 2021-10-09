package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 1345. Jump Game IV
 * <p>
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 * <p>
 * In one step you can jump from index i to index:
 * <p>
 * i + 1 where: i + 1 < arr.length.
 * i - 1 where: i - 1 >= 0.
 * j where: arr[i] == arr[j] and i != j.
 * Return the minimum number of steps to reach the last index of the array.
 * <p>
 * Notice that you can not jump outside of the array at any time.
 * <p>
 * Hint 1: Build a graph of n nodes where nodes are the indices of the array and edges for node i are nodes i+1, i-1, j where arr[i] == arr[j].
 * Hint 2: Start bfs from node 0 and keep distance, answer is the distance when you reach onode n-1.
 *
 * @author varun.shrivastava
 */
public class JumpGameFour {

    public int minJumps(int[] arr) {
        if (arr.length <= 1) return 0;

        // Build a graph of n nodes where nodes are the indices of the array and edges for node i are nodes i+1, i-1, j where arr[i] == arr[j].
        var graph = fillGraph(arr);

        // Start bfs from node 0 and keep distance, answer is the distance when you reach onode n-1.
        var queue = new LinkedList<>(List.of(0));
        var totalJumps = 0;
        while (!queue.isEmpty()) {
            totalJumps++;

            // run only for items present at the moment
            var size = queue.size();
            while (size > 0) {
                var currIndex = queue.poll();
                assert currIndex != null;

                // check index left to current index and add to queue
                var prevIndex = currIndex - 1;
                if (isInBounds(arr, prevIndex) && graph.containsKey(arr[prevIndex]))
                    queue.offer(prevIndex);


                // check index right to curr index and add to queue
                var nextIndex = currIndex + 1;
                if (nextIndex == arr.length - 1)
                    return totalJumps;

                if (isInBounds(arr, nextIndex) && graph.containsKey(arr[nextIndex]))
                    queue.offer(nextIndex);


                // check of the key exists in the graph
                // then add all indices to the queue
                var key = arr[currIndex];
                if (graph.containsKey(key)) {
                    for (Integer index : graph.get(key)) {
                        if (index == arr.length - 1)
                            return totalJumps;

                        if (index.equals(currIndex))
                            continue;

                        queue.offer(index);
                    }
                }

                // remove the key that has been processed already
                graph.remove(key);
                size--;
            }
        }

        return totalJumps;
    }

    private Map<Integer, List<Integer>> fillGraph(int[] arr) {
        var graph = new HashMap<Integer, List<Integer>>();

        IntStream.range(0, arr.length)
                .forEach(index -> graph.computeIfAbsent(arr[index],
                        v -> new ArrayList<>()).add(index));

        return graph;
    }

    private boolean isInBounds(int[] arr, int currIndex) {
        return currIndex >= 0 && currIndex < arr.length;
    }

}
