package com.bma.problemsolving.codeforces.cpp;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DenseArrayTest {

    @Test
    void itShouldCalcNumberOfRequiredStepsToMakeItADenseArray() {
//        var tests = Map.of(
//                new int[]{4, 3, 10, 1}, 4,
//                new int[]{4, 2, 10, 1}, 5,
//                new int[]{1, 2, 3, 4, 3}, 0
//        );
//
//        tests.forEach((test, expected) -> {
//            var denseArray = new DenseArray(test);
//            assertEquals(expected, denseArray.steps());
//        });
        var testArr = new int[]{4, 3, 10, 1};
//        var testArr = new int[]{4, 2, 10, 1};
//        var testArr = new int[]{1, 2, 3, 4, 3};
        var denseArray = new DenseArray(testArr);
        assertEquals(5, denseArray.steps());
    }

}