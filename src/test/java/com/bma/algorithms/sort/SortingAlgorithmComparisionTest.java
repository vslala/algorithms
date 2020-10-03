package com.bma.algorithms.sort;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class SortingAlgorithmComparisionTest {

    private static final String OUTPUT_FILE_NAME = "sort_algo_compare.csv";
    SortingAlgorithmComparision comparision = new SortingAlgorithmComparision(1000000);

    @Test
    public void testWriteStringToFile() throws InterruptedException, IOException {
        comparision.generateReport(25, "src/main/resources/" + OUTPUT_FILE_NAME);
        int retries = 0;
        while (true) {
            if (new File("src/main/resources/" + OUTPUT_FILE_NAME).exists())
                break;
            else {
                retries++;
                Thread.sleep(1000);
                if (retries > 4)
                    break;
            }
        }
    }

}