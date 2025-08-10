package com.bma.problemsolving.leetcode.java.dailychallenge;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 02/06/2025
 */
class CandyTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[1,0,2] 5",
            "[1,2,2] 4",
            "[60,80,100,100,100,100,100] 10"
    }, delimiter = ' ')
    void it_should_return_the_min_num_of_candies_to_distribute_to_the_children(String arrExpr, int expected) {
        int[] ratings = Fixtures.parse1DArray(arrExpr);
        var sol = new Candy();
        int output = sol.candy(ratings);
        assertEquals(expected, output);
    }
}