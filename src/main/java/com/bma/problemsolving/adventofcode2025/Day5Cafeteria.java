package com.bma.problemsolving.adventofcode2025;

import java.util.Comparator;
import java.util.List;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 05/12/2025
 *
 * @aoc2025 <a href="https://adventofcode.com/2025/day/5">Cafeteria</a>
 */
class Day5Cafeteria {

    public int part1(List<Range> ranges, List<Long> ids) {
        ranges.sort(Comparator.comparingLong(Range::start));

        int validIds = 0;
        for (Long id : ids) {
            for (Range range : ranges) {
                if (id >= range.start() && id <= range.end()) {
                    validIds += 1;
                    break;
                }
            }
        }

        return validIds;
    }

    public int part2(List<Range> ranges, List<Long> ids) {
        return 0;
    }

    public record Range(long start, long end) {}


}
