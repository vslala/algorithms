package com.bma.algorithms.disjointsets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuickFindTest {

    // cut => code under test
    private QuickFind cut = new QuickFind(10);

    @Test
    void shouldUnionTwoVertices() {
        cut.union(0, 10);
        cut.union(10, 2);
        cut.union(1, 0);

        assertTrue(cut.connected(1, 2));
        assertFalse(cut.connected(0, 5));
    }

}