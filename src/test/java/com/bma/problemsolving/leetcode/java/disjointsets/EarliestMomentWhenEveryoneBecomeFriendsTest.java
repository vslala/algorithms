package com.bma.problemsolving.leetcode.java.disjointsets;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EarliestMomentWhenEveryoneBecomeFriendsTest {
    private final EarliestMomentWhenEveryoneBecomeFriends emwebf = new EarliestMomentWhenEveryoneBecomeFriends();

    @ParameterizedTest
    @CsvSource(value = {
            "[[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]] 6 20190301",
            "[[9,3,0],[0,2,1],[8,0,1],[1,3,2],[2,2,0],[3,3,1]] 4 2"
    }, delimiter = ' ')
    void shouldReturnTheEarliestTimestampWhenEveryoneBecameFriends(String expression, int vertices, int expected) {
        int [][] logs = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(expression, Integer.class));

        var result = emwebf.earliestAcq(logs, vertices);

        assertEquals(expected, result);
    }
}