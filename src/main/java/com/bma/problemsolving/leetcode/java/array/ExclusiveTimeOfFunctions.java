package com.bma.problemsolving.leetcode.java.array;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.ListIterator;

/**
 * On a single-threaded CPU, we execute a program containing n functions. Each function has a unique ID between 0 and n-1.
 * Function calls are stored in a call stack: when a function call starts, its ID is pushed onto the stack, and when a function call ends, its ID is popped off the stack. The function whose ID is at the top of the stack is the current function being executed. Each time a function starts or ends, we write a log with the ID, whether it started or ended, and the timestamp.
 * You are given a list logs, where logs[i] represents the ith log message formatted as a string "{function_id}:{"start" | "end"}:{timestamp}". For example, "0:start:3" means a function call with function ID 0 started at the beginning of timestamp 3, and "1:end:2" means a function call with function ID 1 ended at the end of timestamp 2. Note that a function can be called multiple times, possibly recursively.
 * A function's exclusive time is the sum of execution times for all function calls in the program. For example, if a function is called twice, one call executing for 2 time units and another call executing for 1 time unit, the exclusive time is 2 + 1 = 3.
 * Return the exclusive time of each function in an array, where the value at the ith index represents the exclusive time for the function with ID i.
 *
 * @author varun.shrivastava
 */
class ExclusiveTimeOfFunctions {
    /**
     * Steps to solve:
     * 1. Push the START logs into the stack
     * 2. When end log is encountered:
     *  - Calculate the duration of that function
     *      - endTime - startTime + 1 - timeSuspended
     *  - Add time to the function id
     *      - output[log.id] += duration
     *  - If stack is not empty
     *      - then add this function duration to the parent function in the stack
     * @param n
     * @param logs
     * @return
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] output = new int[n];
        Deque<Log> stack = new ArrayDeque<>();
        ListIterator<String> logIterator = logs.listIterator();
        while (logIterator.hasNext()) {
            Log log = new Log(logIterator.next());
            if (log.op == LogStatus.START) {
                stack.push(log);
            } else {
                Log top = stack.pop();
                int duration = log.timestamp - top.timestamp + 1 - top.timeSuspended;
                output[top.id] += duration;

                if (!stack.isEmpty())
                    stack.peek().timeSuspended += log.timestamp - top.timestamp + 1;
            }
        }

        return output;
    }

    private static class Log {
        int id;
        int timestamp;
        LogStatus op;
        int timeSuspended;

        public Log(String log) {
            String[] parts = log.split(":");
            int index = 0;
            id = Integer.parseInt(parts[index++]);
            op = LogStatus.parse(parts[index++]);
            timestamp = Integer.parseInt(parts[index]);
            timeSuspended = 0;
        }
    }

    private enum LogStatus {
        START, END;

        public static LogStatus parse(String status) {
            return LogStatus.valueOf(status.toUpperCase());
        }
    }
}
