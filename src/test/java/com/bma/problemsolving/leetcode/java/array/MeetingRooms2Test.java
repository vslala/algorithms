package com.bma.problemsolving.leetcode.java.array;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeetingRooms2Test {
    private MeetingRooms2 sol = new MeetingRooms2();

    @ParameterizedTest
    @CsvSource(value = {
            "[[0,30],[5,10],[15,20]] 2",
            "[[7,10],[2,4]] 1",
            "[[13,15],[1,13]] 1"
    }, delimiter = ' ')
    void shouldReturnTheCountOfSimultaneousMeetingRoomsRequiredForAllMeetings(String inputExpr, int expected) {
        int[][] meetings = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(inputExpr, Integer.class));

        int output = sol.minMeetingRooms(meetings);

        assertEquals(expected, output);
    }
}