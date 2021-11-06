package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

class FloodFillTest {

    private FloodFill sol = new FloodFill();

    @ParameterizedTest
    @CsvSource({
            "[[1_1_1]:[1_1_0]:[1_0_1]], 1, 1, 2, [[2_2_2]:[2_2_0]:[2_0_1]]",
            "[[0_0_0]:[0_0_0]], 0, 0, 2, [[2_2_2]:[2_2_2]]",
            "[[0_0_0]:[0_1_1]], 1, 1, 1, [[0_0_0]:[0_1_1]]"
    })
    void shouldFillAllTheAdjacentPixelsWithTheNewColor(String imagePixelMatrix, int sr, int sc, int newColor, String expectedImagePixelMatrix) {
        var image = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseExpression(imagePixelMatrix));
        var expectedImage = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseExpression(expectedImagePixelMatrix));
        var original = Arrays.copyOf(image, image.length);

        var output = sol.floodFillBfs(image, sr, sc, newColor);

        Fixtures.assertBothMatrixContainsSameItems(original, expectedImage, output);

        var dfsOutput = sol.floodFillDfs(Arrays.copyOf(original, image.length), sr, sc, newColor);
        Fixtures.assertBothMatrixContainsSameItems(original, expectedImage, dfsOutput);
    }

}