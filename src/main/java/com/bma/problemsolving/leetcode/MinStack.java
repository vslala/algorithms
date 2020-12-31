package com.bma.problemsolving.leetcode;

class MinStack {

    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    private Node headNode = null;
    private int minSoFar = Integer.MIN_VALUE;

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        if (minSoFar > x) minSoFar = x;

        if (null == headNode) {
            headNode = new Node(x);
            headNode.next = null;
        } else {
            Node newNode = new Node(x);
            newNode.next = headNode;
            headNode = newNode;
        }
    }

    public void pop() {
        Node nextNode = headNode.next;
        headNode = nextNode;
    }

    public int top() {
        return headNode.data;
    }

    public int getMin() {
        int min = headNode.data;
        Node itr = headNode;
        while (null != itr) {
            if (min > itr.data)
                min = itr.data;
            itr = itr.next;
        }

        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin(); // return -3
        minStack.pop();
        minStack.top();    // return 0
        minStack.getMin(); // return -2
    }
}


/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
