package com.bma.algorithms.stack_and_queues;

import java.util.Objects;
import java.util.Stack;

// 16 bytes object overhead
public class CustomStack {

    // 8 bytes extra overhead because of inner-class
    private class Node {
        String item;    // 8 byte reference to string
        Node next;      // 8 bytes reference to node

        public Node(String item) {
            this.item = item;
        }
    }

    Node headNode;

    public void push(String item) {
        if (Objects.isNull(headNode))
            headNode = new Node(item);
        else {
            Node newNode = new Node(item);
            newNode.next = headNode;            // new node points to head node
            headNode = newNode;                 // new node is the head node
        }
    }

    public String pop() {
        Node topItem = null;
        if (!Objects.isNull(headNode)) {
            topItem = headNode;
            headNode = headNode.next;
        }
        return topItem.item;
    }

    public void printItems() {
        Node itr = headNode;
        while (!Objects.isNull(itr)) {
            System.out.println(itr.item);
            itr = itr.next;
        }
    }

    public static void main(String[] args) {
        CustomStack stack = new CustomStack();
        stack.push("Varun");
        stack.push("Priyanka");
        stack.push("Vaibhav");

        stack.printItems();

        stack.pop();

        System.out.println("------------------");
        stack.printItems();
    }


}
