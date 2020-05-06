package com.bma.algorithms.sort.elementary;

import java.util.Random;

public class KnuthShuffle {

    private final int[] input;
    private static Random random = new Random();
    private long totalTime = 0L;

    public KnuthShuffle(int[] input) {
        this.input = input;
    }

    public void shuffle() {
        long startMillis = System.currentTimeMillis();
        for (int index = 0; index < input.length; index++) {
            int randomUniformIndex = KnuthShuffle.random.nextInt(index + 1);
            Util.swap(input, index, randomUniformIndex);
        }
        totalTime = System.currentTimeMillis() - startMillis;
    }

    public void time() {
        System.out.println(String.format("KnuthShuffle> Total Time Taken: %dms", totalTime));
    }

    public void print() {
        Util.println(input);
    }

    public static void main(String[] args) {
        int[] input = new int[]{1,2,3,4,5,6,7,89,10};
        for (int i=0; i < 10; i++) {
            KnuthShuffle knuthShuffle = new KnuthShuffle(input);
            knuthShuffle.shuffle();
            knuthShuffle.print();
            knuthShuffle.time();
        }
    }

}
