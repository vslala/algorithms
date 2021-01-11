package com.bma.problemsolving.codewars;

import java.security.SecurityPermission;
import java.util.ArrayList;
import java.util.LinkedList;

import static java.lang.Math.*;

public class RangeExtractor {

    public static final String SEPARATOR = ",";

    public String rangeExtraction(int[] arr) {
        var range = new StringBuilder();
        var queue = new LinkedList<Integer>();

        queue.add(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            if (arr[i-1] + 1 != arr[i])
                evaluateAndExtract(range, queue);

            queue.add(arr[i]);
        }

        evaluateAndExtract(range, queue);

        int len = range.length();
        return range.substring(0,len - 1);
    }

    private void evaluateAndExtract(StringBuilder range, LinkedList<Integer> queue) {
        if (queue.size() == 1) {
            range.append(queue.remove()).append(SEPARATOR);
        } else if (queue.size() == 2) {
            range.append(queue.peekFirst()).append(SEPARATOR);
            range.append(queue.peekLast()).append(SEPARATOR);
            queue.clear();
        } else {
            range.append(queue.peekFirst())
                    .append("-")
                    .append(queue.peekLast())
                    .append(SEPARATOR);
            queue.clear();
        }
    }

    public static String mostVotedSolution(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            // append the first integer to the output
            // and then later decide if this is a range or not.
            sb.append(arr[i]);

            // check if this is a range i.e. different between consecutive integers is 1
            int j = i;
            while (j < arr.length - 1 && arr[j] + 1 == arr[j + 1]) j++;
            // if gap is more than 2
            if (i + 1 < j) {
                i = j;
                sb.append("-");
                sb.append(arr[i]);
            }
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
