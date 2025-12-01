package com.bma.problemsolving.adventofcode2025;

import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 01/12/2025
 */
class Day1SecretEntranceTest {

    @ParameterizedTest
    @CsvSource({
            "1, src/main/resources/adventofcode2025/day1.test.txt, 3",
            "1, src/main/resources/adventofcode2025/day1.txt, 1123",
            "2, src/main/resources/adventofcode2025/day1.test.txt, 6",
            "2, src/main/resources/adventofcode2025/day1.txt, 6695"
    })
    @SneakyThrows
    void return_total_times_where_dial_points_at_zero(int part, String inputFilePath, int expected) {
        List<String> input = Files.readAllLines(Path.of(inputFilePath));
        var testClass = new Day1SecretEntrance();

        int password = part == 1 ? testClass.part1(input) : testClass.part2(input);

        assertEquals(expected, password);
    }
}