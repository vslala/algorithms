package com.bma.algorithms.computerphile;

/**
 * A simple bit-shift operation can generate amazing random strings of numbers.
 * Dr. Mike Pound Video: https://www.youtube.com/watch?v=Ks1pw1X22y4&ab_channel=Computerphile
 *
 * @author varun.shrivastava
 */
class LinearFeedbackShiftRegister {
    private static final int WORD_SIZE = 4;
    private final String initialState;
    private int state;

    public LinearFeedbackShiftRegister(String initialState) {
        this.initialState = initialState;
        state = Integer.parseInt(initialState, 2);
    }


    public int nextInt() {
        var allCombinations = new StringBuilder();
        for (int i = 0; i < WORD_SIZE; i++) {
            int newBit = (state ^ (state >> 1)) & 1;
            state = (state >> 1) | (newBit << initialState.length() - 1);
            allCombinations.append(Integer.toBinaryString(newBit));
        }

        return Integer.parseInt(allCombinations.substring(allCombinations.length() - WORD_SIZE), 2);
    }
}
