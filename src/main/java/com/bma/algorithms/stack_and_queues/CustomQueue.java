package com.bma.algorithms.stack_and_queues;

import java.util.Objects;

public class CustomQueue {

    private Node headNode;

    private class Node {
        String item;
        Node next;

        public Node(String item) {
            this.item = item;
        }
    }

    public void enqueue(String item) {
        if (Objects.isNull(headNode)) {
            headNode = new Node(item);
        } else {
            Node newNode = new Node(item);
            lastNode().next = newNode;
        }
    }

    private Node lastNode() {
        Node itr = headNode;
        while (!Objects.isNull(itr.next)) {
            itr = itr.next;
        }
        return itr;
    }

    public void dequeue() {
        Node itr = headNode;
        Node prevNode = null;
        while (!Objects.isNull(itr.next)) {
            prevNode = itr;                 // keep track of second last element
            itr = itr.next;
        }
        prevNode.next = null;               // remove the pointer to the last element in the second last item
    }

    public void printItems() {
        Node itr = headNode;
        while (!Objects.isNull(itr)) {
            System.out.println(itr.item);
            itr = itr.next;
        }
    }

    public static void main(String[] args) {
        CustomQueue queue = new CustomQueue();
        queue.enqueue("Varun");
        queue.enqueue("Priyanka");
        queue.enqueue("Vaibhav");

        queue.printItems();

        queue.dequeue();

        System.out.println("-------------------------");
        queue.printItems();
    }
}
