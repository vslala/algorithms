package com.bma.algorithms.sort;

import com.bma.algorithms.sort.elementary.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.*;

public class SortingAlgorithmComparision {

    private int maxInputArrSize;

    public SortingAlgorithmComparision(int maxInputArrSize) {
        this.maxInputArrSize = maxInputArrSize;
    }

    private List<String> compare(int totalRun) throws InterruptedException {
        List<Thread> workerThreads = new LinkedList<>();
        List<String> stats = Collections.synchronizedList(new LinkedList<>());
        stats.add("Input Size,Quick,Merge,BottomUpMerge,Shell,Insertion,Selection" + System.lineSeparator());

        Random rand = new Random();
        for (int i = 0; i < totalRun; i++) {
            int inputSize = rand.nextInt(maxInputArrSize);
            int input[] = Util.generateUnsortedArray(inputSize);

            Thread t = new Thread(() -> {
                StringBuilder sb = new StringBuilder();
                sb.append(inputSize).append(",")
                        .append(Sort.quickSort(copyOf(input, input.length))).append("ms,")
                        .append(Sort.mergeSort(copyOf(input, input.length))).append("ms,")
                        .append(Sort.bottomUpMergeSort(copyOf(input, input.length))).append("ms,")
                        .append(Sort.shellSort(copyOf(input, input.length))).append("ms,")
                        .append(Sort.insertionSort(copyOf(input, input.length))).append("ms,")
                        .append(Sort.selectionSort(copyOf(input, input.length))).append("ms,")
                        .append(System.lineSeparator());
                stats.add(sb.toString());
            });

            t.start();
            Util.println(String.format("Thread<%d> started!", t.getId()));
            workerThreads.add(t);
        }

        for (Thread workerThread : workerThreads)
            workerThread.join();

        return stats;
    }

    public void generateReport(int totalRun, String outputFileName) throws IOException, InterruptedException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(outputFileName)))) {
            for (String line : compare(totalRun))
                bw.write(line);
        }
    }
}
