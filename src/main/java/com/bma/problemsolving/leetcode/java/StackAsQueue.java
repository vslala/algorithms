package com.bma.problemsolving.leetcode.java;

import java.util.LinkedList;

public class StackAsQueue {

    private LinkedList<Integer> stack1 = new LinkedList<>();
    private LinkedList<Integer> stack2 = new LinkedList<>();

    public void push(Integer item) {
        if (stack1.isEmpty())
            stack1.push(item);
        else {
            popAndPush(stack1, stack2);
            stack1.push(item);
            popAndPush(stack2, stack1);
        }
    }

    private void popAndPush(LinkedList<Integer> stack1, LinkedList<Integer> stack2) {
        while (!stack1.isEmpty())
            stack2.push(stack1.pop());
    }

    public Integer peek() {
        return stack1.peekFirst();
    }

    public Integer pop() {
        return stack1.pop();
    }

    public Boolean empty() {
        return stack1.isEmpty();
    }
}
