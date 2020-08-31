package com.bma.algorithms.leetcode;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class ReverseIntegerTest {

    ReverseInteger test = new ReverseInteger();

    @Test
    public void it_should_reverse_the_integer() {
        var inputs = Map.of(
                123, 321,
                234, 432,
                -123, -321,
                120, 21
        );

        inputs.forEach((input, assertion) -> assertEquals(assertion, test.reverse(input)));
    }

}