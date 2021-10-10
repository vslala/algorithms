package com.bma.problemsolving;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Timer {

    public static long timeIt(Runnable job) {
        long startMillis = System.currentTimeMillis();
        job.run();
        return (System.currentTimeMillis() - startMillis);
    }
}
