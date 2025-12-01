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
                    for (int i = 0; i < distance; i++) {
                        pointer--;
                        if (pointer == 0) {
                            count += 1;
                        }

                        if (pointer == -1) {
                            pointer = 99;
                        }
                    }
                }
                case 'R' -> {
                    for (int i = 0; i < distance; i++) {
                        pointer++;
                        if (pointer == 100) {
                            count += 1;
                            pointer = 0;
                        }
                    }
                }
                default -> throw new RuntimeException("Invalid direction");
            }

        }

        return count;
    }
}
