package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.problemsolving.Model.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PopulateNextPointerInATreeTest {

    private PopulateNextPointerInATree sol = new PopulateNextPointerInATree();

    @Test
    void shouldReturnNodeWithTheRightPointer() {
        var root = new Node(
                1,
                new Node(2, new Node(4, null, null, null), new Node(5, null, null, null), null),
                new Node(3, new Node(6, null, null, null), new Node(7, null, null, null), null),
                null
        );

        var result = sol.connect(root);

        assertNull(root.getNext());
        assertEquals(3, root.getLeft().getNext().getVal());
        assertEquals(6, root.getLeft().getRight().getNext().getVal());
    }

}