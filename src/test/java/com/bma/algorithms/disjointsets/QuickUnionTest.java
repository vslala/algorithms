package com.bma.algorithms.disjointsets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuickUnionTest {

    private QuickUnion cut = new QuickUnion(10);

    @Test
    void shouldConnectComponentsTogether() {
        cut.union(0, 10);
        cut.union(2, 5);
        cut.union(3, 4);
        cut.union(10, 2);

        assertTrue(cut.connected(0, 5));
        assertFalse(cut.connected(1, 5));
    }
}