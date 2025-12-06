package com.bma.problemsolving.adventofcode2025;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 06/12/2025
 */
class Day6TrashCompactor {

    public long part1(List<String> rows) {
        List<List<Long>> matrix = parseInput(rows);
        String[] operations = rows.getLast().split("\\s+");

        int maxRow = matrix.size();
        int maxCol = matrix.getFirst().size();

        long total = 0;
        for (int i = 0; i < maxCol; i++) {
            String colOperation = operations[i];
            long colTotal = colOperation.equals("*") ? 1 : 0;
            for (int j = 0; j < maxRow; j++) {
                long operand = matrix.get(j).get(i);
                switch (colOperation) {
                    case "+" -> colTotal = colTotal + operand;
                    case "*" -> colTotal = colTotal * operand;
                    default -> throw new RuntimeException("Unknown Operation");
                }
            }

            total += colTotal;
        }

        return total;
    }

    private List<List<Long>> parseInput(List<String> input) {
        var rows = new ArrayList<>(input);
        rows.removeLast(); // remove the operations
        List<List<Long>> matrix = new ArrayList<>();

        for (String row : rows) {
            var numberRow = new ArrayList<Long>();
            String[] cols = row.split("\\s+");
            for (String col : cols) {
                numberRow.add(Long.parseLong(col));
            }
            matrix.add(numberRow);
        }

        return matrix;
    }

    public long part2() {
        return 0;
    }
}
