package com.bma.fixtures;

import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class Fixtures {

    public static void measureRunTime(Runnable job) {
        long startMillis = System.currentTimeMillis();
        job.run();
        System.out.println("Total Time Taken = " + (System.currentTimeMillis() - startMillis) + "ms");
    }

    public static int[] splitAndParseArr(String str, String splitter) {
        return Arrays.stream(str.split(splitter)).mapToInt(Integer::parseInt).toArray();
    }
}
