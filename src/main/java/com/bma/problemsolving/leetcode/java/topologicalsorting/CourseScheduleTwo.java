package com.bma.problemsolving.leetcode.java.topologicalsorting;

import com.bma.algorithms.graphs.directed_graphs.EdgeWeightedDigraph;
import com.bma.algorithms.graphs.model.DirectedEdge;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Course Schedule II
 * --------------------
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 *
 * @author varun.shrivastava
 */
class CourseScheduleTwo {
    private EdgeWeightedDigraph digraph;
    private int numOfCourses;
    private boolean[] visited;
    private boolean[] onStack;
    private boolean hasCycle;
    private Deque<Integer> reversePost;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        this.numOfCourses = numCourses;
        this.digraph = EdgeWeightedDigraph.createDigraph(numCourses);

        // construct digraph
        for (int[] course: prerequisites)
            digraph.addEdge(new DirectedEdge(course[1], course[0], 0));

        // topologically sort (find reverse post order)
        int[] result = topologicalSort(digraph);
        if (hasCycle) return new int[0];

        return result;
    }

    private int[] topologicalSort(EdgeWeightedDigraph digraph) {
        visited = new boolean[numOfCourses];
        onStack = new boolean[numOfCourses];
        reversePost = new LinkedList<>();
        for (int v = 0; v < numOfCourses; v++) {
            if (!visited[v])
                dfs(v);
        }

        int[] result = new int[numOfCourses];
        int index = 0;
        while (!reversePost.isEmpty())
            result[index++] = reversePost.pop();

        return result;
    }

    private void dfs(int u) {
        visited[u] = true;
        onStack[u] = true;
        for (DirectedEdge edge: digraph.adj(u)) {
            if (onStack[edge.to()]) {
                hasCycle = true;
                return;
            }

            if (!visited[edge.to()])
                dfs(edge.to());
        }

        reversePost.push(u);
        onStack[u] = false;
    }
}
