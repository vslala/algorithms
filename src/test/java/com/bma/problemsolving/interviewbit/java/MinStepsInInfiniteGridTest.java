package com.bma.problemsolving.interviewbit.java;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MinStepsInInfiniteGridTest {

    @Test
    void  itShouldPrintTheMinimumStepsRequiredToCoverAllThePointsOnTheGrid() {
        List<Integer> a = List.of(0,1,1);
        List<Integer> b = List.of(0,2,1);
        var testClass = new  MinStepsInInfiniteGrid();
        int output = testClass.coverPoints(a, b);

        assertEquals(2, output);
    }

}