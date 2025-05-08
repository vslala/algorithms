package com.bma.problemsolving.leetcode.java.blind75;

import java.util.*;

public class ClosestBinarySearchTreeValueTwo {

    private double target;
    private int k;

    protected static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private record Pad(
            TreeNode node,
            double diff
    ) implements Comparable<Pad> {

        @Override
        public int compareTo(Pad o) {
            return Double.compare(this.diff, o.diff);
        }
    }

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        this.target = target;
        this.k = k;

        Deque<Integer> deque = new LinkedList<>();
        inOrder(root, deque);
        return new ArrayList<>(deque);

//        return solveUsingGrowingWindow(root, target);

//        return findKClosestValuesUsingHeap(root);
    }

    private void inOrder(TreeNode node, Deque<Integer> window) {
        if (node == null) {
            return;
        }

        inOrder(node.left, window);
        window.add(node.val);
        if (window.size() > this.k) {
            if (Math.abs(window.peekFirst() - this.target) <= Math.abs(window.peekLast() - target)) {
                window.removeLast();
                return;
            } else {
                window.removeFirst();
            }
        }
        inOrder(node.right, window);

    }

    private List<Integer> solveUsingGrowingWindow(TreeNode root, double target) {
        var ls = new ArrayList<Integer>();
        inOrder(root, ls);

        double minDiff = Double.MAX_VALUE;
        int closest = 0;
        for (int i = 0; i < ls.size(); i++) {
            int num = ls.get(i);
            if (Math.abs(num - target) < minDiff) {
                minDiff = Math.abs(target - num);
                closest = i;
            }
        }

        int left = closest;
        int right = closest + 1;

        while (right - left - 1 < this.k) {
            if (left < 0) {
                right += 1;
                continue;
            }

            if (right == ls.size() || Math.abs(ls.get(left) - target) <= Math.abs(ls.get(right) - target)) {
                left -= 1;
            } else {
                right += 1;
            }
        }

        return ls.subList(left + 1, right);
    }

    private ArrayList<Integer> findKClosestValuesUsingHeap(TreeNode root) {
        var heap = new PriorityQueue<Pad>(Collections.reverseOrder());
        inOrder(root, heap);
        var output = new ArrayList<Integer>();
        while (!heap.isEmpty()) {
            output.add(heap.poll().node.val);
        }

        return output;
    }

    private void inOrder(TreeNode node, List<Integer> ls) {
        if (node == null) return;
        inOrder(node.left, ls);
        ls.add(node.val);
        inOrder(node.right, ls);
    }

    private void inOrder(TreeNode root, PriorityQueue<Pad> heap) {
        if (root == null) return ;

        inOrder(root.left, heap);

        double diff = 0;
        if (root.val > target) {
            diff = root.val - target;
        } else {
            diff = target - root.val;
        }
        heap.add(new Pad(root, diff));
        if (heap.size() > this.k) {
            heap.poll();
        }

        inOrder(root.right, heap);
    }
}
