package com.bma.algorithms.hash_tables;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Author: Varun Shrivastava
 * Description: The program takes 3 command line parameters
 * @param1 input csv file (commo-separated)
 * @param2 column to consider as key in the csv file
 * @param3 column to consider as val in the csv file
 * then pass the key and the program will return the associated value for that key
 */
public class DictionaryClient {

    public static void main(String[] args) {
        Map<String, String>  dictionary = new HashMap<>();
        File inputFile = new File(args[0]);
        Integer keyCol = Integer.parseInt(args[1]);
        Integer valCol = Integer.parseInt(args[2]);

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line = "";
            while (null != (line = br.readLine())) {
                String key = line.split(",")[keyCol];
                String val = line.split(",")[valCol];
                dictionary.put(key, val);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);
        while (true) {
            String key = scan.nextLine();
            System.out.println(dictionary.getOrDefault(key, "Key not found!!!"));
        }
    }
}
