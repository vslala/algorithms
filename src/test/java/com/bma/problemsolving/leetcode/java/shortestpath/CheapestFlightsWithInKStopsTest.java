package com.bma.problemsolving.leetcode.java.shortestpath;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheapestFlightsWithInKStopsTest {

    @ParameterizedTest
    @CsvSource(value = {
            "3 [[0,1,100],[1,2,100],[0,2,500]] 0 2 1 200",
            "3 [[0,1,100],[1,2,100],[0,2,500]] 0 2 0 500",
            "4 [[0,1,1],[0,2,5],[1,2,1],[2,3,1]] 0 3 1 6",
            "5 [[0,1,5],[1,2,5],[0,3,2],[3,1,2],[1,4,1],[4,2,1]] 0 2 2 7"
    }, delimiter = ' ')
    void shouldReturnTheCheapestFlightBetweenTwoDestinationInKStops(int numOfVertices, String flightsExpr, int src, int dst, int stops, double expected) {
        int[][] flights = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(flightsExpr, Integer.class));

        CheapestFlightsWithInKStops sol = new CheapestFlightsWithInKStops();
        double price = sol.findCheapestPrice(numOfVertices, flights, src, dst, stops);

        assertEquals(expected, price);
    }
}