package com.bma.algorithms.leetcode;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class RomanToInteger {

    Map<Character,Integer> romanToInt = Map.of(
            'I',   1,
            'V',    5,
            'X',    10,
            'L',    50,
            'C',    100,
            'D',    500,
            'M',    1000
    );

    public int convert(String roman) {
        int length = roman.length();
        int pointer = 0;
        int result = 0;

        while (pointer < length) {
            final Integer curr = romanToInt.get(roman.charAt(pointer));
            final Integer next = pointer + 1 < length ? romanToInt.get(roman.charAt(pointer + 1)) : curr;
            if (curr < next)  {
                result += next - curr;
                pointer += 2;
            } else {
                result += curr;
                pointer++;
            }
        }

        return result;
    }
}
