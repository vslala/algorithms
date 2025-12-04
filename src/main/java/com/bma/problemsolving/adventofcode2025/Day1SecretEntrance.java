package com.bma.problemsolving.adventofcode2025;

import java.util.List;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 01/12/2025
 */
class Day1SecretEntrance {

    public int part1(List<String> input) {
        int count = 0;
        int pointer = 50;
        for (String instruction : input) {
            char direction = instruction.charAt(0);
            int distance = Integer.parseInt(instruction.substring(1));

            pointer = switch (direction) {
                case 'L' -> pointer - distance;
                case 'R' -> pointer + distance;
                default -> throw new RuntimeException("Invalid direction");
            };

            pointer = ((pointer % 100) + 100) % 100;

            if (pointer == 0) {
                count += 1;
            }

        }

        return count;
    }

    public int part2(List<String> input) {
        int count = 0;
        int pointer = 50;
        for (String instruction : input) {
            char direction = instruction.charAt(0);
            int distance = Integer.parseInt(instruction.substring(1));

            switch (direction) {
                case 'L' -> {
                    // Count multiples of 100 in range [pointer-distance, pointer-1]
                    count += Math.floorDiv(pointer - 1, 100) - Math.floorDiv(pointer - distance - 1, 100);
                    pointer = ((pointer - distance) % 100 + 100) % 100;
                }
                case 'R' -> {
                    // Count multiples of 100 in range [pointer+1, pointer+distance]
                    count += Math.floorDiv(pointer + distance, 100) - Math.floorDiv(pointer, 100);
                    pointer = (pointer + distance) % 100;
                }
                default -> throw new RuntimeException("Invalid direction");
            }

        }

        return count;
    }
}
