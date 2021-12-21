package com.bma.algorithms.disjointsets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WeightedQuickUnionTest {

    private WeightedQuickUnion qubr = new WeightedQuickUnion(10);

    @Test
    void shouldConnectComponentsTogether() {
        qubr.union(0, 10);
        qubr.union(2, 5);
        qubr.union(3, 4);
        qubr.union(10, 2);

        assertTrue(qubr.connected(0, 5));
        assertFalse(qubr.connected(1, 5));
    }

}