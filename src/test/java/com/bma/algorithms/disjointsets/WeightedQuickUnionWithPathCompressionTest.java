package com.bma.algorithms.disjointsets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WeightedQuickUnionWithPathCompressionTest {

    private WeightedQuickUnionWithPathCompression wquwpc = new WeightedQuickUnionWithPathCompression(10);

    @Test
    void shouldConnectTwoComponentsTogether() {
        wquwpc.union(0, 10);
        wquwpc.union(2, 5);
        wquwpc.union(3, 4);
        wquwpc.union(10, 2);

        assertTrue(wquwpc.connected(0, 5));
        assertFalse(wquwpc.connected(1, 5));
        assertFalse(wquwpc.connected(0, 3));
        assertTrue(wquwpc.connected(2, 5));
    }

}