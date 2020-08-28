package com.bma.fixtures;

public class Fixtures {

    public static void measureRunTime(Runnable job) {
        long startMillis = System.currentTimeMillis();
        job.run();
        System.out.println("Total Time Taken = " + (System.currentTimeMillis() - startMillis) + "ms");
    }
}
