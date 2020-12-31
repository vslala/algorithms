package com.bma.algorithms.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContainerWithMostWaterTest {

    ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();

    @Test
    public void it_should_give_the_max_area_of_the_container() {
        var byInputsByAssertions = Map.of(
                49, new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7},
                64, new int[]{1, 8, 6, 2, 5, 4, 7, 3, 7, 8}
        );
        byInputsByAssertions.forEach((assertion, input) ->
                assertEquals((int) assertion, containerWithMostWater.maxArea(input)));
    }

}