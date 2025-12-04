package com.bma.problemsolving.adventofcode2025;

import com.bma.algorithms.sort.elementary.Util;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 04/12/2025
 */
class Day2GiftShop {


    public long part1(String input) {
        long sum = 0;
        String[] ranges = input.split(",");
        for (String range : ranges) {
            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);

            while (start <= end) {
                String number = String.valueOf(start);
                int mid = number.length() / 2;
                boolean isSame = number.substring(0, mid).equals(number.substring(mid));
//                Util.println(range + ": " + number.substring(0, mid) + "-" + number.substring(mid));
                if (isSame) {
                    sum += start;
                }
                start++;
            }
        }

        return sum;
    }
}
