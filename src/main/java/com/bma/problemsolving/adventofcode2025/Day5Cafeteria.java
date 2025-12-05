package com.bma.problemsolving.adventofcode2025;

import com.bma.algorithms.sort.elementary.Util;

import java.util.ArrayList;
import java.util.Collections;
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
            if (isValidId(ranges, id)) {
                validIds += 1;
            }
        }

        return validIds;
    }

    private boolean isValidId(List<Range> ranges, Long id) {
        Range searchKey = new Range(id, id);

        // Binary search returns:
        // - index if exact match found (range.start == id)
        // - (-(insertion point) - 1) if not found
        int index = Collections.binarySearch(ranges, searchKey, Comparator.comparingLong(Range::start));

        if (index >= 0) {
            return id <= ranges.get(index).end();
        }

        int insertionPoint = -(index + 1);

        // Check all ranges that could contain this id
        // Scan backwards from insertion point - 1 until we find a valid range
        // or reach a range that definitely can't contain the id
        for (int i = insertionPoint - 1; i >= 0; i--) {
            Range range = ranges.get(i);

            // If id is within this range, it's valid
            if (id >= range.start() && id <= range.end()) {
                return true;
            }

            // Optimization: if the range ends before our id starts,
            // and ranges are sorted by start, we won't find it in earlier ranges
            // ONLY if ranges don't overlap past this point
            // But with overlaps, we can't make this assumption safely, so check all
        }

        return false;
    }

    public long part2(List<Range> ranges, List<Long> ids) {
        ranges.sort(Comparator.comparingLong(Range::start));
        var mergedRanges = new ArrayList<Range>();

        mergedRanges.add(ranges.getFirst());

        for (int i = 1; i < ranges.size(); i++) {
            var prev = mergedRanges.getLast();
            var curr = ranges.get(i);
            if (prev.end() >= curr.start()) {
                var newRange = new Range(prev.start(), Math.max(prev.end(), curr.end()));
                mergedRanges.removeLast();
                mergedRanges.add(newRange);
            } else {
                mergedRanges.add(curr);
            }
        }

        long totalIds = 0;
        for (Range mergedRange : mergedRanges) {
            totalIds += mergedRange.total();
        }

        return totalIds;
    }

    public record Range(long start, long end) {
        public long total() {
            return end - start + 1;
        }
    }


}
