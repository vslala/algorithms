package com.bma.problemsolving.leetcode.java.knightprobability;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KnightProbabilityTest {

    @Test
    void givenKnightPositionAndBoardItShouldReturnAllThePlacesThatKnightCanVisit() {

        var test  = new KnightProbability(3, 2, 0, 0);
        var probability = test.probabilityTotalBoundedMovesByTotalMoves();
        assertEquals(0.0625f, probability);


        test = new KnightProbability(3, 3, 0,0);
        assertEquals(0.015625f, test.probabilityTotalBoundedMovesByTotalMoves());
    }
}