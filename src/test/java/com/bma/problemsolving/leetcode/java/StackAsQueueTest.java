package com.bma.problemsolving.leetcode.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackAsQueueTest {

    @Test
    void givenTheListOfNumberImplementAQueueUsingStacksOnly() {
        var myQueue = new StackAsQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        assertEquals(1, myQueue.peek()); // return 1
        assertEquals(1, myQueue.pop()); // return 1, queue is [2]
        assertFalse(myQueue.empty()); // return false
    }

}