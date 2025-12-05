package com.bma.problemsolving.adventofcode2025;

import java.util.List;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 05/12/2025
 *
 * @aoc2025 <a href="https://adventofcode.com/2025/day/4">Printing Department</a>
 */
class Day4PrintingDepartment {

    private static final char PAPER_ROLL = '@';
    private static final char EMPTY = '.';
    private static final int[][] DIR = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};


    public int part1(List<String> input) {
        List<char[]> paperRolls = input.stream().map(String::toCharArray).toList();
        int canMove = 0;

        for (int r = 0; r < input.size(); r++) {
            for (int c = 0; c < input.get(r).length(); c++) {
                if (input.get(r).charAt(c) == PAPER_ROLL) {
                    int adjPaperRollCount = findAdjPaperRollCount(paperRolls, r, c);
                    if (adjPaperRollCount < 4) {
                        canMove += 1;
                    }
                }
            }
        }

        return canMove;
    }

    private int findAdjPaperRollCount(List<char[]> input, int r, int c) {
        int count = 0;
        for (int[] direction : DIR) {
            int newR = r + direction[0];
            int newC = c + direction[1];
            if (isInBounds(input, newR, newC) && input.get(newR)[newC] == PAPER_ROLL) {
                count += 1;
            }
        }

        return count;
    }

    private boolean isInBounds(List<char[]> input, int r, int c) {
        return r >= 0 && r < input.size() && c >= 0 && c < input.get(r).length;
    }


    public int part2(List<String> input) {
        List<char[]> paperRolls = input.stream().map(String::toCharArray).toList();

        int total = 0;
        while (true) {
            int canMove = 0;
            for (int r = 0; r < paperRolls.size(); r++) {
                for (int c = 0; c < paperRolls.get(r).length; c++) {
                    if (paperRolls.get(r)[c] == PAPER_ROLL) {
                        int adjPaperRollCount = findAdjPaperRollCount(paperRolls, r, c);
                        if (adjPaperRollCount < 4) {
                            canMove += 1;
                            paperRolls.get(r)[c] = EMPTY;
                        }
                    }
                }
            }

            if (canMove == 0) {
                break;
            }

            total += canMove;
        }

        return total;
    }
}
